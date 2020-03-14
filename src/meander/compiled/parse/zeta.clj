(ns meander.compiled.parse.zeta (:require [meander.runtime.zeta]))
(clojure.core/defn
 parse
 [input__72393]
 (let*
  [ret__8106__auto__
   (clojure.core/letfn
    [(CATA__FN__72443
      [input__72393]
      (clojure.core/letfn
       [(state__73439
         []
         (if
          (clojure.core/vector? input__72393)
          (if
           (clojure.core/= (clojure.core/count input__72393) 3)
           (clojure.core/let
            [input__72393_nth_0__
             (clojure.core/nth input__72393 0)
             input__72393_nth_1__
             (clojure.core/nth input__72393 1)
             input__72393_nth_2__
             (clojure.core/nth input__72393 2)]
            (clojure.core/case
             input__72393_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__72393_nth_1__)
              (clojure.core/letfn
               [(state__73462
                 []
                 (clojure.core/case
                  input__72393_nth_1__
                  ([])
                  (clojure.core/let
                   [?env input__72393_nth_2__]
                   (try
                    [{:tag :empty}]
                    (catch
                     java.lang.Exception
                     e__10169__auto__
                     (if
                      (meander.runtime.zeta/fail? e__10169__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__10169__auto__)))))
                  (state__73463)))
                (state__73463
                 []
                 (clojure.core/let
                  [n__72450
                   (clojure.core/count input__72393_nth_1__)
                   m__72451
                   (clojure.core/max 0 (clojure.core/- n__72450 2))
                   input__72393_nth_1___l__
                   (clojure.core/subvec
                    input__72393_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__72393_nth_1__)
                     m__72451))
                   input__72393_nth_1___r__
                   (clojure.core/subvec input__72393_nth_1__ m__72451)]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__72393_nth_1___r__)
                    2)
                   (clojure.core/let
                    [!xs (clojure.core/vec input__72393_nth_1___l__)]
                    (if
                     (clojure.core/=
                      (clojure.core/count input__72393_nth_1___r__)
                      2)
                     (clojure.core/let
                      [input__72393_nth_1___r___nth_0__
                       (clojure.core/nth input__72393_nth_1___r__ 0)
                       input__72393_nth_1___r___nth_1__
                       (clojure.core/nth input__72393_nth_1___r__ 1)]
                      (clojure.core/case
                       input__72393_nth_1___r___nth_0__
                       (:meander.zeta/as)
                       (clojure.core/let
                        [?pattern input__72393_nth_1___r___nth_1__]
                        (clojure.core/let
                         [?env input__72393_nth_2__]
                         (try
                          [(clojure.core/let
                            [!xs__counter
                             (meander.runtime.zeta/iterator !xs)]
                            {:tag :as,
                             :pattern
                             (clojure.core/let
                              [CATA_RESULT__9229__auto__
                               (CATA__FN__72443 [?pattern ?env])]
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
                               (CATA__FN__72443
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
                       (state__73440)))
                     (state__73440)))
                   (state__73440))))]
               (state__73462))
              (state__73440))
             (state__73440)))
           (state__73440))
          (state__73440)))
        (state__73440
         []
         (clojure.core/letfn
          [(def__72456
            [arg__72491 ?ns]
            (clojure.core/letfn
             [(state__73464
               []
               (clojure.core/let
                [x__72492 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__72492)
                 (clojure.core/let [?env arg__72491] [?env ?ns])
                 (state__73465))))
              (state__73465
               []
               (if
                (clojure.core/map? arg__72491)
                (clojure.core/let
                 [VAL__72493 (.valAt arg__72491 :aliases)]
                 (if
                  (clojure.core/map? VAL__72493)
                  (clojure.core/let
                   [X__72495 (clojure.core/set VAL__72493)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__72495))
                    (clojure.core/loop
                     [search_space__73466 (clojure.core/seq X__72495)]
                     (if
                      (clojure.core/seq search_space__73466)
                      (clojure.core/let
                       [elem__72496
                        (clojure.core/first search_space__73466)
                        result__73467
                        (clojure.core/let
                         [elem__72496_nth_0__
                          (clojure.core/nth elem__72496 0)
                          elem__72496_nth_1__
                          (clojure.core/nth elem__72496 1)]
                         (if
                          (clojure.core/symbol? elem__72496_nth_0__)
                          (clojure.core/let
                           [X__72498
                            (clojure.core/name elem__72496_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__72498)
                            (if
                             (clojure.core/symbol? elem__72496_nth_1__)
                             (clojure.core/let
                              [X__72500
                               (clojure.core/name elem__72496_nth_1__)]
                              (clojure.core/case
                               X__72500
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__72491]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__73467)
                        (recur (clojure.core/next search_space__73466))
                        result__73467))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__73464)))]
          (if
           (clojure.core/vector? input__72393)
           (if
            (clojure.core/= (clojure.core/count input__72393) 3)
            (clojure.core/let
             [input__72393_nth_0__
              (clojure.core/nth input__72393 0)
              input__72393_nth_1__
              (clojure.core/nth input__72393 1)
              input__72393_nth_2__
              (clojure.core/nth input__72393 2)]
             (clojure.core/case
              input__72393_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__72393_nth_1__)
               (clojure.core/loop
                [search_space__73469
                 (meander.match.runtime.epsilon/partitions
                  2
                  input__72393_nth_1__)]
                (if
                 (clojure.core/seq search_space__73469)
                 (clojure.core/let
                  [input__72393_nth_1___parts__
                   (clojure.core/first search_space__73469)
                   result__73470
                   (clojure.core/let
                    [input__72393_nth_1___l__
                     (clojure.core/nth input__72393_nth_1___parts__ 0)
                     input__72393_nth_1___r__
                     (clojure.core/nth input__72393_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__72393_nth_1___l__)]
                     (clojure.core/let
                      [input__72393_nth_1___r___l__
                       (clojure.core/subvec
                        input__72393_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__72393_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__72393_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__72393_nth_1___r___r__
                         (clojure.core/subvec
                          input__72393_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__72393_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__72393_nth_1___r___l__
                           0)
                          input__72393_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__72393_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__72393_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__72465
                            (clojure.core/namespace
                             input__72393_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__72465]
                            (clojure.core/let
                             [X__72467
                              (clojure.core/name
                               input__72393_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__72467)
                              (clojure.core/let
                               [ret__72468
                                (clojure.core/re-matches
                                 #"&(\d+)"
                                 X__72467)]
                               (if
                                (clojure.core/some? ret__72468)
                                (if
                                 (clojure.core/vector? ret__72468)
                                 (if
                                  (clojure.core/=
                                   (clojure.core/count ret__72468)
                                   2)
                                  (clojure.core/let
                                   [ret__72468_nth_1__
                                    (clojure.core/nth ret__72468 1)]
                                   (clojure.core/let
                                    [?n ret__72468_nth_1__]
                                    (clojure.core/let
                                     [?pattern
                                      input__72393_nth_1___r___l___nth_1__]
                                     (clojure.core/let
                                      [?rest
                                       input__72393_nth_1___r___r__]
                                      (clojure.core/let
                                       [x__7926__auto__
                                        (def__72456
                                         input__72393_nth_2__
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
                                              (CATA__FN__72443
                                               ['meander.dev.parse.zeta/make-join
                                                (clojure.core/let
                                                 [CATA_RESULT__9229__auto__
                                                  (CATA__FN__72443
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
                                                  (CATA__FN__72443
                                                   ['meander.dev.parse.zeta/make-join
                                                    {:tag :slice,
                                                     :size
                                                     (Integer. ?n),
                                                     :pattern
                                                     (clojure.core/let
                                                      [CATA_RESULT__9229__auto__
                                                       (CATA__FN__72443
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
                                                      (CATA__FN__72443
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
                   (meander.runtime.zeta/fail? result__73470)
                   (recur (clojure.core/next search_space__73469))
                   result__73470))
                 (state__73441)))
               (state__73441))
              (state__73441)))
            (state__73441))
           (state__73441))))
        (state__73441
         []
         (clojure.core/letfn
          [(def__72513
            [arg__72545 ?ns]
            (clojure.core/letfn
             [(state__73472
               []
               (clojure.core/let
                [x__72546 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__72546)
                 (clojure.core/let [?env arg__72545] [?env ?ns])
                 (state__73473))))
              (state__73473
               []
               (if
                (clojure.core/map? arg__72545)
                (clojure.core/let
                 [VAL__72547 (.valAt arg__72545 :aliases)]
                 (if
                  (clojure.core/map? VAL__72547)
                  (clojure.core/let
                   [X__72549 (clojure.core/set VAL__72547)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__72549))
                    (clojure.core/loop
                     [search_space__73474 (clojure.core/seq X__72549)]
                     (if
                      (clojure.core/seq search_space__73474)
                      (clojure.core/let
                       [elem__72550
                        (clojure.core/first search_space__73474)
                        result__73475
                        (clojure.core/let
                         [elem__72550_nth_0__
                          (clojure.core/nth elem__72550 0)
                          elem__72550_nth_1__
                          (clojure.core/nth elem__72550 1)]
                         (if
                          (clojure.core/symbol? elem__72550_nth_0__)
                          (clojure.core/let
                           [X__72552
                            (clojure.core/name elem__72550_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__72552)
                            (if
                             (clojure.core/symbol? elem__72550_nth_1__)
                             (clojure.core/let
                              [X__72554
                               (clojure.core/name elem__72550_nth_1__)]
                              (clojure.core/case
                               X__72554
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__72545]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__73475)
                        (recur (clojure.core/next search_space__73474))
                        result__73475))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__73472)))]
          (if
           (clojure.core/vector? input__72393)
           (if
            (clojure.core/= (clojure.core/count input__72393) 3)
            (clojure.core/let
             [input__72393_nth_0__
              (clojure.core/nth input__72393 0)
              input__72393_nth_1__
              (clojure.core/nth input__72393 1)
              input__72393_nth_2__
              (clojure.core/nth input__72393 2)]
             (clojure.core/case
              input__72393_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__72393_nth_1__)
               (clojure.core/loop
                [search_space__73477
                 (meander.match.runtime.epsilon/partitions
                  2
                  input__72393_nth_1__)]
                (if
                 (clojure.core/seq search_space__73477)
                 (clojure.core/let
                  [input__72393_nth_1___parts__
                   (clojure.core/first search_space__73477)
                   result__73478
                   (clojure.core/let
                    [input__72393_nth_1___l__
                     (clojure.core/nth input__72393_nth_1___parts__ 0)
                     input__72393_nth_1___r__
                     (clojure.core/nth input__72393_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__72393_nth_1___l__)]
                     (clojure.core/let
                      [input__72393_nth_1___r___l__
                       (clojure.core/subvec
                        input__72393_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__72393_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__72393_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__72393_nth_1___r___r__
                         (clojure.core/subvec
                          input__72393_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__72393_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__72393_nth_1___r___l__
                           0)
                          input__72393_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__72393_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__72393_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__72522
                            (clojure.core/namespace
                             input__72393_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__72522]
                            (clojure.core/let
                             [X__72524
                              (clojure.core/name
                               input__72393_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__72524)
                              (if
                               (clojure.core/re-matches
                                #"&.*"
                                X__72524)
                               (clojure.core/let
                                [?pattern
                                 input__72393_nth_1___r___l___nth_1__]
                                (clojure.core/let
                                 [?rest input__72393_nth_1___r___r__]
                                 (clojure.core/let
                                  [x__7926__auto__
                                   (def__72513
                                    input__72393_nth_2__
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
                                         (CATA__FN__72443
                                          ['meander.dev.parse.zeta/make-join
                                           (clojure.core/let
                                            [CATA_RESULT__9229__auto__
                                             (CATA__FN__72443
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
                                             (CATA__FN__72443
                                              ['meander.dev.parse.zeta/make-join
                                               (clojure.core/let
                                                [CATA_RESULT__9229__auto__
                                                 (CATA__FN__72443
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
                                                 (CATA__FN__72443
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
                   (meander.runtime.zeta/fail? result__73478)
                   (recur (clojure.core/next search_space__73477))
                   result__73478))
                 (state__73442)))
               (state__73442))
              (state__73442)))
            (state__73442))
           (state__73442))))
        (state__73442
         []
         (if
          (clojure.core/vector? input__72393)
          (clojure.core/letfn
           [(state__73480
             []
             (if
              (clojure.core/= (clojure.core/count input__72393) 3)
              (clojure.core/let
               [input__72393_nth_0__
                (clojure.core/nth input__72393 0)
                input__72393_nth_1__
                (clojure.core/nth input__72393 1)
                input__72393_nth_2__
                (clojure.core/nth input__72393 2)]
               (clojure.core/case
                input__72393_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__72393_nth_1__)
                 (clojure.core/letfn
                  [(state__73485
                    []
                    (clojure.core/let
                     [n__72575
                      (clojure.core/count input__72393_nth_1__)
                      m__72576
                      (clojure.core/max 0 (clojure.core/- n__72575 2))
                      input__72393_nth_1___l__
                      (clojure.core/subvec
                       input__72393_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__72393_nth_1__)
                        m__72576))
                      input__72393_nth_1___r__
                      (clojure.core/subvec
                       input__72393_nth_1__
                       m__72576)]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__72393_nth_1___r__)
                       2)
                      (clojure.core/let
                       [!xs
                        (clojure.core/vec input__72393_nth_1___l__)]
                       (if
                        (clojure.core/=
                         (clojure.core/count input__72393_nth_1___r__)
                         2)
                        (clojure.core/let
                         [input__72393_nth_1___r___nth_0__
                          (clojure.core/nth input__72393_nth_1___r__ 0)
                          input__72393_nth_1___r___nth_1__
                          (clojure.core/nth
                           input__72393_nth_1___r__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__72393_nth_1___r___nth_0__)
                          (clojure.core/let
                           [X__72580
                            (clojure.core/namespace
                             input__72393_nth_1___r___nth_0__)]
                           (clojure.core/let
                            [?ns X__72580]
                            (clojure.core/let
                             [X__72582
                              (clojure.core/name
                               input__72393_nth_1___r___nth_0__)]
                             (if
                              (clojure.core/string? X__72582)
                              (clojure.core/let
                               [ret__72583
                                (clojure.core/re-matches
                                 #"&.*"
                                 X__72582)]
                               (if
                                (clojure.core/some? ret__72583)
                                (clojure.core/let
                                 [?name ret__72583]
                                 (clojure.core/let
                                  [?pattern
                                   input__72393_nth_1___r___nth_1__]
                                  (if
                                   (clojure.core/map?
                                    input__72393_nth_2__)
                                   (clojure.core/let
                                    [VAL__72567
                                     (.valAt
                                      input__72393_nth_2__
                                      :aliases)]
                                    (if
                                     (clojure.core/map? VAL__72567)
                                     (clojure.core/let
                                      [X__72569
                                       (clojure.core/set VAL__72567)]
                                      (if
                                       (clojure.core/<=
                                        1
                                        (clojure.core/count X__72569))
                                       (clojure.core/loop
                                        [search_space__73489
                                         (clojure.core/seq X__72569)]
                                        (if
                                         (clojure.core/seq
                                          search_space__73489)
                                         (clojure.core/let
                                          [elem__72570
                                           (clojure.core/first
                                            search_space__73489)
                                           result__73490
                                           (clojure.core/let
                                            [elem__72570_nth_0__
                                             (clojure.core/nth
                                              elem__72570
                                              0)
                                             elem__72570_nth_1__
                                             (clojure.core/nth
                                              elem__72570
                                              1)]
                                            (if
                                             (clojure.core/symbol?
                                              elem__72570_nth_0__)
                                             (clojure.core/let
                                              [X__72572
                                               (clojure.core/name
                                                elem__72570_nth_0__)]
                                              (if
                                               (clojure.core/=
                                                ?ns
                                                X__72572)
                                               (if
                                                (clojure.core/symbol?
                                                 elem__72570_nth_1__)
                                                (clojure.core/let
                                                 [X__72574
                                                  (clojure.core/name
                                                   elem__72570_nth_1__)]
                                                 (clojure.core/case
                                                  X__72574
                                                  ("meander.zeta")
                                                  (clojure.core/let
                                                   [?env
                                                    input__72393_nth_2__]
                                                   (try
                                                    [(clojure.core/let
                                                      [!xs__counter
                                                       (meander.runtime.zeta/iterator
                                                        !xs)]
                                                      (clojure.core/let
                                                       [CATA_RESULT__9229__auto__
                                                        (CATA__FN__72443
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
                                            result__73490)
                                           (recur
                                            (clojure.core/next
                                             search_space__73489))
                                           result__73490))
                                         (state__73486)))
                                       (state__73486)))
                                     (state__73486)))
                                   (state__73486))))
                                (state__73486)))
                              (state__73486)))))
                          (state__73486)))
                        (state__73486)))
                      (state__73486))))
                   (state__73486
                    []
                    (clojure.core/loop
                     [search_space__73492
                      (meander.match.runtime.epsilon/partitions
                       2
                       input__72393_nth_1__)]
                     (if
                      (clojure.core/seq search_space__73492)
                      (clojure.core/let
                       [input__72393_nth_1___parts__
                        (clojure.core/first search_space__73492)
                        result__73493
                        (clojure.core/let
                         [input__72393_nth_1___l__
                          (clojure.core/nth
                           input__72393_nth_1___parts__
                           0)
                          input__72393_nth_1___r__
                          (clojure.core/nth
                           input__72393_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs
                           (clojure.core/vec input__72393_nth_1___l__)]
                          (clojure.core/let
                           [input__72393_nth_1___r___l__
                            (clojure.core/subvec
                             input__72393_nth_1___r__
                             0
                             (clojure.core/min
                              (clojure.core/count
                               input__72393_nth_1___r__)
                              1))]
                           (if
                            (clojure.core/=
                             (clojure.core/count
                              input__72393_nth_1___r___l__)
                             1)
                            (clojure.core/let
                             [input__72393_nth_1___r___r__
                              (clojure.core/subvec
                               input__72393_nth_1___r__
                               1)]
                             (if
                              (clojure.core/=
                               input__72393_nth_1___r___l__
                               ['.])
                              (clojure.core/let
                               [?rest input__72393_nth_1___r___r__]
                               (clojure.core/let
                                [?env input__72393_nth_2__]
                                (try
                                 [(clojure.core/let
                                   [!xs__counter
                                    (meander.runtime.zeta/iterator
                                     !xs)]
                                   (clojure.core/let
                                    [CATA_RESULT__9229__auto__
                                     (CATA__FN__72443
                                      ['meander.dev.parse.zeta/make-join
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__72443
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
                                         (CATA__FN__72443
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
                        (meander.runtime.zeta/fail? result__73493)
                        (recur (clojure.core/next search_space__73492))
                        result__73493))
                      (state__73487))))
                   (state__73487
                    []
                    (clojure.core/let
                     [input__72393_nth_1___l__
                      (clojure.core/subvec
                       input__72393_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__72393_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__72393_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__72393_nth_1___r__
                        (clojure.core/subvec input__72393_nth_1__ 1)]
                       (if
                        (clojure.core/=
                         input__72393_nth_1___l__
                         ['...])
                        (clojure.core/let
                         [?rest input__72393_nth_1___r__]
                         (clojure.core/let
                          [?env input__72393_nth_2__]
                          (try
                           [(clojure.core/let
                             [CATA_RESULT__9229__auto__
                              (CATA__FN__72443
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
                        (state__73488)))
                      (state__73488))))
                   (state__73488
                    []
                    (clojure.core/loop
                     [search_space__73495
                      (meander.match.runtime.epsilon/partitions
                       2
                       input__72393_nth_1__)]
                     (if
                      (clojure.core/seq search_space__73495)
                      (clojure.core/let
                       [input__72393_nth_1___parts__
                        (clojure.core/first search_space__73495)
                        result__73496
                        (clojure.core/let
                         [input__72393_nth_1___l__
                          (clojure.core/nth
                           input__72393_nth_1___parts__
                           0)
                          input__72393_nth_1___r__
                          (clojure.core/nth
                           input__72393_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs []]
                          (clojure.core/let
                           [ret__8090__auto__
                            (meander.runtime.zeta/epsilon-run-star-1
                             input__72393_nth_1___l__
                             [!xs]
                             (clojure.core/fn
                              [[!xs] input__72600]
                              (clojure.core/let
                               [input__72600_nth_0__
                                (clojure.core/nth input__72600 0)]
                               (clojure.core/letfn
                                [(save__72601
                                  []
                                  (meander.runtime.zeta/fail))
                                 (f__73499
                                  []
                                  (clojure.core/let
                                   [!xs
                                    (clojure.core/conj
                                     !xs
                                     input__72600_nth_0__)]
                                   [!xs]))]
                                (if
                                 (clojure.core/symbol?
                                  input__72600_nth_0__)
                                 (clojure.core/let
                                  [X__72603
                                   (clojure.core/namespace
                                    input__72600_nth_0__)]
                                  (clojure.core/case
                                   X__72603
                                   (nil)
                                   (clojure.core/let
                                    [X__72605
                                     (clojure.core/name
                                      input__72600_nth_0__)]
                                    (if
                                     (clojure.core/string? X__72605)
                                     (if
                                      (clojure.core/re-matches
                                       #"\.\.(?:\.|\d+)"
                                       X__72605)
                                      (save__72601)
                                      (f__73499))
                                     (f__73499)))
                                   (f__73499)))
                                 (f__73499)))))
                             (clojure.core/fn
                              [[!xs]]
                              (clojure.core/let
                               [input__72393_nth_1___r___l__
                                (clojure.core/subvec
                                 input__72393_nth_1___r__
                                 0
                                 (clojure.core/min
                                  (clojure.core/count
                                   input__72393_nth_1___r__)
                                  1))]
                               (if
                                (clojure.core/=
                                 (clojure.core/count
                                  input__72393_nth_1___r___l__)
                                 1)
                                (clojure.core/let
                                 [input__72393_nth_1___r___r__
                                  (clojure.core/subvec
                                   input__72393_nth_1___r__
                                   1)]
                                 (if
                                  (clojure.core/=
                                   input__72393_nth_1___r___l__
                                   ['...])
                                  (clojure.core/let
                                   [?rest input__72393_nth_1___r___r__]
                                   (clojure.core/let
                                    [?env input__72393_nth_2__]
                                    (try
                                     [(clojure.core/let
                                       [!xs__counter
                                        (meander.runtime.zeta/iterator
                                         !xs)]
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__72443
                                          ['meander.dev.parse.zeta/make-star
                                           (clojure.core/let
                                            [CATA_RESULT__9229__auto__
                                             (CATA__FN__72443
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
                                             (CATA__FN__72443
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
                        (meander.runtime.zeta/fail? result__73496)
                        (recur (clojure.core/next search_space__73495))
                        result__73496))
                      (state__73481))))]
                  (state__73485))
                 (state__73481))
                (state__73481)))
              (state__73481)))
            (state__73481
             []
             (if
              (clojure.core/= (clojure.core/count input__72393) 4)
              (clojure.core/let
               [input__72393_nth_0__
                (clojure.core/nth input__72393 0)
                input__72393_nth_1__
                (clojure.core/nth input__72393 1)
                input__72393_nth_2__
                (clojure.core/nth input__72393 2)]
               (clojure.core/letfn
                [(state__73500
                  []
                  (clojure.core/let
                   [input__72393_nth_3__
                    (clojure.core/nth input__72393 3)]
                   (clojure.core/case
                    input__72393_nth_0__
                    (meander.dev.parse.zeta/make-star)
                    (clojure.core/letfn
                     [(state__73502
                       []
                       (if
                        (clojure.core/map? input__72393_nth_1__)
                        (clojure.core/let
                         [VAL__72609
                          (.valAt input__72393_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__72609
                          (:cat)
                          (clojure.core/let
                           [VAL__72610
                            (.valAt input__72393_nth_1__ :sequence)]
                           (if
                            (clojure.core/vector? VAL__72610)
                            (if
                             (clojure.core/=
                              (clojure.core/count VAL__72610)
                              1)
                             (clojure.core/let
                              [VAL__72610_nth_0__
                               (clojure.core/nth VAL__72610 0)]
                              (if
                               (clojure.core/map? VAL__72610_nth_0__)
                               (clojure.core/let
                                [VAL__72615
                                 (.valAt VAL__72610_nth_0__ :tag)]
                                (clojure.core/case
                                 VAL__72615
                                 (:memory-variable)
                                 (clojure.core/let
                                  [?memory-variable VAL__72610_nth_0__]
                                  (clojure.core/let
                                   [VAL__72611
                                    (.valAt
                                     input__72393_nth_1__
                                     :next)]
                                   (if
                                    (clojure.core/map? VAL__72611)
                                    (clojure.core/let
                                     [VAL__72612
                                      (.valAt VAL__72611 :tag)]
                                     (clojure.core/case
                                      VAL__72612
                                      (:empty)
                                      (clojure.core/let
                                       [?next input__72393_nth_2__]
                                       (clojure.core/let
                                        [?env input__72393_nth_3__]
                                        (try
                                         [(clojure.core/let
                                           [CATA_RESULT__9229__auto__
                                            (CATA__FN__72443
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
                                      (state__73503)))
                                    (state__73503))))
                                 (state__73503)))
                               (state__73503)))
                             (state__73503))
                            (state__73503)))
                          (state__73503)))
                        (state__73503)))
                      (state__73503
                       []
                       (clojure.core/let
                        [?pattern input__72393_nth_1__]
                        (clojure.core/let
                         [?next input__72393_nth_2__]
                         (if
                          (clojure.core/map? input__72393_nth_3__)
                          (clojure.core/let
                           [VAL__72618
                            (.valAt input__72393_nth_3__ :context)]
                           (clojure.core/case
                            VAL__72618
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
                            (state__73501)))
                          (state__73501)))))]
                     (state__73502))
                    (state__73501))))
                 (state__73501
                  []
                  (clojure.core/case
                   input__72393_nth_0__
                   (meander.dev.parse.zeta/make-star)
                   (clojure.core/let
                    [?pattern input__72393_nth_1__]
                    (clojure.core/let
                     [?next input__72393_nth_2__]
                     (try
                      [{:tag :star, :pattern ?pattern, :next ?next}]
                      (catch
                       java.lang.Exception
                       e__10169__auto__
                       (if
                        (meander.runtime.zeta/fail? e__10169__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__10169__auto__))))))
                   (state__73482)))]
                (state__73500)))
              (state__73482)))
            (state__73482
             []
             (if
              (clojure.core/= (clojure.core/count input__72393) 3)
              (clojure.core/let
               [input__72393_nth_0__
                (clojure.core/nth input__72393 0)
                input__72393_nth_1__
                (clojure.core/nth input__72393 1)
                input__72393_nth_2__
                (clojure.core/nth input__72393 2)]
               (clojure.core/case
                input__72393_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__72393_nth_1__)
                 (clojure.core/letfn
                  [(state__73504
                    []
                    (clojure.core/let
                     [input__72393_nth_1___l__
                      (clojure.core/subvec
                       input__72393_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__72393_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__72393_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__72393_nth_1___r__
                        (clojure.core/subvec input__72393_nth_1__ 1)]
                       (clojure.core/let
                        [input__72393_nth_1___l___nth_0__
                         (clojure.core/nth input__72393_nth_1___l__ 0)]
                        (if
                         (clojure.core/symbol?
                          input__72393_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__72626
                           (clojure.core/namespace
                            input__72393_nth_1___l___nth_0__)]
                          (clojure.core/case
                           X__72626
                           (nil)
                           (clojure.core/let
                            [X__72628
                             (clojure.core/name
                              input__72393_nth_1___l___nth_0__)]
                            (if
                             (clojure.core/string? X__72628)
                             (clojure.core/let
                              [ret__72629
                               (clojure.core/re-matches
                                #"\.\.(\d+)"
                                X__72628)]
                              (if
                               (clojure.core/some? ret__72629)
                               (if
                                (clojure.core/vector? ret__72629)
                                (if
                                 (clojure.core/=
                                  (clojure.core/count ret__72629)
                                  2)
                                 (clojure.core/let
                                  [ret__72629_nth_1__
                                   (clojure.core/nth ret__72629 1)]
                                  (clojure.core/let
                                   [?n ret__72629_nth_1__]
                                   (clojure.core/let
                                    [?operator
                                     input__72393_nth_1___l___nth_0__]
                                    (clojure.core/let
                                     [?rest input__72393_nth_1___r__]
                                     (clojure.core/let
                                      [?env input__72393_nth_2__]
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
                                 (state__73505))
                                (state__73505))
                               (state__73505)))
                             (state__73505)))
                           (state__73505)))
                         (state__73505))))
                      (state__73505))))
                   (state__73505
                    []
                    (clojure.core/loop
                     [search_space__73511
                      (meander.match.runtime.epsilon/partitions
                       2
                       input__72393_nth_1__)]
                     (if
                      (clojure.core/seq search_space__73511)
                      (clojure.core/let
                       [input__72393_nth_1___parts__
                        (clojure.core/first search_space__73511)
                        result__73512
                        (clojure.core/let
                         [input__72393_nth_1___l__
                          (clojure.core/nth
                           input__72393_nth_1___parts__
                           0)
                          input__72393_nth_1___r__
                          (clojure.core/nth
                           input__72393_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs []]
                          (clojure.core/let
                           [ret__8090__auto__
                            (meander.runtime.zeta/epsilon-run-star-1
                             input__72393_nth_1___l__
                             [!xs]
                             (clojure.core/fn
                              [[!xs] input__72645]
                              (clojure.core/let
                               [input__72645_nth_0__
                                (clojure.core/nth input__72645 0)]
                               (clojure.core/letfn
                                [(save__72646
                                  []
                                  (meander.runtime.zeta/fail))
                                 (f__73515
                                  []
                                  (clojure.core/let
                                   [!xs
                                    (clojure.core/conj
                                     !xs
                                     input__72645_nth_0__)]
                                   [!xs]))]
                                (if
                                 (clojure.core/symbol?
                                  input__72645_nth_0__)
                                 (clojure.core/let
                                  [X__72648
                                   (clojure.core/namespace
                                    input__72645_nth_0__)]
                                  (clojure.core/case
                                   X__72648
                                   (nil)
                                   (clojure.core/let
                                    [X__72650
                                     (clojure.core/name
                                      input__72645_nth_0__)]
                                    (if
                                     (clojure.core/string? X__72650)
                                     (if
                                      (clojure.core/re-matches
                                       #"\.\.(?:\.|\d+)"
                                       X__72650)
                                      (save__72646)
                                      (f__73515))
                                     (f__73515)))
                                   (f__73515)))
                                 (f__73515)))))
                             (clojure.core/fn
                              [[!xs]]
                              (clojure.core/let
                               [input__72393_nth_1___r___l__
                                (clojure.core/subvec
                                 input__72393_nth_1___r__
                                 0
                                 (clojure.core/min
                                  (clojure.core/count
                                   input__72393_nth_1___r__)
                                  1))]
                               (if
                                (clojure.core/=
                                 (clojure.core/count
                                  input__72393_nth_1___r___l__)
                                 1)
                                (clojure.core/let
                                 [input__72393_nth_1___r___r__
                                  (clojure.core/subvec
                                   input__72393_nth_1___r__
                                   1)]
                                 (clojure.core/let
                                  [input__72393_nth_1___r___l___nth_0__
                                   (clojure.core/nth
                                    input__72393_nth_1___r___l__
                                    0)]
                                  (if
                                   (clojure.core/symbol?
                                    input__72393_nth_1___r___l___nth_0__)
                                   (clojure.core/let
                                    [X__72639
                                     (clojure.core/namespace
                                      input__72393_nth_1___r___l___nth_0__)]
                                    (clojure.core/case
                                     X__72639
                                     (nil)
                                     (clojure.core/let
                                      [X__72641
                                       (clojure.core/name
                                        input__72393_nth_1___r___l___nth_0__)]
                                      (if
                                       (clojure.core/string? X__72641)
                                       (clojure.core/let
                                        [ret__72642
                                         (clojure.core/re-matches
                                          #"\.\.(\d+)"
                                          X__72641)]
                                        (if
                                         (clojure.core/some?
                                          ret__72642)
                                         (if
                                          (clojure.core/vector?
                                           ret__72642)
                                          (if
                                           (clojure.core/=
                                            (clojure.core/count
                                             ret__72642)
                                            2)
                                           (clojure.core/let
                                            [ret__72642_nth_1__
                                             (clojure.core/nth
                                              ret__72642
                                              1)]
                                            (clojure.core/let
                                             [?n ret__72642_nth_1__]
                                             (clojure.core/let
                                              [?rest
                                               input__72393_nth_1___r___r__]
                                              (clojure.core/let
                                               [?env
                                                input__72393_nth_2__]
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
                                                     (CATA__FN__72443
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
                                                     (CATA__FN__72443
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
                        (meander.runtime.zeta/fail? result__73512)
                        (recur (clojure.core/next search_space__73511))
                        result__73512))
                      (state__73506))))
                   (state__73506
                    []
                    (clojure.core/let
                     [input__72393_nth_1___l__
                      (clojure.core/subvec
                       input__72393_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__72393_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__72393_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__72393_nth_1___r__
                        (clojure.core/subvec input__72393_nth_1__ 1)]
                       (clojure.core/let
                        [input__72393_nth_1___l___nth_0__
                         (clojure.core/nth input__72393_nth_1___l__ 0)]
                        (if
                         (clojure.core/symbol?
                          input__72393_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__72657
                           (clojure.core/namespace
                            input__72393_nth_1___l___nth_0__)]
                          (clojure.core/case
                           X__72657
                           (nil)
                           (clojure.core/let
                            [X__72659
                             (clojure.core/name
                              input__72393_nth_1___l___nth_0__)]
                            (if
                             (clojure.core/string? X__72659)
                             (clojure.core/let
                              [ret__72660
                               (clojure.core/re-matches
                                #"\.\.(\?.+)"
                                X__72659)]
                              (if
                               (clojure.core/some? ret__72660)
                               (if
                                (clojure.core/vector? ret__72660)
                                (if
                                 (clojure.core/=
                                  (clojure.core/count ret__72660)
                                  2)
                                 (clojure.core/let
                                  [ret__72660_nth_1__
                                   (clojure.core/nth ret__72660 1)]
                                  (clojure.core/let
                                   [?n ret__72660_nth_1__]
                                   (clojure.core/let
                                    [?operator
                                     input__72393_nth_1___l___nth_0__]
                                    (clojure.core/let
                                     [?rest input__72393_nth_1___r__]
                                     (clojure.core/let
                                      [?env input__72393_nth_2__]
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
                                 (state__73507))
                                (state__73507))
                               (state__73507)))
                             (state__73507)))
                           (state__73507)))
                         (state__73507))))
                      (state__73507))))
                   (state__73507
                    []
                    (clojure.core/loop
                     [search_space__73516
                      (meander.match.runtime.epsilon/partitions
                       2
                       input__72393_nth_1__)]
                     (if
                      (clojure.core/seq search_space__73516)
                      (clojure.core/let
                       [input__72393_nth_1___parts__
                        (clojure.core/first search_space__73516)
                        result__73517
                        (clojure.core/let
                         [input__72393_nth_1___l__
                          (clojure.core/nth
                           input__72393_nth_1___parts__
                           0)
                          input__72393_nth_1___r__
                          (clojure.core/nth
                           input__72393_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs []]
                          (clojure.core/let
                           [ret__8090__auto__
                            (meander.runtime.zeta/epsilon-run-star-1
                             input__72393_nth_1___l__
                             [!xs]
                             (clojure.core/fn
                              [[!xs] input__72676]
                              (clojure.core/let
                               [input__72676_nth_0__
                                (clojure.core/nth input__72676 0)]
                               (clojure.core/letfn
                                [(save__72677
                                  []
                                  (meander.runtime.zeta/fail))
                                 (f__73520
                                  []
                                  (clojure.core/let
                                   [!xs
                                    (clojure.core/conj
                                     !xs
                                     input__72676_nth_0__)]
                                   [!xs]))]
                                (if
                                 (clojure.core/symbol?
                                  input__72676_nth_0__)
                                 (clojure.core/let
                                  [X__72679
                                   (clojure.core/namespace
                                    input__72676_nth_0__)]
                                  (clojure.core/case
                                   X__72679
                                   (nil)
                                   (clojure.core/let
                                    [X__72681
                                     (clojure.core/name
                                      input__72676_nth_0__)]
                                    (if
                                     (clojure.core/string? X__72681)
                                     (if
                                      (clojure.core/re-matches
                                       #"\.\.(?:\.|\d+)"
                                       X__72681)
                                      (save__72677)
                                      (f__73520))
                                     (f__73520)))
                                   (f__73520)))
                                 (f__73520)))))
                             (clojure.core/fn
                              [[!xs]]
                              (clojure.core/let
                               [input__72393_nth_1___r___l__
                                (clojure.core/subvec
                                 input__72393_nth_1___r__
                                 0
                                 (clojure.core/min
                                  (clojure.core/count
                                   input__72393_nth_1___r__)
                                  1))]
                               (if
                                (clojure.core/=
                                 (clojure.core/count
                                  input__72393_nth_1___r___l__)
                                 1)
                                (clojure.core/let
                                 [input__72393_nth_1___r___r__
                                  (clojure.core/subvec
                                   input__72393_nth_1___r__
                                   1)]
                                 (clojure.core/let
                                  [input__72393_nth_1___r___l___nth_0__
                                   (clojure.core/nth
                                    input__72393_nth_1___r___l__
                                    0)]
                                  (if
                                   (clojure.core/symbol?
                                    input__72393_nth_1___r___l___nth_0__)
                                   (clojure.core/let
                                    [X__72670
                                     (clojure.core/namespace
                                      input__72393_nth_1___r___l___nth_0__)]
                                    (clojure.core/case
                                     X__72670
                                     (nil)
                                     (clojure.core/let
                                      [X__72672
                                       (clojure.core/name
                                        input__72393_nth_1___r___l___nth_0__)]
                                      (if
                                       (clojure.core/string? X__72672)
                                       (clojure.core/let
                                        [ret__72673
                                         (clojure.core/re-matches
                                          #"\.\.(\?.+)"
                                          X__72672)]
                                        (if
                                         (clojure.core/some?
                                          ret__72673)
                                         (if
                                          (clojure.core/vector?
                                           ret__72673)
                                          (if
                                           (clojure.core/=
                                            (clojure.core/count
                                             ret__72673)
                                            2)
                                           (clojure.core/let
                                            [ret__72673_nth_1__
                                             (clojure.core/nth
                                              ret__72673
                                              1)]
                                            (clojure.core/let
                                             [?n ret__72673_nth_1__]
                                             (clojure.core/let
                                              [?rest
                                               input__72393_nth_1___r___r__]
                                              (clojure.core/let
                                               [?env
                                                input__72393_nth_2__]
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
                                                     (CATA__FN__72443
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
                                                     (CATA__FN__72443
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
                        (meander.runtime.zeta/fail? result__73517)
                        (recur (clojure.core/next search_space__73516))
                        result__73517))
                      (state__73508))))
                   (state__73508
                    []
                    (clojure.core/let
                     [input__72393_nth_1___l__
                      (clojure.core/subvec
                       input__72393_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__72393_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__72393_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__72393_nth_1___r__
                        (clojure.core/subvec input__72393_nth_1__ 1)]
                       (clojure.core/let
                        [input__72393_nth_1___l___nth_0__
                         (clojure.core/nth input__72393_nth_1___l__ 0)]
                        (if
                         (clojure.core/symbol?
                          input__72393_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__72688
                           (clojure.core/namespace
                            input__72393_nth_1___l___nth_0__)]
                          (clojure.core/case
                           X__72688
                           (nil)
                           (clojure.core/let
                            [X__72690
                             (clojure.core/name
                              input__72393_nth_1___l___nth_0__)]
                            (if
                             (clojure.core/string? X__72690)
                             (clojure.core/let
                              [ret__72691
                               (clojure.core/re-matches
                                #"\.\.(!.+)"
                                X__72690)]
                              (if
                               (clojure.core/some? ret__72691)
                               (if
                                (clojure.core/vector? ret__72691)
                                (if
                                 (clojure.core/=
                                  (clojure.core/count ret__72691)
                                  2)
                                 (clojure.core/let
                                  [ret__72691_nth_1__
                                   (clojure.core/nth ret__72691 1)]
                                  (clojure.core/let
                                   [?n ret__72691_nth_1__]
                                   (clojure.core/let
                                    [?operator
                                     input__72393_nth_1___l___nth_0__]
                                    (clojure.core/let
                                     [?rest input__72393_nth_1___r__]
                                     (clojure.core/let
                                      [?env input__72393_nth_2__]
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
                                 (state__73509))
                                (state__73509))
                               (state__73509)))
                             (state__73509)))
                           (state__73509)))
                         (state__73509))))
                      (state__73509))))
                   (state__73509
                    []
                    (clojure.core/loop
                     [search_space__73521
                      (meander.match.runtime.epsilon/partitions
                       2
                       input__72393_nth_1__)]
                     (if
                      (clojure.core/seq search_space__73521)
                      (clojure.core/let
                       [input__72393_nth_1___parts__
                        (clojure.core/first search_space__73521)
                        result__73522
                        (clojure.core/let
                         [input__72393_nth_1___l__
                          (clojure.core/nth
                           input__72393_nth_1___parts__
                           0)
                          input__72393_nth_1___r__
                          (clojure.core/nth
                           input__72393_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs []]
                          (clojure.core/let
                           [ret__8090__auto__
                            (meander.runtime.zeta/epsilon-run-star-1
                             input__72393_nth_1___l__
                             [!xs]
                             (clojure.core/fn
                              [[!xs] input__72707]
                              (clojure.core/let
                               [input__72707_nth_0__
                                (clojure.core/nth input__72707 0)]
                               (clojure.core/letfn
                                [(save__72708
                                  []
                                  (meander.runtime.zeta/fail))
                                 (f__73525
                                  []
                                  (clojure.core/let
                                   [!xs
                                    (clojure.core/conj
                                     !xs
                                     input__72707_nth_0__)]
                                   [!xs]))]
                                (if
                                 (clojure.core/symbol?
                                  input__72707_nth_0__)
                                 (clojure.core/let
                                  [X__72710
                                   (clojure.core/namespace
                                    input__72707_nth_0__)]
                                  (clojure.core/case
                                   X__72710
                                   (nil)
                                   (clojure.core/let
                                    [X__72712
                                     (clojure.core/name
                                      input__72707_nth_0__)]
                                    (if
                                     (clojure.core/string? X__72712)
                                     (if
                                      (clojure.core/re-matches
                                       #"\.\.(?:\.|\d+)"
                                       X__72712)
                                      (save__72708)
                                      (f__73525))
                                     (f__73525)))
                                   (f__73525)))
                                 (f__73525)))))
                             (clojure.core/fn
                              [[!xs]]
                              (clojure.core/let
                               [input__72393_nth_1___r___l__
                                (clojure.core/subvec
                                 input__72393_nth_1___r__
                                 0
                                 (clojure.core/min
                                  (clojure.core/count
                                   input__72393_nth_1___r__)
                                  1))]
                               (if
                                (clojure.core/=
                                 (clojure.core/count
                                  input__72393_nth_1___r___l__)
                                 1)
                                (clojure.core/let
                                 [input__72393_nth_1___r___r__
                                  (clojure.core/subvec
                                   input__72393_nth_1___r__
                                   1)]
                                 (clojure.core/let
                                  [input__72393_nth_1___r___l___nth_0__
                                   (clojure.core/nth
                                    input__72393_nth_1___r___l__
                                    0)]
                                  (if
                                   (clojure.core/symbol?
                                    input__72393_nth_1___r___l___nth_0__)
                                   (clojure.core/let
                                    [X__72701
                                     (clojure.core/namespace
                                      input__72393_nth_1___r___l___nth_0__)]
                                    (clojure.core/case
                                     X__72701
                                     (nil)
                                     (clojure.core/let
                                      [X__72703
                                       (clojure.core/name
                                        input__72393_nth_1___r___l___nth_0__)]
                                      (if
                                       (clojure.core/string? X__72703)
                                       (clojure.core/let
                                        [ret__72704
                                         (clojure.core/re-matches
                                          #"\.\.(\!.+)"
                                          X__72703)]
                                        (if
                                         (clojure.core/some?
                                          ret__72704)
                                         (if
                                          (clojure.core/vector?
                                           ret__72704)
                                          (if
                                           (clojure.core/=
                                            (clojure.core/count
                                             ret__72704)
                                            2)
                                           (clojure.core/let
                                            [ret__72704_nth_1__
                                             (clojure.core/nth
                                              ret__72704
                                              1)]
                                            (clojure.core/let
                                             [?n ret__72704_nth_1__]
                                             (clojure.core/let
                                              [?rest
                                               input__72393_nth_1___r___r__]
                                              (clojure.core/let
                                               [?env
                                                input__72393_nth_2__]
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
                                                     (CATA__FN__72443
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
                                                     (CATA__FN__72443
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
                        (meander.runtime.zeta/fail? result__73522)
                        (recur (clojure.core/next search_space__73521))
                        result__73522))
                      (state__73510))))
                   (state__73510
                    []
                    (clojure.core/let
                     [!xs (clojure.core/vec input__72393_nth_1__)]
                     (clojure.core/let
                      [?env input__72393_nth_2__]
                      (try
                       [(clojure.core/let
                         [!xs__counter
                          (meander.runtime.zeta/iterator !xs)]
                         (clojure.core/let
                          [CATA_RESULT__9229__auto__
                           (CATA__FN__72443
                            ['meander.dev.parse.zeta/make-cat
                             (clojure.core/into
                              []
                              (clojure.core/loop
                               [return__72444
                                (clojure.core/transient [])]
                               (if
                                (clojure.core/and
                                 (.hasNext !xs__counter))
                                (recur
                                 (clojure.core/conj!
                                  return__72444
                                  (clojure.core/let
                                   [CATA_RESULT__9229__auto__
                                    (CATA__FN__72443
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
                                 return__72444))))
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
                  (state__73504))
                 (state__73483))
                (state__73483)))
              (state__73483)))
            (state__73483
             []
             (if
              (clojure.core/= (clojure.core/count input__72393) 4)
              (clojure.core/let
               [input__72393_nth_0__
                (clojure.core/nth input__72393 0)
                input__72393_nth_1__
                (clojure.core/nth input__72393 1)
                input__72393_nth_2__
                (clojure.core/nth input__72393 2)]
               (clojure.core/letfn
                [(state__73526
                  []
                  (clojure.core/case
                   input__72393_nth_0__
                   (meander.dev.parse.zeta/make-cat)
                   (if
                    (clojure.core/vector? input__72393_nth_1__)
                    (clojure.core/let
                     [!forms []]
                     (clojure.core/let
                      [ret__8090__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__72393_nth_1__
                        [!forms]
                        (clojure.core/fn
                         [[!forms] input__72723]
                         (clojure.core/let
                          [input__72723_nth_0__
                           (clojure.core/nth input__72723 0)]
                          (if
                           (clojure.core/map? input__72723_nth_0__)
                           (clojure.core/let
                            [VAL__72724
                             (.valAt input__72723_nth_0__ :tag)]
                            (clojure.core/case
                             VAL__72724
                             (:literal)
                             (clojure.core/let
                              [VAL__72725
                               (.valAt input__72723_nth_0__ :type)]
                              (if
                               (clojure.core/let
                                [x__6986__auto__ VAL__72725]
                                (clojure.core/case
                                 x__6986__auto__
                                 (:string :char)
                                 true
                                 false))
                               (clojure.core/let
                                [VAL__72726
                                 (.valAt input__72723_nth_0__ :form)]
                                (clojure.core/let
                                 [!forms
                                  (clojure.core/conj
                                   !forms
                                   VAL__72726)]
                                 [!forms]))
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail)))
                           (meander.runtime.zeta/fail))))
                        (clojure.core/fn
                         [[!forms]]
                         (if
                          (clojure.core/map? input__72393_nth_2__)
                          (clojure.core/let
                           [VAL__72720
                            (.valAt input__72393_nth_2__ :tag)]
                           (clojure.core/case
                            VAL__72720
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
                            (state__73527)))
                          (state__73527))))]
                      (if
                       (meander.runtime.zeta/fail? ret__8090__auto__)
                       (state__73527)
                       ret__8090__auto__)))
                    (state__73527))
                   (state__73527)))
                 (state__73527
                  []
                  (clojure.core/let
                   [input__72393_nth_3__
                    (clojure.core/nth input__72393 3)]
                   (clojure.core/case
                    input__72393_nth_0__
                    (meander.dev.parse.zeta/make-cat)
                    (clojure.core/letfn
                     [(state__73533
                       []
                       (if
                        (clojure.core/vector? input__72393_nth_1__)
                        (clojure.core/letfn
                         [(state__73535
                           []
                           (clojure.core/let
                            [input__72393_nth_1___l__
                             (clojure.core/subvec
                              input__72393_nth_1__
                              0
                              (clojure.core/min
                               (clojure.core/count
                                input__72393_nth_1__)
                               1))]
                            (if
                             (clojure.core/=
                              (clojure.core/count
                               input__72393_nth_1___l__)
                              1)
                             (clojure.core/let
                              [input__72393_nth_1___r__
                               (clojure.core/subvec
                                input__72393_nth_1__
                                1)]
                              (clojure.core/let
                               [input__72393_nth_1___l___nth_0__
                                (clojure.core/nth
                                 input__72393_nth_1___l__
                                 0)]
                               (if
                                (clojure.core/map?
                                 input__72393_nth_1___l___nth_0__)
                                (clojure.core/let
                                 [VAL__72732
                                  (.valAt
                                   input__72393_nth_1___l___nth_0__
                                   :tag)]
                                 (clojure.core/case
                                  VAL__72732
                                  (:literal)
                                  (clojure.core/let
                                   [VAL__72733
                                    (.valAt
                                     input__72393_nth_1___l___nth_0__
                                     :type)]
                                   (clojure.core/case
                                    VAL__72733
                                    (:string)
                                    (clojure.core/let
                                     [?ast
                                      input__72393_nth_1___l___nth_0__]
                                     (clojure.core/let
                                      [?rest input__72393_nth_1___r__]
                                      (clojure.core/let
                                       [?next input__72393_nth_2__]
                                       (clojure.core/let
                                        [?env input__72393_nth_3__]
                                        (try
                                         [(clojure.core/let
                                           [CATA_RESULT__9229__auto__
                                            (CATA__FN__72443
                                             ['meander.dev.parse.zeta/make-join
                                              ?ast
                                              (clojure.core/let
                                               [CATA_RESULT__9229__auto__
                                                (CATA__FN__72443
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
                                           (throw
                                            e__10169__auto__))))))))
                                    (state__73536)))
                                  (state__73536)))
                                (state__73536))))
                             (state__73536))))
                          (state__73536
                           []
                           (clojure.core/let
                            [!forms []]
                            (clojure.core/let
                             [ret__8090__auto__
                              (meander.runtime.zeta/epsilon-run-star-1
                               input__72393_nth_1__
                               [!forms]
                               (clojure.core/fn
                                [[!forms] input__72739]
                                (clojure.core/let
                                 [input__72739_nth_0__
                                  (clojure.core/nth input__72739 0)]
                                 (if
                                  (clojure.core/map?
                                   input__72739_nth_0__)
                                  (clojure.core/let
                                   [VAL__72740
                                    (.valAt input__72739_nth_0__ :tag)]
                                   (clojure.core/case
                                    VAL__72740
                                    (:literal)
                                    (clojure.core/let
                                     [VAL__72741
                                      (.valAt
                                       input__72739_nth_0__
                                       :form)]
                                     (clojure.core/let
                                      [!forms
                                       (clojure.core/conj
                                        !forms
                                        VAL__72741)]
                                      [!forms]))
                                    (meander.runtime.zeta/fail)))
                                  (meander.runtime.zeta/fail))))
                               (clojure.core/fn
                                [[!forms]]
                                (if
                                 (clojure.core/map?
                                  input__72393_nth_2__)
                                 (clojure.core/let
                                  [VAL__72736
                                   (.valAt input__72393_nth_2__ :tag)]
                                  (clojure.core/case
                                   VAL__72736
                                   (:empty)
                                   (clojure.core/let
                                    [?env input__72393_nth_3__]
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
                                   (state__73534)))
                                 (state__73534))))]
                             (if
                              (meander.runtime.zeta/fail?
                               ret__8090__auto__)
                              (state__73534)
                              ret__8090__auto__))))]
                         (state__73535))
                        (state__73534)))
                      (state__73534
                       []
                       (clojure.core/let
                        [?sequence input__72393_nth_1__]
                        (clojure.core/let
                         [?next input__72393_nth_2__]
                         (if
                          (clojure.core/map? input__72393_nth_3__)
                          (clojure.core/let
                           [VAL__72745
                            (.valAt input__72393_nth_3__ :context)]
                           (clojure.core/case
                            VAL__72745
                            (:string)
                            (try
                             [{:tag :string-cat,
                               :sequence ?sequence,
                               :next ?next}]
                             (catch
                              java.lang.Exception
                              e__10169__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__10169__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__10169__auto__))))
                            (state__73528)))
                          (state__73528)))))]
                     (state__73533))
                    (state__73528))))
                 (state__73528
                  []
                  (clojure.core/case
                   input__72393_nth_0__
                   (meander.dev.parse.zeta/make-cat)
                   (clojure.core/let
                    [?sequence input__72393_nth_1__]
                    (clojure.core/let
                     [?next input__72393_nth_2__]
                     (try
                      [{:tag :cat, :sequence ?sequence, :next ?next}]
                      (catch
                       java.lang.Exception
                       e__10169__auto__
                       (if
                        (meander.runtime.zeta/fail? e__10169__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__10169__auto__))))))
                   (state__73529)))
                 (state__73529
                  []
                  (clojure.core/let
                   [input__72393_nth_3__
                    (clojure.core/nth input__72393 3)]
                   (clojure.core/case
                    input__72393_nth_0__
                    (meander.dev.parse.zeta/make-join)
                    (if
                     (clojure.core/map? input__72393_nth_1__)
                     (clojure.core/let
                      [VAL__73435 (.valAt input__72393_nth_1__ :tag)]
                      (clojure.core/case
                       VAL__73435
                       (:cat)
                       (clojure.core/let
                        [VAL__72751
                         (.valAt input__72393_nth_1__ :sequence)]
                        (clojure.core/let
                         [?sequence VAL__72751]
                         (clojure.core/let
                          [VAL__72752
                           (.valAt input__72393_nth_1__ :next)]
                          (if
                           (clojure.core/map? VAL__72752)
                           (clojure.core/let
                            [VAL__72753 (.valAt VAL__72752 :tag)]
                            (clojure.core/case
                             VAL__72753
                             (:empty)
                             (clojure.core/let
                              [?right input__72393_nth_2__]
                              (clojure.core/let
                               [?env input__72393_nth_3__]
                               (try
                                [(clojure.core/let
                                  [CATA_RESULT__9229__auto__
                                   (CATA__FN__72443
                                    ['meander.dev.parse.zeta/make-cat
                                     ?sequence
                                     ?right
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
                             (state__73530)))
                           (state__73530)))))
                       (:literal)
                       (clojure.core/let
                        [VAL__72757
                         (.valAt input__72393_nth_1__ :type)]
                        (clojure.core/case
                         VAL__72757
                         (:string)
                         (clojure.core/let
                          [VAL__72758
                           (.valAt input__72393_nth_1__ :form)]
                          (clojure.core/let
                           [?form-1 VAL__72758]
                           (if
                            (clojure.core/map? input__72393_nth_2__)
                            (clojure.core/let
                             [VAL__72759
                              (.valAt input__72393_nth_2__ :tag)]
                             (clojure.core/case
                              VAL__72759
                              (:string-join)
                              (clojure.core/let
                               [VAL__72760
                                (.valAt input__72393_nth_2__ :left)]
                               (if
                                (clojure.core/map? VAL__72760)
                                (clojure.core/let
                                 [VAL__72761 (.valAt VAL__72760 :tag)]
                                 (clojure.core/case
                                  VAL__72761
                                  (:literal)
                                  (clojure.core/let
                                   [VAL__72762
                                    (.valAt VAL__72760 :type)]
                                   (clojure.core/case
                                    VAL__72762
                                    (:string)
                                    (clojure.core/let
                                     [VAL__72763
                                      (.valAt VAL__72760 :form)]
                                     (clojure.core/let
                                      [?form-2 VAL__72763]
                                      (clojure.core/let
                                       [VAL__72764
                                        (.valAt
                                         input__72393_nth_2__
                                         :right)]
                                       (clojure.core/let
                                        [?right VAL__72764]
                                        (if
                                         (clojure.core/map?
                                          input__72393_nth_3__)
                                         (clojure.core/let
                                          [VAL__72765
                                           (.valAt
                                            input__72393_nth_3__
                                            :context)]
                                          (clojure.core/case
                                           VAL__72765
                                           (:string)
                                           (clojure.core/let
                                            [?env input__72393_nth_3__]
                                            (try
                                             [(clojure.core/let
                                               [CATA_RESULT__9229__auto__
                                                (CATA__FN__72443
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
                                           (state__73530)))
                                         (state__73530))))))
                                    (state__73530)))
                                  (state__73530)))
                                (state__73530)))
                              (state__73530)))
                            (state__73530))))
                         (state__73530)))
                       (state__73530)))
                     (state__73530))
                    (state__73530))))
                 (state__73530
                  []
                  (clojure.core/case
                   input__72393_nth_0__
                   (meander.dev.parse.zeta/make-join)
                   (if
                    (clojure.core/map? input__72393_nth_1__)
                    (clojure.core/let
                     [VAL__73437 (.valAt input__72393_nth_1__ :tag)]
                     (clojure.core/letfn
                      [(state__73538
                        []
                        (clojure.core/letfn
                         [(save__72769 [] (state__73539))
                          (f__73540
                           []
                           (clojure.core/let
                            [?ast input__72393_nth_1__]
                            (if
                             (clojure.core/map? input__72393_nth_2__)
                             (clojure.core/let
                              [VAL__72770
                               (.valAt input__72393_nth_2__ :tag)]
                              (clojure.core/case
                               VAL__72770
                               (:cat)
                               (clojure.core/let
                                [VAL__72771
                                 (.valAt
                                  input__72393_nth_2__
                                  :sequence)]
                                (clojure.core/let
                                 [?sequence VAL__72771]
                                 (clojure.core/let
                                  [VAL__72772
                                   (.valAt input__72393_nth_2__ :next)]
                                  (clojure.core/let
                                   [?next VAL__72772]
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
                                     e__10169__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__10169__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__10169__auto__))))))))
                               (state__73539)))
                             (state__73539))))]
                         (clojure.core/case
                          VAL__73437
                          (:join)
                          (save__72769)
                          (f__73540))))
                       (state__73539
                        []
                        (clojure.core/letfn
                         [(save__72776 [] (state__73531))
                          (f__73541
                           []
                           (clojure.core/let
                            [?ast input__72393_nth_1__]
                            (if
                             (clojure.core/map? input__72393_nth_2__)
                             (clojure.core/let
                              [VAL__72777
                               (.valAt input__72393_nth_2__ :tag)]
                              (clojure.core/case
                               VAL__72777
                               (:string-cat)
                               (clojure.core/let
                                [VAL__72778
                                 (.valAt
                                  input__72393_nth_2__
                                  :sequence)]
                                (clojure.core/let
                                 [?sequence VAL__72778]
                                 (clojure.core/let
                                  [VAL__72779
                                   (.valAt input__72393_nth_2__ :next)]
                                  (clojure.core/let
                                   [?next VAL__72779]
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
                                      (throw e__10169__auto__))))))))
                               (state__73531)))
                             (state__73531))))]
                         (clojure.core/case
                          VAL__73437
                          (:string-join)
                          (save__72776)
                          (f__73541))))]
                      (state__73538)))
                    (state__73531))
                   (state__73531)))
                 (state__73531
                  []
                  (clojure.core/let
                   [input__72393_nth_3__
                    (clojure.core/nth input__72393 3)]
                   (clojure.core/case
                    input__72393_nth_0__
                    (meander.dev.parse.zeta/make-join)
                    (clojure.core/letfn
                     [(state__73542
                       []
                       (if
                        (clojure.core/map? input__72393_nth_1__)
                        (clojure.core/let
                         [VAL__73438
                          (.valAt input__72393_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__73438
                          (:string-cat)
                          (clojure.core/let
                           [VAL__72783
                            (.valAt input__72393_nth_1__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__72783]
                            (clojure.core/let
                             [VAL__72784
                              (.valAt input__72393_nth_1__ :next)]
                             (if
                              (clojure.core/map? VAL__72784)
                              (clojure.core/let
                               [VAL__72785 (.valAt VAL__72784 :tag)]
                               (clojure.core/case
                                VAL__72785
                                (:empty)
                                (clojure.core/let
                                 [?right input__72393_nth_2__]
                                 (clojure.core/let
                                  [?env input__72393_nth_3__]
                                  (try
                                   [(clojure.core/let
                                     [CATA_RESULT__9229__auto__
                                      (CATA__FN__72443
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
                                (state__73543)))
                              (state__73543)))))
                          (:string-star)
                          (clojure.core/let
                           [VAL__72789
                            (.valAt input__72393_nth_1__ :pattern)]
                           (clojure.core/let
                            [?pattern VAL__72789]
                            (clojure.core/let
                             [VAL__72790
                              (.valAt input__72393_nth_1__ :next)]
                             (if
                              (clojure.core/map? VAL__72790)
                              (clojure.core/let
                               [VAL__72791 (.valAt VAL__72790 :tag)]
                               (clojure.core/case
                                VAL__72791
                                (:empty)
                                (clojure.core/let
                                 [?right input__72393_nth_2__]
                                 (if
                                  (clojure.core/map?
                                   input__72393_nth_3__)
                                  (clojure.core/let
                                   [VAL__72792
                                    (.valAt
                                     input__72393_nth_3__
                                     :context)]
                                   (clojure.core/case
                                    VAL__72792
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
                                    (state__73543)))
                                  (state__73543)))
                                (state__73543)))
                              (state__73543)))))
                          (:string-join)
                          (clojure.core/let
                           [VAL__72796
                            (.valAt input__72393_nth_1__ :left)]
                           (clojure.core/let
                            [?left VAL__72796]
                            (clojure.core/let
                             [VAL__72797
                              (.valAt input__72393_nth_1__ :right)]
                             (clojure.core/let
                              [?right-1 VAL__72797]
                              (clojure.core/let
                               [?right-2 input__72393_nth_2__]
                               (if
                                (clojure.core/map?
                                 input__72393_nth_3__)
                                (clojure.core/let
                                 [VAL__72798
                                  (.valAt
                                   input__72393_nth_3__
                                   :context)]
                                 (clojure.core/case
                                  VAL__72798
                                  (:string)
                                  (clojure.core/let
                                   [?env input__72393_nth_3__]
                                   (try
                                    [{:tag :string-join,
                                      :left ?left,
                                      :right
                                      (clojure.core/let
                                       [CATA_RESULT__9229__auto__
                                        (CATA__FN__72443
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
                                  (state__73543)))
                                (state__73543)))))))
                          (state__73543)))
                        (state__73543)))
                      (state__73543
                       []
                       (clojure.core/let
                        [?left input__72393_nth_1__]
                        (if
                         (clojure.core/map? input__72393_nth_2__)
                         (clojure.core/let
                          [VAL__72801
                           (.valAt input__72393_nth_2__ :tag)]
                          (clojure.core/case
                           VAL__72801
                           (:empty)
                           (clojure.core/let
                            [?env input__72393_nth_3__]
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
                           (state__73544)))
                         (state__73544))))
                      (state__73544
                       []
                       (if
                        (clojure.core/map? input__72393_nth_1__)
                        (clojure.core/let
                         [VAL__72804
                          (.valAt input__72393_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__72804
                          (:empty)
                          (clojure.core/let
                           [?right input__72393_nth_2__]
                           (clojure.core/let
                            [?env input__72393_nth_3__]
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
                          (state__73545)))
                        (state__73545)))
                      (state__73545
                       []
                       (clojure.core/let
                        [?left input__72393_nth_1__]
                        (clojure.core/let
                         [?right input__72393_nth_2__]
                         (clojure.core/letfn
                          [(state__73546
                            []
                            (if
                             (clojure.core/map? input__72393_nth_3__)
                             (clojure.core/let
                              [VAL__72807
                               (.valAt input__72393_nth_3__ :context)]
                              (clojure.core/case
                               VAL__72807
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
                               (state__73547)))
                             (state__73547)))
                           (state__73547
                            []
                            (clojure.core/let
                             [?env input__72393_nth_3__]
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
                          (state__73546)))))]
                     (state__73542))
                    (state__73484))))]
                (state__73526)))
              (state__73484)))
            (state__73484
             []
             (if
              (clojure.core/= (clojure.core/count input__72393) 3)
              (clojure.core/let
               [input__72393_nth_0__
                (clojure.core/nth input__72393 0)
                input__72393_nth_1__
                (clojure.core/nth input__72393 1)
                input__72393_nth_2__
                (clojure.core/nth input__72393 2)]
               (clojure.core/case
                input__72393_nth_0__
                (meander.dev.parse.zeta/parse-entries)
                (if
                 (clojure.core/map? input__72393_nth_1__)
                 (clojure.core/let
                  [VAL__72812
                   (.valAt input__72393_nth_1__ :meander.zeta/as)]
                  (clojure.core/let
                   [?pattern VAL__72812]
                   (clojure.core/let
                    [X__72814
                     ((clojure.core/fn
                       [m__6993__auto__]
                       (clojure.core/dissoc
                        m__6993__auto__
                        :meander.zeta/as))
                      input__72393_nth_1__)]
                    (clojure.core/let
                     [?rest X__72814]
                     (clojure.core/letfn
                      [(save__72815 [] (state__73443))
                       (f__73548
                        []
                        (clojure.core/let
                         [?env input__72393_nth_2__]
                         (try
                          [{:tag :as,
                            :pattern
                            (clojure.core/let
                             [CATA_RESULT__9229__auto__
                              (CATA__FN__72443 [?pattern ?env])]
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
                              (CATA__FN__72443 [?rest ?env])]
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
                       (clojure.core/= ?rest input__72393_nth_1__)
                       (save__72815)
                       (f__73548)))))))
                 (state__73443))
                (state__73443)))
              (state__73443)))]
           (state__73480))
          (state__73443)))
        (state__73443
         []
         (clojure.core/letfn
          [(def__72818
            [arg__72851 ?ns]
            (clojure.core/letfn
             [(state__73549
               []
               (clojure.core/let
                [x__72852 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__72852)
                 (clojure.core/let [?env arg__72851] [?env ?ns])
                 (state__73550))))
              (state__73550
               []
               (if
                (clojure.core/map? arg__72851)
                (clojure.core/let
                 [VAL__72853 (.valAt arg__72851 :aliases)]
                 (if
                  (clojure.core/map? VAL__72853)
                  (clojure.core/let
                   [X__72855 (clojure.core/set VAL__72853)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__72855))
                    (clojure.core/loop
                     [search_space__73551 (clojure.core/seq X__72855)]
                     (if
                      (clojure.core/seq search_space__73551)
                      (clojure.core/let
                       [elem__72856
                        (clojure.core/first search_space__73551)
                        result__73552
                        (clojure.core/let
                         [elem__72856_nth_0__
                          (clojure.core/nth elem__72856 0)
                          elem__72856_nth_1__
                          (clojure.core/nth elem__72856 1)]
                         (if
                          (clojure.core/symbol? elem__72856_nth_0__)
                          (clojure.core/let
                           [X__72858
                            (clojure.core/name elem__72856_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__72858)
                            (if
                             (clojure.core/symbol? elem__72856_nth_1__)
                             (clojure.core/let
                              [X__72860
                               (clojure.core/name elem__72856_nth_1__)]
                              (clojure.core/case
                               X__72860
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__72851]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__73552)
                        (recur (clojure.core/next search_space__73551))
                        result__73552))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__73549)))]
          (if
           (clojure.core/vector? input__72393)
           (if
            (clojure.core/= (clojure.core/count input__72393) 3)
            (clojure.core/let
             [input__72393_nth_0__
              (clojure.core/nth input__72393 0)
              input__72393_nth_1__
              (clojure.core/nth input__72393 1)
              input__72393_nth_2__
              (clojure.core/nth input__72393 2)]
             (clojure.core/case
              input__72393_nth_0__
              (meander.dev.parse.zeta/parse-entries)
              (if
               (clojure.core/map? input__72393_nth_1__)
               (clojure.core/let
                [X__72823 (clojure.core/set input__72393_nth_1__)]
                (if
                 (clojure.core/<= 1 (clojure.core/count X__72823))
                 (clojure.core/loop
                  [search_space__73554 (clojure.core/seq X__72823)]
                  (if
                   (clojure.core/seq search_space__73554)
                   (clojure.core/let
                    [elem__72824
                     (clojure.core/first search_space__73554)
                     result__73555
                     (clojure.core/let
                      [elem__72824_nth_0__
                       (clojure.core/nth elem__72824 0)
                       elem__72824_nth_1__
                       (clojure.core/nth elem__72824 1)]
                      (clojure.core/let
                       [*m__72412 elem__72824_nth_0__]
                       (if
                        (clojure.core/symbol? elem__72824_nth_0__)
                        (clojure.core/let
                         [X__72826
                          (clojure.core/namespace elem__72824_nth_0__)]
                         (clojure.core/let
                          [?ns X__72826]
                          (clojure.core/let
                           [X__72828
                            (clojure.core/name elem__72824_nth_0__)]
                           (if
                            (clojure.core/string? X__72828)
                            (if
                             (clojure.core/re-matches #"&.*" X__72828)
                             (clojure.core/let
                              [?pattern elem__72824_nth_1__]
                              (clojure.core/let
                               [X__72830
                                ((clojure.core/fn
                                  [m__6993__auto__]
                                  (clojure.core/dissoc
                                   m__6993__auto__
                                   *m__72412))
                                 input__72393_nth_1__)]
                               (clojure.core/let
                                [?rest X__72830]
                                (clojure.core/let
                                 [x__7926__auto__
                                  (def__72818
                                   input__72393_nth_2__
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
                                        (CATA__FN__72443
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
                                        (CATA__FN__72443
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
                     (meander.runtime.zeta/fail? result__73555)
                     (recur (clojure.core/next search_space__73554))
                     result__73555))
                   (state__73444)))
                 (state__73444)))
               (state__73444))
              (state__73444)))
            (state__73444))
           (state__73444))))
        (state__73444
         []
         (if
          (clojure.core/vector? input__72393)
          (clojure.core/letfn
           [(state__73557
             []
             (if
              (clojure.core/= (clojure.core/count input__72393) 3)
              (clojure.core/let
               [input__72393_nth_0__
                (clojure.core/nth input__72393 0)
                input__72393_nth_1__
                (clojure.core/nth input__72393 1)
                input__72393_nth_2__
                (clojure.core/nth input__72393 2)]
               (clojure.core/case
                input__72393_nth_0__
                (meander.dev.parse.zeta/parse-entries)
                (clojure.core/letfn
                 [(state__73559
                   []
                   (if
                    (clojure.core/map? input__72393_nth_1__)
                    (clojure.core/let
                     [X__72874 (clojure.core/set input__72393_nth_1__)]
                     (if
                      (clojure.core/<= 1 (clojure.core/count X__72874))
                      (clojure.core/loop
                       [search_space__73561
                        (clojure.core/seq X__72874)]
                       (if
                        (clojure.core/seq search_space__73561)
                        (clojure.core/let
                         [elem__72875
                          (clojure.core/first search_space__73561)
                          result__73562
                          (clojure.core/let
                           [elem__72875_nth_0__
                            (clojure.core/nth elem__72875 0)
                            elem__72875_nth_1__
                            (clojure.core/nth elem__72875 1)]
                           (clojure.core/let
                            [?key-pattern elem__72875_nth_0__]
                            (clojure.core/let
                             [?val-pattern elem__72875_nth_1__]
                             (clojure.core/let
                              [X__72877
                               ((clojure.core/fn
                                 [m__6993__auto__]
                                 (clojure.core/dissoc
                                  m__6993__auto__
                                  ?key-pattern))
                                input__72393_nth_1__)]
                              (clojure.core/let
                               [?rest X__72877]
                               (clojure.core/let
                                [?env input__72393_nth_2__]
                                (try
                                 [{:tag :entry,
                                   :key-pattern
                                   (clojure.core/let
                                    [CATA_RESULT__9229__auto__
                                     (CATA__FN__72443
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
                                     (CATA__FN__72443
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
                                     (CATA__FN__72443
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
                          (meander.runtime.zeta/fail? result__73562)
                          (recur
                           (clojure.core/next search_space__73561))
                          result__73562))
                        (state__73560)))
                      (state__73560)))
                    (state__73560)))
                  (state__73560
                   []
                   (if
                    (clojure.core/map? input__72393_nth_1__)
                    (clojure.core/let
                     [?env input__72393_nth_2__]
                     (try
                      [{:tag :some-map}]
                      (catch
                       java.lang.Exception
                       e__10169__auto__
                       (if
                        (meander.runtime.zeta/fail? e__10169__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__10169__auto__)))))
                    (state__73558)))]
                 (state__73559))
                (meander.dev.parse.zeta/parse-with-bindings)
                (clojure.core/letfn
                 [(state__73564
                   []
                   (if
                    (clojure.core/vector? input__72393_nth_1__)
                    (clojure.core/case
                     input__72393_nth_1__
                     ([])
                     (clojure.core/let
                      [?env input__72393_nth_2__]
                      (try
                       [[]]
                       (catch
                        java.lang.Exception
                        e__10169__auto__
                        (if
                         (meander.runtime.zeta/fail? e__10169__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__10169__auto__)))))
                     (state__73565))
                    (state__73565)))
                  (state__73565
                   []
                   (if
                    (clojure.core/vector? input__72393_nth_1__)
                    (if
                     (clojure.core/=
                      (clojure.core/count input__72393_nth_1__)
                      1)
                     (clojure.core/let
                      [?env input__72393_nth_2__]
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
                     (state__73566))
                    (state__73566)))
                  (state__73566
                   []
                   (if
                    (clojure.core/vector? input__72393_nth_1__)
                    (clojure.core/let
                     [input__72393_nth_1___l__
                      (clojure.core/subvec
                       input__72393_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__72393_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__72393_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__72393_nth_1___r__
                        (clojure.core/subvec input__72393_nth_1__ 2)]
                       (clojure.core/let
                        [input__72393_nth_1___l___nth_0__
                         (clojure.core/nth input__72393_nth_1___l__ 0)
                         input__72393_nth_1___l___nth_1__
                         (clojure.core/nth input__72393_nth_1___l__ 1)]
                        (if
                         (clojure.core/symbol?
                          input__72393_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__72891
                           (clojure.core/namespace
                            input__72393_nth_1___l___nth_0__)]
                          (clojure.core/let
                           [X__72893
                            (clojure.core/name
                             input__72393_nth_1___l___nth_0__)]
                           (if
                            (clojure.core/string? X__72893)
                            (if
                             (clojure.core/re-matches #"%.+" X__72893)
                             (clojure.core/let
                              [?symbol
                               input__72393_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?pattern
                                input__72393_nth_1___l___nth_1__]
                               (clojure.core/let
                                [?rest input__72393_nth_1___r__]
                                (clojure.core/let
                                 [?env input__72393_nth_2__]
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
                                         (CATA__FN__72443
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
                                       (CATA__FN__72443
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
                             (state__73567))
                            (state__73567))))
                         (state__73567))))
                      (state__73567)))
                    (state__73567)))
                  (state__73567
                   []
                   (if
                    (clojure.core/vector? input__72393_nth_1__)
                    (clojure.core/let
                     [input__72393_nth_1___l__
                      (clojure.core/subvec
                       input__72393_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__72393_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__72393_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__72393_nth_1___r__
                        (clojure.core/subvec input__72393_nth_1__ 2)]
                       (clojure.core/let
                        [input__72393_nth_1___l___nth_0__
                         (clojure.core/nth input__72393_nth_1___l__ 0)
                         input__72393_nth_1___l___nth_1__
                         (clojure.core/nth input__72393_nth_1___l__ 1)]
                        (clojure.core/let
                         [?x input__72393_nth_1___l___nth_0__]
                         (clojure.core/let
                          [?pattern input__72393_nth_1___l___nth_1__]
                          (clojure.core/let
                           [?rest input__72393_nth_1___r__]
                           (clojure.core/let
                            [?env input__72393_nth_2__]
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
                      (state__73558)))
                    (state__73558)))]
                 (state__73564))
                (state__73558)))
              (state__73558)))
            (state__73558
             []
             (if
              (clojure.core/= (clojure.core/count input__72393) 2)
              (clojure.core/let
               [input__72393_nth_0__
                (clojure.core/nth input__72393 0)
                input__72393_nth_1__
                (clojure.core/nth input__72393 1)]
               (if
                (clojure.core/vector? input__72393_nth_0__)
                (clojure.core/let
                 [?sequence input__72393_nth_0__]
                 (clojure.core/let
                  [?form input__72393_nth_0__]
                  (clojure.core/let
                   [?env input__72393_nth_1__]
                   (try
                    [{:tag :vector,
                      :next
                      (clojure.core/let
                       [CATA_RESULT__9229__auto__
                        (CATA__FN__72443
                         ['meander.dev.parse.zeta/parse-sequential
                          ?sequence
                          (clojure.core/let
                           [form__9328__auto__ {:context :vector}]
                           (clojure.core/merge
                            (clojure.core/into {} ?env)
                            form__9328__auto__))])]
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
                (state__73445)))
              (state__73445)))]
           (state__73557))
          (state__73445)))
        (state__73445
         []
         (clojure.core/letfn
          [(def__72903
            [arg__72926 ?__72394]
            (clojure.core/letfn
             [(state__73568
               []
               (clojure.core/let
                [x__72927 "meander.zeta"]
                (if
                 (clojure.core/= ?__72394 x__72927)
                 [?__72394]
                 (state__73569))))
              (state__73569
               []
               (if
                (clojure.core/map? arg__72926)
                (clojure.core/let
                 [VAL__72928 (.valAt arg__72926 :aliases)]
                 (if
                  (clojure.core/map? VAL__72928)
                  (clojure.core/let
                   [X__72930 (clojure.core/set VAL__72928)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__72930))
                    (clojure.core/loop
                     [search_space__73570 (clojure.core/seq X__72930)]
                     (if
                      (clojure.core/seq search_space__73570)
                      (clojure.core/let
                       [elem__72931
                        (clojure.core/first search_space__73570)
                        result__73571
                        (clojure.core/let
                         [elem__72931_nth_0__
                          (clojure.core/nth elem__72931 0)
                          elem__72931_nth_1__
                          (clojure.core/nth elem__72931 1)]
                         (if
                          (clojure.core/symbol? elem__72931_nth_0__)
                          (clojure.core/let
                           [X__72933
                            (clojure.core/name elem__72931_nth_0__)]
                           (if
                            (clojure.core/= ?__72394 X__72933)
                            (if
                             (clojure.core/symbol? elem__72931_nth_1__)
                             (clojure.core/let
                              [X__72935
                               (clojure.core/name elem__72931_nth_1__)]
                              (clojure.core/case
                               X__72935
                               ("meander.zeta")
                               [?__72394]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__73571)
                        (recur (clojure.core/next search_space__73570))
                        result__73571))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__73568)))]
          (if
           (clojure.core/vector? input__72393)
           (if
            (clojure.core/= (clojure.core/count input__72393) 2)
            (clojure.core/let
             [input__72393_nth_0__
              (clojure.core/nth input__72393 0)
              input__72393_nth_1__
              (clojure.core/nth input__72393 1)]
             (if
              (clojure.core/seq? input__72393_nth_0__)
              (clojure.core/let
               [input__72393_nth_0___l__
                (clojure.core/take 1 input__72393_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__72393_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__72393_nth_0___r__
                  (clojure.core/drop 1 input__72393_nth_0__)]
                 (clojure.core/let
                  [input__72393_nth_0___l___nth_0__
                   (clojure.core/nth input__72393_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__72393_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__72913
                     (clojure.core/namespace
                      input__72393_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__72394 X__72913]
                     (clojure.core/let
                      [X__72915
                       (clojure.core/name
                        input__72393_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__72915
                       ("with")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__72903 input__72393_nth_1__ ?__72394)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__73446)
                         (clojure.core/let
                          [[?__72394] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__72393)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__72393)
                             2)
                            (clojure.core/let
                             [input__72393_nth_0__
                              (clojure.core/nth input__72393 0)
                              input__72393_nth_1__
                              (clojure.core/nth input__72393 1)]
                             (if
                              (clojure.core/seq? input__72393_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__72393_nth_0__)
                                3)
                               (clojure.core/let
                                [input__72393_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__72393_nth_0__
                                  1)
                                 input__72393_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__72393_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?bindings
                                  input__72393_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?body input__72393_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__72393_nth_0__]
                                   (clojure.core/let
                                    [?env input__72393_nth_1__]
                                    (try
                                     [{:tag :with,
                                       :bindings
                                       {:tag :with-bindings,
                                        :bindings
                                        (clojure.core/let
                                         [CATA_RESULT__9229__auto__
                                          (CATA__FN__72443
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
                                         (CATA__FN__72443
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
                               (state__73446))
                              (state__73446)))
                            (state__73446))
                           (state__73446)))))
                       (state__73446)))))
                   (state__73446))))
                (state__73446)))
              (state__73446)))
            (state__73446))
           (state__73446))))
        (state__73446
         []
         (clojure.core/letfn
          [(def__72937
            [arg__72960 ?__72395]
            (clojure.core/letfn
             [(state__73573
               []
               (clojure.core/let
                [x__72961 "meander.zeta"]
                (if
                 (clojure.core/= ?__72395 x__72961)
                 [?__72395]
                 (state__73574))))
              (state__73574
               []
               (if
                (clojure.core/map? arg__72960)
                (clojure.core/let
                 [VAL__72962 (.valAt arg__72960 :aliases)]
                 (if
                  (clojure.core/map? VAL__72962)
                  (clojure.core/let
                   [X__72964 (clojure.core/set VAL__72962)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__72964))
                    (clojure.core/loop
                     [search_space__73575 (clojure.core/seq X__72964)]
                     (if
                      (clojure.core/seq search_space__73575)
                      (clojure.core/let
                       [elem__72965
                        (clojure.core/first search_space__73575)
                        result__73576
                        (clojure.core/let
                         [elem__72965_nth_0__
                          (clojure.core/nth elem__72965 0)
                          elem__72965_nth_1__
                          (clojure.core/nth elem__72965 1)]
                         (if
                          (clojure.core/symbol? elem__72965_nth_0__)
                          (clojure.core/let
                           [X__72967
                            (clojure.core/name elem__72965_nth_0__)]
                           (if
                            (clojure.core/= ?__72395 X__72967)
                            (if
                             (clojure.core/symbol? elem__72965_nth_1__)
                             (clojure.core/let
                              [X__72969
                               (clojure.core/name elem__72965_nth_1__)]
                              (clojure.core/case
                               X__72969
                               ("meander.zeta")
                               [?__72395]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__73576)
                        (recur (clojure.core/next search_space__73575))
                        result__73576))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__73573)))]
          (if
           (clojure.core/vector? input__72393)
           (if
            (clojure.core/= (clojure.core/count input__72393) 2)
            (clojure.core/let
             [input__72393_nth_0__
              (clojure.core/nth input__72393 0)
              input__72393_nth_1__
              (clojure.core/nth input__72393 1)]
             (if
              (clojure.core/seq? input__72393_nth_0__)
              (clojure.core/let
               [input__72393_nth_0___l__
                (clojure.core/take 1 input__72393_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__72393_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__72393_nth_0___r__
                  (clojure.core/drop 1 input__72393_nth_0__)]
                 (clojure.core/let
                  [input__72393_nth_0___l___nth_0__
                   (clojure.core/nth input__72393_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__72393_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__72947
                     (clojure.core/namespace
                      input__72393_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__72395 X__72947]
                     (clojure.core/let
                      [X__72949
                       (clojure.core/name
                        input__72393_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__72949
                       ("apply")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__72937 input__72393_nth_1__ ?__72395)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__73447)
                         (clojure.core/let
                          [[?__72395] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__72393)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__72393)
                             2)
                            (clojure.core/let
                             [input__72393_nth_0__
                              (clojure.core/nth input__72393 0)
                              input__72393_nth_1__
                              (clojure.core/nth input__72393 1)]
                             (if
                              (clojure.core/seq? input__72393_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__72393_nth_0__)
                                3)
                               (clojure.core/let
                                [input__72393_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__72393_nth_0__
                                  1)
                                 input__72393_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__72393_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?fn input__72393_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?pattern
                                   input__72393_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__72393_nth_0__]
                                   (clojure.core/let
                                    [?env input__72393_nth_1__]
                                    (try
                                     [{:tag :apply,
                                       :fn ?fn,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__72443
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
                               (state__73447))
                              (state__73447)))
                            (state__73447))
                           (state__73447)))))
                       (state__73447)))))
                   (state__73447))))
                (state__73447)))
              (state__73447)))
            (state__73447))
           (state__73447))))
        (state__73447
         []
         (clojure.core/letfn
          [(def__72971
            [arg__72994 ?__72396]
            (clojure.core/letfn
             [(state__73578
               []
               (clojure.core/let
                [x__72995 "meander.zeta"]
                (if
                 (clojure.core/= ?__72396 x__72995)
                 [?__72396]
                 (state__73579))))
              (state__73579
               []
               (if
                (clojure.core/map? arg__72994)
                (clojure.core/let
                 [VAL__72996 (.valAt arg__72994 :aliases)]
                 (if
                  (clojure.core/map? VAL__72996)
                  (clojure.core/let
                   [X__72998 (clojure.core/set VAL__72996)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__72998))
                    (clojure.core/loop
                     [search_space__73580 (clojure.core/seq X__72998)]
                     (if
                      (clojure.core/seq search_space__73580)
                      (clojure.core/let
                       [elem__72999
                        (clojure.core/first search_space__73580)
                        result__73581
                        (clojure.core/let
                         [elem__72999_nth_0__
                          (clojure.core/nth elem__72999 0)
                          elem__72999_nth_1__
                          (clojure.core/nth elem__72999 1)]
                         (if
                          (clojure.core/symbol? elem__72999_nth_0__)
                          (clojure.core/let
                           [X__73001
                            (clojure.core/name elem__72999_nth_0__)]
                           (if
                            (clojure.core/= ?__72396 X__73001)
                            (if
                             (clojure.core/symbol? elem__72999_nth_1__)
                             (clojure.core/let
                              [X__73003
                               (clojure.core/name elem__72999_nth_1__)]
                              (clojure.core/case
                               X__73003
                               ("meander.zeta")
                               [?__72396]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__73581)
                        (recur (clojure.core/next search_space__73580))
                        result__73581))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__73578)))]
          (if
           (clojure.core/vector? input__72393)
           (if
            (clojure.core/= (clojure.core/count input__72393) 2)
            (clojure.core/let
             [input__72393_nth_0__
              (clojure.core/nth input__72393 0)
              input__72393_nth_1__
              (clojure.core/nth input__72393 1)]
             (if
              (clojure.core/seq? input__72393_nth_0__)
              (clojure.core/let
               [input__72393_nth_0___l__
                (clojure.core/take 1 input__72393_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__72393_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__72393_nth_0___r__
                  (clojure.core/drop 1 input__72393_nth_0__)]
                 (clojure.core/let
                  [input__72393_nth_0___l___nth_0__
                   (clojure.core/nth input__72393_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__72393_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__72981
                     (clojure.core/namespace
                      input__72393_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__72396 X__72981]
                     (clojure.core/let
                      [X__72983
                       (clojure.core/name
                        input__72393_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__72983
                       ("and")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__72971 input__72393_nth_1__ ?__72396)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__73448)
                         (clojure.core/let
                          [[?__72396] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__72393)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__72393)
                             2)
                            (clojure.core/let
                             [input__72393_nth_0__
                              (clojure.core/nth input__72393 0)
                              input__72393_nth_1__
                              (clojure.core/nth input__72393 1)]
                             (if
                              (clojure.core/seq? input__72393_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__72393_nth_0__)
                                3)
                               (clojure.core/let
                                [input__72393_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__72393_nth_0__
                                  1)
                                 input__72393_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__72393_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?left input__72393_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?right input__72393_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__72393_nth_0__]
                                   (clojure.core/let
                                    [?env input__72393_nth_1__]
                                    (try
                                     [{:tag :and,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__72443
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
                                         (CATA__FN__72443
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
                               (state__73448))
                              (state__73448)))
                            (state__73448))
                           (state__73448)))))
                       (state__73448)))))
                   (state__73448))))
                (state__73448)))
              (state__73448)))
            (state__73448))
           (state__73448))))
        (state__73448
         []
         (clojure.core/letfn
          [(def__73005
            [arg__73028 ?__72397]
            (clojure.core/letfn
             [(state__73583
               []
               (clojure.core/let
                [x__73029 "meander.zeta"]
                (if
                 (clojure.core/= ?__72397 x__73029)
                 [?__72397]
                 (state__73584))))
              (state__73584
               []
               (if
                (clojure.core/map? arg__73028)
                (clojure.core/let
                 [VAL__73030 (.valAt arg__73028 :aliases)]
                 (if
                  (clojure.core/map? VAL__73030)
                  (clojure.core/let
                   [X__73032 (clojure.core/set VAL__73030)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__73032))
                    (clojure.core/loop
                     [search_space__73585 (clojure.core/seq X__73032)]
                     (if
                      (clojure.core/seq search_space__73585)
                      (clojure.core/let
                       [elem__73033
                        (clojure.core/first search_space__73585)
                        result__73586
                        (clojure.core/let
                         [elem__73033_nth_0__
                          (clojure.core/nth elem__73033 0)
                          elem__73033_nth_1__
                          (clojure.core/nth elem__73033 1)]
                         (if
                          (clojure.core/symbol? elem__73033_nth_0__)
                          (clojure.core/let
                           [X__73035
                            (clojure.core/name elem__73033_nth_0__)]
                           (if
                            (clojure.core/= ?__72397 X__73035)
                            (if
                             (clojure.core/symbol? elem__73033_nth_1__)
                             (clojure.core/let
                              [X__73037
                               (clojure.core/name elem__73033_nth_1__)]
                              (clojure.core/case
                               X__73037
                               ("meander.zeta")
                               [?__72397]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__73586)
                        (recur (clojure.core/next search_space__73585))
                        result__73586))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__73583)))]
          (if
           (clojure.core/vector? input__72393)
           (if
            (clojure.core/= (clojure.core/count input__72393) 2)
            (clojure.core/let
             [input__72393_nth_0__
              (clojure.core/nth input__72393 0)
              input__72393_nth_1__
              (clojure.core/nth input__72393 1)]
             (if
              (clojure.core/seq? input__72393_nth_0__)
              (clojure.core/let
               [input__72393_nth_0___l__
                (clojure.core/take 1 input__72393_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__72393_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__72393_nth_0___r__
                  (clojure.core/drop 1 input__72393_nth_0__)]
                 (clojure.core/let
                  [input__72393_nth_0___l___nth_0__
                   (clojure.core/nth input__72393_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__72393_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__73015
                     (clojure.core/namespace
                      input__72393_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__72397 X__73015]
                     (clojure.core/let
                      [X__73017
                       (clojure.core/name
                        input__72393_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__73017
                       ("cata")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__73005 input__72393_nth_1__ ?__72397)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__73449)
                         (clojure.core/let
                          [[?__72397] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__72393)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__72393)
                             2)
                            (clojure.core/let
                             [input__72393_nth_0__
                              (clojure.core/nth input__72393 0)
                              input__72393_nth_1__
                              (clojure.core/nth input__72393 1)]
                             (if
                              (clojure.core/seq? input__72393_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__72393_nth_0__)
                                2)
                               (clojure.core/let
                                [input__72393_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__72393_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__72393_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__72393_nth_0__]
                                  (clojure.core/let
                                   [?env input__72393_nth_1__]
                                   (try
                                    [{:tag :cata,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__9229__auto__
                                        (CATA__FN__72443
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
                               (state__73449))
                              (state__73449)))
                            (state__73449))
                           (state__73449)))))
                       (state__73449)))))
                   (state__73449))))
                (state__73449)))
              (state__73449)))
            (state__73449))
           (state__73449))))
        (state__73449
         []
         (clojure.core/letfn
          [(def__73039
            [arg__73062 ?__72398]
            (clojure.core/letfn
             [(state__73588
               []
               (clojure.core/let
                [x__73063 "meander.zeta"]
                (if
                 (clojure.core/= ?__72398 x__73063)
                 [?__72398]
                 (state__73589))))
              (state__73589
               []
               (if
                (clojure.core/map? arg__73062)
                (clojure.core/let
                 [VAL__73064 (.valAt arg__73062 :aliases)]
                 (if
                  (clojure.core/map? VAL__73064)
                  (clojure.core/let
                   [X__73066 (clojure.core/set VAL__73064)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__73066))
                    (clojure.core/loop
                     [search_space__73590 (clojure.core/seq X__73066)]
                     (if
                      (clojure.core/seq search_space__73590)
                      (clojure.core/let
                       [elem__73067
                        (clojure.core/first search_space__73590)
                        result__73591
                        (clojure.core/let
                         [elem__73067_nth_0__
                          (clojure.core/nth elem__73067 0)
                          elem__73067_nth_1__
                          (clojure.core/nth elem__73067 1)]
                         (if
                          (clojure.core/symbol? elem__73067_nth_0__)
                          (clojure.core/let
                           [X__73069
                            (clojure.core/name elem__73067_nth_0__)]
                           (if
                            (clojure.core/= ?__72398 X__73069)
                            (if
                             (clojure.core/symbol? elem__73067_nth_1__)
                             (clojure.core/let
                              [X__73071
                               (clojure.core/name elem__73067_nth_1__)]
                              (clojure.core/case
                               X__73071
                               ("meander.zeta")
                               [?__72398]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__73591)
                        (recur (clojure.core/next search_space__73590))
                        result__73591))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__73588)))]
          (if
           (clojure.core/vector? input__72393)
           (if
            (clojure.core/= (clojure.core/count input__72393) 2)
            (clojure.core/let
             [input__72393_nth_0__
              (clojure.core/nth input__72393 0)
              input__72393_nth_1__
              (clojure.core/nth input__72393 1)]
             (if
              (clojure.core/seq? input__72393_nth_0__)
              (clojure.core/let
               [input__72393_nth_0___l__
                (clojure.core/take 1 input__72393_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__72393_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__72393_nth_0___r__
                  (clojure.core/drop 1 input__72393_nth_0__)]
                 (clojure.core/let
                  [input__72393_nth_0___l___nth_0__
                   (clojure.core/nth input__72393_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__72393_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__73049
                     (clojure.core/namespace
                      input__72393_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__72398 X__73049]
                     (clojure.core/let
                      [X__73051
                       (clojure.core/name
                        input__72393_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__73051
                       ("fold")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__73039 input__72393_nth_1__ ?__72398)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__73450)
                         (clojure.core/let
                          [[?__72398] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__72393)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__72393)
                             2)
                            (clojure.core/let
                             [input__72393_nth_0__
                              (clojure.core/nth input__72393 0)
                              input__72393_nth_1__
                              (clojure.core/nth input__72393 1)]
                             (if
                              (clojure.core/seq? input__72393_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 4)
                                 input__72393_nth_0__)
                                4)
                               (clojure.core/let
                                [input__72393_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__72393_nth_0__
                                  1)
                                 input__72393_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__72393_nth_0__
                                  2)
                                 input__72393_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__72393_nth_0__
                                  3)]
                                (clojure.core/let
                                 [?mutable-variable
                                  input__72393_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?initial-value
                                   input__72393_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?fold-function
                                    input__72393_nth_0___nth_3__]
                                   (clojure.core/let
                                    [?form input__72393_nth_0__]
                                    (clojure.core/let
                                     [?env input__72393_nth_1__]
                                     (try
                                      [(clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__72443
                                          ['meander.dev.parse.zeta/make-fold
                                           (clojure.core/let
                                            [CATA_RESULT__9229__auto__
                                             (CATA__FN__72443
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
                                             (CATA__FN__72443
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
                               (state__73450))
                              (state__73450)))
                            (state__73450))
                           (state__73450)))))
                       (state__73450)))))
                   (state__73450))))
                (state__73450)))
              (state__73450)))
            (state__73450))
           (state__73450))))
        (state__73450
         []
         (if
          (clojure.core/vector? input__72393)
          (if
           (clojure.core/= (clojure.core/count input__72393) 5)
           (clojure.core/let
            [input__72393_nth_0__
             (clojure.core/nth input__72393 0)
             input__72393_nth_1__
             (clojure.core/nth input__72393 1)
             input__72393_nth_2__
             (clojure.core/nth input__72393 2)
             input__72393_nth_3__
             (clojure.core/nth input__72393 3)
             input__72393_nth_4__
             (clojure.core/nth input__72393 4)]
            (clojure.core/case
             input__72393_nth_0__
             (meander.dev.parse.zeta/make-fold)
             (if
              (clojure.core/map? input__72393_nth_1__)
              (clojure.core/let
               [VAL__73074 (.valAt input__72393_nth_1__ :tag)]
               (clojure.core/case
                VAL__73074
                (:mutable-variable)
                (clojure.core/let
                 [?variable-ast input__72393_nth_1__]
                 (clojure.core/let
                  [?initial-value-ast input__72393_nth_2__]
                  (clojure.core/let
                   [?fold-function input__72393_nth_3__]
                   (clojure.core/let
                    [?form input__72393_nth_4__]
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
                (state__73451)))
              (state__73451))
             (state__73451)))
           (state__73451))
          (state__73451)))
        (state__73451
         []
         (clojure.core/letfn
          [(def__73076
            [arg__73099 ?__72399]
            (clojure.core/letfn
             [(state__73593
               []
               (clojure.core/let
                [x__73100 "meander.zeta"]
                (if
                 (clojure.core/= ?__72399 x__73100)
                 [?__72399]
                 (state__73594))))
              (state__73594
               []
               (if
                (clojure.core/map? arg__73099)
                (clojure.core/let
                 [VAL__73101 (.valAt arg__73099 :aliases)]
                 (if
                  (clojure.core/map? VAL__73101)
                  (clojure.core/let
                   [X__73103 (clojure.core/set VAL__73101)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__73103))
                    (clojure.core/loop
                     [search_space__73595 (clojure.core/seq X__73103)]
                     (if
                      (clojure.core/seq search_space__73595)
                      (clojure.core/let
                       [elem__73104
                        (clojure.core/first search_space__73595)
                        result__73596
                        (clojure.core/let
                         [elem__73104_nth_0__
                          (clojure.core/nth elem__73104 0)
                          elem__73104_nth_1__
                          (clojure.core/nth elem__73104 1)]
                         (if
                          (clojure.core/symbol? elem__73104_nth_0__)
                          (clojure.core/let
                           [X__73106
                            (clojure.core/name elem__73104_nth_0__)]
                           (if
                            (clojure.core/= ?__72399 X__73106)
                            (if
                             (clojure.core/symbol? elem__73104_nth_1__)
                             (clojure.core/let
                              [X__73108
                               (clojure.core/name elem__73104_nth_1__)]
                              (clojure.core/case
                               X__73108
                               ("meander.zeta")
                               [?__72399]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__73596)
                        (recur (clojure.core/next search_space__73595))
                        result__73596))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__73593)))]
          (if
           (clojure.core/vector? input__72393)
           (if
            (clojure.core/= (clojure.core/count input__72393) 2)
            (clojure.core/let
             [input__72393_nth_0__
              (clojure.core/nth input__72393 0)
              input__72393_nth_1__
              (clojure.core/nth input__72393 1)]
             (if
              (clojure.core/seq? input__72393_nth_0__)
              (clojure.core/let
               [input__72393_nth_0___l__
                (clojure.core/take 1 input__72393_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__72393_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__72393_nth_0___r__
                  (clojure.core/drop 1 input__72393_nth_0__)]
                 (clojure.core/let
                  [input__72393_nth_0___l___nth_0__
                   (clojure.core/nth input__72393_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__72393_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__73086
                     (clojure.core/namespace
                      input__72393_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__72399 X__73086]
                     (clojure.core/let
                      [X__73088
                       (clojure.core/name
                        input__72393_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__73088
                       ("let")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__73076 input__72393_nth_1__ ?__72399)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__73452)
                         (clojure.core/let
                          [[?__72399] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__72393)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__72393)
                             2)
                            (clojure.core/let
                             [input__72393_nth_0__
                              (clojure.core/nth input__72393 0)
                              input__72393_nth_1__
                              (clojure.core/nth input__72393 1)]
                             (if
                              (clojure.core/seq? input__72393_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__72393_nth_0__)
                                3)
                               (clojure.core/let
                                [input__72393_nth_0___nth_0__
                                 (clojure.core/nth
                                  input__72393_nth_0__
                                  0)
                                 input__72393_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__72393_nth_0__
                                  1)
                                 input__72393_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__72393_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?pattern
                                  input__72393_nth_0___nth_0__]
                                 (clojure.core/let
                                  [?expression
                                   input__72393_nth_0___nth_1__]
                                  (clojure.core/let
                                   [?next input__72393_nth_0___nth_2__]
                                   (clojure.core/let
                                    [?form input__72393_nth_0__]
                                    (clojure.core/let
                                     [?env input__72393_nth_1__]
                                     (try
                                      [{:tag :let,
                                        :pattern
                                        (clojure.core/let
                                         [CATA_RESULT__9229__auto__
                                          (CATA__FN__72443
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
                                          (CATA__FN__72443
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
                               (state__73452))
                              (state__73452)))
                            (state__73452))
                           (state__73452)))))
                       (state__73452)))))
                   (state__73452))))
                (state__73452)))
              (state__73452)))
            (state__73452))
           (state__73452))))
        (state__73452
         []
         (clojure.core/letfn
          [(def__73110
            [arg__73133 ?__72400]
            (clojure.core/letfn
             [(state__73598
               []
               (clojure.core/let
                [x__73134 "meander.zeta"]
                (if
                 (clojure.core/= ?__72400 x__73134)
                 [?__72400]
                 (state__73599))))
              (state__73599
               []
               (if
                (clojure.core/map? arg__73133)
                (clojure.core/let
                 [VAL__73135 (.valAt arg__73133 :aliases)]
                 (if
                  (clojure.core/map? VAL__73135)
                  (clojure.core/let
                   [X__73137 (clojure.core/set VAL__73135)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__73137))
                    (clojure.core/loop
                     [search_space__73600 (clojure.core/seq X__73137)]
                     (if
                      (clojure.core/seq search_space__73600)
                      (clojure.core/let
                       [elem__73138
                        (clojure.core/first search_space__73600)
                        result__73601
                        (clojure.core/let
                         [elem__73138_nth_0__
                          (clojure.core/nth elem__73138 0)
                          elem__73138_nth_1__
                          (clojure.core/nth elem__73138 1)]
                         (if
                          (clojure.core/symbol? elem__73138_nth_0__)
                          (clojure.core/let
                           [X__73140
                            (clojure.core/name elem__73138_nth_0__)]
                           (if
                            (clojure.core/= ?__72400 X__73140)
                            (if
                             (clojure.core/symbol? elem__73138_nth_1__)
                             (clojure.core/let
                              [X__73142
                               (clojure.core/name elem__73138_nth_1__)]
                              (clojure.core/case
                               X__73142
                               ("meander.zeta")
                               [?__72400]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__73601)
                        (recur (clojure.core/next search_space__73600))
                        result__73601))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__73598)))]
          (if
           (clojure.core/vector? input__72393)
           (if
            (clojure.core/= (clojure.core/count input__72393) 2)
            (clojure.core/let
             [input__72393_nth_0__
              (clojure.core/nth input__72393 0)
              input__72393_nth_1__
              (clojure.core/nth input__72393 1)]
             (if
              (clojure.core/seq? input__72393_nth_0__)
              (clojure.core/let
               [input__72393_nth_0___l__
                (clojure.core/take 1 input__72393_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__72393_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__72393_nth_0___r__
                  (clojure.core/drop 1 input__72393_nth_0__)]
                 (clojure.core/let
                  [input__72393_nth_0___l___nth_0__
                   (clojure.core/nth input__72393_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__72393_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__73120
                     (clojure.core/namespace
                      input__72393_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__72400 X__73120]
                     (clojure.core/let
                      [X__73122
                       (clojure.core/name
                        input__72393_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__73122
                       ("not")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__73110 input__72393_nth_1__ ?__72400)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__73453)
                         (clojure.core/let
                          [[?__72400] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__72393)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__72393)
                             2)
                            (clojure.core/let
                             [input__72393_nth_0__
                              (clojure.core/nth input__72393 0)
                              input__72393_nth_1__
                              (clojure.core/nth input__72393 1)]
                             (if
                              (clojure.core/seq? input__72393_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__72393_nth_0__)
                                2)
                               (clojure.core/let
                                [input__72393_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__72393_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__72393_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__72393_nth_0__]
                                  (clojure.core/let
                                   [?env input__72393_nth_1__]
                                   (try
                                    [{:tag :not,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__9229__auto__
                                        (CATA__FN__72443
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
                               (state__73453))
                              (state__73453)))
                            (state__73453))
                           (state__73453)))))
                       (state__73453)))))
                   (state__73453))))
                (state__73453)))
              (state__73453)))
            (state__73453))
           (state__73453))))
        (state__73453
         []
         (clojure.core/letfn
          [(def__73144
            [arg__73167 ?__72401]
            (clojure.core/letfn
             [(state__73603
               []
               (clojure.core/let
                [x__73168 "meander.zeta"]
                (if
                 (clojure.core/= ?__72401 x__73168)
                 [?__72401]
                 (state__73604))))
              (state__73604
               []
               (if
                (clojure.core/map? arg__73167)
                (clojure.core/let
                 [VAL__73169 (.valAt arg__73167 :aliases)]
                 (if
                  (clojure.core/map? VAL__73169)
                  (clojure.core/let
                   [X__73171 (clojure.core/set VAL__73169)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__73171))
                    (clojure.core/loop
                     [search_space__73605 (clojure.core/seq X__73171)]
                     (if
                      (clojure.core/seq search_space__73605)
                      (clojure.core/let
                       [elem__73172
                        (clojure.core/first search_space__73605)
                        result__73606
                        (clojure.core/let
                         [elem__73172_nth_0__
                          (clojure.core/nth elem__73172 0)
                          elem__73172_nth_1__
                          (clojure.core/nth elem__73172 1)]
                         (if
                          (clojure.core/symbol? elem__73172_nth_0__)
                          (clojure.core/let
                           [X__73174
                            (clojure.core/name elem__73172_nth_0__)]
                           (if
                            (clojure.core/= ?__72401 X__73174)
                            (if
                             (clojure.core/symbol? elem__73172_nth_1__)
                             (clojure.core/let
                              [X__73176
                               (clojure.core/name elem__73172_nth_1__)]
                              (clojure.core/case
                               X__73176
                               ("meander.zeta")
                               [?__72401]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__73606)
                        (recur (clojure.core/next search_space__73605))
                        result__73606))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__73603)))]
          (if
           (clojure.core/vector? input__72393)
           (if
            (clojure.core/= (clojure.core/count input__72393) 2)
            (clojure.core/let
             [input__72393_nth_0__
              (clojure.core/nth input__72393 0)
              input__72393_nth_1__
              (clojure.core/nth input__72393 1)]
             (if
              (clojure.core/seq? input__72393_nth_0__)
              (clojure.core/let
               [input__72393_nth_0___l__
                (clojure.core/take 1 input__72393_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__72393_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__72393_nth_0___r__
                  (clojure.core/drop 1 input__72393_nth_0__)]
                 (clojure.core/let
                  [input__72393_nth_0___l___nth_0__
                   (clojure.core/nth input__72393_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__72393_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__73154
                     (clojure.core/namespace
                      input__72393_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__72401 X__73154]
                     (clojure.core/let
                      [X__73156
                       (clojure.core/name
                        input__72393_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__73156
                       ("or")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__73144 input__72393_nth_1__ ?__72401)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__73454)
                         (clojure.core/let
                          [[?__72401] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__72393)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__72393)
                             2)
                            (clojure.core/let
                             [input__72393_nth_0__
                              (clojure.core/nth input__72393 0)
                              input__72393_nth_1__
                              (clojure.core/nth input__72393 1)]
                             (if
                              (clojure.core/seq? input__72393_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__72393_nth_0__)
                                3)
                               (clojure.core/let
                                [input__72393_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__72393_nth_0__
                                  1)
                                 input__72393_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__72393_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?left input__72393_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?right input__72393_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__72393_nth_0__]
                                   (clojure.core/let
                                    [?env input__72393_nth_1__]
                                    (try
                                     [{:tag :or,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__72443
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
                                         (CATA__FN__72443
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
                               (state__73454))
                              (state__73454)))
                            (state__73454))
                           (state__73454)))))
                       (state__73454)))))
                   (state__73454))))
                (state__73454)))
              (state__73454)))
            (state__73454))
           (state__73454))))
        (state__73454
         []
         (clojure.core/letfn
          [(def__73178
            [arg__73201 ?__72402]
            (clojure.core/letfn
             [(state__73608
               []
               (clojure.core/let
                [x__73202 "meander.zeta"]
                (if
                 (clojure.core/= ?__72402 x__73202)
                 [?__72402]
                 (state__73609))))
              (state__73609
               []
               (if
                (clojure.core/map? arg__73201)
                (clojure.core/let
                 [VAL__73203 (.valAt arg__73201 :aliases)]
                 (if
                  (clojure.core/map? VAL__73203)
                  (clojure.core/let
                   [X__73205 (clojure.core/set VAL__73203)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__73205))
                    (clojure.core/loop
                     [search_space__73610 (clojure.core/seq X__73205)]
                     (if
                      (clojure.core/seq search_space__73610)
                      (clojure.core/let
                       [elem__73206
                        (clojure.core/first search_space__73610)
                        result__73611
                        (clojure.core/let
                         [elem__73206_nth_0__
                          (clojure.core/nth elem__73206 0)
                          elem__73206_nth_1__
                          (clojure.core/nth elem__73206 1)]
                         (if
                          (clojure.core/symbol? elem__73206_nth_0__)
                          (clojure.core/let
                           [X__73208
                            (clojure.core/name elem__73206_nth_0__)]
                           (if
                            (clojure.core/= ?__72402 X__73208)
                            (if
                             (clojure.core/symbol? elem__73206_nth_1__)
                             (clojure.core/let
                              [X__73210
                               (clojure.core/name elem__73206_nth_1__)]
                              (clojure.core/case
                               X__73210
                               ("meander.zeta")
                               [?__72402]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__73611)
                        (recur (clojure.core/next search_space__73610))
                        result__73611))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__73608)))]
          (if
           (clojure.core/vector? input__72393)
           (if
            (clojure.core/= (clojure.core/count input__72393) 2)
            (clojure.core/let
             [input__72393_nth_0__
              (clojure.core/nth input__72393 0)
              input__72393_nth_1__
              (clojure.core/nth input__72393 1)]
             (if
              (clojure.core/seq? input__72393_nth_0__)
              (clojure.core/let
               [input__72393_nth_0___l__
                (clojure.core/take 1 input__72393_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__72393_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__72393_nth_0___r__
                  (clojure.core/drop 1 input__72393_nth_0__)]
                 (clojure.core/let
                  [input__72393_nth_0___l___nth_0__
                   (clojure.core/nth input__72393_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__72393_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__73188
                     (clojure.core/namespace
                      input__72393_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__72402 X__73188]
                     (clojure.core/let
                      [X__73190
                       (clojure.core/name
                        input__72393_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__73190
                       ("re")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__73178 input__72393_nth_1__ ?__72402)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__73455)
                         (clojure.core/let
                          [[?__72402] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__72393)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__72393)
                             2)
                            (clojure.core/let
                             [input__72393_nth_0__
                              (clojure.core/nth input__72393 0)
                              input__72393_nth_1__
                              (clojure.core/nth input__72393 1)]
                             (if
                              (clojure.core/seq? input__72393_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__72393_nth_0__)
                                2)
                               (clojure.core/let
                                [input__72393_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__72393_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?regex input__72393_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__72393_nth_0__]
                                  (clojure.core/let
                                   [?env input__72393_nth_1__]
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
                               (state__73455))
                              (state__73455)))
                            (state__73455))
                           (state__73455)))))
                       (state__73455)))))
                   (state__73455))))
                (state__73455)))
              (state__73455)))
            (state__73455))
           (state__73455))))
        (state__73455
         []
         (clojure.core/letfn
          [(def__73212
            [arg__73235 ?__72403]
            (clojure.core/letfn
             [(state__73613
               []
               (clojure.core/let
                [x__73236 "meander.zeta"]
                (if
                 (clojure.core/= ?__72403 x__73236)
                 [?__72403]
                 (state__73614))))
              (state__73614
               []
               (if
                (clojure.core/map? arg__73235)
                (clojure.core/let
                 [VAL__73237 (.valAt arg__73235 :aliases)]
                 (if
                  (clojure.core/map? VAL__73237)
                  (clojure.core/let
                   [X__73239 (clojure.core/set VAL__73237)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__73239))
                    (clojure.core/loop
                     [search_space__73615 (clojure.core/seq X__73239)]
                     (if
                      (clojure.core/seq search_space__73615)
                      (clojure.core/let
                       [elem__73240
                        (clojure.core/first search_space__73615)
                        result__73616
                        (clojure.core/let
                         [elem__73240_nth_0__
                          (clojure.core/nth elem__73240 0)
                          elem__73240_nth_1__
                          (clojure.core/nth elem__73240 1)]
                         (if
                          (clojure.core/symbol? elem__73240_nth_0__)
                          (clojure.core/let
                           [X__73242
                            (clojure.core/name elem__73240_nth_0__)]
                           (if
                            (clojure.core/= ?__72403 X__73242)
                            (if
                             (clojure.core/symbol? elem__73240_nth_1__)
                             (clojure.core/let
                              [X__73244
                               (clojure.core/name elem__73240_nth_1__)]
                              (clojure.core/case
                               X__73244
                               ("meander.zeta")
                               [?__72403]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__73616)
                        (recur (clojure.core/next search_space__73615))
                        result__73616))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__73613)))]
          (if
           (clojure.core/vector? input__72393)
           (if
            (clojure.core/= (clojure.core/count input__72393) 2)
            (clojure.core/let
             [input__72393_nth_0__
              (clojure.core/nth input__72393 0)
              input__72393_nth_1__
              (clojure.core/nth input__72393 1)]
             (if
              (clojure.core/seq? input__72393_nth_0__)
              (clojure.core/let
               [input__72393_nth_0___l__
                (clojure.core/take 1 input__72393_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__72393_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__72393_nth_0___r__
                  (clojure.core/drop 1 input__72393_nth_0__)]
                 (clojure.core/let
                  [input__72393_nth_0___l___nth_0__
                   (clojure.core/nth input__72393_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__72393_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__73222
                     (clojure.core/namespace
                      input__72393_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__72403 X__73222]
                     (clojure.core/let
                      [X__73224
                       (clojure.core/name
                        input__72393_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__73224
                       ("re")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__73212 input__72393_nth_1__ ?__72403)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__73456)
                         (clojure.core/let
                          [[?__72403] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__72393)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__72393)
                             2)
                            (clojure.core/let
                             [input__72393_nth_0__
                              (clojure.core/nth input__72393 0)
                              input__72393_nth_1__
                              (clojure.core/nth input__72393 1)]
                             (if
                              (clojure.core/seq? input__72393_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__72393_nth_0__)
                                3)
                               (clojure.core/let
                                [input__72393_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__72393_nth_0__
                                  1)
                                 input__72393_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__72393_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?regex input__72393_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?capture
                                   input__72393_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__72393_nth_0__]
                                   (clojure.core/let
                                    [?env input__72393_nth_1__]
                                    (try
                                     [{:tag :regex,
                                       :regex ?regex,
                                       :capture
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__72443
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
                               (state__73456))
                              (state__73456)))
                            (state__73456))
                           (state__73456)))))
                       (state__73456)))))
                   (state__73456))))
                (state__73456)))
              (state__73456)))
            (state__73456))
           (state__73456))))
        (state__73456
         []
         (clojure.core/letfn
          [(def__73246
            [arg__73269 ?__72404]
            (clojure.core/letfn
             [(state__73618
               []
               (clojure.core/let
                [x__73270 "meander.zeta"]
                (if
                 (clojure.core/= ?__72404 x__73270)
                 [?__72404]
                 (state__73619))))
              (state__73619
               []
               (if
                (clojure.core/map? arg__73269)
                (clojure.core/let
                 [VAL__73271 (.valAt arg__73269 :aliases)]
                 (if
                  (clojure.core/map? VAL__73271)
                  (clojure.core/let
                   [X__73273 (clojure.core/set VAL__73271)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__73273))
                    (clojure.core/loop
                     [search_space__73620 (clojure.core/seq X__73273)]
                     (if
                      (clojure.core/seq search_space__73620)
                      (clojure.core/let
                       [elem__73274
                        (clojure.core/first search_space__73620)
                        result__73621
                        (clojure.core/let
                         [elem__73274_nth_0__
                          (clojure.core/nth elem__73274 0)
                          elem__73274_nth_1__
                          (clojure.core/nth elem__73274 1)]
                         (if
                          (clojure.core/symbol? elem__73274_nth_0__)
                          (clojure.core/let
                           [X__73276
                            (clojure.core/name elem__73274_nth_0__)]
                           (if
                            (clojure.core/= ?__72404 X__73276)
                            (if
                             (clojure.core/symbol? elem__73274_nth_1__)
                             (clojure.core/let
                              [X__73278
                               (clojure.core/name elem__73274_nth_1__)]
                              (clojure.core/case
                               X__73278
                               ("meander.zeta")
                               [?__72404]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__73621)
                        (recur (clojure.core/next search_space__73620))
                        result__73621))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__73618)))]
          (if
           (clojure.core/vector? input__72393)
           (if
            (clojure.core/= (clojure.core/count input__72393) 2)
            (clojure.core/let
             [input__72393_nth_0__
              (clojure.core/nth input__72393 0)
              input__72393_nth_1__
              (clojure.core/nth input__72393 1)]
             (if
              (clojure.core/seq? input__72393_nth_0__)
              (clojure.core/let
               [input__72393_nth_0___l__
                (clojure.core/take 1 input__72393_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__72393_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__72393_nth_0___r__
                  (clojure.core/drop 1 input__72393_nth_0__)]
                 (clojure.core/let
                  [input__72393_nth_0___l___nth_0__
                   (clojure.core/nth input__72393_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__72393_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__73256
                     (clojure.core/namespace
                      input__72393_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__72404 X__73256]
                     (clojure.core/let
                      [X__73258
                       (clojure.core/name
                        input__72393_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__73258
                       ("string")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__73246 input__72393_nth_1__ ?__72404)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__73457)
                         (clojure.core/let
                          [[?__72404] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__72393)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__72393)
                             2)
                            (clojure.core/let
                             [input__72393_nth_0__
                              (clojure.core/nth input__72393 0)
                              input__72393_nth_1__
                              (clojure.core/nth input__72393 1)]
                             (if
                              (clojure.core/seq? input__72393_nth_0__)
                              (clojure.core/let
                               [input__72393_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__72393_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__72393_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__72393_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__72393_nth_0__)]
                                 (clojure.core/let
                                  [?sequence input__72393_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__72393_nth_0__]
                                   (clojure.core/let
                                    [?env input__72393_nth_1__]
                                    (try
                                     [{:tag :string,
                                       :next
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__72443
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
                                (state__73457)))
                              (state__73457)))
                            (state__73457))
                           (state__73457)))))
                       (state__73457)))))
                   (state__73457))))
                (state__73457)))
              (state__73457)))
            (state__73457))
           (state__73457))))
        (state__73457
         []
         (clojure.core/letfn
          [(def__73280
            [arg__73303 ?__72405]
            (clojure.core/letfn
             [(state__73623
               []
               (clojure.core/let
                [x__73304 "meander.zeta"]
                (if
                 (clojure.core/= ?__72405 x__73304)
                 [?__72405]
                 (state__73624))))
              (state__73624
               []
               (if
                (clojure.core/map? arg__73303)
                (clojure.core/let
                 [VAL__73305 (.valAt arg__73303 :aliases)]
                 (if
                  (clojure.core/map? VAL__73305)
                  (clojure.core/let
                   [X__73307 (clojure.core/set VAL__73305)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__73307))
                    (clojure.core/loop
                     [search_space__73625 (clojure.core/seq X__73307)]
                     (if
                      (clojure.core/seq search_space__73625)
                      (clojure.core/let
                       [elem__73308
                        (clojure.core/first search_space__73625)
                        result__73626
                        (clojure.core/let
                         [elem__73308_nth_0__
                          (clojure.core/nth elem__73308 0)
                          elem__73308_nth_1__
                          (clojure.core/nth elem__73308 1)]
                         (if
                          (clojure.core/symbol? elem__73308_nth_0__)
                          (clojure.core/let
                           [X__73310
                            (clojure.core/name elem__73308_nth_0__)]
                           (if
                            (clojure.core/= ?__72405 X__73310)
                            (if
                             (clojure.core/symbol? elem__73308_nth_1__)
                             (clojure.core/let
                              [X__73312
                               (clojure.core/name elem__73308_nth_1__)]
                              (clojure.core/case
                               X__73312
                               ("meander.zeta")
                               [?__72405]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__73626)
                        (recur (clojure.core/next search_space__73625))
                        result__73626))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__73623)))]
          (if
           (clojure.core/vector? input__72393)
           (if
            (clojure.core/= (clojure.core/count input__72393) 2)
            (clojure.core/let
             [input__72393_nth_0__
              (clojure.core/nth input__72393 0)
              input__72393_nth_1__
              (clojure.core/nth input__72393 1)]
             (if
              (clojure.core/seq? input__72393_nth_0__)
              (clojure.core/let
               [input__72393_nth_0___l__
                (clojure.core/take 1 input__72393_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__72393_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__72393_nth_0___r__
                  (clojure.core/drop 1 input__72393_nth_0__)]
                 (clojure.core/let
                  [input__72393_nth_0___l___nth_0__
                   (clojure.core/nth input__72393_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__72393_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__73290
                     (clojure.core/namespace
                      input__72393_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__72405 X__73290]
                     (clojure.core/let
                      [X__73292
                       (clojure.core/name
                        input__72393_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__73292
                       ("symbol")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__73280 input__72393_nth_1__ ?__72405)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__73458)
                         (clojure.core/let
                          [[?__72405] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__72393)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__72393)
                             2)
                            (clojure.core/let
                             [input__72393_nth_0__
                              (clojure.core/nth input__72393 0)
                              input__72393_nth_1__
                              (clojure.core/nth input__72393 1)]
                             (if
                              (clojure.core/seq? input__72393_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__72393_nth_0__)
                                2)
                               (clojure.core/let
                                [input__72393_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__72393_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?name input__72393_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__72393_nth_0__]
                                  (clojure.core/let
                                   [?env input__72393_nth_1__]
                                   (try
                                    [{:tag :symbol,
                                      :name
                                      (clojure.core/let
                                       [CATA_RESULT__9229__auto__
                                        (CATA__FN__72443 [?name ?env])]
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
                               (state__73458))
                              (state__73458)))
                            (state__73458))
                           (state__73458)))))
                       (state__73458)))))
                   (state__73458))))
                (state__73458)))
              (state__73458)))
            (state__73458))
           (state__73458))))
        (state__73458
         []
         (clojure.core/letfn
          [(def__73314
            [arg__73337 ?__72406]
            (clojure.core/letfn
             [(state__73628
               []
               (clojure.core/let
                [x__73338 "meander.zeta"]
                (if
                 (clojure.core/= ?__72406 x__73338)
                 [?__72406]
                 (state__73629))))
              (state__73629
               []
               (if
                (clojure.core/map? arg__73337)
                (clojure.core/let
                 [VAL__73339 (.valAt arg__73337 :aliases)]
                 (if
                  (clojure.core/map? VAL__73339)
                  (clojure.core/let
                   [X__73341 (clojure.core/set VAL__73339)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__73341))
                    (clojure.core/loop
                     [search_space__73630 (clojure.core/seq X__73341)]
                     (if
                      (clojure.core/seq search_space__73630)
                      (clojure.core/let
                       [elem__73342
                        (clojure.core/first search_space__73630)
                        result__73631
                        (clojure.core/let
                         [elem__73342_nth_0__
                          (clojure.core/nth elem__73342 0)
                          elem__73342_nth_1__
                          (clojure.core/nth elem__73342 1)]
                         (if
                          (clojure.core/symbol? elem__73342_nth_0__)
                          (clojure.core/let
                           [X__73344
                            (clojure.core/name elem__73342_nth_0__)]
                           (if
                            (clojure.core/= ?__72406 X__73344)
                            (if
                             (clojure.core/symbol? elem__73342_nth_1__)
                             (clojure.core/let
                              [X__73346
                               (clojure.core/name elem__73342_nth_1__)]
                              (clojure.core/case
                               X__73346
                               ("meander.zeta")
                               [?__72406]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__73631)
                        (recur (clojure.core/next search_space__73630))
                        result__73631))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__73628)))]
          (if
           (clojure.core/vector? input__72393)
           (if
            (clojure.core/= (clojure.core/count input__72393) 2)
            (clojure.core/let
             [input__72393_nth_0__
              (clojure.core/nth input__72393 0)
              input__72393_nth_1__
              (clojure.core/nth input__72393 1)]
             (if
              (clojure.core/seq? input__72393_nth_0__)
              (clojure.core/let
               [input__72393_nth_0___l__
                (clojure.core/take 1 input__72393_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__72393_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__72393_nth_0___r__
                  (clojure.core/drop 1 input__72393_nth_0__)]
                 (clojure.core/let
                  [input__72393_nth_0___l___nth_0__
                   (clojure.core/nth input__72393_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__72393_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__73324
                     (clojure.core/namespace
                      input__72393_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__72406 X__73324]
                     (clojure.core/let
                      [X__73326
                       (clojure.core/name
                        input__72393_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__73326
                       ("symbol")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__73314 input__72393_nth_1__ ?__72406)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__73459)
                         (clojure.core/let
                          [[?__72406] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__72393)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__72393)
                             2)
                            (clojure.core/let
                             [input__72393_nth_0__
                              (clojure.core/nth input__72393 0)
                              input__72393_nth_1__
                              (clojure.core/nth input__72393 1)]
                             (if
                              (clojure.core/seq? input__72393_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__72393_nth_0__)
                                3)
                               (clojure.core/let
                                [input__72393_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__72393_nth_0__
                                  1)
                                 input__72393_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__72393_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?namespace
                                  input__72393_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?name input__72393_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__72393_nth_0__]
                                   (clojure.core/let
                                    [?env input__72393_nth_1__]
                                    (try
                                     [{:tag :symbol,
                                       :name
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__72443
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
                                         (CATA__FN__72443
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
                               (state__73459))
                              (state__73459)))
                            (state__73459))
                           (state__73459)))))
                       (state__73459)))))
                   (state__73459))))
                (state__73459)))
              (state__73459)))
            (state__73459))
           (state__73459))))
        (state__73459
         []
         (clojure.core/letfn
          [(def__73348
            [arg__73371 ?__72407]
            (clojure.core/letfn
             [(state__73633
               []
               (clojure.core/let
                [x__73372 "meander.zeta"]
                (if
                 (clojure.core/= ?__72407 x__73372)
                 [?__72407]
                 (state__73634))))
              (state__73634
               []
               (if
                (clojure.core/map? arg__73371)
                (clojure.core/let
                 [VAL__73373 (.valAt arg__73371 :aliases)]
                 (if
                  (clojure.core/map? VAL__73373)
                  (clojure.core/let
                   [X__73375 (clojure.core/set VAL__73373)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__73375))
                    (clojure.core/loop
                     [search_space__73635 (clojure.core/seq X__73375)]
                     (if
                      (clojure.core/seq search_space__73635)
                      (clojure.core/let
                       [elem__73376
                        (clojure.core/first search_space__73635)
                        result__73636
                        (clojure.core/let
                         [elem__73376_nth_0__
                          (clojure.core/nth elem__73376 0)
                          elem__73376_nth_1__
                          (clojure.core/nth elem__73376 1)]
                         (if
                          (clojure.core/symbol? elem__73376_nth_0__)
                          (clojure.core/let
                           [X__73378
                            (clojure.core/name elem__73376_nth_0__)]
                           (if
                            (clojure.core/= ?__72407 X__73378)
                            (if
                             (clojure.core/symbol? elem__73376_nth_1__)
                             (clojure.core/let
                              [X__73380
                               (clojure.core/name elem__73376_nth_1__)]
                              (clojure.core/case
                               X__73380
                               ("meander.zeta")
                               [?__72407]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__73636)
                        (recur (clojure.core/next search_space__73635))
                        result__73636))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__73633)))]
          (if
           (clojure.core/vector? input__72393)
           (if
            (clojure.core/= (clojure.core/count input__72393) 2)
            (clojure.core/let
             [input__72393_nth_0__
              (clojure.core/nth input__72393 0)
              input__72393_nth_1__
              (clojure.core/nth input__72393 1)]
             (if
              (clojure.core/seq? input__72393_nth_0__)
              (clojure.core/let
               [input__72393_nth_0___l__
                (clojure.core/take 1 input__72393_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__72393_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__72393_nth_0___r__
                  (clojure.core/drop 1 input__72393_nth_0__)]
                 (clojure.core/let
                  [input__72393_nth_0___l___nth_0__
                   (clojure.core/nth input__72393_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__72393_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__73358
                     (clojure.core/namespace
                      input__72393_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__72407 X__73358]
                     (clojure.core/let
                      [X__73360
                       (clojure.core/name
                        input__72393_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__73360
                       ("symbol")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__73348 input__72393_nth_1__ ?__72407)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__73460)
                         (clojure.core/let
                          [[?__72407] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__72393)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__72393)
                             2)
                            (clojure.core/let
                             [input__72393_nth_0__
                              (clojure.core/nth input__72393 0)
                              input__72393_nth_1__
                              (clojure.core/nth input__72393 1)]
                             (if
                              (clojure.core/seq? input__72393_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 5)
                                 input__72393_nth_0__)
                                5)
                               (clojure.core/let
                                [input__72393_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__72393_nth_0__
                                  1)
                                 input__72393_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__72393_nth_0__
                                  2)
                                 input__72393_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__72393_nth_0__
                                  3)
                                 input__72393_nth_0___nth_4__
                                 (clojure.core/nth
                                  input__72393_nth_0__
                                  4)]
                                (clojure.core/case
                                 input__72393_nth_0___nth_3__
                                 (:meander.zeta/as)
                                 (clojure.core/let
                                  [?namespace
                                   input__72393_nth_0___nth_1__]
                                  (clojure.core/let
                                   [?name input__72393_nth_0___nth_2__]
                                   (clojure.core/let
                                    [?pattern
                                     input__72393_nth_0___nth_4__]
                                    (clojure.core/let
                                     [?form input__72393_nth_0__]
                                     (clojure.core/let
                                      [?env input__72393_nth_1__]
                                      (try
                                       [{:tag :symbol,
                                         :name
                                         (clojure.core/let
                                          [CATA_RESULT__9229__auto__
                                           (CATA__FN__72443
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
                                           (CATA__FN__72443
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
                                           (CATA__FN__72443
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
                                 (state__73460)))
                               (state__73460))
                              (state__73460)))
                            (state__73460))
                           (state__73460)))))
                       (state__73460)))))
                   (state__73460))))
                (state__73460)))
              (state__73460)))
            (state__73460))
           (state__73460))))
        (state__73460
         []
         (if
          (clojure.core/vector? input__72393)
          (if
           (clojure.core/= (clojure.core/count input__72393) 2)
           (clojure.core/let
            [input__72393_nth_0__ (clojure.core/nth input__72393 0)]
            (clojure.core/letfn
             [(state__73638
               []
               (clojure.core/let
                [input__72393_nth_1__
                 (clojure.core/nth input__72393 1)]
                (clojure.core/letfn
                 [(state__73643
                   []
                   (if
                    (clojure.core/seq? input__72393_nth_0__)
                    (clojure.core/let
                     [?sequence input__72393_nth_0__]
                     (clojure.core/let
                      [?env input__72393_nth_1__]
                      (try
                       [{:tag :seq,
                         :next
                         (clojure.core/let
                          [CATA_RESULT__9229__auto__
                           (CATA__FN__72443
                            ['meander.dev.parse.zeta/parse-sequential
                             (clojure.core/into [] ?sequence)
                             (clojure.core/let
                              [form__9328__auto__ {:context :seq}]
                              (clojure.core/merge
                               (clojure.core/into {} ?env)
                               form__9328__auto__))])]
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
                    (state__73644)))
                  (state__73644
                   []
                   (if
                    (clojure.core/map? input__72393_nth_0__)
                    (clojure.core/let
                     [?map input__72393_nth_0__]
                     (clojure.core/let
                      [?env input__72393_nth_1__]
                      (try
                       [{:tag :map,
                         :next
                         (clojure.core/let
                          [CATA_RESULT__9229__auto__
                           (CATA__FN__72443
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
                    (state__73639)))]
                 (state__73643))))
              (state__73639
               []
               (if
                (clojure.core/symbol? input__72393_nth_0__)
                (clojure.core/let
                 [X__73390
                  (clojure.core/namespace input__72393_nth_0__)]
                 (clojure.core/let
                  [X__73392 (clojure.core/name input__72393_nth_0__)]
                  (if
                   (clojure.core/string? X__73392)
                   (clojure.core/letfn
                    [(state__73645
                      []
                      (clojure.core/let
                       [ret__73393
                        (clojure.core/re-matches #"_.*" X__73392)]
                       (if
                        (clojure.core/some? ret__73393)
                        (clojure.core/let
                         [?name ret__73393]
                         (clojure.core/let
                          [?symbol input__72393_nth_0__]
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
                        (state__73646))))
                     (state__73646
                      []
                      (clojure.core/let
                       [ret__73400
                        (clojure.core/re-matches #".+#" X__73392)]
                       (if
                        (clojure.core/some? ret__73400)
                        (clojure.core/let
                         [?name ret__73400]
                         (clojure.core/let
                          [?symbol input__72393_nth_0__]
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
                        (state__73647))))
                     (state__73647
                      []
                      (clojure.core/let
                       [ret__73407
                        (clojure.core/re-matches #"%.+" X__73392)]
                       (if
                        (clojure.core/some? ret__73407)
                        (clojure.core/let
                         [?name ret__73407]
                         (clojure.core/let
                          [?symbol input__72393_nth_0__]
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
                        (state__73648))))
                     (state__73648
                      []
                      (clojure.core/let
                       [ret__73414
                        (clojure.core/re-matches #"\*.+" X__73392)]
                       (if
                        (clojure.core/some? ret__73414)
                        (clojure.core/let
                         [?name ret__73414]
                         (clojure.core/let
                          [?symbol input__72393_nth_0__]
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
                        (state__73649))))
                     (state__73649
                      []
                      (clojure.core/let
                       [ret__73421
                        (clojure.core/re-matches #"\!.+" X__73392)]
                       (if
                        (clojure.core/some? ret__73421)
                        (clojure.core/let
                         [?name ret__73421]
                         (clojure.core/let
                          [?symbol input__72393_nth_0__]
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
                        (state__73650))))
                     (state__73650
                      []
                      (clojure.core/let
                       [ret__73428
                        (clojure.core/re-matches #"\?.+" X__73392)]
                       (if
                        (clojure.core/some? ret__73428)
                        (clojure.core/let
                         [?name ret__73428]
                         (clojure.core/let
                          [?symbol input__72393_nth_0__]
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
                        (state__73640))))]
                    (state__73645))
                   (state__73640))))
                (state__73640)))
              (state__73640
               []
               (if
                (string? input__72393_nth_0__)
                (clojure.core/let
                 [?x input__72393_nth_0__]
                 (try
                  [{:tag :literal, :type :string, :form ?x}]
                  (catch
                   java.lang.Exception
                   e__10169__auto__
                   (if
                    (meander.runtime.zeta/fail? e__10169__auto__)
                    (meander.runtime.zeta/fail)
                    (throw e__10169__auto__)))))
                (state__73641)))
              (state__73641
               []
               (if
                (char? input__72393_nth_0__)
                (clojure.core/let
                 [?x input__72393_nth_0__]
                 (try
                  [{:tag :literal, :type :char, :form ?x}]
                  (catch
                   java.lang.Exception
                   e__10169__auto__
                   (if
                    (meander.runtime.zeta/fail? e__10169__auto__)
                    (meander.runtime.zeta/fail)
                    (throw e__10169__auto__)))))
                (state__73642)))
              (state__73642
               []
               (clojure.core/let
                [?x input__72393_nth_0__]
                (try
                 [{:tag :literal, :form ?x}]
                 (catch
                  java.lang.Exception
                  e__10169__auto__
                  (if
                   (meander.runtime.zeta/fail? e__10169__auto__)
                   (meander.runtime.zeta/fail)
                   (throw e__10169__auto__))))))]
             (state__73638)))
           (state__73461))
          (state__73461)))
        (state__73461
         []
         (clojure.core/let
          [?x input__72393]
          (try
           [{:tag :mistake, :x ?x}]
           (catch
            java.lang.Exception
            e__10169__auto__
            (if
             (meander.runtime.zeta/fail? e__10169__auto__)
             (meander.runtime.zeta/fail)
             (throw e__10169__auto__))))))]
       (state__73439)))]
    (clojure.core/let
     [x__7926__auto__ (CATA__FN__72443 input__72393)]
     (if
      (meander.runtime.zeta/fail? x__7926__auto__)
      (meander.runtime.zeta/fail)
      (clojure.core/let
       [[CATA_RETURN__72445] x__7926__auto__]
       CATA_RETURN__72445))))]
  (if
   (meander.runtime.zeta/fail? ret__8106__auto__)
   nil
   ret__8106__auto__)))
