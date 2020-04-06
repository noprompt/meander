(ns meander.compiled.parse.zeta (:require [meander.runtime.zeta]))
(clojure.core/defn
 parse
 [input__53984]
 (let*
  [ret__14518__auto__
   (clojure.core/letfn
    [(CATA__FN__54064
      [input__53984]
      (clojure.core/letfn
       [(state__55536
         []
         (if
          (clojure.core/vector? input__53984)
          (if
           (clojure.core/= (clojure.core/count input__53984) 3)
           (clojure.core/let
            [input__53984_nth_0__
             (clojure.core/nth input__53984 0)
             input__53984_nth_1__
             (clojure.core/nth input__53984 1)
             input__53984_nth_2__
             (clojure.core/nth input__53984 2)]
            (clojure.core/case
             input__53984_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__53984_nth_1__)
              (clojure.core/letfn
               [(state__55575
                 []
                 (clojure.core/case
                  input__53984_nth_1__
                  ([])
                  (clojure.core/let
                   [?env input__53984_nth_2__]
                   (try
                    [{:tag :empty}]
                    (catch
                     java.lang.Exception
                     e__16581__auto__
                     (if
                      (meander.runtime.zeta/fail? e__16581__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__16581__auto__)))))
                  (state__55576)))
                (state__55576
                 []
                 (clojure.core/let
                  [n__54073
                   (clojure.core/count input__53984_nth_1__)
                   m__54074
                   (clojure.core/max 0 (clojure.core/- n__54073 2))
                   input__53984_nth_1___l__
                   (clojure.core/subvec
                    input__53984_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__53984_nth_1__)
                     m__54074))
                   input__53984_nth_1___r__
                   (clojure.core/subvec input__53984_nth_1__ m__54074)]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__53984_nth_1___r__)
                    2)
                   (clojure.core/let
                    [!xs (clojure.core/vec input__53984_nth_1___l__)]
                    (if
                     (clojure.core/=
                      (clojure.core/count input__53984_nth_1___r__)
                      2)
                     (clojure.core/let
                      [input__53984_nth_1___r___nth_0__
                       (clojure.core/nth input__53984_nth_1___r__ 0)
                       input__53984_nth_1___r___nth_1__
                       (clojure.core/nth input__53984_nth_1___r__ 1)]
                      (clojure.core/case
                       input__53984_nth_1___r___nth_0__
                       (:meander.zeta/as)
                       (clojure.core/let
                        [?pattern input__53984_nth_1___r___nth_1__]
                        (clojure.core/let
                         [?env input__53984_nth_2__]
                         (try
                          [(clojure.core/let
                            [!xs__counter
                             (meander.runtime.zeta/iterator !xs)]
                            {:tag :as,
                             :pattern
                             (clojure.core/let
                              [CATA_RESULT__15641__auto__
                               (CATA__FN__54064 [?pattern ?env])]
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
                               (CATA__FN__54064
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
                       (state__55537)))
                     (state__55537)))
                   (state__55537))))]
               (state__55575))
              (state__55537))
             (state__55537)))
           (state__55537))
          (state__55537)))
        (state__55537
         []
         (clojure.core/letfn
          [(def__54079
            [arg__54114 ?ns]
            (clojure.core/letfn
             [(state__55577
               []
               (clojure.core/let
                [x__54115 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__54115)
                 (clojure.core/let [?env arg__54114] [?env ?ns])
                 (state__55578))))
              (state__55578
               []
               (if
                (clojure.core/map? arg__54114)
                (clojure.core/let
                 [VAL__54116 (.valAt arg__54114 :aliases)]
                 (if
                  (clojure.core/map? VAL__54116)
                  (clojure.core/let
                   [X__54118 (clojure.core/set VAL__54116)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__54118))
                    (clojure.core/loop
                     [search_space__55579 (clojure.core/seq X__54118)]
                     (if
                      (clojure.core/seq search_space__55579)
                      (clojure.core/let
                       [elem__54119
                        (clojure.core/first search_space__55579)
                        result__55580
                        (clojure.core/let
                         [elem__54119_nth_0__
                          (clojure.core/nth elem__54119 0)
                          elem__54119_nth_1__
                          (clojure.core/nth elem__54119 1)]
                         (if
                          (clojure.core/symbol? elem__54119_nth_0__)
                          (clojure.core/let
                           [X__54121
                            (clojure.core/name elem__54119_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__54121)
                            (if
                             (clojure.core/symbol? elem__54119_nth_1__)
                             (clojure.core/let
                              [X__54123
                               (clojure.core/name elem__54119_nth_1__)]
                              (clojure.core/case
                               X__54123
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__54114]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__55580)
                        (recur (clojure.core/next search_space__55579))
                        result__55580))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__55577)))]
          (if
           (clojure.core/vector? input__53984)
           (if
            (clojure.core/= (clojure.core/count input__53984) 3)
            (clojure.core/let
             [input__53984_nth_0__
              (clojure.core/nth input__53984 0)
              input__53984_nth_1__
              (clojure.core/nth input__53984 1)
              input__53984_nth_2__
              (clojure.core/nth input__53984 2)]
             (clojure.core/case
              input__53984_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__53984_nth_1__)
               (clojure.core/loop
                [search_space__55582
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__53984_nth_1__)]
                (if
                 (clojure.core/seq search_space__55582)
                 (clojure.core/let
                  [input__53984_nth_1___parts__
                   (clojure.core/first search_space__55582)
                   result__55583
                   (clojure.core/let
                    [input__53984_nth_1___l__
                     (clojure.core/nth input__53984_nth_1___parts__ 0)
                     input__53984_nth_1___r__
                     (clojure.core/nth input__53984_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__53984_nth_1___l__)]
                     (clojure.core/let
                      [input__53984_nth_1___r___l__
                       (clojure.core/subvec
                        input__53984_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__53984_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__53984_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__53984_nth_1___r___r__
                         (clojure.core/subvec
                          input__53984_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__53984_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__53984_nth_1___r___l__
                           0)
                          input__53984_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__53984_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__53984_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__54088
                            (clojure.core/namespace
                             input__53984_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__54088]
                            (clojure.core/let
                             [X__54090
                              (clojure.core/name
                               input__53984_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__54090)
                              (clojure.core/let
                               [ret__54091
                                (clojure.core/re-matches
                                 #"&(\d+)"
                                 X__54090)]
                               (if
                                (clojure.core/some? ret__54091)
                                (if
                                 (clojure.core/vector? ret__54091)
                                 (if
                                  (clojure.core/=
                                   (clojure.core/count ret__54091)
                                   2)
                                  (clojure.core/let
                                   [ret__54091_nth_1__
                                    (clojure.core/nth ret__54091 1)]
                                   (clojure.core/let
                                    [?n ret__54091_nth_1__]
                                    (clojure.core/let
                                     [?pattern
                                      input__53984_nth_1___r___l___nth_1__]
                                     (clojure.core/let
                                      [?rest
                                       input__53984_nth_1___r___r__]
                                      (clojure.core/let
                                       [x__14338__auto__
                                        (def__54079
                                         input__53984_nth_2__
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
                                              (CATA__FN__54064
                                               ['meander.dev.parse.zeta/make-join
                                                (clojure.core/let
                                                 [CATA_RESULT__15641__auto__
                                                  (CATA__FN__54064
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
                                                  (CATA__FN__54064
                                                   ['meander.dev.parse.zeta/make-join
                                                    {:tag :slice,
                                                     :size
                                                     (Integer. ?n),
                                                     :pattern
                                                     (clojure.core/let
                                                      [CATA_RESULT__15641__auto__
                                                       (CATA__FN__54064
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
                                                      (CATA__FN__54064
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
                   (meander.runtime.zeta/fail? result__55583)
                   (recur (clojure.core/next search_space__55582))
                   result__55583))
                 (state__55538)))
               (state__55538))
              (state__55538)))
            (state__55538))
           (state__55538))))
        (state__55538
         []
         (clojure.core/letfn
          [(def__54136
            [arg__54168 ?ns]
            (clojure.core/letfn
             [(state__55585
               []
               (clojure.core/let
                [x__54169 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__54169)
                 (clojure.core/let [?env arg__54168] [?env ?ns])
                 (state__55586))))
              (state__55586
               []
               (if
                (clojure.core/map? arg__54168)
                (clojure.core/let
                 [VAL__54170 (.valAt arg__54168 :aliases)]
                 (if
                  (clojure.core/map? VAL__54170)
                  (clojure.core/let
                   [X__54172 (clojure.core/set VAL__54170)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__54172))
                    (clojure.core/loop
                     [search_space__55587 (clojure.core/seq X__54172)]
                     (if
                      (clojure.core/seq search_space__55587)
                      (clojure.core/let
                       [elem__54173
                        (clojure.core/first search_space__55587)
                        result__55588
                        (clojure.core/let
                         [elem__54173_nth_0__
                          (clojure.core/nth elem__54173 0)
                          elem__54173_nth_1__
                          (clojure.core/nth elem__54173 1)]
                         (if
                          (clojure.core/symbol? elem__54173_nth_0__)
                          (clojure.core/let
                           [X__54175
                            (clojure.core/name elem__54173_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__54175)
                            (if
                             (clojure.core/symbol? elem__54173_nth_1__)
                             (clojure.core/let
                              [X__54177
                               (clojure.core/name elem__54173_nth_1__)]
                              (clojure.core/case
                               X__54177
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__54168]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__55588)
                        (recur (clojure.core/next search_space__55587))
                        result__55588))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__55585)))]
          (if
           (clojure.core/vector? input__53984)
           (if
            (clojure.core/= (clojure.core/count input__53984) 3)
            (clojure.core/let
             [input__53984_nth_0__
              (clojure.core/nth input__53984 0)
              input__53984_nth_1__
              (clojure.core/nth input__53984 1)
              input__53984_nth_2__
              (clojure.core/nth input__53984 2)]
             (clojure.core/case
              input__53984_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__53984_nth_1__)
               (clojure.core/loop
                [search_space__55590
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__53984_nth_1__)]
                (if
                 (clojure.core/seq search_space__55590)
                 (clojure.core/let
                  [input__53984_nth_1___parts__
                   (clojure.core/first search_space__55590)
                   result__55591
                   (clojure.core/let
                    [input__53984_nth_1___l__
                     (clojure.core/nth input__53984_nth_1___parts__ 0)
                     input__53984_nth_1___r__
                     (clojure.core/nth input__53984_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__53984_nth_1___l__)]
                     (clojure.core/let
                      [input__53984_nth_1___r___l__
                       (clojure.core/subvec
                        input__53984_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__53984_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__53984_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__53984_nth_1___r___r__
                         (clojure.core/subvec
                          input__53984_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__53984_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__53984_nth_1___r___l__
                           0)
                          input__53984_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__53984_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__53984_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__54145
                            (clojure.core/namespace
                             input__53984_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__54145]
                            (clojure.core/let
                             [X__54147
                              (clojure.core/name
                               input__53984_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__54147)
                              (if
                               (clojure.core/re-matches
                                #"&.*"
                                X__54147)
                               (clojure.core/let
                                [?pattern
                                 input__53984_nth_1___r___l___nth_1__]
                                (clojure.core/let
                                 [?rest input__53984_nth_1___r___r__]
                                 (clojure.core/let
                                  [x__14338__auto__
                                   (def__54136
                                    input__53984_nth_2__
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
                                         (CATA__FN__54064
                                          ['meander.dev.parse.zeta/make-join
                                           (clojure.core/let
                                            [CATA_RESULT__15641__auto__
                                             (CATA__FN__54064
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
                                             (CATA__FN__54064
                                              ['meander.dev.parse.zeta/make-join
                                               (clojure.core/let
                                                [CATA_RESULT__15641__auto__
                                                 (CATA__FN__54064
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
                                                 (CATA__FN__54064
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
                   (meander.runtime.zeta/fail? result__55591)
                   (recur (clojure.core/next search_space__55590))
                   result__55591))
                 (state__55539)))
               (state__55539))
              (state__55539)))
            (state__55539))
           (state__55539))))
        (state__55539
         []
         (if
          (clojure.core/vector? input__53984)
          (clojure.core/letfn
           [(state__55593
             []
             (if
              (clojure.core/= (clojure.core/count input__53984) 3)
              (clojure.core/let
               [input__53984_nth_0__
                (clojure.core/nth input__53984 0)
                input__53984_nth_1__
                (clojure.core/nth input__53984 1)
                input__53984_nth_2__
                (clojure.core/nth input__53984 2)]
               (clojure.core/case
                input__53984_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__53984_nth_1__)
                 (clojure.core/letfn
                  [(state__55596
                    []
                    (clojure.core/let
                     [n__54198
                      (clojure.core/count input__53984_nth_1__)
                      m__54199
                      (clojure.core/max 0 (clojure.core/- n__54198 2))
                      input__53984_nth_1___l__
                      (clojure.core/subvec
                       input__53984_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__53984_nth_1__)
                        m__54199))
                      input__53984_nth_1___r__
                      (clojure.core/subvec
                       input__53984_nth_1__
                       m__54199)]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__53984_nth_1___r__)
                       2)
                      (clojure.core/let
                       [!xs
                        (clojure.core/vec input__53984_nth_1___l__)]
                       (if
                        (clojure.core/=
                         (clojure.core/count input__53984_nth_1___r__)
                         2)
                        (clojure.core/let
                         [input__53984_nth_1___r___nth_0__
                          (clojure.core/nth input__53984_nth_1___r__ 0)
                          input__53984_nth_1___r___nth_1__
                          (clojure.core/nth
                           input__53984_nth_1___r__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__53984_nth_1___r___nth_0__)
                          (clojure.core/let
                           [X__54203
                            (clojure.core/namespace
                             input__53984_nth_1___r___nth_0__)]
                           (clojure.core/let
                            [?ns X__54203]
                            (clojure.core/let
                             [X__54205
                              (clojure.core/name
                               input__53984_nth_1___r___nth_0__)]
                             (if
                              (clojure.core/string? X__54205)
                              (clojure.core/let
                               [ret__54206
                                (clojure.core/re-matches
                                 #"&.*"
                                 X__54205)]
                               (if
                                (clojure.core/some? ret__54206)
                                (clojure.core/let
                                 [?name ret__54206]
                                 (clojure.core/let
                                  [?pattern
                                   input__53984_nth_1___r___nth_1__]
                                  (if
                                   (clojure.core/map?
                                    input__53984_nth_2__)
                                   (clojure.core/let
                                    [VAL__54190
                                     (.valAt
                                      input__53984_nth_2__
                                      :aliases)]
                                    (if
                                     (clojure.core/map? VAL__54190)
                                     (clojure.core/let
                                      [X__54192
                                       (clojure.core/set VAL__54190)]
                                      (if
                                       (clojure.core/<=
                                        1
                                        (clojure.core/count X__54192))
                                       (clojure.core/loop
                                        [search_space__55600
                                         (clojure.core/seq X__54192)]
                                        (if
                                         (clojure.core/seq
                                          search_space__55600)
                                         (clojure.core/let
                                          [elem__54193
                                           (clojure.core/first
                                            search_space__55600)
                                           result__55601
                                           (clojure.core/let
                                            [elem__54193_nth_0__
                                             (clojure.core/nth
                                              elem__54193
                                              0)
                                             elem__54193_nth_1__
                                             (clojure.core/nth
                                              elem__54193
                                              1)]
                                            (if
                                             (clojure.core/symbol?
                                              elem__54193_nth_0__)
                                             (clojure.core/let
                                              [X__54195
                                               (clojure.core/name
                                                elem__54193_nth_0__)]
                                              (if
                                               (clojure.core/=
                                                ?ns
                                                X__54195)
                                               (if
                                                (clojure.core/symbol?
                                                 elem__54193_nth_1__)
                                                (clojure.core/let
                                                 [X__54197
                                                  (clojure.core/name
                                                   elem__54193_nth_1__)]
                                                 (clojure.core/case
                                                  X__54197
                                                  ("meander.zeta")
                                                  (clojure.core/let
                                                   [?env
                                                    input__53984_nth_2__]
                                                   (try
                                                    [(clojure.core/let
                                                      [!xs__counter
                                                       (meander.runtime.zeta/iterator
                                                        !xs)]
                                                      (clojure.core/let
                                                       [CATA_RESULT__15641__auto__
                                                        (CATA__FN__54064
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
                                            result__55601)
                                           (recur
                                            (clojure.core/next
                                             search_space__55600))
                                           result__55601))
                                         (state__55597)))
                                       (state__55597)))
                                     (state__55597)))
                                   (state__55597))))
                                (state__55597)))
                              (state__55597)))))
                          (state__55597)))
                        (state__55597)))
                      (state__55597))))
                   (state__55597
                    []
                    (clojure.core/loop
                     [search_space__55603
                      (meander.runtime.zeta/epsilon-partitions
                       2
                       input__53984_nth_1__)]
                     (if
                      (clojure.core/seq search_space__55603)
                      (clojure.core/let
                       [input__53984_nth_1___parts__
                        (clojure.core/first search_space__55603)
                        result__55604
                        (clojure.core/let
                         [input__53984_nth_1___l__
                          (clojure.core/nth
                           input__53984_nth_1___parts__
                           0)
                          input__53984_nth_1___r__
                          (clojure.core/nth
                           input__53984_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs
                           (clojure.core/vec input__53984_nth_1___l__)]
                          (clojure.core/let
                           [input__53984_nth_1___r___l__
                            (clojure.core/subvec
                             input__53984_nth_1___r__
                             0
                             (clojure.core/min
                              (clojure.core/count
                               input__53984_nth_1___r__)
                              1))]
                           (if
                            (clojure.core/=
                             (clojure.core/count
                              input__53984_nth_1___r___l__)
                             1)
                            (clojure.core/let
                             [input__53984_nth_1___r___r__
                              (clojure.core/subvec
                               input__53984_nth_1___r__
                               1)]
                             (if
                              (clojure.core/=
                               input__53984_nth_1___r___l__
                               ['.])
                              (clojure.core/let
                               [?rest input__53984_nth_1___r___r__]
                               (clojure.core/let
                                [?env input__53984_nth_2__]
                                (try
                                 [(clojure.core/let
                                   [!xs__counter
                                    (meander.runtime.zeta/iterator
                                     !xs)]
                                   (clojure.core/let
                                    [CATA_RESULT__15641__auto__
                                     (CATA__FN__54064
                                      ['meander.dev.parse.zeta/make-join
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__54064
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
                                         (CATA__FN__54064
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
                        (meander.runtime.zeta/fail? result__55604)
                        (recur (clojure.core/next search_space__55603))
                        result__55604))
                      (state__55598))))
                   (state__55598
                    []
                    (clojure.core/let
                     [input__53984_nth_1___l__
                      (clojure.core/subvec
                       input__53984_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__53984_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__53984_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__53984_nth_1___r__
                        (clojure.core/subvec input__53984_nth_1__ 1)]
                       (if
                        (clojure.core/=
                         input__53984_nth_1___l__
                         ['...])
                        (clojure.core/let
                         [?rest input__53984_nth_1___r__]
                         (clojure.core/let
                          [?env input__53984_nth_2__]
                          (try
                           [(clojure.core/let
                             [CATA_RESULT__15641__auto__
                              (CATA__FN__54064
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
                        (state__55599)))
                      (state__55599))))
                   (state__55599
                    []
                    (clojure.core/loop
                     [search_space__55606
                      (meander.runtime.zeta/epsilon-partitions
                       2
                       input__53984_nth_1__)]
                     (if
                      (clojure.core/seq search_space__55606)
                      (clojure.core/let
                       [input__53984_nth_1___parts__
                        (clojure.core/first search_space__55606)
                        result__55607
                        (clojure.core/let
                         [input__53984_nth_1___l__
                          (clojure.core/nth
                           input__53984_nth_1___parts__
                           0)
                          input__53984_nth_1___r__
                          (clojure.core/nth
                           input__53984_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs []]
                          (clojure.core/let
                           [ret__14502__auto__
                            (meander.runtime.zeta/epsilon-run-star-1
                             input__53984_nth_1___l__
                             [!xs]
                             (clojure.core/fn
                              [[!xs] input__54223]
                              (clojure.core/let
                               [input__54223_nth_0__
                                (clojure.core/nth input__54223 0)]
                               (clojure.core/letfn
                                [(save__54224
                                  []
                                  (meander.runtime.zeta/fail))
                                 (f__55610
                                  []
                                  (clojure.core/let
                                   [!xs
                                    (clojure.core/conj
                                     !xs
                                     input__54223_nth_0__)]
                                   [!xs]))]
                                (if
                                 (clojure.core/symbol?
                                  input__54223_nth_0__)
                                 (clojure.core/let
                                  [X__54226
                                   (clojure.core/namespace
                                    input__54223_nth_0__)]
                                  (clojure.core/case
                                   X__54226
                                   (nil)
                                   (clojure.core/let
                                    [X__54228
                                     (clojure.core/name
                                      input__54223_nth_0__)]
                                    (if
                                     (clojure.core/string? X__54228)
                                     (if
                                      (clojure.core/re-matches
                                       #"\.\.(?:\.|\d+)"
                                       X__54228)
                                      (save__54224)
                                      (f__55610))
                                     (f__55610)))
                                   (f__55610)))
                                 (f__55610)))))
                             (clojure.core/fn
                              [[!xs]]
                              (clojure.core/let
                               [input__53984_nth_1___r___l__
                                (clojure.core/subvec
                                 input__53984_nth_1___r__
                                 0
                                 (clojure.core/min
                                  (clojure.core/count
                                   input__53984_nth_1___r__)
                                  1))]
                               (if
                                (clojure.core/=
                                 (clojure.core/count
                                  input__53984_nth_1___r___l__)
                                 1)
                                (clojure.core/let
                                 [input__53984_nth_1___r___r__
                                  (clojure.core/subvec
                                   input__53984_nth_1___r__
                                   1)]
                                 (if
                                  (clojure.core/=
                                   input__53984_nth_1___r___l__
                                   ['...])
                                  (clojure.core/let
                                   [?rest input__53984_nth_1___r___r__]
                                   (clojure.core/let
                                    [?env input__53984_nth_2__]
                                    (try
                                     [(clojure.core/let
                                       [!xs__counter
                                        (meander.runtime.zeta/iterator
                                         !xs)]
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__54064
                                          ['meander.dev.parse.zeta/make-star
                                           (clojure.core/let
                                            [CATA_RESULT__15641__auto__
                                             (CATA__FN__54064
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
                                             (CATA__FN__54064
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
                        (meander.runtime.zeta/fail? result__55607)
                        (recur (clojure.core/next search_space__55606))
                        result__55607))
                      (state__55594))))]
                  (state__55596))
                 (state__55594))
                (state__55594)))
              (state__55594)))
            (state__55594
             []
             (if
              (clojure.core/= (clojure.core/count input__53984) 4)
              (clojure.core/let
               [input__53984_nth_0__
                (clojure.core/nth input__53984 0)
                input__53984_nth_1__
                (clojure.core/nth input__53984 1)
                input__53984_nth_2__
                (clojure.core/nth input__53984 2)]
               (clojure.core/letfn
                [(state__55611
                  []
                  (clojure.core/let
                   [input__53984_nth_3__
                    (clojure.core/nth input__53984 3)]
                   (clojure.core/case
                    input__53984_nth_0__
                    (meander.dev.parse.zeta/make-star)
                    (clojure.core/letfn
                     [(state__55613
                       []
                       (if
                        (clojure.core/map? input__53984_nth_1__)
                        (clojure.core/let
                         [VAL__54232
                          (.valAt input__53984_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__54232
                          (:cat)
                          (clojure.core/let
                           [VAL__54233
                            (.valAt input__53984_nth_1__ :sequence)]
                           (if
                            (clojure.core/vector? VAL__54233)
                            (if
                             (clojure.core/=
                              (clojure.core/count VAL__54233)
                              1)
                             (clojure.core/let
                              [VAL__54233_nth_0__
                               (clojure.core/nth VAL__54233 0)]
                              (if
                               (clojure.core/map? VAL__54233_nth_0__)
                               (clojure.core/let
                                [VAL__54238
                                 (.valAt VAL__54233_nth_0__ :tag)]
                                (clojure.core/case
                                 VAL__54238
                                 (:memory-variable)
                                 (clojure.core/let
                                  [?memory-variable VAL__54233_nth_0__]
                                  (clojure.core/let
                                   [VAL__54234
                                    (.valAt
                                     input__53984_nth_1__
                                     :next)]
                                   (if
                                    (clojure.core/map? VAL__54234)
                                    (clojure.core/let
                                     [VAL__54235
                                      (.valAt VAL__54234 :tag)]
                                     (clojure.core/case
                                      VAL__54235
                                      (:empty)
                                      (clojure.core/let
                                       [?next input__53984_nth_2__]
                                       (clojure.core/let
                                        [?env input__53984_nth_3__]
                                        (try
                                         [(clojure.core/let
                                           [CATA_RESULT__15641__auto__
                                            (CATA__FN__54064
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
                                      (state__55614)))
                                    (state__55614))))
                                 (state__55614)))
                               (state__55614)))
                             (state__55614))
                            (state__55614)))
                          (state__55614)))
                        (state__55614)))
                      (state__55614
                       []
                       (clojure.core/let
                        [?pattern input__53984_nth_1__]
                        (clojure.core/let
                         [?next input__53984_nth_2__]
                         (if
                          (clojure.core/map? input__53984_nth_3__)
                          (clojure.core/let
                           [VAL__54241
                            (.valAt input__53984_nth_3__ :context)]
                           (clojure.core/case
                            VAL__54241
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
                            (state__55612)))
                          (state__55612)))))]
                     (state__55613))
                    (state__55612))))
                 (state__55612
                  []
                  (clojure.core/case
                   input__53984_nth_0__
                   (meander.dev.parse.zeta/make-star)
                   (clojure.core/let
                    [?pattern input__53984_nth_1__]
                    (clojure.core/let
                     [?next input__53984_nth_2__]
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
                   (state__55595)))]
                (state__55611)))
              (state__55595)))
            (state__55595
             []
             (if
              (clojure.core/= (clojure.core/count input__53984) 3)
              (clojure.core/let
               [input__53984_nth_0__
                (clojure.core/nth input__53984 0)
                input__53984_nth_1__
                (clojure.core/nth input__53984 1)
                input__53984_nth_2__
                (clojure.core/nth input__53984 2)]
               (clojure.core/case
                input__53984_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__53984_nth_1__)
                 (clojure.core/let
                  [input__53984_nth_1___l__
                   (clojure.core/subvec
                    input__53984_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__53984_nth_1__)
                     1))]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__53984_nth_1___l__)
                    1)
                   (clojure.core/let
                    [input__53984_nth_1___r__
                     (clojure.core/subvec input__53984_nth_1__ 1)]
                    (clojure.core/let
                     [input__53984_nth_1___l___nth_0__
                      (clojure.core/nth input__53984_nth_1___l__ 0)]
                     (if
                      (clojure.core/symbol?
                       input__53984_nth_1___l___nth_0__)
                      (clojure.core/let
                       [X__54249
                        (clojure.core/namespace
                         input__53984_nth_1___l___nth_0__)]
                       (clojure.core/case
                        X__54249
                        (nil)
                        (clojure.core/let
                         [X__54251
                          (clojure.core/name
                           input__53984_nth_1___l___nth_0__)]
                         (if
                          (clojure.core/string? X__54251)
                          (clojure.core/let
                           [ret__54252
                            (clojure.core/re-matches
                             #"\.\.(\d+)"
                             X__54251)]
                           (if
                            (clojure.core/some? ret__54252)
                            (if
                             (clojure.core/vector? ret__54252)
                             (if
                              (clojure.core/=
                               (clojure.core/count ret__54252)
                               2)
                              (clojure.core/let
                               [ret__54252_nth_1__
                                (clojure.core/nth ret__54252 1)]
                               (clojure.core/let
                                [?n ret__54252_nth_1__]
                                (clojure.core/let
                                 [?operator
                                  input__53984_nth_1___l___nth_0__]
                                 (clojure.core/let
                                  [?rest input__53984_nth_1___r__]
                                  (clojure.core/let
                                   [?env input__53984_nth_2__]
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
                              (state__55540))
                             (state__55540))
                            (state__55540)))
                          (state__55540)))
                        (state__55540)))
                      (state__55540))))
                   (state__55540)))
                 (state__55540))
                (state__55540)))
              (state__55540)))]
           (state__55593))
          (state__55540)))
        (state__55540
         []
         (clojure.core/letfn
          [(def__54255
            [arg__54279]
            (clojure.core/letfn
             [(state__55615
               []
               (clojure.core/let
                [x__54280 :string-plus]
                (clojure.core/let
                 [?tag x__54280]
                 (if
                  (clojure.core/map? arg__54279)
                  (clojure.core/let
                   [VAL__54281 (.valAt arg__54279 :context)]
                   (clojure.core/case
                    VAL__54281
                    (:string)
                    (clojure.core/let [?env arg__54279] [?tag ?env])
                    (state__55616)))
                  (state__55616)))))
              (state__55616
               []
               (clojure.core/let
                [x__54282 :plus]
                (clojure.core/let
                 [?tag x__54282]
                 (clojure.core/let [?env arg__54279] [?tag ?env]))))]
             (state__55615)))]
          (if
           (clojure.core/vector? input__53984)
           (if
            (clojure.core/= (clojure.core/count input__53984) 3)
            (clojure.core/let
             [input__53984_nth_0__
              (clojure.core/nth input__53984 0)
              input__53984_nth_1__
              (clojure.core/nth input__53984 1)
              input__53984_nth_2__
              (clojure.core/nth input__53984 2)]
             (clojure.core/case
              input__53984_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__53984_nth_1__)
               (clojure.core/loop
                [search_space__55617
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__53984_nth_1__)]
                (if
                 (clojure.core/seq search_space__55617)
                 (clojure.core/let
                  [input__53984_nth_1___parts__
                   (clojure.core/first search_space__55617)
                   result__55618
                   (clojure.core/let
                    [input__53984_nth_1___l__
                     (clojure.core/nth input__53984_nth_1___parts__ 0)
                     input__53984_nth_1___r__
                     (clojure.core/nth input__53984_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__14502__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__53984_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__54272]
                         (clojure.core/let
                          [input__54272_nth_0__
                           (clojure.core/nth input__54272 0)]
                          (clojure.core/letfn
                           [(save__54273
                             []
                             (meander.runtime.zeta/fail))
                            (f__55621
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__54272_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol? input__54272_nth_0__)
                            (clojure.core/let
                             [X__54275
                              (clojure.core/namespace
                               input__54272_nth_0__)]
                             (clojure.core/case
                              X__54275
                              (nil)
                              (clojure.core/let
                               [X__54277
                                (clojure.core/name
                                 input__54272_nth_0__)]
                               (if
                                (clojure.core/string? X__54277)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__54277)
                                 (save__54273)
                                 (f__55621))
                                (f__55621)))
                              (f__55621)))
                            (f__55621)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__53984_nth_1___r___l__
                           (clojure.core/subvec
                            input__53984_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__53984_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__53984_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__53984_nth_1___r___r__
                             (clojure.core/subvec
                              input__53984_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__53984_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__53984_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__53984_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__54266
                                (clojure.core/namespace
                                 input__53984_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__54266
                                (nil)
                                (clojure.core/let
                                 [X__54268
                                  (clojure.core/name
                                   input__53984_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__54268)
                                  (clojure.core/let
                                   [ret__54269
                                    (clojure.core/re-matches
                                     #"\.\.(\d+)"
                                     X__54268)]
                                   (if
                                    (clojure.core/some? ret__54269)
                                    (if
                                     (clojure.core/vector? ret__54269)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__54269)
                                       2)
                                      (clojure.core/let
                                       [ret__54269_nth_1__
                                        (clojure.core/nth
                                         ret__54269
                                         1)]
                                       (clojure.core/let
                                        [?n ret__54269_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__53984_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__14338__auto__
                                           (def__54255
                                            input__53984_nth_2__)]
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
                                                  (CATA__FN__54064
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
                                                  (CATA__FN__54064
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
                   (meander.runtime.zeta/fail? result__55618)
                   (recur (clojure.core/next search_space__55617))
                   result__55618))
                 (state__55541)))
               (state__55541))
              (state__55541)))
            (state__55541))
           (state__55541))))
        (state__55541
         []
         (if
          (clojure.core/vector? input__53984)
          (if
           (clojure.core/= (clojure.core/count input__53984) 3)
           (clojure.core/let
            [input__53984_nth_0__
             (clojure.core/nth input__53984 0)
             input__53984_nth_1__
             (clojure.core/nth input__53984 1)
             input__53984_nth_2__
             (clojure.core/nth input__53984 2)]
            (clojure.core/case
             input__53984_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__53984_nth_1__)
              (clojure.core/let
               [input__53984_nth_1___l__
                (clojure.core/subvec
                 input__53984_nth_1__
                 0
                 (clojure.core/min
                  (clojure.core/count input__53984_nth_1__)
                  1))]
               (if
                (clojure.core/=
                 (clojure.core/count input__53984_nth_1___l__)
                 1)
                (clojure.core/let
                 [input__53984_nth_1___r__
                  (clojure.core/subvec input__53984_nth_1__ 1)]
                 (clojure.core/let
                  [input__53984_nth_1___l___nth_0__
                   (clojure.core/nth input__53984_nth_1___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__53984_nth_1___l___nth_0__)
                   (clojure.core/let
                    [X__54300
                     (clojure.core/namespace
                      input__53984_nth_1___l___nth_0__)]
                    (clojure.core/case
                     X__54300
                     (nil)
                     (clojure.core/let
                      [X__54302
                       (clojure.core/name
                        input__53984_nth_1___l___nth_0__)]
                      (if
                       (clojure.core/string? X__54302)
                       (clojure.core/let
                        [ret__54303
                         (clojure.core/re-matches
                          #"\.\.(\?.+)"
                          X__54302)]
                        (if
                         (clojure.core/some? ret__54303)
                         (if
                          (clojure.core/vector? ret__54303)
                          (if
                           (clojure.core/=
                            (clojure.core/count ret__54303)
                            2)
                           (clojure.core/let
                            [ret__54303_nth_1__
                             (clojure.core/nth ret__54303 1)]
                            (clojure.core/let
                             [?n ret__54303_nth_1__]
                             (clojure.core/let
                              [?operator
                               input__53984_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?rest input__53984_nth_1___r__]
                               (clojure.core/let
                                [?env input__53984_nth_2__]
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
                           (state__55542))
                          (state__55542))
                         (state__55542)))
                       (state__55542)))
                     (state__55542)))
                   (state__55542))))
                (state__55542)))
              (state__55542))
             (state__55542)))
           (state__55542))
          (state__55542)))
        (state__55542
         []
         (clojure.core/letfn
          [(def__54306
            [arg__54330]
            (clojure.core/letfn
             [(state__55622
               []
               (clojure.core/let
                [x__54331 :string-logical-plus]
                (clojure.core/let
                 [?tag x__54331]
                 (if
                  (clojure.core/map? arg__54330)
                  (clojure.core/let
                   [VAL__54332 (.valAt arg__54330 :context)]
                   (clojure.core/case
                    VAL__54332
                    (:string)
                    (clojure.core/let [?env arg__54330] [?tag ?env])
                    (state__55623)))
                  (state__55623)))))
              (state__55623
               []
               (clojure.core/let
                [x__54333 :logical-plus]
                (clojure.core/let
                 [?tag x__54333]
                 (clojure.core/let [?env arg__54330] [?tag ?env]))))]
             (state__55622)))]
          (if
           (clojure.core/vector? input__53984)
           (if
            (clojure.core/= (clojure.core/count input__53984) 3)
            (clojure.core/let
             [input__53984_nth_0__
              (clojure.core/nth input__53984 0)
              input__53984_nth_1__
              (clojure.core/nth input__53984 1)
              input__53984_nth_2__
              (clojure.core/nth input__53984 2)]
             (clojure.core/case
              input__53984_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__53984_nth_1__)
               (clojure.core/loop
                [search_space__55624
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__53984_nth_1__)]
                (if
                 (clojure.core/seq search_space__55624)
                 (clojure.core/let
                  [input__53984_nth_1___parts__
                   (clojure.core/first search_space__55624)
                   result__55625
                   (clojure.core/let
                    [input__53984_nth_1___l__
                     (clojure.core/nth input__53984_nth_1___parts__ 0)
                     input__53984_nth_1___r__
                     (clojure.core/nth input__53984_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__14502__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__53984_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__54323]
                         (clojure.core/let
                          [input__54323_nth_0__
                           (clojure.core/nth input__54323 0)]
                          (clojure.core/letfn
                           [(save__54324
                             []
                             (meander.runtime.zeta/fail))
                            (f__55628
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__54323_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol? input__54323_nth_0__)
                            (clojure.core/let
                             [X__54326
                              (clojure.core/namespace
                               input__54323_nth_0__)]
                             (clojure.core/case
                              X__54326
                              (nil)
                              (clojure.core/let
                               [X__54328
                                (clojure.core/name
                                 input__54323_nth_0__)]
                               (if
                                (clojure.core/string? X__54328)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__54328)
                                 (save__54324)
                                 (f__55628))
                                (f__55628)))
                              (f__55628)))
                            (f__55628)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__53984_nth_1___r___l__
                           (clojure.core/subvec
                            input__53984_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__53984_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__53984_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__53984_nth_1___r___r__
                             (clojure.core/subvec
                              input__53984_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__53984_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__53984_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__53984_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__54317
                                (clojure.core/namespace
                                 input__53984_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__54317
                                (nil)
                                (clojure.core/let
                                 [X__54319
                                  (clojure.core/name
                                   input__53984_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__54319)
                                  (clojure.core/let
                                   [ret__54320
                                    (clojure.core/re-matches
                                     #"\.\.(\?.+)"
                                     X__54319)]
                                   (if
                                    (clojure.core/some? ret__54320)
                                    (if
                                     (clojure.core/vector? ret__54320)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__54320)
                                       2)
                                      (clojure.core/let
                                       [ret__54320_nth_1__
                                        (clojure.core/nth
                                         ret__54320
                                         1)]
                                       (clojure.core/let
                                        [?n ret__54320_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__53984_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__14338__auto__
                                           (def__54306
                                            input__53984_nth_2__)]
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
                                                  (CATA__FN__54064
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
                                                  (CATA__FN__54064
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
                   (meander.runtime.zeta/fail? result__55625)
                   (recur (clojure.core/next search_space__55624))
                   result__55625))
                 (state__55543)))
               (state__55543))
              (state__55543)))
            (state__55543))
           (state__55543))))
        (state__55543
         []
         (if
          (clojure.core/vector? input__53984)
          (if
           (clojure.core/= (clojure.core/count input__53984) 3)
           (clojure.core/let
            [input__53984_nth_0__
             (clojure.core/nth input__53984 0)
             input__53984_nth_1__
             (clojure.core/nth input__53984 1)
             input__53984_nth_2__
             (clojure.core/nth input__53984 2)]
            (clojure.core/case
             input__53984_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__53984_nth_1__)
              (clojure.core/let
               [input__53984_nth_1___l__
                (clojure.core/subvec
                 input__53984_nth_1__
                 0
                 (clojure.core/min
                  (clojure.core/count input__53984_nth_1__)
                  1))]
               (if
                (clojure.core/=
                 (clojure.core/count input__53984_nth_1___l__)
                 1)
                (clojure.core/let
                 [input__53984_nth_1___r__
                  (clojure.core/subvec input__53984_nth_1__ 1)]
                 (clojure.core/let
                  [input__53984_nth_1___l___nth_0__
                   (clojure.core/nth input__53984_nth_1___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__53984_nth_1___l___nth_0__)
                   (clojure.core/let
                    [X__54351
                     (clojure.core/namespace
                      input__53984_nth_1___l___nth_0__)]
                    (clojure.core/case
                     X__54351
                     (nil)
                     (clojure.core/let
                      [X__54353
                       (clojure.core/name
                        input__53984_nth_1___l___nth_0__)]
                      (if
                       (clojure.core/string? X__54353)
                       (clojure.core/let
                        [ret__54354
                         (clojure.core/re-matches
                          #"\.\.(!.+)"
                          X__54353)]
                        (if
                         (clojure.core/some? ret__54354)
                         (if
                          (clojure.core/vector? ret__54354)
                          (if
                           (clojure.core/=
                            (clojure.core/count ret__54354)
                            2)
                           (clojure.core/let
                            [ret__54354_nth_1__
                             (clojure.core/nth ret__54354 1)]
                            (clojure.core/let
                             [?n ret__54354_nth_1__]
                             (clojure.core/let
                              [?operator
                               input__53984_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?rest input__53984_nth_1___r__]
                               (clojure.core/let
                                [?env input__53984_nth_2__]
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
                           (state__55544))
                          (state__55544))
                         (state__55544)))
                       (state__55544)))
                     (state__55544)))
                   (state__55544))))
                (state__55544)))
              (state__55544))
             (state__55544)))
           (state__55544))
          (state__55544)))
        (state__55544
         []
         (clojure.core/letfn
          [(def__54357
            [arg__54381]
            (clojure.core/letfn
             [(state__55629
               []
               (clojure.core/let
                [x__54382 :string-memory-plus]
                (clojure.core/let
                 [?tag x__54382]
                 (if
                  (clojure.core/map? arg__54381)
                  (clojure.core/let
                   [VAL__54383 (.valAt arg__54381 :context)]
                   (clojure.core/case
                    VAL__54383
                    (:string)
                    (clojure.core/let [?env arg__54381] [?tag ?env])
                    (state__55630)))
                  (state__55630)))))
              (state__55630
               []
               (clojure.core/let
                [x__54384 :memory-plus]
                (clojure.core/let
                 [?tag x__54384]
                 (clojure.core/let [?env arg__54381] [?tag ?env]))))]
             (state__55629)))]
          (if
           (clojure.core/vector? input__53984)
           (if
            (clojure.core/= (clojure.core/count input__53984) 3)
            (clojure.core/let
             [input__53984_nth_0__
              (clojure.core/nth input__53984 0)
              input__53984_nth_1__
              (clojure.core/nth input__53984 1)
              input__53984_nth_2__
              (clojure.core/nth input__53984 2)]
             (clojure.core/case
              input__53984_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__53984_nth_1__)
               (clojure.core/loop
                [search_space__55631
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__53984_nth_1__)]
                (if
                 (clojure.core/seq search_space__55631)
                 (clojure.core/let
                  [input__53984_nth_1___parts__
                   (clojure.core/first search_space__55631)
                   result__55632
                   (clojure.core/let
                    [input__53984_nth_1___l__
                     (clojure.core/nth input__53984_nth_1___parts__ 0)
                     input__53984_nth_1___r__
                     (clojure.core/nth input__53984_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__14502__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__53984_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__54374]
                         (clojure.core/let
                          [input__54374_nth_0__
                           (clojure.core/nth input__54374 0)]
                          (clojure.core/letfn
                           [(save__54375
                             []
                             (meander.runtime.zeta/fail))
                            (f__55635
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__54374_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol? input__54374_nth_0__)
                            (clojure.core/let
                             [X__54377
                              (clojure.core/namespace
                               input__54374_nth_0__)]
                             (clojure.core/case
                              X__54377
                              (nil)
                              (clojure.core/let
                               [X__54379
                                (clojure.core/name
                                 input__54374_nth_0__)]
                               (if
                                (clojure.core/string? X__54379)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__54379)
                                 (save__54375)
                                 (f__55635))
                                (f__55635)))
                              (f__55635)))
                            (f__55635)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__53984_nth_1___r___l__
                           (clojure.core/subvec
                            input__53984_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__53984_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__53984_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__53984_nth_1___r___r__
                             (clojure.core/subvec
                              input__53984_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__53984_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__53984_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__53984_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__54368
                                (clojure.core/namespace
                                 input__53984_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__54368
                                (nil)
                                (clojure.core/let
                                 [X__54370
                                  (clojure.core/name
                                   input__53984_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__54370)
                                  (clojure.core/let
                                   [ret__54371
                                    (clojure.core/re-matches
                                     #"\.\.(\!.+)"
                                     X__54370)]
                                   (if
                                    (clojure.core/some? ret__54371)
                                    (if
                                     (clojure.core/vector? ret__54371)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__54371)
                                       2)
                                      (clojure.core/let
                                       [ret__54371_nth_1__
                                        (clojure.core/nth
                                         ret__54371
                                         1)]
                                       (clojure.core/let
                                        [?n ret__54371_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__53984_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__14338__auto__
                                           (def__54357
                                            input__53984_nth_2__)]
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
                                                  (CATA__FN__54064
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
                                                  (CATA__FN__54064
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
                   (meander.runtime.zeta/fail? result__55632)
                   (recur (clojure.core/next search_space__55631))
                   result__55632))
                 (state__55545)))
               (state__55545))
              (state__55545)))
            (state__55545))
           (state__55545))))
        (state__55545
         []
         (if
          (clojure.core/vector? input__53984)
          (clojure.core/letfn
           [(state__55636
             []
             (if
              (clojure.core/= (clojure.core/count input__53984) 3)
              (clojure.core/let
               [input__53984_nth_0__
                (clojure.core/nth input__53984 0)
                input__53984_nth_1__
                (clojure.core/nth input__53984 1)
                input__53984_nth_2__
                (clojure.core/nth input__53984 2)]
               (clojure.core/case
                input__53984_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__53984_nth_1__)
                 (clojure.core/let
                  [!xs (clojure.core/vec input__53984_nth_1__)]
                  (clojure.core/let
                   [?env input__53984_nth_2__]
                   (try
                    [(clojure.core/let
                      [!xs__counter
                       (meander.runtime.zeta/iterator !xs)]
                      (clojure.core/let
                       [CATA_RESULT__15641__auto__
                        (CATA__FN__54064
                         ['meander.dev.parse.zeta/make-cat
                          (clojure.core/into
                           []
                           (clojure.core/loop
                            [return__54065 (clojure.core/transient [])]
                            (if
                             (clojure.core/and (.hasNext !xs__counter))
                             (recur
                              (clojure.core/conj!
                               return__54065
                               (clojure.core/let
                                [CATA_RESULT__15641__auto__
                                 (CATA__FN__54064
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
                              return__54065))))
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
                 (state__55637))
                (state__55637)))
              (state__55637)))
            (state__55637
             []
             (if
              (clojure.core/= (clojure.core/count input__53984) 4)
              (clojure.core/let
               [input__53984_nth_0__
                (clojure.core/nth input__53984 0)
                input__53984_nth_1__
                (clojure.core/nth input__53984 1)
                input__53984_nth_2__
                (clojure.core/nth input__53984 2)]
               (clojure.core/letfn
                [(state__55639
                  []
                  (clojure.core/let
                   [input__53984_nth_3__
                    (clojure.core/nth input__53984 3)]
                   (clojure.core/case
                    input__53984_nth_0__
                    (meander.dev.parse.zeta/make-cat)
                    (if
                     (clojure.core/vector? input__53984_nth_1__)
                     (clojure.core/letfn
                      [(state__55646
                        []
                        (clojure.core/case
                         input__53984_nth_1__
                         ([])
                         (clojure.core/let
                          [?next input__53984_nth_2__]
                          (clojure.core/let
                           [?env input__53984_nth_3__]
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
                         (state__55647)))
                       (state__55647
                        []
                        (clojure.core/loop
                         [search_space__55648
                          (meander.runtime.zeta/epsilon-partitions
                           2
                           input__53984_nth_1__)]
                         (if
                          (clojure.core/seq search_space__55648)
                          (clojure.core/let
                           [input__53984_nth_1___parts__
                            (clojure.core/first search_space__55648)
                            result__55649
                            (clojure.core/let
                             [input__53984_nth_1___l__
                              (clojure.core/nth
                               input__53984_nth_1___parts__
                               0)
                              input__53984_nth_1___r__
                              (clojure.core/nth
                               input__53984_nth_1___parts__
                               1)]
                             (clojure.core/letfn
                              [(state__55651
                                []
                                (clojure.core/let
                                 [!xs []]
                                 (clojure.core/let
                                  [ret__14502__auto__
                                   (meander.runtime.zeta/epsilon-run-star-1
                                    input__53984_nth_1___l__
                                    [!xs]
                                    (clojure.core/fn
                                     [[!xs] input__54410]
                                     (clojure.core/let
                                      [input__54410_nth_0__
                                       (clojure.core/nth
                                        input__54410
                                        0)]
                                      (clojure.core/letfn
                                       [(save__54411
                                         []
                                         (meander.runtime.zeta/fail))
                                        (f__55655
                                         []
                                         (clojure.core/let
                                          [!xs
                                           (clojure.core/conj
                                            !xs
                                            input__54410_nth_0__)]
                                          [!xs]))]
                                       (if
                                        (clojure.core/map?
                                         input__54410_nth_0__)
                                        (clojure.core/let
                                         [VAL__54412
                                          (.valAt
                                           input__54410_nth_0__
                                           :tag)]
                                         (clojure.core/case
                                          VAL__54412
                                          (:group)
                                          (save__54411)
                                          (f__55655)))
                                        (f__55655)))))
                                    (clojure.core/fn
                                     [[!xs]]
                                     (clojure.core/let
                                      [input__53984_nth_1___r___l__
                                       (clojure.core/subvec
                                        input__53984_nth_1___r__
                                        0
                                        (clojure.core/min
                                         (clojure.core/count
                                          input__53984_nth_1___r__)
                                         1))]
                                      (if
                                       (clojure.core/=
                                        (clojure.core/count
                                         input__53984_nth_1___r___l__)
                                        1)
                                       (clojure.core/let
                                        [input__53984_nth_1___r___r__
                                         (clojure.core/subvec
                                          input__53984_nth_1___r__
                                          1)]
                                        (clojure.core/let
                                         [input__53984_nth_1___r___l___nth_0__
                                          (clojure.core/nth
                                           input__53984_nth_1___r___l__
                                           0)]
                                         (if
                                          (clojure.core/map?
                                           input__53984_nth_1___r___l___nth_0__)
                                          (clojure.core/let
                                           [VAL__54409
                                            (.valAt
                                             input__53984_nth_1___r___l___nth_0__
                                             :tag)]
                                           (clojure.core/case
                                            VAL__54409
                                            (:group)
                                            (clojure.core/let
                                             [?group
                                              input__53984_nth_1___r___l___nth_0__]
                                             (clojure.core/let
                                              [?rest
                                               input__53984_nth_1___r___r__]
                                              (clojure.core/let
                                               [?next
                                                input__53984_nth_2__]
                                               (clojure.core/let
                                                [?env
                                                 input__53984_nth_3__]
                                                (try
                                                 [(clojure.core/let
                                                   [!xs__counter
                                                    (meander.runtime.zeta/iterator
                                                     !xs)]
                                                   (clojure.core/let
                                                    [CATA_RESULT__15641__auto__
                                                     (CATA__FN__54064
                                                      ['meander.dev.parse.zeta/make-join
                                                       (clojure.core/let
                                                        [CATA_RESULT__15641__auto__
                                                         (CATA__FN__54064
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
                                                         (CATA__FN__54064
                                                          ['meander.dev.parse.zeta/make-join
                                                           ?group
                                                           (clojure.core/let
                                                            [CATA_RESULT__15641__auto__
                                                             (CATA__FN__54064
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
                                            (state__55652)))
                                          (state__55652))))
                                       (state__55652)))))]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    ret__14502__auto__)
                                   (state__55652)
                                   ret__14502__auto__))))
                               (state__55652
                                []
                                (clojure.core/let
                                 [!xs []]
                                 (clojure.core/let
                                  [ret__14502__auto__
                                   (meander.runtime.zeta/epsilon-run-star-1
                                    input__53984_nth_1___l__
                                    [!xs]
                                    (clojure.core/fn
                                     [[!xs] input__54421]
                                     (clojure.core/let
                                      [input__54421_nth_0__
                                       (clojure.core/nth
                                        input__54421
                                        0)]
                                      (clojure.core/letfn
                                       [(save__54422
                                         []
                                         (meander.runtime.zeta/fail))
                                        (f__55657
                                         []
                                         (clojure.core/let
                                          [!xs
                                           (clojure.core/conj
                                            !xs
                                            input__54421_nth_0__)]
                                          [!xs]))]
                                       (if
                                        (clojure.core/map?
                                         input__54421_nth_0__)
                                        (clojure.core/let
                                         [VAL__54423
                                          (.valAt
                                           input__54421_nth_0__
                                           :tag)]
                                         (clojure.core/case
                                          VAL__54423
                                          (:star)
                                          (save__54422)
                                          (f__55657)))
                                        (f__55657)))))
                                    (clojure.core/fn
                                     [[!xs]]
                                     (clojure.core/let
                                      [input__53984_nth_1___r___l__
                                       (clojure.core/subvec
                                        input__53984_nth_1___r__
                                        0
                                        (clojure.core/min
                                         (clojure.core/count
                                          input__53984_nth_1___r__)
                                         1))]
                                      (if
                                       (clojure.core/=
                                        (clojure.core/count
                                         input__53984_nth_1___r___l__)
                                        1)
                                       (clojure.core/let
                                        [input__53984_nth_1___r___r__
                                         (clojure.core/subvec
                                          input__53984_nth_1___r__
                                          1)]
                                        (clojure.core/let
                                         [input__53984_nth_1___r___l___nth_0__
                                          (clojure.core/nth
                                           input__53984_nth_1___r___l__
                                           0)]
                                         (if
                                          (clojure.core/map?
                                           input__53984_nth_1___r___l___nth_0__)
                                          (clojure.core/let
                                           [VAL__54420
                                            (.valAt
                                             input__53984_nth_1___r___l___nth_0__
                                             :tag)]
                                           (clojure.core/case
                                            VAL__54420
                                            (:star)
                                            (clojure.core/let
                                             [?star
                                              input__53984_nth_1___r___l___nth_0__]
                                             (clojure.core/let
                                              [?rest
                                               input__53984_nth_1___r___r__]
                                              (clojure.core/let
                                               [?next
                                                input__53984_nth_2__]
                                               (clojure.core/let
                                                [?env
                                                 input__53984_nth_3__]
                                                (try
                                                 [(clojure.core/let
                                                   [!xs__counter
                                                    (meander.runtime.zeta/iterator
                                                     !xs)]
                                                   (clojure.core/let
                                                    [CATA_RESULT__15641__auto__
                                                     (CATA__FN__54064
                                                      ['meander.dev.parse.zeta/make-join
                                                       (clojure.core/let
                                                        [CATA_RESULT__15641__auto__
                                                         (CATA__FN__54064
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
                                                         (CATA__FN__54064
                                                          ['meander.dev.parse.zeta/make-join
                                                           ?star
                                                           (clojure.core/let
                                                            [CATA_RESULT__15641__auto__
                                                             (CATA__FN__54064
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
                                            (state__55653)))
                                          (state__55653))))
                                       (state__55653)))))]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    ret__14502__auto__)
                                   (state__55653)
                                   ret__14502__auto__))))
                               (state__55653
                                []
                                (clojure.core/let
                                 [!xs []]
                                 (clojure.core/let
                                  [ret__14502__auto__
                                   (meander.runtime.zeta/epsilon-run-star-1
                                    input__53984_nth_1___l__
                                    [!xs]
                                    (clojure.core/fn
                                     [[!xs] input__54432]
                                     (clojure.core/let
                                      [input__54432_nth_0__
                                       (clojure.core/nth
                                        input__54432
                                        0)]
                                      (clojure.core/letfn
                                       [(save__54433
                                         []
                                         (meander.runtime.zeta/fail))
                                        (f__55659
                                         []
                                         (clojure.core/let
                                          [!xs
                                           (clojure.core/conj
                                            !xs
                                            input__54432_nth_0__)]
                                          [!xs]))]
                                       (if
                                        (clojure.core/map?
                                         input__54432_nth_0__)
                                        (clojure.core/let
                                         [VAL__54434
                                          (.valAt
                                           input__54432_nth_0__
                                           :tag)]
                                         (clojure.core/case
                                          VAL__54434
                                          (:reference)
                                          (save__54433)
                                          (f__55659)))
                                        (f__55659)))))
                                    (clojure.core/fn
                                     [[!xs]]
                                     (clojure.core/let
                                      [input__53984_nth_1___r___l__
                                       (clojure.core/subvec
                                        input__53984_nth_1___r__
                                        0
                                        (clojure.core/min
                                         (clojure.core/count
                                          input__53984_nth_1___r__)
                                         1))]
                                      (if
                                       (clojure.core/=
                                        (clojure.core/count
                                         input__53984_nth_1___r___l__)
                                        1)
                                       (clojure.core/let
                                        [input__53984_nth_1___r___r__
                                         (clojure.core/subvec
                                          input__53984_nth_1___r__
                                          1)]
                                        (clojure.core/let
                                         [input__53984_nth_1___r___l___nth_0__
                                          (clojure.core/nth
                                           input__53984_nth_1___r___l__
                                           0)]
                                         (if
                                          (clojure.core/map?
                                           input__53984_nth_1___r___l___nth_0__)
                                          (clojure.core/let
                                           [VAL__54431
                                            (.valAt
                                             input__53984_nth_1___r___l___nth_0__
                                             :tag)]
                                           (clojure.core/case
                                            VAL__54431
                                            (:reference)
                                            (clojure.core/let
                                             [?reference
                                              input__53984_nth_1___r___l___nth_0__]
                                             (clojure.core/let
                                              [?rest
                                               input__53984_nth_1___r___r__]
                                              (clojure.core/let
                                               [?next
                                                input__53984_nth_2__]
                                               (clojure.core/let
                                                [?env
                                                 input__53984_nth_3__]
                                                (try
                                                 [(clojure.core/let
                                                   [!xs__counter
                                                    (meander.runtime.zeta/iterator
                                                     !xs)]
                                                   (clojure.core/let
                                                    [CATA_RESULT__15641__auto__
                                                     (CATA__FN__54064
                                                      ['meander.dev.parse.zeta/make-join
                                                       (clojure.core/let
                                                        [CATA_RESULT__15641__auto__
                                                         (CATA__FN__54064
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
                                                         (CATA__FN__54064
                                                          ['meander.dev.parse.zeta/make-join
                                                           ?reference
                                                           (clojure.core/let
                                                            [CATA_RESULT__15641__auto__
                                                             (CATA__FN__54064
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
                              (state__55651)))]
                           (if
                            (meander.runtime.zeta/fail? result__55649)
                            (recur
                             (clojure.core/next search_space__55648))
                            result__55649))
                          (state__55640))))]
                      (state__55646))
                     (state__55640))
                    (state__55640))))
                 (state__55640
                  []
                  (clojure.core/case
                   input__53984_nth_0__
                   (meander.dev.parse.zeta/make-cat)
                   (if
                    (clojure.core/vector? input__53984_nth_1__)
                    (clojure.core/let
                     [input__53984_nth_1___l__
                      (clojure.core/subvec
                       input__53984_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__53984_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__53984_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__53984_nth_1___r__
                        (clojure.core/subvec input__53984_nth_1__ 1)]
                       (clojure.core/let
                        [input__53984_nth_1___l___nth_0__
                         (clojure.core/nth input__53984_nth_1___l__ 0)]
                        (if
                         (clojure.core/map?
                          input__53984_nth_1___l___nth_0__)
                         (clojure.core/let
                          [VAL__54443
                           (.valAt
                            input__53984_nth_1___l___nth_0__
                            :tag)]
                          (clojure.core/case
                           VAL__54443
                           (:literal)
                           (clojure.core/let
                            [VAL__54444
                             (.valAt
                              input__53984_nth_1___l___nth_0__
                              :type)]
                            (if
                             (clojure.core/let
                              [x__13398__auto__ VAL__54444]
                              (clojure.core/case
                               x__13398__auto__
                               (:string :char)
                               true
                               false))
                             (clojure.core/let
                              [VAL__54445
                               (.valAt
                                input__53984_nth_1___l___nth_0__
                                :form)]
                              (clojure.core/let
                               [!forms []]
                               (clojure.core/let
                                [!forms
                                 (clojure.core/conj !forms VAL__54445)]
                                (clojure.core/loop
                                 [i__14475__auto__
                                  0
                                  coll__55660
                                  input__53984_nth_1___r__
                                  [!forms]
                                  [!forms]]
                                 (clojure.core/let
                                  [input__54446
                                   (clojure.core/subvec
                                    coll__55660
                                    0
                                    (clojure.core/min
                                     (clojure.core/count coll__55660)
                                     1))]
                                  (if
                                   (clojure.core/=
                                    (clojure.core/count input__54446)
                                    1)
                                   (clojure.core/let
                                    [result__14476__auto__
                                     (clojure.core/let
                                      [input__54446_nth_0__
                                       (clojure.core/nth
                                        input__54446
                                        0)]
                                      (if
                                       (clojure.core/map?
                                        input__54446_nth_0__)
                                       (clojure.core/let
                                        [VAL__54447
                                         (.valAt
                                          input__54446_nth_0__
                                          :tag)]
                                        (clojure.core/case
                                         VAL__54447
                                         (:literal)
                                         (clojure.core/let
                                          [VAL__54448
                                           (.valAt
                                            input__54446_nth_0__
                                            :type)]
                                          (if
                                           (clojure.core/let
                                            [x__13398__auto__
                                             VAL__54448]
                                            (clojure.core/case
                                             x__13398__auto__
                                             (:string :char)
                                             true
                                             false))
                                           (clojure.core/let
                                            [VAL__54449
                                             (.valAt
                                              input__54446_nth_0__
                                              :form)]
                                            (clojure.core/let
                                             [!forms
                                              (clojure.core/conj
                                               !forms
                                               VAL__54449)]
                                             [!forms]))
                                           (meander.runtime.zeta/fail)))
                                         (meander.runtime.zeta/fail)))
                                       (meander.runtime.zeta/fail)))]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      result__14476__auto__)
                                     (state__55641)
                                     (recur
                                      (clojure.core/inc
                                       i__14475__auto__)
                                      (clojure.core/subvec
                                       coll__55660
                                       1)
                                      result__14476__auto__)))
                                   (if
                                    (clojure.core/or
                                     (clojure.core/seq coll__55660)
                                     (clojure.core/<
                                      i__14475__auto__
                                      0))
                                    (state__55641)
                                    (if
                                     (clojure.core/map?
                                      input__53984_nth_2__)
                                     (clojure.core/let
                                      [VAL__54438
                                       (.valAt
                                        input__53984_nth_2__
                                        :tag)]
                                      (clojure.core/case
                                       VAL__54438
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
                                       (state__55641)))
                                     (state__55641)))))))))
                             (state__55641)))
                           (state__55641)))
                         (state__55641))))
                      (state__55641)))
                    (state__55641))
                   (state__55641)))
                 (state__55641
                  []
                  (clojure.core/let
                   [input__53984_nth_3__
                    (clojure.core/nth input__53984 3)]
                   (clojure.core/case
                    input__53984_nth_0__
                    (meander.dev.parse.zeta/make-cat)
                    (clojure.core/letfn
                     [(state__55661
                       []
                       (if
                        (clojure.core/vector? input__53984_nth_1__)
                        (clojure.core/let
                         [input__53984_nth_1___l__
                          (clojure.core/subvec
                           input__53984_nth_1__
                           0
                           (clojure.core/min
                            (clojure.core/count input__53984_nth_1__)
                            1))]
                         (if
                          (clojure.core/=
                           (clojure.core/count
                            input__53984_nth_1___l__)
                           1)
                          (clojure.core/let
                           [input__53984_nth_1___r__
                            (clojure.core/subvec
                             input__53984_nth_1__
                             1)]
                           (clojure.core/let
                            [input__53984_nth_1___l___nth_0__
                             (clojure.core/nth
                              input__53984_nth_1___l__
                              0)]
                            (if
                             (clojure.core/map?
                              input__53984_nth_1___l___nth_0__)
                             (clojure.core/let
                              [VAL__55532
                               (.valAt
                                input__53984_nth_1___l___nth_0__
                                :tag)]
                              (clojure.core/case
                               VAL__55532
                               (:literal)
                               (clojure.core/letfn
                                [(state__55663
                                  []
                                  (clojure.core/let
                                   [VAL__54456
                                    (.valAt
                                     input__53984_nth_1___l___nth_0__
                                     :type)]
                                   (clojure.core/case
                                    VAL__54456
                                    (:string)
                                    (clojure.core/let
                                     [?ast
                                      input__53984_nth_1___l___nth_0__]
                                     (clojure.core/let
                                      [?rest input__53984_nth_1___r__]
                                      (clojure.core/let
                                       [?next input__53984_nth_2__]
                                       (clojure.core/let
                                        [?env input__53984_nth_3__]
                                        (try
                                         [(clojure.core/let
                                           [CATA_RESULT__15641__auto__
                                            (CATA__FN__54064
                                             ['meander.dev.parse.zeta/make-join
                                              ?ast
                                              (clojure.core/let
                                               [CATA_RESULT__15641__auto__
                                                (CATA__FN__54064
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
                                    (state__55664))))
                                 (state__55664
                                  []
                                  (clojure.core/let
                                   [VAL__54466
                                    (.valAt
                                     input__53984_nth_1___l___nth_0__
                                     :form)]
                                   (clojure.core/let
                                    [!forms []]
                                    (clojure.core/let
                                     [!forms
                                      (clojure.core/conj
                                       !forms
                                       VAL__54466)]
                                     (clojure.core/loop
                                      [i__14475__auto__
                                       0
                                       coll__55665
                                       input__53984_nth_1___r__
                                       [!forms]
                                       [!forms]]
                                      (clojure.core/let
                                       [input__54467
                                        (clojure.core/subvec
                                         coll__55665
                                         0
                                         (clojure.core/min
                                          (clojure.core/count
                                           coll__55665)
                                          1))]
                                       (if
                                        (clojure.core/=
                                         (clojure.core/count
                                          input__54467)
                                         1)
                                        (clojure.core/let
                                         [result__14476__auto__
                                          (clojure.core/let
                                           [input__54467_nth_0__
                                            (clojure.core/nth
                                             input__54467
                                             0)]
                                           (if
                                            (clojure.core/map?
                                             input__54467_nth_0__)
                                            (clojure.core/let
                                             [VAL__54468
                                              (.valAt
                                               input__54467_nth_0__
                                               :tag)]
                                             (clojure.core/case
                                              VAL__54468
                                              (:literal)
                                              (clojure.core/let
                                               [VAL__54469
                                                (.valAt
                                                 input__54467_nth_0__
                                                 :form)]
                                               (clojure.core/let
                                                [!forms
                                                 (clojure.core/conj
                                                  !forms
                                                  VAL__54469)]
                                                [!forms]))
                                              (meander.runtime.zeta/fail)))
                                            (meander.runtime.zeta/fail)))]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           result__14476__auto__)
                                          (state__55662)
                                          (recur
                                           (clojure.core/inc
                                            i__14475__auto__)
                                           (clojure.core/subvec
                                            coll__55665
                                            1)
                                           result__14476__auto__)))
                                        (if
                                         (clojure.core/or
                                          (clojure.core/seq
                                           coll__55665)
                                          (clojure.core/<
                                           i__14475__auto__
                                           0))
                                         (state__55662)
                                         (if
                                          (clojure.core/map?
                                           input__53984_nth_2__)
                                          (clojure.core/let
                                           [VAL__54459
                                            (.valAt
                                             input__53984_nth_2__
                                             :tag)]
                                           (clojure.core/case
                                            VAL__54459
                                            (:empty)
                                            (if
                                             (clojure.core/map?
                                              input__53984_nth_3__)
                                             (clojure.core/let
                                              [VAL__54460
                                               (.valAt
                                                input__53984_nth_3__
                                                :context)]
                                              (clojure.core/let
                                               [?context VAL__54460]
                                               (clojure.core/let
                                                [?env
                                                 input__53984_nth_3__]
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
                                             (state__55662))
                                            (state__55662)))
                                          (state__55662))))))))))]
                                (state__55663))
                               (state__55662)))
                             (state__55662))))
                          (state__55662)))
                        (state__55662)))
                      (state__55662
                       []
                       (clojure.core/let
                        [?sequence input__53984_nth_1__]
                        (clojure.core/let
                         [?next input__53984_nth_2__]
                         (if
                          (clojure.core/map? input__53984_nth_3__)
                          (clojure.core/let
                           [VAL__54473
                            (.valAt input__53984_nth_3__ :context)]
                           (clojure.core/case
                            VAL__54473
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
                            (state__55642)))
                          (state__55642)))))]
                     (state__55661))
                    (state__55642))))
                 (state__55642
                  []
                  (clojure.core/case
                   input__53984_nth_0__
                   (meander.dev.parse.zeta/make-cat)
                   (clojure.core/let
                    [?sequence input__53984_nth_1__]
                    (clojure.core/let
                     [?next input__53984_nth_2__]
                     (try
                      [{:tag :cat, :sequence ?sequence, :next ?next}]
                      (catch
                       java.lang.Exception
                       e__16581__auto__
                       (if
                        (meander.runtime.zeta/fail? e__16581__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__16581__auto__))))))
                   (state__55643)))
                 (state__55643
                  []
                  (clojure.core/let
                   [input__53984_nth_3__
                    (clojure.core/nth input__53984 3)]
                   (clojure.core/case
                    input__53984_nth_0__
                    (meander.dev.parse.zeta/make-join)
                    (if
                     (clojure.core/map? input__53984_nth_1__)
                     (clojure.core/let
                      [VAL__55530 (.valAt input__53984_nth_1__ :tag)]
                      (clojure.core/case
                       VAL__55530
                       (:cat)
                       (clojure.core/let
                        [VAL__54479
                         (.valAt input__53984_nth_1__ :sequence)]
                        (clojure.core/let
                         [?sequence VAL__54479]
                         (clojure.core/let
                          [VAL__54480
                           (.valAt input__53984_nth_1__ :next)]
                          (if
                           (clojure.core/map? VAL__54480)
                           (clojure.core/let
                            [VAL__54481 (.valAt VAL__54480 :tag)]
                            (clojure.core/case
                             VAL__54481
                             (:empty)
                             (clojure.core/let
                              [?right input__53984_nth_2__]
                              (clojure.core/let
                               [?env input__53984_nth_3__]
                               (try
                                [(clojure.core/let
                                  [CATA_RESULT__15641__auto__
                                   (CATA__FN__54064
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
                             (state__55644)))
                           (state__55644)))))
                       (:literal)
                       (clojure.core/let
                        [VAL__54485
                         (.valAt input__53984_nth_1__ :type)]
                        (clojure.core/case
                         VAL__54485
                         (:string)
                         (clojure.core/let
                          [VAL__54486
                           (.valAt input__53984_nth_1__ :form)]
                          (clojure.core/let
                           [?form-1 VAL__54486]
                           (if
                            (clojure.core/map? input__53984_nth_2__)
                            (clojure.core/let
                             [VAL__54487
                              (.valAt input__53984_nth_2__ :tag)]
                             (clojure.core/case
                              VAL__54487
                              (:string-join)
                              (clojure.core/let
                               [VAL__54488
                                (.valAt input__53984_nth_2__ :left)]
                               (if
                                (clojure.core/map? VAL__54488)
                                (clojure.core/let
                                 [VAL__54489 (.valAt VAL__54488 :tag)]
                                 (clojure.core/case
                                  VAL__54489
                                  (:literal)
                                  (clojure.core/let
                                   [VAL__54490
                                    (.valAt VAL__54488 :type)]
                                   (clojure.core/case
                                    VAL__54490
                                    (:string)
                                    (clojure.core/let
                                     [VAL__54491
                                      (.valAt VAL__54488 :form)]
                                     (clojure.core/let
                                      [?form-2 VAL__54491]
                                      (clojure.core/let
                                       [VAL__54492
                                        (.valAt
                                         input__53984_nth_2__
                                         :right)]
                                       (clojure.core/let
                                        [?right VAL__54492]
                                        (if
                                         (clojure.core/map?
                                          input__53984_nth_3__)
                                         (clojure.core/let
                                          [VAL__54493
                                           (.valAt
                                            input__53984_nth_3__
                                            :context)]
                                          (clojure.core/case
                                           VAL__54493
                                           (:string)
                                           (clojure.core/let
                                            [?env input__53984_nth_3__]
                                            (try
                                             [(clojure.core/let
                                               [CATA_RESULT__15641__auto__
                                                (CATA__FN__54064
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
                                           (state__55644)))
                                         (state__55644))))))
                                    (state__55644)))
                                  (state__55644)))
                                (state__55644)))
                              (state__55644)))
                            (state__55644))))
                         (state__55644)))
                       (state__55644)))
                     (state__55644))
                    (state__55644))))
                 (state__55644
                  []
                  (clojure.core/case
                   input__53984_nth_0__
                   (meander.dev.parse.zeta/make-join)
                   (if
                    (clojure.core/map? input__53984_nth_1__)
                    (clojure.core/let
                     [VAL__55531 (.valAt input__53984_nth_1__ :tag)]
                     (clojure.core/case
                      VAL__55531
                      (:cat)
                      (clojure.core/let
                       [?ast input__53984_nth_1__]
                       (if
                        (clojure.core/map? input__53984_nth_2__)
                        (clojure.core/let
                         [VAL__54497
                          (.valAt input__53984_nth_2__ :tag)]
                         (clojure.core/case
                          VAL__54497
                          (:cat)
                          (clojure.core/let
                           [VAL__54498
                            (.valAt input__53984_nth_2__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__54498]
                            (clojure.core/let
                             [VAL__54499
                              (.valAt input__53984_nth_2__ :next)]
                             (clojure.core/let
                              [?next VAL__54499]
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
                          (state__55645)))
                        (state__55645)))
                      (:string-cat)
                      (clojure.core/let
                       [?ast input__53984_nth_1__]
                       (if
                        (clojure.core/map? input__53984_nth_2__)
                        (clojure.core/let
                         [VAL__54503
                          (.valAt input__53984_nth_2__ :tag)]
                         (clojure.core/case
                          VAL__54503
                          (:string-cat)
                          (clojure.core/let
                           [VAL__54504
                            (.valAt input__53984_nth_2__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__54504]
                            (clojure.core/let
                             [VAL__54505
                              (.valAt input__53984_nth_2__ :next)]
                             (clojure.core/let
                              [?next VAL__54505]
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
                          (state__55645)))
                        (state__55645)))
                      (state__55645)))
                    (state__55645))
                   (state__55645)))
                 (state__55645
                  []
                  (clojure.core/let
                   [input__53984_nth_3__
                    (clojure.core/nth input__53984 3)]
                   (clojure.core/case
                    input__53984_nth_0__
                    (meander.dev.parse.zeta/make-join)
                    (clojure.core/letfn
                     [(state__55666
                       []
                       (if
                        (clojure.core/map? input__53984_nth_1__)
                        (clojure.core/let
                         [VAL__55535
                          (.valAt input__53984_nth_1__ :next)
                          VAL__55534
                          (.valAt input__53984_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__55534
                          (:string-cat)
                          (clojure.core/let
                           [VAL__54509
                            (.valAt input__53984_nth_1__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__54509]
                            (if
                             (clojure.core/map? VAL__55535)
                             (clojure.core/let
                              [VAL__54511 (.valAt VAL__55535 :tag)]
                              (clojure.core/case
                               VAL__54511
                               (:empty)
                               (clojure.core/let
                                [?right input__53984_nth_2__]
                                (clojure.core/let
                                 [?env input__53984_nth_3__]
                                 (try
                                  [(clojure.core/let
                                    [CATA_RESULT__15641__auto__
                                     (CATA__FN__54064
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
                               (state__55667)))
                             (state__55667))))
                          (:string-star)
                          (clojure.core/let
                           [VAL__54515
                            (.valAt input__53984_nth_1__ :pattern)]
                           (clojure.core/let
                            [?pattern VAL__54515]
                            (if
                             (clojure.core/map? VAL__55535)
                             (clojure.core/let
                              [VAL__54517 (.valAt VAL__55535 :tag)]
                              (clojure.core/case
                               VAL__54517
                               (:empty)
                               (clojure.core/let
                                [?right input__53984_nth_2__]
                                (if
                                 (clojure.core/map?
                                  input__53984_nth_3__)
                                 (clojure.core/let
                                  [VAL__54518
                                   (.valAt
                                    input__53984_nth_3__
                                    :context)]
                                  (clojure.core/case
                                   VAL__54518
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
                                   (state__55667)))
                                 (state__55667)))
                               (state__55667)))
                             (state__55667))))
                          (:string-join)
                          (clojure.core/let
                           [VAL__54522
                            (.valAt input__53984_nth_1__ :left)]
                           (clojure.core/let
                            [?left VAL__54522]
                            (clojure.core/let
                             [VAL__54523
                              (.valAt input__53984_nth_1__ :right)]
                             (clojure.core/let
                              [?right-1 VAL__54523]
                              (clojure.core/let
                               [?right-2 input__53984_nth_2__]
                               (if
                                (clojure.core/map?
                                 input__53984_nth_3__)
                                (clojure.core/let
                                 [VAL__54524
                                  (.valAt
                                   input__53984_nth_3__
                                   :context)]
                                 (clojure.core/case
                                  VAL__54524
                                  (:string)
                                  (clojure.core/let
                                   [?env input__53984_nth_3__]
                                   (try
                                    [{:tag :string-join,
                                      :left ?left,
                                      :right
                                      (clojure.core/let
                                       [CATA_RESULT__15641__auto__
                                        (CATA__FN__54064
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
                                  (state__55667)))
                                (state__55667)))))))
                          (state__55667)))
                        (state__55667)))
                      (state__55667
                       []
                       (clojure.core/let
                        [?left input__53984_nth_1__]
                        (if
                         (clojure.core/map? input__53984_nth_2__)
                         (clojure.core/let
                          [VAL__54527
                           (.valAt input__53984_nth_2__ :tag)]
                          (clojure.core/case
                           VAL__54527
                           (:empty)
                           (clojure.core/let
                            [?env input__53984_nth_3__]
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
                           (state__55668)))
                         (state__55668))))
                      (state__55668
                       []
                       (if
                        (clojure.core/map? input__53984_nth_1__)
                        (clojure.core/let
                         [VAL__55533
                          (.valAt input__53984_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__55533
                          (:empty)
                          (clojure.core/let
                           [?right input__53984_nth_2__]
                           (clojure.core/let
                            [?env input__53984_nth_3__]
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
                           [VAL__54534
                            (.valAt input__53984_nth_1__ :next)]
                           (if
                            (clojure.core/map? VAL__54534)
                            (clojure.core/let
                             [VAL__54535 (.valAt VAL__54534 :tag)]
                             (clojure.core/case
                              VAL__54535
                              (:empty)
                              (clojure.core/let
                               [?left input__53984_nth_1__]
                               (clojure.core/let
                                [?right input__53984_nth_2__]
                                (clojure.core/let
                                 [?env input__53984_nth_3__]
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
                              (state__55669)))
                            (state__55669)))
                          (state__55669)))
                        (state__55669)))
                      (state__55669
                       []
                       (clojure.core/let
                        [?left input__53984_nth_1__]
                        (clojure.core/let
                         [?right input__53984_nth_2__]
                         (clojure.core/letfn
                          [(state__55670
                            []
                            (if
                             (clojure.core/map? input__53984_nth_3__)
                             (clojure.core/let
                              [VAL__54538
                               (.valAt input__53984_nth_3__ :context)]
                              (clojure.core/case
                               VAL__54538
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
                               (state__55671)))
                             (state__55671)))
                           (state__55671
                            []
                            (clojure.core/let
                             [?env input__53984_nth_3__]
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
                          (state__55670)))))]
                     (state__55666))
                    (state__55638))))]
                (state__55639)))
              (state__55638)))
            (state__55638
             []
             (if
              (clojure.core/= (clojure.core/count input__53984) 3)
              (clojure.core/let
               [input__53984_nth_0__
                (clojure.core/nth input__53984 0)
                input__53984_nth_1__
                (clojure.core/nth input__53984 1)
                input__53984_nth_2__
                (clojure.core/nth input__53984 2)]
               (clojure.core/case
                input__53984_nth_0__
                (meander.dev.parse.zeta/parse-entries)
                (if
                 (clojure.core/map? input__53984_nth_1__)
                 (clojure.core/let
                  [VAL__54543
                   (.valAt input__53984_nth_1__ :meander.zeta/as)]
                  (clojure.core/let
                   [?pattern VAL__54543]
                   (clojure.core/let
                    [X__54545
                     ((clojure.core/fn
                       [m__13405__auto__]
                       (clojure.core/dissoc
                        m__13405__auto__
                        :meander.zeta/as))
                      input__53984_nth_1__)]
                    (clojure.core/let
                     [?rest X__54545]
                     (clojure.core/letfn
                      [(save__54546 [] (state__55546))
                       (f__55672
                        []
                        (clojure.core/let
                         [?env input__53984_nth_2__]
                         (try
                          [{:tag :as,
                            :pattern
                            (clojure.core/let
                             [CATA_RESULT__15641__auto__
                              (CATA__FN__54064 [?pattern ?env])]
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
                              (CATA__FN__54064 [?rest ?env])]
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
                       (clojure.core/= ?rest input__53984_nth_1__)
                       (save__54546)
                       (f__55672)))))))
                 (state__55546))
                (state__55546)))
              (state__55546)))]
           (state__55636))
          (state__55546)))
        (state__55546
         []
         (clojure.core/letfn
          [(def__54549
            [arg__54582 ?ns]
            (clojure.core/letfn
             [(state__55673
               []
               (clojure.core/let
                [x__54583 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__54583)
                 (clojure.core/let [?env arg__54582] [?env ?ns])
                 (state__55674))))
              (state__55674
               []
               (if
                (clojure.core/map? arg__54582)
                (clojure.core/let
                 [VAL__54584 (.valAt arg__54582 :aliases)]
                 (if
                  (clojure.core/map? VAL__54584)
                  (clojure.core/let
                   [X__54586 (clojure.core/set VAL__54584)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__54586))
                    (clojure.core/loop
                     [search_space__55675 (clojure.core/seq X__54586)]
                     (if
                      (clojure.core/seq search_space__55675)
                      (clojure.core/let
                       [elem__54587
                        (clojure.core/first search_space__55675)
                        result__55676
                        (clojure.core/let
                         [elem__54587_nth_0__
                          (clojure.core/nth elem__54587 0)
                          elem__54587_nth_1__
                          (clojure.core/nth elem__54587 1)]
                         (if
                          (clojure.core/symbol? elem__54587_nth_0__)
                          (clojure.core/let
                           [X__54589
                            (clojure.core/name elem__54587_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__54589)
                            (if
                             (clojure.core/symbol? elem__54587_nth_1__)
                             (clojure.core/let
                              [X__54591
                               (clojure.core/name elem__54587_nth_1__)]
                              (clojure.core/case
                               X__54591
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__54582]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__55676)
                        (recur (clojure.core/next search_space__55675))
                        result__55676))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__55673)))]
          (if
           (clojure.core/vector? input__53984)
           (if
            (clojure.core/= (clojure.core/count input__53984) 3)
            (clojure.core/let
             [input__53984_nth_0__
              (clojure.core/nth input__53984 0)
              input__53984_nth_1__
              (clojure.core/nth input__53984 1)
              input__53984_nth_2__
              (clojure.core/nth input__53984 2)]
             (clojure.core/case
              input__53984_nth_0__
              (meander.dev.parse.zeta/parse-entries)
              (if
               (clojure.core/map? input__53984_nth_1__)
               (clojure.core/let
                [X__54554 (clojure.core/set input__53984_nth_1__)]
                (if
                 (clojure.core/<= 1 (clojure.core/count X__54554))
                 (clojure.core/loop
                  [search_space__55678 (clojure.core/seq X__54554)]
                  (if
                   (clojure.core/seq search_space__55678)
                   (clojure.core/let
                    [elem__54555
                     (clojure.core/first search_space__55678)
                     result__55679
                     (clojure.core/let
                      [elem__54555_nth_0__
                       (clojure.core/nth elem__54555 0)
                       elem__54555_nth_1__
                       (clojure.core/nth elem__54555 1)]
                      (clojure.core/let
                       [*m__54017 elem__54555_nth_0__]
                       (if
                        (clojure.core/symbol? elem__54555_nth_0__)
                        (clojure.core/let
                         [X__54557
                          (clojure.core/namespace elem__54555_nth_0__)]
                         (clojure.core/let
                          [?ns X__54557]
                          (clojure.core/let
                           [X__54559
                            (clojure.core/name elem__54555_nth_0__)]
                           (if
                            (clojure.core/string? X__54559)
                            (if
                             (clojure.core/re-matches #"&.*" X__54559)
                             (clojure.core/let
                              [?pattern elem__54555_nth_1__]
                              (clojure.core/let
                               [X__54561
                                ((clojure.core/fn
                                  [m__13405__auto__]
                                  (clojure.core/dissoc
                                   m__13405__auto__
                                   *m__54017))
                                 input__53984_nth_1__)]
                               (clojure.core/let
                                [?rest X__54561]
                                (clojure.core/let
                                 [x__14338__auto__
                                  (def__54549
                                   input__53984_nth_2__
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
                                        (CATA__FN__54064
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
                                        (CATA__FN__54064
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
                     (meander.runtime.zeta/fail? result__55679)
                     (recur (clojure.core/next search_space__55678))
                     result__55679))
                   (state__55547)))
                 (state__55547)))
               (state__55547))
              (state__55547)))
            (state__55547))
           (state__55547))))
        (state__55547
         []
         (if
          (clojure.core/vector? input__53984)
          (clojure.core/letfn
           [(state__55681
             []
             (if
              (clojure.core/= (clojure.core/count input__53984) 3)
              (clojure.core/let
               [input__53984_nth_0__
                (clojure.core/nth input__53984 0)
                input__53984_nth_1__
                (clojure.core/nth input__53984 1)
                input__53984_nth_2__
                (clojure.core/nth input__53984 2)]
               (clojure.core/case
                input__53984_nth_0__
                (meander.dev.parse.zeta/parse-entries)
                (clojure.core/letfn
                 [(state__55683
                   []
                   (if
                    (clojure.core/map? input__53984_nth_1__)
                    (clojure.core/let
                     [X__54605 (clojure.core/set input__53984_nth_1__)]
                     (if
                      (clojure.core/<= 1 (clojure.core/count X__54605))
                      (clojure.core/loop
                       [search_space__55685
                        (clojure.core/seq X__54605)]
                       (if
                        (clojure.core/seq search_space__55685)
                        (clojure.core/let
                         [elem__54606
                          (clojure.core/first search_space__55685)
                          result__55686
                          (clojure.core/let
                           [elem__54606_nth_0__
                            (clojure.core/nth elem__54606 0)
                            elem__54606_nth_1__
                            (clojure.core/nth elem__54606 1)]
                           (clojure.core/let
                            [?key-pattern elem__54606_nth_0__]
                            (clojure.core/let
                             [?val-pattern elem__54606_nth_1__]
                             (clojure.core/let
                              [X__54608
                               ((clojure.core/fn
                                 [m__13405__auto__]
                                 (clojure.core/dissoc
                                  m__13405__auto__
                                  ?key-pattern))
                                input__53984_nth_1__)]
                              (clojure.core/let
                               [?rest X__54608]
                               (clojure.core/let
                                [?env input__53984_nth_2__]
                                (try
                                 [{:tag :entry,
                                   :key-pattern
                                   (clojure.core/let
                                    [CATA_RESULT__15641__auto__
                                     (CATA__FN__54064
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
                                     (CATA__FN__54064
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
                                     (CATA__FN__54064
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
                          (meander.runtime.zeta/fail? result__55686)
                          (recur
                           (clojure.core/next search_space__55685))
                          result__55686))
                        (state__55684)))
                      (state__55684)))
                    (state__55684)))
                  (state__55684
                   []
                   (if
                    (clojure.core/map? input__53984_nth_1__)
                    (clojure.core/let
                     [?env input__53984_nth_2__]
                     (try
                      [{:tag :some-map}]
                      (catch
                       java.lang.Exception
                       e__16581__auto__
                       (if
                        (meander.runtime.zeta/fail? e__16581__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__16581__auto__)))))
                    (state__55682)))]
                 (state__55683))
                (meander.dev.parse.zeta/parse-with-bindings)
                (clojure.core/letfn
                 [(state__55688
                   []
                   (if
                    (clojure.core/vector? input__53984_nth_1__)
                    (clojure.core/case
                     input__53984_nth_1__
                     ([])
                     (clojure.core/let
                      [?env input__53984_nth_2__]
                      (try
                       [[]]
                       (catch
                        java.lang.Exception
                        e__16581__auto__
                        (if
                         (meander.runtime.zeta/fail? e__16581__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__16581__auto__)))))
                     (state__55689))
                    (state__55689)))
                  (state__55689
                   []
                   (if
                    (clojure.core/vector? input__53984_nth_1__)
                    (if
                     (clojure.core/=
                      (clojure.core/count input__53984_nth_1__)
                      1)
                     (clojure.core/let
                      [?env input__53984_nth_2__]
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
                     (state__55690))
                    (state__55690)))
                  (state__55690
                   []
                   (if
                    (clojure.core/vector? input__53984_nth_1__)
                    (clojure.core/let
                     [input__53984_nth_1___l__
                      (clojure.core/subvec
                       input__53984_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__53984_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__53984_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__53984_nth_1___r__
                        (clojure.core/subvec input__53984_nth_1__ 2)]
                       (clojure.core/let
                        [input__53984_nth_1___l___nth_0__
                         (clojure.core/nth input__53984_nth_1___l__ 0)
                         input__53984_nth_1___l___nth_1__
                         (clojure.core/nth input__53984_nth_1___l__ 1)]
                        (if
                         (clojure.core/symbol?
                          input__53984_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__54622
                           (clojure.core/namespace
                            input__53984_nth_1___l___nth_0__)]
                          (clojure.core/let
                           [X__54624
                            (clojure.core/name
                             input__53984_nth_1___l___nth_0__)]
                           (if
                            (clojure.core/string? X__54624)
                            (if
                             (clojure.core/re-matches #"%.+" X__54624)
                             (clojure.core/let
                              [?symbol
                               input__53984_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?pattern
                                input__53984_nth_1___l___nth_1__]
                               (clojure.core/let
                                [?rest input__53984_nth_1___r__]
                                (clojure.core/let
                                 [?env input__53984_nth_2__]
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
                                         (CATA__FN__54064
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
                                       (CATA__FN__54064
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
                             (state__55691))
                            (state__55691))))
                         (state__55691))))
                      (state__55691)))
                    (state__55691)))
                  (state__55691
                   []
                   (if
                    (clojure.core/vector? input__53984_nth_1__)
                    (clojure.core/let
                     [input__53984_nth_1___l__
                      (clojure.core/subvec
                       input__53984_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__53984_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__53984_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__53984_nth_1___r__
                        (clojure.core/subvec input__53984_nth_1__ 2)]
                       (clojure.core/let
                        [input__53984_nth_1___l___nth_0__
                         (clojure.core/nth input__53984_nth_1___l__ 0)
                         input__53984_nth_1___l___nth_1__
                         (clojure.core/nth input__53984_nth_1___l__ 1)]
                        (clojure.core/let
                         [?x input__53984_nth_1___l___nth_0__]
                         (clojure.core/let
                          [?pattern input__53984_nth_1___l___nth_1__]
                          (clojure.core/let
                           [?rest input__53984_nth_1___r__]
                           (clojure.core/let
                            [?env input__53984_nth_2__]
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
                      (state__55682)))
                    (state__55682)))]
                 (state__55688))
                (state__55682)))
              (state__55682)))
            (state__55682
             []
             (if
              (clojure.core/= (clojure.core/count input__53984) 2)
              (clojure.core/let
               [input__53984_nth_0__
                (clojure.core/nth input__53984 0)
                input__53984_nth_1__
                (clojure.core/nth input__53984 1)]
               (if
                (clojure.core/vector? input__53984_nth_0__)
                (clojure.core/let
                 [?sequence input__53984_nth_0__]
                 (clojure.core/let
                  [?form input__53984_nth_0__]
                  (clojure.core/let
                   [?env input__53984_nth_1__]
                   (try
                    [{:tag :vector,
                      :next
                      (clojure.core/let
                       [CATA_RESULT__15641__auto__
                        (CATA__FN__54064
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
                (state__55548)))
              (state__55548)))]
           (state__55681))
          (state__55548)))
        (state__55548
         []
         (clojure.core/letfn
          [(def__54634
            [arg__54657 ?__53985]
            (clojure.core/letfn
             [(state__55692
               []
               (clojure.core/let
                [x__54658 "clojure.core"]
                (if
                 (clojure.core/= ?__53985 x__54658)
                 [?__53985]
                 (state__55693))))
              (state__55693
               []
               (if
                (clojure.core/map? arg__54657)
                (clojure.core/let
                 [VAL__54659 (.valAt arg__54657 :aliases)]
                 (if
                  (clojure.core/map? VAL__54659)
                  (clojure.core/let
                   [X__54661 (clojure.core/set VAL__54659)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__54661))
                    (clojure.core/loop
                     [search_space__55694 (clojure.core/seq X__54661)]
                     (if
                      (clojure.core/seq search_space__55694)
                      (clojure.core/let
                       [elem__54662
                        (clojure.core/first search_space__55694)
                        result__55695
                        (clojure.core/let
                         [elem__54662_nth_0__
                          (clojure.core/nth elem__54662 0)
                          elem__54662_nth_1__
                          (clojure.core/nth elem__54662 1)]
                         (if
                          (clojure.core/symbol? elem__54662_nth_0__)
                          (clojure.core/let
                           [X__54664
                            (clojure.core/name elem__54662_nth_0__)]
                           (if
                            (clojure.core/= ?__53985 X__54664)
                            (if
                             (clojure.core/symbol? elem__54662_nth_1__)
                             (clojure.core/let
                              [X__54666
                               (clojure.core/name elem__54662_nth_1__)]
                              (clojure.core/case
                               X__54666
                               ("clojure.core")
                               [?__53985]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__55695)
                        (recur (clojure.core/next search_space__55694))
                        result__55695))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__55692)))]
          (if
           (clojure.core/vector? input__53984)
           (if
            (clojure.core/= (clojure.core/count input__53984) 2)
            (clojure.core/let
             [input__53984_nth_0__
              (clojure.core/nth input__53984 0)
              input__53984_nth_1__
              (clojure.core/nth input__53984 1)]
             (if
              (clojure.core/seq? input__53984_nth_0__)
              (clojure.core/let
               [input__53984_nth_0___l__
                (clojure.core/take 1 input__53984_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__53984_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__53984_nth_0___r__
                  (clojure.core/drop 1 input__53984_nth_0__)]
                 (clojure.core/let
                  [input__53984_nth_0___l___nth_0__
                   (clojure.core/nth input__53984_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__53984_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__54644
                     (clojure.core/namespace
                      input__53984_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__53985 X__54644]
                     (clojure.core/let
                      [X__54646
                       (clojure.core/name
                        input__53984_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__54646
                       ("unquote")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__54634 input__53984_nth_1__ ?__53985)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__55549)
                         (clojure.core/let
                          [[?__53985] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__53984)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__53984)
                             2)
                            (clojure.core/let
                             [input__53984_nth_0__
                              (clojure.core/nth input__53984 0)
                              input__53984_nth_1__
                              (clojure.core/nth input__53984 1)]
                             (if
                              (clojure.core/seq? input__53984_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__53984_nth_0__)
                                2)
                               (clojure.core/let
                                [input__53984_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__53984_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?x input__53984_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__53984_nth_0__]
                                  (clojure.core/let
                                   [?env input__53984_nth_1__]
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
                               (state__55549))
                              (state__55549)))
                            (state__55549))
                           (state__55549)))))
                       (state__55549)))))
                   (state__55549))))
                (state__55549)))
              (state__55549)))
            (state__55549))
           (state__55549))))
        (state__55549
         []
         (clojure.core/letfn
          [(def__54668
            [arg__54691 ?__53986]
            (clojure.core/letfn
             [(state__55697
               []
               (clojure.core/let
                [x__54692 "meander.zeta"]
                (if
                 (clojure.core/= ?__53986 x__54692)
                 [?__53986]
                 (state__55698))))
              (state__55698
               []
               (if
                (clojure.core/map? arg__54691)
                (clojure.core/let
                 [VAL__54693 (.valAt arg__54691 :aliases)]
                 (if
                  (clojure.core/map? VAL__54693)
                  (clojure.core/let
                   [X__54695 (clojure.core/set VAL__54693)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__54695))
                    (clojure.core/loop
                     [search_space__55699 (clojure.core/seq X__54695)]
                     (if
                      (clojure.core/seq search_space__55699)
                      (clojure.core/let
                       [elem__54696
                        (clojure.core/first search_space__55699)
                        result__55700
                        (clojure.core/let
                         [elem__54696_nth_0__
                          (clojure.core/nth elem__54696 0)
                          elem__54696_nth_1__
                          (clojure.core/nth elem__54696 1)]
                         (if
                          (clojure.core/symbol? elem__54696_nth_0__)
                          (clojure.core/let
                           [X__54698
                            (clojure.core/name elem__54696_nth_0__)]
                           (if
                            (clojure.core/= ?__53986 X__54698)
                            (if
                             (clojure.core/symbol? elem__54696_nth_1__)
                             (clojure.core/let
                              [X__54700
                               (clojure.core/name elem__54696_nth_1__)]
                              (clojure.core/case
                               X__54700
                               ("meander.zeta")
                               [?__53986]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__55700)
                        (recur (clojure.core/next search_space__55699))
                        result__55700))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__55697)))]
          (if
           (clojure.core/vector? input__53984)
           (if
            (clojure.core/= (clojure.core/count input__53984) 2)
            (clojure.core/let
             [input__53984_nth_0__
              (clojure.core/nth input__53984 0)
              input__53984_nth_1__
              (clojure.core/nth input__53984 1)]
             (if
              (clojure.core/seq? input__53984_nth_0__)
              (clojure.core/let
               [input__53984_nth_0___l__
                (clojure.core/take 1 input__53984_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__53984_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__53984_nth_0___r__
                  (clojure.core/drop 1 input__53984_nth_0__)]
                 (clojure.core/let
                  [input__53984_nth_0___l___nth_0__
                   (clojure.core/nth input__53984_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__53984_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__54678
                     (clojure.core/namespace
                      input__53984_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__53986 X__54678]
                     (clojure.core/let
                      [X__54680
                       (clojure.core/name
                        input__53984_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__54680
                       ("*")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__54668 input__53984_nth_1__ ?__53986)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__55550)
                         (clojure.core/let
                          [[?__53986] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__53984)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__53984)
                             2)
                            (clojure.core/let
                             [input__53984_nth_0__
                              (clojure.core/nth input__53984 0)
                              input__53984_nth_1__
                              (clojure.core/nth input__53984 1)]
                             (if
                              (clojure.core/seq? input__53984_nth_0__)
                              (clojure.core/let
                               [input__53984_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__53984_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__53984_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__53984_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__53984_nth_0__)]
                                 (clojure.core/let
                                  [?patterns input__53984_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__53984_nth_0__]
                                   (clojure.core/let
                                    [?env input__53984_nth_1__]
                                    (try
                                     [{:tag :star,
                                       :greedy? true,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__54064
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
                                (state__55550)))
                              (state__55550)))
                            (state__55550))
                           (state__55550)))))
                       (state__55550)))))
                   (state__55550))))
                (state__55550)))
              (state__55550)))
            (state__55550))
           (state__55550))))
        (state__55550
         []
         (clojure.core/letfn
          [(def__54702
            [arg__54725 ?__53987]
            (clojure.core/letfn
             [(state__55702
               []
               (clojure.core/let
                [x__54726 "meander.zeta"]
                (if
                 (clojure.core/= ?__53987 x__54726)
                 [?__53987]
                 (state__55703))))
              (state__55703
               []
               (if
                (clojure.core/map? arg__54725)
                (clojure.core/let
                 [VAL__54727 (.valAt arg__54725 :aliases)]
                 (if
                  (clojure.core/map? VAL__54727)
                  (clojure.core/let
                   [X__54729 (clojure.core/set VAL__54727)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__54729))
                    (clojure.core/loop
                     [search_space__55704 (clojure.core/seq X__54729)]
                     (if
                      (clojure.core/seq search_space__55704)
                      (clojure.core/let
                       [elem__54730
                        (clojure.core/first search_space__55704)
                        result__55705
                        (clojure.core/let
                         [elem__54730_nth_0__
                          (clojure.core/nth elem__54730 0)
                          elem__54730_nth_1__
                          (clojure.core/nth elem__54730 1)]
                         (if
                          (clojure.core/symbol? elem__54730_nth_0__)
                          (clojure.core/let
                           [X__54732
                            (clojure.core/name elem__54730_nth_0__)]
                           (if
                            (clojure.core/= ?__53987 X__54732)
                            (if
                             (clojure.core/symbol? elem__54730_nth_1__)
                             (clojure.core/let
                              [X__54734
                               (clojure.core/name elem__54730_nth_1__)]
                              (clojure.core/case
                               X__54734
                               ("meander.zeta")
                               [?__53987]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__55705)
                        (recur (clojure.core/next search_space__55704))
                        result__55705))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__55702)))]
          (if
           (clojure.core/vector? input__53984)
           (if
            (clojure.core/= (clojure.core/count input__53984) 2)
            (clojure.core/let
             [input__53984_nth_0__
              (clojure.core/nth input__53984 0)
              input__53984_nth_1__
              (clojure.core/nth input__53984 1)]
             (if
              (clojure.core/seq? input__53984_nth_0__)
              (clojure.core/let
               [input__53984_nth_0___l__
                (clojure.core/take 1 input__53984_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__53984_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__53984_nth_0___r__
                  (clojure.core/drop 1 input__53984_nth_0__)]
                 (clojure.core/let
                  [input__53984_nth_0___l___nth_0__
                   (clojure.core/nth input__53984_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__53984_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__54712
                     (clojure.core/namespace
                      input__53984_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__53987 X__54712]
                     (clojure.core/let
                      [X__54714
                       (clojure.core/name
                        input__53984_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__54714
                       ("<>")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__54702 input__53984_nth_1__ ?__53987)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__55551)
                         (clojure.core/let
                          [[?__53987] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__53984)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__53984)
                             2)
                            (clojure.core/let
                             [input__53984_nth_0__
                              (clojure.core/nth input__53984 0)
                              input__53984_nth_1__
                              (clojure.core/nth input__53984 1)]
                             (if
                              (clojure.core/seq? input__53984_nth_0__)
                              (clojure.core/let
                               [input__53984_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__53984_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__53984_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__53984_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__53984_nth_0__)]
                                 (clojure.core/let
                                  [?patterns input__53984_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__53984_nth_0__]
                                   (clojure.core/let
                                    [?env input__53984_nth_1__]
                                    (try
                                     [{:tag :group,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__54064
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
                                (state__55551)))
                              (state__55551)))
                            (state__55551))
                           (state__55551)))))
                       (state__55551)))))
                   (state__55551))))
                (state__55551)))
              (state__55551)))
            (state__55551))
           (state__55551))))
        (state__55551
         []
         (clojure.core/letfn
          [(def__54736
            [arg__54759 ?__53988]
            (clojure.core/letfn
             [(state__55707
               []
               (clojure.core/let
                [x__54760 "meander.math.zeta"]
                (if
                 (clojure.core/= ?__53988 x__54760)
                 [?__53988]
                 (state__55708))))
              (state__55708
               []
               (if
                (clojure.core/map? arg__54759)
                (clojure.core/let
                 [VAL__54761 (.valAt arg__54759 :aliases)]
                 (if
                  (clojure.core/map? VAL__54761)
                  (clojure.core/let
                   [X__54763 (clojure.core/set VAL__54761)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__54763))
                    (clojure.core/loop
                     [search_space__55709 (clojure.core/seq X__54763)]
                     (if
                      (clojure.core/seq search_space__55709)
                      (clojure.core/let
                       [elem__54764
                        (clojure.core/first search_space__55709)
                        result__55710
                        (clojure.core/let
                         [elem__54764_nth_0__
                          (clojure.core/nth elem__54764 0)
                          elem__54764_nth_1__
                          (clojure.core/nth elem__54764 1)]
                         (if
                          (clojure.core/symbol? elem__54764_nth_0__)
                          (clojure.core/let
                           [X__54766
                            (clojure.core/name elem__54764_nth_0__)]
                           (if
                            (clojure.core/= ?__53988 X__54766)
                            (if
                             (clojure.core/symbol? elem__54764_nth_1__)
                             (clojure.core/let
                              [X__54768
                               (clojure.core/name elem__54764_nth_1__)]
                              (clojure.core/case
                               X__54768
                               ("meander.math.zeta")
                               [?__53988]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__55710)
                        (recur (clojure.core/next search_space__55709))
                        result__55710))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__55707)))]
          (if
           (clojure.core/vector? input__53984)
           (if
            (clojure.core/= (clojure.core/count input__53984) 2)
            (clojure.core/let
             [input__53984_nth_0__
              (clojure.core/nth input__53984 0)
              input__53984_nth_1__
              (clojure.core/nth input__53984 1)]
             (if
              (clojure.core/seq? input__53984_nth_0__)
              (clojure.core/let
               [input__53984_nth_0___l__
                (clojure.core/take 1 input__53984_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__53984_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__53984_nth_0___r__
                  (clojure.core/drop 1 input__53984_nth_0__)]
                 (clojure.core/let
                  [input__53984_nth_0___l___nth_0__
                   (clojure.core/nth input__53984_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__53984_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__54746
                     (clojure.core/namespace
                      input__53984_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__53988 X__54746]
                     (clojure.core/let
                      [X__54748
                       (clojure.core/name
                        input__53984_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__54748
                       ("+")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__54736 input__53984_nth_1__ ?__53988)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__55552)
                         (clojure.core/let
                          [[?__53988] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__53984)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__53984)
                             2)
                            (clojure.core/let
                             [input__53984_nth_0__
                              (clojure.core/nth input__53984 0)
                              input__53984_nth_1__
                              (clojure.core/nth input__53984 1)]
                             (if
                              (clojure.core/seq? input__53984_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__53984_nth_0__)
                                3)
                               (clojure.core/let
                                [input__53984_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__53984_nth_0__
                                  1)
                                 input__53984_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__53984_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?a input__53984_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?b input__53984_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__53984_nth_0__]
                                   (clojure.core/let
                                    [?env input__53984_nth_1__]
                                    (try
                                     [{:tag :meander.math.zeta/+,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__54064 [?a ?env])]
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
                                         (CATA__FN__54064 [?b ?env])]
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
                               (state__55552))
                              (state__55552)))
                            (state__55552))
                           (state__55552)))))
                       (state__55552)))))
                   (state__55552))))
                (state__55552)))
              (state__55552)))
            (state__55552))
           (state__55552))))
        (state__55552
         []
         (clojure.core/letfn
          [(def__54770
            [arg__54793 ?__53989]
            (clojure.core/letfn
             [(state__55712
               []
               (clojure.core/let
                [x__54794 "meander.math.zeta"]
                (if
                 (clojure.core/= ?__53989 x__54794)
                 [?__53989]
                 (state__55713))))
              (state__55713
               []
               (if
                (clojure.core/map? arg__54793)
                (clojure.core/let
                 [VAL__54795 (.valAt arg__54793 :aliases)]
                 (if
                  (clojure.core/map? VAL__54795)
                  (clojure.core/let
                   [X__54797 (clojure.core/set VAL__54795)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__54797))
                    (clojure.core/loop
                     [search_space__55714 (clojure.core/seq X__54797)]
                     (if
                      (clojure.core/seq search_space__55714)
                      (clojure.core/let
                       [elem__54798
                        (clojure.core/first search_space__55714)
                        result__55715
                        (clojure.core/let
                         [elem__54798_nth_0__
                          (clojure.core/nth elem__54798 0)
                          elem__54798_nth_1__
                          (clojure.core/nth elem__54798 1)]
                         (if
                          (clojure.core/symbol? elem__54798_nth_0__)
                          (clojure.core/let
                           [X__54800
                            (clojure.core/name elem__54798_nth_0__)]
                           (if
                            (clojure.core/= ?__53989 X__54800)
                            (if
                             (clojure.core/symbol? elem__54798_nth_1__)
                             (clojure.core/let
                              [X__54802
                               (clojure.core/name elem__54798_nth_1__)]
                              (clojure.core/case
                               X__54802
                               ("meander.math.zeta")
                               [?__53989]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__55715)
                        (recur (clojure.core/next search_space__55714))
                        result__55715))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__55712)))]
          (if
           (clojure.core/vector? input__53984)
           (if
            (clojure.core/= (clojure.core/count input__53984) 2)
            (clojure.core/let
             [input__53984_nth_0__
              (clojure.core/nth input__53984 0)
              input__53984_nth_1__
              (clojure.core/nth input__53984 1)]
             (if
              (clojure.core/seq? input__53984_nth_0__)
              (clojure.core/let
               [input__53984_nth_0___l__
                (clojure.core/take 1 input__53984_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__53984_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__53984_nth_0___r__
                  (clojure.core/drop 1 input__53984_nth_0__)]
                 (clojure.core/let
                  [input__53984_nth_0___l___nth_0__
                   (clojure.core/nth input__53984_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__53984_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__54780
                     (clojure.core/namespace
                      input__53984_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__53989 X__54780]
                     (clojure.core/let
                      [X__54782
                       (clojure.core/name
                        input__53984_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__54782
                       ("-")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__54770 input__53984_nth_1__ ?__53989)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__55553)
                         (clojure.core/let
                          [[?__53989] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__53984)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__53984)
                             2)
                            (clojure.core/let
                             [input__53984_nth_0__
                              (clojure.core/nth input__53984 0)
                              input__53984_nth_1__
                              (clojure.core/nth input__53984 1)]
                             (if
                              (clojure.core/seq? input__53984_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__53984_nth_0__)
                                3)
                               (clojure.core/let
                                [input__53984_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__53984_nth_0__
                                  1)
                                 input__53984_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__53984_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?a input__53984_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?b input__53984_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__53984_nth_0__]
                                   (clojure.core/let
                                    [?env input__53984_nth_1__]
                                    (try
                                     [{:tag :meander.math.zeta/-,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__54064 [?a ?env])]
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
                                         (CATA__FN__54064 [?b ?env])]
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
                               (state__55553))
                              (state__55553)))
                            (state__55553))
                           (state__55553)))))
                       (state__55553)))))
                   (state__55553))))
                (state__55553)))
              (state__55553)))
            (state__55553))
           (state__55553))))
        (state__55553
         []
         (clojure.core/letfn
          [(def__54804
            [arg__54827 ?__53990]
            (clojure.core/letfn
             [(state__55717
               []
               (clojure.core/let
                [x__54828 "meander.zeta"]
                (if
                 (clojure.core/= ?__53990 x__54828)
                 [?__53990]
                 (state__55718))))
              (state__55718
               []
               (if
                (clojure.core/map? arg__54827)
                (clojure.core/let
                 [VAL__54829 (.valAt arg__54827 :aliases)]
                 (if
                  (clojure.core/map? VAL__54829)
                  (clojure.core/let
                   [X__54831 (clojure.core/set VAL__54829)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__54831))
                    (clojure.core/loop
                     [search_space__55719 (clojure.core/seq X__54831)]
                     (if
                      (clojure.core/seq search_space__55719)
                      (clojure.core/let
                       [elem__54832
                        (clojure.core/first search_space__55719)
                        result__55720
                        (clojure.core/let
                         [elem__54832_nth_0__
                          (clojure.core/nth elem__54832 0)
                          elem__54832_nth_1__
                          (clojure.core/nth elem__54832 1)]
                         (if
                          (clojure.core/symbol? elem__54832_nth_0__)
                          (clojure.core/let
                           [X__54834
                            (clojure.core/name elem__54832_nth_0__)]
                           (if
                            (clojure.core/= ?__53990 X__54834)
                            (if
                             (clojure.core/symbol? elem__54832_nth_1__)
                             (clojure.core/let
                              [X__54836
                               (clojure.core/name elem__54832_nth_1__)]
                              (clojure.core/case
                               X__54836
                               ("meander.zeta")
                               [?__53990]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__55720)
                        (recur (clojure.core/next search_space__55719))
                        result__55720))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__55717)))]
          (if
           (clojure.core/vector? input__53984)
           (if
            (clojure.core/= (clojure.core/count input__53984) 2)
            (clojure.core/let
             [input__53984_nth_0__
              (clojure.core/nth input__53984 0)
              input__53984_nth_1__
              (clojure.core/nth input__53984 1)]
             (if
              (clojure.core/seq? input__53984_nth_0__)
              (clojure.core/let
               [input__53984_nth_0___l__
                (clojure.core/take 1 input__53984_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__53984_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__53984_nth_0___r__
                  (clojure.core/drop 1 input__53984_nth_0__)]
                 (clojure.core/let
                  [input__53984_nth_0___l___nth_0__
                   (clojure.core/nth input__53984_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__53984_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__54814
                     (clojure.core/namespace
                      input__53984_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__53990 X__54814]
                     (clojure.core/let
                      [X__54816
                       (clojure.core/name
                        input__53984_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__54816
                       ("with")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__54804 input__53984_nth_1__ ?__53990)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__55554)
                         (clojure.core/let
                          [[?__53990] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__53984)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__53984)
                             2)
                            (clojure.core/let
                             [input__53984_nth_0__
                              (clojure.core/nth input__53984 0)
                              input__53984_nth_1__
                              (clojure.core/nth input__53984 1)]
                             (if
                              (clojure.core/seq? input__53984_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__53984_nth_0__)
                                3)
                               (clojure.core/let
                                [input__53984_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__53984_nth_0__
                                  1)
                                 input__53984_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__53984_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?bindings
                                  input__53984_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?body input__53984_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__53984_nth_0__]
                                   (clojure.core/let
                                    [?env input__53984_nth_1__]
                                    (try
                                     [{:tag :with,
                                       :bindings
                                       {:tag :with-bindings,
                                        :bindings
                                        (clojure.core/let
                                         [CATA_RESULT__15641__auto__
                                          (CATA__FN__54064
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
                                         (CATA__FN__54064
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
                               (state__55554))
                              (state__55554)))
                            (state__55554))
                           (state__55554)))))
                       (state__55554)))))
                   (state__55554))))
                (state__55554)))
              (state__55554)))
            (state__55554))
           (state__55554))))
        (state__55554
         []
         (clojure.core/letfn
          [(def__54838
            [arg__54861 ?__53991]
            (clojure.core/letfn
             [(state__55722
               []
               (clojure.core/let
                [x__54862 "meander.zeta"]
                (if
                 (clojure.core/= ?__53991 x__54862)
                 [?__53991]
                 (state__55723))))
              (state__55723
               []
               (if
                (clojure.core/map? arg__54861)
                (clojure.core/let
                 [VAL__54863 (.valAt arg__54861 :aliases)]
                 (if
                  (clojure.core/map? VAL__54863)
                  (clojure.core/let
                   [X__54865 (clojure.core/set VAL__54863)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__54865))
                    (clojure.core/loop
                     [search_space__55724 (clojure.core/seq X__54865)]
                     (if
                      (clojure.core/seq search_space__55724)
                      (clojure.core/let
                       [elem__54866
                        (clojure.core/first search_space__55724)
                        result__55725
                        (clojure.core/let
                         [elem__54866_nth_0__
                          (clojure.core/nth elem__54866 0)
                          elem__54866_nth_1__
                          (clojure.core/nth elem__54866 1)]
                         (if
                          (clojure.core/symbol? elem__54866_nth_0__)
                          (clojure.core/let
                           [X__54868
                            (clojure.core/name elem__54866_nth_0__)]
                           (if
                            (clojure.core/= ?__53991 X__54868)
                            (if
                             (clojure.core/symbol? elem__54866_nth_1__)
                             (clojure.core/let
                              [X__54870
                               (clojure.core/name elem__54866_nth_1__)]
                              (clojure.core/case
                               X__54870
                               ("meander.zeta")
                               [?__53991]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__55725)
                        (recur (clojure.core/next search_space__55724))
                        result__55725))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__55722)))]
          (if
           (clojure.core/vector? input__53984)
           (if
            (clojure.core/= (clojure.core/count input__53984) 2)
            (clojure.core/let
             [input__53984_nth_0__
              (clojure.core/nth input__53984 0)
              input__53984_nth_1__
              (clojure.core/nth input__53984 1)]
             (if
              (clojure.core/seq? input__53984_nth_0__)
              (clojure.core/let
               [input__53984_nth_0___l__
                (clojure.core/take 1 input__53984_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__53984_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__53984_nth_0___r__
                  (clojure.core/drop 1 input__53984_nth_0__)]
                 (clojure.core/let
                  [input__53984_nth_0___l___nth_0__
                   (clojure.core/nth input__53984_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__53984_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__54848
                     (clojure.core/namespace
                      input__53984_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__53991 X__54848]
                     (clojure.core/let
                      [X__54850
                       (clojure.core/name
                        input__53984_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__54850
                       ("apply")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__54838 input__53984_nth_1__ ?__53991)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__55555)
                         (clojure.core/let
                          [[?__53991] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__53984)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__53984)
                             2)
                            (clojure.core/let
                             [input__53984_nth_0__
                              (clojure.core/nth input__53984 0)
                              input__53984_nth_1__
                              (clojure.core/nth input__53984 1)]
                             (if
                              (clojure.core/seq? input__53984_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__53984_nth_0__)
                                3)
                               (clojure.core/let
                                [input__53984_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__53984_nth_0__
                                  1)
                                 input__53984_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__53984_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?fn input__53984_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?pattern
                                   input__53984_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__53984_nth_0__]
                                   (clojure.core/let
                                    [?env input__53984_nth_1__]
                                    (try
                                     [{:tag :apply,
                                       :fn ?fn,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__54064
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
                               (state__55555))
                              (state__55555)))
                            (state__55555))
                           (state__55555)))))
                       (state__55555)))))
                   (state__55555))))
                (state__55555)))
              (state__55555)))
            (state__55555))
           (state__55555))))
        (state__55555
         []
         (clojure.core/letfn
          [(def__54872
            [arg__54897 ?__53992]
            (clojure.core/letfn
             [(state__55727
               []
               (clojure.core/let
                [x__54898 "meander.zeta"]
                (if
                 (clojure.core/= ?__53992 x__54898)
                 [?__53992]
                 (state__55728))))
              (state__55728
               []
               (if
                (clojure.core/map? arg__54897)
                (clojure.core/let
                 [VAL__54899 (.valAt arg__54897 :aliases)]
                 (if
                  (clojure.core/map? VAL__54899)
                  (clojure.core/let
                   [X__54901 (clojure.core/set VAL__54899)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__54901))
                    (clojure.core/loop
                     [search_space__55729 (clojure.core/seq X__54901)]
                     (if
                      (clojure.core/seq search_space__55729)
                      (clojure.core/let
                       [elem__54902
                        (clojure.core/first search_space__55729)
                        result__55730
                        (clojure.core/let
                         [elem__54902_nth_0__
                          (clojure.core/nth elem__54902 0)
                          elem__54902_nth_1__
                          (clojure.core/nth elem__54902 1)]
                         (if
                          (clojure.core/symbol? elem__54902_nth_0__)
                          (clojure.core/let
                           [X__54904
                            (clojure.core/name elem__54902_nth_0__)]
                           (if
                            (clojure.core/= ?__53992 X__54904)
                            (if
                             (clojure.core/symbol? elem__54902_nth_1__)
                             (clojure.core/let
                              [X__54906
                               (clojure.core/name elem__54902_nth_1__)]
                              (clojure.core/case
                               X__54906
                               ("meander.zeta")
                               [?__53992]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__55730)
                        (recur (clojure.core/next search_space__55729))
                        result__55730))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__55727)))]
          (if
           (clojure.core/vector? input__53984)
           (if
            (clojure.core/= (clojure.core/count input__53984) 2)
            (clojure.core/let
             [input__53984_nth_0__
              (clojure.core/nth input__53984 0)
              input__53984_nth_1__
              (clojure.core/nth input__53984 1)]
             (if
              (clojure.core/seq? input__53984_nth_0__)
              (clojure.core/let
               [input__53984_nth_0___l__
                (clojure.core/take 1 input__53984_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__53984_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__53984_nth_0___r__
                  (clojure.core/drop 1 input__53984_nth_0__)]
                 (clojure.core/let
                  [input__53984_nth_0___l___nth_0__
                   (clojure.core/nth input__53984_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__53984_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__54884
                     (clojure.core/namespace
                      input__53984_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__53992 X__54884]
                     (clojure.core/let
                      [X__54886
                       (clojure.core/name
                        input__53984_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__54886
                       ("and")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__54872 input__53984_nth_1__ ?__53992)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__55556)
                         (clojure.core/let
                          [[?__53992] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__53984)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__53984)
                             2)
                            (clojure.core/let
                             [input__53984_nth_0__
                              (clojure.core/nth input__53984 0)
                              input__53984_nth_1__
                              (clojure.core/nth input__53984 1)]
                             (if
                              (clojure.core/seq? input__53984_nth_0__)
                              (clojure.core/let
                               [input__53984_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__53984_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__53984_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__53984_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__53984_nth_0__)]
                                 (clojure.core/let
                                  [!forms
                                   (clojure.core/vec
                                    input__53984_nth_0___r__)]
                                  (clojure.core/let
                                   [?form input__53984_nth_0__]
                                   (clojure.core/let
                                    [?env input__53984_nth_1__]
                                    (try
                                     [(clojure.core/let
                                       [!forms__counter
                                        (meander.runtime.zeta/iterator
                                         !forms)]
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__54064
                                          ['meander.dev.parse.zeta/make-and
                                           (clojure.core/into
                                            []
                                            (clojure.core/loop
                                             [return__54066
                                              (clojure.core/transient
                                               [])]
                                             (if
                                              (clojure.core/and
                                               (.hasNext
                                                !forms__counter))
                                              (recur
                                               (clojure.core/conj!
                                                return__54066
                                                (clojure.core/let
                                                 [CATA_RESULT__15641__auto__
                                                  (CATA__FN__54064
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
                                               return__54066))))
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
                                (state__55556)))
                              (state__55556)))
                            (state__55556))
                           (state__55556)))))
                       (state__55556)))))
                   (state__55556))))
                (state__55556)))
              (state__55556)))
            (state__55556))
           (state__55556))))
        (state__55556
         []
         (if
          (clojure.core/vector? input__53984)
          (if
           (clojure.core/= (clojure.core/count input__53984) 3)
           (clojure.core/let
            [input__53984_nth_0__
             (clojure.core/nth input__53984 0)
             input__53984_nth_1__
             (clojure.core/nth input__53984 1)
             input__53984_nth_2__
             (clojure.core/nth input__53984 2)]
            (clojure.core/case
             input__53984_nth_0__
             (meander.dev.parse.zeta/make-and)
             (clojure.core/letfn
              [(state__55732
                []
                (if
                 (clojure.core/vector? input__53984_nth_1__)
                 (clojure.core/case
                  input__53984_nth_1__
                  ([])
                  (clojure.core/let
                   [?form input__53984_nth_2__]
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
                  (state__55733))
                 (state__55733)))
               (state__55733
                []
                (clojure.core/case
                 input__53984_nth_2__
                 (nil)
                 (if
                  (clojure.core/vector? input__53984_nth_1__)
                  (if
                   (clojure.core/=
                    (clojure.core/count input__53984_nth_1__)
                    1)
                   (clojure.core/let
                    [input__53984_nth_1___nth_0__
                     (clojure.core/nth input__53984_nth_1__ 0)]
                    (clojure.core/let
                     [?ast-a input__53984_nth_1___nth_0__]
                     (try
                      [?ast-a]
                      (catch
                       java.lang.Exception
                       e__16581__auto__
                       (if
                        (meander.runtime.zeta/fail? e__16581__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__16581__auto__))))))
                   (state__55734))
                  (state__55734))
                 (state__55734)))
               (state__55734
                []
                (if
                 (clojure.core/vector? input__53984_nth_1__)
                 (clojure.core/letfn
                  [(state__55735
                    []
                    (if
                     (clojure.core/=
                      (clojure.core/count input__53984_nth_1__)
                      1)
                     (clojure.core/let
                      [input__53984_nth_1___nth_0__
                       (clojure.core/nth input__53984_nth_1__ 0)]
                      (clojure.core/let
                       [?ast-a input__53984_nth_1___nth_0__]
                       (clojure.core/let
                        [?form input__53984_nth_2__]
                        (try
                         [(clojure.core/let
                           [CATA_RESULT__15641__auto__
                            (CATA__FN__54064
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
                     (state__55736)))
                   (state__55736
                    []
                    (if
                     (clojure.core/=
                      (clojure.core/count input__53984_nth_1__)
                      2)
                     (clojure.core/let
                      [input__53984_nth_1___nth_0__
                       (clojure.core/nth input__53984_nth_1__ 0)
                       input__53984_nth_1___nth_1__
                       (clojure.core/nth input__53984_nth_1__ 1)]
                      (clojure.core/let
                       [?ast-a input__53984_nth_1___nth_0__]
                       (clojure.core/let
                        [?ast-b input__53984_nth_1___nth_1__]
                        (clojure.core/let
                         [?form input__53984_nth_2__]
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
                     (state__55737)))
                   (state__55737
                    []
                    (clojure.core/loop
                     [search_space__55738
                      (meander.runtime.zeta/epsilon-partitions
                       2
                       input__53984_nth_1__)]
                     (if
                      (clojure.core/seq search_space__55738)
                      (clojure.core/let
                       [input__53984_nth_1___parts__
                        (clojure.core/first search_space__55738)
                        result__55739
                        (clojure.core/let
                         [input__53984_nth_1___l__
                          (clojure.core/nth
                           input__53984_nth_1___parts__
                           0)
                          input__53984_nth_1___r__
                          (clojure.core/nth
                           input__53984_nth_1___parts__
                           1)]
                         (clojure.core/letfn
                          [(state__55741
                            []
                            (clojure.core/let
                             [!asts-1 []]
                             (clojure.core/let
                              [ret__14502__auto__
                               (meander.runtime.zeta/epsilon-run-star-1
                                input__53984_nth_1___l__
                                [!asts-1]
                                (clojure.core/fn
                                 [[!asts-1] input__54933]
                                 (clojure.core/let
                                  [input__54933_nth_0__
                                   (clojure.core/nth input__54933 0)]
                                  (clojure.core/let
                                   [!asts-1
                                    (clojure.core/conj
                                     !asts-1
                                     input__54933_nth_0__)]
                                   [!asts-1])))
                                (clojure.core/fn
                                 [[!asts-1]]
                                 (clojure.core/let
                                  [X__54926
                                   (clojure.core/count
                                    input__53984_nth_1___l__)]
                                  (clojure.core/let
                                   [?n X__54926]
                                   (clojure.core/let
                                    [X__54930
                                     (clojure.core/count
                                      input__53984_nth_1___r__)]
                                    (if
                                     (clojure.core/= ?n X__54930)
                                     (clojure.core/let
                                      [!asts-2 []]
                                      (clojure.core/let
                                       [ret__14502__auto__
                                        (meander.runtime.zeta/epsilon-run-star-1
                                         input__53984_nth_1___r__
                                         [!asts-2 !asts-1]
                                         (clojure.core/fn
                                          [[!asts-2 !asts-1]
                                           input__54931]
                                          (clojure.core/let
                                           [input__54931_nth_0__
                                            (clojure.core/nth
                                             input__54931
                                             0)]
                                           (clojure.core/let
                                            [!asts-2
                                             (clojure.core/conj
                                              !asts-2
                                              input__54931_nth_0__)]
                                            [!asts-2 !asts-1])))
                                         (clojure.core/fn
                                          [[!asts-2 !asts-1]]
                                          (clojure.core/let
                                           [?form input__53984_nth_2__]
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
                                                (CATA__FN__54064
                                                 ['meander.dev.parse.zeta/make-and
                                                  [(clojure.core/let
                                                    [CATA_RESULT__15641__auto__
                                                     (CATA__FN__54064
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
                                                     (CATA__FN__54064
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
                                        (state__55742)
                                        ret__14502__auto__)))
                                     (state__55742)))))))]
                              (if
                               (meander.runtime.zeta/fail?
                                ret__14502__auto__)
                               (state__55742)
                               ret__14502__auto__))))
                           (state__55742
                            []
                            (clojure.core/let
                             [!asts-1 []]
                             (clojure.core/let
                              [ret__14502__auto__
                               (meander.runtime.zeta/epsilon-run-star-1
                                input__53984_nth_1___l__
                                [!asts-1]
                                (clojure.core/fn
                                 [[!asts-1] input__54949]
                                 (clojure.core/let
                                  [input__54949_nth_0__
                                   (clojure.core/nth input__54949 0)]
                                  (clojure.core/let
                                   [!asts-1
                                    (clojure.core/conj
                                     !asts-1
                                     input__54949_nth_0__)]
                                   [!asts-1])))
                                (clojure.core/fn
                                 [[!asts-1]]
                                 (clojure.core/let
                                  [X__54940
                                   (clojure.core/count
                                    input__53984_nth_1___l__)]
                                  (clojure.core/let
                                   [?n X__54940]
                                   (clojure.core/let
                                    [input__53984_nth_1___r___l__
                                     (clojure.core/subvec
                                      input__53984_nth_1___r__
                                      0
                                      (clojure.core/min
                                       (clojure.core/count
                                        input__53984_nth_1___r__)
                                       1))]
                                    (if
                                     (clojure.core/=
                                      (clojure.core/count
                                       input__53984_nth_1___r___l__)
                                      1)
                                     (clojure.core/let
                                      [input__53984_nth_1___r___r__
                                       (clojure.core/subvec
                                        input__53984_nth_1___r__
                                        1)]
                                      (clojure.core/let
                                       [input__53984_nth_1___r___l___nth_0__
                                        (clojure.core/nth
                                         input__53984_nth_1___r___l__
                                         0)]
                                       (clojure.core/let
                                        [?ast
                                         input__53984_nth_1___r___l___nth_0__]
                                        (clojure.core/let
                                         [X__54946
                                          (clojure.core/count
                                           input__53984_nth_1___r___r__)]
                                         (if
                                          (clojure.core/= ?n X__54946)
                                          (clojure.core/let
                                           [!asts-2 []]
                                           (clojure.core/let
                                            [ret__14502__auto__
                                             (meander.runtime.zeta/epsilon-run-star-1
                                              input__53984_nth_1___r___r__
                                              [!asts-2 !asts-1]
                                              (clojure.core/fn
                                               [[!asts-2 !asts-1]
                                                input__54947]
                                               (clojure.core/let
                                                [input__54947_nth_0__
                                                 (clojure.core/nth
                                                  input__54947
                                                  0)]
                                                (clojure.core/let
                                                 [!asts-2
                                                  (clojure.core/conj
                                                   !asts-2
                                                   input__54947_nth_0__)]
                                                 [!asts-2 !asts-1])))
                                              (clojure.core/fn
                                               [[!asts-2 !asts-1]]
                                               (clojure.core/let
                                                [?form
                                                 input__53984_nth_2__]
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
                                                     (CATA__FN__54064
                                                      ['meander.dev.parse.zeta/make-and
                                                       [(clojure.core/let
                                                         [CATA_RESULT__15641__auto__
                                                          (CATA__FN__54064
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
                                                          (CATA__FN__54064
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
                          (state__55741)))]
                       (if
                        (meander.runtime.zeta/fail? result__55739)
                        (recur (clojure.core/next search_space__55738))
                        result__55739))
                      (state__55557))))]
                  (state__55735))
                 (state__55557)))]
              (state__55732))
             (state__55557)))
           (state__55557))
          (state__55557)))
        (state__55557
         []
         (clojure.core/letfn
          [(def__54952
            [arg__54975 ?__53993]
            (clojure.core/letfn
             [(state__55747
               []
               (clojure.core/let
                [x__54976 "meander.zeta"]
                (if
                 (clojure.core/= ?__53993 x__54976)
                 [?__53993]
                 (state__55748))))
              (state__55748
               []
               (if
                (clojure.core/map? arg__54975)
                (clojure.core/let
                 [VAL__54977 (.valAt arg__54975 :aliases)]
                 (if
                  (clojure.core/map? VAL__54977)
                  (clojure.core/let
                   [X__54979 (clojure.core/set VAL__54977)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__54979))
                    (clojure.core/loop
                     [search_space__55749 (clojure.core/seq X__54979)]
                     (if
                      (clojure.core/seq search_space__55749)
                      (clojure.core/let
                       [elem__54980
                        (clojure.core/first search_space__55749)
                        result__55750
                        (clojure.core/let
                         [elem__54980_nth_0__
                          (clojure.core/nth elem__54980 0)
                          elem__54980_nth_1__
                          (clojure.core/nth elem__54980 1)]
                         (if
                          (clojure.core/symbol? elem__54980_nth_0__)
                          (clojure.core/let
                           [X__54982
                            (clojure.core/name elem__54980_nth_0__)]
                           (if
                            (clojure.core/= ?__53993 X__54982)
                            (if
                             (clojure.core/symbol? elem__54980_nth_1__)
                             (clojure.core/let
                              [X__54984
                               (clojure.core/name elem__54980_nth_1__)]
                              (clojure.core/case
                               X__54984
                               ("meander.zeta")
                               [?__53993]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__55750)
                        (recur (clojure.core/next search_space__55749))
                        result__55750))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__55747)))]
          (if
           (clojure.core/vector? input__53984)
           (if
            (clojure.core/= (clojure.core/count input__53984) 2)
            (clojure.core/let
             [input__53984_nth_0__
              (clojure.core/nth input__53984 0)
              input__53984_nth_1__
              (clojure.core/nth input__53984 1)]
             (if
              (clojure.core/seq? input__53984_nth_0__)
              (clojure.core/let
               [input__53984_nth_0___l__
                (clojure.core/take 1 input__53984_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__53984_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__53984_nth_0___r__
                  (clojure.core/drop 1 input__53984_nth_0__)]
                 (clojure.core/let
                  [input__53984_nth_0___l___nth_0__
                   (clojure.core/nth input__53984_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__53984_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__54962
                     (clojure.core/namespace
                      input__53984_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__53993 X__54962]
                     (clojure.core/let
                      [X__54964
                       (clojure.core/name
                        input__53984_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__54964
                       ("cata")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__54952 input__53984_nth_1__ ?__53993)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__55558)
                         (clojure.core/let
                          [[?__53993] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__53984)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__53984)
                             2)
                            (clojure.core/let
                             [input__53984_nth_0__
                              (clojure.core/nth input__53984 0)
                              input__53984_nth_1__
                              (clojure.core/nth input__53984 1)]
                             (if
                              (clojure.core/seq? input__53984_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__53984_nth_0__)
                                2)
                               (clojure.core/let
                                [input__53984_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__53984_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__53984_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__53984_nth_0__]
                                  (clojure.core/let
                                   [?env input__53984_nth_1__]
                                   (try
                                    [{:tag :cata,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__15641__auto__
                                        (CATA__FN__54064
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
                               (state__55558))
                              (state__55558)))
                            (state__55558))
                           (state__55558)))))
                       (state__55558)))))
                   (state__55558))))
                (state__55558)))
              (state__55558)))
            (state__55558))
           (state__55558))))
        (state__55558
         []
         (clojure.core/letfn
          [(def__54986
            [arg__55009 ?__53994]
            (clojure.core/letfn
             [(state__55752
               []
               (clojure.core/let
                [x__55010 "meander.zeta"]
                (if
                 (clojure.core/= ?__53994 x__55010)
                 [?__53994]
                 (state__55753))))
              (state__55753
               []
               (if
                (clojure.core/map? arg__55009)
                (clojure.core/let
                 [VAL__55011 (.valAt arg__55009 :aliases)]
                 (if
                  (clojure.core/map? VAL__55011)
                  (clojure.core/let
                   [X__55013 (clojure.core/set VAL__55011)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__55013))
                    (clojure.core/loop
                     [search_space__55754 (clojure.core/seq X__55013)]
                     (if
                      (clojure.core/seq search_space__55754)
                      (clojure.core/let
                       [elem__55014
                        (clojure.core/first search_space__55754)
                        result__55755
                        (clojure.core/let
                         [elem__55014_nth_0__
                          (clojure.core/nth elem__55014 0)
                          elem__55014_nth_1__
                          (clojure.core/nth elem__55014 1)]
                         (if
                          (clojure.core/symbol? elem__55014_nth_0__)
                          (clojure.core/let
                           [X__55016
                            (clojure.core/name elem__55014_nth_0__)]
                           (if
                            (clojure.core/= ?__53994 X__55016)
                            (if
                             (clojure.core/symbol? elem__55014_nth_1__)
                             (clojure.core/let
                              [X__55018
                               (clojure.core/name elem__55014_nth_1__)]
                              (clojure.core/case
                               X__55018
                               ("meander.zeta")
                               [?__53994]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__55755)
                        (recur (clojure.core/next search_space__55754))
                        result__55755))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__55752)))]
          (if
           (clojure.core/vector? input__53984)
           (if
            (clojure.core/= (clojure.core/count input__53984) 2)
            (clojure.core/let
             [input__53984_nth_0__
              (clojure.core/nth input__53984 0)
              input__53984_nth_1__
              (clojure.core/nth input__53984 1)]
             (if
              (clojure.core/seq? input__53984_nth_0__)
              (clojure.core/let
               [input__53984_nth_0___l__
                (clojure.core/take 1 input__53984_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__53984_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__53984_nth_0___r__
                  (clojure.core/drop 1 input__53984_nth_0__)]
                 (clojure.core/let
                  [input__53984_nth_0___l___nth_0__
                   (clojure.core/nth input__53984_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__53984_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__54996
                     (clojure.core/namespace
                      input__53984_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__53994 X__54996]
                     (clojure.core/let
                      [X__54998
                       (clojure.core/name
                        input__53984_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__54998
                       ("fold")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__54986 input__53984_nth_1__ ?__53994)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__55559)
                         (clojure.core/let
                          [[?__53994] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__53984)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__53984)
                             2)
                            (clojure.core/let
                             [input__53984_nth_0__
                              (clojure.core/nth input__53984 0)
                              input__53984_nth_1__
                              (clojure.core/nth input__53984 1)]
                             (if
                              (clojure.core/seq? input__53984_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 4)
                                 input__53984_nth_0__)
                                4)
                               (clojure.core/let
                                [input__53984_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__53984_nth_0__
                                  1)
                                 input__53984_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__53984_nth_0__
                                  2)
                                 input__53984_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__53984_nth_0__
                                  3)]
                                (clojure.core/let
                                 [?mutable-variable
                                  input__53984_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?initial-value
                                   input__53984_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?fold-function
                                    input__53984_nth_0___nth_3__]
                                   (clojure.core/let
                                    [?form input__53984_nth_0__]
                                    (clojure.core/let
                                     [?env input__53984_nth_1__]
                                     (try
                                      [(clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__54064
                                          ['meander.dev.parse.zeta/make-fold
                                           (clojure.core/let
                                            [CATA_RESULT__15641__auto__
                                             (CATA__FN__54064
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
                                             (CATA__FN__54064
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
                               (state__55559))
                              (state__55559)))
                            (state__55559))
                           (state__55559)))))
                       (state__55559)))))
                   (state__55559))))
                (state__55559)))
              (state__55559)))
            (state__55559))
           (state__55559))))
        (state__55559
         []
         (if
          (clojure.core/vector? input__53984)
          (if
           (clojure.core/= (clojure.core/count input__53984) 5)
           (clojure.core/let
            [input__53984_nth_0__
             (clojure.core/nth input__53984 0)
             input__53984_nth_1__
             (clojure.core/nth input__53984 1)
             input__53984_nth_2__
             (clojure.core/nth input__53984 2)
             input__53984_nth_3__
             (clojure.core/nth input__53984 3)
             input__53984_nth_4__
             (clojure.core/nth input__53984 4)]
            (clojure.core/case
             input__53984_nth_0__
             (meander.dev.parse.zeta/make-fold)
             (if
              (clojure.core/map? input__53984_nth_1__)
              (clojure.core/let
               [VAL__55021 (.valAt input__53984_nth_1__ :tag)]
               (clojure.core/case
                VAL__55021
                (:mutable-variable)
                (clojure.core/let
                 [?variable-ast input__53984_nth_1__]
                 (clojure.core/let
                  [?initial-value-ast input__53984_nth_2__]
                  (clojure.core/let
                   [?fold-function input__53984_nth_3__]
                   (clojure.core/let
                    [?form input__53984_nth_4__]
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
                (state__55560)))
              (state__55560))
             (state__55560)))
           (state__55560))
          (state__55560)))
        (state__55560
         []
         (clojure.core/letfn
          [(def__55023
            [arg__55046 ?__53995]
            (clojure.core/letfn
             [(state__55757
               []
               (clojure.core/let
                [x__55047 "meander.zeta"]
                (if
                 (clojure.core/= ?__53995 x__55047)
                 [?__53995]
                 (state__55758))))
              (state__55758
               []
               (if
                (clojure.core/map? arg__55046)
                (clojure.core/let
                 [VAL__55048 (.valAt arg__55046 :aliases)]
                 (if
                  (clojure.core/map? VAL__55048)
                  (clojure.core/let
                   [X__55050 (clojure.core/set VAL__55048)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__55050))
                    (clojure.core/loop
                     [search_space__55759 (clojure.core/seq X__55050)]
                     (if
                      (clojure.core/seq search_space__55759)
                      (clojure.core/let
                       [elem__55051
                        (clojure.core/first search_space__55759)
                        result__55760
                        (clojure.core/let
                         [elem__55051_nth_0__
                          (clojure.core/nth elem__55051 0)
                          elem__55051_nth_1__
                          (clojure.core/nth elem__55051 1)]
                         (if
                          (clojure.core/symbol? elem__55051_nth_0__)
                          (clojure.core/let
                           [X__55053
                            (clojure.core/name elem__55051_nth_0__)]
                           (if
                            (clojure.core/= ?__53995 X__55053)
                            (if
                             (clojure.core/symbol? elem__55051_nth_1__)
                             (clojure.core/let
                              [X__55055
                               (clojure.core/name elem__55051_nth_1__)]
                              (clojure.core/case
                               X__55055
                               ("meander.zeta")
                               [?__53995]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__55760)
                        (recur (clojure.core/next search_space__55759))
                        result__55760))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__55757)))]
          (if
           (clojure.core/vector? input__53984)
           (if
            (clojure.core/= (clojure.core/count input__53984) 2)
            (clojure.core/let
             [input__53984_nth_0__
              (clojure.core/nth input__53984 0)
              input__53984_nth_1__
              (clojure.core/nth input__53984 1)]
             (if
              (clojure.core/seq? input__53984_nth_0__)
              (clojure.core/let
               [input__53984_nth_0___l__
                (clojure.core/take 1 input__53984_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__53984_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__53984_nth_0___r__
                  (clojure.core/drop 1 input__53984_nth_0__)]
                 (clojure.core/let
                  [input__53984_nth_0___l___nth_0__
                   (clojure.core/nth input__53984_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__53984_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__55033
                     (clojure.core/namespace
                      input__53984_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__53995 X__55033]
                     (clojure.core/let
                      [X__55035
                       (clojure.core/name
                        input__53984_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__55035
                       ("let")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__55023 input__53984_nth_1__ ?__53995)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__55561)
                         (clojure.core/let
                          [[?__53995] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__53984)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__53984)
                             2)
                            (clojure.core/let
                             [input__53984_nth_0__
                              (clojure.core/nth input__53984 0)
                              input__53984_nth_1__
                              (clojure.core/nth input__53984 1)]
                             (if
                              (clojure.core/seq? input__53984_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__53984_nth_0__)
                                3)
                               (clojure.core/let
                                [input__53984_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__53984_nth_0__
                                  1)
                                 input__53984_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__53984_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?pattern
                                  input__53984_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?expression
                                   input__53984_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__53984_nth_0__]
                                   (clojure.core/let
                                    [?env input__53984_nth_1__]
                                    (try
                                     [{:tag :let,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__54064
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
                               (state__55561))
                              (state__55561)))
                            (state__55561))
                           (state__55561)))))
                       (state__55561)))))
                   (state__55561))))
                (state__55561)))
              (state__55561)))
            (state__55561))
           (state__55561))))
        (state__55561
         []
         (clojure.core/letfn
          [(def__55057
            [arg__55080 ?__53996]
            (clojure.core/letfn
             [(state__55762
               []
               (clojure.core/let
                [x__55081 "meander.zeta"]
                (if
                 (clojure.core/= ?__53996 x__55081)
                 [?__53996]
                 (state__55763))))
              (state__55763
               []
               (if
                (clojure.core/map? arg__55080)
                (clojure.core/let
                 [VAL__55082 (.valAt arg__55080 :aliases)]
                 (if
                  (clojure.core/map? VAL__55082)
                  (clojure.core/let
                   [X__55084 (clojure.core/set VAL__55082)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__55084))
                    (clojure.core/loop
                     [search_space__55764 (clojure.core/seq X__55084)]
                     (if
                      (clojure.core/seq search_space__55764)
                      (clojure.core/let
                       [elem__55085
                        (clojure.core/first search_space__55764)
                        result__55765
                        (clojure.core/let
                         [elem__55085_nth_0__
                          (clojure.core/nth elem__55085 0)
                          elem__55085_nth_1__
                          (clojure.core/nth elem__55085 1)]
                         (if
                          (clojure.core/symbol? elem__55085_nth_0__)
                          (clojure.core/let
                           [X__55087
                            (clojure.core/name elem__55085_nth_0__)]
                           (if
                            (clojure.core/= ?__53996 X__55087)
                            (if
                             (clojure.core/symbol? elem__55085_nth_1__)
                             (clojure.core/let
                              [X__55089
                               (clojure.core/name elem__55085_nth_1__)]
                              (clojure.core/case
                               X__55089
                               ("meander.zeta")
                               [?__53996]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__55765)
                        (recur (clojure.core/next search_space__55764))
                        result__55765))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__55762)))]
          (if
           (clojure.core/vector? input__53984)
           (if
            (clojure.core/= (clojure.core/count input__53984) 2)
            (clojure.core/let
             [input__53984_nth_0__
              (clojure.core/nth input__53984 0)
              input__53984_nth_1__
              (clojure.core/nth input__53984 1)]
             (if
              (clojure.core/seq? input__53984_nth_0__)
              (clojure.core/let
               [input__53984_nth_0___l__
                (clojure.core/take 1 input__53984_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__53984_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__53984_nth_0___r__
                  (clojure.core/drop 1 input__53984_nth_0__)]
                 (clojure.core/let
                  [input__53984_nth_0___l___nth_0__
                   (clojure.core/nth input__53984_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__53984_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__55067
                     (clojure.core/namespace
                      input__53984_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__53996 X__55067]
                     (clojure.core/let
                      [X__55069
                       (clojure.core/name
                        input__53984_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__55069
                       ("let")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__55057 input__53984_nth_1__ ?__53996)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__55562)
                         (clojure.core/let
                          [[?__53996] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__53984)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__53984)
                             2)
                            (clojure.core/let
                             [input__53984_nth_0__
                              (clojure.core/nth input__53984 0)
                              input__53984_nth_1__
                              (clojure.core/nth input__53984 1)]
                             (if
                              (clojure.core/seq? input__53984_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 4)
                                 input__53984_nth_0__)
                                4)
                               (clojure.core/let
                                [input__53984_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__53984_nth_0__
                                  1)
                                 input__53984_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__53984_nth_0__
                                  2)
                                 input__53984_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__53984_nth_0__
                                  3)]
                                (clojure.core/let
                                 [?pattern
                                  input__53984_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?expression
                                   input__53984_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?next input__53984_nth_0___nth_3__]
                                   (clojure.core/let
                                    [?form input__53984_nth_0__]
                                    (clojure.core/let
                                     [?env input__53984_nth_1__]
                                     (try
                                      [{:tag :let,
                                        :pattern
                                        (clojure.core/let
                                         [CATA_RESULT__15641__auto__
                                          (CATA__FN__54064
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
                                          (CATA__FN__54064
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
                               (state__55562))
                              (state__55562)))
                            (state__55562))
                           (state__55562)))))
                       (state__55562)))))
                   (state__55562))))
                (state__55562)))
              (state__55562)))
            (state__55562))
           (state__55562))))
        (state__55562
         []
         (clojure.core/letfn
          [(def__55091
            [arg__55114 ?__53997]
            (clojure.core/letfn
             [(state__55767
               []
               (clojure.core/let
                [x__55115 "meander.zeta"]
                (if
                 (clojure.core/= ?__53997 x__55115)
                 [?__53997]
                 (state__55768))))
              (state__55768
               []
               (if
                (clojure.core/map? arg__55114)
                (clojure.core/let
                 [VAL__55116 (.valAt arg__55114 :aliases)]
                 (if
                  (clojure.core/map? VAL__55116)
                  (clojure.core/let
                   [X__55118 (clojure.core/set VAL__55116)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__55118))
                    (clojure.core/loop
                     [search_space__55769 (clojure.core/seq X__55118)]
                     (if
                      (clojure.core/seq search_space__55769)
                      (clojure.core/let
                       [elem__55119
                        (clojure.core/first search_space__55769)
                        result__55770
                        (clojure.core/let
                         [elem__55119_nth_0__
                          (clojure.core/nth elem__55119 0)
                          elem__55119_nth_1__
                          (clojure.core/nth elem__55119 1)]
                         (if
                          (clojure.core/symbol? elem__55119_nth_0__)
                          (clojure.core/let
                           [X__55121
                            (clojure.core/name elem__55119_nth_0__)]
                           (if
                            (clojure.core/= ?__53997 X__55121)
                            (if
                             (clojure.core/symbol? elem__55119_nth_1__)
                             (clojure.core/let
                              [X__55123
                               (clojure.core/name elem__55119_nth_1__)]
                              (clojure.core/case
                               X__55123
                               ("meander.zeta")
                               [?__53997]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__55770)
                        (recur (clojure.core/next search_space__55769))
                        result__55770))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__55767)))]
          (if
           (clojure.core/vector? input__53984)
           (if
            (clojure.core/= (clojure.core/count input__53984) 2)
            (clojure.core/let
             [input__53984_nth_0__
              (clojure.core/nth input__53984 0)
              input__53984_nth_1__
              (clojure.core/nth input__53984 1)]
             (if
              (clojure.core/seq? input__53984_nth_0__)
              (clojure.core/let
               [input__53984_nth_0___l__
                (clojure.core/take 1 input__53984_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__53984_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__53984_nth_0___r__
                  (clojure.core/drop 1 input__53984_nth_0__)]
                 (clojure.core/let
                  [input__53984_nth_0___l___nth_0__
                   (clojure.core/nth input__53984_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__53984_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__55101
                     (clojure.core/namespace
                      input__53984_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__53997 X__55101]
                     (clojure.core/let
                      [X__55103
                       (clojure.core/name
                        input__53984_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__55103
                       ("not")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__55091 input__53984_nth_1__ ?__53997)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__55563)
                         (clojure.core/let
                          [[?__53997] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__53984)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__53984)
                             2)
                            (clojure.core/let
                             [input__53984_nth_0__
                              (clojure.core/nth input__53984 0)
                              input__53984_nth_1__
                              (clojure.core/nth input__53984 1)]
                             (if
                              (clojure.core/seq? input__53984_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__53984_nth_0__)
                                2)
                               (clojure.core/let
                                [input__53984_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__53984_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__53984_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__53984_nth_0__]
                                  (clojure.core/let
                                   [?env input__53984_nth_1__]
                                   (try
                                    [{:tag :not,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__15641__auto__
                                        (CATA__FN__54064
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
                               (state__55563))
                              (state__55563)))
                            (state__55563))
                           (state__55563)))))
                       (state__55563)))))
                   (state__55563))))
                (state__55563)))
              (state__55563)))
            (state__55563))
           (state__55563))))
        (state__55563
         []
         (clojure.core/letfn
          [(def__55125
            [arg__55150 ?__53998]
            (clojure.core/letfn
             [(state__55772
               []
               (clojure.core/let
                [x__55151 "meander.zeta"]
                (if
                 (clojure.core/= ?__53998 x__55151)
                 [?__53998]
                 (state__55773))))
              (state__55773
               []
               (if
                (clojure.core/map? arg__55150)
                (clojure.core/let
                 [VAL__55152 (.valAt arg__55150 :aliases)]
                 (if
                  (clojure.core/map? VAL__55152)
                  (clojure.core/let
                   [X__55154 (clojure.core/set VAL__55152)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__55154))
                    (clojure.core/loop
                     [search_space__55774 (clojure.core/seq X__55154)]
                     (if
                      (clojure.core/seq search_space__55774)
                      (clojure.core/let
                       [elem__55155
                        (clojure.core/first search_space__55774)
                        result__55775
                        (clojure.core/let
                         [elem__55155_nth_0__
                          (clojure.core/nth elem__55155 0)
                          elem__55155_nth_1__
                          (clojure.core/nth elem__55155 1)]
                         (if
                          (clojure.core/symbol? elem__55155_nth_0__)
                          (clojure.core/let
                           [X__55157
                            (clojure.core/name elem__55155_nth_0__)]
                           (if
                            (clojure.core/= ?__53998 X__55157)
                            (if
                             (clojure.core/symbol? elem__55155_nth_1__)
                             (clojure.core/let
                              [X__55159
                               (clojure.core/name elem__55155_nth_1__)]
                              (clojure.core/case
                               X__55159
                               ("meander.zeta")
                               [?__53998]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__55775)
                        (recur (clojure.core/next search_space__55774))
                        result__55775))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__55772)))]
          (if
           (clojure.core/vector? input__53984)
           (if
            (clojure.core/= (clojure.core/count input__53984) 2)
            (clojure.core/let
             [input__53984_nth_0__
              (clojure.core/nth input__53984 0)
              input__53984_nth_1__
              (clojure.core/nth input__53984 1)]
             (if
              (clojure.core/seq? input__53984_nth_0__)
              (clojure.core/let
               [input__53984_nth_0___l__
                (clojure.core/take 1 input__53984_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__53984_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__53984_nth_0___r__
                  (clojure.core/drop 1 input__53984_nth_0__)]
                 (clojure.core/let
                  [input__53984_nth_0___l___nth_0__
                   (clojure.core/nth input__53984_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__53984_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__55137
                     (clojure.core/namespace
                      input__53984_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__53998 X__55137]
                     (clojure.core/let
                      [X__55139
                       (clojure.core/name
                        input__53984_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__55139
                       ("or")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__55125 input__53984_nth_1__ ?__53998)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__55564)
                         (clojure.core/let
                          [[?__53998] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__53984)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__53984)
                             2)
                            (clojure.core/let
                             [input__53984_nth_0__
                              (clojure.core/nth input__53984 0)
                              input__53984_nth_1__
                              (clojure.core/nth input__53984 1)]
                             (if
                              (clojure.core/seq? input__53984_nth_0__)
                              (clojure.core/let
                               [input__53984_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__53984_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__53984_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__53984_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__53984_nth_0__)]
                                 (clojure.core/let
                                  [!forms
                                   (clojure.core/vec
                                    input__53984_nth_0___r__)]
                                  (clojure.core/let
                                   [?form input__53984_nth_0__]
                                   (clojure.core/let
                                    [?env input__53984_nth_1__]
                                    (try
                                     [(clojure.core/let
                                       [!forms__counter
                                        (meander.runtime.zeta/iterator
                                         !forms)]
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__54064
                                          ['meander.dev.parse.zeta/make-or
                                           (clojure.core/into
                                            []
                                            (clojure.core/loop
                                             [return__54067
                                              (clojure.core/transient
                                               [])]
                                             (if
                                              (clojure.core/and
                                               (.hasNext
                                                !forms__counter))
                                              (recur
                                               (clojure.core/conj!
                                                return__54067
                                                (clojure.core/let
                                                 [CATA_RESULT__15641__auto__
                                                  (CATA__FN__54064
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
                                               return__54067))))
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
                                (state__55564)))
                              (state__55564)))
                            (state__55564))
                           (state__55564)))))
                       (state__55564)))))
                   (state__55564))))
                (state__55564)))
              (state__55564)))
            (state__55564))
           (state__55564))))
        (state__55564
         []
         (if
          (clojure.core/vector? input__53984)
          (if
           (clojure.core/= (clojure.core/count input__53984) 3)
           (clojure.core/let
            [input__53984_nth_0__
             (clojure.core/nth input__53984 0)
             input__53984_nth_1__
             (clojure.core/nth input__53984 1)
             input__53984_nth_2__
             (clojure.core/nth input__53984 2)]
            (clojure.core/case
             input__53984_nth_0__
             (meander.dev.parse.zeta/make-or)
             (clojure.core/letfn
              [(state__55777
                []
                (if
                 (clojure.core/vector? input__53984_nth_1__)
                 (clojure.core/case
                  input__53984_nth_1__
                  ([])
                  (clojure.core/let
                   [?form input__53984_nth_2__]
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
                  (state__55778))
                 (state__55778)))
               (state__55778
                []
                (clojure.core/case
                 input__53984_nth_2__
                 (nil)
                 (if
                  (clojure.core/vector? input__53984_nth_1__)
                  (if
                   (clojure.core/=
                    (clojure.core/count input__53984_nth_1__)
                    1)
                   (clojure.core/let
                    [input__53984_nth_1___nth_0__
                     (clojure.core/nth input__53984_nth_1__ 0)]
                    (clojure.core/let
                     [?ast-a input__53984_nth_1___nth_0__]
                     (try
                      [?ast-a]
                      (catch
                       java.lang.Exception
                       e__16581__auto__
                       (if
                        (meander.runtime.zeta/fail? e__16581__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__16581__auto__))))))
                   (state__55779))
                  (state__55779))
                 (state__55779)))
               (state__55779
                []
                (if
                 (clojure.core/vector? input__53984_nth_1__)
                 (clojure.core/letfn
                  [(state__55780
                    []
                    (if
                     (clojure.core/=
                      (clojure.core/count input__53984_nth_1__)
                      1)
                     (clojure.core/let
                      [input__53984_nth_1___nth_0__
                       (clojure.core/nth input__53984_nth_1__ 0)]
                      (clojure.core/let
                       [?ast-a input__53984_nth_1___nth_0__]
                       (clojure.core/let
                        [?form input__53984_nth_2__]
                        (try
                         [(clojure.core/let
                           [CATA_RESULT__15641__auto__
                            (CATA__FN__54064
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
                     (state__55781)))
                   (state__55781
                    []
                    (if
                     (clojure.core/=
                      (clojure.core/count input__53984_nth_1__)
                      2)
                     (clojure.core/let
                      [input__53984_nth_1___nth_0__
                       (clojure.core/nth input__53984_nth_1__ 0)
                       input__53984_nth_1___nth_1__
                       (clojure.core/nth input__53984_nth_1__ 1)]
                      (clojure.core/let
                       [?ast-a input__53984_nth_1___nth_0__]
                       (clojure.core/let
                        [?ast-b input__53984_nth_1___nth_1__]
                        (clojure.core/let
                         [?form input__53984_nth_2__]
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
                     (state__55782)))
                   (state__55782
                    []
                    (clojure.core/loop
                     [search_space__55783
                      (meander.runtime.zeta/epsilon-partitions
                       2
                       input__53984_nth_1__)]
                     (if
                      (clojure.core/seq search_space__55783)
                      (clojure.core/let
                       [input__53984_nth_1___parts__
                        (clojure.core/first search_space__55783)
                        result__55784
                        (clojure.core/let
                         [input__53984_nth_1___l__
                          (clojure.core/nth
                           input__53984_nth_1___parts__
                           0)
                          input__53984_nth_1___r__
                          (clojure.core/nth
                           input__53984_nth_1___parts__
                           1)]
                         (clojure.core/letfn
                          [(state__55786
                            []
                            (clojure.core/let
                             [!asts-1 []]
                             (clojure.core/let
                              [ret__14502__auto__
                               (meander.runtime.zeta/epsilon-run-star-1
                                input__53984_nth_1___l__
                                [!asts-1]
                                (clojure.core/fn
                                 [[!asts-1] input__55186]
                                 (clojure.core/let
                                  [input__55186_nth_0__
                                   (clojure.core/nth input__55186 0)]
                                  (clojure.core/let
                                   [!asts-1
                                    (clojure.core/conj
                                     !asts-1
                                     input__55186_nth_0__)]
                                   [!asts-1])))
                                (clojure.core/fn
                                 [[!asts-1]]
                                 (clojure.core/let
                                  [X__55179
                                   (clojure.core/count
                                    input__53984_nth_1___l__)]
                                  (clojure.core/let
                                   [?n X__55179]
                                   (clojure.core/let
                                    [X__55183
                                     (clojure.core/count
                                      input__53984_nth_1___r__)]
                                    (if
                                     (clojure.core/= ?n X__55183)
                                     (clojure.core/let
                                      [!asts-2 []]
                                      (clojure.core/let
                                       [ret__14502__auto__
                                        (meander.runtime.zeta/epsilon-run-star-1
                                         input__53984_nth_1___r__
                                         [!asts-2 !asts-1]
                                         (clojure.core/fn
                                          [[!asts-2 !asts-1]
                                           input__55184]
                                          (clojure.core/let
                                           [input__55184_nth_0__
                                            (clojure.core/nth
                                             input__55184
                                             0)]
                                           (clojure.core/let
                                            [!asts-2
                                             (clojure.core/conj
                                              !asts-2
                                              input__55184_nth_0__)]
                                            [!asts-2 !asts-1])))
                                         (clojure.core/fn
                                          [[!asts-2 !asts-1]]
                                          (clojure.core/let
                                           [?form input__53984_nth_2__]
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
                                                (CATA__FN__54064
                                                 ['meander.dev.parse.zeta/make-or
                                                  [(clojure.core/let
                                                    [CATA_RESULT__15641__auto__
                                                     (CATA__FN__54064
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
                                                     (CATA__FN__54064
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
                                        (state__55787)
                                        ret__14502__auto__)))
                                     (state__55787)))))))]
                              (if
                               (meander.runtime.zeta/fail?
                                ret__14502__auto__)
                               (state__55787)
                               ret__14502__auto__))))
                           (state__55787
                            []
                            (clojure.core/let
                             [!asts-1 []]
                             (clojure.core/let
                              [ret__14502__auto__
                               (meander.runtime.zeta/epsilon-run-star-1
                                input__53984_nth_1___l__
                                [!asts-1]
                                (clojure.core/fn
                                 [[!asts-1] input__55202]
                                 (clojure.core/let
                                  [input__55202_nth_0__
                                   (clojure.core/nth input__55202 0)]
                                  (clojure.core/let
                                   [!asts-1
                                    (clojure.core/conj
                                     !asts-1
                                     input__55202_nth_0__)]
                                   [!asts-1])))
                                (clojure.core/fn
                                 [[!asts-1]]
                                 (clojure.core/let
                                  [X__55193
                                   (clojure.core/count
                                    input__53984_nth_1___l__)]
                                  (clojure.core/let
                                   [?n X__55193]
                                   (clojure.core/let
                                    [input__53984_nth_1___r___l__
                                     (clojure.core/subvec
                                      input__53984_nth_1___r__
                                      0
                                      (clojure.core/min
                                       (clojure.core/count
                                        input__53984_nth_1___r__)
                                       1))]
                                    (if
                                     (clojure.core/=
                                      (clojure.core/count
                                       input__53984_nth_1___r___l__)
                                      1)
                                     (clojure.core/let
                                      [input__53984_nth_1___r___r__
                                       (clojure.core/subvec
                                        input__53984_nth_1___r__
                                        1)]
                                      (clojure.core/let
                                       [input__53984_nth_1___r___l___nth_0__
                                        (clojure.core/nth
                                         input__53984_nth_1___r___l__
                                         0)]
                                       (clojure.core/let
                                        [?ast
                                         input__53984_nth_1___r___l___nth_0__]
                                        (clojure.core/let
                                         [X__55199
                                          (clojure.core/count
                                           input__53984_nth_1___r___r__)]
                                         (if
                                          (clojure.core/= ?n X__55199)
                                          (clojure.core/let
                                           [!asts-2 []]
                                           (clojure.core/let
                                            [ret__14502__auto__
                                             (meander.runtime.zeta/epsilon-run-star-1
                                              input__53984_nth_1___r___r__
                                              [!asts-2 !asts-1]
                                              (clojure.core/fn
                                               [[!asts-2 !asts-1]
                                                input__55200]
                                               (clojure.core/let
                                                [input__55200_nth_0__
                                                 (clojure.core/nth
                                                  input__55200
                                                  0)]
                                                (clojure.core/let
                                                 [!asts-2
                                                  (clojure.core/conj
                                                   !asts-2
                                                   input__55200_nth_0__)]
                                                 [!asts-2 !asts-1])))
                                              (clojure.core/fn
                                               [[!asts-2 !asts-1]]
                                               (clojure.core/let
                                                [?form
                                                 input__53984_nth_2__]
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
                                                     (CATA__FN__54064
                                                      ['meander.dev.parse.zeta/make-or
                                                       [(clojure.core/let
                                                         [CATA_RESULT__15641__auto__
                                                          (CATA__FN__54064
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
                                                          (CATA__FN__54064
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
                          (state__55786)))]
                       (if
                        (meander.runtime.zeta/fail? result__55784)
                        (recur (clojure.core/next search_space__55783))
                        result__55784))
                      (state__55565))))]
                  (state__55780))
                 (state__55565)))]
              (state__55777))
             (state__55565)))
           (state__55565))
          (state__55565)))
        (state__55565
         []
         (clojure.core/letfn
          [(def__55205
            [arg__55228 ?__53999]
            (clojure.core/letfn
             [(state__55792
               []
               (clojure.core/let
                [x__55229 "meander.zeta"]
                (if
                 (clojure.core/= ?__53999 x__55229)
                 [?__53999]
                 (state__55793))))
              (state__55793
               []
               (if
                (clojure.core/map? arg__55228)
                (clojure.core/let
                 [VAL__55230 (.valAt arg__55228 :aliases)]
                 (if
                  (clojure.core/map? VAL__55230)
                  (clojure.core/let
                   [X__55232 (clojure.core/set VAL__55230)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__55232))
                    (clojure.core/loop
                     [search_space__55794 (clojure.core/seq X__55232)]
                     (if
                      (clojure.core/seq search_space__55794)
                      (clojure.core/let
                       [elem__55233
                        (clojure.core/first search_space__55794)
                        result__55795
                        (clojure.core/let
                         [elem__55233_nth_0__
                          (clojure.core/nth elem__55233 0)
                          elem__55233_nth_1__
                          (clojure.core/nth elem__55233 1)]
                         (if
                          (clojure.core/symbol? elem__55233_nth_0__)
                          (clojure.core/let
                           [X__55235
                            (clojure.core/name elem__55233_nth_0__)]
                           (if
                            (clojure.core/= ?__53999 X__55235)
                            (if
                             (clojure.core/symbol? elem__55233_nth_1__)
                             (clojure.core/let
                              [X__55237
                               (clojure.core/name elem__55233_nth_1__)]
                              (clojure.core/case
                               X__55237
                               ("meander.zeta")
                               [?__53999]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__55795)
                        (recur (clojure.core/next search_space__55794))
                        result__55795))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__55792)))]
          (if
           (clojure.core/vector? input__53984)
           (if
            (clojure.core/= (clojure.core/count input__53984) 2)
            (clojure.core/let
             [input__53984_nth_0__
              (clojure.core/nth input__53984 0)
              input__53984_nth_1__
              (clojure.core/nth input__53984 1)]
             (if
              (clojure.core/seq? input__53984_nth_0__)
              (clojure.core/let
               [input__53984_nth_0___l__
                (clojure.core/take 1 input__53984_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__53984_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__53984_nth_0___r__
                  (clojure.core/drop 1 input__53984_nth_0__)]
                 (clojure.core/let
                  [input__53984_nth_0___l___nth_0__
                   (clojure.core/nth input__53984_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__53984_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__55215
                     (clojure.core/namespace
                      input__53984_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__53999 X__55215]
                     (clojure.core/let
                      [X__55217
                       (clojure.core/name
                        input__53984_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__55217
                       ("pred")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__55205 input__53984_nth_1__ ?__53999)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__55566)
                         (clojure.core/let
                          [[?__53999] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__53984)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__53984)
                             2)
                            (clojure.core/let
                             [input__53984_nth_0__
                              (clojure.core/nth input__53984 0)
                              input__53984_nth_1__
                              (clojure.core/nth input__53984 1)]
                             (if
                              (clojure.core/seq? input__53984_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__53984_nth_0__)
                                2)
                               (clojure.core/let
                                [input__53984_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__53984_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?expression
                                  input__53984_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__53984_nth_0__]
                                  (clojure.core/let
                                   [?env input__53984_nth_1__]
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
                               (state__55566))
                              (state__55566)))
                            (state__55566))
                           (state__55566)))))
                       (state__55566)))))
                   (state__55566))))
                (state__55566)))
              (state__55566)))
            (state__55566))
           (state__55566))))
        (state__55566
         []
         (clojure.core/letfn
          [(def__55239
            [arg__55262 ?__54000]
            (clojure.core/letfn
             [(state__55797
               []
               (clojure.core/let
                [x__55263 "meander.zeta"]
                (if
                 (clojure.core/= ?__54000 x__55263)
                 [?__54000]
                 (state__55798))))
              (state__55798
               []
               (if
                (clojure.core/map? arg__55262)
                (clojure.core/let
                 [VAL__55264 (.valAt arg__55262 :aliases)]
                 (if
                  (clojure.core/map? VAL__55264)
                  (clojure.core/let
                   [X__55266 (clojure.core/set VAL__55264)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__55266))
                    (clojure.core/loop
                     [search_space__55799 (clojure.core/seq X__55266)]
                     (if
                      (clojure.core/seq search_space__55799)
                      (clojure.core/let
                       [elem__55267
                        (clojure.core/first search_space__55799)
                        result__55800
                        (clojure.core/let
                         [elem__55267_nth_0__
                          (clojure.core/nth elem__55267 0)
                          elem__55267_nth_1__
                          (clojure.core/nth elem__55267 1)]
                         (if
                          (clojure.core/symbol? elem__55267_nth_0__)
                          (clojure.core/let
                           [X__55269
                            (clojure.core/name elem__55267_nth_0__)]
                           (if
                            (clojure.core/= ?__54000 X__55269)
                            (if
                             (clojure.core/symbol? elem__55267_nth_1__)
                             (clojure.core/let
                              [X__55271
                               (clojure.core/name elem__55267_nth_1__)]
                              (clojure.core/case
                               X__55271
                               ("meander.zeta")
                               [?__54000]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__55800)
                        (recur (clojure.core/next search_space__55799))
                        result__55800))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__55797)))]
          (if
           (clojure.core/vector? input__53984)
           (if
            (clojure.core/= (clojure.core/count input__53984) 2)
            (clojure.core/let
             [input__53984_nth_0__
              (clojure.core/nth input__53984 0)
              input__53984_nth_1__
              (clojure.core/nth input__53984 1)]
             (if
              (clojure.core/seq? input__53984_nth_0__)
              (clojure.core/let
               [input__53984_nth_0___l__
                (clojure.core/take 1 input__53984_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__53984_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__53984_nth_0___r__
                  (clojure.core/drop 1 input__53984_nth_0__)]
                 (clojure.core/let
                  [input__53984_nth_0___l___nth_0__
                   (clojure.core/nth input__53984_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__53984_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__55249
                     (clojure.core/namespace
                      input__53984_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__54000 X__55249]
                     (clojure.core/let
                      [X__55251
                       (clojure.core/name
                        input__53984_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__55251
                       ("pred")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__55239 input__53984_nth_1__ ?__54000)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__55567)
                         (clojure.core/let
                          [[?__54000] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__53984)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__53984)
                             2)
                            (clojure.core/let
                             [input__53984_nth_0__
                              (clojure.core/nth input__53984 0)
                              input__53984_nth_1__
                              (clojure.core/nth input__53984 1)]
                             (if
                              (clojure.core/seq? input__53984_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__53984_nth_0__)
                                3)
                               (clojure.core/let
                                [input__53984_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__53984_nth_0__
                                  1)
                                 input__53984_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__53984_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?expression
                                  input__53984_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?pattern
                                   input__53984_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__53984_nth_0__]
                                   (clojure.core/let
                                    [?env input__53984_nth_1__]
                                    (try
                                     [{:tag :pred,
                                       :expression
                                       {:tag :host-expression,
                                        :form ?expression},
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__54064
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
                               (state__55567))
                              (state__55567)))
                            (state__55567))
                           (state__55567)))))
                       (state__55567)))))
                   (state__55567))))
                (state__55567)))
              (state__55567)))
            (state__55567))
           (state__55567))))
        (state__55567
         []
         (clojure.core/letfn
          [(def__55273
            [arg__55296 ?__54001]
            (clojure.core/letfn
             [(state__55802
               []
               (clojure.core/let
                [x__55297 "meander.zeta"]
                (if
                 (clojure.core/= ?__54001 x__55297)
                 [?__54001]
                 (state__55803))))
              (state__55803
               []
               (if
                (clojure.core/map? arg__55296)
                (clojure.core/let
                 [VAL__55298 (.valAt arg__55296 :aliases)]
                 (if
                  (clojure.core/map? VAL__55298)
                  (clojure.core/let
                   [X__55300 (clojure.core/set VAL__55298)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__55300))
                    (clojure.core/loop
                     [search_space__55804 (clojure.core/seq X__55300)]
                     (if
                      (clojure.core/seq search_space__55804)
                      (clojure.core/let
                       [elem__55301
                        (clojure.core/first search_space__55804)
                        result__55805
                        (clojure.core/let
                         [elem__55301_nth_0__
                          (clojure.core/nth elem__55301 0)
                          elem__55301_nth_1__
                          (clojure.core/nth elem__55301 1)]
                         (if
                          (clojure.core/symbol? elem__55301_nth_0__)
                          (clojure.core/let
                           [X__55303
                            (clojure.core/name elem__55301_nth_0__)]
                           (if
                            (clojure.core/= ?__54001 X__55303)
                            (if
                             (clojure.core/symbol? elem__55301_nth_1__)
                             (clojure.core/let
                              [X__55305
                               (clojure.core/name elem__55301_nth_1__)]
                              (clojure.core/case
                               X__55305
                               ("meander.zeta")
                               [?__54001]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__55805)
                        (recur (clojure.core/next search_space__55804))
                        result__55805))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__55802)))]
          (if
           (clojure.core/vector? input__53984)
           (if
            (clojure.core/= (clojure.core/count input__53984) 2)
            (clojure.core/let
             [input__53984_nth_0__
              (clojure.core/nth input__53984 0)
              input__53984_nth_1__
              (clojure.core/nth input__53984 1)]
             (if
              (clojure.core/seq? input__53984_nth_0__)
              (clojure.core/let
               [input__53984_nth_0___l__
                (clojure.core/take 1 input__53984_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__53984_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__53984_nth_0___r__
                  (clojure.core/drop 1 input__53984_nth_0__)]
                 (clojure.core/let
                  [input__53984_nth_0___l___nth_0__
                   (clojure.core/nth input__53984_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__53984_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__55283
                     (clojure.core/namespace
                      input__53984_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__54001 X__55283]
                     (clojure.core/let
                      [X__55285
                       (clojure.core/name
                        input__53984_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__55285
                       ("re")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__55273 input__53984_nth_1__ ?__54001)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__55568)
                         (clojure.core/let
                          [[?__54001] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__53984)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__53984)
                             2)
                            (clojure.core/let
                             [input__53984_nth_0__
                              (clojure.core/nth input__53984 0)
                              input__53984_nth_1__
                              (clojure.core/nth input__53984 1)]
                             (if
                              (clojure.core/seq? input__53984_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__53984_nth_0__)
                                2)
                               (clojure.core/let
                                [input__53984_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__53984_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?regex input__53984_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__53984_nth_0__]
                                  (clojure.core/let
                                   [?env input__53984_nth_1__]
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
                               (state__55568))
                              (state__55568)))
                            (state__55568))
                           (state__55568)))))
                       (state__55568)))))
                   (state__55568))))
                (state__55568)))
              (state__55568)))
            (state__55568))
           (state__55568))))
        (state__55568
         []
         (clojure.core/letfn
          [(def__55307
            [arg__55330 ?__54002]
            (clojure.core/letfn
             [(state__55807
               []
               (clojure.core/let
                [x__55331 "meander.zeta"]
                (if
                 (clojure.core/= ?__54002 x__55331)
                 [?__54002]
                 (state__55808))))
              (state__55808
               []
               (if
                (clojure.core/map? arg__55330)
                (clojure.core/let
                 [VAL__55332 (.valAt arg__55330 :aliases)]
                 (if
                  (clojure.core/map? VAL__55332)
                  (clojure.core/let
                   [X__55334 (clojure.core/set VAL__55332)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__55334))
                    (clojure.core/loop
                     [search_space__55809 (clojure.core/seq X__55334)]
                     (if
                      (clojure.core/seq search_space__55809)
                      (clojure.core/let
                       [elem__55335
                        (clojure.core/first search_space__55809)
                        result__55810
                        (clojure.core/let
                         [elem__55335_nth_0__
                          (clojure.core/nth elem__55335 0)
                          elem__55335_nth_1__
                          (clojure.core/nth elem__55335 1)]
                         (if
                          (clojure.core/symbol? elem__55335_nth_0__)
                          (clojure.core/let
                           [X__55337
                            (clojure.core/name elem__55335_nth_0__)]
                           (if
                            (clojure.core/= ?__54002 X__55337)
                            (if
                             (clojure.core/symbol? elem__55335_nth_1__)
                             (clojure.core/let
                              [X__55339
                               (clojure.core/name elem__55335_nth_1__)]
                              (clojure.core/case
                               X__55339
                               ("meander.zeta")
                               [?__54002]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__55810)
                        (recur (clojure.core/next search_space__55809))
                        result__55810))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__55807)))]
          (if
           (clojure.core/vector? input__53984)
           (if
            (clojure.core/= (clojure.core/count input__53984) 2)
            (clojure.core/let
             [input__53984_nth_0__
              (clojure.core/nth input__53984 0)
              input__53984_nth_1__
              (clojure.core/nth input__53984 1)]
             (if
              (clojure.core/seq? input__53984_nth_0__)
              (clojure.core/let
               [input__53984_nth_0___l__
                (clojure.core/take 1 input__53984_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__53984_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__53984_nth_0___r__
                  (clojure.core/drop 1 input__53984_nth_0__)]
                 (clojure.core/let
                  [input__53984_nth_0___l___nth_0__
                   (clojure.core/nth input__53984_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__53984_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__55317
                     (clojure.core/namespace
                      input__53984_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__54002 X__55317]
                     (clojure.core/let
                      [X__55319
                       (clojure.core/name
                        input__53984_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__55319
                       ("re")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__55307 input__53984_nth_1__ ?__54002)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__55569)
                         (clojure.core/let
                          [[?__54002] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__53984)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__53984)
                             2)
                            (clojure.core/let
                             [input__53984_nth_0__
                              (clojure.core/nth input__53984 0)
                              input__53984_nth_1__
                              (clojure.core/nth input__53984 1)]
                             (if
                              (clojure.core/seq? input__53984_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__53984_nth_0__)
                                3)
                               (clojure.core/let
                                [input__53984_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__53984_nth_0__
                                  1)
                                 input__53984_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__53984_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?regex input__53984_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?capture
                                   input__53984_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__53984_nth_0__]
                                   (clojure.core/let
                                    [?env input__53984_nth_1__]
                                    (try
                                     [{:tag :regex,
                                       :regex ?regex,
                                       :capture
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__54064
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
                               (state__55569))
                              (state__55569)))
                            (state__55569))
                           (state__55569)))))
                       (state__55569)))))
                   (state__55569))))
                (state__55569)))
              (state__55569)))
            (state__55569))
           (state__55569))))
        (state__55569
         []
         (clojure.core/letfn
          [(def__55341
            [arg__55364 ?__54003]
            (clojure.core/letfn
             [(state__55812
               []
               (clojure.core/let
                [x__55365 "meander.zeta"]
                (if
                 (clojure.core/= ?__54003 x__55365)
                 [?__54003]
                 (state__55813))))
              (state__55813
               []
               (if
                (clojure.core/map? arg__55364)
                (clojure.core/let
                 [VAL__55366 (.valAt arg__55364 :aliases)]
                 (if
                  (clojure.core/map? VAL__55366)
                  (clojure.core/let
                   [X__55368 (clojure.core/set VAL__55366)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__55368))
                    (clojure.core/loop
                     [search_space__55814 (clojure.core/seq X__55368)]
                     (if
                      (clojure.core/seq search_space__55814)
                      (clojure.core/let
                       [elem__55369
                        (clojure.core/first search_space__55814)
                        result__55815
                        (clojure.core/let
                         [elem__55369_nth_0__
                          (clojure.core/nth elem__55369 0)
                          elem__55369_nth_1__
                          (clojure.core/nth elem__55369 1)]
                         (if
                          (clojure.core/symbol? elem__55369_nth_0__)
                          (clojure.core/let
                           [X__55371
                            (clojure.core/name elem__55369_nth_0__)]
                           (if
                            (clojure.core/= ?__54003 X__55371)
                            (if
                             (clojure.core/symbol? elem__55369_nth_1__)
                             (clojure.core/let
                              [X__55373
                               (clojure.core/name elem__55369_nth_1__)]
                              (clojure.core/case
                               X__55373
                               ("meander.zeta")
                               [?__54003]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__55815)
                        (recur (clojure.core/next search_space__55814))
                        result__55815))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__55812)))]
          (if
           (clojure.core/vector? input__53984)
           (if
            (clojure.core/= (clojure.core/count input__53984) 2)
            (clojure.core/let
             [input__53984_nth_0__
              (clojure.core/nth input__53984 0)
              input__53984_nth_1__
              (clojure.core/nth input__53984 1)]
             (if
              (clojure.core/seq? input__53984_nth_0__)
              (clojure.core/let
               [input__53984_nth_0___l__
                (clojure.core/take 1 input__53984_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__53984_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__53984_nth_0___r__
                  (clojure.core/drop 1 input__53984_nth_0__)]
                 (clojure.core/let
                  [input__53984_nth_0___l___nth_0__
                   (clojure.core/nth input__53984_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__53984_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__55351
                     (clojure.core/namespace
                      input__53984_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__54003 X__55351]
                     (clojure.core/let
                      [X__55353
                       (clojure.core/name
                        input__53984_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__55353
                       ("string")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__55341 input__53984_nth_1__ ?__54003)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__55570)
                         (clojure.core/let
                          [[?__54003] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__53984)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__53984)
                             2)
                            (clojure.core/let
                             [input__53984_nth_0__
                              (clojure.core/nth input__53984 0)
                              input__53984_nth_1__
                              (clojure.core/nth input__53984 1)]
                             (if
                              (clojure.core/seq? input__53984_nth_0__)
                              (clojure.core/let
                               [input__53984_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__53984_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__53984_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__53984_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__53984_nth_0__)]
                                 (clojure.core/let
                                  [?sequence input__53984_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__53984_nth_0__]
                                   (clojure.core/let
                                    [?env input__53984_nth_1__]
                                    (try
                                     [{:tag :string,
                                       :next
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__54064
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
                                (state__55570)))
                              (state__55570)))
                            (state__55570))
                           (state__55570)))))
                       (state__55570)))))
                   (state__55570))))
                (state__55570)))
              (state__55570)))
            (state__55570))
           (state__55570))))
        (state__55570
         []
         (clojure.core/letfn
          [(def__55375
            [arg__55398 ?__54004]
            (clojure.core/letfn
             [(state__55817
               []
               (clojure.core/let
                [x__55399 "meander.zeta"]
                (if
                 (clojure.core/= ?__54004 x__55399)
                 [?__54004]
                 (state__55818))))
              (state__55818
               []
               (if
                (clojure.core/map? arg__55398)
                (clojure.core/let
                 [VAL__55400 (.valAt arg__55398 :aliases)]
                 (if
                  (clojure.core/map? VAL__55400)
                  (clojure.core/let
                   [X__55402 (clojure.core/set VAL__55400)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__55402))
                    (clojure.core/loop
                     [search_space__55819 (clojure.core/seq X__55402)]
                     (if
                      (clojure.core/seq search_space__55819)
                      (clojure.core/let
                       [elem__55403
                        (clojure.core/first search_space__55819)
                        result__55820
                        (clojure.core/let
                         [elem__55403_nth_0__
                          (clojure.core/nth elem__55403 0)
                          elem__55403_nth_1__
                          (clojure.core/nth elem__55403 1)]
                         (if
                          (clojure.core/symbol? elem__55403_nth_0__)
                          (clojure.core/let
                           [X__55405
                            (clojure.core/name elem__55403_nth_0__)]
                           (if
                            (clojure.core/= ?__54004 X__55405)
                            (if
                             (clojure.core/symbol? elem__55403_nth_1__)
                             (clojure.core/let
                              [X__55407
                               (clojure.core/name elem__55403_nth_1__)]
                              (clojure.core/case
                               X__55407
                               ("meander.zeta")
                               [?__54004]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__55820)
                        (recur (clojure.core/next search_space__55819))
                        result__55820))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__55817)))]
          (if
           (clojure.core/vector? input__53984)
           (if
            (clojure.core/= (clojure.core/count input__53984) 2)
            (clojure.core/let
             [input__53984_nth_0__
              (clojure.core/nth input__53984 0)
              input__53984_nth_1__
              (clojure.core/nth input__53984 1)]
             (if
              (clojure.core/seq? input__53984_nth_0__)
              (clojure.core/let
               [input__53984_nth_0___l__
                (clojure.core/take 1 input__53984_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__53984_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__53984_nth_0___r__
                  (clojure.core/drop 1 input__53984_nth_0__)]
                 (clojure.core/let
                  [input__53984_nth_0___l___nth_0__
                   (clojure.core/nth input__53984_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__53984_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__55385
                     (clojure.core/namespace
                      input__53984_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__54004 X__55385]
                     (clojure.core/let
                      [X__55387
                       (clojure.core/name
                        input__53984_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__55387
                       ("symbol")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__55375 input__53984_nth_1__ ?__54004)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__55571)
                         (clojure.core/let
                          [[?__54004] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__53984)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__53984)
                             2)
                            (clojure.core/let
                             [input__53984_nth_0__
                              (clojure.core/nth input__53984 0)
                              input__53984_nth_1__
                              (clojure.core/nth input__53984 1)]
                             (if
                              (clojure.core/seq? input__53984_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__53984_nth_0__)
                                2)
                               (clojure.core/let
                                [input__53984_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__53984_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?name input__53984_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__53984_nth_0__]
                                  (clojure.core/let
                                   [?env input__53984_nth_1__]
                                   (try
                                    [{:tag :symbol,
                                      :name
                                      (clojure.core/let
                                       [CATA_RESULT__15641__auto__
                                        (CATA__FN__54064 [?name ?env])]
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
                               (state__55571))
                              (state__55571)))
                            (state__55571))
                           (state__55571)))))
                       (state__55571)))))
                   (state__55571))))
                (state__55571)))
              (state__55571)))
            (state__55571))
           (state__55571))))
        (state__55571
         []
         (clojure.core/letfn
          [(def__55409
            [arg__55432 ?__54005]
            (clojure.core/letfn
             [(state__55822
               []
               (clojure.core/let
                [x__55433 "meander.zeta"]
                (if
                 (clojure.core/= ?__54005 x__55433)
                 [?__54005]
                 (state__55823))))
              (state__55823
               []
               (if
                (clojure.core/map? arg__55432)
                (clojure.core/let
                 [VAL__55434 (.valAt arg__55432 :aliases)]
                 (if
                  (clojure.core/map? VAL__55434)
                  (clojure.core/let
                   [X__55436 (clojure.core/set VAL__55434)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__55436))
                    (clojure.core/loop
                     [search_space__55824 (clojure.core/seq X__55436)]
                     (if
                      (clojure.core/seq search_space__55824)
                      (clojure.core/let
                       [elem__55437
                        (clojure.core/first search_space__55824)
                        result__55825
                        (clojure.core/let
                         [elem__55437_nth_0__
                          (clojure.core/nth elem__55437 0)
                          elem__55437_nth_1__
                          (clojure.core/nth elem__55437 1)]
                         (if
                          (clojure.core/symbol? elem__55437_nth_0__)
                          (clojure.core/let
                           [X__55439
                            (clojure.core/name elem__55437_nth_0__)]
                           (if
                            (clojure.core/= ?__54005 X__55439)
                            (if
                             (clojure.core/symbol? elem__55437_nth_1__)
                             (clojure.core/let
                              [X__55441
                               (clojure.core/name elem__55437_nth_1__)]
                              (clojure.core/case
                               X__55441
                               ("meander.zeta")
                               [?__54005]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__55825)
                        (recur (clojure.core/next search_space__55824))
                        result__55825))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__55822)))]
          (if
           (clojure.core/vector? input__53984)
           (if
            (clojure.core/= (clojure.core/count input__53984) 2)
            (clojure.core/let
             [input__53984_nth_0__
              (clojure.core/nth input__53984 0)
              input__53984_nth_1__
              (clojure.core/nth input__53984 1)]
             (if
              (clojure.core/seq? input__53984_nth_0__)
              (clojure.core/let
               [input__53984_nth_0___l__
                (clojure.core/take 1 input__53984_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__53984_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__53984_nth_0___r__
                  (clojure.core/drop 1 input__53984_nth_0__)]
                 (clojure.core/let
                  [input__53984_nth_0___l___nth_0__
                   (clojure.core/nth input__53984_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__53984_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__55419
                     (clojure.core/namespace
                      input__53984_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__54005 X__55419]
                     (clojure.core/let
                      [X__55421
                       (clojure.core/name
                        input__53984_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__55421
                       ("symbol")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__55409 input__53984_nth_1__ ?__54005)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__55572)
                         (clojure.core/let
                          [[?__54005] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__53984)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__53984)
                             2)
                            (clojure.core/let
                             [input__53984_nth_0__
                              (clojure.core/nth input__53984 0)
                              input__53984_nth_1__
                              (clojure.core/nth input__53984 1)]
                             (if
                              (clojure.core/seq? input__53984_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__53984_nth_0__)
                                3)
                               (clojure.core/let
                                [input__53984_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__53984_nth_0__
                                  1)
                                 input__53984_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__53984_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?namespace
                                  input__53984_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?name input__53984_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__53984_nth_0__]
                                   (clojure.core/let
                                    [?env input__53984_nth_1__]
                                    (try
                                     [{:tag :symbol,
                                       :name
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__54064
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
                                         (CATA__FN__54064
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
                               (state__55572))
                              (state__55572)))
                            (state__55572))
                           (state__55572)))))
                       (state__55572)))))
                   (state__55572))))
                (state__55572)))
              (state__55572)))
            (state__55572))
           (state__55572))))
        (state__55572
         []
         (clojure.core/letfn
          [(def__55443
            [arg__55466 ?__54006]
            (clojure.core/letfn
             [(state__55827
               []
               (clojure.core/let
                [x__55467 "meander.zeta"]
                (if
                 (clojure.core/= ?__54006 x__55467)
                 [?__54006]
                 (state__55828))))
              (state__55828
               []
               (if
                (clojure.core/map? arg__55466)
                (clojure.core/let
                 [VAL__55468 (.valAt arg__55466 :aliases)]
                 (if
                  (clojure.core/map? VAL__55468)
                  (clojure.core/let
                   [X__55470 (clojure.core/set VAL__55468)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__55470))
                    (clojure.core/loop
                     [search_space__55829 (clojure.core/seq X__55470)]
                     (if
                      (clojure.core/seq search_space__55829)
                      (clojure.core/let
                       [elem__55471
                        (clojure.core/first search_space__55829)
                        result__55830
                        (clojure.core/let
                         [elem__55471_nth_0__
                          (clojure.core/nth elem__55471 0)
                          elem__55471_nth_1__
                          (clojure.core/nth elem__55471 1)]
                         (if
                          (clojure.core/symbol? elem__55471_nth_0__)
                          (clojure.core/let
                           [X__55473
                            (clojure.core/name elem__55471_nth_0__)]
                           (if
                            (clojure.core/= ?__54006 X__55473)
                            (if
                             (clojure.core/symbol? elem__55471_nth_1__)
                             (clojure.core/let
                              [X__55475
                               (clojure.core/name elem__55471_nth_1__)]
                              (clojure.core/case
                               X__55475
                               ("meander.zeta")
                               [?__54006]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__55830)
                        (recur (clojure.core/next search_space__55829))
                        result__55830))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__55827)))]
          (if
           (clojure.core/vector? input__53984)
           (if
            (clojure.core/= (clojure.core/count input__53984) 2)
            (clojure.core/let
             [input__53984_nth_0__
              (clojure.core/nth input__53984 0)
              input__53984_nth_1__
              (clojure.core/nth input__53984 1)]
             (if
              (clojure.core/seq? input__53984_nth_0__)
              (clojure.core/let
               [input__53984_nth_0___l__
                (clojure.core/take 1 input__53984_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__53984_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__53984_nth_0___r__
                  (clojure.core/drop 1 input__53984_nth_0__)]
                 (clojure.core/let
                  [input__53984_nth_0___l___nth_0__
                   (clojure.core/nth input__53984_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__53984_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__55453
                     (clojure.core/namespace
                      input__53984_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__54006 X__55453]
                     (clojure.core/let
                      [X__55455
                       (clojure.core/name
                        input__53984_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__55455
                       ("symbol")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__55443 input__53984_nth_1__ ?__54006)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__55573)
                         (clojure.core/let
                          [[?__54006] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__53984)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__53984)
                             2)
                            (clojure.core/let
                             [input__53984_nth_0__
                              (clojure.core/nth input__53984 0)
                              input__53984_nth_1__
                              (clojure.core/nth input__53984 1)]
                             (if
                              (clojure.core/seq? input__53984_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 5)
                                 input__53984_nth_0__)
                                5)
                               (clojure.core/let
                                [input__53984_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__53984_nth_0__
                                  1)
                                 input__53984_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__53984_nth_0__
                                  2)
                                 input__53984_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__53984_nth_0__
                                  3)
                                 input__53984_nth_0___nth_4__
                                 (clojure.core/nth
                                  input__53984_nth_0__
                                  4)]
                                (clojure.core/case
                                 input__53984_nth_0___nth_3__
                                 (:meander.zeta/as)
                                 (clojure.core/let
                                  [?namespace
                                   input__53984_nth_0___nth_1__]
                                  (clojure.core/let
                                   [?name input__53984_nth_0___nth_2__]
                                   (clojure.core/let
                                    [?pattern
                                     input__53984_nth_0___nth_4__]
                                    (clojure.core/let
                                     [?form input__53984_nth_0__]
                                     (clojure.core/let
                                      [?env input__53984_nth_1__]
                                      (try
                                       [{:tag :symbol,
                                         :name
                                         (clojure.core/let
                                          [CATA_RESULT__15641__auto__
                                           (CATA__FN__54064
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
                                           (CATA__FN__54064
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
                                           (CATA__FN__54064
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
                                 (state__55573)))
                               (state__55573))
                              (state__55573)))
                            (state__55573))
                           (state__55573)))))
                       (state__55573)))))
                   (state__55573))))
                (state__55573)))
              (state__55573)))
            (state__55573))
           (state__55573))))
        (state__55573
         []
         (if
          (clojure.core/vector? input__53984)
          (if
           (clojure.core/= (clojure.core/count input__53984) 2)
           (clojure.core/let
            [input__53984_nth_0__ (clojure.core/nth input__53984 0)]
            (clojure.core/letfn
             [(state__55832
               []
               (clojure.core/let
                [input__53984_nth_1__
                 (clojure.core/nth input__53984 1)]
                (clojure.core/letfn
                 [(state__55837
                   []
                   (if
                    (clojure.core/seq? input__53984_nth_0__)
                    (clojure.core/let
                     [?sequence input__53984_nth_0__]
                     (clojure.core/let
                      [?env input__53984_nth_1__]
                      (try
                       [{:tag :seq,
                         :next
                         (clojure.core/let
                          [CATA_RESULT__15641__auto__
                           (CATA__FN__54064
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
                    (state__55838)))
                  (state__55838
                   []
                   (if
                    (clojure.core/map? input__53984_nth_0__)
                    (clojure.core/let
                     [?map input__53984_nth_0__]
                     (clojure.core/let
                      [?env input__53984_nth_1__]
                      (try
                       [{:tag :map,
                         :next
                         (clojure.core/let
                          [CATA_RESULT__15641__auto__
                           (CATA__FN__54064
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
                    (state__55833)))]
                 (state__55837))))
              (state__55833
               []
               (if
                (clojure.core/symbol? input__53984_nth_0__)
                (clojure.core/let
                 [X__55485
                  (clojure.core/namespace input__53984_nth_0__)]
                 (clojure.core/let
                  [X__55487 (clojure.core/name input__53984_nth_0__)]
                  (if
                   (clojure.core/string? X__55487)
                   (clojure.core/letfn
                    [(state__55839
                      []
                      (clojure.core/let
                       [ret__55488
                        (clojure.core/re-matches #"_.*" X__55487)]
                       (if
                        (clojure.core/some? ret__55488)
                        (clojure.core/let
                         [?name ret__55488]
                         (clojure.core/let
                          [?symbol input__53984_nth_0__]
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
                        (state__55840))))
                     (state__55840
                      []
                      (clojure.core/let
                       [ret__55495
                        (clojure.core/re-matches #".+#" X__55487)]
                       (if
                        (clojure.core/some? ret__55495)
                        (clojure.core/let
                         [?name ret__55495]
                         (clojure.core/let
                          [?symbol input__53984_nth_0__]
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
                        (state__55841))))
                     (state__55841
                      []
                      (clojure.core/let
                       [ret__55502
                        (clojure.core/re-matches #"%.+" X__55487)]
                       (if
                        (clojure.core/some? ret__55502)
                        (clojure.core/let
                         [?name ret__55502]
                         (clojure.core/let
                          [?symbol input__53984_nth_0__]
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
                        (state__55842))))
                     (state__55842
                      []
                      (clojure.core/let
                       [ret__55509
                        (clojure.core/re-matches #"\*.+" X__55487)]
                       (if
                        (clojure.core/some? ret__55509)
                        (clojure.core/let
                         [?name ret__55509]
                         (clojure.core/let
                          [?symbol input__53984_nth_0__]
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
                        (state__55843))))
                     (state__55843
                      []
                      (clojure.core/let
                       [ret__55516
                        (clojure.core/re-matches #"\!.+" X__55487)]
                       (if
                        (clojure.core/some? ret__55516)
                        (clojure.core/let
                         [?name ret__55516]
                         (clojure.core/let
                          [?symbol input__53984_nth_0__]
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
                        (state__55844))))
                     (state__55844
                      []
                      (clojure.core/let
                       [ret__55523
                        (clojure.core/re-matches #"\?.+" X__55487)]
                       (if
                        (clojure.core/some? ret__55523)
                        (clojure.core/let
                         [?name ret__55523]
                         (clojure.core/let
                          [?symbol input__53984_nth_0__]
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
                        (state__55834))))]
                    (state__55839))
                   (state__55834))))
                (state__55834)))
              (state__55834
               []
               (if
                (string? input__53984_nth_0__)
                (clojure.core/let
                 [?x input__53984_nth_0__]
                 (try
                  [{:tag :literal, :type :string, :form ?x}]
                  (catch
                   java.lang.Exception
                   e__16581__auto__
                   (if
                    (meander.runtime.zeta/fail? e__16581__auto__)
                    (meander.runtime.zeta/fail)
                    (throw e__16581__auto__)))))
                (state__55835)))
              (state__55835
               []
               (if
                (char? input__53984_nth_0__)
                (clojure.core/let
                 [?x input__53984_nth_0__]
                 (try
                  [{:tag :literal, :type :char, :form ?x}]
                  (catch
                   java.lang.Exception
                   e__16581__auto__
                   (if
                    (meander.runtime.zeta/fail? e__16581__auto__)
                    (meander.runtime.zeta/fail)
                    (throw e__16581__auto__)))))
                (state__55836)))
              (state__55836
               []
               (clojure.core/let
                [?x input__53984_nth_0__]
                (try
                 [{:tag :literal, :form ?x}]
                 (catch
                  java.lang.Exception
                  e__16581__auto__
                  (if
                   (meander.runtime.zeta/fail? e__16581__auto__)
                   (meander.runtime.zeta/fail)
                   (throw e__16581__auto__))))))]
             (state__55832)))
           (state__55574))
          (state__55574)))
        (state__55574
         []
         (clojure.core/let
          [?x input__53984]
          (try
           [{:tag :mistake, :x ?x}]
           (catch
            java.lang.Exception
            e__16581__auto__
            (if
             (meander.runtime.zeta/fail? e__16581__auto__)
             (meander.runtime.zeta/fail)
             (throw e__16581__auto__))))))]
       (state__55536)))]
    (clojure.core/let
     [x__14338__auto__ (CATA__FN__54064 input__53984)]
     (if
      (meander.runtime.zeta/fail? x__14338__auto__)
      (meander.runtime.zeta/fail)
      (clojure.core/let
       [[CATA_RETURN__54068] x__14338__auto__]
       CATA_RETURN__54068))))]
  (if
   (meander.runtime.zeta/fail? ret__14518__auto__)
   nil
   ret__14518__auto__)))
