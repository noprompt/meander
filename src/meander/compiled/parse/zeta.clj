(ns meander.compiled.parse.zeta (:require [meander.runtime.zeta]))
(clojure.core/defn
 parse
 [input__125886]
 (let*
  [ret__14518__auto__
   (clojure.core/letfn
    [(CATA__FN__125969
      [input__125886]
      (clojure.core/letfn
       [(state__127479
         []
         (if
          (clojure.core/vector? input__125886)
          (if
           (clojure.core/= (clojure.core/count input__125886) 3)
           (clojure.core/let
            [input__125886_nth_0__
             (clojure.core/nth input__125886 0)
             input__125886_nth_1__
             (clojure.core/nth input__125886 1)
             input__125886_nth_2__
             (clojure.core/nth input__125886 2)]
            (clojure.core/case
             input__125886_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__125886_nth_1__)
              (clojure.core/letfn
               [(state__127519
                 []
                 (clojure.core/case
                  input__125886_nth_1__
                  ([])
                  (clojure.core/let
                   [?env input__125886_nth_2__]
                   (try
                    [{:tag :empty}]
                    (catch
                     java.lang.Exception
                     e__16581__auto__
                     (if
                      (meander.runtime.zeta/fail? e__16581__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__16581__auto__)))))
                  (state__127520)))
                (state__127520
                 []
                 (clojure.core/let
                  [n__125978
                   (clojure.core/count input__125886_nth_1__)
                   m__125979
                   (clojure.core/max 0 (clojure.core/- n__125978 2))
                   input__125886_nth_1___l__
                   (clojure.core/subvec
                    input__125886_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__125886_nth_1__)
                     m__125979))
                   input__125886_nth_1___r__
                   (clojure.core/subvec
                    input__125886_nth_1__
                    m__125979)]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__125886_nth_1___r__)
                    2)
                   (clojure.core/let
                    [!xs (clojure.core/vec input__125886_nth_1___l__)]
                    (if
                     (clojure.core/=
                      (clojure.core/count input__125886_nth_1___r__)
                      2)
                     (clojure.core/let
                      [input__125886_nth_1___r___nth_0__
                       (clojure.core/nth input__125886_nth_1___r__ 0)
                       input__125886_nth_1___r___nth_1__
                       (clojure.core/nth input__125886_nth_1___r__ 1)]
                      (clojure.core/case
                       input__125886_nth_1___r___nth_0__
                       (:meander.zeta/as)
                       (clojure.core/let
                        [?pattern input__125886_nth_1___r___nth_1__]
                        (clojure.core/let
                         [?env input__125886_nth_2__]
                         (try
                          [(clojure.core/let
                            [!xs__counter
                             (meander.runtime.zeta/iterator !xs)]
                            {:tag :as,
                             :pattern
                             (clojure.core/let
                              [CATA_RESULT__15641__auto__
                               (CATA__FN__125969 [?pattern ?env])]
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
                               (CATA__FN__125969
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
                       (state__127480)))
                     (state__127480)))
                   (state__127480))))]
               (state__127519))
              (state__127480))
             (state__127480)))
           (state__127480))
          (state__127480)))
        (state__127480
         []
         (clojure.core/letfn
          [(def__125984
            [arg__126019 ?ns]
            (clojure.core/letfn
             [(state__127521
               []
               (clojure.core/let
                [x__126020 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__126020)
                 (clojure.core/let [?env arg__126019] [?env ?ns])
                 (state__127522))))
              (state__127522
               []
               (if
                (clojure.core/map? arg__126019)
                (clojure.core/let
                 [VAL__126021 (.valAt arg__126019 :aliases)]
                 (if
                  (clojure.core/map? VAL__126021)
                  (clojure.core/let
                   [X__126023 (clojure.core/set VAL__126021)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__126023))
                    (clojure.core/loop
                     [search_space__127523
                      (clojure.core/seq X__126023)]
                     (if
                      (clojure.core/seq search_space__127523)
                      (clojure.core/let
                       [elem__126024
                        (clojure.core/first search_space__127523)
                        result__127524
                        (clojure.core/let
                         [elem__126024_nth_0__
                          (clojure.core/nth elem__126024 0)
                          elem__126024_nth_1__
                          (clojure.core/nth elem__126024 1)]
                         (if
                          (clojure.core/symbol? elem__126024_nth_0__)
                          (clojure.core/let
                           [X__126026
                            (clojure.core/name elem__126024_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__126026)
                            (if
                             (clojure.core/symbol?
                              elem__126024_nth_1__)
                             (clojure.core/let
                              [X__126028
                               (clojure.core/name
                                elem__126024_nth_1__)]
                              (clojure.core/case
                               X__126028
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__126019]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__127524)
                        (recur
                         (clojure.core/next search_space__127523))
                        result__127524))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__127521)))]
          (if
           (clojure.core/vector? input__125886)
           (if
            (clojure.core/= (clojure.core/count input__125886) 3)
            (clojure.core/let
             [input__125886_nth_0__
              (clojure.core/nth input__125886 0)
              input__125886_nth_1__
              (clojure.core/nth input__125886 1)
              input__125886_nth_2__
              (clojure.core/nth input__125886 2)]
             (clojure.core/case
              input__125886_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__125886_nth_1__)
               (clojure.core/loop
                [search_space__127526
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__125886_nth_1__)]
                (if
                 (clojure.core/seq search_space__127526)
                 (clojure.core/let
                  [input__125886_nth_1___parts__
                   (clojure.core/first search_space__127526)
                   result__127527
                   (clojure.core/let
                    [input__125886_nth_1___l__
                     (clojure.core/nth input__125886_nth_1___parts__ 0)
                     input__125886_nth_1___r__
                     (clojure.core/nth
                      input__125886_nth_1___parts__
                      1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__125886_nth_1___l__)]
                     (clojure.core/let
                      [input__125886_nth_1___r___l__
                       (clojure.core/subvec
                        input__125886_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__125886_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__125886_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__125886_nth_1___r___r__
                         (clojure.core/subvec
                          input__125886_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__125886_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__125886_nth_1___r___l__
                           0)
                          input__125886_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__125886_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__125886_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__125993
                            (clojure.core/namespace
                             input__125886_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__125993]
                            (clojure.core/let
                             [X__125995
                              (clojure.core/name
                               input__125886_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__125995)
                              (clojure.core/let
                               [ret__125996
                                (clojure.core/re-matches
                                 #"&(\d+)"
                                 X__125995)]
                               (if
                                (clojure.core/some? ret__125996)
                                (if
                                 (clojure.core/vector? ret__125996)
                                 (if
                                  (clojure.core/=
                                   (clojure.core/count ret__125996)
                                   2)
                                  (clojure.core/let
                                   [ret__125996_nth_1__
                                    (clojure.core/nth ret__125996 1)]
                                   (clojure.core/let
                                    [?n ret__125996_nth_1__]
                                    (clojure.core/let
                                     [?pattern
                                      input__125886_nth_1___r___l___nth_1__]
                                     (clojure.core/let
                                      [?rest
                                       input__125886_nth_1___r___r__]
                                      (clojure.core/let
                                       [x__14338__auto__
                                        (def__125984
                                         input__125886_nth_2__
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
                                              (CATA__FN__125969
                                               ['meander.dev.parse.zeta/make-join
                                                (clojure.core/let
                                                 [CATA_RESULT__15641__auto__
                                                  (CATA__FN__125969
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
                                                  (CATA__FN__125969
                                                   ['meander.dev.parse.zeta/make-join
                                                    {:tag :slice,
                                                     :size
                                                     (Integer. ?n),
                                                     :pattern
                                                     (clojure.core/let
                                                      [CATA_RESULT__15641__auto__
                                                       (CATA__FN__125969
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
                                                      (CATA__FN__125969
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
                   (meander.runtime.zeta/fail? result__127527)
                   (recur (clojure.core/next search_space__127526))
                   result__127527))
                 (state__127481)))
               (state__127481))
              (state__127481)))
            (state__127481))
           (state__127481))))
        (state__127481
         []
         (clojure.core/letfn
          [(def__126041
            [arg__126073 ?ns]
            (clojure.core/letfn
             [(state__127529
               []
               (clojure.core/let
                [x__126074 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__126074)
                 (clojure.core/let [?env arg__126073] [?env ?ns])
                 (state__127530))))
              (state__127530
               []
               (if
                (clojure.core/map? arg__126073)
                (clojure.core/let
                 [VAL__126075 (.valAt arg__126073 :aliases)]
                 (if
                  (clojure.core/map? VAL__126075)
                  (clojure.core/let
                   [X__126077 (clojure.core/set VAL__126075)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__126077))
                    (clojure.core/loop
                     [search_space__127531
                      (clojure.core/seq X__126077)]
                     (if
                      (clojure.core/seq search_space__127531)
                      (clojure.core/let
                       [elem__126078
                        (clojure.core/first search_space__127531)
                        result__127532
                        (clojure.core/let
                         [elem__126078_nth_0__
                          (clojure.core/nth elem__126078 0)
                          elem__126078_nth_1__
                          (clojure.core/nth elem__126078 1)]
                         (if
                          (clojure.core/symbol? elem__126078_nth_0__)
                          (clojure.core/let
                           [X__126080
                            (clojure.core/name elem__126078_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__126080)
                            (if
                             (clojure.core/symbol?
                              elem__126078_nth_1__)
                             (clojure.core/let
                              [X__126082
                               (clojure.core/name
                                elem__126078_nth_1__)]
                              (clojure.core/case
                               X__126082
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__126073]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__127532)
                        (recur
                         (clojure.core/next search_space__127531))
                        result__127532))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__127529)))]
          (if
           (clojure.core/vector? input__125886)
           (if
            (clojure.core/= (clojure.core/count input__125886) 3)
            (clojure.core/let
             [input__125886_nth_0__
              (clojure.core/nth input__125886 0)
              input__125886_nth_1__
              (clojure.core/nth input__125886 1)
              input__125886_nth_2__
              (clojure.core/nth input__125886 2)]
             (clojure.core/case
              input__125886_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__125886_nth_1__)
               (clojure.core/loop
                [search_space__127534
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__125886_nth_1__)]
                (if
                 (clojure.core/seq search_space__127534)
                 (clojure.core/let
                  [input__125886_nth_1___parts__
                   (clojure.core/first search_space__127534)
                   result__127535
                   (clojure.core/let
                    [input__125886_nth_1___l__
                     (clojure.core/nth input__125886_nth_1___parts__ 0)
                     input__125886_nth_1___r__
                     (clojure.core/nth
                      input__125886_nth_1___parts__
                      1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__125886_nth_1___l__)]
                     (clojure.core/let
                      [input__125886_nth_1___r___l__
                       (clojure.core/subvec
                        input__125886_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__125886_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__125886_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__125886_nth_1___r___r__
                         (clojure.core/subvec
                          input__125886_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__125886_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__125886_nth_1___r___l__
                           0)
                          input__125886_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__125886_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__125886_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__126050
                            (clojure.core/namespace
                             input__125886_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__126050]
                            (clojure.core/let
                             [X__126052
                              (clojure.core/name
                               input__125886_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__126052)
                              (if
                               (clojure.core/re-matches
                                #"&.*"
                                X__126052)
                               (clojure.core/let
                                [?pattern
                                 input__125886_nth_1___r___l___nth_1__]
                                (clojure.core/let
                                 [?rest input__125886_nth_1___r___r__]
                                 (clojure.core/let
                                  [x__14338__auto__
                                   (def__126041
                                    input__125886_nth_2__
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
                                         (CATA__FN__125969
                                          ['meander.dev.parse.zeta/make-join
                                           (clojure.core/let
                                            [CATA_RESULT__15641__auto__
                                             (CATA__FN__125969
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
                                             (CATA__FN__125969
                                              ['meander.dev.parse.zeta/make-join
                                               (clojure.core/let
                                                [CATA_RESULT__15641__auto__
                                                 (CATA__FN__125969
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
                                                 (CATA__FN__125969
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
                   (meander.runtime.zeta/fail? result__127535)
                   (recur (clojure.core/next search_space__127534))
                   result__127535))
                 (state__127482)))
               (state__127482))
              (state__127482)))
            (state__127482))
           (state__127482))))
        (state__127482
         []
         (if
          (clojure.core/vector? input__125886)
          (clojure.core/letfn
           [(state__127537
             []
             (if
              (clojure.core/= (clojure.core/count input__125886) 3)
              (clojure.core/let
               [input__125886_nth_0__
                (clojure.core/nth input__125886 0)
                input__125886_nth_1__
                (clojure.core/nth input__125886 1)
                input__125886_nth_2__
                (clojure.core/nth input__125886 2)]
               (clojure.core/case
                input__125886_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__125886_nth_1__)
                 (clojure.core/letfn
                  [(state__127540
                    []
                    (clojure.core/let
                     [n__126103
                      (clojure.core/count input__125886_nth_1__)
                      m__126104
                      (clojure.core/max 0 (clojure.core/- n__126103 2))
                      input__125886_nth_1___l__
                      (clojure.core/subvec
                       input__125886_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__125886_nth_1__)
                        m__126104))
                      input__125886_nth_1___r__
                      (clojure.core/subvec
                       input__125886_nth_1__
                       m__126104)]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__125886_nth_1___r__)
                       2)
                      (clojure.core/let
                       [!xs
                        (clojure.core/vec input__125886_nth_1___l__)]
                       (if
                        (clojure.core/=
                         (clojure.core/count input__125886_nth_1___r__)
                         2)
                        (clojure.core/let
                         [input__125886_nth_1___r___nth_0__
                          (clojure.core/nth
                           input__125886_nth_1___r__
                           0)
                          input__125886_nth_1___r___nth_1__
                          (clojure.core/nth
                           input__125886_nth_1___r__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__125886_nth_1___r___nth_0__)
                          (clojure.core/let
                           [X__126108
                            (clojure.core/namespace
                             input__125886_nth_1___r___nth_0__)]
                           (clojure.core/let
                            [?ns X__126108]
                            (clojure.core/let
                             [X__126110
                              (clojure.core/name
                               input__125886_nth_1___r___nth_0__)]
                             (if
                              (clojure.core/string? X__126110)
                              (clojure.core/let
                               [ret__126111
                                (clojure.core/re-matches
                                 #"&.*"
                                 X__126110)]
                               (if
                                (clojure.core/some? ret__126111)
                                (clojure.core/let
                                 [?name ret__126111]
                                 (clojure.core/let
                                  [?pattern
                                   input__125886_nth_1___r___nth_1__]
                                  (if
                                   (clojure.core/map?
                                    input__125886_nth_2__)
                                   (clojure.core/let
                                    [VAL__126095
                                     (.valAt
                                      input__125886_nth_2__
                                      :aliases)]
                                    (if
                                     (clojure.core/map? VAL__126095)
                                     (clojure.core/let
                                      [X__126097
                                       (clojure.core/set VAL__126095)]
                                      (if
                                       (clojure.core/<=
                                        1
                                        (clojure.core/count X__126097))
                                       (clojure.core/loop
                                        [search_space__127544
                                         (clojure.core/seq X__126097)]
                                        (if
                                         (clojure.core/seq
                                          search_space__127544)
                                         (clojure.core/let
                                          [elem__126098
                                           (clojure.core/first
                                            search_space__127544)
                                           result__127545
                                           (clojure.core/let
                                            [elem__126098_nth_0__
                                             (clojure.core/nth
                                              elem__126098
                                              0)
                                             elem__126098_nth_1__
                                             (clojure.core/nth
                                              elem__126098
                                              1)]
                                            (if
                                             (clojure.core/symbol?
                                              elem__126098_nth_0__)
                                             (clojure.core/let
                                              [X__126100
                                               (clojure.core/name
                                                elem__126098_nth_0__)]
                                              (if
                                               (clojure.core/=
                                                ?ns
                                                X__126100)
                                               (if
                                                (clojure.core/symbol?
                                                 elem__126098_nth_1__)
                                                (clojure.core/let
                                                 [X__126102
                                                  (clojure.core/name
                                                   elem__126098_nth_1__)]
                                                 (clojure.core/case
                                                  X__126102
                                                  ("meander.zeta")
                                                  (clojure.core/let
                                                   [?env
                                                    input__125886_nth_2__]
                                                   (try
                                                    [(clojure.core/let
                                                      [!xs__counter
                                                       (meander.runtime.zeta/iterator
                                                        !xs)]
                                                      (clojure.core/let
                                                       [CATA_RESULT__15641__auto__
                                                        (CATA__FN__125969
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
                                            result__127545)
                                           (recur
                                            (clojure.core/next
                                             search_space__127544))
                                           result__127545))
                                         (state__127541)))
                                       (state__127541)))
                                     (state__127541)))
                                   (state__127541))))
                                (state__127541)))
                              (state__127541)))))
                          (state__127541)))
                        (state__127541)))
                      (state__127541))))
                   (state__127541
                    []
                    (clojure.core/loop
                     [search_space__127547
                      (meander.runtime.zeta/epsilon-partitions
                       2
                       input__125886_nth_1__)]
                     (if
                      (clojure.core/seq search_space__127547)
                      (clojure.core/let
                       [input__125886_nth_1___parts__
                        (clojure.core/first search_space__127547)
                        result__127548
                        (clojure.core/let
                         [input__125886_nth_1___l__
                          (clojure.core/nth
                           input__125886_nth_1___parts__
                           0)
                          input__125886_nth_1___r__
                          (clojure.core/nth
                           input__125886_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs
                           (clojure.core/vec
                            input__125886_nth_1___l__)]
                          (clojure.core/let
                           [input__125886_nth_1___r___l__
                            (clojure.core/subvec
                             input__125886_nth_1___r__
                             0
                             (clojure.core/min
                              (clojure.core/count
                               input__125886_nth_1___r__)
                              1))]
                           (if
                            (clojure.core/=
                             (clojure.core/count
                              input__125886_nth_1___r___l__)
                             1)
                            (clojure.core/let
                             [input__125886_nth_1___r___r__
                              (clojure.core/subvec
                               input__125886_nth_1___r__
                               1)]
                             (if
                              (clojure.core/=
                               input__125886_nth_1___r___l__
                               ['.])
                              (clojure.core/let
                               [?rest input__125886_nth_1___r___r__]
                               (clojure.core/let
                                [?env input__125886_nth_2__]
                                (try
                                 [(clojure.core/let
                                   [!xs__counter
                                    (meander.runtime.zeta/iterator
                                     !xs)]
                                   (clojure.core/let
                                    [CATA_RESULT__15641__auto__
                                     (CATA__FN__125969
                                      ['meander.dev.parse.zeta/make-join
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__125969
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
                                         (CATA__FN__125969
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
                        (meander.runtime.zeta/fail? result__127548)
                        (recur
                         (clojure.core/next search_space__127547))
                        result__127548))
                      (state__127542))))
                   (state__127542
                    []
                    (clojure.core/let
                     [input__125886_nth_1___l__
                      (clojure.core/subvec
                       input__125886_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__125886_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__125886_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__125886_nth_1___r__
                        (clojure.core/subvec input__125886_nth_1__ 1)]
                       (if
                        (clojure.core/=
                         input__125886_nth_1___l__
                         ['...])
                        (clojure.core/let
                         [?rest input__125886_nth_1___r__]
                         (clojure.core/let
                          [?env input__125886_nth_2__]
                          (try
                           [(clojure.core/let
                             [CATA_RESULT__15641__auto__
                              (CATA__FN__125969
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
                        (state__127543)))
                      (state__127543))))
                   (state__127543
                    []
                    (clojure.core/loop
                     [search_space__127550
                      (meander.runtime.zeta/epsilon-partitions
                       2
                       input__125886_nth_1__)]
                     (if
                      (clojure.core/seq search_space__127550)
                      (clojure.core/let
                       [input__125886_nth_1___parts__
                        (clojure.core/first search_space__127550)
                        result__127551
                        (clojure.core/let
                         [input__125886_nth_1___l__
                          (clojure.core/nth
                           input__125886_nth_1___parts__
                           0)
                          input__125886_nth_1___r__
                          (clojure.core/nth
                           input__125886_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs []]
                          (clojure.core/let
                           [ret__14502__auto__
                            (meander.runtime.zeta/epsilon-run-star-1
                             input__125886_nth_1___l__
                             [!xs]
                             (clojure.core/fn
                              [[!xs] input__126128]
                              (clojure.core/let
                               [input__126128_nth_0__
                                (clojure.core/nth input__126128 0)]
                               (clojure.core/letfn
                                [(save__126129
                                  []
                                  (meander.runtime.zeta/fail))
                                 (f__127554
                                  []
                                  (clojure.core/let
                                   [!xs
                                    (clojure.core/conj
                                     !xs
                                     input__126128_nth_0__)]
                                   [!xs]))]
                                (if
                                 (clojure.core/symbol?
                                  input__126128_nth_0__)
                                 (clojure.core/let
                                  [X__126131
                                   (clojure.core/namespace
                                    input__126128_nth_0__)]
                                  (clojure.core/case
                                   X__126131
                                   (nil)
                                   (clojure.core/let
                                    [X__126133
                                     (clojure.core/name
                                      input__126128_nth_0__)]
                                    (if
                                     (clojure.core/string? X__126133)
                                     (if
                                      (clojure.core/re-matches
                                       #"\.\.(?:\.|\d+)"
                                       X__126133)
                                      (save__126129)
                                      (f__127554))
                                     (f__127554)))
                                   (f__127554)))
                                 (f__127554)))))
                             (clojure.core/fn
                              [[!xs]]
                              (clojure.core/let
                               [input__125886_nth_1___r___l__
                                (clojure.core/subvec
                                 input__125886_nth_1___r__
                                 0
                                 (clojure.core/min
                                  (clojure.core/count
                                   input__125886_nth_1___r__)
                                  1))]
                               (if
                                (clojure.core/=
                                 (clojure.core/count
                                  input__125886_nth_1___r___l__)
                                 1)
                                (clojure.core/let
                                 [input__125886_nth_1___r___r__
                                  (clojure.core/subvec
                                   input__125886_nth_1___r__
                                   1)]
                                 (if
                                  (clojure.core/=
                                   input__125886_nth_1___r___l__
                                   ['...])
                                  (clojure.core/let
                                   [?rest
                                    input__125886_nth_1___r___r__]
                                   (clojure.core/let
                                    [?env input__125886_nth_2__]
                                    (try
                                     [(clojure.core/let
                                       [!xs__counter
                                        (meander.runtime.zeta/iterator
                                         !xs)]
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__125969
                                          ['meander.dev.parse.zeta/make-star
                                           (clojure.core/let
                                            [CATA_RESULT__15641__auto__
                                             (CATA__FN__125969
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
                                             (CATA__FN__125969
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
                        (meander.runtime.zeta/fail? result__127551)
                        (recur
                         (clojure.core/next search_space__127550))
                        result__127551))
                      (state__127538))))]
                  (state__127540))
                 (state__127538))
                (state__127538)))
              (state__127538)))
            (state__127538
             []
             (if
              (clojure.core/= (clojure.core/count input__125886) 4)
              (clojure.core/let
               [input__125886_nth_0__
                (clojure.core/nth input__125886 0)
                input__125886_nth_1__
                (clojure.core/nth input__125886 1)
                input__125886_nth_2__
                (clojure.core/nth input__125886 2)]
               (clojure.core/letfn
                [(state__127555
                  []
                  (clojure.core/let
                   [input__125886_nth_3__
                    (clojure.core/nth input__125886 3)]
                   (clojure.core/case
                    input__125886_nth_0__
                    (meander.dev.parse.zeta/make-star)
                    (clojure.core/letfn
                     [(state__127557
                       []
                       (if
                        (clojure.core/map? input__125886_nth_1__)
                        (clojure.core/let
                         [VAL__126137
                          (.valAt input__125886_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__126137
                          (:cat)
                          (clojure.core/let
                           [VAL__126138
                            (.valAt input__125886_nth_1__ :sequence)]
                           (if
                            (clojure.core/vector? VAL__126138)
                            (if
                             (clojure.core/=
                              (clojure.core/count VAL__126138)
                              1)
                             (clojure.core/let
                              [VAL__126138_nth_0__
                               (clojure.core/nth VAL__126138 0)]
                              (if
                               (clojure.core/map? VAL__126138_nth_0__)
                               (clojure.core/let
                                [VAL__126143
                                 (.valAt VAL__126138_nth_0__ :tag)]
                                (clojure.core/case
                                 VAL__126143
                                 (:memory-variable)
                                 (clojure.core/let
                                  [?memory-variable
                                   VAL__126138_nth_0__]
                                  (clojure.core/let
                                   [VAL__126139
                                    (.valAt
                                     input__125886_nth_1__
                                     :next)]
                                   (if
                                    (clojure.core/map? VAL__126139)
                                    (clojure.core/let
                                     [VAL__126140
                                      (.valAt VAL__126139 :tag)]
                                     (clojure.core/case
                                      VAL__126140
                                      (:empty)
                                      (clojure.core/let
                                       [?next input__125886_nth_2__]
                                       (clojure.core/let
                                        [?env input__125886_nth_3__]
                                        (try
                                         [(clojure.core/let
                                           [CATA_RESULT__15641__auto__
                                            (CATA__FN__125969
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
                                      (state__127558)))
                                    (state__127558))))
                                 (state__127558)))
                               (state__127558)))
                             (state__127558))
                            (state__127558)))
                          (state__127558)))
                        (state__127558)))
                      (state__127558
                       []
                       (clojure.core/let
                        [?pattern input__125886_nth_1__]
                        (clojure.core/let
                         [?next input__125886_nth_2__]
                         (if
                          (clojure.core/map? input__125886_nth_3__)
                          (clojure.core/let
                           [VAL__126146
                            (.valAt input__125886_nth_3__ :context)]
                           (clojure.core/case
                            VAL__126146
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
                            (state__127556)))
                          (state__127556)))))]
                     (state__127557))
                    (state__127556))))
                 (state__127556
                  []
                  (clojure.core/case
                   input__125886_nth_0__
                   (meander.dev.parse.zeta/make-star)
                   (clojure.core/let
                    [?pattern input__125886_nth_1__]
                    (clojure.core/let
                     [?next input__125886_nth_2__]
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
                   (state__127539)))]
                (state__127555)))
              (state__127539)))
            (state__127539
             []
             (if
              (clojure.core/= (clojure.core/count input__125886) 3)
              (clojure.core/let
               [input__125886_nth_0__
                (clojure.core/nth input__125886 0)
                input__125886_nth_1__
                (clojure.core/nth input__125886 1)
                input__125886_nth_2__
                (clojure.core/nth input__125886 2)]
               (clojure.core/case
                input__125886_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__125886_nth_1__)
                 (clojure.core/let
                  [input__125886_nth_1___l__
                   (clojure.core/subvec
                    input__125886_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__125886_nth_1__)
                     1))]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__125886_nth_1___l__)
                    1)
                   (clojure.core/let
                    [input__125886_nth_1___r__
                     (clojure.core/subvec input__125886_nth_1__ 1)]
                    (clojure.core/let
                     [input__125886_nth_1___l___nth_0__
                      (clojure.core/nth input__125886_nth_1___l__ 0)]
                     (if
                      (clojure.core/symbol?
                       input__125886_nth_1___l___nth_0__)
                      (clojure.core/let
                       [X__126154
                        (clojure.core/namespace
                         input__125886_nth_1___l___nth_0__)]
                       (clojure.core/case
                        X__126154
                        (nil)
                        (clojure.core/let
                         [X__126156
                          (clojure.core/name
                           input__125886_nth_1___l___nth_0__)]
                         (if
                          (clojure.core/string? X__126156)
                          (clojure.core/let
                           [ret__126157
                            (clojure.core/re-matches
                             #"\.\.(\d+)"
                             X__126156)]
                           (if
                            (clojure.core/some? ret__126157)
                            (if
                             (clojure.core/vector? ret__126157)
                             (if
                              (clojure.core/=
                               (clojure.core/count ret__126157)
                               2)
                              (clojure.core/let
                               [ret__126157_nth_1__
                                (clojure.core/nth ret__126157 1)]
                               (clojure.core/let
                                [?n ret__126157_nth_1__]
                                (clojure.core/let
                                 [?operator
                                  input__125886_nth_1___l___nth_0__]
                                 (clojure.core/let
                                  [?rest input__125886_nth_1___r__]
                                  (clojure.core/let
                                   [?env input__125886_nth_2__]
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
                              (state__127483))
                             (state__127483))
                            (state__127483)))
                          (state__127483)))
                        (state__127483)))
                      (state__127483))))
                   (state__127483)))
                 (state__127483))
                (state__127483)))
              (state__127483)))]
           (state__127537))
          (state__127483)))
        (state__127483
         []
         (clojure.core/letfn
          [(def__126160
            [arg__126184]
            (clojure.core/letfn
             [(state__127559
               []
               (clojure.core/let
                [x__126185 :string-plus]
                (clojure.core/let
                 [?tag x__126185]
                 (if
                  (clojure.core/map? arg__126184)
                  (clojure.core/let
                   [VAL__126186 (.valAt arg__126184 :context)]
                   (clojure.core/case
                    VAL__126186
                    (:string)
                    (clojure.core/let [?env arg__126184] [?tag ?env])
                    (state__127560)))
                  (state__127560)))))
              (state__127560
               []
               (clojure.core/let
                [x__126187 :plus]
                (clojure.core/let
                 [?tag x__126187]
                 (clojure.core/let [?env arg__126184] [?tag ?env]))))]
             (state__127559)))]
          (if
           (clojure.core/vector? input__125886)
           (if
            (clojure.core/= (clojure.core/count input__125886) 3)
            (clojure.core/let
             [input__125886_nth_0__
              (clojure.core/nth input__125886 0)
              input__125886_nth_1__
              (clojure.core/nth input__125886 1)
              input__125886_nth_2__
              (clojure.core/nth input__125886 2)]
             (clojure.core/case
              input__125886_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__125886_nth_1__)
               (clojure.core/loop
                [search_space__127561
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__125886_nth_1__)]
                (if
                 (clojure.core/seq search_space__127561)
                 (clojure.core/let
                  [input__125886_nth_1___parts__
                   (clojure.core/first search_space__127561)
                   result__127562
                   (clojure.core/let
                    [input__125886_nth_1___l__
                     (clojure.core/nth input__125886_nth_1___parts__ 0)
                     input__125886_nth_1___r__
                     (clojure.core/nth
                      input__125886_nth_1___parts__
                      1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__14502__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__125886_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__126177]
                         (clojure.core/let
                          [input__126177_nth_0__
                           (clojure.core/nth input__126177 0)]
                          (clojure.core/letfn
                           [(save__126178
                             []
                             (meander.runtime.zeta/fail))
                            (f__127565
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__126177_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol?
                             input__126177_nth_0__)
                            (clojure.core/let
                             [X__126180
                              (clojure.core/namespace
                               input__126177_nth_0__)]
                             (clojure.core/case
                              X__126180
                              (nil)
                              (clojure.core/let
                               [X__126182
                                (clojure.core/name
                                 input__126177_nth_0__)]
                               (if
                                (clojure.core/string? X__126182)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__126182)
                                 (save__126178)
                                 (f__127565))
                                (f__127565)))
                              (f__127565)))
                            (f__127565)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__125886_nth_1___r___l__
                           (clojure.core/subvec
                            input__125886_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__125886_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__125886_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__125886_nth_1___r___r__
                             (clojure.core/subvec
                              input__125886_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__125886_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__125886_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__125886_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__126171
                                (clojure.core/namespace
                                 input__125886_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__126171
                                (nil)
                                (clojure.core/let
                                 [X__126173
                                  (clojure.core/name
                                   input__125886_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__126173)
                                  (clojure.core/let
                                   [ret__126174
                                    (clojure.core/re-matches
                                     #"\.\.(\d+)"
                                     X__126173)]
                                   (if
                                    (clojure.core/some? ret__126174)
                                    (if
                                     (clojure.core/vector? ret__126174)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__126174)
                                       2)
                                      (clojure.core/let
                                       [ret__126174_nth_1__
                                        (clojure.core/nth
                                         ret__126174
                                         1)]
                                       (clojure.core/let
                                        [?n ret__126174_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__125886_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__14338__auto__
                                           (def__126160
                                            input__125886_nth_2__)]
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
                                                  (CATA__FN__125969
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
                                                  (CATA__FN__125969
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
                   (meander.runtime.zeta/fail? result__127562)
                   (recur (clojure.core/next search_space__127561))
                   result__127562))
                 (state__127484)))
               (state__127484))
              (state__127484)))
            (state__127484))
           (state__127484))))
        (state__127484
         []
         (if
          (clojure.core/vector? input__125886)
          (if
           (clojure.core/= (clojure.core/count input__125886) 3)
           (clojure.core/let
            [input__125886_nth_0__
             (clojure.core/nth input__125886 0)
             input__125886_nth_1__
             (clojure.core/nth input__125886 1)
             input__125886_nth_2__
             (clojure.core/nth input__125886 2)]
            (clojure.core/case
             input__125886_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__125886_nth_1__)
              (clojure.core/let
               [input__125886_nth_1___l__
                (clojure.core/subvec
                 input__125886_nth_1__
                 0
                 (clojure.core/min
                  (clojure.core/count input__125886_nth_1__)
                  1))]
               (if
                (clojure.core/=
                 (clojure.core/count input__125886_nth_1___l__)
                 1)
                (clojure.core/let
                 [input__125886_nth_1___r__
                  (clojure.core/subvec input__125886_nth_1__ 1)]
                 (clojure.core/let
                  [input__125886_nth_1___l___nth_0__
                   (clojure.core/nth input__125886_nth_1___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__125886_nth_1___l___nth_0__)
                   (clojure.core/let
                    [X__126205
                     (clojure.core/namespace
                      input__125886_nth_1___l___nth_0__)]
                    (clojure.core/case
                     X__126205
                     (nil)
                     (clojure.core/let
                      [X__126207
                       (clojure.core/name
                        input__125886_nth_1___l___nth_0__)]
                      (if
                       (clojure.core/string? X__126207)
                       (clojure.core/let
                        [ret__126208
                         (clojure.core/re-matches
                          #"\.\.(\?.+)"
                          X__126207)]
                        (if
                         (clojure.core/some? ret__126208)
                         (if
                          (clojure.core/vector? ret__126208)
                          (if
                           (clojure.core/=
                            (clojure.core/count ret__126208)
                            2)
                           (clojure.core/let
                            [ret__126208_nth_1__
                             (clojure.core/nth ret__126208 1)]
                            (clojure.core/let
                             [?n ret__126208_nth_1__]
                             (clojure.core/let
                              [?operator
                               input__125886_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?rest input__125886_nth_1___r__]
                               (clojure.core/let
                                [?env input__125886_nth_2__]
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
                           (state__127485))
                          (state__127485))
                         (state__127485)))
                       (state__127485)))
                     (state__127485)))
                   (state__127485))))
                (state__127485)))
              (state__127485))
             (state__127485)))
           (state__127485))
          (state__127485)))
        (state__127485
         []
         (clojure.core/letfn
          [(def__126211
            [arg__126235]
            (clojure.core/letfn
             [(state__127566
               []
               (clojure.core/let
                [x__126236 :string-logical-plus]
                (clojure.core/let
                 [?tag x__126236]
                 (if
                  (clojure.core/map? arg__126235)
                  (clojure.core/let
                   [VAL__126237 (.valAt arg__126235 :context)]
                   (clojure.core/case
                    VAL__126237
                    (:string)
                    (clojure.core/let [?env arg__126235] [?tag ?env])
                    (state__127567)))
                  (state__127567)))))
              (state__127567
               []
               (clojure.core/let
                [x__126238 :logical-plus]
                (clojure.core/let
                 [?tag x__126238]
                 (clojure.core/let [?env arg__126235] [?tag ?env]))))]
             (state__127566)))]
          (if
           (clojure.core/vector? input__125886)
           (if
            (clojure.core/= (clojure.core/count input__125886) 3)
            (clojure.core/let
             [input__125886_nth_0__
              (clojure.core/nth input__125886 0)
              input__125886_nth_1__
              (clojure.core/nth input__125886 1)
              input__125886_nth_2__
              (clojure.core/nth input__125886 2)]
             (clojure.core/case
              input__125886_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__125886_nth_1__)
               (clojure.core/loop
                [search_space__127568
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__125886_nth_1__)]
                (if
                 (clojure.core/seq search_space__127568)
                 (clojure.core/let
                  [input__125886_nth_1___parts__
                   (clojure.core/first search_space__127568)
                   result__127569
                   (clojure.core/let
                    [input__125886_nth_1___l__
                     (clojure.core/nth input__125886_nth_1___parts__ 0)
                     input__125886_nth_1___r__
                     (clojure.core/nth
                      input__125886_nth_1___parts__
                      1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__14502__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__125886_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__126228]
                         (clojure.core/let
                          [input__126228_nth_0__
                           (clojure.core/nth input__126228 0)]
                          (clojure.core/letfn
                           [(save__126229
                             []
                             (meander.runtime.zeta/fail))
                            (f__127572
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__126228_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol?
                             input__126228_nth_0__)
                            (clojure.core/let
                             [X__126231
                              (clojure.core/namespace
                               input__126228_nth_0__)]
                             (clojure.core/case
                              X__126231
                              (nil)
                              (clojure.core/let
                               [X__126233
                                (clojure.core/name
                                 input__126228_nth_0__)]
                               (if
                                (clojure.core/string? X__126233)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__126233)
                                 (save__126229)
                                 (f__127572))
                                (f__127572)))
                              (f__127572)))
                            (f__127572)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__125886_nth_1___r___l__
                           (clojure.core/subvec
                            input__125886_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__125886_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__125886_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__125886_nth_1___r___r__
                             (clojure.core/subvec
                              input__125886_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__125886_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__125886_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__125886_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__126222
                                (clojure.core/namespace
                                 input__125886_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__126222
                                (nil)
                                (clojure.core/let
                                 [X__126224
                                  (clojure.core/name
                                   input__125886_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__126224)
                                  (clojure.core/let
                                   [ret__126225
                                    (clojure.core/re-matches
                                     #"\.\.(\?.+)"
                                     X__126224)]
                                   (if
                                    (clojure.core/some? ret__126225)
                                    (if
                                     (clojure.core/vector? ret__126225)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__126225)
                                       2)
                                      (clojure.core/let
                                       [ret__126225_nth_1__
                                        (clojure.core/nth
                                         ret__126225
                                         1)]
                                       (clojure.core/let
                                        [?n ret__126225_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__125886_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__14338__auto__
                                           (def__126211
                                            input__125886_nth_2__)]
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
                                                  (CATA__FN__125969
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
                                                  (CATA__FN__125969
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
                   (meander.runtime.zeta/fail? result__127569)
                   (recur (clojure.core/next search_space__127568))
                   result__127569))
                 (state__127486)))
               (state__127486))
              (state__127486)))
            (state__127486))
           (state__127486))))
        (state__127486
         []
         (if
          (clojure.core/vector? input__125886)
          (if
           (clojure.core/= (clojure.core/count input__125886) 3)
           (clojure.core/let
            [input__125886_nth_0__
             (clojure.core/nth input__125886 0)
             input__125886_nth_1__
             (clojure.core/nth input__125886 1)
             input__125886_nth_2__
             (clojure.core/nth input__125886 2)]
            (clojure.core/case
             input__125886_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__125886_nth_1__)
              (clojure.core/let
               [input__125886_nth_1___l__
                (clojure.core/subvec
                 input__125886_nth_1__
                 0
                 (clojure.core/min
                  (clojure.core/count input__125886_nth_1__)
                  1))]
               (if
                (clojure.core/=
                 (clojure.core/count input__125886_nth_1___l__)
                 1)
                (clojure.core/let
                 [input__125886_nth_1___r__
                  (clojure.core/subvec input__125886_nth_1__ 1)]
                 (clojure.core/let
                  [input__125886_nth_1___l___nth_0__
                   (clojure.core/nth input__125886_nth_1___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__125886_nth_1___l___nth_0__)
                   (clojure.core/let
                    [X__126256
                     (clojure.core/namespace
                      input__125886_nth_1___l___nth_0__)]
                    (clojure.core/case
                     X__126256
                     (nil)
                     (clojure.core/let
                      [X__126258
                       (clojure.core/name
                        input__125886_nth_1___l___nth_0__)]
                      (if
                       (clojure.core/string? X__126258)
                       (clojure.core/let
                        [ret__126259
                         (clojure.core/re-matches
                          #"\.\.(!.+)"
                          X__126258)]
                        (if
                         (clojure.core/some? ret__126259)
                         (if
                          (clojure.core/vector? ret__126259)
                          (if
                           (clojure.core/=
                            (clojure.core/count ret__126259)
                            2)
                           (clojure.core/let
                            [ret__126259_nth_1__
                             (clojure.core/nth ret__126259 1)]
                            (clojure.core/let
                             [?n ret__126259_nth_1__]
                             (clojure.core/let
                              [?operator
                               input__125886_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?rest input__125886_nth_1___r__]
                               (clojure.core/let
                                [?env input__125886_nth_2__]
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
                           (state__127487))
                          (state__127487))
                         (state__127487)))
                       (state__127487)))
                     (state__127487)))
                   (state__127487))))
                (state__127487)))
              (state__127487))
             (state__127487)))
           (state__127487))
          (state__127487)))
        (state__127487
         []
         (clojure.core/letfn
          [(def__126262
            [arg__126286]
            (clojure.core/letfn
             [(state__127573
               []
               (clojure.core/let
                [x__126287 :string-memory-plus]
                (clojure.core/let
                 [?tag x__126287]
                 (if
                  (clojure.core/map? arg__126286)
                  (clojure.core/let
                   [VAL__126288 (.valAt arg__126286 :context)]
                   (clojure.core/case
                    VAL__126288
                    (:string)
                    (clojure.core/let [?env arg__126286] [?tag ?env])
                    (state__127574)))
                  (state__127574)))))
              (state__127574
               []
               (clojure.core/let
                [x__126289 :memory-plus]
                (clojure.core/let
                 [?tag x__126289]
                 (clojure.core/let [?env arg__126286] [?tag ?env]))))]
             (state__127573)))]
          (if
           (clojure.core/vector? input__125886)
           (if
            (clojure.core/= (clojure.core/count input__125886) 3)
            (clojure.core/let
             [input__125886_nth_0__
              (clojure.core/nth input__125886 0)
              input__125886_nth_1__
              (clojure.core/nth input__125886 1)
              input__125886_nth_2__
              (clojure.core/nth input__125886 2)]
             (clojure.core/case
              input__125886_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__125886_nth_1__)
               (clojure.core/loop
                [search_space__127575
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__125886_nth_1__)]
                (if
                 (clojure.core/seq search_space__127575)
                 (clojure.core/let
                  [input__125886_nth_1___parts__
                   (clojure.core/first search_space__127575)
                   result__127576
                   (clojure.core/let
                    [input__125886_nth_1___l__
                     (clojure.core/nth input__125886_nth_1___parts__ 0)
                     input__125886_nth_1___r__
                     (clojure.core/nth
                      input__125886_nth_1___parts__
                      1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__14502__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__125886_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__126279]
                         (clojure.core/let
                          [input__126279_nth_0__
                           (clojure.core/nth input__126279 0)]
                          (clojure.core/letfn
                           [(save__126280
                             []
                             (meander.runtime.zeta/fail))
                            (f__127579
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__126279_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol?
                             input__126279_nth_0__)
                            (clojure.core/let
                             [X__126282
                              (clojure.core/namespace
                               input__126279_nth_0__)]
                             (clojure.core/case
                              X__126282
                              (nil)
                              (clojure.core/let
                               [X__126284
                                (clojure.core/name
                                 input__126279_nth_0__)]
                               (if
                                (clojure.core/string? X__126284)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__126284)
                                 (save__126280)
                                 (f__127579))
                                (f__127579)))
                              (f__127579)))
                            (f__127579)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__125886_nth_1___r___l__
                           (clojure.core/subvec
                            input__125886_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__125886_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__125886_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__125886_nth_1___r___r__
                             (clojure.core/subvec
                              input__125886_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__125886_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__125886_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__125886_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__126273
                                (clojure.core/namespace
                                 input__125886_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__126273
                                (nil)
                                (clojure.core/let
                                 [X__126275
                                  (clojure.core/name
                                   input__125886_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__126275)
                                  (clojure.core/let
                                   [ret__126276
                                    (clojure.core/re-matches
                                     #"\.\.(\!.+)"
                                     X__126275)]
                                   (if
                                    (clojure.core/some? ret__126276)
                                    (if
                                     (clojure.core/vector? ret__126276)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__126276)
                                       2)
                                      (clojure.core/let
                                       [ret__126276_nth_1__
                                        (clojure.core/nth
                                         ret__126276
                                         1)]
                                       (clojure.core/let
                                        [?n ret__126276_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__125886_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__14338__auto__
                                           (def__126262
                                            input__125886_nth_2__)]
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
                                                  (CATA__FN__125969
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
                                                  (CATA__FN__125969
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
                   (meander.runtime.zeta/fail? result__127576)
                   (recur (clojure.core/next search_space__127575))
                   result__127576))
                 (state__127488)))
               (state__127488))
              (state__127488)))
            (state__127488))
           (state__127488))))
        (state__127488
         []
         (if
          (clojure.core/vector? input__125886)
          (clojure.core/letfn
           [(state__127580
             []
             (if
              (clojure.core/= (clojure.core/count input__125886) 3)
              (clojure.core/let
               [input__125886_nth_0__
                (clojure.core/nth input__125886 0)
                input__125886_nth_1__
                (clojure.core/nth input__125886 1)
                input__125886_nth_2__
                (clojure.core/nth input__125886 2)]
               (clojure.core/case
                input__125886_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__125886_nth_1__)
                 (clojure.core/let
                  [!xs (clojure.core/vec input__125886_nth_1__)]
                  (clojure.core/let
                   [?env input__125886_nth_2__]
                   (try
                    [(clojure.core/let
                      [!xs__counter
                       (meander.runtime.zeta/iterator !xs)]
                      (clojure.core/let
                       [CATA_RESULT__15641__auto__
                        (CATA__FN__125969
                         ['meander.dev.parse.zeta/make-cat
                          (clojure.core/into
                           []
                           (clojure.core/loop
                            [return__125970
                             (clojure.core/transient [])]
                            (if
                             (clojure.core/and (.hasNext !xs__counter))
                             (recur
                              (clojure.core/conj!
                               return__125970
                               (clojure.core/let
                                [CATA_RESULT__15641__auto__
                                 (CATA__FN__125969
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
                              return__125970))))
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
                 (state__127581))
                (state__127581)))
              (state__127581)))
            (state__127581
             []
             (if
              (clojure.core/= (clojure.core/count input__125886) 4)
              (clojure.core/let
               [input__125886_nth_0__
                (clojure.core/nth input__125886 0)
                input__125886_nth_1__
                (clojure.core/nth input__125886 1)
                input__125886_nth_2__
                (clojure.core/nth input__125886 2)]
               (clojure.core/letfn
                [(state__127583
                  []
                  (clojure.core/let
                   [input__125886_nth_3__
                    (clojure.core/nth input__125886 3)]
                   (clojure.core/case
                    input__125886_nth_0__
                    (meander.dev.parse.zeta/make-cat)
                    (if
                     (clojure.core/vector? input__125886_nth_1__)
                     (clojure.core/letfn
                      [(state__127590
                        []
                        (clojure.core/case
                         input__125886_nth_1__
                         ([])
                         (clojure.core/let
                          [?next input__125886_nth_2__]
                          (clojure.core/let
                           [?env input__125886_nth_3__]
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
                         (state__127591)))
                       (state__127591
                        []
                        (clojure.core/loop
                         [search_space__127592
                          (meander.runtime.zeta/epsilon-partitions
                           2
                           input__125886_nth_1__)]
                         (if
                          (clojure.core/seq search_space__127592)
                          (clojure.core/let
                           [input__125886_nth_1___parts__
                            (clojure.core/first search_space__127592)
                            result__127593
                            (clojure.core/let
                             [input__125886_nth_1___l__
                              (clojure.core/nth
                               input__125886_nth_1___parts__
                               0)
                              input__125886_nth_1___r__
                              (clojure.core/nth
                               input__125886_nth_1___parts__
                               1)]
                             (clojure.core/letfn
                              [(state__127595
                                []
                                (clojure.core/let
                                 [!xs []]
                                 (clojure.core/let
                                  [ret__14502__auto__
                                   (meander.runtime.zeta/epsilon-run-star-1
                                    input__125886_nth_1___l__
                                    [!xs]
                                    (clojure.core/fn
                                     [[!xs] input__126315]
                                     (clojure.core/let
                                      [input__126315_nth_0__
                                       (clojure.core/nth
                                        input__126315
                                        0)]
                                      (clojure.core/letfn
                                       [(save__126316
                                         []
                                         (meander.runtime.zeta/fail))
                                        (f__127599
                                         []
                                         (clojure.core/let
                                          [!xs
                                           (clojure.core/conj
                                            !xs
                                            input__126315_nth_0__)]
                                          [!xs]))]
                                       (if
                                        (clojure.core/map?
                                         input__126315_nth_0__)
                                        (clojure.core/let
                                         [VAL__126317
                                          (.valAt
                                           input__126315_nth_0__
                                           :tag)]
                                         (clojure.core/case
                                          VAL__126317
                                          (:group)
                                          (save__126316)
                                          (f__127599)))
                                        (f__127599)))))
                                    (clojure.core/fn
                                     [[!xs]]
                                     (clojure.core/let
                                      [input__125886_nth_1___r___l__
                                       (clojure.core/subvec
                                        input__125886_nth_1___r__
                                        0
                                        (clojure.core/min
                                         (clojure.core/count
                                          input__125886_nth_1___r__)
                                         1))]
                                      (if
                                       (clojure.core/=
                                        (clojure.core/count
                                         input__125886_nth_1___r___l__)
                                        1)
                                       (clojure.core/let
                                        [input__125886_nth_1___r___r__
                                         (clojure.core/subvec
                                          input__125886_nth_1___r__
                                          1)]
                                        (clojure.core/let
                                         [input__125886_nth_1___r___l___nth_0__
                                          (clojure.core/nth
                                           input__125886_nth_1___r___l__
                                           0)]
                                         (if
                                          (clojure.core/map?
                                           input__125886_nth_1___r___l___nth_0__)
                                          (clojure.core/let
                                           [VAL__126314
                                            (.valAt
                                             input__125886_nth_1___r___l___nth_0__
                                             :tag)]
                                           (clojure.core/case
                                            VAL__126314
                                            (:group)
                                            (clojure.core/let
                                             [?group
                                              input__125886_nth_1___r___l___nth_0__]
                                             (clojure.core/let
                                              [?rest
                                               input__125886_nth_1___r___r__]
                                              (clojure.core/let
                                               [?next
                                                input__125886_nth_2__]
                                               (clojure.core/let
                                                [?env
                                                 input__125886_nth_3__]
                                                (try
                                                 [(clojure.core/let
                                                   [!xs__counter
                                                    (meander.runtime.zeta/iterator
                                                     !xs)]
                                                   (clojure.core/let
                                                    [CATA_RESULT__15641__auto__
                                                     (CATA__FN__125969
                                                      ['meander.dev.parse.zeta/make-join
                                                       (clojure.core/let
                                                        [CATA_RESULT__15641__auto__
                                                         (CATA__FN__125969
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
                                                         (CATA__FN__125969
                                                          ['meander.dev.parse.zeta/make-join
                                                           ?group
                                                           (clojure.core/let
                                                            [CATA_RESULT__15641__auto__
                                                             (CATA__FN__125969
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
                                            (state__127596)))
                                          (state__127596))))
                                       (state__127596)))))]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    ret__14502__auto__)
                                   (state__127596)
                                   ret__14502__auto__))))
                               (state__127596
                                []
                                (clojure.core/let
                                 [!xs []]
                                 (clojure.core/let
                                  [ret__14502__auto__
                                   (meander.runtime.zeta/epsilon-run-star-1
                                    input__125886_nth_1___l__
                                    [!xs]
                                    (clojure.core/fn
                                     [[!xs] input__126326]
                                     (clojure.core/let
                                      [input__126326_nth_0__
                                       (clojure.core/nth
                                        input__126326
                                        0)]
                                      (clojure.core/letfn
                                       [(save__126327
                                         []
                                         (meander.runtime.zeta/fail))
                                        (f__127601
                                         []
                                         (clojure.core/let
                                          [!xs
                                           (clojure.core/conj
                                            !xs
                                            input__126326_nth_0__)]
                                          [!xs]))]
                                       (if
                                        (clojure.core/map?
                                         input__126326_nth_0__)
                                        (clojure.core/let
                                         [VAL__126328
                                          (.valAt
                                           input__126326_nth_0__
                                           :tag)]
                                         (clojure.core/case
                                          VAL__126328
                                          (:star)
                                          (save__126327)
                                          (f__127601)))
                                        (f__127601)))))
                                    (clojure.core/fn
                                     [[!xs]]
                                     (clojure.core/let
                                      [input__125886_nth_1___r___l__
                                       (clojure.core/subvec
                                        input__125886_nth_1___r__
                                        0
                                        (clojure.core/min
                                         (clojure.core/count
                                          input__125886_nth_1___r__)
                                         1))]
                                      (if
                                       (clojure.core/=
                                        (clojure.core/count
                                         input__125886_nth_1___r___l__)
                                        1)
                                       (clojure.core/let
                                        [input__125886_nth_1___r___r__
                                         (clojure.core/subvec
                                          input__125886_nth_1___r__
                                          1)]
                                        (clojure.core/let
                                         [input__125886_nth_1___r___l___nth_0__
                                          (clojure.core/nth
                                           input__125886_nth_1___r___l__
                                           0)]
                                         (if
                                          (clojure.core/map?
                                           input__125886_nth_1___r___l___nth_0__)
                                          (clojure.core/let
                                           [VAL__126325
                                            (.valAt
                                             input__125886_nth_1___r___l___nth_0__
                                             :tag)]
                                           (clojure.core/case
                                            VAL__126325
                                            (:star)
                                            (clojure.core/let
                                             [?star
                                              input__125886_nth_1___r___l___nth_0__]
                                             (clojure.core/let
                                              [?rest
                                               input__125886_nth_1___r___r__]
                                              (clojure.core/let
                                               [?next
                                                input__125886_nth_2__]
                                               (clojure.core/let
                                                [?env
                                                 input__125886_nth_3__]
                                                (try
                                                 [(clojure.core/let
                                                   [!xs__counter
                                                    (meander.runtime.zeta/iterator
                                                     !xs)]
                                                   (clojure.core/let
                                                    [CATA_RESULT__15641__auto__
                                                     (CATA__FN__125969
                                                      ['meander.dev.parse.zeta/make-join
                                                       (clojure.core/let
                                                        [CATA_RESULT__15641__auto__
                                                         (CATA__FN__125969
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
                                                         (CATA__FN__125969
                                                          ['meander.dev.parse.zeta/make-join
                                                           ?star
                                                           (clojure.core/let
                                                            [CATA_RESULT__15641__auto__
                                                             (CATA__FN__125969
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
                                            (state__127597)))
                                          (state__127597))))
                                       (state__127597)))))]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    ret__14502__auto__)
                                   (state__127597)
                                   ret__14502__auto__))))
                               (state__127597
                                []
                                (clojure.core/let
                                 [input__125886_nth_1___l___l__
                                  (clojure.core/subvec
                                   input__125886_nth_1___l__
                                   0
                                   (clojure.core/min
                                    (clojure.core/count
                                     input__125886_nth_1___l__)
                                    1))]
                                 (if
                                  (clojure.core/=
                                   (clojure.core/count
                                    input__125886_nth_1___l___l__)
                                   1)
                                  (clojure.core/let
                                   [input__125886_nth_1___l___r__
                                    (clojure.core/subvec
                                     input__125886_nth_1___l__
                                     1)]
                                   (clojure.core/let
                                    [input__125886_nth_1___l___l___nth_0__
                                     (clojure.core/nth
                                      input__125886_nth_1___l___l__
                                      0)]
                                    (clojure.core/letfn
                                     [(save__126336
                                       []
                                       (meander.runtime.zeta/fail))
                                      (f__127602
                                       []
                                       (clojure.core/let
                                        [!xs []]
                                        (clojure.core/let
                                         [!xs
                                          (clojure.core/conj
                                           !xs
                                           input__125886_nth_1___l___l___nth_0__)]
                                         (clojure.core/loop
                                          [i__14475__auto__
                                           0
                                           coll__127603
                                           input__125886_nth_1___l___r__
                                           [!xs]
                                           [!xs]]
                                          (clojure.core/let
                                           [input__126341
                                            (clojure.core/subvec
                                             coll__127603
                                             0
                                             (clojure.core/min
                                              (clojure.core/count
                                               coll__127603)
                                              1))]
                                           (if
                                            (clojure.core/=
                                             (clojure.core/count
                                              input__126341)
                                             1)
                                            (clojure.core/let
                                             [result__14476__auto__
                                              (clojure.core/let
                                               [input__126341_nth_0__
                                                (clojure.core/nth
                                                 input__126341
                                                 0)]
                                               (clojure.core/letfn
                                                [(save__126342
                                                  []
                                                  (meander.runtime.zeta/fail))
                                                 (f__127604
                                                  []
                                                  (clojure.core/let
                                                   [!xs
                                                    (clojure.core/conj
                                                     !xs
                                                     input__126341_nth_0__)]
                                                   [!xs]))]
                                                (if
                                                 (clojure.core/map?
                                                  input__126341_nth_0__)
                                                 (clojure.core/let
                                                  [VAL__126343
                                                   (.valAt
                                                    input__126341_nth_0__
                                                    :tag)]
                                                  (clojure.core/case
                                                   VAL__126343
                                                   (:reference)
                                                   (save__126342)
                                                   (f__127604)))
                                                 (f__127604))))]
                                             (if
                                              (meander.runtime.zeta/fail?
                                               result__14476__auto__)
                                              (meander.runtime.zeta/fail)
                                              (recur
                                               (clojure.core/inc
                                                i__14475__auto__)
                                               (clojure.core/subvec
                                                coll__127603
                                                1)
                                               result__14476__auto__)))
                                            (if
                                             (clojure.core/or
                                              (clojure.core/seq
                                               coll__127603)
                                              (clojure.core/<
                                               i__14475__auto__
                                               0))
                                             (meander.runtime.zeta/fail)
                                             (clojure.core/let
                                              [input__125886_nth_1___r___l__
                                               (clojure.core/subvec
                                                input__125886_nth_1___r__
                                                0
                                                (clojure.core/min
                                                 (clojure.core/count
                                                  input__125886_nth_1___r__)
                                                 1))]
                                              (if
                                               (clojure.core/=
                                                (clojure.core/count
                                                 input__125886_nth_1___r___l__)
                                                1)
                                               (clojure.core/let
                                                [input__125886_nth_1___r___r__
                                                 (clojure.core/subvec
                                                  input__125886_nth_1___r__
                                                  1)]
                                                (clojure.core/let
                                                 [input__125886_nth_1___r___l___nth_0__
                                                  (clojure.core/nth
                                                   input__125886_nth_1___r___l__
                                                   0)]
                                                 (if
                                                  (clojure.core/map?
                                                   input__125886_nth_1___r___l___nth_0__)
                                                  (clojure.core/let
                                                   [VAL__126340
                                                    (.valAt
                                                     input__125886_nth_1___r___l___nth_0__
                                                     :tag)]
                                                   (clojure.core/case
                                                    VAL__126340
                                                    (:reference)
                                                    (clojure.core/let
                                                     [?reference
                                                      input__125886_nth_1___r___l___nth_0__]
                                                     (clojure.core/let
                                                      [?rest
                                                       input__125886_nth_1___r___r__]
                                                      (clojure.core/let
                                                       [?next
                                                        input__125886_nth_2__]
                                                       (clojure.core/let
                                                        [?env
                                                         input__125886_nth_3__]
                                                        (try
                                                         [(clojure.core/let
                                                           [!xs__counter
                                                            (meander.runtime.zeta/iterator
                                                             !xs)]
                                                           (clojure.core/let
                                                            [CATA_RESULT__15641__auto__
                                                             (CATA__FN__125969
                                                              ['meander.dev.parse.zeta/make-join
                                                               (clojure.core/let
                                                                [CATA_RESULT__15641__auto__
                                                                 (CATA__FN__125969
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
                                                                 (CATA__FN__125969
                                                                  ['meander.dev.parse.zeta/make-join
                                                                   (clojure.core/let
                                                                    [CATA_RESULT__15641__auto__
                                                                     (CATA__FN__125969
                                                                      ['meander.dev.parse.zeta/make-cat
                                                                       [?reference]
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
                                                                     (CATA__FN__125969
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
                                               (meander.runtime.zeta/fail))))))))))]
                                     (if
                                      (clojure.core/map?
                                       input__125886_nth_1___l___l___nth_0__)
                                      (clojure.core/let
                                       [VAL__126337
                                        (.valAt
                                         input__125886_nth_1___l___l___nth_0__
                                         :tag)]
                                       (clojure.core/case
                                        VAL__126337
                                        (:reference)
                                        (save__126336)
                                        (f__127602)))
                                      (f__127602)))))
                                  (meander.runtime.zeta/fail))))]
                              (state__127595)))]
                           (if
                            (meander.runtime.zeta/fail? result__127593)
                            (recur
                             (clojure.core/next search_space__127592))
                            result__127593))
                          (state__127584))))]
                      (state__127590))
                     (state__127584))
                    (state__127584))))
                 (state__127584
                  []
                  (clojure.core/case
                   input__125886_nth_0__
                   (meander.dev.parse.zeta/make-cat)
                   (if
                    (clojure.core/vector? input__125886_nth_1__)
                    (clojure.core/let
                     [input__125886_nth_1___l__
                      (clojure.core/subvec
                       input__125886_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__125886_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__125886_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__125886_nth_1___r__
                        (clojure.core/subvec input__125886_nth_1__ 1)]
                       (clojure.core/let
                        [input__125886_nth_1___l___nth_0__
                         (clojure.core/nth
                          input__125886_nth_1___l__
                          0)]
                        (if
                         (clojure.core/map?
                          input__125886_nth_1___l___nth_0__)
                         (clojure.core/let
                          [VAL__126352
                           (.valAt
                            input__125886_nth_1___l___nth_0__
                            :tag)]
                          (clojure.core/case
                           VAL__126352
                           (:literal)
                           (clojure.core/let
                            [VAL__126353
                             (.valAt
                              input__125886_nth_1___l___nth_0__
                              :type)]
                            (if
                             (clojure.core/let
                              [x__13398__auto__ VAL__126353]
                              (clojure.core/case
                               x__13398__auto__
                               (:string :char)
                               true
                               false))
                             (clojure.core/let
                              [VAL__126354
                               (.valAt
                                input__125886_nth_1___l___nth_0__
                                :form)]
                              (clojure.core/let
                               [!forms []]
                               (clojure.core/let
                                [!forms
                                 (clojure.core/conj
                                  !forms
                                  VAL__126354)]
                                (clojure.core/loop
                                 [i__14475__auto__
                                  0
                                  coll__127605
                                  input__125886_nth_1___r__
                                  [!forms]
                                  [!forms]]
                                 (clojure.core/let
                                  [input__126355
                                   (clojure.core/subvec
                                    coll__127605
                                    0
                                    (clojure.core/min
                                     (clojure.core/count coll__127605)
                                     1))]
                                  (if
                                   (clojure.core/=
                                    (clojure.core/count input__126355)
                                    1)
                                   (clojure.core/let
                                    [result__14476__auto__
                                     (clojure.core/let
                                      [input__126355_nth_0__
                                       (clojure.core/nth
                                        input__126355
                                        0)]
                                      (if
                                       (clojure.core/map?
                                        input__126355_nth_0__)
                                       (clojure.core/let
                                        [VAL__126356
                                         (.valAt
                                          input__126355_nth_0__
                                          :tag)]
                                        (clojure.core/case
                                         VAL__126356
                                         (:literal)
                                         (clojure.core/let
                                          [VAL__126357
                                           (.valAt
                                            input__126355_nth_0__
                                            :type)]
                                          (if
                                           (clojure.core/let
                                            [x__13398__auto__
                                             VAL__126357]
                                            (clojure.core/case
                                             x__13398__auto__
                                             (:string :char)
                                             true
                                             false))
                                           (clojure.core/let
                                            [VAL__126358
                                             (.valAt
                                              input__126355_nth_0__
                                              :form)]
                                            (clojure.core/let
                                             [!forms
                                              (clojure.core/conj
                                               !forms
                                               VAL__126358)]
                                             [!forms]))
                                           (meander.runtime.zeta/fail)))
                                         (meander.runtime.zeta/fail)))
                                       (meander.runtime.zeta/fail)))]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      result__14476__auto__)
                                     (state__127585)
                                     (recur
                                      (clojure.core/inc
                                       i__14475__auto__)
                                      (clojure.core/subvec
                                       coll__127605
                                       1)
                                      result__14476__auto__)))
                                   (if
                                    (clojure.core/or
                                     (clojure.core/seq coll__127605)
                                     (clojure.core/<
                                      i__14475__auto__
                                      0))
                                    (state__127585)
                                    (if
                                     (clojure.core/map?
                                      input__125886_nth_2__)
                                     (clojure.core/let
                                      [VAL__126347
                                       (.valAt
                                        input__125886_nth_2__
                                        :tag)]
                                      (clojure.core/case
                                       VAL__126347
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
                                       (state__127585)))
                                     (state__127585)))))))))
                             (state__127585)))
                           (state__127585)))
                         (state__127585))))
                      (state__127585)))
                    (state__127585))
                   (state__127585)))
                 (state__127585
                  []
                  (clojure.core/let
                   [input__125886_nth_3__
                    (clojure.core/nth input__125886 3)]
                   (clojure.core/case
                    input__125886_nth_0__
                    (meander.dev.parse.zeta/make-cat)
                    (clojure.core/letfn
                     [(state__127606
                       []
                       (if
                        (clojure.core/vector? input__125886_nth_1__)
                        (clojure.core/let
                         [input__125886_nth_1___l__
                          (clojure.core/subvec
                           input__125886_nth_1__
                           0
                           (clojure.core/min
                            (clojure.core/count input__125886_nth_1__)
                            1))]
                         (if
                          (clojure.core/=
                           (clojure.core/count
                            input__125886_nth_1___l__)
                           1)
                          (clojure.core/let
                           [input__125886_nth_1___r__
                            (clojure.core/subvec
                             input__125886_nth_1__
                             1)]
                           (clojure.core/let
                            [input__125886_nth_1___l___nth_0__
                             (clojure.core/nth
                              input__125886_nth_1___l__
                              0)]
                            (if
                             (clojure.core/map?
                              input__125886_nth_1___l___nth_0__)
                             (clojure.core/let
                              [VAL__127475
                               (.valAt
                                input__125886_nth_1___l___nth_0__
                                :tag)]
                              (clojure.core/case
                               VAL__127475
                               (:literal)
                               (clojure.core/letfn
                                [(state__127608
                                  []
                                  (clojure.core/let
                                   [VAL__126365
                                    (.valAt
                                     input__125886_nth_1___l___nth_0__
                                     :type)]
                                   (clojure.core/case
                                    VAL__126365
                                    (:string)
                                    (clojure.core/let
                                     [?ast
                                      input__125886_nth_1___l___nth_0__]
                                     (clojure.core/let
                                      [?rest input__125886_nth_1___r__]
                                      (clojure.core/let
                                       [?next input__125886_nth_2__]
                                       (clojure.core/let
                                        [?env input__125886_nth_3__]
                                        (try
                                         [(clojure.core/let
                                           [CATA_RESULT__15641__auto__
                                            (CATA__FN__125969
                                             ['meander.dev.parse.zeta/make-join
                                              ?ast
                                              (clojure.core/let
                                               [CATA_RESULT__15641__auto__
                                                (CATA__FN__125969
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
                                    (state__127609))))
                                 (state__127609
                                  []
                                  (clojure.core/let
                                   [VAL__126375
                                    (.valAt
                                     input__125886_nth_1___l___nth_0__
                                     :form)]
                                   (clojure.core/let
                                    [!forms []]
                                    (clojure.core/let
                                     [!forms
                                      (clojure.core/conj
                                       !forms
                                       VAL__126375)]
                                     (clojure.core/loop
                                      [i__14475__auto__
                                       0
                                       coll__127610
                                       input__125886_nth_1___r__
                                       [!forms]
                                       [!forms]]
                                      (clojure.core/let
                                       [input__126376
                                        (clojure.core/subvec
                                         coll__127610
                                         0
                                         (clojure.core/min
                                          (clojure.core/count
                                           coll__127610)
                                          1))]
                                       (if
                                        (clojure.core/=
                                         (clojure.core/count
                                          input__126376)
                                         1)
                                        (clojure.core/let
                                         [result__14476__auto__
                                          (clojure.core/let
                                           [input__126376_nth_0__
                                            (clojure.core/nth
                                             input__126376
                                             0)]
                                           (if
                                            (clojure.core/map?
                                             input__126376_nth_0__)
                                            (clojure.core/let
                                             [VAL__126377
                                              (.valAt
                                               input__126376_nth_0__
                                               :tag)]
                                             (clojure.core/case
                                              VAL__126377
                                              (:literal)
                                              (clojure.core/let
                                               [VAL__126378
                                                (.valAt
                                                 input__126376_nth_0__
                                                 :form)]
                                               (clojure.core/let
                                                [!forms
                                                 (clojure.core/conj
                                                  !forms
                                                  VAL__126378)]
                                                [!forms]))
                                              (meander.runtime.zeta/fail)))
                                            (meander.runtime.zeta/fail)))]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           result__14476__auto__)
                                          (state__127607)
                                          (recur
                                           (clojure.core/inc
                                            i__14475__auto__)
                                           (clojure.core/subvec
                                            coll__127610
                                            1)
                                           result__14476__auto__)))
                                        (if
                                         (clojure.core/or
                                          (clojure.core/seq
                                           coll__127610)
                                          (clojure.core/<
                                           i__14475__auto__
                                           0))
                                         (state__127607)
                                         (if
                                          (clojure.core/map?
                                           input__125886_nth_2__)
                                          (clojure.core/let
                                           [VAL__126368
                                            (.valAt
                                             input__125886_nth_2__
                                             :tag)]
                                           (clojure.core/case
                                            VAL__126368
                                            (:empty)
                                            (if
                                             (clojure.core/map?
                                              input__125886_nth_3__)
                                             (clojure.core/let
                                              [VAL__126369
                                               (.valAt
                                                input__125886_nth_3__
                                                :context)]
                                              (clojure.core/let
                                               [?context VAL__126369]
                                               (clojure.core/let
                                                [?env
                                                 input__125886_nth_3__]
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
                                             (state__127607))
                                            (state__127607)))
                                          (state__127607))))))))))]
                                (state__127608))
                               (state__127607)))
                             (state__127607))))
                          (state__127607)))
                        (state__127607)))
                      (state__127607
                       []
                       (clojure.core/let
                        [?sequence input__125886_nth_1__]
                        (clojure.core/let
                         [?next input__125886_nth_2__]
                         (if
                          (clojure.core/map? input__125886_nth_3__)
                          (clojure.core/let
                           [VAL__126382
                            (.valAt input__125886_nth_3__ :context)]
                           (clojure.core/case
                            VAL__126382
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
                            (state__127586)))
                          (state__127586)))))]
                     (state__127606))
                    (state__127586))))
                 (state__127586
                  []
                  (clojure.core/case
                   input__125886_nth_0__
                   (meander.dev.parse.zeta/make-cat)
                   (clojure.core/let
                    [?sequence input__125886_nth_1__]
                    (clojure.core/let
                     [?next input__125886_nth_2__]
                     (try
                      [{:tag :cat, :sequence ?sequence, :next ?next}]
                      (catch
                       java.lang.Exception
                       e__16581__auto__
                       (if
                        (meander.runtime.zeta/fail? e__16581__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__16581__auto__))))))
                   (state__127587)))
                 (state__127587
                  []
                  (clojure.core/let
                   [input__125886_nth_3__
                    (clojure.core/nth input__125886 3)]
                   (clojure.core/case
                    input__125886_nth_0__
                    (meander.dev.parse.zeta/make-join)
                    (if
                     (clojure.core/map? input__125886_nth_1__)
                     (clojure.core/let
                      [VAL__127473 (.valAt input__125886_nth_1__ :tag)]
                      (clojure.core/case
                       VAL__127473
                       (:cat)
                       (clojure.core/let
                        [VAL__126388
                         (.valAt input__125886_nth_1__ :sequence)]
                        (clojure.core/let
                         [?sequence VAL__126388]
                         (clojure.core/let
                          [VAL__126389
                           (.valAt input__125886_nth_1__ :next)]
                          (if
                           (clojure.core/map? VAL__126389)
                           (clojure.core/let
                            [VAL__126390 (.valAt VAL__126389 :tag)]
                            (clojure.core/case
                             VAL__126390
                             (:empty)
                             (clojure.core/let
                              [?right input__125886_nth_2__]
                              (clojure.core/let
                               [?env input__125886_nth_3__]
                               (try
                                [(clojure.core/let
                                  [CATA_RESULT__15641__auto__
                                   (CATA__FN__125969
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
                             (state__127588)))
                           (state__127588)))))
                       (:literal)
                       (clojure.core/let
                        [VAL__126394
                         (.valAt input__125886_nth_1__ :type)]
                        (clojure.core/case
                         VAL__126394
                         (:string)
                         (clojure.core/let
                          [VAL__126395
                           (.valAt input__125886_nth_1__ :form)]
                          (clojure.core/let
                           [?form-1 VAL__126395]
                           (if
                            (clojure.core/map? input__125886_nth_2__)
                            (clojure.core/let
                             [VAL__126396
                              (.valAt input__125886_nth_2__ :tag)]
                             (clojure.core/case
                              VAL__126396
                              (:string-join)
                              (clojure.core/let
                               [VAL__126397
                                (.valAt input__125886_nth_2__ :left)]
                               (if
                                (clojure.core/map? VAL__126397)
                                (clojure.core/let
                                 [VAL__126398
                                  (.valAt VAL__126397 :tag)]
                                 (clojure.core/case
                                  VAL__126398
                                  (:literal)
                                  (clojure.core/let
                                   [VAL__126399
                                    (.valAt VAL__126397 :type)]
                                   (clojure.core/case
                                    VAL__126399
                                    (:string)
                                    (clojure.core/let
                                     [VAL__126400
                                      (.valAt VAL__126397 :form)]
                                     (clojure.core/let
                                      [?form-2 VAL__126400]
                                      (clojure.core/let
                                       [VAL__126401
                                        (.valAt
                                         input__125886_nth_2__
                                         :right)]
                                       (clojure.core/let
                                        [?right VAL__126401]
                                        (if
                                         (clojure.core/map?
                                          input__125886_nth_3__)
                                         (clojure.core/let
                                          [VAL__126402
                                           (.valAt
                                            input__125886_nth_3__
                                            :context)]
                                          (clojure.core/case
                                           VAL__126402
                                           (:string)
                                           (clojure.core/let
                                            [?env
                                             input__125886_nth_3__]
                                            (try
                                             [(clojure.core/let
                                               [CATA_RESULT__15641__auto__
                                                (CATA__FN__125969
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
                                           (state__127588)))
                                         (state__127588))))))
                                    (state__127588)))
                                  (state__127588)))
                                (state__127588)))
                              (state__127588)))
                            (state__127588))))
                         (state__127588)))
                       (state__127588)))
                     (state__127588))
                    (state__127588))))
                 (state__127588
                  []
                  (clojure.core/case
                   input__125886_nth_0__
                   (meander.dev.parse.zeta/make-join)
                   (if
                    (clojure.core/map? input__125886_nth_1__)
                    (clojure.core/let
                     [VAL__127474 (.valAt input__125886_nth_1__ :tag)]
                     (clojure.core/case
                      VAL__127474
                      (:cat)
                      (clojure.core/let
                       [?ast input__125886_nth_1__]
                       (if
                        (clojure.core/map? input__125886_nth_2__)
                        (clojure.core/let
                         [VAL__126406
                          (.valAt input__125886_nth_2__ :tag)]
                         (clojure.core/case
                          VAL__126406
                          (:cat)
                          (clojure.core/let
                           [VAL__126407
                            (.valAt input__125886_nth_2__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__126407]
                            (clojure.core/let
                             [VAL__126408
                              (.valAt input__125886_nth_2__ :next)]
                             (clojure.core/let
                              [?next VAL__126408]
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
                          (state__127589)))
                        (state__127589)))
                      (:string-cat)
                      (clojure.core/let
                       [?ast input__125886_nth_1__]
                       (if
                        (clojure.core/map? input__125886_nth_2__)
                        (clojure.core/let
                         [VAL__126412
                          (.valAt input__125886_nth_2__ :tag)]
                         (clojure.core/case
                          VAL__126412
                          (:string-cat)
                          (clojure.core/let
                           [VAL__126413
                            (.valAt input__125886_nth_2__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__126413]
                            (clojure.core/let
                             [VAL__126414
                              (.valAt input__125886_nth_2__ :next)]
                             (clojure.core/let
                              [?next VAL__126414]
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
                          (state__127589)))
                        (state__127589)))
                      (state__127589)))
                    (state__127589))
                   (state__127589)))
                 (state__127589
                  []
                  (clojure.core/let
                   [input__125886_nth_3__
                    (clojure.core/nth input__125886 3)]
                   (clojure.core/case
                    input__125886_nth_0__
                    (meander.dev.parse.zeta/make-join)
                    (clojure.core/letfn
                     [(state__127611
                       []
                       (if
                        (clojure.core/map? input__125886_nth_1__)
                        (clojure.core/let
                         [VAL__127478
                          (.valAt input__125886_nth_1__ :next)
                          VAL__127477
                          (.valAt input__125886_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__127477
                          (:string-cat)
                          (clojure.core/let
                           [VAL__126418
                            (.valAt input__125886_nth_1__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__126418]
                            (if
                             (clojure.core/map? VAL__127478)
                             (clojure.core/let
                              [VAL__126420 (.valAt VAL__127478 :tag)]
                              (clojure.core/case
                               VAL__126420
                               (:empty)
                               (clojure.core/let
                                [?right input__125886_nth_2__]
                                (clojure.core/let
                                 [?env input__125886_nth_3__]
                                 (try
                                  [(clojure.core/let
                                    [CATA_RESULT__15641__auto__
                                     (CATA__FN__125969
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
                               (state__127612)))
                             (state__127612))))
                          (:string-star)
                          (clojure.core/let
                           [VAL__126424
                            (.valAt input__125886_nth_1__ :pattern)]
                           (clojure.core/let
                            [?pattern VAL__126424]
                            (if
                             (clojure.core/map? VAL__127478)
                             (clojure.core/let
                              [VAL__126426 (.valAt VAL__127478 :tag)]
                              (clojure.core/case
                               VAL__126426
                               (:empty)
                               (clojure.core/let
                                [?right input__125886_nth_2__]
                                (if
                                 (clojure.core/map?
                                  input__125886_nth_3__)
                                 (clojure.core/let
                                  [VAL__126427
                                   (.valAt
                                    input__125886_nth_3__
                                    :context)]
                                  (clojure.core/case
                                   VAL__126427
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
                                   (state__127612)))
                                 (state__127612)))
                               (state__127612)))
                             (state__127612))))
                          (:string-join)
                          (clojure.core/let
                           [VAL__126431
                            (.valAt input__125886_nth_1__ :left)]
                           (clojure.core/let
                            [?left VAL__126431]
                            (clojure.core/let
                             [VAL__126432
                              (.valAt input__125886_nth_1__ :right)]
                             (clojure.core/let
                              [?right-1 VAL__126432]
                              (clojure.core/let
                               [?right-2 input__125886_nth_2__]
                               (if
                                (clojure.core/map?
                                 input__125886_nth_3__)
                                (clojure.core/let
                                 [VAL__126433
                                  (.valAt
                                   input__125886_nth_3__
                                   :context)]
                                 (clojure.core/case
                                  VAL__126433
                                  (:string)
                                  (clojure.core/let
                                   [?env input__125886_nth_3__]
                                   (try
                                    [{:tag :string-join,
                                      :left ?left,
                                      :right
                                      (clojure.core/let
                                       [CATA_RESULT__15641__auto__
                                        (CATA__FN__125969
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
                                  (state__127612)))
                                (state__127612)))))))
                          (state__127612)))
                        (state__127612)))
                      (state__127612
                       []
                       (clojure.core/let
                        [?left input__125886_nth_1__]
                        (if
                         (clojure.core/map? input__125886_nth_2__)
                         (clojure.core/let
                          [VAL__126436
                           (.valAt input__125886_nth_2__ :tag)]
                          (clojure.core/case
                           VAL__126436
                           (:empty)
                           (clojure.core/let
                            [?env input__125886_nth_3__]
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
                           (state__127613)))
                         (state__127613))))
                      (state__127613
                       []
                       (if
                        (clojure.core/map? input__125886_nth_1__)
                        (clojure.core/let
                         [VAL__127476
                          (.valAt input__125886_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__127476
                          (:empty)
                          (clojure.core/let
                           [?right input__125886_nth_2__]
                           (clojure.core/let
                            [?env input__125886_nth_3__]
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
                           [VAL__126443
                            (.valAt input__125886_nth_1__ :next)]
                           (if
                            (clojure.core/map? VAL__126443)
                            (clojure.core/let
                             [VAL__126444 (.valAt VAL__126443 :tag)]
                             (clojure.core/case
                              VAL__126444
                              (:empty)
                              (clojure.core/let
                               [?left input__125886_nth_1__]
                               (clojure.core/let
                                [?right input__125886_nth_2__]
                                (clojure.core/let
                                 [?env input__125886_nth_3__]
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
                              (state__127614)))
                            (state__127614)))
                          (state__127614)))
                        (state__127614)))
                      (state__127614
                       []
                       (clojure.core/let
                        [?left input__125886_nth_1__]
                        (clojure.core/let
                         [?right input__125886_nth_2__]
                         (clojure.core/letfn
                          [(state__127615
                            []
                            (if
                             (clojure.core/map? input__125886_nth_3__)
                             (clojure.core/let
                              [VAL__126447
                               (.valAt input__125886_nth_3__ :context)]
                              (clojure.core/case
                               VAL__126447
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
                               (state__127616)))
                             (state__127616)))
                           (state__127616
                            []
                            (clojure.core/let
                             [?env input__125886_nth_3__]
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
                          (state__127615)))))]
                     (state__127611))
                    (state__127582))))]
                (state__127583)))
              (state__127582)))
            (state__127582
             []
             (if
              (clojure.core/= (clojure.core/count input__125886) 3)
              (clojure.core/let
               [input__125886_nth_0__
                (clojure.core/nth input__125886 0)
                input__125886_nth_1__
                (clojure.core/nth input__125886 1)
                input__125886_nth_2__
                (clojure.core/nth input__125886 2)]
               (clojure.core/case
                input__125886_nth_0__
                (meander.dev.parse.zeta/parse-entries)
                (if
                 (clojure.core/map? input__125886_nth_1__)
                 (clojure.core/let
                  [VAL__126452
                   (.valAt input__125886_nth_1__ :meander.zeta/as)]
                  (clojure.core/let
                   [?pattern VAL__126452]
                   (clojure.core/let
                    [X__126454
                     ((clojure.core/fn
                       [m__13405__auto__]
                       (clojure.core/dissoc
                        m__13405__auto__
                        :meander.zeta/as))
                      input__125886_nth_1__)]
                    (clojure.core/let
                     [?rest X__126454]
                     (clojure.core/letfn
                      [(save__126455 [] (state__127489))
                       (f__127617
                        []
                        (clojure.core/let
                         [?env input__125886_nth_2__]
                         (try
                          [{:tag :as,
                            :pattern
                            (clojure.core/let
                             [CATA_RESULT__15641__auto__
                              (CATA__FN__125969 [?pattern ?env])]
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
                              (CATA__FN__125969 [?rest ?env])]
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
                       (clojure.core/= ?rest input__125886_nth_1__)
                       (save__126455)
                       (f__127617)))))))
                 (state__127489))
                (state__127489)))
              (state__127489)))]
           (state__127580))
          (state__127489)))
        (state__127489
         []
         (clojure.core/letfn
          [(def__126458
            [arg__126491 ?ns]
            (clojure.core/letfn
             [(state__127618
               []
               (clojure.core/let
                [x__126492 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__126492)
                 (clojure.core/let [?env arg__126491] [?env ?ns])
                 (state__127619))))
              (state__127619
               []
               (if
                (clojure.core/map? arg__126491)
                (clojure.core/let
                 [VAL__126493 (.valAt arg__126491 :aliases)]
                 (if
                  (clojure.core/map? VAL__126493)
                  (clojure.core/let
                   [X__126495 (clojure.core/set VAL__126493)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__126495))
                    (clojure.core/loop
                     [search_space__127620
                      (clojure.core/seq X__126495)]
                     (if
                      (clojure.core/seq search_space__127620)
                      (clojure.core/let
                       [elem__126496
                        (clojure.core/first search_space__127620)
                        result__127621
                        (clojure.core/let
                         [elem__126496_nth_0__
                          (clojure.core/nth elem__126496 0)
                          elem__126496_nth_1__
                          (clojure.core/nth elem__126496 1)]
                         (if
                          (clojure.core/symbol? elem__126496_nth_0__)
                          (clojure.core/let
                           [X__126498
                            (clojure.core/name elem__126496_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__126498)
                            (if
                             (clojure.core/symbol?
                              elem__126496_nth_1__)
                             (clojure.core/let
                              [X__126500
                               (clojure.core/name
                                elem__126496_nth_1__)]
                              (clojure.core/case
                               X__126500
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__126491]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__127621)
                        (recur
                         (clojure.core/next search_space__127620))
                        result__127621))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__127618)))]
          (if
           (clojure.core/vector? input__125886)
           (if
            (clojure.core/= (clojure.core/count input__125886) 3)
            (clojure.core/let
             [input__125886_nth_0__
              (clojure.core/nth input__125886 0)
              input__125886_nth_1__
              (clojure.core/nth input__125886 1)
              input__125886_nth_2__
              (clojure.core/nth input__125886 2)]
             (clojure.core/case
              input__125886_nth_0__
              (meander.dev.parse.zeta/parse-entries)
              (if
               (clojure.core/map? input__125886_nth_1__)
               (clojure.core/let
                [X__126463 (clojure.core/set input__125886_nth_1__)]
                (if
                 (clojure.core/<= 1 (clojure.core/count X__126463))
                 (clojure.core/loop
                  [search_space__127623 (clojure.core/seq X__126463)]
                  (if
                   (clojure.core/seq search_space__127623)
                   (clojure.core/let
                    [elem__126464
                     (clojure.core/first search_space__127623)
                     result__127624
                     (clojure.core/let
                      [elem__126464_nth_0__
                       (clojure.core/nth elem__126464 0)
                       elem__126464_nth_1__
                       (clojure.core/nth elem__126464 1)]
                      (clojure.core/let
                       [*m__125920 elem__126464_nth_0__]
                       (if
                        (clojure.core/symbol? elem__126464_nth_0__)
                        (clojure.core/let
                         [X__126466
                          (clojure.core/namespace
                           elem__126464_nth_0__)]
                         (clojure.core/let
                          [?ns X__126466]
                          (clojure.core/let
                           [X__126468
                            (clojure.core/name elem__126464_nth_0__)]
                           (if
                            (clojure.core/string? X__126468)
                            (if
                             (clojure.core/re-matches #"&.*" X__126468)
                             (clojure.core/let
                              [?pattern elem__126464_nth_1__]
                              (clojure.core/let
                               [X__126470
                                ((clojure.core/fn
                                  [m__13405__auto__]
                                  (clojure.core/dissoc
                                   m__13405__auto__
                                   *m__125920))
                                 input__125886_nth_1__)]
                               (clojure.core/let
                                [?rest X__126470]
                                (clojure.core/let
                                 [x__14338__auto__
                                  (def__126458
                                   input__125886_nth_2__
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
                                        (CATA__FN__125969
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
                                        (CATA__FN__125969
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
                     (meander.runtime.zeta/fail? result__127624)
                     (recur (clojure.core/next search_space__127623))
                     result__127624))
                   (state__127490)))
                 (state__127490)))
               (state__127490))
              (state__127490)))
            (state__127490))
           (state__127490))))
        (state__127490
         []
         (if
          (clojure.core/vector? input__125886)
          (clojure.core/letfn
           [(state__127626
             []
             (if
              (clojure.core/= (clojure.core/count input__125886) 3)
              (clojure.core/let
               [input__125886_nth_0__
                (clojure.core/nth input__125886 0)
                input__125886_nth_1__
                (clojure.core/nth input__125886 1)
                input__125886_nth_2__
                (clojure.core/nth input__125886 2)]
               (clojure.core/case
                input__125886_nth_0__
                (meander.dev.parse.zeta/parse-entries)
                (clojure.core/letfn
                 [(state__127628
                   []
                   (if
                    (clojure.core/map? input__125886_nth_1__)
                    (clojure.core/let
                     [X__126514
                      (clojure.core/set input__125886_nth_1__)]
                     (if
                      (clojure.core/<=
                       1
                       (clojure.core/count X__126514))
                      (clojure.core/loop
                       [search_space__127630
                        (clojure.core/seq X__126514)]
                       (if
                        (clojure.core/seq search_space__127630)
                        (clojure.core/let
                         [elem__126515
                          (clojure.core/first search_space__127630)
                          result__127631
                          (clojure.core/let
                           [elem__126515_nth_0__
                            (clojure.core/nth elem__126515 0)
                            elem__126515_nth_1__
                            (clojure.core/nth elem__126515 1)]
                           (clojure.core/let
                            [?key-pattern elem__126515_nth_0__]
                            (clojure.core/let
                             [?val-pattern elem__126515_nth_1__]
                             (clojure.core/let
                              [X__126517
                               ((clojure.core/fn
                                 [m__13405__auto__]
                                 (clojure.core/dissoc
                                  m__13405__auto__
                                  ?key-pattern))
                                input__125886_nth_1__)]
                              (clojure.core/let
                               [?rest X__126517]
                               (clojure.core/let
                                [?env input__125886_nth_2__]
                                (try
                                 [{:tag :entry,
                                   :key-pattern
                                   (clojure.core/let
                                    [CATA_RESULT__15641__auto__
                                     (CATA__FN__125969
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
                                     (CATA__FN__125969
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
                                     (CATA__FN__125969
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
                          (meander.runtime.zeta/fail? result__127631)
                          (recur
                           (clojure.core/next search_space__127630))
                          result__127631))
                        (state__127629)))
                      (state__127629)))
                    (state__127629)))
                  (state__127629
                   []
                   (if
                    (clojure.core/map? input__125886_nth_1__)
                    (clojure.core/let
                     [?env input__125886_nth_2__]
                     (try
                      [{:tag :some-map}]
                      (catch
                       java.lang.Exception
                       e__16581__auto__
                       (if
                        (meander.runtime.zeta/fail? e__16581__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__16581__auto__)))))
                    (state__127627)))]
                 (state__127628))
                (meander.dev.parse.zeta/parse-with-bindings)
                (clojure.core/letfn
                 [(state__127633
                   []
                   (if
                    (clojure.core/vector? input__125886_nth_1__)
                    (clojure.core/case
                     input__125886_nth_1__
                     ([])
                     (clojure.core/let
                      [?env input__125886_nth_2__]
                      (try
                       [[]]
                       (catch
                        java.lang.Exception
                        e__16581__auto__
                        (if
                         (meander.runtime.zeta/fail? e__16581__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__16581__auto__)))))
                     (state__127634))
                    (state__127634)))
                  (state__127634
                   []
                   (if
                    (clojure.core/vector? input__125886_nth_1__)
                    (if
                     (clojure.core/=
                      (clojure.core/count input__125886_nth_1__)
                      1)
                     (clojure.core/let
                      [?env input__125886_nth_2__]
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
                     (state__127635))
                    (state__127635)))
                  (state__127635
                   []
                   (if
                    (clojure.core/vector? input__125886_nth_1__)
                    (clojure.core/let
                     [input__125886_nth_1___l__
                      (clojure.core/subvec
                       input__125886_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__125886_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__125886_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__125886_nth_1___r__
                        (clojure.core/subvec input__125886_nth_1__ 2)]
                       (clojure.core/let
                        [input__125886_nth_1___l___nth_0__
                         (clojure.core/nth input__125886_nth_1___l__ 0)
                         input__125886_nth_1___l___nth_1__
                         (clojure.core/nth
                          input__125886_nth_1___l__
                          1)]
                        (if
                         (clojure.core/symbol?
                          input__125886_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__126531
                           (clojure.core/namespace
                            input__125886_nth_1___l___nth_0__)]
                          (clojure.core/let
                           [X__126533
                            (clojure.core/name
                             input__125886_nth_1___l___nth_0__)]
                           (if
                            (clojure.core/string? X__126533)
                            (if
                             (clojure.core/re-matches #"%.+" X__126533)
                             (clojure.core/let
                              [?symbol
                               input__125886_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?pattern
                                input__125886_nth_1___l___nth_1__]
                               (clojure.core/let
                                [?rest input__125886_nth_1___r__]
                                (clojure.core/let
                                 [?env input__125886_nth_2__]
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
                                         (CATA__FN__125969
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
                                       (CATA__FN__125969
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
                             (state__127636))
                            (state__127636))))
                         (state__127636))))
                      (state__127636)))
                    (state__127636)))
                  (state__127636
                   []
                   (if
                    (clojure.core/vector? input__125886_nth_1__)
                    (clojure.core/let
                     [input__125886_nth_1___l__
                      (clojure.core/subvec
                       input__125886_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__125886_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__125886_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__125886_nth_1___r__
                        (clojure.core/subvec input__125886_nth_1__ 2)]
                       (clojure.core/let
                        [input__125886_nth_1___l___nth_0__
                         (clojure.core/nth input__125886_nth_1___l__ 0)
                         input__125886_nth_1___l___nth_1__
                         (clojure.core/nth
                          input__125886_nth_1___l__
                          1)]
                        (clojure.core/let
                         [?x input__125886_nth_1___l___nth_0__]
                         (clojure.core/let
                          [?pattern input__125886_nth_1___l___nth_1__]
                          (clojure.core/let
                           [?rest input__125886_nth_1___r__]
                           (clojure.core/let
                            [?env input__125886_nth_2__]
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
                      (state__127627)))
                    (state__127627)))]
                 (state__127633))
                (state__127627)))
              (state__127627)))
            (state__127627
             []
             (if
              (clojure.core/= (clojure.core/count input__125886) 2)
              (clojure.core/let
               [input__125886_nth_0__
                (clojure.core/nth input__125886 0)
                input__125886_nth_1__
                (clojure.core/nth input__125886 1)]
               (if
                (clojure.core/vector? input__125886_nth_0__)
                (clojure.core/let
                 [?sequence input__125886_nth_0__]
                 (clojure.core/let
                  [?form input__125886_nth_0__]
                  (clojure.core/let
                   [?env input__125886_nth_1__]
                   (try
                    [{:tag :vector,
                      :next
                      (clojure.core/let
                       [CATA_RESULT__15641__auto__
                        (CATA__FN__125969
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
                (state__127491)))
              (state__127491)))]
           (state__127626))
          (state__127491)))
        (state__127491
         []
         (clojure.core/letfn
          [(def__126543
            [arg__126566 ?__125887]
            (clojure.core/letfn
             [(state__127637
               []
               (clojure.core/let
                [x__126567 "clojure.core"]
                (if
                 (clojure.core/= ?__125887 x__126567)
                 [?__125887]
                 (state__127638))))
              (state__127638
               []
               (if
                (clojure.core/map? arg__126566)
                (clojure.core/let
                 [VAL__126568 (.valAt arg__126566 :aliases)]
                 (if
                  (clojure.core/map? VAL__126568)
                  (clojure.core/let
                   [X__126570 (clojure.core/set VAL__126568)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__126570))
                    (clojure.core/loop
                     [search_space__127639
                      (clojure.core/seq X__126570)]
                     (if
                      (clojure.core/seq search_space__127639)
                      (clojure.core/let
                       [elem__126571
                        (clojure.core/first search_space__127639)
                        result__127640
                        (clojure.core/let
                         [elem__126571_nth_0__
                          (clojure.core/nth elem__126571 0)
                          elem__126571_nth_1__
                          (clojure.core/nth elem__126571 1)]
                         (if
                          (clojure.core/symbol? elem__126571_nth_0__)
                          (clojure.core/let
                           [X__126573
                            (clojure.core/name elem__126571_nth_0__)]
                           (if
                            (clojure.core/= ?__125887 X__126573)
                            (if
                             (clojure.core/symbol?
                              elem__126571_nth_1__)
                             (clojure.core/let
                              [X__126575
                               (clojure.core/name
                                elem__126571_nth_1__)]
                              (clojure.core/case
                               X__126575
                               ("clojure.core")
                               [?__125887]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__127640)
                        (recur
                         (clojure.core/next search_space__127639))
                        result__127640))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__127637)))]
          (if
           (clojure.core/vector? input__125886)
           (if
            (clojure.core/= (clojure.core/count input__125886) 2)
            (clojure.core/let
             [input__125886_nth_0__
              (clojure.core/nth input__125886 0)
              input__125886_nth_1__
              (clojure.core/nth input__125886 1)]
             (if
              (clojure.core/seq? input__125886_nth_0__)
              (clojure.core/let
               [input__125886_nth_0___l__
                (clojure.core/take 1 input__125886_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__125886_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__125886_nth_0___r__
                  (clojure.core/drop 1 input__125886_nth_0__)]
                 (clojure.core/let
                  [input__125886_nth_0___l___nth_0__
                   (clojure.core/nth input__125886_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__125886_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__126553
                     (clojure.core/namespace
                      input__125886_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__125887 X__126553]
                     (clojure.core/let
                      [X__126555
                       (clojure.core/name
                        input__125886_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__126555
                       ("unquote")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__126543 input__125886_nth_1__ ?__125887)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__127492)
                         (clojure.core/let
                          [[?__125887] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__125886)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__125886)
                             2)
                            (clojure.core/let
                             [input__125886_nth_0__
                              (clojure.core/nth input__125886 0)
                              input__125886_nth_1__
                              (clojure.core/nth input__125886 1)]
                             (if
                              (clojure.core/seq? input__125886_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__125886_nth_0__)
                                2)
                               (clojure.core/let
                                [input__125886_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__125886_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?x input__125886_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__125886_nth_0__]
                                  (clojure.core/let
                                   [?env input__125886_nth_1__]
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
                               (state__127492))
                              (state__127492)))
                            (state__127492))
                           (state__127492)))))
                       (state__127492)))))
                   (state__127492))))
                (state__127492)))
              (state__127492)))
            (state__127492))
           (state__127492))))
        (state__127492
         []
         (clojure.core/letfn
          [(def__126577
            [arg__126600 ?__125888]
            (clojure.core/letfn
             [(state__127642
               []
               (clojure.core/let
                [x__126601 "meander.zeta"]
                (if
                 (clojure.core/= ?__125888 x__126601)
                 [?__125888]
                 (state__127643))))
              (state__127643
               []
               (if
                (clojure.core/map? arg__126600)
                (clojure.core/let
                 [VAL__126602 (.valAt arg__126600 :aliases)]
                 (if
                  (clojure.core/map? VAL__126602)
                  (clojure.core/let
                   [X__126604 (clojure.core/set VAL__126602)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__126604))
                    (clojure.core/loop
                     [search_space__127644
                      (clojure.core/seq X__126604)]
                     (if
                      (clojure.core/seq search_space__127644)
                      (clojure.core/let
                       [elem__126605
                        (clojure.core/first search_space__127644)
                        result__127645
                        (clojure.core/let
                         [elem__126605_nth_0__
                          (clojure.core/nth elem__126605 0)
                          elem__126605_nth_1__
                          (clojure.core/nth elem__126605 1)]
                         (if
                          (clojure.core/symbol? elem__126605_nth_0__)
                          (clojure.core/let
                           [X__126607
                            (clojure.core/name elem__126605_nth_0__)]
                           (if
                            (clojure.core/= ?__125888 X__126607)
                            (if
                             (clojure.core/symbol?
                              elem__126605_nth_1__)
                             (clojure.core/let
                              [X__126609
                               (clojure.core/name
                                elem__126605_nth_1__)]
                              (clojure.core/case
                               X__126609
                               ("meander.zeta")
                               [?__125888]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__127645)
                        (recur
                         (clojure.core/next search_space__127644))
                        result__127645))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__127642)))]
          (if
           (clojure.core/vector? input__125886)
           (if
            (clojure.core/= (clojure.core/count input__125886) 2)
            (clojure.core/let
             [input__125886_nth_0__
              (clojure.core/nth input__125886 0)
              input__125886_nth_1__
              (clojure.core/nth input__125886 1)]
             (if
              (clojure.core/seq? input__125886_nth_0__)
              (clojure.core/let
               [input__125886_nth_0___l__
                (clojure.core/take 1 input__125886_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__125886_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__125886_nth_0___r__
                  (clojure.core/drop 1 input__125886_nth_0__)]
                 (clojure.core/let
                  [input__125886_nth_0___l___nth_0__
                   (clojure.core/nth input__125886_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__125886_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__126587
                     (clojure.core/namespace
                      input__125886_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__125888 X__126587]
                     (clojure.core/let
                      [X__126589
                       (clojure.core/name
                        input__125886_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__126589
                       ("*")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__126577 input__125886_nth_1__ ?__125888)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__127493)
                         (clojure.core/let
                          [[?__125888] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__125886)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__125886)
                             2)
                            (clojure.core/let
                             [input__125886_nth_0__
                              (clojure.core/nth input__125886 0)
                              input__125886_nth_1__
                              (clojure.core/nth input__125886 1)]
                             (if
                              (clojure.core/seq? input__125886_nth_0__)
                              (clojure.core/let
                               [input__125886_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__125886_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__125886_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__125886_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__125886_nth_0__)]
                                 (clojure.core/let
                                  [?patterns input__125886_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__125886_nth_0__]
                                   (clojure.core/let
                                    [?env input__125886_nth_1__]
                                    (try
                                     [{:tag :star,
                                       :greedy? true,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__125969
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
                                (state__127493)))
                              (state__127493)))
                            (state__127493))
                           (state__127493)))))
                       (state__127493)))))
                   (state__127493))))
                (state__127493)))
              (state__127493)))
            (state__127493))
           (state__127493))))
        (state__127493
         []
         (clojure.core/letfn
          [(def__126611
            [arg__126634 ?__125889]
            (clojure.core/letfn
             [(state__127647
               []
               (clojure.core/let
                [x__126635 "meander.zeta"]
                (if
                 (clojure.core/= ?__125889 x__126635)
                 [?__125889]
                 (state__127648))))
              (state__127648
               []
               (if
                (clojure.core/map? arg__126634)
                (clojure.core/let
                 [VAL__126636 (.valAt arg__126634 :aliases)]
                 (if
                  (clojure.core/map? VAL__126636)
                  (clojure.core/let
                   [X__126638 (clojure.core/set VAL__126636)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__126638))
                    (clojure.core/loop
                     [search_space__127649
                      (clojure.core/seq X__126638)]
                     (if
                      (clojure.core/seq search_space__127649)
                      (clojure.core/let
                       [elem__126639
                        (clojure.core/first search_space__127649)
                        result__127650
                        (clojure.core/let
                         [elem__126639_nth_0__
                          (clojure.core/nth elem__126639 0)
                          elem__126639_nth_1__
                          (clojure.core/nth elem__126639 1)]
                         (if
                          (clojure.core/symbol? elem__126639_nth_0__)
                          (clojure.core/let
                           [X__126641
                            (clojure.core/name elem__126639_nth_0__)]
                           (if
                            (clojure.core/= ?__125889 X__126641)
                            (if
                             (clojure.core/symbol?
                              elem__126639_nth_1__)
                             (clojure.core/let
                              [X__126643
                               (clojure.core/name
                                elem__126639_nth_1__)]
                              (clojure.core/case
                               X__126643
                               ("meander.zeta")
                               [?__125889]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__127650)
                        (recur
                         (clojure.core/next search_space__127649))
                        result__127650))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__127647)))]
          (if
           (clojure.core/vector? input__125886)
           (if
            (clojure.core/= (clojure.core/count input__125886) 2)
            (clojure.core/let
             [input__125886_nth_0__
              (clojure.core/nth input__125886 0)
              input__125886_nth_1__
              (clojure.core/nth input__125886 1)]
             (if
              (clojure.core/seq? input__125886_nth_0__)
              (clojure.core/let
               [input__125886_nth_0___l__
                (clojure.core/take 1 input__125886_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__125886_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__125886_nth_0___r__
                  (clojure.core/drop 1 input__125886_nth_0__)]
                 (clojure.core/let
                  [input__125886_nth_0___l___nth_0__
                   (clojure.core/nth input__125886_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__125886_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__126621
                     (clojure.core/namespace
                      input__125886_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__125889 X__126621]
                     (clojure.core/let
                      [X__126623
                       (clojure.core/name
                        input__125886_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__126623
                       ("<>")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__126611 input__125886_nth_1__ ?__125889)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__127494)
                         (clojure.core/let
                          [[?__125889] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__125886)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__125886)
                             2)
                            (clojure.core/let
                             [input__125886_nth_0__
                              (clojure.core/nth input__125886 0)
                              input__125886_nth_1__
                              (clojure.core/nth input__125886 1)]
                             (if
                              (clojure.core/seq? input__125886_nth_0__)
                              (clojure.core/let
                               [input__125886_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__125886_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__125886_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__125886_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__125886_nth_0__)]
                                 (clojure.core/let
                                  [?patterns input__125886_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__125886_nth_0__]
                                   (clojure.core/let
                                    [?env input__125886_nth_1__]
                                    (try
                                     [{:tag :group,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__125969
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
                                (state__127494)))
                              (state__127494)))
                            (state__127494))
                           (state__127494)))))
                       (state__127494)))))
                   (state__127494))))
                (state__127494)))
              (state__127494)))
            (state__127494))
           (state__127494))))
        (state__127494
         []
         (clojure.core/letfn
          [(def__126645
            [arg__126668 ?__125890]
            (clojure.core/letfn
             [(state__127652
               []
               (clojure.core/let
                [x__126669 "meander.math.zeta"]
                (if
                 (clojure.core/= ?__125890 x__126669)
                 [?__125890]
                 (state__127653))))
              (state__127653
               []
               (if
                (clojure.core/map? arg__126668)
                (clojure.core/let
                 [VAL__126670 (.valAt arg__126668 :aliases)]
                 (if
                  (clojure.core/map? VAL__126670)
                  (clojure.core/let
                   [X__126672 (clojure.core/set VAL__126670)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__126672))
                    (clojure.core/loop
                     [search_space__127654
                      (clojure.core/seq X__126672)]
                     (if
                      (clojure.core/seq search_space__127654)
                      (clojure.core/let
                       [elem__126673
                        (clojure.core/first search_space__127654)
                        result__127655
                        (clojure.core/let
                         [elem__126673_nth_0__
                          (clojure.core/nth elem__126673 0)
                          elem__126673_nth_1__
                          (clojure.core/nth elem__126673 1)]
                         (if
                          (clojure.core/symbol? elem__126673_nth_0__)
                          (clojure.core/let
                           [X__126675
                            (clojure.core/name elem__126673_nth_0__)]
                           (if
                            (clojure.core/= ?__125890 X__126675)
                            (if
                             (clojure.core/symbol?
                              elem__126673_nth_1__)
                             (clojure.core/let
                              [X__126677
                               (clojure.core/name
                                elem__126673_nth_1__)]
                              (clojure.core/case
                               X__126677
                               ("meander.math.zeta")
                               [?__125890]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__127655)
                        (recur
                         (clojure.core/next search_space__127654))
                        result__127655))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__127652)))]
          (if
           (clojure.core/vector? input__125886)
           (if
            (clojure.core/= (clojure.core/count input__125886) 2)
            (clojure.core/let
             [input__125886_nth_0__
              (clojure.core/nth input__125886 0)
              input__125886_nth_1__
              (clojure.core/nth input__125886 1)]
             (if
              (clojure.core/seq? input__125886_nth_0__)
              (clojure.core/let
               [input__125886_nth_0___l__
                (clojure.core/take 1 input__125886_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__125886_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__125886_nth_0___r__
                  (clojure.core/drop 1 input__125886_nth_0__)]
                 (clojure.core/let
                  [input__125886_nth_0___l___nth_0__
                   (clojure.core/nth input__125886_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__125886_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__126655
                     (clojure.core/namespace
                      input__125886_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__125890 X__126655]
                     (clojure.core/let
                      [X__126657
                       (clojure.core/name
                        input__125886_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__126657
                       ("+")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__126645 input__125886_nth_1__ ?__125890)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__127495)
                         (clojure.core/let
                          [[?__125890] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__125886)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__125886)
                             2)
                            (clojure.core/let
                             [input__125886_nth_0__
                              (clojure.core/nth input__125886 0)
                              input__125886_nth_1__
                              (clojure.core/nth input__125886 1)]
                             (if
                              (clojure.core/seq? input__125886_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__125886_nth_0__)
                                3)
                               (clojure.core/let
                                [input__125886_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__125886_nth_0__
                                  1)
                                 input__125886_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__125886_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?a input__125886_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?b input__125886_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__125886_nth_0__]
                                   (clojure.core/let
                                    [?env input__125886_nth_1__]
                                    (try
                                     [{:tag :meander.math.zeta/+,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__125969 [?a ?env])]
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
                                         (CATA__FN__125969 [?b ?env])]
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
                               (state__127495))
                              (state__127495)))
                            (state__127495))
                           (state__127495)))))
                       (state__127495)))))
                   (state__127495))))
                (state__127495)))
              (state__127495)))
            (state__127495))
           (state__127495))))
        (state__127495
         []
         (clojure.core/letfn
          [(def__126679
            [arg__126702 ?__125891]
            (clojure.core/letfn
             [(state__127657
               []
               (clojure.core/let
                [x__126703 "meander.math.zeta"]
                (if
                 (clojure.core/= ?__125891 x__126703)
                 [?__125891]
                 (state__127658))))
              (state__127658
               []
               (if
                (clojure.core/map? arg__126702)
                (clojure.core/let
                 [VAL__126704 (.valAt arg__126702 :aliases)]
                 (if
                  (clojure.core/map? VAL__126704)
                  (clojure.core/let
                   [X__126706 (clojure.core/set VAL__126704)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__126706))
                    (clojure.core/loop
                     [search_space__127659
                      (clojure.core/seq X__126706)]
                     (if
                      (clojure.core/seq search_space__127659)
                      (clojure.core/let
                       [elem__126707
                        (clojure.core/first search_space__127659)
                        result__127660
                        (clojure.core/let
                         [elem__126707_nth_0__
                          (clojure.core/nth elem__126707 0)
                          elem__126707_nth_1__
                          (clojure.core/nth elem__126707 1)]
                         (if
                          (clojure.core/symbol? elem__126707_nth_0__)
                          (clojure.core/let
                           [X__126709
                            (clojure.core/name elem__126707_nth_0__)]
                           (if
                            (clojure.core/= ?__125891 X__126709)
                            (if
                             (clojure.core/symbol?
                              elem__126707_nth_1__)
                             (clojure.core/let
                              [X__126711
                               (clojure.core/name
                                elem__126707_nth_1__)]
                              (clojure.core/case
                               X__126711
                               ("meander.math.zeta")
                               [?__125891]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__127660)
                        (recur
                         (clojure.core/next search_space__127659))
                        result__127660))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__127657)))]
          (if
           (clojure.core/vector? input__125886)
           (if
            (clojure.core/= (clojure.core/count input__125886) 2)
            (clojure.core/let
             [input__125886_nth_0__
              (clojure.core/nth input__125886 0)
              input__125886_nth_1__
              (clojure.core/nth input__125886 1)]
             (if
              (clojure.core/seq? input__125886_nth_0__)
              (clojure.core/let
               [input__125886_nth_0___l__
                (clojure.core/take 1 input__125886_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__125886_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__125886_nth_0___r__
                  (clojure.core/drop 1 input__125886_nth_0__)]
                 (clojure.core/let
                  [input__125886_nth_0___l___nth_0__
                   (clojure.core/nth input__125886_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__125886_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__126689
                     (clojure.core/namespace
                      input__125886_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__125891 X__126689]
                     (clojure.core/let
                      [X__126691
                       (clojure.core/name
                        input__125886_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__126691
                       ("-")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__126679 input__125886_nth_1__ ?__125891)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__127496)
                         (clojure.core/let
                          [[?__125891] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__125886)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__125886)
                             2)
                            (clojure.core/let
                             [input__125886_nth_0__
                              (clojure.core/nth input__125886 0)
                              input__125886_nth_1__
                              (clojure.core/nth input__125886 1)]
                             (if
                              (clojure.core/seq? input__125886_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__125886_nth_0__)
                                3)
                               (clojure.core/let
                                [input__125886_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__125886_nth_0__
                                  1)
                                 input__125886_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__125886_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?a input__125886_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?b input__125886_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__125886_nth_0__]
                                   (clojure.core/let
                                    [?env input__125886_nth_1__]
                                    (try
                                     [{:tag :meander.math.zeta/-,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__125969 [?a ?env])]
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
                                         (CATA__FN__125969 [?b ?env])]
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
                               (state__127496))
                              (state__127496)))
                            (state__127496))
                           (state__127496)))))
                       (state__127496)))))
                   (state__127496))))
                (state__127496)))
              (state__127496)))
            (state__127496))
           (state__127496))))
        (state__127496
         []
         (clojure.core/letfn
          [(def__126713
            [arg__126736 ?__125892]
            (clojure.core/letfn
             [(state__127662
               []
               (clojure.core/let
                [x__126737 "meander.zeta"]
                (if
                 (clojure.core/= ?__125892 x__126737)
                 [?__125892]
                 (state__127663))))
              (state__127663
               []
               (if
                (clojure.core/map? arg__126736)
                (clojure.core/let
                 [VAL__126738 (.valAt arg__126736 :aliases)]
                 (if
                  (clojure.core/map? VAL__126738)
                  (clojure.core/let
                   [X__126740 (clojure.core/set VAL__126738)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__126740))
                    (clojure.core/loop
                     [search_space__127664
                      (clojure.core/seq X__126740)]
                     (if
                      (clojure.core/seq search_space__127664)
                      (clojure.core/let
                       [elem__126741
                        (clojure.core/first search_space__127664)
                        result__127665
                        (clojure.core/let
                         [elem__126741_nth_0__
                          (clojure.core/nth elem__126741 0)
                          elem__126741_nth_1__
                          (clojure.core/nth elem__126741 1)]
                         (if
                          (clojure.core/symbol? elem__126741_nth_0__)
                          (clojure.core/let
                           [X__126743
                            (clojure.core/name elem__126741_nth_0__)]
                           (if
                            (clojure.core/= ?__125892 X__126743)
                            (if
                             (clojure.core/symbol?
                              elem__126741_nth_1__)
                             (clojure.core/let
                              [X__126745
                               (clojure.core/name
                                elem__126741_nth_1__)]
                              (clojure.core/case
                               X__126745
                               ("meander.zeta")
                               [?__125892]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__127665)
                        (recur
                         (clojure.core/next search_space__127664))
                        result__127665))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__127662)))]
          (if
           (clojure.core/vector? input__125886)
           (if
            (clojure.core/= (clojure.core/count input__125886) 2)
            (clojure.core/let
             [input__125886_nth_0__
              (clojure.core/nth input__125886 0)
              input__125886_nth_1__
              (clojure.core/nth input__125886 1)]
             (if
              (clojure.core/seq? input__125886_nth_0__)
              (clojure.core/let
               [input__125886_nth_0___l__
                (clojure.core/take 1 input__125886_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__125886_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__125886_nth_0___r__
                  (clojure.core/drop 1 input__125886_nth_0__)]
                 (clojure.core/let
                  [input__125886_nth_0___l___nth_0__
                   (clojure.core/nth input__125886_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__125886_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__126723
                     (clojure.core/namespace
                      input__125886_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__125892 X__126723]
                     (clojure.core/let
                      [X__126725
                       (clojure.core/name
                        input__125886_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__126725
                       ("with")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__126713 input__125886_nth_1__ ?__125892)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__127497)
                         (clojure.core/let
                          [[?__125892] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__125886)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__125886)
                             2)
                            (clojure.core/let
                             [input__125886_nth_0__
                              (clojure.core/nth input__125886 0)
                              input__125886_nth_1__
                              (clojure.core/nth input__125886 1)]
                             (if
                              (clojure.core/seq? input__125886_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__125886_nth_0__)
                                3)
                               (clojure.core/let
                                [input__125886_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__125886_nth_0__
                                  1)
                                 input__125886_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__125886_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?bindings
                                  input__125886_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?body input__125886_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__125886_nth_0__]
                                   (clojure.core/let
                                    [?env input__125886_nth_1__]
                                    (try
                                     [{:tag :with,
                                       :bindings
                                       {:tag :with-bindings,
                                        :bindings
                                        (clojure.core/let
                                         [CATA_RESULT__15641__auto__
                                          (CATA__FN__125969
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
                                         (CATA__FN__125969
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
                               (state__127497))
                              (state__127497)))
                            (state__127497))
                           (state__127497)))))
                       (state__127497)))))
                   (state__127497))))
                (state__127497)))
              (state__127497)))
            (state__127497))
           (state__127497))))
        (state__127497
         []
         (clojure.core/letfn
          [(def__126747
            [arg__126770 ?__125893]
            (clojure.core/letfn
             [(state__127667
               []
               (clojure.core/let
                [x__126771 "meander.zeta"]
                (if
                 (clojure.core/= ?__125893 x__126771)
                 [?__125893]
                 (state__127668))))
              (state__127668
               []
               (if
                (clojure.core/map? arg__126770)
                (clojure.core/let
                 [VAL__126772 (.valAt arg__126770 :aliases)]
                 (if
                  (clojure.core/map? VAL__126772)
                  (clojure.core/let
                   [X__126774 (clojure.core/set VAL__126772)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__126774))
                    (clojure.core/loop
                     [search_space__127669
                      (clojure.core/seq X__126774)]
                     (if
                      (clojure.core/seq search_space__127669)
                      (clojure.core/let
                       [elem__126775
                        (clojure.core/first search_space__127669)
                        result__127670
                        (clojure.core/let
                         [elem__126775_nth_0__
                          (clojure.core/nth elem__126775 0)
                          elem__126775_nth_1__
                          (clojure.core/nth elem__126775 1)]
                         (if
                          (clojure.core/symbol? elem__126775_nth_0__)
                          (clojure.core/let
                           [X__126777
                            (clojure.core/name elem__126775_nth_0__)]
                           (if
                            (clojure.core/= ?__125893 X__126777)
                            (if
                             (clojure.core/symbol?
                              elem__126775_nth_1__)
                             (clojure.core/let
                              [X__126779
                               (clojure.core/name
                                elem__126775_nth_1__)]
                              (clojure.core/case
                               X__126779
                               ("meander.zeta")
                               [?__125893]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__127670)
                        (recur
                         (clojure.core/next search_space__127669))
                        result__127670))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__127667)))]
          (if
           (clojure.core/vector? input__125886)
           (if
            (clojure.core/= (clojure.core/count input__125886) 2)
            (clojure.core/let
             [input__125886_nth_0__
              (clojure.core/nth input__125886 0)
              input__125886_nth_1__
              (clojure.core/nth input__125886 1)]
             (if
              (clojure.core/seq? input__125886_nth_0__)
              (clojure.core/let
               [input__125886_nth_0___l__
                (clojure.core/take 1 input__125886_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__125886_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__125886_nth_0___r__
                  (clojure.core/drop 1 input__125886_nth_0__)]
                 (clojure.core/let
                  [input__125886_nth_0___l___nth_0__
                   (clojure.core/nth input__125886_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__125886_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__126757
                     (clojure.core/namespace
                      input__125886_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__125893 X__126757]
                     (clojure.core/let
                      [X__126759
                       (clojure.core/name
                        input__125886_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__126759
                       ("apply")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__126747 input__125886_nth_1__ ?__125893)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__127498)
                         (clojure.core/let
                          [[?__125893] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__125886)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__125886)
                             2)
                            (clojure.core/let
                             [input__125886_nth_0__
                              (clojure.core/nth input__125886 0)
                              input__125886_nth_1__
                              (clojure.core/nth input__125886 1)]
                             (if
                              (clojure.core/seq? input__125886_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__125886_nth_0__)
                                3)
                               (clojure.core/let
                                [input__125886_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__125886_nth_0__
                                  1)
                                 input__125886_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__125886_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?fn input__125886_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?pattern
                                   input__125886_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__125886_nth_0__]
                                   (clojure.core/let
                                    [?env input__125886_nth_1__]
                                    (try
                                     [{:tag :apply,
                                       :fn ?fn,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__125969
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
                               (state__127498))
                              (state__127498)))
                            (state__127498))
                           (state__127498)))))
                       (state__127498)))))
                   (state__127498))))
                (state__127498)))
              (state__127498)))
            (state__127498))
           (state__127498))))
        (state__127498
         []
         (clojure.core/letfn
          [(def__126781
            [arg__126806 ?__125894]
            (clojure.core/letfn
             [(state__127672
               []
               (clojure.core/let
                [x__126807 "meander.zeta"]
                (if
                 (clojure.core/= ?__125894 x__126807)
                 [?__125894]
                 (state__127673))))
              (state__127673
               []
               (if
                (clojure.core/map? arg__126806)
                (clojure.core/let
                 [VAL__126808 (.valAt arg__126806 :aliases)]
                 (if
                  (clojure.core/map? VAL__126808)
                  (clojure.core/let
                   [X__126810 (clojure.core/set VAL__126808)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__126810))
                    (clojure.core/loop
                     [search_space__127674
                      (clojure.core/seq X__126810)]
                     (if
                      (clojure.core/seq search_space__127674)
                      (clojure.core/let
                       [elem__126811
                        (clojure.core/first search_space__127674)
                        result__127675
                        (clojure.core/let
                         [elem__126811_nth_0__
                          (clojure.core/nth elem__126811 0)
                          elem__126811_nth_1__
                          (clojure.core/nth elem__126811 1)]
                         (if
                          (clojure.core/symbol? elem__126811_nth_0__)
                          (clojure.core/let
                           [X__126813
                            (clojure.core/name elem__126811_nth_0__)]
                           (if
                            (clojure.core/= ?__125894 X__126813)
                            (if
                             (clojure.core/symbol?
                              elem__126811_nth_1__)
                             (clojure.core/let
                              [X__126815
                               (clojure.core/name
                                elem__126811_nth_1__)]
                              (clojure.core/case
                               X__126815
                               ("meander.zeta")
                               [?__125894]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__127675)
                        (recur
                         (clojure.core/next search_space__127674))
                        result__127675))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__127672)))]
          (if
           (clojure.core/vector? input__125886)
           (if
            (clojure.core/= (clojure.core/count input__125886) 2)
            (clojure.core/let
             [input__125886_nth_0__
              (clojure.core/nth input__125886 0)
              input__125886_nth_1__
              (clojure.core/nth input__125886 1)]
             (if
              (clojure.core/seq? input__125886_nth_0__)
              (clojure.core/let
               [input__125886_nth_0___l__
                (clojure.core/take 1 input__125886_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__125886_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__125886_nth_0___r__
                  (clojure.core/drop 1 input__125886_nth_0__)]
                 (clojure.core/let
                  [input__125886_nth_0___l___nth_0__
                   (clojure.core/nth input__125886_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__125886_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__126793
                     (clojure.core/namespace
                      input__125886_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__125894 X__126793]
                     (clojure.core/let
                      [X__126795
                       (clojure.core/name
                        input__125886_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__126795
                       ("and")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__126781 input__125886_nth_1__ ?__125894)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__127499)
                         (clojure.core/let
                          [[?__125894] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__125886)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__125886)
                             2)
                            (clojure.core/let
                             [input__125886_nth_0__
                              (clojure.core/nth input__125886 0)
                              input__125886_nth_1__
                              (clojure.core/nth input__125886 1)]
                             (if
                              (clojure.core/seq? input__125886_nth_0__)
                              (clojure.core/let
                               [input__125886_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__125886_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__125886_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__125886_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__125886_nth_0__)]
                                 (clojure.core/let
                                  [!forms
                                   (clojure.core/vec
                                    input__125886_nth_0___r__)]
                                  (clojure.core/let
                                   [?form input__125886_nth_0__]
                                   (clojure.core/let
                                    [?env input__125886_nth_1__]
                                    (try
                                     [(clojure.core/let
                                       [!forms__counter
                                        (meander.runtime.zeta/iterator
                                         !forms)]
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__125969
                                          ['meander.dev.parse.zeta/make-and
                                           (clojure.core/into
                                            []
                                            (clojure.core/loop
                                             [return__125971
                                              (clojure.core/transient
                                               [])]
                                             (if
                                              (clojure.core/and
                                               (.hasNext
                                                !forms__counter))
                                              (recur
                                               (clojure.core/conj!
                                                return__125971
                                                (clojure.core/let
                                                 [CATA_RESULT__15641__auto__
                                                  (CATA__FN__125969
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
                                               return__125971))))
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
                                (state__127499)))
                              (state__127499)))
                            (state__127499))
                           (state__127499)))))
                       (state__127499)))))
                   (state__127499))))
                (state__127499)))
              (state__127499)))
            (state__127499))
           (state__127499))))
        (state__127499
         []
         (if
          (clojure.core/vector? input__125886)
          (if
           (clojure.core/= (clojure.core/count input__125886) 3)
           (clojure.core/let
            [input__125886_nth_0__
             (clojure.core/nth input__125886 0)
             input__125886_nth_1__
             (clojure.core/nth input__125886 1)
             input__125886_nth_2__
             (clojure.core/nth input__125886 2)]
            (clojure.core/case
             input__125886_nth_0__
             (meander.dev.parse.zeta/make-and)
             (clojure.core/letfn
              [(state__127677
                []
                (if
                 (clojure.core/vector? input__125886_nth_1__)
                 (clojure.core/case
                  input__125886_nth_1__
                  ([])
                  (clojure.core/let
                   [?form input__125886_nth_2__]
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
                  (state__127678))
                 (state__127678)))
               (state__127678
                []
                (clojure.core/case
                 input__125886_nth_2__
                 (nil)
                 (if
                  (clojure.core/vector? input__125886_nth_1__)
                  (if
                   (clojure.core/=
                    (clojure.core/count input__125886_nth_1__)
                    1)
                   (clojure.core/let
                    [input__125886_nth_1___nth_0__
                     (clojure.core/nth input__125886_nth_1__ 0)]
                    (clojure.core/let
                     [?ast-a input__125886_nth_1___nth_0__]
                     (try
                      [?ast-a]
                      (catch
                       java.lang.Exception
                       e__16581__auto__
                       (if
                        (meander.runtime.zeta/fail? e__16581__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__16581__auto__))))))
                   (state__127679))
                  (state__127679))
                 (state__127679)))
               (state__127679
                []
                (if
                 (clojure.core/vector? input__125886_nth_1__)
                 (clojure.core/letfn
                  [(state__127680
                    []
                    (if
                     (clojure.core/=
                      (clojure.core/count input__125886_nth_1__)
                      1)
                     (clojure.core/let
                      [input__125886_nth_1___nth_0__
                       (clojure.core/nth input__125886_nth_1__ 0)]
                      (clojure.core/let
                       [?ast-a input__125886_nth_1___nth_0__]
                       (clojure.core/let
                        [?form input__125886_nth_2__]
                        (try
                         [(clojure.core/let
                           [CATA_RESULT__15641__auto__
                            (CATA__FN__125969
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
                     (state__127681)))
                   (state__127681
                    []
                    (if
                     (clojure.core/=
                      (clojure.core/count input__125886_nth_1__)
                      2)
                     (clojure.core/let
                      [input__125886_nth_1___nth_0__
                       (clojure.core/nth input__125886_nth_1__ 0)
                       input__125886_nth_1___nth_1__
                       (clojure.core/nth input__125886_nth_1__ 1)]
                      (clojure.core/let
                       [?ast-a input__125886_nth_1___nth_0__]
                       (clojure.core/let
                        [?ast-b input__125886_nth_1___nth_1__]
                        (clojure.core/let
                         [?form input__125886_nth_2__]
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
                     (state__127682)))
                   (state__127682
                    []
                    (clojure.core/loop
                     [search_space__127683
                      (meander.runtime.zeta/epsilon-partitions
                       2
                       input__125886_nth_1__)]
                     (if
                      (clojure.core/seq search_space__127683)
                      (clojure.core/let
                       [input__125886_nth_1___parts__
                        (clojure.core/first search_space__127683)
                        result__127684
                        (clojure.core/let
                         [input__125886_nth_1___l__
                          (clojure.core/nth
                           input__125886_nth_1___parts__
                           0)
                          input__125886_nth_1___r__
                          (clojure.core/nth
                           input__125886_nth_1___parts__
                           1)]
                         (clojure.core/letfn
                          [(state__127686
                            []
                            (clojure.core/let
                             [!asts-1 []]
                             (clojure.core/let
                              [ret__14502__auto__
                               (meander.runtime.zeta/epsilon-run-star-1
                                input__125886_nth_1___l__
                                [!asts-1]
                                (clojure.core/fn
                                 [[!asts-1] input__126842]
                                 (clojure.core/let
                                  [input__126842_nth_0__
                                   (clojure.core/nth input__126842 0)]
                                  (clojure.core/let
                                   [!asts-1
                                    (clojure.core/conj
                                     !asts-1
                                     input__126842_nth_0__)]
                                   [!asts-1])))
                                (clojure.core/fn
                                 [[!asts-1]]
                                 (clojure.core/let
                                  [X__126835
                                   (clojure.core/count
                                    input__125886_nth_1___l__)]
                                  (clojure.core/let
                                   [?n X__126835]
                                   (clojure.core/let
                                    [X__126839
                                     (clojure.core/count
                                      input__125886_nth_1___r__)]
                                    (if
                                     (clojure.core/= ?n X__126839)
                                     (clojure.core/let
                                      [!asts-2 []]
                                      (clojure.core/let
                                       [ret__14502__auto__
                                        (meander.runtime.zeta/epsilon-run-star-1
                                         input__125886_nth_1___r__
                                         [!asts-2 !asts-1]
                                         (clojure.core/fn
                                          [[!asts-2 !asts-1]
                                           input__126840]
                                          (clojure.core/let
                                           [input__126840_nth_0__
                                            (clojure.core/nth
                                             input__126840
                                             0)]
                                           (clojure.core/let
                                            [!asts-2
                                             (clojure.core/conj
                                              !asts-2
                                              input__126840_nth_0__)]
                                            [!asts-2 !asts-1])))
                                         (clojure.core/fn
                                          [[!asts-2 !asts-1]]
                                          (clojure.core/let
                                           [?form
                                            input__125886_nth_2__]
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
                                                (CATA__FN__125969
                                                 ['meander.dev.parse.zeta/make-and
                                                  [(clojure.core/let
                                                    [CATA_RESULT__15641__auto__
                                                     (CATA__FN__125969
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
                                                     (CATA__FN__125969
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
                                        (state__127687)
                                        ret__14502__auto__)))
                                     (state__127687)))))))]
                              (if
                               (meander.runtime.zeta/fail?
                                ret__14502__auto__)
                               (state__127687)
                               ret__14502__auto__))))
                           (state__127687
                            []
                            (clojure.core/let
                             [!asts-1 []]
                             (clojure.core/let
                              [ret__14502__auto__
                               (meander.runtime.zeta/epsilon-run-star-1
                                input__125886_nth_1___l__
                                [!asts-1]
                                (clojure.core/fn
                                 [[!asts-1] input__126858]
                                 (clojure.core/let
                                  [input__126858_nth_0__
                                   (clojure.core/nth input__126858 0)]
                                  (clojure.core/let
                                   [!asts-1
                                    (clojure.core/conj
                                     !asts-1
                                     input__126858_nth_0__)]
                                   [!asts-1])))
                                (clojure.core/fn
                                 [[!asts-1]]
                                 (clojure.core/let
                                  [X__126849
                                   (clojure.core/count
                                    input__125886_nth_1___l__)]
                                  (clojure.core/let
                                   [?n X__126849]
                                   (clojure.core/let
                                    [input__125886_nth_1___r___l__
                                     (clojure.core/subvec
                                      input__125886_nth_1___r__
                                      0
                                      (clojure.core/min
                                       (clojure.core/count
                                        input__125886_nth_1___r__)
                                       1))]
                                    (if
                                     (clojure.core/=
                                      (clojure.core/count
                                       input__125886_nth_1___r___l__)
                                      1)
                                     (clojure.core/let
                                      [input__125886_nth_1___r___r__
                                       (clojure.core/subvec
                                        input__125886_nth_1___r__
                                        1)]
                                      (clojure.core/let
                                       [input__125886_nth_1___r___l___nth_0__
                                        (clojure.core/nth
                                         input__125886_nth_1___r___l__
                                         0)]
                                       (clojure.core/let
                                        [?ast
                                         input__125886_nth_1___r___l___nth_0__]
                                        (clojure.core/let
                                         [X__126855
                                          (clojure.core/count
                                           input__125886_nth_1___r___r__)]
                                         (if
                                          (clojure.core/= ?n X__126855)
                                          (clojure.core/let
                                           [!asts-2 []]
                                           (clojure.core/let
                                            [ret__14502__auto__
                                             (meander.runtime.zeta/epsilon-run-star-1
                                              input__125886_nth_1___r___r__
                                              [!asts-2 !asts-1]
                                              (clojure.core/fn
                                               [[!asts-2 !asts-1]
                                                input__126856]
                                               (clojure.core/let
                                                [input__126856_nth_0__
                                                 (clojure.core/nth
                                                  input__126856
                                                  0)]
                                                (clojure.core/let
                                                 [!asts-2
                                                  (clojure.core/conj
                                                   !asts-2
                                                   input__126856_nth_0__)]
                                                 [!asts-2 !asts-1])))
                                              (clojure.core/fn
                                               [[!asts-2 !asts-1]]
                                               (clojure.core/let
                                                [?form
                                                 input__125886_nth_2__]
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
                                                     (CATA__FN__125969
                                                      ['meander.dev.parse.zeta/make-and
                                                       [(clojure.core/let
                                                         [CATA_RESULT__15641__auto__
                                                          (CATA__FN__125969
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
                                                          (CATA__FN__125969
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
                          (state__127686)))]
                       (if
                        (meander.runtime.zeta/fail? result__127684)
                        (recur
                         (clojure.core/next search_space__127683))
                        result__127684))
                      (state__127500))))]
                  (state__127680))
                 (state__127500)))]
              (state__127677))
             (state__127500)))
           (state__127500))
          (state__127500)))
        (state__127500
         []
         (clojure.core/letfn
          [(def__126861
            [arg__126884 ?__125895]
            (clojure.core/letfn
             [(state__127692
               []
               (clojure.core/let
                [x__126885 "meander.zeta"]
                (if
                 (clojure.core/= ?__125895 x__126885)
                 [?__125895]
                 (state__127693))))
              (state__127693
               []
               (if
                (clojure.core/map? arg__126884)
                (clojure.core/let
                 [VAL__126886 (.valAt arg__126884 :aliases)]
                 (if
                  (clojure.core/map? VAL__126886)
                  (clojure.core/let
                   [X__126888 (clojure.core/set VAL__126886)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__126888))
                    (clojure.core/loop
                     [search_space__127694
                      (clojure.core/seq X__126888)]
                     (if
                      (clojure.core/seq search_space__127694)
                      (clojure.core/let
                       [elem__126889
                        (clojure.core/first search_space__127694)
                        result__127695
                        (clojure.core/let
                         [elem__126889_nth_0__
                          (clojure.core/nth elem__126889 0)
                          elem__126889_nth_1__
                          (clojure.core/nth elem__126889 1)]
                         (if
                          (clojure.core/symbol? elem__126889_nth_0__)
                          (clojure.core/let
                           [X__126891
                            (clojure.core/name elem__126889_nth_0__)]
                           (if
                            (clojure.core/= ?__125895 X__126891)
                            (if
                             (clojure.core/symbol?
                              elem__126889_nth_1__)
                             (clojure.core/let
                              [X__126893
                               (clojure.core/name
                                elem__126889_nth_1__)]
                              (clojure.core/case
                               X__126893
                               ("meander.zeta")
                               [?__125895]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__127695)
                        (recur
                         (clojure.core/next search_space__127694))
                        result__127695))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__127692)))]
          (if
           (clojure.core/vector? input__125886)
           (if
            (clojure.core/= (clojure.core/count input__125886) 2)
            (clojure.core/let
             [input__125886_nth_0__
              (clojure.core/nth input__125886 0)
              input__125886_nth_1__
              (clojure.core/nth input__125886 1)]
             (if
              (clojure.core/seq? input__125886_nth_0__)
              (clojure.core/let
               [input__125886_nth_0___l__
                (clojure.core/take 1 input__125886_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__125886_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__125886_nth_0___r__
                  (clojure.core/drop 1 input__125886_nth_0__)]
                 (clojure.core/let
                  [input__125886_nth_0___l___nth_0__
                   (clojure.core/nth input__125886_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__125886_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__126871
                     (clojure.core/namespace
                      input__125886_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__125895 X__126871]
                     (clojure.core/let
                      [X__126873
                       (clojure.core/name
                        input__125886_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__126873
                       ("cata")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__126861 input__125886_nth_1__ ?__125895)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__127501)
                         (clojure.core/let
                          [[?__125895] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__125886)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__125886)
                             2)
                            (clojure.core/let
                             [input__125886_nth_0__
                              (clojure.core/nth input__125886 0)
                              input__125886_nth_1__
                              (clojure.core/nth input__125886 1)]
                             (if
                              (clojure.core/seq? input__125886_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__125886_nth_0__)
                                2)
                               (clojure.core/let
                                [input__125886_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__125886_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__125886_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__125886_nth_0__]
                                  (clojure.core/let
                                   [?env input__125886_nth_1__]
                                   (try
                                    [{:tag :cata,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__15641__auto__
                                        (CATA__FN__125969
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
                               (state__127501))
                              (state__127501)))
                            (state__127501))
                           (state__127501)))))
                       (state__127501)))))
                   (state__127501))))
                (state__127501)))
              (state__127501)))
            (state__127501))
           (state__127501))))
        (state__127501
         []
         (clojure.core/letfn
          [(def__126895
            [arg__126918 ?__125896]
            (clojure.core/letfn
             [(state__127697
               []
               (clojure.core/let
                [x__126919 "meander.zeta"]
                (if
                 (clojure.core/= ?__125896 x__126919)
                 [?__125896]
                 (state__127698))))
              (state__127698
               []
               (if
                (clojure.core/map? arg__126918)
                (clojure.core/let
                 [VAL__126920 (.valAt arg__126918 :aliases)]
                 (if
                  (clojure.core/map? VAL__126920)
                  (clojure.core/let
                   [X__126922 (clojure.core/set VAL__126920)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__126922))
                    (clojure.core/loop
                     [search_space__127699
                      (clojure.core/seq X__126922)]
                     (if
                      (clojure.core/seq search_space__127699)
                      (clojure.core/let
                       [elem__126923
                        (clojure.core/first search_space__127699)
                        result__127700
                        (clojure.core/let
                         [elem__126923_nth_0__
                          (clojure.core/nth elem__126923 0)
                          elem__126923_nth_1__
                          (clojure.core/nth elem__126923 1)]
                         (if
                          (clojure.core/symbol? elem__126923_nth_0__)
                          (clojure.core/let
                           [X__126925
                            (clojure.core/name elem__126923_nth_0__)]
                           (if
                            (clojure.core/= ?__125896 X__126925)
                            (if
                             (clojure.core/symbol?
                              elem__126923_nth_1__)
                             (clojure.core/let
                              [X__126927
                               (clojure.core/name
                                elem__126923_nth_1__)]
                              (clojure.core/case
                               X__126927
                               ("meander.zeta")
                               [?__125896]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__127700)
                        (recur
                         (clojure.core/next search_space__127699))
                        result__127700))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__127697)))]
          (if
           (clojure.core/vector? input__125886)
           (if
            (clojure.core/= (clojure.core/count input__125886) 2)
            (clojure.core/let
             [input__125886_nth_0__
              (clojure.core/nth input__125886 0)
              input__125886_nth_1__
              (clojure.core/nth input__125886 1)]
             (if
              (clojure.core/seq? input__125886_nth_0__)
              (clojure.core/let
               [input__125886_nth_0___l__
                (clojure.core/take 1 input__125886_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__125886_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__125886_nth_0___r__
                  (clojure.core/drop 1 input__125886_nth_0__)]
                 (clojure.core/let
                  [input__125886_nth_0___l___nth_0__
                   (clojure.core/nth input__125886_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__125886_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__126905
                     (clojure.core/namespace
                      input__125886_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__125896 X__126905]
                     (clojure.core/let
                      [X__126907
                       (clojure.core/name
                        input__125886_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__126907
                       ("fold")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__126895 input__125886_nth_1__ ?__125896)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__127502)
                         (clojure.core/let
                          [[?__125896] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__125886)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__125886)
                             2)
                            (clojure.core/let
                             [input__125886_nth_0__
                              (clojure.core/nth input__125886 0)
                              input__125886_nth_1__
                              (clojure.core/nth input__125886 1)]
                             (if
                              (clojure.core/seq? input__125886_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 4)
                                 input__125886_nth_0__)
                                4)
                               (clojure.core/let
                                [input__125886_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__125886_nth_0__
                                  1)
                                 input__125886_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__125886_nth_0__
                                  2)
                                 input__125886_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__125886_nth_0__
                                  3)]
                                (clojure.core/let
                                 [?mutable-variable
                                  input__125886_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?initial-value
                                   input__125886_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?fold-function
                                    input__125886_nth_0___nth_3__]
                                   (clojure.core/let
                                    [?form input__125886_nth_0__]
                                    (clojure.core/let
                                     [?env input__125886_nth_1__]
                                     (try
                                      [(clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__125969
                                          ['meander.dev.parse.zeta/make-fold
                                           (clojure.core/let
                                            [CATA_RESULT__15641__auto__
                                             (CATA__FN__125969
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
                                             (CATA__FN__125969
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
                               (state__127502))
                              (state__127502)))
                            (state__127502))
                           (state__127502)))))
                       (state__127502)))))
                   (state__127502))))
                (state__127502)))
              (state__127502)))
            (state__127502))
           (state__127502))))
        (state__127502
         []
         (if
          (clojure.core/vector? input__125886)
          (if
           (clojure.core/= (clojure.core/count input__125886) 5)
           (clojure.core/let
            [input__125886_nth_0__
             (clojure.core/nth input__125886 0)
             input__125886_nth_1__
             (clojure.core/nth input__125886 1)
             input__125886_nth_2__
             (clojure.core/nth input__125886 2)
             input__125886_nth_3__
             (clojure.core/nth input__125886 3)
             input__125886_nth_4__
             (clojure.core/nth input__125886 4)]
            (clojure.core/case
             input__125886_nth_0__
             (meander.dev.parse.zeta/make-fold)
             (if
              (clojure.core/map? input__125886_nth_1__)
              (clojure.core/let
               [VAL__126930 (.valAt input__125886_nth_1__ :tag)]
               (clojure.core/case
                VAL__126930
                (:mutable-variable)
                (clojure.core/let
                 [?variable-ast input__125886_nth_1__]
                 (clojure.core/let
                  [?initial-value-ast input__125886_nth_2__]
                  (clojure.core/let
                   [?fold-function input__125886_nth_3__]
                   (clojure.core/let
                    [?form input__125886_nth_4__]
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
                (state__127503)))
              (state__127503))
             (state__127503)))
           (state__127503))
          (state__127503)))
        (state__127503
         []
         (clojure.core/letfn
          [(def__126932
            [arg__126955 ?__125897]
            (clojure.core/letfn
             [(state__127702
               []
               (clojure.core/let
                [x__126956 "meander.zeta"]
                (if
                 (clojure.core/= ?__125897 x__126956)
                 [?__125897]
                 (state__127703))))
              (state__127703
               []
               (if
                (clojure.core/map? arg__126955)
                (clojure.core/let
                 [VAL__126957 (.valAt arg__126955 :aliases)]
                 (if
                  (clojure.core/map? VAL__126957)
                  (clojure.core/let
                   [X__126959 (clojure.core/set VAL__126957)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__126959))
                    (clojure.core/loop
                     [search_space__127704
                      (clojure.core/seq X__126959)]
                     (if
                      (clojure.core/seq search_space__127704)
                      (clojure.core/let
                       [elem__126960
                        (clojure.core/first search_space__127704)
                        result__127705
                        (clojure.core/let
                         [elem__126960_nth_0__
                          (clojure.core/nth elem__126960 0)
                          elem__126960_nth_1__
                          (clojure.core/nth elem__126960 1)]
                         (if
                          (clojure.core/symbol? elem__126960_nth_0__)
                          (clojure.core/let
                           [X__126962
                            (clojure.core/name elem__126960_nth_0__)]
                           (if
                            (clojure.core/= ?__125897 X__126962)
                            (if
                             (clojure.core/symbol?
                              elem__126960_nth_1__)
                             (clojure.core/let
                              [X__126964
                               (clojure.core/name
                                elem__126960_nth_1__)]
                              (clojure.core/case
                               X__126964
                               ("meander.zeta")
                               [?__125897]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__127705)
                        (recur
                         (clojure.core/next search_space__127704))
                        result__127705))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__127702)))]
          (if
           (clojure.core/vector? input__125886)
           (if
            (clojure.core/= (clojure.core/count input__125886) 2)
            (clojure.core/let
             [input__125886_nth_0__
              (clojure.core/nth input__125886 0)
              input__125886_nth_1__
              (clojure.core/nth input__125886 1)]
             (if
              (clojure.core/seq? input__125886_nth_0__)
              (clojure.core/let
               [input__125886_nth_0___l__
                (clojure.core/take 1 input__125886_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__125886_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__125886_nth_0___r__
                  (clojure.core/drop 1 input__125886_nth_0__)]
                 (clojure.core/let
                  [input__125886_nth_0___l___nth_0__
                   (clojure.core/nth input__125886_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__125886_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__126942
                     (clojure.core/namespace
                      input__125886_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__125897 X__126942]
                     (clojure.core/let
                      [X__126944
                       (clojure.core/name
                        input__125886_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__126944
                       ("keyword")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__126932 input__125886_nth_1__ ?__125897)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__127504)
                         (clojure.core/let
                          [[?__125897] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__125886)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__125886)
                             2)
                            (clojure.core/let
                             [input__125886_nth_0__
                              (clojure.core/nth input__125886 0)
                              input__125886_nth_1__
                              (clojure.core/nth input__125886 1)]
                             (if
                              (clojure.core/seq? input__125886_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__125886_nth_0__)
                                2)
                               (clojure.core/let
                                [input__125886_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__125886_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?name input__125886_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__125886_nth_0__]
                                  (clojure.core/let
                                   [?env input__125886_nth_1__]
                                   (try
                                    [{:tag :keyword,
                                      :name
                                      (clojure.core/let
                                       [CATA_RESULT__15641__auto__
                                        (CATA__FN__125969
                                         [?name ?env])]
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
                               (state__127504))
                              (state__127504)))
                            (state__127504))
                           (state__127504)))))
                       (state__127504)))))
                   (state__127504))))
                (state__127504)))
              (state__127504)))
            (state__127504))
           (state__127504))))
        (state__127504
         []
         (clojure.core/letfn
          [(def__126966
            [arg__126989 ?__125898]
            (clojure.core/letfn
             [(state__127707
               []
               (clojure.core/let
                [x__126990 "meander.zeta"]
                (if
                 (clojure.core/= ?__125898 x__126990)
                 [?__125898]
                 (state__127708))))
              (state__127708
               []
               (if
                (clojure.core/map? arg__126989)
                (clojure.core/let
                 [VAL__126991 (.valAt arg__126989 :aliases)]
                 (if
                  (clojure.core/map? VAL__126991)
                  (clojure.core/let
                   [X__126993 (clojure.core/set VAL__126991)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__126993))
                    (clojure.core/loop
                     [search_space__127709
                      (clojure.core/seq X__126993)]
                     (if
                      (clojure.core/seq search_space__127709)
                      (clojure.core/let
                       [elem__126994
                        (clojure.core/first search_space__127709)
                        result__127710
                        (clojure.core/let
                         [elem__126994_nth_0__
                          (clojure.core/nth elem__126994 0)
                          elem__126994_nth_1__
                          (clojure.core/nth elem__126994 1)]
                         (if
                          (clojure.core/symbol? elem__126994_nth_0__)
                          (clojure.core/let
                           [X__126996
                            (clojure.core/name elem__126994_nth_0__)]
                           (if
                            (clojure.core/= ?__125898 X__126996)
                            (if
                             (clojure.core/symbol?
                              elem__126994_nth_1__)
                             (clojure.core/let
                              [X__126998
                               (clojure.core/name
                                elem__126994_nth_1__)]
                              (clojure.core/case
                               X__126998
                               ("meander.zeta")
                               [?__125898]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__127710)
                        (recur
                         (clojure.core/next search_space__127709))
                        result__127710))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__127707)))]
          (if
           (clojure.core/vector? input__125886)
           (if
            (clojure.core/= (clojure.core/count input__125886) 2)
            (clojure.core/let
             [input__125886_nth_0__
              (clojure.core/nth input__125886 0)
              input__125886_nth_1__
              (clojure.core/nth input__125886 1)]
             (if
              (clojure.core/seq? input__125886_nth_0__)
              (clojure.core/let
               [input__125886_nth_0___l__
                (clojure.core/take 1 input__125886_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__125886_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__125886_nth_0___r__
                  (clojure.core/drop 1 input__125886_nth_0__)]
                 (clojure.core/let
                  [input__125886_nth_0___l___nth_0__
                   (clojure.core/nth input__125886_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__125886_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__126976
                     (clojure.core/namespace
                      input__125886_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__125898 X__126976]
                     (clojure.core/let
                      [X__126978
                       (clojure.core/name
                        input__125886_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__126978
                       ("let")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__126966 input__125886_nth_1__ ?__125898)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__127505)
                         (clojure.core/let
                          [[?__125898] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__125886)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__125886)
                             2)
                            (clojure.core/let
                             [input__125886_nth_0__
                              (clojure.core/nth input__125886 0)
                              input__125886_nth_1__
                              (clojure.core/nth input__125886 1)]
                             (if
                              (clojure.core/seq? input__125886_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__125886_nth_0__)
                                3)
                               (clojure.core/let
                                [input__125886_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__125886_nth_0__
                                  1)
                                 input__125886_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__125886_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?pattern
                                  input__125886_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?expression
                                   input__125886_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__125886_nth_0__]
                                   (clojure.core/let
                                    [?env input__125886_nth_1__]
                                    (try
                                     [{:tag :let,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__125969
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
                               (state__127505))
                              (state__127505)))
                            (state__127505))
                           (state__127505)))))
                       (state__127505)))))
                   (state__127505))))
                (state__127505)))
              (state__127505)))
            (state__127505))
           (state__127505))))
        (state__127505
         []
         (clojure.core/letfn
          [(def__127000
            [arg__127023 ?__125899]
            (clojure.core/letfn
             [(state__127712
               []
               (clojure.core/let
                [x__127024 "meander.zeta"]
                (if
                 (clojure.core/= ?__125899 x__127024)
                 [?__125899]
                 (state__127713))))
              (state__127713
               []
               (if
                (clojure.core/map? arg__127023)
                (clojure.core/let
                 [VAL__127025 (.valAt arg__127023 :aliases)]
                 (if
                  (clojure.core/map? VAL__127025)
                  (clojure.core/let
                   [X__127027 (clojure.core/set VAL__127025)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__127027))
                    (clojure.core/loop
                     [search_space__127714
                      (clojure.core/seq X__127027)]
                     (if
                      (clojure.core/seq search_space__127714)
                      (clojure.core/let
                       [elem__127028
                        (clojure.core/first search_space__127714)
                        result__127715
                        (clojure.core/let
                         [elem__127028_nth_0__
                          (clojure.core/nth elem__127028 0)
                          elem__127028_nth_1__
                          (clojure.core/nth elem__127028 1)]
                         (if
                          (clojure.core/symbol? elem__127028_nth_0__)
                          (clojure.core/let
                           [X__127030
                            (clojure.core/name elem__127028_nth_0__)]
                           (if
                            (clojure.core/= ?__125899 X__127030)
                            (if
                             (clojure.core/symbol?
                              elem__127028_nth_1__)
                             (clojure.core/let
                              [X__127032
                               (clojure.core/name
                                elem__127028_nth_1__)]
                              (clojure.core/case
                               X__127032
                               ("meander.zeta")
                               [?__125899]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__127715)
                        (recur
                         (clojure.core/next search_space__127714))
                        result__127715))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__127712)))]
          (if
           (clojure.core/vector? input__125886)
           (if
            (clojure.core/= (clojure.core/count input__125886) 2)
            (clojure.core/let
             [input__125886_nth_0__
              (clojure.core/nth input__125886 0)
              input__125886_nth_1__
              (clojure.core/nth input__125886 1)]
             (if
              (clojure.core/seq? input__125886_nth_0__)
              (clojure.core/let
               [input__125886_nth_0___l__
                (clojure.core/take 1 input__125886_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__125886_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__125886_nth_0___r__
                  (clojure.core/drop 1 input__125886_nth_0__)]
                 (clojure.core/let
                  [input__125886_nth_0___l___nth_0__
                   (clojure.core/nth input__125886_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__125886_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__127010
                     (clojure.core/namespace
                      input__125886_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__125899 X__127010]
                     (clojure.core/let
                      [X__127012
                       (clojure.core/name
                        input__125886_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__127012
                       ("let")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__127000 input__125886_nth_1__ ?__125899)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__127506)
                         (clojure.core/let
                          [[?__125899] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__125886)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__125886)
                             2)
                            (clojure.core/let
                             [input__125886_nth_0__
                              (clojure.core/nth input__125886 0)
                              input__125886_nth_1__
                              (clojure.core/nth input__125886 1)]
                             (if
                              (clojure.core/seq? input__125886_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 4)
                                 input__125886_nth_0__)
                                4)
                               (clojure.core/let
                                [input__125886_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__125886_nth_0__
                                  1)
                                 input__125886_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__125886_nth_0__
                                  2)
                                 input__125886_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__125886_nth_0__
                                  3)]
                                (clojure.core/let
                                 [?pattern
                                  input__125886_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?expression
                                   input__125886_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?next
                                    input__125886_nth_0___nth_3__]
                                   (clojure.core/let
                                    [?form input__125886_nth_0__]
                                    (clojure.core/let
                                     [?env input__125886_nth_1__]
                                     (try
                                      [{:tag :let,
                                        :pattern
                                        (clojure.core/let
                                         [CATA_RESULT__15641__auto__
                                          (CATA__FN__125969
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
                                          (CATA__FN__125969
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
                               (state__127506))
                              (state__127506)))
                            (state__127506))
                           (state__127506)))))
                       (state__127506)))))
                   (state__127506))))
                (state__127506)))
              (state__127506)))
            (state__127506))
           (state__127506))))
        (state__127506
         []
         (clojure.core/letfn
          [(def__127034
            [arg__127057 ?__125900]
            (clojure.core/letfn
             [(state__127717
               []
               (clojure.core/let
                [x__127058 "meander.zeta"]
                (if
                 (clojure.core/= ?__125900 x__127058)
                 [?__125900]
                 (state__127718))))
              (state__127718
               []
               (if
                (clojure.core/map? arg__127057)
                (clojure.core/let
                 [VAL__127059 (.valAt arg__127057 :aliases)]
                 (if
                  (clojure.core/map? VAL__127059)
                  (clojure.core/let
                   [X__127061 (clojure.core/set VAL__127059)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__127061))
                    (clojure.core/loop
                     [search_space__127719
                      (clojure.core/seq X__127061)]
                     (if
                      (clojure.core/seq search_space__127719)
                      (clojure.core/let
                       [elem__127062
                        (clojure.core/first search_space__127719)
                        result__127720
                        (clojure.core/let
                         [elem__127062_nth_0__
                          (clojure.core/nth elem__127062 0)
                          elem__127062_nth_1__
                          (clojure.core/nth elem__127062 1)]
                         (if
                          (clojure.core/symbol? elem__127062_nth_0__)
                          (clojure.core/let
                           [X__127064
                            (clojure.core/name elem__127062_nth_0__)]
                           (if
                            (clojure.core/= ?__125900 X__127064)
                            (if
                             (clojure.core/symbol?
                              elem__127062_nth_1__)
                             (clojure.core/let
                              [X__127066
                               (clojure.core/name
                                elem__127062_nth_1__)]
                              (clojure.core/case
                               X__127066
                               ("meander.zeta")
                               [?__125900]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__127720)
                        (recur
                         (clojure.core/next search_space__127719))
                        result__127720))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__127717)))]
          (if
           (clojure.core/vector? input__125886)
           (if
            (clojure.core/= (clojure.core/count input__125886) 2)
            (clojure.core/let
             [input__125886_nth_0__
              (clojure.core/nth input__125886 0)
              input__125886_nth_1__
              (clojure.core/nth input__125886 1)]
             (if
              (clojure.core/seq? input__125886_nth_0__)
              (clojure.core/let
               [input__125886_nth_0___l__
                (clojure.core/take 1 input__125886_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__125886_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__125886_nth_0___r__
                  (clojure.core/drop 1 input__125886_nth_0__)]
                 (clojure.core/let
                  [input__125886_nth_0___l___nth_0__
                   (clojure.core/nth input__125886_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__125886_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__127044
                     (clojure.core/namespace
                      input__125886_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__125900 X__127044]
                     (clojure.core/let
                      [X__127046
                       (clojure.core/name
                        input__125886_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__127046
                       ("not")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__127034 input__125886_nth_1__ ?__125900)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__127507)
                         (clojure.core/let
                          [[?__125900] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__125886)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__125886)
                             2)
                            (clojure.core/let
                             [input__125886_nth_0__
                              (clojure.core/nth input__125886 0)
                              input__125886_nth_1__
                              (clojure.core/nth input__125886 1)]
                             (if
                              (clojure.core/seq? input__125886_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__125886_nth_0__)
                                2)
                               (clojure.core/let
                                [input__125886_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__125886_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__125886_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__125886_nth_0__]
                                  (clojure.core/let
                                   [?env input__125886_nth_1__]
                                   (try
                                    [{:tag :not,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__15641__auto__
                                        (CATA__FN__125969
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
                               (state__127507))
                              (state__127507)))
                            (state__127507))
                           (state__127507)))))
                       (state__127507)))))
                   (state__127507))))
                (state__127507)))
              (state__127507)))
            (state__127507))
           (state__127507))))
        (state__127507
         []
         (clojure.core/letfn
          [(def__127068
            [arg__127093 ?__125901]
            (clojure.core/letfn
             [(state__127722
               []
               (clojure.core/let
                [x__127094 "meander.zeta"]
                (if
                 (clojure.core/= ?__125901 x__127094)
                 [?__125901]
                 (state__127723))))
              (state__127723
               []
               (if
                (clojure.core/map? arg__127093)
                (clojure.core/let
                 [VAL__127095 (.valAt arg__127093 :aliases)]
                 (if
                  (clojure.core/map? VAL__127095)
                  (clojure.core/let
                   [X__127097 (clojure.core/set VAL__127095)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__127097))
                    (clojure.core/loop
                     [search_space__127724
                      (clojure.core/seq X__127097)]
                     (if
                      (clojure.core/seq search_space__127724)
                      (clojure.core/let
                       [elem__127098
                        (clojure.core/first search_space__127724)
                        result__127725
                        (clojure.core/let
                         [elem__127098_nth_0__
                          (clojure.core/nth elem__127098 0)
                          elem__127098_nth_1__
                          (clojure.core/nth elem__127098 1)]
                         (if
                          (clojure.core/symbol? elem__127098_nth_0__)
                          (clojure.core/let
                           [X__127100
                            (clojure.core/name elem__127098_nth_0__)]
                           (if
                            (clojure.core/= ?__125901 X__127100)
                            (if
                             (clojure.core/symbol?
                              elem__127098_nth_1__)
                             (clojure.core/let
                              [X__127102
                               (clojure.core/name
                                elem__127098_nth_1__)]
                              (clojure.core/case
                               X__127102
                               ("meander.zeta")
                               [?__125901]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__127725)
                        (recur
                         (clojure.core/next search_space__127724))
                        result__127725))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__127722)))]
          (if
           (clojure.core/vector? input__125886)
           (if
            (clojure.core/= (clojure.core/count input__125886) 2)
            (clojure.core/let
             [input__125886_nth_0__
              (clojure.core/nth input__125886 0)
              input__125886_nth_1__
              (clojure.core/nth input__125886 1)]
             (if
              (clojure.core/seq? input__125886_nth_0__)
              (clojure.core/let
               [input__125886_nth_0___l__
                (clojure.core/take 1 input__125886_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__125886_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__125886_nth_0___r__
                  (clojure.core/drop 1 input__125886_nth_0__)]
                 (clojure.core/let
                  [input__125886_nth_0___l___nth_0__
                   (clojure.core/nth input__125886_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__125886_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__127080
                     (clojure.core/namespace
                      input__125886_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__125901 X__127080]
                     (clojure.core/let
                      [X__127082
                       (clojure.core/name
                        input__125886_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__127082
                       ("or")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__127068 input__125886_nth_1__ ?__125901)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__127508)
                         (clojure.core/let
                          [[?__125901] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__125886)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__125886)
                             2)
                            (clojure.core/let
                             [input__125886_nth_0__
                              (clojure.core/nth input__125886 0)
                              input__125886_nth_1__
                              (clojure.core/nth input__125886 1)]
                             (if
                              (clojure.core/seq? input__125886_nth_0__)
                              (clojure.core/let
                               [input__125886_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__125886_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__125886_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__125886_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__125886_nth_0__)]
                                 (clojure.core/let
                                  [!forms
                                   (clojure.core/vec
                                    input__125886_nth_0___r__)]
                                  (clojure.core/let
                                   [?form input__125886_nth_0__]
                                   (clojure.core/let
                                    [?env input__125886_nth_1__]
                                    (try
                                     [(clojure.core/let
                                       [!forms__counter
                                        (meander.runtime.zeta/iterator
                                         !forms)]
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__125969
                                          ['meander.dev.parse.zeta/make-or
                                           (clojure.core/into
                                            []
                                            (clojure.core/loop
                                             [return__125972
                                              (clojure.core/transient
                                               [])]
                                             (if
                                              (clojure.core/and
                                               (.hasNext
                                                !forms__counter))
                                              (recur
                                               (clojure.core/conj!
                                                return__125972
                                                (clojure.core/let
                                                 [CATA_RESULT__15641__auto__
                                                  (CATA__FN__125969
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
                                               return__125972))))
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
                                (state__127508)))
                              (state__127508)))
                            (state__127508))
                           (state__127508)))))
                       (state__127508)))))
                   (state__127508))))
                (state__127508)))
              (state__127508)))
            (state__127508))
           (state__127508))))
        (state__127508
         []
         (if
          (clojure.core/vector? input__125886)
          (if
           (clojure.core/= (clojure.core/count input__125886) 3)
           (clojure.core/let
            [input__125886_nth_0__
             (clojure.core/nth input__125886 0)
             input__125886_nth_1__
             (clojure.core/nth input__125886 1)
             input__125886_nth_2__
             (clojure.core/nth input__125886 2)]
            (clojure.core/case
             input__125886_nth_0__
             (meander.dev.parse.zeta/make-or)
             (clojure.core/letfn
              [(state__127727
                []
                (if
                 (clojure.core/vector? input__125886_nth_1__)
                 (clojure.core/case
                  input__125886_nth_1__
                  ([])
                  (clojure.core/let
                   [?form input__125886_nth_2__]
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
                  (state__127728))
                 (state__127728)))
               (state__127728
                []
                (clojure.core/case
                 input__125886_nth_2__
                 (nil)
                 (if
                  (clojure.core/vector? input__125886_nth_1__)
                  (if
                   (clojure.core/=
                    (clojure.core/count input__125886_nth_1__)
                    1)
                   (clojure.core/let
                    [input__125886_nth_1___nth_0__
                     (clojure.core/nth input__125886_nth_1__ 0)]
                    (clojure.core/let
                     [?ast-a input__125886_nth_1___nth_0__]
                     (try
                      [?ast-a]
                      (catch
                       java.lang.Exception
                       e__16581__auto__
                       (if
                        (meander.runtime.zeta/fail? e__16581__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__16581__auto__))))))
                   (state__127729))
                  (state__127729))
                 (state__127729)))
               (state__127729
                []
                (if
                 (clojure.core/vector? input__125886_nth_1__)
                 (clojure.core/letfn
                  [(state__127730
                    []
                    (if
                     (clojure.core/=
                      (clojure.core/count input__125886_nth_1__)
                      1)
                     (clojure.core/let
                      [input__125886_nth_1___nth_0__
                       (clojure.core/nth input__125886_nth_1__ 0)]
                      (clojure.core/let
                       [?ast-a input__125886_nth_1___nth_0__]
                       (clojure.core/let
                        [?form input__125886_nth_2__]
                        (try
                         [(clojure.core/let
                           [CATA_RESULT__15641__auto__
                            (CATA__FN__125969
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
                     (state__127731)))
                   (state__127731
                    []
                    (if
                     (clojure.core/=
                      (clojure.core/count input__125886_nth_1__)
                      2)
                     (clojure.core/let
                      [input__125886_nth_1___nth_0__
                       (clojure.core/nth input__125886_nth_1__ 0)
                       input__125886_nth_1___nth_1__
                       (clojure.core/nth input__125886_nth_1__ 1)]
                      (clojure.core/let
                       [?ast-a input__125886_nth_1___nth_0__]
                       (clojure.core/let
                        [?ast-b input__125886_nth_1___nth_1__]
                        (clojure.core/let
                         [?form input__125886_nth_2__]
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
                     (state__127732)))
                   (state__127732
                    []
                    (clojure.core/loop
                     [search_space__127733
                      (meander.runtime.zeta/epsilon-partitions
                       2
                       input__125886_nth_1__)]
                     (if
                      (clojure.core/seq search_space__127733)
                      (clojure.core/let
                       [input__125886_nth_1___parts__
                        (clojure.core/first search_space__127733)
                        result__127734
                        (clojure.core/let
                         [input__125886_nth_1___l__
                          (clojure.core/nth
                           input__125886_nth_1___parts__
                           0)
                          input__125886_nth_1___r__
                          (clojure.core/nth
                           input__125886_nth_1___parts__
                           1)]
                         (clojure.core/letfn
                          [(state__127736
                            []
                            (clojure.core/let
                             [!asts-1 []]
                             (clojure.core/let
                              [ret__14502__auto__
                               (meander.runtime.zeta/epsilon-run-star-1
                                input__125886_nth_1___l__
                                [!asts-1]
                                (clojure.core/fn
                                 [[!asts-1] input__127129]
                                 (clojure.core/let
                                  [input__127129_nth_0__
                                   (clojure.core/nth input__127129 0)]
                                  (clojure.core/let
                                   [!asts-1
                                    (clojure.core/conj
                                     !asts-1
                                     input__127129_nth_0__)]
                                   [!asts-1])))
                                (clojure.core/fn
                                 [[!asts-1]]
                                 (clojure.core/let
                                  [X__127122
                                   (clojure.core/count
                                    input__125886_nth_1___l__)]
                                  (clojure.core/let
                                   [?n X__127122]
                                   (clojure.core/let
                                    [X__127126
                                     (clojure.core/count
                                      input__125886_nth_1___r__)]
                                    (if
                                     (clojure.core/= ?n X__127126)
                                     (clojure.core/let
                                      [!asts-2 []]
                                      (clojure.core/let
                                       [ret__14502__auto__
                                        (meander.runtime.zeta/epsilon-run-star-1
                                         input__125886_nth_1___r__
                                         [!asts-2 !asts-1]
                                         (clojure.core/fn
                                          [[!asts-2 !asts-1]
                                           input__127127]
                                          (clojure.core/let
                                           [input__127127_nth_0__
                                            (clojure.core/nth
                                             input__127127
                                             0)]
                                           (clojure.core/let
                                            [!asts-2
                                             (clojure.core/conj
                                              !asts-2
                                              input__127127_nth_0__)]
                                            [!asts-2 !asts-1])))
                                         (clojure.core/fn
                                          [[!asts-2 !asts-1]]
                                          (clojure.core/let
                                           [?form
                                            input__125886_nth_2__]
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
                                                (CATA__FN__125969
                                                 ['meander.dev.parse.zeta/make-or
                                                  [(clojure.core/let
                                                    [CATA_RESULT__15641__auto__
                                                     (CATA__FN__125969
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
                                                     (CATA__FN__125969
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
                                        (state__127737)
                                        ret__14502__auto__)))
                                     (state__127737)))))))]
                              (if
                               (meander.runtime.zeta/fail?
                                ret__14502__auto__)
                               (state__127737)
                               ret__14502__auto__))))
                           (state__127737
                            []
                            (clojure.core/let
                             [!asts-1 []]
                             (clojure.core/let
                              [ret__14502__auto__
                               (meander.runtime.zeta/epsilon-run-star-1
                                input__125886_nth_1___l__
                                [!asts-1]
                                (clojure.core/fn
                                 [[!asts-1] input__127145]
                                 (clojure.core/let
                                  [input__127145_nth_0__
                                   (clojure.core/nth input__127145 0)]
                                  (clojure.core/let
                                   [!asts-1
                                    (clojure.core/conj
                                     !asts-1
                                     input__127145_nth_0__)]
                                   [!asts-1])))
                                (clojure.core/fn
                                 [[!asts-1]]
                                 (clojure.core/let
                                  [X__127136
                                   (clojure.core/count
                                    input__125886_nth_1___l__)]
                                  (clojure.core/let
                                   [?n X__127136]
                                   (clojure.core/let
                                    [input__125886_nth_1___r___l__
                                     (clojure.core/subvec
                                      input__125886_nth_1___r__
                                      0
                                      (clojure.core/min
                                       (clojure.core/count
                                        input__125886_nth_1___r__)
                                       1))]
                                    (if
                                     (clojure.core/=
                                      (clojure.core/count
                                       input__125886_nth_1___r___l__)
                                      1)
                                     (clojure.core/let
                                      [input__125886_nth_1___r___r__
                                       (clojure.core/subvec
                                        input__125886_nth_1___r__
                                        1)]
                                      (clojure.core/let
                                       [input__125886_nth_1___r___l___nth_0__
                                        (clojure.core/nth
                                         input__125886_nth_1___r___l__
                                         0)]
                                       (clojure.core/let
                                        [?ast
                                         input__125886_nth_1___r___l___nth_0__]
                                        (clojure.core/let
                                         [X__127142
                                          (clojure.core/count
                                           input__125886_nth_1___r___r__)]
                                         (if
                                          (clojure.core/= ?n X__127142)
                                          (clojure.core/let
                                           [!asts-2 []]
                                           (clojure.core/let
                                            [ret__14502__auto__
                                             (meander.runtime.zeta/epsilon-run-star-1
                                              input__125886_nth_1___r___r__
                                              [!asts-2 !asts-1]
                                              (clojure.core/fn
                                               [[!asts-2 !asts-1]
                                                input__127143]
                                               (clojure.core/let
                                                [input__127143_nth_0__
                                                 (clojure.core/nth
                                                  input__127143
                                                  0)]
                                                (clojure.core/let
                                                 [!asts-2
                                                  (clojure.core/conj
                                                   !asts-2
                                                   input__127143_nth_0__)]
                                                 [!asts-2 !asts-1])))
                                              (clojure.core/fn
                                               [[!asts-2 !asts-1]]
                                               (clojure.core/let
                                                [?form
                                                 input__125886_nth_2__]
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
                                                     (CATA__FN__125969
                                                      ['meander.dev.parse.zeta/make-or
                                                       [(clojure.core/let
                                                         [CATA_RESULT__15641__auto__
                                                          (CATA__FN__125969
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
                                                          (CATA__FN__125969
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
                          (state__127736)))]
                       (if
                        (meander.runtime.zeta/fail? result__127734)
                        (recur
                         (clojure.core/next search_space__127733))
                        result__127734))
                      (state__127509))))]
                  (state__127730))
                 (state__127509)))]
              (state__127727))
             (state__127509)))
           (state__127509))
          (state__127509)))
        (state__127509
         []
         (clojure.core/letfn
          [(def__127148
            [arg__127171 ?__125902]
            (clojure.core/letfn
             [(state__127742
               []
               (clojure.core/let
                [x__127172 "meander.zeta"]
                (if
                 (clojure.core/= ?__125902 x__127172)
                 [?__125902]
                 (state__127743))))
              (state__127743
               []
               (if
                (clojure.core/map? arg__127171)
                (clojure.core/let
                 [VAL__127173 (.valAt arg__127171 :aliases)]
                 (if
                  (clojure.core/map? VAL__127173)
                  (clojure.core/let
                   [X__127175 (clojure.core/set VAL__127173)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__127175))
                    (clojure.core/loop
                     [search_space__127744
                      (clojure.core/seq X__127175)]
                     (if
                      (clojure.core/seq search_space__127744)
                      (clojure.core/let
                       [elem__127176
                        (clojure.core/first search_space__127744)
                        result__127745
                        (clojure.core/let
                         [elem__127176_nth_0__
                          (clojure.core/nth elem__127176 0)
                          elem__127176_nth_1__
                          (clojure.core/nth elem__127176 1)]
                         (if
                          (clojure.core/symbol? elem__127176_nth_0__)
                          (clojure.core/let
                           [X__127178
                            (clojure.core/name elem__127176_nth_0__)]
                           (if
                            (clojure.core/= ?__125902 X__127178)
                            (if
                             (clojure.core/symbol?
                              elem__127176_nth_1__)
                             (clojure.core/let
                              [X__127180
                               (clojure.core/name
                                elem__127176_nth_1__)]
                              (clojure.core/case
                               X__127180
                               ("meander.zeta")
                               [?__125902]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__127745)
                        (recur
                         (clojure.core/next search_space__127744))
                        result__127745))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__127742)))]
          (if
           (clojure.core/vector? input__125886)
           (if
            (clojure.core/= (clojure.core/count input__125886) 2)
            (clojure.core/let
             [input__125886_nth_0__
              (clojure.core/nth input__125886 0)
              input__125886_nth_1__
              (clojure.core/nth input__125886 1)]
             (if
              (clojure.core/seq? input__125886_nth_0__)
              (clojure.core/let
               [input__125886_nth_0___l__
                (clojure.core/take 1 input__125886_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__125886_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__125886_nth_0___r__
                  (clojure.core/drop 1 input__125886_nth_0__)]
                 (clojure.core/let
                  [input__125886_nth_0___l___nth_0__
                   (clojure.core/nth input__125886_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__125886_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__127158
                     (clojure.core/namespace
                      input__125886_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__125902 X__127158]
                     (clojure.core/let
                      [X__127160
                       (clojure.core/name
                        input__125886_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__127160
                       ("pred")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__127148 input__125886_nth_1__ ?__125902)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__127510)
                         (clojure.core/let
                          [[?__125902] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__125886)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__125886)
                             2)
                            (clojure.core/let
                             [input__125886_nth_0__
                              (clojure.core/nth input__125886 0)
                              input__125886_nth_1__
                              (clojure.core/nth input__125886 1)]
                             (if
                              (clojure.core/seq? input__125886_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__125886_nth_0__)
                                2)
                               (clojure.core/let
                                [input__125886_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__125886_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?expression
                                  input__125886_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__125886_nth_0__]
                                  (clojure.core/let
                                   [?env input__125886_nth_1__]
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
                               (state__127510))
                              (state__127510)))
                            (state__127510))
                           (state__127510)))))
                       (state__127510)))))
                   (state__127510))))
                (state__127510)))
              (state__127510)))
            (state__127510))
           (state__127510))))
        (state__127510
         []
         (clojure.core/letfn
          [(def__127182
            [arg__127205 ?__125903]
            (clojure.core/letfn
             [(state__127747
               []
               (clojure.core/let
                [x__127206 "meander.zeta"]
                (if
                 (clojure.core/= ?__125903 x__127206)
                 [?__125903]
                 (state__127748))))
              (state__127748
               []
               (if
                (clojure.core/map? arg__127205)
                (clojure.core/let
                 [VAL__127207 (.valAt arg__127205 :aliases)]
                 (if
                  (clojure.core/map? VAL__127207)
                  (clojure.core/let
                   [X__127209 (clojure.core/set VAL__127207)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__127209))
                    (clojure.core/loop
                     [search_space__127749
                      (clojure.core/seq X__127209)]
                     (if
                      (clojure.core/seq search_space__127749)
                      (clojure.core/let
                       [elem__127210
                        (clojure.core/first search_space__127749)
                        result__127750
                        (clojure.core/let
                         [elem__127210_nth_0__
                          (clojure.core/nth elem__127210 0)
                          elem__127210_nth_1__
                          (clojure.core/nth elem__127210 1)]
                         (if
                          (clojure.core/symbol? elem__127210_nth_0__)
                          (clojure.core/let
                           [X__127212
                            (clojure.core/name elem__127210_nth_0__)]
                           (if
                            (clojure.core/= ?__125903 X__127212)
                            (if
                             (clojure.core/symbol?
                              elem__127210_nth_1__)
                             (clojure.core/let
                              [X__127214
                               (clojure.core/name
                                elem__127210_nth_1__)]
                              (clojure.core/case
                               X__127214
                               ("meander.zeta")
                               [?__125903]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__127750)
                        (recur
                         (clojure.core/next search_space__127749))
                        result__127750))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__127747)))]
          (if
           (clojure.core/vector? input__125886)
           (if
            (clojure.core/= (clojure.core/count input__125886) 2)
            (clojure.core/let
             [input__125886_nth_0__
              (clojure.core/nth input__125886 0)
              input__125886_nth_1__
              (clojure.core/nth input__125886 1)]
             (if
              (clojure.core/seq? input__125886_nth_0__)
              (clojure.core/let
               [input__125886_nth_0___l__
                (clojure.core/take 1 input__125886_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__125886_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__125886_nth_0___r__
                  (clojure.core/drop 1 input__125886_nth_0__)]
                 (clojure.core/let
                  [input__125886_nth_0___l___nth_0__
                   (clojure.core/nth input__125886_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__125886_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__127192
                     (clojure.core/namespace
                      input__125886_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__125903 X__127192]
                     (clojure.core/let
                      [X__127194
                       (clojure.core/name
                        input__125886_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__127194
                       ("pred")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__127182 input__125886_nth_1__ ?__125903)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__127511)
                         (clojure.core/let
                          [[?__125903] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__125886)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__125886)
                             2)
                            (clojure.core/let
                             [input__125886_nth_0__
                              (clojure.core/nth input__125886 0)
                              input__125886_nth_1__
                              (clojure.core/nth input__125886 1)]
                             (if
                              (clojure.core/seq? input__125886_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__125886_nth_0__)
                                3)
                               (clojure.core/let
                                [input__125886_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__125886_nth_0__
                                  1)
                                 input__125886_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__125886_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?expression
                                  input__125886_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?pattern
                                   input__125886_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__125886_nth_0__]
                                   (clojure.core/let
                                    [?env input__125886_nth_1__]
                                    (try
                                     [{:tag :pred,
                                       :expression
                                       {:tag :host-expression,
                                        :form ?expression},
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__125969
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
                               (state__127511))
                              (state__127511)))
                            (state__127511))
                           (state__127511)))))
                       (state__127511)))))
                   (state__127511))))
                (state__127511)))
              (state__127511)))
            (state__127511))
           (state__127511))))
        (state__127511
         []
         (clojure.core/letfn
          [(def__127216
            [arg__127239 ?__125904]
            (clojure.core/letfn
             [(state__127752
               []
               (clojure.core/let
                [x__127240 "meander.zeta"]
                (if
                 (clojure.core/= ?__125904 x__127240)
                 [?__125904]
                 (state__127753))))
              (state__127753
               []
               (if
                (clojure.core/map? arg__127239)
                (clojure.core/let
                 [VAL__127241 (.valAt arg__127239 :aliases)]
                 (if
                  (clojure.core/map? VAL__127241)
                  (clojure.core/let
                   [X__127243 (clojure.core/set VAL__127241)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__127243))
                    (clojure.core/loop
                     [search_space__127754
                      (clojure.core/seq X__127243)]
                     (if
                      (clojure.core/seq search_space__127754)
                      (clojure.core/let
                       [elem__127244
                        (clojure.core/first search_space__127754)
                        result__127755
                        (clojure.core/let
                         [elem__127244_nth_0__
                          (clojure.core/nth elem__127244 0)
                          elem__127244_nth_1__
                          (clojure.core/nth elem__127244 1)]
                         (if
                          (clojure.core/symbol? elem__127244_nth_0__)
                          (clojure.core/let
                           [X__127246
                            (clojure.core/name elem__127244_nth_0__)]
                           (if
                            (clojure.core/= ?__125904 X__127246)
                            (if
                             (clojure.core/symbol?
                              elem__127244_nth_1__)
                             (clojure.core/let
                              [X__127248
                               (clojure.core/name
                                elem__127244_nth_1__)]
                              (clojure.core/case
                               X__127248
                               ("meander.zeta")
                               [?__125904]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__127755)
                        (recur
                         (clojure.core/next search_space__127754))
                        result__127755))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__127752)))]
          (if
           (clojure.core/vector? input__125886)
           (if
            (clojure.core/= (clojure.core/count input__125886) 2)
            (clojure.core/let
             [input__125886_nth_0__
              (clojure.core/nth input__125886 0)
              input__125886_nth_1__
              (clojure.core/nth input__125886 1)]
             (if
              (clojure.core/seq? input__125886_nth_0__)
              (clojure.core/let
               [input__125886_nth_0___l__
                (clojure.core/take 1 input__125886_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__125886_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__125886_nth_0___r__
                  (clojure.core/drop 1 input__125886_nth_0__)]
                 (clojure.core/let
                  [input__125886_nth_0___l___nth_0__
                   (clojure.core/nth input__125886_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__125886_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__127226
                     (clojure.core/namespace
                      input__125886_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__125904 X__127226]
                     (clojure.core/let
                      [X__127228
                       (clojure.core/name
                        input__125886_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__127228
                       ("re")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__127216 input__125886_nth_1__ ?__125904)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__127512)
                         (clojure.core/let
                          [[?__125904] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__125886)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__125886)
                             2)
                            (clojure.core/let
                             [input__125886_nth_0__
                              (clojure.core/nth input__125886 0)
                              input__125886_nth_1__
                              (clojure.core/nth input__125886 1)]
                             (if
                              (clojure.core/seq? input__125886_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__125886_nth_0__)
                                2)
                               (clojure.core/let
                                [input__125886_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__125886_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?regex input__125886_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__125886_nth_0__]
                                  (clojure.core/let
                                   [?env input__125886_nth_1__]
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
                               (state__127512))
                              (state__127512)))
                            (state__127512))
                           (state__127512)))))
                       (state__127512)))))
                   (state__127512))))
                (state__127512)))
              (state__127512)))
            (state__127512))
           (state__127512))))
        (state__127512
         []
         (clojure.core/letfn
          [(def__127250
            [arg__127273 ?__125905]
            (clojure.core/letfn
             [(state__127757
               []
               (clojure.core/let
                [x__127274 "meander.zeta"]
                (if
                 (clojure.core/= ?__125905 x__127274)
                 [?__125905]
                 (state__127758))))
              (state__127758
               []
               (if
                (clojure.core/map? arg__127273)
                (clojure.core/let
                 [VAL__127275 (.valAt arg__127273 :aliases)]
                 (if
                  (clojure.core/map? VAL__127275)
                  (clojure.core/let
                   [X__127277 (clojure.core/set VAL__127275)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__127277))
                    (clojure.core/loop
                     [search_space__127759
                      (clojure.core/seq X__127277)]
                     (if
                      (clojure.core/seq search_space__127759)
                      (clojure.core/let
                       [elem__127278
                        (clojure.core/first search_space__127759)
                        result__127760
                        (clojure.core/let
                         [elem__127278_nth_0__
                          (clojure.core/nth elem__127278 0)
                          elem__127278_nth_1__
                          (clojure.core/nth elem__127278 1)]
                         (if
                          (clojure.core/symbol? elem__127278_nth_0__)
                          (clojure.core/let
                           [X__127280
                            (clojure.core/name elem__127278_nth_0__)]
                           (if
                            (clojure.core/= ?__125905 X__127280)
                            (if
                             (clojure.core/symbol?
                              elem__127278_nth_1__)
                             (clojure.core/let
                              [X__127282
                               (clojure.core/name
                                elem__127278_nth_1__)]
                              (clojure.core/case
                               X__127282
                               ("meander.zeta")
                               [?__125905]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__127760)
                        (recur
                         (clojure.core/next search_space__127759))
                        result__127760))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__127757)))]
          (if
           (clojure.core/vector? input__125886)
           (if
            (clojure.core/= (clojure.core/count input__125886) 2)
            (clojure.core/let
             [input__125886_nth_0__
              (clojure.core/nth input__125886 0)
              input__125886_nth_1__
              (clojure.core/nth input__125886 1)]
             (if
              (clojure.core/seq? input__125886_nth_0__)
              (clojure.core/let
               [input__125886_nth_0___l__
                (clojure.core/take 1 input__125886_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__125886_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__125886_nth_0___r__
                  (clojure.core/drop 1 input__125886_nth_0__)]
                 (clojure.core/let
                  [input__125886_nth_0___l___nth_0__
                   (clojure.core/nth input__125886_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__125886_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__127260
                     (clojure.core/namespace
                      input__125886_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__125905 X__127260]
                     (clojure.core/let
                      [X__127262
                       (clojure.core/name
                        input__125886_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__127262
                       ("re")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__127250 input__125886_nth_1__ ?__125905)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__127513)
                         (clojure.core/let
                          [[?__125905] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__125886)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__125886)
                             2)
                            (clojure.core/let
                             [input__125886_nth_0__
                              (clojure.core/nth input__125886 0)
                              input__125886_nth_1__
                              (clojure.core/nth input__125886 1)]
                             (if
                              (clojure.core/seq? input__125886_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__125886_nth_0__)
                                3)
                               (clojure.core/let
                                [input__125886_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__125886_nth_0__
                                  1)
                                 input__125886_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__125886_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?regex input__125886_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?capture
                                   input__125886_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__125886_nth_0__]
                                   (clojure.core/let
                                    [?env input__125886_nth_1__]
                                    (try
                                     [{:tag :regex,
                                       :regex ?regex,
                                       :capture
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__125969
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
                               (state__127513))
                              (state__127513)))
                            (state__127513))
                           (state__127513)))))
                       (state__127513)))))
                   (state__127513))))
                (state__127513)))
              (state__127513)))
            (state__127513))
           (state__127513))))
        (state__127513
         []
         (clojure.core/letfn
          [(def__127284
            [arg__127307 ?__125906]
            (clojure.core/letfn
             [(state__127762
               []
               (clojure.core/let
                [x__127308 "meander.zeta"]
                (if
                 (clojure.core/= ?__125906 x__127308)
                 [?__125906]
                 (state__127763))))
              (state__127763
               []
               (if
                (clojure.core/map? arg__127307)
                (clojure.core/let
                 [VAL__127309 (.valAt arg__127307 :aliases)]
                 (if
                  (clojure.core/map? VAL__127309)
                  (clojure.core/let
                   [X__127311 (clojure.core/set VAL__127309)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__127311))
                    (clojure.core/loop
                     [search_space__127764
                      (clojure.core/seq X__127311)]
                     (if
                      (clojure.core/seq search_space__127764)
                      (clojure.core/let
                       [elem__127312
                        (clojure.core/first search_space__127764)
                        result__127765
                        (clojure.core/let
                         [elem__127312_nth_0__
                          (clojure.core/nth elem__127312 0)
                          elem__127312_nth_1__
                          (clojure.core/nth elem__127312 1)]
                         (if
                          (clojure.core/symbol? elem__127312_nth_0__)
                          (clojure.core/let
                           [X__127314
                            (clojure.core/name elem__127312_nth_0__)]
                           (if
                            (clojure.core/= ?__125906 X__127314)
                            (if
                             (clojure.core/symbol?
                              elem__127312_nth_1__)
                             (clojure.core/let
                              [X__127316
                               (clojure.core/name
                                elem__127312_nth_1__)]
                              (clojure.core/case
                               X__127316
                               ("meander.zeta")
                               [?__125906]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__127765)
                        (recur
                         (clojure.core/next search_space__127764))
                        result__127765))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__127762)))]
          (if
           (clojure.core/vector? input__125886)
           (if
            (clojure.core/= (clojure.core/count input__125886) 2)
            (clojure.core/let
             [input__125886_nth_0__
              (clojure.core/nth input__125886 0)
              input__125886_nth_1__
              (clojure.core/nth input__125886 1)]
             (if
              (clojure.core/seq? input__125886_nth_0__)
              (clojure.core/let
               [input__125886_nth_0___l__
                (clojure.core/take 1 input__125886_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__125886_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__125886_nth_0___r__
                  (clojure.core/drop 1 input__125886_nth_0__)]
                 (clojure.core/let
                  [input__125886_nth_0___l___nth_0__
                   (clojure.core/nth input__125886_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__125886_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__127294
                     (clojure.core/namespace
                      input__125886_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__125906 X__127294]
                     (clojure.core/let
                      [X__127296
                       (clojure.core/name
                        input__125886_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__127296
                       ("string")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__127284 input__125886_nth_1__ ?__125906)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__127514)
                         (clojure.core/let
                          [[?__125906] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__125886)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__125886)
                             2)
                            (clojure.core/let
                             [input__125886_nth_0__
                              (clojure.core/nth input__125886 0)
                              input__125886_nth_1__
                              (clojure.core/nth input__125886 1)]
                             (if
                              (clojure.core/seq? input__125886_nth_0__)
                              (clojure.core/let
                               [input__125886_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__125886_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__125886_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__125886_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__125886_nth_0__)]
                                 (clojure.core/let
                                  [?sequence input__125886_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__125886_nth_0__]
                                   (clojure.core/let
                                    [?env input__125886_nth_1__]
                                    (try
                                     [{:tag :string,
                                       :next
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__125969
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
                                (state__127514)))
                              (state__127514)))
                            (state__127514))
                           (state__127514)))))
                       (state__127514)))))
                   (state__127514))))
                (state__127514)))
              (state__127514)))
            (state__127514))
           (state__127514))))
        (state__127514
         []
         (clojure.core/letfn
          [(def__127318
            [arg__127341 ?__125907]
            (clojure.core/letfn
             [(state__127767
               []
               (clojure.core/let
                [x__127342 "meander.zeta"]
                (if
                 (clojure.core/= ?__125907 x__127342)
                 [?__125907]
                 (state__127768))))
              (state__127768
               []
               (if
                (clojure.core/map? arg__127341)
                (clojure.core/let
                 [VAL__127343 (.valAt arg__127341 :aliases)]
                 (if
                  (clojure.core/map? VAL__127343)
                  (clojure.core/let
                   [X__127345 (clojure.core/set VAL__127343)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__127345))
                    (clojure.core/loop
                     [search_space__127769
                      (clojure.core/seq X__127345)]
                     (if
                      (clojure.core/seq search_space__127769)
                      (clojure.core/let
                       [elem__127346
                        (clojure.core/first search_space__127769)
                        result__127770
                        (clojure.core/let
                         [elem__127346_nth_0__
                          (clojure.core/nth elem__127346 0)
                          elem__127346_nth_1__
                          (clojure.core/nth elem__127346 1)]
                         (if
                          (clojure.core/symbol? elem__127346_nth_0__)
                          (clojure.core/let
                           [X__127348
                            (clojure.core/name elem__127346_nth_0__)]
                           (if
                            (clojure.core/= ?__125907 X__127348)
                            (if
                             (clojure.core/symbol?
                              elem__127346_nth_1__)
                             (clojure.core/let
                              [X__127350
                               (clojure.core/name
                                elem__127346_nth_1__)]
                              (clojure.core/case
                               X__127350
                               ("meander.zeta")
                               [?__125907]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__127770)
                        (recur
                         (clojure.core/next search_space__127769))
                        result__127770))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__127767)))]
          (if
           (clojure.core/vector? input__125886)
           (if
            (clojure.core/= (clojure.core/count input__125886) 2)
            (clojure.core/let
             [input__125886_nth_0__
              (clojure.core/nth input__125886 0)
              input__125886_nth_1__
              (clojure.core/nth input__125886 1)]
             (if
              (clojure.core/seq? input__125886_nth_0__)
              (clojure.core/let
               [input__125886_nth_0___l__
                (clojure.core/take 1 input__125886_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__125886_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__125886_nth_0___r__
                  (clojure.core/drop 1 input__125886_nth_0__)]
                 (clojure.core/let
                  [input__125886_nth_0___l___nth_0__
                   (clojure.core/nth input__125886_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__125886_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__127328
                     (clojure.core/namespace
                      input__125886_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__125907 X__127328]
                     (clojure.core/let
                      [X__127330
                       (clojure.core/name
                        input__125886_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__127330
                       ("symbol")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__127318 input__125886_nth_1__ ?__125907)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__127515)
                         (clojure.core/let
                          [[?__125907] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__125886)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__125886)
                             2)
                            (clojure.core/let
                             [input__125886_nth_0__
                              (clojure.core/nth input__125886 0)
                              input__125886_nth_1__
                              (clojure.core/nth input__125886 1)]
                             (if
                              (clojure.core/seq? input__125886_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__125886_nth_0__)
                                2)
                               (clojure.core/let
                                [input__125886_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__125886_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?name input__125886_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__125886_nth_0__]
                                  (clojure.core/let
                                   [?env input__125886_nth_1__]
                                   (try
                                    [{:tag :symbol,
                                      :name
                                      (clojure.core/let
                                       [CATA_RESULT__15641__auto__
                                        (CATA__FN__125969
                                         [?name ?env])]
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
                               (state__127515))
                              (state__127515)))
                            (state__127515))
                           (state__127515)))))
                       (state__127515)))))
                   (state__127515))))
                (state__127515)))
              (state__127515)))
            (state__127515))
           (state__127515))))
        (state__127515
         []
         (clojure.core/letfn
          [(def__127352
            [arg__127375 ?__125908]
            (clojure.core/letfn
             [(state__127772
               []
               (clojure.core/let
                [x__127376 "meander.zeta"]
                (if
                 (clojure.core/= ?__125908 x__127376)
                 [?__125908]
                 (state__127773))))
              (state__127773
               []
               (if
                (clojure.core/map? arg__127375)
                (clojure.core/let
                 [VAL__127377 (.valAt arg__127375 :aliases)]
                 (if
                  (clojure.core/map? VAL__127377)
                  (clojure.core/let
                   [X__127379 (clojure.core/set VAL__127377)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__127379))
                    (clojure.core/loop
                     [search_space__127774
                      (clojure.core/seq X__127379)]
                     (if
                      (clojure.core/seq search_space__127774)
                      (clojure.core/let
                       [elem__127380
                        (clojure.core/first search_space__127774)
                        result__127775
                        (clojure.core/let
                         [elem__127380_nth_0__
                          (clojure.core/nth elem__127380 0)
                          elem__127380_nth_1__
                          (clojure.core/nth elem__127380 1)]
                         (if
                          (clojure.core/symbol? elem__127380_nth_0__)
                          (clojure.core/let
                           [X__127382
                            (clojure.core/name elem__127380_nth_0__)]
                           (if
                            (clojure.core/= ?__125908 X__127382)
                            (if
                             (clojure.core/symbol?
                              elem__127380_nth_1__)
                             (clojure.core/let
                              [X__127384
                               (clojure.core/name
                                elem__127380_nth_1__)]
                              (clojure.core/case
                               X__127384
                               ("meander.zeta")
                               [?__125908]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__127775)
                        (recur
                         (clojure.core/next search_space__127774))
                        result__127775))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__127772)))]
          (if
           (clojure.core/vector? input__125886)
           (if
            (clojure.core/= (clojure.core/count input__125886) 2)
            (clojure.core/let
             [input__125886_nth_0__
              (clojure.core/nth input__125886 0)
              input__125886_nth_1__
              (clojure.core/nth input__125886 1)]
             (if
              (clojure.core/seq? input__125886_nth_0__)
              (clojure.core/let
               [input__125886_nth_0___l__
                (clojure.core/take 1 input__125886_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__125886_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__125886_nth_0___r__
                  (clojure.core/drop 1 input__125886_nth_0__)]
                 (clojure.core/let
                  [input__125886_nth_0___l___nth_0__
                   (clojure.core/nth input__125886_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__125886_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__127362
                     (clojure.core/namespace
                      input__125886_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__125908 X__127362]
                     (clojure.core/let
                      [X__127364
                       (clojure.core/name
                        input__125886_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__127364
                       ("symbol")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__127352 input__125886_nth_1__ ?__125908)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__127516)
                         (clojure.core/let
                          [[?__125908] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__125886)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__125886)
                             2)
                            (clojure.core/let
                             [input__125886_nth_0__
                              (clojure.core/nth input__125886 0)
                              input__125886_nth_1__
                              (clojure.core/nth input__125886 1)]
                             (if
                              (clojure.core/seq? input__125886_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__125886_nth_0__)
                                3)
                               (clojure.core/let
                                [input__125886_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__125886_nth_0__
                                  1)
                                 input__125886_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__125886_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?namespace
                                  input__125886_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?name input__125886_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__125886_nth_0__]
                                   (clojure.core/let
                                    [?env input__125886_nth_1__]
                                    (try
                                     [{:tag :symbol,
                                       :name
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__125969
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
                                         (CATA__FN__125969
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
                               (state__127516))
                              (state__127516)))
                            (state__127516))
                           (state__127516)))))
                       (state__127516)))))
                   (state__127516))))
                (state__127516)))
              (state__127516)))
            (state__127516))
           (state__127516))))
        (state__127516
         []
         (clojure.core/letfn
          [(def__127386
            [arg__127409 ?__125909]
            (clojure.core/letfn
             [(state__127777
               []
               (clojure.core/let
                [x__127410 "meander.zeta"]
                (if
                 (clojure.core/= ?__125909 x__127410)
                 [?__125909]
                 (state__127778))))
              (state__127778
               []
               (if
                (clojure.core/map? arg__127409)
                (clojure.core/let
                 [VAL__127411 (.valAt arg__127409 :aliases)]
                 (if
                  (clojure.core/map? VAL__127411)
                  (clojure.core/let
                   [X__127413 (clojure.core/set VAL__127411)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__127413))
                    (clojure.core/loop
                     [search_space__127779
                      (clojure.core/seq X__127413)]
                     (if
                      (clojure.core/seq search_space__127779)
                      (clojure.core/let
                       [elem__127414
                        (clojure.core/first search_space__127779)
                        result__127780
                        (clojure.core/let
                         [elem__127414_nth_0__
                          (clojure.core/nth elem__127414 0)
                          elem__127414_nth_1__
                          (clojure.core/nth elem__127414 1)]
                         (if
                          (clojure.core/symbol? elem__127414_nth_0__)
                          (clojure.core/let
                           [X__127416
                            (clojure.core/name elem__127414_nth_0__)]
                           (if
                            (clojure.core/= ?__125909 X__127416)
                            (if
                             (clojure.core/symbol?
                              elem__127414_nth_1__)
                             (clojure.core/let
                              [X__127418
                               (clojure.core/name
                                elem__127414_nth_1__)]
                              (clojure.core/case
                               X__127418
                               ("meander.zeta")
                               [?__125909]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__127780)
                        (recur
                         (clojure.core/next search_space__127779))
                        result__127780))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__127777)))]
          (if
           (clojure.core/vector? input__125886)
           (if
            (clojure.core/= (clojure.core/count input__125886) 2)
            (clojure.core/let
             [input__125886_nth_0__
              (clojure.core/nth input__125886 0)
              input__125886_nth_1__
              (clojure.core/nth input__125886 1)]
             (if
              (clojure.core/seq? input__125886_nth_0__)
              (clojure.core/let
               [input__125886_nth_0___l__
                (clojure.core/take 1 input__125886_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__125886_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__125886_nth_0___r__
                  (clojure.core/drop 1 input__125886_nth_0__)]
                 (clojure.core/let
                  [input__125886_nth_0___l___nth_0__
                   (clojure.core/nth input__125886_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__125886_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__127396
                     (clojure.core/namespace
                      input__125886_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__125909 X__127396]
                     (clojure.core/let
                      [X__127398
                       (clojure.core/name
                        input__125886_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__127398
                       ("symbol")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__127386 input__125886_nth_1__ ?__125909)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__127517)
                         (clojure.core/let
                          [[?__125909] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__125886)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__125886)
                             2)
                            (clojure.core/let
                             [input__125886_nth_0__
                              (clojure.core/nth input__125886 0)
                              input__125886_nth_1__
                              (clojure.core/nth input__125886 1)]
                             (if
                              (clojure.core/seq? input__125886_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 5)
                                 input__125886_nth_0__)
                                5)
                               (clojure.core/let
                                [input__125886_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__125886_nth_0__
                                  1)
                                 input__125886_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__125886_nth_0__
                                  2)
                                 input__125886_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__125886_nth_0__
                                  3)
                                 input__125886_nth_0___nth_4__
                                 (clojure.core/nth
                                  input__125886_nth_0__
                                  4)]
                                (clojure.core/case
                                 input__125886_nth_0___nth_3__
                                 (:meander.zeta/as)
                                 (clojure.core/let
                                  [?namespace
                                   input__125886_nth_0___nth_1__]
                                  (clojure.core/let
                                   [?name
                                    input__125886_nth_0___nth_2__]
                                   (clojure.core/let
                                    [?pattern
                                     input__125886_nth_0___nth_4__]
                                    (clojure.core/let
                                     [?form input__125886_nth_0__]
                                     (clojure.core/let
                                      [?env input__125886_nth_1__]
                                      (try
                                       [{:tag :symbol,
                                         :name
                                         (clojure.core/let
                                          [CATA_RESULT__15641__auto__
                                           (CATA__FN__125969
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
                                           (CATA__FN__125969
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
                                           (CATA__FN__125969
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
                                 (state__127517)))
                               (state__127517))
                              (state__127517)))
                            (state__127517))
                           (state__127517)))))
                       (state__127517)))))
                   (state__127517))))
                (state__127517)))
              (state__127517)))
            (state__127517))
           (state__127517))))
        (state__127517
         []
         (if
          (clojure.core/vector? input__125886)
          (if
           (clojure.core/= (clojure.core/count input__125886) 2)
           (clojure.core/let
            [input__125886_nth_0__ (clojure.core/nth input__125886 0)]
            (clojure.core/letfn
             [(state__127782
               []
               (clojure.core/let
                [input__125886_nth_1__
                 (clojure.core/nth input__125886 1)]
                (clojure.core/letfn
                 [(state__127787
                   []
                   (if
                    (clojure.core/seq? input__125886_nth_0__)
                    (clojure.core/let
                     [?sequence input__125886_nth_0__]
                     (clojure.core/let
                      [?env input__125886_nth_1__]
                      (try
                       [{:tag :seq,
                         :next
                         (clojure.core/let
                          [CATA_RESULT__15641__auto__
                           (CATA__FN__125969
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
                    (state__127788)))
                  (state__127788
                   []
                   (if
                    (clojure.core/map? input__125886_nth_0__)
                    (clojure.core/let
                     [?map input__125886_nth_0__]
                     (clojure.core/let
                      [?env input__125886_nth_1__]
                      (try
                       [{:tag :map,
                         :next
                         (clojure.core/let
                          [CATA_RESULT__15641__auto__
                           (CATA__FN__125969
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
                    (state__127783)))]
                 (state__127787))))
              (state__127783
               []
               (if
                (clojure.core/symbol? input__125886_nth_0__)
                (clojure.core/let
                 [X__127428
                  (clojure.core/namespace input__125886_nth_0__)]
                 (clojure.core/let
                  [X__127430 (clojure.core/name input__125886_nth_0__)]
                  (if
                   (clojure.core/string? X__127430)
                   (clojure.core/letfn
                    [(state__127789
                      []
                      (clojure.core/let
                       [ret__127431
                        (clojure.core/re-matches #"_.*" X__127430)]
                       (if
                        (clojure.core/some? ret__127431)
                        (clojure.core/let
                         [?name ret__127431]
                         (clojure.core/let
                          [?symbol input__125886_nth_0__]
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
                        (state__127790))))
                     (state__127790
                      []
                      (clojure.core/let
                       [ret__127438
                        (clojure.core/re-matches #".+#" X__127430)]
                       (if
                        (clojure.core/some? ret__127438)
                        (clojure.core/let
                         [?name ret__127438]
                         (clojure.core/let
                          [?symbol input__125886_nth_0__]
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
                        (state__127791))))
                     (state__127791
                      []
                      (clojure.core/let
                       [ret__127445
                        (clojure.core/re-matches #"%.+" X__127430)]
                       (if
                        (clojure.core/some? ret__127445)
                        (clojure.core/let
                         [?name ret__127445]
                         (clojure.core/let
                          [?symbol input__125886_nth_0__]
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
                        (state__127792))))
                     (state__127792
                      []
                      (clojure.core/let
                       [ret__127452
                        (clojure.core/re-matches #"\*.+" X__127430)]
                       (if
                        (clojure.core/some? ret__127452)
                        (clojure.core/let
                         [?name ret__127452]
                         (clojure.core/let
                          [?symbol input__125886_nth_0__]
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
                        (state__127793))))
                     (state__127793
                      []
                      (clojure.core/let
                       [ret__127459
                        (clojure.core/re-matches #"\!.+" X__127430)]
                       (if
                        (clojure.core/some? ret__127459)
                        (clojure.core/let
                         [?name ret__127459]
                         (clojure.core/let
                          [?symbol input__125886_nth_0__]
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
                        (state__127794))))
                     (state__127794
                      []
                      (clojure.core/let
                       [ret__127466
                        (clojure.core/re-matches #"\?.+" X__127430)]
                       (if
                        (clojure.core/some? ret__127466)
                        (clojure.core/let
                         [?name ret__127466]
                         (clojure.core/let
                          [?symbol input__125886_nth_0__]
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
                        (state__127784))))]
                    (state__127789))
                   (state__127784))))
                (state__127784)))
              (state__127784
               []
               (if
                (string? input__125886_nth_0__)
                (clojure.core/let
                 [?x input__125886_nth_0__]
                 (try
                  [{:tag :literal, :type :string, :form ?x}]
                  (catch
                   java.lang.Exception
                   e__16581__auto__
                   (if
                    (meander.runtime.zeta/fail? e__16581__auto__)
                    (meander.runtime.zeta/fail)
                    (throw e__16581__auto__)))))
                (state__127785)))
              (state__127785
               []
               (if
                (char? input__125886_nth_0__)
                (clojure.core/let
                 [?x input__125886_nth_0__]
                 (try
                  [{:tag :literal, :type :char, :form ?x}]
                  (catch
                   java.lang.Exception
                   e__16581__auto__
                   (if
                    (meander.runtime.zeta/fail? e__16581__auto__)
                    (meander.runtime.zeta/fail)
                    (throw e__16581__auto__)))))
                (state__127786)))
              (state__127786
               []
               (clojure.core/let
                [?x input__125886_nth_0__]
                (try
                 [{:tag :literal, :form ?x}]
                 (catch
                  java.lang.Exception
                  e__16581__auto__
                  (if
                   (meander.runtime.zeta/fail? e__16581__auto__)
                   (meander.runtime.zeta/fail)
                   (throw e__16581__auto__))))))]
             (state__127782)))
           (state__127518))
          (state__127518)))
        (state__127518
         []
         (clojure.core/let
          [?x input__125886]
          (try
           [{:tag :mistake, :x ?x}]
           (catch
            java.lang.Exception
            e__16581__auto__
            (if
             (meander.runtime.zeta/fail? e__16581__auto__)
             (meander.runtime.zeta/fail)
             (throw e__16581__auto__))))))]
       (state__127479)))]
    (clojure.core/let
     [x__14338__auto__ (CATA__FN__125969 input__125886)]
     (if
      (meander.runtime.zeta/fail? x__14338__auto__)
      (meander.runtime.zeta/fail)
      (clojure.core/let
       [[CATA_RETURN__125973] x__14338__auto__]
       CATA_RETURN__125973))))]
  (if
   (meander.runtime.zeta/fail? ret__14518__auto__)
   nil
   ret__14518__auto__)))
