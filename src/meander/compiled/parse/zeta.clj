(ns meander.compiled.parse.zeta (:require [meander.runtime.zeta]))
(clojure.core/defn
 parse
 [input__105355]
 (let*
  [ret__8106__auto__
   (clojure.core/letfn
    [(CATA__FN__105414
      [input__105355]
      (clojure.core/letfn
       [(state__106526
         []
         (if
          (clojure.core/vector? input__105355)
          (if
           (clojure.core/= (clojure.core/count input__105355) 3)
           (clojure.core/let
            [input__105355_nth_0__
             (clojure.core/nth input__105355 0)
             input__105355_nth_1__
             (clojure.core/nth input__105355 1)
             input__105355_nth_2__
             (clojure.core/nth input__105355 2)]
            (clojure.core/case
             input__105355_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__105355_nth_1__)
              (clojure.core/letfn
               [(state__106556
                 []
                 (clojure.core/case
                  input__105355_nth_1__
                  ([])
                  (clojure.core/let
                   [?env input__105355_nth_2__]
                   (try
                    [{:tag :empty}]
                    (catch
                     java.lang.Exception
                     e__10169__auto__
                     (if
                      (meander.runtime.zeta/fail? e__10169__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__10169__auto__)))))
                  (state__106557)))
                (state__106557
                 []
                 (clojure.core/let
                  [n__105421
                   (clojure.core/count input__105355_nth_1__)
                   m__105422
                   (clojure.core/max 0 (clojure.core/- n__105421 2))
                   input__105355_nth_1___l__
                   (clojure.core/subvec
                    input__105355_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__105355_nth_1__)
                     m__105422))
                   input__105355_nth_1___r__
                   (clojure.core/subvec
                    input__105355_nth_1__
                    m__105422)]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__105355_nth_1___r__)
                    2)
                   (clojure.core/let
                    [!xs (clojure.core/vec input__105355_nth_1___l__)]
                    (if
                     (clojure.core/=
                      (clojure.core/count input__105355_nth_1___r__)
                      2)
                     (clojure.core/let
                      [input__105355_nth_1___r___nth_0__
                       (clojure.core/nth input__105355_nth_1___r__ 0)
                       input__105355_nth_1___r___nth_1__
                       (clojure.core/nth input__105355_nth_1___r__ 1)]
                      (clojure.core/case
                       input__105355_nth_1___r___nth_0__
                       (:meander.zeta/as)
                       (clojure.core/let
                        [?pattern input__105355_nth_1___r___nth_1__]
                        (clojure.core/let
                         [?env input__105355_nth_2__]
                         (try
                          [(clojure.core/let
                            [!xs__counter
                             (meander.runtime.zeta/iterator !xs)]
                            {:tag :as,
                             :pattern
                             (clojure.core/let
                              [CATA_RESULT__9229__auto__
                               (CATA__FN__105414 [?pattern ?env])]
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
                               (CATA__FN__105414
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
                       (state__106527)))
                     (state__106527)))
                   (state__106527))))]
               (state__106556))
              (state__106527))
             (state__106527)))
           (state__106527))
          (state__106527)))
        (state__106527
         []
         (clojure.core/letfn
          [(def__105427
            [arg__105462 ?ns]
            (clojure.core/letfn
             [(state__106558
               []
               (clojure.core/let
                [x__105463 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__105463)
                 (clojure.core/let [?env arg__105462] [?env ?ns])
                 (state__106559))))
              (state__106559
               []
               (if
                (clojure.core/map? arg__105462)
                (clojure.core/let
                 [VAL__105464 (.valAt arg__105462 :aliases)]
                 (if
                  (clojure.core/map? VAL__105464)
                  (clojure.core/let
                   [X__105466 (clojure.core/set VAL__105464)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__105466))
                    (clojure.core/loop
                     [search_space__106560
                      (clojure.core/seq X__105466)]
                     (if
                      (clojure.core/seq search_space__106560)
                      (clojure.core/let
                       [elem__105467
                        (clojure.core/first search_space__106560)
                        result__106561
                        (clojure.core/let
                         [elem__105467_nth_0__
                          (clojure.core/nth elem__105467 0)
                          elem__105467_nth_1__
                          (clojure.core/nth elem__105467 1)]
                         (if
                          (clojure.core/symbol? elem__105467_nth_0__)
                          (clojure.core/let
                           [X__105469
                            (clojure.core/name elem__105467_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__105469)
                            (if
                             (clojure.core/symbol?
                              elem__105467_nth_1__)
                             (clojure.core/let
                              [X__105471
                               (clojure.core/name
                                elem__105467_nth_1__)]
                              (clojure.core/case
                               X__105471
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__105462]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__106561)
                        (recur
                         (clojure.core/next search_space__106560))
                        result__106561))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__106558)))]
          (if
           (clojure.core/vector? input__105355)
           (if
            (clojure.core/= (clojure.core/count input__105355) 3)
            (clojure.core/let
             [input__105355_nth_0__
              (clojure.core/nth input__105355 0)
              input__105355_nth_1__
              (clojure.core/nth input__105355 1)
              input__105355_nth_2__
              (clojure.core/nth input__105355 2)]
             (clojure.core/case
              input__105355_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__105355_nth_1__)
               (clojure.core/loop
                [search_space__106563
                 (meander.match.runtime.epsilon/partitions
                  2
                  input__105355_nth_1__)]
                (if
                 (clojure.core/seq search_space__106563)
                 (clojure.core/let
                  [input__105355_nth_1___parts__
                   (clojure.core/first search_space__106563)
                   result__106564
                   (clojure.core/let
                    [input__105355_nth_1___l__
                     (clojure.core/nth input__105355_nth_1___parts__ 0)
                     input__105355_nth_1___r__
                     (clojure.core/nth
                      input__105355_nth_1___parts__
                      1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__105355_nth_1___l__)]
                     (clojure.core/let
                      [input__105355_nth_1___r___l__
                       (clojure.core/subvec
                        input__105355_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__105355_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__105355_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__105355_nth_1___r___r__
                         (clojure.core/subvec
                          input__105355_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__105355_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__105355_nth_1___r___l__
                           0)
                          input__105355_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__105355_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__105355_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__105436
                            (clojure.core/namespace
                             input__105355_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__105436]
                            (clojure.core/let
                             [X__105438
                              (clojure.core/name
                               input__105355_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__105438)
                              (clojure.core/let
                               [ret__105439
                                (clojure.core/re-matches
                                 #"&(\d+)"
                                 X__105438)]
                               (if
                                (clojure.core/some? ret__105439)
                                (if
                                 (clojure.core/vector? ret__105439)
                                 (if
                                  (clojure.core/=
                                   (clojure.core/count ret__105439)
                                   2)
                                  (clojure.core/let
                                   [ret__105439_nth_1__
                                    (clojure.core/nth ret__105439 1)]
                                   (clojure.core/let
                                    [?n ret__105439_nth_1__]
                                    (clojure.core/let
                                     [?pattern
                                      input__105355_nth_1___r___l___nth_1__]
                                     (clojure.core/let
                                      [?rest
                                       input__105355_nth_1___r___r__]
                                      (clojure.core/let
                                       [x__7926__auto__
                                        (def__105427
                                         input__105355_nth_2__
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
                                              (CATA__FN__105414
                                               ['meander.dev.parse.zeta/make-join
                                                (clojure.core/let
                                                 [CATA_RESULT__9229__auto__
                                                  (CATA__FN__105414
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
                                                  (CATA__FN__105414
                                                   ['meander.dev.parse.zeta/make-join
                                                    {:tag :slice,
                                                     :size
                                                     (Integer. ?n),
                                                     :pattern
                                                     (clojure.core/let
                                                      [CATA_RESULT__9229__auto__
                                                       (CATA__FN__105414
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
                                                      (CATA__FN__105414
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
                   (meander.runtime.zeta/fail? result__106564)
                   (recur (clojure.core/next search_space__106563))
                   result__106564))
                 (state__106528)))
               (state__106528))
              (state__106528)))
            (state__106528))
           (state__106528))))
        (state__106528
         []
         (clojure.core/letfn
          [(def__105484
            [arg__105516 ?ns]
            (clojure.core/letfn
             [(state__106566
               []
               (clojure.core/let
                [x__105517 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__105517)
                 (clojure.core/let [?env arg__105516] [?env ?ns])
                 (state__106567))))
              (state__106567
               []
               (if
                (clojure.core/map? arg__105516)
                (clojure.core/let
                 [VAL__105518 (.valAt arg__105516 :aliases)]
                 (if
                  (clojure.core/map? VAL__105518)
                  (clojure.core/let
                   [X__105520 (clojure.core/set VAL__105518)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__105520))
                    (clojure.core/loop
                     [search_space__106568
                      (clojure.core/seq X__105520)]
                     (if
                      (clojure.core/seq search_space__106568)
                      (clojure.core/let
                       [elem__105521
                        (clojure.core/first search_space__106568)
                        result__106569
                        (clojure.core/let
                         [elem__105521_nth_0__
                          (clojure.core/nth elem__105521 0)
                          elem__105521_nth_1__
                          (clojure.core/nth elem__105521 1)]
                         (if
                          (clojure.core/symbol? elem__105521_nth_0__)
                          (clojure.core/let
                           [X__105523
                            (clojure.core/name elem__105521_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__105523)
                            (if
                             (clojure.core/symbol?
                              elem__105521_nth_1__)
                             (clojure.core/let
                              [X__105525
                               (clojure.core/name
                                elem__105521_nth_1__)]
                              (clojure.core/case
                               X__105525
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__105516]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__106569)
                        (recur
                         (clojure.core/next search_space__106568))
                        result__106569))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__106566)))]
          (if
           (clojure.core/vector? input__105355)
           (if
            (clojure.core/= (clojure.core/count input__105355) 3)
            (clojure.core/let
             [input__105355_nth_0__
              (clojure.core/nth input__105355 0)
              input__105355_nth_1__
              (clojure.core/nth input__105355 1)
              input__105355_nth_2__
              (clojure.core/nth input__105355 2)]
             (clojure.core/case
              input__105355_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__105355_nth_1__)
               (clojure.core/loop
                [search_space__106571
                 (meander.match.runtime.epsilon/partitions
                  2
                  input__105355_nth_1__)]
                (if
                 (clojure.core/seq search_space__106571)
                 (clojure.core/let
                  [input__105355_nth_1___parts__
                   (clojure.core/first search_space__106571)
                   result__106572
                   (clojure.core/let
                    [input__105355_nth_1___l__
                     (clojure.core/nth input__105355_nth_1___parts__ 0)
                     input__105355_nth_1___r__
                     (clojure.core/nth
                      input__105355_nth_1___parts__
                      1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__105355_nth_1___l__)]
                     (clojure.core/let
                      [input__105355_nth_1___r___l__
                       (clojure.core/subvec
                        input__105355_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__105355_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__105355_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__105355_nth_1___r___r__
                         (clojure.core/subvec
                          input__105355_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__105355_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__105355_nth_1___r___l__
                           0)
                          input__105355_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__105355_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__105355_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__105493
                            (clojure.core/namespace
                             input__105355_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__105493]
                            (clojure.core/let
                             [X__105495
                              (clojure.core/name
                               input__105355_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__105495)
                              (if
                               (clojure.core/re-matches
                                #"&.*"
                                X__105495)
                               (clojure.core/let
                                [?pattern
                                 input__105355_nth_1___r___l___nth_1__]
                                (clojure.core/let
                                 [?rest input__105355_nth_1___r___r__]
                                 (clojure.core/let
                                  [x__7926__auto__
                                   (def__105484
                                    input__105355_nth_2__
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
                                         (CATA__FN__105414
                                          ['meander.dev.parse.zeta/make-join
                                           (clojure.core/let
                                            [CATA_RESULT__9229__auto__
                                             (CATA__FN__105414
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
                                             (CATA__FN__105414
                                              ['meander.dev.parse.zeta/make-join
                                               (clojure.core/let
                                                [CATA_RESULT__9229__auto__
                                                 (CATA__FN__105414
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
                                                 (CATA__FN__105414
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
                   (meander.runtime.zeta/fail? result__106572)
                   (recur (clojure.core/next search_space__106571))
                   result__106572))
                 (state__106529)))
               (state__106529))
              (state__106529)))
            (state__106529))
           (state__106529))))
        (state__106529
         []
         (if
          (clojure.core/vector? input__105355)
          (clojure.core/letfn
           [(state__106574
             []
             (if
              (clojure.core/= (clojure.core/count input__105355) 3)
              (clojure.core/let
               [input__105355_nth_0__
                (clojure.core/nth input__105355 0)
                input__105355_nth_1__
                (clojure.core/nth input__105355 1)
                input__105355_nth_2__
                (clojure.core/nth input__105355 2)]
               (clojure.core/case
                input__105355_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__105355_nth_1__)
                 (clojure.core/letfn
                  [(state__106577
                    []
                    (clojure.core/let
                     [n__105546
                      (clojure.core/count input__105355_nth_1__)
                      m__105547
                      (clojure.core/max 0 (clojure.core/- n__105546 2))
                      input__105355_nth_1___l__
                      (clojure.core/subvec
                       input__105355_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__105355_nth_1__)
                        m__105547))
                      input__105355_nth_1___r__
                      (clojure.core/subvec
                       input__105355_nth_1__
                       m__105547)]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__105355_nth_1___r__)
                       2)
                      (clojure.core/let
                       [!xs
                        (clojure.core/vec input__105355_nth_1___l__)]
                       (if
                        (clojure.core/=
                         (clojure.core/count input__105355_nth_1___r__)
                         2)
                        (clojure.core/let
                         [input__105355_nth_1___r___nth_0__
                          (clojure.core/nth
                           input__105355_nth_1___r__
                           0)
                          input__105355_nth_1___r___nth_1__
                          (clojure.core/nth
                           input__105355_nth_1___r__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__105355_nth_1___r___nth_0__)
                          (clojure.core/let
                           [X__105551
                            (clojure.core/namespace
                             input__105355_nth_1___r___nth_0__)]
                           (clojure.core/let
                            [?ns X__105551]
                            (clojure.core/let
                             [X__105553
                              (clojure.core/name
                               input__105355_nth_1___r___nth_0__)]
                             (if
                              (clojure.core/string? X__105553)
                              (clojure.core/let
                               [ret__105554
                                (clojure.core/re-matches
                                 #"&.*"
                                 X__105553)]
                               (if
                                (clojure.core/some? ret__105554)
                                (clojure.core/let
                                 [?name ret__105554]
                                 (clojure.core/let
                                  [?pattern
                                   input__105355_nth_1___r___nth_1__]
                                  (if
                                   (clojure.core/map?
                                    input__105355_nth_2__)
                                   (clojure.core/let
                                    [VAL__105538
                                     (.valAt
                                      input__105355_nth_2__
                                      :aliases)]
                                    (if
                                     (clojure.core/map? VAL__105538)
                                     (clojure.core/let
                                      [X__105540
                                       (clojure.core/set VAL__105538)]
                                      (if
                                       (clojure.core/<=
                                        1
                                        (clojure.core/count X__105540))
                                       (clojure.core/loop
                                        [search_space__106581
                                         (clojure.core/seq X__105540)]
                                        (if
                                         (clojure.core/seq
                                          search_space__106581)
                                         (clojure.core/let
                                          [elem__105541
                                           (clojure.core/first
                                            search_space__106581)
                                           result__106582
                                           (clojure.core/let
                                            [elem__105541_nth_0__
                                             (clojure.core/nth
                                              elem__105541
                                              0)
                                             elem__105541_nth_1__
                                             (clojure.core/nth
                                              elem__105541
                                              1)]
                                            (if
                                             (clojure.core/symbol?
                                              elem__105541_nth_0__)
                                             (clojure.core/let
                                              [X__105543
                                               (clojure.core/name
                                                elem__105541_nth_0__)]
                                              (if
                                               (clojure.core/=
                                                ?ns
                                                X__105543)
                                               (if
                                                (clojure.core/symbol?
                                                 elem__105541_nth_1__)
                                                (clojure.core/let
                                                 [X__105545
                                                  (clojure.core/name
                                                   elem__105541_nth_1__)]
                                                 (clojure.core/case
                                                  X__105545
                                                  ("meander.zeta")
                                                  (clojure.core/let
                                                   [?env
                                                    input__105355_nth_2__]
                                                   (try
                                                    [(clojure.core/let
                                                      [!xs__counter
                                                       (meander.runtime.zeta/iterator
                                                        !xs)]
                                                      (clojure.core/let
                                                       [CATA_RESULT__9229__auto__
                                                        (CATA__FN__105414
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
                                            result__106582)
                                           (recur
                                            (clojure.core/next
                                             search_space__106581))
                                           result__106582))
                                         (state__106578)))
                                       (state__106578)))
                                     (state__106578)))
                                   (state__106578))))
                                (state__106578)))
                              (state__106578)))))
                          (state__106578)))
                        (state__106578)))
                      (state__106578))))
                   (state__106578
                    []
                    (clojure.core/loop
                     [search_space__106584
                      (meander.match.runtime.epsilon/partitions
                       2
                       input__105355_nth_1__)]
                     (if
                      (clojure.core/seq search_space__106584)
                      (clojure.core/let
                       [input__105355_nth_1___parts__
                        (clojure.core/first search_space__106584)
                        result__106585
                        (clojure.core/let
                         [input__105355_nth_1___l__
                          (clojure.core/nth
                           input__105355_nth_1___parts__
                           0)
                          input__105355_nth_1___r__
                          (clojure.core/nth
                           input__105355_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs
                           (clojure.core/vec
                            input__105355_nth_1___l__)]
                          (clojure.core/let
                           [input__105355_nth_1___r___l__
                            (clojure.core/subvec
                             input__105355_nth_1___r__
                             0
                             (clojure.core/min
                              (clojure.core/count
                               input__105355_nth_1___r__)
                              1))]
                           (if
                            (clojure.core/=
                             (clojure.core/count
                              input__105355_nth_1___r___l__)
                             1)
                            (clojure.core/let
                             [input__105355_nth_1___r___r__
                              (clojure.core/subvec
                               input__105355_nth_1___r__
                               1)]
                             (if
                              (clojure.core/=
                               input__105355_nth_1___r___l__
                               ['.])
                              (clojure.core/let
                               [?rest input__105355_nth_1___r___r__]
                               (clojure.core/let
                                [?env input__105355_nth_2__]
                                (try
                                 [(clojure.core/let
                                   [!xs__counter
                                    (meander.runtime.zeta/iterator
                                     !xs)]
                                   (clojure.core/let
                                    [CATA_RESULT__9229__auto__
                                     (CATA__FN__105414
                                      ['meander.dev.parse.zeta/make-join
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__105414
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
                                         (CATA__FN__105414
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
                        (meander.runtime.zeta/fail? result__106585)
                        (recur
                         (clojure.core/next search_space__106584))
                        result__106585))
                      (state__106579))))
                   (state__106579
                    []
                    (clojure.core/let
                     [input__105355_nth_1___l__
                      (clojure.core/subvec
                       input__105355_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__105355_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__105355_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__105355_nth_1___r__
                        (clojure.core/subvec input__105355_nth_1__ 1)]
                       (if
                        (clojure.core/=
                         input__105355_nth_1___l__
                         ['...])
                        (clojure.core/let
                         [?rest input__105355_nth_1___r__]
                         (clojure.core/let
                          [?env input__105355_nth_2__]
                          (try
                           [(clojure.core/let
                             [CATA_RESULT__9229__auto__
                              (CATA__FN__105414
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
                        (state__106580)))
                      (state__106580))))
                   (state__106580
                    []
                    (clojure.core/loop
                     [search_space__106587
                      (meander.match.runtime.epsilon/partitions
                       2
                       input__105355_nth_1__)]
                     (if
                      (clojure.core/seq search_space__106587)
                      (clojure.core/let
                       [input__105355_nth_1___parts__
                        (clojure.core/first search_space__106587)
                        result__106588
                        (clojure.core/let
                         [input__105355_nth_1___l__
                          (clojure.core/nth
                           input__105355_nth_1___parts__
                           0)
                          input__105355_nth_1___r__
                          (clojure.core/nth
                           input__105355_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs []]
                          (clojure.core/let
                           [ret__8090__auto__
                            (meander.runtime.zeta/epsilon-run-star-1
                             input__105355_nth_1___l__
                             [!xs]
                             (clojure.core/fn
                              [[!xs] input__105571]
                              (clojure.core/let
                               [input__105571_nth_0__
                                (clojure.core/nth input__105571 0)]
                               (clojure.core/letfn
                                [(save__105572
                                  []
                                  (meander.runtime.zeta/fail))
                                 (f__106591
                                  []
                                  (clojure.core/let
                                   [!xs
                                    (clojure.core/conj
                                     !xs
                                     input__105571_nth_0__)]
                                   [!xs]))]
                                (if
                                 (clojure.core/symbol?
                                  input__105571_nth_0__)
                                 (clojure.core/let
                                  [X__105574
                                   (clojure.core/namespace
                                    input__105571_nth_0__)]
                                  (clojure.core/case
                                   X__105574
                                   (nil)
                                   (clojure.core/let
                                    [X__105576
                                     (clojure.core/name
                                      input__105571_nth_0__)]
                                    (if
                                     (clojure.core/string? X__105576)
                                     (if
                                      (clojure.core/re-matches
                                       #"\.\.(?:\.|\d+)"
                                       X__105576)
                                      (save__105572)
                                      (f__106591))
                                     (f__106591)))
                                   (f__106591)))
                                 (f__106591)))))
                             (clojure.core/fn
                              [[!xs]]
                              (clojure.core/let
                               [input__105355_nth_1___r___l__
                                (clojure.core/subvec
                                 input__105355_nth_1___r__
                                 0
                                 (clojure.core/min
                                  (clojure.core/count
                                   input__105355_nth_1___r__)
                                  1))]
                               (if
                                (clojure.core/=
                                 (clojure.core/count
                                  input__105355_nth_1___r___l__)
                                 1)
                                (clojure.core/let
                                 [input__105355_nth_1___r___r__
                                  (clojure.core/subvec
                                   input__105355_nth_1___r__
                                   1)]
                                 (if
                                  (clojure.core/=
                                   input__105355_nth_1___r___l__
                                   ['...])
                                  (clojure.core/let
                                   [?rest
                                    input__105355_nth_1___r___r__]
                                   (clojure.core/let
                                    [?env input__105355_nth_2__]
                                    (try
                                     [(clojure.core/let
                                       [!xs__counter
                                        (meander.runtime.zeta/iterator
                                         !xs)]
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__105414
                                          ['meander.dev.parse.zeta/make-star
                                           (clojure.core/let
                                            [CATA_RESULT__9229__auto__
                                             (CATA__FN__105414
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
                                             (CATA__FN__105414
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
                        (meander.runtime.zeta/fail? result__106588)
                        (recur
                         (clojure.core/next search_space__106587))
                        result__106588))
                      (state__106575))))]
                  (state__106577))
                 (state__106575))
                (state__106575)))
              (state__106575)))
            (state__106575
             []
             (if
              (clojure.core/= (clojure.core/count input__105355) 4)
              (clojure.core/let
               [input__105355_nth_0__
                (clojure.core/nth input__105355 0)
                input__105355_nth_1__
                (clojure.core/nth input__105355 1)
                input__105355_nth_2__
                (clojure.core/nth input__105355 2)]
               (clojure.core/letfn
                [(state__106592
                  []
                  (clojure.core/let
                   [input__105355_nth_3__
                    (clojure.core/nth input__105355 3)]
                   (clojure.core/case
                    input__105355_nth_0__
                    (meander.dev.parse.zeta/make-star)
                    (clojure.core/letfn
                     [(state__106594
                       []
                       (if
                        (clojure.core/map? input__105355_nth_1__)
                        (clojure.core/let
                         [VAL__105580
                          (.valAt input__105355_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__105580
                          (:cat)
                          (clojure.core/let
                           [VAL__105581
                            (.valAt input__105355_nth_1__ :sequence)]
                           (if
                            (clojure.core/vector? VAL__105581)
                            (if
                             (clojure.core/=
                              (clojure.core/count VAL__105581)
                              1)
                             (clojure.core/let
                              [VAL__105581_nth_0__
                               (clojure.core/nth VAL__105581 0)]
                              (if
                               (clojure.core/map? VAL__105581_nth_0__)
                               (clojure.core/let
                                [VAL__105586
                                 (.valAt VAL__105581_nth_0__ :tag)]
                                (clojure.core/case
                                 VAL__105586
                                 (:memory-variable)
                                 (clojure.core/let
                                  [?memory-variable
                                   VAL__105581_nth_0__]
                                  (clojure.core/let
                                   [VAL__105582
                                    (.valAt
                                     input__105355_nth_1__
                                     :next)]
                                   (if
                                    (clojure.core/map? VAL__105582)
                                    (clojure.core/let
                                     [VAL__105583
                                      (.valAt VAL__105582 :tag)]
                                     (clojure.core/case
                                      VAL__105583
                                      (:empty)
                                      (clojure.core/let
                                       [?next input__105355_nth_2__]
                                       (clojure.core/let
                                        [?env input__105355_nth_3__]
                                        (try
                                         [(clojure.core/let
                                           [CATA_RESULT__9229__auto__
                                            (CATA__FN__105414
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
                                      (state__106595)))
                                    (state__106595))))
                                 (state__106595)))
                               (state__106595)))
                             (state__106595))
                            (state__106595)))
                          (state__106595)))
                        (state__106595)))
                      (state__106595
                       []
                       (clojure.core/let
                        [?pattern input__105355_nth_1__]
                        (clojure.core/let
                         [?next input__105355_nth_2__]
                         (if
                          (clojure.core/map? input__105355_nth_3__)
                          (clojure.core/let
                           [VAL__105589
                            (.valAt input__105355_nth_3__ :context)]
                           (clojure.core/case
                            VAL__105589
                            (:string)
                            (try
                             [{:tag :string-star,
                               :greedy? false,
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
                            (state__106593)))
                          (state__106593)))))]
                     (state__106594))
                    (state__106593))))
                 (state__106593
                  []
                  (clojure.core/case
                   input__105355_nth_0__
                   (meander.dev.parse.zeta/make-star)
                   (clojure.core/let
                    [?pattern input__105355_nth_1__]
                    (clojure.core/let
                     [?next input__105355_nth_2__]
                     (try
                      [{:tag :star,
                        :greedy? false,
                        :pattern ?pattern,
                        :next ?next}]
                      (catch
                       java.lang.Exception
                       e__10169__auto__
                       (if
                        (meander.runtime.zeta/fail? e__10169__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__10169__auto__))))))
                   (state__106576)))]
                (state__106592)))
              (state__106576)))
            (state__106576
             []
             (if
              (clojure.core/= (clojure.core/count input__105355) 3)
              (clojure.core/let
               [input__105355_nth_0__
                (clojure.core/nth input__105355 0)
                input__105355_nth_1__
                (clojure.core/nth input__105355 1)
                input__105355_nth_2__
                (clojure.core/nth input__105355 2)]
               (clojure.core/case
                input__105355_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__105355_nth_1__)
                 (clojure.core/let
                  [input__105355_nth_1___l__
                   (clojure.core/subvec
                    input__105355_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__105355_nth_1__)
                     1))]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__105355_nth_1___l__)
                    1)
                   (clojure.core/let
                    [input__105355_nth_1___r__
                     (clojure.core/subvec input__105355_nth_1__ 1)]
                    (clojure.core/let
                     [input__105355_nth_1___l___nth_0__
                      (clojure.core/nth input__105355_nth_1___l__ 0)]
                     (if
                      (clojure.core/symbol?
                       input__105355_nth_1___l___nth_0__)
                      (clojure.core/let
                       [X__105597
                        (clojure.core/namespace
                         input__105355_nth_1___l___nth_0__)]
                       (clojure.core/case
                        X__105597
                        (nil)
                        (clojure.core/let
                         [X__105599
                          (clojure.core/name
                           input__105355_nth_1___l___nth_0__)]
                         (if
                          (clojure.core/string? X__105599)
                          (clojure.core/let
                           [ret__105600
                            (clojure.core/re-matches
                             #"\.\.(\d+)"
                             X__105599)]
                           (if
                            (clojure.core/some? ret__105600)
                            (if
                             (clojure.core/vector? ret__105600)
                             (if
                              (clojure.core/=
                               (clojure.core/count ret__105600)
                               2)
                              (clojure.core/let
                               [ret__105600_nth_1__
                                (clojure.core/nth ret__105600 1)]
                               (clojure.core/let
                                [?n ret__105600_nth_1__]
                                (clojure.core/let
                                 [?operator
                                  input__105355_nth_1___l___nth_0__]
                                 (clojure.core/let
                                  [?rest input__105355_nth_1___r__]
                                  (clojure.core/let
                                   [?env input__105355_nth_2__]
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
                                      (throw e__10169__auto__)))))))))
                              (state__106530))
                             (state__106530))
                            (state__106530)))
                          (state__106530)))
                        (state__106530)))
                      (state__106530))))
                   (state__106530)))
                 (state__106530))
                (state__106530)))
              (state__106530)))]
           (state__106574))
          (state__106530)))
        (state__106530
         []
         (clojure.core/letfn
          [(def__105603
            [arg__105627]
            (clojure.core/letfn
             [(state__106596
               []
               (clojure.core/let
                [x__105628 :string-plus]
                (clojure.core/let
                 [?tag x__105628]
                 (if
                  (clojure.core/map? arg__105627)
                  (clojure.core/let
                   [VAL__105629 (.valAt arg__105627 :context)]
                   (clojure.core/case
                    VAL__105629
                    (:string)
                    (clojure.core/let [?env arg__105627] [?tag ?env])
                    (state__106597)))
                  (state__106597)))))
              (state__106597
               []
               (clojure.core/let
                [x__105630 :plus]
                (clojure.core/let
                 [?tag x__105630]
                 (clojure.core/let [?env arg__105627] [?tag ?env]))))]
             (state__106596)))]
          (if
           (clojure.core/vector? input__105355)
           (if
            (clojure.core/= (clojure.core/count input__105355) 3)
            (clojure.core/let
             [input__105355_nth_0__
              (clojure.core/nth input__105355 0)
              input__105355_nth_1__
              (clojure.core/nth input__105355 1)
              input__105355_nth_2__
              (clojure.core/nth input__105355 2)]
             (clojure.core/case
              input__105355_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__105355_nth_1__)
               (clojure.core/loop
                [search_space__106598
                 (meander.match.runtime.epsilon/partitions
                  2
                  input__105355_nth_1__)]
                (if
                 (clojure.core/seq search_space__106598)
                 (clojure.core/let
                  [input__105355_nth_1___parts__
                   (clojure.core/first search_space__106598)
                   result__106599
                   (clojure.core/let
                    [input__105355_nth_1___l__
                     (clojure.core/nth input__105355_nth_1___parts__ 0)
                     input__105355_nth_1___r__
                     (clojure.core/nth
                      input__105355_nth_1___parts__
                      1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__8090__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__105355_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__105620]
                         (clojure.core/let
                          [input__105620_nth_0__
                           (clojure.core/nth input__105620 0)]
                          (clojure.core/letfn
                           [(save__105621
                             []
                             (meander.runtime.zeta/fail))
                            (f__106602
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__105620_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol?
                             input__105620_nth_0__)
                            (clojure.core/let
                             [X__105623
                              (clojure.core/namespace
                               input__105620_nth_0__)]
                             (clojure.core/case
                              X__105623
                              (nil)
                              (clojure.core/let
                               [X__105625
                                (clojure.core/name
                                 input__105620_nth_0__)]
                               (if
                                (clojure.core/string? X__105625)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__105625)
                                 (save__105621)
                                 (f__106602))
                                (f__106602)))
                              (f__106602)))
                            (f__106602)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__105355_nth_1___r___l__
                           (clojure.core/subvec
                            input__105355_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__105355_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__105355_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__105355_nth_1___r___r__
                             (clojure.core/subvec
                              input__105355_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__105355_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__105355_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__105355_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__105614
                                (clojure.core/namespace
                                 input__105355_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__105614
                                (nil)
                                (clojure.core/let
                                 [X__105616
                                  (clojure.core/name
                                   input__105355_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__105616)
                                  (clojure.core/let
                                   [ret__105617
                                    (clojure.core/re-matches
                                     #"\.\.(\d+)"
                                     X__105616)]
                                   (if
                                    (clojure.core/some? ret__105617)
                                    (if
                                     (clojure.core/vector? ret__105617)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__105617)
                                       2)
                                      (clojure.core/let
                                       [ret__105617_nth_1__
                                        (clojure.core/nth
                                         ret__105617
                                         1)]
                                       (clojure.core/let
                                        [?n ret__105617_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__105355_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__7926__auto__
                                           (def__105603
                                            input__105355_nth_2__)]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            x__7926__auto__)
                                           (meander.runtime.zeta/fail)
                                           (clojure.core/let
                                            [[?tag ?env]
                                             x__7926__auto__]
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
                                                 [CATA_RESULT__9229__auto__
                                                  (CATA__FN__105414
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
                                                  (CATA__FN__105414
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
                                                e__10169__auto__))))))))))
                                      (meander.runtime.zeta/fail))
                                     (meander.runtime.zeta/fail))
                                    (meander.runtime.zeta/fail)))
                                  (meander.runtime.zeta/fail)))
                                (meander.runtime.zeta/fail)))
                              (meander.runtime.zeta/fail))))
                           (meander.runtime.zeta/fail)))))]
                      (if
                       (meander.runtime.zeta/fail? ret__8090__auto__)
                       (meander.runtime.zeta/fail)
                       ret__8090__auto__))))]
                  (if
                   (meander.runtime.zeta/fail? result__106599)
                   (recur (clojure.core/next search_space__106598))
                   result__106599))
                 (state__106531)))
               (state__106531))
              (state__106531)))
            (state__106531))
           (state__106531))))
        (state__106531
         []
         (if
          (clojure.core/vector? input__105355)
          (if
           (clojure.core/= (clojure.core/count input__105355) 3)
           (clojure.core/let
            [input__105355_nth_0__
             (clojure.core/nth input__105355 0)
             input__105355_nth_1__
             (clojure.core/nth input__105355 1)
             input__105355_nth_2__
             (clojure.core/nth input__105355 2)]
            (clojure.core/case
             input__105355_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__105355_nth_1__)
              (clojure.core/let
               [input__105355_nth_1___l__
                (clojure.core/subvec
                 input__105355_nth_1__
                 0
                 (clojure.core/min
                  (clojure.core/count input__105355_nth_1__)
                  1))]
               (if
                (clojure.core/=
                 (clojure.core/count input__105355_nth_1___l__)
                 1)
                (clojure.core/let
                 [input__105355_nth_1___r__
                  (clojure.core/subvec input__105355_nth_1__ 1)]
                 (clojure.core/let
                  [input__105355_nth_1___l___nth_0__
                   (clojure.core/nth input__105355_nth_1___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__105355_nth_1___l___nth_0__)
                   (clojure.core/let
                    [X__105648
                     (clojure.core/namespace
                      input__105355_nth_1___l___nth_0__)]
                    (clojure.core/case
                     X__105648
                     (nil)
                     (clojure.core/let
                      [X__105650
                       (clojure.core/name
                        input__105355_nth_1___l___nth_0__)]
                      (if
                       (clojure.core/string? X__105650)
                       (clojure.core/let
                        [ret__105651
                         (clojure.core/re-matches
                          #"\.\.(\?.+)"
                          X__105650)]
                        (if
                         (clojure.core/some? ret__105651)
                         (if
                          (clojure.core/vector? ret__105651)
                          (if
                           (clojure.core/=
                            (clojure.core/count ret__105651)
                            2)
                           (clojure.core/let
                            [ret__105651_nth_1__
                             (clojure.core/nth ret__105651 1)]
                            (clojure.core/let
                             [?n ret__105651_nth_1__]
                             (clojure.core/let
                              [?operator
                               input__105355_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?rest input__105355_nth_1___r__]
                               (clojure.core/let
                                [?env input__105355_nth_2__]
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
                                   (throw e__10169__auto__)))))))))
                           (state__106532))
                          (state__106532))
                         (state__106532)))
                       (state__106532)))
                     (state__106532)))
                   (state__106532))))
                (state__106532)))
              (state__106532))
             (state__106532)))
           (state__106532))
          (state__106532)))
        (state__106532
         []
         (clojure.core/letfn
          [(def__105654
            [arg__105678]
            (clojure.core/letfn
             [(state__106603
               []
               (clojure.core/let
                [x__105679 :string-logical-plus]
                (clojure.core/let
                 [?tag x__105679]
                 (if
                  (clojure.core/map? arg__105678)
                  (clojure.core/let
                   [VAL__105680 (.valAt arg__105678 :context)]
                   (clojure.core/case
                    VAL__105680
                    (:string)
                    (clojure.core/let [?env arg__105678] [?tag ?env])
                    (state__106604)))
                  (state__106604)))))
              (state__106604
               []
               (clojure.core/let
                [x__105681 :logical-plus]
                (clojure.core/let
                 [?tag x__105681]
                 (clojure.core/let [?env arg__105678] [?tag ?env]))))]
             (state__106603)))]
          (if
           (clojure.core/vector? input__105355)
           (if
            (clojure.core/= (clojure.core/count input__105355) 3)
            (clojure.core/let
             [input__105355_nth_0__
              (clojure.core/nth input__105355 0)
              input__105355_nth_1__
              (clojure.core/nth input__105355 1)
              input__105355_nth_2__
              (clojure.core/nth input__105355 2)]
             (clojure.core/case
              input__105355_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__105355_nth_1__)
               (clojure.core/loop
                [search_space__106605
                 (meander.match.runtime.epsilon/partitions
                  2
                  input__105355_nth_1__)]
                (if
                 (clojure.core/seq search_space__106605)
                 (clojure.core/let
                  [input__105355_nth_1___parts__
                   (clojure.core/first search_space__106605)
                   result__106606
                   (clojure.core/let
                    [input__105355_nth_1___l__
                     (clojure.core/nth input__105355_nth_1___parts__ 0)
                     input__105355_nth_1___r__
                     (clojure.core/nth
                      input__105355_nth_1___parts__
                      1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__8090__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__105355_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__105671]
                         (clojure.core/let
                          [input__105671_nth_0__
                           (clojure.core/nth input__105671 0)]
                          (clojure.core/letfn
                           [(save__105672
                             []
                             (meander.runtime.zeta/fail))
                            (f__106609
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__105671_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol?
                             input__105671_nth_0__)
                            (clojure.core/let
                             [X__105674
                              (clojure.core/namespace
                               input__105671_nth_0__)]
                             (clojure.core/case
                              X__105674
                              (nil)
                              (clojure.core/let
                               [X__105676
                                (clojure.core/name
                                 input__105671_nth_0__)]
                               (if
                                (clojure.core/string? X__105676)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__105676)
                                 (save__105672)
                                 (f__106609))
                                (f__106609)))
                              (f__106609)))
                            (f__106609)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__105355_nth_1___r___l__
                           (clojure.core/subvec
                            input__105355_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__105355_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__105355_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__105355_nth_1___r___r__
                             (clojure.core/subvec
                              input__105355_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__105355_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__105355_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__105355_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__105665
                                (clojure.core/namespace
                                 input__105355_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__105665
                                (nil)
                                (clojure.core/let
                                 [X__105667
                                  (clojure.core/name
                                   input__105355_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__105667)
                                  (clojure.core/let
                                   [ret__105668
                                    (clojure.core/re-matches
                                     #"\.\.(\?.+)"
                                     X__105667)]
                                   (if
                                    (clojure.core/some? ret__105668)
                                    (if
                                     (clojure.core/vector? ret__105668)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__105668)
                                       2)
                                      (clojure.core/let
                                       [ret__105668_nth_1__
                                        (clojure.core/nth
                                         ret__105668
                                         1)]
                                       (clojure.core/let
                                        [?n ret__105668_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__105355_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__7926__auto__
                                           (def__105654
                                            input__105355_nth_2__)]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            x__7926__auto__)
                                           (meander.runtime.zeta/fail)
                                           (clojure.core/let
                                            [[?tag ?env]
                                             x__7926__auto__]
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
                                                 [CATA_RESULT__9229__auto__
                                                  (CATA__FN__105414
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
                                                  (CATA__FN__105414
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
                                                e__10169__auto__))))))))))
                                      (meander.runtime.zeta/fail))
                                     (meander.runtime.zeta/fail))
                                    (meander.runtime.zeta/fail)))
                                  (meander.runtime.zeta/fail)))
                                (meander.runtime.zeta/fail)))
                              (meander.runtime.zeta/fail))))
                           (meander.runtime.zeta/fail)))))]
                      (if
                       (meander.runtime.zeta/fail? ret__8090__auto__)
                       (meander.runtime.zeta/fail)
                       ret__8090__auto__))))]
                  (if
                   (meander.runtime.zeta/fail? result__106606)
                   (recur (clojure.core/next search_space__106605))
                   result__106606))
                 (state__106533)))
               (state__106533))
              (state__106533)))
            (state__106533))
           (state__106533))))
        (state__106533
         []
         (if
          (clojure.core/vector? input__105355)
          (if
           (clojure.core/= (clojure.core/count input__105355) 3)
           (clojure.core/let
            [input__105355_nth_0__
             (clojure.core/nth input__105355 0)
             input__105355_nth_1__
             (clojure.core/nth input__105355 1)
             input__105355_nth_2__
             (clojure.core/nth input__105355 2)]
            (clojure.core/case
             input__105355_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__105355_nth_1__)
              (clojure.core/let
               [input__105355_nth_1___l__
                (clojure.core/subvec
                 input__105355_nth_1__
                 0
                 (clojure.core/min
                  (clojure.core/count input__105355_nth_1__)
                  1))]
               (if
                (clojure.core/=
                 (clojure.core/count input__105355_nth_1___l__)
                 1)
                (clojure.core/let
                 [input__105355_nth_1___r__
                  (clojure.core/subvec input__105355_nth_1__ 1)]
                 (clojure.core/let
                  [input__105355_nth_1___l___nth_0__
                   (clojure.core/nth input__105355_nth_1___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__105355_nth_1___l___nth_0__)
                   (clojure.core/let
                    [X__105699
                     (clojure.core/namespace
                      input__105355_nth_1___l___nth_0__)]
                    (clojure.core/case
                     X__105699
                     (nil)
                     (clojure.core/let
                      [X__105701
                       (clojure.core/name
                        input__105355_nth_1___l___nth_0__)]
                      (if
                       (clojure.core/string? X__105701)
                       (clojure.core/let
                        [ret__105702
                         (clojure.core/re-matches
                          #"\.\.(!.+)"
                          X__105701)]
                        (if
                         (clojure.core/some? ret__105702)
                         (if
                          (clojure.core/vector? ret__105702)
                          (if
                           (clojure.core/=
                            (clojure.core/count ret__105702)
                            2)
                           (clojure.core/let
                            [ret__105702_nth_1__
                             (clojure.core/nth ret__105702 1)]
                            (clojure.core/let
                             [?n ret__105702_nth_1__]
                             (clojure.core/let
                              [?operator
                               input__105355_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?rest input__105355_nth_1___r__]
                               (clojure.core/let
                                [?env input__105355_nth_2__]
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
                                   (throw e__10169__auto__)))))))))
                           (state__106534))
                          (state__106534))
                         (state__106534)))
                       (state__106534)))
                     (state__106534)))
                   (state__106534))))
                (state__106534)))
              (state__106534))
             (state__106534)))
           (state__106534))
          (state__106534)))
        (state__106534
         []
         (clojure.core/letfn
          [(def__105705
            [arg__105729]
            (clojure.core/letfn
             [(state__106610
               []
               (clojure.core/let
                [x__105730 :string-memory-plus]
                (clojure.core/let
                 [?tag x__105730]
                 (if
                  (clojure.core/map? arg__105729)
                  (clojure.core/let
                   [VAL__105731 (.valAt arg__105729 :context)]
                   (clojure.core/case
                    VAL__105731
                    (:string)
                    (clojure.core/let [?env arg__105729] [?tag ?env])
                    (state__106611)))
                  (state__106611)))))
              (state__106611
               []
               (clojure.core/let
                [x__105732 :memory-plus]
                (clojure.core/let
                 [?tag x__105732]
                 (clojure.core/let [?env arg__105729] [?tag ?env]))))]
             (state__106610)))]
          (if
           (clojure.core/vector? input__105355)
           (if
            (clojure.core/= (clojure.core/count input__105355) 3)
            (clojure.core/let
             [input__105355_nth_0__
              (clojure.core/nth input__105355 0)
              input__105355_nth_1__
              (clojure.core/nth input__105355 1)
              input__105355_nth_2__
              (clojure.core/nth input__105355 2)]
             (clojure.core/case
              input__105355_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__105355_nth_1__)
               (clojure.core/loop
                [search_space__106612
                 (meander.match.runtime.epsilon/partitions
                  2
                  input__105355_nth_1__)]
                (if
                 (clojure.core/seq search_space__106612)
                 (clojure.core/let
                  [input__105355_nth_1___parts__
                   (clojure.core/first search_space__106612)
                   result__106613
                   (clojure.core/let
                    [input__105355_nth_1___l__
                     (clojure.core/nth input__105355_nth_1___parts__ 0)
                     input__105355_nth_1___r__
                     (clojure.core/nth
                      input__105355_nth_1___parts__
                      1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__8090__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__105355_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__105722]
                         (clojure.core/let
                          [input__105722_nth_0__
                           (clojure.core/nth input__105722 0)]
                          (clojure.core/letfn
                           [(save__105723
                             []
                             (meander.runtime.zeta/fail))
                            (f__106616
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__105722_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol?
                             input__105722_nth_0__)
                            (clojure.core/let
                             [X__105725
                              (clojure.core/namespace
                               input__105722_nth_0__)]
                             (clojure.core/case
                              X__105725
                              (nil)
                              (clojure.core/let
                               [X__105727
                                (clojure.core/name
                                 input__105722_nth_0__)]
                               (if
                                (clojure.core/string? X__105727)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__105727)
                                 (save__105723)
                                 (f__106616))
                                (f__106616)))
                              (f__106616)))
                            (f__106616)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__105355_nth_1___r___l__
                           (clojure.core/subvec
                            input__105355_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__105355_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__105355_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__105355_nth_1___r___r__
                             (clojure.core/subvec
                              input__105355_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__105355_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__105355_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__105355_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__105716
                                (clojure.core/namespace
                                 input__105355_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__105716
                                (nil)
                                (clojure.core/let
                                 [X__105718
                                  (clojure.core/name
                                   input__105355_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__105718)
                                  (clojure.core/let
                                   [ret__105719
                                    (clojure.core/re-matches
                                     #"\.\.(\!.+)"
                                     X__105718)]
                                   (if
                                    (clojure.core/some? ret__105719)
                                    (if
                                     (clojure.core/vector? ret__105719)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__105719)
                                       2)
                                      (clojure.core/let
                                       [ret__105719_nth_1__
                                        (clojure.core/nth
                                         ret__105719
                                         1)]
                                       (clojure.core/let
                                        [?n ret__105719_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__105355_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__7926__auto__
                                           (def__105705
                                            input__105355_nth_2__)]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            x__7926__auto__)
                                           (meander.runtime.zeta/fail)
                                           (clojure.core/let
                                            [[?tag ?env]
                                             x__7926__auto__]
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
                                                 [CATA_RESULT__9229__auto__
                                                  (CATA__FN__105414
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
                                                  (CATA__FN__105414
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
                                                e__10169__auto__))))))))))
                                      (meander.runtime.zeta/fail))
                                     (meander.runtime.zeta/fail))
                                    (meander.runtime.zeta/fail)))
                                  (meander.runtime.zeta/fail)))
                                (meander.runtime.zeta/fail)))
                              (meander.runtime.zeta/fail))))
                           (meander.runtime.zeta/fail)))))]
                      (if
                       (meander.runtime.zeta/fail? ret__8090__auto__)
                       (meander.runtime.zeta/fail)
                       ret__8090__auto__))))]
                  (if
                   (meander.runtime.zeta/fail? result__106613)
                   (recur (clojure.core/next search_space__106612))
                   result__106613))
                 (state__106535)))
               (state__106535))
              (state__106535)))
            (state__106535))
           (state__106535))))
        (state__106535
         []
         (if
          (clojure.core/vector? input__105355)
          (clojure.core/letfn
           [(state__106617
             []
             (if
              (clojure.core/= (clojure.core/count input__105355) 3)
              (clojure.core/let
               [input__105355_nth_0__
                (clojure.core/nth input__105355 0)
                input__105355_nth_1__
                (clojure.core/nth input__105355 1)
                input__105355_nth_2__
                (clojure.core/nth input__105355 2)]
               (clojure.core/case
                input__105355_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__105355_nth_1__)
                 (clojure.core/let
                  [!xs (clojure.core/vec input__105355_nth_1__)]
                  (clojure.core/let
                   [?env input__105355_nth_2__]
                   (try
                    [(clojure.core/let
                      [!xs__counter
                       (meander.runtime.zeta/iterator !xs)]
                      (clojure.core/let
                       [CATA_RESULT__9229__auto__
                        (CATA__FN__105414
                         ['meander.dev.parse.zeta/make-cat
                          (clojure.core/into
                           []
                           (clojure.core/loop
                            [return__105415
                             (clojure.core/transient [])]
                            (if
                             (clojure.core/and (.hasNext !xs__counter))
                             (recur
                              (clojure.core/conj!
                               return__105415
                               (clojure.core/let
                                [CATA_RESULT__9229__auto__
                                 (CATA__FN__105414
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
                              return__105415))))
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
                      (throw e__10169__auto__))))))
                 (state__106618))
                (state__106618)))
              (state__106618)))
            (state__106618
             []
             (if
              (clojure.core/= (clojure.core/count input__105355) 4)
              (clojure.core/let
               [input__105355_nth_0__
                (clojure.core/nth input__105355 0)
                input__105355_nth_1__
                (clojure.core/nth input__105355 1)
                input__105355_nth_2__
                (clojure.core/nth input__105355 2)]
               (clojure.core/letfn
                [(state__106620
                  []
                  (clojure.core/let
                   [input__105355_nth_3__
                    (clojure.core/nth input__105355 3)]
                   (clojure.core/case
                    input__105355_nth_0__
                    (meander.dev.parse.zeta/make-cat)
                    (if
                     (clojure.core/vector? input__105355_nth_1__)
                     (clojure.core/letfn
                      [(state__106627
                        []
                        (clojure.core/case
                         input__105355_nth_1__
                         ([])
                         (clojure.core/let
                          [?next input__105355_nth_2__]
                          (clojure.core/let
                           [?env input__105355_nth_3__]
                           (try
                            [?next]
                            (catch
                             java.lang.Exception
                             e__10169__auto__
                             (if
                              (meander.runtime.zeta/fail?
                               e__10169__auto__)
                              (meander.runtime.zeta/fail)
                              (throw e__10169__auto__))))))
                         (state__106628)))
                       (state__106628
                        []
                        (clojure.core/loop
                         [search_space__106629
                          (meander.match.runtime.epsilon/partitions
                           2
                           input__105355_nth_1__)]
                         (if
                          (clojure.core/seq search_space__106629)
                          (clojure.core/let
                           [input__105355_nth_1___parts__
                            (clojure.core/first search_space__106629)
                            result__106630
                            (clojure.core/let
                             [input__105355_nth_1___l__
                              (clojure.core/nth
                               input__105355_nth_1___parts__
                               0)
                              input__105355_nth_1___r__
                              (clojure.core/nth
                               input__105355_nth_1___parts__
                               1)]
                             (clojure.core/let
                              [!xs []]
                              (clojure.core/let
                               [ret__8090__auto__
                                (meander.runtime.zeta/epsilon-run-star-1
                                 input__105355_nth_1___l__
                                 [!xs]
                                 (clojure.core/fn
                                  [[!xs] input__105758]
                                  (clojure.core/let
                                   [input__105758_nth_0__
                                    (clojure.core/nth input__105758 0)]
                                   (clojure.core/letfn
                                    [(save__105759
                                      []
                                      (meander.runtime.zeta/fail))
                                     (f__106633
                                      []
                                      (clojure.core/let
                                       [!xs
                                        (clojure.core/conj
                                         !xs
                                         input__105758_nth_0__)]
                                       [!xs]))]
                                    (if
                                     (clojure.core/map?
                                      input__105758_nth_0__)
                                     (clojure.core/let
                                      [VAL__105760
                                       (.valAt
                                        input__105758_nth_0__
                                        :tag)]
                                      (clojure.core/case
                                       VAL__105760
                                       (:group)
                                       (save__105759)
                                       (f__106633)))
                                     (f__106633)))))
                                 (clojure.core/fn
                                  [[!xs]]
                                  (clojure.core/let
                                   [input__105355_nth_1___r___l__
                                    (clojure.core/subvec
                                     input__105355_nth_1___r__
                                     0
                                     (clojure.core/min
                                      (clojure.core/count
                                       input__105355_nth_1___r__)
                                      1))]
                                   (if
                                    (clojure.core/=
                                     (clojure.core/count
                                      input__105355_nth_1___r___l__)
                                     1)
                                    (clojure.core/let
                                     [input__105355_nth_1___r___r__
                                      (clojure.core/subvec
                                       input__105355_nth_1___r__
                                       1)]
                                     (clojure.core/let
                                      [input__105355_nth_1___r___l___nth_0__
                                       (clojure.core/nth
                                        input__105355_nth_1___r___l__
                                        0)]
                                      (if
                                       (clojure.core/map?
                                        input__105355_nth_1___r___l___nth_0__)
                                       (clojure.core/let
                                        [VAL__105757
                                         (.valAt
                                          input__105355_nth_1___r___l___nth_0__
                                          :tag)]
                                        (clojure.core/case
                                         VAL__105757
                                         (:group)
                                         (clojure.core/let
                                          [?group
                                           input__105355_nth_1___r___l___nth_0__]
                                          (clojure.core/let
                                           [?rest
                                            input__105355_nth_1___r___r__]
                                           (clojure.core/let
                                            [?next
                                             input__105355_nth_2__]
                                            (clojure.core/let
                                             [?env
                                              input__105355_nth_3__]
                                             (try
                                              [(clojure.core/let
                                                [!xs__counter
                                                 (meander.runtime.zeta/iterator
                                                  !xs)]
                                                (clojure.core/let
                                                 [CATA_RESULT__9229__auto__
                                                  (CATA__FN__105414
                                                   ['meander.dev.parse.zeta/make-join
                                                    (clojure.core/let
                                                     [CATA_RESULT__9229__auto__
                                                      (CATA__FN__105414
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
                                                       CATA_RESULT__9229__auto__)
                                                      (throw
                                                       (meander.runtime.zeta/fail))
                                                      (clojure.core/nth
                                                       CATA_RESULT__9229__auto__
                                                       0)))
                                                    (clojure.core/let
                                                     [CATA_RESULT__9229__auto__
                                                      (CATA__FN__105414
                                                       ['meander.dev.parse.zeta/make-join
                                                        ?group
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
                                                (throw
                                                 e__10169__auto__))))))))
                                         (meander.runtime.zeta/fail)))
                                       (meander.runtime.zeta/fail))))
                                    (meander.runtime.zeta/fail)))))]
                               (if
                                (meander.runtime.zeta/fail?
                                 ret__8090__auto__)
                                (meander.runtime.zeta/fail)
                                ret__8090__auto__))))]
                           (if
                            (meander.runtime.zeta/fail? result__106630)
                            (recur
                             (clojure.core/next search_space__106629))
                            result__106630))
                          (state__106621))))]
                      (state__106627))
                     (state__106621))
                    (state__106621))))
                 (state__106621
                  []
                  (clojure.core/case
                   input__105355_nth_0__
                   (meander.dev.parse.zeta/make-cat)
                   (if
                    (clojure.core/vector? input__105355_nth_1__)
                    (clojure.core/let
                     [input__105355_nth_1___l__
                      (clojure.core/subvec
                       input__105355_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__105355_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__105355_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__105355_nth_1___r__
                        (clojure.core/subvec input__105355_nth_1__ 1)]
                       (clojure.core/let
                        [input__105355_nth_1___l___nth_0__
                         (clojure.core/nth
                          input__105355_nth_1___l__
                          0)]
                        (if
                         (clojure.core/map?
                          input__105355_nth_1___l___nth_0__)
                         (clojure.core/let
                          [VAL__105769
                           (.valAt
                            input__105355_nth_1___l___nth_0__
                            :tag)]
                          (clojure.core/case
                           VAL__105769
                           (:literal)
                           (clojure.core/let
                            [VAL__105770
                             (.valAt
                              input__105355_nth_1___l___nth_0__
                              :type)]
                            (if
                             (clojure.core/let
                              [x__6986__auto__ VAL__105770]
                              (clojure.core/case
                               x__6986__auto__
                               (:string :char)
                               true
                               false))
                             (clojure.core/let
                              [VAL__105771
                               (.valAt
                                input__105355_nth_1___l___nth_0__
                                :form)]
                              (clojure.core/let
                               [!forms []]
                               (clojure.core/let
                                [!forms
                                 (clojure.core/conj
                                  !forms
                                  VAL__105771)]
                                (clojure.core/loop
                                 [i__8063__auto__
                                  0
                                  coll__106634
                                  input__105355_nth_1___r__
                                  [!forms]
                                  [!forms]]
                                 (clojure.core/let
                                  [input__105772
                                   (clojure.core/subvec
                                    coll__106634
                                    0
                                    (clojure.core/min
                                     (clojure.core/count coll__106634)
                                     1))]
                                  (if
                                   (clojure.core/=
                                    (clojure.core/count input__105772)
                                    1)
                                   (clojure.core/let
                                    [result__8064__auto__
                                     (clojure.core/let
                                      [input__105772_nth_0__
                                       (clojure.core/nth
                                        input__105772
                                        0)]
                                      (if
                                       (clojure.core/map?
                                        input__105772_nth_0__)
                                       (clojure.core/let
                                        [VAL__105773
                                         (.valAt
                                          input__105772_nth_0__
                                          :tag)]
                                        (clojure.core/case
                                         VAL__105773
                                         (:literal)
                                         (clojure.core/let
                                          [VAL__105774
                                           (.valAt
                                            input__105772_nth_0__
                                            :type)]
                                          (if
                                           (clojure.core/let
                                            [x__6986__auto__
                                             VAL__105774]
                                            (clojure.core/case
                                             x__6986__auto__
                                             (:string :char)
                                             true
                                             false))
                                           (clojure.core/let
                                            [VAL__105775
                                             (.valAt
                                              input__105772_nth_0__
                                              :form)]
                                            (clojure.core/let
                                             [!forms
                                              (clojure.core/conj
                                               !forms
                                               VAL__105775)]
                                             [!forms]))
                                           (meander.runtime.zeta/fail)))
                                         (meander.runtime.zeta/fail)))
                                       (meander.runtime.zeta/fail)))]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      result__8064__auto__)
                                     (state__106622)
                                     (recur
                                      (clojure.core/inc
                                       i__8063__auto__)
                                      (clojure.core/subvec
                                       coll__106634
                                       1)
                                      result__8064__auto__)))
                                   (if
                                    (clojure.core/or
                                     (clojure.core/seq coll__106634)
                                     (clojure.core/<
                                      i__8063__auto__
                                      0))
                                    (state__106622)
                                    (if
                                     (clojure.core/map?
                                      input__105355_nth_2__)
                                     (clojure.core/let
                                      [VAL__105764
                                       (.valAt
                                        input__105355_nth_2__
                                        :tag)]
                                      (clojure.core/case
                                       VAL__105764
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
                                         e__10169__auto__
                                         (if
                                          (meander.runtime.zeta/fail?
                                           e__10169__auto__)
                                          (meander.runtime.zeta/fail)
                                          (throw e__10169__auto__))))
                                       (state__106622)))
                                     (state__106622)))))))))
                             (state__106622)))
                           (state__106622)))
                         (state__106622))))
                      (state__106622)))
                    (state__106622))
                   (state__106622)))
                 (state__106622
                  []
                  (clojure.core/let
                   [input__105355_nth_3__
                    (clojure.core/nth input__105355 3)]
                   (clojure.core/case
                    input__105355_nth_0__
                    (meander.dev.parse.zeta/make-cat)
                    (clojure.core/letfn
                     [(state__106635
                       []
                       (if
                        (clojure.core/vector? input__105355_nth_1__)
                        (clojure.core/let
                         [input__105355_nth_1___l__
                          (clojure.core/subvec
                           input__105355_nth_1__
                           0
                           (clojure.core/min
                            (clojure.core/count input__105355_nth_1__)
                            1))]
                         (if
                          (clojure.core/=
                           (clojure.core/count
                            input__105355_nth_1___l__)
                           1)
                          (clojure.core/let
                           [input__105355_nth_1___r__
                            (clojure.core/subvec
                             input__105355_nth_1__
                             1)]
                           (clojure.core/let
                            [input__105355_nth_1___l___nth_0__
                             (clojure.core/nth
                              input__105355_nth_1___l__
                              0)]
                            (if
                             (clojure.core/map?
                              input__105355_nth_1___l___nth_0__)
                             (clojure.core/let
                              [VAL__106525
                               (.valAt
                                input__105355_nth_1___l___nth_0__
                                :tag)]
                              (clojure.core/case
                               VAL__106525
                               (:literal)
                               (clojure.core/letfn
                                [(state__106637
                                  []
                                  (clojure.core/let
                                   [VAL__105782
                                    (.valAt
                                     input__105355_nth_1___l___nth_0__
                                     :type)]
                                   (clojure.core/case
                                    VAL__105782
                                    (:string)
                                    (clojure.core/let
                                     [?ast
                                      input__105355_nth_1___l___nth_0__]
                                     (clojure.core/let
                                      [?rest input__105355_nth_1___r__]
                                      (clojure.core/let
                                       [?next input__105355_nth_2__]
                                       (clojure.core/let
                                        [?env input__105355_nth_3__]
                                        (try
                                         [(clojure.core/let
                                           [CATA_RESULT__9229__auto__
                                            (CATA__FN__105414
                                             ['meander.dev.parse.zeta/make-join
                                              ?ast
                                              (clojure.core/let
                                               [CATA_RESULT__9229__auto__
                                                (CATA__FN__105414
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
                                    (state__106638))))
                                 (state__106638
                                  []
                                  (clojure.core/let
                                   [VAL__105792
                                    (.valAt
                                     input__105355_nth_1___l___nth_0__
                                     :form)]
                                   (clojure.core/let
                                    [!forms []]
                                    (clojure.core/let
                                     [!forms
                                      (clojure.core/conj
                                       !forms
                                       VAL__105792)]
                                     (clojure.core/loop
                                      [i__8063__auto__
                                       0
                                       coll__106639
                                       input__105355_nth_1___r__
                                       [!forms]
                                       [!forms]]
                                      (clojure.core/let
                                       [input__105793
                                        (clojure.core/subvec
                                         coll__106639
                                         0
                                         (clojure.core/min
                                          (clojure.core/count
                                           coll__106639)
                                          1))]
                                       (if
                                        (clojure.core/=
                                         (clojure.core/count
                                          input__105793)
                                         1)
                                        (clojure.core/let
                                         [result__8064__auto__
                                          (clojure.core/let
                                           [input__105793_nth_0__
                                            (clojure.core/nth
                                             input__105793
                                             0)]
                                           (if
                                            (clojure.core/map?
                                             input__105793_nth_0__)
                                            (clojure.core/let
                                             [VAL__105794
                                              (.valAt
                                               input__105793_nth_0__
                                               :tag)]
                                             (clojure.core/case
                                              VAL__105794
                                              (:literal)
                                              (clojure.core/let
                                               [VAL__105795
                                                (.valAt
                                                 input__105793_nth_0__
                                                 :form)]
                                               (clojure.core/let
                                                [!forms
                                                 (clojure.core/conj
                                                  !forms
                                                  VAL__105795)]
                                                [!forms]))
                                              (meander.runtime.zeta/fail)))
                                            (meander.runtime.zeta/fail)))]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           result__8064__auto__)
                                          (state__106636)
                                          (recur
                                           (clojure.core/inc
                                            i__8063__auto__)
                                           (clojure.core/subvec
                                            coll__106639
                                            1)
                                           result__8064__auto__)))
                                        (if
                                         (clojure.core/or
                                          (clojure.core/seq
                                           coll__106639)
                                          (clojure.core/<
                                           i__8063__auto__
                                           0))
                                         (state__106636)
                                         (if
                                          (clojure.core/map?
                                           input__105355_nth_2__)
                                          (clojure.core/let
                                           [VAL__105785
                                            (.valAt
                                             input__105355_nth_2__
                                             :tag)]
                                           (clojure.core/case
                                            VAL__105785
                                            (:empty)
                                            (if
                                             (clojure.core/map?
                                              input__105355_nth_3__)
                                             (clojure.core/let
                                              [VAL__105786
                                               (.valAt
                                                input__105355_nth_3__
                                                :context)]
                                              (clojure.core/let
                                               [?context VAL__105786]
                                               (clojure.core/let
                                                [?env
                                                 input__105355_nth_3__]
                                                (try
                                                 [{:tag :literal,
                                                   :type ?context,
                                                   :form
                                                   (clojure.core/into
                                                    []
                                                    !forms)}]
                                                 (catch
                                                  java.lang.Exception
                                                  e__10169__auto__
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    e__10169__auto__)
                                                   (meander.runtime.zeta/fail)
                                                   (throw
                                                    e__10169__auto__)))))))
                                             (state__106636))
                                            (state__106636)))
                                          (state__106636))))))))))]
                                (state__106637))
                               (state__106636)))
                             (state__106636))))
                          (state__106636)))
                        (state__106636)))
                      (state__106636
                       []
                       (clojure.core/let
                        [?sequence input__105355_nth_1__]
                        (clojure.core/let
                         [?next input__105355_nth_2__]
                         (if
                          (clojure.core/map? input__105355_nth_3__)
                          (clojure.core/let
                           [VAL__105799
                            (.valAt input__105355_nth_3__ :context)]
                           (clojure.core/case
                            VAL__105799
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
                            (state__106623)))
                          (state__106623)))))]
                     (state__106635))
                    (state__106623))))
                 (state__106623
                  []
                  (clojure.core/case
                   input__105355_nth_0__
                   (meander.dev.parse.zeta/make-cat)
                   (clojure.core/let
                    [?sequence input__105355_nth_1__]
                    (clojure.core/let
                     [?next input__105355_nth_2__]
                     (try
                      [{:tag :cat, :sequence ?sequence, :next ?next}]
                      (catch
                       java.lang.Exception
                       e__10169__auto__
                       (if
                        (meander.runtime.zeta/fail? e__10169__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__10169__auto__))))))
                   (state__106624)))
                 (state__106624
                  []
                  (clojure.core/let
                   [input__105355_nth_3__
                    (clojure.core/nth input__105355 3)]
                   (clojure.core/case
                    input__105355_nth_0__
                    (meander.dev.parse.zeta/make-join)
                    (if
                     (clojure.core/map? input__105355_nth_1__)
                     (clojure.core/let
                      [VAL__106521 (.valAt input__105355_nth_1__ :tag)]
                      (clojure.core/case
                       VAL__106521
                       (:cat)
                       (clojure.core/let
                        [VAL__105805
                         (.valAt input__105355_nth_1__ :sequence)]
                        (clojure.core/let
                         [?sequence VAL__105805]
                         (clojure.core/let
                          [VAL__105806
                           (.valAt input__105355_nth_1__ :next)]
                          (if
                           (clojure.core/map? VAL__105806)
                           (clojure.core/let
                            [VAL__105807 (.valAt VAL__105806 :tag)]
                            (clojure.core/case
                             VAL__105807
                             (:empty)
                             (clojure.core/let
                              [?right input__105355_nth_2__]
                              (clojure.core/let
                               [?env input__105355_nth_3__]
                               (try
                                [(clojure.core/let
                                  [CATA_RESULT__9229__auto__
                                   (CATA__FN__105414
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
                             (state__106625)))
                           (state__106625)))))
                       (:literal)
                       (clojure.core/let
                        [VAL__105811
                         (.valAt input__105355_nth_1__ :type)]
                        (clojure.core/case
                         VAL__105811
                         (:string)
                         (clojure.core/let
                          [VAL__105812
                           (.valAt input__105355_nth_1__ :form)]
                          (clojure.core/let
                           [?form-1 VAL__105812]
                           (if
                            (clojure.core/map? input__105355_nth_2__)
                            (clojure.core/let
                             [VAL__105813
                              (.valAt input__105355_nth_2__ :tag)]
                             (clojure.core/case
                              VAL__105813
                              (:string-join)
                              (clojure.core/let
                               [VAL__105814
                                (.valAt input__105355_nth_2__ :left)]
                               (if
                                (clojure.core/map? VAL__105814)
                                (clojure.core/let
                                 [VAL__105815
                                  (.valAt VAL__105814 :tag)]
                                 (clojure.core/case
                                  VAL__105815
                                  (:literal)
                                  (clojure.core/let
                                   [VAL__105816
                                    (.valAt VAL__105814 :type)]
                                   (clojure.core/case
                                    VAL__105816
                                    (:string)
                                    (clojure.core/let
                                     [VAL__105817
                                      (.valAt VAL__105814 :form)]
                                     (clojure.core/let
                                      [?form-2 VAL__105817]
                                      (clojure.core/let
                                       [VAL__105818
                                        (.valAt
                                         input__105355_nth_2__
                                         :right)]
                                       (clojure.core/let
                                        [?right VAL__105818]
                                        (if
                                         (clojure.core/map?
                                          input__105355_nth_3__)
                                         (clojure.core/let
                                          [VAL__105819
                                           (.valAt
                                            input__105355_nth_3__
                                            :context)]
                                          (clojure.core/case
                                           VAL__105819
                                           (:string)
                                           (clojure.core/let
                                            [?env
                                             input__105355_nth_3__]
                                            (try
                                             [(clojure.core/let
                                               [CATA_RESULT__9229__auto__
                                                (CATA__FN__105414
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
                                           (state__106625)))
                                         (state__106625))))))
                                    (state__106625)))
                                  (state__106625)))
                                (state__106625)))
                              (state__106625)))
                            (state__106625))))
                         (state__106625)))
                       (state__106625)))
                     (state__106625))
                    (state__106625))))
                 (state__106625
                  []
                  (clojure.core/case
                   input__105355_nth_0__
                   (meander.dev.parse.zeta/make-join)
                   (if
                    (clojure.core/map? input__105355_nth_1__)
                    (clojure.core/let
                     [VAL__106523 (.valAt input__105355_nth_1__ :tag)]
                     (clojure.core/case
                      VAL__106523
                      (:cat)
                      (clojure.core/let
                       [?ast input__105355_nth_1__]
                       (if
                        (clojure.core/map? input__105355_nth_2__)
                        (clojure.core/let
                         [VAL__105823
                          (.valAt input__105355_nth_2__ :tag)]
                         (clojure.core/case
                          VAL__105823
                          (:cat)
                          (clojure.core/let
                           [VAL__105824
                            (.valAt input__105355_nth_2__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__105824]
                            (clojure.core/let
                             [VAL__105825
                              (.valAt input__105355_nth_2__ :next)]
                             (clojure.core/let
                              [?next VAL__105825]
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
                          (state__106626)))
                        (state__106626)))
                      (:string-cat)
                      (clojure.core/let
                       [?ast input__105355_nth_1__]
                       (if
                        (clojure.core/map? input__105355_nth_2__)
                        (clojure.core/let
                         [VAL__105829
                          (.valAt input__105355_nth_2__ :tag)]
                         (clojure.core/case
                          VAL__105829
                          (:string-cat)
                          (clojure.core/let
                           [VAL__105830
                            (.valAt input__105355_nth_2__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__105830]
                            (clojure.core/let
                             [VAL__105831
                              (.valAt input__105355_nth_2__ :next)]
                             (clojure.core/let
                              [?next VAL__105831]
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
                          (state__106626)))
                        (state__106626)))
                      (state__106626)))
                    (state__106626))
                   (state__106626)))
                 (state__106626
                  []
                  (clojure.core/let
                   [input__105355_nth_3__
                    (clojure.core/nth input__105355 3)]
                   (clojure.core/case
                    input__105355_nth_0__
                    (meander.dev.parse.zeta/make-join)
                    (clojure.core/letfn
                     [(state__106640
                       []
                       (if
                        (clojure.core/map? input__105355_nth_1__)
                        (clojure.core/let
                         [VAL__106524
                          (.valAt input__105355_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__106524
                          (:string-cat)
                          (clojure.core/let
                           [VAL__105835
                            (.valAt input__105355_nth_1__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__105835]
                            (clojure.core/let
                             [VAL__105836
                              (.valAt input__105355_nth_1__ :next)]
                             (if
                              (clojure.core/map? VAL__105836)
                              (clojure.core/let
                               [VAL__105837 (.valAt VAL__105836 :tag)]
                               (clojure.core/case
                                VAL__105837
                                (:empty)
                                (clojure.core/let
                                 [?right input__105355_nth_2__]
                                 (clojure.core/let
                                  [?env input__105355_nth_3__]
                                  (try
                                   [(clojure.core/let
                                     [CATA_RESULT__9229__auto__
                                      (CATA__FN__105414
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
                                (state__106641)))
                              (state__106641)))))
                          (:string-star)
                          (clojure.core/let
                           [VAL__105841
                            (.valAt input__105355_nth_1__ :pattern)]
                           (clojure.core/let
                            [?pattern VAL__105841]
                            (clojure.core/let
                             [VAL__105842
                              (.valAt input__105355_nth_1__ :next)]
                             (if
                              (clojure.core/map? VAL__105842)
                              (clojure.core/let
                               [VAL__105843 (.valAt VAL__105842 :tag)]
                               (clojure.core/case
                                VAL__105843
                                (:empty)
                                (clojure.core/let
                                 [?right input__105355_nth_2__]
                                 (if
                                  (clojure.core/map?
                                   input__105355_nth_3__)
                                  (clojure.core/let
                                   [VAL__105844
                                    (.valAt
                                     input__105355_nth_3__
                                     :context)]
                                   (clojure.core/case
                                    VAL__105844
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
                                    (state__106641)))
                                  (state__106641)))
                                (state__106641)))
                              (state__106641)))))
                          (:string-join)
                          (clojure.core/let
                           [VAL__105848
                            (.valAt input__105355_nth_1__ :left)]
                           (clojure.core/let
                            [?left VAL__105848]
                            (clojure.core/let
                             [VAL__105849
                              (.valAt input__105355_nth_1__ :right)]
                             (clojure.core/let
                              [?right-1 VAL__105849]
                              (clojure.core/let
                               [?right-2 input__105355_nth_2__]
                               (if
                                (clojure.core/map?
                                 input__105355_nth_3__)
                                (clojure.core/let
                                 [VAL__105850
                                  (.valAt
                                   input__105355_nth_3__
                                   :context)]
                                 (clojure.core/case
                                  VAL__105850
                                  (:string)
                                  (clojure.core/let
                                   [?env input__105355_nth_3__]
                                   (try
                                    [{:tag :string-join,
                                      :left ?left,
                                      :right
                                      (clojure.core/let
                                       [CATA_RESULT__9229__auto__
                                        (CATA__FN__105414
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
                                  (state__106641)))
                                (state__106641)))))))
                          (state__106641)))
                        (state__106641)))
                      (state__106641
                       []
                       (clojure.core/let
                        [?left input__105355_nth_1__]
                        (if
                         (clojure.core/map? input__105355_nth_2__)
                         (clojure.core/let
                          [VAL__105853
                           (.valAt input__105355_nth_2__ :tag)]
                          (clojure.core/case
                           VAL__105853
                           (:empty)
                           (clojure.core/let
                            [?env input__105355_nth_3__]
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
                           (state__106642)))
                         (state__106642))))
                      (state__106642
                       []
                       (if
                        (clojure.core/map? input__105355_nth_1__)
                        (clojure.core/let
                         [VAL__105856
                          (.valAt input__105355_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__105856
                          (:empty)
                          (clojure.core/let
                           [?right input__105355_nth_2__]
                           (clojure.core/let
                            [?env input__105355_nth_3__]
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
                          (state__106643)))
                        (state__106643)))
                      (state__106643
                       []
                       (clojure.core/let
                        [?left input__105355_nth_1__]
                        (clojure.core/let
                         [?right input__105355_nth_2__]
                         (clojure.core/letfn
                          [(state__106644
                            []
                            (if
                             (clojure.core/map? input__105355_nth_3__)
                             (clojure.core/let
                              [VAL__105859
                               (.valAt input__105355_nth_3__ :context)]
                              (clojure.core/case
                               VAL__105859
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
                               (state__106645)))
                             (state__106645)))
                           (state__106645
                            []
                            (clojure.core/let
                             [?env input__105355_nth_3__]
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
                          (state__106644)))))]
                     (state__106640))
                    (state__106619))))]
                (state__106620)))
              (state__106619)))
            (state__106619
             []
             (if
              (clojure.core/= (clojure.core/count input__105355) 3)
              (clojure.core/let
               [input__105355_nth_0__
                (clojure.core/nth input__105355 0)
                input__105355_nth_1__
                (clojure.core/nth input__105355 1)
                input__105355_nth_2__
                (clojure.core/nth input__105355 2)]
               (clojure.core/case
                input__105355_nth_0__
                (meander.dev.parse.zeta/parse-entries)
                (if
                 (clojure.core/map? input__105355_nth_1__)
                 (clojure.core/let
                  [VAL__105864
                   (.valAt input__105355_nth_1__ :meander.zeta/as)]
                  (clojure.core/let
                   [?pattern VAL__105864]
                   (clojure.core/let
                    [X__105866
                     ((clojure.core/fn
                       [m__6993__auto__]
                       (clojure.core/dissoc
                        m__6993__auto__
                        :meander.zeta/as))
                      input__105355_nth_1__)]
                    (clojure.core/let
                     [?rest X__105866]
                     (clojure.core/letfn
                      [(save__105867 [] (state__106536))
                       (f__106646
                        []
                        (clojure.core/let
                         [?env input__105355_nth_2__]
                         (try
                          [{:tag :as,
                            :pattern
                            (clojure.core/let
                             [CATA_RESULT__9229__auto__
                              (CATA__FN__105414 [?pattern ?env])]
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
                              (CATA__FN__105414 [?rest ?env])]
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
                       (clojure.core/= ?rest input__105355_nth_1__)
                       (save__105867)
                       (f__106646)))))))
                 (state__106536))
                (state__106536)))
              (state__106536)))]
           (state__106617))
          (state__106536)))
        (state__106536
         []
         (clojure.core/letfn
          [(def__105870
            [arg__105903 ?ns]
            (clojure.core/letfn
             [(state__106647
               []
               (clojure.core/let
                [x__105904 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__105904)
                 (clojure.core/let [?env arg__105903] [?env ?ns])
                 (state__106648))))
              (state__106648
               []
               (if
                (clojure.core/map? arg__105903)
                (clojure.core/let
                 [VAL__105905 (.valAt arg__105903 :aliases)]
                 (if
                  (clojure.core/map? VAL__105905)
                  (clojure.core/let
                   [X__105907 (clojure.core/set VAL__105905)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__105907))
                    (clojure.core/loop
                     [search_space__106649
                      (clojure.core/seq X__105907)]
                     (if
                      (clojure.core/seq search_space__106649)
                      (clojure.core/let
                       [elem__105908
                        (clojure.core/first search_space__106649)
                        result__106650
                        (clojure.core/let
                         [elem__105908_nth_0__
                          (clojure.core/nth elem__105908 0)
                          elem__105908_nth_1__
                          (clojure.core/nth elem__105908 1)]
                         (if
                          (clojure.core/symbol? elem__105908_nth_0__)
                          (clojure.core/let
                           [X__105910
                            (clojure.core/name elem__105908_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__105910)
                            (if
                             (clojure.core/symbol?
                              elem__105908_nth_1__)
                             (clojure.core/let
                              [X__105912
                               (clojure.core/name
                                elem__105908_nth_1__)]
                              (clojure.core/case
                               X__105912
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__105903]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__106650)
                        (recur
                         (clojure.core/next search_space__106649))
                        result__106650))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__106647)))]
          (if
           (clojure.core/vector? input__105355)
           (if
            (clojure.core/= (clojure.core/count input__105355) 3)
            (clojure.core/let
             [input__105355_nth_0__
              (clojure.core/nth input__105355 0)
              input__105355_nth_1__
              (clojure.core/nth input__105355 1)
              input__105355_nth_2__
              (clojure.core/nth input__105355 2)]
             (clojure.core/case
              input__105355_nth_0__
              (meander.dev.parse.zeta/parse-entries)
              (if
               (clojure.core/map? input__105355_nth_1__)
               (clojure.core/let
                [X__105875 (clojure.core/set input__105355_nth_1__)]
                (if
                 (clojure.core/<= 1 (clojure.core/count X__105875))
                 (clojure.core/loop
                  [search_space__106652 (clojure.core/seq X__105875)]
                  (if
                   (clojure.core/seq search_space__106652)
                   (clojure.core/let
                    [elem__105876
                     (clojure.core/first search_space__106652)
                     result__106653
                     (clojure.core/let
                      [elem__105876_nth_0__
                       (clojure.core/nth elem__105876 0)
                       elem__105876_nth_1__
                       (clojure.core/nth elem__105876 1)]
                      (clojure.core/let
                       [*m__105381 elem__105876_nth_0__]
                       (if
                        (clojure.core/symbol? elem__105876_nth_0__)
                        (clojure.core/let
                         [X__105878
                          (clojure.core/namespace
                           elem__105876_nth_0__)]
                         (clojure.core/let
                          [?ns X__105878]
                          (clojure.core/let
                           [X__105880
                            (clojure.core/name elem__105876_nth_0__)]
                           (if
                            (clojure.core/string? X__105880)
                            (if
                             (clojure.core/re-matches #"&.*" X__105880)
                             (clojure.core/let
                              [?pattern elem__105876_nth_1__]
                              (clojure.core/let
                               [X__105882
                                ((clojure.core/fn
                                  [m__6993__auto__]
                                  (clojure.core/dissoc
                                   m__6993__auto__
                                   *m__105381))
                                 input__105355_nth_1__)]
                               (clojure.core/let
                                [?rest X__105882]
                                (clojure.core/let
                                 [x__7926__auto__
                                  (def__105870
                                   input__105355_nth_2__
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
                                        (CATA__FN__105414
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
                                        (CATA__FN__105414
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
                     (meander.runtime.zeta/fail? result__106653)
                     (recur (clojure.core/next search_space__106652))
                     result__106653))
                   (state__106537)))
                 (state__106537)))
               (state__106537))
              (state__106537)))
            (state__106537))
           (state__106537))))
        (state__106537
         []
         (if
          (clojure.core/vector? input__105355)
          (clojure.core/letfn
           [(state__106655
             []
             (if
              (clojure.core/= (clojure.core/count input__105355) 3)
              (clojure.core/let
               [input__105355_nth_0__
                (clojure.core/nth input__105355 0)
                input__105355_nth_1__
                (clojure.core/nth input__105355 1)
                input__105355_nth_2__
                (clojure.core/nth input__105355 2)]
               (clojure.core/case
                input__105355_nth_0__
                (meander.dev.parse.zeta/parse-entries)
                (clojure.core/letfn
                 [(state__106657
                   []
                   (if
                    (clojure.core/map? input__105355_nth_1__)
                    (clojure.core/let
                     [X__105926
                      (clojure.core/set input__105355_nth_1__)]
                     (if
                      (clojure.core/<=
                       1
                       (clojure.core/count X__105926))
                      (clojure.core/loop
                       [search_space__106659
                        (clojure.core/seq X__105926)]
                       (if
                        (clojure.core/seq search_space__106659)
                        (clojure.core/let
                         [elem__105927
                          (clojure.core/first search_space__106659)
                          result__106660
                          (clojure.core/let
                           [elem__105927_nth_0__
                            (clojure.core/nth elem__105927 0)
                            elem__105927_nth_1__
                            (clojure.core/nth elem__105927 1)]
                           (clojure.core/let
                            [?key-pattern elem__105927_nth_0__]
                            (clojure.core/let
                             [?val-pattern elem__105927_nth_1__]
                             (clojure.core/let
                              [X__105929
                               ((clojure.core/fn
                                 [m__6993__auto__]
                                 (clojure.core/dissoc
                                  m__6993__auto__
                                  ?key-pattern))
                                input__105355_nth_1__)]
                              (clojure.core/let
                               [?rest X__105929]
                               (clojure.core/let
                                [?env input__105355_nth_2__]
                                (try
                                 [{:tag :entry,
                                   :key-pattern
                                   (clojure.core/let
                                    [CATA_RESULT__9229__auto__
                                     (CATA__FN__105414
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
                                     (CATA__FN__105414
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
                                     (CATA__FN__105414
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
                          (meander.runtime.zeta/fail? result__106660)
                          (recur
                           (clojure.core/next search_space__106659))
                          result__106660))
                        (state__106658)))
                      (state__106658)))
                    (state__106658)))
                  (state__106658
                   []
                   (if
                    (clojure.core/map? input__105355_nth_1__)
                    (clojure.core/let
                     [?env input__105355_nth_2__]
                     (try
                      [{:tag :some-map}]
                      (catch
                       java.lang.Exception
                       e__10169__auto__
                       (if
                        (meander.runtime.zeta/fail? e__10169__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__10169__auto__)))))
                    (state__106656)))]
                 (state__106657))
                (meander.dev.parse.zeta/parse-with-bindings)
                (clojure.core/letfn
                 [(state__106662
                   []
                   (if
                    (clojure.core/vector? input__105355_nth_1__)
                    (clojure.core/case
                     input__105355_nth_1__
                     ([])
                     (clojure.core/let
                      [?env input__105355_nth_2__]
                      (try
                       [[]]
                       (catch
                        java.lang.Exception
                        e__10169__auto__
                        (if
                         (meander.runtime.zeta/fail? e__10169__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__10169__auto__)))))
                     (state__106663))
                    (state__106663)))
                  (state__106663
                   []
                   (if
                    (clojure.core/vector? input__105355_nth_1__)
                    (if
                     (clojure.core/=
                      (clojure.core/count input__105355_nth_1__)
                      1)
                     (clojure.core/let
                      [?env input__105355_nth_2__]
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
                     (state__106664))
                    (state__106664)))
                  (state__106664
                   []
                   (if
                    (clojure.core/vector? input__105355_nth_1__)
                    (clojure.core/let
                     [input__105355_nth_1___l__
                      (clojure.core/subvec
                       input__105355_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__105355_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__105355_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__105355_nth_1___r__
                        (clojure.core/subvec input__105355_nth_1__ 2)]
                       (clojure.core/let
                        [input__105355_nth_1___l___nth_0__
                         (clojure.core/nth input__105355_nth_1___l__ 0)
                         input__105355_nth_1___l___nth_1__
                         (clojure.core/nth
                          input__105355_nth_1___l__
                          1)]
                        (if
                         (clojure.core/symbol?
                          input__105355_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__105943
                           (clojure.core/namespace
                            input__105355_nth_1___l___nth_0__)]
                          (clojure.core/let
                           [X__105945
                            (clojure.core/name
                             input__105355_nth_1___l___nth_0__)]
                           (if
                            (clojure.core/string? X__105945)
                            (if
                             (clojure.core/re-matches #"%.+" X__105945)
                             (clojure.core/let
                              [?symbol
                               input__105355_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?pattern
                                input__105355_nth_1___l___nth_1__]
                               (clojure.core/let
                                [?rest input__105355_nth_1___r__]
                                (clojure.core/let
                                 [?env input__105355_nth_2__]
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
                                         (CATA__FN__105414
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
                                       (CATA__FN__105414
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
                             (state__106665))
                            (state__106665))))
                         (state__106665))))
                      (state__106665)))
                    (state__106665)))
                  (state__106665
                   []
                   (if
                    (clojure.core/vector? input__105355_nth_1__)
                    (clojure.core/let
                     [input__105355_nth_1___l__
                      (clojure.core/subvec
                       input__105355_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__105355_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__105355_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__105355_nth_1___r__
                        (clojure.core/subvec input__105355_nth_1__ 2)]
                       (clojure.core/let
                        [input__105355_nth_1___l___nth_0__
                         (clojure.core/nth input__105355_nth_1___l__ 0)
                         input__105355_nth_1___l___nth_1__
                         (clojure.core/nth
                          input__105355_nth_1___l__
                          1)]
                        (clojure.core/let
                         [?x input__105355_nth_1___l___nth_0__]
                         (clojure.core/let
                          [?pattern input__105355_nth_1___l___nth_1__]
                          (clojure.core/let
                           [?rest input__105355_nth_1___r__]
                           (clojure.core/let
                            [?env input__105355_nth_2__]
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
                      (state__106656)))
                    (state__106656)))]
                 (state__106662))
                (state__106656)))
              (state__106656)))
            (state__106656
             []
             (if
              (clojure.core/= (clojure.core/count input__105355) 2)
              (clojure.core/let
               [input__105355_nth_0__
                (clojure.core/nth input__105355 0)
                input__105355_nth_1__
                (clojure.core/nth input__105355 1)]
               (if
                (clojure.core/vector? input__105355_nth_0__)
                (clojure.core/let
                 [?sequence input__105355_nth_0__]
                 (clojure.core/let
                  [?form input__105355_nth_0__]
                  (clojure.core/let
                   [?env input__105355_nth_1__]
                   (try
                    [{:tag :vector,
                      :next
                      (clojure.core/let
                       [CATA_RESULT__9229__auto__
                        (CATA__FN__105414
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
                (state__106538)))
              (state__106538)))]
           (state__106655))
          (state__106538)))
        (state__106538
         []
         (clojure.core/letfn
          [(def__105955
            [arg__105978 ?__105356]
            (clojure.core/letfn
             [(state__106666
               []
               (clojure.core/let
                [x__105979 "meander.zeta"]
                (if
                 (clojure.core/= ?__105356 x__105979)
                 [?__105356]
                 (state__106667))))
              (state__106667
               []
               (if
                (clojure.core/map? arg__105978)
                (clojure.core/let
                 [VAL__105980 (.valAt arg__105978 :aliases)]
                 (if
                  (clojure.core/map? VAL__105980)
                  (clojure.core/let
                   [X__105982 (clojure.core/set VAL__105980)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__105982))
                    (clojure.core/loop
                     [search_space__106668
                      (clojure.core/seq X__105982)]
                     (if
                      (clojure.core/seq search_space__106668)
                      (clojure.core/let
                       [elem__105983
                        (clojure.core/first search_space__106668)
                        result__106669
                        (clojure.core/let
                         [elem__105983_nth_0__
                          (clojure.core/nth elem__105983 0)
                          elem__105983_nth_1__
                          (clojure.core/nth elem__105983 1)]
                         (if
                          (clojure.core/symbol? elem__105983_nth_0__)
                          (clojure.core/let
                           [X__105985
                            (clojure.core/name elem__105983_nth_0__)]
                           (if
                            (clojure.core/= ?__105356 X__105985)
                            (if
                             (clojure.core/symbol?
                              elem__105983_nth_1__)
                             (clojure.core/let
                              [X__105987
                               (clojure.core/name
                                elem__105983_nth_1__)]
                              (clojure.core/case
                               X__105987
                               ("meander.zeta")
                               [?__105356]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__106669)
                        (recur
                         (clojure.core/next search_space__106668))
                        result__106669))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__106666)))]
          (if
           (clojure.core/vector? input__105355)
           (if
            (clojure.core/= (clojure.core/count input__105355) 2)
            (clojure.core/let
             [input__105355_nth_0__
              (clojure.core/nth input__105355 0)
              input__105355_nth_1__
              (clojure.core/nth input__105355 1)]
             (if
              (clojure.core/seq? input__105355_nth_0__)
              (clojure.core/let
               [input__105355_nth_0___l__
                (clojure.core/take 1 input__105355_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__105355_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__105355_nth_0___r__
                  (clojure.core/drop 1 input__105355_nth_0__)]
                 (clojure.core/let
                  [input__105355_nth_0___l___nth_0__
                   (clojure.core/nth input__105355_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__105355_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__105965
                     (clojure.core/namespace
                      input__105355_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__105356 X__105965]
                     (clojure.core/let
                      [X__105967
                       (clojure.core/name
                        input__105355_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__105967
                       ("<>")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__105955 input__105355_nth_1__ ?__105356)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__106539)
                         (clojure.core/let
                          [[?__105356] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__105355)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__105355)
                             2)
                            (clojure.core/let
                             [input__105355_nth_0__
                              (clojure.core/nth input__105355 0)
                              input__105355_nth_1__
                              (clojure.core/nth input__105355 1)]
                             (if
                              (clojure.core/seq? input__105355_nth_0__)
                              (clojure.core/let
                               [input__105355_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__105355_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__105355_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__105355_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__105355_nth_0__)]
                                 (clojure.core/let
                                  [?patterns input__105355_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__105355_nth_0__]
                                   (clojure.core/let
                                    [?env input__105355_nth_1__]
                                    (try
                                     [{:tag :group,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__105414
                                          ['meander.dev.parse.zeta/parse-sequential
                                           (clojure.core/into
                                            []
                                            ?patterns)
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
                                       (throw e__10169__auto__))))))))
                                (state__106539)))
                              (state__106539)))
                            (state__106539))
                           (state__106539)))))
                       (state__106539)))))
                   (state__106539))))
                (state__106539)))
              (state__106539)))
            (state__106539))
           (state__106539))))
        (state__106539
         []
         (clojure.core/letfn
          [(def__105989
            [arg__106012 ?__105357]
            (clojure.core/letfn
             [(state__106671
               []
               (clojure.core/let
                [x__106013 "meander.zeta"]
                (if
                 (clojure.core/= ?__105357 x__106013)
                 [?__105357]
                 (state__106672))))
              (state__106672
               []
               (if
                (clojure.core/map? arg__106012)
                (clojure.core/let
                 [VAL__106014 (.valAt arg__106012 :aliases)]
                 (if
                  (clojure.core/map? VAL__106014)
                  (clojure.core/let
                   [X__106016 (clojure.core/set VAL__106014)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__106016))
                    (clojure.core/loop
                     [search_space__106673
                      (clojure.core/seq X__106016)]
                     (if
                      (clojure.core/seq search_space__106673)
                      (clojure.core/let
                       [elem__106017
                        (clojure.core/first search_space__106673)
                        result__106674
                        (clojure.core/let
                         [elem__106017_nth_0__
                          (clojure.core/nth elem__106017 0)
                          elem__106017_nth_1__
                          (clojure.core/nth elem__106017 1)]
                         (if
                          (clojure.core/symbol? elem__106017_nth_0__)
                          (clojure.core/let
                           [X__106019
                            (clojure.core/name elem__106017_nth_0__)]
                           (if
                            (clojure.core/= ?__105357 X__106019)
                            (if
                             (clojure.core/symbol?
                              elem__106017_nth_1__)
                             (clojure.core/let
                              [X__106021
                               (clojure.core/name
                                elem__106017_nth_1__)]
                              (clojure.core/case
                               X__106021
                               ("meander.zeta")
                               [?__105357]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__106674)
                        (recur
                         (clojure.core/next search_space__106673))
                        result__106674))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__106671)))]
          (if
           (clojure.core/vector? input__105355)
           (if
            (clojure.core/= (clojure.core/count input__105355) 2)
            (clojure.core/let
             [input__105355_nth_0__
              (clojure.core/nth input__105355 0)
              input__105355_nth_1__
              (clojure.core/nth input__105355 1)]
             (if
              (clojure.core/seq? input__105355_nth_0__)
              (clojure.core/let
               [input__105355_nth_0___l__
                (clojure.core/take 1 input__105355_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__105355_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__105355_nth_0___r__
                  (clojure.core/drop 1 input__105355_nth_0__)]
                 (clojure.core/let
                  [input__105355_nth_0___l___nth_0__
                   (clojure.core/nth input__105355_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__105355_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__105999
                     (clojure.core/namespace
                      input__105355_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__105357 X__105999]
                     (clojure.core/let
                      [X__106001
                       (clojure.core/name
                        input__105355_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__106001
                       ("with")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__105989 input__105355_nth_1__ ?__105357)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__106540)
                         (clojure.core/let
                          [[?__105357] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__105355)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__105355)
                             2)
                            (clojure.core/let
                             [input__105355_nth_0__
                              (clojure.core/nth input__105355 0)
                              input__105355_nth_1__
                              (clojure.core/nth input__105355 1)]
                             (if
                              (clojure.core/seq? input__105355_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__105355_nth_0__)
                                3)
                               (clojure.core/let
                                [input__105355_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__105355_nth_0__
                                  1)
                                 input__105355_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__105355_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?bindings
                                  input__105355_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?body input__105355_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__105355_nth_0__]
                                   (clojure.core/let
                                    [?env input__105355_nth_1__]
                                    (try
                                     [{:tag :with,
                                       :bindings
                                       {:tag :with-bindings,
                                        :bindings
                                        (clojure.core/let
                                         [CATA_RESULT__9229__auto__
                                          (CATA__FN__105414
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
                                         (CATA__FN__105414
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
                               (state__106540))
                              (state__106540)))
                            (state__106540))
                           (state__106540)))))
                       (state__106540)))))
                   (state__106540))))
                (state__106540)))
              (state__106540)))
            (state__106540))
           (state__106540))))
        (state__106540
         []
         (clojure.core/letfn
          [(def__106023
            [arg__106046 ?__105358]
            (clojure.core/letfn
             [(state__106676
               []
               (clojure.core/let
                [x__106047 "meander.zeta"]
                (if
                 (clojure.core/= ?__105358 x__106047)
                 [?__105358]
                 (state__106677))))
              (state__106677
               []
               (if
                (clojure.core/map? arg__106046)
                (clojure.core/let
                 [VAL__106048 (.valAt arg__106046 :aliases)]
                 (if
                  (clojure.core/map? VAL__106048)
                  (clojure.core/let
                   [X__106050 (clojure.core/set VAL__106048)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__106050))
                    (clojure.core/loop
                     [search_space__106678
                      (clojure.core/seq X__106050)]
                     (if
                      (clojure.core/seq search_space__106678)
                      (clojure.core/let
                       [elem__106051
                        (clojure.core/first search_space__106678)
                        result__106679
                        (clojure.core/let
                         [elem__106051_nth_0__
                          (clojure.core/nth elem__106051 0)
                          elem__106051_nth_1__
                          (clojure.core/nth elem__106051 1)]
                         (if
                          (clojure.core/symbol? elem__106051_nth_0__)
                          (clojure.core/let
                           [X__106053
                            (clojure.core/name elem__106051_nth_0__)]
                           (if
                            (clojure.core/= ?__105358 X__106053)
                            (if
                             (clojure.core/symbol?
                              elem__106051_nth_1__)
                             (clojure.core/let
                              [X__106055
                               (clojure.core/name
                                elem__106051_nth_1__)]
                              (clojure.core/case
                               X__106055
                               ("meander.zeta")
                               [?__105358]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__106679)
                        (recur
                         (clojure.core/next search_space__106678))
                        result__106679))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__106676)))]
          (if
           (clojure.core/vector? input__105355)
           (if
            (clojure.core/= (clojure.core/count input__105355) 2)
            (clojure.core/let
             [input__105355_nth_0__
              (clojure.core/nth input__105355 0)
              input__105355_nth_1__
              (clojure.core/nth input__105355 1)]
             (if
              (clojure.core/seq? input__105355_nth_0__)
              (clojure.core/let
               [input__105355_nth_0___l__
                (clojure.core/take 1 input__105355_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__105355_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__105355_nth_0___r__
                  (clojure.core/drop 1 input__105355_nth_0__)]
                 (clojure.core/let
                  [input__105355_nth_0___l___nth_0__
                   (clojure.core/nth input__105355_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__105355_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__106033
                     (clojure.core/namespace
                      input__105355_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__105358 X__106033]
                     (clojure.core/let
                      [X__106035
                       (clojure.core/name
                        input__105355_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__106035
                       ("apply")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__106023 input__105355_nth_1__ ?__105358)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__106541)
                         (clojure.core/let
                          [[?__105358] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__105355)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__105355)
                             2)
                            (clojure.core/let
                             [input__105355_nth_0__
                              (clojure.core/nth input__105355 0)
                              input__105355_nth_1__
                              (clojure.core/nth input__105355 1)]
                             (if
                              (clojure.core/seq? input__105355_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__105355_nth_0__)
                                3)
                               (clojure.core/let
                                [input__105355_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__105355_nth_0__
                                  1)
                                 input__105355_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__105355_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?fn input__105355_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?pattern
                                   input__105355_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__105355_nth_0__]
                                   (clojure.core/let
                                    [?env input__105355_nth_1__]
                                    (try
                                     [{:tag :apply,
                                       :fn ?fn,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__105414
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
                               (state__106541))
                              (state__106541)))
                            (state__106541))
                           (state__106541)))))
                       (state__106541)))))
                   (state__106541))))
                (state__106541)))
              (state__106541)))
            (state__106541))
           (state__106541))))
        (state__106541
         []
         (clojure.core/letfn
          [(def__106057
            [arg__106080 ?__105359]
            (clojure.core/letfn
             [(state__106681
               []
               (clojure.core/let
                [x__106081 "meander.zeta"]
                (if
                 (clojure.core/= ?__105359 x__106081)
                 [?__105359]
                 (state__106682))))
              (state__106682
               []
               (if
                (clojure.core/map? arg__106080)
                (clojure.core/let
                 [VAL__106082 (.valAt arg__106080 :aliases)]
                 (if
                  (clojure.core/map? VAL__106082)
                  (clojure.core/let
                   [X__106084 (clojure.core/set VAL__106082)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__106084))
                    (clojure.core/loop
                     [search_space__106683
                      (clojure.core/seq X__106084)]
                     (if
                      (clojure.core/seq search_space__106683)
                      (clojure.core/let
                       [elem__106085
                        (clojure.core/first search_space__106683)
                        result__106684
                        (clojure.core/let
                         [elem__106085_nth_0__
                          (clojure.core/nth elem__106085 0)
                          elem__106085_nth_1__
                          (clojure.core/nth elem__106085 1)]
                         (if
                          (clojure.core/symbol? elem__106085_nth_0__)
                          (clojure.core/let
                           [X__106087
                            (clojure.core/name elem__106085_nth_0__)]
                           (if
                            (clojure.core/= ?__105359 X__106087)
                            (if
                             (clojure.core/symbol?
                              elem__106085_nth_1__)
                             (clojure.core/let
                              [X__106089
                               (clojure.core/name
                                elem__106085_nth_1__)]
                              (clojure.core/case
                               X__106089
                               ("meander.zeta")
                               [?__105359]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__106684)
                        (recur
                         (clojure.core/next search_space__106683))
                        result__106684))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__106681)))]
          (if
           (clojure.core/vector? input__105355)
           (if
            (clojure.core/= (clojure.core/count input__105355) 2)
            (clojure.core/let
             [input__105355_nth_0__
              (clojure.core/nth input__105355 0)
              input__105355_nth_1__
              (clojure.core/nth input__105355 1)]
             (if
              (clojure.core/seq? input__105355_nth_0__)
              (clojure.core/let
               [input__105355_nth_0___l__
                (clojure.core/take 1 input__105355_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__105355_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__105355_nth_0___r__
                  (clojure.core/drop 1 input__105355_nth_0__)]
                 (clojure.core/let
                  [input__105355_nth_0___l___nth_0__
                   (clojure.core/nth input__105355_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__105355_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__106067
                     (clojure.core/namespace
                      input__105355_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__105359 X__106067]
                     (clojure.core/let
                      [X__106069
                       (clojure.core/name
                        input__105355_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__106069
                       ("and")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__106057 input__105355_nth_1__ ?__105359)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__106542)
                         (clojure.core/let
                          [[?__105359] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__105355)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__105355)
                             2)
                            (clojure.core/let
                             [input__105355_nth_0__
                              (clojure.core/nth input__105355 0)
                              input__105355_nth_1__
                              (clojure.core/nth input__105355 1)]
                             (if
                              (clojure.core/seq? input__105355_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__105355_nth_0__)
                                3)
                               (clojure.core/let
                                [input__105355_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__105355_nth_0__
                                  1)
                                 input__105355_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__105355_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?left input__105355_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?right
                                   input__105355_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__105355_nth_0__]
                                   (clojure.core/let
                                    [?env input__105355_nth_1__]
                                    (try
                                     [{:tag :and,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__105414
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
                                         (CATA__FN__105414
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
                               (state__106542))
                              (state__106542)))
                            (state__106542))
                           (state__106542)))))
                       (state__106542)))))
                   (state__106542))))
                (state__106542)))
              (state__106542)))
            (state__106542))
           (state__106542))))
        (state__106542
         []
         (clojure.core/letfn
          [(def__106091
            [arg__106114 ?__105360]
            (clojure.core/letfn
             [(state__106686
               []
               (clojure.core/let
                [x__106115 "meander.zeta"]
                (if
                 (clojure.core/= ?__105360 x__106115)
                 [?__105360]
                 (state__106687))))
              (state__106687
               []
               (if
                (clojure.core/map? arg__106114)
                (clojure.core/let
                 [VAL__106116 (.valAt arg__106114 :aliases)]
                 (if
                  (clojure.core/map? VAL__106116)
                  (clojure.core/let
                   [X__106118 (clojure.core/set VAL__106116)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__106118))
                    (clojure.core/loop
                     [search_space__106688
                      (clojure.core/seq X__106118)]
                     (if
                      (clojure.core/seq search_space__106688)
                      (clojure.core/let
                       [elem__106119
                        (clojure.core/first search_space__106688)
                        result__106689
                        (clojure.core/let
                         [elem__106119_nth_0__
                          (clojure.core/nth elem__106119 0)
                          elem__106119_nth_1__
                          (clojure.core/nth elem__106119 1)]
                         (if
                          (clojure.core/symbol? elem__106119_nth_0__)
                          (clojure.core/let
                           [X__106121
                            (clojure.core/name elem__106119_nth_0__)]
                           (if
                            (clojure.core/= ?__105360 X__106121)
                            (if
                             (clojure.core/symbol?
                              elem__106119_nth_1__)
                             (clojure.core/let
                              [X__106123
                               (clojure.core/name
                                elem__106119_nth_1__)]
                              (clojure.core/case
                               X__106123
                               ("meander.zeta")
                               [?__105360]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__106689)
                        (recur
                         (clojure.core/next search_space__106688))
                        result__106689))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__106686)))]
          (if
           (clojure.core/vector? input__105355)
           (if
            (clojure.core/= (clojure.core/count input__105355) 2)
            (clojure.core/let
             [input__105355_nth_0__
              (clojure.core/nth input__105355 0)
              input__105355_nth_1__
              (clojure.core/nth input__105355 1)]
             (if
              (clojure.core/seq? input__105355_nth_0__)
              (clojure.core/let
               [input__105355_nth_0___l__
                (clojure.core/take 1 input__105355_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__105355_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__105355_nth_0___r__
                  (clojure.core/drop 1 input__105355_nth_0__)]
                 (clojure.core/let
                  [input__105355_nth_0___l___nth_0__
                   (clojure.core/nth input__105355_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__105355_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__106101
                     (clojure.core/namespace
                      input__105355_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__105360 X__106101]
                     (clojure.core/let
                      [X__106103
                       (clojure.core/name
                        input__105355_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__106103
                       ("cata")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__106091 input__105355_nth_1__ ?__105360)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__106543)
                         (clojure.core/let
                          [[?__105360] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__105355)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__105355)
                             2)
                            (clojure.core/let
                             [input__105355_nth_0__
                              (clojure.core/nth input__105355 0)
                              input__105355_nth_1__
                              (clojure.core/nth input__105355 1)]
                             (if
                              (clojure.core/seq? input__105355_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__105355_nth_0__)
                                2)
                               (clojure.core/let
                                [input__105355_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__105355_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__105355_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__105355_nth_0__]
                                  (clojure.core/let
                                   [?env input__105355_nth_1__]
                                   (try
                                    [{:tag :cata,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__9229__auto__
                                        (CATA__FN__105414
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
                               (state__106543))
                              (state__106543)))
                            (state__106543))
                           (state__106543)))))
                       (state__106543)))))
                   (state__106543))))
                (state__106543)))
              (state__106543)))
            (state__106543))
           (state__106543))))
        (state__106543
         []
         (clojure.core/letfn
          [(def__106125
            [arg__106148 ?__105361]
            (clojure.core/letfn
             [(state__106691
               []
               (clojure.core/let
                [x__106149 "meander.zeta"]
                (if
                 (clojure.core/= ?__105361 x__106149)
                 [?__105361]
                 (state__106692))))
              (state__106692
               []
               (if
                (clojure.core/map? arg__106148)
                (clojure.core/let
                 [VAL__106150 (.valAt arg__106148 :aliases)]
                 (if
                  (clojure.core/map? VAL__106150)
                  (clojure.core/let
                   [X__106152 (clojure.core/set VAL__106150)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__106152))
                    (clojure.core/loop
                     [search_space__106693
                      (clojure.core/seq X__106152)]
                     (if
                      (clojure.core/seq search_space__106693)
                      (clojure.core/let
                       [elem__106153
                        (clojure.core/first search_space__106693)
                        result__106694
                        (clojure.core/let
                         [elem__106153_nth_0__
                          (clojure.core/nth elem__106153 0)
                          elem__106153_nth_1__
                          (clojure.core/nth elem__106153 1)]
                         (if
                          (clojure.core/symbol? elem__106153_nth_0__)
                          (clojure.core/let
                           [X__106155
                            (clojure.core/name elem__106153_nth_0__)]
                           (if
                            (clojure.core/= ?__105361 X__106155)
                            (if
                             (clojure.core/symbol?
                              elem__106153_nth_1__)
                             (clojure.core/let
                              [X__106157
                               (clojure.core/name
                                elem__106153_nth_1__)]
                              (clojure.core/case
                               X__106157
                               ("meander.zeta")
                               [?__105361]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__106694)
                        (recur
                         (clojure.core/next search_space__106693))
                        result__106694))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__106691)))]
          (if
           (clojure.core/vector? input__105355)
           (if
            (clojure.core/= (clojure.core/count input__105355) 2)
            (clojure.core/let
             [input__105355_nth_0__
              (clojure.core/nth input__105355 0)
              input__105355_nth_1__
              (clojure.core/nth input__105355 1)]
             (if
              (clojure.core/seq? input__105355_nth_0__)
              (clojure.core/let
               [input__105355_nth_0___l__
                (clojure.core/take 1 input__105355_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__105355_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__105355_nth_0___r__
                  (clojure.core/drop 1 input__105355_nth_0__)]
                 (clojure.core/let
                  [input__105355_nth_0___l___nth_0__
                   (clojure.core/nth input__105355_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__105355_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__106135
                     (clojure.core/namespace
                      input__105355_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__105361 X__106135]
                     (clojure.core/let
                      [X__106137
                       (clojure.core/name
                        input__105355_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__106137
                       ("fold")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__106125 input__105355_nth_1__ ?__105361)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__106544)
                         (clojure.core/let
                          [[?__105361] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__105355)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__105355)
                             2)
                            (clojure.core/let
                             [input__105355_nth_0__
                              (clojure.core/nth input__105355 0)
                              input__105355_nth_1__
                              (clojure.core/nth input__105355 1)]
                             (if
                              (clojure.core/seq? input__105355_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 4)
                                 input__105355_nth_0__)
                                4)
                               (clojure.core/let
                                [input__105355_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__105355_nth_0__
                                  1)
                                 input__105355_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__105355_nth_0__
                                  2)
                                 input__105355_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__105355_nth_0__
                                  3)]
                                (clojure.core/let
                                 [?mutable-variable
                                  input__105355_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?initial-value
                                   input__105355_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?fold-function
                                    input__105355_nth_0___nth_3__]
                                   (clojure.core/let
                                    [?form input__105355_nth_0__]
                                    (clojure.core/let
                                     [?env input__105355_nth_1__]
                                     (try
                                      [(clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__105414
                                          ['meander.dev.parse.zeta/make-fold
                                           (clojure.core/let
                                            [CATA_RESULT__9229__auto__
                                             (CATA__FN__105414
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
                                             (CATA__FN__105414
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
                               (state__106544))
                              (state__106544)))
                            (state__106544))
                           (state__106544)))))
                       (state__106544)))))
                   (state__106544))))
                (state__106544)))
              (state__106544)))
            (state__106544))
           (state__106544))))
        (state__106544
         []
         (if
          (clojure.core/vector? input__105355)
          (if
           (clojure.core/= (clojure.core/count input__105355) 5)
           (clojure.core/let
            [input__105355_nth_0__
             (clojure.core/nth input__105355 0)
             input__105355_nth_1__
             (clojure.core/nth input__105355 1)
             input__105355_nth_2__
             (clojure.core/nth input__105355 2)
             input__105355_nth_3__
             (clojure.core/nth input__105355 3)
             input__105355_nth_4__
             (clojure.core/nth input__105355 4)]
            (clojure.core/case
             input__105355_nth_0__
             (meander.dev.parse.zeta/make-fold)
             (if
              (clojure.core/map? input__105355_nth_1__)
              (clojure.core/let
               [VAL__106160 (.valAt input__105355_nth_1__ :tag)]
               (clojure.core/case
                VAL__106160
                (:mutable-variable)
                (clojure.core/let
                 [?variable-ast input__105355_nth_1__]
                 (clojure.core/let
                  [?initial-value-ast input__105355_nth_2__]
                  (clojure.core/let
                   [?fold-function input__105355_nth_3__]
                   (clojure.core/let
                    [?form input__105355_nth_4__]
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
                (state__106545)))
              (state__106545))
             (state__106545)))
           (state__106545))
          (state__106545)))
        (state__106545
         []
         (clojure.core/letfn
          [(def__106162
            [arg__106185 ?__105362]
            (clojure.core/letfn
             [(state__106696
               []
               (clojure.core/let
                [x__106186 "meander.zeta"]
                (if
                 (clojure.core/= ?__105362 x__106186)
                 [?__105362]
                 (state__106697))))
              (state__106697
               []
               (if
                (clojure.core/map? arg__106185)
                (clojure.core/let
                 [VAL__106187 (.valAt arg__106185 :aliases)]
                 (if
                  (clojure.core/map? VAL__106187)
                  (clojure.core/let
                   [X__106189 (clojure.core/set VAL__106187)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__106189))
                    (clojure.core/loop
                     [search_space__106698
                      (clojure.core/seq X__106189)]
                     (if
                      (clojure.core/seq search_space__106698)
                      (clojure.core/let
                       [elem__106190
                        (clojure.core/first search_space__106698)
                        result__106699
                        (clojure.core/let
                         [elem__106190_nth_0__
                          (clojure.core/nth elem__106190 0)
                          elem__106190_nth_1__
                          (clojure.core/nth elem__106190 1)]
                         (if
                          (clojure.core/symbol? elem__106190_nth_0__)
                          (clojure.core/let
                           [X__106192
                            (clojure.core/name elem__106190_nth_0__)]
                           (if
                            (clojure.core/= ?__105362 X__106192)
                            (if
                             (clojure.core/symbol?
                              elem__106190_nth_1__)
                             (clojure.core/let
                              [X__106194
                               (clojure.core/name
                                elem__106190_nth_1__)]
                              (clojure.core/case
                               X__106194
                               ("meander.zeta")
                               [?__105362]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__106699)
                        (recur
                         (clojure.core/next search_space__106698))
                        result__106699))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__106696)))]
          (if
           (clojure.core/vector? input__105355)
           (if
            (clojure.core/= (clojure.core/count input__105355) 2)
            (clojure.core/let
             [input__105355_nth_0__
              (clojure.core/nth input__105355 0)
              input__105355_nth_1__
              (clojure.core/nth input__105355 1)]
             (if
              (clojure.core/seq? input__105355_nth_0__)
              (clojure.core/let
               [input__105355_nth_0___l__
                (clojure.core/take 1 input__105355_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__105355_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__105355_nth_0___r__
                  (clojure.core/drop 1 input__105355_nth_0__)]
                 (clojure.core/let
                  [input__105355_nth_0___l___nth_0__
                   (clojure.core/nth input__105355_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__105355_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__106172
                     (clojure.core/namespace
                      input__105355_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__105362 X__106172]
                     (clojure.core/let
                      [X__106174
                       (clojure.core/name
                        input__105355_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__106174
                       ("let")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__106162 input__105355_nth_1__ ?__105362)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__106546)
                         (clojure.core/let
                          [[?__105362] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__105355)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__105355)
                             2)
                            (clojure.core/let
                             [input__105355_nth_0__
                              (clojure.core/nth input__105355 0)
                              input__105355_nth_1__
                              (clojure.core/nth input__105355 1)]
                             (if
                              (clojure.core/seq? input__105355_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__105355_nth_0__)
                                3)
                               (clojure.core/let
                                [input__105355_nth_0___nth_0__
                                 (clojure.core/nth
                                  input__105355_nth_0__
                                  0)
                                 input__105355_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__105355_nth_0__
                                  1)
                                 input__105355_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__105355_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?pattern
                                  input__105355_nth_0___nth_0__]
                                 (clojure.core/let
                                  [?expression
                                   input__105355_nth_0___nth_1__]
                                  (clojure.core/let
                                   [?next
                                    input__105355_nth_0___nth_2__]
                                   (clojure.core/let
                                    [?form input__105355_nth_0__]
                                    (clojure.core/let
                                     [?env input__105355_nth_1__]
                                     (try
                                      [{:tag :let,
                                        :pattern
                                        (clojure.core/let
                                         [CATA_RESULT__9229__auto__
                                          (CATA__FN__105414
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
                                          (CATA__FN__105414
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
                               (state__106546))
                              (state__106546)))
                            (state__106546))
                           (state__106546)))))
                       (state__106546)))))
                   (state__106546))))
                (state__106546)))
              (state__106546)))
            (state__106546))
           (state__106546))))
        (state__106546
         []
         (clojure.core/letfn
          [(def__106196
            [arg__106219 ?__105363]
            (clojure.core/letfn
             [(state__106701
               []
               (clojure.core/let
                [x__106220 "meander.zeta"]
                (if
                 (clojure.core/= ?__105363 x__106220)
                 [?__105363]
                 (state__106702))))
              (state__106702
               []
               (if
                (clojure.core/map? arg__106219)
                (clojure.core/let
                 [VAL__106221 (.valAt arg__106219 :aliases)]
                 (if
                  (clojure.core/map? VAL__106221)
                  (clojure.core/let
                   [X__106223 (clojure.core/set VAL__106221)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__106223))
                    (clojure.core/loop
                     [search_space__106703
                      (clojure.core/seq X__106223)]
                     (if
                      (clojure.core/seq search_space__106703)
                      (clojure.core/let
                       [elem__106224
                        (clojure.core/first search_space__106703)
                        result__106704
                        (clojure.core/let
                         [elem__106224_nth_0__
                          (clojure.core/nth elem__106224 0)
                          elem__106224_nth_1__
                          (clojure.core/nth elem__106224 1)]
                         (if
                          (clojure.core/symbol? elem__106224_nth_0__)
                          (clojure.core/let
                           [X__106226
                            (clojure.core/name elem__106224_nth_0__)]
                           (if
                            (clojure.core/= ?__105363 X__106226)
                            (if
                             (clojure.core/symbol?
                              elem__106224_nth_1__)
                             (clojure.core/let
                              [X__106228
                               (clojure.core/name
                                elem__106224_nth_1__)]
                              (clojure.core/case
                               X__106228
                               ("meander.zeta")
                               [?__105363]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__106704)
                        (recur
                         (clojure.core/next search_space__106703))
                        result__106704))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__106701)))]
          (if
           (clojure.core/vector? input__105355)
           (if
            (clojure.core/= (clojure.core/count input__105355) 2)
            (clojure.core/let
             [input__105355_nth_0__
              (clojure.core/nth input__105355 0)
              input__105355_nth_1__
              (clojure.core/nth input__105355 1)]
             (if
              (clojure.core/seq? input__105355_nth_0__)
              (clojure.core/let
               [input__105355_nth_0___l__
                (clojure.core/take 1 input__105355_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__105355_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__105355_nth_0___r__
                  (clojure.core/drop 1 input__105355_nth_0__)]
                 (clojure.core/let
                  [input__105355_nth_0___l___nth_0__
                   (clojure.core/nth input__105355_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__105355_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__106206
                     (clojure.core/namespace
                      input__105355_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__105363 X__106206]
                     (clojure.core/let
                      [X__106208
                       (clojure.core/name
                        input__105355_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__106208
                       ("not")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__106196 input__105355_nth_1__ ?__105363)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__106547)
                         (clojure.core/let
                          [[?__105363] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__105355)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__105355)
                             2)
                            (clojure.core/let
                             [input__105355_nth_0__
                              (clojure.core/nth input__105355 0)
                              input__105355_nth_1__
                              (clojure.core/nth input__105355 1)]
                             (if
                              (clojure.core/seq? input__105355_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__105355_nth_0__)
                                2)
                               (clojure.core/let
                                [input__105355_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__105355_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__105355_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__105355_nth_0__]
                                  (clojure.core/let
                                   [?env input__105355_nth_1__]
                                   (try
                                    [{:tag :not,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__9229__auto__
                                        (CATA__FN__105414
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
                               (state__106547))
                              (state__106547)))
                            (state__106547))
                           (state__106547)))))
                       (state__106547)))))
                   (state__106547))))
                (state__106547)))
              (state__106547)))
            (state__106547))
           (state__106547))))
        (state__106547
         []
         (clojure.core/letfn
          [(def__106230
            [arg__106253 ?__105364]
            (clojure.core/letfn
             [(state__106706
               []
               (clojure.core/let
                [x__106254 "meander.zeta"]
                (if
                 (clojure.core/= ?__105364 x__106254)
                 [?__105364]
                 (state__106707))))
              (state__106707
               []
               (if
                (clojure.core/map? arg__106253)
                (clojure.core/let
                 [VAL__106255 (.valAt arg__106253 :aliases)]
                 (if
                  (clojure.core/map? VAL__106255)
                  (clojure.core/let
                   [X__106257 (clojure.core/set VAL__106255)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__106257))
                    (clojure.core/loop
                     [search_space__106708
                      (clojure.core/seq X__106257)]
                     (if
                      (clojure.core/seq search_space__106708)
                      (clojure.core/let
                       [elem__106258
                        (clojure.core/first search_space__106708)
                        result__106709
                        (clojure.core/let
                         [elem__106258_nth_0__
                          (clojure.core/nth elem__106258 0)
                          elem__106258_nth_1__
                          (clojure.core/nth elem__106258 1)]
                         (if
                          (clojure.core/symbol? elem__106258_nth_0__)
                          (clojure.core/let
                           [X__106260
                            (clojure.core/name elem__106258_nth_0__)]
                           (if
                            (clojure.core/= ?__105364 X__106260)
                            (if
                             (clojure.core/symbol?
                              elem__106258_nth_1__)
                             (clojure.core/let
                              [X__106262
                               (clojure.core/name
                                elem__106258_nth_1__)]
                              (clojure.core/case
                               X__106262
                               ("meander.zeta")
                               [?__105364]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__106709)
                        (recur
                         (clojure.core/next search_space__106708))
                        result__106709))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__106706)))]
          (if
           (clojure.core/vector? input__105355)
           (if
            (clojure.core/= (clojure.core/count input__105355) 2)
            (clojure.core/let
             [input__105355_nth_0__
              (clojure.core/nth input__105355 0)
              input__105355_nth_1__
              (clojure.core/nth input__105355 1)]
             (if
              (clojure.core/seq? input__105355_nth_0__)
              (clojure.core/let
               [input__105355_nth_0___l__
                (clojure.core/take 1 input__105355_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__105355_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__105355_nth_0___r__
                  (clojure.core/drop 1 input__105355_nth_0__)]
                 (clojure.core/let
                  [input__105355_nth_0___l___nth_0__
                   (clojure.core/nth input__105355_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__105355_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__106240
                     (clojure.core/namespace
                      input__105355_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__105364 X__106240]
                     (clojure.core/let
                      [X__106242
                       (clojure.core/name
                        input__105355_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__106242
                       ("or")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__106230 input__105355_nth_1__ ?__105364)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__106548)
                         (clojure.core/let
                          [[?__105364] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__105355)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__105355)
                             2)
                            (clojure.core/let
                             [input__105355_nth_0__
                              (clojure.core/nth input__105355 0)
                              input__105355_nth_1__
                              (clojure.core/nth input__105355 1)]
                             (if
                              (clojure.core/seq? input__105355_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__105355_nth_0__)
                                3)
                               (clojure.core/let
                                [input__105355_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__105355_nth_0__
                                  1)
                                 input__105355_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__105355_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?left input__105355_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?right
                                   input__105355_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__105355_nth_0__]
                                   (clojure.core/let
                                    [?env input__105355_nth_1__]
                                    (try
                                     [{:tag :or,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__105414
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
                                         (CATA__FN__105414
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
                               (state__106548))
                              (state__106548)))
                            (state__106548))
                           (state__106548)))))
                       (state__106548)))))
                   (state__106548))))
                (state__106548)))
              (state__106548)))
            (state__106548))
           (state__106548))))
        (state__106548
         []
         (clojure.core/letfn
          [(def__106264
            [arg__106287 ?__105365]
            (clojure.core/letfn
             [(state__106711
               []
               (clojure.core/let
                [x__106288 "meander.zeta"]
                (if
                 (clojure.core/= ?__105365 x__106288)
                 [?__105365]
                 (state__106712))))
              (state__106712
               []
               (if
                (clojure.core/map? arg__106287)
                (clojure.core/let
                 [VAL__106289 (.valAt arg__106287 :aliases)]
                 (if
                  (clojure.core/map? VAL__106289)
                  (clojure.core/let
                   [X__106291 (clojure.core/set VAL__106289)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__106291))
                    (clojure.core/loop
                     [search_space__106713
                      (clojure.core/seq X__106291)]
                     (if
                      (clojure.core/seq search_space__106713)
                      (clojure.core/let
                       [elem__106292
                        (clojure.core/first search_space__106713)
                        result__106714
                        (clojure.core/let
                         [elem__106292_nth_0__
                          (clojure.core/nth elem__106292 0)
                          elem__106292_nth_1__
                          (clojure.core/nth elem__106292 1)]
                         (if
                          (clojure.core/symbol? elem__106292_nth_0__)
                          (clojure.core/let
                           [X__106294
                            (clojure.core/name elem__106292_nth_0__)]
                           (if
                            (clojure.core/= ?__105365 X__106294)
                            (if
                             (clojure.core/symbol?
                              elem__106292_nth_1__)
                             (clojure.core/let
                              [X__106296
                               (clojure.core/name
                                elem__106292_nth_1__)]
                              (clojure.core/case
                               X__106296
                               ("meander.zeta")
                               [?__105365]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__106714)
                        (recur
                         (clojure.core/next search_space__106713))
                        result__106714))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__106711)))]
          (if
           (clojure.core/vector? input__105355)
           (if
            (clojure.core/= (clojure.core/count input__105355) 2)
            (clojure.core/let
             [input__105355_nth_0__
              (clojure.core/nth input__105355 0)
              input__105355_nth_1__
              (clojure.core/nth input__105355 1)]
             (if
              (clojure.core/seq? input__105355_nth_0__)
              (clojure.core/let
               [input__105355_nth_0___l__
                (clojure.core/take 1 input__105355_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__105355_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__105355_nth_0___r__
                  (clojure.core/drop 1 input__105355_nth_0__)]
                 (clojure.core/let
                  [input__105355_nth_0___l___nth_0__
                   (clojure.core/nth input__105355_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__105355_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__106274
                     (clojure.core/namespace
                      input__105355_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__105365 X__106274]
                     (clojure.core/let
                      [X__106276
                       (clojure.core/name
                        input__105355_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__106276
                       ("re")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__106264 input__105355_nth_1__ ?__105365)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__106549)
                         (clojure.core/let
                          [[?__105365] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__105355)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__105355)
                             2)
                            (clojure.core/let
                             [input__105355_nth_0__
                              (clojure.core/nth input__105355 0)
                              input__105355_nth_1__
                              (clojure.core/nth input__105355 1)]
                             (if
                              (clojure.core/seq? input__105355_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__105355_nth_0__)
                                2)
                               (clojure.core/let
                                [input__105355_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__105355_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?regex input__105355_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__105355_nth_0__]
                                  (clojure.core/let
                                   [?env input__105355_nth_1__]
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
                               (state__106549))
                              (state__106549)))
                            (state__106549))
                           (state__106549)))))
                       (state__106549)))))
                   (state__106549))))
                (state__106549)))
              (state__106549)))
            (state__106549))
           (state__106549))))
        (state__106549
         []
         (clojure.core/letfn
          [(def__106298
            [arg__106321 ?__105366]
            (clojure.core/letfn
             [(state__106716
               []
               (clojure.core/let
                [x__106322 "meander.zeta"]
                (if
                 (clojure.core/= ?__105366 x__106322)
                 [?__105366]
                 (state__106717))))
              (state__106717
               []
               (if
                (clojure.core/map? arg__106321)
                (clojure.core/let
                 [VAL__106323 (.valAt arg__106321 :aliases)]
                 (if
                  (clojure.core/map? VAL__106323)
                  (clojure.core/let
                   [X__106325 (clojure.core/set VAL__106323)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__106325))
                    (clojure.core/loop
                     [search_space__106718
                      (clojure.core/seq X__106325)]
                     (if
                      (clojure.core/seq search_space__106718)
                      (clojure.core/let
                       [elem__106326
                        (clojure.core/first search_space__106718)
                        result__106719
                        (clojure.core/let
                         [elem__106326_nth_0__
                          (clojure.core/nth elem__106326 0)
                          elem__106326_nth_1__
                          (clojure.core/nth elem__106326 1)]
                         (if
                          (clojure.core/symbol? elem__106326_nth_0__)
                          (clojure.core/let
                           [X__106328
                            (clojure.core/name elem__106326_nth_0__)]
                           (if
                            (clojure.core/= ?__105366 X__106328)
                            (if
                             (clojure.core/symbol?
                              elem__106326_nth_1__)
                             (clojure.core/let
                              [X__106330
                               (clojure.core/name
                                elem__106326_nth_1__)]
                              (clojure.core/case
                               X__106330
                               ("meander.zeta")
                               [?__105366]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__106719)
                        (recur
                         (clojure.core/next search_space__106718))
                        result__106719))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__106716)))]
          (if
           (clojure.core/vector? input__105355)
           (if
            (clojure.core/= (clojure.core/count input__105355) 2)
            (clojure.core/let
             [input__105355_nth_0__
              (clojure.core/nth input__105355 0)
              input__105355_nth_1__
              (clojure.core/nth input__105355 1)]
             (if
              (clojure.core/seq? input__105355_nth_0__)
              (clojure.core/let
               [input__105355_nth_0___l__
                (clojure.core/take 1 input__105355_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__105355_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__105355_nth_0___r__
                  (clojure.core/drop 1 input__105355_nth_0__)]
                 (clojure.core/let
                  [input__105355_nth_0___l___nth_0__
                   (clojure.core/nth input__105355_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__105355_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__106308
                     (clojure.core/namespace
                      input__105355_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__105366 X__106308]
                     (clojure.core/let
                      [X__106310
                       (clojure.core/name
                        input__105355_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__106310
                       ("re")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__106298 input__105355_nth_1__ ?__105366)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__106550)
                         (clojure.core/let
                          [[?__105366] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__105355)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__105355)
                             2)
                            (clojure.core/let
                             [input__105355_nth_0__
                              (clojure.core/nth input__105355 0)
                              input__105355_nth_1__
                              (clojure.core/nth input__105355 1)]
                             (if
                              (clojure.core/seq? input__105355_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__105355_nth_0__)
                                3)
                               (clojure.core/let
                                [input__105355_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__105355_nth_0__
                                  1)
                                 input__105355_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__105355_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?regex input__105355_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?capture
                                   input__105355_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__105355_nth_0__]
                                   (clojure.core/let
                                    [?env input__105355_nth_1__]
                                    (try
                                     [{:tag :regex,
                                       :regex ?regex,
                                       :capture
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__105414
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
                               (state__106550))
                              (state__106550)))
                            (state__106550))
                           (state__106550)))))
                       (state__106550)))))
                   (state__106550))))
                (state__106550)))
              (state__106550)))
            (state__106550))
           (state__106550))))
        (state__106550
         []
         (clojure.core/letfn
          [(def__106332
            [arg__106355 ?__105367]
            (clojure.core/letfn
             [(state__106721
               []
               (clojure.core/let
                [x__106356 "meander.zeta"]
                (if
                 (clojure.core/= ?__105367 x__106356)
                 [?__105367]
                 (state__106722))))
              (state__106722
               []
               (if
                (clojure.core/map? arg__106355)
                (clojure.core/let
                 [VAL__106357 (.valAt arg__106355 :aliases)]
                 (if
                  (clojure.core/map? VAL__106357)
                  (clojure.core/let
                   [X__106359 (clojure.core/set VAL__106357)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__106359))
                    (clojure.core/loop
                     [search_space__106723
                      (clojure.core/seq X__106359)]
                     (if
                      (clojure.core/seq search_space__106723)
                      (clojure.core/let
                       [elem__106360
                        (clojure.core/first search_space__106723)
                        result__106724
                        (clojure.core/let
                         [elem__106360_nth_0__
                          (clojure.core/nth elem__106360 0)
                          elem__106360_nth_1__
                          (clojure.core/nth elem__106360 1)]
                         (if
                          (clojure.core/symbol? elem__106360_nth_0__)
                          (clojure.core/let
                           [X__106362
                            (clojure.core/name elem__106360_nth_0__)]
                           (if
                            (clojure.core/= ?__105367 X__106362)
                            (if
                             (clojure.core/symbol?
                              elem__106360_nth_1__)
                             (clojure.core/let
                              [X__106364
                               (clojure.core/name
                                elem__106360_nth_1__)]
                              (clojure.core/case
                               X__106364
                               ("meander.zeta")
                               [?__105367]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__106724)
                        (recur
                         (clojure.core/next search_space__106723))
                        result__106724))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__106721)))]
          (if
           (clojure.core/vector? input__105355)
           (if
            (clojure.core/= (clojure.core/count input__105355) 2)
            (clojure.core/let
             [input__105355_nth_0__
              (clojure.core/nth input__105355 0)
              input__105355_nth_1__
              (clojure.core/nth input__105355 1)]
             (if
              (clojure.core/seq? input__105355_nth_0__)
              (clojure.core/let
               [input__105355_nth_0___l__
                (clojure.core/take 1 input__105355_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__105355_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__105355_nth_0___r__
                  (clojure.core/drop 1 input__105355_nth_0__)]
                 (clojure.core/let
                  [input__105355_nth_0___l___nth_0__
                   (clojure.core/nth input__105355_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__105355_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__106342
                     (clojure.core/namespace
                      input__105355_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__105367 X__106342]
                     (clojure.core/let
                      [X__106344
                       (clojure.core/name
                        input__105355_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__106344
                       ("string")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__106332 input__105355_nth_1__ ?__105367)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__106551)
                         (clojure.core/let
                          [[?__105367] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__105355)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__105355)
                             2)
                            (clojure.core/let
                             [input__105355_nth_0__
                              (clojure.core/nth input__105355 0)
                              input__105355_nth_1__
                              (clojure.core/nth input__105355 1)]
                             (if
                              (clojure.core/seq? input__105355_nth_0__)
                              (clojure.core/let
                               [input__105355_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__105355_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__105355_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__105355_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__105355_nth_0__)]
                                 (clojure.core/let
                                  [?sequence input__105355_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__105355_nth_0__]
                                   (clojure.core/let
                                    [?env input__105355_nth_1__]
                                    (try
                                     [{:tag :string,
                                       :next
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__105414
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
                                (state__106551)))
                              (state__106551)))
                            (state__106551))
                           (state__106551)))))
                       (state__106551)))))
                   (state__106551))))
                (state__106551)))
              (state__106551)))
            (state__106551))
           (state__106551))))
        (state__106551
         []
         (clojure.core/letfn
          [(def__106366
            [arg__106389 ?__105368]
            (clojure.core/letfn
             [(state__106726
               []
               (clojure.core/let
                [x__106390 "meander.zeta"]
                (if
                 (clojure.core/= ?__105368 x__106390)
                 [?__105368]
                 (state__106727))))
              (state__106727
               []
               (if
                (clojure.core/map? arg__106389)
                (clojure.core/let
                 [VAL__106391 (.valAt arg__106389 :aliases)]
                 (if
                  (clojure.core/map? VAL__106391)
                  (clojure.core/let
                   [X__106393 (clojure.core/set VAL__106391)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__106393))
                    (clojure.core/loop
                     [search_space__106728
                      (clojure.core/seq X__106393)]
                     (if
                      (clojure.core/seq search_space__106728)
                      (clojure.core/let
                       [elem__106394
                        (clojure.core/first search_space__106728)
                        result__106729
                        (clojure.core/let
                         [elem__106394_nth_0__
                          (clojure.core/nth elem__106394 0)
                          elem__106394_nth_1__
                          (clojure.core/nth elem__106394 1)]
                         (if
                          (clojure.core/symbol? elem__106394_nth_0__)
                          (clojure.core/let
                           [X__106396
                            (clojure.core/name elem__106394_nth_0__)]
                           (if
                            (clojure.core/= ?__105368 X__106396)
                            (if
                             (clojure.core/symbol?
                              elem__106394_nth_1__)
                             (clojure.core/let
                              [X__106398
                               (clojure.core/name
                                elem__106394_nth_1__)]
                              (clojure.core/case
                               X__106398
                               ("meander.zeta")
                               [?__105368]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__106729)
                        (recur
                         (clojure.core/next search_space__106728))
                        result__106729))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__106726)))]
          (if
           (clojure.core/vector? input__105355)
           (if
            (clojure.core/= (clojure.core/count input__105355) 2)
            (clojure.core/let
             [input__105355_nth_0__
              (clojure.core/nth input__105355 0)
              input__105355_nth_1__
              (clojure.core/nth input__105355 1)]
             (if
              (clojure.core/seq? input__105355_nth_0__)
              (clojure.core/let
               [input__105355_nth_0___l__
                (clojure.core/take 1 input__105355_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__105355_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__105355_nth_0___r__
                  (clojure.core/drop 1 input__105355_nth_0__)]
                 (clojure.core/let
                  [input__105355_nth_0___l___nth_0__
                   (clojure.core/nth input__105355_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__105355_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__106376
                     (clojure.core/namespace
                      input__105355_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__105368 X__106376]
                     (clojure.core/let
                      [X__106378
                       (clojure.core/name
                        input__105355_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__106378
                       ("symbol")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__106366 input__105355_nth_1__ ?__105368)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__106552)
                         (clojure.core/let
                          [[?__105368] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__105355)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__105355)
                             2)
                            (clojure.core/let
                             [input__105355_nth_0__
                              (clojure.core/nth input__105355 0)
                              input__105355_nth_1__
                              (clojure.core/nth input__105355 1)]
                             (if
                              (clojure.core/seq? input__105355_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__105355_nth_0__)
                                2)
                               (clojure.core/let
                                [input__105355_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__105355_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?name input__105355_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__105355_nth_0__]
                                  (clojure.core/let
                                   [?env input__105355_nth_1__]
                                   (try
                                    [{:tag :symbol,
                                      :name
                                      (clojure.core/let
                                       [CATA_RESULT__9229__auto__
                                        (CATA__FN__105414
                                         [?name ?env])]
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
                               (state__106552))
                              (state__106552)))
                            (state__106552))
                           (state__106552)))))
                       (state__106552)))))
                   (state__106552))))
                (state__106552)))
              (state__106552)))
            (state__106552))
           (state__106552))))
        (state__106552
         []
         (clojure.core/letfn
          [(def__106400
            [arg__106423 ?__105369]
            (clojure.core/letfn
             [(state__106731
               []
               (clojure.core/let
                [x__106424 "meander.zeta"]
                (if
                 (clojure.core/= ?__105369 x__106424)
                 [?__105369]
                 (state__106732))))
              (state__106732
               []
               (if
                (clojure.core/map? arg__106423)
                (clojure.core/let
                 [VAL__106425 (.valAt arg__106423 :aliases)]
                 (if
                  (clojure.core/map? VAL__106425)
                  (clojure.core/let
                   [X__106427 (clojure.core/set VAL__106425)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__106427))
                    (clojure.core/loop
                     [search_space__106733
                      (clojure.core/seq X__106427)]
                     (if
                      (clojure.core/seq search_space__106733)
                      (clojure.core/let
                       [elem__106428
                        (clojure.core/first search_space__106733)
                        result__106734
                        (clojure.core/let
                         [elem__106428_nth_0__
                          (clojure.core/nth elem__106428 0)
                          elem__106428_nth_1__
                          (clojure.core/nth elem__106428 1)]
                         (if
                          (clojure.core/symbol? elem__106428_nth_0__)
                          (clojure.core/let
                           [X__106430
                            (clojure.core/name elem__106428_nth_0__)]
                           (if
                            (clojure.core/= ?__105369 X__106430)
                            (if
                             (clojure.core/symbol?
                              elem__106428_nth_1__)
                             (clojure.core/let
                              [X__106432
                               (clojure.core/name
                                elem__106428_nth_1__)]
                              (clojure.core/case
                               X__106432
                               ("meander.zeta")
                               [?__105369]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__106734)
                        (recur
                         (clojure.core/next search_space__106733))
                        result__106734))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__106731)))]
          (if
           (clojure.core/vector? input__105355)
           (if
            (clojure.core/= (clojure.core/count input__105355) 2)
            (clojure.core/let
             [input__105355_nth_0__
              (clojure.core/nth input__105355 0)
              input__105355_nth_1__
              (clojure.core/nth input__105355 1)]
             (if
              (clojure.core/seq? input__105355_nth_0__)
              (clojure.core/let
               [input__105355_nth_0___l__
                (clojure.core/take 1 input__105355_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__105355_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__105355_nth_0___r__
                  (clojure.core/drop 1 input__105355_nth_0__)]
                 (clojure.core/let
                  [input__105355_nth_0___l___nth_0__
                   (clojure.core/nth input__105355_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__105355_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__106410
                     (clojure.core/namespace
                      input__105355_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__105369 X__106410]
                     (clojure.core/let
                      [X__106412
                       (clojure.core/name
                        input__105355_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__106412
                       ("symbol")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__106400 input__105355_nth_1__ ?__105369)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__106553)
                         (clojure.core/let
                          [[?__105369] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__105355)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__105355)
                             2)
                            (clojure.core/let
                             [input__105355_nth_0__
                              (clojure.core/nth input__105355 0)
                              input__105355_nth_1__
                              (clojure.core/nth input__105355 1)]
                             (if
                              (clojure.core/seq? input__105355_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__105355_nth_0__)
                                3)
                               (clojure.core/let
                                [input__105355_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__105355_nth_0__
                                  1)
                                 input__105355_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__105355_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?namespace
                                  input__105355_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?name input__105355_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__105355_nth_0__]
                                   (clojure.core/let
                                    [?env input__105355_nth_1__]
                                    (try
                                     [{:tag :symbol,
                                       :name
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__105414
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
                                         (CATA__FN__105414
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
                               (state__106553))
                              (state__106553)))
                            (state__106553))
                           (state__106553)))))
                       (state__106553)))))
                   (state__106553))))
                (state__106553)))
              (state__106553)))
            (state__106553))
           (state__106553))))
        (state__106553
         []
         (clojure.core/letfn
          [(def__106434
            [arg__106457 ?__105370]
            (clojure.core/letfn
             [(state__106736
               []
               (clojure.core/let
                [x__106458 "meander.zeta"]
                (if
                 (clojure.core/= ?__105370 x__106458)
                 [?__105370]
                 (state__106737))))
              (state__106737
               []
               (if
                (clojure.core/map? arg__106457)
                (clojure.core/let
                 [VAL__106459 (.valAt arg__106457 :aliases)]
                 (if
                  (clojure.core/map? VAL__106459)
                  (clojure.core/let
                   [X__106461 (clojure.core/set VAL__106459)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__106461))
                    (clojure.core/loop
                     [search_space__106738
                      (clojure.core/seq X__106461)]
                     (if
                      (clojure.core/seq search_space__106738)
                      (clojure.core/let
                       [elem__106462
                        (clojure.core/first search_space__106738)
                        result__106739
                        (clojure.core/let
                         [elem__106462_nth_0__
                          (clojure.core/nth elem__106462 0)
                          elem__106462_nth_1__
                          (clojure.core/nth elem__106462 1)]
                         (if
                          (clojure.core/symbol? elem__106462_nth_0__)
                          (clojure.core/let
                           [X__106464
                            (clojure.core/name elem__106462_nth_0__)]
                           (if
                            (clojure.core/= ?__105370 X__106464)
                            (if
                             (clojure.core/symbol?
                              elem__106462_nth_1__)
                             (clojure.core/let
                              [X__106466
                               (clojure.core/name
                                elem__106462_nth_1__)]
                              (clojure.core/case
                               X__106466
                               ("meander.zeta")
                               [?__105370]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__106739)
                        (recur
                         (clojure.core/next search_space__106738))
                        result__106739))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__106736)))]
          (if
           (clojure.core/vector? input__105355)
           (if
            (clojure.core/= (clojure.core/count input__105355) 2)
            (clojure.core/let
             [input__105355_nth_0__
              (clojure.core/nth input__105355 0)
              input__105355_nth_1__
              (clojure.core/nth input__105355 1)]
             (if
              (clojure.core/seq? input__105355_nth_0__)
              (clojure.core/let
               [input__105355_nth_0___l__
                (clojure.core/take 1 input__105355_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__105355_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__105355_nth_0___r__
                  (clojure.core/drop 1 input__105355_nth_0__)]
                 (clojure.core/let
                  [input__105355_nth_0___l___nth_0__
                   (clojure.core/nth input__105355_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__105355_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__106444
                     (clojure.core/namespace
                      input__105355_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__105370 X__106444]
                     (clojure.core/let
                      [X__106446
                       (clojure.core/name
                        input__105355_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__106446
                       ("symbol")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__106434 input__105355_nth_1__ ?__105370)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__106554)
                         (clojure.core/let
                          [[?__105370] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__105355)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__105355)
                             2)
                            (clojure.core/let
                             [input__105355_nth_0__
                              (clojure.core/nth input__105355 0)
                              input__105355_nth_1__
                              (clojure.core/nth input__105355 1)]
                             (if
                              (clojure.core/seq? input__105355_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 5)
                                 input__105355_nth_0__)
                                5)
                               (clojure.core/let
                                [input__105355_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__105355_nth_0__
                                  1)
                                 input__105355_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__105355_nth_0__
                                  2)
                                 input__105355_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__105355_nth_0__
                                  3)
                                 input__105355_nth_0___nth_4__
                                 (clojure.core/nth
                                  input__105355_nth_0__
                                  4)]
                                (clojure.core/case
                                 input__105355_nth_0___nth_3__
                                 (:meander.zeta/as)
                                 (clojure.core/let
                                  [?namespace
                                   input__105355_nth_0___nth_1__]
                                  (clojure.core/let
                                   [?name
                                    input__105355_nth_0___nth_2__]
                                   (clojure.core/let
                                    [?pattern
                                     input__105355_nth_0___nth_4__]
                                    (clojure.core/let
                                     [?form input__105355_nth_0__]
                                     (clojure.core/let
                                      [?env input__105355_nth_1__]
                                      (try
                                       [{:tag :symbol,
                                         :name
                                         (clojure.core/let
                                          [CATA_RESULT__9229__auto__
                                           (CATA__FN__105414
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
                                           (CATA__FN__105414
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
                                           (CATA__FN__105414
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
                                 (state__106554)))
                               (state__106554))
                              (state__106554)))
                            (state__106554))
                           (state__106554)))))
                       (state__106554)))))
                   (state__106554))))
                (state__106554)))
              (state__106554)))
            (state__106554))
           (state__106554))))
        (state__106554
         []
         (if
          (clojure.core/vector? input__105355)
          (if
           (clojure.core/= (clojure.core/count input__105355) 2)
           (clojure.core/let
            [input__105355_nth_0__ (clojure.core/nth input__105355 0)]
            (clojure.core/letfn
             [(state__106741
               []
               (clojure.core/let
                [input__105355_nth_1__
                 (clojure.core/nth input__105355 1)]
                (clojure.core/letfn
                 [(state__106746
                   []
                   (if
                    (clojure.core/seq? input__105355_nth_0__)
                    (clojure.core/let
                     [?sequence input__105355_nth_0__]
                     (clojure.core/let
                      [?env input__105355_nth_1__]
                      (try
                       [{:tag :seq,
                         :next
                         (clojure.core/let
                          [CATA_RESULT__9229__auto__
                           (CATA__FN__105414
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
                    (state__106747)))
                  (state__106747
                   []
                   (if
                    (clojure.core/map? input__105355_nth_0__)
                    (clojure.core/let
                     [?map input__105355_nth_0__]
                     (clojure.core/let
                      [?env input__105355_nth_1__]
                      (try
                       [{:tag :map,
                         :next
                         (clojure.core/let
                          [CATA_RESULT__9229__auto__
                           (CATA__FN__105414
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
                    (state__106742)))]
                 (state__106746))))
              (state__106742
               []
               (if
                (clojure.core/symbol? input__105355_nth_0__)
                (clojure.core/let
                 [X__106476
                  (clojure.core/namespace input__105355_nth_0__)]
                 (clojure.core/let
                  [X__106478 (clojure.core/name input__105355_nth_0__)]
                  (if
                   (clojure.core/string? X__106478)
                   (clojure.core/letfn
                    [(state__106748
                      []
                      (clojure.core/let
                       [ret__106479
                        (clojure.core/re-matches #"_.*" X__106478)]
                       (if
                        (clojure.core/some? ret__106479)
                        (clojure.core/let
                         [?name ret__106479]
                         (clojure.core/let
                          [?symbol input__105355_nth_0__]
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
                        (state__106749))))
                     (state__106749
                      []
                      (clojure.core/let
                       [ret__106486
                        (clojure.core/re-matches #".+#" X__106478)]
                       (if
                        (clojure.core/some? ret__106486)
                        (clojure.core/let
                         [?name ret__106486]
                         (clojure.core/let
                          [?symbol input__105355_nth_0__]
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
                        (state__106750))))
                     (state__106750
                      []
                      (clojure.core/let
                       [ret__106493
                        (clojure.core/re-matches #"%.+" X__106478)]
                       (if
                        (clojure.core/some? ret__106493)
                        (clojure.core/let
                         [?name ret__106493]
                         (clojure.core/let
                          [?symbol input__105355_nth_0__]
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
                        (state__106751))))
                     (state__106751
                      []
                      (clojure.core/let
                       [ret__106500
                        (clojure.core/re-matches #"\*.+" X__106478)]
                       (if
                        (clojure.core/some? ret__106500)
                        (clojure.core/let
                         [?name ret__106500]
                         (clojure.core/let
                          [?symbol input__105355_nth_0__]
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
                        (state__106752))))
                     (state__106752
                      []
                      (clojure.core/let
                       [ret__106507
                        (clojure.core/re-matches #"\!.+" X__106478)]
                       (if
                        (clojure.core/some? ret__106507)
                        (clojure.core/let
                         [?name ret__106507]
                         (clojure.core/let
                          [?symbol input__105355_nth_0__]
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
                        (state__106753))))
                     (state__106753
                      []
                      (clojure.core/let
                       [ret__106514
                        (clojure.core/re-matches #"\?.+" X__106478)]
                       (if
                        (clojure.core/some? ret__106514)
                        (clojure.core/let
                         [?name ret__106514]
                         (clojure.core/let
                          [?symbol input__105355_nth_0__]
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
                        (state__106743))))]
                    (state__106748))
                   (state__106743))))
                (state__106743)))
              (state__106743
               []
               (if
                (string? input__105355_nth_0__)
                (clojure.core/let
                 [?x input__105355_nth_0__]
                 (try
                  [{:tag :literal, :type :string, :form ?x}]
                  (catch
                   java.lang.Exception
                   e__10169__auto__
                   (if
                    (meander.runtime.zeta/fail? e__10169__auto__)
                    (meander.runtime.zeta/fail)
                    (throw e__10169__auto__)))))
                (state__106744)))
              (state__106744
               []
               (if
                (char? input__105355_nth_0__)
                (clojure.core/let
                 [?x input__105355_nth_0__]
                 (try
                  [{:tag :literal, :type :char, :form ?x}]
                  (catch
                   java.lang.Exception
                   e__10169__auto__
                   (if
                    (meander.runtime.zeta/fail? e__10169__auto__)
                    (meander.runtime.zeta/fail)
                    (throw e__10169__auto__)))))
                (state__106745)))
              (state__106745
               []
               (clojure.core/let
                [?x input__105355_nth_0__]
                (try
                 [{:tag :literal, :form ?x}]
                 (catch
                  java.lang.Exception
                  e__10169__auto__
                  (if
                   (meander.runtime.zeta/fail? e__10169__auto__)
                   (meander.runtime.zeta/fail)
                   (throw e__10169__auto__))))))]
             (state__106741)))
           (state__106555))
          (state__106555)))
        (state__106555
         []
         (clojure.core/let
          [?x input__105355]
          (try
           [{:tag :mistake, :x ?x}]
           (catch
            java.lang.Exception
            e__10169__auto__
            (if
             (meander.runtime.zeta/fail? e__10169__auto__)
             (meander.runtime.zeta/fail)
             (throw e__10169__auto__))))))]
       (state__106526)))]
    (clojure.core/let
     [x__7926__auto__ (CATA__FN__105414 input__105355)]
     (if
      (meander.runtime.zeta/fail? x__7926__auto__)
      (meander.runtime.zeta/fail)
      (clojure.core/let
       [[CATA_RETURN__105416] x__7926__auto__]
       CATA_RETURN__105416))))]
  (if
   (meander.runtime.zeta/fail? ret__8106__auto__)
   nil
   ret__8106__auto__)))
