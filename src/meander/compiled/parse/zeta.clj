(ns meander.compiled.parse.zeta (:require [meander.runtime.zeta]))
(clojure.core/defn
 parse
 [input__42248]
 (let*
  [ret__14518__auto__
   (clojure.core/letfn
    [(CATA__FN__42325
      [input__42248]
      (clojure.core/letfn
       [(state__43763
         []
         (if
          (clojure.core/vector? input__42248)
          (if
           (clojure.core/= (clojure.core/count input__42248) 3)
           (clojure.core/let
            [input__42248_nth_0__
             (clojure.core/nth input__42248 0)
             input__42248_nth_1__
             (clojure.core/nth input__42248 1)
             input__42248_nth_2__
             (clojure.core/nth input__42248 2)]
            (clojure.core/case
             input__42248_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__42248_nth_1__)
              (clojure.core/letfn
               [(state__43801
                 []
                 (clojure.core/case
                  input__42248_nth_1__
                  ([])
                  (clojure.core/let
                   [?env input__42248_nth_2__]
                   (try
                    [{:tag :empty}]
                    (catch
                     java.lang.Exception
                     e__16581__auto__
                     (if
                      (meander.runtime.zeta/fail? e__16581__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__16581__auto__)))))
                  (state__43802)))
                (state__43802
                 []
                 (clojure.core/let
                  [n__42334
                   (clojure.core/count input__42248_nth_1__)
                   m__42335
                   (clojure.core/max 0 (clojure.core/- n__42334 2))
                   input__42248_nth_1___l__
                   (clojure.core/subvec
                    input__42248_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__42248_nth_1__)
                     m__42335))
                   input__42248_nth_1___r__
                   (clojure.core/subvec input__42248_nth_1__ m__42335)]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__42248_nth_1___r__)
                    2)
                   (clojure.core/let
                    [!xs (clojure.core/vec input__42248_nth_1___l__)]
                    (if
                     (clojure.core/=
                      (clojure.core/count input__42248_nth_1___r__)
                      2)
                     (clojure.core/let
                      [input__42248_nth_1___r___nth_0__
                       (clojure.core/nth input__42248_nth_1___r__ 0)
                       input__42248_nth_1___r___nth_1__
                       (clojure.core/nth input__42248_nth_1___r__ 1)]
                      (clojure.core/case
                       input__42248_nth_1___r___nth_0__
                       (:meander.zeta/as)
                       (clojure.core/let
                        [?pattern input__42248_nth_1___r___nth_1__]
                        (clojure.core/let
                         [?env input__42248_nth_2__]
                         (try
                          [(clojure.core/let
                            [!xs__counter
                             (meander.runtime.zeta/iterator !xs)]
                            {:tag :as,
                             :pattern
                             (clojure.core/let
                              [CATA_RESULT__15641__auto__
                               (CATA__FN__42325 [?pattern ?env])]
                              (if
                               (meander.runtime.zeta/fail?
                                CATA_RESULT__15641__auto__)
                               (throw (meander.runtime.zeta/fail))
                               (clojure.core/nth
                                CATA_RESULT__15641__auto__
                                0))),
                             :next
                             (clojure.core/let
                              [CATA_RESULT__15641__auto__
                               (CATA__FN__42325
                                ['meander.dev.parse.zeta/parse-sequential
                                 (clojure.core/into
                                  []
                                  (clojure.core/vec
                                   (clojure.core/iterator-seq
                                    !xs__counter)))
                                 ?env])]
                              (if
                               (meander.runtime.zeta/fail?
                                CATA_RESULT__15641__auto__)
                               (throw (meander.runtime.zeta/fail))
                               (clojure.core/nth
                                CATA_RESULT__15641__auto__
                                0)))})]
                          (catch
                           java.lang.Exception
                           e__16581__auto__
                           (if
                            (meander.runtime.zeta/fail?
                             e__16581__auto__)
                            (meander.runtime.zeta/fail)
                            (throw e__16581__auto__))))))
                       (state__43764)))
                     (state__43764)))
                   (state__43764))))]
               (state__43801))
              (state__43764))
             (state__43764)))
           (state__43764))
          (state__43764)))
        (state__43764
         []
         (clojure.core/letfn
          [(def__42340
            [arg__42375 ?ns]
            (clojure.core/letfn
             [(state__43803
               []
               (clojure.core/let
                [x__42376 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__42376)
                 (clojure.core/let [?env arg__42375] [?env ?ns])
                 (state__43804))))
              (state__43804
               []
               (if
                (clojure.core/map? arg__42375)
                (clojure.core/let
                 [VAL__42377 (.valAt arg__42375 :aliases)]
                 (if
                  (clojure.core/map? VAL__42377)
                  (clojure.core/let
                   [X__42379 (clojure.core/set VAL__42377)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__42379))
                    (clojure.core/loop
                     [search_space__43805 (clojure.core/seq X__42379)]
                     (if
                      (clojure.core/seq search_space__43805)
                      (clojure.core/let
                       [elem__42380
                        (clojure.core/first search_space__43805)
                        result__43806
                        (clojure.core/let
                         [elem__42380_nth_0__
                          (clojure.core/nth elem__42380 0)
                          elem__42380_nth_1__
                          (clojure.core/nth elem__42380 1)]
                         (if
                          (clojure.core/symbol? elem__42380_nth_0__)
                          (clojure.core/let
                           [X__42382
                            (clojure.core/name elem__42380_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__42382)
                            (if
                             (clojure.core/symbol? elem__42380_nth_1__)
                             (clojure.core/let
                              [X__42384
                               (clojure.core/name elem__42380_nth_1__)]
                              (clojure.core/case
                               X__42384
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__42375]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__43806)
                        (recur (clojure.core/next search_space__43805))
                        result__43806))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__43803)))]
          (if
           (clojure.core/vector? input__42248)
           (if
            (clojure.core/= (clojure.core/count input__42248) 3)
            (clojure.core/let
             [input__42248_nth_0__
              (clojure.core/nth input__42248 0)
              input__42248_nth_1__
              (clojure.core/nth input__42248 1)
              input__42248_nth_2__
              (clojure.core/nth input__42248 2)]
             (clojure.core/case
              input__42248_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__42248_nth_1__)
               (clojure.core/loop
                [search_space__43808
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__42248_nth_1__)]
                (if
                 (clojure.core/seq search_space__43808)
                 (clojure.core/let
                  [input__42248_nth_1___parts__
                   (clojure.core/first search_space__43808)
                   result__43809
                   (clojure.core/let
                    [input__42248_nth_1___l__
                     (clojure.core/nth input__42248_nth_1___parts__ 0)
                     input__42248_nth_1___r__
                     (clojure.core/nth input__42248_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__42248_nth_1___l__)]
                     (clojure.core/let
                      [input__42248_nth_1___r___l__
                       (clojure.core/subvec
                        input__42248_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__42248_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__42248_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__42248_nth_1___r___r__
                         (clojure.core/subvec
                          input__42248_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__42248_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__42248_nth_1___r___l__
                           0)
                          input__42248_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__42248_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__42248_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__42349
                            (clojure.core/namespace
                             input__42248_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__42349]
                            (clojure.core/let
                             [X__42351
                              (clojure.core/name
                               input__42248_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__42351)
                              (clojure.core/let
                               [ret__42352
                                (clojure.core/re-matches
                                 #"&(\d+)"
                                 X__42351)]
                               (if
                                (clojure.core/some? ret__42352)
                                (if
                                 (clojure.core/vector? ret__42352)
                                 (if
                                  (clojure.core/=
                                   (clojure.core/count ret__42352)
                                   2)
                                  (clojure.core/let
                                   [ret__42352_nth_1__
                                    (clojure.core/nth ret__42352 1)]
                                   (clojure.core/let
                                    [?n ret__42352_nth_1__]
                                    (clojure.core/let
                                     [?pattern
                                      input__42248_nth_1___r___l___nth_1__]
                                     (clojure.core/let
                                      [?rest
                                       input__42248_nth_1___r___r__]
                                      (clojure.core/let
                                       [x__14338__auto__
                                        (def__42340
                                         input__42248_nth_2__
                                         ?ns)]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         x__14338__auto__)
                                        (meander.runtime.zeta/fail)
                                        (clojure.core/let
                                         [[?env ?ns] x__14338__auto__]
                                         (try
                                          [(clojure.core/let
                                            [!init__counter
                                             (meander.runtime.zeta/iterator
                                              !init)]
                                            (clojure.core/let
                                             [CATA_RESULT__15641__auto__
                                              (CATA__FN__42325
                                               ['meander.dev.parse.zeta/make-join
                                                (clojure.core/let
                                                 [CATA_RESULT__15641__auto__
                                                  (CATA__FN__42325
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    (clojure.core/into
                                                     []
                                                     (clojure.core/vec
                                                      (clojure.core/iterator-seq
                                                       !init__counter)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__15641__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__15641__auto__
                                                   0)))
                                                (clojure.core/let
                                                 [CATA_RESULT__15641__auto__
                                                  (CATA__FN__42325
                                                   ['meander.dev.parse.zeta/make-join
                                                    {:tag :slice,
                                                     :size
                                                     (Integer. ?n),
                                                     :pattern
                                                     (clojure.core/let
                                                      [CATA_RESULT__15641__auto__
                                                       (CATA__FN__42325
                                                        [?pattern
                                                         ?env])]
                                                      (if
                                                       (meander.runtime.zeta/fail?
                                                        CATA_RESULT__15641__auto__)
                                                       (throw
                                                        (meander.runtime.zeta/fail))
                                                       (clojure.core/nth
                                                        CATA_RESULT__15641__auto__
                                                        0)))}
                                                    (clojure.core/let
                                                     [CATA_RESULT__15641__auto__
                                                      (CATA__FN__42325
                                                       ['meander.dev.parse.zeta/parse-sequential
                                                        (clojure.core/into
                                                         []
                                                         ?rest)
                                                        ?env])]
                                                     (if
                                                      (meander.runtime.zeta/fail?
                                                       CATA_RESULT__15641__auto__)
                                                      (throw
                                                       (meander.runtime.zeta/fail))
                                                      (clojure.core/nth
                                                       CATA_RESULT__15641__auto__
                                                       0)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__15641__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__15641__auto__
                                                   0)))
                                                ?env])]
                                             (if
                                              (meander.runtime.zeta/fail?
                                               CATA_RESULT__15641__auto__)
                                              (throw
                                               (meander.runtime.zeta/fail))
                                              (clojure.core/nth
                                               CATA_RESULT__15641__auto__
                                               0))))]
                                          (catch
                                           java.lang.Exception
                                           e__16581__auto__
                                           (if
                                            (meander.runtime.zeta/fail?
                                             e__16581__auto__)
                                            (meander.runtime.zeta/fail)
                                            (throw
                                             e__16581__auto__)))))))))))
                                  (meander.runtime.zeta/fail))
                                 (meander.runtime.zeta/fail))
                                (meander.runtime.zeta/fail)))
                              (meander.runtime.zeta/fail)))))
                          (meander.runtime.zeta/fail))))
                       (meander.runtime.zeta/fail)))))]
                  (if
                   (meander.runtime.zeta/fail? result__43809)
                   (recur (clojure.core/next search_space__43808))
                   result__43809))
                 (state__43765)))
               (state__43765))
              (state__43765)))
            (state__43765))
           (state__43765))))
        (state__43765
         []
         (clojure.core/letfn
          [(def__42397
            [arg__42429 ?ns]
            (clojure.core/letfn
             [(state__43811
               []
               (clojure.core/let
                [x__42430 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__42430)
                 (clojure.core/let [?env arg__42429] [?env ?ns])
                 (state__43812))))
              (state__43812
               []
               (if
                (clojure.core/map? arg__42429)
                (clojure.core/let
                 [VAL__42431 (.valAt arg__42429 :aliases)]
                 (if
                  (clojure.core/map? VAL__42431)
                  (clojure.core/let
                   [X__42433 (clojure.core/set VAL__42431)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__42433))
                    (clojure.core/loop
                     [search_space__43813 (clojure.core/seq X__42433)]
                     (if
                      (clojure.core/seq search_space__43813)
                      (clojure.core/let
                       [elem__42434
                        (clojure.core/first search_space__43813)
                        result__43814
                        (clojure.core/let
                         [elem__42434_nth_0__
                          (clojure.core/nth elem__42434 0)
                          elem__42434_nth_1__
                          (clojure.core/nth elem__42434 1)]
                         (if
                          (clojure.core/symbol? elem__42434_nth_0__)
                          (clojure.core/let
                           [X__42436
                            (clojure.core/name elem__42434_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__42436)
                            (if
                             (clojure.core/symbol? elem__42434_nth_1__)
                             (clojure.core/let
                              [X__42438
                               (clojure.core/name elem__42434_nth_1__)]
                              (clojure.core/case
                               X__42438
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__42429]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__43814)
                        (recur (clojure.core/next search_space__43813))
                        result__43814))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__43811)))]
          (if
           (clojure.core/vector? input__42248)
           (if
            (clojure.core/= (clojure.core/count input__42248) 3)
            (clojure.core/let
             [input__42248_nth_0__
              (clojure.core/nth input__42248 0)
              input__42248_nth_1__
              (clojure.core/nth input__42248 1)
              input__42248_nth_2__
              (clojure.core/nth input__42248 2)]
             (clojure.core/case
              input__42248_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__42248_nth_1__)
               (clojure.core/loop
                [search_space__43816
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__42248_nth_1__)]
                (if
                 (clojure.core/seq search_space__43816)
                 (clojure.core/let
                  [input__42248_nth_1___parts__
                   (clojure.core/first search_space__43816)
                   result__43817
                   (clojure.core/let
                    [input__42248_nth_1___l__
                     (clojure.core/nth input__42248_nth_1___parts__ 0)
                     input__42248_nth_1___r__
                     (clojure.core/nth input__42248_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__42248_nth_1___l__)]
                     (clojure.core/let
                      [input__42248_nth_1___r___l__
                       (clojure.core/subvec
                        input__42248_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__42248_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__42248_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__42248_nth_1___r___r__
                         (clojure.core/subvec
                          input__42248_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__42248_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__42248_nth_1___r___l__
                           0)
                          input__42248_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__42248_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__42248_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__42406
                            (clojure.core/namespace
                             input__42248_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__42406]
                            (clojure.core/let
                             [X__42408
                              (clojure.core/name
                               input__42248_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__42408)
                              (if
                               (clojure.core/re-matches
                                #"&.*"
                                X__42408)
                               (clojure.core/let
                                [?pattern
                                 input__42248_nth_1___r___l___nth_1__]
                                (clojure.core/let
                                 [?rest input__42248_nth_1___r___r__]
                                 (clojure.core/let
                                  [x__14338__auto__
                                   (def__42397
                                    input__42248_nth_2__
                                    ?ns)]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    x__14338__auto__)
                                   (meander.runtime.zeta/fail)
                                   (clojure.core/let
                                    [[?env ?ns] x__14338__auto__]
                                    (try
                                     [(clojure.core/let
                                       [!init__counter
                                        (meander.runtime.zeta/iterator
                                         !init)]
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__42325
                                          ['meander.dev.parse.zeta/make-join
                                           (clojure.core/let
                                            [CATA_RESULT__15641__auto__
                                             (CATA__FN__42325
                                              ['meander.dev.parse.zeta/parse-sequential
                                               (clojure.core/into
                                                []
                                                (clojure.core/vec
                                                 (clojure.core/iterator-seq
                                                  !init__counter)))
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__15641__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__15641__auto__
                                              0)))
                                           (clojure.core/let
                                            [CATA_RESULT__15641__auto__
                                             (CATA__FN__42325
                                              ['meander.dev.parse.zeta/make-join
                                               (clojure.core/let
                                                [CATA_RESULT__15641__auto__
                                                 (CATA__FN__42325
                                                  [?pattern ?env])]
                                                (if
                                                 (meander.runtime.zeta/fail?
                                                  CATA_RESULT__15641__auto__)
                                                 (throw
                                                  (meander.runtime.zeta/fail))
                                                 (clojure.core/nth
                                                  CATA_RESULT__15641__auto__
                                                  0)))
                                               (clojure.core/let
                                                [CATA_RESULT__15641__auto__
                                                 (CATA__FN__42325
                                                  ['meander.dev.parse.zeta/parse-sequential
                                                   (clojure.core/into
                                                    []
                                                    ?rest)
                                                   ?env])]
                                                (if
                                                 (meander.runtime.zeta/fail?
                                                  CATA_RESULT__15641__auto__)
                                                 (throw
                                                  (meander.runtime.zeta/fail))
                                                 (clojure.core/nth
                                                  CATA_RESULT__15641__auto__
                                                  0)))
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__15641__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__15641__auto__
                                              0)))
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__15641__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__15641__auto__
                                          0))))]
                                     (catch
                                      java.lang.Exception
                                      e__16581__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__16581__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__16581__auto__)))))))))
                               (meander.runtime.zeta/fail))
                              (meander.runtime.zeta/fail)))))
                          (meander.runtime.zeta/fail))))
                       (meander.runtime.zeta/fail)))))]
                  (if
                   (meander.runtime.zeta/fail? result__43817)
                   (recur (clojure.core/next search_space__43816))
                   result__43817))
                 (state__43766)))
               (state__43766))
              (state__43766)))
            (state__43766))
           (state__43766))))
        (state__43766
         []
         (if
          (clojure.core/vector? input__42248)
          (clojure.core/letfn
           [(state__43819
             []
             (if
              (clojure.core/= (clojure.core/count input__42248) 3)
              (clojure.core/let
               [input__42248_nth_0__
                (clojure.core/nth input__42248 0)
                input__42248_nth_1__
                (clojure.core/nth input__42248 1)
                input__42248_nth_2__
                (clojure.core/nth input__42248 2)]
               (clojure.core/case
                input__42248_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__42248_nth_1__)
                 (clojure.core/letfn
                  [(state__43822
                    []
                    (clojure.core/let
                     [n__42459
                      (clojure.core/count input__42248_nth_1__)
                      m__42460
                      (clojure.core/max 0 (clojure.core/- n__42459 2))
                      input__42248_nth_1___l__
                      (clojure.core/subvec
                       input__42248_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__42248_nth_1__)
                        m__42460))
                      input__42248_nth_1___r__
                      (clojure.core/subvec
                       input__42248_nth_1__
                       m__42460)]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__42248_nth_1___r__)
                       2)
                      (clojure.core/let
                       [!xs
                        (clojure.core/vec input__42248_nth_1___l__)]
                       (if
                        (clojure.core/=
                         (clojure.core/count input__42248_nth_1___r__)
                         2)
                        (clojure.core/let
                         [input__42248_nth_1___r___nth_0__
                          (clojure.core/nth input__42248_nth_1___r__ 0)
                          input__42248_nth_1___r___nth_1__
                          (clojure.core/nth
                           input__42248_nth_1___r__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__42248_nth_1___r___nth_0__)
                          (clojure.core/let
                           [X__42464
                            (clojure.core/namespace
                             input__42248_nth_1___r___nth_0__)]
                           (clojure.core/let
                            [?ns X__42464]
                            (clojure.core/let
                             [X__42466
                              (clojure.core/name
                               input__42248_nth_1___r___nth_0__)]
                             (if
                              (clojure.core/string? X__42466)
                              (clojure.core/let
                               [ret__42467
                                (clojure.core/re-matches
                                 #"&.*"
                                 X__42466)]
                               (if
                                (clojure.core/some? ret__42467)
                                (clojure.core/let
                                 [?name ret__42467]
                                 (clojure.core/let
                                  [?pattern
                                   input__42248_nth_1___r___nth_1__]
                                  (if
                                   (clojure.core/map?
                                    input__42248_nth_2__)
                                   (clojure.core/let
                                    [VAL__42451
                                     (.valAt
                                      input__42248_nth_2__
                                      :aliases)]
                                    (if
                                     (clojure.core/map? VAL__42451)
                                     (clojure.core/let
                                      [X__42453
                                       (clojure.core/set VAL__42451)]
                                      (if
                                       (clojure.core/<=
                                        1
                                        (clojure.core/count X__42453))
                                       (clojure.core/loop
                                        [search_space__43826
                                         (clojure.core/seq X__42453)]
                                        (if
                                         (clojure.core/seq
                                          search_space__43826)
                                         (clojure.core/let
                                          [elem__42454
                                           (clojure.core/first
                                            search_space__43826)
                                           result__43827
                                           (clojure.core/let
                                            [elem__42454_nth_0__
                                             (clojure.core/nth
                                              elem__42454
                                              0)
                                             elem__42454_nth_1__
                                             (clojure.core/nth
                                              elem__42454
                                              1)]
                                            (if
                                             (clojure.core/symbol?
                                              elem__42454_nth_0__)
                                             (clojure.core/let
                                              [X__42456
                                               (clojure.core/name
                                                elem__42454_nth_0__)]
                                              (if
                                               (clojure.core/=
                                                ?ns
                                                X__42456)
                                               (if
                                                (clojure.core/symbol?
                                                 elem__42454_nth_1__)
                                                (clojure.core/let
                                                 [X__42458
                                                  (clojure.core/name
                                                   elem__42454_nth_1__)]
                                                 (clojure.core/case
                                                  X__42458
                                                  ("meander.zeta")
                                                  (clojure.core/let
                                                   [?env
                                                    input__42248_nth_2__]
                                                   (try
                                                    [(clojure.core/let
                                                      [!xs__counter
                                                       (meander.runtime.zeta/iterator
                                                        !xs)]
                                                      (clojure.core/let
                                                       [CATA_RESULT__15641__auto__
                                                        (CATA__FN__42325
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
                                                         CATA_RESULT__15641__auto__)
                                                        (throw
                                                         (meander.runtime.zeta/fail))
                                                        (clojure.core/nth
                                                         CATA_RESULT__15641__auto__
                                                         0))))]
                                                    (catch
                                                     java.lang.Exception
                                                     e__16581__auto__
                                                     (if
                                                      (meander.runtime.zeta/fail?
                                                       e__16581__auto__)
                                                      (meander.runtime.zeta/fail)
                                                      (throw
                                                       e__16581__auto__)))))
                                                  (meander.runtime.zeta/fail)))
                                                (meander.runtime.zeta/fail))
                                               (meander.runtime.zeta/fail)))
                                             (meander.runtime.zeta/fail)))]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            result__43827)
                                           (recur
                                            (clojure.core/next
                                             search_space__43826))
                                           result__43827))
                                         (state__43823)))
                                       (state__43823)))
                                     (state__43823)))
                                   (state__43823))))
                                (state__43823)))
                              (state__43823)))))
                          (state__43823)))
                        (state__43823)))
                      (state__43823))))
                   (state__43823
                    []
                    (clojure.core/loop
                     [search_space__43829
                      (meander.runtime.zeta/epsilon-partitions
                       2
                       input__42248_nth_1__)]
                     (if
                      (clojure.core/seq search_space__43829)
                      (clojure.core/let
                       [input__42248_nth_1___parts__
                        (clojure.core/first search_space__43829)
                        result__43830
                        (clojure.core/let
                         [input__42248_nth_1___l__
                          (clojure.core/nth
                           input__42248_nth_1___parts__
                           0)
                          input__42248_nth_1___r__
                          (clojure.core/nth
                           input__42248_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs
                           (clojure.core/vec input__42248_nth_1___l__)]
                          (clojure.core/let
                           [input__42248_nth_1___r___l__
                            (clojure.core/subvec
                             input__42248_nth_1___r__
                             0
                             (clojure.core/min
                              (clojure.core/count
                               input__42248_nth_1___r__)
                              1))]
                           (if
                            (clojure.core/=
                             (clojure.core/count
                              input__42248_nth_1___r___l__)
                             1)
                            (clojure.core/let
                             [input__42248_nth_1___r___r__
                              (clojure.core/subvec
                               input__42248_nth_1___r__
                               1)]
                             (if
                              (clojure.core/=
                               input__42248_nth_1___r___l__
                               ['.])
                              (clojure.core/let
                               [?rest input__42248_nth_1___r___r__]
                               (clojure.core/let
                                [?env input__42248_nth_2__]
                                (try
                                 [(clojure.core/let
                                   [!xs__counter
                                    (meander.runtime.zeta/iterator
                                     !xs)]
                                   (clojure.core/let
                                    [CATA_RESULT__15641__auto__
                                     (CATA__FN__42325
                                      ['meander.dev.parse.zeta/make-join
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__42325
                                          ['meander.dev.parse.zeta/parse-sequential
                                           (clojure.core/into
                                            []
                                            (clojure.core/vec
                                             (clojure.core/iterator-seq
                                              !xs__counter)))
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__15641__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__15641__auto__
                                          0)))
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__42325
                                          ['meander.dev.parse.zeta/parse-sequential
                                           ?rest
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__15641__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__15641__auto__
                                          0)))
                                       ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__15641__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__15641__auto__
                                      0))))]
                                 (catch
                                  java.lang.Exception
                                  e__16581__auto__
                                  (if
                                   (meander.runtime.zeta/fail?
                                    e__16581__auto__)
                                   (meander.runtime.zeta/fail)
                                   (throw e__16581__auto__))))))
                              (meander.runtime.zeta/fail)))
                            (meander.runtime.zeta/fail)))))]
                       (if
                        (meander.runtime.zeta/fail? result__43830)
                        (recur (clojure.core/next search_space__43829))
                        result__43830))
                      (state__43824))))
                   (state__43824
                    []
                    (clojure.core/let
                     [input__42248_nth_1___l__
                      (clojure.core/subvec
                       input__42248_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__42248_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__42248_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__42248_nth_1___r__
                        (clojure.core/subvec input__42248_nth_1__ 1)]
                       (if
                        (clojure.core/=
                         input__42248_nth_1___l__
                         ['...])
                        (clojure.core/let
                         [?rest input__42248_nth_1___r__]
                         (clojure.core/let
                          [?env input__42248_nth_2__]
                          (try
                           [(clojure.core/let
                             [CATA_RESULT__15641__auto__
                              (CATA__FN__42325
                               ['meander.dev.parse.zeta/parse-sequential
                                ?rest
                                ?env])]
                             (if
                              (meander.runtime.zeta/fail?
                               CATA_RESULT__15641__auto__)
                              (throw (meander.runtime.zeta/fail))
                              (clojure.core/nth
                               CATA_RESULT__15641__auto__
                               0)))]
                           (catch
                            java.lang.Exception
                            e__16581__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__16581__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__16581__auto__))))))
                        (state__43825)))
                      (state__43825))))
                   (state__43825
                    []
                    (clojure.core/loop
                     [search_space__43832
                      (meander.runtime.zeta/epsilon-partitions
                       2
                       input__42248_nth_1__)]
                     (if
                      (clojure.core/seq search_space__43832)
                      (clojure.core/let
                       [input__42248_nth_1___parts__
                        (clojure.core/first search_space__43832)
                        result__43833
                        (clojure.core/let
                         [input__42248_nth_1___l__
                          (clojure.core/nth
                           input__42248_nth_1___parts__
                           0)
                          input__42248_nth_1___r__
                          (clojure.core/nth
                           input__42248_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs []]
                          (clojure.core/let
                           [ret__14502__auto__
                            (meander.runtime.zeta/epsilon-run-star-1
                             input__42248_nth_1___l__
                             [!xs]
                             (clojure.core/fn
                              [[!xs] input__42484]
                              (clojure.core/let
                               [input__42484_nth_0__
                                (clojure.core/nth input__42484 0)]
                               (clojure.core/letfn
                                [(save__42485
                                  []
                                  (meander.runtime.zeta/fail))
                                 (f__43836
                                  []
                                  (clojure.core/let
                                   [!xs
                                    (clojure.core/conj
                                     !xs
                                     input__42484_nth_0__)]
                                   [!xs]))]
                                (if
                                 (clojure.core/symbol?
                                  input__42484_nth_0__)
                                 (clojure.core/let
                                  [X__42487
                                   (clojure.core/namespace
                                    input__42484_nth_0__)]
                                  (clojure.core/case
                                   X__42487
                                   (nil)
                                   (clojure.core/let
                                    [X__42489
                                     (clojure.core/name
                                      input__42484_nth_0__)]
                                    (if
                                     (clojure.core/string? X__42489)
                                     (if
                                      (clojure.core/re-matches
                                       #"\.\.(?:\.|\d+)"
                                       X__42489)
                                      (save__42485)
                                      (f__43836))
                                     (f__43836)))
                                   (f__43836)))
                                 (f__43836)))))
                             (clojure.core/fn
                              [[!xs]]
                              (clojure.core/let
                               [input__42248_nth_1___r___l__
                                (clojure.core/subvec
                                 input__42248_nth_1___r__
                                 0
                                 (clojure.core/min
                                  (clojure.core/count
                                   input__42248_nth_1___r__)
                                  1))]
                               (if
                                (clojure.core/=
                                 (clojure.core/count
                                  input__42248_nth_1___r___l__)
                                 1)
                                (clojure.core/let
                                 [input__42248_nth_1___r___r__
                                  (clojure.core/subvec
                                   input__42248_nth_1___r__
                                   1)]
                                 (if
                                  (clojure.core/=
                                   input__42248_nth_1___r___l__
                                   ['...])
                                  (clojure.core/let
                                   [?rest input__42248_nth_1___r___r__]
                                   (clojure.core/let
                                    [?env input__42248_nth_2__]
                                    (try
                                     [(clojure.core/let
                                       [!xs__counter
                                        (meander.runtime.zeta/iterator
                                         !xs)]
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__42325
                                          ['meander.dev.parse.zeta/make-star
                                           (clojure.core/let
                                            [CATA_RESULT__15641__auto__
                                             (CATA__FN__42325
                                              ['meander.dev.parse.zeta/parse-sequential
                                               (clojure.core/into
                                                []
                                                (clojure.core/vec
                                                 (clojure.core/iterator-seq
                                                  !xs__counter)))
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__15641__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__15641__auto__
                                              0)))
                                           (clojure.core/let
                                            [CATA_RESULT__15641__auto__
                                             (CATA__FN__42325
                                              ['meander.dev.parse.zeta/parse-sequential
                                               ?rest
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__15641__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__15641__auto__
                                              0)))
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__15641__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__15641__auto__
                                          0))))]
                                     (catch
                                      java.lang.Exception
                                      e__16581__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__16581__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__16581__auto__))))))
                                  (meander.runtime.zeta/fail)))
                                (meander.runtime.zeta/fail)))))]
                           (if
                            (meander.runtime.zeta/fail?
                             ret__14502__auto__)
                            (meander.runtime.zeta/fail)
                            ret__14502__auto__))))]
                       (if
                        (meander.runtime.zeta/fail? result__43833)
                        (recur (clojure.core/next search_space__43832))
                        result__43833))
                      (state__43820))))]
                  (state__43822))
                 (state__43820))
                (state__43820)))
              (state__43820)))
            (state__43820
             []
             (if
              (clojure.core/= (clojure.core/count input__42248) 4)
              (clojure.core/let
               [input__42248_nth_0__
                (clojure.core/nth input__42248 0)
                input__42248_nth_1__
                (clojure.core/nth input__42248 1)
                input__42248_nth_2__
                (clojure.core/nth input__42248 2)]
               (clojure.core/letfn
                [(state__43837
                  []
                  (clojure.core/let
                   [input__42248_nth_3__
                    (clojure.core/nth input__42248 3)]
                   (clojure.core/case
                    input__42248_nth_0__
                    (meander.dev.parse.zeta/make-star)
                    (clojure.core/letfn
                     [(state__43839
                       []
                       (if
                        (clojure.core/map? input__42248_nth_1__)
                        (clojure.core/let
                         [VAL__42493
                          (.valAt input__42248_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__42493
                          (:cat)
                          (clojure.core/let
                           [VAL__42494
                            (.valAt input__42248_nth_1__ :sequence)]
                           (if
                            (clojure.core/vector? VAL__42494)
                            (if
                             (clojure.core/=
                              (clojure.core/count VAL__42494)
                              1)
                             (clojure.core/let
                              [VAL__42494_nth_0__
                               (clojure.core/nth VAL__42494 0)]
                              (if
                               (clojure.core/map? VAL__42494_nth_0__)
                               (clojure.core/let
                                [VAL__42499
                                 (.valAt VAL__42494_nth_0__ :tag)]
                                (clojure.core/case
                                 VAL__42499
                                 (:memory-variable)
                                 (clojure.core/let
                                  [?memory-variable VAL__42494_nth_0__]
                                  (clojure.core/let
                                   [VAL__42495
                                    (.valAt
                                     input__42248_nth_1__
                                     :next)]
                                   (if
                                    (clojure.core/map? VAL__42495)
                                    (clojure.core/let
                                     [VAL__42496
                                      (.valAt VAL__42495 :tag)]
                                     (clojure.core/case
                                      VAL__42496
                                      (:empty)
                                      (clojure.core/let
                                       [?next input__42248_nth_2__]
                                       (clojure.core/let
                                        [?env input__42248_nth_3__]
                                        (try
                                         [(clojure.core/let
                                           [CATA_RESULT__15641__auto__
                                            (CATA__FN__42325
                                             ['meander.dev.parse.zeta/make-join
                                              {:tag :into,
                                               :memory-variable
                                               ?memory-variable}
                                              ?next
                                              ?env])]
                                           (if
                                            (meander.runtime.zeta/fail?
                                             CATA_RESULT__15641__auto__)
                                            (throw
                                             (meander.runtime.zeta/fail))
                                            (clojure.core/nth
                                             CATA_RESULT__15641__auto__
                                             0)))]
                                         (catch
                                          java.lang.Exception
                                          e__16581__auto__
                                          (if
                                           (meander.runtime.zeta/fail?
                                            e__16581__auto__)
                                           (meander.runtime.zeta/fail)
                                           (throw
                                            e__16581__auto__))))))
                                      (state__43840)))
                                    (state__43840))))
                                 (state__43840)))
                               (state__43840)))
                             (state__43840))
                            (state__43840)))
                          (state__43840)))
                        (state__43840)))
                      (state__43840
                       []
                       (clojure.core/let
                        [?pattern input__42248_nth_1__]
                        (clojure.core/let
                         [?next input__42248_nth_2__]
                         (if
                          (clojure.core/map? input__42248_nth_3__)
                          (clojure.core/let
                           [VAL__42502
                            (.valAt input__42248_nth_3__ :context)]
                           (clojure.core/case
                            VAL__42502
                            (:string)
                            (try
                             [{:tag :string-star,
                               :greedy? false,
                               :pattern ?pattern,
                               :next ?next}]
                             (catch
                              java.lang.Exception
                              e__16581__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__16581__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__16581__auto__))))
                            (state__43838)))
                          (state__43838)))))]
                     (state__43839))
                    (state__43838))))
                 (state__43838
                  []
                  (clojure.core/case
                   input__42248_nth_0__
                   (meander.dev.parse.zeta/make-star)
                   (clojure.core/let
                    [?pattern input__42248_nth_1__]
                    (clojure.core/let
                     [?next input__42248_nth_2__]
                     (try
                      [{:tag :star,
                        :greedy? false,
                        :pattern ?pattern,
                        :next ?next}]
                      (catch
                       java.lang.Exception
                       e__16581__auto__
                       (if
                        (meander.runtime.zeta/fail? e__16581__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__16581__auto__))))))
                   (state__43821)))]
                (state__43837)))
              (state__43821)))
            (state__43821
             []
             (if
              (clojure.core/= (clojure.core/count input__42248) 3)
              (clojure.core/let
               [input__42248_nth_0__
                (clojure.core/nth input__42248 0)
                input__42248_nth_1__
                (clojure.core/nth input__42248 1)
                input__42248_nth_2__
                (clojure.core/nth input__42248 2)]
               (clojure.core/case
                input__42248_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__42248_nth_1__)
                 (clojure.core/let
                  [input__42248_nth_1___l__
                   (clojure.core/subvec
                    input__42248_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__42248_nth_1__)
                     1))]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__42248_nth_1___l__)
                    1)
                   (clojure.core/let
                    [input__42248_nth_1___r__
                     (clojure.core/subvec input__42248_nth_1__ 1)]
                    (clojure.core/let
                     [input__42248_nth_1___l___nth_0__
                      (clojure.core/nth input__42248_nth_1___l__ 0)]
                     (if
                      (clojure.core/symbol?
                       input__42248_nth_1___l___nth_0__)
                      (clojure.core/let
                       [X__42510
                        (clojure.core/namespace
                         input__42248_nth_1___l___nth_0__)]
                       (clojure.core/case
                        X__42510
                        (nil)
                        (clojure.core/let
                         [X__42512
                          (clojure.core/name
                           input__42248_nth_1___l___nth_0__)]
                         (if
                          (clojure.core/string? X__42512)
                          (clojure.core/let
                           [ret__42513
                            (clojure.core/re-matches
                             #"\.\.(\d+)"
                             X__42512)]
                           (if
                            (clojure.core/some? ret__42513)
                            (if
                             (clojure.core/vector? ret__42513)
                             (if
                              (clojure.core/=
                               (clojure.core/count ret__42513)
                               2)
                              (clojure.core/let
                               [ret__42513_nth_1__
                                (clojure.core/nth ret__42513 1)]
                               (clojure.core/let
                                [?n ret__42513_nth_1__]
                                (clojure.core/let
                                 [?operator
                                  input__42248_nth_1___l___nth_0__]
                                 (clojure.core/let
                                  [?rest input__42248_nth_1___r__]
                                  (clojure.core/let
                                   [?env input__42248_nth_2__]
                                   (try
                                    [{:tag :syntax-error,
                                      :message
                                      "The n or more operator ..N must be preceeded by at least one pattern"}]
                                    (catch
                                     java.lang.Exception
                                     e__16581__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__16581__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__16581__auto__)))))))))
                              (state__43767))
                             (state__43767))
                            (state__43767)))
                          (state__43767)))
                        (state__43767)))
                      (state__43767))))
                   (state__43767)))
                 (state__43767))
                (state__43767)))
              (state__43767)))]
           (state__43819))
          (state__43767)))
        (state__43767
         []
         (clojure.core/letfn
          [(def__42516
            [arg__42540]
            (clojure.core/letfn
             [(state__43841
               []
               (clojure.core/let
                [x__42541 :string-plus]
                (clojure.core/let
                 [?tag x__42541]
                 (if
                  (clojure.core/map? arg__42540)
                  (clojure.core/let
                   [VAL__42542 (.valAt arg__42540 :context)]
                   (clojure.core/case
                    VAL__42542
                    (:string)
                    (clojure.core/let [?env arg__42540] [?tag ?env])
                    (state__43842)))
                  (state__43842)))))
              (state__43842
               []
               (clojure.core/let
                [x__42543 :plus]
                (clojure.core/let
                 [?tag x__42543]
                 (clojure.core/let [?env arg__42540] [?tag ?env]))))]
             (state__43841)))]
          (if
           (clojure.core/vector? input__42248)
           (if
            (clojure.core/= (clojure.core/count input__42248) 3)
            (clojure.core/let
             [input__42248_nth_0__
              (clojure.core/nth input__42248 0)
              input__42248_nth_1__
              (clojure.core/nth input__42248 1)
              input__42248_nth_2__
              (clojure.core/nth input__42248 2)]
             (clojure.core/case
              input__42248_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__42248_nth_1__)
               (clojure.core/loop
                [search_space__43843
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__42248_nth_1__)]
                (if
                 (clojure.core/seq search_space__43843)
                 (clojure.core/let
                  [input__42248_nth_1___parts__
                   (clojure.core/first search_space__43843)
                   result__43844
                   (clojure.core/let
                    [input__42248_nth_1___l__
                     (clojure.core/nth input__42248_nth_1___parts__ 0)
                     input__42248_nth_1___r__
                     (clojure.core/nth input__42248_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__14502__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__42248_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__42533]
                         (clojure.core/let
                          [input__42533_nth_0__
                           (clojure.core/nth input__42533 0)]
                          (clojure.core/letfn
                           [(save__42534
                             []
                             (meander.runtime.zeta/fail))
                            (f__43847
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__42533_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol? input__42533_nth_0__)
                            (clojure.core/let
                             [X__42536
                              (clojure.core/namespace
                               input__42533_nth_0__)]
                             (clojure.core/case
                              X__42536
                              (nil)
                              (clojure.core/let
                               [X__42538
                                (clojure.core/name
                                 input__42533_nth_0__)]
                               (if
                                (clojure.core/string? X__42538)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__42538)
                                 (save__42534)
                                 (f__43847))
                                (f__43847)))
                              (f__43847)))
                            (f__43847)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__42248_nth_1___r___l__
                           (clojure.core/subvec
                            input__42248_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__42248_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__42248_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__42248_nth_1___r___r__
                             (clojure.core/subvec
                              input__42248_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__42248_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__42248_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__42248_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__42527
                                (clojure.core/namespace
                                 input__42248_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__42527
                                (nil)
                                (clojure.core/let
                                 [X__42529
                                  (clojure.core/name
                                   input__42248_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__42529)
                                  (clojure.core/let
                                   [ret__42530
                                    (clojure.core/re-matches
                                     #"\.\.(\d+)"
                                     X__42529)]
                                   (if
                                    (clojure.core/some? ret__42530)
                                    (if
                                     (clojure.core/vector? ret__42530)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__42530)
                                       2)
                                      (clojure.core/let
                                       [ret__42530_nth_1__
                                        (clojure.core/nth
                                         ret__42530
                                         1)]
                                       (clojure.core/let
                                        [?n ret__42530_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__42248_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__14338__auto__
                                           (def__42516
                                            input__42248_nth_2__)]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            x__14338__auto__)
                                           (meander.runtime.zeta/fail)
                                           (clojure.core/let
                                            [[?tag ?env]
                                             x__14338__auto__]
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
                                                 [CATA_RESULT__15641__auto__
                                                  (CATA__FN__42325
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    (clojure.core/into
                                                     []
                                                     (clojure.core/vec
                                                      (clojure.core/iterator-seq
                                                       !xs__counter)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__15641__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__15641__auto__
                                                   0))),
                                                :next
                                                (clojure.core/let
                                                 [CATA_RESULT__15641__auto__
                                                  (CATA__FN__42325
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    ?rest
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__15641__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__15641__auto__
                                                   0)))})]
                                             (catch
                                              java.lang.Exception
                                              e__16581__auto__
                                              (if
                                               (meander.runtime.zeta/fail?
                                                e__16581__auto__)
                                               (meander.runtime.zeta/fail)
                                               (throw
                                                e__16581__auto__))))))))))
                                      (meander.runtime.zeta/fail))
                                     (meander.runtime.zeta/fail))
                                    (meander.runtime.zeta/fail)))
                                  (meander.runtime.zeta/fail)))
                                (meander.runtime.zeta/fail)))
                              (meander.runtime.zeta/fail))))
                           (meander.runtime.zeta/fail)))))]
                      (if
                       (meander.runtime.zeta/fail? ret__14502__auto__)
                       (meander.runtime.zeta/fail)
                       ret__14502__auto__))))]
                  (if
                   (meander.runtime.zeta/fail? result__43844)
                   (recur (clojure.core/next search_space__43843))
                   result__43844))
                 (state__43768)))
               (state__43768))
              (state__43768)))
            (state__43768))
           (state__43768))))
        (state__43768
         []
         (if
          (clojure.core/vector? input__42248)
          (if
           (clojure.core/= (clojure.core/count input__42248) 3)
           (clojure.core/let
            [input__42248_nth_0__
             (clojure.core/nth input__42248 0)
             input__42248_nth_1__
             (clojure.core/nth input__42248 1)
             input__42248_nth_2__
             (clojure.core/nth input__42248 2)]
            (clojure.core/case
             input__42248_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__42248_nth_1__)
              (clojure.core/let
               [input__42248_nth_1___l__
                (clojure.core/subvec
                 input__42248_nth_1__
                 0
                 (clojure.core/min
                  (clojure.core/count input__42248_nth_1__)
                  1))]
               (if
                (clojure.core/=
                 (clojure.core/count input__42248_nth_1___l__)
                 1)
                (clojure.core/let
                 [input__42248_nth_1___r__
                  (clojure.core/subvec input__42248_nth_1__ 1)]
                 (clojure.core/let
                  [input__42248_nth_1___l___nth_0__
                   (clojure.core/nth input__42248_nth_1___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__42248_nth_1___l___nth_0__)
                   (clojure.core/let
                    [X__42561
                     (clojure.core/namespace
                      input__42248_nth_1___l___nth_0__)]
                    (clojure.core/case
                     X__42561
                     (nil)
                     (clojure.core/let
                      [X__42563
                       (clojure.core/name
                        input__42248_nth_1___l___nth_0__)]
                      (if
                       (clojure.core/string? X__42563)
                       (clojure.core/let
                        [ret__42564
                         (clojure.core/re-matches
                          #"\.\.(\?.+)"
                          X__42563)]
                        (if
                         (clojure.core/some? ret__42564)
                         (if
                          (clojure.core/vector? ret__42564)
                          (if
                           (clojure.core/=
                            (clojure.core/count ret__42564)
                            2)
                           (clojure.core/let
                            [ret__42564_nth_1__
                             (clojure.core/nth ret__42564 1)]
                            (clojure.core/let
                             [?n ret__42564_nth_1__]
                             (clojure.core/let
                              [?operator
                               input__42248_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?rest input__42248_nth_1___r__]
                               (clojure.core/let
                                [?env input__42248_nth_2__]
                                (try
                                 [{:tag :syntax-error,
                                   :message
                                   "The ?n or more operator ..?n must be preceeded by at least one pattern"}]
                                 (catch
                                  java.lang.Exception
                                  e__16581__auto__
                                  (if
                                   (meander.runtime.zeta/fail?
                                    e__16581__auto__)
                                   (meander.runtime.zeta/fail)
                                   (throw e__16581__auto__)))))))))
                           (state__43769))
                          (state__43769))
                         (state__43769)))
                       (state__43769)))
                     (state__43769)))
                   (state__43769))))
                (state__43769)))
              (state__43769))
             (state__43769)))
           (state__43769))
          (state__43769)))
        (state__43769
         []
         (clojure.core/letfn
          [(def__42567
            [arg__42591]
            (clojure.core/letfn
             [(state__43848
               []
               (clojure.core/let
                [x__42592 :string-logical-plus]
                (clojure.core/let
                 [?tag x__42592]
                 (if
                  (clojure.core/map? arg__42591)
                  (clojure.core/let
                   [VAL__42593 (.valAt arg__42591 :context)]
                   (clojure.core/case
                    VAL__42593
                    (:string)
                    (clojure.core/let [?env arg__42591] [?tag ?env])
                    (state__43849)))
                  (state__43849)))))
              (state__43849
               []
               (clojure.core/let
                [x__42594 :logical-plus]
                (clojure.core/let
                 [?tag x__42594]
                 (clojure.core/let [?env arg__42591] [?tag ?env]))))]
             (state__43848)))]
          (if
           (clojure.core/vector? input__42248)
           (if
            (clojure.core/= (clojure.core/count input__42248) 3)
            (clojure.core/let
             [input__42248_nth_0__
              (clojure.core/nth input__42248 0)
              input__42248_nth_1__
              (clojure.core/nth input__42248 1)
              input__42248_nth_2__
              (clojure.core/nth input__42248 2)]
             (clojure.core/case
              input__42248_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__42248_nth_1__)
               (clojure.core/loop
                [search_space__43850
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__42248_nth_1__)]
                (if
                 (clojure.core/seq search_space__43850)
                 (clojure.core/let
                  [input__42248_nth_1___parts__
                   (clojure.core/first search_space__43850)
                   result__43851
                   (clojure.core/let
                    [input__42248_nth_1___l__
                     (clojure.core/nth input__42248_nth_1___parts__ 0)
                     input__42248_nth_1___r__
                     (clojure.core/nth input__42248_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__14502__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__42248_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__42584]
                         (clojure.core/let
                          [input__42584_nth_0__
                           (clojure.core/nth input__42584 0)]
                          (clojure.core/letfn
                           [(save__42585
                             []
                             (meander.runtime.zeta/fail))
                            (f__43854
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__42584_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol? input__42584_nth_0__)
                            (clojure.core/let
                             [X__42587
                              (clojure.core/namespace
                               input__42584_nth_0__)]
                             (clojure.core/case
                              X__42587
                              (nil)
                              (clojure.core/let
                               [X__42589
                                (clojure.core/name
                                 input__42584_nth_0__)]
                               (if
                                (clojure.core/string? X__42589)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__42589)
                                 (save__42585)
                                 (f__43854))
                                (f__43854)))
                              (f__43854)))
                            (f__43854)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__42248_nth_1___r___l__
                           (clojure.core/subvec
                            input__42248_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__42248_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__42248_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__42248_nth_1___r___r__
                             (clojure.core/subvec
                              input__42248_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__42248_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__42248_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__42248_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__42578
                                (clojure.core/namespace
                                 input__42248_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__42578
                                (nil)
                                (clojure.core/let
                                 [X__42580
                                  (clojure.core/name
                                   input__42248_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__42580)
                                  (clojure.core/let
                                   [ret__42581
                                    (clojure.core/re-matches
                                     #"\.\.(\?.+)"
                                     X__42580)]
                                   (if
                                    (clojure.core/some? ret__42581)
                                    (if
                                     (clojure.core/vector? ret__42581)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__42581)
                                       2)
                                      (clojure.core/let
                                       [ret__42581_nth_1__
                                        (clojure.core/nth
                                         ret__42581
                                         1)]
                                       (clojure.core/let
                                        [?n ret__42581_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__42248_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__14338__auto__
                                           (def__42567
                                            input__42248_nth_2__)]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            x__14338__auto__)
                                           (meander.runtime.zeta/fail)
                                           (clojure.core/let
                                            [[?tag ?env]
                                             x__14338__auto__]
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
                                                 [CATA_RESULT__15641__auto__
                                                  (CATA__FN__42325
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    (clojure.core/into
                                                     []
                                                     (clojure.core/vec
                                                      (clojure.core/iterator-seq
                                                       !xs__counter)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__15641__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__15641__auto__
                                                   0))),
                                                :next
                                                (clojure.core/let
                                                 [CATA_RESULT__15641__auto__
                                                  (CATA__FN__42325
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    ?rest
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__15641__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__15641__auto__
                                                   0)))})]
                                             (catch
                                              java.lang.Exception
                                              e__16581__auto__
                                              (if
                                               (meander.runtime.zeta/fail?
                                                e__16581__auto__)
                                               (meander.runtime.zeta/fail)
                                               (throw
                                                e__16581__auto__))))))))))
                                      (meander.runtime.zeta/fail))
                                     (meander.runtime.zeta/fail))
                                    (meander.runtime.zeta/fail)))
                                  (meander.runtime.zeta/fail)))
                                (meander.runtime.zeta/fail)))
                              (meander.runtime.zeta/fail))))
                           (meander.runtime.zeta/fail)))))]
                      (if
                       (meander.runtime.zeta/fail? ret__14502__auto__)
                       (meander.runtime.zeta/fail)
                       ret__14502__auto__))))]
                  (if
                   (meander.runtime.zeta/fail? result__43851)
                   (recur (clojure.core/next search_space__43850))
                   result__43851))
                 (state__43770)))
               (state__43770))
              (state__43770)))
            (state__43770))
           (state__43770))))
        (state__43770
         []
         (if
          (clojure.core/vector? input__42248)
          (if
           (clojure.core/= (clojure.core/count input__42248) 3)
           (clojure.core/let
            [input__42248_nth_0__
             (clojure.core/nth input__42248 0)
             input__42248_nth_1__
             (clojure.core/nth input__42248 1)
             input__42248_nth_2__
             (clojure.core/nth input__42248 2)]
            (clojure.core/case
             input__42248_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__42248_nth_1__)
              (clojure.core/let
               [input__42248_nth_1___l__
                (clojure.core/subvec
                 input__42248_nth_1__
                 0
                 (clojure.core/min
                  (clojure.core/count input__42248_nth_1__)
                  1))]
               (if
                (clojure.core/=
                 (clojure.core/count input__42248_nth_1___l__)
                 1)
                (clojure.core/let
                 [input__42248_nth_1___r__
                  (clojure.core/subvec input__42248_nth_1__ 1)]
                 (clojure.core/let
                  [input__42248_nth_1___l___nth_0__
                   (clojure.core/nth input__42248_nth_1___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__42248_nth_1___l___nth_0__)
                   (clojure.core/let
                    [X__42612
                     (clojure.core/namespace
                      input__42248_nth_1___l___nth_0__)]
                    (clojure.core/case
                     X__42612
                     (nil)
                     (clojure.core/let
                      [X__42614
                       (clojure.core/name
                        input__42248_nth_1___l___nth_0__)]
                      (if
                       (clojure.core/string? X__42614)
                       (clojure.core/let
                        [ret__42615
                         (clojure.core/re-matches
                          #"\.\.(!.+)"
                          X__42614)]
                        (if
                         (clojure.core/some? ret__42615)
                         (if
                          (clojure.core/vector? ret__42615)
                          (if
                           (clojure.core/=
                            (clojure.core/count ret__42615)
                            2)
                           (clojure.core/let
                            [ret__42615_nth_1__
                             (clojure.core/nth ret__42615 1)]
                            (clojure.core/let
                             [?n ret__42615_nth_1__]
                             (clojure.core/let
                              [?operator
                               input__42248_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?rest input__42248_nth_1___r__]
                               (clojure.core/let
                                [?env input__42248_nth_2__]
                                (try
                                 [{:tag :syntax-error,
                                   :message
                                   "The operator ..!n must be preceeded by at least one pattern"}]
                                 (catch
                                  java.lang.Exception
                                  e__16581__auto__
                                  (if
                                   (meander.runtime.zeta/fail?
                                    e__16581__auto__)
                                   (meander.runtime.zeta/fail)
                                   (throw e__16581__auto__)))))))))
                           (state__43771))
                          (state__43771))
                         (state__43771)))
                       (state__43771)))
                     (state__43771)))
                   (state__43771))))
                (state__43771)))
              (state__43771))
             (state__43771)))
           (state__43771))
          (state__43771)))
        (state__43771
         []
         (clojure.core/letfn
          [(def__42618
            [arg__42642]
            (clojure.core/letfn
             [(state__43855
               []
               (clojure.core/let
                [x__42643 :string-memory-plus]
                (clojure.core/let
                 [?tag x__42643]
                 (if
                  (clojure.core/map? arg__42642)
                  (clojure.core/let
                   [VAL__42644 (.valAt arg__42642 :context)]
                   (clojure.core/case
                    VAL__42644
                    (:string)
                    (clojure.core/let [?env arg__42642] [?tag ?env])
                    (state__43856)))
                  (state__43856)))))
              (state__43856
               []
               (clojure.core/let
                [x__42645 :memory-plus]
                (clojure.core/let
                 [?tag x__42645]
                 (clojure.core/let [?env arg__42642] [?tag ?env]))))]
             (state__43855)))]
          (if
           (clojure.core/vector? input__42248)
           (if
            (clojure.core/= (clojure.core/count input__42248) 3)
            (clojure.core/let
             [input__42248_nth_0__
              (clojure.core/nth input__42248 0)
              input__42248_nth_1__
              (clojure.core/nth input__42248 1)
              input__42248_nth_2__
              (clojure.core/nth input__42248 2)]
             (clojure.core/case
              input__42248_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__42248_nth_1__)
               (clojure.core/loop
                [search_space__43857
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__42248_nth_1__)]
                (if
                 (clojure.core/seq search_space__43857)
                 (clojure.core/let
                  [input__42248_nth_1___parts__
                   (clojure.core/first search_space__43857)
                   result__43858
                   (clojure.core/let
                    [input__42248_nth_1___l__
                     (clojure.core/nth input__42248_nth_1___parts__ 0)
                     input__42248_nth_1___r__
                     (clojure.core/nth input__42248_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__14502__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__42248_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__42635]
                         (clojure.core/let
                          [input__42635_nth_0__
                           (clojure.core/nth input__42635 0)]
                          (clojure.core/letfn
                           [(save__42636
                             []
                             (meander.runtime.zeta/fail))
                            (f__43861
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__42635_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol? input__42635_nth_0__)
                            (clojure.core/let
                             [X__42638
                              (clojure.core/namespace
                               input__42635_nth_0__)]
                             (clojure.core/case
                              X__42638
                              (nil)
                              (clojure.core/let
                               [X__42640
                                (clojure.core/name
                                 input__42635_nth_0__)]
                               (if
                                (clojure.core/string? X__42640)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__42640)
                                 (save__42636)
                                 (f__43861))
                                (f__43861)))
                              (f__43861)))
                            (f__43861)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__42248_nth_1___r___l__
                           (clojure.core/subvec
                            input__42248_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__42248_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__42248_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__42248_nth_1___r___r__
                             (clojure.core/subvec
                              input__42248_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__42248_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__42248_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__42248_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__42629
                                (clojure.core/namespace
                                 input__42248_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__42629
                                (nil)
                                (clojure.core/let
                                 [X__42631
                                  (clojure.core/name
                                   input__42248_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__42631)
                                  (clojure.core/let
                                   [ret__42632
                                    (clojure.core/re-matches
                                     #"\.\.(\!.+)"
                                     X__42631)]
                                   (if
                                    (clojure.core/some? ret__42632)
                                    (if
                                     (clojure.core/vector? ret__42632)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__42632)
                                       2)
                                      (clojure.core/let
                                       [ret__42632_nth_1__
                                        (clojure.core/nth
                                         ret__42632
                                         1)]
                                       (clojure.core/let
                                        [?n ret__42632_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__42248_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__14338__auto__
                                           (def__42618
                                            input__42248_nth_2__)]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            x__14338__auto__)
                                           (meander.runtime.zeta/fail)
                                           (clojure.core/let
                                            [[?tag ?env]
                                             x__14338__auto__]
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
                                                 [CATA_RESULT__15641__auto__
                                                  (CATA__FN__42325
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    (clojure.core/into
                                                     []
                                                     (clojure.core/vec
                                                      (clojure.core/iterator-seq
                                                       !xs__counter)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__15641__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__15641__auto__
                                                   0))),
                                                :next
                                                (clojure.core/let
                                                 [CATA_RESULT__15641__auto__
                                                  (CATA__FN__42325
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    ?rest
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__15641__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__15641__auto__
                                                   0)))})]
                                             (catch
                                              java.lang.Exception
                                              e__16581__auto__
                                              (if
                                               (meander.runtime.zeta/fail?
                                                e__16581__auto__)
                                               (meander.runtime.zeta/fail)
                                               (throw
                                                e__16581__auto__))))))))))
                                      (meander.runtime.zeta/fail))
                                     (meander.runtime.zeta/fail))
                                    (meander.runtime.zeta/fail)))
                                  (meander.runtime.zeta/fail)))
                                (meander.runtime.zeta/fail)))
                              (meander.runtime.zeta/fail))))
                           (meander.runtime.zeta/fail)))))]
                      (if
                       (meander.runtime.zeta/fail? ret__14502__auto__)
                       (meander.runtime.zeta/fail)
                       ret__14502__auto__))))]
                  (if
                   (meander.runtime.zeta/fail? result__43858)
                   (recur (clojure.core/next search_space__43857))
                   result__43858))
                 (state__43772)))
               (state__43772))
              (state__43772)))
            (state__43772))
           (state__43772))))
        (state__43772
         []
         (if
          (clojure.core/vector? input__42248)
          (clojure.core/letfn
           [(state__43862
             []
             (if
              (clojure.core/= (clojure.core/count input__42248) 3)
              (clojure.core/let
               [input__42248_nth_0__
                (clojure.core/nth input__42248 0)
                input__42248_nth_1__
                (clojure.core/nth input__42248 1)
                input__42248_nth_2__
                (clojure.core/nth input__42248 2)]
               (clojure.core/case
                input__42248_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__42248_nth_1__)
                 (clojure.core/let
                  [!xs (clojure.core/vec input__42248_nth_1__)]
                  (clojure.core/let
                   [?env input__42248_nth_2__]
                   (try
                    [(clojure.core/let
                      [!xs__counter
                       (meander.runtime.zeta/iterator !xs)]
                      (clojure.core/let
                       [CATA_RESULT__15641__auto__
                        (CATA__FN__42325
                         ['meander.dev.parse.zeta/make-cat
                          (clojure.core/into
                           []
                           (clojure.core/loop
                            [return__42326 (clojure.core/transient [])]
                            (if
                             (clojure.core/and (.hasNext !xs__counter))
                             (recur
                              (clojure.core/conj!
                               return__42326
                               (clojure.core/let
                                [CATA_RESULT__15641__auto__
                                 (CATA__FN__42325
                                  [(if
                                    (.hasNext !xs__counter)
                                    (.next !xs__counter))
                                   ?env])]
                                (if
                                 (meander.runtime.zeta/fail?
                                  CATA_RESULT__15641__auto__)
                                 (throw (meander.runtime.zeta/fail))
                                 (clojure.core/nth
                                  CATA_RESULT__15641__auto__
                                  0)))))
                             (clojure.core/persistent!
                              return__42326))))
                          {:tag :empty}
                          ?env])]
                       (if
                        (meander.runtime.zeta/fail?
                         CATA_RESULT__15641__auto__)
                        (throw (meander.runtime.zeta/fail))
                        (clojure.core/nth
                         CATA_RESULT__15641__auto__
                         0))))]
                    (catch
                     java.lang.Exception
                     e__16581__auto__
                     (if
                      (meander.runtime.zeta/fail? e__16581__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__16581__auto__))))))
                 (state__43863))
                (state__43863)))
              (state__43863)))
            (state__43863
             []
             (if
              (clojure.core/= (clojure.core/count input__42248) 4)
              (clojure.core/let
               [input__42248_nth_0__
                (clojure.core/nth input__42248 0)
                input__42248_nth_1__
                (clojure.core/nth input__42248 1)
                input__42248_nth_2__
                (clojure.core/nth input__42248 2)]
               (clojure.core/letfn
                [(state__43865
                  []
                  (clojure.core/let
                   [input__42248_nth_3__
                    (clojure.core/nth input__42248 3)]
                   (clojure.core/case
                    input__42248_nth_0__
                    (meander.dev.parse.zeta/make-cat)
                    (if
                     (clojure.core/vector? input__42248_nth_1__)
                     (clojure.core/letfn
                      [(state__43872
                        []
                        (clojure.core/case
                         input__42248_nth_1__
                         ([])
                         (clojure.core/let
                          [?next input__42248_nth_2__]
                          (clojure.core/let
                           [?env input__42248_nth_3__]
                           (try
                            [?next]
                            (catch
                             java.lang.Exception
                             e__16581__auto__
                             (if
                              (meander.runtime.zeta/fail?
                               e__16581__auto__)
                              (meander.runtime.zeta/fail)
                              (throw e__16581__auto__))))))
                         (state__43873)))
                       (state__43873
                        []
                        (clojure.core/loop
                         [search_space__43874
                          (meander.runtime.zeta/epsilon-partitions
                           2
                           input__42248_nth_1__)]
                         (if
                          (clojure.core/seq search_space__43874)
                          (clojure.core/let
                           [input__42248_nth_1___parts__
                            (clojure.core/first search_space__43874)
                            result__43875
                            (clojure.core/let
                             [input__42248_nth_1___l__
                              (clojure.core/nth
                               input__42248_nth_1___parts__
                               0)
                              input__42248_nth_1___r__
                              (clojure.core/nth
                               input__42248_nth_1___parts__
                               1)]
                             (clojure.core/letfn
                              [(state__43877
                                []
                                (clojure.core/let
                                 [!xs []]
                                 (clojure.core/let
                                  [ret__14502__auto__
                                   (meander.runtime.zeta/epsilon-run-star-1
                                    input__42248_nth_1___l__
                                    [!xs]
                                    (clojure.core/fn
                                     [[!xs] input__42671]
                                     (clojure.core/let
                                      [input__42671_nth_0__
                                       (clojure.core/nth
                                        input__42671
                                        0)]
                                      (clojure.core/letfn
                                       [(save__42672
                                         []
                                         (meander.runtime.zeta/fail))
                                        (f__43881
                                         []
                                         (clojure.core/let
                                          [!xs
                                           (clojure.core/conj
                                            !xs
                                            input__42671_nth_0__)]
                                          [!xs]))]
                                       (if
                                        (clojure.core/map?
                                         input__42671_nth_0__)
                                        (clojure.core/let
                                         [VAL__42673
                                          (.valAt
                                           input__42671_nth_0__
                                           :tag)]
                                         (clojure.core/case
                                          VAL__42673
                                          (:group)
                                          (save__42672)
                                          (f__43881)))
                                        (f__43881)))))
                                    (clojure.core/fn
                                     [[!xs]]
                                     (clojure.core/let
                                      [input__42248_nth_1___r___l__
                                       (clojure.core/subvec
                                        input__42248_nth_1___r__
                                        0
                                        (clojure.core/min
                                         (clojure.core/count
                                          input__42248_nth_1___r__)
                                         1))]
                                      (if
                                       (clojure.core/=
                                        (clojure.core/count
                                         input__42248_nth_1___r___l__)
                                        1)
                                       (clojure.core/let
                                        [input__42248_nth_1___r___r__
                                         (clojure.core/subvec
                                          input__42248_nth_1___r__
                                          1)]
                                        (clojure.core/let
                                         [input__42248_nth_1___r___l___nth_0__
                                          (clojure.core/nth
                                           input__42248_nth_1___r___l__
                                           0)]
                                         (if
                                          (clojure.core/map?
                                           input__42248_nth_1___r___l___nth_0__)
                                          (clojure.core/let
                                           [VAL__42670
                                            (.valAt
                                             input__42248_nth_1___r___l___nth_0__
                                             :tag)]
                                           (clojure.core/case
                                            VAL__42670
                                            (:group)
                                            (clojure.core/let
                                             [?group
                                              input__42248_nth_1___r___l___nth_0__]
                                             (clojure.core/let
                                              [?rest
                                               input__42248_nth_1___r___r__]
                                              (clojure.core/let
                                               [?next
                                                input__42248_nth_2__]
                                               (clojure.core/let
                                                [?env
                                                 input__42248_nth_3__]
                                                (try
                                                 [(clojure.core/let
                                                   [!xs__counter
                                                    (meander.runtime.zeta/iterator
                                                     !xs)]
                                                   (clojure.core/let
                                                    [CATA_RESULT__15641__auto__
                                                     (CATA__FN__42325
                                                      ['meander.dev.parse.zeta/make-join
                                                       (clojure.core/let
                                                        [CATA_RESULT__15641__auto__
                                                         (CATA__FN__42325
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
                                                          CATA_RESULT__15641__auto__)
                                                         (throw
                                                          (meander.runtime.zeta/fail))
                                                         (clojure.core/nth
                                                          CATA_RESULT__15641__auto__
                                                          0)))
                                                       (clojure.core/let
                                                        [CATA_RESULT__15641__auto__
                                                         (CATA__FN__42325
                                                          ['meander.dev.parse.zeta/make-join
                                                           ?group
                                                           (clojure.core/let
                                                            [CATA_RESULT__15641__auto__
                                                             (CATA__FN__42325
                                                              ['meander.dev.parse.zeta/make-cat
                                                               ?rest
                                                               ?next
                                                               ?env])]
                                                            (if
                                                             (meander.runtime.zeta/fail?
                                                              CATA_RESULT__15641__auto__)
                                                             (throw
                                                              (meander.runtime.zeta/fail))
                                                             (clojure.core/nth
                                                              CATA_RESULT__15641__auto__
                                                              0)))
                                                           ?env])]
                                                        (if
                                                         (meander.runtime.zeta/fail?
                                                          CATA_RESULT__15641__auto__)
                                                         (throw
                                                          (meander.runtime.zeta/fail))
                                                         (clojure.core/nth
                                                          CATA_RESULT__15641__auto__
                                                          0)))
                                                       ?env])]
                                                    (if
                                                     (meander.runtime.zeta/fail?
                                                      CATA_RESULT__15641__auto__)
                                                     (throw
                                                      (meander.runtime.zeta/fail))
                                                     (clojure.core/nth
                                                      CATA_RESULT__15641__auto__
                                                      0))))]
                                                 (catch
                                                  java.lang.Exception
                                                  e__16581__auto__
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    e__16581__auto__)
                                                   (meander.runtime.zeta/fail)
                                                   (throw
                                                    e__16581__auto__))))))))
                                            (state__43878)))
                                          (state__43878))))
                                       (state__43878)))))]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    ret__14502__auto__)
                                   (state__43878)
                                   ret__14502__auto__))))
                               (state__43878
                                []
                                (clojure.core/let
                                 [!xs []]
                                 (clojure.core/let
                                  [ret__14502__auto__
                                   (meander.runtime.zeta/epsilon-run-star-1
                                    input__42248_nth_1___l__
                                    [!xs]
                                    (clojure.core/fn
                                     [[!xs] input__42682]
                                     (clojure.core/let
                                      [input__42682_nth_0__
                                       (clojure.core/nth
                                        input__42682
                                        0)]
                                      (clojure.core/letfn
                                       [(save__42683
                                         []
                                         (meander.runtime.zeta/fail))
                                        (f__43883
                                         []
                                         (clojure.core/let
                                          [!xs
                                           (clojure.core/conj
                                            !xs
                                            input__42682_nth_0__)]
                                          [!xs]))]
                                       (if
                                        (clojure.core/map?
                                         input__42682_nth_0__)
                                        (clojure.core/let
                                         [VAL__42684
                                          (.valAt
                                           input__42682_nth_0__
                                           :tag)]
                                         (clojure.core/case
                                          VAL__42684
                                          (:star)
                                          (save__42683)
                                          (f__43883)))
                                        (f__43883)))))
                                    (clojure.core/fn
                                     [[!xs]]
                                     (clojure.core/let
                                      [input__42248_nth_1___r___l__
                                       (clojure.core/subvec
                                        input__42248_nth_1___r__
                                        0
                                        (clojure.core/min
                                         (clojure.core/count
                                          input__42248_nth_1___r__)
                                         1))]
                                      (if
                                       (clojure.core/=
                                        (clojure.core/count
                                         input__42248_nth_1___r___l__)
                                        1)
                                       (clojure.core/let
                                        [input__42248_nth_1___r___r__
                                         (clojure.core/subvec
                                          input__42248_nth_1___r__
                                          1)]
                                        (clojure.core/let
                                         [input__42248_nth_1___r___l___nth_0__
                                          (clojure.core/nth
                                           input__42248_nth_1___r___l__
                                           0)]
                                         (if
                                          (clojure.core/map?
                                           input__42248_nth_1___r___l___nth_0__)
                                          (clojure.core/let
                                           [VAL__42681
                                            (.valAt
                                             input__42248_nth_1___r___l___nth_0__
                                             :tag)]
                                           (clojure.core/case
                                            VAL__42681
                                            (:star)
                                            (clojure.core/let
                                             [?star
                                              input__42248_nth_1___r___l___nth_0__]
                                             (clojure.core/let
                                              [?rest
                                               input__42248_nth_1___r___r__]
                                              (clojure.core/let
                                               [?next
                                                input__42248_nth_2__]
                                               (clojure.core/let
                                                [?env
                                                 input__42248_nth_3__]
                                                (try
                                                 [(clojure.core/let
                                                   [!xs__counter
                                                    (meander.runtime.zeta/iterator
                                                     !xs)]
                                                   (clojure.core/let
                                                    [CATA_RESULT__15641__auto__
                                                     (CATA__FN__42325
                                                      ['meander.dev.parse.zeta/make-join
                                                       (clojure.core/let
                                                        [CATA_RESULT__15641__auto__
                                                         (CATA__FN__42325
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
                                                          CATA_RESULT__15641__auto__)
                                                         (throw
                                                          (meander.runtime.zeta/fail))
                                                         (clojure.core/nth
                                                          CATA_RESULT__15641__auto__
                                                          0)))
                                                       (clojure.core/let
                                                        [CATA_RESULT__15641__auto__
                                                         (CATA__FN__42325
                                                          ['meander.dev.parse.zeta/make-join
                                                           ?star
                                                           (clojure.core/let
                                                            [CATA_RESULT__15641__auto__
                                                             (CATA__FN__42325
                                                              ['meander.dev.parse.zeta/make-cat
                                                               ?rest
                                                               ?next
                                                               ?env])]
                                                            (if
                                                             (meander.runtime.zeta/fail?
                                                              CATA_RESULT__15641__auto__)
                                                             (throw
                                                              (meander.runtime.zeta/fail))
                                                             (clojure.core/nth
                                                              CATA_RESULT__15641__auto__
                                                              0)))
                                                           ?env])]
                                                        (if
                                                         (meander.runtime.zeta/fail?
                                                          CATA_RESULT__15641__auto__)
                                                         (throw
                                                          (meander.runtime.zeta/fail))
                                                         (clojure.core/nth
                                                          CATA_RESULT__15641__auto__
                                                          0)))
                                                       ?env])]
                                                    (if
                                                     (meander.runtime.zeta/fail?
                                                      CATA_RESULT__15641__auto__)
                                                     (throw
                                                      (meander.runtime.zeta/fail))
                                                     (clojure.core/nth
                                                      CATA_RESULT__15641__auto__
                                                      0))))]
                                                 (catch
                                                  java.lang.Exception
                                                  e__16581__auto__
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    e__16581__auto__)
                                                   (meander.runtime.zeta/fail)
                                                   (throw
                                                    e__16581__auto__))))))))
                                            (state__43879)))
                                          (state__43879))))
                                       (state__43879)))))]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    ret__14502__auto__)
                                   (state__43879)
                                   ret__14502__auto__))))
                               (state__43879
                                []
                                (clojure.core/let
                                 [!xs []]
                                 (clojure.core/let
                                  [ret__14502__auto__
                                   (meander.runtime.zeta/epsilon-run-star-1
                                    input__42248_nth_1___l__
                                    [!xs]
                                    (clojure.core/fn
                                     [[!xs] input__42693]
                                     (clojure.core/let
                                      [input__42693_nth_0__
                                       (clojure.core/nth
                                        input__42693
                                        0)]
                                      (clojure.core/letfn
                                       [(save__42694
                                         []
                                         (meander.runtime.zeta/fail))
                                        (f__43885
                                         []
                                         (clojure.core/let
                                          [!xs
                                           (clojure.core/conj
                                            !xs
                                            input__42693_nth_0__)]
                                          [!xs]))]
                                       (if
                                        (clojure.core/map?
                                         input__42693_nth_0__)
                                        (clojure.core/let
                                         [VAL__42695
                                          (.valAt
                                           input__42693_nth_0__
                                           :tag)]
                                         (clojure.core/case
                                          VAL__42695
                                          (:reference)
                                          (save__42694)
                                          (f__43885)))
                                        (f__43885)))))
                                    (clojure.core/fn
                                     [[!xs]]
                                     (clojure.core/let
                                      [input__42248_nth_1___r___l__
                                       (clojure.core/subvec
                                        input__42248_nth_1___r__
                                        0
                                        (clojure.core/min
                                         (clojure.core/count
                                          input__42248_nth_1___r__)
                                         1))]
                                      (if
                                       (clojure.core/=
                                        (clojure.core/count
                                         input__42248_nth_1___r___l__)
                                        1)
                                       (clojure.core/let
                                        [input__42248_nth_1___r___r__
                                         (clojure.core/subvec
                                          input__42248_nth_1___r__
                                          1)]
                                        (clojure.core/let
                                         [input__42248_nth_1___r___l___nth_0__
                                          (clojure.core/nth
                                           input__42248_nth_1___r___l__
                                           0)]
                                         (if
                                          (clojure.core/map?
                                           input__42248_nth_1___r___l___nth_0__)
                                          (clojure.core/let
                                           [VAL__42692
                                            (.valAt
                                             input__42248_nth_1___r___l___nth_0__
                                             :tag)]
                                           (clojure.core/case
                                            VAL__42692
                                            (:reference)
                                            (clojure.core/let
                                             [?reference
                                              input__42248_nth_1___r___l___nth_0__]
                                             (clojure.core/let
                                              [?rest
                                               input__42248_nth_1___r___r__]
                                              (clojure.core/let
                                               [?next
                                                input__42248_nth_2__]
                                               (clojure.core/let
                                                [?env
                                                 input__42248_nth_3__]
                                                (try
                                                 [(clojure.core/let
                                                   [!xs__counter
                                                    (meander.runtime.zeta/iterator
                                                     !xs)]
                                                   (clojure.core/let
                                                    [CATA_RESULT__15641__auto__
                                                     (CATA__FN__42325
                                                      ['meander.dev.parse.zeta/make-join
                                                       (clojure.core/let
                                                        [CATA_RESULT__15641__auto__
                                                         (CATA__FN__42325
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
                                                          CATA_RESULT__15641__auto__)
                                                         (throw
                                                          (meander.runtime.zeta/fail))
                                                         (clojure.core/nth
                                                          CATA_RESULT__15641__auto__
                                                          0)))
                                                       (clojure.core/let
                                                        [CATA_RESULT__15641__auto__
                                                         (CATA__FN__42325
                                                          ['meander.dev.parse.zeta/make-join
                                                           ?reference
                                                           (clojure.core/let
                                                            [CATA_RESULT__15641__auto__
                                                             (CATA__FN__42325
                                                              ['meander.dev.parse.zeta/make-cat
                                                               ?rest
                                                               ?next
                                                               ?env])]
                                                            (if
                                                             (meander.runtime.zeta/fail?
                                                              CATA_RESULT__15641__auto__)
                                                             (throw
                                                              (meander.runtime.zeta/fail))
                                                             (clojure.core/nth
                                                              CATA_RESULT__15641__auto__
                                                              0)))
                                                           ?env])]
                                                        (if
                                                         (meander.runtime.zeta/fail?
                                                          CATA_RESULT__15641__auto__)
                                                         (throw
                                                          (meander.runtime.zeta/fail))
                                                         (clojure.core/nth
                                                          CATA_RESULT__15641__auto__
                                                          0)))
                                                       ?env])]
                                                    (if
                                                     (meander.runtime.zeta/fail?
                                                      CATA_RESULT__15641__auto__)
                                                     (throw
                                                      (meander.runtime.zeta/fail))
                                                     (clojure.core/nth
                                                      CATA_RESULT__15641__auto__
                                                      0))))]
                                                 (catch
                                                  java.lang.Exception
                                                  e__16581__auto__
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    e__16581__auto__)
                                                   (meander.runtime.zeta/fail)
                                                   (throw
                                                    e__16581__auto__))))))))
                                            (meander.runtime.zeta/fail)))
                                          (meander.runtime.zeta/fail))))
                                       (meander.runtime.zeta/fail)))))]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    ret__14502__auto__)
                                   (meander.runtime.zeta/fail)
                                   ret__14502__auto__))))]
                              (state__43877)))]
                           (if
                            (meander.runtime.zeta/fail? result__43875)
                            (recur
                             (clojure.core/next search_space__43874))
                            result__43875))
                          (state__43866))))]
                      (state__43872))
                     (state__43866))
                    (state__43866))))
                 (state__43866
                  []
                  (clojure.core/case
                   input__42248_nth_0__
                   (meander.dev.parse.zeta/make-cat)
                   (if
                    (clojure.core/vector? input__42248_nth_1__)
                    (clojure.core/let
                     [input__42248_nth_1___l__
                      (clojure.core/subvec
                       input__42248_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__42248_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__42248_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__42248_nth_1___r__
                        (clojure.core/subvec input__42248_nth_1__ 1)]
                       (clojure.core/let
                        [input__42248_nth_1___l___nth_0__
                         (clojure.core/nth input__42248_nth_1___l__ 0)]
                        (if
                         (clojure.core/map?
                          input__42248_nth_1___l___nth_0__)
                         (clojure.core/let
                          [VAL__42704
                           (.valAt
                            input__42248_nth_1___l___nth_0__
                            :tag)]
                          (clojure.core/case
                           VAL__42704
                           (:literal)
                           (clojure.core/let
                            [VAL__42705
                             (.valAt
                              input__42248_nth_1___l___nth_0__
                              :type)]
                            (if
                             (clojure.core/let
                              [x__13398__auto__ VAL__42705]
                              (clojure.core/case
                               x__13398__auto__
                               (:string :char)
                               true
                               false))
                             (clojure.core/let
                              [VAL__42706
                               (.valAt
                                input__42248_nth_1___l___nth_0__
                                :form)]
                              (clojure.core/let
                               [!forms []]
                               (clojure.core/let
                                [!forms
                                 (clojure.core/conj !forms VAL__42706)]
                                (clojure.core/loop
                                 [i__14475__auto__
                                  0
                                  coll__43886
                                  input__42248_nth_1___r__
                                  [!forms]
                                  [!forms]]
                                 (clojure.core/let
                                  [input__42707
                                   (clojure.core/subvec
                                    coll__43886
                                    0
                                    (clojure.core/min
                                     (clojure.core/count coll__43886)
                                     1))]
                                  (if
                                   (clojure.core/=
                                    (clojure.core/count input__42707)
                                    1)
                                   (clojure.core/let
                                    [result__14476__auto__
                                     (clojure.core/let
                                      [input__42707_nth_0__
                                       (clojure.core/nth
                                        input__42707
                                        0)]
                                      (if
                                       (clojure.core/map?
                                        input__42707_nth_0__)
                                       (clojure.core/let
                                        [VAL__42708
                                         (.valAt
                                          input__42707_nth_0__
                                          :tag)]
                                        (clojure.core/case
                                         VAL__42708
                                         (:literal)
                                         (clojure.core/let
                                          [VAL__42709
                                           (.valAt
                                            input__42707_nth_0__
                                            :type)]
                                          (if
                                           (clojure.core/let
                                            [x__13398__auto__
                                             VAL__42709]
                                            (clojure.core/case
                                             x__13398__auto__
                                             (:string :char)
                                             true
                                             false))
                                           (clojure.core/let
                                            [VAL__42710
                                             (.valAt
                                              input__42707_nth_0__
                                              :form)]
                                            (clojure.core/let
                                             [!forms
                                              (clojure.core/conj
                                               !forms
                                               VAL__42710)]
                                             [!forms]))
                                           (meander.runtime.zeta/fail)))
                                         (meander.runtime.zeta/fail)))
                                       (meander.runtime.zeta/fail)))]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      result__14476__auto__)
                                     (state__43867)
                                     (recur
                                      (clojure.core/inc
                                       i__14475__auto__)
                                      (clojure.core/subvec
                                       coll__43886
                                       1)
                                      result__14476__auto__)))
                                   (if
                                    (clojure.core/or
                                     (clojure.core/seq coll__43886)
                                     (clojure.core/<
                                      i__14475__auto__
                                      0))
                                    (state__43867)
                                    (if
                                     (clojure.core/map?
                                      input__42248_nth_2__)
                                     (clojure.core/let
                                      [VAL__42699
                                       (.valAt
                                        input__42248_nth_2__
                                        :tag)]
                                      (clojure.core/case
                                       VAL__42699
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
                                         e__16581__auto__
                                         (if
                                          (meander.runtime.zeta/fail?
                                           e__16581__auto__)
                                          (meander.runtime.zeta/fail)
                                          (throw e__16581__auto__))))
                                       (state__43867)))
                                     (state__43867)))))))))
                             (state__43867)))
                           (state__43867)))
                         (state__43867))))
                      (state__43867)))
                    (state__43867))
                   (state__43867)))
                 (state__43867
                  []
                  (clojure.core/let
                   [input__42248_nth_3__
                    (clojure.core/nth input__42248 3)]
                   (clojure.core/case
                    input__42248_nth_0__
                    (meander.dev.parse.zeta/make-cat)
                    (clojure.core/letfn
                     [(state__43887
                       []
                       (if
                        (clojure.core/vector? input__42248_nth_1__)
                        (clojure.core/let
                         [input__42248_nth_1___l__
                          (clojure.core/subvec
                           input__42248_nth_1__
                           0
                           (clojure.core/min
                            (clojure.core/count input__42248_nth_1__)
                            1))]
                         (if
                          (clojure.core/=
                           (clojure.core/count
                            input__42248_nth_1___l__)
                           1)
                          (clojure.core/let
                           [input__42248_nth_1___r__
                            (clojure.core/subvec
                             input__42248_nth_1__
                             1)]
                           (clojure.core/let
                            [input__42248_nth_1___l___nth_0__
                             (clojure.core/nth
                              input__42248_nth_1___l__
                              0)]
                            (if
                             (clojure.core/map?
                              input__42248_nth_1___l___nth_0__)
                             (clojure.core/let
                              [VAL__43759
                               (.valAt
                                input__42248_nth_1___l___nth_0__
                                :tag)]
                              (clojure.core/case
                               VAL__43759
                               (:literal)
                               (clojure.core/letfn
                                [(state__43889
                                  []
                                  (clojure.core/let
                                   [VAL__42717
                                    (.valAt
                                     input__42248_nth_1___l___nth_0__
                                     :type)]
                                   (clojure.core/case
                                    VAL__42717
                                    (:string)
                                    (clojure.core/let
                                     [?ast
                                      input__42248_nth_1___l___nth_0__]
                                     (clojure.core/let
                                      [?rest input__42248_nth_1___r__]
                                      (clojure.core/let
                                       [?next input__42248_nth_2__]
                                       (clojure.core/let
                                        [?env input__42248_nth_3__]
                                        (try
                                         [(clojure.core/let
                                           [CATA_RESULT__15641__auto__
                                            (CATA__FN__42325
                                             ['meander.dev.parse.zeta/make-join
                                              ?ast
                                              (clojure.core/let
                                               [CATA_RESULT__15641__auto__
                                                (CATA__FN__42325
                                                 ['meander.dev.parse.zeta/make-cat
                                                  ?rest
                                                  ?next
                                                  ?env])]
                                               (if
                                                (meander.runtime.zeta/fail?
                                                 CATA_RESULT__15641__auto__)
                                                (throw
                                                 (meander.runtime.zeta/fail))
                                                (clojure.core/nth
                                                 CATA_RESULT__15641__auto__
                                                 0)))
                                              ?env])]
                                           (if
                                            (meander.runtime.zeta/fail?
                                             CATA_RESULT__15641__auto__)
                                            (throw
                                             (meander.runtime.zeta/fail))
                                            (clojure.core/nth
                                             CATA_RESULT__15641__auto__
                                             0)))]
                                         (catch
                                          java.lang.Exception
                                          e__16581__auto__
                                          (if
                                           (meander.runtime.zeta/fail?
                                            e__16581__auto__)
                                           (meander.runtime.zeta/fail)
                                           (throw
                                            e__16581__auto__))))))))
                                    (state__43890))))
                                 (state__43890
                                  []
                                  (clojure.core/let
                                   [VAL__42727
                                    (.valAt
                                     input__42248_nth_1___l___nth_0__
                                     :form)]
                                   (clojure.core/let
                                    [!forms []]
                                    (clojure.core/let
                                     [!forms
                                      (clojure.core/conj
                                       !forms
                                       VAL__42727)]
                                     (clojure.core/loop
                                      [i__14475__auto__
                                       0
                                       coll__43891
                                       input__42248_nth_1___r__
                                       [!forms]
                                       [!forms]]
                                      (clojure.core/let
                                       [input__42728
                                        (clojure.core/subvec
                                         coll__43891
                                         0
                                         (clojure.core/min
                                          (clojure.core/count
                                           coll__43891)
                                          1))]
                                       (if
                                        (clojure.core/=
                                         (clojure.core/count
                                          input__42728)
                                         1)
                                        (clojure.core/let
                                         [result__14476__auto__
                                          (clojure.core/let
                                           [input__42728_nth_0__
                                            (clojure.core/nth
                                             input__42728
                                             0)]
                                           (if
                                            (clojure.core/map?
                                             input__42728_nth_0__)
                                            (clojure.core/let
                                             [VAL__42729
                                              (.valAt
                                               input__42728_nth_0__
                                               :tag)]
                                             (clojure.core/case
                                              VAL__42729
                                              (:literal)
                                              (clojure.core/let
                                               [VAL__42730
                                                (.valAt
                                                 input__42728_nth_0__
                                                 :form)]
                                               (clojure.core/let
                                                [!forms
                                                 (clojure.core/conj
                                                  !forms
                                                  VAL__42730)]
                                                [!forms]))
                                              (meander.runtime.zeta/fail)))
                                            (meander.runtime.zeta/fail)))]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           result__14476__auto__)
                                          (state__43888)
                                          (recur
                                           (clojure.core/inc
                                            i__14475__auto__)
                                           (clojure.core/subvec
                                            coll__43891
                                            1)
                                           result__14476__auto__)))
                                        (if
                                         (clojure.core/or
                                          (clojure.core/seq
                                           coll__43891)
                                          (clojure.core/<
                                           i__14475__auto__
                                           0))
                                         (state__43888)
                                         (if
                                          (clojure.core/map?
                                           input__42248_nth_2__)
                                          (clojure.core/let
                                           [VAL__42720
                                            (.valAt
                                             input__42248_nth_2__
                                             :tag)]
                                           (clojure.core/case
                                            VAL__42720
                                            (:empty)
                                            (if
                                             (clojure.core/map?
                                              input__42248_nth_3__)
                                             (clojure.core/let
                                              [VAL__42721
                                               (.valAt
                                                input__42248_nth_3__
                                                :context)]
                                              (clojure.core/let
                                               [?context VAL__42721]
                                               (clojure.core/let
                                                [?env
                                                 input__42248_nth_3__]
                                                (try
                                                 [{:tag :literal,
                                                   :type ?context,
                                                   :form
                                                   (clojure.core/into
                                                    []
                                                    !forms)}]
                                                 (catch
                                                  java.lang.Exception
                                                  e__16581__auto__
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    e__16581__auto__)
                                                   (meander.runtime.zeta/fail)
                                                   (throw
                                                    e__16581__auto__)))))))
                                             (state__43888))
                                            (state__43888)))
                                          (state__43888))))))))))]
                                (state__43889))
                               (state__43888)))
                             (state__43888))))
                          (state__43888)))
                        (state__43888)))
                      (state__43888
                       []
                       (clojure.core/let
                        [?sequence input__42248_nth_1__]
                        (clojure.core/let
                         [?next input__42248_nth_2__]
                         (if
                          (clojure.core/map? input__42248_nth_3__)
                          (clojure.core/let
                           [VAL__42734
                            (.valAt input__42248_nth_3__ :context)]
                           (clojure.core/case
                            VAL__42734
                            (:string)
                            (try
                             [{:tag :string-cat,
                               :sequence ?sequence,
                               :next ?next}]
                             (catch
                              java.lang.Exception
                              e__16581__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__16581__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__16581__auto__))))
                            (state__43868)))
                          (state__43868)))))]
                     (state__43887))
                    (state__43868))))
                 (state__43868
                  []
                  (clojure.core/case
                   input__42248_nth_0__
                   (meander.dev.parse.zeta/make-cat)
                   (clojure.core/let
                    [?sequence input__42248_nth_1__]
                    (clojure.core/let
                     [?next input__42248_nth_2__]
                     (try
                      [{:tag :cat, :sequence ?sequence, :next ?next}]
                      (catch
                       java.lang.Exception
                       e__16581__auto__
                       (if
                        (meander.runtime.zeta/fail? e__16581__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__16581__auto__))))))
                   (state__43869)))
                 (state__43869
                  []
                  (clojure.core/let
                   [input__42248_nth_3__
                    (clojure.core/nth input__42248 3)]
                   (clojure.core/case
                    input__42248_nth_0__
                    (meander.dev.parse.zeta/make-join)
                    (if
                     (clojure.core/map? input__42248_nth_1__)
                     (clojure.core/let
                      [VAL__43757 (.valAt input__42248_nth_1__ :tag)]
                      (clojure.core/case
                       VAL__43757
                       (:cat)
                       (clojure.core/let
                        [VAL__42740
                         (.valAt input__42248_nth_1__ :sequence)]
                        (clojure.core/let
                         [?sequence VAL__42740]
                         (clojure.core/let
                          [VAL__42741
                           (.valAt input__42248_nth_1__ :next)]
                          (if
                           (clojure.core/map? VAL__42741)
                           (clojure.core/let
                            [VAL__42742 (.valAt VAL__42741 :tag)]
                            (clojure.core/case
                             VAL__42742
                             (:empty)
                             (clojure.core/let
                              [?right input__42248_nth_2__]
                              (clojure.core/let
                               [?env input__42248_nth_3__]
                               (try
                                [(clojure.core/let
                                  [CATA_RESULT__15641__auto__
                                   (CATA__FN__42325
                                    ['meander.dev.parse.zeta/make-cat
                                     ?sequence
                                     ?right
                                     ?env])]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    CATA_RESULT__15641__auto__)
                                   (throw (meander.runtime.zeta/fail))
                                   (clojure.core/nth
                                    CATA_RESULT__15641__auto__
                                    0)))]
                                (catch
                                 java.lang.Exception
                                 e__16581__auto__
                                 (if
                                  (meander.runtime.zeta/fail?
                                   e__16581__auto__)
                                  (meander.runtime.zeta/fail)
                                  (throw e__16581__auto__))))))
                             (state__43870)))
                           (state__43870)))))
                       (:literal)
                       (clojure.core/let
                        [VAL__42746
                         (.valAt input__42248_nth_1__ :type)]
                        (clojure.core/case
                         VAL__42746
                         (:string)
                         (clojure.core/let
                          [VAL__42747
                           (.valAt input__42248_nth_1__ :form)]
                          (clojure.core/let
                           [?form-1 VAL__42747]
                           (if
                            (clojure.core/map? input__42248_nth_2__)
                            (clojure.core/let
                             [VAL__42748
                              (.valAt input__42248_nth_2__ :tag)]
                             (clojure.core/case
                              VAL__42748
                              (:string-join)
                              (clojure.core/let
                               [VAL__42749
                                (.valAt input__42248_nth_2__ :left)]
                               (if
                                (clojure.core/map? VAL__42749)
                                (clojure.core/let
                                 [VAL__42750 (.valAt VAL__42749 :tag)]
                                 (clojure.core/case
                                  VAL__42750
                                  (:literal)
                                  (clojure.core/let
                                   [VAL__42751
                                    (.valAt VAL__42749 :type)]
                                   (clojure.core/case
                                    VAL__42751
                                    (:string)
                                    (clojure.core/let
                                     [VAL__42752
                                      (.valAt VAL__42749 :form)]
                                     (clojure.core/let
                                      [?form-2 VAL__42752]
                                      (clojure.core/let
                                       [VAL__42753
                                        (.valAt
                                         input__42248_nth_2__
                                         :right)]
                                       (clojure.core/let
                                        [?right VAL__42753]
                                        (if
                                         (clojure.core/map?
                                          input__42248_nth_3__)
                                         (clojure.core/let
                                          [VAL__42754
                                           (.valAt
                                            input__42248_nth_3__
                                            :context)]
                                          (clojure.core/case
                                           VAL__42754
                                           (:string)
                                           (clojure.core/let
                                            [?env input__42248_nth_3__]
                                            (try
                                             [(clojure.core/let
                                               [CATA_RESULT__15641__auto__
                                                (CATA__FN__42325
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
                                                 CATA_RESULT__15641__auto__)
                                                (throw
                                                 (meander.runtime.zeta/fail))
                                                (clojure.core/nth
                                                 CATA_RESULT__15641__auto__
                                                 0)))]
                                             (catch
                                              java.lang.Exception
                                              e__16581__auto__
                                              (if
                                               (meander.runtime.zeta/fail?
                                                e__16581__auto__)
                                               (meander.runtime.zeta/fail)
                                               (throw
                                                e__16581__auto__)))))
                                           (state__43870)))
                                         (state__43870))))))
                                    (state__43870)))
                                  (state__43870)))
                                (state__43870)))
                              (state__43870)))
                            (state__43870))))
                         (state__43870)))
                       (state__43870)))
                     (state__43870))
                    (state__43870))))
                 (state__43870
                  []
                  (clojure.core/case
                   input__42248_nth_0__
                   (meander.dev.parse.zeta/make-join)
                   (if
                    (clojure.core/map? input__42248_nth_1__)
                    (clojure.core/let
                     [VAL__43758 (.valAt input__42248_nth_1__ :tag)]
                     (clojure.core/case
                      VAL__43758
                      (:cat)
                      (clojure.core/let
                       [?ast input__42248_nth_1__]
                       (if
                        (clojure.core/map? input__42248_nth_2__)
                        (clojure.core/let
                         [VAL__42758
                          (.valAt input__42248_nth_2__ :tag)]
                         (clojure.core/case
                          VAL__42758
                          (:cat)
                          (clojure.core/let
                           [VAL__42759
                            (.valAt input__42248_nth_2__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__42759]
                            (clojure.core/let
                             [VAL__42760
                              (.valAt input__42248_nth_2__ :next)]
                             (clojure.core/let
                              [?next VAL__42760]
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
                                e__16581__auto__
                                (if
                                 (meander.runtime.zeta/fail?
                                  e__16581__auto__)
                                 (meander.runtime.zeta/fail)
                                 (throw e__16581__auto__))))))))
                          (state__43871)))
                        (state__43871)))
                      (:string-cat)
                      (clojure.core/let
                       [?ast input__42248_nth_1__]
                       (if
                        (clojure.core/map? input__42248_nth_2__)
                        (clojure.core/let
                         [VAL__42764
                          (.valAt input__42248_nth_2__ :tag)]
                         (clojure.core/case
                          VAL__42764
                          (:string-cat)
                          (clojure.core/let
                           [VAL__42765
                            (.valAt input__42248_nth_2__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__42765]
                            (clojure.core/let
                             [VAL__42766
                              (.valAt input__42248_nth_2__ :next)]
                             (clojure.core/let
                              [?next VAL__42766]
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
                                e__16581__auto__
                                (if
                                 (meander.runtime.zeta/fail?
                                  e__16581__auto__)
                                 (meander.runtime.zeta/fail)
                                 (throw e__16581__auto__))))))))
                          (state__43871)))
                        (state__43871)))
                      (state__43871)))
                    (state__43871))
                   (state__43871)))
                 (state__43871
                  []
                  (clojure.core/let
                   [input__42248_nth_3__
                    (clojure.core/nth input__42248 3)]
                   (clojure.core/case
                    input__42248_nth_0__
                    (meander.dev.parse.zeta/make-join)
                    (clojure.core/letfn
                     [(state__43892
                       []
                       (if
                        (clojure.core/map? input__42248_nth_1__)
                        (clojure.core/let
                         [VAL__43762
                          (.valAt input__42248_nth_1__ :next)
                          VAL__43761
                          (.valAt input__42248_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__43761
                          (:string-cat)
                          (clojure.core/let
                           [VAL__42770
                            (.valAt input__42248_nth_1__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__42770]
                            (if
                             (clojure.core/map? VAL__43762)
                             (clojure.core/let
                              [VAL__42772 (.valAt VAL__43762 :tag)]
                              (clojure.core/case
                               VAL__42772
                               (:empty)
                               (clojure.core/let
                                [?right input__42248_nth_2__]
                                (clojure.core/let
                                 [?env input__42248_nth_3__]
                                 (try
                                  [(clojure.core/let
                                    [CATA_RESULT__15641__auto__
                                     (CATA__FN__42325
                                      ['meander.dev.parse.zeta/make-join
                                       ?sequence
                                       ?right
                                       ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__15641__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__15641__auto__
                                      0)))]
                                  (catch
                                   java.lang.Exception
                                   e__16581__auto__
                                   (if
                                    (meander.runtime.zeta/fail?
                                     e__16581__auto__)
                                    (meander.runtime.zeta/fail)
                                    (throw e__16581__auto__))))))
                               (state__43893)))
                             (state__43893))))
                          (:string-star)
                          (clojure.core/let
                           [VAL__42776
                            (.valAt input__42248_nth_1__ :pattern)]
                           (clojure.core/let
                            [?pattern VAL__42776]
                            (if
                             (clojure.core/map? VAL__43762)
                             (clojure.core/let
                              [VAL__42778 (.valAt VAL__43762 :tag)]
                              (clojure.core/case
                               VAL__42778
                               (:empty)
                               (clojure.core/let
                                [?right input__42248_nth_2__]
                                (if
                                 (clojure.core/map?
                                  input__42248_nth_3__)
                                 (clojure.core/let
                                  [VAL__42779
                                   (.valAt
                                    input__42248_nth_3__
                                    :context)]
                                  (clojure.core/case
                                   VAL__42779
                                   (:string)
                                   (try
                                    [{:tag :string-star,
                                      :pattern ?pattern,
                                      :next ?right}]
                                    (catch
                                     java.lang.Exception
                                     e__16581__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__16581__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__16581__auto__))))
                                   (state__43893)))
                                 (state__43893)))
                               (state__43893)))
                             (state__43893))))
                          (:string-join)
                          (clojure.core/let
                           [VAL__42783
                            (.valAt input__42248_nth_1__ :left)]
                           (clojure.core/let
                            [?left VAL__42783]
                            (clojure.core/let
                             [VAL__42784
                              (.valAt input__42248_nth_1__ :right)]
                             (clojure.core/let
                              [?right-1 VAL__42784]
                              (clojure.core/let
                               [?right-2 input__42248_nth_2__]
                               (if
                                (clojure.core/map?
                                 input__42248_nth_3__)
                                (clojure.core/let
                                 [VAL__42785
                                  (.valAt
                                   input__42248_nth_3__
                                   :context)]
                                 (clojure.core/case
                                  VAL__42785
                                  (:string)
                                  (clojure.core/let
                                   [?env input__42248_nth_3__]
                                   (try
                                    [{:tag :string-join,
                                      :left ?left,
                                      :right
                                      (clojure.core/let
                                       [CATA_RESULT__15641__auto__
                                        (CATA__FN__42325
                                         ['meander.dev.parse.zeta/make-join
                                          ?right-1
                                          ?right-2
                                          ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__15641__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__15641__auto__
                                         0)))}]
                                    (catch
                                     java.lang.Exception
                                     e__16581__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__16581__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__16581__auto__)))))
                                  (state__43893)))
                                (state__43893)))))))
                          (state__43893)))
                        (state__43893)))
                      (state__43893
                       []
                       (clojure.core/let
                        [?left input__42248_nth_1__]
                        (if
                         (clojure.core/map? input__42248_nth_2__)
                         (clojure.core/let
                          [VAL__42788
                           (.valAt input__42248_nth_2__ :tag)]
                          (clojure.core/case
                           VAL__42788
                           (:empty)
                           (clojure.core/let
                            [?env input__42248_nth_3__]
                            (try
                             [?left]
                             (catch
                              java.lang.Exception
                              e__16581__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__16581__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__16581__auto__)))))
                           (state__43894)))
                         (state__43894))))
                      (state__43894
                       []
                       (if
                        (clojure.core/map? input__42248_nth_1__)
                        (clojure.core/let
                         [VAL__43760
                          (.valAt input__42248_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__43760
                          (:empty)
                          (clojure.core/let
                           [?right input__42248_nth_2__]
                           (clojure.core/let
                            [?env input__42248_nth_3__]
                            (try
                             [?right]
                             (catch
                              java.lang.Exception
                              e__16581__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__16581__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__16581__auto__))))))
                          (:star)
                          (clojure.core/let
                           [VAL__42795
                            (.valAt input__42248_nth_1__ :next)]
                           (if
                            (clojure.core/map? VAL__42795)
                            (clojure.core/let
                             [VAL__42796 (.valAt VAL__42795 :tag)]
                             (clojure.core/case
                              VAL__42796
                              (:empty)
                              (clojure.core/let
                               [?left input__42248_nth_1__]
                               (clojure.core/let
                                [?right input__42248_nth_2__]
                                (clojure.core/let
                                 [?env input__42248_nth_3__]
                                 (try
                                  [(clojure.core/let
                                    [form__15740__auto__
                                     {:tag :star, :next ?right}]
                                    (clojure.core/merge
                                     (clojure.core/into {} ?left)
                                     form__15740__auto__))]
                                  (catch
                                   java.lang.Exception
                                   e__16581__auto__
                                   (if
                                    (meander.runtime.zeta/fail?
                                     e__16581__auto__)
                                    (meander.runtime.zeta/fail)
                                    (throw e__16581__auto__)))))))
                              (state__43895)))
                            (state__43895)))
                          (state__43895)))
                        (state__43895)))
                      (state__43895
                       []
                       (clojure.core/let
                        [?left input__42248_nth_1__]
                        (clojure.core/let
                         [?right input__42248_nth_2__]
                         (clojure.core/letfn
                          [(state__43896
                            []
                            (if
                             (clojure.core/map? input__42248_nth_3__)
                             (clojure.core/let
                              [VAL__42799
                               (.valAt input__42248_nth_3__ :context)]
                              (clojure.core/case
                               VAL__42799
                               (:string)
                               (try
                                [{:tag :string-join,
                                  :left ?left,
                                  :right ?right}]
                                (catch
                                 java.lang.Exception
                                 e__16581__auto__
                                 (if
                                  (meander.runtime.zeta/fail?
                                   e__16581__auto__)
                                  (meander.runtime.zeta/fail)
                                  (throw e__16581__auto__))))
                               (state__43897)))
                             (state__43897)))
                           (state__43897
                            []
                            (clojure.core/let
                             [?env input__42248_nth_3__]
                             (try
                              [{:tag :join,
                                :left ?left,
                                :right ?right}]
                              (catch
                               java.lang.Exception
                               e__16581__auto__
                               (if
                                (meander.runtime.zeta/fail?
                                 e__16581__auto__)
                                (meander.runtime.zeta/fail)
                                (throw e__16581__auto__))))))]
                          (state__43896)))))]
                     (state__43892))
                    (state__43864))))]
                (state__43865)))
              (state__43864)))
            (state__43864
             []
             (if
              (clojure.core/= (clojure.core/count input__42248) 3)
              (clojure.core/let
               [input__42248_nth_0__
                (clojure.core/nth input__42248 0)
                input__42248_nth_1__
                (clojure.core/nth input__42248 1)
                input__42248_nth_2__
                (clojure.core/nth input__42248 2)]
               (clojure.core/case
                input__42248_nth_0__
                (meander.dev.parse.zeta/parse-entries)
                (if
                 (clojure.core/map? input__42248_nth_1__)
                 (clojure.core/let
                  [VAL__42804
                   (.valAt input__42248_nth_1__ :meander.zeta/as)]
                  (clojure.core/let
                   [?pattern VAL__42804]
                   (clojure.core/let
                    [X__42806
                     ((clojure.core/fn
                       [m__13405__auto__]
                       (clojure.core/dissoc
                        m__13405__auto__
                        :meander.zeta/as))
                      input__42248_nth_1__)]
                    (clojure.core/let
                     [?rest X__42806]
                     (clojure.core/letfn
                      [(save__42807 [] (state__43773))
                       (f__43898
                        []
                        (clojure.core/let
                         [?env input__42248_nth_2__]
                         (try
                          [{:tag :as,
                            :pattern
                            (clojure.core/let
                             [CATA_RESULT__15641__auto__
                              (CATA__FN__42325 [?pattern ?env])]
                             (if
                              (meander.runtime.zeta/fail?
                               CATA_RESULT__15641__auto__)
                              (throw (meander.runtime.zeta/fail))
                              (clojure.core/nth
                               CATA_RESULT__15641__auto__
                               0))),
                            :next
                            (clojure.core/let
                             [CATA_RESULT__15641__auto__
                              (CATA__FN__42325 [?rest ?env])]
                             (if
                              (meander.runtime.zeta/fail?
                               CATA_RESULT__15641__auto__)
                              (throw (meander.runtime.zeta/fail))
                              (clojure.core/nth
                               CATA_RESULT__15641__auto__
                               0)))}]
                          (catch
                           java.lang.Exception
                           e__16581__auto__
                           (if
                            (meander.runtime.zeta/fail?
                             e__16581__auto__)
                            (meander.runtime.zeta/fail)
                            (throw e__16581__auto__))))))]
                      (if
                       (clojure.core/= ?rest input__42248_nth_1__)
                       (save__42807)
                       (f__43898)))))))
                 (state__43773))
                (state__43773)))
              (state__43773)))]
           (state__43862))
          (state__43773)))
        (state__43773
         []
         (clojure.core/letfn
          [(def__42810
            [arg__42843 ?ns]
            (clojure.core/letfn
             [(state__43899
               []
               (clojure.core/let
                [x__42844 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__42844)
                 (clojure.core/let [?env arg__42843] [?env ?ns])
                 (state__43900))))
              (state__43900
               []
               (if
                (clojure.core/map? arg__42843)
                (clojure.core/let
                 [VAL__42845 (.valAt arg__42843 :aliases)]
                 (if
                  (clojure.core/map? VAL__42845)
                  (clojure.core/let
                   [X__42847 (clojure.core/set VAL__42845)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__42847))
                    (clojure.core/loop
                     [search_space__43901 (clojure.core/seq X__42847)]
                     (if
                      (clojure.core/seq search_space__43901)
                      (clojure.core/let
                       [elem__42848
                        (clojure.core/first search_space__43901)
                        result__43902
                        (clojure.core/let
                         [elem__42848_nth_0__
                          (clojure.core/nth elem__42848 0)
                          elem__42848_nth_1__
                          (clojure.core/nth elem__42848 1)]
                         (if
                          (clojure.core/symbol? elem__42848_nth_0__)
                          (clojure.core/let
                           [X__42850
                            (clojure.core/name elem__42848_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__42850)
                            (if
                             (clojure.core/symbol? elem__42848_nth_1__)
                             (clojure.core/let
                              [X__42852
                               (clojure.core/name elem__42848_nth_1__)]
                              (clojure.core/case
                               X__42852
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__42843]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__43902)
                        (recur (clojure.core/next search_space__43901))
                        result__43902))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__43899)))]
          (if
           (clojure.core/vector? input__42248)
           (if
            (clojure.core/= (clojure.core/count input__42248) 3)
            (clojure.core/let
             [input__42248_nth_0__
              (clojure.core/nth input__42248 0)
              input__42248_nth_1__
              (clojure.core/nth input__42248 1)
              input__42248_nth_2__
              (clojure.core/nth input__42248 2)]
             (clojure.core/case
              input__42248_nth_0__
              (meander.dev.parse.zeta/parse-entries)
              (if
               (clojure.core/map? input__42248_nth_1__)
               (clojure.core/let
                [X__42815 (clojure.core/set input__42248_nth_1__)]
                (if
                 (clojure.core/<= 1 (clojure.core/count X__42815))
                 (clojure.core/loop
                  [search_space__43904 (clojure.core/seq X__42815)]
                  (if
                   (clojure.core/seq search_space__43904)
                   (clojure.core/let
                    [elem__42816
                     (clojure.core/first search_space__43904)
                     result__43905
                     (clojure.core/let
                      [elem__42816_nth_0__
                       (clojure.core/nth elem__42816 0)
                       elem__42816_nth_1__
                       (clojure.core/nth elem__42816 1)]
                      (clojure.core/let
                       [*m__42280 elem__42816_nth_0__]
                       (if
                        (clojure.core/symbol? elem__42816_nth_0__)
                        (clojure.core/let
                         [X__42818
                          (clojure.core/namespace elem__42816_nth_0__)]
                         (clojure.core/let
                          [?ns X__42818]
                          (clojure.core/let
                           [X__42820
                            (clojure.core/name elem__42816_nth_0__)]
                           (if
                            (clojure.core/string? X__42820)
                            (if
                             (clojure.core/re-matches #"&.*" X__42820)
                             (clojure.core/let
                              [?pattern elem__42816_nth_1__]
                              (clojure.core/let
                               [X__42822
                                ((clojure.core/fn
                                  [m__13405__auto__]
                                  (clojure.core/dissoc
                                   m__13405__auto__
                                   *m__42280))
                                 input__42248_nth_1__)]
                               (clojure.core/let
                                [?rest X__42822]
                                (clojure.core/let
                                 [x__14338__auto__
                                  (def__42810
                                   input__42248_nth_2__
                                   ?ns)]
                                 (if
                                  (meander.runtime.zeta/fail?
                                   x__14338__auto__)
                                  (meander.runtime.zeta/fail)
                                  (clojure.core/let
                                   [[?env ?ns] x__14338__auto__]
                                   (try
                                    [{:tag :rest-map,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__15641__auto__
                                        (CATA__FN__42325
                                         [?pattern ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__15641__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__15641__auto__
                                         0))),
                                      :next
                                      (clojure.core/let
                                       [CATA_RESULT__15641__auto__
                                        (CATA__FN__42325
                                         ['meander.dev.parse.zeta/parse-entries
                                          ?rest
                                          ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__15641__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__15641__auto__
                                         0)))}]
                                    (catch
                                     java.lang.Exception
                                     e__16581__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__16581__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__16581__auto__))))))))))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))))
                        (meander.runtime.zeta/fail))))]
                    (if
                     (meander.runtime.zeta/fail? result__43905)
                     (recur (clojure.core/next search_space__43904))
                     result__43905))
                   (state__43774)))
                 (state__43774)))
               (state__43774))
              (state__43774)))
            (state__43774))
           (state__43774))))
        (state__43774
         []
         (if
          (clojure.core/vector? input__42248)
          (clojure.core/letfn
           [(state__43907
             []
             (if
              (clojure.core/= (clojure.core/count input__42248) 3)
              (clojure.core/let
               [input__42248_nth_0__
                (clojure.core/nth input__42248 0)
                input__42248_nth_1__
                (clojure.core/nth input__42248 1)
                input__42248_nth_2__
                (clojure.core/nth input__42248 2)]
               (clojure.core/case
                input__42248_nth_0__
                (meander.dev.parse.zeta/parse-entries)
                (clojure.core/letfn
                 [(state__43909
                   []
                   (if
                    (clojure.core/map? input__42248_nth_1__)
                    (clojure.core/let
                     [X__42866 (clojure.core/set input__42248_nth_1__)]
                     (if
                      (clojure.core/<= 1 (clojure.core/count X__42866))
                      (clojure.core/loop
                       [search_space__43911
                        (clojure.core/seq X__42866)]
                       (if
                        (clojure.core/seq search_space__43911)
                        (clojure.core/let
                         [elem__42867
                          (clojure.core/first search_space__43911)
                          result__43912
                          (clojure.core/let
                           [elem__42867_nth_0__
                            (clojure.core/nth elem__42867 0)
                            elem__42867_nth_1__
                            (clojure.core/nth elem__42867 1)]
                           (clojure.core/let
                            [?key-pattern elem__42867_nth_0__]
                            (clojure.core/let
                             [?val-pattern elem__42867_nth_1__]
                             (clojure.core/let
                              [X__42869
                               ((clojure.core/fn
                                 [m__13405__auto__]
                                 (clojure.core/dissoc
                                  m__13405__auto__
                                  ?key-pattern))
                                input__42248_nth_1__)]
                              (clojure.core/let
                               [?rest X__42869]
                               (clojure.core/let
                                [?env input__42248_nth_2__]
                                (try
                                 [{:tag :entry,
                                   :key-pattern
                                   (clojure.core/let
                                    [CATA_RESULT__15641__auto__
                                     (CATA__FN__42325
                                      [?key-pattern ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__15641__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__15641__auto__
                                      0))),
                                   :val-pattern
                                   (clojure.core/let
                                    [CATA_RESULT__15641__auto__
                                     (CATA__FN__42325
                                      [?val-pattern ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__15641__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__15641__auto__
                                      0))),
                                   :next
                                   (clojure.core/let
                                    [CATA_RESULT__15641__auto__
                                     (CATA__FN__42325
                                      ['meander.dev.parse.zeta/parse-entries
                                       ?rest
                                       ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__15641__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__15641__auto__
                                      0)))}]
                                 (catch
                                  java.lang.Exception
                                  e__16581__auto__
                                  (if
                                   (meander.runtime.zeta/fail?
                                    e__16581__auto__)
                                   (meander.runtime.zeta/fail)
                                   (throw e__16581__auto__))))))))))]
                         (if
                          (meander.runtime.zeta/fail? result__43912)
                          (recur
                           (clojure.core/next search_space__43911))
                          result__43912))
                        (state__43910)))
                      (state__43910)))
                    (state__43910)))
                  (state__43910
                   []
                   (if
                    (clojure.core/map? input__42248_nth_1__)
                    (clojure.core/let
                     [?env input__42248_nth_2__]
                     (try
                      [{:tag :some-map}]
                      (catch
                       java.lang.Exception
                       e__16581__auto__
                       (if
                        (meander.runtime.zeta/fail? e__16581__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__16581__auto__)))))
                    (state__43908)))]
                 (state__43909))
                (meander.dev.parse.zeta/parse-with-bindings)
                (clojure.core/letfn
                 [(state__43914
                   []
                   (if
                    (clojure.core/vector? input__42248_nth_1__)
                    (clojure.core/case
                     input__42248_nth_1__
                     ([])
                     (clojure.core/let
                      [?env input__42248_nth_2__]
                      (try
                       [[]]
                       (catch
                        java.lang.Exception
                        e__16581__auto__
                        (if
                         (meander.runtime.zeta/fail? e__16581__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__16581__auto__)))))
                     (state__43915))
                    (state__43915)))
                  (state__43915
                   []
                   (if
                    (clojure.core/vector? input__42248_nth_1__)
                    (if
                     (clojure.core/=
                      (clojure.core/count input__42248_nth_1__)
                      1)
                     (clojure.core/let
                      [?env input__42248_nth_2__]
                      (try
                       [[{:tag :error,
                          :message
                          "meander.zeta/with expects an even number of bindings"}]]
                       (catch
                        java.lang.Exception
                        e__16581__auto__
                        (if
                         (meander.runtime.zeta/fail? e__16581__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__16581__auto__)))))
                     (state__43916))
                    (state__43916)))
                  (state__43916
                   []
                   (if
                    (clojure.core/vector? input__42248_nth_1__)
                    (clojure.core/let
                     [input__42248_nth_1___l__
                      (clojure.core/subvec
                       input__42248_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__42248_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__42248_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__42248_nth_1___r__
                        (clojure.core/subvec input__42248_nth_1__ 2)]
                       (clojure.core/let
                        [input__42248_nth_1___l___nth_0__
                         (clojure.core/nth input__42248_nth_1___l__ 0)
                         input__42248_nth_1___l___nth_1__
                         (clojure.core/nth input__42248_nth_1___l__ 1)]
                        (if
                         (clojure.core/symbol?
                          input__42248_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__42883
                           (clojure.core/namespace
                            input__42248_nth_1___l___nth_0__)]
                          (clojure.core/let
                           [X__42885
                            (clojure.core/name
                             input__42248_nth_1___l___nth_0__)]
                           (if
                            (clojure.core/string? X__42885)
                            (if
                             (clojure.core/re-matches #"%.+" X__42885)
                             (clojure.core/let
                              [?symbol
                               input__42248_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?pattern
                                input__42248_nth_1___l___nth_1__]
                               (clojure.core/let
                                [?rest input__42248_nth_1___r__]
                                (clojure.core/let
                                 [?env input__42248_nth_2__]
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
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__42325
                                          [?pattern ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__15641__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__15641__auto__
                                          0)))})
                                     (clojure.core/let
                                      [CATA_RESULT__15641__auto__
                                       (CATA__FN__42325
                                        ['meander.dev.parse.zeta/parse-with-bindings
                                         ?rest
                                         ?env])]
                                      (if
                                       (meander.runtime.zeta/fail?
                                        CATA_RESULT__15641__auto__)
                                       (throw
                                        (meander.runtime.zeta/fail))
                                       (clojure.core/nth
                                        CATA_RESULT__15641__auto__
                                        0)))))]
                                  (catch
                                   java.lang.Exception
                                   e__16581__auto__
                                   (if
                                    (meander.runtime.zeta/fail?
                                     e__16581__auto__)
                                    (meander.runtime.zeta/fail)
                                    (throw e__16581__auto__))))))))
                             (state__43917))
                            (state__43917))))
                         (state__43917))))
                      (state__43917)))
                    (state__43917)))
                  (state__43917
                   []
                   (if
                    (clojure.core/vector? input__42248_nth_1__)
                    (clojure.core/let
                     [input__42248_nth_1___l__
                      (clojure.core/subvec
                       input__42248_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__42248_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__42248_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__42248_nth_1___r__
                        (clojure.core/subvec input__42248_nth_1__ 2)]
                       (clojure.core/let
                        [input__42248_nth_1___l___nth_0__
                         (clojure.core/nth input__42248_nth_1___l__ 0)
                         input__42248_nth_1___l___nth_1__
                         (clojure.core/nth input__42248_nth_1___l__ 1)]
                        (clojure.core/let
                         [?x input__42248_nth_1___l___nth_0__]
                         (clojure.core/let
                          [?pattern input__42248_nth_1___l___nth_1__]
                          (clojure.core/let
                           [?rest input__42248_nth_1___r__]
                           (clojure.core/let
                            [?env input__42248_nth_2__]
                            (try
                             [[{:tag :error,
                                :message
                                "meander.zeta/with bindings must be an repeating sequence of %name pattern"}]]
                             (catch
                              java.lang.Exception
                              e__16581__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__16581__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__16581__auto__))))))))))
                      (state__43908)))
                    (state__43908)))]
                 (state__43914))
                (state__43908)))
              (state__43908)))
            (state__43908
             []
             (if
              (clojure.core/= (clojure.core/count input__42248) 2)
              (clojure.core/let
               [input__42248_nth_0__
                (clojure.core/nth input__42248 0)
                input__42248_nth_1__
                (clojure.core/nth input__42248 1)]
               (if
                (clojure.core/vector? input__42248_nth_0__)
                (clojure.core/let
                 [?sequence input__42248_nth_0__]
                 (clojure.core/let
                  [?form input__42248_nth_0__]
                  (clojure.core/let
                   [?env input__42248_nth_1__]
                   (try
                    [{:tag :vector,
                      :next
                      (clojure.core/let
                       [CATA_RESULT__15641__auto__
                        (CATA__FN__42325
                         ['meander.dev.parse.zeta/parse-sequential
                          ?sequence
                          (clojure.core/let
                           [form__15740__auto__ {:context :vector}]
                           (clojure.core/merge
                            (clojure.core/into {} ?env)
                            form__15740__auto__))])]
                       (if
                        (meander.runtime.zeta/fail?
                         CATA_RESULT__15641__auto__)
                        (throw (meander.runtime.zeta/fail))
                        (clojure.core/nth
                         CATA_RESULT__15641__auto__
                         0))),
                      :form ?form}]
                    (catch
                     java.lang.Exception
                     e__16581__auto__
                     (if
                      (meander.runtime.zeta/fail? e__16581__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__16581__auto__)))))))
                (state__43775)))
              (state__43775)))]
           (state__43907))
          (state__43775)))
        (state__43775
         []
         (clojure.core/letfn
          [(def__42895
            [arg__42918 ?__42249]
            (clojure.core/letfn
             [(state__43918
               []
               (clojure.core/let
                [x__42919 "clojure.core"]
                (if
                 (clojure.core/= ?__42249 x__42919)
                 [?__42249]
                 (state__43919))))
              (state__43919
               []
               (if
                (clojure.core/map? arg__42918)
                (clojure.core/let
                 [VAL__42920 (.valAt arg__42918 :aliases)]
                 (if
                  (clojure.core/map? VAL__42920)
                  (clojure.core/let
                   [X__42922 (clojure.core/set VAL__42920)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__42922))
                    (clojure.core/loop
                     [search_space__43920 (clojure.core/seq X__42922)]
                     (if
                      (clojure.core/seq search_space__43920)
                      (clojure.core/let
                       [elem__42923
                        (clojure.core/first search_space__43920)
                        result__43921
                        (clojure.core/let
                         [elem__42923_nth_0__
                          (clojure.core/nth elem__42923 0)
                          elem__42923_nth_1__
                          (clojure.core/nth elem__42923 1)]
                         (if
                          (clojure.core/symbol? elem__42923_nth_0__)
                          (clojure.core/let
                           [X__42925
                            (clojure.core/name elem__42923_nth_0__)]
                           (if
                            (clojure.core/= ?__42249 X__42925)
                            (if
                             (clojure.core/symbol? elem__42923_nth_1__)
                             (clojure.core/let
                              [X__42927
                               (clojure.core/name elem__42923_nth_1__)]
                              (clojure.core/case
                               X__42927
                               ("clojure.core")
                               [?__42249]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__43921)
                        (recur (clojure.core/next search_space__43920))
                        result__43921))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__43918)))]
          (if
           (clojure.core/vector? input__42248)
           (if
            (clojure.core/= (clojure.core/count input__42248) 2)
            (clojure.core/let
             [input__42248_nth_0__
              (clojure.core/nth input__42248 0)
              input__42248_nth_1__
              (clojure.core/nth input__42248 1)]
             (if
              (clojure.core/seq? input__42248_nth_0__)
              (clojure.core/let
               [input__42248_nth_0___l__
                (clojure.core/take 1 input__42248_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__42248_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__42248_nth_0___r__
                  (clojure.core/drop 1 input__42248_nth_0__)]
                 (clojure.core/let
                  [input__42248_nth_0___l___nth_0__
                   (clojure.core/nth input__42248_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__42248_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__42905
                     (clojure.core/namespace
                      input__42248_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__42249 X__42905]
                     (clojure.core/let
                      [X__42907
                       (clojure.core/name
                        input__42248_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__42907
                       ("unquote")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__42895 input__42248_nth_1__ ?__42249)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__43776)
                         (clojure.core/let
                          [[?__42249] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__42248)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__42248)
                             2)
                            (clojure.core/let
                             [input__42248_nth_0__
                              (clojure.core/nth input__42248 0)
                              input__42248_nth_1__
                              (clojure.core/nth input__42248 1)]
                             (if
                              (clojure.core/seq? input__42248_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__42248_nth_0__)
                                2)
                               (clojure.core/let
                                [input__42248_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__42248_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?x input__42248_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__42248_nth_0__]
                                  (clojure.core/let
                                   [?env input__42248_nth_1__]
                                   (try
                                    [{:tag :host-expression,
                                      :expression ?x,
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__16581__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__16581__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__16581__auto__))))))))
                               (state__43776))
                              (state__43776)))
                            (state__43776))
                           (state__43776)))))
                       (state__43776)))))
                   (state__43776))))
                (state__43776)))
              (state__43776)))
            (state__43776))
           (state__43776))))
        (state__43776
         []
         (clojure.core/letfn
          [(def__42929
            [arg__42952 ?__42250]
            (clojure.core/letfn
             [(state__43923
               []
               (clojure.core/let
                [x__42953 "meander.zeta"]
                (if
                 (clojure.core/= ?__42250 x__42953)
                 [?__42250]
                 (state__43924))))
              (state__43924
               []
               (if
                (clojure.core/map? arg__42952)
                (clojure.core/let
                 [VAL__42954 (.valAt arg__42952 :aliases)]
                 (if
                  (clojure.core/map? VAL__42954)
                  (clojure.core/let
                   [X__42956 (clojure.core/set VAL__42954)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__42956))
                    (clojure.core/loop
                     [search_space__43925 (clojure.core/seq X__42956)]
                     (if
                      (clojure.core/seq search_space__43925)
                      (clojure.core/let
                       [elem__42957
                        (clojure.core/first search_space__43925)
                        result__43926
                        (clojure.core/let
                         [elem__42957_nth_0__
                          (clojure.core/nth elem__42957 0)
                          elem__42957_nth_1__
                          (clojure.core/nth elem__42957 1)]
                         (if
                          (clojure.core/symbol? elem__42957_nth_0__)
                          (clojure.core/let
                           [X__42959
                            (clojure.core/name elem__42957_nth_0__)]
                           (if
                            (clojure.core/= ?__42250 X__42959)
                            (if
                             (clojure.core/symbol? elem__42957_nth_1__)
                             (clojure.core/let
                              [X__42961
                               (clojure.core/name elem__42957_nth_1__)]
                              (clojure.core/case
                               X__42961
                               ("meander.zeta")
                               [?__42250]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__43926)
                        (recur (clojure.core/next search_space__43925))
                        result__43926))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__43923)))]
          (if
           (clojure.core/vector? input__42248)
           (if
            (clojure.core/= (clojure.core/count input__42248) 2)
            (clojure.core/let
             [input__42248_nth_0__
              (clojure.core/nth input__42248 0)
              input__42248_nth_1__
              (clojure.core/nth input__42248 1)]
             (if
              (clojure.core/seq? input__42248_nth_0__)
              (clojure.core/let
               [input__42248_nth_0___l__
                (clojure.core/take 1 input__42248_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__42248_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__42248_nth_0___r__
                  (clojure.core/drop 1 input__42248_nth_0__)]
                 (clojure.core/let
                  [input__42248_nth_0___l___nth_0__
                   (clojure.core/nth input__42248_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__42248_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__42939
                     (clojure.core/namespace
                      input__42248_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__42250 X__42939]
                     (clojure.core/let
                      [X__42941
                       (clojure.core/name
                        input__42248_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__42941
                       ("*")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__42929 input__42248_nth_1__ ?__42250)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__43777)
                         (clojure.core/let
                          [[?__42250] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__42248)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__42248)
                             2)
                            (clojure.core/let
                             [input__42248_nth_0__
                              (clojure.core/nth input__42248 0)
                              input__42248_nth_1__
                              (clojure.core/nth input__42248 1)]
                             (if
                              (clojure.core/seq? input__42248_nth_0__)
                              (clojure.core/let
                               [input__42248_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__42248_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__42248_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__42248_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__42248_nth_0__)]
                                 (clojure.core/let
                                  [?patterns input__42248_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__42248_nth_0__]
                                   (clojure.core/let
                                    [?env input__42248_nth_1__]
                                    (try
                                     [{:tag :star,
                                       :greedy? true,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__42325
                                          ['meander.dev.parse.zeta/parse-sequential
                                           (clojure.core/into
                                            []
                                            ?patterns)
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__15641__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__15641__auto__
                                          0))),
                                       :next {:tag :empty}}]
                                     (catch
                                      java.lang.Exception
                                      e__16581__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__16581__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__16581__auto__))))))))
                                (state__43777)))
                              (state__43777)))
                            (state__43777))
                           (state__43777)))))
                       (state__43777)))))
                   (state__43777))))
                (state__43777)))
              (state__43777)))
            (state__43777))
           (state__43777))))
        (state__43777
         []
         (clojure.core/letfn
          [(def__42963
            [arg__42986 ?__42251]
            (clojure.core/letfn
             [(state__43928
               []
               (clojure.core/let
                [x__42987 "meander.zeta"]
                (if
                 (clojure.core/= ?__42251 x__42987)
                 [?__42251]
                 (state__43929))))
              (state__43929
               []
               (if
                (clojure.core/map? arg__42986)
                (clojure.core/let
                 [VAL__42988 (.valAt arg__42986 :aliases)]
                 (if
                  (clojure.core/map? VAL__42988)
                  (clojure.core/let
                   [X__42990 (clojure.core/set VAL__42988)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__42990))
                    (clojure.core/loop
                     [search_space__43930 (clojure.core/seq X__42990)]
                     (if
                      (clojure.core/seq search_space__43930)
                      (clojure.core/let
                       [elem__42991
                        (clojure.core/first search_space__43930)
                        result__43931
                        (clojure.core/let
                         [elem__42991_nth_0__
                          (clojure.core/nth elem__42991 0)
                          elem__42991_nth_1__
                          (clojure.core/nth elem__42991 1)]
                         (if
                          (clojure.core/symbol? elem__42991_nth_0__)
                          (clojure.core/let
                           [X__42993
                            (clojure.core/name elem__42991_nth_0__)]
                           (if
                            (clojure.core/= ?__42251 X__42993)
                            (if
                             (clojure.core/symbol? elem__42991_nth_1__)
                             (clojure.core/let
                              [X__42995
                               (clojure.core/name elem__42991_nth_1__)]
                              (clojure.core/case
                               X__42995
                               ("meander.zeta")
                               [?__42251]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__43931)
                        (recur (clojure.core/next search_space__43930))
                        result__43931))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__43928)))]
          (if
           (clojure.core/vector? input__42248)
           (if
            (clojure.core/= (clojure.core/count input__42248) 2)
            (clojure.core/let
             [input__42248_nth_0__
              (clojure.core/nth input__42248 0)
              input__42248_nth_1__
              (clojure.core/nth input__42248 1)]
             (if
              (clojure.core/seq? input__42248_nth_0__)
              (clojure.core/let
               [input__42248_nth_0___l__
                (clojure.core/take 1 input__42248_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__42248_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__42248_nth_0___r__
                  (clojure.core/drop 1 input__42248_nth_0__)]
                 (clojure.core/let
                  [input__42248_nth_0___l___nth_0__
                   (clojure.core/nth input__42248_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__42248_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__42973
                     (clojure.core/namespace
                      input__42248_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__42251 X__42973]
                     (clojure.core/let
                      [X__42975
                       (clojure.core/name
                        input__42248_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__42975
                       ("<>")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__42963 input__42248_nth_1__ ?__42251)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__43778)
                         (clojure.core/let
                          [[?__42251] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__42248)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__42248)
                             2)
                            (clojure.core/let
                             [input__42248_nth_0__
                              (clojure.core/nth input__42248 0)
                              input__42248_nth_1__
                              (clojure.core/nth input__42248 1)]
                             (if
                              (clojure.core/seq? input__42248_nth_0__)
                              (clojure.core/let
                               [input__42248_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__42248_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__42248_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__42248_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__42248_nth_0__)]
                                 (clojure.core/let
                                  [?patterns input__42248_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__42248_nth_0__]
                                   (clojure.core/let
                                    [?env input__42248_nth_1__]
                                    (try
                                     [{:tag :group,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__42325
                                          ['meander.dev.parse.zeta/parse-sequential
                                           (clojure.core/into
                                            []
                                            ?patterns)
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__15641__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__15641__auto__
                                          0)))}]
                                     (catch
                                      java.lang.Exception
                                      e__16581__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__16581__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__16581__auto__))))))))
                                (state__43778)))
                              (state__43778)))
                            (state__43778))
                           (state__43778)))))
                       (state__43778)))))
                   (state__43778))))
                (state__43778)))
              (state__43778)))
            (state__43778))
           (state__43778))))
        (state__43778
         []
         (clojure.core/letfn
          [(def__42997
            [arg__43020 ?__42252]
            (clojure.core/letfn
             [(state__43933
               []
               (clojure.core/let
                [x__43021 "meander.math.zeta"]
                (if
                 (clojure.core/= ?__42252 x__43021)
                 [?__42252]
                 (state__43934))))
              (state__43934
               []
               (if
                (clojure.core/map? arg__43020)
                (clojure.core/let
                 [VAL__43022 (.valAt arg__43020 :aliases)]
                 (if
                  (clojure.core/map? VAL__43022)
                  (clojure.core/let
                   [X__43024 (clojure.core/set VAL__43022)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__43024))
                    (clojure.core/loop
                     [search_space__43935 (clojure.core/seq X__43024)]
                     (if
                      (clojure.core/seq search_space__43935)
                      (clojure.core/let
                       [elem__43025
                        (clojure.core/first search_space__43935)
                        result__43936
                        (clojure.core/let
                         [elem__43025_nth_0__
                          (clojure.core/nth elem__43025 0)
                          elem__43025_nth_1__
                          (clojure.core/nth elem__43025 1)]
                         (if
                          (clojure.core/symbol? elem__43025_nth_0__)
                          (clojure.core/let
                           [X__43027
                            (clojure.core/name elem__43025_nth_0__)]
                           (if
                            (clojure.core/= ?__42252 X__43027)
                            (if
                             (clojure.core/symbol? elem__43025_nth_1__)
                             (clojure.core/let
                              [X__43029
                               (clojure.core/name elem__43025_nth_1__)]
                              (clojure.core/case
                               X__43029
                               ("meander.math.zeta")
                               [?__42252]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__43936)
                        (recur (clojure.core/next search_space__43935))
                        result__43936))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__43933)))]
          (if
           (clojure.core/vector? input__42248)
           (if
            (clojure.core/= (clojure.core/count input__42248) 2)
            (clojure.core/let
             [input__42248_nth_0__
              (clojure.core/nth input__42248 0)
              input__42248_nth_1__
              (clojure.core/nth input__42248 1)]
             (if
              (clojure.core/seq? input__42248_nth_0__)
              (clojure.core/let
               [input__42248_nth_0___l__
                (clojure.core/take 1 input__42248_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__42248_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__42248_nth_0___r__
                  (clojure.core/drop 1 input__42248_nth_0__)]
                 (clojure.core/let
                  [input__42248_nth_0___l___nth_0__
                   (clojure.core/nth input__42248_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__42248_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__43007
                     (clojure.core/namespace
                      input__42248_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__42252 X__43007]
                     (clojure.core/let
                      [X__43009
                       (clojure.core/name
                        input__42248_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__43009
                       ("+")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__42997 input__42248_nth_1__ ?__42252)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__43779)
                         (clojure.core/let
                          [[?__42252] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__42248)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__42248)
                             2)
                            (clojure.core/let
                             [input__42248_nth_0__
                              (clojure.core/nth input__42248 0)
                              input__42248_nth_1__
                              (clojure.core/nth input__42248 1)]
                             (if
                              (clojure.core/seq? input__42248_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__42248_nth_0__)
                                3)
                               (clojure.core/let
                                [input__42248_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__42248_nth_0__
                                  1)
                                 input__42248_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__42248_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?a input__42248_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?b input__42248_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__42248_nth_0__]
                                   (clojure.core/let
                                    [?env input__42248_nth_1__]
                                    (try
                                     [{:tag :meander.math.zeta/+,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__42325 [?a ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__15641__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__15641__auto__
                                          0))),
                                       :right
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__42325 [?b ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__15641__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__15641__auto__
                                          0)))}]
                                     (catch
                                      java.lang.Exception
                                      e__16581__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__16581__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__16581__auto__)))))))))
                               (state__43779))
                              (state__43779)))
                            (state__43779))
                           (state__43779)))))
                       (state__43779)))))
                   (state__43779))))
                (state__43779)))
              (state__43779)))
            (state__43779))
           (state__43779))))
        (state__43779
         []
         (clojure.core/letfn
          [(def__43031
            [arg__43054 ?__42253]
            (clojure.core/letfn
             [(state__43938
               []
               (clojure.core/let
                [x__43055 "meander.math.zeta"]
                (if
                 (clojure.core/= ?__42253 x__43055)
                 [?__42253]
                 (state__43939))))
              (state__43939
               []
               (if
                (clojure.core/map? arg__43054)
                (clojure.core/let
                 [VAL__43056 (.valAt arg__43054 :aliases)]
                 (if
                  (clojure.core/map? VAL__43056)
                  (clojure.core/let
                   [X__43058 (clojure.core/set VAL__43056)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__43058))
                    (clojure.core/loop
                     [search_space__43940 (clojure.core/seq X__43058)]
                     (if
                      (clojure.core/seq search_space__43940)
                      (clojure.core/let
                       [elem__43059
                        (clojure.core/first search_space__43940)
                        result__43941
                        (clojure.core/let
                         [elem__43059_nth_0__
                          (clojure.core/nth elem__43059 0)
                          elem__43059_nth_1__
                          (clojure.core/nth elem__43059 1)]
                         (if
                          (clojure.core/symbol? elem__43059_nth_0__)
                          (clojure.core/let
                           [X__43061
                            (clojure.core/name elem__43059_nth_0__)]
                           (if
                            (clojure.core/= ?__42253 X__43061)
                            (if
                             (clojure.core/symbol? elem__43059_nth_1__)
                             (clojure.core/let
                              [X__43063
                               (clojure.core/name elem__43059_nth_1__)]
                              (clojure.core/case
                               X__43063
                               ("meander.math.zeta")
                               [?__42253]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__43941)
                        (recur (clojure.core/next search_space__43940))
                        result__43941))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__43938)))]
          (if
           (clojure.core/vector? input__42248)
           (if
            (clojure.core/= (clojure.core/count input__42248) 2)
            (clojure.core/let
             [input__42248_nth_0__
              (clojure.core/nth input__42248 0)
              input__42248_nth_1__
              (clojure.core/nth input__42248 1)]
             (if
              (clojure.core/seq? input__42248_nth_0__)
              (clojure.core/let
               [input__42248_nth_0___l__
                (clojure.core/take 1 input__42248_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__42248_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__42248_nth_0___r__
                  (clojure.core/drop 1 input__42248_nth_0__)]
                 (clojure.core/let
                  [input__42248_nth_0___l___nth_0__
                   (clojure.core/nth input__42248_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__42248_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__43041
                     (clojure.core/namespace
                      input__42248_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__42253 X__43041]
                     (clojure.core/let
                      [X__43043
                       (clojure.core/name
                        input__42248_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__43043
                       ("-")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__43031 input__42248_nth_1__ ?__42253)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__43780)
                         (clojure.core/let
                          [[?__42253] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__42248)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__42248)
                             2)
                            (clojure.core/let
                             [input__42248_nth_0__
                              (clojure.core/nth input__42248 0)
                              input__42248_nth_1__
                              (clojure.core/nth input__42248 1)]
                             (if
                              (clojure.core/seq? input__42248_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__42248_nth_0__)
                                3)
                               (clojure.core/let
                                [input__42248_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__42248_nth_0__
                                  1)
                                 input__42248_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__42248_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?a input__42248_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?b input__42248_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__42248_nth_0__]
                                   (clojure.core/let
                                    [?env input__42248_nth_1__]
                                    (try
                                     [{:tag :meander.math.zeta/-,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__42325 [?a ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__15641__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__15641__auto__
                                          0))),
                                       :right
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__42325 [?b ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__15641__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__15641__auto__
                                          0)))}]
                                     (catch
                                      java.lang.Exception
                                      e__16581__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__16581__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__16581__auto__)))))))))
                               (state__43780))
                              (state__43780)))
                            (state__43780))
                           (state__43780)))))
                       (state__43780)))))
                   (state__43780))))
                (state__43780)))
              (state__43780)))
            (state__43780))
           (state__43780))))
        (state__43780
         []
         (clojure.core/letfn
          [(def__43065
            [arg__43088 ?__42254]
            (clojure.core/letfn
             [(state__43943
               []
               (clojure.core/let
                [x__43089 "meander.zeta"]
                (if
                 (clojure.core/= ?__42254 x__43089)
                 [?__42254]
                 (state__43944))))
              (state__43944
               []
               (if
                (clojure.core/map? arg__43088)
                (clojure.core/let
                 [VAL__43090 (.valAt arg__43088 :aliases)]
                 (if
                  (clojure.core/map? VAL__43090)
                  (clojure.core/let
                   [X__43092 (clojure.core/set VAL__43090)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__43092))
                    (clojure.core/loop
                     [search_space__43945 (clojure.core/seq X__43092)]
                     (if
                      (clojure.core/seq search_space__43945)
                      (clojure.core/let
                       [elem__43093
                        (clojure.core/first search_space__43945)
                        result__43946
                        (clojure.core/let
                         [elem__43093_nth_0__
                          (clojure.core/nth elem__43093 0)
                          elem__43093_nth_1__
                          (clojure.core/nth elem__43093 1)]
                         (if
                          (clojure.core/symbol? elem__43093_nth_0__)
                          (clojure.core/let
                           [X__43095
                            (clojure.core/name elem__43093_nth_0__)]
                           (if
                            (clojure.core/= ?__42254 X__43095)
                            (if
                             (clojure.core/symbol? elem__43093_nth_1__)
                             (clojure.core/let
                              [X__43097
                               (clojure.core/name elem__43093_nth_1__)]
                              (clojure.core/case
                               X__43097
                               ("meander.zeta")
                               [?__42254]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__43946)
                        (recur (clojure.core/next search_space__43945))
                        result__43946))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__43943)))]
          (if
           (clojure.core/vector? input__42248)
           (if
            (clojure.core/= (clojure.core/count input__42248) 2)
            (clojure.core/let
             [input__42248_nth_0__
              (clojure.core/nth input__42248 0)
              input__42248_nth_1__
              (clojure.core/nth input__42248 1)]
             (if
              (clojure.core/seq? input__42248_nth_0__)
              (clojure.core/let
               [input__42248_nth_0___l__
                (clojure.core/take 1 input__42248_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__42248_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__42248_nth_0___r__
                  (clojure.core/drop 1 input__42248_nth_0__)]
                 (clojure.core/let
                  [input__42248_nth_0___l___nth_0__
                   (clojure.core/nth input__42248_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__42248_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__43075
                     (clojure.core/namespace
                      input__42248_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__42254 X__43075]
                     (clojure.core/let
                      [X__43077
                       (clojure.core/name
                        input__42248_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__43077
                       ("with")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__43065 input__42248_nth_1__ ?__42254)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__43781)
                         (clojure.core/let
                          [[?__42254] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__42248)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__42248)
                             2)
                            (clojure.core/let
                             [input__42248_nth_0__
                              (clojure.core/nth input__42248 0)
                              input__42248_nth_1__
                              (clojure.core/nth input__42248 1)]
                             (if
                              (clojure.core/seq? input__42248_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__42248_nth_0__)
                                3)
                               (clojure.core/let
                                [input__42248_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__42248_nth_0__
                                  1)
                                 input__42248_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__42248_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?bindings
                                  input__42248_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?body input__42248_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__42248_nth_0__]
                                   (clojure.core/let
                                    [?env input__42248_nth_1__]
                                    (try
                                     [{:tag :with,
                                       :bindings
                                       {:tag :with-bindings,
                                        :bindings
                                        (clojure.core/let
                                         [CATA_RESULT__15641__auto__
                                          (CATA__FN__42325
                                           ['meander.dev.parse.zeta/parse-with-bindings
                                            ?bindings
                                            ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__15641__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__15641__auto__
                                           0)))},
                                       :body
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__42325
                                          [?body ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__15641__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__15641__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__16581__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__16581__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__16581__auto__)))))))))
                               (state__43781))
                              (state__43781)))
                            (state__43781))
                           (state__43781)))))
                       (state__43781)))))
                   (state__43781))))
                (state__43781)))
              (state__43781)))
            (state__43781))
           (state__43781))))
        (state__43781
         []
         (clojure.core/letfn
          [(def__43099
            [arg__43122 ?__42255]
            (clojure.core/letfn
             [(state__43948
               []
               (clojure.core/let
                [x__43123 "meander.zeta"]
                (if
                 (clojure.core/= ?__42255 x__43123)
                 [?__42255]
                 (state__43949))))
              (state__43949
               []
               (if
                (clojure.core/map? arg__43122)
                (clojure.core/let
                 [VAL__43124 (.valAt arg__43122 :aliases)]
                 (if
                  (clojure.core/map? VAL__43124)
                  (clojure.core/let
                   [X__43126 (clojure.core/set VAL__43124)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__43126))
                    (clojure.core/loop
                     [search_space__43950 (clojure.core/seq X__43126)]
                     (if
                      (clojure.core/seq search_space__43950)
                      (clojure.core/let
                       [elem__43127
                        (clojure.core/first search_space__43950)
                        result__43951
                        (clojure.core/let
                         [elem__43127_nth_0__
                          (clojure.core/nth elem__43127 0)
                          elem__43127_nth_1__
                          (clojure.core/nth elem__43127 1)]
                         (if
                          (clojure.core/symbol? elem__43127_nth_0__)
                          (clojure.core/let
                           [X__43129
                            (clojure.core/name elem__43127_nth_0__)]
                           (if
                            (clojure.core/= ?__42255 X__43129)
                            (if
                             (clojure.core/symbol? elem__43127_nth_1__)
                             (clojure.core/let
                              [X__43131
                               (clojure.core/name elem__43127_nth_1__)]
                              (clojure.core/case
                               X__43131
                               ("meander.zeta")
                               [?__42255]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__43951)
                        (recur (clojure.core/next search_space__43950))
                        result__43951))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__43948)))]
          (if
           (clojure.core/vector? input__42248)
           (if
            (clojure.core/= (clojure.core/count input__42248) 2)
            (clojure.core/let
             [input__42248_nth_0__
              (clojure.core/nth input__42248 0)
              input__42248_nth_1__
              (clojure.core/nth input__42248 1)]
             (if
              (clojure.core/seq? input__42248_nth_0__)
              (clojure.core/let
               [input__42248_nth_0___l__
                (clojure.core/take 1 input__42248_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__42248_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__42248_nth_0___r__
                  (clojure.core/drop 1 input__42248_nth_0__)]
                 (clojure.core/let
                  [input__42248_nth_0___l___nth_0__
                   (clojure.core/nth input__42248_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__42248_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__43109
                     (clojure.core/namespace
                      input__42248_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__42255 X__43109]
                     (clojure.core/let
                      [X__43111
                       (clojure.core/name
                        input__42248_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__43111
                       ("apply")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__43099 input__42248_nth_1__ ?__42255)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__43782)
                         (clojure.core/let
                          [[?__42255] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__42248)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__42248)
                             2)
                            (clojure.core/let
                             [input__42248_nth_0__
                              (clojure.core/nth input__42248 0)
                              input__42248_nth_1__
                              (clojure.core/nth input__42248 1)]
                             (if
                              (clojure.core/seq? input__42248_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__42248_nth_0__)
                                3)
                               (clojure.core/let
                                [input__42248_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__42248_nth_0__
                                  1)
                                 input__42248_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__42248_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?fn input__42248_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?pattern
                                   input__42248_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__42248_nth_0__]
                                   (clojure.core/let
                                    [?env input__42248_nth_1__]
                                    (try
                                     [{:tag :apply,
                                       :fn ?fn,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__42325
                                          [?pattern ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__15641__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__15641__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__16581__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__16581__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__16581__auto__)))))))))
                               (state__43782))
                              (state__43782)))
                            (state__43782))
                           (state__43782)))))
                       (state__43782)))))
                   (state__43782))))
                (state__43782)))
              (state__43782)))
            (state__43782))
           (state__43782))))
        (state__43782
         []
         (clojure.core/letfn
          [(def__43133
            [arg__43158 ?__42256]
            (clojure.core/letfn
             [(state__43953
               []
               (clojure.core/let
                [x__43159 "meander.zeta"]
                (if
                 (clojure.core/= ?__42256 x__43159)
                 [?__42256]
                 (state__43954))))
              (state__43954
               []
               (if
                (clojure.core/map? arg__43158)
                (clojure.core/let
                 [VAL__43160 (.valAt arg__43158 :aliases)]
                 (if
                  (clojure.core/map? VAL__43160)
                  (clojure.core/let
                   [X__43162 (clojure.core/set VAL__43160)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__43162))
                    (clojure.core/loop
                     [search_space__43955 (clojure.core/seq X__43162)]
                     (if
                      (clojure.core/seq search_space__43955)
                      (clojure.core/let
                       [elem__43163
                        (clojure.core/first search_space__43955)
                        result__43956
                        (clojure.core/let
                         [elem__43163_nth_0__
                          (clojure.core/nth elem__43163 0)
                          elem__43163_nth_1__
                          (clojure.core/nth elem__43163 1)]
                         (if
                          (clojure.core/symbol? elem__43163_nth_0__)
                          (clojure.core/let
                           [X__43165
                            (clojure.core/name elem__43163_nth_0__)]
                           (if
                            (clojure.core/= ?__42256 X__43165)
                            (if
                             (clojure.core/symbol? elem__43163_nth_1__)
                             (clojure.core/let
                              [X__43167
                               (clojure.core/name elem__43163_nth_1__)]
                              (clojure.core/case
                               X__43167
                               ("meander.zeta")
                               [?__42256]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__43956)
                        (recur (clojure.core/next search_space__43955))
                        result__43956))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__43953)))]
          (if
           (clojure.core/vector? input__42248)
           (if
            (clojure.core/= (clojure.core/count input__42248) 2)
            (clojure.core/let
             [input__42248_nth_0__
              (clojure.core/nth input__42248 0)
              input__42248_nth_1__
              (clojure.core/nth input__42248 1)]
             (if
              (clojure.core/seq? input__42248_nth_0__)
              (clojure.core/let
               [input__42248_nth_0___l__
                (clojure.core/take 1 input__42248_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__42248_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__42248_nth_0___r__
                  (clojure.core/drop 1 input__42248_nth_0__)]
                 (clojure.core/let
                  [input__42248_nth_0___l___nth_0__
                   (clojure.core/nth input__42248_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__42248_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__43145
                     (clojure.core/namespace
                      input__42248_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__42256 X__43145]
                     (clojure.core/let
                      [X__43147
                       (clojure.core/name
                        input__42248_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__43147
                       ("and")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__43133 input__42248_nth_1__ ?__42256)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__43783)
                         (clojure.core/let
                          [[?__42256] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__42248)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__42248)
                             2)
                            (clojure.core/let
                             [input__42248_nth_0__
                              (clojure.core/nth input__42248 0)
                              input__42248_nth_1__
                              (clojure.core/nth input__42248 1)]
                             (if
                              (clojure.core/seq? input__42248_nth_0__)
                              (clojure.core/let
                               [input__42248_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__42248_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__42248_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__42248_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__42248_nth_0__)]
                                 (clojure.core/let
                                  [!forms
                                   (clojure.core/vec
                                    input__42248_nth_0___r__)]
                                  (clojure.core/let
                                   [?form input__42248_nth_0__]
                                   (clojure.core/let
                                    [?env input__42248_nth_1__]
                                    (try
                                     [(clojure.core/let
                                       [!forms__counter
                                        (meander.runtime.zeta/iterator
                                         !forms)]
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__42325
                                          ['meander.dev.parse.zeta/make-and
                                           (clojure.core/into
                                            []
                                            (clojure.core/loop
                                             [return__42327
                                              (clojure.core/transient
                                               [])]
                                             (if
                                              (clojure.core/and
                                               (.hasNext
                                                !forms__counter))
                                              (recur
                                               (clojure.core/conj!
                                                return__42327
                                                (clojure.core/let
                                                 [CATA_RESULT__15641__auto__
                                                  (CATA__FN__42325
                                                   [(if
                                                     (.hasNext
                                                      !forms__counter)
                                                     (.next
                                                      !forms__counter))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__15641__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__15641__auto__
                                                   0)))))
                                              (clojure.core/persistent!
                                               return__42327))))
                                           ?form])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__15641__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__15641__auto__
                                          0))))]
                                     (catch
                                      java.lang.Exception
                                      e__16581__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__16581__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__16581__auto__))))))))
                                (state__43783)))
                              (state__43783)))
                            (state__43783))
                           (state__43783)))))
                       (state__43783)))))
                   (state__43783))))
                (state__43783)))
              (state__43783)))
            (state__43783))
           (state__43783))))
        (state__43783
         []
         (if
          (clojure.core/vector? input__42248)
          (if
           (clojure.core/= (clojure.core/count input__42248) 3)
           (clojure.core/let
            [input__42248_nth_0__
             (clojure.core/nth input__42248 0)
             input__42248_nth_1__
             (clojure.core/nth input__42248 1)
             input__42248_nth_2__
             (clojure.core/nth input__42248 2)]
            (clojure.core/case
             input__42248_nth_0__
             (meander.dev.parse.zeta/make-and)
             (clojure.core/letfn
              [(state__43958
                []
                (if
                 (clojure.core/vector? input__42248_nth_1__)
                 (clojure.core/case
                  input__42248_nth_1__
                  ([])
                  (clojure.core/let
                   [?form input__42248_nth_2__]
                   (try
                    [{:tag :error,
                      :message
                      "meander.zeta/and requires 1 or more arguments",
                      :form ?form}]
                    (catch
                     java.lang.Exception
                     e__16581__auto__
                     (if
                      (meander.runtime.zeta/fail? e__16581__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__16581__auto__)))))
                  (state__43959))
                 (state__43959)))
               (state__43959
                []
                (clojure.core/case
                 input__42248_nth_2__
                 (nil)
                 (if
                  (clojure.core/vector? input__42248_nth_1__)
                  (if
                   (clojure.core/=
                    (clojure.core/count input__42248_nth_1__)
                    1)
                   (clojure.core/let
                    [input__42248_nth_1___nth_0__
                     (clojure.core/nth input__42248_nth_1__ 0)]
                    (clojure.core/let
                     [?ast-a input__42248_nth_1___nth_0__]
                     (try
                      [?ast-a]
                      (catch
                       java.lang.Exception
                       e__16581__auto__
                       (if
                        (meander.runtime.zeta/fail? e__16581__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__16581__auto__))))))
                   (state__43960))
                  (state__43960))
                 (state__43960)))
               (state__43960
                []
                (if
                 (clojure.core/vector? input__42248_nth_1__)
                 (clojure.core/letfn
                  [(state__43961
                    []
                    (if
                     (clojure.core/=
                      (clojure.core/count input__42248_nth_1__)
                      1)
                     (clojure.core/let
                      [input__42248_nth_1___nth_0__
                       (clojure.core/nth input__42248_nth_1__ 0)]
                      (clojure.core/let
                       [?ast-a input__42248_nth_1___nth_0__]
                       (clojure.core/let
                        [?form input__42248_nth_2__]
                        (try
                         [(clojure.core/let
                           [CATA_RESULT__15641__auto__
                            (CATA__FN__42325
                             ['meander.dev.parse.zeta/make-and
                              [?ast-a {:tag :pass}]
                              ?form])]
                           (if
                            (meander.runtime.zeta/fail?
                             CATA_RESULT__15641__auto__)
                            (throw (meander.runtime.zeta/fail))
                            (clojure.core/nth
                             CATA_RESULT__15641__auto__
                             0)))]
                         (catch
                          java.lang.Exception
                          e__16581__auto__
                          (if
                           (meander.runtime.zeta/fail?
                            e__16581__auto__)
                           (meander.runtime.zeta/fail)
                           (throw e__16581__auto__)))))))
                     (state__43962)))
                   (state__43962
                    []
                    (if
                     (clojure.core/=
                      (clojure.core/count input__42248_nth_1__)
                      2)
                     (clojure.core/let
                      [input__42248_nth_1___nth_0__
                       (clojure.core/nth input__42248_nth_1__ 0)
                       input__42248_nth_1___nth_1__
                       (clojure.core/nth input__42248_nth_1__ 1)]
                      (clojure.core/let
                       [?ast-a input__42248_nth_1___nth_0__]
                       (clojure.core/let
                        [?ast-b input__42248_nth_1___nth_1__]
                        (clojure.core/let
                         [?form input__42248_nth_2__]
                         (try
                          [{:tag :and,
                            :left ?ast-a,
                            :right ?ast-b,
                            :form ?form}]
                          (catch
                           java.lang.Exception
                           e__16581__auto__
                           (if
                            (meander.runtime.zeta/fail?
                             e__16581__auto__)
                            (meander.runtime.zeta/fail)
                            (throw e__16581__auto__))))))))
                     (state__43963)))
                   (state__43963
                    []
                    (clojure.core/loop
                     [search_space__43964
                      (meander.runtime.zeta/epsilon-partitions
                       2
                       input__42248_nth_1__)]
                     (if
                      (clojure.core/seq search_space__43964)
                      (clojure.core/let
                       [input__42248_nth_1___parts__
                        (clojure.core/first search_space__43964)
                        result__43965
                        (clojure.core/let
                         [input__42248_nth_1___l__
                          (clojure.core/nth
                           input__42248_nth_1___parts__
                           0)
                          input__42248_nth_1___r__
                          (clojure.core/nth
                           input__42248_nth_1___parts__
                           1)]
                         (clojure.core/letfn
                          [(state__43967
                            []
                            (clojure.core/let
                             [!asts-1 []]
                             (clojure.core/let
                              [ret__14502__auto__
                               (meander.runtime.zeta/epsilon-run-star-1
                                input__42248_nth_1___l__
                                [!asts-1]
                                (clojure.core/fn
                                 [[!asts-1] input__43194]
                                 (clojure.core/let
                                  [input__43194_nth_0__
                                   (clojure.core/nth input__43194 0)]
                                  (clojure.core/let
                                   [!asts-1
                                    (clojure.core/conj
                                     !asts-1
                                     input__43194_nth_0__)]
                                   [!asts-1])))
                                (clojure.core/fn
                                 [[!asts-1]]
                                 (clojure.core/let
                                  [X__43187
                                   (clojure.core/count
                                    input__42248_nth_1___l__)]
                                  (clojure.core/let
                                   [?n X__43187]
                                   (clojure.core/let
                                    [X__43191
                                     (clojure.core/count
                                      input__42248_nth_1___r__)]
                                    (if
                                     (clojure.core/= ?n X__43191)
                                     (clojure.core/let
                                      [!asts-2 []]
                                      (clojure.core/let
                                       [ret__14502__auto__
                                        (meander.runtime.zeta/epsilon-run-star-1
                                         input__42248_nth_1___r__
                                         [!asts-2 !asts-1]
                                         (clojure.core/fn
                                          [[!asts-2 !asts-1]
                                           input__43192]
                                          (clojure.core/let
                                           [input__43192_nth_0__
                                            (clojure.core/nth
                                             input__43192
                                             0)]
                                           (clojure.core/let
                                            [!asts-2
                                             (clojure.core/conj
                                              !asts-2
                                              input__43192_nth_0__)]
                                            [!asts-2 !asts-1])))
                                         (clojure.core/fn
                                          [[!asts-2 !asts-1]]
                                          (clojure.core/let
                                           [?form input__42248_nth_2__]
                                           (try
                                            [(clojure.core/let
                                              [!asts-1__counter
                                               (meander.runtime.zeta/iterator
                                                !asts-1)
                                               !asts-2__counter
                                               (meander.runtime.zeta/iterator
                                                !asts-2)]
                                              (clojure.core/let
                                               [CATA_RESULT__15641__auto__
                                                (CATA__FN__42325
                                                 ['meander.dev.parse.zeta/make-and
                                                  [(clojure.core/let
                                                    [CATA_RESULT__15641__auto__
                                                     (CATA__FN__42325
                                                      ['meander.dev.parse.zeta/make-and
                                                       (clojure.core/into
                                                        []
                                                        (clojure.core/vec
                                                         (clojure.core/iterator-seq
                                                          !asts-1__counter)))
                                                       nil])]
                                                    (if
                                                     (meander.runtime.zeta/fail?
                                                      CATA_RESULT__15641__auto__)
                                                     (throw
                                                      (meander.runtime.zeta/fail))
                                                     (clojure.core/nth
                                                      CATA_RESULT__15641__auto__
                                                      0)))
                                                   (clojure.core/let
                                                    [CATA_RESULT__15641__auto__
                                                     (CATA__FN__42325
                                                      ['meander.dev.parse.zeta/make-and
                                                       (clojure.core/into
                                                        []
                                                        (clojure.core/vec
                                                         (clojure.core/iterator-seq
                                                          !asts-2__counter)))
                                                       nil])]
                                                    (if
                                                     (meander.runtime.zeta/fail?
                                                      CATA_RESULT__15641__auto__)
                                                     (throw
                                                      (meander.runtime.zeta/fail))
                                                     (clojure.core/nth
                                                      CATA_RESULT__15641__auto__
                                                      0)))]
                                                  ?form])]
                                               (if
                                                (meander.runtime.zeta/fail?
                                                 CATA_RESULT__15641__auto__)
                                                (throw
                                                 (meander.runtime.zeta/fail))
                                                (clojure.core/nth
                                                 CATA_RESULT__15641__auto__
                                                 0))))]
                                            (catch
                                             java.lang.Exception
                                             e__16581__auto__
                                             (if
                                              (meander.runtime.zeta/fail?
                                               e__16581__auto__)
                                              (meander.runtime.zeta/fail)
                                              (throw
                                               e__16581__auto__)))))))]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         ret__14502__auto__)
                                        (state__43968)
                                        ret__14502__auto__)))
                                     (state__43968)))))))]
                              (if
                               (meander.runtime.zeta/fail?
                                ret__14502__auto__)
                               (state__43968)
                               ret__14502__auto__))))
                           (state__43968
                            []
                            (clojure.core/let
                             [!asts-1 []]
                             (clojure.core/let
                              [ret__14502__auto__
                               (meander.runtime.zeta/epsilon-run-star-1
                                input__42248_nth_1___l__
                                [!asts-1]
                                (clojure.core/fn
                                 [[!asts-1] input__43210]
                                 (clojure.core/let
                                  [input__43210_nth_0__
                                   (clojure.core/nth input__43210 0)]
                                  (clojure.core/let
                                   [!asts-1
                                    (clojure.core/conj
                                     !asts-1
                                     input__43210_nth_0__)]
                                   [!asts-1])))
                                (clojure.core/fn
                                 [[!asts-1]]
                                 (clojure.core/let
                                  [X__43201
                                   (clojure.core/count
                                    input__42248_nth_1___l__)]
                                  (clojure.core/let
                                   [?n X__43201]
                                   (clojure.core/let
                                    [input__42248_nth_1___r___l__
                                     (clojure.core/subvec
                                      input__42248_nth_1___r__
                                      0
                                      (clojure.core/min
                                       (clojure.core/count
                                        input__42248_nth_1___r__)
                                       1))]
                                    (if
                                     (clojure.core/=
                                      (clojure.core/count
                                       input__42248_nth_1___r___l__)
                                      1)
                                     (clojure.core/let
                                      [input__42248_nth_1___r___r__
                                       (clojure.core/subvec
                                        input__42248_nth_1___r__
                                        1)]
                                      (clojure.core/let
                                       [input__42248_nth_1___r___l___nth_0__
                                        (clojure.core/nth
                                         input__42248_nth_1___r___l__
                                         0)]
                                       (clojure.core/let
                                        [?ast
                                         input__42248_nth_1___r___l___nth_0__]
                                        (clojure.core/let
                                         [X__43207
                                          (clojure.core/count
                                           input__42248_nth_1___r___r__)]
                                         (if
                                          (clojure.core/= ?n X__43207)
                                          (clojure.core/let
                                           [!asts-2 []]
                                           (clojure.core/let
                                            [ret__14502__auto__
                                             (meander.runtime.zeta/epsilon-run-star-1
                                              input__42248_nth_1___r___r__
                                              [!asts-2 !asts-1]
                                              (clojure.core/fn
                                               [[!asts-2 !asts-1]
                                                input__43208]
                                               (clojure.core/let
                                                [input__43208_nth_0__
                                                 (clojure.core/nth
                                                  input__43208
                                                  0)]
                                                (clojure.core/let
                                                 [!asts-2
                                                  (clojure.core/conj
                                                   !asts-2
                                                   input__43208_nth_0__)]
                                                 [!asts-2 !asts-1])))
                                              (clojure.core/fn
                                               [[!asts-2 !asts-1]]
                                               (clojure.core/let
                                                [?form
                                                 input__42248_nth_2__]
                                                (try
                                                 [(clojure.core/let
                                                   [!asts-1__counter
                                                    (meander.runtime.zeta/iterator
                                                     !asts-1)
                                                    !asts-2__counter
                                                    (meander.runtime.zeta/iterator
                                                     !asts-2)]
                                                   (clojure.core/let
                                                    [CATA_RESULT__15641__auto__
                                                     (CATA__FN__42325
                                                      ['meander.dev.parse.zeta/make-and
                                                       [(clojure.core/let
                                                         [CATA_RESULT__15641__auto__
                                                          (CATA__FN__42325
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
                                                           CATA_RESULT__15641__auto__)
                                                          (throw
                                                           (meander.runtime.zeta/fail))
                                                          (clojure.core/nth
                                                           CATA_RESULT__15641__auto__
                                                           0)))
                                                        (clojure.core/let
                                                         [CATA_RESULT__15641__auto__
                                                          (CATA__FN__42325
                                                           ['meander.dev.parse.zeta/make-and
                                                            (clojure.core/into
                                                             []
                                                             (clojure.core/vec
                                                              (clojure.core/iterator-seq
                                                               !asts-2__counter)))
                                                            nil])]
                                                         (if
                                                          (meander.runtime.zeta/fail?
                                                           CATA_RESULT__15641__auto__)
                                                          (throw
                                                           (meander.runtime.zeta/fail))
                                                          (clojure.core/nth
                                                           CATA_RESULT__15641__auto__
                                                           0)))]
                                                       ?form])]
                                                    (if
                                                     (meander.runtime.zeta/fail?
                                                      CATA_RESULT__15641__auto__)
                                                     (throw
                                                      (meander.runtime.zeta/fail))
                                                     (clojure.core/nth
                                                      CATA_RESULT__15641__auto__
                                                      0))))]
                                                 (catch
                                                  java.lang.Exception
                                                  e__16581__auto__
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    e__16581__auto__)
                                                   (meander.runtime.zeta/fail)
                                                   (throw
                                                    e__16581__auto__)))))))]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              ret__14502__auto__)
                                             (meander.runtime.zeta/fail)
                                             ret__14502__auto__)))
                                          (meander.runtime.zeta/fail))))))
                                     (meander.runtime.zeta/fail)))))))]
                              (if
                               (meander.runtime.zeta/fail?
                                ret__14502__auto__)
                               (meander.runtime.zeta/fail)
                               ret__14502__auto__))))]
                          (state__43967)))]
                       (if
                        (meander.runtime.zeta/fail? result__43965)
                        (recur (clojure.core/next search_space__43964))
                        result__43965))
                      (state__43784))))]
                  (state__43961))
                 (state__43784)))]
              (state__43958))
             (state__43784)))
           (state__43784))
          (state__43784)))
        (state__43784
         []
         (clojure.core/letfn
          [(def__43213
            [arg__43236 ?__42257]
            (clojure.core/letfn
             [(state__43973
               []
               (clojure.core/let
                [x__43237 "meander.zeta"]
                (if
                 (clojure.core/= ?__42257 x__43237)
                 [?__42257]
                 (state__43974))))
              (state__43974
               []
               (if
                (clojure.core/map? arg__43236)
                (clojure.core/let
                 [VAL__43238 (.valAt arg__43236 :aliases)]
                 (if
                  (clojure.core/map? VAL__43238)
                  (clojure.core/let
                   [X__43240 (clojure.core/set VAL__43238)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__43240))
                    (clojure.core/loop
                     [search_space__43975 (clojure.core/seq X__43240)]
                     (if
                      (clojure.core/seq search_space__43975)
                      (clojure.core/let
                       [elem__43241
                        (clojure.core/first search_space__43975)
                        result__43976
                        (clojure.core/let
                         [elem__43241_nth_0__
                          (clojure.core/nth elem__43241 0)
                          elem__43241_nth_1__
                          (clojure.core/nth elem__43241 1)]
                         (if
                          (clojure.core/symbol? elem__43241_nth_0__)
                          (clojure.core/let
                           [X__43243
                            (clojure.core/name elem__43241_nth_0__)]
                           (if
                            (clojure.core/= ?__42257 X__43243)
                            (if
                             (clojure.core/symbol? elem__43241_nth_1__)
                             (clojure.core/let
                              [X__43245
                               (clojure.core/name elem__43241_nth_1__)]
                              (clojure.core/case
                               X__43245
                               ("meander.zeta")
                               [?__42257]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__43976)
                        (recur (clojure.core/next search_space__43975))
                        result__43976))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__43973)))]
          (if
           (clojure.core/vector? input__42248)
           (if
            (clojure.core/= (clojure.core/count input__42248) 2)
            (clojure.core/let
             [input__42248_nth_0__
              (clojure.core/nth input__42248 0)
              input__42248_nth_1__
              (clojure.core/nth input__42248 1)]
             (if
              (clojure.core/seq? input__42248_nth_0__)
              (clojure.core/let
               [input__42248_nth_0___l__
                (clojure.core/take 1 input__42248_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__42248_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__42248_nth_0___r__
                  (clojure.core/drop 1 input__42248_nth_0__)]
                 (clojure.core/let
                  [input__42248_nth_0___l___nth_0__
                   (clojure.core/nth input__42248_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__42248_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__43223
                     (clojure.core/namespace
                      input__42248_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__42257 X__43223]
                     (clojure.core/let
                      [X__43225
                       (clojure.core/name
                        input__42248_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__43225
                       ("cata")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__43213 input__42248_nth_1__ ?__42257)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__43785)
                         (clojure.core/let
                          [[?__42257] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__42248)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__42248)
                             2)
                            (clojure.core/let
                             [input__42248_nth_0__
                              (clojure.core/nth input__42248 0)
                              input__42248_nth_1__
                              (clojure.core/nth input__42248 1)]
                             (if
                              (clojure.core/seq? input__42248_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__42248_nth_0__)
                                2)
                               (clojure.core/let
                                [input__42248_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__42248_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__42248_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__42248_nth_0__]
                                  (clojure.core/let
                                   [?env input__42248_nth_1__]
                                   (try
                                    [{:tag :cata,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__15641__auto__
                                        (CATA__FN__42325
                                         [?pattern ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__15641__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__15641__auto__
                                         0))),
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__16581__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__16581__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__16581__auto__))))))))
                               (state__43785))
                              (state__43785)))
                            (state__43785))
                           (state__43785)))))
                       (state__43785)))))
                   (state__43785))))
                (state__43785)))
              (state__43785)))
            (state__43785))
           (state__43785))))
        (state__43785
         []
         (clojure.core/letfn
          [(def__43247
            [arg__43270 ?__42258]
            (clojure.core/letfn
             [(state__43978
               []
               (clojure.core/let
                [x__43271 "meander.zeta"]
                (if
                 (clojure.core/= ?__42258 x__43271)
                 [?__42258]
                 (state__43979))))
              (state__43979
               []
               (if
                (clojure.core/map? arg__43270)
                (clojure.core/let
                 [VAL__43272 (.valAt arg__43270 :aliases)]
                 (if
                  (clojure.core/map? VAL__43272)
                  (clojure.core/let
                   [X__43274 (clojure.core/set VAL__43272)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__43274))
                    (clojure.core/loop
                     [search_space__43980 (clojure.core/seq X__43274)]
                     (if
                      (clojure.core/seq search_space__43980)
                      (clojure.core/let
                       [elem__43275
                        (clojure.core/first search_space__43980)
                        result__43981
                        (clojure.core/let
                         [elem__43275_nth_0__
                          (clojure.core/nth elem__43275 0)
                          elem__43275_nth_1__
                          (clojure.core/nth elem__43275 1)]
                         (if
                          (clojure.core/symbol? elem__43275_nth_0__)
                          (clojure.core/let
                           [X__43277
                            (clojure.core/name elem__43275_nth_0__)]
                           (if
                            (clojure.core/= ?__42258 X__43277)
                            (if
                             (clojure.core/symbol? elem__43275_nth_1__)
                             (clojure.core/let
                              [X__43279
                               (clojure.core/name elem__43275_nth_1__)]
                              (clojure.core/case
                               X__43279
                               ("meander.zeta")
                               [?__42258]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__43981)
                        (recur (clojure.core/next search_space__43980))
                        result__43981))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__43978)))]
          (if
           (clojure.core/vector? input__42248)
           (if
            (clojure.core/= (clojure.core/count input__42248) 2)
            (clojure.core/let
             [input__42248_nth_0__
              (clojure.core/nth input__42248 0)
              input__42248_nth_1__
              (clojure.core/nth input__42248 1)]
             (if
              (clojure.core/seq? input__42248_nth_0__)
              (clojure.core/let
               [input__42248_nth_0___l__
                (clojure.core/take 1 input__42248_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__42248_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__42248_nth_0___r__
                  (clojure.core/drop 1 input__42248_nth_0__)]
                 (clojure.core/let
                  [input__42248_nth_0___l___nth_0__
                   (clojure.core/nth input__42248_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__42248_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__43257
                     (clojure.core/namespace
                      input__42248_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__42258 X__43257]
                     (clojure.core/let
                      [X__43259
                       (clojure.core/name
                        input__42248_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__43259
                       ("fold")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__43247 input__42248_nth_1__ ?__42258)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__43786)
                         (clojure.core/let
                          [[?__42258] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__42248)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__42248)
                             2)
                            (clojure.core/let
                             [input__42248_nth_0__
                              (clojure.core/nth input__42248 0)
                              input__42248_nth_1__
                              (clojure.core/nth input__42248 1)]
                             (if
                              (clojure.core/seq? input__42248_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 4)
                                 input__42248_nth_0__)
                                4)
                               (clojure.core/let
                                [input__42248_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__42248_nth_0__
                                  1)
                                 input__42248_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__42248_nth_0__
                                  2)
                                 input__42248_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__42248_nth_0__
                                  3)]
                                (clojure.core/let
                                 [?mutable-variable
                                  input__42248_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?initial-value
                                   input__42248_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?fold-function
                                    input__42248_nth_0___nth_3__]
                                   (clojure.core/let
                                    [?form input__42248_nth_0__]
                                    (clojure.core/let
                                     [?env input__42248_nth_1__]
                                     (try
                                      [(clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__42325
                                          ['meander.dev.parse.zeta/make-fold
                                           (clojure.core/let
                                            [CATA_RESULT__15641__auto__
                                             (CATA__FN__42325
                                              [?mutable-variable
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__15641__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__15641__auto__
                                              0)))
                                           (clojure.core/let
                                            [CATA_RESULT__15641__auto__
                                             (CATA__FN__42325
                                              [?initial-value ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__15641__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__15641__auto__
                                              0)))
                                           ?fold-function
                                           ?form])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__15641__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__15641__auto__
                                          0)))]
                                      (catch
                                       java.lang.Exception
                                       e__16581__auto__
                                       (if
                                        (meander.runtime.zeta/fail?
                                         e__16581__auto__)
                                        (meander.runtime.zeta/fail)
                                        (throw
                                         e__16581__auto__))))))))))
                               (state__43786))
                              (state__43786)))
                            (state__43786))
                           (state__43786)))))
                       (state__43786)))))
                   (state__43786))))
                (state__43786)))
              (state__43786)))
            (state__43786))
           (state__43786))))
        (state__43786
         []
         (if
          (clojure.core/vector? input__42248)
          (if
           (clojure.core/= (clojure.core/count input__42248) 5)
           (clojure.core/let
            [input__42248_nth_0__
             (clojure.core/nth input__42248 0)
             input__42248_nth_1__
             (clojure.core/nth input__42248 1)
             input__42248_nth_2__
             (clojure.core/nth input__42248 2)
             input__42248_nth_3__
             (clojure.core/nth input__42248 3)
             input__42248_nth_4__
             (clojure.core/nth input__42248 4)]
            (clojure.core/case
             input__42248_nth_0__
             (meander.dev.parse.zeta/make-fold)
             (if
              (clojure.core/map? input__42248_nth_1__)
              (clojure.core/let
               [VAL__43282 (.valAt input__42248_nth_1__ :tag)]
               (clojure.core/case
                VAL__43282
                (:mutable-variable)
                (clojure.core/let
                 [?variable-ast input__42248_nth_1__]
                 (clojure.core/let
                  [?initial-value-ast input__42248_nth_2__]
                  (clojure.core/let
                   [?fold-function input__42248_nth_3__]
                   (clojure.core/let
                    [?form input__42248_nth_4__]
                    (try
                     [{:tag :fold,
                       :variable ?variable-ast,
                       :initial-value ?initial-value-ast,
                       :fold-function
                       {:tag :host-expression, :form ?fold-function},
                       :form ?form}]
                     (catch
                      java.lang.Exception
                      e__16581__auto__
                      (if
                       (meander.runtime.zeta/fail? e__16581__auto__)
                       (meander.runtime.zeta/fail)
                       (throw e__16581__auto__))))))))
                (state__43787)))
              (state__43787))
             (state__43787)))
           (state__43787))
          (state__43787)))
        (state__43787
         []
         (clojure.core/letfn
          [(def__43284
            [arg__43307 ?__42259]
            (clojure.core/letfn
             [(state__43983
               []
               (clojure.core/let
                [x__43308 "meander.zeta"]
                (if
                 (clojure.core/= ?__42259 x__43308)
                 [?__42259]
                 (state__43984))))
              (state__43984
               []
               (if
                (clojure.core/map? arg__43307)
                (clojure.core/let
                 [VAL__43309 (.valAt arg__43307 :aliases)]
                 (if
                  (clojure.core/map? VAL__43309)
                  (clojure.core/let
                   [X__43311 (clojure.core/set VAL__43309)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__43311))
                    (clojure.core/loop
                     [search_space__43985 (clojure.core/seq X__43311)]
                     (if
                      (clojure.core/seq search_space__43985)
                      (clojure.core/let
                       [elem__43312
                        (clojure.core/first search_space__43985)
                        result__43986
                        (clojure.core/let
                         [elem__43312_nth_0__
                          (clojure.core/nth elem__43312 0)
                          elem__43312_nth_1__
                          (clojure.core/nth elem__43312 1)]
                         (if
                          (clojure.core/symbol? elem__43312_nth_0__)
                          (clojure.core/let
                           [X__43314
                            (clojure.core/name elem__43312_nth_0__)]
                           (if
                            (clojure.core/= ?__42259 X__43314)
                            (if
                             (clojure.core/symbol? elem__43312_nth_1__)
                             (clojure.core/let
                              [X__43316
                               (clojure.core/name elem__43312_nth_1__)]
                              (clojure.core/case
                               X__43316
                               ("meander.zeta")
                               [?__42259]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__43986)
                        (recur (clojure.core/next search_space__43985))
                        result__43986))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__43983)))]
          (if
           (clojure.core/vector? input__42248)
           (if
            (clojure.core/= (clojure.core/count input__42248) 2)
            (clojure.core/let
             [input__42248_nth_0__
              (clojure.core/nth input__42248 0)
              input__42248_nth_1__
              (clojure.core/nth input__42248 1)]
             (if
              (clojure.core/seq? input__42248_nth_0__)
              (clojure.core/let
               [input__42248_nth_0___l__
                (clojure.core/take 1 input__42248_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__42248_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__42248_nth_0___r__
                  (clojure.core/drop 1 input__42248_nth_0__)]
                 (clojure.core/let
                  [input__42248_nth_0___l___nth_0__
                   (clojure.core/nth input__42248_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__42248_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__43294
                     (clojure.core/namespace
                      input__42248_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__42259 X__43294]
                     (clojure.core/let
                      [X__43296
                       (clojure.core/name
                        input__42248_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__43296
                       ("let")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__43284 input__42248_nth_1__ ?__42259)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__43788)
                         (clojure.core/let
                          [[?__42259] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__42248)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__42248)
                             2)
                            (clojure.core/let
                             [input__42248_nth_0__
                              (clojure.core/nth input__42248 0)
                              input__42248_nth_1__
                              (clojure.core/nth input__42248 1)]
                             (if
                              (clojure.core/seq? input__42248_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__42248_nth_0__)
                                3)
                               (clojure.core/let
                                [input__42248_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__42248_nth_0__
                                  1)
                                 input__42248_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__42248_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?pattern
                                  input__42248_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?expression
                                   input__42248_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__42248_nth_0__]
                                   (clojure.core/let
                                    [?env input__42248_nth_1__]
                                    (try
                                     [{:tag :let,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__42325
                                          [?pattern ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__15641__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__15641__auto__
                                          0))),
                                       :expression
                                       {:tag :host-expression,
                                        :form ?expression},
                                       :next {:tag :pass}}]
                                     (catch
                                      java.lang.Exception
                                      e__16581__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__16581__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__16581__auto__)))))))))
                               (state__43788))
                              (state__43788)))
                            (state__43788))
                           (state__43788)))))
                       (state__43788)))))
                   (state__43788))))
                (state__43788)))
              (state__43788)))
            (state__43788))
           (state__43788))))
        (state__43788
         []
         (clojure.core/letfn
          [(def__43318
            [arg__43341 ?__42260]
            (clojure.core/letfn
             [(state__43988
               []
               (clojure.core/let
                [x__43342 "meander.zeta"]
                (if
                 (clojure.core/= ?__42260 x__43342)
                 [?__42260]
                 (state__43989))))
              (state__43989
               []
               (if
                (clojure.core/map? arg__43341)
                (clojure.core/let
                 [VAL__43343 (.valAt arg__43341 :aliases)]
                 (if
                  (clojure.core/map? VAL__43343)
                  (clojure.core/let
                   [X__43345 (clojure.core/set VAL__43343)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__43345))
                    (clojure.core/loop
                     [search_space__43990 (clojure.core/seq X__43345)]
                     (if
                      (clojure.core/seq search_space__43990)
                      (clojure.core/let
                       [elem__43346
                        (clojure.core/first search_space__43990)
                        result__43991
                        (clojure.core/let
                         [elem__43346_nth_0__
                          (clojure.core/nth elem__43346 0)
                          elem__43346_nth_1__
                          (clojure.core/nth elem__43346 1)]
                         (if
                          (clojure.core/symbol? elem__43346_nth_0__)
                          (clojure.core/let
                           [X__43348
                            (clojure.core/name elem__43346_nth_0__)]
                           (if
                            (clojure.core/= ?__42260 X__43348)
                            (if
                             (clojure.core/symbol? elem__43346_nth_1__)
                             (clojure.core/let
                              [X__43350
                               (clojure.core/name elem__43346_nth_1__)]
                              (clojure.core/case
                               X__43350
                               ("meander.zeta")
                               [?__42260]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__43991)
                        (recur (clojure.core/next search_space__43990))
                        result__43991))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__43988)))]
          (if
           (clojure.core/vector? input__42248)
           (if
            (clojure.core/= (clojure.core/count input__42248) 2)
            (clojure.core/let
             [input__42248_nth_0__
              (clojure.core/nth input__42248 0)
              input__42248_nth_1__
              (clojure.core/nth input__42248 1)]
             (if
              (clojure.core/seq? input__42248_nth_0__)
              (clojure.core/let
               [input__42248_nth_0___l__
                (clojure.core/take 1 input__42248_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__42248_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__42248_nth_0___r__
                  (clojure.core/drop 1 input__42248_nth_0__)]
                 (clojure.core/let
                  [input__42248_nth_0___l___nth_0__
                   (clojure.core/nth input__42248_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__42248_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__43328
                     (clojure.core/namespace
                      input__42248_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__42260 X__43328]
                     (clojure.core/let
                      [X__43330
                       (clojure.core/name
                        input__42248_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__43330
                       ("let")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__43318 input__42248_nth_1__ ?__42260)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__43789)
                         (clojure.core/let
                          [[?__42260] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__42248)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__42248)
                             2)
                            (clojure.core/let
                             [input__42248_nth_0__
                              (clojure.core/nth input__42248 0)
                              input__42248_nth_1__
                              (clojure.core/nth input__42248 1)]
                             (if
                              (clojure.core/seq? input__42248_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 4)
                                 input__42248_nth_0__)
                                4)
                               (clojure.core/let
                                [input__42248_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__42248_nth_0__
                                  1)
                                 input__42248_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__42248_nth_0__
                                  2)
                                 input__42248_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__42248_nth_0__
                                  3)]
                                (clojure.core/let
                                 [?pattern
                                  input__42248_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?expression
                                   input__42248_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?next input__42248_nth_0___nth_3__]
                                   (clojure.core/let
                                    [?form input__42248_nth_0__]
                                    (clojure.core/let
                                     [?env input__42248_nth_1__]
                                     (try
                                      [{:tag :let,
                                        :pattern
                                        (clojure.core/let
                                         [CATA_RESULT__15641__auto__
                                          (CATA__FN__42325
                                           [?pattern ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__15641__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__15641__auto__
                                           0))),
                                        :expression
                                        {:tag :host-expression,
                                         :form ?expression},
                                        :next
                                        (clojure.core/let
                                         [CATA_RESULT__15641__auto__
                                          (CATA__FN__42325
                                           [?next ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__15641__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__15641__auto__
                                           0)))}]
                                      (catch
                                       java.lang.Exception
                                       e__16581__auto__
                                       (if
                                        (meander.runtime.zeta/fail?
                                         e__16581__auto__)
                                        (meander.runtime.zeta/fail)
                                        (throw
                                         e__16581__auto__))))))))))
                               (state__43789))
                              (state__43789)))
                            (state__43789))
                           (state__43789)))))
                       (state__43789)))))
                   (state__43789))))
                (state__43789)))
              (state__43789)))
            (state__43789))
           (state__43789))))
        (state__43789
         []
         (clojure.core/letfn
          [(def__43352
            [arg__43375 ?__42261]
            (clojure.core/letfn
             [(state__43993
               []
               (clojure.core/let
                [x__43376 "meander.zeta"]
                (if
                 (clojure.core/= ?__42261 x__43376)
                 [?__42261]
                 (state__43994))))
              (state__43994
               []
               (if
                (clojure.core/map? arg__43375)
                (clojure.core/let
                 [VAL__43377 (.valAt arg__43375 :aliases)]
                 (if
                  (clojure.core/map? VAL__43377)
                  (clojure.core/let
                   [X__43379 (clojure.core/set VAL__43377)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__43379))
                    (clojure.core/loop
                     [search_space__43995 (clojure.core/seq X__43379)]
                     (if
                      (clojure.core/seq search_space__43995)
                      (clojure.core/let
                       [elem__43380
                        (clojure.core/first search_space__43995)
                        result__43996
                        (clojure.core/let
                         [elem__43380_nth_0__
                          (clojure.core/nth elem__43380 0)
                          elem__43380_nth_1__
                          (clojure.core/nth elem__43380 1)]
                         (if
                          (clojure.core/symbol? elem__43380_nth_0__)
                          (clojure.core/let
                           [X__43382
                            (clojure.core/name elem__43380_nth_0__)]
                           (if
                            (clojure.core/= ?__42261 X__43382)
                            (if
                             (clojure.core/symbol? elem__43380_nth_1__)
                             (clojure.core/let
                              [X__43384
                               (clojure.core/name elem__43380_nth_1__)]
                              (clojure.core/case
                               X__43384
                               ("meander.zeta")
                               [?__42261]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__43996)
                        (recur (clojure.core/next search_space__43995))
                        result__43996))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__43993)))]
          (if
           (clojure.core/vector? input__42248)
           (if
            (clojure.core/= (clojure.core/count input__42248) 2)
            (clojure.core/let
             [input__42248_nth_0__
              (clojure.core/nth input__42248 0)
              input__42248_nth_1__
              (clojure.core/nth input__42248 1)]
             (if
              (clojure.core/seq? input__42248_nth_0__)
              (clojure.core/let
               [input__42248_nth_0___l__
                (clojure.core/take 1 input__42248_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__42248_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__42248_nth_0___r__
                  (clojure.core/drop 1 input__42248_nth_0__)]
                 (clojure.core/let
                  [input__42248_nth_0___l___nth_0__
                   (clojure.core/nth input__42248_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__42248_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__43362
                     (clojure.core/namespace
                      input__42248_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__42261 X__43362]
                     (clojure.core/let
                      [X__43364
                       (clojure.core/name
                        input__42248_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__43364
                       ("not")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__43352 input__42248_nth_1__ ?__42261)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__43790)
                         (clojure.core/let
                          [[?__42261] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__42248)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__42248)
                             2)
                            (clojure.core/let
                             [input__42248_nth_0__
                              (clojure.core/nth input__42248 0)
                              input__42248_nth_1__
                              (clojure.core/nth input__42248 1)]
                             (if
                              (clojure.core/seq? input__42248_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__42248_nth_0__)
                                2)
                               (clojure.core/let
                                [input__42248_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__42248_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__42248_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__42248_nth_0__]
                                  (clojure.core/let
                                   [?env input__42248_nth_1__]
                                   (try
                                    [{:tag :not,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__15641__auto__
                                        (CATA__FN__42325
                                         [?pattern ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__15641__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__15641__auto__
                                         0))),
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__16581__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__16581__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__16581__auto__))))))))
                               (state__43790))
                              (state__43790)))
                            (state__43790))
                           (state__43790)))))
                       (state__43790)))))
                   (state__43790))))
                (state__43790)))
              (state__43790)))
            (state__43790))
           (state__43790))))
        (state__43790
         []
         (clojure.core/letfn
          [(def__43386
            [arg__43411 ?__42262]
            (clojure.core/letfn
             [(state__43998
               []
               (clojure.core/let
                [x__43412 "meander.zeta"]
                (if
                 (clojure.core/= ?__42262 x__43412)
                 [?__42262]
                 (state__43999))))
              (state__43999
               []
               (if
                (clojure.core/map? arg__43411)
                (clojure.core/let
                 [VAL__43413 (.valAt arg__43411 :aliases)]
                 (if
                  (clojure.core/map? VAL__43413)
                  (clojure.core/let
                   [X__43415 (clojure.core/set VAL__43413)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__43415))
                    (clojure.core/loop
                     [search_space__44000 (clojure.core/seq X__43415)]
                     (if
                      (clojure.core/seq search_space__44000)
                      (clojure.core/let
                       [elem__43416
                        (clojure.core/first search_space__44000)
                        result__44001
                        (clojure.core/let
                         [elem__43416_nth_0__
                          (clojure.core/nth elem__43416 0)
                          elem__43416_nth_1__
                          (clojure.core/nth elem__43416 1)]
                         (if
                          (clojure.core/symbol? elem__43416_nth_0__)
                          (clojure.core/let
                           [X__43418
                            (clojure.core/name elem__43416_nth_0__)]
                           (if
                            (clojure.core/= ?__42262 X__43418)
                            (if
                             (clojure.core/symbol? elem__43416_nth_1__)
                             (clojure.core/let
                              [X__43420
                               (clojure.core/name elem__43416_nth_1__)]
                              (clojure.core/case
                               X__43420
                               ("meander.zeta")
                               [?__42262]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__44001)
                        (recur (clojure.core/next search_space__44000))
                        result__44001))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__43998)))]
          (if
           (clojure.core/vector? input__42248)
           (if
            (clojure.core/= (clojure.core/count input__42248) 2)
            (clojure.core/let
             [input__42248_nth_0__
              (clojure.core/nth input__42248 0)
              input__42248_nth_1__
              (clojure.core/nth input__42248 1)]
             (if
              (clojure.core/seq? input__42248_nth_0__)
              (clojure.core/let
               [input__42248_nth_0___l__
                (clojure.core/take 1 input__42248_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__42248_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__42248_nth_0___r__
                  (clojure.core/drop 1 input__42248_nth_0__)]
                 (clojure.core/let
                  [input__42248_nth_0___l___nth_0__
                   (clojure.core/nth input__42248_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__42248_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__43398
                     (clojure.core/namespace
                      input__42248_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__42262 X__43398]
                     (clojure.core/let
                      [X__43400
                       (clojure.core/name
                        input__42248_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__43400
                       ("or")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__43386 input__42248_nth_1__ ?__42262)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__43791)
                         (clojure.core/let
                          [[?__42262] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__42248)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__42248)
                             2)
                            (clojure.core/let
                             [input__42248_nth_0__
                              (clojure.core/nth input__42248 0)
                              input__42248_nth_1__
                              (clojure.core/nth input__42248 1)]
                             (if
                              (clojure.core/seq? input__42248_nth_0__)
                              (clojure.core/let
                               [input__42248_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__42248_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__42248_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__42248_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__42248_nth_0__)]
                                 (clojure.core/let
                                  [!forms
                                   (clojure.core/vec
                                    input__42248_nth_0___r__)]
                                  (clojure.core/let
                                   [?form input__42248_nth_0__]
                                   (clojure.core/let
                                    [?env input__42248_nth_1__]
                                    (try
                                     [(clojure.core/let
                                       [!forms__counter
                                        (meander.runtime.zeta/iterator
                                         !forms)]
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__42325
                                          ['meander.dev.parse.zeta/make-or
                                           (clojure.core/into
                                            []
                                            (clojure.core/loop
                                             [return__42328
                                              (clojure.core/transient
                                               [])]
                                             (if
                                              (clojure.core/and
                                               (.hasNext
                                                !forms__counter))
                                              (recur
                                               (clojure.core/conj!
                                                return__42328
                                                (clojure.core/let
                                                 [CATA_RESULT__15641__auto__
                                                  (CATA__FN__42325
                                                   [(if
                                                     (.hasNext
                                                      !forms__counter)
                                                     (.next
                                                      !forms__counter))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__15641__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__15641__auto__
                                                   0)))))
                                              (clojure.core/persistent!
                                               return__42328))))
                                           ?form])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__15641__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__15641__auto__
                                          0))))]
                                     (catch
                                      java.lang.Exception
                                      e__16581__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__16581__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__16581__auto__))))))))
                                (state__43791)))
                              (state__43791)))
                            (state__43791))
                           (state__43791)))))
                       (state__43791)))))
                   (state__43791))))
                (state__43791)))
              (state__43791)))
            (state__43791))
           (state__43791))))
        (state__43791
         []
         (if
          (clojure.core/vector? input__42248)
          (if
           (clojure.core/= (clojure.core/count input__42248) 3)
           (clojure.core/let
            [input__42248_nth_0__
             (clojure.core/nth input__42248 0)
             input__42248_nth_1__
             (clojure.core/nth input__42248 1)
             input__42248_nth_2__
             (clojure.core/nth input__42248 2)]
            (clojure.core/case
             input__42248_nth_0__
             (meander.dev.parse.zeta/make-or)
             (clojure.core/letfn
              [(state__44003
                []
                (if
                 (clojure.core/vector? input__42248_nth_1__)
                 (clojure.core/case
                  input__42248_nth_1__
                  ([])
                  (clojure.core/let
                   [?form input__42248_nth_2__]
                   (try
                    [{:tag :error,
                      :message
                      "meander.zeta/or requires 1 or more arguments",
                      :form ?form}]
                    (catch
                     java.lang.Exception
                     e__16581__auto__
                     (if
                      (meander.runtime.zeta/fail? e__16581__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__16581__auto__)))))
                  (state__44004))
                 (state__44004)))
               (state__44004
                []
                (clojure.core/case
                 input__42248_nth_2__
                 (nil)
                 (if
                  (clojure.core/vector? input__42248_nth_1__)
                  (if
                   (clojure.core/=
                    (clojure.core/count input__42248_nth_1__)
                    1)
                   (clojure.core/let
                    [input__42248_nth_1___nth_0__
                     (clojure.core/nth input__42248_nth_1__ 0)]
                    (clojure.core/let
                     [?ast-a input__42248_nth_1___nth_0__]
                     (try
                      [?ast-a]
                      (catch
                       java.lang.Exception
                       e__16581__auto__
                       (if
                        (meander.runtime.zeta/fail? e__16581__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__16581__auto__))))))
                   (state__44005))
                  (state__44005))
                 (state__44005)))
               (state__44005
                []
                (if
                 (clojure.core/vector? input__42248_nth_1__)
                 (clojure.core/letfn
                  [(state__44006
                    []
                    (if
                     (clojure.core/=
                      (clojure.core/count input__42248_nth_1__)
                      1)
                     (clojure.core/let
                      [input__42248_nth_1___nth_0__
                       (clojure.core/nth input__42248_nth_1__ 0)]
                      (clojure.core/let
                       [?ast-a input__42248_nth_1___nth_0__]
                       (clojure.core/let
                        [?form input__42248_nth_2__]
                        (try
                         [(clojure.core/let
                           [CATA_RESULT__15641__auto__
                            (CATA__FN__42325
                             ['meander.dev.parse.zeta/make-or
                              [?ast-a {:tag :pass}]
                              ?form])]
                           (if
                            (meander.runtime.zeta/fail?
                             CATA_RESULT__15641__auto__)
                            (throw (meander.runtime.zeta/fail))
                            (clojure.core/nth
                             CATA_RESULT__15641__auto__
                             0)))]
                         (catch
                          java.lang.Exception
                          e__16581__auto__
                          (if
                           (meander.runtime.zeta/fail?
                            e__16581__auto__)
                           (meander.runtime.zeta/fail)
                           (throw e__16581__auto__)))))))
                     (state__44007)))
                   (state__44007
                    []
                    (if
                     (clojure.core/=
                      (clojure.core/count input__42248_nth_1__)
                      2)
                     (clojure.core/let
                      [input__42248_nth_1___nth_0__
                       (clojure.core/nth input__42248_nth_1__ 0)
                       input__42248_nth_1___nth_1__
                       (clojure.core/nth input__42248_nth_1__ 1)]
                      (clojure.core/let
                       [?ast-a input__42248_nth_1___nth_0__]
                       (clojure.core/let
                        [?ast-b input__42248_nth_1___nth_1__]
                        (clojure.core/let
                         [?form input__42248_nth_2__]
                         (try
                          [{:tag :or,
                            :left ?ast-a,
                            :right ?ast-b,
                            :form ?form}]
                          (catch
                           java.lang.Exception
                           e__16581__auto__
                           (if
                            (meander.runtime.zeta/fail?
                             e__16581__auto__)
                            (meander.runtime.zeta/fail)
                            (throw e__16581__auto__))))))))
                     (state__44008)))
                   (state__44008
                    []
                    (clojure.core/loop
                     [search_space__44009
                      (meander.runtime.zeta/epsilon-partitions
                       2
                       input__42248_nth_1__)]
                     (if
                      (clojure.core/seq search_space__44009)
                      (clojure.core/let
                       [input__42248_nth_1___parts__
                        (clojure.core/first search_space__44009)
                        result__44010
                        (clojure.core/let
                         [input__42248_nth_1___l__
                          (clojure.core/nth
                           input__42248_nth_1___parts__
                           0)
                          input__42248_nth_1___r__
                          (clojure.core/nth
                           input__42248_nth_1___parts__
                           1)]
                         (clojure.core/letfn
                          [(state__44012
                            []
                            (clojure.core/let
                             [!asts-1 []]
                             (clojure.core/let
                              [ret__14502__auto__
                               (meander.runtime.zeta/epsilon-run-star-1
                                input__42248_nth_1___l__
                                [!asts-1]
                                (clojure.core/fn
                                 [[!asts-1] input__43447]
                                 (clojure.core/let
                                  [input__43447_nth_0__
                                   (clojure.core/nth input__43447 0)]
                                  (clojure.core/let
                                   [!asts-1
                                    (clojure.core/conj
                                     !asts-1
                                     input__43447_nth_0__)]
                                   [!asts-1])))
                                (clojure.core/fn
                                 [[!asts-1]]
                                 (clojure.core/let
                                  [X__43440
                                   (clojure.core/count
                                    input__42248_nth_1___l__)]
                                  (clojure.core/let
                                   [?n X__43440]
                                   (clojure.core/let
                                    [X__43444
                                     (clojure.core/count
                                      input__42248_nth_1___r__)]
                                    (if
                                     (clojure.core/= ?n X__43444)
                                     (clojure.core/let
                                      [!asts-2 []]
                                      (clojure.core/let
                                       [ret__14502__auto__
                                        (meander.runtime.zeta/epsilon-run-star-1
                                         input__42248_nth_1___r__
                                         [!asts-2 !asts-1]
                                         (clojure.core/fn
                                          [[!asts-2 !asts-1]
                                           input__43445]
                                          (clojure.core/let
                                           [input__43445_nth_0__
                                            (clojure.core/nth
                                             input__43445
                                             0)]
                                           (clojure.core/let
                                            [!asts-2
                                             (clojure.core/conj
                                              !asts-2
                                              input__43445_nth_0__)]
                                            [!asts-2 !asts-1])))
                                         (clojure.core/fn
                                          [[!asts-2 !asts-1]]
                                          (clojure.core/let
                                           [?form input__42248_nth_2__]
                                           (try
                                            [(clojure.core/let
                                              [!asts-1__counter
                                               (meander.runtime.zeta/iterator
                                                !asts-1)
                                               !asts-2__counter
                                               (meander.runtime.zeta/iterator
                                                !asts-2)]
                                              (clojure.core/let
                                               [CATA_RESULT__15641__auto__
                                                (CATA__FN__42325
                                                 ['meander.dev.parse.zeta/make-or
                                                  [(clojure.core/let
                                                    [CATA_RESULT__15641__auto__
                                                     (CATA__FN__42325
                                                      ['meander.dev.parse.zeta/make-or
                                                       (clojure.core/into
                                                        []
                                                        (clojure.core/vec
                                                         (clojure.core/iterator-seq
                                                          !asts-1__counter)))
                                                       nil])]
                                                    (if
                                                     (meander.runtime.zeta/fail?
                                                      CATA_RESULT__15641__auto__)
                                                     (throw
                                                      (meander.runtime.zeta/fail))
                                                     (clojure.core/nth
                                                      CATA_RESULT__15641__auto__
                                                      0)))
                                                   (clojure.core/let
                                                    [CATA_RESULT__15641__auto__
                                                     (CATA__FN__42325
                                                      ['meander.dev.parse.zeta/make-or
                                                       (clojure.core/into
                                                        []
                                                        (clojure.core/vec
                                                         (clojure.core/iterator-seq
                                                          !asts-2__counter)))
                                                       nil])]
                                                    (if
                                                     (meander.runtime.zeta/fail?
                                                      CATA_RESULT__15641__auto__)
                                                     (throw
                                                      (meander.runtime.zeta/fail))
                                                     (clojure.core/nth
                                                      CATA_RESULT__15641__auto__
                                                      0)))]
                                                  ?form])]
                                               (if
                                                (meander.runtime.zeta/fail?
                                                 CATA_RESULT__15641__auto__)
                                                (throw
                                                 (meander.runtime.zeta/fail))
                                                (clojure.core/nth
                                                 CATA_RESULT__15641__auto__
                                                 0))))]
                                            (catch
                                             java.lang.Exception
                                             e__16581__auto__
                                             (if
                                              (meander.runtime.zeta/fail?
                                               e__16581__auto__)
                                              (meander.runtime.zeta/fail)
                                              (throw
                                               e__16581__auto__)))))))]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         ret__14502__auto__)
                                        (state__44013)
                                        ret__14502__auto__)))
                                     (state__44013)))))))]
                              (if
                               (meander.runtime.zeta/fail?
                                ret__14502__auto__)
                               (state__44013)
                               ret__14502__auto__))))
                           (state__44013
                            []
                            (clojure.core/let
                             [!asts-1 []]
                             (clojure.core/let
                              [ret__14502__auto__
                               (meander.runtime.zeta/epsilon-run-star-1
                                input__42248_nth_1___l__
                                [!asts-1]
                                (clojure.core/fn
                                 [[!asts-1] input__43463]
                                 (clojure.core/let
                                  [input__43463_nth_0__
                                   (clojure.core/nth input__43463 0)]
                                  (clojure.core/let
                                   [!asts-1
                                    (clojure.core/conj
                                     !asts-1
                                     input__43463_nth_0__)]
                                   [!asts-1])))
                                (clojure.core/fn
                                 [[!asts-1]]
                                 (clojure.core/let
                                  [X__43454
                                   (clojure.core/count
                                    input__42248_nth_1___l__)]
                                  (clojure.core/let
                                   [?n X__43454]
                                   (clojure.core/let
                                    [input__42248_nth_1___r___l__
                                     (clojure.core/subvec
                                      input__42248_nth_1___r__
                                      0
                                      (clojure.core/min
                                       (clojure.core/count
                                        input__42248_nth_1___r__)
                                       1))]
                                    (if
                                     (clojure.core/=
                                      (clojure.core/count
                                       input__42248_nth_1___r___l__)
                                      1)
                                     (clojure.core/let
                                      [input__42248_nth_1___r___r__
                                       (clojure.core/subvec
                                        input__42248_nth_1___r__
                                        1)]
                                      (clojure.core/let
                                       [input__42248_nth_1___r___l___nth_0__
                                        (clojure.core/nth
                                         input__42248_nth_1___r___l__
                                         0)]
                                       (clojure.core/let
                                        [?ast
                                         input__42248_nth_1___r___l___nth_0__]
                                        (clojure.core/let
                                         [X__43460
                                          (clojure.core/count
                                           input__42248_nth_1___r___r__)]
                                         (if
                                          (clojure.core/= ?n X__43460)
                                          (clojure.core/let
                                           [!asts-2 []]
                                           (clojure.core/let
                                            [ret__14502__auto__
                                             (meander.runtime.zeta/epsilon-run-star-1
                                              input__42248_nth_1___r___r__
                                              [!asts-2 !asts-1]
                                              (clojure.core/fn
                                               [[!asts-2 !asts-1]
                                                input__43461]
                                               (clojure.core/let
                                                [input__43461_nth_0__
                                                 (clojure.core/nth
                                                  input__43461
                                                  0)]
                                                (clojure.core/let
                                                 [!asts-2
                                                  (clojure.core/conj
                                                   !asts-2
                                                   input__43461_nth_0__)]
                                                 [!asts-2 !asts-1])))
                                              (clojure.core/fn
                                               [[!asts-2 !asts-1]]
                                               (clojure.core/let
                                                [?form
                                                 input__42248_nth_2__]
                                                (try
                                                 [(clojure.core/let
                                                   [!asts-1__counter
                                                    (meander.runtime.zeta/iterator
                                                     !asts-1)
                                                    !asts-2__counter
                                                    (meander.runtime.zeta/iterator
                                                     !asts-2)]
                                                   (clojure.core/let
                                                    [CATA_RESULT__15641__auto__
                                                     (CATA__FN__42325
                                                      ['meander.dev.parse.zeta/make-or
                                                       [(clojure.core/let
                                                         [CATA_RESULT__15641__auto__
                                                          (CATA__FN__42325
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
                                                           CATA_RESULT__15641__auto__)
                                                          (throw
                                                           (meander.runtime.zeta/fail))
                                                          (clojure.core/nth
                                                           CATA_RESULT__15641__auto__
                                                           0)))
                                                        (clojure.core/let
                                                         [CATA_RESULT__15641__auto__
                                                          (CATA__FN__42325
                                                           ['meander.dev.parse.zeta/make-or
                                                            (clojure.core/into
                                                             []
                                                             (clojure.core/vec
                                                              (clojure.core/iterator-seq
                                                               !asts-2__counter)))
                                                            nil])]
                                                         (if
                                                          (meander.runtime.zeta/fail?
                                                           CATA_RESULT__15641__auto__)
                                                          (throw
                                                           (meander.runtime.zeta/fail))
                                                          (clojure.core/nth
                                                           CATA_RESULT__15641__auto__
                                                           0)))]
                                                       ?form])]
                                                    (if
                                                     (meander.runtime.zeta/fail?
                                                      CATA_RESULT__15641__auto__)
                                                     (throw
                                                      (meander.runtime.zeta/fail))
                                                     (clojure.core/nth
                                                      CATA_RESULT__15641__auto__
                                                      0))))]
                                                 (catch
                                                  java.lang.Exception
                                                  e__16581__auto__
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    e__16581__auto__)
                                                   (meander.runtime.zeta/fail)
                                                   (throw
                                                    e__16581__auto__)))))))]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              ret__14502__auto__)
                                             (meander.runtime.zeta/fail)
                                             ret__14502__auto__)))
                                          (meander.runtime.zeta/fail))))))
                                     (meander.runtime.zeta/fail)))))))]
                              (if
                               (meander.runtime.zeta/fail?
                                ret__14502__auto__)
                               (meander.runtime.zeta/fail)
                               ret__14502__auto__))))]
                          (state__44012)))]
                       (if
                        (meander.runtime.zeta/fail? result__44010)
                        (recur (clojure.core/next search_space__44009))
                        result__44010))
                      (state__43792))))]
                  (state__44006))
                 (state__43792)))]
              (state__44003))
             (state__43792)))
           (state__43792))
          (state__43792)))
        (state__43792
         []
         (clojure.core/letfn
          [(def__43466
            [arg__43489 ?__42263]
            (clojure.core/letfn
             [(state__44018
               []
               (clojure.core/let
                [x__43490 "meander.zeta"]
                (if
                 (clojure.core/= ?__42263 x__43490)
                 [?__42263]
                 (state__44019))))
              (state__44019
               []
               (if
                (clojure.core/map? arg__43489)
                (clojure.core/let
                 [VAL__43491 (.valAt arg__43489 :aliases)]
                 (if
                  (clojure.core/map? VAL__43491)
                  (clojure.core/let
                   [X__43493 (clojure.core/set VAL__43491)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__43493))
                    (clojure.core/loop
                     [search_space__44020 (clojure.core/seq X__43493)]
                     (if
                      (clojure.core/seq search_space__44020)
                      (clojure.core/let
                       [elem__43494
                        (clojure.core/first search_space__44020)
                        result__44021
                        (clojure.core/let
                         [elem__43494_nth_0__
                          (clojure.core/nth elem__43494 0)
                          elem__43494_nth_1__
                          (clojure.core/nth elem__43494 1)]
                         (if
                          (clojure.core/symbol? elem__43494_nth_0__)
                          (clojure.core/let
                           [X__43496
                            (clojure.core/name elem__43494_nth_0__)]
                           (if
                            (clojure.core/= ?__42263 X__43496)
                            (if
                             (clojure.core/symbol? elem__43494_nth_1__)
                             (clojure.core/let
                              [X__43498
                               (clojure.core/name elem__43494_nth_1__)]
                              (clojure.core/case
                               X__43498
                               ("meander.zeta")
                               [?__42263]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__44021)
                        (recur (clojure.core/next search_space__44020))
                        result__44021))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__44018)))]
          (if
           (clojure.core/vector? input__42248)
           (if
            (clojure.core/= (clojure.core/count input__42248) 2)
            (clojure.core/let
             [input__42248_nth_0__
              (clojure.core/nth input__42248 0)
              input__42248_nth_1__
              (clojure.core/nth input__42248 1)]
             (if
              (clojure.core/seq? input__42248_nth_0__)
              (clojure.core/let
               [input__42248_nth_0___l__
                (clojure.core/take 1 input__42248_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__42248_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__42248_nth_0___r__
                  (clojure.core/drop 1 input__42248_nth_0__)]
                 (clojure.core/let
                  [input__42248_nth_0___l___nth_0__
                   (clojure.core/nth input__42248_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__42248_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__43476
                     (clojure.core/namespace
                      input__42248_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__42263 X__43476]
                     (clojure.core/let
                      [X__43478
                       (clojure.core/name
                        input__42248_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__43478
                       ("pred")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__43466 input__42248_nth_1__ ?__42263)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__43793)
                         (clojure.core/let
                          [[?__42263] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__42248)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__42248)
                             2)
                            (clojure.core/let
                             [input__42248_nth_0__
                              (clojure.core/nth input__42248 0)
                              input__42248_nth_1__
                              (clojure.core/nth input__42248 1)]
                             (if
                              (clojure.core/seq? input__42248_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__42248_nth_0__)
                                2)
                               (clojure.core/let
                                [input__42248_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__42248_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?expression
                                  input__42248_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__42248_nth_0__]
                                  (clojure.core/let
                                   [?env input__42248_nth_1__]
                                   (try
                                    [{:tag :pred,
                                      :expression
                                      {:tag :host-expression,
                                       :form ?expression},
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__16581__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__16581__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__16581__auto__))))))))
                               (state__43793))
                              (state__43793)))
                            (state__43793))
                           (state__43793)))))
                       (state__43793)))))
                   (state__43793))))
                (state__43793)))
              (state__43793)))
            (state__43793))
           (state__43793))))
        (state__43793
         []
         (clojure.core/letfn
          [(def__43500
            [arg__43523 ?__42264]
            (clojure.core/letfn
             [(state__44023
               []
               (clojure.core/let
                [x__43524 "meander.zeta"]
                (if
                 (clojure.core/= ?__42264 x__43524)
                 [?__42264]
                 (state__44024))))
              (state__44024
               []
               (if
                (clojure.core/map? arg__43523)
                (clojure.core/let
                 [VAL__43525 (.valAt arg__43523 :aliases)]
                 (if
                  (clojure.core/map? VAL__43525)
                  (clojure.core/let
                   [X__43527 (clojure.core/set VAL__43525)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__43527))
                    (clojure.core/loop
                     [search_space__44025 (clojure.core/seq X__43527)]
                     (if
                      (clojure.core/seq search_space__44025)
                      (clojure.core/let
                       [elem__43528
                        (clojure.core/first search_space__44025)
                        result__44026
                        (clojure.core/let
                         [elem__43528_nth_0__
                          (clojure.core/nth elem__43528 0)
                          elem__43528_nth_1__
                          (clojure.core/nth elem__43528 1)]
                         (if
                          (clojure.core/symbol? elem__43528_nth_0__)
                          (clojure.core/let
                           [X__43530
                            (clojure.core/name elem__43528_nth_0__)]
                           (if
                            (clojure.core/= ?__42264 X__43530)
                            (if
                             (clojure.core/symbol? elem__43528_nth_1__)
                             (clojure.core/let
                              [X__43532
                               (clojure.core/name elem__43528_nth_1__)]
                              (clojure.core/case
                               X__43532
                               ("meander.zeta")
                               [?__42264]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__44026)
                        (recur (clojure.core/next search_space__44025))
                        result__44026))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__44023)))]
          (if
           (clojure.core/vector? input__42248)
           (if
            (clojure.core/= (clojure.core/count input__42248) 2)
            (clojure.core/let
             [input__42248_nth_0__
              (clojure.core/nth input__42248 0)
              input__42248_nth_1__
              (clojure.core/nth input__42248 1)]
             (if
              (clojure.core/seq? input__42248_nth_0__)
              (clojure.core/let
               [input__42248_nth_0___l__
                (clojure.core/take 1 input__42248_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__42248_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__42248_nth_0___r__
                  (clojure.core/drop 1 input__42248_nth_0__)]
                 (clojure.core/let
                  [input__42248_nth_0___l___nth_0__
                   (clojure.core/nth input__42248_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__42248_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__43510
                     (clojure.core/namespace
                      input__42248_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__42264 X__43510]
                     (clojure.core/let
                      [X__43512
                       (clojure.core/name
                        input__42248_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__43512
                       ("re")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__43500 input__42248_nth_1__ ?__42264)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__43794)
                         (clojure.core/let
                          [[?__42264] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__42248)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__42248)
                             2)
                            (clojure.core/let
                             [input__42248_nth_0__
                              (clojure.core/nth input__42248 0)
                              input__42248_nth_1__
                              (clojure.core/nth input__42248 1)]
                             (if
                              (clojure.core/seq? input__42248_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__42248_nth_0__)
                                2)
                               (clojure.core/let
                                [input__42248_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__42248_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?regex input__42248_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__42248_nth_0__]
                                  (clojure.core/let
                                   [?env input__42248_nth_1__]
                                   (try
                                    [{:tag :regex,
                                      :regex ?regex,
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__16581__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__16581__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__16581__auto__))))))))
                               (state__43794))
                              (state__43794)))
                            (state__43794))
                           (state__43794)))))
                       (state__43794)))))
                   (state__43794))))
                (state__43794)))
              (state__43794)))
            (state__43794))
           (state__43794))))
        (state__43794
         []
         (clojure.core/letfn
          [(def__43534
            [arg__43557 ?__42265]
            (clojure.core/letfn
             [(state__44028
               []
               (clojure.core/let
                [x__43558 "meander.zeta"]
                (if
                 (clojure.core/= ?__42265 x__43558)
                 [?__42265]
                 (state__44029))))
              (state__44029
               []
               (if
                (clojure.core/map? arg__43557)
                (clojure.core/let
                 [VAL__43559 (.valAt arg__43557 :aliases)]
                 (if
                  (clojure.core/map? VAL__43559)
                  (clojure.core/let
                   [X__43561 (clojure.core/set VAL__43559)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__43561))
                    (clojure.core/loop
                     [search_space__44030 (clojure.core/seq X__43561)]
                     (if
                      (clojure.core/seq search_space__44030)
                      (clojure.core/let
                       [elem__43562
                        (clojure.core/first search_space__44030)
                        result__44031
                        (clojure.core/let
                         [elem__43562_nth_0__
                          (clojure.core/nth elem__43562 0)
                          elem__43562_nth_1__
                          (clojure.core/nth elem__43562 1)]
                         (if
                          (clojure.core/symbol? elem__43562_nth_0__)
                          (clojure.core/let
                           [X__43564
                            (clojure.core/name elem__43562_nth_0__)]
                           (if
                            (clojure.core/= ?__42265 X__43564)
                            (if
                             (clojure.core/symbol? elem__43562_nth_1__)
                             (clojure.core/let
                              [X__43566
                               (clojure.core/name elem__43562_nth_1__)]
                              (clojure.core/case
                               X__43566
                               ("meander.zeta")
                               [?__42265]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__44031)
                        (recur (clojure.core/next search_space__44030))
                        result__44031))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__44028)))]
          (if
           (clojure.core/vector? input__42248)
           (if
            (clojure.core/= (clojure.core/count input__42248) 2)
            (clojure.core/let
             [input__42248_nth_0__
              (clojure.core/nth input__42248 0)
              input__42248_nth_1__
              (clojure.core/nth input__42248 1)]
             (if
              (clojure.core/seq? input__42248_nth_0__)
              (clojure.core/let
               [input__42248_nth_0___l__
                (clojure.core/take 1 input__42248_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__42248_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__42248_nth_0___r__
                  (clojure.core/drop 1 input__42248_nth_0__)]
                 (clojure.core/let
                  [input__42248_nth_0___l___nth_0__
                   (clojure.core/nth input__42248_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__42248_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__43544
                     (clojure.core/namespace
                      input__42248_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__42265 X__43544]
                     (clojure.core/let
                      [X__43546
                       (clojure.core/name
                        input__42248_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__43546
                       ("re")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__43534 input__42248_nth_1__ ?__42265)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__43795)
                         (clojure.core/let
                          [[?__42265] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__42248)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__42248)
                             2)
                            (clojure.core/let
                             [input__42248_nth_0__
                              (clojure.core/nth input__42248 0)
                              input__42248_nth_1__
                              (clojure.core/nth input__42248 1)]
                             (if
                              (clojure.core/seq? input__42248_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__42248_nth_0__)
                                3)
                               (clojure.core/let
                                [input__42248_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__42248_nth_0__
                                  1)
                                 input__42248_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__42248_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?regex input__42248_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?capture
                                   input__42248_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__42248_nth_0__]
                                   (clojure.core/let
                                    [?env input__42248_nth_1__]
                                    (try
                                     [{:tag :regex,
                                       :regex ?regex,
                                       :capture
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__42325
                                          [?capture ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__15641__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__15641__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__16581__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__16581__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__16581__auto__)))))))))
                               (state__43795))
                              (state__43795)))
                            (state__43795))
                           (state__43795)))))
                       (state__43795)))))
                   (state__43795))))
                (state__43795)))
              (state__43795)))
            (state__43795))
           (state__43795))))
        (state__43795
         []
         (clojure.core/letfn
          [(def__43568
            [arg__43591 ?__42266]
            (clojure.core/letfn
             [(state__44033
               []
               (clojure.core/let
                [x__43592 "meander.zeta"]
                (if
                 (clojure.core/= ?__42266 x__43592)
                 [?__42266]
                 (state__44034))))
              (state__44034
               []
               (if
                (clojure.core/map? arg__43591)
                (clojure.core/let
                 [VAL__43593 (.valAt arg__43591 :aliases)]
                 (if
                  (clojure.core/map? VAL__43593)
                  (clojure.core/let
                   [X__43595 (clojure.core/set VAL__43593)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__43595))
                    (clojure.core/loop
                     [search_space__44035 (clojure.core/seq X__43595)]
                     (if
                      (clojure.core/seq search_space__44035)
                      (clojure.core/let
                       [elem__43596
                        (clojure.core/first search_space__44035)
                        result__44036
                        (clojure.core/let
                         [elem__43596_nth_0__
                          (clojure.core/nth elem__43596 0)
                          elem__43596_nth_1__
                          (clojure.core/nth elem__43596 1)]
                         (if
                          (clojure.core/symbol? elem__43596_nth_0__)
                          (clojure.core/let
                           [X__43598
                            (clojure.core/name elem__43596_nth_0__)]
                           (if
                            (clojure.core/= ?__42266 X__43598)
                            (if
                             (clojure.core/symbol? elem__43596_nth_1__)
                             (clojure.core/let
                              [X__43600
                               (clojure.core/name elem__43596_nth_1__)]
                              (clojure.core/case
                               X__43600
                               ("meander.zeta")
                               [?__42266]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__44036)
                        (recur (clojure.core/next search_space__44035))
                        result__44036))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__44033)))]
          (if
           (clojure.core/vector? input__42248)
           (if
            (clojure.core/= (clojure.core/count input__42248) 2)
            (clojure.core/let
             [input__42248_nth_0__
              (clojure.core/nth input__42248 0)
              input__42248_nth_1__
              (clojure.core/nth input__42248 1)]
             (if
              (clojure.core/seq? input__42248_nth_0__)
              (clojure.core/let
               [input__42248_nth_0___l__
                (clojure.core/take 1 input__42248_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__42248_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__42248_nth_0___r__
                  (clojure.core/drop 1 input__42248_nth_0__)]
                 (clojure.core/let
                  [input__42248_nth_0___l___nth_0__
                   (clojure.core/nth input__42248_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__42248_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__43578
                     (clojure.core/namespace
                      input__42248_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__42266 X__43578]
                     (clojure.core/let
                      [X__43580
                       (clojure.core/name
                        input__42248_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__43580
                       ("string")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__43568 input__42248_nth_1__ ?__42266)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__43796)
                         (clojure.core/let
                          [[?__42266] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__42248)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__42248)
                             2)
                            (clojure.core/let
                             [input__42248_nth_0__
                              (clojure.core/nth input__42248 0)
                              input__42248_nth_1__
                              (clojure.core/nth input__42248 1)]
                             (if
                              (clojure.core/seq? input__42248_nth_0__)
                              (clojure.core/let
                               [input__42248_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__42248_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__42248_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__42248_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__42248_nth_0__)]
                                 (clojure.core/let
                                  [?sequence input__42248_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__42248_nth_0__]
                                   (clojure.core/let
                                    [?env input__42248_nth_1__]
                                    (try
                                     [{:tag :string,
                                       :next
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__42325
                                          ['meander.dev.parse.zeta/parse-sequential
                                           (clojure.core/into
                                            []
                                            ?sequence)
                                           (clojure.core/let
                                            [form__15740__auto__
                                             {:context :string}]
                                            (clojure.core/merge
                                             (clojure.core/into
                                              {}
                                              ?env)
                                             form__15740__auto__))])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__15641__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__15641__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__16581__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__16581__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__16581__auto__))))))))
                                (state__43796)))
                              (state__43796)))
                            (state__43796))
                           (state__43796)))))
                       (state__43796)))))
                   (state__43796))))
                (state__43796)))
              (state__43796)))
            (state__43796))
           (state__43796))))
        (state__43796
         []
         (clojure.core/letfn
          [(def__43602
            [arg__43625 ?__42267]
            (clojure.core/letfn
             [(state__44038
               []
               (clojure.core/let
                [x__43626 "meander.zeta"]
                (if
                 (clojure.core/= ?__42267 x__43626)
                 [?__42267]
                 (state__44039))))
              (state__44039
               []
               (if
                (clojure.core/map? arg__43625)
                (clojure.core/let
                 [VAL__43627 (.valAt arg__43625 :aliases)]
                 (if
                  (clojure.core/map? VAL__43627)
                  (clojure.core/let
                   [X__43629 (clojure.core/set VAL__43627)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__43629))
                    (clojure.core/loop
                     [search_space__44040 (clojure.core/seq X__43629)]
                     (if
                      (clojure.core/seq search_space__44040)
                      (clojure.core/let
                       [elem__43630
                        (clojure.core/first search_space__44040)
                        result__44041
                        (clojure.core/let
                         [elem__43630_nth_0__
                          (clojure.core/nth elem__43630 0)
                          elem__43630_nth_1__
                          (clojure.core/nth elem__43630 1)]
                         (if
                          (clojure.core/symbol? elem__43630_nth_0__)
                          (clojure.core/let
                           [X__43632
                            (clojure.core/name elem__43630_nth_0__)]
                           (if
                            (clojure.core/= ?__42267 X__43632)
                            (if
                             (clojure.core/symbol? elem__43630_nth_1__)
                             (clojure.core/let
                              [X__43634
                               (clojure.core/name elem__43630_nth_1__)]
                              (clojure.core/case
                               X__43634
                               ("meander.zeta")
                               [?__42267]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__44041)
                        (recur (clojure.core/next search_space__44040))
                        result__44041))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__44038)))]
          (if
           (clojure.core/vector? input__42248)
           (if
            (clojure.core/= (clojure.core/count input__42248) 2)
            (clojure.core/let
             [input__42248_nth_0__
              (clojure.core/nth input__42248 0)
              input__42248_nth_1__
              (clojure.core/nth input__42248 1)]
             (if
              (clojure.core/seq? input__42248_nth_0__)
              (clojure.core/let
               [input__42248_nth_0___l__
                (clojure.core/take 1 input__42248_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__42248_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__42248_nth_0___r__
                  (clojure.core/drop 1 input__42248_nth_0__)]
                 (clojure.core/let
                  [input__42248_nth_0___l___nth_0__
                   (clojure.core/nth input__42248_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__42248_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__43612
                     (clojure.core/namespace
                      input__42248_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__42267 X__43612]
                     (clojure.core/let
                      [X__43614
                       (clojure.core/name
                        input__42248_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__43614
                       ("symbol")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__43602 input__42248_nth_1__ ?__42267)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__43797)
                         (clojure.core/let
                          [[?__42267] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__42248)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__42248)
                             2)
                            (clojure.core/let
                             [input__42248_nth_0__
                              (clojure.core/nth input__42248 0)
                              input__42248_nth_1__
                              (clojure.core/nth input__42248 1)]
                             (if
                              (clojure.core/seq? input__42248_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__42248_nth_0__)
                                2)
                               (clojure.core/let
                                [input__42248_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__42248_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?name input__42248_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__42248_nth_0__]
                                  (clojure.core/let
                                   [?env input__42248_nth_1__]
                                   (try
                                    [{:tag :symbol,
                                      :name
                                      (clojure.core/let
                                       [CATA_RESULT__15641__auto__
                                        (CATA__FN__42325 [?name ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__15641__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__15641__auto__
                                         0))),
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__16581__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__16581__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__16581__auto__))))))))
                               (state__43797))
                              (state__43797)))
                            (state__43797))
                           (state__43797)))))
                       (state__43797)))))
                   (state__43797))))
                (state__43797)))
              (state__43797)))
            (state__43797))
           (state__43797))))
        (state__43797
         []
         (clojure.core/letfn
          [(def__43636
            [arg__43659 ?__42268]
            (clojure.core/letfn
             [(state__44043
               []
               (clojure.core/let
                [x__43660 "meander.zeta"]
                (if
                 (clojure.core/= ?__42268 x__43660)
                 [?__42268]
                 (state__44044))))
              (state__44044
               []
               (if
                (clojure.core/map? arg__43659)
                (clojure.core/let
                 [VAL__43661 (.valAt arg__43659 :aliases)]
                 (if
                  (clojure.core/map? VAL__43661)
                  (clojure.core/let
                   [X__43663 (clojure.core/set VAL__43661)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__43663))
                    (clojure.core/loop
                     [search_space__44045 (clojure.core/seq X__43663)]
                     (if
                      (clojure.core/seq search_space__44045)
                      (clojure.core/let
                       [elem__43664
                        (clojure.core/first search_space__44045)
                        result__44046
                        (clojure.core/let
                         [elem__43664_nth_0__
                          (clojure.core/nth elem__43664 0)
                          elem__43664_nth_1__
                          (clojure.core/nth elem__43664 1)]
                         (if
                          (clojure.core/symbol? elem__43664_nth_0__)
                          (clojure.core/let
                           [X__43666
                            (clojure.core/name elem__43664_nth_0__)]
                           (if
                            (clojure.core/= ?__42268 X__43666)
                            (if
                             (clojure.core/symbol? elem__43664_nth_1__)
                             (clojure.core/let
                              [X__43668
                               (clojure.core/name elem__43664_nth_1__)]
                              (clojure.core/case
                               X__43668
                               ("meander.zeta")
                               [?__42268]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__44046)
                        (recur (clojure.core/next search_space__44045))
                        result__44046))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__44043)))]
          (if
           (clojure.core/vector? input__42248)
           (if
            (clojure.core/= (clojure.core/count input__42248) 2)
            (clojure.core/let
             [input__42248_nth_0__
              (clojure.core/nth input__42248 0)
              input__42248_nth_1__
              (clojure.core/nth input__42248 1)]
             (if
              (clojure.core/seq? input__42248_nth_0__)
              (clojure.core/let
               [input__42248_nth_0___l__
                (clojure.core/take 1 input__42248_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__42248_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__42248_nth_0___r__
                  (clojure.core/drop 1 input__42248_nth_0__)]
                 (clojure.core/let
                  [input__42248_nth_0___l___nth_0__
                   (clojure.core/nth input__42248_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__42248_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__43646
                     (clojure.core/namespace
                      input__42248_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__42268 X__43646]
                     (clojure.core/let
                      [X__43648
                       (clojure.core/name
                        input__42248_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__43648
                       ("symbol")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__43636 input__42248_nth_1__ ?__42268)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__43798)
                         (clojure.core/let
                          [[?__42268] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__42248)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__42248)
                             2)
                            (clojure.core/let
                             [input__42248_nth_0__
                              (clojure.core/nth input__42248 0)
                              input__42248_nth_1__
                              (clojure.core/nth input__42248 1)]
                             (if
                              (clojure.core/seq? input__42248_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__42248_nth_0__)
                                3)
                               (clojure.core/let
                                [input__42248_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__42248_nth_0__
                                  1)
                                 input__42248_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__42248_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?namespace
                                  input__42248_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?name input__42248_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__42248_nth_0__]
                                   (clojure.core/let
                                    [?env input__42248_nth_1__]
                                    (try
                                     [{:tag :symbol,
                                       :name
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__42325
                                          [?name ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__15641__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__15641__auto__
                                          0))),
                                       :namespace
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__42325
                                          [?namespace ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__15641__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__15641__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__16581__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__16581__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__16581__auto__)))))))))
                               (state__43798))
                              (state__43798)))
                            (state__43798))
                           (state__43798)))))
                       (state__43798)))))
                   (state__43798))))
                (state__43798)))
              (state__43798)))
            (state__43798))
           (state__43798))))
        (state__43798
         []
         (clojure.core/letfn
          [(def__43670
            [arg__43693 ?__42269]
            (clojure.core/letfn
             [(state__44048
               []
               (clojure.core/let
                [x__43694 "meander.zeta"]
                (if
                 (clojure.core/= ?__42269 x__43694)
                 [?__42269]
                 (state__44049))))
              (state__44049
               []
               (if
                (clojure.core/map? arg__43693)
                (clojure.core/let
                 [VAL__43695 (.valAt arg__43693 :aliases)]
                 (if
                  (clojure.core/map? VAL__43695)
                  (clojure.core/let
                   [X__43697 (clojure.core/set VAL__43695)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__43697))
                    (clojure.core/loop
                     [search_space__44050 (clojure.core/seq X__43697)]
                     (if
                      (clojure.core/seq search_space__44050)
                      (clojure.core/let
                       [elem__43698
                        (clojure.core/first search_space__44050)
                        result__44051
                        (clojure.core/let
                         [elem__43698_nth_0__
                          (clojure.core/nth elem__43698 0)
                          elem__43698_nth_1__
                          (clojure.core/nth elem__43698 1)]
                         (if
                          (clojure.core/symbol? elem__43698_nth_0__)
                          (clojure.core/let
                           [X__43700
                            (clojure.core/name elem__43698_nth_0__)]
                           (if
                            (clojure.core/= ?__42269 X__43700)
                            (if
                             (clojure.core/symbol? elem__43698_nth_1__)
                             (clojure.core/let
                              [X__43702
                               (clojure.core/name elem__43698_nth_1__)]
                              (clojure.core/case
                               X__43702
                               ("meander.zeta")
                               [?__42269]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__44051)
                        (recur (clojure.core/next search_space__44050))
                        result__44051))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__44048)))]
          (if
           (clojure.core/vector? input__42248)
           (if
            (clojure.core/= (clojure.core/count input__42248) 2)
            (clojure.core/let
             [input__42248_nth_0__
              (clojure.core/nth input__42248 0)
              input__42248_nth_1__
              (clojure.core/nth input__42248 1)]
             (if
              (clojure.core/seq? input__42248_nth_0__)
              (clojure.core/let
               [input__42248_nth_0___l__
                (clojure.core/take 1 input__42248_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__42248_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__42248_nth_0___r__
                  (clojure.core/drop 1 input__42248_nth_0__)]
                 (clojure.core/let
                  [input__42248_nth_0___l___nth_0__
                   (clojure.core/nth input__42248_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__42248_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__43680
                     (clojure.core/namespace
                      input__42248_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__42269 X__43680]
                     (clojure.core/let
                      [X__43682
                       (clojure.core/name
                        input__42248_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__43682
                       ("symbol")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__43670 input__42248_nth_1__ ?__42269)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__43799)
                         (clojure.core/let
                          [[?__42269] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__42248)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__42248)
                             2)
                            (clojure.core/let
                             [input__42248_nth_0__
                              (clojure.core/nth input__42248 0)
                              input__42248_nth_1__
                              (clojure.core/nth input__42248 1)]
                             (if
                              (clojure.core/seq? input__42248_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 5)
                                 input__42248_nth_0__)
                                5)
                               (clojure.core/let
                                [input__42248_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__42248_nth_0__
                                  1)
                                 input__42248_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__42248_nth_0__
                                  2)
                                 input__42248_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__42248_nth_0__
                                  3)
                                 input__42248_nth_0___nth_4__
                                 (clojure.core/nth
                                  input__42248_nth_0__
                                  4)]
                                (clojure.core/case
                                 input__42248_nth_0___nth_3__
                                 (:meander.zeta/as)
                                 (clojure.core/let
                                  [?namespace
                                   input__42248_nth_0___nth_1__]
                                  (clojure.core/let
                                   [?name input__42248_nth_0___nth_2__]
                                   (clojure.core/let
                                    [?pattern
                                     input__42248_nth_0___nth_4__]
                                    (clojure.core/let
                                     [?form input__42248_nth_0__]
                                     (clojure.core/let
                                      [?env input__42248_nth_1__]
                                      (try
                                       [{:tag :symbol,
                                         :name
                                         (clojure.core/let
                                          [CATA_RESULT__15641__auto__
                                           (CATA__FN__42325
                                            [?name ?env])]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            CATA_RESULT__15641__auto__)
                                           (throw
                                            (meander.runtime.zeta/fail))
                                           (clojure.core/nth
                                            CATA_RESULT__15641__auto__
                                            0))),
                                         :namespace
                                         (clojure.core/let
                                          [CATA_RESULT__15641__auto__
                                           (CATA__FN__42325
                                            [?namespace ?env])]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            CATA_RESULT__15641__auto__)
                                           (throw
                                            (meander.runtime.zeta/fail))
                                           (clojure.core/nth
                                            CATA_RESULT__15641__auto__
                                            0))),
                                         :as-pattern
                                         (clojure.core/let
                                          [CATA_RESULT__15641__auto__
                                           (CATA__FN__42325
                                            [?pattern ?env])]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            CATA_RESULT__15641__auto__)
                                           (throw
                                            (meander.runtime.zeta/fail))
                                           (clojure.core/nth
                                            CATA_RESULT__15641__auto__
                                            0))),
                                         :form ?form}]
                                       (catch
                                        java.lang.Exception
                                        e__16581__auto__
                                        (if
                                         (meander.runtime.zeta/fail?
                                          e__16581__auto__)
                                         (meander.runtime.zeta/fail)
                                         (throw
                                          e__16581__auto__)))))))))
                                 (state__43799)))
                               (state__43799))
                              (state__43799)))
                            (state__43799))
                           (state__43799)))))
                       (state__43799)))))
                   (state__43799))))
                (state__43799)))
              (state__43799)))
            (state__43799))
           (state__43799))))
        (state__43799
         []
         (if
          (clojure.core/vector? input__42248)
          (if
           (clojure.core/= (clojure.core/count input__42248) 2)
           (clojure.core/let
            [input__42248_nth_0__ (clojure.core/nth input__42248 0)]
            (clojure.core/letfn
             [(state__44053
               []
               (clojure.core/let
                [input__42248_nth_1__
                 (clojure.core/nth input__42248 1)]
                (clojure.core/letfn
                 [(state__44058
                   []
                   (if
                    (clojure.core/seq? input__42248_nth_0__)
                    (clojure.core/let
                     [?sequence input__42248_nth_0__]
                     (clojure.core/let
                      [?env input__42248_nth_1__]
                      (try
                       [{:tag :seq,
                         :next
                         (clojure.core/let
                          [CATA_RESULT__15641__auto__
                           (CATA__FN__42325
                            ['meander.dev.parse.zeta/parse-sequential
                             (clojure.core/into [] ?sequence)
                             (clojure.core/let
                              [form__15740__auto__ {:context :seq}]
                              (clojure.core/merge
                               (clojure.core/into {} ?env)
                               form__15740__auto__))])]
                          (if
                           (meander.runtime.zeta/fail?
                            CATA_RESULT__15641__auto__)
                           (throw (meander.runtime.zeta/fail))
                           (clojure.core/nth
                            CATA_RESULT__15641__auto__
                            0))),
                         :form ?sequence}]
                       (catch
                        java.lang.Exception
                        e__16581__auto__
                        (if
                         (meander.runtime.zeta/fail? e__16581__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__16581__auto__))))))
                    (state__44059)))
                  (state__44059
                   []
                   (if
                    (clojure.core/map? input__42248_nth_0__)
                    (clojure.core/let
                     [?map input__42248_nth_0__]
                     (clojure.core/let
                      [?env input__42248_nth_1__]
                      (try
                       [{:tag :map,
                         :next
                         (clojure.core/let
                          [CATA_RESULT__15641__auto__
                           (CATA__FN__42325
                            ['meander.dev.parse.zeta/parse-entries
                             ?map
                             ?env])]
                          (if
                           (meander.runtime.zeta/fail?
                            CATA_RESULT__15641__auto__)
                           (throw (meander.runtime.zeta/fail))
                           (clojure.core/nth
                            CATA_RESULT__15641__auto__
                            0))),
                         :form ?map}]
                       (catch
                        java.lang.Exception
                        e__16581__auto__
                        (if
                         (meander.runtime.zeta/fail? e__16581__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__16581__auto__))))))
                    (state__44054)))]
                 (state__44058))))
              (state__44054
               []
               (if
                (clojure.core/symbol? input__42248_nth_0__)
                (clojure.core/let
                 [X__43712
                  (clojure.core/namespace input__42248_nth_0__)]
                 (clojure.core/let
                  [X__43714 (clojure.core/name input__42248_nth_0__)]
                  (if
                   (clojure.core/string? X__43714)
                   (clojure.core/letfn
                    [(state__44060
                      []
                      (clojure.core/let
                       [ret__43715
                        (clojure.core/re-matches #"_.*" X__43714)]
                       (if
                        (clojure.core/some? ret__43715)
                        (clojure.core/let
                         [?name ret__43715]
                         (clojure.core/let
                          [?symbol input__42248_nth_0__]
                          (try
                           [{:tag :wildcard,
                             :name ?name,
                             :form ?symbol,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__16581__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__16581__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__16581__auto__))))))
                        (state__44061))))
                     (state__44061
                      []
                      (clojure.core/let
                       [ret__43722
                        (clojure.core/re-matches #".+#" X__43714)]
                       (if
                        (clojure.core/some? ret__43722)
                        (clojure.core/let
                         [?name ret__43722]
                         (clojure.core/let
                          [?symbol input__42248_nth_0__]
                          (try
                           [{:tag :random-symbol,
                             :name ?name,
                             :form ?symbol,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__16581__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__16581__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__16581__auto__))))))
                        (state__44062))))
                     (state__44062
                      []
                      (clojure.core/let
                       [ret__43729
                        (clojure.core/re-matches #"%.+" X__43714)]
                       (if
                        (clojure.core/some? ret__43729)
                        (clojure.core/let
                         [?name ret__43729]
                         (clojure.core/let
                          [?symbol input__42248_nth_0__]
                          (try
                           [{:tag :reference,
                             :name ?name,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__16581__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__16581__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__16581__auto__))))))
                        (state__44063))))
                     (state__44063
                      []
                      (clojure.core/let
                       [ret__43736
                        (clojure.core/re-matches #"\*.+" X__43714)]
                       (if
                        (clojure.core/some? ret__43736)
                        (clojure.core/let
                         [?name ret__43736]
                         (clojure.core/let
                          [?symbol input__42248_nth_0__]
                          (try
                           [{:tag :mutable-variable,
                             :name ?name,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__16581__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__16581__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__16581__auto__))))))
                        (state__44064))))
                     (state__44064
                      []
                      (clojure.core/let
                       [ret__43743
                        (clojure.core/re-matches #"\!.+" X__43714)]
                       (if
                        (clojure.core/some? ret__43743)
                        (clojure.core/let
                         [?name ret__43743]
                         (clojure.core/let
                          [?symbol input__42248_nth_0__]
                          (try
                           [{:tag :memory-variable,
                             :name ?name,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__16581__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__16581__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__16581__auto__))))))
                        (state__44065))))
                     (state__44065
                      []
                      (clojure.core/let
                       [ret__43750
                        (clojure.core/re-matches #"\?.+" X__43714)]
                       (if
                        (clojure.core/some? ret__43750)
                        (clojure.core/let
                         [?name ret__43750]
                         (clojure.core/let
                          [?symbol input__42248_nth_0__]
                          (try
                           [{:tag :logic-variable,
                             :name ?name,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__16581__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__16581__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__16581__auto__))))))
                        (state__44055))))]
                    (state__44060))
                   (state__44055))))
                (state__44055)))
              (state__44055
               []
               (if
                (string? input__42248_nth_0__)
                (clojure.core/let
                 [?x input__42248_nth_0__]
                 (try
                  [{:tag :literal, :type :string, :form ?x}]
                  (catch
                   java.lang.Exception
                   e__16581__auto__
                   (if
                    (meander.runtime.zeta/fail? e__16581__auto__)
                    (meander.runtime.zeta/fail)
                    (throw e__16581__auto__)))))
                (state__44056)))
              (state__44056
               []
               (if
                (char? input__42248_nth_0__)
                (clojure.core/let
                 [?x input__42248_nth_0__]
                 (try
                  [{:tag :literal, :type :char, :form ?x}]
                  (catch
                   java.lang.Exception
                   e__16581__auto__
                   (if
                    (meander.runtime.zeta/fail? e__16581__auto__)
                    (meander.runtime.zeta/fail)
                    (throw e__16581__auto__)))))
                (state__44057)))
              (state__44057
               []
               (clojure.core/let
                [?x input__42248_nth_0__]
                (try
                 [{:tag :literal, :form ?x}]
                 (catch
                  java.lang.Exception
                  e__16581__auto__
                  (if
                   (meander.runtime.zeta/fail? e__16581__auto__)
                   (meander.runtime.zeta/fail)
                   (throw e__16581__auto__))))))]
             (state__44053)))
           (state__43800))
          (state__43800)))
        (state__43800
         []
         (clojure.core/let
          [?x input__42248]
          (try
           [{:tag :mistake, :x ?x}]
           (catch
            java.lang.Exception
            e__16581__auto__
            (if
             (meander.runtime.zeta/fail? e__16581__auto__)
             (meander.runtime.zeta/fail)
             (throw e__16581__auto__))))))]
       (state__43763)))]
    (clojure.core/let
     [x__14338__auto__ (CATA__FN__42325 input__42248)]
     (if
      (meander.runtime.zeta/fail? x__14338__auto__)
      (meander.runtime.zeta/fail)
      (clojure.core/let
       [[CATA_RETURN__42329] x__14338__auto__]
       CATA_RETURN__42329))))]
  (if
   (meander.runtime.zeta/fail? ret__14518__auto__)
   nil
   ret__14518__auto__)))
