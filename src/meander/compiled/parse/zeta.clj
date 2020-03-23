(ns meander.compiled.parse.zeta (:require [meander.runtime.zeta]))
(clojure.core/defn
 parse
 [input__60417]
 (let*
  [ret__12776__auto__
   (clojure.core/letfn
    [(CATA__FN__60479
      [input__60417]
      (clojure.core/letfn
       [(state__61642
         []
         (if
          (clojure.core/vector? input__60417)
          (if
           (clojure.core/= (clojure.core/count input__60417) 3)
           (clojure.core/let
            [input__60417_nth_0__
             (clojure.core/nth input__60417 0)
             input__60417_nth_1__
             (clojure.core/nth input__60417 1)
             input__60417_nth_2__
             (clojure.core/nth input__60417 2)]
            (clojure.core/case
             input__60417_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__60417_nth_1__)
              (clojure.core/letfn
               [(state__61673
                 []
                 (clojure.core/case
                  input__60417_nth_1__
                  ([])
                  (clojure.core/let
                   [?env input__60417_nth_2__]
                   (try
                    [{:tag :empty}]
                    (catch
                     java.lang.Exception
                     e__14839__auto__
                     (if
                      (meander.runtime.zeta/fail? e__14839__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__14839__auto__)))))
                  (state__61674)))
                (state__61674
                 []
                 (clojure.core/let
                  [n__60486
                   (clojure.core/count input__60417_nth_1__)
                   m__60487
                   (clojure.core/max 0 (clojure.core/- n__60486 2))
                   input__60417_nth_1___l__
                   (clojure.core/subvec
                    input__60417_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__60417_nth_1__)
                     m__60487))
                   input__60417_nth_1___r__
                   (clojure.core/subvec input__60417_nth_1__ m__60487)]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__60417_nth_1___r__)
                    2)
                   (clojure.core/let
                    [!xs (clojure.core/vec input__60417_nth_1___l__)]
                    (if
                     (clojure.core/=
                      (clojure.core/count input__60417_nth_1___r__)
                      2)
                     (clojure.core/let
                      [input__60417_nth_1___r___nth_0__
                       (clojure.core/nth input__60417_nth_1___r__ 0)
                       input__60417_nth_1___r___nth_1__
                       (clojure.core/nth input__60417_nth_1___r__ 1)]
                      (clojure.core/case
                       input__60417_nth_1___r___nth_0__
                       (:meander.zeta/as)
                       (clojure.core/let
                        [?pattern input__60417_nth_1___r___nth_1__]
                        (clojure.core/let
                         [?env input__60417_nth_2__]
                         (try
                          [(clojure.core/let
                            [!xs__counter
                             (meander.runtime.zeta/iterator !xs)]
                            {:tag :as,
                             :pattern
                             (clojure.core/let
                              [CATA_RESULT__13899__auto__
                               (CATA__FN__60479 [?pattern ?env])]
                              (if
                               (meander.runtime.zeta/fail?
                                CATA_RESULT__13899__auto__)
                               (throw (meander.runtime.zeta/fail))
                               (clojure.core/nth
                                CATA_RESULT__13899__auto__
                                0))),
                             :next
                             (clojure.core/let
                              [CATA_RESULT__13899__auto__
                               (CATA__FN__60479
                                ['meander.dev.parse.zeta/parse-sequential
                                 (clojure.core/into
                                  []
                                  (clojure.core/vec
                                   (clojure.core/iterator-seq
                                    !xs__counter)))
                                 ?env])]
                              (if
                               (meander.runtime.zeta/fail?
                                CATA_RESULT__13899__auto__)
                               (throw (meander.runtime.zeta/fail))
                               (clojure.core/nth
                                CATA_RESULT__13899__auto__
                                0)))})]
                          (catch
                           java.lang.Exception
                           e__14839__auto__
                           (if
                            (meander.runtime.zeta/fail?
                             e__14839__auto__)
                            (meander.runtime.zeta/fail)
                            (throw e__14839__auto__))))))
                       (state__61643)))
                     (state__61643)))
                   (state__61643))))]
               (state__61673))
              (state__61643))
             (state__61643)))
           (state__61643))
          (state__61643)))
        (state__61643
         []
         (clojure.core/letfn
          [(def__60492
            [arg__60527 ?ns]
            (clojure.core/letfn
             [(state__61675
               []
               (clojure.core/let
                [x__60528 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__60528)
                 (clojure.core/let [?env arg__60527] [?env ?ns])
                 (state__61676))))
              (state__61676
               []
               (if
                (clojure.core/map? arg__60527)
                (clojure.core/let
                 [VAL__60529 (.valAt arg__60527 :aliases)]
                 (if
                  (clojure.core/map? VAL__60529)
                  (clojure.core/let
                   [X__60531 (clojure.core/set VAL__60529)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__60531))
                    (clojure.core/loop
                     [search_space__61677 (clojure.core/seq X__60531)]
                     (if
                      (clojure.core/seq search_space__61677)
                      (clojure.core/let
                       [elem__60532
                        (clojure.core/first search_space__61677)
                        result__61678
                        (clojure.core/let
                         [elem__60532_nth_0__
                          (clojure.core/nth elem__60532 0)
                          elem__60532_nth_1__
                          (clojure.core/nth elem__60532 1)]
                         (if
                          (clojure.core/symbol? elem__60532_nth_0__)
                          (clojure.core/let
                           [X__60534
                            (clojure.core/name elem__60532_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__60534)
                            (if
                             (clojure.core/symbol? elem__60532_nth_1__)
                             (clojure.core/let
                              [X__60536
                               (clojure.core/name elem__60532_nth_1__)]
                              (clojure.core/case
                               X__60536
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__60527]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__61678)
                        (recur (clojure.core/next search_space__61677))
                        result__61678))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__61675)))]
          (if
           (clojure.core/vector? input__60417)
           (if
            (clojure.core/= (clojure.core/count input__60417) 3)
            (clojure.core/let
             [input__60417_nth_0__
              (clojure.core/nth input__60417 0)
              input__60417_nth_1__
              (clojure.core/nth input__60417 1)
              input__60417_nth_2__
              (clojure.core/nth input__60417 2)]
             (clojure.core/case
              input__60417_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__60417_nth_1__)
               (clojure.core/loop
                [search_space__61680
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__60417_nth_1__)]
                (if
                 (clojure.core/seq search_space__61680)
                 (clojure.core/let
                  [input__60417_nth_1___parts__
                   (clojure.core/first search_space__61680)
                   result__61681
                   (clojure.core/let
                    [input__60417_nth_1___l__
                     (clojure.core/nth input__60417_nth_1___parts__ 0)
                     input__60417_nth_1___r__
                     (clojure.core/nth input__60417_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__60417_nth_1___l__)]
                     (clojure.core/let
                      [input__60417_nth_1___r___l__
                       (clojure.core/subvec
                        input__60417_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__60417_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__60417_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__60417_nth_1___r___r__
                         (clojure.core/subvec
                          input__60417_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__60417_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__60417_nth_1___r___l__
                           0)
                          input__60417_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__60417_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__60417_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__60501
                            (clojure.core/namespace
                             input__60417_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__60501]
                            (clojure.core/let
                             [X__60503
                              (clojure.core/name
                               input__60417_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__60503)
                              (clojure.core/let
                               [ret__60504
                                (clojure.core/re-matches
                                 #"&(\d+)"
                                 X__60503)]
                               (if
                                (clojure.core/some? ret__60504)
                                (if
                                 (clojure.core/vector? ret__60504)
                                 (if
                                  (clojure.core/=
                                   (clojure.core/count ret__60504)
                                   2)
                                  (clojure.core/let
                                   [ret__60504_nth_1__
                                    (clojure.core/nth ret__60504 1)]
                                   (clojure.core/let
                                    [?n ret__60504_nth_1__]
                                    (clojure.core/let
                                     [?pattern
                                      input__60417_nth_1___r___l___nth_1__]
                                     (clojure.core/let
                                      [?rest
                                       input__60417_nth_1___r___r__]
                                      (clojure.core/let
                                       [x__12596__auto__
                                        (def__60492
                                         input__60417_nth_2__
                                         ?ns)]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         x__12596__auto__)
                                        (meander.runtime.zeta/fail)
                                        (clojure.core/let
                                         [[?env ?ns] x__12596__auto__]
                                         (try
                                          [(clojure.core/let
                                            [!init__counter
                                             (meander.runtime.zeta/iterator
                                              !init)]
                                            (clojure.core/let
                                             [CATA_RESULT__13899__auto__
                                              (CATA__FN__60479
                                               ['meander.dev.parse.zeta/make-join
                                                (clojure.core/let
                                                 [CATA_RESULT__13899__auto__
                                                  (CATA__FN__60479
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    (clojure.core/into
                                                     []
                                                     (clojure.core/vec
                                                      (clojure.core/iterator-seq
                                                       !init__counter)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__13899__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__13899__auto__
                                                   0)))
                                                (clojure.core/let
                                                 [CATA_RESULT__13899__auto__
                                                  (CATA__FN__60479
                                                   ['meander.dev.parse.zeta/make-join
                                                    {:tag :slice,
                                                     :size
                                                     (Integer. ?n),
                                                     :pattern
                                                     (clojure.core/let
                                                      [CATA_RESULT__13899__auto__
                                                       (CATA__FN__60479
                                                        [?pattern
                                                         ?env])]
                                                      (if
                                                       (meander.runtime.zeta/fail?
                                                        CATA_RESULT__13899__auto__)
                                                       (throw
                                                        (meander.runtime.zeta/fail))
                                                       (clojure.core/nth
                                                        CATA_RESULT__13899__auto__
                                                        0)))}
                                                    (clojure.core/let
                                                     [CATA_RESULT__13899__auto__
                                                      (CATA__FN__60479
                                                       ['meander.dev.parse.zeta/parse-sequential
                                                        (clojure.core/into
                                                         []
                                                         ?rest)
                                                        ?env])]
                                                     (if
                                                      (meander.runtime.zeta/fail?
                                                       CATA_RESULT__13899__auto__)
                                                      (throw
                                                       (meander.runtime.zeta/fail))
                                                      (clojure.core/nth
                                                       CATA_RESULT__13899__auto__
                                                       0)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__13899__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__13899__auto__
                                                   0)))
                                                ?env])]
                                             (if
                                              (meander.runtime.zeta/fail?
                                               CATA_RESULT__13899__auto__)
                                              (throw
                                               (meander.runtime.zeta/fail))
                                              (clojure.core/nth
                                               CATA_RESULT__13899__auto__
                                               0))))]
                                          (catch
                                           java.lang.Exception
                                           e__14839__auto__
                                           (if
                                            (meander.runtime.zeta/fail?
                                             e__14839__auto__)
                                            (meander.runtime.zeta/fail)
                                            (throw
                                             e__14839__auto__)))))))))))
                                  (meander.runtime.zeta/fail))
                                 (meander.runtime.zeta/fail))
                                (meander.runtime.zeta/fail)))
                              (meander.runtime.zeta/fail)))))
                          (meander.runtime.zeta/fail))))
                       (meander.runtime.zeta/fail)))))]
                  (if
                   (meander.runtime.zeta/fail? result__61681)
                   (recur (clojure.core/next search_space__61680))
                   result__61681))
                 (state__61644)))
               (state__61644))
              (state__61644)))
            (state__61644))
           (state__61644))))
        (state__61644
         []
         (clojure.core/letfn
          [(def__60549
            [arg__60581 ?ns]
            (clojure.core/letfn
             [(state__61683
               []
               (clojure.core/let
                [x__60582 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__60582)
                 (clojure.core/let [?env arg__60581] [?env ?ns])
                 (state__61684))))
              (state__61684
               []
               (if
                (clojure.core/map? arg__60581)
                (clojure.core/let
                 [VAL__60583 (.valAt arg__60581 :aliases)]
                 (if
                  (clojure.core/map? VAL__60583)
                  (clojure.core/let
                   [X__60585 (clojure.core/set VAL__60583)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__60585))
                    (clojure.core/loop
                     [search_space__61685 (clojure.core/seq X__60585)]
                     (if
                      (clojure.core/seq search_space__61685)
                      (clojure.core/let
                       [elem__60586
                        (clojure.core/first search_space__61685)
                        result__61686
                        (clojure.core/let
                         [elem__60586_nth_0__
                          (clojure.core/nth elem__60586 0)
                          elem__60586_nth_1__
                          (clojure.core/nth elem__60586 1)]
                         (if
                          (clojure.core/symbol? elem__60586_nth_0__)
                          (clojure.core/let
                           [X__60588
                            (clojure.core/name elem__60586_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__60588)
                            (if
                             (clojure.core/symbol? elem__60586_nth_1__)
                             (clojure.core/let
                              [X__60590
                               (clojure.core/name elem__60586_nth_1__)]
                              (clojure.core/case
                               X__60590
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__60581]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__61686)
                        (recur (clojure.core/next search_space__61685))
                        result__61686))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__61683)))]
          (if
           (clojure.core/vector? input__60417)
           (if
            (clojure.core/= (clojure.core/count input__60417) 3)
            (clojure.core/let
             [input__60417_nth_0__
              (clojure.core/nth input__60417 0)
              input__60417_nth_1__
              (clojure.core/nth input__60417 1)
              input__60417_nth_2__
              (clojure.core/nth input__60417 2)]
             (clojure.core/case
              input__60417_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__60417_nth_1__)
               (clojure.core/loop
                [search_space__61688
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__60417_nth_1__)]
                (if
                 (clojure.core/seq search_space__61688)
                 (clojure.core/let
                  [input__60417_nth_1___parts__
                   (clojure.core/first search_space__61688)
                   result__61689
                   (clojure.core/let
                    [input__60417_nth_1___l__
                     (clojure.core/nth input__60417_nth_1___parts__ 0)
                     input__60417_nth_1___r__
                     (clojure.core/nth input__60417_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__60417_nth_1___l__)]
                     (clojure.core/let
                      [input__60417_nth_1___r___l__
                       (clojure.core/subvec
                        input__60417_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__60417_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__60417_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__60417_nth_1___r___r__
                         (clojure.core/subvec
                          input__60417_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__60417_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__60417_nth_1___r___l__
                           0)
                          input__60417_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__60417_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__60417_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__60558
                            (clojure.core/namespace
                             input__60417_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__60558]
                            (clojure.core/let
                             [X__60560
                              (clojure.core/name
                               input__60417_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__60560)
                              (if
                               (clojure.core/re-matches
                                #"&.*"
                                X__60560)
                               (clojure.core/let
                                [?pattern
                                 input__60417_nth_1___r___l___nth_1__]
                                (clojure.core/let
                                 [?rest input__60417_nth_1___r___r__]
                                 (clojure.core/let
                                  [x__12596__auto__
                                   (def__60549
                                    input__60417_nth_2__
                                    ?ns)]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    x__12596__auto__)
                                   (meander.runtime.zeta/fail)
                                   (clojure.core/let
                                    [[?env ?ns] x__12596__auto__]
                                    (try
                                     [(clojure.core/let
                                       [!init__counter
                                        (meander.runtime.zeta/iterator
                                         !init)]
                                       (clojure.core/let
                                        [CATA_RESULT__13899__auto__
                                         (CATA__FN__60479
                                          ['meander.dev.parse.zeta/make-join
                                           (clojure.core/let
                                            [CATA_RESULT__13899__auto__
                                             (CATA__FN__60479
                                              ['meander.dev.parse.zeta/parse-sequential
                                               (clojure.core/into
                                                []
                                                (clojure.core/vec
                                                 (clojure.core/iterator-seq
                                                  !init__counter)))
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__13899__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__13899__auto__
                                              0)))
                                           (clojure.core/let
                                            [CATA_RESULT__13899__auto__
                                             (CATA__FN__60479
                                              ['meander.dev.parse.zeta/make-join
                                               (clojure.core/let
                                                [CATA_RESULT__13899__auto__
                                                 (CATA__FN__60479
                                                  [?pattern ?env])]
                                                (if
                                                 (meander.runtime.zeta/fail?
                                                  CATA_RESULT__13899__auto__)
                                                 (throw
                                                  (meander.runtime.zeta/fail))
                                                 (clojure.core/nth
                                                  CATA_RESULT__13899__auto__
                                                  0)))
                                               (clojure.core/let
                                                [CATA_RESULT__13899__auto__
                                                 (CATA__FN__60479
                                                  ['meander.dev.parse.zeta/parse-sequential
                                                   (clojure.core/into
                                                    []
                                                    ?rest)
                                                   ?env])]
                                                (if
                                                 (meander.runtime.zeta/fail?
                                                  CATA_RESULT__13899__auto__)
                                                 (throw
                                                  (meander.runtime.zeta/fail))
                                                 (clojure.core/nth
                                                  CATA_RESULT__13899__auto__
                                                  0)))
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__13899__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__13899__auto__
                                              0)))
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__13899__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__13899__auto__
                                          0))))]
                                     (catch
                                      java.lang.Exception
                                      e__14839__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__14839__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__14839__auto__)))))))))
                               (meander.runtime.zeta/fail))
                              (meander.runtime.zeta/fail)))))
                          (meander.runtime.zeta/fail))))
                       (meander.runtime.zeta/fail)))))]
                  (if
                   (meander.runtime.zeta/fail? result__61689)
                   (recur (clojure.core/next search_space__61688))
                   result__61689))
                 (state__61645)))
               (state__61645))
              (state__61645)))
            (state__61645))
           (state__61645))))
        (state__61645
         []
         (if
          (clojure.core/vector? input__60417)
          (clojure.core/letfn
           [(state__61691
             []
             (if
              (clojure.core/= (clojure.core/count input__60417) 3)
              (clojure.core/let
               [input__60417_nth_0__
                (clojure.core/nth input__60417 0)
                input__60417_nth_1__
                (clojure.core/nth input__60417 1)
                input__60417_nth_2__
                (clojure.core/nth input__60417 2)]
               (clojure.core/case
                input__60417_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__60417_nth_1__)
                 (clojure.core/letfn
                  [(state__61694
                    []
                    (clojure.core/let
                     [n__60611
                      (clojure.core/count input__60417_nth_1__)
                      m__60612
                      (clojure.core/max 0 (clojure.core/- n__60611 2))
                      input__60417_nth_1___l__
                      (clojure.core/subvec
                       input__60417_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__60417_nth_1__)
                        m__60612))
                      input__60417_nth_1___r__
                      (clojure.core/subvec
                       input__60417_nth_1__
                       m__60612)]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__60417_nth_1___r__)
                       2)
                      (clojure.core/let
                       [!xs
                        (clojure.core/vec input__60417_nth_1___l__)]
                       (if
                        (clojure.core/=
                         (clojure.core/count input__60417_nth_1___r__)
                         2)
                        (clojure.core/let
                         [input__60417_nth_1___r___nth_0__
                          (clojure.core/nth input__60417_nth_1___r__ 0)
                          input__60417_nth_1___r___nth_1__
                          (clojure.core/nth
                           input__60417_nth_1___r__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__60417_nth_1___r___nth_0__)
                          (clojure.core/let
                           [X__60616
                            (clojure.core/namespace
                             input__60417_nth_1___r___nth_0__)]
                           (clojure.core/let
                            [?ns X__60616]
                            (clojure.core/let
                             [X__60618
                              (clojure.core/name
                               input__60417_nth_1___r___nth_0__)]
                             (if
                              (clojure.core/string? X__60618)
                              (clojure.core/let
                               [ret__60619
                                (clojure.core/re-matches
                                 #"&.*"
                                 X__60618)]
                               (if
                                (clojure.core/some? ret__60619)
                                (clojure.core/let
                                 [?name ret__60619]
                                 (clojure.core/let
                                  [?pattern
                                   input__60417_nth_1___r___nth_1__]
                                  (if
                                   (clojure.core/map?
                                    input__60417_nth_2__)
                                   (clojure.core/let
                                    [VAL__60603
                                     (.valAt
                                      input__60417_nth_2__
                                      :aliases)]
                                    (if
                                     (clojure.core/map? VAL__60603)
                                     (clojure.core/let
                                      [X__60605
                                       (clojure.core/set VAL__60603)]
                                      (if
                                       (clojure.core/<=
                                        1
                                        (clojure.core/count X__60605))
                                       (clojure.core/loop
                                        [search_space__61698
                                         (clojure.core/seq X__60605)]
                                        (if
                                         (clojure.core/seq
                                          search_space__61698)
                                         (clojure.core/let
                                          [elem__60606
                                           (clojure.core/first
                                            search_space__61698)
                                           result__61699
                                           (clojure.core/let
                                            [elem__60606_nth_0__
                                             (clojure.core/nth
                                              elem__60606
                                              0)
                                             elem__60606_nth_1__
                                             (clojure.core/nth
                                              elem__60606
                                              1)]
                                            (if
                                             (clojure.core/symbol?
                                              elem__60606_nth_0__)
                                             (clojure.core/let
                                              [X__60608
                                               (clojure.core/name
                                                elem__60606_nth_0__)]
                                              (if
                                               (clojure.core/=
                                                ?ns
                                                X__60608)
                                               (if
                                                (clojure.core/symbol?
                                                 elem__60606_nth_1__)
                                                (clojure.core/let
                                                 [X__60610
                                                  (clojure.core/name
                                                   elem__60606_nth_1__)]
                                                 (clojure.core/case
                                                  X__60610
                                                  ("meander.zeta")
                                                  (clojure.core/let
                                                   [?env
                                                    input__60417_nth_2__]
                                                   (try
                                                    [(clojure.core/let
                                                      [!xs__counter
                                                       (meander.runtime.zeta/iterator
                                                        !xs)]
                                                      (clojure.core/let
                                                       [CATA_RESULT__13899__auto__
                                                        (CATA__FN__60479
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
                                                         CATA_RESULT__13899__auto__)
                                                        (throw
                                                         (meander.runtime.zeta/fail))
                                                        (clojure.core/nth
                                                         CATA_RESULT__13899__auto__
                                                         0))))]
                                                    (catch
                                                     java.lang.Exception
                                                     e__14839__auto__
                                                     (if
                                                      (meander.runtime.zeta/fail?
                                                       e__14839__auto__)
                                                      (meander.runtime.zeta/fail)
                                                      (throw
                                                       e__14839__auto__)))))
                                                  (meander.runtime.zeta/fail)))
                                                (meander.runtime.zeta/fail))
                                               (meander.runtime.zeta/fail)))
                                             (meander.runtime.zeta/fail)))]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            result__61699)
                                           (recur
                                            (clojure.core/next
                                             search_space__61698))
                                           result__61699))
                                         (state__61695)))
                                       (state__61695)))
                                     (state__61695)))
                                   (state__61695))))
                                (state__61695)))
                              (state__61695)))))
                          (state__61695)))
                        (state__61695)))
                      (state__61695))))
                   (state__61695
                    []
                    (clojure.core/loop
                     [search_space__61701
                      (meander.runtime.zeta/epsilon-partitions
                       2
                       input__60417_nth_1__)]
                     (if
                      (clojure.core/seq search_space__61701)
                      (clojure.core/let
                       [input__60417_nth_1___parts__
                        (clojure.core/first search_space__61701)
                        result__61702
                        (clojure.core/let
                         [input__60417_nth_1___l__
                          (clojure.core/nth
                           input__60417_nth_1___parts__
                           0)
                          input__60417_nth_1___r__
                          (clojure.core/nth
                           input__60417_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs
                           (clojure.core/vec input__60417_nth_1___l__)]
                          (clojure.core/let
                           [input__60417_nth_1___r___l__
                            (clojure.core/subvec
                             input__60417_nth_1___r__
                             0
                             (clojure.core/min
                              (clojure.core/count
                               input__60417_nth_1___r__)
                              1))]
                           (if
                            (clojure.core/=
                             (clojure.core/count
                              input__60417_nth_1___r___l__)
                             1)
                            (clojure.core/let
                             [input__60417_nth_1___r___r__
                              (clojure.core/subvec
                               input__60417_nth_1___r__
                               1)]
                             (if
                              (clojure.core/=
                               input__60417_nth_1___r___l__
                               ['.])
                              (clojure.core/let
                               [?rest input__60417_nth_1___r___r__]
                               (clojure.core/let
                                [?env input__60417_nth_2__]
                                (try
                                 [(clojure.core/let
                                   [!xs__counter
                                    (meander.runtime.zeta/iterator
                                     !xs)]
                                   (clojure.core/let
                                    [CATA_RESULT__13899__auto__
                                     (CATA__FN__60479
                                      ['meander.dev.parse.zeta/make-join
                                       (clojure.core/let
                                        [CATA_RESULT__13899__auto__
                                         (CATA__FN__60479
                                          ['meander.dev.parse.zeta/parse-sequential
                                           (clojure.core/into
                                            []
                                            (clojure.core/vec
                                             (clojure.core/iterator-seq
                                              !xs__counter)))
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__13899__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__13899__auto__
                                          0)))
                                       (clojure.core/let
                                        [CATA_RESULT__13899__auto__
                                         (CATA__FN__60479
                                          ['meander.dev.parse.zeta/parse-sequential
                                           ?rest
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__13899__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__13899__auto__
                                          0)))
                                       ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__13899__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__13899__auto__
                                      0))))]
                                 (catch
                                  java.lang.Exception
                                  e__14839__auto__
                                  (if
                                   (meander.runtime.zeta/fail?
                                    e__14839__auto__)
                                   (meander.runtime.zeta/fail)
                                   (throw e__14839__auto__))))))
                              (meander.runtime.zeta/fail)))
                            (meander.runtime.zeta/fail)))))]
                       (if
                        (meander.runtime.zeta/fail? result__61702)
                        (recur (clojure.core/next search_space__61701))
                        result__61702))
                      (state__61696))))
                   (state__61696
                    []
                    (clojure.core/let
                     [input__60417_nth_1___l__
                      (clojure.core/subvec
                       input__60417_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__60417_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__60417_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__60417_nth_1___r__
                        (clojure.core/subvec input__60417_nth_1__ 1)]
                       (if
                        (clojure.core/=
                         input__60417_nth_1___l__
                         ['...])
                        (clojure.core/let
                         [?rest input__60417_nth_1___r__]
                         (clojure.core/let
                          [?env input__60417_nth_2__]
                          (try
                           [(clojure.core/let
                             [CATA_RESULT__13899__auto__
                              (CATA__FN__60479
                               ['meander.dev.parse.zeta/parse-sequential
                                ?rest
                                ?env])]
                             (if
                              (meander.runtime.zeta/fail?
                               CATA_RESULT__13899__auto__)
                              (throw (meander.runtime.zeta/fail))
                              (clojure.core/nth
                               CATA_RESULT__13899__auto__
                               0)))]
                           (catch
                            java.lang.Exception
                            e__14839__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__14839__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__14839__auto__))))))
                        (state__61697)))
                      (state__61697))))
                   (state__61697
                    []
                    (clojure.core/loop
                     [search_space__61704
                      (meander.runtime.zeta/epsilon-partitions
                       2
                       input__60417_nth_1__)]
                     (if
                      (clojure.core/seq search_space__61704)
                      (clojure.core/let
                       [input__60417_nth_1___parts__
                        (clojure.core/first search_space__61704)
                        result__61705
                        (clojure.core/let
                         [input__60417_nth_1___l__
                          (clojure.core/nth
                           input__60417_nth_1___parts__
                           0)
                          input__60417_nth_1___r__
                          (clojure.core/nth
                           input__60417_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs []]
                          (clojure.core/let
                           [ret__12760__auto__
                            (meander.runtime.zeta/epsilon-run-star-1
                             input__60417_nth_1___l__
                             [!xs]
                             (clojure.core/fn
                              [[!xs] input__60636]
                              (clojure.core/let
                               [input__60636_nth_0__
                                (clojure.core/nth input__60636 0)]
                               (clojure.core/letfn
                                [(save__60637
                                  []
                                  (meander.runtime.zeta/fail))
                                 (f__61708
                                  []
                                  (clojure.core/let
                                   [!xs
                                    (clojure.core/conj
                                     !xs
                                     input__60636_nth_0__)]
                                   [!xs]))]
                                (if
                                 (clojure.core/symbol?
                                  input__60636_nth_0__)
                                 (clojure.core/let
                                  [X__60639
                                   (clojure.core/namespace
                                    input__60636_nth_0__)]
                                  (clojure.core/case
                                   X__60639
                                   (nil)
                                   (clojure.core/let
                                    [X__60641
                                     (clojure.core/name
                                      input__60636_nth_0__)]
                                    (if
                                     (clojure.core/string? X__60641)
                                     (if
                                      (clojure.core/re-matches
                                       #"\.\.(?:\.|\d+)"
                                       X__60641)
                                      (save__60637)
                                      (f__61708))
                                     (f__61708)))
                                   (f__61708)))
                                 (f__61708)))))
                             (clojure.core/fn
                              [[!xs]]
                              (clojure.core/let
                               [input__60417_nth_1___r___l__
                                (clojure.core/subvec
                                 input__60417_nth_1___r__
                                 0
                                 (clojure.core/min
                                  (clojure.core/count
                                   input__60417_nth_1___r__)
                                  1))]
                               (if
                                (clojure.core/=
                                 (clojure.core/count
                                  input__60417_nth_1___r___l__)
                                 1)
                                (clojure.core/let
                                 [input__60417_nth_1___r___r__
                                  (clojure.core/subvec
                                   input__60417_nth_1___r__
                                   1)]
                                 (if
                                  (clojure.core/=
                                   input__60417_nth_1___r___l__
                                   ['...])
                                  (clojure.core/let
                                   [?rest input__60417_nth_1___r___r__]
                                   (clojure.core/let
                                    [?env input__60417_nth_2__]
                                    (try
                                     [(clojure.core/let
                                       [!xs__counter
                                        (meander.runtime.zeta/iterator
                                         !xs)]
                                       (clojure.core/let
                                        [CATA_RESULT__13899__auto__
                                         (CATA__FN__60479
                                          ['meander.dev.parse.zeta/make-star
                                           (clojure.core/let
                                            [CATA_RESULT__13899__auto__
                                             (CATA__FN__60479
                                              ['meander.dev.parse.zeta/parse-sequential
                                               (clojure.core/into
                                                []
                                                (clojure.core/vec
                                                 (clojure.core/iterator-seq
                                                  !xs__counter)))
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__13899__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__13899__auto__
                                              0)))
                                           (clojure.core/let
                                            [CATA_RESULT__13899__auto__
                                             (CATA__FN__60479
                                              ['meander.dev.parse.zeta/parse-sequential
                                               ?rest
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__13899__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__13899__auto__
                                              0)))
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__13899__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__13899__auto__
                                          0))))]
                                     (catch
                                      java.lang.Exception
                                      e__14839__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__14839__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__14839__auto__))))))
                                  (meander.runtime.zeta/fail)))
                                (meander.runtime.zeta/fail)))))]
                           (if
                            (meander.runtime.zeta/fail?
                             ret__12760__auto__)
                            (meander.runtime.zeta/fail)
                            ret__12760__auto__))))]
                       (if
                        (meander.runtime.zeta/fail? result__61705)
                        (recur (clojure.core/next search_space__61704))
                        result__61705))
                      (state__61692))))]
                  (state__61694))
                 (state__61692))
                (state__61692)))
              (state__61692)))
            (state__61692
             []
             (if
              (clojure.core/= (clojure.core/count input__60417) 4)
              (clojure.core/let
               [input__60417_nth_0__
                (clojure.core/nth input__60417 0)
                input__60417_nth_1__
                (clojure.core/nth input__60417 1)
                input__60417_nth_2__
                (clojure.core/nth input__60417 2)]
               (clojure.core/letfn
                [(state__61709
                  []
                  (clojure.core/let
                   [input__60417_nth_3__
                    (clojure.core/nth input__60417 3)]
                   (clojure.core/case
                    input__60417_nth_0__
                    (meander.dev.parse.zeta/make-star)
                    (clojure.core/letfn
                     [(state__61711
                       []
                       (if
                        (clojure.core/map? input__60417_nth_1__)
                        (clojure.core/let
                         [VAL__60645
                          (.valAt input__60417_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__60645
                          (:cat)
                          (clojure.core/let
                           [VAL__60646
                            (.valAt input__60417_nth_1__ :sequence)]
                           (if
                            (clojure.core/vector? VAL__60646)
                            (if
                             (clojure.core/=
                              (clojure.core/count VAL__60646)
                              1)
                             (clojure.core/let
                              [VAL__60646_nth_0__
                               (clojure.core/nth VAL__60646 0)]
                              (if
                               (clojure.core/map? VAL__60646_nth_0__)
                               (clojure.core/let
                                [VAL__60651
                                 (.valAt VAL__60646_nth_0__ :tag)]
                                (clojure.core/case
                                 VAL__60651
                                 (:memory-variable)
                                 (clojure.core/let
                                  [?memory-variable VAL__60646_nth_0__]
                                  (clojure.core/let
                                   [VAL__60647
                                    (.valAt
                                     input__60417_nth_1__
                                     :next)]
                                   (if
                                    (clojure.core/map? VAL__60647)
                                    (clojure.core/let
                                     [VAL__60648
                                      (.valAt VAL__60647 :tag)]
                                     (clojure.core/case
                                      VAL__60648
                                      (:empty)
                                      (clojure.core/let
                                       [?next input__60417_nth_2__]
                                       (clojure.core/let
                                        [?env input__60417_nth_3__]
                                        (try
                                         [(clojure.core/let
                                           [CATA_RESULT__13899__auto__
                                            (CATA__FN__60479
                                             ['meander.dev.parse.zeta/make-join
                                              {:tag :into,
                                               :memory-variable
                                               ?memory-variable}
                                              ?next
                                              ?env])]
                                           (if
                                            (meander.runtime.zeta/fail?
                                             CATA_RESULT__13899__auto__)
                                            (throw
                                             (meander.runtime.zeta/fail))
                                            (clojure.core/nth
                                             CATA_RESULT__13899__auto__
                                             0)))]
                                         (catch
                                          java.lang.Exception
                                          e__14839__auto__
                                          (if
                                           (meander.runtime.zeta/fail?
                                            e__14839__auto__)
                                           (meander.runtime.zeta/fail)
                                           (throw
                                            e__14839__auto__))))))
                                      (state__61712)))
                                    (state__61712))))
                                 (state__61712)))
                               (state__61712)))
                             (state__61712))
                            (state__61712)))
                          (state__61712)))
                        (state__61712)))
                      (state__61712
                       []
                       (clojure.core/let
                        [?pattern input__60417_nth_1__]
                        (clojure.core/let
                         [?next input__60417_nth_2__]
                         (if
                          (clojure.core/map? input__60417_nth_3__)
                          (clojure.core/let
                           [VAL__60654
                            (.valAt input__60417_nth_3__ :context)]
                           (clojure.core/case
                            VAL__60654
                            (:string)
                            (try
                             [{:tag :string-star,
                               :greedy? false,
                               :pattern ?pattern,
                               :next ?next}]
                             (catch
                              java.lang.Exception
                              e__14839__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__14839__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__14839__auto__))))
                            (state__61710)))
                          (state__61710)))))]
                     (state__61711))
                    (state__61710))))
                 (state__61710
                  []
                  (clojure.core/case
                   input__60417_nth_0__
                   (meander.dev.parse.zeta/make-star)
                   (clojure.core/let
                    [?pattern input__60417_nth_1__]
                    (clojure.core/let
                     [?next input__60417_nth_2__]
                     (try
                      [{:tag :star,
                        :greedy? false,
                        :pattern ?pattern,
                        :next ?next}]
                      (catch
                       java.lang.Exception
                       e__14839__auto__
                       (if
                        (meander.runtime.zeta/fail? e__14839__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__14839__auto__))))))
                   (state__61693)))]
                (state__61709)))
              (state__61693)))
            (state__61693
             []
             (if
              (clojure.core/= (clojure.core/count input__60417) 3)
              (clojure.core/let
               [input__60417_nth_0__
                (clojure.core/nth input__60417 0)
                input__60417_nth_1__
                (clojure.core/nth input__60417 1)
                input__60417_nth_2__
                (clojure.core/nth input__60417 2)]
               (clojure.core/case
                input__60417_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__60417_nth_1__)
                 (clojure.core/let
                  [input__60417_nth_1___l__
                   (clojure.core/subvec
                    input__60417_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__60417_nth_1__)
                     1))]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__60417_nth_1___l__)
                    1)
                   (clojure.core/let
                    [input__60417_nth_1___r__
                     (clojure.core/subvec input__60417_nth_1__ 1)]
                    (clojure.core/let
                     [input__60417_nth_1___l___nth_0__
                      (clojure.core/nth input__60417_nth_1___l__ 0)]
                     (if
                      (clojure.core/symbol?
                       input__60417_nth_1___l___nth_0__)
                      (clojure.core/let
                       [X__60662
                        (clojure.core/namespace
                         input__60417_nth_1___l___nth_0__)]
                       (clojure.core/case
                        X__60662
                        (nil)
                        (clojure.core/let
                         [X__60664
                          (clojure.core/name
                           input__60417_nth_1___l___nth_0__)]
                         (if
                          (clojure.core/string? X__60664)
                          (clojure.core/let
                           [ret__60665
                            (clojure.core/re-matches
                             #"\.\.(\d+)"
                             X__60664)]
                           (if
                            (clojure.core/some? ret__60665)
                            (if
                             (clojure.core/vector? ret__60665)
                             (if
                              (clojure.core/=
                               (clojure.core/count ret__60665)
                               2)
                              (clojure.core/let
                               [ret__60665_nth_1__
                                (clojure.core/nth ret__60665 1)]
                               (clojure.core/let
                                [?n ret__60665_nth_1__]
                                (clojure.core/let
                                 [?operator
                                  input__60417_nth_1___l___nth_0__]
                                 (clojure.core/let
                                  [?rest input__60417_nth_1___r__]
                                  (clojure.core/let
                                   [?env input__60417_nth_2__]
                                   (try
                                    [{:tag :syntax-error,
                                      :message
                                      "The n or more operator ..N must be preceeded by at least one pattern"}]
                                    (catch
                                     java.lang.Exception
                                     e__14839__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__14839__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__14839__auto__)))))))))
                              (state__61646))
                             (state__61646))
                            (state__61646)))
                          (state__61646)))
                        (state__61646)))
                      (state__61646))))
                   (state__61646)))
                 (state__61646))
                (state__61646)))
              (state__61646)))]
           (state__61691))
          (state__61646)))
        (state__61646
         []
         (clojure.core/letfn
          [(def__60668
            [arg__60692]
            (clojure.core/letfn
             [(state__61713
               []
               (clojure.core/let
                [x__60693 :string-plus]
                (clojure.core/let
                 [?tag x__60693]
                 (if
                  (clojure.core/map? arg__60692)
                  (clojure.core/let
                   [VAL__60694 (.valAt arg__60692 :context)]
                   (clojure.core/case
                    VAL__60694
                    (:string)
                    (clojure.core/let [?env arg__60692] [?tag ?env])
                    (state__61714)))
                  (state__61714)))))
              (state__61714
               []
               (clojure.core/let
                [x__60695 :plus]
                (clojure.core/let
                 [?tag x__60695]
                 (clojure.core/let [?env arg__60692] [?tag ?env]))))]
             (state__61713)))]
          (if
           (clojure.core/vector? input__60417)
           (if
            (clojure.core/= (clojure.core/count input__60417) 3)
            (clojure.core/let
             [input__60417_nth_0__
              (clojure.core/nth input__60417 0)
              input__60417_nth_1__
              (clojure.core/nth input__60417 1)
              input__60417_nth_2__
              (clojure.core/nth input__60417 2)]
             (clojure.core/case
              input__60417_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__60417_nth_1__)
               (clojure.core/loop
                [search_space__61715
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__60417_nth_1__)]
                (if
                 (clojure.core/seq search_space__61715)
                 (clojure.core/let
                  [input__60417_nth_1___parts__
                   (clojure.core/first search_space__61715)
                   result__61716
                   (clojure.core/let
                    [input__60417_nth_1___l__
                     (clojure.core/nth input__60417_nth_1___parts__ 0)
                     input__60417_nth_1___r__
                     (clojure.core/nth input__60417_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__12760__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__60417_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__60685]
                         (clojure.core/let
                          [input__60685_nth_0__
                           (clojure.core/nth input__60685 0)]
                          (clojure.core/letfn
                           [(save__60686
                             []
                             (meander.runtime.zeta/fail))
                            (f__61719
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__60685_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol? input__60685_nth_0__)
                            (clojure.core/let
                             [X__60688
                              (clojure.core/namespace
                               input__60685_nth_0__)]
                             (clojure.core/case
                              X__60688
                              (nil)
                              (clojure.core/let
                               [X__60690
                                (clojure.core/name
                                 input__60685_nth_0__)]
                               (if
                                (clojure.core/string? X__60690)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__60690)
                                 (save__60686)
                                 (f__61719))
                                (f__61719)))
                              (f__61719)))
                            (f__61719)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__60417_nth_1___r___l__
                           (clojure.core/subvec
                            input__60417_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__60417_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__60417_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__60417_nth_1___r___r__
                             (clojure.core/subvec
                              input__60417_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__60417_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__60417_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__60417_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__60679
                                (clojure.core/namespace
                                 input__60417_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__60679
                                (nil)
                                (clojure.core/let
                                 [X__60681
                                  (clojure.core/name
                                   input__60417_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__60681)
                                  (clojure.core/let
                                   [ret__60682
                                    (clojure.core/re-matches
                                     #"\.\.(\d+)"
                                     X__60681)]
                                   (if
                                    (clojure.core/some? ret__60682)
                                    (if
                                     (clojure.core/vector? ret__60682)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__60682)
                                       2)
                                      (clojure.core/let
                                       [ret__60682_nth_1__
                                        (clojure.core/nth
                                         ret__60682
                                         1)]
                                       (clojure.core/let
                                        [?n ret__60682_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__60417_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__12596__auto__
                                           (def__60668
                                            input__60417_nth_2__)]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            x__12596__auto__)
                                           (meander.runtime.zeta/fail)
                                           (clojure.core/let
                                            [[?tag ?env]
                                             x__12596__auto__]
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
                                                 [CATA_RESULT__13899__auto__
                                                  (CATA__FN__60479
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    (clojure.core/into
                                                     []
                                                     (clojure.core/vec
                                                      (clojure.core/iterator-seq
                                                       !xs__counter)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__13899__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__13899__auto__
                                                   0))),
                                                :next
                                                (clojure.core/let
                                                 [CATA_RESULT__13899__auto__
                                                  (CATA__FN__60479
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    ?rest
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__13899__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__13899__auto__
                                                   0)))})]
                                             (catch
                                              java.lang.Exception
                                              e__14839__auto__
                                              (if
                                               (meander.runtime.zeta/fail?
                                                e__14839__auto__)
                                               (meander.runtime.zeta/fail)
                                               (throw
                                                e__14839__auto__))))))))))
                                      (meander.runtime.zeta/fail))
                                     (meander.runtime.zeta/fail))
                                    (meander.runtime.zeta/fail)))
                                  (meander.runtime.zeta/fail)))
                                (meander.runtime.zeta/fail)))
                              (meander.runtime.zeta/fail))))
                           (meander.runtime.zeta/fail)))))]
                      (if
                       (meander.runtime.zeta/fail? ret__12760__auto__)
                       (meander.runtime.zeta/fail)
                       ret__12760__auto__))))]
                  (if
                   (meander.runtime.zeta/fail? result__61716)
                   (recur (clojure.core/next search_space__61715))
                   result__61716))
                 (state__61647)))
               (state__61647))
              (state__61647)))
            (state__61647))
           (state__61647))))
        (state__61647
         []
         (if
          (clojure.core/vector? input__60417)
          (if
           (clojure.core/= (clojure.core/count input__60417) 3)
           (clojure.core/let
            [input__60417_nth_0__
             (clojure.core/nth input__60417 0)
             input__60417_nth_1__
             (clojure.core/nth input__60417 1)
             input__60417_nth_2__
             (clojure.core/nth input__60417 2)]
            (clojure.core/case
             input__60417_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__60417_nth_1__)
              (clojure.core/let
               [input__60417_nth_1___l__
                (clojure.core/subvec
                 input__60417_nth_1__
                 0
                 (clojure.core/min
                  (clojure.core/count input__60417_nth_1__)
                  1))]
               (if
                (clojure.core/=
                 (clojure.core/count input__60417_nth_1___l__)
                 1)
                (clojure.core/let
                 [input__60417_nth_1___r__
                  (clojure.core/subvec input__60417_nth_1__ 1)]
                 (clojure.core/let
                  [input__60417_nth_1___l___nth_0__
                   (clojure.core/nth input__60417_nth_1___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__60417_nth_1___l___nth_0__)
                   (clojure.core/let
                    [X__60713
                     (clojure.core/namespace
                      input__60417_nth_1___l___nth_0__)]
                    (clojure.core/case
                     X__60713
                     (nil)
                     (clojure.core/let
                      [X__60715
                       (clojure.core/name
                        input__60417_nth_1___l___nth_0__)]
                      (if
                       (clojure.core/string? X__60715)
                       (clojure.core/let
                        [ret__60716
                         (clojure.core/re-matches
                          #"\.\.(\?.+)"
                          X__60715)]
                        (if
                         (clojure.core/some? ret__60716)
                         (if
                          (clojure.core/vector? ret__60716)
                          (if
                           (clojure.core/=
                            (clojure.core/count ret__60716)
                            2)
                           (clojure.core/let
                            [ret__60716_nth_1__
                             (clojure.core/nth ret__60716 1)]
                            (clojure.core/let
                             [?n ret__60716_nth_1__]
                             (clojure.core/let
                              [?operator
                               input__60417_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?rest input__60417_nth_1___r__]
                               (clojure.core/let
                                [?env input__60417_nth_2__]
                                (try
                                 [{:tag :syntax-error,
                                   :message
                                   "The ?n or more operator ..?n must be preceeded by at least one pattern"}]
                                 (catch
                                  java.lang.Exception
                                  e__14839__auto__
                                  (if
                                   (meander.runtime.zeta/fail?
                                    e__14839__auto__)
                                   (meander.runtime.zeta/fail)
                                   (throw e__14839__auto__)))))))))
                           (state__61648))
                          (state__61648))
                         (state__61648)))
                       (state__61648)))
                     (state__61648)))
                   (state__61648))))
                (state__61648)))
              (state__61648))
             (state__61648)))
           (state__61648))
          (state__61648)))
        (state__61648
         []
         (clojure.core/letfn
          [(def__60719
            [arg__60743]
            (clojure.core/letfn
             [(state__61720
               []
               (clojure.core/let
                [x__60744 :string-logical-plus]
                (clojure.core/let
                 [?tag x__60744]
                 (if
                  (clojure.core/map? arg__60743)
                  (clojure.core/let
                   [VAL__60745 (.valAt arg__60743 :context)]
                   (clojure.core/case
                    VAL__60745
                    (:string)
                    (clojure.core/let [?env arg__60743] [?tag ?env])
                    (state__61721)))
                  (state__61721)))))
              (state__61721
               []
               (clojure.core/let
                [x__60746 :logical-plus]
                (clojure.core/let
                 [?tag x__60746]
                 (clojure.core/let [?env arg__60743] [?tag ?env]))))]
             (state__61720)))]
          (if
           (clojure.core/vector? input__60417)
           (if
            (clojure.core/= (clojure.core/count input__60417) 3)
            (clojure.core/let
             [input__60417_nth_0__
              (clojure.core/nth input__60417 0)
              input__60417_nth_1__
              (clojure.core/nth input__60417 1)
              input__60417_nth_2__
              (clojure.core/nth input__60417 2)]
             (clojure.core/case
              input__60417_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__60417_nth_1__)
               (clojure.core/loop
                [search_space__61722
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__60417_nth_1__)]
                (if
                 (clojure.core/seq search_space__61722)
                 (clojure.core/let
                  [input__60417_nth_1___parts__
                   (clojure.core/first search_space__61722)
                   result__61723
                   (clojure.core/let
                    [input__60417_nth_1___l__
                     (clojure.core/nth input__60417_nth_1___parts__ 0)
                     input__60417_nth_1___r__
                     (clojure.core/nth input__60417_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__12760__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__60417_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__60736]
                         (clojure.core/let
                          [input__60736_nth_0__
                           (clojure.core/nth input__60736 0)]
                          (clojure.core/letfn
                           [(save__60737
                             []
                             (meander.runtime.zeta/fail))
                            (f__61726
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__60736_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol? input__60736_nth_0__)
                            (clojure.core/let
                             [X__60739
                              (clojure.core/namespace
                               input__60736_nth_0__)]
                             (clojure.core/case
                              X__60739
                              (nil)
                              (clojure.core/let
                               [X__60741
                                (clojure.core/name
                                 input__60736_nth_0__)]
                               (if
                                (clojure.core/string? X__60741)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__60741)
                                 (save__60737)
                                 (f__61726))
                                (f__61726)))
                              (f__61726)))
                            (f__61726)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__60417_nth_1___r___l__
                           (clojure.core/subvec
                            input__60417_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__60417_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__60417_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__60417_nth_1___r___r__
                             (clojure.core/subvec
                              input__60417_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__60417_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__60417_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__60417_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__60730
                                (clojure.core/namespace
                                 input__60417_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__60730
                                (nil)
                                (clojure.core/let
                                 [X__60732
                                  (clojure.core/name
                                   input__60417_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__60732)
                                  (clojure.core/let
                                   [ret__60733
                                    (clojure.core/re-matches
                                     #"\.\.(\?.+)"
                                     X__60732)]
                                   (if
                                    (clojure.core/some? ret__60733)
                                    (if
                                     (clojure.core/vector? ret__60733)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__60733)
                                       2)
                                      (clojure.core/let
                                       [ret__60733_nth_1__
                                        (clojure.core/nth
                                         ret__60733
                                         1)]
                                       (clojure.core/let
                                        [?n ret__60733_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__60417_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__12596__auto__
                                           (def__60719
                                            input__60417_nth_2__)]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            x__12596__auto__)
                                           (meander.runtime.zeta/fail)
                                           (clojure.core/let
                                            [[?tag ?env]
                                             x__12596__auto__]
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
                                                 [CATA_RESULT__13899__auto__
                                                  (CATA__FN__60479
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    (clojure.core/into
                                                     []
                                                     (clojure.core/vec
                                                      (clojure.core/iterator-seq
                                                       !xs__counter)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__13899__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__13899__auto__
                                                   0))),
                                                :next
                                                (clojure.core/let
                                                 [CATA_RESULT__13899__auto__
                                                  (CATA__FN__60479
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    ?rest
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__13899__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__13899__auto__
                                                   0)))})]
                                             (catch
                                              java.lang.Exception
                                              e__14839__auto__
                                              (if
                                               (meander.runtime.zeta/fail?
                                                e__14839__auto__)
                                               (meander.runtime.zeta/fail)
                                               (throw
                                                e__14839__auto__))))))))))
                                      (meander.runtime.zeta/fail))
                                     (meander.runtime.zeta/fail))
                                    (meander.runtime.zeta/fail)))
                                  (meander.runtime.zeta/fail)))
                                (meander.runtime.zeta/fail)))
                              (meander.runtime.zeta/fail))))
                           (meander.runtime.zeta/fail)))))]
                      (if
                       (meander.runtime.zeta/fail? ret__12760__auto__)
                       (meander.runtime.zeta/fail)
                       ret__12760__auto__))))]
                  (if
                   (meander.runtime.zeta/fail? result__61723)
                   (recur (clojure.core/next search_space__61722))
                   result__61723))
                 (state__61649)))
               (state__61649))
              (state__61649)))
            (state__61649))
           (state__61649))))
        (state__61649
         []
         (if
          (clojure.core/vector? input__60417)
          (if
           (clojure.core/= (clojure.core/count input__60417) 3)
           (clojure.core/let
            [input__60417_nth_0__
             (clojure.core/nth input__60417 0)
             input__60417_nth_1__
             (clojure.core/nth input__60417 1)
             input__60417_nth_2__
             (clojure.core/nth input__60417 2)]
            (clojure.core/case
             input__60417_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__60417_nth_1__)
              (clojure.core/let
               [input__60417_nth_1___l__
                (clojure.core/subvec
                 input__60417_nth_1__
                 0
                 (clojure.core/min
                  (clojure.core/count input__60417_nth_1__)
                  1))]
               (if
                (clojure.core/=
                 (clojure.core/count input__60417_nth_1___l__)
                 1)
                (clojure.core/let
                 [input__60417_nth_1___r__
                  (clojure.core/subvec input__60417_nth_1__ 1)]
                 (clojure.core/let
                  [input__60417_nth_1___l___nth_0__
                   (clojure.core/nth input__60417_nth_1___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__60417_nth_1___l___nth_0__)
                   (clojure.core/let
                    [X__60764
                     (clojure.core/namespace
                      input__60417_nth_1___l___nth_0__)]
                    (clojure.core/case
                     X__60764
                     (nil)
                     (clojure.core/let
                      [X__60766
                       (clojure.core/name
                        input__60417_nth_1___l___nth_0__)]
                      (if
                       (clojure.core/string? X__60766)
                       (clojure.core/let
                        [ret__60767
                         (clojure.core/re-matches
                          #"\.\.(!.+)"
                          X__60766)]
                        (if
                         (clojure.core/some? ret__60767)
                         (if
                          (clojure.core/vector? ret__60767)
                          (if
                           (clojure.core/=
                            (clojure.core/count ret__60767)
                            2)
                           (clojure.core/let
                            [ret__60767_nth_1__
                             (clojure.core/nth ret__60767 1)]
                            (clojure.core/let
                             [?n ret__60767_nth_1__]
                             (clojure.core/let
                              [?operator
                               input__60417_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?rest input__60417_nth_1___r__]
                               (clojure.core/let
                                [?env input__60417_nth_2__]
                                (try
                                 [{:tag :syntax-error,
                                   :message
                                   "The operator ..!n must be preceeded by at least one pattern"}]
                                 (catch
                                  java.lang.Exception
                                  e__14839__auto__
                                  (if
                                   (meander.runtime.zeta/fail?
                                    e__14839__auto__)
                                   (meander.runtime.zeta/fail)
                                   (throw e__14839__auto__)))))))))
                           (state__61650))
                          (state__61650))
                         (state__61650)))
                       (state__61650)))
                     (state__61650)))
                   (state__61650))))
                (state__61650)))
              (state__61650))
             (state__61650)))
           (state__61650))
          (state__61650)))
        (state__61650
         []
         (clojure.core/letfn
          [(def__60770
            [arg__60794]
            (clojure.core/letfn
             [(state__61727
               []
               (clojure.core/let
                [x__60795 :string-memory-plus]
                (clojure.core/let
                 [?tag x__60795]
                 (if
                  (clojure.core/map? arg__60794)
                  (clojure.core/let
                   [VAL__60796 (.valAt arg__60794 :context)]
                   (clojure.core/case
                    VAL__60796
                    (:string)
                    (clojure.core/let [?env arg__60794] [?tag ?env])
                    (state__61728)))
                  (state__61728)))))
              (state__61728
               []
               (clojure.core/let
                [x__60797 :memory-plus]
                (clojure.core/let
                 [?tag x__60797]
                 (clojure.core/let [?env arg__60794] [?tag ?env]))))]
             (state__61727)))]
          (if
           (clojure.core/vector? input__60417)
           (if
            (clojure.core/= (clojure.core/count input__60417) 3)
            (clojure.core/let
             [input__60417_nth_0__
              (clojure.core/nth input__60417 0)
              input__60417_nth_1__
              (clojure.core/nth input__60417 1)
              input__60417_nth_2__
              (clojure.core/nth input__60417 2)]
             (clojure.core/case
              input__60417_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__60417_nth_1__)
               (clojure.core/loop
                [search_space__61729
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__60417_nth_1__)]
                (if
                 (clojure.core/seq search_space__61729)
                 (clojure.core/let
                  [input__60417_nth_1___parts__
                   (clojure.core/first search_space__61729)
                   result__61730
                   (clojure.core/let
                    [input__60417_nth_1___l__
                     (clojure.core/nth input__60417_nth_1___parts__ 0)
                     input__60417_nth_1___r__
                     (clojure.core/nth input__60417_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__12760__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__60417_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__60787]
                         (clojure.core/let
                          [input__60787_nth_0__
                           (clojure.core/nth input__60787 0)]
                          (clojure.core/letfn
                           [(save__60788
                             []
                             (meander.runtime.zeta/fail))
                            (f__61733
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__60787_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol? input__60787_nth_0__)
                            (clojure.core/let
                             [X__60790
                              (clojure.core/namespace
                               input__60787_nth_0__)]
                             (clojure.core/case
                              X__60790
                              (nil)
                              (clojure.core/let
                               [X__60792
                                (clojure.core/name
                                 input__60787_nth_0__)]
                               (if
                                (clojure.core/string? X__60792)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__60792)
                                 (save__60788)
                                 (f__61733))
                                (f__61733)))
                              (f__61733)))
                            (f__61733)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__60417_nth_1___r___l__
                           (clojure.core/subvec
                            input__60417_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__60417_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__60417_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__60417_nth_1___r___r__
                             (clojure.core/subvec
                              input__60417_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__60417_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__60417_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__60417_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__60781
                                (clojure.core/namespace
                                 input__60417_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__60781
                                (nil)
                                (clojure.core/let
                                 [X__60783
                                  (clojure.core/name
                                   input__60417_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__60783)
                                  (clojure.core/let
                                   [ret__60784
                                    (clojure.core/re-matches
                                     #"\.\.(\!.+)"
                                     X__60783)]
                                   (if
                                    (clojure.core/some? ret__60784)
                                    (if
                                     (clojure.core/vector? ret__60784)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__60784)
                                       2)
                                      (clojure.core/let
                                       [ret__60784_nth_1__
                                        (clojure.core/nth
                                         ret__60784
                                         1)]
                                       (clojure.core/let
                                        [?n ret__60784_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__60417_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__12596__auto__
                                           (def__60770
                                            input__60417_nth_2__)]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            x__12596__auto__)
                                           (meander.runtime.zeta/fail)
                                           (clojure.core/let
                                            [[?tag ?env]
                                             x__12596__auto__]
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
                                                 [CATA_RESULT__13899__auto__
                                                  (CATA__FN__60479
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    (clojure.core/into
                                                     []
                                                     (clojure.core/vec
                                                      (clojure.core/iterator-seq
                                                       !xs__counter)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__13899__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__13899__auto__
                                                   0))),
                                                :next
                                                (clojure.core/let
                                                 [CATA_RESULT__13899__auto__
                                                  (CATA__FN__60479
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    ?rest
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__13899__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__13899__auto__
                                                   0)))})]
                                             (catch
                                              java.lang.Exception
                                              e__14839__auto__
                                              (if
                                               (meander.runtime.zeta/fail?
                                                e__14839__auto__)
                                               (meander.runtime.zeta/fail)
                                               (throw
                                                e__14839__auto__))))))))))
                                      (meander.runtime.zeta/fail))
                                     (meander.runtime.zeta/fail))
                                    (meander.runtime.zeta/fail)))
                                  (meander.runtime.zeta/fail)))
                                (meander.runtime.zeta/fail)))
                              (meander.runtime.zeta/fail))))
                           (meander.runtime.zeta/fail)))))]
                      (if
                       (meander.runtime.zeta/fail? ret__12760__auto__)
                       (meander.runtime.zeta/fail)
                       ret__12760__auto__))))]
                  (if
                   (meander.runtime.zeta/fail? result__61730)
                   (recur (clojure.core/next search_space__61729))
                   result__61730))
                 (state__61651)))
               (state__61651))
              (state__61651)))
            (state__61651))
           (state__61651))))
        (state__61651
         []
         (if
          (clojure.core/vector? input__60417)
          (clojure.core/letfn
           [(state__61734
             []
             (if
              (clojure.core/= (clojure.core/count input__60417) 3)
              (clojure.core/let
               [input__60417_nth_0__
                (clojure.core/nth input__60417 0)
                input__60417_nth_1__
                (clojure.core/nth input__60417 1)
                input__60417_nth_2__
                (clojure.core/nth input__60417 2)]
               (clojure.core/case
                input__60417_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__60417_nth_1__)
                 (clojure.core/let
                  [!xs (clojure.core/vec input__60417_nth_1__)]
                  (clojure.core/let
                   [?env input__60417_nth_2__]
                   (try
                    [(clojure.core/let
                      [!xs__counter
                       (meander.runtime.zeta/iterator !xs)]
                      (clojure.core/let
                       [CATA_RESULT__13899__auto__
                        (CATA__FN__60479
                         ['meander.dev.parse.zeta/make-cat
                          (clojure.core/into
                           []
                           (clojure.core/loop
                            [return__60480 (clojure.core/transient [])]
                            (if
                             (clojure.core/and (.hasNext !xs__counter))
                             (recur
                              (clojure.core/conj!
                               return__60480
                               (clojure.core/let
                                [CATA_RESULT__13899__auto__
                                 (CATA__FN__60479
                                  [(if
                                    (.hasNext !xs__counter)
                                    (.next !xs__counter))
                                   ?env])]
                                (if
                                 (meander.runtime.zeta/fail?
                                  CATA_RESULT__13899__auto__)
                                 (throw (meander.runtime.zeta/fail))
                                 (clojure.core/nth
                                  CATA_RESULT__13899__auto__
                                  0)))))
                             (clojure.core/persistent!
                              return__60480))))
                          {:tag :empty}
                          ?env])]
                       (if
                        (meander.runtime.zeta/fail?
                         CATA_RESULT__13899__auto__)
                        (throw (meander.runtime.zeta/fail))
                        (clojure.core/nth
                         CATA_RESULT__13899__auto__
                         0))))]
                    (catch
                     java.lang.Exception
                     e__14839__auto__
                     (if
                      (meander.runtime.zeta/fail? e__14839__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__14839__auto__))))))
                 (state__61735))
                (state__61735)))
              (state__61735)))
            (state__61735
             []
             (if
              (clojure.core/= (clojure.core/count input__60417) 4)
              (clojure.core/let
               [input__60417_nth_0__
                (clojure.core/nth input__60417 0)
                input__60417_nth_1__
                (clojure.core/nth input__60417 1)
                input__60417_nth_2__
                (clojure.core/nth input__60417 2)]
               (clojure.core/letfn
                [(state__61737
                  []
                  (clojure.core/let
                   [input__60417_nth_3__
                    (clojure.core/nth input__60417 3)]
                   (clojure.core/case
                    input__60417_nth_0__
                    (meander.dev.parse.zeta/make-cat)
                    (if
                     (clojure.core/vector? input__60417_nth_1__)
                     (clojure.core/letfn
                      [(state__61744
                        []
                        (clojure.core/case
                         input__60417_nth_1__
                         ([])
                         (clojure.core/let
                          [?next input__60417_nth_2__]
                          (clojure.core/let
                           [?env input__60417_nth_3__]
                           (try
                            [?next]
                            (catch
                             java.lang.Exception
                             e__14839__auto__
                             (if
                              (meander.runtime.zeta/fail?
                               e__14839__auto__)
                              (meander.runtime.zeta/fail)
                              (throw e__14839__auto__))))))
                         (state__61745)))
                       (state__61745
                        []
                        (clojure.core/loop
                         [search_space__61746
                          (meander.runtime.zeta/epsilon-partitions
                           2
                           input__60417_nth_1__)]
                         (if
                          (clojure.core/seq search_space__61746)
                          (clojure.core/let
                           [input__60417_nth_1___parts__
                            (clojure.core/first search_space__61746)
                            result__61747
                            (clojure.core/let
                             [input__60417_nth_1___l__
                              (clojure.core/nth
                               input__60417_nth_1___parts__
                               0)
                              input__60417_nth_1___r__
                              (clojure.core/nth
                               input__60417_nth_1___parts__
                               1)]
                             (clojure.core/letfn
                              [(state__61749
                                []
                                (clojure.core/let
                                 [!xs []]
                                 (clojure.core/let
                                  [ret__12760__auto__
                                   (meander.runtime.zeta/epsilon-run-star-1
                                    input__60417_nth_1___l__
                                    [!xs]
                                    (clojure.core/fn
                                     [[!xs] input__60823]
                                     (clojure.core/let
                                      [input__60823_nth_0__
                                       (clojure.core/nth
                                        input__60823
                                        0)]
                                      (clojure.core/letfn
                                       [(save__60824
                                         []
                                         (meander.runtime.zeta/fail))
                                        (f__61752
                                         []
                                         (clojure.core/let
                                          [!xs
                                           (clojure.core/conj
                                            !xs
                                            input__60823_nth_0__)]
                                          [!xs]))]
                                       (if
                                        (clojure.core/map?
                                         input__60823_nth_0__)
                                        (clojure.core/let
                                         [VAL__60825
                                          (.valAt
                                           input__60823_nth_0__
                                           :tag)]
                                         (clojure.core/case
                                          VAL__60825
                                          (:group)
                                          (save__60824)
                                          (f__61752)))
                                        (f__61752)))))
                                    (clojure.core/fn
                                     [[!xs]]
                                     (clojure.core/let
                                      [input__60417_nth_1___r___l__
                                       (clojure.core/subvec
                                        input__60417_nth_1___r__
                                        0
                                        (clojure.core/min
                                         (clojure.core/count
                                          input__60417_nth_1___r__)
                                         1))]
                                      (if
                                       (clojure.core/=
                                        (clojure.core/count
                                         input__60417_nth_1___r___l__)
                                        1)
                                       (clojure.core/let
                                        [input__60417_nth_1___r___r__
                                         (clojure.core/subvec
                                          input__60417_nth_1___r__
                                          1)]
                                        (clojure.core/let
                                         [input__60417_nth_1___r___l___nth_0__
                                          (clojure.core/nth
                                           input__60417_nth_1___r___l__
                                           0)]
                                         (if
                                          (clojure.core/map?
                                           input__60417_nth_1___r___l___nth_0__)
                                          (clojure.core/let
                                           [VAL__60822
                                            (.valAt
                                             input__60417_nth_1___r___l___nth_0__
                                             :tag)]
                                           (clojure.core/case
                                            VAL__60822
                                            (:group)
                                            (clojure.core/let
                                             [?group
                                              input__60417_nth_1___r___l___nth_0__]
                                             (clojure.core/let
                                              [?rest
                                               input__60417_nth_1___r___r__]
                                              (clojure.core/let
                                               [?next
                                                input__60417_nth_2__]
                                               (clojure.core/let
                                                [?env
                                                 input__60417_nth_3__]
                                                (try
                                                 [(clojure.core/let
                                                   [!xs__counter
                                                    (meander.runtime.zeta/iterator
                                                     !xs)]
                                                   (clojure.core/let
                                                    [CATA_RESULT__13899__auto__
                                                     (CATA__FN__60479
                                                      ['meander.dev.parse.zeta/make-join
                                                       (clojure.core/let
                                                        [CATA_RESULT__13899__auto__
                                                         (CATA__FN__60479
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
                                                          CATA_RESULT__13899__auto__)
                                                         (throw
                                                          (meander.runtime.zeta/fail))
                                                         (clojure.core/nth
                                                          CATA_RESULT__13899__auto__
                                                          0)))
                                                       (clojure.core/let
                                                        [CATA_RESULT__13899__auto__
                                                         (CATA__FN__60479
                                                          ['meander.dev.parse.zeta/make-join
                                                           ?group
                                                           (clojure.core/let
                                                            [CATA_RESULT__13899__auto__
                                                             (CATA__FN__60479
                                                              ['meander.dev.parse.zeta/make-cat
                                                               ?rest
                                                               ?next
                                                               ?env])]
                                                            (if
                                                             (meander.runtime.zeta/fail?
                                                              CATA_RESULT__13899__auto__)
                                                             (throw
                                                              (meander.runtime.zeta/fail))
                                                             (clojure.core/nth
                                                              CATA_RESULT__13899__auto__
                                                              0)))
                                                           ?env])]
                                                        (if
                                                         (meander.runtime.zeta/fail?
                                                          CATA_RESULT__13899__auto__)
                                                         (throw
                                                          (meander.runtime.zeta/fail))
                                                         (clojure.core/nth
                                                          CATA_RESULT__13899__auto__
                                                          0)))
                                                       ?env])]
                                                    (if
                                                     (meander.runtime.zeta/fail?
                                                      CATA_RESULT__13899__auto__)
                                                     (throw
                                                      (meander.runtime.zeta/fail))
                                                     (clojure.core/nth
                                                      CATA_RESULT__13899__auto__
                                                      0))))]
                                                 (catch
                                                  java.lang.Exception
                                                  e__14839__auto__
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    e__14839__auto__)
                                                   (meander.runtime.zeta/fail)
                                                   (throw
                                                    e__14839__auto__))))))))
                                            (state__61750)))
                                          (state__61750))))
                                       (state__61750)))))]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    ret__12760__auto__)
                                   (state__61750)
                                   ret__12760__auto__))))
                               (state__61750
                                []
                                (clojure.core/let
                                 [!xs []]
                                 (clojure.core/let
                                  [ret__12760__auto__
                                   (meander.runtime.zeta/epsilon-run-star-1
                                    input__60417_nth_1___l__
                                    [!xs]
                                    (clojure.core/fn
                                     [[!xs] input__60834]
                                     (clojure.core/let
                                      [input__60834_nth_0__
                                       (clojure.core/nth
                                        input__60834
                                        0)]
                                      (clojure.core/letfn
                                       [(save__60835
                                         []
                                         (meander.runtime.zeta/fail))
                                        (f__61754
                                         []
                                         (clojure.core/let
                                          [!xs
                                           (clojure.core/conj
                                            !xs
                                            input__60834_nth_0__)]
                                          [!xs]))]
                                       (if
                                        (clojure.core/map?
                                         input__60834_nth_0__)
                                        (clojure.core/let
                                         [VAL__60836
                                          (.valAt
                                           input__60834_nth_0__
                                           :tag)]
                                         (clojure.core/case
                                          VAL__60836
                                          (:star)
                                          (save__60835)
                                          (f__61754)))
                                        (f__61754)))))
                                    (clojure.core/fn
                                     [[!xs]]
                                     (clojure.core/let
                                      [input__60417_nth_1___r___l__
                                       (clojure.core/subvec
                                        input__60417_nth_1___r__
                                        0
                                        (clojure.core/min
                                         (clojure.core/count
                                          input__60417_nth_1___r__)
                                         1))]
                                      (if
                                       (clojure.core/=
                                        (clojure.core/count
                                         input__60417_nth_1___r___l__)
                                        1)
                                       (clojure.core/let
                                        [input__60417_nth_1___r___r__
                                         (clojure.core/subvec
                                          input__60417_nth_1___r__
                                          1)]
                                        (clojure.core/let
                                         [input__60417_nth_1___r___l___nth_0__
                                          (clojure.core/nth
                                           input__60417_nth_1___r___l__
                                           0)]
                                         (if
                                          (clojure.core/map?
                                           input__60417_nth_1___r___l___nth_0__)
                                          (clojure.core/let
                                           [VAL__60833
                                            (.valAt
                                             input__60417_nth_1___r___l___nth_0__
                                             :tag)]
                                           (clojure.core/case
                                            VAL__60833
                                            (:star)
                                            (clojure.core/let
                                             [?group
                                              input__60417_nth_1___r___l___nth_0__]
                                             (clojure.core/let
                                              [?rest
                                               input__60417_nth_1___r___r__]
                                              (clojure.core/let
                                               [?next
                                                input__60417_nth_2__]
                                               (clojure.core/let
                                                [?env
                                                 input__60417_nth_3__]
                                                (try
                                                 [(clojure.core/let
                                                   [!xs__counter
                                                    (meander.runtime.zeta/iterator
                                                     !xs)]
                                                   (clojure.core/let
                                                    [CATA_RESULT__13899__auto__
                                                     (CATA__FN__60479
                                                      ['meander.dev.parse.zeta/make-join
                                                       (clojure.core/let
                                                        [CATA_RESULT__13899__auto__
                                                         (CATA__FN__60479
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
                                                          CATA_RESULT__13899__auto__)
                                                         (throw
                                                          (meander.runtime.zeta/fail))
                                                         (clojure.core/nth
                                                          CATA_RESULT__13899__auto__
                                                          0)))
                                                       (clojure.core/let
                                                        [CATA_RESULT__13899__auto__
                                                         (CATA__FN__60479
                                                          ['meander.dev.parse.zeta/make-join
                                                           ?group
                                                           (clojure.core/let
                                                            [CATA_RESULT__13899__auto__
                                                             (CATA__FN__60479
                                                              ['meander.dev.parse.zeta/make-cat
                                                               ?rest
                                                               ?next
                                                               ?env])]
                                                            (if
                                                             (meander.runtime.zeta/fail?
                                                              CATA_RESULT__13899__auto__)
                                                             (throw
                                                              (meander.runtime.zeta/fail))
                                                             (clojure.core/nth
                                                              CATA_RESULT__13899__auto__
                                                              0)))
                                                           ?env])]
                                                        (if
                                                         (meander.runtime.zeta/fail?
                                                          CATA_RESULT__13899__auto__)
                                                         (throw
                                                          (meander.runtime.zeta/fail))
                                                         (clojure.core/nth
                                                          CATA_RESULT__13899__auto__
                                                          0)))
                                                       ?env])]
                                                    (if
                                                     (meander.runtime.zeta/fail?
                                                      CATA_RESULT__13899__auto__)
                                                     (throw
                                                      (meander.runtime.zeta/fail))
                                                     (clojure.core/nth
                                                      CATA_RESULT__13899__auto__
                                                      0))))]
                                                 (catch
                                                  java.lang.Exception
                                                  e__14839__auto__
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    e__14839__auto__)
                                                   (meander.runtime.zeta/fail)
                                                   (throw
                                                    e__14839__auto__))))))))
                                            (meander.runtime.zeta/fail)))
                                          (meander.runtime.zeta/fail))))
                                       (meander.runtime.zeta/fail)))))]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    ret__12760__auto__)
                                   (meander.runtime.zeta/fail)
                                   ret__12760__auto__))))]
                              (state__61749)))]
                           (if
                            (meander.runtime.zeta/fail? result__61747)
                            (recur
                             (clojure.core/next search_space__61746))
                            result__61747))
                          (state__61738))))]
                      (state__61744))
                     (state__61738))
                    (state__61738))))
                 (state__61738
                  []
                  (clojure.core/case
                   input__60417_nth_0__
                   (meander.dev.parse.zeta/make-cat)
                   (if
                    (clojure.core/vector? input__60417_nth_1__)
                    (clojure.core/let
                     [input__60417_nth_1___l__
                      (clojure.core/subvec
                       input__60417_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__60417_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__60417_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__60417_nth_1___r__
                        (clojure.core/subvec input__60417_nth_1__ 1)]
                       (clojure.core/let
                        [input__60417_nth_1___l___nth_0__
                         (clojure.core/nth input__60417_nth_1___l__ 0)]
                        (if
                         (clojure.core/map?
                          input__60417_nth_1___l___nth_0__)
                         (clojure.core/let
                          [VAL__60845
                           (.valAt
                            input__60417_nth_1___l___nth_0__
                            :tag)]
                          (clojure.core/case
                           VAL__60845
                           (:literal)
                           (clojure.core/let
                            [VAL__60846
                             (.valAt
                              input__60417_nth_1___l___nth_0__
                              :type)]
                            (if
                             (clojure.core/let
                              [x__11656__auto__ VAL__60846]
                              (clojure.core/case
                               x__11656__auto__
                               (:string :char)
                               true
                               false))
                             (clojure.core/let
                              [VAL__60847
                               (.valAt
                                input__60417_nth_1___l___nth_0__
                                :form)]
                              (clojure.core/let
                               [!forms []]
                               (clojure.core/let
                                [!forms
                                 (clojure.core/conj !forms VAL__60847)]
                                (clojure.core/loop
                                 [i__12733__auto__
                                  0
                                  coll__61755
                                  input__60417_nth_1___r__
                                  [!forms]
                                  [!forms]]
                                 (clojure.core/let
                                  [input__60848
                                   (clojure.core/subvec
                                    coll__61755
                                    0
                                    (clojure.core/min
                                     (clojure.core/count coll__61755)
                                     1))]
                                  (if
                                   (clojure.core/=
                                    (clojure.core/count input__60848)
                                    1)
                                   (clojure.core/let
                                    [result__12734__auto__
                                     (clojure.core/let
                                      [input__60848_nth_0__
                                       (clojure.core/nth
                                        input__60848
                                        0)]
                                      (if
                                       (clojure.core/map?
                                        input__60848_nth_0__)
                                       (clojure.core/let
                                        [VAL__60849
                                         (.valAt
                                          input__60848_nth_0__
                                          :tag)]
                                        (clojure.core/case
                                         VAL__60849
                                         (:literal)
                                         (clojure.core/let
                                          [VAL__60850
                                           (.valAt
                                            input__60848_nth_0__
                                            :type)]
                                          (if
                                           (clojure.core/let
                                            [x__11656__auto__
                                             VAL__60850]
                                            (clojure.core/case
                                             x__11656__auto__
                                             (:string :char)
                                             true
                                             false))
                                           (clojure.core/let
                                            [VAL__60851
                                             (.valAt
                                              input__60848_nth_0__
                                              :form)]
                                            (clojure.core/let
                                             [!forms
                                              (clojure.core/conj
                                               !forms
                                               VAL__60851)]
                                             [!forms]))
                                           (meander.runtime.zeta/fail)))
                                         (meander.runtime.zeta/fail)))
                                       (meander.runtime.zeta/fail)))]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      result__12734__auto__)
                                     (state__61739)
                                     (recur
                                      (clojure.core/inc
                                       i__12733__auto__)
                                      (clojure.core/subvec
                                       coll__61755
                                       1)
                                      result__12734__auto__)))
                                   (if
                                    (clojure.core/or
                                     (clojure.core/seq coll__61755)
                                     (clojure.core/<
                                      i__12733__auto__
                                      0))
                                    (state__61739)
                                    (if
                                     (clojure.core/map?
                                      input__60417_nth_2__)
                                     (clojure.core/let
                                      [VAL__60840
                                       (.valAt
                                        input__60417_nth_2__
                                        :tag)]
                                      (clojure.core/case
                                       VAL__60840
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
                                         e__14839__auto__
                                         (if
                                          (meander.runtime.zeta/fail?
                                           e__14839__auto__)
                                          (meander.runtime.zeta/fail)
                                          (throw e__14839__auto__))))
                                       (state__61739)))
                                     (state__61739)))))))))
                             (state__61739)))
                           (state__61739)))
                         (state__61739))))
                      (state__61739)))
                    (state__61739))
                   (state__61739)))
                 (state__61739
                  []
                  (clojure.core/let
                   [input__60417_nth_3__
                    (clojure.core/nth input__60417 3)]
                   (clojure.core/case
                    input__60417_nth_0__
                    (meander.dev.parse.zeta/make-cat)
                    (clojure.core/letfn
                     [(state__61756
                       []
                       (if
                        (clojure.core/vector? input__60417_nth_1__)
                        (clojure.core/let
                         [input__60417_nth_1___l__
                          (clojure.core/subvec
                           input__60417_nth_1__
                           0
                           (clojure.core/min
                            (clojure.core/count input__60417_nth_1__)
                            1))]
                         (if
                          (clojure.core/=
                           (clojure.core/count
                            input__60417_nth_1___l__)
                           1)
                          (clojure.core/let
                           [input__60417_nth_1___r__
                            (clojure.core/subvec
                             input__60417_nth_1__
                             1)]
                           (clojure.core/let
                            [input__60417_nth_1___l___nth_0__
                             (clojure.core/nth
                              input__60417_nth_1___l__
                              0)]
                            (if
                             (clojure.core/map?
                              input__60417_nth_1___l___nth_0__)
                             (clojure.core/let
                              [VAL__61638
                               (.valAt
                                input__60417_nth_1___l___nth_0__
                                :tag)]
                              (clojure.core/case
                               VAL__61638
                               (:literal)
                               (clojure.core/letfn
                                [(state__61758
                                  []
                                  (clojure.core/let
                                   [VAL__60858
                                    (.valAt
                                     input__60417_nth_1___l___nth_0__
                                     :type)]
                                   (clojure.core/case
                                    VAL__60858
                                    (:string)
                                    (clojure.core/let
                                     [?ast
                                      input__60417_nth_1___l___nth_0__]
                                     (clojure.core/let
                                      [?rest input__60417_nth_1___r__]
                                      (clojure.core/let
                                       [?next input__60417_nth_2__]
                                       (clojure.core/let
                                        [?env input__60417_nth_3__]
                                        (try
                                         [(clojure.core/let
                                           [CATA_RESULT__13899__auto__
                                            (CATA__FN__60479
                                             ['meander.dev.parse.zeta/make-join
                                              ?ast
                                              (clojure.core/let
                                               [CATA_RESULT__13899__auto__
                                                (CATA__FN__60479
                                                 ['meander.dev.parse.zeta/make-cat
                                                  ?rest
                                                  ?next
                                                  ?env])]
                                               (if
                                                (meander.runtime.zeta/fail?
                                                 CATA_RESULT__13899__auto__)
                                                (throw
                                                 (meander.runtime.zeta/fail))
                                                (clojure.core/nth
                                                 CATA_RESULT__13899__auto__
                                                 0)))
                                              ?env])]
                                           (if
                                            (meander.runtime.zeta/fail?
                                             CATA_RESULT__13899__auto__)
                                            (throw
                                             (meander.runtime.zeta/fail))
                                            (clojure.core/nth
                                             CATA_RESULT__13899__auto__
                                             0)))]
                                         (catch
                                          java.lang.Exception
                                          e__14839__auto__
                                          (if
                                           (meander.runtime.zeta/fail?
                                            e__14839__auto__)
                                           (meander.runtime.zeta/fail)
                                           (throw
                                            e__14839__auto__))))))))
                                    (state__61759))))
                                 (state__61759
                                  []
                                  (clojure.core/let
                                   [VAL__60868
                                    (.valAt
                                     input__60417_nth_1___l___nth_0__
                                     :form)]
                                   (clojure.core/let
                                    [!forms []]
                                    (clojure.core/let
                                     [!forms
                                      (clojure.core/conj
                                       !forms
                                       VAL__60868)]
                                     (clojure.core/loop
                                      [i__12733__auto__
                                       0
                                       coll__61760
                                       input__60417_nth_1___r__
                                       [!forms]
                                       [!forms]]
                                      (clojure.core/let
                                       [input__60869
                                        (clojure.core/subvec
                                         coll__61760
                                         0
                                         (clojure.core/min
                                          (clojure.core/count
                                           coll__61760)
                                          1))]
                                       (if
                                        (clojure.core/=
                                         (clojure.core/count
                                          input__60869)
                                         1)
                                        (clojure.core/let
                                         [result__12734__auto__
                                          (clojure.core/let
                                           [input__60869_nth_0__
                                            (clojure.core/nth
                                             input__60869
                                             0)]
                                           (if
                                            (clojure.core/map?
                                             input__60869_nth_0__)
                                            (clojure.core/let
                                             [VAL__60870
                                              (.valAt
                                               input__60869_nth_0__
                                               :tag)]
                                             (clojure.core/case
                                              VAL__60870
                                              (:literal)
                                              (clojure.core/let
                                               [VAL__60871
                                                (.valAt
                                                 input__60869_nth_0__
                                                 :form)]
                                               (clojure.core/let
                                                [!forms
                                                 (clojure.core/conj
                                                  !forms
                                                  VAL__60871)]
                                                [!forms]))
                                              (meander.runtime.zeta/fail)))
                                            (meander.runtime.zeta/fail)))]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           result__12734__auto__)
                                          (state__61757)
                                          (recur
                                           (clojure.core/inc
                                            i__12733__auto__)
                                           (clojure.core/subvec
                                            coll__61760
                                            1)
                                           result__12734__auto__)))
                                        (if
                                         (clojure.core/or
                                          (clojure.core/seq
                                           coll__61760)
                                          (clojure.core/<
                                           i__12733__auto__
                                           0))
                                         (state__61757)
                                         (if
                                          (clojure.core/map?
                                           input__60417_nth_2__)
                                          (clojure.core/let
                                           [VAL__60861
                                            (.valAt
                                             input__60417_nth_2__
                                             :tag)]
                                           (clojure.core/case
                                            VAL__60861
                                            (:empty)
                                            (if
                                             (clojure.core/map?
                                              input__60417_nth_3__)
                                             (clojure.core/let
                                              [VAL__60862
                                               (.valAt
                                                input__60417_nth_3__
                                                :context)]
                                              (clojure.core/let
                                               [?context VAL__60862]
                                               (clojure.core/let
                                                [?env
                                                 input__60417_nth_3__]
                                                (try
                                                 [{:tag :literal,
                                                   :type ?context,
                                                   :form
                                                   (clojure.core/into
                                                    []
                                                    !forms)}]
                                                 (catch
                                                  java.lang.Exception
                                                  e__14839__auto__
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    e__14839__auto__)
                                                   (meander.runtime.zeta/fail)
                                                   (throw
                                                    e__14839__auto__)))))))
                                             (state__61757))
                                            (state__61757)))
                                          (state__61757))))))))))]
                                (state__61758))
                               (state__61757)))
                             (state__61757))))
                          (state__61757)))
                        (state__61757)))
                      (state__61757
                       []
                       (clojure.core/let
                        [?sequence input__60417_nth_1__]
                        (clojure.core/let
                         [?next input__60417_nth_2__]
                         (if
                          (clojure.core/map? input__60417_nth_3__)
                          (clojure.core/let
                           [VAL__60875
                            (.valAt input__60417_nth_3__ :context)]
                           (clojure.core/case
                            VAL__60875
                            (:string)
                            (try
                             [{:tag :string-cat,
                               :sequence ?sequence,
                               :next ?next}]
                             (catch
                              java.lang.Exception
                              e__14839__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__14839__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__14839__auto__))))
                            (state__61740)))
                          (state__61740)))))]
                     (state__61756))
                    (state__61740))))
                 (state__61740
                  []
                  (clojure.core/case
                   input__60417_nth_0__
                   (meander.dev.parse.zeta/make-cat)
                   (clojure.core/let
                    [?sequence input__60417_nth_1__]
                    (clojure.core/let
                     [?next input__60417_nth_2__]
                     (try
                      [{:tag :cat, :sequence ?sequence, :next ?next}]
                      (catch
                       java.lang.Exception
                       e__14839__auto__
                       (if
                        (meander.runtime.zeta/fail? e__14839__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__14839__auto__))))))
                   (state__61741)))
                 (state__61741
                  []
                  (clojure.core/let
                   [input__60417_nth_3__
                    (clojure.core/nth input__60417 3)]
                   (clojure.core/case
                    input__60417_nth_0__
                    (meander.dev.parse.zeta/make-join)
                    (if
                     (clojure.core/map? input__60417_nth_1__)
                     (clojure.core/let
                      [VAL__61636 (.valAt input__60417_nth_1__ :tag)]
                      (clojure.core/case
                       VAL__61636
                       (:cat)
                       (clojure.core/let
                        [VAL__60881
                         (.valAt input__60417_nth_1__ :sequence)]
                        (clojure.core/let
                         [?sequence VAL__60881]
                         (clojure.core/let
                          [VAL__60882
                           (.valAt input__60417_nth_1__ :next)]
                          (if
                           (clojure.core/map? VAL__60882)
                           (clojure.core/let
                            [VAL__60883 (.valAt VAL__60882 :tag)]
                            (clojure.core/case
                             VAL__60883
                             (:empty)
                             (clojure.core/let
                              [?right input__60417_nth_2__]
                              (clojure.core/let
                               [?env input__60417_nth_3__]
                               (try
                                [(clojure.core/let
                                  [CATA_RESULT__13899__auto__
                                   (CATA__FN__60479
                                    ['meander.dev.parse.zeta/make-cat
                                     ?sequence
                                     ?right
                                     ?env])]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    CATA_RESULT__13899__auto__)
                                   (throw (meander.runtime.zeta/fail))
                                   (clojure.core/nth
                                    CATA_RESULT__13899__auto__
                                    0)))]
                                (catch
                                 java.lang.Exception
                                 e__14839__auto__
                                 (if
                                  (meander.runtime.zeta/fail?
                                   e__14839__auto__)
                                  (meander.runtime.zeta/fail)
                                  (throw e__14839__auto__))))))
                             (state__61742)))
                           (state__61742)))))
                       (:literal)
                       (clojure.core/let
                        [VAL__60887
                         (.valAt input__60417_nth_1__ :type)]
                        (clojure.core/case
                         VAL__60887
                         (:string)
                         (clojure.core/let
                          [VAL__60888
                           (.valAt input__60417_nth_1__ :form)]
                          (clojure.core/let
                           [?form-1 VAL__60888]
                           (if
                            (clojure.core/map? input__60417_nth_2__)
                            (clojure.core/let
                             [VAL__60889
                              (.valAt input__60417_nth_2__ :tag)]
                             (clojure.core/case
                              VAL__60889
                              (:string-join)
                              (clojure.core/let
                               [VAL__60890
                                (.valAt input__60417_nth_2__ :left)]
                               (if
                                (clojure.core/map? VAL__60890)
                                (clojure.core/let
                                 [VAL__60891 (.valAt VAL__60890 :tag)]
                                 (clojure.core/case
                                  VAL__60891
                                  (:literal)
                                  (clojure.core/let
                                   [VAL__60892
                                    (.valAt VAL__60890 :type)]
                                   (clojure.core/case
                                    VAL__60892
                                    (:string)
                                    (clojure.core/let
                                     [VAL__60893
                                      (.valAt VAL__60890 :form)]
                                     (clojure.core/let
                                      [?form-2 VAL__60893]
                                      (clojure.core/let
                                       [VAL__60894
                                        (.valAt
                                         input__60417_nth_2__
                                         :right)]
                                       (clojure.core/let
                                        [?right VAL__60894]
                                        (if
                                         (clojure.core/map?
                                          input__60417_nth_3__)
                                         (clojure.core/let
                                          [VAL__60895
                                           (.valAt
                                            input__60417_nth_3__
                                            :context)]
                                          (clojure.core/case
                                           VAL__60895
                                           (:string)
                                           (clojure.core/let
                                            [?env input__60417_nth_3__]
                                            (try
                                             [(clojure.core/let
                                               [CATA_RESULT__13899__auto__
                                                (CATA__FN__60479
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
                                                 CATA_RESULT__13899__auto__)
                                                (throw
                                                 (meander.runtime.zeta/fail))
                                                (clojure.core/nth
                                                 CATA_RESULT__13899__auto__
                                                 0)))]
                                             (catch
                                              java.lang.Exception
                                              e__14839__auto__
                                              (if
                                               (meander.runtime.zeta/fail?
                                                e__14839__auto__)
                                               (meander.runtime.zeta/fail)
                                               (throw
                                                e__14839__auto__)))))
                                           (state__61742)))
                                         (state__61742))))))
                                    (state__61742)))
                                  (state__61742)))
                                (state__61742)))
                              (state__61742)))
                            (state__61742))))
                         (state__61742)))
                       (state__61742)))
                     (state__61742))
                    (state__61742))))
                 (state__61742
                  []
                  (clojure.core/case
                   input__60417_nth_0__
                   (meander.dev.parse.zeta/make-join)
                   (if
                    (clojure.core/map? input__60417_nth_1__)
                    (clojure.core/let
                     [VAL__61637 (.valAt input__60417_nth_1__ :tag)]
                     (clojure.core/case
                      VAL__61637
                      (:cat)
                      (clojure.core/let
                       [?ast input__60417_nth_1__]
                       (if
                        (clojure.core/map? input__60417_nth_2__)
                        (clojure.core/let
                         [VAL__60899
                          (.valAt input__60417_nth_2__ :tag)]
                         (clojure.core/case
                          VAL__60899
                          (:cat)
                          (clojure.core/let
                           [VAL__60900
                            (.valAt input__60417_nth_2__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__60900]
                            (clojure.core/let
                             [VAL__60901
                              (.valAt input__60417_nth_2__ :next)]
                             (clojure.core/let
                              [?next VAL__60901]
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
                                e__14839__auto__
                                (if
                                 (meander.runtime.zeta/fail?
                                  e__14839__auto__)
                                 (meander.runtime.zeta/fail)
                                 (throw e__14839__auto__))))))))
                          (state__61743)))
                        (state__61743)))
                      (:string-cat)
                      (clojure.core/let
                       [?ast input__60417_nth_1__]
                       (if
                        (clojure.core/map? input__60417_nth_2__)
                        (clojure.core/let
                         [VAL__60905
                          (.valAt input__60417_nth_2__ :tag)]
                         (clojure.core/case
                          VAL__60905
                          (:string-cat)
                          (clojure.core/let
                           [VAL__60906
                            (.valAt input__60417_nth_2__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__60906]
                            (clojure.core/let
                             [VAL__60907
                              (.valAt input__60417_nth_2__ :next)]
                             (clojure.core/let
                              [?next VAL__60907]
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
                                e__14839__auto__
                                (if
                                 (meander.runtime.zeta/fail?
                                  e__14839__auto__)
                                 (meander.runtime.zeta/fail)
                                 (throw e__14839__auto__))))))))
                          (state__61743)))
                        (state__61743)))
                      (state__61743)))
                    (state__61743))
                   (state__61743)))
                 (state__61743
                  []
                  (clojure.core/let
                   [input__60417_nth_3__
                    (clojure.core/nth input__60417 3)]
                   (clojure.core/case
                    input__60417_nth_0__
                    (meander.dev.parse.zeta/make-join)
                    (clojure.core/letfn
                     [(state__61761
                       []
                       (if
                        (clojure.core/map? input__60417_nth_1__)
                        (clojure.core/let
                         [VAL__61641
                          (.valAt input__60417_nth_1__ :next)
                          VAL__61640
                          (.valAt input__60417_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__61640
                          (:string-cat)
                          (clojure.core/let
                           [VAL__60911
                            (.valAt input__60417_nth_1__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__60911]
                            (if
                             (clojure.core/map? VAL__61641)
                             (clojure.core/let
                              [VAL__60913 (.valAt VAL__61641 :tag)]
                              (clojure.core/case
                               VAL__60913
                               (:empty)
                               (clojure.core/let
                                [?right input__60417_nth_2__]
                                (clojure.core/let
                                 [?env input__60417_nth_3__]
                                 (try
                                  [(clojure.core/let
                                    [CATA_RESULT__13899__auto__
                                     (CATA__FN__60479
                                      ['meander.dev.parse.zeta/make-join
                                       ?sequence
                                       ?right
                                       ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__13899__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__13899__auto__
                                      0)))]
                                  (catch
                                   java.lang.Exception
                                   e__14839__auto__
                                   (if
                                    (meander.runtime.zeta/fail?
                                     e__14839__auto__)
                                    (meander.runtime.zeta/fail)
                                    (throw e__14839__auto__))))))
                               (state__61762)))
                             (state__61762))))
                          (:string-star)
                          (clojure.core/let
                           [VAL__60917
                            (.valAt input__60417_nth_1__ :pattern)]
                           (clojure.core/let
                            [?pattern VAL__60917]
                            (if
                             (clojure.core/map? VAL__61641)
                             (clojure.core/let
                              [VAL__60919 (.valAt VAL__61641 :tag)]
                              (clojure.core/case
                               VAL__60919
                               (:empty)
                               (clojure.core/let
                                [?right input__60417_nth_2__]
                                (if
                                 (clojure.core/map?
                                  input__60417_nth_3__)
                                 (clojure.core/let
                                  [VAL__60920
                                   (.valAt
                                    input__60417_nth_3__
                                    :context)]
                                  (clojure.core/case
                                   VAL__60920
                                   (:string)
                                   (try
                                    [{:tag :string-star,
                                      :pattern ?pattern,
                                      :next ?right}]
                                    (catch
                                     java.lang.Exception
                                     e__14839__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__14839__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__14839__auto__))))
                                   (state__61762)))
                                 (state__61762)))
                               (state__61762)))
                             (state__61762))))
                          (:string-join)
                          (clojure.core/let
                           [VAL__60924
                            (.valAt input__60417_nth_1__ :left)]
                           (clojure.core/let
                            [?left VAL__60924]
                            (clojure.core/let
                             [VAL__60925
                              (.valAt input__60417_nth_1__ :right)]
                             (clojure.core/let
                              [?right-1 VAL__60925]
                              (clojure.core/let
                               [?right-2 input__60417_nth_2__]
                               (if
                                (clojure.core/map?
                                 input__60417_nth_3__)
                                (clojure.core/let
                                 [VAL__60926
                                  (.valAt
                                   input__60417_nth_3__
                                   :context)]
                                 (clojure.core/case
                                  VAL__60926
                                  (:string)
                                  (clojure.core/let
                                   [?env input__60417_nth_3__]
                                   (try
                                    [{:tag :string-join,
                                      :left ?left,
                                      :right
                                      (clojure.core/let
                                       [CATA_RESULT__13899__auto__
                                        (CATA__FN__60479
                                         ['meander.dev.parse.zeta/make-join
                                          ?right-1
                                          ?right-2
                                          ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__13899__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__13899__auto__
                                         0)))}]
                                    (catch
                                     java.lang.Exception
                                     e__14839__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__14839__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__14839__auto__)))))
                                  (state__61762)))
                                (state__61762)))))))
                          (state__61762)))
                        (state__61762)))
                      (state__61762
                       []
                       (clojure.core/let
                        [?left input__60417_nth_1__]
                        (if
                         (clojure.core/map? input__60417_nth_2__)
                         (clojure.core/let
                          [VAL__60929
                           (.valAt input__60417_nth_2__ :tag)]
                          (clojure.core/case
                           VAL__60929
                           (:empty)
                           (clojure.core/let
                            [?env input__60417_nth_3__]
                            (try
                             [?left]
                             (catch
                              java.lang.Exception
                              e__14839__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__14839__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__14839__auto__)))))
                           (state__61763)))
                         (state__61763))))
                      (state__61763
                       []
                       (if
                        (clojure.core/map? input__60417_nth_1__)
                        (clojure.core/let
                         [VAL__61639
                          (.valAt input__60417_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__61639
                          (:empty)
                          (clojure.core/let
                           [?right input__60417_nth_2__]
                           (clojure.core/let
                            [?env input__60417_nth_3__]
                            (try
                             [?right]
                             (catch
                              java.lang.Exception
                              e__14839__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__14839__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__14839__auto__))))))
                          (:star)
                          (clojure.core/let
                           [VAL__60936
                            (.valAt input__60417_nth_1__ :next)]
                           (if
                            (clojure.core/map? VAL__60936)
                            (clojure.core/let
                             [VAL__60937 (.valAt VAL__60936 :tag)]
                             (clojure.core/case
                              VAL__60937
                              (:empty)
                              (clojure.core/let
                               [?left input__60417_nth_1__]
                               (clojure.core/let
                                [?right input__60417_nth_2__]
                                (clojure.core/let
                                 [?env input__60417_nth_3__]
                                 (try
                                  [(clojure.core/let
                                    [form__13998__auto__
                                     {:tag :star, :next ?right}]
                                    (clojure.core/merge
                                     (clojure.core/into {} ?left)
                                     form__13998__auto__))]
                                  (catch
                                   java.lang.Exception
                                   e__14839__auto__
                                   (if
                                    (meander.runtime.zeta/fail?
                                     e__14839__auto__)
                                    (meander.runtime.zeta/fail)
                                    (throw e__14839__auto__)))))))
                              (state__61764)))
                            (state__61764)))
                          (state__61764)))
                        (state__61764)))
                      (state__61764
                       []
                       (clojure.core/let
                        [?left input__60417_nth_1__]
                        (clojure.core/let
                         [?right input__60417_nth_2__]
                         (clojure.core/letfn
                          [(state__61765
                            []
                            (if
                             (clojure.core/map? input__60417_nth_3__)
                             (clojure.core/let
                              [VAL__60940
                               (.valAt input__60417_nth_3__ :context)]
                              (clojure.core/case
                               VAL__60940
                               (:string)
                               (try
                                [{:tag :string-join,
                                  :left ?left,
                                  :right ?right}]
                                (catch
                                 java.lang.Exception
                                 e__14839__auto__
                                 (if
                                  (meander.runtime.zeta/fail?
                                   e__14839__auto__)
                                  (meander.runtime.zeta/fail)
                                  (throw e__14839__auto__))))
                               (state__61766)))
                             (state__61766)))
                           (state__61766
                            []
                            (clojure.core/let
                             [?env input__60417_nth_3__]
                             (try
                              [{:tag :join,
                                :left ?left,
                                :right ?right}]
                              (catch
                               java.lang.Exception
                               e__14839__auto__
                               (if
                                (meander.runtime.zeta/fail?
                                 e__14839__auto__)
                                (meander.runtime.zeta/fail)
                                (throw e__14839__auto__))))))]
                          (state__61765)))))]
                     (state__61761))
                    (state__61736))))]
                (state__61737)))
              (state__61736)))
            (state__61736
             []
             (if
              (clojure.core/= (clojure.core/count input__60417) 3)
              (clojure.core/let
               [input__60417_nth_0__
                (clojure.core/nth input__60417 0)
                input__60417_nth_1__
                (clojure.core/nth input__60417 1)
                input__60417_nth_2__
                (clojure.core/nth input__60417 2)]
               (clojure.core/case
                input__60417_nth_0__
                (meander.dev.parse.zeta/parse-entries)
                (if
                 (clojure.core/map? input__60417_nth_1__)
                 (clojure.core/let
                  [VAL__60945
                   (.valAt input__60417_nth_1__ :meander.zeta/as)]
                  (clojure.core/let
                   [?pattern VAL__60945]
                   (clojure.core/let
                    [X__60947
                     ((clojure.core/fn
                       [m__11663__auto__]
                       (clojure.core/dissoc
                        m__11663__auto__
                        :meander.zeta/as))
                      input__60417_nth_1__)]
                    (clojure.core/let
                     [?rest X__60947]
                     (clojure.core/letfn
                      [(save__60948 [] (state__61652))
                       (f__61767
                        []
                        (clojure.core/let
                         [?env input__60417_nth_2__]
                         (try
                          [{:tag :as,
                            :pattern
                            (clojure.core/let
                             [CATA_RESULT__13899__auto__
                              (CATA__FN__60479 [?pattern ?env])]
                             (if
                              (meander.runtime.zeta/fail?
                               CATA_RESULT__13899__auto__)
                              (throw (meander.runtime.zeta/fail))
                              (clojure.core/nth
                               CATA_RESULT__13899__auto__
                               0))),
                            :next
                            (clojure.core/let
                             [CATA_RESULT__13899__auto__
                              (CATA__FN__60479 [?rest ?env])]
                             (if
                              (meander.runtime.zeta/fail?
                               CATA_RESULT__13899__auto__)
                              (throw (meander.runtime.zeta/fail))
                              (clojure.core/nth
                               CATA_RESULT__13899__auto__
                               0)))}]
                          (catch
                           java.lang.Exception
                           e__14839__auto__
                           (if
                            (meander.runtime.zeta/fail?
                             e__14839__auto__)
                            (meander.runtime.zeta/fail)
                            (throw e__14839__auto__))))))]
                      (if
                       (clojure.core/= ?rest input__60417_nth_1__)
                       (save__60948)
                       (f__61767)))))))
                 (state__61652))
                (state__61652)))
              (state__61652)))]
           (state__61734))
          (state__61652)))
        (state__61652
         []
         (clojure.core/letfn
          [(def__60951
            [arg__60984 ?ns]
            (clojure.core/letfn
             [(state__61768
               []
               (clojure.core/let
                [x__60985 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__60985)
                 (clojure.core/let [?env arg__60984] [?env ?ns])
                 (state__61769))))
              (state__61769
               []
               (if
                (clojure.core/map? arg__60984)
                (clojure.core/let
                 [VAL__60986 (.valAt arg__60984 :aliases)]
                 (if
                  (clojure.core/map? VAL__60986)
                  (clojure.core/let
                   [X__60988 (clojure.core/set VAL__60986)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__60988))
                    (clojure.core/loop
                     [search_space__61770 (clojure.core/seq X__60988)]
                     (if
                      (clojure.core/seq search_space__61770)
                      (clojure.core/let
                       [elem__60989
                        (clojure.core/first search_space__61770)
                        result__61771
                        (clojure.core/let
                         [elem__60989_nth_0__
                          (clojure.core/nth elem__60989 0)
                          elem__60989_nth_1__
                          (clojure.core/nth elem__60989 1)]
                         (if
                          (clojure.core/symbol? elem__60989_nth_0__)
                          (clojure.core/let
                           [X__60991
                            (clojure.core/name elem__60989_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__60991)
                            (if
                             (clojure.core/symbol? elem__60989_nth_1__)
                             (clojure.core/let
                              [X__60993
                               (clojure.core/name elem__60989_nth_1__)]
                              (clojure.core/case
                               X__60993
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__60984]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__61771)
                        (recur (clojure.core/next search_space__61770))
                        result__61771))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__61768)))]
          (if
           (clojure.core/vector? input__60417)
           (if
            (clojure.core/= (clojure.core/count input__60417) 3)
            (clojure.core/let
             [input__60417_nth_0__
              (clojure.core/nth input__60417 0)
              input__60417_nth_1__
              (clojure.core/nth input__60417 1)
              input__60417_nth_2__
              (clojure.core/nth input__60417 2)]
             (clojure.core/case
              input__60417_nth_0__
              (meander.dev.parse.zeta/parse-entries)
              (if
               (clojure.core/map? input__60417_nth_1__)
               (clojure.core/let
                [X__60956 (clojure.core/set input__60417_nth_1__)]
                (if
                 (clojure.core/<= 1 (clojure.core/count X__60956))
                 (clojure.core/loop
                  [search_space__61773 (clojure.core/seq X__60956)]
                  (if
                   (clojure.core/seq search_space__61773)
                   (clojure.core/let
                    [elem__60957
                     (clojure.core/first search_space__61773)
                     result__61774
                     (clojure.core/let
                      [elem__60957_nth_0__
                       (clojure.core/nth elem__60957 0)
                       elem__60957_nth_1__
                       (clojure.core/nth elem__60957 1)]
                      (clojure.core/let
                       [*m__60444 elem__60957_nth_0__]
                       (if
                        (clojure.core/symbol? elem__60957_nth_0__)
                        (clojure.core/let
                         [X__60959
                          (clojure.core/namespace elem__60957_nth_0__)]
                         (clojure.core/let
                          [?ns X__60959]
                          (clojure.core/let
                           [X__60961
                            (clojure.core/name elem__60957_nth_0__)]
                           (if
                            (clojure.core/string? X__60961)
                            (if
                             (clojure.core/re-matches #"&.*" X__60961)
                             (clojure.core/let
                              [?pattern elem__60957_nth_1__]
                              (clojure.core/let
                               [X__60963
                                ((clojure.core/fn
                                  [m__11663__auto__]
                                  (clojure.core/dissoc
                                   m__11663__auto__
                                   *m__60444))
                                 input__60417_nth_1__)]
                               (clojure.core/let
                                [?rest X__60963]
                                (clojure.core/let
                                 [x__12596__auto__
                                  (def__60951
                                   input__60417_nth_2__
                                   ?ns)]
                                 (if
                                  (meander.runtime.zeta/fail?
                                   x__12596__auto__)
                                  (meander.runtime.zeta/fail)
                                  (clojure.core/let
                                   [[?env ?ns] x__12596__auto__]
                                   (try
                                    [{:tag :rest-map,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__13899__auto__
                                        (CATA__FN__60479
                                         [?pattern ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__13899__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__13899__auto__
                                         0))),
                                      :next
                                      (clojure.core/let
                                       [CATA_RESULT__13899__auto__
                                        (CATA__FN__60479
                                         ['meander.dev.parse.zeta/parse-entries
                                          ?rest
                                          ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__13899__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__13899__auto__
                                         0)))}]
                                    (catch
                                     java.lang.Exception
                                     e__14839__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__14839__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__14839__auto__))))))))))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))))
                        (meander.runtime.zeta/fail))))]
                    (if
                     (meander.runtime.zeta/fail? result__61774)
                     (recur (clojure.core/next search_space__61773))
                     result__61774))
                   (state__61653)))
                 (state__61653)))
               (state__61653))
              (state__61653)))
            (state__61653))
           (state__61653))))
        (state__61653
         []
         (if
          (clojure.core/vector? input__60417)
          (clojure.core/letfn
           [(state__61776
             []
             (if
              (clojure.core/= (clojure.core/count input__60417) 3)
              (clojure.core/let
               [input__60417_nth_0__
                (clojure.core/nth input__60417 0)
                input__60417_nth_1__
                (clojure.core/nth input__60417 1)
                input__60417_nth_2__
                (clojure.core/nth input__60417 2)]
               (clojure.core/case
                input__60417_nth_0__
                (meander.dev.parse.zeta/parse-entries)
                (clojure.core/letfn
                 [(state__61778
                   []
                   (if
                    (clojure.core/map? input__60417_nth_1__)
                    (clojure.core/let
                     [X__61007 (clojure.core/set input__60417_nth_1__)]
                     (if
                      (clojure.core/<= 1 (clojure.core/count X__61007))
                      (clojure.core/loop
                       [search_space__61780
                        (clojure.core/seq X__61007)]
                       (if
                        (clojure.core/seq search_space__61780)
                        (clojure.core/let
                         [elem__61008
                          (clojure.core/first search_space__61780)
                          result__61781
                          (clojure.core/let
                           [elem__61008_nth_0__
                            (clojure.core/nth elem__61008 0)
                            elem__61008_nth_1__
                            (clojure.core/nth elem__61008 1)]
                           (clojure.core/let
                            [?key-pattern elem__61008_nth_0__]
                            (clojure.core/let
                             [?val-pattern elem__61008_nth_1__]
                             (clojure.core/let
                              [X__61010
                               ((clojure.core/fn
                                 [m__11663__auto__]
                                 (clojure.core/dissoc
                                  m__11663__auto__
                                  ?key-pattern))
                                input__60417_nth_1__)]
                              (clojure.core/let
                               [?rest X__61010]
                               (clojure.core/let
                                [?env input__60417_nth_2__]
                                (try
                                 [{:tag :entry,
                                   :key-pattern
                                   (clojure.core/let
                                    [CATA_RESULT__13899__auto__
                                     (CATA__FN__60479
                                      [?key-pattern ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__13899__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__13899__auto__
                                      0))),
                                   :val-pattern
                                   (clojure.core/let
                                    [CATA_RESULT__13899__auto__
                                     (CATA__FN__60479
                                      [?val-pattern ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__13899__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__13899__auto__
                                      0))),
                                   :next
                                   (clojure.core/let
                                    [CATA_RESULT__13899__auto__
                                     (CATA__FN__60479
                                      ['meander.dev.parse.zeta/parse-entries
                                       ?rest
                                       ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__13899__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__13899__auto__
                                      0)))}]
                                 (catch
                                  java.lang.Exception
                                  e__14839__auto__
                                  (if
                                   (meander.runtime.zeta/fail?
                                    e__14839__auto__)
                                   (meander.runtime.zeta/fail)
                                   (throw e__14839__auto__))))))))))]
                         (if
                          (meander.runtime.zeta/fail? result__61781)
                          (recur
                           (clojure.core/next search_space__61780))
                          result__61781))
                        (state__61779)))
                      (state__61779)))
                    (state__61779)))
                  (state__61779
                   []
                   (if
                    (clojure.core/map? input__60417_nth_1__)
                    (clojure.core/let
                     [?env input__60417_nth_2__]
                     (try
                      [{:tag :some-map}]
                      (catch
                       java.lang.Exception
                       e__14839__auto__
                       (if
                        (meander.runtime.zeta/fail? e__14839__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__14839__auto__)))))
                    (state__61777)))]
                 (state__61778))
                (meander.dev.parse.zeta/parse-with-bindings)
                (clojure.core/letfn
                 [(state__61783
                   []
                   (if
                    (clojure.core/vector? input__60417_nth_1__)
                    (clojure.core/case
                     input__60417_nth_1__
                     ([])
                     (clojure.core/let
                      [?env input__60417_nth_2__]
                      (try
                       [[]]
                       (catch
                        java.lang.Exception
                        e__14839__auto__
                        (if
                         (meander.runtime.zeta/fail? e__14839__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__14839__auto__)))))
                     (state__61784))
                    (state__61784)))
                  (state__61784
                   []
                   (if
                    (clojure.core/vector? input__60417_nth_1__)
                    (if
                     (clojure.core/=
                      (clojure.core/count input__60417_nth_1__)
                      1)
                     (clojure.core/let
                      [?env input__60417_nth_2__]
                      (try
                       [[{:tag :error,
                          :message
                          "meander.zeta/with expects an even number of bindings"}]]
                       (catch
                        java.lang.Exception
                        e__14839__auto__
                        (if
                         (meander.runtime.zeta/fail? e__14839__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__14839__auto__)))))
                     (state__61785))
                    (state__61785)))
                  (state__61785
                   []
                   (if
                    (clojure.core/vector? input__60417_nth_1__)
                    (clojure.core/let
                     [input__60417_nth_1___l__
                      (clojure.core/subvec
                       input__60417_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__60417_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__60417_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__60417_nth_1___r__
                        (clojure.core/subvec input__60417_nth_1__ 2)]
                       (clojure.core/let
                        [input__60417_nth_1___l___nth_0__
                         (clojure.core/nth input__60417_nth_1___l__ 0)
                         input__60417_nth_1___l___nth_1__
                         (clojure.core/nth input__60417_nth_1___l__ 1)]
                        (if
                         (clojure.core/symbol?
                          input__60417_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__61024
                           (clojure.core/namespace
                            input__60417_nth_1___l___nth_0__)]
                          (clojure.core/let
                           [X__61026
                            (clojure.core/name
                             input__60417_nth_1___l___nth_0__)]
                           (if
                            (clojure.core/string? X__61026)
                            (if
                             (clojure.core/re-matches #"%.+" X__61026)
                             (clojure.core/let
                              [?symbol
                               input__60417_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?pattern
                                input__60417_nth_1___l___nth_1__]
                               (clojure.core/let
                                [?rest input__60417_nth_1___r__]
                                (clojure.core/let
                                 [?env input__60417_nth_2__]
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
                                        [CATA_RESULT__13899__auto__
                                         (CATA__FN__60479
                                          [?pattern ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__13899__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__13899__auto__
                                          0)))})
                                     (clojure.core/let
                                      [CATA_RESULT__13899__auto__
                                       (CATA__FN__60479
                                        ['meander.dev.parse.zeta/parse-with-bindings
                                         ?rest
                                         ?env])]
                                      (if
                                       (meander.runtime.zeta/fail?
                                        CATA_RESULT__13899__auto__)
                                       (throw
                                        (meander.runtime.zeta/fail))
                                       (clojure.core/nth
                                        CATA_RESULT__13899__auto__
                                        0)))))]
                                  (catch
                                   java.lang.Exception
                                   e__14839__auto__
                                   (if
                                    (meander.runtime.zeta/fail?
                                     e__14839__auto__)
                                    (meander.runtime.zeta/fail)
                                    (throw e__14839__auto__))))))))
                             (state__61786))
                            (state__61786))))
                         (state__61786))))
                      (state__61786)))
                    (state__61786)))
                  (state__61786
                   []
                   (if
                    (clojure.core/vector? input__60417_nth_1__)
                    (clojure.core/let
                     [input__60417_nth_1___l__
                      (clojure.core/subvec
                       input__60417_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__60417_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__60417_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__60417_nth_1___r__
                        (clojure.core/subvec input__60417_nth_1__ 2)]
                       (clojure.core/let
                        [input__60417_nth_1___l___nth_0__
                         (clojure.core/nth input__60417_nth_1___l__ 0)
                         input__60417_nth_1___l___nth_1__
                         (clojure.core/nth input__60417_nth_1___l__ 1)]
                        (clojure.core/let
                         [?x input__60417_nth_1___l___nth_0__]
                         (clojure.core/let
                          [?pattern input__60417_nth_1___l___nth_1__]
                          (clojure.core/let
                           [?rest input__60417_nth_1___r__]
                           (clojure.core/let
                            [?env input__60417_nth_2__]
                            (try
                             [[{:tag :error,
                                :message
                                "meander.zeta/with bindings must be an repeating sequence of %name pattern"}]]
                             (catch
                              java.lang.Exception
                              e__14839__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__14839__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__14839__auto__))))))))))
                      (state__61777)))
                    (state__61777)))]
                 (state__61783))
                (state__61777)))
              (state__61777)))
            (state__61777
             []
             (if
              (clojure.core/= (clojure.core/count input__60417) 2)
              (clojure.core/let
               [input__60417_nth_0__
                (clojure.core/nth input__60417 0)
                input__60417_nth_1__
                (clojure.core/nth input__60417 1)]
               (if
                (clojure.core/vector? input__60417_nth_0__)
                (clojure.core/let
                 [?sequence input__60417_nth_0__]
                 (clojure.core/let
                  [?form input__60417_nth_0__]
                  (clojure.core/let
                   [?env input__60417_nth_1__]
                   (try
                    [{:tag :vector,
                      :next
                      (clojure.core/let
                       [CATA_RESULT__13899__auto__
                        (CATA__FN__60479
                         ['meander.dev.parse.zeta/parse-sequential
                          ?sequence
                          (clojure.core/let
                           [form__13998__auto__ {:context :vector}]
                           (clojure.core/merge
                            (clojure.core/into {} ?env)
                            form__13998__auto__))])]
                       (if
                        (meander.runtime.zeta/fail?
                         CATA_RESULT__13899__auto__)
                        (throw (meander.runtime.zeta/fail))
                        (clojure.core/nth
                         CATA_RESULT__13899__auto__
                         0))),
                      :form ?form}]
                    (catch
                     java.lang.Exception
                     e__14839__auto__
                     (if
                      (meander.runtime.zeta/fail? e__14839__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__14839__auto__)))))))
                (state__61654)))
              (state__61654)))]
           (state__61776))
          (state__61654)))
        (state__61654
         []
         (clojure.core/letfn
          [(def__61036
            [arg__61059 ?__60418]
            (clojure.core/letfn
             [(state__61787
               []
               (clojure.core/let
                [x__61060 "meander.zeta"]
                (if
                 (clojure.core/= ?__60418 x__61060)
                 [?__60418]
                 (state__61788))))
              (state__61788
               []
               (if
                (clojure.core/map? arg__61059)
                (clojure.core/let
                 [VAL__61061 (.valAt arg__61059 :aliases)]
                 (if
                  (clojure.core/map? VAL__61061)
                  (clojure.core/let
                   [X__61063 (clojure.core/set VAL__61061)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__61063))
                    (clojure.core/loop
                     [search_space__61789 (clojure.core/seq X__61063)]
                     (if
                      (clojure.core/seq search_space__61789)
                      (clojure.core/let
                       [elem__61064
                        (clojure.core/first search_space__61789)
                        result__61790
                        (clojure.core/let
                         [elem__61064_nth_0__
                          (clojure.core/nth elem__61064 0)
                          elem__61064_nth_1__
                          (clojure.core/nth elem__61064 1)]
                         (if
                          (clojure.core/symbol? elem__61064_nth_0__)
                          (clojure.core/let
                           [X__61066
                            (clojure.core/name elem__61064_nth_0__)]
                           (if
                            (clojure.core/= ?__60418 X__61066)
                            (if
                             (clojure.core/symbol? elem__61064_nth_1__)
                             (clojure.core/let
                              [X__61068
                               (clojure.core/name elem__61064_nth_1__)]
                              (clojure.core/case
                               X__61068
                               ("meander.zeta")
                               [?__60418]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__61790)
                        (recur (clojure.core/next search_space__61789))
                        result__61790))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__61787)))]
          (if
           (clojure.core/vector? input__60417)
           (if
            (clojure.core/= (clojure.core/count input__60417) 2)
            (clojure.core/let
             [input__60417_nth_0__
              (clojure.core/nth input__60417 0)
              input__60417_nth_1__
              (clojure.core/nth input__60417 1)]
             (if
              (clojure.core/seq? input__60417_nth_0__)
              (clojure.core/let
               [input__60417_nth_0___l__
                (clojure.core/take 1 input__60417_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__60417_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__60417_nth_0___r__
                  (clojure.core/drop 1 input__60417_nth_0__)]
                 (clojure.core/let
                  [input__60417_nth_0___l___nth_0__
                   (clojure.core/nth input__60417_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__60417_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__61046
                     (clojure.core/namespace
                      input__60417_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__60418 X__61046]
                     (clojure.core/let
                      [X__61048
                       (clojure.core/name
                        input__60417_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__61048
                       ("*")
                       (clojure.core/let
                        [x__12596__auto__
                         (def__61036 input__60417_nth_1__ ?__60418)]
                        (if
                         (meander.runtime.zeta/fail? x__12596__auto__)
                         (state__61655)
                         (clojure.core/let
                          [[?__60418] x__12596__auto__]
                          (if
                           (clojure.core/vector? input__60417)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__60417)
                             2)
                            (clojure.core/let
                             [input__60417_nth_0__
                              (clojure.core/nth input__60417 0)
                              input__60417_nth_1__
                              (clojure.core/nth input__60417 1)]
                             (if
                              (clojure.core/seq? input__60417_nth_0__)
                              (clojure.core/let
                               [input__60417_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__60417_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__60417_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__60417_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__60417_nth_0__)]
                                 (clojure.core/let
                                  [?patterns input__60417_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__60417_nth_0__]
                                   (clojure.core/let
                                    [?env input__60417_nth_1__]
                                    (try
                                     [{:tag :star,
                                       :greedy? true,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__13899__auto__
                                         (CATA__FN__60479
                                          ['meander.dev.parse.zeta/parse-sequential
                                           (clojure.core/into
                                            []
                                            ?patterns)
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__13899__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__13899__auto__
                                          0))),
                                       :next {:tag :empty}}]
                                     (catch
                                      java.lang.Exception
                                      e__14839__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__14839__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__14839__auto__))))))))
                                (state__61655)))
                              (state__61655)))
                            (state__61655))
                           (state__61655)))))
                       (state__61655)))))
                   (state__61655))))
                (state__61655)))
              (state__61655)))
            (state__61655))
           (state__61655))))
        (state__61655
         []
         (clojure.core/letfn
          [(def__61070
            [arg__61093 ?__60419]
            (clojure.core/letfn
             [(state__61792
               []
               (clojure.core/let
                [x__61094 "meander.zeta"]
                (if
                 (clojure.core/= ?__60419 x__61094)
                 [?__60419]
                 (state__61793))))
              (state__61793
               []
               (if
                (clojure.core/map? arg__61093)
                (clojure.core/let
                 [VAL__61095 (.valAt arg__61093 :aliases)]
                 (if
                  (clojure.core/map? VAL__61095)
                  (clojure.core/let
                   [X__61097 (clojure.core/set VAL__61095)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__61097))
                    (clojure.core/loop
                     [search_space__61794 (clojure.core/seq X__61097)]
                     (if
                      (clojure.core/seq search_space__61794)
                      (clojure.core/let
                       [elem__61098
                        (clojure.core/first search_space__61794)
                        result__61795
                        (clojure.core/let
                         [elem__61098_nth_0__
                          (clojure.core/nth elem__61098 0)
                          elem__61098_nth_1__
                          (clojure.core/nth elem__61098 1)]
                         (if
                          (clojure.core/symbol? elem__61098_nth_0__)
                          (clojure.core/let
                           [X__61100
                            (clojure.core/name elem__61098_nth_0__)]
                           (if
                            (clojure.core/= ?__60419 X__61100)
                            (if
                             (clojure.core/symbol? elem__61098_nth_1__)
                             (clojure.core/let
                              [X__61102
                               (clojure.core/name elem__61098_nth_1__)]
                              (clojure.core/case
                               X__61102
                               ("meander.zeta")
                               [?__60419]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__61795)
                        (recur (clojure.core/next search_space__61794))
                        result__61795))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__61792)))]
          (if
           (clojure.core/vector? input__60417)
           (if
            (clojure.core/= (clojure.core/count input__60417) 2)
            (clojure.core/let
             [input__60417_nth_0__
              (clojure.core/nth input__60417 0)
              input__60417_nth_1__
              (clojure.core/nth input__60417 1)]
             (if
              (clojure.core/seq? input__60417_nth_0__)
              (clojure.core/let
               [input__60417_nth_0___l__
                (clojure.core/take 1 input__60417_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__60417_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__60417_nth_0___r__
                  (clojure.core/drop 1 input__60417_nth_0__)]
                 (clojure.core/let
                  [input__60417_nth_0___l___nth_0__
                   (clojure.core/nth input__60417_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__60417_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__61080
                     (clojure.core/namespace
                      input__60417_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__60419 X__61080]
                     (clojure.core/let
                      [X__61082
                       (clojure.core/name
                        input__60417_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__61082
                       ("<>")
                       (clojure.core/let
                        [x__12596__auto__
                         (def__61070 input__60417_nth_1__ ?__60419)]
                        (if
                         (meander.runtime.zeta/fail? x__12596__auto__)
                         (state__61656)
                         (clojure.core/let
                          [[?__60419] x__12596__auto__]
                          (if
                           (clojure.core/vector? input__60417)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__60417)
                             2)
                            (clojure.core/let
                             [input__60417_nth_0__
                              (clojure.core/nth input__60417 0)
                              input__60417_nth_1__
                              (clojure.core/nth input__60417 1)]
                             (if
                              (clojure.core/seq? input__60417_nth_0__)
                              (clojure.core/let
                               [input__60417_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__60417_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__60417_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__60417_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__60417_nth_0__)]
                                 (clojure.core/let
                                  [?patterns input__60417_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__60417_nth_0__]
                                   (clojure.core/let
                                    [?env input__60417_nth_1__]
                                    (try
                                     [{:tag :group,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__13899__auto__
                                         (CATA__FN__60479
                                          ['meander.dev.parse.zeta/parse-sequential
                                           (clojure.core/into
                                            []
                                            ?patterns)
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__13899__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__13899__auto__
                                          0)))}]
                                     (catch
                                      java.lang.Exception
                                      e__14839__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__14839__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__14839__auto__))))))))
                                (state__61656)))
                              (state__61656)))
                            (state__61656))
                           (state__61656)))))
                       (state__61656)))))
                   (state__61656))))
                (state__61656)))
              (state__61656)))
            (state__61656))
           (state__61656))))
        (state__61656
         []
         (clojure.core/letfn
          [(def__61104
            [arg__61127 ?__60420]
            (clojure.core/letfn
             [(state__61797
               []
               (clojure.core/let
                [x__61128 "meander.zeta"]
                (if
                 (clojure.core/= ?__60420 x__61128)
                 [?__60420]
                 (state__61798))))
              (state__61798
               []
               (if
                (clojure.core/map? arg__61127)
                (clojure.core/let
                 [VAL__61129 (.valAt arg__61127 :aliases)]
                 (if
                  (clojure.core/map? VAL__61129)
                  (clojure.core/let
                   [X__61131 (clojure.core/set VAL__61129)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__61131))
                    (clojure.core/loop
                     [search_space__61799 (clojure.core/seq X__61131)]
                     (if
                      (clojure.core/seq search_space__61799)
                      (clojure.core/let
                       [elem__61132
                        (clojure.core/first search_space__61799)
                        result__61800
                        (clojure.core/let
                         [elem__61132_nth_0__
                          (clojure.core/nth elem__61132 0)
                          elem__61132_nth_1__
                          (clojure.core/nth elem__61132 1)]
                         (if
                          (clojure.core/symbol? elem__61132_nth_0__)
                          (clojure.core/let
                           [X__61134
                            (clojure.core/name elem__61132_nth_0__)]
                           (if
                            (clojure.core/= ?__60420 X__61134)
                            (if
                             (clojure.core/symbol? elem__61132_nth_1__)
                             (clojure.core/let
                              [X__61136
                               (clojure.core/name elem__61132_nth_1__)]
                              (clojure.core/case
                               X__61136
                               ("meander.zeta")
                               [?__60420]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__61800)
                        (recur (clojure.core/next search_space__61799))
                        result__61800))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__61797)))]
          (if
           (clojure.core/vector? input__60417)
           (if
            (clojure.core/= (clojure.core/count input__60417) 2)
            (clojure.core/let
             [input__60417_nth_0__
              (clojure.core/nth input__60417 0)
              input__60417_nth_1__
              (clojure.core/nth input__60417 1)]
             (if
              (clojure.core/seq? input__60417_nth_0__)
              (clojure.core/let
               [input__60417_nth_0___l__
                (clojure.core/take 1 input__60417_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__60417_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__60417_nth_0___r__
                  (clojure.core/drop 1 input__60417_nth_0__)]
                 (clojure.core/let
                  [input__60417_nth_0___l___nth_0__
                   (clojure.core/nth input__60417_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__60417_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__61114
                     (clojure.core/namespace
                      input__60417_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__60420 X__61114]
                     (clojure.core/let
                      [X__61116
                       (clojure.core/name
                        input__60417_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__61116
                       ("with")
                       (clojure.core/let
                        [x__12596__auto__
                         (def__61104 input__60417_nth_1__ ?__60420)]
                        (if
                         (meander.runtime.zeta/fail? x__12596__auto__)
                         (state__61657)
                         (clojure.core/let
                          [[?__60420] x__12596__auto__]
                          (if
                           (clojure.core/vector? input__60417)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__60417)
                             2)
                            (clojure.core/let
                             [input__60417_nth_0__
                              (clojure.core/nth input__60417 0)
                              input__60417_nth_1__
                              (clojure.core/nth input__60417 1)]
                             (if
                              (clojure.core/seq? input__60417_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__60417_nth_0__)
                                3)
                               (clojure.core/let
                                [input__60417_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__60417_nth_0__
                                  1)
                                 input__60417_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__60417_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?bindings
                                  input__60417_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?body input__60417_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__60417_nth_0__]
                                   (clojure.core/let
                                    [?env input__60417_nth_1__]
                                    (try
                                     [{:tag :with,
                                       :bindings
                                       {:tag :with-bindings,
                                        :bindings
                                        (clojure.core/let
                                         [CATA_RESULT__13899__auto__
                                          (CATA__FN__60479
                                           ['meander.dev.parse.zeta/parse-with-bindings
                                            ?bindings
                                            ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__13899__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__13899__auto__
                                           0)))},
                                       :body
                                       (clojure.core/let
                                        [CATA_RESULT__13899__auto__
                                         (CATA__FN__60479
                                          [?body ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__13899__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__13899__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__14839__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__14839__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__14839__auto__)))))))))
                               (state__61657))
                              (state__61657)))
                            (state__61657))
                           (state__61657)))))
                       (state__61657)))))
                   (state__61657))))
                (state__61657)))
              (state__61657)))
            (state__61657))
           (state__61657))))
        (state__61657
         []
         (clojure.core/letfn
          [(def__61138
            [arg__61161 ?__60421]
            (clojure.core/letfn
             [(state__61802
               []
               (clojure.core/let
                [x__61162 "meander.zeta"]
                (if
                 (clojure.core/= ?__60421 x__61162)
                 [?__60421]
                 (state__61803))))
              (state__61803
               []
               (if
                (clojure.core/map? arg__61161)
                (clojure.core/let
                 [VAL__61163 (.valAt arg__61161 :aliases)]
                 (if
                  (clojure.core/map? VAL__61163)
                  (clojure.core/let
                   [X__61165 (clojure.core/set VAL__61163)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__61165))
                    (clojure.core/loop
                     [search_space__61804 (clojure.core/seq X__61165)]
                     (if
                      (clojure.core/seq search_space__61804)
                      (clojure.core/let
                       [elem__61166
                        (clojure.core/first search_space__61804)
                        result__61805
                        (clojure.core/let
                         [elem__61166_nth_0__
                          (clojure.core/nth elem__61166 0)
                          elem__61166_nth_1__
                          (clojure.core/nth elem__61166 1)]
                         (if
                          (clojure.core/symbol? elem__61166_nth_0__)
                          (clojure.core/let
                           [X__61168
                            (clojure.core/name elem__61166_nth_0__)]
                           (if
                            (clojure.core/= ?__60421 X__61168)
                            (if
                             (clojure.core/symbol? elem__61166_nth_1__)
                             (clojure.core/let
                              [X__61170
                               (clojure.core/name elem__61166_nth_1__)]
                              (clojure.core/case
                               X__61170
                               ("meander.zeta")
                               [?__60421]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__61805)
                        (recur (clojure.core/next search_space__61804))
                        result__61805))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__61802)))]
          (if
           (clojure.core/vector? input__60417)
           (if
            (clojure.core/= (clojure.core/count input__60417) 2)
            (clojure.core/let
             [input__60417_nth_0__
              (clojure.core/nth input__60417 0)
              input__60417_nth_1__
              (clojure.core/nth input__60417 1)]
             (if
              (clojure.core/seq? input__60417_nth_0__)
              (clojure.core/let
               [input__60417_nth_0___l__
                (clojure.core/take 1 input__60417_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__60417_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__60417_nth_0___r__
                  (clojure.core/drop 1 input__60417_nth_0__)]
                 (clojure.core/let
                  [input__60417_nth_0___l___nth_0__
                   (clojure.core/nth input__60417_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__60417_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__61148
                     (clojure.core/namespace
                      input__60417_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__60421 X__61148]
                     (clojure.core/let
                      [X__61150
                       (clojure.core/name
                        input__60417_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__61150
                       ("apply")
                       (clojure.core/let
                        [x__12596__auto__
                         (def__61138 input__60417_nth_1__ ?__60421)]
                        (if
                         (meander.runtime.zeta/fail? x__12596__auto__)
                         (state__61658)
                         (clojure.core/let
                          [[?__60421] x__12596__auto__]
                          (if
                           (clojure.core/vector? input__60417)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__60417)
                             2)
                            (clojure.core/let
                             [input__60417_nth_0__
                              (clojure.core/nth input__60417 0)
                              input__60417_nth_1__
                              (clojure.core/nth input__60417 1)]
                             (if
                              (clojure.core/seq? input__60417_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__60417_nth_0__)
                                3)
                               (clojure.core/let
                                [input__60417_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__60417_nth_0__
                                  1)
                                 input__60417_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__60417_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?fn input__60417_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?pattern
                                   input__60417_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__60417_nth_0__]
                                   (clojure.core/let
                                    [?env input__60417_nth_1__]
                                    (try
                                     [{:tag :apply,
                                       :fn ?fn,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__13899__auto__
                                         (CATA__FN__60479
                                          [?pattern ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__13899__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__13899__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__14839__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__14839__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__14839__auto__)))))))))
                               (state__61658))
                              (state__61658)))
                            (state__61658))
                           (state__61658)))))
                       (state__61658)))))
                   (state__61658))))
                (state__61658)))
              (state__61658)))
            (state__61658))
           (state__61658))))
        (state__61658
         []
         (clojure.core/letfn
          [(def__61172
            [arg__61195 ?__60422]
            (clojure.core/letfn
             [(state__61807
               []
               (clojure.core/let
                [x__61196 "meander.zeta"]
                (if
                 (clojure.core/= ?__60422 x__61196)
                 [?__60422]
                 (state__61808))))
              (state__61808
               []
               (if
                (clojure.core/map? arg__61195)
                (clojure.core/let
                 [VAL__61197 (.valAt arg__61195 :aliases)]
                 (if
                  (clojure.core/map? VAL__61197)
                  (clojure.core/let
                   [X__61199 (clojure.core/set VAL__61197)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__61199))
                    (clojure.core/loop
                     [search_space__61809 (clojure.core/seq X__61199)]
                     (if
                      (clojure.core/seq search_space__61809)
                      (clojure.core/let
                       [elem__61200
                        (clojure.core/first search_space__61809)
                        result__61810
                        (clojure.core/let
                         [elem__61200_nth_0__
                          (clojure.core/nth elem__61200 0)
                          elem__61200_nth_1__
                          (clojure.core/nth elem__61200 1)]
                         (if
                          (clojure.core/symbol? elem__61200_nth_0__)
                          (clojure.core/let
                           [X__61202
                            (clojure.core/name elem__61200_nth_0__)]
                           (if
                            (clojure.core/= ?__60422 X__61202)
                            (if
                             (clojure.core/symbol? elem__61200_nth_1__)
                             (clojure.core/let
                              [X__61204
                               (clojure.core/name elem__61200_nth_1__)]
                              (clojure.core/case
                               X__61204
                               ("meander.zeta")
                               [?__60422]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__61810)
                        (recur (clojure.core/next search_space__61809))
                        result__61810))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__61807)))]
          (if
           (clojure.core/vector? input__60417)
           (if
            (clojure.core/= (clojure.core/count input__60417) 2)
            (clojure.core/let
             [input__60417_nth_0__
              (clojure.core/nth input__60417 0)
              input__60417_nth_1__
              (clojure.core/nth input__60417 1)]
             (if
              (clojure.core/seq? input__60417_nth_0__)
              (clojure.core/let
               [input__60417_nth_0___l__
                (clojure.core/take 1 input__60417_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__60417_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__60417_nth_0___r__
                  (clojure.core/drop 1 input__60417_nth_0__)]
                 (clojure.core/let
                  [input__60417_nth_0___l___nth_0__
                   (clojure.core/nth input__60417_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__60417_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__61182
                     (clojure.core/namespace
                      input__60417_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__60422 X__61182]
                     (clojure.core/let
                      [X__61184
                       (clojure.core/name
                        input__60417_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__61184
                       ("and")
                       (clojure.core/let
                        [x__12596__auto__
                         (def__61172 input__60417_nth_1__ ?__60422)]
                        (if
                         (meander.runtime.zeta/fail? x__12596__auto__)
                         (state__61659)
                         (clojure.core/let
                          [[?__60422] x__12596__auto__]
                          (if
                           (clojure.core/vector? input__60417)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__60417)
                             2)
                            (clojure.core/let
                             [input__60417_nth_0__
                              (clojure.core/nth input__60417 0)
                              input__60417_nth_1__
                              (clojure.core/nth input__60417 1)]
                             (if
                              (clojure.core/seq? input__60417_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__60417_nth_0__)
                                3)
                               (clojure.core/let
                                [input__60417_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__60417_nth_0__
                                  1)
                                 input__60417_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__60417_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?left input__60417_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?right input__60417_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__60417_nth_0__]
                                   (clojure.core/let
                                    [?env input__60417_nth_1__]
                                    (try
                                     [{:tag :and,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__13899__auto__
                                         (CATA__FN__60479
                                          [?left ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__13899__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__13899__auto__
                                          0))),
                                       :right
                                       (clojure.core/let
                                        [CATA_RESULT__13899__auto__
                                         (CATA__FN__60479
                                          [?right ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__13899__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__13899__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__14839__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__14839__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__14839__auto__)))))))))
                               (state__61659))
                              (state__61659)))
                            (state__61659))
                           (state__61659)))))
                       (state__61659)))))
                   (state__61659))))
                (state__61659)))
              (state__61659)))
            (state__61659))
           (state__61659))))
        (state__61659
         []
         (clojure.core/letfn
          [(def__61206
            [arg__61229 ?__60423]
            (clojure.core/letfn
             [(state__61812
               []
               (clojure.core/let
                [x__61230 "meander.zeta"]
                (if
                 (clojure.core/= ?__60423 x__61230)
                 [?__60423]
                 (state__61813))))
              (state__61813
               []
               (if
                (clojure.core/map? arg__61229)
                (clojure.core/let
                 [VAL__61231 (.valAt arg__61229 :aliases)]
                 (if
                  (clojure.core/map? VAL__61231)
                  (clojure.core/let
                   [X__61233 (clojure.core/set VAL__61231)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__61233))
                    (clojure.core/loop
                     [search_space__61814 (clojure.core/seq X__61233)]
                     (if
                      (clojure.core/seq search_space__61814)
                      (clojure.core/let
                       [elem__61234
                        (clojure.core/first search_space__61814)
                        result__61815
                        (clojure.core/let
                         [elem__61234_nth_0__
                          (clojure.core/nth elem__61234 0)
                          elem__61234_nth_1__
                          (clojure.core/nth elem__61234 1)]
                         (if
                          (clojure.core/symbol? elem__61234_nth_0__)
                          (clojure.core/let
                           [X__61236
                            (clojure.core/name elem__61234_nth_0__)]
                           (if
                            (clojure.core/= ?__60423 X__61236)
                            (if
                             (clojure.core/symbol? elem__61234_nth_1__)
                             (clojure.core/let
                              [X__61238
                               (clojure.core/name elem__61234_nth_1__)]
                              (clojure.core/case
                               X__61238
                               ("meander.zeta")
                               [?__60423]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__61815)
                        (recur (clojure.core/next search_space__61814))
                        result__61815))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__61812)))]
          (if
           (clojure.core/vector? input__60417)
           (if
            (clojure.core/= (clojure.core/count input__60417) 2)
            (clojure.core/let
             [input__60417_nth_0__
              (clojure.core/nth input__60417 0)
              input__60417_nth_1__
              (clojure.core/nth input__60417 1)]
             (if
              (clojure.core/seq? input__60417_nth_0__)
              (clojure.core/let
               [input__60417_nth_0___l__
                (clojure.core/take 1 input__60417_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__60417_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__60417_nth_0___r__
                  (clojure.core/drop 1 input__60417_nth_0__)]
                 (clojure.core/let
                  [input__60417_nth_0___l___nth_0__
                   (clojure.core/nth input__60417_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__60417_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__61216
                     (clojure.core/namespace
                      input__60417_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__60423 X__61216]
                     (clojure.core/let
                      [X__61218
                       (clojure.core/name
                        input__60417_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__61218
                       ("cata")
                       (clojure.core/let
                        [x__12596__auto__
                         (def__61206 input__60417_nth_1__ ?__60423)]
                        (if
                         (meander.runtime.zeta/fail? x__12596__auto__)
                         (state__61660)
                         (clojure.core/let
                          [[?__60423] x__12596__auto__]
                          (if
                           (clojure.core/vector? input__60417)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__60417)
                             2)
                            (clojure.core/let
                             [input__60417_nth_0__
                              (clojure.core/nth input__60417 0)
                              input__60417_nth_1__
                              (clojure.core/nth input__60417 1)]
                             (if
                              (clojure.core/seq? input__60417_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__60417_nth_0__)
                                2)
                               (clojure.core/let
                                [input__60417_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__60417_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__60417_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__60417_nth_0__]
                                  (clojure.core/let
                                   [?env input__60417_nth_1__]
                                   (try
                                    [{:tag :cata,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__13899__auto__
                                        (CATA__FN__60479
                                         [?pattern ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__13899__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__13899__auto__
                                         0))),
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__14839__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__14839__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__14839__auto__))))))))
                               (state__61660))
                              (state__61660)))
                            (state__61660))
                           (state__61660)))))
                       (state__61660)))))
                   (state__61660))))
                (state__61660)))
              (state__61660)))
            (state__61660))
           (state__61660))))
        (state__61660
         []
         (clojure.core/letfn
          [(def__61240
            [arg__61263 ?__60424]
            (clojure.core/letfn
             [(state__61817
               []
               (clojure.core/let
                [x__61264 "meander.zeta"]
                (if
                 (clojure.core/= ?__60424 x__61264)
                 [?__60424]
                 (state__61818))))
              (state__61818
               []
               (if
                (clojure.core/map? arg__61263)
                (clojure.core/let
                 [VAL__61265 (.valAt arg__61263 :aliases)]
                 (if
                  (clojure.core/map? VAL__61265)
                  (clojure.core/let
                   [X__61267 (clojure.core/set VAL__61265)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__61267))
                    (clojure.core/loop
                     [search_space__61819 (clojure.core/seq X__61267)]
                     (if
                      (clojure.core/seq search_space__61819)
                      (clojure.core/let
                       [elem__61268
                        (clojure.core/first search_space__61819)
                        result__61820
                        (clojure.core/let
                         [elem__61268_nth_0__
                          (clojure.core/nth elem__61268 0)
                          elem__61268_nth_1__
                          (clojure.core/nth elem__61268 1)]
                         (if
                          (clojure.core/symbol? elem__61268_nth_0__)
                          (clojure.core/let
                           [X__61270
                            (clojure.core/name elem__61268_nth_0__)]
                           (if
                            (clojure.core/= ?__60424 X__61270)
                            (if
                             (clojure.core/symbol? elem__61268_nth_1__)
                             (clojure.core/let
                              [X__61272
                               (clojure.core/name elem__61268_nth_1__)]
                              (clojure.core/case
                               X__61272
                               ("meander.zeta")
                               [?__60424]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__61820)
                        (recur (clojure.core/next search_space__61819))
                        result__61820))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__61817)))]
          (if
           (clojure.core/vector? input__60417)
           (if
            (clojure.core/= (clojure.core/count input__60417) 2)
            (clojure.core/let
             [input__60417_nth_0__
              (clojure.core/nth input__60417 0)
              input__60417_nth_1__
              (clojure.core/nth input__60417 1)]
             (if
              (clojure.core/seq? input__60417_nth_0__)
              (clojure.core/let
               [input__60417_nth_0___l__
                (clojure.core/take 1 input__60417_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__60417_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__60417_nth_0___r__
                  (clojure.core/drop 1 input__60417_nth_0__)]
                 (clojure.core/let
                  [input__60417_nth_0___l___nth_0__
                   (clojure.core/nth input__60417_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__60417_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__61250
                     (clojure.core/namespace
                      input__60417_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__60424 X__61250]
                     (clojure.core/let
                      [X__61252
                       (clojure.core/name
                        input__60417_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__61252
                       ("fold")
                       (clojure.core/let
                        [x__12596__auto__
                         (def__61240 input__60417_nth_1__ ?__60424)]
                        (if
                         (meander.runtime.zeta/fail? x__12596__auto__)
                         (state__61661)
                         (clojure.core/let
                          [[?__60424] x__12596__auto__]
                          (if
                           (clojure.core/vector? input__60417)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__60417)
                             2)
                            (clojure.core/let
                             [input__60417_nth_0__
                              (clojure.core/nth input__60417 0)
                              input__60417_nth_1__
                              (clojure.core/nth input__60417 1)]
                             (if
                              (clojure.core/seq? input__60417_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 4)
                                 input__60417_nth_0__)
                                4)
                               (clojure.core/let
                                [input__60417_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__60417_nth_0__
                                  1)
                                 input__60417_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__60417_nth_0__
                                  2)
                                 input__60417_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__60417_nth_0__
                                  3)]
                                (clojure.core/let
                                 [?mutable-variable
                                  input__60417_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?initial-value
                                   input__60417_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?fold-function
                                    input__60417_nth_0___nth_3__]
                                   (clojure.core/let
                                    [?form input__60417_nth_0__]
                                    (clojure.core/let
                                     [?env input__60417_nth_1__]
                                     (try
                                      [(clojure.core/let
                                        [CATA_RESULT__13899__auto__
                                         (CATA__FN__60479
                                          ['meander.dev.parse.zeta/make-fold
                                           (clojure.core/let
                                            [CATA_RESULT__13899__auto__
                                             (CATA__FN__60479
                                              [?mutable-variable
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__13899__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__13899__auto__
                                              0)))
                                           (clojure.core/let
                                            [CATA_RESULT__13899__auto__
                                             (CATA__FN__60479
                                              [?initial-value ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__13899__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__13899__auto__
                                              0)))
                                           ?fold-function
                                           ?form])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__13899__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__13899__auto__
                                          0)))]
                                      (catch
                                       java.lang.Exception
                                       e__14839__auto__
                                       (if
                                        (meander.runtime.zeta/fail?
                                         e__14839__auto__)
                                        (meander.runtime.zeta/fail)
                                        (throw
                                         e__14839__auto__))))))))))
                               (state__61661))
                              (state__61661)))
                            (state__61661))
                           (state__61661)))))
                       (state__61661)))))
                   (state__61661))))
                (state__61661)))
              (state__61661)))
            (state__61661))
           (state__61661))))
        (state__61661
         []
         (if
          (clojure.core/vector? input__60417)
          (if
           (clojure.core/= (clojure.core/count input__60417) 5)
           (clojure.core/let
            [input__60417_nth_0__
             (clojure.core/nth input__60417 0)
             input__60417_nth_1__
             (clojure.core/nth input__60417 1)
             input__60417_nth_2__
             (clojure.core/nth input__60417 2)
             input__60417_nth_3__
             (clojure.core/nth input__60417 3)
             input__60417_nth_4__
             (clojure.core/nth input__60417 4)]
            (clojure.core/case
             input__60417_nth_0__
             (meander.dev.parse.zeta/make-fold)
             (if
              (clojure.core/map? input__60417_nth_1__)
              (clojure.core/let
               [VAL__61275 (.valAt input__60417_nth_1__ :tag)]
               (clojure.core/case
                VAL__61275
                (:mutable-variable)
                (clojure.core/let
                 [?variable-ast input__60417_nth_1__]
                 (clojure.core/let
                  [?initial-value-ast input__60417_nth_2__]
                  (clojure.core/let
                   [?fold-function input__60417_nth_3__]
                   (clojure.core/let
                    [?form input__60417_nth_4__]
                    (try
                     [{:tag :fold,
                       :variable ?variable-ast,
                       :initial-value ?initial-value-ast,
                       :fold-function
                       {:tag :host-expression, :form ?fold-function},
                       :form ?form}]
                     (catch
                      java.lang.Exception
                      e__14839__auto__
                      (if
                       (meander.runtime.zeta/fail? e__14839__auto__)
                       (meander.runtime.zeta/fail)
                       (throw e__14839__auto__))))))))
                (state__61662)))
              (state__61662))
             (state__61662)))
           (state__61662))
          (state__61662)))
        (state__61662
         []
         (clojure.core/letfn
          [(def__61277
            [arg__61300 ?__60425]
            (clojure.core/letfn
             [(state__61822
               []
               (clojure.core/let
                [x__61301 "meander.zeta"]
                (if
                 (clojure.core/= ?__60425 x__61301)
                 [?__60425]
                 (state__61823))))
              (state__61823
               []
               (if
                (clojure.core/map? arg__61300)
                (clojure.core/let
                 [VAL__61302 (.valAt arg__61300 :aliases)]
                 (if
                  (clojure.core/map? VAL__61302)
                  (clojure.core/let
                   [X__61304 (clojure.core/set VAL__61302)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__61304))
                    (clojure.core/loop
                     [search_space__61824 (clojure.core/seq X__61304)]
                     (if
                      (clojure.core/seq search_space__61824)
                      (clojure.core/let
                       [elem__61305
                        (clojure.core/first search_space__61824)
                        result__61825
                        (clojure.core/let
                         [elem__61305_nth_0__
                          (clojure.core/nth elem__61305 0)
                          elem__61305_nth_1__
                          (clojure.core/nth elem__61305 1)]
                         (if
                          (clojure.core/symbol? elem__61305_nth_0__)
                          (clojure.core/let
                           [X__61307
                            (clojure.core/name elem__61305_nth_0__)]
                           (if
                            (clojure.core/= ?__60425 X__61307)
                            (if
                             (clojure.core/symbol? elem__61305_nth_1__)
                             (clojure.core/let
                              [X__61309
                               (clojure.core/name elem__61305_nth_1__)]
                              (clojure.core/case
                               X__61309
                               ("meander.zeta")
                               [?__60425]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__61825)
                        (recur (clojure.core/next search_space__61824))
                        result__61825))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__61822)))]
          (if
           (clojure.core/vector? input__60417)
           (if
            (clojure.core/= (clojure.core/count input__60417) 2)
            (clojure.core/let
             [input__60417_nth_0__
              (clojure.core/nth input__60417 0)
              input__60417_nth_1__
              (clojure.core/nth input__60417 1)]
             (if
              (clojure.core/seq? input__60417_nth_0__)
              (clojure.core/let
               [input__60417_nth_0___l__
                (clojure.core/take 1 input__60417_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__60417_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__60417_nth_0___r__
                  (clojure.core/drop 1 input__60417_nth_0__)]
                 (clojure.core/let
                  [input__60417_nth_0___l___nth_0__
                   (clojure.core/nth input__60417_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__60417_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__61287
                     (clojure.core/namespace
                      input__60417_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__60425 X__61287]
                     (clojure.core/let
                      [X__61289
                       (clojure.core/name
                        input__60417_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__61289
                       ("let")
                       (clojure.core/let
                        [x__12596__auto__
                         (def__61277 input__60417_nth_1__ ?__60425)]
                        (if
                         (meander.runtime.zeta/fail? x__12596__auto__)
                         (state__61663)
                         (clojure.core/let
                          [[?__60425] x__12596__auto__]
                          (if
                           (clojure.core/vector? input__60417)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__60417)
                             2)
                            (clojure.core/let
                             [input__60417_nth_0__
                              (clojure.core/nth input__60417 0)
                              input__60417_nth_1__
                              (clojure.core/nth input__60417 1)]
                             (if
                              (clojure.core/seq? input__60417_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__60417_nth_0__)
                                3)
                               (clojure.core/let
                                [input__60417_nth_0___nth_0__
                                 (clojure.core/nth
                                  input__60417_nth_0__
                                  0)
                                 input__60417_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__60417_nth_0__
                                  1)
                                 input__60417_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__60417_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?pattern
                                  input__60417_nth_0___nth_0__]
                                 (clojure.core/let
                                  [?expression
                                   input__60417_nth_0___nth_1__]
                                  (clojure.core/let
                                   [?next input__60417_nth_0___nth_2__]
                                   (clojure.core/let
                                    [?form input__60417_nth_0__]
                                    (clojure.core/let
                                     [?env input__60417_nth_1__]
                                     (try
                                      [{:tag :let,
                                        :pattern
                                        (clojure.core/let
                                         [CATA_RESULT__13899__auto__
                                          (CATA__FN__60479
                                           [?pattern ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__13899__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__13899__auto__
                                           0))),
                                        :expression
                                        {:tag :host-expression,
                                         :form ?expression},
                                        :next
                                        (clojure.core/let
                                         [CATA_RESULT__13899__auto__
                                          (CATA__FN__60479
                                           [?next ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__13899__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__13899__auto__
                                           0)))}]
                                      (catch
                                       java.lang.Exception
                                       e__14839__auto__
                                       (if
                                        (meander.runtime.zeta/fail?
                                         e__14839__auto__)
                                        (meander.runtime.zeta/fail)
                                        (throw
                                         e__14839__auto__))))))))))
                               (state__61663))
                              (state__61663)))
                            (state__61663))
                           (state__61663)))))
                       (state__61663)))))
                   (state__61663))))
                (state__61663)))
              (state__61663)))
            (state__61663))
           (state__61663))))
        (state__61663
         []
         (clojure.core/letfn
          [(def__61311
            [arg__61334 ?__60426]
            (clojure.core/letfn
             [(state__61827
               []
               (clojure.core/let
                [x__61335 "meander.zeta"]
                (if
                 (clojure.core/= ?__60426 x__61335)
                 [?__60426]
                 (state__61828))))
              (state__61828
               []
               (if
                (clojure.core/map? arg__61334)
                (clojure.core/let
                 [VAL__61336 (.valAt arg__61334 :aliases)]
                 (if
                  (clojure.core/map? VAL__61336)
                  (clojure.core/let
                   [X__61338 (clojure.core/set VAL__61336)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__61338))
                    (clojure.core/loop
                     [search_space__61829 (clojure.core/seq X__61338)]
                     (if
                      (clojure.core/seq search_space__61829)
                      (clojure.core/let
                       [elem__61339
                        (clojure.core/first search_space__61829)
                        result__61830
                        (clojure.core/let
                         [elem__61339_nth_0__
                          (clojure.core/nth elem__61339 0)
                          elem__61339_nth_1__
                          (clojure.core/nth elem__61339 1)]
                         (if
                          (clojure.core/symbol? elem__61339_nth_0__)
                          (clojure.core/let
                           [X__61341
                            (clojure.core/name elem__61339_nth_0__)]
                           (if
                            (clojure.core/= ?__60426 X__61341)
                            (if
                             (clojure.core/symbol? elem__61339_nth_1__)
                             (clojure.core/let
                              [X__61343
                               (clojure.core/name elem__61339_nth_1__)]
                              (clojure.core/case
                               X__61343
                               ("meander.zeta")
                               [?__60426]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__61830)
                        (recur (clojure.core/next search_space__61829))
                        result__61830))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__61827)))]
          (if
           (clojure.core/vector? input__60417)
           (if
            (clojure.core/= (clojure.core/count input__60417) 2)
            (clojure.core/let
             [input__60417_nth_0__
              (clojure.core/nth input__60417 0)
              input__60417_nth_1__
              (clojure.core/nth input__60417 1)]
             (if
              (clojure.core/seq? input__60417_nth_0__)
              (clojure.core/let
               [input__60417_nth_0___l__
                (clojure.core/take 1 input__60417_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__60417_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__60417_nth_0___r__
                  (clojure.core/drop 1 input__60417_nth_0__)]
                 (clojure.core/let
                  [input__60417_nth_0___l___nth_0__
                   (clojure.core/nth input__60417_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__60417_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__61321
                     (clojure.core/namespace
                      input__60417_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__60426 X__61321]
                     (clojure.core/let
                      [X__61323
                       (clojure.core/name
                        input__60417_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__61323
                       ("not")
                       (clojure.core/let
                        [x__12596__auto__
                         (def__61311 input__60417_nth_1__ ?__60426)]
                        (if
                         (meander.runtime.zeta/fail? x__12596__auto__)
                         (state__61664)
                         (clojure.core/let
                          [[?__60426] x__12596__auto__]
                          (if
                           (clojure.core/vector? input__60417)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__60417)
                             2)
                            (clojure.core/let
                             [input__60417_nth_0__
                              (clojure.core/nth input__60417 0)
                              input__60417_nth_1__
                              (clojure.core/nth input__60417 1)]
                             (if
                              (clojure.core/seq? input__60417_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__60417_nth_0__)
                                2)
                               (clojure.core/let
                                [input__60417_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__60417_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__60417_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__60417_nth_0__]
                                  (clojure.core/let
                                   [?env input__60417_nth_1__]
                                   (try
                                    [{:tag :not,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__13899__auto__
                                        (CATA__FN__60479
                                         [?pattern ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__13899__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__13899__auto__
                                         0))),
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__14839__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__14839__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__14839__auto__))))))))
                               (state__61664))
                              (state__61664)))
                            (state__61664))
                           (state__61664)))))
                       (state__61664)))))
                   (state__61664))))
                (state__61664)))
              (state__61664)))
            (state__61664))
           (state__61664))))
        (state__61664
         []
         (clojure.core/letfn
          [(def__61345
            [arg__61368 ?__60427]
            (clojure.core/letfn
             [(state__61832
               []
               (clojure.core/let
                [x__61369 "meander.zeta"]
                (if
                 (clojure.core/= ?__60427 x__61369)
                 [?__60427]
                 (state__61833))))
              (state__61833
               []
               (if
                (clojure.core/map? arg__61368)
                (clojure.core/let
                 [VAL__61370 (.valAt arg__61368 :aliases)]
                 (if
                  (clojure.core/map? VAL__61370)
                  (clojure.core/let
                   [X__61372 (clojure.core/set VAL__61370)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__61372))
                    (clojure.core/loop
                     [search_space__61834 (clojure.core/seq X__61372)]
                     (if
                      (clojure.core/seq search_space__61834)
                      (clojure.core/let
                       [elem__61373
                        (clojure.core/first search_space__61834)
                        result__61835
                        (clojure.core/let
                         [elem__61373_nth_0__
                          (clojure.core/nth elem__61373 0)
                          elem__61373_nth_1__
                          (clojure.core/nth elem__61373 1)]
                         (if
                          (clojure.core/symbol? elem__61373_nth_0__)
                          (clojure.core/let
                           [X__61375
                            (clojure.core/name elem__61373_nth_0__)]
                           (if
                            (clojure.core/= ?__60427 X__61375)
                            (if
                             (clojure.core/symbol? elem__61373_nth_1__)
                             (clojure.core/let
                              [X__61377
                               (clojure.core/name elem__61373_nth_1__)]
                              (clojure.core/case
                               X__61377
                               ("meander.zeta")
                               [?__60427]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__61835)
                        (recur (clojure.core/next search_space__61834))
                        result__61835))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__61832)))]
          (if
           (clojure.core/vector? input__60417)
           (if
            (clojure.core/= (clojure.core/count input__60417) 2)
            (clojure.core/let
             [input__60417_nth_0__
              (clojure.core/nth input__60417 0)
              input__60417_nth_1__
              (clojure.core/nth input__60417 1)]
             (if
              (clojure.core/seq? input__60417_nth_0__)
              (clojure.core/let
               [input__60417_nth_0___l__
                (clojure.core/take 1 input__60417_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__60417_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__60417_nth_0___r__
                  (clojure.core/drop 1 input__60417_nth_0__)]
                 (clojure.core/let
                  [input__60417_nth_0___l___nth_0__
                   (clojure.core/nth input__60417_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__60417_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__61355
                     (clojure.core/namespace
                      input__60417_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__60427 X__61355]
                     (clojure.core/let
                      [X__61357
                       (clojure.core/name
                        input__60417_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__61357
                       ("or")
                       (clojure.core/let
                        [x__12596__auto__
                         (def__61345 input__60417_nth_1__ ?__60427)]
                        (if
                         (meander.runtime.zeta/fail? x__12596__auto__)
                         (state__61665)
                         (clojure.core/let
                          [[?__60427] x__12596__auto__]
                          (if
                           (clojure.core/vector? input__60417)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__60417)
                             2)
                            (clojure.core/let
                             [input__60417_nth_0__
                              (clojure.core/nth input__60417 0)
                              input__60417_nth_1__
                              (clojure.core/nth input__60417 1)]
                             (if
                              (clojure.core/seq? input__60417_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__60417_nth_0__)
                                3)
                               (clojure.core/let
                                [input__60417_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__60417_nth_0__
                                  1)
                                 input__60417_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__60417_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?left input__60417_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?right input__60417_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__60417_nth_0__]
                                   (clojure.core/let
                                    [?env input__60417_nth_1__]
                                    (try
                                     [{:tag :or,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__13899__auto__
                                         (CATA__FN__60479
                                          [?left ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__13899__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__13899__auto__
                                          0))),
                                       :right
                                       (clojure.core/let
                                        [CATA_RESULT__13899__auto__
                                         (CATA__FN__60479
                                          [?right ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__13899__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__13899__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__14839__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__14839__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__14839__auto__)))))))))
                               (state__61665))
                              (state__61665)))
                            (state__61665))
                           (state__61665)))))
                       (state__61665)))))
                   (state__61665))))
                (state__61665)))
              (state__61665)))
            (state__61665))
           (state__61665))))
        (state__61665
         []
         (clojure.core/letfn
          [(def__61379
            [arg__61402 ?__60428]
            (clojure.core/letfn
             [(state__61837
               []
               (clojure.core/let
                [x__61403 "meander.zeta"]
                (if
                 (clojure.core/= ?__60428 x__61403)
                 [?__60428]
                 (state__61838))))
              (state__61838
               []
               (if
                (clojure.core/map? arg__61402)
                (clojure.core/let
                 [VAL__61404 (.valAt arg__61402 :aliases)]
                 (if
                  (clojure.core/map? VAL__61404)
                  (clojure.core/let
                   [X__61406 (clojure.core/set VAL__61404)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__61406))
                    (clojure.core/loop
                     [search_space__61839 (clojure.core/seq X__61406)]
                     (if
                      (clojure.core/seq search_space__61839)
                      (clojure.core/let
                       [elem__61407
                        (clojure.core/first search_space__61839)
                        result__61840
                        (clojure.core/let
                         [elem__61407_nth_0__
                          (clojure.core/nth elem__61407 0)
                          elem__61407_nth_1__
                          (clojure.core/nth elem__61407 1)]
                         (if
                          (clojure.core/symbol? elem__61407_nth_0__)
                          (clojure.core/let
                           [X__61409
                            (clojure.core/name elem__61407_nth_0__)]
                           (if
                            (clojure.core/= ?__60428 X__61409)
                            (if
                             (clojure.core/symbol? elem__61407_nth_1__)
                             (clojure.core/let
                              [X__61411
                               (clojure.core/name elem__61407_nth_1__)]
                              (clojure.core/case
                               X__61411
                               ("meander.zeta")
                               [?__60428]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__61840)
                        (recur (clojure.core/next search_space__61839))
                        result__61840))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__61837)))]
          (if
           (clojure.core/vector? input__60417)
           (if
            (clojure.core/= (clojure.core/count input__60417) 2)
            (clojure.core/let
             [input__60417_nth_0__
              (clojure.core/nth input__60417 0)
              input__60417_nth_1__
              (clojure.core/nth input__60417 1)]
             (if
              (clojure.core/seq? input__60417_nth_0__)
              (clojure.core/let
               [input__60417_nth_0___l__
                (clojure.core/take 1 input__60417_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__60417_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__60417_nth_0___r__
                  (clojure.core/drop 1 input__60417_nth_0__)]
                 (clojure.core/let
                  [input__60417_nth_0___l___nth_0__
                   (clojure.core/nth input__60417_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__60417_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__61389
                     (clojure.core/namespace
                      input__60417_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__60428 X__61389]
                     (clojure.core/let
                      [X__61391
                       (clojure.core/name
                        input__60417_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__61391
                       ("re")
                       (clojure.core/let
                        [x__12596__auto__
                         (def__61379 input__60417_nth_1__ ?__60428)]
                        (if
                         (meander.runtime.zeta/fail? x__12596__auto__)
                         (state__61666)
                         (clojure.core/let
                          [[?__60428] x__12596__auto__]
                          (if
                           (clojure.core/vector? input__60417)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__60417)
                             2)
                            (clojure.core/let
                             [input__60417_nth_0__
                              (clojure.core/nth input__60417 0)
                              input__60417_nth_1__
                              (clojure.core/nth input__60417 1)]
                             (if
                              (clojure.core/seq? input__60417_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__60417_nth_0__)
                                2)
                               (clojure.core/let
                                [input__60417_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__60417_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?regex input__60417_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__60417_nth_0__]
                                  (clojure.core/let
                                   [?env input__60417_nth_1__]
                                   (try
                                    [{:tag :regex,
                                      :regex ?regex,
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__14839__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__14839__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__14839__auto__))))))))
                               (state__61666))
                              (state__61666)))
                            (state__61666))
                           (state__61666)))))
                       (state__61666)))))
                   (state__61666))))
                (state__61666)))
              (state__61666)))
            (state__61666))
           (state__61666))))
        (state__61666
         []
         (clojure.core/letfn
          [(def__61413
            [arg__61436 ?__60429]
            (clojure.core/letfn
             [(state__61842
               []
               (clojure.core/let
                [x__61437 "meander.zeta"]
                (if
                 (clojure.core/= ?__60429 x__61437)
                 [?__60429]
                 (state__61843))))
              (state__61843
               []
               (if
                (clojure.core/map? arg__61436)
                (clojure.core/let
                 [VAL__61438 (.valAt arg__61436 :aliases)]
                 (if
                  (clojure.core/map? VAL__61438)
                  (clojure.core/let
                   [X__61440 (clojure.core/set VAL__61438)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__61440))
                    (clojure.core/loop
                     [search_space__61844 (clojure.core/seq X__61440)]
                     (if
                      (clojure.core/seq search_space__61844)
                      (clojure.core/let
                       [elem__61441
                        (clojure.core/first search_space__61844)
                        result__61845
                        (clojure.core/let
                         [elem__61441_nth_0__
                          (clojure.core/nth elem__61441 0)
                          elem__61441_nth_1__
                          (clojure.core/nth elem__61441 1)]
                         (if
                          (clojure.core/symbol? elem__61441_nth_0__)
                          (clojure.core/let
                           [X__61443
                            (clojure.core/name elem__61441_nth_0__)]
                           (if
                            (clojure.core/= ?__60429 X__61443)
                            (if
                             (clojure.core/symbol? elem__61441_nth_1__)
                             (clojure.core/let
                              [X__61445
                               (clojure.core/name elem__61441_nth_1__)]
                              (clojure.core/case
                               X__61445
                               ("meander.zeta")
                               [?__60429]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__61845)
                        (recur (clojure.core/next search_space__61844))
                        result__61845))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__61842)))]
          (if
           (clojure.core/vector? input__60417)
           (if
            (clojure.core/= (clojure.core/count input__60417) 2)
            (clojure.core/let
             [input__60417_nth_0__
              (clojure.core/nth input__60417 0)
              input__60417_nth_1__
              (clojure.core/nth input__60417 1)]
             (if
              (clojure.core/seq? input__60417_nth_0__)
              (clojure.core/let
               [input__60417_nth_0___l__
                (clojure.core/take 1 input__60417_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__60417_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__60417_nth_0___r__
                  (clojure.core/drop 1 input__60417_nth_0__)]
                 (clojure.core/let
                  [input__60417_nth_0___l___nth_0__
                   (clojure.core/nth input__60417_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__60417_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__61423
                     (clojure.core/namespace
                      input__60417_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__60429 X__61423]
                     (clojure.core/let
                      [X__61425
                       (clojure.core/name
                        input__60417_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__61425
                       ("re")
                       (clojure.core/let
                        [x__12596__auto__
                         (def__61413 input__60417_nth_1__ ?__60429)]
                        (if
                         (meander.runtime.zeta/fail? x__12596__auto__)
                         (state__61667)
                         (clojure.core/let
                          [[?__60429] x__12596__auto__]
                          (if
                           (clojure.core/vector? input__60417)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__60417)
                             2)
                            (clojure.core/let
                             [input__60417_nth_0__
                              (clojure.core/nth input__60417 0)
                              input__60417_nth_1__
                              (clojure.core/nth input__60417 1)]
                             (if
                              (clojure.core/seq? input__60417_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__60417_nth_0__)
                                3)
                               (clojure.core/let
                                [input__60417_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__60417_nth_0__
                                  1)
                                 input__60417_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__60417_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?regex input__60417_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?capture
                                   input__60417_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__60417_nth_0__]
                                   (clojure.core/let
                                    [?env input__60417_nth_1__]
                                    (try
                                     [{:tag :regex,
                                       :regex ?regex,
                                       :capture
                                       (clojure.core/let
                                        [CATA_RESULT__13899__auto__
                                         (CATA__FN__60479
                                          [?capture ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__13899__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__13899__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__14839__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__14839__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__14839__auto__)))))))))
                               (state__61667))
                              (state__61667)))
                            (state__61667))
                           (state__61667)))))
                       (state__61667)))))
                   (state__61667))))
                (state__61667)))
              (state__61667)))
            (state__61667))
           (state__61667))))
        (state__61667
         []
         (clojure.core/letfn
          [(def__61447
            [arg__61470 ?__60430]
            (clojure.core/letfn
             [(state__61847
               []
               (clojure.core/let
                [x__61471 "meander.zeta"]
                (if
                 (clojure.core/= ?__60430 x__61471)
                 [?__60430]
                 (state__61848))))
              (state__61848
               []
               (if
                (clojure.core/map? arg__61470)
                (clojure.core/let
                 [VAL__61472 (.valAt arg__61470 :aliases)]
                 (if
                  (clojure.core/map? VAL__61472)
                  (clojure.core/let
                   [X__61474 (clojure.core/set VAL__61472)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__61474))
                    (clojure.core/loop
                     [search_space__61849 (clojure.core/seq X__61474)]
                     (if
                      (clojure.core/seq search_space__61849)
                      (clojure.core/let
                       [elem__61475
                        (clojure.core/first search_space__61849)
                        result__61850
                        (clojure.core/let
                         [elem__61475_nth_0__
                          (clojure.core/nth elem__61475 0)
                          elem__61475_nth_1__
                          (clojure.core/nth elem__61475 1)]
                         (if
                          (clojure.core/symbol? elem__61475_nth_0__)
                          (clojure.core/let
                           [X__61477
                            (clojure.core/name elem__61475_nth_0__)]
                           (if
                            (clojure.core/= ?__60430 X__61477)
                            (if
                             (clojure.core/symbol? elem__61475_nth_1__)
                             (clojure.core/let
                              [X__61479
                               (clojure.core/name elem__61475_nth_1__)]
                              (clojure.core/case
                               X__61479
                               ("meander.zeta")
                               [?__60430]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__61850)
                        (recur (clojure.core/next search_space__61849))
                        result__61850))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__61847)))]
          (if
           (clojure.core/vector? input__60417)
           (if
            (clojure.core/= (clojure.core/count input__60417) 2)
            (clojure.core/let
             [input__60417_nth_0__
              (clojure.core/nth input__60417 0)
              input__60417_nth_1__
              (clojure.core/nth input__60417 1)]
             (if
              (clojure.core/seq? input__60417_nth_0__)
              (clojure.core/let
               [input__60417_nth_0___l__
                (clojure.core/take 1 input__60417_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__60417_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__60417_nth_0___r__
                  (clojure.core/drop 1 input__60417_nth_0__)]
                 (clojure.core/let
                  [input__60417_nth_0___l___nth_0__
                   (clojure.core/nth input__60417_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__60417_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__61457
                     (clojure.core/namespace
                      input__60417_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__60430 X__61457]
                     (clojure.core/let
                      [X__61459
                       (clojure.core/name
                        input__60417_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__61459
                       ("string")
                       (clojure.core/let
                        [x__12596__auto__
                         (def__61447 input__60417_nth_1__ ?__60430)]
                        (if
                         (meander.runtime.zeta/fail? x__12596__auto__)
                         (state__61668)
                         (clojure.core/let
                          [[?__60430] x__12596__auto__]
                          (if
                           (clojure.core/vector? input__60417)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__60417)
                             2)
                            (clojure.core/let
                             [input__60417_nth_0__
                              (clojure.core/nth input__60417 0)
                              input__60417_nth_1__
                              (clojure.core/nth input__60417 1)]
                             (if
                              (clojure.core/seq? input__60417_nth_0__)
                              (clojure.core/let
                               [input__60417_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__60417_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__60417_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__60417_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__60417_nth_0__)]
                                 (clojure.core/let
                                  [?sequence input__60417_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__60417_nth_0__]
                                   (clojure.core/let
                                    [?env input__60417_nth_1__]
                                    (try
                                     [{:tag :string,
                                       :next
                                       (clojure.core/let
                                        [CATA_RESULT__13899__auto__
                                         (CATA__FN__60479
                                          ['meander.dev.parse.zeta/parse-sequential
                                           (clojure.core/into
                                            []
                                            ?sequence)
                                           (clojure.core/let
                                            [form__13998__auto__
                                             {:context :string}]
                                            (clojure.core/merge
                                             (clojure.core/into
                                              {}
                                              ?env)
                                             form__13998__auto__))])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__13899__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__13899__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__14839__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__14839__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__14839__auto__))))))))
                                (state__61668)))
                              (state__61668)))
                            (state__61668))
                           (state__61668)))))
                       (state__61668)))))
                   (state__61668))))
                (state__61668)))
              (state__61668)))
            (state__61668))
           (state__61668))))
        (state__61668
         []
         (clojure.core/letfn
          [(def__61481
            [arg__61504 ?__60431]
            (clojure.core/letfn
             [(state__61852
               []
               (clojure.core/let
                [x__61505 "meander.zeta"]
                (if
                 (clojure.core/= ?__60431 x__61505)
                 [?__60431]
                 (state__61853))))
              (state__61853
               []
               (if
                (clojure.core/map? arg__61504)
                (clojure.core/let
                 [VAL__61506 (.valAt arg__61504 :aliases)]
                 (if
                  (clojure.core/map? VAL__61506)
                  (clojure.core/let
                   [X__61508 (clojure.core/set VAL__61506)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__61508))
                    (clojure.core/loop
                     [search_space__61854 (clojure.core/seq X__61508)]
                     (if
                      (clojure.core/seq search_space__61854)
                      (clojure.core/let
                       [elem__61509
                        (clojure.core/first search_space__61854)
                        result__61855
                        (clojure.core/let
                         [elem__61509_nth_0__
                          (clojure.core/nth elem__61509 0)
                          elem__61509_nth_1__
                          (clojure.core/nth elem__61509 1)]
                         (if
                          (clojure.core/symbol? elem__61509_nth_0__)
                          (clojure.core/let
                           [X__61511
                            (clojure.core/name elem__61509_nth_0__)]
                           (if
                            (clojure.core/= ?__60431 X__61511)
                            (if
                             (clojure.core/symbol? elem__61509_nth_1__)
                             (clojure.core/let
                              [X__61513
                               (clojure.core/name elem__61509_nth_1__)]
                              (clojure.core/case
                               X__61513
                               ("meander.zeta")
                               [?__60431]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__61855)
                        (recur (clojure.core/next search_space__61854))
                        result__61855))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__61852)))]
          (if
           (clojure.core/vector? input__60417)
           (if
            (clojure.core/= (clojure.core/count input__60417) 2)
            (clojure.core/let
             [input__60417_nth_0__
              (clojure.core/nth input__60417 0)
              input__60417_nth_1__
              (clojure.core/nth input__60417 1)]
             (if
              (clojure.core/seq? input__60417_nth_0__)
              (clojure.core/let
               [input__60417_nth_0___l__
                (clojure.core/take 1 input__60417_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__60417_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__60417_nth_0___r__
                  (clojure.core/drop 1 input__60417_nth_0__)]
                 (clojure.core/let
                  [input__60417_nth_0___l___nth_0__
                   (clojure.core/nth input__60417_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__60417_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__61491
                     (clojure.core/namespace
                      input__60417_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__60431 X__61491]
                     (clojure.core/let
                      [X__61493
                       (clojure.core/name
                        input__60417_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__61493
                       ("symbol")
                       (clojure.core/let
                        [x__12596__auto__
                         (def__61481 input__60417_nth_1__ ?__60431)]
                        (if
                         (meander.runtime.zeta/fail? x__12596__auto__)
                         (state__61669)
                         (clojure.core/let
                          [[?__60431] x__12596__auto__]
                          (if
                           (clojure.core/vector? input__60417)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__60417)
                             2)
                            (clojure.core/let
                             [input__60417_nth_0__
                              (clojure.core/nth input__60417 0)
                              input__60417_nth_1__
                              (clojure.core/nth input__60417 1)]
                             (if
                              (clojure.core/seq? input__60417_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__60417_nth_0__)
                                2)
                               (clojure.core/let
                                [input__60417_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__60417_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?name input__60417_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__60417_nth_0__]
                                  (clojure.core/let
                                   [?env input__60417_nth_1__]
                                   (try
                                    [{:tag :symbol,
                                      :name
                                      (clojure.core/let
                                       [CATA_RESULT__13899__auto__
                                        (CATA__FN__60479 [?name ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__13899__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__13899__auto__
                                         0))),
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__14839__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__14839__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__14839__auto__))))))))
                               (state__61669))
                              (state__61669)))
                            (state__61669))
                           (state__61669)))))
                       (state__61669)))))
                   (state__61669))))
                (state__61669)))
              (state__61669)))
            (state__61669))
           (state__61669))))
        (state__61669
         []
         (clojure.core/letfn
          [(def__61515
            [arg__61538 ?__60432]
            (clojure.core/letfn
             [(state__61857
               []
               (clojure.core/let
                [x__61539 "meander.zeta"]
                (if
                 (clojure.core/= ?__60432 x__61539)
                 [?__60432]
                 (state__61858))))
              (state__61858
               []
               (if
                (clojure.core/map? arg__61538)
                (clojure.core/let
                 [VAL__61540 (.valAt arg__61538 :aliases)]
                 (if
                  (clojure.core/map? VAL__61540)
                  (clojure.core/let
                   [X__61542 (clojure.core/set VAL__61540)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__61542))
                    (clojure.core/loop
                     [search_space__61859 (clojure.core/seq X__61542)]
                     (if
                      (clojure.core/seq search_space__61859)
                      (clojure.core/let
                       [elem__61543
                        (clojure.core/first search_space__61859)
                        result__61860
                        (clojure.core/let
                         [elem__61543_nth_0__
                          (clojure.core/nth elem__61543 0)
                          elem__61543_nth_1__
                          (clojure.core/nth elem__61543 1)]
                         (if
                          (clojure.core/symbol? elem__61543_nth_0__)
                          (clojure.core/let
                           [X__61545
                            (clojure.core/name elem__61543_nth_0__)]
                           (if
                            (clojure.core/= ?__60432 X__61545)
                            (if
                             (clojure.core/symbol? elem__61543_nth_1__)
                             (clojure.core/let
                              [X__61547
                               (clojure.core/name elem__61543_nth_1__)]
                              (clojure.core/case
                               X__61547
                               ("meander.zeta")
                               [?__60432]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__61860)
                        (recur (clojure.core/next search_space__61859))
                        result__61860))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__61857)))]
          (if
           (clojure.core/vector? input__60417)
           (if
            (clojure.core/= (clojure.core/count input__60417) 2)
            (clojure.core/let
             [input__60417_nth_0__
              (clojure.core/nth input__60417 0)
              input__60417_nth_1__
              (clojure.core/nth input__60417 1)]
             (if
              (clojure.core/seq? input__60417_nth_0__)
              (clojure.core/let
               [input__60417_nth_0___l__
                (clojure.core/take 1 input__60417_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__60417_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__60417_nth_0___r__
                  (clojure.core/drop 1 input__60417_nth_0__)]
                 (clojure.core/let
                  [input__60417_nth_0___l___nth_0__
                   (clojure.core/nth input__60417_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__60417_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__61525
                     (clojure.core/namespace
                      input__60417_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__60432 X__61525]
                     (clojure.core/let
                      [X__61527
                       (clojure.core/name
                        input__60417_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__61527
                       ("symbol")
                       (clojure.core/let
                        [x__12596__auto__
                         (def__61515 input__60417_nth_1__ ?__60432)]
                        (if
                         (meander.runtime.zeta/fail? x__12596__auto__)
                         (state__61670)
                         (clojure.core/let
                          [[?__60432] x__12596__auto__]
                          (if
                           (clojure.core/vector? input__60417)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__60417)
                             2)
                            (clojure.core/let
                             [input__60417_nth_0__
                              (clojure.core/nth input__60417 0)
                              input__60417_nth_1__
                              (clojure.core/nth input__60417 1)]
                             (if
                              (clojure.core/seq? input__60417_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__60417_nth_0__)
                                3)
                               (clojure.core/let
                                [input__60417_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__60417_nth_0__
                                  1)
                                 input__60417_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__60417_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?namespace
                                  input__60417_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?name input__60417_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__60417_nth_0__]
                                   (clojure.core/let
                                    [?env input__60417_nth_1__]
                                    (try
                                     [{:tag :symbol,
                                       :name
                                       (clojure.core/let
                                        [CATA_RESULT__13899__auto__
                                         (CATA__FN__60479
                                          [?name ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__13899__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__13899__auto__
                                          0))),
                                       :namespace
                                       (clojure.core/let
                                        [CATA_RESULT__13899__auto__
                                         (CATA__FN__60479
                                          [?namespace ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__13899__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__13899__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__14839__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__14839__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__14839__auto__)))))))))
                               (state__61670))
                              (state__61670)))
                            (state__61670))
                           (state__61670)))))
                       (state__61670)))))
                   (state__61670))))
                (state__61670)))
              (state__61670)))
            (state__61670))
           (state__61670))))
        (state__61670
         []
         (clojure.core/letfn
          [(def__61549
            [arg__61572 ?__60433]
            (clojure.core/letfn
             [(state__61862
               []
               (clojure.core/let
                [x__61573 "meander.zeta"]
                (if
                 (clojure.core/= ?__60433 x__61573)
                 [?__60433]
                 (state__61863))))
              (state__61863
               []
               (if
                (clojure.core/map? arg__61572)
                (clojure.core/let
                 [VAL__61574 (.valAt arg__61572 :aliases)]
                 (if
                  (clojure.core/map? VAL__61574)
                  (clojure.core/let
                   [X__61576 (clojure.core/set VAL__61574)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__61576))
                    (clojure.core/loop
                     [search_space__61864 (clojure.core/seq X__61576)]
                     (if
                      (clojure.core/seq search_space__61864)
                      (clojure.core/let
                       [elem__61577
                        (clojure.core/first search_space__61864)
                        result__61865
                        (clojure.core/let
                         [elem__61577_nth_0__
                          (clojure.core/nth elem__61577 0)
                          elem__61577_nth_1__
                          (clojure.core/nth elem__61577 1)]
                         (if
                          (clojure.core/symbol? elem__61577_nth_0__)
                          (clojure.core/let
                           [X__61579
                            (clojure.core/name elem__61577_nth_0__)]
                           (if
                            (clojure.core/= ?__60433 X__61579)
                            (if
                             (clojure.core/symbol? elem__61577_nth_1__)
                             (clojure.core/let
                              [X__61581
                               (clojure.core/name elem__61577_nth_1__)]
                              (clojure.core/case
                               X__61581
                               ("meander.zeta")
                               [?__60433]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__61865)
                        (recur (clojure.core/next search_space__61864))
                        result__61865))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__61862)))]
          (if
           (clojure.core/vector? input__60417)
           (if
            (clojure.core/= (clojure.core/count input__60417) 2)
            (clojure.core/let
             [input__60417_nth_0__
              (clojure.core/nth input__60417 0)
              input__60417_nth_1__
              (clojure.core/nth input__60417 1)]
             (if
              (clojure.core/seq? input__60417_nth_0__)
              (clojure.core/let
               [input__60417_nth_0___l__
                (clojure.core/take 1 input__60417_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__60417_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__60417_nth_0___r__
                  (clojure.core/drop 1 input__60417_nth_0__)]
                 (clojure.core/let
                  [input__60417_nth_0___l___nth_0__
                   (clojure.core/nth input__60417_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__60417_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__61559
                     (clojure.core/namespace
                      input__60417_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__60433 X__61559]
                     (clojure.core/let
                      [X__61561
                       (clojure.core/name
                        input__60417_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__61561
                       ("symbol")
                       (clojure.core/let
                        [x__12596__auto__
                         (def__61549 input__60417_nth_1__ ?__60433)]
                        (if
                         (meander.runtime.zeta/fail? x__12596__auto__)
                         (state__61671)
                         (clojure.core/let
                          [[?__60433] x__12596__auto__]
                          (if
                           (clojure.core/vector? input__60417)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__60417)
                             2)
                            (clojure.core/let
                             [input__60417_nth_0__
                              (clojure.core/nth input__60417 0)
                              input__60417_nth_1__
                              (clojure.core/nth input__60417 1)]
                             (if
                              (clojure.core/seq? input__60417_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 5)
                                 input__60417_nth_0__)
                                5)
                               (clojure.core/let
                                [input__60417_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__60417_nth_0__
                                  1)
                                 input__60417_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__60417_nth_0__
                                  2)
                                 input__60417_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__60417_nth_0__
                                  3)
                                 input__60417_nth_0___nth_4__
                                 (clojure.core/nth
                                  input__60417_nth_0__
                                  4)]
                                (clojure.core/case
                                 input__60417_nth_0___nth_3__
                                 (:meander.zeta/as)
                                 (clojure.core/let
                                  [?namespace
                                   input__60417_nth_0___nth_1__]
                                  (clojure.core/let
                                   [?name input__60417_nth_0___nth_2__]
                                   (clojure.core/let
                                    [?pattern
                                     input__60417_nth_0___nth_4__]
                                    (clojure.core/let
                                     [?form input__60417_nth_0__]
                                     (clojure.core/let
                                      [?env input__60417_nth_1__]
                                      (try
                                       [{:tag :symbol,
                                         :name
                                         (clojure.core/let
                                          [CATA_RESULT__13899__auto__
                                           (CATA__FN__60479
                                            [?name ?env])]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            CATA_RESULT__13899__auto__)
                                           (throw
                                            (meander.runtime.zeta/fail))
                                           (clojure.core/nth
                                            CATA_RESULT__13899__auto__
                                            0))),
                                         :namespace
                                         (clojure.core/let
                                          [CATA_RESULT__13899__auto__
                                           (CATA__FN__60479
                                            [?namespace ?env])]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            CATA_RESULT__13899__auto__)
                                           (throw
                                            (meander.runtime.zeta/fail))
                                           (clojure.core/nth
                                            CATA_RESULT__13899__auto__
                                            0))),
                                         :as-pattern
                                         (clojure.core/let
                                          [CATA_RESULT__13899__auto__
                                           (CATA__FN__60479
                                            [?pattern ?env])]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            CATA_RESULT__13899__auto__)
                                           (throw
                                            (meander.runtime.zeta/fail))
                                           (clojure.core/nth
                                            CATA_RESULT__13899__auto__
                                            0))),
                                         :form ?form}]
                                       (catch
                                        java.lang.Exception
                                        e__14839__auto__
                                        (if
                                         (meander.runtime.zeta/fail?
                                          e__14839__auto__)
                                         (meander.runtime.zeta/fail)
                                         (throw
                                          e__14839__auto__)))))))))
                                 (state__61671)))
                               (state__61671))
                              (state__61671)))
                            (state__61671))
                           (state__61671)))))
                       (state__61671)))))
                   (state__61671))))
                (state__61671)))
              (state__61671)))
            (state__61671))
           (state__61671))))
        (state__61671
         []
         (if
          (clojure.core/vector? input__60417)
          (if
           (clojure.core/= (clojure.core/count input__60417) 2)
           (clojure.core/let
            [input__60417_nth_0__ (clojure.core/nth input__60417 0)]
            (clojure.core/letfn
             [(state__61867
               []
               (clojure.core/let
                [input__60417_nth_1__
                 (clojure.core/nth input__60417 1)]
                (clojure.core/letfn
                 [(state__61872
                   []
                   (if
                    (clojure.core/seq? input__60417_nth_0__)
                    (clojure.core/let
                     [?sequence input__60417_nth_0__]
                     (clojure.core/let
                      [?env input__60417_nth_1__]
                      (try
                       [{:tag :seq,
                         :next
                         (clojure.core/let
                          [CATA_RESULT__13899__auto__
                           (CATA__FN__60479
                            ['meander.dev.parse.zeta/parse-sequential
                             (clojure.core/into [] ?sequence)
                             (clojure.core/let
                              [form__13998__auto__ {:context :seq}]
                              (clojure.core/merge
                               (clojure.core/into {} ?env)
                               form__13998__auto__))])]
                          (if
                           (meander.runtime.zeta/fail?
                            CATA_RESULT__13899__auto__)
                           (throw (meander.runtime.zeta/fail))
                           (clojure.core/nth
                            CATA_RESULT__13899__auto__
                            0))),
                         :form ?sequence}]
                       (catch
                        java.lang.Exception
                        e__14839__auto__
                        (if
                         (meander.runtime.zeta/fail? e__14839__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__14839__auto__))))))
                    (state__61873)))
                  (state__61873
                   []
                   (if
                    (clojure.core/map? input__60417_nth_0__)
                    (clojure.core/let
                     [?map input__60417_nth_0__]
                     (clojure.core/let
                      [?env input__60417_nth_1__]
                      (try
                       [{:tag :map,
                         :next
                         (clojure.core/let
                          [CATA_RESULT__13899__auto__
                           (CATA__FN__60479
                            ['meander.dev.parse.zeta/parse-entries
                             ?map
                             ?env])]
                          (if
                           (meander.runtime.zeta/fail?
                            CATA_RESULT__13899__auto__)
                           (throw (meander.runtime.zeta/fail))
                           (clojure.core/nth
                            CATA_RESULT__13899__auto__
                            0))),
                         :form ?map}]
                       (catch
                        java.lang.Exception
                        e__14839__auto__
                        (if
                         (meander.runtime.zeta/fail? e__14839__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__14839__auto__))))))
                    (state__61868)))]
                 (state__61872))))
              (state__61868
               []
               (if
                (clojure.core/symbol? input__60417_nth_0__)
                (clojure.core/let
                 [X__61591
                  (clojure.core/namespace input__60417_nth_0__)]
                 (clojure.core/let
                  [X__61593 (clojure.core/name input__60417_nth_0__)]
                  (if
                   (clojure.core/string? X__61593)
                   (clojure.core/letfn
                    [(state__61874
                      []
                      (clojure.core/let
                       [ret__61594
                        (clojure.core/re-matches #"_.*" X__61593)]
                       (if
                        (clojure.core/some? ret__61594)
                        (clojure.core/let
                         [?name ret__61594]
                         (clojure.core/let
                          [?symbol input__60417_nth_0__]
                          (try
                           [{:tag :wildcard,
                             :name ?name,
                             :form ?symbol,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__14839__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__14839__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__14839__auto__))))))
                        (state__61875))))
                     (state__61875
                      []
                      (clojure.core/let
                       [ret__61601
                        (clojure.core/re-matches #".+#" X__61593)]
                       (if
                        (clojure.core/some? ret__61601)
                        (clojure.core/let
                         [?name ret__61601]
                         (clojure.core/let
                          [?symbol input__60417_nth_0__]
                          (try
                           [{:tag :random-symbol,
                             :name ?name,
                             :form ?symbol,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__14839__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__14839__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__14839__auto__))))))
                        (state__61876))))
                     (state__61876
                      []
                      (clojure.core/let
                       [ret__61608
                        (clojure.core/re-matches #"%.+" X__61593)]
                       (if
                        (clojure.core/some? ret__61608)
                        (clojure.core/let
                         [?name ret__61608]
                         (clojure.core/let
                          [?symbol input__60417_nth_0__]
                          (try
                           [{:tag :reference,
                             :name ?name,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__14839__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__14839__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__14839__auto__))))))
                        (state__61877))))
                     (state__61877
                      []
                      (clojure.core/let
                       [ret__61615
                        (clojure.core/re-matches #"\*.+" X__61593)]
                       (if
                        (clojure.core/some? ret__61615)
                        (clojure.core/let
                         [?name ret__61615]
                         (clojure.core/let
                          [?symbol input__60417_nth_0__]
                          (try
                           [{:tag :mutable-variable,
                             :name ?name,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__14839__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__14839__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__14839__auto__))))))
                        (state__61878))))
                     (state__61878
                      []
                      (clojure.core/let
                       [ret__61622
                        (clojure.core/re-matches #"\!.+" X__61593)]
                       (if
                        (clojure.core/some? ret__61622)
                        (clojure.core/let
                         [?name ret__61622]
                         (clojure.core/let
                          [?symbol input__60417_nth_0__]
                          (try
                           [{:tag :memory-variable,
                             :name ?name,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__14839__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__14839__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__14839__auto__))))))
                        (state__61879))))
                     (state__61879
                      []
                      (clojure.core/let
                       [ret__61629
                        (clojure.core/re-matches #"\?.+" X__61593)]
                       (if
                        (clojure.core/some? ret__61629)
                        (clojure.core/let
                         [?name ret__61629]
                         (clojure.core/let
                          [?symbol input__60417_nth_0__]
                          (try
                           [{:tag :logic-variable,
                             :name ?name,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__14839__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__14839__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__14839__auto__))))))
                        (state__61869))))]
                    (state__61874))
                   (state__61869))))
                (state__61869)))
              (state__61869
               []
               (if
                (string? input__60417_nth_0__)
                (clojure.core/let
                 [?x input__60417_nth_0__]
                 (try
                  [{:tag :literal, :type :string, :form ?x}]
                  (catch
                   java.lang.Exception
                   e__14839__auto__
                   (if
                    (meander.runtime.zeta/fail? e__14839__auto__)
                    (meander.runtime.zeta/fail)
                    (throw e__14839__auto__)))))
                (state__61870)))
              (state__61870
               []
               (if
                (char? input__60417_nth_0__)
                (clojure.core/let
                 [?x input__60417_nth_0__]
                 (try
                  [{:tag :literal, :type :char, :form ?x}]
                  (catch
                   java.lang.Exception
                   e__14839__auto__
                   (if
                    (meander.runtime.zeta/fail? e__14839__auto__)
                    (meander.runtime.zeta/fail)
                    (throw e__14839__auto__)))))
                (state__61871)))
              (state__61871
               []
               (clojure.core/let
                [?x input__60417_nth_0__]
                (try
                 [{:tag :literal, :form ?x}]
                 (catch
                  java.lang.Exception
                  e__14839__auto__
                  (if
                   (meander.runtime.zeta/fail? e__14839__auto__)
                   (meander.runtime.zeta/fail)
                   (throw e__14839__auto__))))))]
             (state__61867)))
           (state__61672))
          (state__61672)))
        (state__61672
         []
         (clojure.core/let
          [?x input__60417]
          (try
           [{:tag :mistake, :x ?x}]
           (catch
            java.lang.Exception
            e__14839__auto__
            (if
             (meander.runtime.zeta/fail? e__14839__auto__)
             (meander.runtime.zeta/fail)
             (throw e__14839__auto__))))))]
       (state__61642)))]
    (clojure.core/let
     [x__12596__auto__ (CATA__FN__60479 input__60417)]
     (if
      (meander.runtime.zeta/fail? x__12596__auto__)
      (meander.runtime.zeta/fail)
      (clojure.core/let
       [[CATA_RETURN__60481] x__12596__auto__]
       CATA_RETURN__60481))))]
  (if
   (meander.runtime.zeta/fail? ret__12776__auto__)
   nil
   ret__12776__auto__)))
