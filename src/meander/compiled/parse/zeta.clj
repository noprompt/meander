(ns
 meander.compiled.parse.zeta
 (:require [meander.runtime.zeta] [meander.util.zeta]))
(clojure.core/defn
 parse
 [input__14353]
 (let*
  [ret__8115__auto__
   (clojure.core/letfn
    [(CATA__FN__14430
      [input__14353]
      (clojure.core/letfn
       [(state__15849
         []
         (if
          (clojure.core/vector? input__14353)
          (if
           (clojure.core/= (clojure.core/count input__14353) 3)
           (clojure.core/let
            [input__14353_nth_0__
             (clojure.core/nth input__14353 0)
             input__14353_nth_1__
             (clojure.core/nth input__14353 1)
             input__14353_nth_2__
             (clojure.core/nth input__14353 2)]
            (clojure.core/case
             input__14353_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__14353_nth_1__)
              (clojure.core/letfn
               [(state__15888
                 []
                 (clojure.core/case
                  input__14353_nth_1__
                  ([])
                  (clojure.core/let
                   [?env input__14353_nth_2__]
                   (try
                    [{:tag :empty}]
                    (catch
                     java.lang.Exception
                     e__10178__auto__
                     (if
                      (meander.runtime.zeta/fail? e__10178__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__10178__auto__)))))
                  (state__15889)))
                (state__15889
                 []
                 (clojure.core/let
                  [n__14439
                   (clojure.core/count input__14353_nth_1__)
                   m__14440
                   (clojure.core/max 0 (clojure.core/- n__14439 2))
                   input__14353_nth_1___l__
                   (clojure.core/subvec
                    input__14353_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__14353_nth_1__)
                     m__14440))
                   input__14353_nth_1___r__
                   (clojure.core/subvec input__14353_nth_1__ m__14440)]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__14353_nth_1___r__)
                    2)
                   (clojure.core/let
                    [!xs (clojure.core/vec input__14353_nth_1___l__)]
                    (if
                     (clojure.core/=
                      (clojure.core/count input__14353_nth_1___r__)
                      2)
                     (clojure.core/let
                      [input__14353_nth_1___r___nth_0__
                       (clojure.core/nth input__14353_nth_1___r__ 0)
                       input__14353_nth_1___r___nth_1__
                       (clojure.core/nth input__14353_nth_1___r__ 1)]
                      (clojure.core/case
                       input__14353_nth_1___r___nth_0__
                       (:meander.zeta/as)
                       (clojure.core/let
                        [?pattern input__14353_nth_1___r___nth_1__]
                        (clojure.core/let
                         [?env input__14353_nth_2__]
                         (try
                          [(clojure.core/let
                            [!xs__counter
                             (meander.runtime.zeta/iterator !xs)]
                            {:tag :as,
                             :pattern
                             (clojure.core/let
                              [CATA_RESULT__9238__auto__
                               (CATA__FN__14430 [?pattern ?env])]
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
                               (CATA__FN__14430
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
                       (state__15850)))
                     (state__15850)))
                   (state__15850))))]
               (state__15888))
              (state__15850))
             (state__15850)))
           (state__15850))
          (state__15850)))
        (state__15850
         []
         (clojure.core/letfn
          [(def__14445
            [arg__14480 ?ns]
            (clojure.core/letfn
             [(state__15890
               []
               (clojure.core/let
                [x__14481 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__14481)
                 (clojure.core/let [?env arg__14480] [?env ?ns])
                 (state__15891))))
              (state__15891
               []
               (if
                (clojure.core/map? arg__14480)
                (clojure.core/let
                 [VAL__14482 (.valAt arg__14480 :aliases)]
                 (if
                  (clojure.core/map? VAL__14482)
                  (clojure.core/let
                   [X__14484 (clojure.core/set VAL__14482)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__14484))
                    (clojure.core/loop
                     [search_space__15892 (clojure.core/seq X__14484)]
                     (if
                      (clojure.core/seq search_space__15892)
                      (clojure.core/let
                       [elem__14485
                        (clojure.core/first search_space__15892)
                        result__15893
                        (clojure.core/let
                         [elem__14485_nth_0__
                          (clojure.core/nth elem__14485 0)
                          elem__14485_nth_1__
                          (clojure.core/nth elem__14485 1)]
                         (if
                          (clojure.core/symbol? elem__14485_nth_0__)
                          (clojure.core/let
                           [X__14487
                            (clojure.core/name elem__14485_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__14487)
                            (if
                             (clojure.core/symbol? elem__14485_nth_1__)
                             (clojure.core/let
                              [X__14489
                               (clojure.core/name elem__14485_nth_1__)]
                              (clojure.core/case
                               X__14489
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__14480]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__15893)
                        (recur (clojure.core/next search_space__15892))
                        result__15893))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__15890)))]
          (if
           (clojure.core/vector? input__14353)
           (if
            (clojure.core/= (clojure.core/count input__14353) 3)
            (clojure.core/let
             [input__14353_nth_0__
              (clojure.core/nth input__14353 0)
              input__14353_nth_1__
              (clojure.core/nth input__14353 1)
              input__14353_nth_2__
              (clojure.core/nth input__14353 2)]
             (clojure.core/case
              input__14353_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__14353_nth_1__)
               (clojure.core/loop
                [search_space__15895
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__14353_nth_1__)]
                (if
                 (clojure.core/seq search_space__15895)
                 (clojure.core/let
                  [input__14353_nth_1___parts__
                   (clojure.core/first search_space__15895)
                   result__15896
                   (clojure.core/let
                    [input__14353_nth_1___l__
                     (clojure.core/nth input__14353_nth_1___parts__ 0)
                     input__14353_nth_1___r__
                     (clojure.core/nth input__14353_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__14353_nth_1___l__)]
                     (clojure.core/let
                      [input__14353_nth_1___r___l__
                       (clojure.core/subvec
                        input__14353_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__14353_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__14353_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__14353_nth_1___r___r__
                         (clojure.core/subvec
                          input__14353_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__14353_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__14353_nth_1___r___l__
                           0)
                          input__14353_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__14353_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__14353_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__14454
                            (clojure.core/namespace
                             input__14353_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__14454]
                            (clojure.core/let
                             [X__14456
                              (clojure.core/name
                               input__14353_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__14456)
                              (clojure.core/let
                               [ret__14457
                                (clojure.core/re-matches
                                 #"&(\d+)"
                                 X__14456)]
                               (if
                                (clojure.core/some? ret__14457)
                                (if
                                 (clojure.core/vector? ret__14457)
                                 (if
                                  (clojure.core/=
                                   (clojure.core/count ret__14457)
                                   2)
                                  (clojure.core/let
                                   [ret__14457_nth_1__
                                    (clojure.core/nth ret__14457 1)]
                                   (clojure.core/let
                                    [?n ret__14457_nth_1__]
                                    (clojure.core/let
                                     [?pattern
                                      input__14353_nth_1___r___l___nth_1__]
                                     (clojure.core/let
                                      [?rest
                                       input__14353_nth_1___r___r__]
                                      (clojure.core/let
                                       [x__7935__auto__
                                        (def__14445
                                         input__14353_nth_2__
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
                                              (CATA__FN__14430
                                               ['meander.dev.parse.zeta/make-join
                                                (clojure.core/let
                                                 [CATA_RESULT__9238__auto__
                                                  (CATA__FN__14430
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
                                                  (CATA__FN__14430
                                                   ['meander.dev.parse.zeta/make-join
                                                    {:tag :slice,
                                                     :size
                                                     (Integer. ?n),
                                                     :pattern
                                                     (clojure.core/let
                                                      [CATA_RESULT__9238__auto__
                                                       (CATA__FN__14430
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
                                                      (CATA__FN__14430
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
                   (meander.runtime.zeta/fail? result__15896)
                   (recur (clojure.core/next search_space__15895))
                   result__15896))
                 (state__15851)))
               (state__15851))
              (state__15851)))
            (state__15851))
           (state__15851))))
        (state__15851
         []
         (clojure.core/letfn
          [(def__14502
            [arg__14534 ?ns]
            (clojure.core/letfn
             [(state__15898
               []
               (clojure.core/let
                [x__14535 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__14535)
                 (clojure.core/let [?env arg__14534] [?env ?ns])
                 (state__15899))))
              (state__15899
               []
               (if
                (clojure.core/map? arg__14534)
                (clojure.core/let
                 [VAL__14536 (.valAt arg__14534 :aliases)]
                 (if
                  (clojure.core/map? VAL__14536)
                  (clojure.core/let
                   [X__14538 (clojure.core/set VAL__14536)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__14538))
                    (clojure.core/loop
                     [search_space__15900 (clojure.core/seq X__14538)]
                     (if
                      (clojure.core/seq search_space__15900)
                      (clojure.core/let
                       [elem__14539
                        (clojure.core/first search_space__15900)
                        result__15901
                        (clojure.core/let
                         [elem__14539_nth_0__
                          (clojure.core/nth elem__14539 0)
                          elem__14539_nth_1__
                          (clojure.core/nth elem__14539 1)]
                         (if
                          (clojure.core/symbol? elem__14539_nth_0__)
                          (clojure.core/let
                           [X__14541
                            (clojure.core/name elem__14539_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__14541)
                            (if
                             (clojure.core/symbol? elem__14539_nth_1__)
                             (clojure.core/let
                              [X__14543
                               (clojure.core/name elem__14539_nth_1__)]
                              (clojure.core/case
                               X__14543
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__14534]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__15901)
                        (recur (clojure.core/next search_space__15900))
                        result__15901))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__15898)))]
          (if
           (clojure.core/vector? input__14353)
           (if
            (clojure.core/= (clojure.core/count input__14353) 3)
            (clojure.core/let
             [input__14353_nth_0__
              (clojure.core/nth input__14353 0)
              input__14353_nth_1__
              (clojure.core/nth input__14353 1)
              input__14353_nth_2__
              (clojure.core/nth input__14353 2)]
             (clojure.core/case
              input__14353_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__14353_nth_1__)
               (clojure.core/loop
                [search_space__15903
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__14353_nth_1__)]
                (if
                 (clojure.core/seq search_space__15903)
                 (clojure.core/let
                  [input__14353_nth_1___parts__
                   (clojure.core/first search_space__15903)
                   result__15904
                   (clojure.core/let
                    [input__14353_nth_1___l__
                     (clojure.core/nth input__14353_nth_1___parts__ 0)
                     input__14353_nth_1___r__
                     (clojure.core/nth input__14353_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__14353_nth_1___l__)]
                     (clojure.core/let
                      [input__14353_nth_1___r___l__
                       (clojure.core/subvec
                        input__14353_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__14353_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__14353_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__14353_nth_1___r___r__
                         (clojure.core/subvec
                          input__14353_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__14353_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__14353_nth_1___r___l__
                           0)
                          input__14353_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__14353_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__14353_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__14511
                            (clojure.core/namespace
                             input__14353_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__14511]
                            (clojure.core/let
                             [X__14513
                              (clojure.core/name
                               input__14353_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__14513)
                              (if
                               (clojure.core/re-matches
                                #"&.*"
                                X__14513)
                               (clojure.core/let
                                [?pattern
                                 input__14353_nth_1___r___l___nth_1__]
                                (clojure.core/let
                                 [?rest input__14353_nth_1___r___r__]
                                 (clojure.core/let
                                  [x__7935__auto__
                                   (def__14502
                                    input__14353_nth_2__
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
                                         (CATA__FN__14430
                                          ['meander.dev.parse.zeta/make-join
                                           (clojure.core/let
                                            [CATA_RESULT__9238__auto__
                                             (CATA__FN__14430
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
                                             (CATA__FN__14430
                                              ['meander.dev.parse.zeta/make-join
                                               (clojure.core/let
                                                [CATA_RESULT__9238__auto__
                                                 (CATA__FN__14430
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
                                                 (CATA__FN__14430
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
                   (meander.runtime.zeta/fail? result__15904)
                   (recur (clojure.core/next search_space__15903))
                   result__15904))
                 (state__15852)))
               (state__15852))
              (state__15852)))
            (state__15852))
           (state__15852))))
        (state__15852
         []
         (if
          (clojure.core/vector? input__14353)
          (clojure.core/letfn
           [(state__15906
             []
             (if
              (clojure.core/= (clojure.core/count input__14353) 3)
              (clojure.core/let
               [input__14353_nth_0__
                (clojure.core/nth input__14353 0)
                input__14353_nth_1__
                (clojure.core/nth input__14353 1)
                input__14353_nth_2__
                (clojure.core/nth input__14353 2)]
               (clojure.core/case
                input__14353_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__14353_nth_1__)
                 (clojure.core/letfn
                  [(state__15909
                    []
                    (clojure.core/let
                     [n__14564
                      (clojure.core/count input__14353_nth_1__)
                      m__14565
                      (clojure.core/max 0 (clojure.core/- n__14564 2))
                      input__14353_nth_1___l__
                      (clojure.core/subvec
                       input__14353_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__14353_nth_1__)
                        m__14565))
                      input__14353_nth_1___r__
                      (clojure.core/subvec
                       input__14353_nth_1__
                       m__14565)]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__14353_nth_1___r__)
                       2)
                      (clojure.core/let
                       [!xs
                        (clojure.core/vec input__14353_nth_1___l__)]
                       (if
                        (clojure.core/=
                         (clojure.core/count input__14353_nth_1___r__)
                         2)
                        (clojure.core/let
                         [input__14353_nth_1___r___nth_0__
                          (clojure.core/nth input__14353_nth_1___r__ 0)
                          input__14353_nth_1___r___nth_1__
                          (clojure.core/nth
                           input__14353_nth_1___r__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__14353_nth_1___r___nth_0__)
                          (clojure.core/let
                           [X__14569
                            (clojure.core/namespace
                             input__14353_nth_1___r___nth_0__)]
                           (clojure.core/let
                            [?ns X__14569]
                            (clojure.core/let
                             [X__14571
                              (clojure.core/name
                               input__14353_nth_1___r___nth_0__)]
                             (if
                              (clojure.core/string? X__14571)
                              (clojure.core/let
                               [ret__14572
                                (clojure.core/re-matches
                                 #"&.*"
                                 X__14571)]
                               (if
                                (clojure.core/some? ret__14572)
                                (clojure.core/let
                                 [?name ret__14572]
                                 (clojure.core/let
                                  [?pattern
                                   input__14353_nth_1___r___nth_1__]
                                  (if
                                   (clojure.core/map?
                                    input__14353_nth_2__)
                                   (clojure.core/let
                                    [VAL__14556
                                     (.valAt
                                      input__14353_nth_2__
                                      :aliases)]
                                    (if
                                     (clojure.core/map? VAL__14556)
                                     (clojure.core/let
                                      [X__14558
                                       (clojure.core/set VAL__14556)]
                                      (if
                                       (clojure.core/<=
                                        1
                                        (clojure.core/count X__14558))
                                       (clojure.core/loop
                                        [search_space__15913
                                         (clojure.core/seq X__14558)]
                                        (if
                                         (clojure.core/seq
                                          search_space__15913)
                                         (clojure.core/let
                                          [elem__14559
                                           (clojure.core/first
                                            search_space__15913)
                                           result__15914
                                           (clojure.core/let
                                            [elem__14559_nth_0__
                                             (clojure.core/nth
                                              elem__14559
                                              0)
                                             elem__14559_nth_1__
                                             (clojure.core/nth
                                              elem__14559
                                              1)]
                                            (if
                                             (clojure.core/symbol?
                                              elem__14559_nth_0__)
                                             (clojure.core/let
                                              [X__14561
                                               (clojure.core/name
                                                elem__14559_nth_0__)]
                                              (if
                                               (clojure.core/=
                                                ?ns
                                                X__14561)
                                               (if
                                                (clojure.core/symbol?
                                                 elem__14559_nth_1__)
                                                (clojure.core/let
                                                 [X__14563
                                                  (clojure.core/name
                                                   elem__14559_nth_1__)]
                                                 (clojure.core/case
                                                  X__14563
                                                  ("meander.zeta")
                                                  (clojure.core/let
                                                   [?env
                                                    input__14353_nth_2__]
                                                   (try
                                                    [(clojure.core/let
                                                      [!xs__counter
                                                       (meander.runtime.zeta/iterator
                                                        !xs)]
                                                      (clojure.core/let
                                                       [CATA_RESULT__9238__auto__
                                                        (CATA__FN__14430
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
                                            result__15914)
                                           (recur
                                            (clojure.core/next
                                             search_space__15913))
                                           result__15914))
                                         (state__15910)))
                                       (state__15910)))
                                     (state__15910)))
                                   (state__15910))))
                                (state__15910)))
                              (state__15910)))))
                          (state__15910)))
                        (state__15910)))
                      (state__15910))))
                   (state__15910
                    []
                    (clojure.core/loop
                     [search_space__15916
                      (meander.runtime.zeta/epsilon-partitions
                       2
                       input__14353_nth_1__)]
                     (if
                      (clojure.core/seq search_space__15916)
                      (clojure.core/let
                       [input__14353_nth_1___parts__
                        (clojure.core/first search_space__15916)
                        result__15917
                        (clojure.core/let
                         [input__14353_nth_1___l__
                          (clojure.core/nth
                           input__14353_nth_1___parts__
                           0)
                          input__14353_nth_1___r__
                          (clojure.core/nth
                           input__14353_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs
                           (clojure.core/vec input__14353_nth_1___l__)]
                          (clojure.core/let
                           [input__14353_nth_1___r___l__
                            (clojure.core/subvec
                             input__14353_nth_1___r__
                             0
                             (clojure.core/min
                              (clojure.core/count
                               input__14353_nth_1___r__)
                              1))]
                           (if
                            (clojure.core/=
                             (clojure.core/count
                              input__14353_nth_1___r___l__)
                             1)
                            (clojure.core/let
                             [input__14353_nth_1___r___r__
                              (clojure.core/subvec
                               input__14353_nth_1___r__
                               1)]
                             (if
                              (clojure.core/=
                               input__14353_nth_1___r___l__
                               ['.])
                              (clojure.core/let
                               [?rest input__14353_nth_1___r___r__]
                               (clojure.core/let
                                [?env input__14353_nth_2__]
                                (try
                                 [(clojure.core/let
                                   [!xs__counter
                                    (meander.runtime.zeta/iterator
                                     !xs)]
                                   (clojure.core/let
                                    [CATA_RESULT__9238__auto__
                                     (CATA__FN__14430
                                      ['meander.dev.parse.zeta/make-join
                                       (clojure.core/let
                                        [CATA_RESULT__9238__auto__
                                         (CATA__FN__14430
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
                                         (CATA__FN__14430
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
                        (meander.runtime.zeta/fail? result__15917)
                        (recur (clojure.core/next search_space__15916))
                        result__15917))
                      (state__15911))))
                   (state__15911
                    []
                    (clojure.core/let
                     [input__14353_nth_1___l__
                      (clojure.core/subvec
                       input__14353_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__14353_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__14353_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__14353_nth_1___r__
                        (clojure.core/subvec input__14353_nth_1__ 1)]
                       (if
                        (clojure.core/=
                         input__14353_nth_1___l__
                         ['...])
                        (clojure.core/let
                         [?rest input__14353_nth_1___r__]
                         (clojure.core/let
                          [?env input__14353_nth_2__]
                          (try
                           [(clojure.core/let
                             [CATA_RESULT__9238__auto__
                              (CATA__FN__14430
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
                        (state__15912)))
                      (state__15912))))
                   (state__15912
                    []
                    (clojure.core/loop
                     [search_space__15919
                      (meander.runtime.zeta/epsilon-partitions
                       2
                       input__14353_nth_1__)]
                     (if
                      (clojure.core/seq search_space__15919)
                      (clojure.core/let
                       [input__14353_nth_1___parts__
                        (clojure.core/first search_space__15919)
                        result__15920
                        (clojure.core/let
                         [input__14353_nth_1___l__
                          (clojure.core/nth
                           input__14353_nth_1___parts__
                           0)
                          input__14353_nth_1___r__
                          (clojure.core/nth
                           input__14353_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs []]
                          (clojure.core/let
                           [ret__8099__auto__
                            (meander.runtime.zeta/epsilon-run-star-1
                             input__14353_nth_1___l__
                             [!xs]
                             (clojure.core/fn
                              [[!xs] input__14589]
                              (clojure.core/let
                               [input__14589_nth_0__
                                (clojure.core/nth input__14589 0)]
                               (clojure.core/letfn
                                [(save__14590
                                  []
                                  (meander.runtime.zeta/fail))
                                 (f__15923
                                  []
                                  (clojure.core/let
                                   [!xs
                                    (clojure.core/conj
                                     !xs
                                     input__14589_nth_0__)]
                                   [!xs]))]
                                (if
                                 (clojure.core/symbol?
                                  input__14589_nth_0__)
                                 (clojure.core/let
                                  [X__14592
                                   (clojure.core/namespace
                                    input__14589_nth_0__)]
                                  (clojure.core/case
                                   X__14592
                                   (nil)
                                   (clojure.core/let
                                    [X__14594
                                     (clojure.core/name
                                      input__14589_nth_0__)]
                                    (if
                                     (clojure.core/string? X__14594)
                                     (if
                                      (clojure.core/re-matches
                                       #"\.\.(?:\.|\d+)"
                                       X__14594)
                                      (save__14590)
                                      (f__15923))
                                     (f__15923)))
                                   (f__15923)))
                                 (f__15923)))))
                             (clojure.core/fn
                              [[!xs]]
                              (clojure.core/let
                               [input__14353_nth_1___r___l__
                                (clojure.core/subvec
                                 input__14353_nth_1___r__
                                 0
                                 (clojure.core/min
                                  (clojure.core/count
                                   input__14353_nth_1___r__)
                                  1))]
                               (if
                                (clojure.core/=
                                 (clojure.core/count
                                  input__14353_nth_1___r___l__)
                                 1)
                                (clojure.core/let
                                 [input__14353_nth_1___r___r__
                                  (clojure.core/subvec
                                   input__14353_nth_1___r__
                                   1)]
                                 (if
                                  (clojure.core/=
                                   input__14353_nth_1___r___l__
                                   ['...])
                                  (clojure.core/let
                                   [?rest input__14353_nth_1___r___r__]
                                   (clojure.core/let
                                    [?env input__14353_nth_2__]
                                    (try
                                     [(clojure.core/let
                                       [!xs__counter
                                        (meander.runtime.zeta/iterator
                                         !xs)]
                                       (clojure.core/let
                                        [CATA_RESULT__9238__auto__
                                         (CATA__FN__14430
                                          ['meander.dev.parse.zeta/make-star
                                           (clojure.core/let
                                            [CATA_RESULT__9238__auto__
                                             (CATA__FN__14430
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
                                             (CATA__FN__14430
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
                        (meander.runtime.zeta/fail? result__15920)
                        (recur (clojure.core/next search_space__15919))
                        result__15920))
                      (state__15907))))]
                  (state__15909))
                 (state__15907))
                (state__15907)))
              (state__15907)))
            (state__15907
             []
             (if
              (clojure.core/= (clojure.core/count input__14353) 4)
              (clojure.core/let
               [input__14353_nth_0__
                (clojure.core/nth input__14353 0)
                input__14353_nth_1__
                (clojure.core/nth input__14353 1)
                input__14353_nth_2__
                (clojure.core/nth input__14353 2)]
               (clojure.core/letfn
                [(state__15924
                  []
                  (clojure.core/let
                   [input__14353_nth_3__
                    (clojure.core/nth input__14353 3)]
                   (clojure.core/case
                    input__14353_nth_0__
                    (meander.dev.parse.zeta/make-star)
                    (clojure.core/letfn
                     [(state__15926
                       []
                       (if
                        (clojure.core/map? input__14353_nth_1__)
                        (clojure.core/let
                         [VAL__14598
                          (.valAt input__14353_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__14598
                          (:cat)
                          (clojure.core/let
                           [VAL__14599
                            (.valAt input__14353_nth_1__ :sequence)]
                           (if
                            (clojure.core/vector? VAL__14599)
                            (if
                             (clojure.core/=
                              (clojure.core/count VAL__14599)
                              1)
                             (clojure.core/let
                              [VAL__14599_nth_0__
                               (clojure.core/nth VAL__14599 0)]
                              (if
                               (clojure.core/map? VAL__14599_nth_0__)
                               (clojure.core/let
                                [VAL__14604
                                 (.valAt VAL__14599_nth_0__ :tag)]
                                (clojure.core/case
                                 VAL__14604
                                 (:memory-variable)
                                 (clojure.core/let
                                  [?memory-variable VAL__14599_nth_0__]
                                  (clojure.core/let
                                   [VAL__14600
                                    (.valAt
                                     input__14353_nth_1__
                                     :next)]
                                   (if
                                    (clojure.core/map? VAL__14600)
                                    (clojure.core/let
                                     [VAL__14601
                                      (.valAt VAL__14600 :tag)]
                                     (clojure.core/case
                                      VAL__14601
                                      (:empty)
                                      (clojure.core/let
                                       [?next input__14353_nth_2__]
                                       (clojure.core/let
                                        [?env input__14353_nth_3__]
                                        (try
                                         [(clojure.core/let
                                           [CATA_RESULT__9238__auto__
                                            (CATA__FN__14430
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
                                      (state__15927)))
                                    (state__15927))))
                                 (state__15927)))
                               (state__15927)))
                             (state__15927))
                            (state__15927)))
                          (state__15927)))
                        (state__15927)))
                      (state__15927
                       []
                       (clojure.core/let
                        [?pattern input__14353_nth_1__]
                        (clojure.core/let
                         [?next input__14353_nth_2__]
                         (if
                          (clojure.core/map? input__14353_nth_3__)
                          (clojure.core/let
                           [VAL__14607
                            (.valAt input__14353_nth_3__ :context)]
                           (clojure.core/case
                            VAL__14607
                            (:string)
                            (try
                             [{:tag :string-star,
                               :greedy? false,
                               :pattern ?pattern,
                               :next ?next}]
                             (catch
                              java.lang.Exception
                              e__10178__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__10178__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__10178__auto__))))
                            (state__15925)))
                          (state__15925)))))]
                     (state__15926))
                    (state__15925))))
                 (state__15925
                  []
                  (clojure.core/case
                   input__14353_nth_0__
                   (meander.dev.parse.zeta/make-star)
                   (clojure.core/let
                    [?pattern input__14353_nth_1__]
                    (clojure.core/let
                     [?next input__14353_nth_2__]
                     (try
                      [{:tag :star,
                        :greedy? false,
                        :pattern ?pattern,
                        :next ?next}]
                      (catch
                       java.lang.Exception
                       e__10178__auto__
                       (if
                        (meander.runtime.zeta/fail? e__10178__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__10178__auto__))))))
                   (state__15908)))]
                (state__15924)))
              (state__15908)))
            (state__15908
             []
             (if
              (clojure.core/= (clojure.core/count input__14353) 3)
              (clojure.core/let
               [input__14353_nth_0__
                (clojure.core/nth input__14353 0)
                input__14353_nth_1__
                (clojure.core/nth input__14353 1)
                input__14353_nth_2__
                (clojure.core/nth input__14353 2)]
               (clojure.core/case
                input__14353_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__14353_nth_1__)
                 (clojure.core/let
                  [input__14353_nth_1___l__
                   (clojure.core/subvec
                    input__14353_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__14353_nth_1__)
                     1))]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__14353_nth_1___l__)
                    1)
                   (clojure.core/let
                    [input__14353_nth_1___r__
                     (clojure.core/subvec input__14353_nth_1__ 1)]
                    (clojure.core/let
                     [input__14353_nth_1___l___nth_0__
                      (clojure.core/nth input__14353_nth_1___l__ 0)]
                     (if
                      (clojure.core/symbol?
                       input__14353_nth_1___l___nth_0__)
                      (clojure.core/let
                       [X__14615
                        (clojure.core/namespace
                         input__14353_nth_1___l___nth_0__)]
                       (clojure.core/case
                        X__14615
                        (nil)
                        (clojure.core/let
                         [X__14617
                          (clojure.core/name
                           input__14353_nth_1___l___nth_0__)]
                         (if
                          (clojure.core/string? X__14617)
                          (clojure.core/let
                           [ret__14618
                            (clojure.core/re-matches
                             #"\.\.(\d+)"
                             X__14617)]
                           (if
                            (clojure.core/some? ret__14618)
                            (if
                             (clojure.core/vector? ret__14618)
                             (if
                              (clojure.core/=
                               (clojure.core/count ret__14618)
                               2)
                              (clojure.core/let
                               [ret__14618_nth_1__
                                (clojure.core/nth ret__14618 1)]
                               (clojure.core/let
                                [?n ret__14618_nth_1__]
                                (clojure.core/let
                                 [?operator
                                  input__14353_nth_1___l___nth_0__]
                                 (clojure.core/let
                                  [?rest input__14353_nth_1___r__]
                                  (clojure.core/let
                                   [?env input__14353_nth_2__]
                                   (try
                                    [{:tag :syntax-error,
                                      :message
                                      "The n or more operator ..N must be preceeded by at least one pattern"}]
                                    (catch
                                     java.lang.Exception
                                     e__10178__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__10178__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__10178__auto__)))))))))
                              (state__15853))
                             (state__15853))
                            (state__15853)))
                          (state__15853)))
                        (state__15853)))
                      (state__15853))))
                   (state__15853)))
                 (state__15853))
                (state__15853)))
              (state__15853)))]
           (state__15906))
          (state__15853)))
        (state__15853
         []
         (clojure.core/letfn
          [(def__14621
            [arg__14645]
            (clojure.core/letfn
             [(state__15928
               []
               (clojure.core/let
                [x__14646 :string-plus]
                (clojure.core/let
                 [?tag x__14646]
                 (if
                  (clojure.core/map? arg__14645)
                  (clojure.core/let
                   [VAL__14647 (.valAt arg__14645 :context)]
                   (clojure.core/case
                    VAL__14647
                    (:string)
                    (clojure.core/let [?env arg__14645] [?tag ?env])
                    (state__15929)))
                  (state__15929)))))
              (state__15929
               []
               (clojure.core/let
                [x__14648 :plus]
                (clojure.core/let
                 [?tag x__14648]
                 (clojure.core/let [?env arg__14645] [?tag ?env]))))]
             (state__15928)))]
          (if
           (clojure.core/vector? input__14353)
           (if
            (clojure.core/= (clojure.core/count input__14353) 3)
            (clojure.core/let
             [input__14353_nth_0__
              (clojure.core/nth input__14353 0)
              input__14353_nth_1__
              (clojure.core/nth input__14353 1)
              input__14353_nth_2__
              (clojure.core/nth input__14353 2)]
             (clojure.core/case
              input__14353_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__14353_nth_1__)
               (clojure.core/loop
                [search_space__15930
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__14353_nth_1__)]
                (if
                 (clojure.core/seq search_space__15930)
                 (clojure.core/let
                  [input__14353_nth_1___parts__
                   (clojure.core/first search_space__15930)
                   result__15931
                   (clojure.core/let
                    [input__14353_nth_1___l__
                     (clojure.core/nth input__14353_nth_1___parts__ 0)
                     input__14353_nth_1___r__
                     (clojure.core/nth input__14353_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__8099__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__14353_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__14638]
                         (clojure.core/let
                          [input__14638_nth_0__
                           (clojure.core/nth input__14638 0)]
                          (clojure.core/letfn
                           [(save__14639
                             []
                             (meander.runtime.zeta/fail))
                            (f__15934
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__14638_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol? input__14638_nth_0__)
                            (clojure.core/let
                             [X__14641
                              (clojure.core/namespace
                               input__14638_nth_0__)]
                             (clojure.core/case
                              X__14641
                              (nil)
                              (clojure.core/let
                               [X__14643
                                (clojure.core/name
                                 input__14638_nth_0__)]
                               (if
                                (clojure.core/string? X__14643)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__14643)
                                 (save__14639)
                                 (f__15934))
                                (f__15934)))
                              (f__15934)))
                            (f__15934)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__14353_nth_1___r___l__
                           (clojure.core/subvec
                            input__14353_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__14353_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__14353_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__14353_nth_1___r___r__
                             (clojure.core/subvec
                              input__14353_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__14353_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__14353_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__14353_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__14632
                                (clojure.core/namespace
                                 input__14353_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__14632
                                (nil)
                                (clojure.core/let
                                 [X__14634
                                  (clojure.core/name
                                   input__14353_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__14634)
                                  (clojure.core/let
                                   [ret__14635
                                    (clojure.core/re-matches
                                     #"\.\.(\d+)"
                                     X__14634)]
                                   (if
                                    (clojure.core/some? ret__14635)
                                    (if
                                     (clojure.core/vector? ret__14635)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__14635)
                                       2)
                                      (clojure.core/let
                                       [ret__14635_nth_1__
                                        (clojure.core/nth
                                         ret__14635
                                         1)]
                                       (clojure.core/let
                                        [?n ret__14635_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__14353_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__7935__auto__
                                           (def__14621
                                            input__14353_nth_2__)]
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
                                                  (CATA__FN__14430
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
                                                  (CATA__FN__14430
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
                   (meander.runtime.zeta/fail? result__15931)
                   (recur (clojure.core/next search_space__15930))
                   result__15931))
                 (state__15854)))
               (state__15854))
              (state__15854)))
            (state__15854))
           (state__15854))))
        (state__15854
         []
         (if
          (clojure.core/vector? input__14353)
          (if
           (clojure.core/= (clojure.core/count input__14353) 3)
           (clojure.core/let
            [input__14353_nth_0__
             (clojure.core/nth input__14353 0)
             input__14353_nth_1__
             (clojure.core/nth input__14353 1)
             input__14353_nth_2__
             (clojure.core/nth input__14353 2)]
            (clojure.core/case
             input__14353_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__14353_nth_1__)
              (clojure.core/let
               [input__14353_nth_1___l__
                (clojure.core/subvec
                 input__14353_nth_1__
                 0
                 (clojure.core/min
                  (clojure.core/count input__14353_nth_1__)
                  1))]
               (if
                (clojure.core/=
                 (clojure.core/count input__14353_nth_1___l__)
                 1)
                (clojure.core/let
                 [input__14353_nth_1___r__
                  (clojure.core/subvec input__14353_nth_1__ 1)]
                 (clojure.core/let
                  [input__14353_nth_1___l___nth_0__
                   (clojure.core/nth input__14353_nth_1___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14353_nth_1___l___nth_0__)
                   (clojure.core/let
                    [X__14666
                     (clojure.core/namespace
                      input__14353_nth_1___l___nth_0__)]
                    (clojure.core/case
                     X__14666
                     (nil)
                     (clojure.core/let
                      [X__14668
                       (clojure.core/name
                        input__14353_nth_1___l___nth_0__)]
                      (if
                       (clojure.core/string? X__14668)
                       (clojure.core/let
                        [ret__14669
                         (clojure.core/re-matches
                          #"\.\.(\?.+)"
                          X__14668)]
                        (if
                         (clojure.core/some? ret__14669)
                         (if
                          (clojure.core/vector? ret__14669)
                          (if
                           (clojure.core/=
                            (clojure.core/count ret__14669)
                            2)
                           (clojure.core/let
                            [ret__14669_nth_1__
                             (clojure.core/nth ret__14669 1)]
                            (clojure.core/let
                             [?n ret__14669_nth_1__]
                             (clojure.core/let
                              [?operator
                               input__14353_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?rest input__14353_nth_1___r__]
                               (clojure.core/let
                                [?env input__14353_nth_2__]
                                (try
                                 [{:tag :syntax-error,
                                   :message
                                   "The ?n or more operator ..?n must be preceeded by at least one pattern"}]
                                 (catch
                                  java.lang.Exception
                                  e__10178__auto__
                                  (if
                                   (meander.runtime.zeta/fail?
                                    e__10178__auto__)
                                   (meander.runtime.zeta/fail)
                                   (throw e__10178__auto__)))))))))
                           (state__15855))
                          (state__15855))
                         (state__15855)))
                       (state__15855)))
                     (state__15855)))
                   (state__15855))))
                (state__15855)))
              (state__15855))
             (state__15855)))
           (state__15855))
          (state__15855)))
        (state__15855
         []
         (clojure.core/letfn
          [(def__14672
            [arg__14696]
            (clojure.core/letfn
             [(state__15935
               []
               (clojure.core/let
                [x__14697 :string-logical-plus]
                (clojure.core/let
                 [?tag x__14697]
                 (if
                  (clojure.core/map? arg__14696)
                  (clojure.core/let
                   [VAL__14698 (.valAt arg__14696 :context)]
                   (clojure.core/case
                    VAL__14698
                    (:string)
                    (clojure.core/let [?env arg__14696] [?tag ?env])
                    (state__15936)))
                  (state__15936)))))
              (state__15936
               []
               (clojure.core/let
                [x__14699 :logical-plus]
                (clojure.core/let
                 [?tag x__14699]
                 (clojure.core/let [?env arg__14696] [?tag ?env]))))]
             (state__15935)))]
          (if
           (clojure.core/vector? input__14353)
           (if
            (clojure.core/= (clojure.core/count input__14353) 3)
            (clojure.core/let
             [input__14353_nth_0__
              (clojure.core/nth input__14353 0)
              input__14353_nth_1__
              (clojure.core/nth input__14353 1)
              input__14353_nth_2__
              (clojure.core/nth input__14353 2)]
             (clojure.core/case
              input__14353_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__14353_nth_1__)
               (clojure.core/loop
                [search_space__15937
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__14353_nth_1__)]
                (if
                 (clojure.core/seq search_space__15937)
                 (clojure.core/let
                  [input__14353_nth_1___parts__
                   (clojure.core/first search_space__15937)
                   result__15938
                   (clojure.core/let
                    [input__14353_nth_1___l__
                     (clojure.core/nth input__14353_nth_1___parts__ 0)
                     input__14353_nth_1___r__
                     (clojure.core/nth input__14353_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__8099__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__14353_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__14689]
                         (clojure.core/let
                          [input__14689_nth_0__
                           (clojure.core/nth input__14689 0)]
                          (clojure.core/letfn
                           [(save__14690
                             []
                             (meander.runtime.zeta/fail))
                            (f__15941
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__14689_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol? input__14689_nth_0__)
                            (clojure.core/let
                             [X__14692
                              (clojure.core/namespace
                               input__14689_nth_0__)]
                             (clojure.core/case
                              X__14692
                              (nil)
                              (clojure.core/let
                               [X__14694
                                (clojure.core/name
                                 input__14689_nth_0__)]
                               (if
                                (clojure.core/string? X__14694)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__14694)
                                 (save__14690)
                                 (f__15941))
                                (f__15941)))
                              (f__15941)))
                            (f__15941)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__14353_nth_1___r___l__
                           (clojure.core/subvec
                            input__14353_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__14353_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__14353_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__14353_nth_1___r___r__
                             (clojure.core/subvec
                              input__14353_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__14353_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__14353_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__14353_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__14683
                                (clojure.core/namespace
                                 input__14353_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__14683
                                (nil)
                                (clojure.core/let
                                 [X__14685
                                  (clojure.core/name
                                   input__14353_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__14685)
                                  (clojure.core/let
                                   [ret__14686
                                    (clojure.core/re-matches
                                     #"\.\.(\?.+)"
                                     X__14685)]
                                   (if
                                    (clojure.core/some? ret__14686)
                                    (if
                                     (clojure.core/vector? ret__14686)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__14686)
                                       2)
                                      (clojure.core/let
                                       [ret__14686_nth_1__
                                        (clojure.core/nth
                                         ret__14686
                                         1)]
                                       (clojure.core/let
                                        [?n ret__14686_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__14353_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__7935__auto__
                                           (def__14672
                                            input__14353_nth_2__)]
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
                                                  (CATA__FN__14430
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
                                                  (CATA__FN__14430
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
                   (meander.runtime.zeta/fail? result__15938)
                   (recur (clojure.core/next search_space__15937))
                   result__15938))
                 (state__15856)))
               (state__15856))
              (state__15856)))
            (state__15856))
           (state__15856))))
        (state__15856
         []
         (if
          (clojure.core/vector? input__14353)
          (if
           (clojure.core/= (clojure.core/count input__14353) 3)
           (clojure.core/let
            [input__14353_nth_0__
             (clojure.core/nth input__14353 0)
             input__14353_nth_1__
             (clojure.core/nth input__14353 1)
             input__14353_nth_2__
             (clojure.core/nth input__14353 2)]
            (clojure.core/case
             input__14353_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__14353_nth_1__)
              (clojure.core/let
               [input__14353_nth_1___l__
                (clojure.core/subvec
                 input__14353_nth_1__
                 0
                 (clojure.core/min
                  (clojure.core/count input__14353_nth_1__)
                  1))]
               (if
                (clojure.core/=
                 (clojure.core/count input__14353_nth_1___l__)
                 1)
                (clojure.core/let
                 [input__14353_nth_1___r__
                  (clojure.core/subvec input__14353_nth_1__ 1)]
                 (clojure.core/let
                  [input__14353_nth_1___l___nth_0__
                   (clojure.core/nth input__14353_nth_1___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14353_nth_1___l___nth_0__)
                   (clojure.core/let
                    [X__14717
                     (clojure.core/namespace
                      input__14353_nth_1___l___nth_0__)]
                    (clojure.core/case
                     X__14717
                     (nil)
                     (clojure.core/let
                      [X__14719
                       (clojure.core/name
                        input__14353_nth_1___l___nth_0__)]
                      (if
                       (clojure.core/string? X__14719)
                       (clojure.core/let
                        [ret__14720
                         (clojure.core/re-matches
                          #"\.\.(!.+)"
                          X__14719)]
                        (if
                         (clojure.core/some? ret__14720)
                         (if
                          (clojure.core/vector? ret__14720)
                          (if
                           (clojure.core/=
                            (clojure.core/count ret__14720)
                            2)
                           (clojure.core/let
                            [ret__14720_nth_1__
                             (clojure.core/nth ret__14720 1)]
                            (clojure.core/let
                             [?n ret__14720_nth_1__]
                             (clojure.core/let
                              [?operator
                               input__14353_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?rest input__14353_nth_1___r__]
                               (clojure.core/let
                                [?env input__14353_nth_2__]
                                (try
                                 [{:tag :syntax-error,
                                   :message
                                   "The operator ..!n must be preceeded by at least one pattern"}]
                                 (catch
                                  java.lang.Exception
                                  e__10178__auto__
                                  (if
                                   (meander.runtime.zeta/fail?
                                    e__10178__auto__)
                                   (meander.runtime.zeta/fail)
                                   (throw e__10178__auto__)))))))))
                           (state__15857))
                          (state__15857))
                         (state__15857)))
                       (state__15857)))
                     (state__15857)))
                   (state__15857))))
                (state__15857)))
              (state__15857))
             (state__15857)))
           (state__15857))
          (state__15857)))
        (state__15857
         []
         (clojure.core/letfn
          [(def__14723
            [arg__14747]
            (clojure.core/letfn
             [(state__15942
               []
               (clojure.core/let
                [x__14748 :string-memory-plus]
                (clojure.core/let
                 [?tag x__14748]
                 (if
                  (clojure.core/map? arg__14747)
                  (clojure.core/let
                   [VAL__14749 (.valAt arg__14747 :context)]
                   (clojure.core/case
                    VAL__14749
                    (:string)
                    (clojure.core/let [?env arg__14747] [?tag ?env])
                    (state__15943)))
                  (state__15943)))))
              (state__15943
               []
               (clojure.core/let
                [x__14750 :memory-plus]
                (clojure.core/let
                 [?tag x__14750]
                 (clojure.core/let [?env arg__14747] [?tag ?env]))))]
             (state__15942)))]
          (if
           (clojure.core/vector? input__14353)
           (if
            (clojure.core/= (clojure.core/count input__14353) 3)
            (clojure.core/let
             [input__14353_nth_0__
              (clojure.core/nth input__14353 0)
              input__14353_nth_1__
              (clojure.core/nth input__14353 1)
              input__14353_nth_2__
              (clojure.core/nth input__14353 2)]
             (clojure.core/case
              input__14353_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__14353_nth_1__)
               (clojure.core/loop
                [search_space__15944
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__14353_nth_1__)]
                (if
                 (clojure.core/seq search_space__15944)
                 (clojure.core/let
                  [input__14353_nth_1___parts__
                   (clojure.core/first search_space__15944)
                   result__15945
                   (clojure.core/let
                    [input__14353_nth_1___l__
                     (clojure.core/nth input__14353_nth_1___parts__ 0)
                     input__14353_nth_1___r__
                     (clojure.core/nth input__14353_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__8099__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__14353_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__14740]
                         (clojure.core/let
                          [input__14740_nth_0__
                           (clojure.core/nth input__14740 0)]
                          (clojure.core/letfn
                           [(save__14741
                             []
                             (meander.runtime.zeta/fail))
                            (f__15948
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__14740_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol? input__14740_nth_0__)
                            (clojure.core/let
                             [X__14743
                              (clojure.core/namespace
                               input__14740_nth_0__)]
                             (clojure.core/case
                              X__14743
                              (nil)
                              (clojure.core/let
                               [X__14745
                                (clojure.core/name
                                 input__14740_nth_0__)]
                               (if
                                (clojure.core/string? X__14745)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__14745)
                                 (save__14741)
                                 (f__15948))
                                (f__15948)))
                              (f__15948)))
                            (f__15948)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__14353_nth_1___r___l__
                           (clojure.core/subvec
                            input__14353_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__14353_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__14353_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__14353_nth_1___r___r__
                             (clojure.core/subvec
                              input__14353_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__14353_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__14353_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__14353_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__14734
                                (clojure.core/namespace
                                 input__14353_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__14734
                                (nil)
                                (clojure.core/let
                                 [X__14736
                                  (clojure.core/name
                                   input__14353_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__14736)
                                  (clojure.core/let
                                   [ret__14737
                                    (clojure.core/re-matches
                                     #"\.\.(\!.+)"
                                     X__14736)]
                                   (if
                                    (clojure.core/some? ret__14737)
                                    (if
                                     (clojure.core/vector? ret__14737)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__14737)
                                       2)
                                      (clojure.core/let
                                       [ret__14737_nth_1__
                                        (clojure.core/nth
                                         ret__14737
                                         1)]
                                       (clojure.core/let
                                        [?n ret__14737_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__14353_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__7935__auto__
                                           (def__14723
                                            input__14353_nth_2__)]
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
                                                  (CATA__FN__14430
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
                                                  (CATA__FN__14430
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
                   (meander.runtime.zeta/fail? result__15945)
                   (recur (clojure.core/next search_space__15944))
                   result__15945))
                 (state__15858)))
               (state__15858))
              (state__15858)))
            (state__15858))
           (state__15858))))
        (state__15858
         []
         (if
          (clojure.core/vector? input__14353)
          (clojure.core/letfn
           [(state__15949
             []
             (if
              (clojure.core/= (clojure.core/count input__14353) 3)
              (clojure.core/let
               [input__14353_nth_0__
                (clojure.core/nth input__14353 0)
                input__14353_nth_1__
                (clojure.core/nth input__14353 1)
                input__14353_nth_2__
                (clojure.core/nth input__14353 2)]
               (clojure.core/case
                input__14353_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__14353_nth_1__)
                 (clojure.core/let
                  [!xs (clojure.core/vec input__14353_nth_1__)]
                  (clojure.core/let
                   [?env input__14353_nth_2__]
                   (try
                    [(clojure.core/let
                      [!xs__counter
                       (meander.runtime.zeta/iterator !xs)]
                      (clojure.core/let
                       [CATA_RESULT__9238__auto__
                        (CATA__FN__14430
                         ['meander.dev.parse.zeta/make-cat
                          (clojure.core/into
                           []
                           (clojure.core/loop
                            [return__14431 (clojure.core/transient [])]
                            (if
                             (clojure.core/and (.hasNext !xs__counter))
                             (recur
                              (clojure.core/conj!
                               return__14431
                               (clojure.core/let
                                [CATA_RESULT__9238__auto__
                                 (CATA__FN__14430
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
                              return__14431))))
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
                 (state__15950))
                (state__15950)))
              (state__15950)))
            (state__15950
             []
             (if
              (clojure.core/= (clojure.core/count input__14353) 4)
              (clojure.core/let
               [input__14353_nth_0__
                (clojure.core/nth input__14353 0)
                input__14353_nth_1__
                (clojure.core/nth input__14353 1)
                input__14353_nth_2__
                (clojure.core/nth input__14353 2)]
               (clojure.core/letfn
                [(state__15952
                  []
                  (clojure.core/let
                   [input__14353_nth_3__
                    (clojure.core/nth input__14353 3)]
                   (clojure.core/case
                    input__14353_nth_0__
                    (meander.dev.parse.zeta/make-cat)
                    (clojure.core/letfn
                     [(state__15957
                       []
                       (if
                        (clojure.core/vector? input__14353_nth_1__)
                        (clojure.core/letfn
                         [(state__15959
                           []
                           (clojure.core/case
                            input__14353_nth_1__
                            ([])
                            (clojure.core/let
                             [?next input__14353_nth_2__]
                             (clojure.core/let
                              [?env input__14353_nth_3__]
                              (try
                               [?next]
                               (catch
                                java.lang.Exception
                                e__10178__auto__
                                (if
                                 (meander.runtime.zeta/fail?
                                  e__10178__auto__)
                                 (meander.runtime.zeta/fail)
                                 (throw e__10178__auto__))))))
                            (state__15960)))
                          (state__15960
                           []
                           (clojure.core/loop
                            [search_space__15962
                             (meander.runtime.zeta/epsilon-partitions
                              2
                              input__14353_nth_1__)]
                            (if
                             (clojure.core/seq search_space__15962)
                             (clojure.core/let
                              [input__14353_nth_1___parts__
                               (clojure.core/first search_space__15962)
                               result__15963
                               (clojure.core/let
                                [input__14353_nth_1___l__
                                 (clojure.core/nth
                                  input__14353_nth_1___parts__
                                  0)
                                 input__14353_nth_1___r__
                                 (clojure.core/nth
                                  input__14353_nth_1___parts__
                                  1)]
                                (clojure.core/letfn
                                 [(state__15965
                                   []
                                   (clojure.core/let
                                    [!xs []]
                                    (clojure.core/let
                                     [ret__8099__auto__
                                      (meander.runtime.zeta/epsilon-run-star-1
                                       input__14353_nth_1___l__
                                       [!xs]
                                       (clojure.core/fn
                                        [[!xs] input__14776]
                                        (clojure.core/let
                                         [input__14776_nth_0__
                                          (clojure.core/nth
                                           input__14776
                                           0)]
                                         (clojure.core/letfn
                                          [(save__14777
                                            []
                                            (meander.runtime.zeta/fail))
                                           (f__15969
                                            []
                                            (clojure.core/let
                                             [!xs
                                              (clojure.core/conj
                                               !xs
                                               input__14776_nth_0__)]
                                             [!xs]))]
                                          (if
                                           (clojure.core/map?
                                            input__14776_nth_0__)
                                           (clojure.core/let
                                            [VAL__14778
                                             (.valAt
                                              input__14776_nth_0__
                                              :tag)]
                                            (clojure.core/case
                                             VAL__14778
                                             (:group)
                                             (save__14777)
                                             (f__15969)))
                                           (f__15969)))))
                                       (clojure.core/fn
                                        [[!xs]]
                                        (clojure.core/let
                                         [input__14353_nth_1___r___l__
                                          (clojure.core/subvec
                                           input__14353_nth_1___r__
                                           0
                                           (clojure.core/min
                                            (clojure.core/count
                                             input__14353_nth_1___r__)
                                            1))]
                                         (if
                                          (clojure.core/=
                                           (clojure.core/count
                                            input__14353_nth_1___r___l__)
                                           1)
                                          (clojure.core/let
                                           [input__14353_nth_1___r___r__
                                            (clojure.core/subvec
                                             input__14353_nth_1___r__
                                             1)]
                                           (clojure.core/let
                                            [input__14353_nth_1___r___l___nth_0__
                                             (clojure.core/nth
                                              input__14353_nth_1___r___l__
                                              0)]
                                            (if
                                             (clojure.core/map?
                                              input__14353_nth_1___r___l___nth_0__)
                                             (clojure.core/let
                                              [VAL__14775
                                               (.valAt
                                                input__14353_nth_1___r___l___nth_0__
                                                :tag)]
                                              (clojure.core/case
                                               VAL__14775
                                               (:group)
                                               (clojure.core/let
                                                [?group
                                                 input__14353_nth_1___r___l___nth_0__]
                                                (clojure.core/let
                                                 [?rest
                                                  input__14353_nth_1___r___r__]
                                                 (clojure.core/let
                                                  [?next
                                                   input__14353_nth_2__]
                                                  (clojure.core/let
                                                   [?env
                                                    input__14353_nth_3__]
                                                   (try
                                                    [(clojure.core/let
                                                      [!xs__counter
                                                       (meander.runtime.zeta/iterator
                                                        !xs)]
                                                      (clojure.core/let
                                                       [CATA_RESULT__9238__auto__
                                                        (CATA__FN__14430
                                                         ['meander.dev.parse.zeta/make-join
                                                          (clojure.core/let
                                                           [CATA_RESULT__9238__auto__
                                                            (CATA__FN__14430
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
                                                            (CATA__FN__14430
                                                             ['meander.dev.parse.zeta/make-join
                                                              ?group
                                                              (clojure.core/let
                                                               [CATA_RESULT__9238__auto__
                                                                (CATA__FN__14430
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
                                               (state__15966)))
                                             (state__15966))))
                                          (state__15966)))))]
                                     (if
                                      (meander.runtime.zeta/fail?
                                       ret__8099__auto__)
                                      (state__15966)
                                      ret__8099__auto__))))
                                  (state__15966
                                   []
                                   (clojure.core/let
                                    [!xs []]
                                    (clojure.core/let
                                     [ret__8099__auto__
                                      (meander.runtime.zeta/epsilon-run-star-1
                                       input__14353_nth_1___l__
                                       [!xs]
                                       (clojure.core/fn
                                        [[!xs] input__14787]
                                        (clojure.core/let
                                         [input__14787_nth_0__
                                          (clojure.core/nth
                                           input__14787
                                           0)]
                                         (clojure.core/letfn
                                          [(save__14788
                                            []
                                            (meander.runtime.zeta/fail))
                                           (f__15971
                                            []
                                            (clojure.core/let
                                             [!xs
                                              (clojure.core/conj
                                               !xs
                                               input__14787_nth_0__)]
                                             [!xs]))]
                                          (if
                                           (clojure.core/map?
                                            input__14787_nth_0__)
                                           (clojure.core/let
                                            [VAL__14789
                                             (.valAt
                                              input__14787_nth_0__
                                              :tag)]
                                            (clojure.core/case
                                             VAL__14789
                                             (:star)
                                             (save__14788)
                                             (f__15971)))
                                           (f__15971)))))
                                       (clojure.core/fn
                                        [[!xs]]
                                        (clojure.core/let
                                         [input__14353_nth_1___r___l__
                                          (clojure.core/subvec
                                           input__14353_nth_1___r__
                                           0
                                           (clojure.core/min
                                            (clojure.core/count
                                             input__14353_nth_1___r__)
                                            1))]
                                         (if
                                          (clojure.core/=
                                           (clojure.core/count
                                            input__14353_nth_1___r___l__)
                                           1)
                                          (clojure.core/let
                                           [input__14353_nth_1___r___r__
                                            (clojure.core/subvec
                                             input__14353_nth_1___r__
                                             1)]
                                           (clojure.core/let
                                            [input__14353_nth_1___r___l___nth_0__
                                             (clojure.core/nth
                                              input__14353_nth_1___r___l__
                                              0)]
                                            (if
                                             (clojure.core/map?
                                              input__14353_nth_1___r___l___nth_0__)
                                             (clojure.core/let
                                              [VAL__14786
                                               (.valAt
                                                input__14353_nth_1___r___l___nth_0__
                                                :tag)]
                                              (clojure.core/case
                                               VAL__14786
                                               (:star)
                                               (clojure.core/let
                                                [?star
                                                 input__14353_nth_1___r___l___nth_0__]
                                                (clojure.core/let
                                                 [?rest
                                                  input__14353_nth_1___r___r__]
                                                 (clojure.core/let
                                                  [?next
                                                   input__14353_nth_2__]
                                                  (clojure.core/let
                                                   [?env
                                                    input__14353_nth_3__]
                                                   (try
                                                    [(clojure.core/let
                                                      [!xs__counter
                                                       (meander.runtime.zeta/iterator
                                                        !xs)]
                                                      (clojure.core/let
                                                       [CATA_RESULT__9238__auto__
                                                        (CATA__FN__14430
                                                         ['meander.dev.parse.zeta/make-join
                                                          (clojure.core/let
                                                           [CATA_RESULT__9238__auto__
                                                            (CATA__FN__14430
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
                                                            (CATA__FN__14430
                                                             ['meander.dev.parse.zeta/make-join
                                                              ?star
                                                              (clojure.core/let
                                                               [CATA_RESULT__9238__auto__
                                                                (CATA__FN__14430
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
                                               (state__15967)))
                                             (state__15967))))
                                          (state__15967)))))]
                                     (if
                                      (meander.runtime.zeta/fail?
                                       ret__8099__auto__)
                                      (state__15967)
                                      ret__8099__auto__))))
                                  (state__15967
                                   []
                                   (clojure.core/let
                                    [input__14353_nth_1___l___l__
                                     (clojure.core/subvec
                                      input__14353_nth_1___l__
                                      0
                                      (clojure.core/min
                                       (clojure.core/count
                                        input__14353_nth_1___l__)
                                       1))]
                                    (if
                                     (clojure.core/=
                                      (clojure.core/count
                                       input__14353_nth_1___l___l__)
                                      1)
                                     (clojure.core/let
                                      [input__14353_nth_1___l___r__
                                       (clojure.core/subvec
                                        input__14353_nth_1___l__
                                        1)]
                                      (clojure.core/let
                                       [input__14353_nth_1___l___l___nth_0__
                                        (clojure.core/nth
                                         input__14353_nth_1___l___l__
                                         0)]
                                       (clojure.core/letfn
                                        [(save__14797
                                          []
                                          (meander.runtime.zeta/fail))
                                         (f__15972
                                          []
                                          (clojure.core/let
                                           [!xs []]
                                           (clojure.core/let
                                            [!xs
                                             (clojure.core/conj
                                              !xs
                                              input__14353_nth_1___l___l___nth_0__)]
                                            (clojure.core/loop
                                             [i__8072__auto__
                                              0
                                              coll__15973
                                              input__14353_nth_1___l___r__
                                              [!xs]
                                              [!xs]]
                                             (clojure.core/let
                                              [input__14802
                                               (clojure.core/subvec
                                                coll__15973
                                                0
                                                (clojure.core/min
                                                 (clojure.core/count
                                                  coll__15973)
                                                 1))]
                                              (if
                                               (clojure.core/=
                                                (clojure.core/count
                                                 input__14802)
                                                1)
                                               (clojure.core/let
                                                [result__8073__auto__
                                                 (clojure.core/let
                                                  [input__14802_nth_0__
                                                   (clojure.core/nth
                                                    input__14802
                                                    0)]
                                                  (clojure.core/letfn
                                                   [(save__14803
                                                     []
                                                     (meander.runtime.zeta/fail))
                                                    (f__15974
                                                     []
                                                     (clojure.core/let
                                                      [!xs
                                                       (clojure.core/conj
                                                        !xs
                                                        input__14802_nth_0__)]
                                                      [!xs]))]
                                                   (if
                                                    (clojure.core/map?
                                                     input__14802_nth_0__)
                                                    (clojure.core/let
                                                     [VAL__14804
                                                      (.valAt
                                                       input__14802_nth_0__
                                                       :tag)]
                                                     (clojure.core/case
                                                      VAL__14804
                                                      (:reference)
                                                      (save__14803)
                                                      (f__15974)))
                                                    (f__15974))))]
                                                (if
                                                 (meander.runtime.zeta/fail?
                                                  result__8073__auto__)
                                                 (meander.runtime.zeta/fail)
                                                 (recur
                                                  (clojure.core/inc
                                                   i__8072__auto__)
                                                  (clojure.core/subvec
                                                   coll__15973
                                                   1)
                                                  result__8073__auto__)))
                                               (if
                                                (clojure.core/or
                                                 (clojure.core/seq
                                                  coll__15973)
                                                 (clojure.core/<
                                                  i__8072__auto__
                                                  0))
                                                (meander.runtime.zeta/fail)
                                                (clojure.core/let
                                                 [input__14353_nth_1___r___l__
                                                  (clojure.core/subvec
                                                   input__14353_nth_1___r__
                                                   0
                                                   (clojure.core/min
                                                    (clojure.core/count
                                                     input__14353_nth_1___r__)
                                                    1))]
                                                 (if
                                                  (clojure.core/=
                                                   (clojure.core/count
                                                    input__14353_nth_1___r___l__)
                                                   1)
                                                  (clojure.core/let
                                                   [input__14353_nth_1___r___r__
                                                    (clojure.core/subvec
                                                     input__14353_nth_1___r__
                                                     1)]
                                                   (clojure.core/let
                                                    [input__14353_nth_1___r___l___nth_0__
                                                     (clojure.core/nth
                                                      input__14353_nth_1___r___l__
                                                      0)]
                                                    (if
                                                     (clojure.core/map?
                                                      input__14353_nth_1___r___l___nth_0__)
                                                     (clojure.core/let
                                                      [VAL__14801
                                                       (.valAt
                                                        input__14353_nth_1___r___l___nth_0__
                                                        :tag)]
                                                      (clojure.core/case
                                                       VAL__14801
                                                       (:reference)
                                                       (clojure.core/let
                                                        [?reference
                                                         input__14353_nth_1___r___l___nth_0__]
                                                        (clojure.core/let
                                                         [?rest
                                                          input__14353_nth_1___r___r__]
                                                         (clojure.core/let
                                                          [?next
                                                           input__14353_nth_2__]
                                                          (clojure.core/let
                                                           [?env
                                                            input__14353_nth_3__]
                                                           (try
                                                            [(clojure.core/let
                                                              [!xs__counter
                                                               (meander.runtime.zeta/iterator
                                                                !xs)]
                                                              (clojure.core/let
                                                               [CATA_RESULT__9238__auto__
                                                                (CATA__FN__14430
                                                                 ['meander.dev.parse.zeta/make-join
                                                                  (clojure.core/let
                                                                   [CATA_RESULT__9238__auto__
                                                                    (CATA__FN__14430
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
                                                                    (CATA__FN__14430
                                                                     ['meander.dev.parse.zeta/make-join
                                                                      (clojure.core/let
                                                                       [CATA_RESULT__9238__auto__
                                                                        (CATA__FN__14430
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
                                                                        (CATA__FN__14430
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
                                                       (meander.runtime.zeta/fail)))
                                                     (meander.runtime.zeta/fail))))
                                                  (meander.runtime.zeta/fail))))))))))]
                                        (if
                                         (clojure.core/map?
                                          input__14353_nth_1___l___l___nth_0__)
                                         (clojure.core/let
                                          [VAL__14798
                                           (.valAt
                                            input__14353_nth_1___l___l___nth_0__
                                            :tag)]
                                          (clojure.core/case
                                           VAL__14798
                                           (:reference)
                                           (save__14797)
                                           (f__15972)))
                                         (f__15972)))))
                                     (meander.runtime.zeta/fail))))]
                                 (state__15965)))]
                              (if
                               (meander.runtime.zeta/fail?
                                result__15963)
                               (recur
                                (clojure.core/next
                                 search_space__15962))
                               result__15963))
                             (state__15961))))
                          (state__15961
                           []
                           (clojure.core/let
                            [input__14353_nth_1___l__
                             (clojure.core/subvec
                              input__14353_nth_1__
                              0
                              (clojure.core/min
                               (clojure.core/count
                                input__14353_nth_1__)
                               1))]
                            (if
                             (clojure.core/=
                              (clojure.core/count
                               input__14353_nth_1___l__)
                              1)
                             (clojure.core/let
                              [input__14353_nth_1___r__
                               (clojure.core/subvec
                                input__14353_nth_1__
                                1)]
                              (clojure.core/let
                               [input__14353_nth_1___l___nth_0__
                                (clojure.core/nth
                                 input__14353_nth_1___l__
                                 0)]
                               (if
                                (clojure.core/map?
                                 input__14353_nth_1___l___nth_0__)
                                (clojure.core/let
                                 [VAL__14810
                                  (.valAt
                                   input__14353_nth_1___l___nth_0__
                                   :tag)]
                                 (clojure.core/case
                                  VAL__14810
                                  (:literal)
                                  (clojure.core/let
                                   [VAL__14811
                                    (.valAt
                                     input__14353_nth_1___l___nth_0__
                                     :type)]
                                   (clojure.core/case
                                    VAL__14811
                                    (:string)
                                    (clojure.core/let
                                     [?ast
                                      input__14353_nth_1___l___nth_0__]
                                     (clojure.core/let
                                      [?rest input__14353_nth_1___r__]
                                      (clojure.core/let
                                       [?next input__14353_nth_2__]
                                       (clojure.core/let
                                        [?env input__14353_nth_3__]
                                        (try
                                         [(clojure.core/let
                                           [CATA_RESULT__9238__auto__
                                            (CATA__FN__14430
                                             ['meander.dev.parse.zeta/make-join
                                              ?ast
                                              (clojure.core/let
                                               [CATA_RESULT__9238__auto__
                                                (CATA__FN__14430
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
                                    (state__15958)))
                                  (state__15958)))
                                (state__15958))))
                             (state__15958))))]
                         (state__15959))
                        (state__15958)))
                      (state__15958
                       []
                       (clojure.core/let
                        [?sequence input__14353_nth_1__]
                        (clojure.core/let
                         [?next input__14353_nth_2__]
                         (if
                          (clojure.core/map? input__14353_nth_3__)
                          (clojure.core/let
                           [VAL__14814
                            (.valAt input__14353_nth_3__ :context)]
                           (clojure.core/case
                            VAL__14814
                            (:string)
                            (try
                             [{:tag :string-cat,
                               :sequence ?sequence,
                               :next ?next}]
                             (catch
                              java.lang.Exception
                              e__10178__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__10178__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__10178__auto__))))
                            (state__15953)))
                          (state__15953)))))]
                     (state__15957))
                    (state__15953))))
                 (state__15953
                  []
                  (clojure.core/case
                   input__14353_nth_0__
                   (meander.dev.parse.zeta/make-cat)
                   (clojure.core/let
                    [?sequence input__14353_nth_1__]
                    (clojure.core/let
                     [?next input__14353_nth_2__]
                     (try
                      [{:tag :cat, :sequence ?sequence, :next ?next}]
                      (catch
                       java.lang.Exception
                       e__10178__auto__
                       (if
                        (meander.runtime.zeta/fail? e__10178__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__10178__auto__))))))
                   (state__15954)))
                 (state__15954
                  []
                  (clojure.core/let
                   [input__14353_nth_3__
                    (clojure.core/nth input__14353 3)]
                   (clojure.core/case
                    input__14353_nth_0__
                    (meander.dev.parse.zeta/make-join)
                    (if
                     (clojure.core/map? input__14353_nth_1__)
                     (clojure.core/let
                      [VAL__14819 (.valAt input__14353_nth_1__ :tag)]
                      (clojure.core/case
                       VAL__14819
                       (:cat)
                       (clojure.core/let
                        [VAL__14820
                         (.valAt input__14353_nth_1__ :sequence)]
                        (clojure.core/let
                         [?sequence VAL__14820]
                         (clojure.core/let
                          [VAL__14821
                           (.valAt input__14353_nth_1__ :next)]
                          (if
                           (clojure.core/map? VAL__14821)
                           (clojure.core/let
                            [VAL__14822 (.valAt VAL__14821 :tag)]
                            (clojure.core/case
                             VAL__14822
                             (:empty)
                             (clojure.core/let
                              [?right input__14353_nth_2__]
                              (clojure.core/let
                               [?env input__14353_nth_3__]
                               (try
                                [(clojure.core/let
                                  [CATA_RESULT__9238__auto__
                                   (CATA__FN__14430
                                    ['meander.dev.parse.zeta/make-cat
                                     ?sequence
                                     ?right
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
                             (state__15955)))
                           (state__15955)))))
                       (state__15955)))
                     (state__15955))
                    (state__15955))))
                 (state__15955
                  []
                  (clojure.core/case
                   input__14353_nth_0__
                   (meander.dev.parse.zeta/make-join)
                   (if
                    (clojure.core/map? input__14353_nth_1__)
                    (clojure.core/let
                     [VAL__15845 (.valAt input__14353_nth_1__ :tag)]
                     (clojure.core/case
                      VAL__15845
                      (:cat)
                      (clojure.core/let
                       [?ast input__14353_nth_1__]
                       (if
                        (clojure.core/map? input__14353_nth_2__)
                        (clojure.core/let
                         [VAL__14826
                          (.valAt input__14353_nth_2__ :tag)]
                         (clojure.core/case
                          VAL__14826
                          (:cat)
                          (clojure.core/let
                           [VAL__14827
                            (.valAt input__14353_nth_2__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__14827]
                            (clojure.core/let
                             [VAL__14828
                              (.valAt input__14353_nth_2__ :next)]
                             (clojure.core/let
                              [?next VAL__14828]
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
                                e__10178__auto__
                                (if
                                 (meander.runtime.zeta/fail?
                                  e__10178__auto__)
                                 (meander.runtime.zeta/fail)
                                 (throw e__10178__auto__))))))))
                          (state__15956)))
                        (state__15956)))
                      (:string-cat)
                      (clojure.core/let
                       [?ast input__14353_nth_1__]
                       (if
                        (clojure.core/map? input__14353_nth_2__)
                        (clojure.core/let
                         [VAL__14832
                          (.valAt input__14353_nth_2__ :tag)]
                         (clojure.core/case
                          VAL__14832
                          (:string-cat)
                          (clojure.core/let
                           [VAL__14833
                            (.valAt input__14353_nth_2__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__14833]
                            (clojure.core/let
                             [VAL__14834
                              (.valAt input__14353_nth_2__ :next)]
                             (clojure.core/let
                              [?next VAL__14834]
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
                                e__10178__auto__
                                (if
                                 (meander.runtime.zeta/fail?
                                  e__10178__auto__)
                                 (meander.runtime.zeta/fail)
                                 (throw e__10178__auto__))))))))
                          (state__15956)))
                        (state__15956)))
                      (state__15956)))
                    (state__15956))
                   (state__15956)))
                 (state__15956
                  []
                  (clojure.core/let
                   [input__14353_nth_3__
                    (clojure.core/nth input__14353 3)]
                   (clojure.core/case
                    input__14353_nth_0__
                    (meander.dev.parse.zeta/make-join)
                    (clojure.core/letfn
                     [(state__15975
                       []
                       (if
                        (clojure.core/map? input__14353_nth_1__)
                        (clojure.core/let
                         [VAL__15848
                          (.valAt input__14353_nth_1__ :next)
                          VAL__15847
                          (.valAt input__14353_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__15847
                          (:string-cat)
                          (clojure.core/let
                           [VAL__14838
                            (.valAt input__14353_nth_1__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__14838]
                            (if
                             (clojure.core/map? VAL__15848)
                             (clojure.core/let
                              [VAL__14840 (.valAt VAL__15848 :tag)]
                              (clojure.core/case
                               VAL__14840
                               (:empty)
                               (clojure.core/let
                                [?right input__14353_nth_2__]
                                (clojure.core/let
                                 [?env input__14353_nth_3__]
                                 (try
                                  [(clojure.core/let
                                    [CATA_RESULT__9238__auto__
                                     (CATA__FN__14430
                                      ['meander.dev.parse.zeta/make-join
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
                               (state__15976)))
                             (state__15976))))
                          (:string-star)
                          (clojure.core/let
                           [VAL__14844
                            (.valAt input__14353_nth_1__ :pattern)]
                           (clojure.core/let
                            [?pattern VAL__14844]
                            (if
                             (clojure.core/map? VAL__15848)
                             (clojure.core/let
                              [VAL__14846 (.valAt VAL__15848 :tag)]
                              (clojure.core/case
                               VAL__14846
                               (:empty)
                               (clojure.core/let
                                [?right input__14353_nth_2__]
                                (if
                                 (clojure.core/map?
                                  input__14353_nth_3__)
                                 (clojure.core/let
                                  [VAL__14847
                                   (.valAt
                                    input__14353_nth_3__
                                    :context)]
                                  (clojure.core/case
                                   VAL__14847
                                   (:string)
                                   (try
                                    [{:tag :string-star,
                                      :pattern ?pattern,
                                      :next ?right}]
                                    (catch
                                     java.lang.Exception
                                     e__10178__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__10178__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__10178__auto__))))
                                   (state__15976)))
                                 (state__15976)))
                               (state__15976)))
                             (state__15976))))
                          (:string-join)
                          (clojure.core/let
                           [VAL__14851
                            (.valAt input__14353_nth_1__ :left)]
                           (clojure.core/let
                            [?left VAL__14851]
                            (clojure.core/let
                             [VAL__14852
                              (.valAt input__14353_nth_1__ :right)]
                             (clojure.core/let
                              [?right-1 VAL__14852]
                              (clojure.core/let
                               [?right-2 input__14353_nth_2__]
                               (if
                                (clojure.core/map?
                                 input__14353_nth_3__)
                                (clojure.core/let
                                 [VAL__14853
                                  (.valAt
                                   input__14353_nth_3__
                                   :context)]
                                 (clojure.core/case
                                  VAL__14853
                                  (:string)
                                  (clojure.core/let
                                   [?env input__14353_nth_3__]
                                   (try
                                    [{:tag :string-join,
                                      :left ?left,
                                      :right
                                      (clojure.core/let
                                       [CATA_RESULT__9238__auto__
                                        (CATA__FN__14430
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
                                  (state__15976)))
                                (state__15976)))))))
                          (state__15976)))
                        (state__15976)))
                      (state__15976
                       []
                       (clojure.core/let
                        [?left input__14353_nth_1__]
                        (if
                         (clojure.core/map? input__14353_nth_2__)
                         (clojure.core/let
                          [VAL__14856
                           (.valAt input__14353_nth_2__ :tag)]
                          (clojure.core/case
                           VAL__14856
                           (:empty)
                           (clojure.core/let
                            [?env input__14353_nth_3__]
                            (try
                             [?left]
                             (catch
                              java.lang.Exception
                              e__10178__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__10178__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__10178__auto__)))))
                           (state__15977)))
                         (state__15977))))
                      (state__15977
                       []
                       (if
                        (clojure.core/map? input__14353_nth_1__)
                        (clojure.core/let
                         [VAL__15846
                          (.valAt input__14353_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__15846
                          (:empty)
                          (clojure.core/let
                           [?right input__14353_nth_2__]
                           (clojure.core/let
                            [?env input__14353_nth_3__]
                            (try
                             [?right]
                             (catch
                              java.lang.Exception
                              e__10178__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__10178__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__10178__auto__))))))
                          (:star)
                          (clojure.core/let
                           [VAL__14863
                            (.valAt input__14353_nth_1__ :next)]
                           (if
                            (clojure.core/map? VAL__14863)
                            (clojure.core/let
                             [VAL__14864 (.valAt VAL__14863 :tag)]
                             (clojure.core/case
                              VAL__14864
                              (:empty)
                              (clojure.core/let
                               [?left input__14353_nth_1__]
                               (clojure.core/let
                                [?right input__14353_nth_2__]
                                (clojure.core/let
                                 [?env input__14353_nth_3__]
                                 (try
                                  [(clojure.core/let
                                    [form__9337__auto__
                                     {:tag :star, :next ?right}]
                                    (clojure.core/merge
                                     (clojure.core/into {} ?left)
                                     form__9337__auto__))]
                                  (catch
                                   java.lang.Exception
                                   e__10178__auto__
                                   (if
                                    (meander.runtime.zeta/fail?
                                     e__10178__auto__)
                                    (meander.runtime.zeta/fail)
                                    (throw e__10178__auto__)))))))
                              (state__15978)))
                            (state__15978)))
                          (state__15978)))
                        (state__15978)))
                      (state__15978
                       []
                       (clojure.core/let
                        [?left input__14353_nth_1__]
                        (clojure.core/let
                         [?right input__14353_nth_2__]
                         (clojure.core/letfn
                          [(state__15979
                            []
                            (if
                             (clojure.core/map? input__14353_nth_3__)
                             (clojure.core/let
                              [VAL__14867
                               (.valAt input__14353_nth_3__ :context)]
                              (clojure.core/case
                               VAL__14867
                               (:string)
                               (try
                                [{:tag :string-join,
                                  :left ?left,
                                  :right ?right}]
                                (catch
                                 java.lang.Exception
                                 e__10178__auto__
                                 (if
                                  (meander.runtime.zeta/fail?
                                   e__10178__auto__)
                                  (meander.runtime.zeta/fail)
                                  (throw e__10178__auto__))))
                               (state__15980)))
                             (state__15980)))
                           (state__15980
                            []
                            (clojure.core/let
                             [?env input__14353_nth_3__]
                             (try
                              [{:tag :join,
                                :left ?left,
                                :right ?right}]
                              (catch
                               java.lang.Exception
                               e__10178__auto__
                               (if
                                (meander.runtime.zeta/fail?
                                 e__10178__auto__)
                                (meander.runtime.zeta/fail)
                                (throw e__10178__auto__))))))]
                          (state__15979)))))]
                     (state__15975))
                    (state__15951))))]
                (state__15952)))
              (state__15951)))
            (state__15951
             []
             (if
              (clojure.core/= (clojure.core/count input__14353) 3)
              (clojure.core/let
               [input__14353_nth_0__
                (clojure.core/nth input__14353 0)
                input__14353_nth_1__
                (clojure.core/nth input__14353 1)
                input__14353_nth_2__
                (clojure.core/nth input__14353 2)]
               (clojure.core/case
                input__14353_nth_0__
                (meander.dev.parse.zeta/parse-entries)
                (if
                 (clojure.core/map? input__14353_nth_1__)
                 (clojure.core/let
                  [VAL__14872
                   (.valAt input__14353_nth_1__ :meander.zeta/as)]
                  (clojure.core/let
                   [?pattern VAL__14872]
                   (clojure.core/let
                    [X__14874
                     ((clojure.core/fn
                       [m__7002__auto__]
                       (clojure.core/dissoc
                        m__7002__auto__
                        :meander.zeta/as))
                      input__14353_nth_1__)]
                    (clojure.core/let
                     [?rest X__14874]
                     (clojure.core/letfn
                      [(save__14875 [] (state__15859))
                       (f__15981
                        []
                        (clojure.core/let
                         [?env input__14353_nth_2__]
                         (try
                          [{:tag :as,
                            :pattern
                            (clojure.core/let
                             [CATA_RESULT__9238__auto__
                              (CATA__FN__14430 [?pattern ?env])]
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
                              (CATA__FN__14430 [?rest ?env])]
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
                       (clojure.core/= ?rest input__14353_nth_1__)
                       (save__14875)
                       (f__15981)))))))
                 (state__15859))
                (state__15859)))
              (state__15859)))]
           (state__15949))
          (state__15859)))
        (state__15859
         []
         (clojure.core/letfn
          [(def__14878
            [arg__14911 ?ns]
            (clojure.core/letfn
             [(state__15982
               []
               (clojure.core/let
                [x__14912 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__14912)
                 (clojure.core/let [?env arg__14911] [?env ?ns])
                 (state__15983))))
              (state__15983
               []
               (if
                (clojure.core/map? arg__14911)
                (clojure.core/let
                 [VAL__14913 (.valAt arg__14911 :aliases)]
                 (if
                  (clojure.core/map? VAL__14913)
                  (clojure.core/let
                   [X__14915 (clojure.core/set VAL__14913)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__14915))
                    (clojure.core/loop
                     [search_space__15984 (clojure.core/seq X__14915)]
                     (if
                      (clojure.core/seq search_space__15984)
                      (clojure.core/let
                       [elem__14916
                        (clojure.core/first search_space__15984)
                        result__15985
                        (clojure.core/let
                         [elem__14916_nth_0__
                          (clojure.core/nth elem__14916 0)
                          elem__14916_nth_1__
                          (clojure.core/nth elem__14916 1)]
                         (if
                          (clojure.core/symbol? elem__14916_nth_0__)
                          (clojure.core/let
                           [X__14918
                            (clojure.core/name elem__14916_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__14918)
                            (if
                             (clojure.core/symbol? elem__14916_nth_1__)
                             (clojure.core/let
                              [X__14920
                               (clojure.core/name elem__14916_nth_1__)]
                              (clojure.core/case
                               X__14920
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__14911]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__15985)
                        (recur (clojure.core/next search_space__15984))
                        result__15985))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__15982)))]
          (if
           (clojure.core/vector? input__14353)
           (if
            (clojure.core/= (clojure.core/count input__14353) 3)
            (clojure.core/let
             [input__14353_nth_0__
              (clojure.core/nth input__14353 0)
              input__14353_nth_1__
              (clojure.core/nth input__14353 1)
              input__14353_nth_2__
              (clojure.core/nth input__14353 2)]
             (clojure.core/case
              input__14353_nth_0__
              (meander.dev.parse.zeta/parse-entries)
              (if
               (clojure.core/map? input__14353_nth_1__)
               (clojure.core/let
                [X__14883 (clojure.core/set input__14353_nth_1__)]
                (if
                 (clojure.core/<= 1 (clojure.core/count X__14883))
                 (clojure.core/loop
                  [search_space__15987 (clojure.core/seq X__14883)]
                  (if
                   (clojure.core/seq search_space__15987)
                   (clojure.core/let
                    [elem__14884
                     (clojure.core/first search_space__15987)
                     result__15988
                     (clojure.core/let
                      [elem__14884_nth_0__
                       (clojure.core/nth elem__14884 0)
                       elem__14884_nth_1__
                       (clojure.core/nth elem__14884 1)]
                      (clojure.core/let
                       [*m__14385 elem__14884_nth_0__]
                       (if
                        (clojure.core/symbol? elem__14884_nth_0__)
                        (clojure.core/let
                         [X__14886
                          (clojure.core/namespace elem__14884_nth_0__)]
                         (clojure.core/let
                          [?ns X__14886]
                          (clojure.core/let
                           [X__14888
                            (clojure.core/name elem__14884_nth_0__)]
                           (if
                            (clojure.core/string? X__14888)
                            (if
                             (clojure.core/re-matches #"&.*" X__14888)
                             (clojure.core/let
                              [?pattern elem__14884_nth_1__]
                              (clojure.core/let
                               [X__14890
                                ((clojure.core/fn
                                  [m__7002__auto__]
                                  (clojure.core/dissoc
                                   m__7002__auto__
                                   *m__14385))
                                 input__14353_nth_1__)]
                               (clojure.core/let
                                [?rest X__14890]
                                (clojure.core/let
                                 [x__7935__auto__
                                  (def__14878
                                   input__14353_nth_2__
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
                                        (CATA__FN__14430
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
                                        (CATA__FN__14430
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
                     (meander.runtime.zeta/fail? result__15988)
                     (recur (clojure.core/next search_space__15987))
                     result__15988))
                   (state__15860)))
                 (state__15860)))
               (state__15860))
              (state__15860)))
            (state__15860))
           (state__15860))))
        (state__15860
         []
         (if
          (clojure.core/vector? input__14353)
          (clojure.core/letfn
           [(state__15990
             []
             (if
              (clojure.core/= (clojure.core/count input__14353) 3)
              (clojure.core/let
               [input__14353_nth_0__
                (clojure.core/nth input__14353 0)
                input__14353_nth_1__
                (clojure.core/nth input__14353 1)
                input__14353_nth_2__
                (clojure.core/nth input__14353 2)]
               (clojure.core/case
                input__14353_nth_0__
                (meander.dev.parse.zeta/parse-entries)
                (clojure.core/letfn
                 [(state__15992
                   []
                   (if
                    (clojure.core/map? input__14353_nth_1__)
                    (clojure.core/let
                     [X__14934 (clojure.core/set input__14353_nth_1__)]
                     (if
                      (clojure.core/<= 1 (clojure.core/count X__14934))
                      (clojure.core/loop
                       [search_space__15994
                        (clojure.core/seq X__14934)]
                       (if
                        (clojure.core/seq search_space__15994)
                        (clojure.core/let
                         [elem__14935
                          (clojure.core/first search_space__15994)
                          result__15995
                          (clojure.core/let
                           [elem__14935_nth_0__
                            (clojure.core/nth elem__14935 0)
                            elem__14935_nth_1__
                            (clojure.core/nth elem__14935 1)]
                           (clojure.core/let
                            [?key-pattern elem__14935_nth_0__]
                            (clojure.core/let
                             [?val-pattern elem__14935_nth_1__]
                             (clojure.core/let
                              [X__14937
                               ((clojure.core/fn
                                 [m__7002__auto__]
                                 (clojure.core/dissoc
                                  m__7002__auto__
                                  ?key-pattern))
                                input__14353_nth_1__)]
                              (clojure.core/let
                               [?rest X__14937]
                               (clojure.core/let
                                [?env input__14353_nth_2__]
                                (try
                                 [{:tag :entry,
                                   :key-pattern
                                   (clojure.core/let
                                    [CATA_RESULT__9238__auto__
                                     (CATA__FN__14430
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
                                     (CATA__FN__14430
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
                                     (CATA__FN__14430
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
                          (meander.runtime.zeta/fail? result__15995)
                          (recur
                           (clojure.core/next search_space__15994))
                          result__15995))
                        (state__15993)))
                      (state__15993)))
                    (state__15993)))
                  (state__15993
                   []
                   (if
                    (clojure.core/map? input__14353_nth_1__)
                    (clojure.core/let
                     [?env input__14353_nth_2__]
                     (try
                      [{:tag :some-map}]
                      (catch
                       java.lang.Exception
                       e__10178__auto__
                       (if
                        (meander.runtime.zeta/fail? e__10178__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__10178__auto__)))))
                    (state__15991)))]
                 (state__15992))
                (meander.dev.parse.zeta/parse-with-bindings)
                (clojure.core/letfn
                 [(state__15997
                   []
                   (if
                    (clojure.core/vector? input__14353_nth_1__)
                    (clojure.core/case
                     input__14353_nth_1__
                     ([])
                     (clojure.core/let
                      [?env input__14353_nth_2__]
                      (try
                       [[]]
                       (catch
                        java.lang.Exception
                        e__10178__auto__
                        (if
                         (meander.runtime.zeta/fail? e__10178__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__10178__auto__)))))
                     (state__15998))
                    (state__15998)))
                  (state__15998
                   []
                   (if
                    (clojure.core/vector? input__14353_nth_1__)
                    (if
                     (clojure.core/=
                      (clojure.core/count input__14353_nth_1__)
                      1)
                     (clojure.core/let
                      [?env input__14353_nth_2__]
                      (try
                       [[{:tag :error,
                          :message
                          "meander.zeta/with expects an even number of bindings"}]]
                       (catch
                        java.lang.Exception
                        e__10178__auto__
                        (if
                         (meander.runtime.zeta/fail? e__10178__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__10178__auto__)))))
                     (state__15999))
                    (state__15999)))
                  (state__15999
                   []
                   (if
                    (clojure.core/vector? input__14353_nth_1__)
                    (clojure.core/let
                     [input__14353_nth_1___l__
                      (clojure.core/subvec
                       input__14353_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__14353_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__14353_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__14353_nth_1___r__
                        (clojure.core/subvec input__14353_nth_1__ 2)]
                       (clojure.core/let
                        [input__14353_nth_1___l___nth_0__
                         (clojure.core/nth input__14353_nth_1___l__ 0)
                         input__14353_nth_1___l___nth_1__
                         (clojure.core/nth input__14353_nth_1___l__ 1)]
                        (if
                         (clojure.core/symbol?
                          input__14353_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__14951
                           (clojure.core/namespace
                            input__14353_nth_1___l___nth_0__)]
                          (clojure.core/let
                           [X__14953
                            (clojure.core/name
                             input__14353_nth_1___l___nth_0__)]
                           (if
                            (clojure.core/string? X__14953)
                            (if
                             (clojure.core/re-matches #"%.+" X__14953)
                             (clojure.core/let
                              [?symbol
                               input__14353_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?pattern
                                input__14353_nth_1___l___nth_1__]
                               (clojure.core/let
                                [?rest input__14353_nth_1___r__]
                                (clojure.core/let
                                 [?env input__14353_nth_2__]
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
                                         (CATA__FN__14430
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
                                       (CATA__FN__14430
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
                             (state__16000))
                            (state__16000))))
                         (state__16000))))
                      (state__16000)))
                    (state__16000)))
                  (state__16000
                   []
                   (if
                    (clojure.core/vector? input__14353_nth_1__)
                    (clojure.core/let
                     [input__14353_nth_1___l__
                      (clojure.core/subvec
                       input__14353_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__14353_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__14353_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__14353_nth_1___r__
                        (clojure.core/subvec input__14353_nth_1__ 2)]
                       (clojure.core/let
                        [input__14353_nth_1___l___nth_0__
                         (clojure.core/nth input__14353_nth_1___l__ 0)
                         input__14353_nth_1___l___nth_1__
                         (clojure.core/nth input__14353_nth_1___l__ 1)]
                        (clojure.core/let
                         [?x input__14353_nth_1___l___nth_0__]
                         (clojure.core/let
                          [?pattern input__14353_nth_1___l___nth_1__]
                          (clojure.core/let
                           [?rest input__14353_nth_1___r__]
                           (clojure.core/let
                            [?env input__14353_nth_2__]
                            (try
                             [[{:tag :error,
                                :message
                                "meander.zeta/with bindings must be an repeating sequence of %name pattern"}]]
                             (catch
                              java.lang.Exception
                              e__10178__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__10178__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__10178__auto__))))))))))
                      (state__15991)))
                    (state__15991)))]
                 (state__15997))
                (state__15991)))
              (state__15991)))
            (state__15991
             []
             (if
              (clojure.core/= (clojure.core/count input__14353) 2)
              (clojure.core/let
               [input__14353_nth_0__
                (clojure.core/nth input__14353 0)
                input__14353_nth_1__
                (clojure.core/nth input__14353 1)]
               (if
                (clojure.core/vector? input__14353_nth_0__)
                (clojure.core/let
                 [?sequence input__14353_nth_0__]
                 (clojure.core/let
                  [?form input__14353_nth_0__]
                  (clojure.core/let
                   [?env input__14353_nth_1__]
                   (try
                    [{:tag :vector,
                      :next
                      (clojure.core/let
                       [CATA_RESULT__9238__auto__
                        (CATA__FN__14430
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
                (state__15861)))
              (state__15861)))]
           (state__15990))
          (state__15861)))
        (state__15861
         []
         (clojure.core/letfn
          [(def__14963
            [arg__14986 ?__14354]
            (clojure.core/letfn
             [(state__16001
               []
               (clojure.core/let
                [x__14987 "clojure.core"]
                (if
                 (clojure.core/= ?__14354 x__14987)
                 [?__14354]
                 (state__16002))))
              (state__16002
               []
               (if
                (clojure.core/map? arg__14986)
                (clojure.core/let
                 [VAL__14988 (.valAt arg__14986 :aliases)]
                 (if
                  (clojure.core/map? VAL__14988)
                  (clojure.core/let
                   [X__14990 (clojure.core/set VAL__14988)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__14990))
                    (clojure.core/loop
                     [search_space__16003 (clojure.core/seq X__14990)]
                     (if
                      (clojure.core/seq search_space__16003)
                      (clojure.core/let
                       [elem__14991
                        (clojure.core/first search_space__16003)
                        result__16004
                        (clojure.core/let
                         [elem__14991_nth_0__
                          (clojure.core/nth elem__14991 0)
                          elem__14991_nth_1__
                          (clojure.core/nth elem__14991 1)]
                         (if
                          (clojure.core/symbol? elem__14991_nth_0__)
                          (clojure.core/let
                           [X__14993
                            (clojure.core/name elem__14991_nth_0__)]
                           (if
                            (clojure.core/= ?__14354 X__14993)
                            (if
                             (clojure.core/symbol? elem__14991_nth_1__)
                             (clojure.core/let
                              [X__14995
                               (clojure.core/name elem__14991_nth_1__)]
                              (clojure.core/case
                               X__14995
                               ("clojure.core")
                               [?__14354]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16004)
                        (recur (clojure.core/next search_space__16003))
                        result__16004))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16001)))]
          (if
           (clojure.core/vector? input__14353)
           (if
            (clojure.core/= (clojure.core/count input__14353) 2)
            (clojure.core/let
             [input__14353_nth_0__
              (clojure.core/nth input__14353 0)
              input__14353_nth_1__
              (clojure.core/nth input__14353 1)]
             (if
              (clojure.core/seq? input__14353_nth_0__)
              (clojure.core/let
               [input__14353_nth_0___l__
                (clojure.core/take 1 input__14353_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14353_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14353_nth_0___r__
                  (clojure.core/drop 1 input__14353_nth_0__)]
                 (clojure.core/let
                  [input__14353_nth_0___l___nth_0__
                   (clojure.core/nth input__14353_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14353_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__14973
                     (clojure.core/namespace
                      input__14353_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14354 X__14973]
                     (clojure.core/let
                      [X__14975
                       (clojure.core/name
                        input__14353_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__14975
                       ("unquote")
                       (clojure.core/let
                        [x__7935__auto__
                         (def__14963 input__14353_nth_1__ ?__14354)]
                        (if
                         (meander.runtime.zeta/fail? x__7935__auto__)
                         (state__15862)
                         (clojure.core/let
                          [[?__14354] x__7935__auto__]
                          (if
                           (clojure.core/vector? input__14353)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14353)
                             2)
                            (clojure.core/let
                             [input__14353_nth_0__
                              (clojure.core/nth input__14353 0)
                              input__14353_nth_1__
                              (clojure.core/nth input__14353 1)]
                             (if
                              (clojure.core/seq? input__14353_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__14353_nth_0__)
                                2)
                               (clojure.core/let
                                [input__14353_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14353_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?x input__14353_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__14353_nth_0__]
                                  (clojure.core/let
                                   [?env input__14353_nth_1__]
                                   (try
                                    [{:tag :host-expression,
                                      :expression ?x,
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__10178__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__10178__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__10178__auto__))))))))
                               (state__15862))
                              (state__15862)))
                            (state__15862))
                           (state__15862)))))
                       (state__15862)))))
                   (state__15862))))
                (state__15862)))
              (state__15862)))
            (state__15862))
           (state__15862))))
        (state__15862
         []
         (clojure.core/letfn
          [(def__14997
            [arg__15020 ?__14355]
            (clojure.core/letfn
             [(state__16006
               []
               (clojure.core/let
                [x__15021 "meander.zeta"]
                (if
                 (clojure.core/= ?__14355 x__15021)
                 [?__14355]
                 (state__16007))))
              (state__16007
               []
               (if
                (clojure.core/map? arg__15020)
                (clojure.core/let
                 [VAL__15022 (.valAt arg__15020 :aliases)]
                 (if
                  (clojure.core/map? VAL__15022)
                  (clojure.core/let
                   [X__15024 (clojure.core/set VAL__15022)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15024))
                    (clojure.core/loop
                     [search_space__16008 (clojure.core/seq X__15024)]
                     (if
                      (clojure.core/seq search_space__16008)
                      (clojure.core/let
                       [elem__15025
                        (clojure.core/first search_space__16008)
                        result__16009
                        (clojure.core/let
                         [elem__15025_nth_0__
                          (clojure.core/nth elem__15025 0)
                          elem__15025_nth_1__
                          (clojure.core/nth elem__15025 1)]
                         (if
                          (clojure.core/symbol? elem__15025_nth_0__)
                          (clojure.core/let
                           [X__15027
                            (clojure.core/name elem__15025_nth_0__)]
                           (if
                            (clojure.core/= ?__14355 X__15027)
                            (if
                             (clojure.core/symbol? elem__15025_nth_1__)
                             (clojure.core/let
                              [X__15029
                               (clojure.core/name elem__15025_nth_1__)]
                              (clojure.core/case
                               X__15029
                               ("meander.zeta")
                               [?__14355]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16009)
                        (recur (clojure.core/next search_space__16008))
                        result__16009))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16006)))]
          (if
           (clojure.core/vector? input__14353)
           (if
            (clojure.core/= (clojure.core/count input__14353) 2)
            (clojure.core/let
             [input__14353_nth_0__
              (clojure.core/nth input__14353 0)
              input__14353_nth_1__
              (clojure.core/nth input__14353 1)]
             (if
              (clojure.core/seq? input__14353_nth_0__)
              (clojure.core/let
               [input__14353_nth_0___l__
                (clojure.core/take 1 input__14353_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14353_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14353_nth_0___r__
                  (clojure.core/drop 1 input__14353_nth_0__)]
                 (clojure.core/let
                  [input__14353_nth_0___l___nth_0__
                   (clojure.core/nth input__14353_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14353_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15007
                     (clojure.core/namespace
                      input__14353_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14355 X__15007]
                     (clojure.core/let
                      [X__15009
                       (clojure.core/name
                        input__14353_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15009
                       ("*")
                       (clojure.core/let
                        [x__7935__auto__
                         (def__14997 input__14353_nth_1__ ?__14355)]
                        (if
                         (meander.runtime.zeta/fail? x__7935__auto__)
                         (state__15863)
                         (clojure.core/let
                          [[?__14355] x__7935__auto__]
                          (if
                           (clojure.core/vector? input__14353)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14353)
                             2)
                            (clojure.core/let
                             [input__14353_nth_0__
                              (clojure.core/nth input__14353 0)
                              input__14353_nth_1__
                              (clojure.core/nth input__14353 1)]
                             (if
                              (clojure.core/seq? input__14353_nth_0__)
                              (clojure.core/let
                               [input__14353_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__14353_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__14353_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__14353_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__14353_nth_0__)]
                                 (clojure.core/let
                                  [?patterns input__14353_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__14353_nth_0__]
                                   (clojure.core/let
                                    [?env input__14353_nth_1__]
                                    (try
                                     [{:tag :star,
                                       :greedy? true,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__9238__auto__
                                         (CATA__FN__14430
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
                                (state__15863)))
                              (state__15863)))
                            (state__15863))
                           (state__15863)))))
                       (state__15863)))))
                   (state__15863))))
                (state__15863)))
              (state__15863)))
            (state__15863))
           (state__15863))))
        (state__15863
         []
         (clojure.core/letfn
          [(def__15031
            [arg__15054 ?__14356]
            (clojure.core/letfn
             [(state__16011
               []
               (clojure.core/let
                [x__15055 "meander.zeta"]
                (if
                 (clojure.core/= ?__14356 x__15055)
                 [?__14356]
                 (state__16012))))
              (state__16012
               []
               (if
                (clojure.core/map? arg__15054)
                (clojure.core/let
                 [VAL__15056 (.valAt arg__15054 :aliases)]
                 (if
                  (clojure.core/map? VAL__15056)
                  (clojure.core/let
                   [X__15058 (clojure.core/set VAL__15056)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15058))
                    (clojure.core/loop
                     [search_space__16013 (clojure.core/seq X__15058)]
                     (if
                      (clojure.core/seq search_space__16013)
                      (clojure.core/let
                       [elem__15059
                        (clojure.core/first search_space__16013)
                        result__16014
                        (clojure.core/let
                         [elem__15059_nth_0__
                          (clojure.core/nth elem__15059 0)
                          elem__15059_nth_1__
                          (clojure.core/nth elem__15059 1)]
                         (if
                          (clojure.core/symbol? elem__15059_nth_0__)
                          (clojure.core/let
                           [X__15061
                            (clojure.core/name elem__15059_nth_0__)]
                           (if
                            (clojure.core/= ?__14356 X__15061)
                            (if
                             (clojure.core/symbol? elem__15059_nth_1__)
                             (clojure.core/let
                              [X__15063
                               (clojure.core/name elem__15059_nth_1__)]
                              (clojure.core/case
                               X__15063
                               ("meander.zeta")
                               [?__14356]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16014)
                        (recur (clojure.core/next search_space__16013))
                        result__16014))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16011)))]
          (if
           (clojure.core/vector? input__14353)
           (if
            (clojure.core/= (clojure.core/count input__14353) 2)
            (clojure.core/let
             [input__14353_nth_0__
              (clojure.core/nth input__14353 0)
              input__14353_nth_1__
              (clojure.core/nth input__14353 1)]
             (if
              (clojure.core/seq? input__14353_nth_0__)
              (clojure.core/let
               [input__14353_nth_0___l__
                (clojure.core/take 1 input__14353_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14353_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14353_nth_0___r__
                  (clojure.core/drop 1 input__14353_nth_0__)]
                 (clojure.core/let
                  [input__14353_nth_0___l___nth_0__
                   (clojure.core/nth input__14353_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14353_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15041
                     (clojure.core/namespace
                      input__14353_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14356 X__15041]
                     (clojure.core/let
                      [X__15043
                       (clojure.core/name
                        input__14353_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15043
                       ("<>")
                       (clojure.core/let
                        [x__7935__auto__
                         (def__15031 input__14353_nth_1__ ?__14356)]
                        (if
                         (meander.runtime.zeta/fail? x__7935__auto__)
                         (state__15864)
                         (clojure.core/let
                          [[?__14356] x__7935__auto__]
                          (if
                           (clojure.core/vector? input__14353)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14353)
                             2)
                            (clojure.core/let
                             [input__14353_nth_0__
                              (clojure.core/nth input__14353 0)
                              input__14353_nth_1__
                              (clojure.core/nth input__14353 1)]
                             (if
                              (clojure.core/seq? input__14353_nth_0__)
                              (clojure.core/let
                               [input__14353_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__14353_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__14353_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__14353_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__14353_nth_0__)]
                                 (clojure.core/let
                                  [?patterns input__14353_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__14353_nth_0__]
                                   (clojure.core/let
                                    [?env input__14353_nth_1__]
                                    (try
                                     [{:tag :group,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__9238__auto__
                                         (CATA__FN__14430
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
                                (state__15864)))
                              (state__15864)))
                            (state__15864))
                           (state__15864)))))
                       (state__15864)))))
                   (state__15864))))
                (state__15864)))
              (state__15864)))
            (state__15864))
           (state__15864))))
        (state__15864
         []
         (clojure.core/letfn
          [(def__15065
            [arg__15088 ?__14357]
            (clojure.core/letfn
             [(state__16016
               []
               (clojure.core/let
                [x__15089 "meander.math.zeta"]
                (if
                 (clojure.core/= ?__14357 x__15089)
                 [?__14357]
                 (state__16017))))
              (state__16017
               []
               (if
                (clojure.core/map? arg__15088)
                (clojure.core/let
                 [VAL__15090 (.valAt arg__15088 :aliases)]
                 (if
                  (clojure.core/map? VAL__15090)
                  (clojure.core/let
                   [X__15092 (clojure.core/set VAL__15090)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15092))
                    (clojure.core/loop
                     [search_space__16018 (clojure.core/seq X__15092)]
                     (if
                      (clojure.core/seq search_space__16018)
                      (clojure.core/let
                       [elem__15093
                        (clojure.core/first search_space__16018)
                        result__16019
                        (clojure.core/let
                         [elem__15093_nth_0__
                          (clojure.core/nth elem__15093 0)
                          elem__15093_nth_1__
                          (clojure.core/nth elem__15093 1)]
                         (if
                          (clojure.core/symbol? elem__15093_nth_0__)
                          (clojure.core/let
                           [X__15095
                            (clojure.core/name elem__15093_nth_0__)]
                           (if
                            (clojure.core/= ?__14357 X__15095)
                            (if
                             (clojure.core/symbol? elem__15093_nth_1__)
                             (clojure.core/let
                              [X__15097
                               (clojure.core/name elem__15093_nth_1__)]
                              (clojure.core/case
                               X__15097
                               ("meander.math.zeta")
                               [?__14357]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16019)
                        (recur (clojure.core/next search_space__16018))
                        result__16019))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16016)))]
          (if
           (clojure.core/vector? input__14353)
           (if
            (clojure.core/= (clojure.core/count input__14353) 2)
            (clojure.core/let
             [input__14353_nth_0__
              (clojure.core/nth input__14353 0)
              input__14353_nth_1__
              (clojure.core/nth input__14353 1)]
             (if
              (clojure.core/seq? input__14353_nth_0__)
              (clojure.core/let
               [input__14353_nth_0___l__
                (clojure.core/take 1 input__14353_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14353_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14353_nth_0___r__
                  (clojure.core/drop 1 input__14353_nth_0__)]
                 (clojure.core/let
                  [input__14353_nth_0___l___nth_0__
                   (clojure.core/nth input__14353_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14353_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15075
                     (clojure.core/namespace
                      input__14353_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14357 X__15075]
                     (clojure.core/let
                      [X__15077
                       (clojure.core/name
                        input__14353_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15077
                       ("+")
                       (clojure.core/let
                        [x__7935__auto__
                         (def__15065 input__14353_nth_1__ ?__14357)]
                        (if
                         (meander.runtime.zeta/fail? x__7935__auto__)
                         (state__15865)
                         (clojure.core/let
                          [[?__14357] x__7935__auto__]
                          (if
                           (clojure.core/vector? input__14353)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14353)
                             2)
                            (clojure.core/let
                             [input__14353_nth_0__
                              (clojure.core/nth input__14353 0)
                              input__14353_nth_1__
                              (clojure.core/nth input__14353 1)]
                             (if
                              (clojure.core/seq? input__14353_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__14353_nth_0__)
                                3)
                               (clojure.core/let
                                [input__14353_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14353_nth_0__
                                  1)
                                 input__14353_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__14353_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?a input__14353_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?b input__14353_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__14353_nth_0__]
                                   (clojure.core/let
                                    [?env input__14353_nth_1__]
                                    (try
                                     [{:tag :meander.math.zeta/+,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__9238__auto__
                                         (CATA__FN__14430 [?a ?env])]
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
                                         (CATA__FN__14430 [?b ?env])]
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
                               (state__15865))
                              (state__15865)))
                            (state__15865))
                           (state__15865)))))
                       (state__15865)))))
                   (state__15865))))
                (state__15865)))
              (state__15865)))
            (state__15865))
           (state__15865))))
        (state__15865
         []
         (clojure.core/letfn
          [(def__15099
            [arg__15122 ?__14358]
            (clojure.core/letfn
             [(state__16021
               []
               (clojure.core/let
                [x__15123 "meander.math.zeta"]
                (if
                 (clojure.core/= ?__14358 x__15123)
                 [?__14358]
                 (state__16022))))
              (state__16022
               []
               (if
                (clojure.core/map? arg__15122)
                (clojure.core/let
                 [VAL__15124 (.valAt arg__15122 :aliases)]
                 (if
                  (clojure.core/map? VAL__15124)
                  (clojure.core/let
                   [X__15126 (clojure.core/set VAL__15124)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15126))
                    (clojure.core/loop
                     [search_space__16023 (clojure.core/seq X__15126)]
                     (if
                      (clojure.core/seq search_space__16023)
                      (clojure.core/let
                       [elem__15127
                        (clojure.core/first search_space__16023)
                        result__16024
                        (clojure.core/let
                         [elem__15127_nth_0__
                          (clojure.core/nth elem__15127 0)
                          elem__15127_nth_1__
                          (clojure.core/nth elem__15127 1)]
                         (if
                          (clojure.core/symbol? elem__15127_nth_0__)
                          (clojure.core/let
                           [X__15129
                            (clojure.core/name elem__15127_nth_0__)]
                           (if
                            (clojure.core/= ?__14358 X__15129)
                            (if
                             (clojure.core/symbol? elem__15127_nth_1__)
                             (clojure.core/let
                              [X__15131
                               (clojure.core/name elem__15127_nth_1__)]
                              (clojure.core/case
                               X__15131
                               ("meander.math.zeta")
                               [?__14358]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16024)
                        (recur (clojure.core/next search_space__16023))
                        result__16024))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16021)))]
          (if
           (clojure.core/vector? input__14353)
           (if
            (clojure.core/= (clojure.core/count input__14353) 2)
            (clojure.core/let
             [input__14353_nth_0__
              (clojure.core/nth input__14353 0)
              input__14353_nth_1__
              (clojure.core/nth input__14353 1)]
             (if
              (clojure.core/seq? input__14353_nth_0__)
              (clojure.core/let
               [input__14353_nth_0___l__
                (clojure.core/take 1 input__14353_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14353_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14353_nth_0___r__
                  (clojure.core/drop 1 input__14353_nth_0__)]
                 (clojure.core/let
                  [input__14353_nth_0___l___nth_0__
                   (clojure.core/nth input__14353_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14353_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15109
                     (clojure.core/namespace
                      input__14353_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14358 X__15109]
                     (clojure.core/let
                      [X__15111
                       (clojure.core/name
                        input__14353_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15111
                       ("-")
                       (clojure.core/let
                        [x__7935__auto__
                         (def__15099 input__14353_nth_1__ ?__14358)]
                        (if
                         (meander.runtime.zeta/fail? x__7935__auto__)
                         (state__15866)
                         (clojure.core/let
                          [[?__14358] x__7935__auto__]
                          (if
                           (clojure.core/vector? input__14353)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14353)
                             2)
                            (clojure.core/let
                             [input__14353_nth_0__
                              (clojure.core/nth input__14353 0)
                              input__14353_nth_1__
                              (clojure.core/nth input__14353 1)]
                             (if
                              (clojure.core/seq? input__14353_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__14353_nth_0__)
                                3)
                               (clojure.core/let
                                [input__14353_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14353_nth_0__
                                  1)
                                 input__14353_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__14353_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?a input__14353_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?b input__14353_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__14353_nth_0__]
                                   (clojure.core/let
                                    [?env input__14353_nth_1__]
                                    (try
                                     [{:tag :meander.math.zeta/-,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__9238__auto__
                                         (CATA__FN__14430 [?a ?env])]
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
                                         (CATA__FN__14430 [?b ?env])]
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
                               (state__15866))
                              (state__15866)))
                            (state__15866))
                           (state__15866)))))
                       (state__15866)))))
                   (state__15866))))
                (state__15866)))
              (state__15866)))
            (state__15866))
           (state__15866))))
        (state__15866
         []
         (clojure.core/letfn
          [(def__15133
            [arg__15156 ?__14359]
            (clojure.core/letfn
             [(state__16026
               []
               (clojure.core/let
                [x__15157 "meander.zeta"]
                (if
                 (clojure.core/= ?__14359 x__15157)
                 [?__14359]
                 (state__16027))))
              (state__16027
               []
               (if
                (clojure.core/map? arg__15156)
                (clojure.core/let
                 [VAL__15158 (.valAt arg__15156 :aliases)]
                 (if
                  (clojure.core/map? VAL__15158)
                  (clojure.core/let
                   [X__15160 (clojure.core/set VAL__15158)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15160))
                    (clojure.core/loop
                     [search_space__16028 (clojure.core/seq X__15160)]
                     (if
                      (clojure.core/seq search_space__16028)
                      (clojure.core/let
                       [elem__15161
                        (clojure.core/first search_space__16028)
                        result__16029
                        (clojure.core/let
                         [elem__15161_nth_0__
                          (clojure.core/nth elem__15161 0)
                          elem__15161_nth_1__
                          (clojure.core/nth elem__15161 1)]
                         (if
                          (clojure.core/symbol? elem__15161_nth_0__)
                          (clojure.core/let
                           [X__15163
                            (clojure.core/name elem__15161_nth_0__)]
                           (if
                            (clojure.core/= ?__14359 X__15163)
                            (if
                             (clojure.core/symbol? elem__15161_nth_1__)
                             (clojure.core/let
                              [X__15165
                               (clojure.core/name elem__15161_nth_1__)]
                              (clojure.core/case
                               X__15165
                               ("meander.zeta")
                               [?__14359]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16029)
                        (recur (clojure.core/next search_space__16028))
                        result__16029))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16026)))]
          (if
           (clojure.core/vector? input__14353)
           (if
            (clojure.core/= (clojure.core/count input__14353) 2)
            (clojure.core/let
             [input__14353_nth_0__
              (clojure.core/nth input__14353 0)
              input__14353_nth_1__
              (clojure.core/nth input__14353 1)]
             (if
              (clojure.core/seq? input__14353_nth_0__)
              (clojure.core/let
               [input__14353_nth_0___l__
                (clojure.core/take 1 input__14353_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14353_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14353_nth_0___r__
                  (clojure.core/drop 1 input__14353_nth_0__)]
                 (clojure.core/let
                  [input__14353_nth_0___l___nth_0__
                   (clojure.core/nth input__14353_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14353_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15143
                     (clojure.core/namespace
                      input__14353_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14359 X__15143]
                     (clojure.core/let
                      [X__15145
                       (clojure.core/name
                        input__14353_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15145
                       ("with")
                       (clojure.core/let
                        [x__7935__auto__
                         (def__15133 input__14353_nth_1__ ?__14359)]
                        (if
                         (meander.runtime.zeta/fail? x__7935__auto__)
                         (state__15867)
                         (clojure.core/let
                          [[?__14359] x__7935__auto__]
                          (if
                           (clojure.core/vector? input__14353)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14353)
                             2)
                            (clojure.core/let
                             [input__14353_nth_0__
                              (clojure.core/nth input__14353 0)
                              input__14353_nth_1__
                              (clojure.core/nth input__14353 1)]
                             (if
                              (clojure.core/seq? input__14353_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__14353_nth_0__)
                                3)
                               (clojure.core/let
                                [input__14353_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14353_nth_0__
                                  1)
                                 input__14353_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__14353_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?bindings
                                  input__14353_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?body input__14353_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__14353_nth_0__]
                                   (clojure.core/let
                                    [?env input__14353_nth_1__]
                                    (try
                                     [{:tag :with,
                                       :bindings
                                       {:tag :with-bindings,
                                        :bindings
                                        (clojure.core/let
                                         [CATA_RESULT__9238__auto__
                                          (CATA__FN__14430
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
                                         (CATA__FN__14430
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
                               (state__15867))
                              (state__15867)))
                            (state__15867))
                           (state__15867)))))
                       (state__15867)))))
                   (state__15867))))
                (state__15867)))
              (state__15867)))
            (state__15867))
           (state__15867))))
        (state__15867
         []
         (clojure.core/letfn
          [(def__15167
            [arg__15190 ?__14360]
            (clojure.core/letfn
             [(state__16031
               []
               (clojure.core/let
                [x__15191 "meander.zeta"]
                (if
                 (clojure.core/= ?__14360 x__15191)
                 [?__14360]
                 (state__16032))))
              (state__16032
               []
               (if
                (clojure.core/map? arg__15190)
                (clojure.core/let
                 [VAL__15192 (.valAt arg__15190 :aliases)]
                 (if
                  (clojure.core/map? VAL__15192)
                  (clojure.core/let
                   [X__15194 (clojure.core/set VAL__15192)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15194))
                    (clojure.core/loop
                     [search_space__16033 (clojure.core/seq X__15194)]
                     (if
                      (clojure.core/seq search_space__16033)
                      (clojure.core/let
                       [elem__15195
                        (clojure.core/first search_space__16033)
                        result__16034
                        (clojure.core/let
                         [elem__15195_nth_0__
                          (clojure.core/nth elem__15195 0)
                          elem__15195_nth_1__
                          (clojure.core/nth elem__15195 1)]
                         (if
                          (clojure.core/symbol? elem__15195_nth_0__)
                          (clojure.core/let
                           [X__15197
                            (clojure.core/name elem__15195_nth_0__)]
                           (if
                            (clojure.core/= ?__14360 X__15197)
                            (if
                             (clojure.core/symbol? elem__15195_nth_1__)
                             (clojure.core/let
                              [X__15199
                               (clojure.core/name elem__15195_nth_1__)]
                              (clojure.core/case
                               X__15199
                               ("meander.zeta")
                               [?__14360]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16034)
                        (recur (clojure.core/next search_space__16033))
                        result__16034))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16031)))]
          (if
           (clojure.core/vector? input__14353)
           (if
            (clojure.core/= (clojure.core/count input__14353) 2)
            (clojure.core/let
             [input__14353_nth_0__
              (clojure.core/nth input__14353 0)
              input__14353_nth_1__
              (clojure.core/nth input__14353 1)]
             (if
              (clojure.core/seq? input__14353_nth_0__)
              (clojure.core/let
               [input__14353_nth_0___l__
                (clojure.core/take 1 input__14353_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14353_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14353_nth_0___r__
                  (clojure.core/drop 1 input__14353_nth_0__)]
                 (clojure.core/let
                  [input__14353_nth_0___l___nth_0__
                   (clojure.core/nth input__14353_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14353_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15177
                     (clojure.core/namespace
                      input__14353_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14360 X__15177]
                     (clojure.core/let
                      [X__15179
                       (clojure.core/name
                        input__14353_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15179
                       ("apply")
                       (clojure.core/let
                        [x__7935__auto__
                         (def__15167 input__14353_nth_1__ ?__14360)]
                        (if
                         (meander.runtime.zeta/fail? x__7935__auto__)
                         (state__15868)
                         (clojure.core/let
                          [[?__14360] x__7935__auto__]
                          (if
                           (clojure.core/vector? input__14353)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14353)
                             2)
                            (clojure.core/let
                             [input__14353_nth_0__
                              (clojure.core/nth input__14353 0)
                              input__14353_nth_1__
                              (clojure.core/nth input__14353 1)]
                             (if
                              (clojure.core/seq? input__14353_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__14353_nth_0__)
                                3)
                               (clojure.core/let
                                [input__14353_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14353_nth_0__
                                  1)
                                 input__14353_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__14353_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?fn input__14353_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?pattern
                                   input__14353_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__14353_nth_0__]
                                   (clojure.core/let
                                    [?env input__14353_nth_1__]
                                    (try
                                     [{:tag :apply,
                                       :fn ?fn,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__9238__auto__
                                         (CATA__FN__14430
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
                               (state__15868))
                              (state__15868)))
                            (state__15868))
                           (state__15868)))))
                       (state__15868)))))
                   (state__15868))))
                (state__15868)))
              (state__15868)))
            (state__15868))
           (state__15868))))
        (state__15868
         []
         (clojure.core/letfn
          [(def__15201
            [arg__15226 ?__14361]
            (clojure.core/letfn
             [(state__16036
               []
               (clojure.core/let
                [x__15227 "meander.zeta"]
                (if
                 (clojure.core/= ?__14361 x__15227)
                 [?__14361]
                 (state__16037))))
              (state__16037
               []
               (if
                (clojure.core/map? arg__15226)
                (clojure.core/let
                 [VAL__15228 (.valAt arg__15226 :aliases)]
                 (if
                  (clojure.core/map? VAL__15228)
                  (clojure.core/let
                   [X__15230 (clojure.core/set VAL__15228)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15230))
                    (clojure.core/loop
                     [search_space__16038 (clojure.core/seq X__15230)]
                     (if
                      (clojure.core/seq search_space__16038)
                      (clojure.core/let
                       [elem__15231
                        (clojure.core/first search_space__16038)
                        result__16039
                        (clojure.core/let
                         [elem__15231_nth_0__
                          (clojure.core/nth elem__15231 0)
                          elem__15231_nth_1__
                          (clojure.core/nth elem__15231 1)]
                         (if
                          (clojure.core/symbol? elem__15231_nth_0__)
                          (clojure.core/let
                           [X__15233
                            (clojure.core/name elem__15231_nth_0__)]
                           (if
                            (clojure.core/= ?__14361 X__15233)
                            (if
                             (clojure.core/symbol? elem__15231_nth_1__)
                             (clojure.core/let
                              [X__15235
                               (clojure.core/name elem__15231_nth_1__)]
                              (clojure.core/case
                               X__15235
                               ("meander.zeta")
                               [?__14361]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16039)
                        (recur (clojure.core/next search_space__16038))
                        result__16039))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16036)))]
          (if
           (clojure.core/vector? input__14353)
           (if
            (clojure.core/= (clojure.core/count input__14353) 2)
            (clojure.core/let
             [input__14353_nth_0__
              (clojure.core/nth input__14353 0)
              input__14353_nth_1__
              (clojure.core/nth input__14353 1)]
             (if
              (clojure.core/seq? input__14353_nth_0__)
              (clojure.core/let
               [input__14353_nth_0___l__
                (clojure.core/take 1 input__14353_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14353_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14353_nth_0___r__
                  (clojure.core/drop 1 input__14353_nth_0__)]
                 (clojure.core/let
                  [input__14353_nth_0___l___nth_0__
                   (clojure.core/nth input__14353_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14353_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15213
                     (clojure.core/namespace
                      input__14353_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14361 X__15213]
                     (clojure.core/let
                      [X__15215
                       (clojure.core/name
                        input__14353_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15215
                       ("and")
                       (clojure.core/let
                        [x__7935__auto__
                         (def__15201 input__14353_nth_1__ ?__14361)]
                        (if
                         (meander.runtime.zeta/fail? x__7935__auto__)
                         (state__15869)
                         (clojure.core/let
                          [[?__14361] x__7935__auto__]
                          (if
                           (clojure.core/vector? input__14353)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14353)
                             2)
                            (clojure.core/let
                             [input__14353_nth_0__
                              (clojure.core/nth input__14353 0)
                              input__14353_nth_1__
                              (clojure.core/nth input__14353 1)]
                             (if
                              (clojure.core/seq? input__14353_nth_0__)
                              (clojure.core/let
                               [input__14353_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__14353_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__14353_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__14353_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__14353_nth_0__)]
                                 (clojure.core/let
                                  [!forms
                                   (clojure.core/vec
                                    input__14353_nth_0___r__)]
                                  (clojure.core/let
                                   [?form input__14353_nth_0__]
                                   (clojure.core/let
                                    [?env input__14353_nth_1__]
                                    (try
                                     [(clojure.core/let
                                       [!forms__counter
                                        (meander.runtime.zeta/iterator
                                         !forms)]
                                       (clojure.core/let
                                        [CATA_RESULT__9238__auto__
                                         (CATA__FN__14430
                                          ['meander.dev.parse.zeta/make-and
                                           (clojure.core/into
                                            []
                                            (clojure.core/loop
                                             [return__14432
                                              (clojure.core/transient
                                               [])]
                                             (if
                                              (clojure.core/and
                                               (.hasNext
                                                !forms__counter))
                                              (recur
                                               (clojure.core/conj!
                                                return__14432
                                                (clojure.core/let
                                                 [CATA_RESULT__9238__auto__
                                                  (CATA__FN__14430
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
                                               return__14432))))
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
                                (state__15869)))
                              (state__15869)))
                            (state__15869))
                           (state__15869)))))
                       (state__15869)))))
                   (state__15869))))
                (state__15869)))
              (state__15869)))
            (state__15869))
           (state__15869))))
        (state__15869
         []
         (if
          (clojure.core/vector? input__14353)
          (if
           (clojure.core/= (clojure.core/count input__14353) 3)
           (clojure.core/let
            [input__14353_nth_0__
             (clojure.core/nth input__14353 0)
             input__14353_nth_1__
             (clojure.core/nth input__14353 1)
             input__14353_nth_2__
             (clojure.core/nth input__14353 2)]
            (clojure.core/case
             input__14353_nth_0__
             (meander.dev.parse.zeta/make-and)
             (clojure.core/letfn
              [(state__16041
                []
                (if
                 (clojure.core/vector? input__14353_nth_1__)
                 (clojure.core/case
                  input__14353_nth_1__
                  ([])
                  (clojure.core/let
                   [?form input__14353_nth_2__]
                   (try
                    [{:tag :error,
                      :message
                      "meander.zeta/and requires 1 or more arguments",
                      :form ?form}]
                    (catch
                     java.lang.Exception
                     e__10178__auto__
                     (if
                      (meander.runtime.zeta/fail? e__10178__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__10178__auto__)))))
                  (state__16042))
                 (state__16042)))
               (state__16042
                []
                (clojure.core/case
                 input__14353_nth_2__
                 (nil)
                 (if
                  (clojure.core/vector? input__14353_nth_1__)
                  (if
                   (clojure.core/=
                    (clojure.core/count input__14353_nth_1__)
                    1)
                   (clojure.core/let
                    [input__14353_nth_1___nth_0__
                     (clojure.core/nth input__14353_nth_1__ 0)]
                    (clojure.core/let
                     [?ast-a input__14353_nth_1___nth_0__]
                     (try
                      [?ast-a]
                      (catch
                       java.lang.Exception
                       e__10178__auto__
                       (if
                        (meander.runtime.zeta/fail? e__10178__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__10178__auto__))))))
                   (state__16043))
                  (state__16043))
                 (state__16043)))
               (state__16043
                []
                (if
                 (clojure.core/vector? input__14353_nth_1__)
                 (clojure.core/letfn
                  [(state__16044
                    []
                    (if
                     (clojure.core/=
                      (clojure.core/count input__14353_nth_1__)
                      1)
                     (clojure.core/let
                      [input__14353_nth_1___nth_0__
                       (clojure.core/nth input__14353_nth_1__ 0)]
                      (clojure.core/let
                       [?ast-a input__14353_nth_1___nth_0__]
                       (clojure.core/let
                        [?form input__14353_nth_2__]
                        (try
                         [(clojure.core/let
                           [CATA_RESULT__9238__auto__
                            (CATA__FN__14430
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
                     (state__16045)))
                   (state__16045
                    []
                    (if
                     (clojure.core/=
                      (clojure.core/count input__14353_nth_1__)
                      2)
                     (clojure.core/let
                      [input__14353_nth_1___nth_0__
                       (clojure.core/nth input__14353_nth_1__ 0)
                       input__14353_nth_1___nth_1__
                       (clojure.core/nth input__14353_nth_1__ 1)]
                      (clojure.core/let
                       [?ast-a input__14353_nth_1___nth_0__]
                       (clojure.core/let
                        [?ast-b input__14353_nth_1___nth_1__]
                        (clojure.core/let
                         [?form input__14353_nth_2__]
                         (try
                          [{:tag :and,
                            :left ?ast-a,
                            :right ?ast-b,
                            :form ?form}]
                          (catch
                           java.lang.Exception
                           e__10178__auto__
                           (if
                            (meander.runtime.zeta/fail?
                             e__10178__auto__)
                            (meander.runtime.zeta/fail)
                            (throw e__10178__auto__))))))))
                     (state__16046)))
                   (state__16046
                    []
                    (clojure.core/loop
                     [search_space__16047
                      (meander.runtime.zeta/epsilon-partitions
                       2
                       input__14353_nth_1__)]
                     (if
                      (clojure.core/seq search_space__16047)
                      (clojure.core/let
                       [input__14353_nth_1___parts__
                        (clojure.core/first search_space__16047)
                        result__16048
                        (clojure.core/let
                         [input__14353_nth_1___l__
                          (clojure.core/nth
                           input__14353_nth_1___parts__
                           0)
                          input__14353_nth_1___r__
                          (clojure.core/nth
                           input__14353_nth_1___parts__
                           1)]
                         (clojure.core/letfn
                          [(state__16050
                            []
                            (clojure.core/let
                             [!asts-1 []]
                             (clojure.core/let
                              [ret__8099__auto__
                               (meander.runtime.zeta/epsilon-run-star-1
                                input__14353_nth_1___l__
                                [!asts-1]
                                (clojure.core/fn
                                 [[!asts-1] input__15262]
                                 (clojure.core/let
                                  [input__15262_nth_0__
                                   (clojure.core/nth input__15262 0)]
                                  (clojure.core/let
                                   [!asts-1
                                    (clojure.core/conj
                                     !asts-1
                                     input__15262_nth_0__)]
                                   [!asts-1])))
                                (clojure.core/fn
                                 [[!asts-1]]
                                 (clojure.core/let
                                  [X__15255
                                   (clojure.core/count
                                    input__14353_nth_1___l__)]
                                  (clojure.core/let
                                   [?n X__15255]
                                   (clojure.core/let
                                    [X__15259
                                     (clojure.core/count
                                      input__14353_nth_1___r__)]
                                    (if
                                     (clojure.core/= ?n X__15259)
                                     (clojure.core/let
                                      [!asts-2 []]
                                      (clojure.core/let
                                       [ret__8099__auto__
                                        (meander.runtime.zeta/epsilon-run-star-1
                                         input__14353_nth_1___r__
                                         [!asts-2 !asts-1]
                                         (clojure.core/fn
                                          [[!asts-2 !asts-1]
                                           input__15260]
                                          (clojure.core/let
                                           [input__15260_nth_0__
                                            (clojure.core/nth
                                             input__15260
                                             0)]
                                           (clojure.core/let
                                            [!asts-2
                                             (clojure.core/conj
                                              !asts-2
                                              input__15260_nth_0__)]
                                            [!asts-2 !asts-1])))
                                         (clojure.core/fn
                                          [[!asts-2 !asts-1]]
                                          (clojure.core/let
                                           [?form input__14353_nth_2__]
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
                                                (CATA__FN__14430
                                                 ['meander.dev.parse.zeta/make-and
                                                  [(clojure.core/let
                                                    [CATA_RESULT__9238__auto__
                                                     (CATA__FN__14430
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
                                                     (CATA__FN__14430
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
                                        (state__16051)
                                        ret__8099__auto__)))
                                     (state__16051)))))))]
                              (if
                               (meander.runtime.zeta/fail?
                                ret__8099__auto__)
                               (state__16051)
                               ret__8099__auto__))))
                           (state__16051
                            []
                            (clojure.core/let
                             [!asts-1 []]
                             (clojure.core/let
                              [ret__8099__auto__
                               (meander.runtime.zeta/epsilon-run-star-1
                                input__14353_nth_1___l__
                                [!asts-1]
                                (clojure.core/fn
                                 [[!asts-1] input__15278]
                                 (clojure.core/let
                                  [input__15278_nth_0__
                                   (clojure.core/nth input__15278 0)]
                                  (clojure.core/let
                                   [!asts-1
                                    (clojure.core/conj
                                     !asts-1
                                     input__15278_nth_0__)]
                                   [!asts-1])))
                                (clojure.core/fn
                                 [[!asts-1]]
                                 (clojure.core/let
                                  [X__15269
                                   (clojure.core/count
                                    input__14353_nth_1___l__)]
                                  (clojure.core/let
                                   [?n X__15269]
                                   (clojure.core/let
                                    [input__14353_nth_1___r___l__
                                     (clojure.core/subvec
                                      input__14353_nth_1___r__
                                      0
                                      (clojure.core/min
                                       (clojure.core/count
                                        input__14353_nth_1___r__)
                                       1))]
                                    (if
                                     (clojure.core/=
                                      (clojure.core/count
                                       input__14353_nth_1___r___l__)
                                      1)
                                     (clojure.core/let
                                      [input__14353_nth_1___r___r__
                                       (clojure.core/subvec
                                        input__14353_nth_1___r__
                                        1)]
                                      (clojure.core/let
                                       [input__14353_nth_1___r___l___nth_0__
                                        (clojure.core/nth
                                         input__14353_nth_1___r___l__
                                         0)]
                                       (clojure.core/let
                                        [?ast
                                         input__14353_nth_1___r___l___nth_0__]
                                        (clojure.core/let
                                         [X__15275
                                          (clojure.core/count
                                           input__14353_nth_1___r___r__)]
                                         (if
                                          (clojure.core/= ?n X__15275)
                                          (clojure.core/let
                                           [!asts-2 []]
                                           (clojure.core/let
                                            [ret__8099__auto__
                                             (meander.runtime.zeta/epsilon-run-star-1
                                              input__14353_nth_1___r___r__
                                              [!asts-2 !asts-1]
                                              (clojure.core/fn
                                               [[!asts-2 !asts-1]
                                                input__15276]
                                               (clojure.core/let
                                                [input__15276_nth_0__
                                                 (clojure.core/nth
                                                  input__15276
                                                  0)]
                                                (clojure.core/let
                                                 [!asts-2
                                                  (clojure.core/conj
                                                   !asts-2
                                                   input__15276_nth_0__)]
                                                 [!asts-2 !asts-1])))
                                              (clojure.core/fn
                                               [[!asts-2 !asts-1]]
                                               (clojure.core/let
                                                [?form
                                                 input__14353_nth_2__]
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
                                                     (CATA__FN__14430
                                                      ['meander.dev.parse.zeta/make-and
                                                       [(clojure.core/let
                                                         [CATA_RESULT__9238__auto__
                                                          (CATA__FN__14430
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
                                                          (CATA__FN__14430
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
                          (state__16050)))]
                       (if
                        (meander.runtime.zeta/fail? result__16048)
                        (recur (clojure.core/next search_space__16047))
                        result__16048))
                      (state__15870))))]
                  (state__16044))
                 (state__15870)))]
              (state__16041))
             (state__15870)))
           (state__15870))
          (state__15870)))
        (state__15870
         []
         (clojure.core/letfn
          [(def__15281
            [arg__15304 ?__14362]
            (clojure.core/letfn
             [(state__16056
               []
               (clojure.core/let
                [x__15305 "meander.zeta"]
                (if
                 (clojure.core/= ?__14362 x__15305)
                 [?__14362]
                 (state__16057))))
              (state__16057
               []
               (if
                (clojure.core/map? arg__15304)
                (clojure.core/let
                 [VAL__15306 (.valAt arg__15304 :aliases)]
                 (if
                  (clojure.core/map? VAL__15306)
                  (clojure.core/let
                   [X__15308 (clojure.core/set VAL__15306)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15308))
                    (clojure.core/loop
                     [search_space__16058 (clojure.core/seq X__15308)]
                     (if
                      (clojure.core/seq search_space__16058)
                      (clojure.core/let
                       [elem__15309
                        (clojure.core/first search_space__16058)
                        result__16059
                        (clojure.core/let
                         [elem__15309_nth_0__
                          (clojure.core/nth elem__15309 0)
                          elem__15309_nth_1__
                          (clojure.core/nth elem__15309 1)]
                         (if
                          (clojure.core/symbol? elem__15309_nth_0__)
                          (clojure.core/let
                           [X__15311
                            (clojure.core/name elem__15309_nth_0__)]
                           (if
                            (clojure.core/= ?__14362 X__15311)
                            (if
                             (clojure.core/symbol? elem__15309_nth_1__)
                             (clojure.core/let
                              [X__15313
                               (clojure.core/name elem__15309_nth_1__)]
                              (clojure.core/case
                               X__15313
                               ("meander.zeta")
                               [?__14362]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16059)
                        (recur (clojure.core/next search_space__16058))
                        result__16059))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16056)))]
          (if
           (clojure.core/vector? input__14353)
           (if
            (clojure.core/= (clojure.core/count input__14353) 2)
            (clojure.core/let
             [input__14353_nth_0__
              (clojure.core/nth input__14353 0)
              input__14353_nth_1__
              (clojure.core/nth input__14353 1)]
             (if
              (clojure.core/seq? input__14353_nth_0__)
              (clojure.core/let
               [input__14353_nth_0___l__
                (clojure.core/take 1 input__14353_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14353_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14353_nth_0___r__
                  (clojure.core/drop 1 input__14353_nth_0__)]
                 (clojure.core/let
                  [input__14353_nth_0___l___nth_0__
                   (clojure.core/nth input__14353_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14353_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15291
                     (clojure.core/namespace
                      input__14353_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14362 X__15291]
                     (clojure.core/let
                      [X__15293
                       (clojure.core/name
                        input__14353_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15293
                       ("cata")
                       (clojure.core/let
                        [x__7935__auto__
                         (def__15281 input__14353_nth_1__ ?__14362)]
                        (if
                         (meander.runtime.zeta/fail? x__7935__auto__)
                         (state__15871)
                         (clojure.core/let
                          [[?__14362] x__7935__auto__]
                          (if
                           (clojure.core/vector? input__14353)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14353)
                             2)
                            (clojure.core/let
                             [input__14353_nth_0__
                              (clojure.core/nth input__14353 0)
                              input__14353_nth_1__
                              (clojure.core/nth input__14353 1)]
                             (if
                              (clojure.core/seq? input__14353_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__14353_nth_0__)
                                2)
                               (clojure.core/let
                                [input__14353_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14353_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__14353_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__14353_nth_0__]
                                  (clojure.core/let
                                   [?env input__14353_nth_1__]
                                   (try
                                    [{:tag :cata,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__9238__auto__
                                        (CATA__FN__14430
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
                               (state__15871))
                              (state__15871)))
                            (state__15871))
                           (state__15871)))))
                       (state__15871)))))
                   (state__15871))))
                (state__15871)))
              (state__15871)))
            (state__15871))
           (state__15871))))
        (state__15871
         []
         (clojure.core/letfn
          [(def__15315
            [arg__15338 ?__14363]
            (clojure.core/letfn
             [(state__16061
               []
               (clojure.core/let
                [x__15339 "meander.zeta"]
                (if
                 (clojure.core/= ?__14363 x__15339)
                 [?__14363]
                 (state__16062))))
              (state__16062
               []
               (if
                (clojure.core/map? arg__15338)
                (clojure.core/let
                 [VAL__15340 (.valAt arg__15338 :aliases)]
                 (if
                  (clojure.core/map? VAL__15340)
                  (clojure.core/let
                   [X__15342 (clojure.core/set VAL__15340)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15342))
                    (clojure.core/loop
                     [search_space__16063 (clojure.core/seq X__15342)]
                     (if
                      (clojure.core/seq search_space__16063)
                      (clojure.core/let
                       [elem__15343
                        (clojure.core/first search_space__16063)
                        result__16064
                        (clojure.core/let
                         [elem__15343_nth_0__
                          (clojure.core/nth elem__15343 0)
                          elem__15343_nth_1__
                          (clojure.core/nth elem__15343 1)]
                         (if
                          (clojure.core/symbol? elem__15343_nth_0__)
                          (clojure.core/let
                           [X__15345
                            (clojure.core/name elem__15343_nth_0__)]
                           (if
                            (clojure.core/= ?__14363 X__15345)
                            (if
                             (clojure.core/symbol? elem__15343_nth_1__)
                             (clojure.core/let
                              [X__15347
                               (clojure.core/name elem__15343_nth_1__)]
                              (clojure.core/case
                               X__15347
                               ("meander.zeta")
                               [?__14363]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16064)
                        (recur (clojure.core/next search_space__16063))
                        result__16064))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16061)))]
          (if
           (clojure.core/vector? input__14353)
           (if
            (clojure.core/= (clojure.core/count input__14353) 2)
            (clojure.core/let
             [input__14353_nth_0__
              (clojure.core/nth input__14353 0)
              input__14353_nth_1__
              (clojure.core/nth input__14353 1)]
             (if
              (clojure.core/seq? input__14353_nth_0__)
              (clojure.core/let
               [input__14353_nth_0___l__
                (clojure.core/take 1 input__14353_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14353_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14353_nth_0___r__
                  (clojure.core/drop 1 input__14353_nth_0__)]
                 (clojure.core/let
                  [input__14353_nth_0___l___nth_0__
                   (clojure.core/nth input__14353_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14353_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15325
                     (clojure.core/namespace
                      input__14353_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14363 X__15325]
                     (clojure.core/let
                      [X__15327
                       (clojure.core/name
                        input__14353_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15327
                       ("fold")
                       (clojure.core/let
                        [x__7935__auto__
                         (def__15315 input__14353_nth_1__ ?__14363)]
                        (if
                         (meander.runtime.zeta/fail? x__7935__auto__)
                         (state__15872)
                         (clojure.core/let
                          [[?__14363] x__7935__auto__]
                          (if
                           (clojure.core/vector? input__14353)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14353)
                             2)
                            (clojure.core/let
                             [input__14353_nth_0__
                              (clojure.core/nth input__14353 0)
                              input__14353_nth_1__
                              (clojure.core/nth input__14353 1)]
                             (if
                              (clojure.core/seq? input__14353_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 4)
                                 input__14353_nth_0__)
                                4)
                               (clojure.core/let
                                [input__14353_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14353_nth_0__
                                  1)
                                 input__14353_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__14353_nth_0__
                                  2)
                                 input__14353_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__14353_nth_0__
                                  3)]
                                (clojure.core/let
                                 [?mutable-variable
                                  input__14353_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?initial-value
                                   input__14353_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?fold-function
                                    input__14353_nth_0___nth_3__]
                                   (clojure.core/let
                                    [?form input__14353_nth_0__]
                                    (clojure.core/let
                                     [?env input__14353_nth_1__]
                                     (try
                                      [(clojure.core/let
                                        [CATA_RESULT__9238__auto__
                                         (CATA__FN__14430
                                          ['meander.dev.parse.zeta/make-fold
                                           (clojure.core/let
                                            [CATA_RESULT__9238__auto__
                                             (CATA__FN__14430
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
                                             (CATA__FN__14430
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
                               (state__15872))
                              (state__15872)))
                            (state__15872))
                           (state__15872)))))
                       (state__15872)))))
                   (state__15872))))
                (state__15872)))
              (state__15872)))
            (state__15872))
           (state__15872))))
        (state__15872
         []
         (if
          (clojure.core/vector? input__14353)
          (if
           (clojure.core/= (clojure.core/count input__14353) 5)
           (clojure.core/let
            [input__14353_nth_0__
             (clojure.core/nth input__14353 0)
             input__14353_nth_1__
             (clojure.core/nth input__14353 1)
             input__14353_nth_2__
             (clojure.core/nth input__14353 2)
             input__14353_nth_3__
             (clojure.core/nth input__14353 3)
             input__14353_nth_4__
             (clojure.core/nth input__14353 4)]
            (clojure.core/case
             input__14353_nth_0__
             (meander.dev.parse.zeta/make-fold)
             (if
              (clojure.core/map? input__14353_nth_1__)
              (clojure.core/let
               [VAL__15350 (.valAt input__14353_nth_1__ :tag)]
               (clojure.core/case
                VAL__15350
                (:mutable-variable)
                (clojure.core/let
                 [?variable-ast input__14353_nth_1__]
                 (clojure.core/let
                  [?initial-value-ast input__14353_nth_2__]
                  (clojure.core/let
                   [?fold-function input__14353_nth_3__]
                   (clojure.core/let
                    [?form input__14353_nth_4__]
                    (try
                     [{:tag :fold,
                       :variable ?variable-ast,
                       :initial-value ?initial-value-ast,
                       :fold-function
                       {:tag :host-expression, :form ?fold-function},
                       :form ?form}]
                     (catch
                      java.lang.Exception
                      e__10178__auto__
                      (if
                       (meander.runtime.zeta/fail? e__10178__auto__)
                       (meander.runtime.zeta/fail)
                       (throw e__10178__auto__))))))))
                (state__15873)))
              (state__15873))
             (state__15873)))
           (state__15873))
          (state__15873)))
        (state__15873
         []
         (clojure.core/letfn
          [(def__15352
            [arg__15375 ?__14364]
            (clojure.core/letfn
             [(state__16066
               []
               (clojure.core/let
                [x__15376 "meander.zeta"]
                (if
                 (clojure.core/= ?__14364 x__15376)
                 [?__14364]
                 (state__16067))))
              (state__16067
               []
               (if
                (clojure.core/map? arg__15375)
                (clojure.core/let
                 [VAL__15377 (.valAt arg__15375 :aliases)]
                 (if
                  (clojure.core/map? VAL__15377)
                  (clojure.core/let
                   [X__15379 (clojure.core/set VAL__15377)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15379))
                    (clojure.core/loop
                     [search_space__16068 (clojure.core/seq X__15379)]
                     (if
                      (clojure.core/seq search_space__16068)
                      (clojure.core/let
                       [elem__15380
                        (clojure.core/first search_space__16068)
                        result__16069
                        (clojure.core/let
                         [elem__15380_nth_0__
                          (clojure.core/nth elem__15380 0)
                          elem__15380_nth_1__
                          (clojure.core/nth elem__15380 1)]
                         (if
                          (clojure.core/symbol? elem__15380_nth_0__)
                          (clojure.core/let
                           [X__15382
                            (clojure.core/name elem__15380_nth_0__)]
                           (if
                            (clojure.core/= ?__14364 X__15382)
                            (if
                             (clojure.core/symbol? elem__15380_nth_1__)
                             (clojure.core/let
                              [X__15384
                               (clojure.core/name elem__15380_nth_1__)]
                              (clojure.core/case
                               X__15384
                               ("meander.zeta")
                               [?__14364]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16069)
                        (recur (clojure.core/next search_space__16068))
                        result__16069))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16066)))]
          (if
           (clojure.core/vector? input__14353)
           (if
            (clojure.core/= (clojure.core/count input__14353) 2)
            (clojure.core/let
             [input__14353_nth_0__
              (clojure.core/nth input__14353 0)
              input__14353_nth_1__
              (clojure.core/nth input__14353 1)]
             (if
              (clojure.core/seq? input__14353_nth_0__)
              (clojure.core/let
               [input__14353_nth_0___l__
                (clojure.core/take 1 input__14353_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14353_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14353_nth_0___r__
                  (clojure.core/drop 1 input__14353_nth_0__)]
                 (clojure.core/let
                  [input__14353_nth_0___l___nth_0__
                   (clojure.core/nth input__14353_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14353_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15362
                     (clojure.core/namespace
                      input__14353_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14364 X__15362]
                     (clojure.core/let
                      [X__15364
                       (clojure.core/name
                        input__14353_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15364
                       ("keyword")
                       (clojure.core/let
                        [x__7935__auto__
                         (def__15352 input__14353_nth_1__ ?__14364)]
                        (if
                         (meander.runtime.zeta/fail? x__7935__auto__)
                         (state__15874)
                         (clojure.core/let
                          [[?__14364] x__7935__auto__]
                          (if
                           (clojure.core/vector? input__14353)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14353)
                             2)
                            (clojure.core/let
                             [input__14353_nth_0__
                              (clojure.core/nth input__14353 0)
                              input__14353_nth_1__
                              (clojure.core/nth input__14353 1)]
                             (if
                              (clojure.core/seq? input__14353_nth_0__)
                              (clojure.core/let
                               [input__14353_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__14353_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__14353_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__14353_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__14353_nth_0__)]
                                 (clojure.core/let
                                  [?keyword-args
                                   input__14353_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__14353_nth_0__]
                                   (clojure.core/let
                                    [?env input__14353_nth_1__]
                                    (try
                                     [(clojure.core/let
                                       [CATA_RESULT__9238__auto__
                                        (CATA__FN__14430
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
                                (state__15874)))
                              (state__15874)))
                            (state__15874))
                           (state__15874)))))
                       (state__15874)))))
                   (state__15874))))
                (state__15874)))
              (state__15874)))
            (state__15874))
           (state__15874))))
        (state__15874
         []
         (if
          (clojure.core/vector? input__14353)
          (if
           (clojure.core/= (clojure.core/count input__14353) 4)
           (clojure.core/let
            [input__14353_nth_0__
             (clojure.core/nth input__14353 0)
             input__14353_nth_1__
             (clojure.core/nth input__14353 1)
             input__14353_nth_2__
             (clojure.core/nth input__14353 2)]
            (clojure.core/letfn
             [(state__16071
               []
               (clojure.core/case
                input__14353_nth_0__
                (meander.dev.parse.zeta/make-keyword)
                (if
                 (clojure.core/vector? input__14353_nth_1__)
                 (clojure.core/case
                  input__14353_nth_1__
                  ([])
                  (clojure.core/let
                   [?form input__14353_nth_2__]
                   (try
                    [{:tag :keyword,
                      :namespace {:tag :wildcard},
                      :name {:tag :wildcard},
                      :form ?form}]
                    (catch
                     java.lang.Exception
                     e__10178__auto__
                     (if
                      (meander.runtime.zeta/fail? e__10178__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__10178__auto__)))))
                  (state__16072))
                 (state__16072))
                (state__16072)))
              (state__16072
               []
               (clojure.core/let
                [input__14353_nth_3__
                 (clojure.core/nth input__14353 3)]
                (clojure.core/case
                 input__14353_nth_0__
                 (meander.dev.parse.zeta/make-keyword)
                 (if
                  (clojure.core/vector? input__14353_nth_1__)
                  (clojure.core/letfn
                   [(state__16073
                     []
                     (if
                      (clojure.core/=
                       (clojure.core/count input__14353_nth_1__)
                       1)
                      (clojure.core/let
                       [input__14353_nth_1___nth_0__
                        (clojure.core/nth input__14353_nth_1__ 0)]
                       (clojure.core/let
                        [?name input__14353_nth_1___nth_0__]
                        (clojure.core/let
                         [?form input__14353_nth_2__]
                         (clojure.core/let
                          [?env input__14353_nth_3__]
                          (try
                           [{:tag :keyword,
                             :namespace {:tag :wildcard},
                             :name
                             (clojure.core/let
                              [CATA_RESULT__9238__auto__
                               (CATA__FN__14430 [?name ?env])]
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
                      (state__16074)))
                    (state__16074
                     []
                     (if
                      (clojure.core/=
                       (clojure.core/count input__14353_nth_1__)
                       2)
                      (clojure.core/let
                       [input__14353_nth_1___nth_0__
                        (clojure.core/nth input__14353_nth_1__ 0)
                        input__14353_nth_1___nth_1__
                        (clojure.core/nth input__14353_nth_1__ 1)]
                       (clojure.core/let
                        [?namespace input__14353_nth_1___nth_0__]
                        (clojure.core/let
                         [?name input__14353_nth_1___nth_1__]
                         (clojure.core/let
                          [?form input__14353_nth_2__]
                          (clojure.core/let
                           [?env input__14353_nth_3__]
                           (try
                            [{:tag :keyword,
                              :namespace
                              (clojure.core/let
                               [CATA_RESULT__9238__auto__
                                (CATA__FN__14430 [?namespace ?env])]
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
                                (CATA__FN__14430 [?name ?env])]
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
                      (state__15875)))]
                   (state__16073))
                  (state__15875))
                 (state__15875))))]
             (state__16071)))
           (state__15875))
          (state__15875)))
        (state__15875
         []
         (clojure.core/letfn
          [(def__15396
            [arg__15419 ?__14365]
            (clojure.core/letfn
             [(state__16075
               []
               (clojure.core/let
                [x__15420 "meander.zeta"]
                (if
                 (clojure.core/= ?__14365 x__15420)
                 [?__14365]
                 (state__16076))))
              (state__16076
               []
               (if
                (clojure.core/map? arg__15419)
                (clojure.core/let
                 [VAL__15421 (.valAt arg__15419 :aliases)]
                 (if
                  (clojure.core/map? VAL__15421)
                  (clojure.core/let
                   [X__15423 (clojure.core/set VAL__15421)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15423))
                    (clojure.core/loop
                     [search_space__16077 (clojure.core/seq X__15423)]
                     (if
                      (clojure.core/seq search_space__16077)
                      (clojure.core/let
                       [elem__15424
                        (clojure.core/first search_space__16077)
                        result__16078
                        (clojure.core/let
                         [elem__15424_nth_0__
                          (clojure.core/nth elem__15424 0)
                          elem__15424_nth_1__
                          (clojure.core/nth elem__15424 1)]
                         (if
                          (clojure.core/symbol? elem__15424_nth_0__)
                          (clojure.core/let
                           [X__15426
                            (clojure.core/name elem__15424_nth_0__)]
                           (if
                            (clojure.core/= ?__14365 X__15426)
                            (if
                             (clojure.core/symbol? elem__15424_nth_1__)
                             (clojure.core/let
                              [X__15428
                               (clojure.core/name elem__15424_nth_1__)]
                              (clojure.core/case
                               X__15428
                               ("meander.zeta")
                               [?__14365]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16078)
                        (recur (clojure.core/next search_space__16077))
                        result__16078))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16075)))]
          (if
           (clojure.core/vector? input__14353)
           (if
            (clojure.core/= (clojure.core/count input__14353) 2)
            (clojure.core/let
             [input__14353_nth_0__
              (clojure.core/nth input__14353 0)
              input__14353_nth_1__
              (clojure.core/nth input__14353 1)]
             (if
              (clojure.core/seq? input__14353_nth_0__)
              (clojure.core/let
               [input__14353_nth_0___l__
                (clojure.core/take 1 input__14353_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14353_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14353_nth_0___r__
                  (clojure.core/drop 1 input__14353_nth_0__)]
                 (clojure.core/let
                  [input__14353_nth_0___l___nth_0__
                   (clojure.core/nth input__14353_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14353_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15406
                     (clojure.core/namespace
                      input__14353_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14365 X__15406]
                     (clojure.core/let
                      [X__15408
                       (clojure.core/name
                        input__14353_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15408
                       ("let")
                       (clojure.core/let
                        [x__7935__auto__
                         (def__15396 input__14353_nth_1__ ?__14365)]
                        (if
                         (meander.runtime.zeta/fail? x__7935__auto__)
                         (state__15876)
                         (clojure.core/let
                          [[?__14365] x__7935__auto__]
                          (if
                           (clojure.core/vector? input__14353)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14353)
                             2)
                            (clojure.core/let
                             [input__14353_nth_0__
                              (clojure.core/nth input__14353 0)
                              input__14353_nth_1__
                              (clojure.core/nth input__14353 1)]
                             (if
                              (clojure.core/seq? input__14353_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__14353_nth_0__)
                                3)
                               (clojure.core/let
                                [input__14353_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14353_nth_0__
                                  1)
                                 input__14353_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__14353_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?pattern
                                  input__14353_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?expression
                                   input__14353_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__14353_nth_0__]
                                   (clojure.core/let
                                    [?env input__14353_nth_1__]
                                    (try
                                     [{:tag :let,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__9238__auto__
                                         (CATA__FN__14430
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
                               (state__15876))
                              (state__15876)))
                            (state__15876))
                           (state__15876)))))
                       (state__15876)))))
                   (state__15876))))
                (state__15876)))
              (state__15876)))
            (state__15876))
           (state__15876))))
        (state__15876
         []
         (clojure.core/letfn
          [(def__15430
            [arg__15453 ?__14366]
            (clojure.core/letfn
             [(state__16080
               []
               (clojure.core/let
                [x__15454 "meander.zeta"]
                (if
                 (clojure.core/= ?__14366 x__15454)
                 [?__14366]
                 (state__16081))))
              (state__16081
               []
               (if
                (clojure.core/map? arg__15453)
                (clojure.core/let
                 [VAL__15455 (.valAt arg__15453 :aliases)]
                 (if
                  (clojure.core/map? VAL__15455)
                  (clojure.core/let
                   [X__15457 (clojure.core/set VAL__15455)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15457))
                    (clojure.core/loop
                     [search_space__16082 (clojure.core/seq X__15457)]
                     (if
                      (clojure.core/seq search_space__16082)
                      (clojure.core/let
                       [elem__15458
                        (clojure.core/first search_space__16082)
                        result__16083
                        (clojure.core/let
                         [elem__15458_nth_0__
                          (clojure.core/nth elem__15458 0)
                          elem__15458_nth_1__
                          (clojure.core/nth elem__15458 1)]
                         (if
                          (clojure.core/symbol? elem__15458_nth_0__)
                          (clojure.core/let
                           [X__15460
                            (clojure.core/name elem__15458_nth_0__)]
                           (if
                            (clojure.core/= ?__14366 X__15460)
                            (if
                             (clojure.core/symbol? elem__15458_nth_1__)
                             (clojure.core/let
                              [X__15462
                               (clojure.core/name elem__15458_nth_1__)]
                              (clojure.core/case
                               X__15462
                               ("meander.zeta")
                               [?__14366]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16083)
                        (recur (clojure.core/next search_space__16082))
                        result__16083))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16080)))]
          (if
           (clojure.core/vector? input__14353)
           (if
            (clojure.core/= (clojure.core/count input__14353) 2)
            (clojure.core/let
             [input__14353_nth_0__
              (clojure.core/nth input__14353 0)
              input__14353_nth_1__
              (clojure.core/nth input__14353 1)]
             (if
              (clojure.core/seq? input__14353_nth_0__)
              (clojure.core/let
               [input__14353_nth_0___l__
                (clojure.core/take 1 input__14353_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14353_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14353_nth_0___r__
                  (clojure.core/drop 1 input__14353_nth_0__)]
                 (clojure.core/let
                  [input__14353_nth_0___l___nth_0__
                   (clojure.core/nth input__14353_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14353_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15440
                     (clojure.core/namespace
                      input__14353_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14366 X__15440]
                     (clojure.core/let
                      [X__15442
                       (clojure.core/name
                        input__14353_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15442
                       ("let")
                       (clojure.core/let
                        [x__7935__auto__
                         (def__15430 input__14353_nth_1__ ?__14366)]
                        (if
                         (meander.runtime.zeta/fail? x__7935__auto__)
                         (state__15877)
                         (clojure.core/let
                          [[?__14366] x__7935__auto__]
                          (if
                           (clojure.core/vector? input__14353)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14353)
                             2)
                            (clojure.core/let
                             [input__14353_nth_0__
                              (clojure.core/nth input__14353 0)
                              input__14353_nth_1__
                              (clojure.core/nth input__14353 1)]
                             (if
                              (clojure.core/seq? input__14353_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 4)
                                 input__14353_nth_0__)
                                4)
                               (clojure.core/let
                                [input__14353_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14353_nth_0__
                                  1)
                                 input__14353_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__14353_nth_0__
                                  2)
                                 input__14353_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__14353_nth_0__
                                  3)]
                                (clojure.core/let
                                 [?pattern
                                  input__14353_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?expression
                                   input__14353_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?next input__14353_nth_0___nth_3__]
                                   (clojure.core/let
                                    [?form input__14353_nth_0__]
                                    (clojure.core/let
                                     [?env input__14353_nth_1__]
                                     (try
                                      [{:tag :let,
                                        :pattern
                                        (clojure.core/let
                                         [CATA_RESULT__9238__auto__
                                          (CATA__FN__14430
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
                                          (CATA__FN__14430
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
                               (state__15877))
                              (state__15877)))
                            (state__15877))
                           (state__15877)))))
                       (state__15877)))))
                   (state__15877))))
                (state__15877)))
              (state__15877)))
            (state__15877))
           (state__15877))))
        (state__15877
         []
         (clojure.core/letfn
          [(def__15464
            [arg__15487 ?__14367]
            (clojure.core/letfn
             [(state__16085
               []
               (clojure.core/let
                [x__15488 "meander.zeta"]
                (if
                 (clojure.core/= ?__14367 x__15488)
                 [?__14367]
                 (state__16086))))
              (state__16086
               []
               (if
                (clojure.core/map? arg__15487)
                (clojure.core/let
                 [VAL__15489 (.valAt arg__15487 :aliases)]
                 (if
                  (clojure.core/map? VAL__15489)
                  (clojure.core/let
                   [X__15491 (clojure.core/set VAL__15489)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15491))
                    (clojure.core/loop
                     [search_space__16087 (clojure.core/seq X__15491)]
                     (if
                      (clojure.core/seq search_space__16087)
                      (clojure.core/let
                       [elem__15492
                        (clojure.core/first search_space__16087)
                        result__16088
                        (clojure.core/let
                         [elem__15492_nth_0__
                          (clojure.core/nth elem__15492 0)
                          elem__15492_nth_1__
                          (clojure.core/nth elem__15492 1)]
                         (if
                          (clojure.core/symbol? elem__15492_nth_0__)
                          (clojure.core/let
                           [X__15494
                            (clojure.core/name elem__15492_nth_0__)]
                           (if
                            (clojure.core/= ?__14367 X__15494)
                            (if
                             (clojure.core/symbol? elem__15492_nth_1__)
                             (clojure.core/let
                              [X__15496
                               (clojure.core/name elem__15492_nth_1__)]
                              (clojure.core/case
                               X__15496
                               ("meander.zeta")
                               [?__14367]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16088)
                        (recur (clojure.core/next search_space__16087))
                        result__16088))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16085)))]
          (if
           (clojure.core/vector? input__14353)
           (if
            (clojure.core/= (clojure.core/count input__14353) 2)
            (clojure.core/let
             [input__14353_nth_0__
              (clojure.core/nth input__14353 0)
              input__14353_nth_1__
              (clojure.core/nth input__14353 1)]
             (if
              (clojure.core/seq? input__14353_nth_0__)
              (clojure.core/let
               [input__14353_nth_0___l__
                (clojure.core/take 1 input__14353_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14353_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14353_nth_0___r__
                  (clojure.core/drop 1 input__14353_nth_0__)]
                 (clojure.core/let
                  [input__14353_nth_0___l___nth_0__
                   (clojure.core/nth input__14353_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14353_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15474
                     (clojure.core/namespace
                      input__14353_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14367 X__15474]
                     (clojure.core/let
                      [X__15476
                       (clojure.core/name
                        input__14353_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15476
                       ("not")
                       (clojure.core/let
                        [x__7935__auto__
                         (def__15464 input__14353_nth_1__ ?__14367)]
                        (if
                         (meander.runtime.zeta/fail? x__7935__auto__)
                         (state__15878)
                         (clojure.core/let
                          [[?__14367] x__7935__auto__]
                          (if
                           (clojure.core/vector? input__14353)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14353)
                             2)
                            (clojure.core/let
                             [input__14353_nth_0__
                              (clojure.core/nth input__14353 0)
                              input__14353_nth_1__
                              (clojure.core/nth input__14353 1)]
                             (if
                              (clojure.core/seq? input__14353_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__14353_nth_0__)
                                2)
                               (clojure.core/let
                                [input__14353_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14353_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__14353_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__14353_nth_0__]
                                  (clojure.core/let
                                   [?env input__14353_nth_1__]
                                   (try
                                    [{:tag :not,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__9238__auto__
                                        (CATA__FN__14430
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
                               (state__15878))
                              (state__15878)))
                            (state__15878))
                           (state__15878)))))
                       (state__15878)))))
                   (state__15878))))
                (state__15878)))
              (state__15878)))
            (state__15878))
           (state__15878))))
        (state__15878
         []
         (clojure.core/letfn
          [(def__15498
            [arg__15523 ?__14368]
            (clojure.core/letfn
             [(state__16090
               []
               (clojure.core/let
                [x__15524 "meander.zeta"]
                (if
                 (clojure.core/= ?__14368 x__15524)
                 [?__14368]
                 (state__16091))))
              (state__16091
               []
               (if
                (clojure.core/map? arg__15523)
                (clojure.core/let
                 [VAL__15525 (.valAt arg__15523 :aliases)]
                 (if
                  (clojure.core/map? VAL__15525)
                  (clojure.core/let
                   [X__15527 (clojure.core/set VAL__15525)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15527))
                    (clojure.core/loop
                     [search_space__16092 (clojure.core/seq X__15527)]
                     (if
                      (clojure.core/seq search_space__16092)
                      (clojure.core/let
                       [elem__15528
                        (clojure.core/first search_space__16092)
                        result__16093
                        (clojure.core/let
                         [elem__15528_nth_0__
                          (clojure.core/nth elem__15528 0)
                          elem__15528_nth_1__
                          (clojure.core/nth elem__15528 1)]
                         (if
                          (clojure.core/symbol? elem__15528_nth_0__)
                          (clojure.core/let
                           [X__15530
                            (clojure.core/name elem__15528_nth_0__)]
                           (if
                            (clojure.core/= ?__14368 X__15530)
                            (if
                             (clojure.core/symbol? elem__15528_nth_1__)
                             (clojure.core/let
                              [X__15532
                               (clojure.core/name elem__15528_nth_1__)]
                              (clojure.core/case
                               X__15532
                               ("meander.zeta")
                               [?__14368]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16093)
                        (recur (clojure.core/next search_space__16092))
                        result__16093))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16090)))]
          (if
           (clojure.core/vector? input__14353)
           (if
            (clojure.core/= (clojure.core/count input__14353) 2)
            (clojure.core/let
             [input__14353_nth_0__
              (clojure.core/nth input__14353 0)
              input__14353_nth_1__
              (clojure.core/nth input__14353 1)]
             (if
              (clojure.core/seq? input__14353_nth_0__)
              (clojure.core/let
               [input__14353_nth_0___l__
                (clojure.core/take 1 input__14353_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14353_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14353_nth_0___r__
                  (clojure.core/drop 1 input__14353_nth_0__)]
                 (clojure.core/let
                  [input__14353_nth_0___l___nth_0__
                   (clojure.core/nth input__14353_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14353_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15510
                     (clojure.core/namespace
                      input__14353_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14368 X__15510]
                     (clojure.core/let
                      [X__15512
                       (clojure.core/name
                        input__14353_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15512
                       ("or")
                       (clojure.core/let
                        [x__7935__auto__
                         (def__15498 input__14353_nth_1__ ?__14368)]
                        (if
                         (meander.runtime.zeta/fail? x__7935__auto__)
                         (state__15879)
                         (clojure.core/let
                          [[?__14368] x__7935__auto__]
                          (if
                           (clojure.core/vector? input__14353)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14353)
                             2)
                            (clojure.core/let
                             [input__14353_nth_0__
                              (clojure.core/nth input__14353 0)
                              input__14353_nth_1__
                              (clojure.core/nth input__14353 1)]
                             (if
                              (clojure.core/seq? input__14353_nth_0__)
                              (clojure.core/let
                               [input__14353_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__14353_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__14353_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__14353_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__14353_nth_0__)]
                                 (clojure.core/let
                                  [!forms
                                   (clojure.core/vec
                                    input__14353_nth_0___r__)]
                                  (clojure.core/let
                                   [?form input__14353_nth_0__]
                                   (clojure.core/let
                                    [?env input__14353_nth_1__]
                                    (try
                                     [(clojure.core/let
                                       [!forms__counter
                                        (meander.runtime.zeta/iterator
                                         !forms)]
                                       (clojure.core/let
                                        [CATA_RESULT__9238__auto__
                                         (CATA__FN__14430
                                          ['meander.dev.parse.zeta/make-or
                                           (clojure.core/into
                                            []
                                            (clojure.core/loop
                                             [return__14433
                                              (clojure.core/transient
                                               [])]
                                             (if
                                              (clojure.core/and
                                               (.hasNext
                                                !forms__counter))
                                              (recur
                                               (clojure.core/conj!
                                                return__14433
                                                (clojure.core/let
                                                 [CATA_RESULT__9238__auto__
                                                  (CATA__FN__14430
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
                                               return__14433))))
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
                                (state__15879)))
                              (state__15879)))
                            (state__15879))
                           (state__15879)))))
                       (state__15879)))))
                   (state__15879))))
                (state__15879)))
              (state__15879)))
            (state__15879))
           (state__15879))))
        (state__15879
         []
         (if
          (clojure.core/vector? input__14353)
          (if
           (clojure.core/= (clojure.core/count input__14353) 3)
           (clojure.core/let
            [input__14353_nth_0__
             (clojure.core/nth input__14353 0)
             input__14353_nth_1__
             (clojure.core/nth input__14353 1)
             input__14353_nth_2__
             (clojure.core/nth input__14353 2)]
            (clojure.core/case
             input__14353_nth_0__
             (meander.dev.parse.zeta/make-or)
             (clojure.core/letfn
              [(state__16095
                []
                (if
                 (clojure.core/vector? input__14353_nth_1__)
                 (clojure.core/case
                  input__14353_nth_1__
                  ([])
                  (clojure.core/let
                   [?form input__14353_nth_2__]
                   (try
                    [{:tag :error,
                      :message
                      "meander.zeta/or requires 1 or more arguments",
                      :form ?form}]
                    (catch
                     java.lang.Exception
                     e__10178__auto__
                     (if
                      (meander.runtime.zeta/fail? e__10178__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__10178__auto__)))))
                  (state__16096))
                 (state__16096)))
               (state__16096
                []
                (clojure.core/case
                 input__14353_nth_2__
                 (nil)
                 (if
                  (clojure.core/vector? input__14353_nth_1__)
                  (if
                   (clojure.core/=
                    (clojure.core/count input__14353_nth_1__)
                    1)
                   (clojure.core/let
                    [input__14353_nth_1___nth_0__
                     (clojure.core/nth input__14353_nth_1__ 0)]
                    (clojure.core/let
                     [?ast-a input__14353_nth_1___nth_0__]
                     (try
                      [?ast-a]
                      (catch
                       java.lang.Exception
                       e__10178__auto__
                       (if
                        (meander.runtime.zeta/fail? e__10178__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__10178__auto__))))))
                   (state__16097))
                  (state__16097))
                 (state__16097)))
               (state__16097
                []
                (if
                 (clojure.core/vector? input__14353_nth_1__)
                 (clojure.core/letfn
                  [(state__16098
                    []
                    (if
                     (clojure.core/=
                      (clojure.core/count input__14353_nth_1__)
                      1)
                     (clojure.core/let
                      [input__14353_nth_1___nth_0__
                       (clojure.core/nth input__14353_nth_1__ 0)]
                      (clojure.core/let
                       [?ast-a input__14353_nth_1___nth_0__]
                       (clojure.core/let
                        [?form input__14353_nth_2__]
                        (try
                         [(clojure.core/let
                           [CATA_RESULT__9238__auto__
                            (CATA__FN__14430
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
                     (state__16099)))
                   (state__16099
                    []
                    (if
                     (clojure.core/=
                      (clojure.core/count input__14353_nth_1__)
                      2)
                     (clojure.core/let
                      [input__14353_nth_1___nth_0__
                       (clojure.core/nth input__14353_nth_1__ 0)
                       input__14353_nth_1___nth_1__
                       (clojure.core/nth input__14353_nth_1__ 1)]
                      (clojure.core/let
                       [?ast-a input__14353_nth_1___nth_0__]
                       (clojure.core/let
                        [?ast-b input__14353_nth_1___nth_1__]
                        (clojure.core/let
                         [?form input__14353_nth_2__]
                         (try
                          [{:tag :or,
                            :left ?ast-a,
                            :right ?ast-b,
                            :form ?form}]
                          (catch
                           java.lang.Exception
                           e__10178__auto__
                           (if
                            (meander.runtime.zeta/fail?
                             e__10178__auto__)
                            (meander.runtime.zeta/fail)
                            (throw e__10178__auto__))))))))
                     (state__16100)))
                   (state__16100
                    []
                    (clojure.core/loop
                     [search_space__16101
                      (meander.runtime.zeta/epsilon-partitions
                       2
                       input__14353_nth_1__)]
                     (if
                      (clojure.core/seq search_space__16101)
                      (clojure.core/let
                       [input__14353_nth_1___parts__
                        (clojure.core/first search_space__16101)
                        result__16102
                        (clojure.core/let
                         [input__14353_nth_1___l__
                          (clojure.core/nth
                           input__14353_nth_1___parts__
                           0)
                          input__14353_nth_1___r__
                          (clojure.core/nth
                           input__14353_nth_1___parts__
                           1)]
                         (clojure.core/letfn
                          [(state__16104
                            []
                            (clojure.core/let
                             [!asts-1 []]
                             (clojure.core/let
                              [ret__8099__auto__
                               (meander.runtime.zeta/epsilon-run-star-1
                                input__14353_nth_1___l__
                                [!asts-1]
                                (clojure.core/fn
                                 [[!asts-1] input__15559]
                                 (clojure.core/let
                                  [input__15559_nth_0__
                                   (clojure.core/nth input__15559 0)]
                                  (clojure.core/let
                                   [!asts-1
                                    (clojure.core/conj
                                     !asts-1
                                     input__15559_nth_0__)]
                                   [!asts-1])))
                                (clojure.core/fn
                                 [[!asts-1]]
                                 (clojure.core/let
                                  [X__15552
                                   (clojure.core/count
                                    input__14353_nth_1___l__)]
                                  (clojure.core/let
                                   [?n X__15552]
                                   (clojure.core/let
                                    [X__15556
                                     (clojure.core/count
                                      input__14353_nth_1___r__)]
                                    (if
                                     (clojure.core/= ?n X__15556)
                                     (clojure.core/let
                                      [!asts-2 []]
                                      (clojure.core/let
                                       [ret__8099__auto__
                                        (meander.runtime.zeta/epsilon-run-star-1
                                         input__14353_nth_1___r__
                                         [!asts-2 !asts-1]
                                         (clojure.core/fn
                                          [[!asts-2 !asts-1]
                                           input__15557]
                                          (clojure.core/let
                                           [input__15557_nth_0__
                                            (clojure.core/nth
                                             input__15557
                                             0)]
                                           (clojure.core/let
                                            [!asts-2
                                             (clojure.core/conj
                                              !asts-2
                                              input__15557_nth_0__)]
                                            [!asts-2 !asts-1])))
                                         (clojure.core/fn
                                          [[!asts-2 !asts-1]]
                                          (clojure.core/let
                                           [?form input__14353_nth_2__]
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
                                                (CATA__FN__14430
                                                 ['meander.dev.parse.zeta/make-or
                                                  [(clojure.core/let
                                                    [CATA_RESULT__9238__auto__
                                                     (CATA__FN__14430
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
                                                     (CATA__FN__14430
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
                                        (state__16105)
                                        ret__8099__auto__)))
                                     (state__16105)))))))]
                              (if
                               (meander.runtime.zeta/fail?
                                ret__8099__auto__)
                               (state__16105)
                               ret__8099__auto__))))
                           (state__16105
                            []
                            (clojure.core/let
                             [!asts-1 []]
                             (clojure.core/let
                              [ret__8099__auto__
                               (meander.runtime.zeta/epsilon-run-star-1
                                input__14353_nth_1___l__
                                [!asts-1]
                                (clojure.core/fn
                                 [[!asts-1] input__15575]
                                 (clojure.core/let
                                  [input__15575_nth_0__
                                   (clojure.core/nth input__15575 0)]
                                  (clojure.core/let
                                   [!asts-1
                                    (clojure.core/conj
                                     !asts-1
                                     input__15575_nth_0__)]
                                   [!asts-1])))
                                (clojure.core/fn
                                 [[!asts-1]]
                                 (clojure.core/let
                                  [X__15566
                                   (clojure.core/count
                                    input__14353_nth_1___l__)]
                                  (clojure.core/let
                                   [?n X__15566]
                                   (clojure.core/let
                                    [input__14353_nth_1___r___l__
                                     (clojure.core/subvec
                                      input__14353_nth_1___r__
                                      0
                                      (clojure.core/min
                                       (clojure.core/count
                                        input__14353_nth_1___r__)
                                       1))]
                                    (if
                                     (clojure.core/=
                                      (clojure.core/count
                                       input__14353_nth_1___r___l__)
                                      1)
                                     (clojure.core/let
                                      [input__14353_nth_1___r___r__
                                       (clojure.core/subvec
                                        input__14353_nth_1___r__
                                        1)]
                                      (clojure.core/let
                                       [input__14353_nth_1___r___l___nth_0__
                                        (clojure.core/nth
                                         input__14353_nth_1___r___l__
                                         0)]
                                       (clojure.core/let
                                        [?ast
                                         input__14353_nth_1___r___l___nth_0__]
                                        (clojure.core/let
                                         [X__15572
                                          (clojure.core/count
                                           input__14353_nth_1___r___r__)]
                                         (if
                                          (clojure.core/= ?n X__15572)
                                          (clojure.core/let
                                           [!asts-2 []]
                                           (clojure.core/let
                                            [ret__8099__auto__
                                             (meander.runtime.zeta/epsilon-run-star-1
                                              input__14353_nth_1___r___r__
                                              [!asts-2 !asts-1]
                                              (clojure.core/fn
                                               [[!asts-2 !asts-1]
                                                input__15573]
                                               (clojure.core/let
                                                [input__15573_nth_0__
                                                 (clojure.core/nth
                                                  input__15573
                                                  0)]
                                                (clojure.core/let
                                                 [!asts-2
                                                  (clojure.core/conj
                                                   !asts-2
                                                   input__15573_nth_0__)]
                                                 [!asts-2 !asts-1])))
                                              (clojure.core/fn
                                               [[!asts-2 !asts-1]]
                                               (clojure.core/let
                                                [?form
                                                 input__14353_nth_2__]
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
                                                     (CATA__FN__14430
                                                      ['meander.dev.parse.zeta/make-or
                                                       [(clojure.core/let
                                                         [CATA_RESULT__9238__auto__
                                                          (CATA__FN__14430
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
                                                          (CATA__FN__14430
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
                          (state__16104)))]
                       (if
                        (meander.runtime.zeta/fail? result__16102)
                        (recur (clojure.core/next search_space__16101))
                        result__16102))
                      (state__15880))))]
                  (state__16098))
                 (state__15880)))]
              (state__16095))
             (state__15880)))
           (state__15880))
          (state__15880)))
        (state__15880
         []
         (clojure.core/letfn
          [(def__15578
            [arg__15601 ?__14369]
            (clojure.core/letfn
             [(state__16110
               []
               (clojure.core/let
                [x__15602 "meander.zeta"]
                (if
                 (clojure.core/= ?__14369 x__15602)
                 [?__14369]
                 (state__16111))))
              (state__16111
               []
               (if
                (clojure.core/map? arg__15601)
                (clojure.core/let
                 [VAL__15603 (.valAt arg__15601 :aliases)]
                 (if
                  (clojure.core/map? VAL__15603)
                  (clojure.core/let
                   [X__15605 (clojure.core/set VAL__15603)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15605))
                    (clojure.core/loop
                     [search_space__16112 (clojure.core/seq X__15605)]
                     (if
                      (clojure.core/seq search_space__16112)
                      (clojure.core/let
                       [elem__15606
                        (clojure.core/first search_space__16112)
                        result__16113
                        (clojure.core/let
                         [elem__15606_nth_0__
                          (clojure.core/nth elem__15606 0)
                          elem__15606_nth_1__
                          (clojure.core/nth elem__15606 1)]
                         (if
                          (clojure.core/symbol? elem__15606_nth_0__)
                          (clojure.core/let
                           [X__15608
                            (clojure.core/name elem__15606_nth_0__)]
                           (if
                            (clojure.core/= ?__14369 X__15608)
                            (if
                             (clojure.core/symbol? elem__15606_nth_1__)
                             (clojure.core/let
                              [X__15610
                               (clojure.core/name elem__15606_nth_1__)]
                              (clojure.core/case
                               X__15610
                               ("meander.zeta")
                               [?__14369]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16113)
                        (recur (clojure.core/next search_space__16112))
                        result__16113))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16110)))]
          (if
           (clojure.core/vector? input__14353)
           (if
            (clojure.core/= (clojure.core/count input__14353) 2)
            (clojure.core/let
             [input__14353_nth_0__
              (clojure.core/nth input__14353 0)
              input__14353_nth_1__
              (clojure.core/nth input__14353 1)]
             (if
              (clojure.core/seq? input__14353_nth_0__)
              (clojure.core/let
               [input__14353_nth_0___l__
                (clojure.core/take 1 input__14353_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14353_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14353_nth_0___r__
                  (clojure.core/drop 1 input__14353_nth_0__)]
                 (clojure.core/let
                  [input__14353_nth_0___l___nth_0__
                   (clojure.core/nth input__14353_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14353_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15588
                     (clojure.core/namespace
                      input__14353_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14369 X__15588]
                     (clojure.core/let
                      [X__15590
                       (clojure.core/name
                        input__14353_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15590
                       ("pred")
                       (clojure.core/let
                        [x__7935__auto__
                         (def__15578 input__14353_nth_1__ ?__14369)]
                        (if
                         (meander.runtime.zeta/fail? x__7935__auto__)
                         (state__15881)
                         (clojure.core/let
                          [[?__14369] x__7935__auto__]
                          (if
                           (clojure.core/vector? input__14353)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14353)
                             2)
                            (clojure.core/let
                             [input__14353_nth_0__
                              (clojure.core/nth input__14353 0)
                              input__14353_nth_1__
                              (clojure.core/nth input__14353 1)]
                             (if
                              (clojure.core/seq? input__14353_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__14353_nth_0__)
                                2)
                               (clojure.core/let
                                [input__14353_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14353_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?expression
                                  input__14353_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__14353_nth_0__]
                                  (clojure.core/let
                                   [?env input__14353_nth_1__]
                                   (try
                                    [{:tag :pred,
                                      :expression
                                      {:tag :host-expression,
                                       :form ?expression},
                                      :pattern {:tag :wildcard},
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__10178__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__10178__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__10178__auto__))))))))
                               (state__15881))
                              (state__15881)))
                            (state__15881))
                           (state__15881)))))
                       (state__15881)))))
                   (state__15881))))
                (state__15881)))
              (state__15881)))
            (state__15881))
           (state__15881))))
        (state__15881
         []
         (clojure.core/letfn
          [(def__15612
            [arg__15635 ?__14370]
            (clojure.core/letfn
             [(state__16115
               []
               (clojure.core/let
                [x__15636 "meander.zeta"]
                (if
                 (clojure.core/= ?__14370 x__15636)
                 [?__14370]
                 (state__16116))))
              (state__16116
               []
               (if
                (clojure.core/map? arg__15635)
                (clojure.core/let
                 [VAL__15637 (.valAt arg__15635 :aliases)]
                 (if
                  (clojure.core/map? VAL__15637)
                  (clojure.core/let
                   [X__15639 (clojure.core/set VAL__15637)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15639))
                    (clojure.core/loop
                     [search_space__16117 (clojure.core/seq X__15639)]
                     (if
                      (clojure.core/seq search_space__16117)
                      (clojure.core/let
                       [elem__15640
                        (clojure.core/first search_space__16117)
                        result__16118
                        (clojure.core/let
                         [elem__15640_nth_0__
                          (clojure.core/nth elem__15640 0)
                          elem__15640_nth_1__
                          (clojure.core/nth elem__15640 1)]
                         (if
                          (clojure.core/symbol? elem__15640_nth_0__)
                          (clojure.core/let
                           [X__15642
                            (clojure.core/name elem__15640_nth_0__)]
                           (if
                            (clojure.core/= ?__14370 X__15642)
                            (if
                             (clojure.core/symbol? elem__15640_nth_1__)
                             (clojure.core/let
                              [X__15644
                               (clojure.core/name elem__15640_nth_1__)]
                              (clojure.core/case
                               X__15644
                               ("meander.zeta")
                               [?__14370]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16118)
                        (recur (clojure.core/next search_space__16117))
                        result__16118))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16115)))]
          (if
           (clojure.core/vector? input__14353)
           (if
            (clojure.core/= (clojure.core/count input__14353) 2)
            (clojure.core/let
             [input__14353_nth_0__
              (clojure.core/nth input__14353 0)
              input__14353_nth_1__
              (clojure.core/nth input__14353 1)]
             (if
              (clojure.core/seq? input__14353_nth_0__)
              (clojure.core/let
               [input__14353_nth_0___l__
                (clojure.core/take 1 input__14353_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14353_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14353_nth_0___r__
                  (clojure.core/drop 1 input__14353_nth_0__)]
                 (clojure.core/let
                  [input__14353_nth_0___l___nth_0__
                   (clojure.core/nth input__14353_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14353_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15622
                     (clojure.core/namespace
                      input__14353_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14370 X__15622]
                     (clojure.core/let
                      [X__15624
                       (clojure.core/name
                        input__14353_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15624
                       ("pred")
                       (clojure.core/let
                        [x__7935__auto__
                         (def__15612 input__14353_nth_1__ ?__14370)]
                        (if
                         (meander.runtime.zeta/fail? x__7935__auto__)
                         (state__15882)
                         (clojure.core/let
                          [[?__14370] x__7935__auto__]
                          (if
                           (clojure.core/vector? input__14353)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14353)
                             2)
                            (clojure.core/let
                             [input__14353_nth_0__
                              (clojure.core/nth input__14353 0)
                              input__14353_nth_1__
                              (clojure.core/nth input__14353 1)]
                             (if
                              (clojure.core/seq? input__14353_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__14353_nth_0__)
                                3)
                               (clojure.core/let
                                [input__14353_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14353_nth_0__
                                  1)
                                 input__14353_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__14353_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?expression
                                  input__14353_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?pattern
                                   input__14353_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__14353_nth_0__]
                                   (clojure.core/let
                                    [?env input__14353_nth_1__]
                                    (try
                                     [{:tag :pred,
                                       :expression
                                       {:tag :host-expression,
                                        :form ?expression},
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__9238__auto__
                                         (CATA__FN__14430
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
                               (state__15882))
                              (state__15882)))
                            (state__15882))
                           (state__15882)))))
                       (state__15882)))))
                   (state__15882))))
                (state__15882)))
              (state__15882)))
            (state__15882))
           (state__15882))))
        (state__15882
         []
         (clojure.core/letfn
          [(def__15646
            [arg__15669 ?__14371]
            (clojure.core/letfn
             [(state__16120
               []
               (clojure.core/let
                [x__15670 "meander.zeta"]
                (if
                 (clojure.core/= ?__14371 x__15670)
                 [?__14371]
                 (state__16121))))
              (state__16121
               []
               (if
                (clojure.core/map? arg__15669)
                (clojure.core/let
                 [VAL__15671 (.valAt arg__15669 :aliases)]
                 (if
                  (clojure.core/map? VAL__15671)
                  (clojure.core/let
                   [X__15673 (clojure.core/set VAL__15671)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15673))
                    (clojure.core/loop
                     [search_space__16122 (clojure.core/seq X__15673)]
                     (if
                      (clojure.core/seq search_space__16122)
                      (clojure.core/let
                       [elem__15674
                        (clojure.core/first search_space__16122)
                        result__16123
                        (clojure.core/let
                         [elem__15674_nth_0__
                          (clojure.core/nth elem__15674 0)
                          elem__15674_nth_1__
                          (clojure.core/nth elem__15674 1)]
                         (if
                          (clojure.core/symbol? elem__15674_nth_0__)
                          (clojure.core/let
                           [X__15676
                            (clojure.core/name elem__15674_nth_0__)]
                           (if
                            (clojure.core/= ?__14371 X__15676)
                            (if
                             (clojure.core/symbol? elem__15674_nth_1__)
                             (clojure.core/let
                              [X__15678
                               (clojure.core/name elem__15674_nth_1__)]
                              (clojure.core/case
                               X__15678
                               ("meander.zeta")
                               [?__14371]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16123)
                        (recur (clojure.core/next search_space__16122))
                        result__16123))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16120)))]
          (if
           (clojure.core/vector? input__14353)
           (if
            (clojure.core/= (clojure.core/count input__14353) 2)
            (clojure.core/let
             [input__14353_nth_0__
              (clojure.core/nth input__14353 0)
              input__14353_nth_1__
              (clojure.core/nth input__14353 1)]
             (if
              (clojure.core/seq? input__14353_nth_0__)
              (clojure.core/let
               [input__14353_nth_0___l__
                (clojure.core/take 1 input__14353_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14353_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14353_nth_0___r__
                  (clojure.core/drop 1 input__14353_nth_0__)]
                 (clojure.core/let
                  [input__14353_nth_0___l___nth_0__
                   (clojure.core/nth input__14353_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14353_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15656
                     (clojure.core/namespace
                      input__14353_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14371 X__15656]
                     (clojure.core/let
                      [X__15658
                       (clojure.core/name
                        input__14353_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15658
                       ("re")
                       (clojure.core/let
                        [x__7935__auto__
                         (def__15646 input__14353_nth_1__ ?__14371)]
                        (if
                         (meander.runtime.zeta/fail? x__7935__auto__)
                         (state__15883)
                         (clojure.core/let
                          [[?__14371] x__7935__auto__]
                          (if
                           (clojure.core/vector? input__14353)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14353)
                             2)
                            (clojure.core/let
                             [input__14353_nth_0__
                              (clojure.core/nth input__14353 0)
                              input__14353_nth_1__
                              (clojure.core/nth input__14353 1)]
                             (if
                              (clojure.core/seq? input__14353_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__14353_nth_0__)
                                2)
                               (clojure.core/let
                                [input__14353_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14353_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?regex input__14353_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__14353_nth_0__]
                                  (clojure.core/let
                                   [?env input__14353_nth_1__]
                                   (try
                                    [{:tag :regex,
                                      :regex ?regex,
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__10178__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__10178__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__10178__auto__))))))))
                               (state__15883))
                              (state__15883)))
                            (state__15883))
                           (state__15883)))))
                       (state__15883)))))
                   (state__15883))))
                (state__15883)))
              (state__15883)))
            (state__15883))
           (state__15883))))
        (state__15883
         []
         (clojure.core/letfn
          [(def__15680
            [arg__15703 ?__14372]
            (clojure.core/letfn
             [(state__16125
               []
               (clojure.core/let
                [x__15704 "meander.zeta"]
                (if
                 (clojure.core/= ?__14372 x__15704)
                 [?__14372]
                 (state__16126))))
              (state__16126
               []
               (if
                (clojure.core/map? arg__15703)
                (clojure.core/let
                 [VAL__15705 (.valAt arg__15703 :aliases)]
                 (if
                  (clojure.core/map? VAL__15705)
                  (clojure.core/let
                   [X__15707 (clojure.core/set VAL__15705)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15707))
                    (clojure.core/loop
                     [search_space__16127 (clojure.core/seq X__15707)]
                     (if
                      (clojure.core/seq search_space__16127)
                      (clojure.core/let
                       [elem__15708
                        (clojure.core/first search_space__16127)
                        result__16128
                        (clojure.core/let
                         [elem__15708_nth_0__
                          (clojure.core/nth elem__15708 0)
                          elem__15708_nth_1__
                          (clojure.core/nth elem__15708 1)]
                         (if
                          (clojure.core/symbol? elem__15708_nth_0__)
                          (clojure.core/let
                           [X__15710
                            (clojure.core/name elem__15708_nth_0__)]
                           (if
                            (clojure.core/= ?__14372 X__15710)
                            (if
                             (clojure.core/symbol? elem__15708_nth_1__)
                             (clojure.core/let
                              [X__15712
                               (clojure.core/name elem__15708_nth_1__)]
                              (clojure.core/case
                               X__15712
                               ("meander.zeta")
                               [?__14372]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16128)
                        (recur (clojure.core/next search_space__16127))
                        result__16128))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16125)))]
          (if
           (clojure.core/vector? input__14353)
           (if
            (clojure.core/= (clojure.core/count input__14353) 2)
            (clojure.core/let
             [input__14353_nth_0__
              (clojure.core/nth input__14353 0)
              input__14353_nth_1__
              (clojure.core/nth input__14353 1)]
             (if
              (clojure.core/seq? input__14353_nth_0__)
              (clojure.core/let
               [input__14353_nth_0___l__
                (clojure.core/take 1 input__14353_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14353_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14353_nth_0___r__
                  (clojure.core/drop 1 input__14353_nth_0__)]
                 (clojure.core/let
                  [input__14353_nth_0___l___nth_0__
                   (clojure.core/nth input__14353_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14353_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15690
                     (clojure.core/namespace
                      input__14353_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14372 X__15690]
                     (clojure.core/let
                      [X__15692
                       (clojure.core/name
                        input__14353_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15692
                       ("re")
                       (clojure.core/let
                        [x__7935__auto__
                         (def__15680 input__14353_nth_1__ ?__14372)]
                        (if
                         (meander.runtime.zeta/fail? x__7935__auto__)
                         (state__15884)
                         (clojure.core/let
                          [[?__14372] x__7935__auto__]
                          (if
                           (clojure.core/vector? input__14353)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14353)
                             2)
                            (clojure.core/let
                             [input__14353_nth_0__
                              (clojure.core/nth input__14353 0)
                              input__14353_nth_1__
                              (clojure.core/nth input__14353 1)]
                             (if
                              (clojure.core/seq? input__14353_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__14353_nth_0__)
                                3)
                               (clojure.core/let
                                [input__14353_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14353_nth_0__
                                  1)
                                 input__14353_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__14353_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?regex input__14353_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?capture
                                   input__14353_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__14353_nth_0__]
                                   (clojure.core/let
                                    [?env input__14353_nth_1__]
                                    (try
                                     [{:tag :regex-with-capture,
                                       :regex ?regex,
                                       :capture
                                       (clojure.core/let
                                        [CATA_RESULT__9238__auto__
                                         (CATA__FN__14430
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
                               (state__15884))
                              (state__15884)))
                            (state__15884))
                           (state__15884)))))
                       (state__15884)))))
                   (state__15884))))
                (state__15884)))
              (state__15884)))
            (state__15884))
           (state__15884))))
        (state__15884
         []
         (clojure.core/letfn
          [(def__15714
            [arg__15737 ?__14373]
            (clojure.core/letfn
             [(state__16130
               []
               (clojure.core/let
                [x__15738 "meander.zeta"]
                (if
                 (clojure.core/= ?__14373 x__15738)
                 [?__14373]
                 (state__16131))))
              (state__16131
               []
               (if
                (clojure.core/map? arg__15737)
                (clojure.core/let
                 [VAL__15739 (.valAt arg__15737 :aliases)]
                 (if
                  (clojure.core/map? VAL__15739)
                  (clojure.core/let
                   [X__15741 (clojure.core/set VAL__15739)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15741))
                    (clojure.core/loop
                     [search_space__16132 (clojure.core/seq X__15741)]
                     (if
                      (clojure.core/seq search_space__16132)
                      (clojure.core/let
                       [elem__15742
                        (clojure.core/first search_space__16132)
                        result__16133
                        (clojure.core/let
                         [elem__15742_nth_0__
                          (clojure.core/nth elem__15742 0)
                          elem__15742_nth_1__
                          (clojure.core/nth elem__15742 1)]
                         (if
                          (clojure.core/symbol? elem__15742_nth_0__)
                          (clojure.core/let
                           [X__15744
                            (clojure.core/name elem__15742_nth_0__)]
                           (if
                            (clojure.core/= ?__14373 X__15744)
                            (if
                             (clojure.core/symbol? elem__15742_nth_1__)
                             (clojure.core/let
                              [X__15746
                               (clojure.core/name elem__15742_nth_1__)]
                              (clojure.core/case
                               X__15746
                               ("meander.zeta")
                               [?__14373]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16133)
                        (recur (clojure.core/next search_space__16132))
                        result__16133))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16130)))]
          (if
           (clojure.core/vector? input__14353)
           (if
            (clojure.core/= (clojure.core/count input__14353) 2)
            (clojure.core/let
             [input__14353_nth_0__
              (clojure.core/nth input__14353 0)
              input__14353_nth_1__
              (clojure.core/nth input__14353 1)]
             (if
              (clojure.core/seq? input__14353_nth_0__)
              (clojure.core/let
               [input__14353_nth_0___l__
                (clojure.core/take 1 input__14353_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14353_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14353_nth_0___r__
                  (clojure.core/drop 1 input__14353_nth_0__)]
                 (clojure.core/let
                  [input__14353_nth_0___l___nth_0__
                   (clojure.core/nth input__14353_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14353_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15724
                     (clojure.core/namespace
                      input__14353_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14373 X__15724]
                     (clojure.core/let
                      [X__15726
                       (clojure.core/name
                        input__14353_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15726
                       ("string")
                       (clojure.core/let
                        [x__7935__auto__
                         (def__15714 input__14353_nth_1__ ?__14373)]
                        (if
                         (meander.runtime.zeta/fail? x__7935__auto__)
                         (state__15885)
                         (clojure.core/let
                          [[?__14373] x__7935__auto__]
                          (if
                           (clojure.core/vector? input__14353)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14353)
                             2)
                            (clojure.core/let
                             [input__14353_nth_0__
                              (clojure.core/nth input__14353 0)
                              input__14353_nth_1__
                              (clojure.core/nth input__14353 1)]
                             (if
                              (clojure.core/seq? input__14353_nth_0__)
                              (clojure.core/let
                               [input__14353_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__14353_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__14353_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__14353_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__14353_nth_0__)]
                                 (clojure.core/let
                                  [?sequence input__14353_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__14353_nth_0__]
                                   (clojure.core/let
                                    [?env input__14353_nth_1__]
                                    (try
                                     [{:tag :string,
                                       :next
                                       (clojure.core/let
                                        [CATA_RESULT__9238__auto__
                                         (CATA__FN__14430
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
                                (state__15885)))
                              (state__15885)))
                            (state__15885))
                           (state__15885)))))
                       (state__15885)))))
                   (state__15885))))
                (state__15885)))
              (state__15885)))
            (state__15885))
           (state__15885))))
        (state__15885
         []
         (clojure.core/letfn
          [(def__15748
            [arg__15771 ?__14374]
            (clojure.core/letfn
             [(state__16135
               []
               (clojure.core/let
                [x__15772 "meander.zeta"]
                (if
                 (clojure.core/= ?__14374 x__15772)
                 [?__14374]
                 (state__16136))))
              (state__16136
               []
               (if
                (clojure.core/map? arg__15771)
                (clojure.core/let
                 [VAL__15773 (.valAt arg__15771 :aliases)]
                 (if
                  (clojure.core/map? VAL__15773)
                  (clojure.core/let
                   [X__15775 (clojure.core/set VAL__15773)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15775))
                    (clojure.core/loop
                     [search_space__16137 (clojure.core/seq X__15775)]
                     (if
                      (clojure.core/seq search_space__16137)
                      (clojure.core/let
                       [elem__15776
                        (clojure.core/first search_space__16137)
                        result__16138
                        (clojure.core/let
                         [elem__15776_nth_0__
                          (clojure.core/nth elem__15776 0)
                          elem__15776_nth_1__
                          (clojure.core/nth elem__15776 1)]
                         (if
                          (clojure.core/symbol? elem__15776_nth_0__)
                          (clojure.core/let
                           [X__15778
                            (clojure.core/name elem__15776_nth_0__)]
                           (if
                            (clojure.core/= ?__14374 X__15778)
                            (if
                             (clojure.core/symbol? elem__15776_nth_1__)
                             (clojure.core/let
                              [X__15780
                               (clojure.core/name elem__15776_nth_1__)]
                              (clojure.core/case
                               X__15780
                               ("meander.zeta")
                               [?__14374]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16138)
                        (recur (clojure.core/next search_space__16137))
                        result__16138))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16135)))]
          (if
           (clojure.core/vector? input__14353)
           (if
            (clojure.core/= (clojure.core/count input__14353) 2)
            (clojure.core/let
             [input__14353_nth_0__
              (clojure.core/nth input__14353 0)
              input__14353_nth_1__
              (clojure.core/nth input__14353 1)]
             (if
              (clojure.core/seq? input__14353_nth_0__)
              (clojure.core/let
               [input__14353_nth_0___l__
                (clojure.core/take 1 input__14353_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14353_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14353_nth_0___r__
                  (clojure.core/drop 1 input__14353_nth_0__)]
                 (clojure.core/let
                  [input__14353_nth_0___l___nth_0__
                   (clojure.core/nth input__14353_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14353_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15758
                     (clojure.core/namespace
                      input__14353_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14374 X__15758]
                     (clojure.core/let
                      [X__15760
                       (clojure.core/name
                        input__14353_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15760
                       ("symbol")
                       (clojure.core/let
                        [x__7935__auto__
                         (def__15748 input__14353_nth_1__ ?__14374)]
                        (if
                         (meander.runtime.zeta/fail? x__7935__auto__)
                         (state__15886)
                         (clojure.core/let
                          [[?__14374] x__7935__auto__]
                          (if
                           (clojure.core/vector? input__14353)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14353)
                             2)
                            (clojure.core/let
                             [input__14353_nth_0__
                              (clojure.core/nth input__14353 0)
                              input__14353_nth_1__
                              (clojure.core/nth input__14353 1)]
                             (if
                              (clojure.core/seq? input__14353_nth_0__)
                              (clojure.core/let
                               [input__14353_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__14353_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__14353_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__14353_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__14353_nth_0__)]
                                 (clojure.core/let
                                  [?symbol-args
                                   input__14353_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__14353_nth_0__]
                                   (clojure.core/let
                                    [?env input__14353_nth_1__]
                                    (try
                                     [(clojure.core/let
                                       [CATA_RESULT__9238__auto__
                                        (CATA__FN__14430
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
                                (state__15886)))
                              (state__15886)))
                            (state__15886))
                           (state__15886)))))
                       (state__15886)))))
                   (state__15886))))
                (state__15886)))
              (state__15886)))
            (state__15886))
           (state__15886))))
        (state__15886
         []
         (if
          (clojure.core/vector? input__14353)
          (clojure.core/letfn
           [(state__16140
             []
             (if
              (clojure.core/= (clojure.core/count input__14353) 4)
              (clojure.core/let
               [input__14353_nth_0__
                (clojure.core/nth input__14353 0)
                input__14353_nth_1__
                (clojure.core/nth input__14353 1)
                input__14353_nth_2__
                (clojure.core/nth input__14353 2)]
               (clojure.core/letfn
                [(state__16142
                  []
                  (clojure.core/case
                   input__14353_nth_0__
                   (meander.dev.parse.zeta/make-symbol)
                   (if
                    (clojure.core/vector? input__14353_nth_1__)
                    (clojure.core/case
                     input__14353_nth_1__
                     ([])
                     (clojure.core/let
                      [?form input__14353_nth_2__]
                      (try
                       [{:tag :symbol,
                         :namespace {:tag :wildcard},
                         :name {:tag :wildcard},
                         :form ?form}]
                       (catch
                        java.lang.Exception
                        e__10178__auto__
                        (if
                         (meander.runtime.zeta/fail? e__10178__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__10178__auto__)))))
                     (state__16143))
                    (state__16143))
                   (state__16143)))
                 (state__16143
                  []
                  (clojure.core/let
                   [input__14353_nth_3__
                    (clojure.core/nth input__14353 3)]
                   (clojure.core/case
                    input__14353_nth_0__
                    (meander.dev.parse.zeta/make-symbol)
                    (if
                     (clojure.core/vector? input__14353_nth_1__)
                     (clojure.core/letfn
                      [(state__16144
                        []
                        (if
                         (clojure.core/=
                          (clojure.core/count input__14353_nth_1__)
                          1)
                         (clojure.core/let
                          [input__14353_nth_1___nth_0__
                           (clojure.core/nth input__14353_nth_1__ 0)]
                          (clojure.core/let
                           [?name input__14353_nth_1___nth_0__]
                           (clojure.core/let
                            [?form input__14353_nth_2__]
                            (clojure.core/let
                             [?env input__14353_nth_3__]
                             (try
                              [{:tag :symbol,
                                :namespace {:tag :wildcard},
                                :name
                                (clojure.core/let
                                 [CATA_RESULT__9238__auto__
                                  (CATA__FN__14430 [?name ?env])]
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
                         (state__16145)))
                       (state__16145
                        []
                        (if
                         (clojure.core/=
                          (clojure.core/count input__14353_nth_1__)
                          2)
                         (clojure.core/let
                          [input__14353_nth_1___nth_0__
                           (clojure.core/nth input__14353_nth_1__ 0)
                           input__14353_nth_1___nth_1__
                           (clojure.core/nth input__14353_nth_1__ 1)]
                          (clojure.core/let
                           [?namespace input__14353_nth_1___nth_0__]
                           (clojure.core/let
                            [?name input__14353_nth_1___nth_1__]
                            (clojure.core/let
                             [?form input__14353_nth_2__]
                             (clojure.core/let
                              [?env input__14353_nth_3__]
                              (try
                               [{:tag :symbol,
                                 :namespace
                                 (clojure.core/let
                                  [CATA_RESULT__9238__auto__
                                   (CATA__FN__14430 [?namespace ?env])]
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
                                   (CATA__FN__14430 [?name ?env])]
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
                         (state__16141)))]
                      (state__16144))
                     (state__16141))
                    (state__16141))))]
                (state__16142)))
              (state__16141)))
            (state__16141
             []
             (if
              (clojure.core/= (clojure.core/count input__14353) 2)
              (clojure.core/let
               [input__14353_nth_0__ (clojure.core/nth input__14353 0)]
               (clojure.core/letfn
                [(state__16146
                  []
                  (clojure.core/let
                   [input__14353_nth_1__
                    (clojure.core/nth input__14353 1)]
                   (clojure.core/letfn
                    [(state__16151
                      []
                      (if
                       (clojure.core/seq? input__14353_nth_0__)
                       (clojure.core/let
                        [?sequence input__14353_nth_0__]
                        (clojure.core/let
                         [?env input__14353_nth_1__]
                         (try
                          [{:tag :seq,
                            :next
                            (clojure.core/let
                             [CATA_RESULT__9238__auto__
                              (CATA__FN__14430
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
                       (state__16152)))
                     (state__16152
                      []
                      (if
                       (clojure.core/map? input__14353_nth_0__)
                       (clojure.core/let
                        [?map input__14353_nth_0__]
                        (clojure.core/let
                         [?env input__14353_nth_1__]
                         (try
                          [{:tag :map,
                            :next
                            (clojure.core/let
                             [CATA_RESULT__9238__auto__
                              (CATA__FN__14430
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
                       (state__16147)))]
                    (state__16151))))
                 (state__16147
                  []
                  (if
                   (clojure.core/symbol? input__14353_nth_0__)
                   (clojure.core/let
                    [X__15800
                     (clojure.core/namespace input__14353_nth_0__)]
                    (clojure.core/let
                     [X__15802
                      (clojure.core/name input__14353_nth_0__)]
                     (if
                      (clojure.core/string? X__15802)
                      (clojure.core/letfn
                       [(state__16153
                         []
                         (clojure.core/let
                          [ret__15803
                           (clojure.core/re-matches #"_.*" X__15802)]
                          (if
                           (clojure.core/some? ret__15803)
                           (clojure.core/let
                            [?name ret__15803]
                            (clojure.core/let
                             [?symbol input__14353_nth_0__]
                             (try
                              [{:tag :wildcard,
                                :name ?name,
                                :form ?symbol,
                                :symbol ?symbol}]
                              (catch
                               java.lang.Exception
                               e__10178__auto__
                               (if
                                (meander.runtime.zeta/fail?
                                 e__10178__auto__)
                                (meander.runtime.zeta/fail)
                                (throw e__10178__auto__))))))
                           (state__16154))))
                        (state__16154
                         []
                         (clojure.core/let
                          [ret__15810
                           (clojure.core/re-matches #".+#" X__15802)]
                          (if
                           (clojure.core/some? ret__15810)
                           (clojure.core/let
                            [?name ret__15810]
                            (clojure.core/let
                             [?symbol input__14353_nth_0__]
                             (try
                              [{:tag :random-symbol,
                                :name ?name,
                                :form ?symbol,
                                :symbol ?symbol}]
                              (catch
                               java.lang.Exception
                               e__10178__auto__
                               (if
                                (meander.runtime.zeta/fail?
                                 e__10178__auto__)
                                (meander.runtime.zeta/fail)
                                (throw e__10178__auto__))))))
                           (state__16155))))
                        (state__16155
                         []
                         (clojure.core/let
                          [ret__15817
                           (clojure.core/re-matches #"%.+" X__15802)]
                          (if
                           (clojure.core/some? ret__15817)
                           (clojure.core/let
                            [?name ret__15817]
                            (clojure.core/let
                             [?symbol input__14353_nth_0__]
                             (try
                              [{:tag :reference,
                                :name ?name,
                                :symbol ?symbol}]
                              (catch
                               java.lang.Exception
                               e__10178__auto__
                               (if
                                (meander.runtime.zeta/fail?
                                 e__10178__auto__)
                                (meander.runtime.zeta/fail)
                                (throw e__10178__auto__))))))
                           (state__16156))))
                        (state__16156
                         []
                         (clojure.core/let
                          [ret__15824
                           (clojure.core/re-matches #"\*.+" X__15802)]
                          (if
                           (clojure.core/some? ret__15824)
                           (clojure.core/let
                            [?name ret__15824]
                            (clojure.core/let
                             [?symbol input__14353_nth_0__]
                             (try
                              [{:tag :mutable-variable,
                                :name ?name,
                                :symbol ?symbol}]
                              (catch
                               java.lang.Exception
                               e__10178__auto__
                               (if
                                (meander.runtime.zeta/fail?
                                 e__10178__auto__)
                                (meander.runtime.zeta/fail)
                                (throw e__10178__auto__))))))
                           (state__16157))))
                        (state__16157
                         []
                         (clojure.core/let
                          [ret__15831
                           (clojure.core/re-matches #"\!.+" X__15802)]
                          (if
                           (clojure.core/some? ret__15831)
                           (clojure.core/let
                            [?name ret__15831]
                            (clojure.core/let
                             [?symbol input__14353_nth_0__]
                             (try
                              [{:tag :memory-variable,
                                :name ?name,
                                :symbol ?symbol}]
                              (catch
                               java.lang.Exception
                               e__10178__auto__
                               (if
                                (meander.runtime.zeta/fail?
                                 e__10178__auto__)
                                (meander.runtime.zeta/fail)
                                (throw e__10178__auto__))))))
                           (state__16158))))
                        (state__16158
                         []
                         (clojure.core/let
                          [ret__15838
                           (clojure.core/re-matches #"\?.+" X__15802)]
                          (if
                           (clojure.core/some? ret__15838)
                           (clojure.core/let
                            [?name ret__15838]
                            (clojure.core/let
                             [?symbol input__14353_nth_0__]
                             (try
                              [{:tag :logic-variable,
                                :name ?name,
                                :symbol ?symbol}]
                              (catch
                               java.lang.Exception
                               e__10178__auto__
                               (if
                                (meander.runtime.zeta/fail?
                                 e__10178__auto__)
                                (meander.runtime.zeta/fail)
                                (throw e__10178__auto__))))))
                           (state__16148))))]
                       (state__16153))
                      (state__16148))))
                   (state__16148)))
                 (state__16148
                  []
                  (if
                   (string? input__14353_nth_0__)
                   (clojure.core/let
                    [?x input__14353_nth_0__]
                    (try
                     [{:tag :literal, :type :string, :form ?x}]
                     (catch
                      java.lang.Exception
                      e__10178__auto__
                      (if
                       (meander.runtime.zeta/fail? e__10178__auto__)
                       (meander.runtime.zeta/fail)
                       (throw e__10178__auto__)))))
                   (state__16149)))
                 (state__16149
                  []
                  (if
                   (char? input__14353_nth_0__)
                   (clojure.core/let
                    [?x input__14353_nth_0__]
                    (try
                     [{:tag :literal, :type :char, :form ?x}]
                     (catch
                      java.lang.Exception
                      e__10178__auto__
                      (if
                       (meander.runtime.zeta/fail? e__10178__auto__)
                       (meander.runtime.zeta/fail)
                       (throw e__10178__auto__)))))
                   (state__16150)))
                 (state__16150
                  []
                  (clojure.core/let
                   [?x input__14353_nth_0__]
                   (try
                    [{:tag :literal, :form ?x}]
                    (catch
                     java.lang.Exception
                     e__10178__auto__
                     (if
                      (meander.runtime.zeta/fail? e__10178__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__10178__auto__))))))]
                (state__16146)))
              (state__15887)))]
           (state__16140))
          (state__15887)))
        (state__15887
         []
         (clojure.core/let
          [?x input__14353]
          (try
           [{:tag :mistake, :x ?x}]
           (catch
            java.lang.Exception
            e__10178__auto__
            (if
             (meander.runtime.zeta/fail? e__10178__auto__)
             (meander.runtime.zeta/fail)
             (throw e__10178__auto__))))))]
       (state__15849)))]
    (clojure.core/let
     [x__7935__auto__ (CATA__FN__14430 input__14353)]
     (if
      (meander.runtime.zeta/fail? x__7935__auto__)
      (meander.runtime.zeta/fail)
      (clojure.core/let
       [[CATA_RETURN__14434] x__7935__auto__]
       CATA_RETURN__14434))))]
  (if
   (meander.runtime.zeta/fail? ret__8115__auto__)
   nil
   ret__8115__auto__)))
