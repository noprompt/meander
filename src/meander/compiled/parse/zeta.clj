(ns meander.compiled.parse.zeta (:require [meander.runtime.zeta]))
(clojure.core/defn
 parse
 [input__66972]
 (let*
  [ret__31168__auto__
   (clojure.core/letfn
    [(CATA__FN__67009
      [input__66972]
      (clojure.core/letfn
       [(state__67901
         []
         (if
          (clojure.core/vector? input__66972)
          (if
           (clojure.core/= (clojure.core/count input__66972) 3)
           (clojure.core/let
            [input__66972_nth_0__
             (clojure.core/nth input__66972 0)
             input__66972_nth_1__
             (clojure.core/nth input__66972 1)
             input__66972_nth_2__
             (clojure.core/nth input__66972 2)]
            (if
             (clojure.core/let
              [x__30048__auto__ input__66972_nth_0__]
              (clojure.core/case
               x__30048__auto__
               (meander.dev.parse.zeta/parse-seq
                meander.dev.parse.zeta/parse-string)
               true
               false))
             (clojure.core/letfn
              [(state__67920
                []
                (if
                 (clojure.core/vector? input__66972_nth_1__)
                 (clojure.core/case
                  input__66972_nth_1__
                  ([])
                  (clojure.core/let
                   [?env input__66972_nth_2__]
                   (try
                    [{:tag :empty}]
                    (catch
                     java.lang.Exception
                     e__33231__auto__
                     (if
                      (meander.runtime.zeta/fail? e__33231__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__33231__auto__)))))
                  (state__67921))
                 (state__67921)))
               (state__67921
                []
                (clojure.core/let
                 [?rule-name input__66972_nth_0__]
                 (if
                  (clojure.core/vector? input__66972_nth_1__)
                  (clojure.core/let
                   [n__67017
                    (clojure.core/count input__66972_nth_1__)
                    m__67018
                    (clojure.core/max 0 (clojure.core/- n__67017 2))
                    input__66972_nth_1___l__
                    (clojure.core/subvec
                     input__66972_nth_1__
                     0
                     (clojure.core/min
                      (clojure.core/count input__66972_nth_1__)
                      m__67018))
                    input__66972_nth_1___r__
                    (clojure.core/subvec
                     input__66972_nth_1__
                     m__67018)]
                   (if
                    (clojure.core/=
                     (clojure.core/count input__66972_nth_1___r__)
                     2)
                    (clojure.core/let
                     [!xs (clojure.core/vec input__66972_nth_1___l__)]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__66972_nth_1___r__)
                       2)
                      (clojure.core/let
                       [input__66972_nth_1___r___nth_0__
                        (clojure.core/nth input__66972_nth_1___r__ 0)
                        input__66972_nth_1___r___nth_1__
                        (clojure.core/nth input__66972_nth_1___r__ 1)]
                       (clojure.core/case
                        input__66972_nth_1___r___nth_0__
                        (:meander.zeta/as)
                        (clojure.core/let
                         [?pattern input__66972_nth_1___r___nth_1__]
                         (clojure.core/let
                          [?env input__66972_nth_2__]
                          (try
                           [(clojure.core/let
                             [!xs__counter
                              (meander.runtime.zeta/iterator !xs)]
                             {:tag :as,
                              :pattern
                              (clojure.core/let
                               [CATA_RESULT__32291__auto__
                                (CATA__FN__67009 [?pattern ?env])]
                               (if
                                (meander.runtime.zeta/fail?
                                 CATA_RESULT__32291__auto__)
                                (throw (meander.runtime.zeta/fail))
                                (clojure.core/nth
                                 CATA_RESULT__32291__auto__
                                 0))),
                              :next
                              (clojure.core/let
                               [CATA_RESULT__32291__auto__
                                (CATA__FN__67009
                                 [?rule-name
                                  (clojure.core/into
                                   []
                                   (clojure.core/vec
                                    (clojure.core/iterator-seq
                                     !xs__counter)))
                                  ?env])]
                               (if
                                (meander.runtime.zeta/fail?
                                 CATA_RESULT__32291__auto__)
                                (throw (meander.runtime.zeta/fail))
                                (clojure.core/nth
                                 CATA_RESULT__32291__auto__
                                 0)))})]
                           (catch
                            java.lang.Exception
                            e__33231__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__33231__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__33231__auto__))))))
                        (state__67902)))
                      (state__67902)))
                    (state__67902)))
                  (state__67902))))]
              (state__67920))
             (state__67902)))
           (state__67902))
          (state__67902)))
        (state__67902
         []
         (clojure.core/letfn
          [(def__67023
            [arg__67058 ?ns]
            (clojure.core/letfn
             [(state__67922
               []
               (clojure.core/let
                [x__67059 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__67059)
                 (clojure.core/let [?env arg__67058] [?env ?ns])
                 (state__67923))))
              (state__67923
               []
               (if
                (clojure.core/map? arg__67058)
                (clojure.core/let
                 [VAL__67060 (.valAt arg__67058 :aliases)]
                 (if
                  (clojure.core/map? VAL__67060)
                  (clojure.core/let
                   [X__67062 (clojure.core/set VAL__67060)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__67062))
                    (clojure.core/loop
                     [search_space__67924 (clojure.core/seq X__67062)]
                     (if
                      (clojure.core/seq search_space__67924)
                      (clojure.core/let
                       [elem__67063
                        (clojure.core/first search_space__67924)
                        result__67925
                        (clojure.core/let
                         [elem__67063_nth_0__
                          (clojure.core/nth elem__67063 0)
                          elem__67063_nth_1__
                          (clojure.core/nth elem__67063 1)]
                         (if
                          (clojure.core/symbol? elem__67063_nth_0__)
                          (clojure.core/let
                           [X__67065
                            (clojure.core/name elem__67063_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__67065)
                            (if
                             (clojure.core/symbol? elem__67063_nth_1__)
                             (clojure.core/let
                              [X__67067
                               (clojure.core/name elem__67063_nth_1__)]
                              (clojure.core/case
                               X__67067
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__67058]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__67925)
                        (recur (clojure.core/next search_space__67924))
                        result__67925))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__67922)))]
          (if
           (clojure.core/vector? input__66972)
           (if
            (clojure.core/= (clojure.core/count input__66972) 3)
            (clojure.core/let
             [input__66972_nth_0__
              (clojure.core/nth input__66972 0)
              input__66972_nth_1__
              (clojure.core/nth input__66972 1)
              input__66972_nth_2__
              (clojure.core/nth input__66972 2)]
             (if
              (clojure.core/let
               [x__30048__auto__ input__66972_nth_0__]
               (clojure.core/case
                x__30048__auto__
                (meander.dev.parse.zeta/parse-seq
                 meander.dev.parse.zeta/parse-string)
                true
                false))
              (clojure.core/let
               [?rule-name input__66972_nth_0__]
               (if
                (clojure.core/vector? input__66972_nth_1__)
                (clojure.core/loop
                 [search_space__67927
                  (meander.match.runtime.epsilon/partitions
                   2
                   input__66972_nth_1__)]
                 (if
                  (clojure.core/seq search_space__67927)
                  (clojure.core/let
                   [input__66972_nth_1___parts__
                    (clojure.core/first search_space__67927)
                    result__67928
                    (clojure.core/let
                     [input__66972_nth_1___l__
                      (clojure.core/nth input__66972_nth_1___parts__ 0)
                      input__66972_nth_1___r__
                      (clojure.core/nth
                       input__66972_nth_1___parts__
                       1)]
                     (clojure.core/let
                      [!init
                       (clojure.core/vec input__66972_nth_1___l__)]
                      (clojure.core/let
                       [input__66972_nth_1___r___l__
                        (clojure.core/subvec
                         input__66972_nth_1___r__
                         0
                         (clojure.core/min
                          (clojure.core/count input__66972_nth_1___r__)
                          2))]
                       (if
                        (clojure.core/=
                         (clojure.core/count
                          input__66972_nth_1___r___l__)
                         2)
                        (clojure.core/let
                         [input__66972_nth_1___r___r__
                          (clojure.core/subvec
                           input__66972_nth_1___r__
                           2)]
                         (clojure.core/let
                          [input__66972_nth_1___r___l___nth_0__
                           (clojure.core/nth
                            input__66972_nth_1___r___l__
                            0)
                           input__66972_nth_1___r___l___nth_1__
                           (clojure.core/nth
                            input__66972_nth_1___r___l__
                            1)]
                          (if
                           (clojure.core/symbol?
                            input__66972_nth_1___r___l___nth_0__)
                           (clojure.core/let
                            [X__67032
                             (clojure.core/namespace
                              input__66972_nth_1___r___l___nth_0__)]
                            (clojure.core/let
                             [?ns X__67032]
                             (clojure.core/let
                              [X__67034
                               (clojure.core/name
                                input__66972_nth_1___r___l___nth_0__)]
                              (if
                               (clojure.core/string? X__67034)
                               (clojure.core/let
                                [ret__67035
                                 (clojure.core/re-matches
                                  #"&(\d+)"
                                  X__67034)]
                                (if
                                 (clojure.core/some? ret__67035)
                                 (if
                                  (clojure.core/vector? ret__67035)
                                  (if
                                   (clojure.core/=
                                    (clojure.core/count ret__67035)
                                    2)
                                   (clojure.core/let
                                    [ret__67035_nth_1__
                                     (clojure.core/nth ret__67035 1)]
                                    (clojure.core/let
                                     [?n ret__67035_nth_1__]
                                     (clojure.core/let
                                      [?pattern
                                       input__66972_nth_1___r___l___nth_1__]
                                      (clojure.core/let
                                       [?rest
                                        input__66972_nth_1___r___r__]
                                       (clojure.core/let
                                        [x__30988__auto__
                                         (def__67023
                                          input__66972_nth_2__
                                          ?ns)]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          x__30988__auto__)
                                         (meander.runtime.zeta/fail)
                                         (clojure.core/let
                                          [[?env ?ns] x__30988__auto__]
                                          (try
                                           [(clojure.core/let
                                             [!init__counter
                                              (meander.runtime.zeta/iterator
                                               !init)]
                                             (clojure.core/let
                                              [CATA_RESULT__32291__auto__
                                               (CATA__FN__67009
                                                ['meander.dev.parse.zeta/join-args
                                                 (clojure.core/let
                                                  [CATA_RESULT__32291__auto__
                                                   (CATA__FN__67009
                                                    [?rule-name
                                                     (clojure.core/into
                                                      []
                                                      (clojure.core/vec
                                                       (clojure.core/iterator-seq
                                                        !init__counter)))
                                                     ?env])]
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    CATA_RESULT__32291__auto__)
                                                   (throw
                                                    (meander.runtime.zeta/fail))
                                                   (clojure.core/nth
                                                    CATA_RESULT__32291__auto__
                                                    0)))
                                                 (clojure.core/let
                                                  [CATA_RESULT__32291__auto__
                                                   (CATA__FN__67009
                                                    ['meander.dev.parse.zeta/join-args
                                                     {:tag :slice,
                                                      :size
                                                      (Integer. ?n),
                                                      :pattern
                                                      (clojure.core/let
                                                       [CATA_RESULT__32291__auto__
                                                        (CATA__FN__67009
                                                         [?pattern
                                                          ?env])]
                                                       (if
                                                        (meander.runtime.zeta/fail?
                                                         CATA_RESULT__32291__auto__)
                                                        (throw
                                                         (meander.runtime.zeta/fail))
                                                        (clojure.core/nth
                                                         CATA_RESULT__32291__auto__
                                                         0)))}
                                                     (clojure.core/let
                                                      [CATA_RESULT__32291__auto__
                                                       (CATA__FN__67009
                                                        [?rule-name
                                                         (clojure.core/into
                                                          []
                                                          ?rest)
                                                         ?env])]
                                                      (if
                                                       (meander.runtime.zeta/fail?
                                                        CATA_RESULT__32291__auto__)
                                                       (throw
                                                        (meander.runtime.zeta/fail))
                                                       (clojure.core/nth
                                                        CATA_RESULT__32291__auto__
                                                        0)))])]
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    CATA_RESULT__32291__auto__)
                                                   (throw
                                                    (meander.runtime.zeta/fail))
                                                   (clojure.core/nth
                                                    CATA_RESULT__32291__auto__
                                                    0)))])]
                                              (if
                                               (meander.runtime.zeta/fail?
                                                CATA_RESULT__32291__auto__)
                                               (throw
                                                (meander.runtime.zeta/fail))
                                               (clojure.core/nth
                                                CATA_RESULT__32291__auto__
                                                0))))]
                                           (catch
                                            java.lang.Exception
                                            e__33231__auto__
                                            (if
                                             (meander.runtime.zeta/fail?
                                              e__33231__auto__)
                                             (meander.runtime.zeta/fail)
                                             (throw
                                              e__33231__auto__)))))))))))
                                   (meander.runtime.zeta/fail))
                                  (meander.runtime.zeta/fail))
                                 (meander.runtime.zeta/fail)))
                               (meander.runtime.zeta/fail)))))
                           (meander.runtime.zeta/fail))))
                        (meander.runtime.zeta/fail)))))]
                   (if
                    (meander.runtime.zeta/fail? result__67928)
                    (recur (clojure.core/next search_space__67927))
                    result__67928))
                  (state__67903)))
                (state__67903)))
              (state__67903)))
            (state__67903))
           (state__67903))))
        (state__67903
         []
         (clojure.core/letfn
          [(def__67080
            [arg__67112 ?ns]
            (clojure.core/letfn
             [(state__67930
               []
               (clojure.core/let
                [x__67113 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__67113)
                 (clojure.core/let [?env arg__67112] [?env ?ns])
                 (state__67931))))
              (state__67931
               []
               (if
                (clojure.core/map? arg__67112)
                (clojure.core/let
                 [VAL__67114 (.valAt arg__67112 :aliases)]
                 (if
                  (clojure.core/map? VAL__67114)
                  (clojure.core/let
                   [X__67116 (clojure.core/set VAL__67114)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__67116))
                    (clojure.core/loop
                     [search_space__67932 (clojure.core/seq X__67116)]
                     (if
                      (clojure.core/seq search_space__67932)
                      (clojure.core/let
                       [elem__67117
                        (clojure.core/first search_space__67932)
                        result__67933
                        (clojure.core/let
                         [elem__67117_nth_0__
                          (clojure.core/nth elem__67117 0)
                          elem__67117_nth_1__
                          (clojure.core/nth elem__67117 1)]
                         (if
                          (clojure.core/symbol? elem__67117_nth_0__)
                          (clojure.core/let
                           [X__67119
                            (clojure.core/name elem__67117_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__67119)
                            (if
                             (clojure.core/symbol? elem__67117_nth_1__)
                             (clojure.core/let
                              [X__67121
                               (clojure.core/name elem__67117_nth_1__)]
                              (clojure.core/case
                               X__67121
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__67112]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__67933)
                        (recur (clojure.core/next search_space__67932))
                        result__67933))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__67930)))]
          (if
           (clojure.core/vector? input__66972)
           (if
            (clojure.core/= (clojure.core/count input__66972) 3)
            (clojure.core/let
             [input__66972_nth_0__
              (clojure.core/nth input__66972 0)
              input__66972_nth_1__
              (clojure.core/nth input__66972 1)
              input__66972_nth_2__
              (clojure.core/nth input__66972 2)]
             (if
              (clojure.core/=
               input__66972_nth_0__
               'meander.dev.parse.zeta/parse-seq)
              (if
               (clojure.core/vector? input__66972_nth_1__)
               (clojure.core/loop
                [search_space__67935
                 (meander.match.runtime.epsilon/partitions
                  2
                  input__66972_nth_1__)]
                (if
                 (clojure.core/seq search_space__67935)
                 (clojure.core/let
                  [input__66972_nth_1___parts__
                   (clojure.core/first search_space__67935)
                   result__67936
                   (clojure.core/let
                    [input__66972_nth_1___l__
                     (clojure.core/nth input__66972_nth_1___parts__ 0)
                     input__66972_nth_1___r__
                     (clojure.core/nth input__66972_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__66972_nth_1___l__)]
                     (clojure.core/let
                      [input__66972_nth_1___r___l__
                       (clojure.core/subvec
                        input__66972_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__66972_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__66972_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__66972_nth_1___r___r__
                         (clojure.core/subvec
                          input__66972_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__66972_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__66972_nth_1___r___l__
                           0)
                          input__66972_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__66972_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__66972_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__67089
                            (clojure.core/namespace
                             input__66972_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__67089]
                            (clojure.core/let
                             [X__67091
                              (clojure.core/name
                               input__66972_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__67091)
                              (if
                               (clojure.core/re-matches
                                #"&.*"
                                X__67091)
                               (clojure.core/let
                                [?pattern
                                 input__66972_nth_1___r___l___nth_1__]
                                (clojure.core/let
                                 [?rest input__66972_nth_1___r___r__]
                                 (clojure.core/let
                                  [x__30988__auto__
                                   (def__67080
                                    input__66972_nth_2__
                                    ?ns)]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    x__30988__auto__)
                                   (meander.runtime.zeta/fail)
                                   (clojure.core/let
                                    [[?env ?ns] x__30988__auto__]
                                    (try
                                     [(clojure.core/let
                                       [!init__counter
                                        (meander.runtime.zeta/iterator
                                         !init)]
                                       (clojure.core/let
                                        [CATA_RESULT__32291__auto__
                                         (CATA__FN__67009
                                          ['meander.dev.parse.zeta/join-args
                                           (clojure.core/let
                                            [CATA_RESULT__32291__auto__
                                             (CATA__FN__67009
                                              ['meander.dev.parse.zeta/parse-seq
                                               (clojure.core/into
                                                []
                                                (clojure.core/vec
                                                 (clojure.core/iterator-seq
                                                  !init__counter)))
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__32291__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__32291__auto__
                                              0)))
                                           (clojure.core/let
                                            [CATA_RESULT__32291__auto__
                                             (CATA__FN__67009
                                              ['meander.dev.parse.zeta/join-args
                                               (clojure.core/let
                                                [CATA_RESULT__32291__auto__
                                                 (CATA__FN__67009
                                                  [?pattern ?env])]
                                                (if
                                                 (meander.runtime.zeta/fail?
                                                  CATA_RESULT__32291__auto__)
                                                 (throw
                                                  (meander.runtime.zeta/fail))
                                                 (clojure.core/nth
                                                  CATA_RESULT__32291__auto__
                                                  0)))
                                               (clojure.core/let
                                                [CATA_RESULT__32291__auto__
                                                 (CATA__FN__67009
                                                  ['meander.dev.parse.zeta/parse-string
                                                   (clojure.core/into
                                                    []
                                                    ?rest)
                                                   ?env])]
                                                (if
                                                 (meander.runtime.zeta/fail?
                                                  CATA_RESULT__32291__auto__)
                                                 (throw
                                                  (meander.runtime.zeta/fail))
                                                 (clojure.core/nth
                                                  CATA_RESULT__32291__auto__
                                                  0)))])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__32291__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__32291__auto__
                                              0)))])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__32291__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__32291__auto__
                                          0))))]
                                     (catch
                                      java.lang.Exception
                                      e__33231__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__33231__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__33231__auto__)))))))))
                               (meander.runtime.zeta/fail))
                              (meander.runtime.zeta/fail)))))
                          (meander.runtime.zeta/fail))))
                       (meander.runtime.zeta/fail)))))]
                  (if
                   (meander.runtime.zeta/fail? result__67936)
                   (recur (clojure.core/next search_space__67935))
                   result__67936))
                 (state__67904)))
               (state__67904))
              (state__67904)))
            (state__67904))
           (state__67904))))
        (state__67904
         []
         (clojure.core/letfn
          [(def__67134
            [arg__67166 ?ns]
            (clojure.core/letfn
             [(state__67938
               []
               (clojure.core/let
                [x__67167 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__67167)
                 (clojure.core/let [?env arg__67166] [?env ?ns])
                 (state__67939))))
              (state__67939
               []
               (if
                (clojure.core/map? arg__67166)
                (clojure.core/let
                 [VAL__67168 (.valAt arg__67166 :aliases)]
                 (if
                  (clojure.core/map? VAL__67168)
                  (clojure.core/let
                   [X__67170 (clojure.core/set VAL__67168)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__67170))
                    (clojure.core/loop
                     [search_space__67940 (clojure.core/seq X__67170)]
                     (if
                      (clojure.core/seq search_space__67940)
                      (clojure.core/let
                       [elem__67171
                        (clojure.core/first search_space__67940)
                        result__67941
                        (clojure.core/let
                         [elem__67171_nth_0__
                          (clojure.core/nth elem__67171 0)
                          elem__67171_nth_1__
                          (clojure.core/nth elem__67171 1)]
                         (if
                          (clojure.core/symbol? elem__67171_nth_0__)
                          (clojure.core/let
                           [X__67173
                            (clojure.core/name elem__67171_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__67173)
                            (if
                             (clojure.core/symbol? elem__67171_nth_1__)
                             (clojure.core/let
                              [X__67175
                               (clojure.core/name elem__67171_nth_1__)]
                              (clojure.core/case
                               X__67175
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__67166]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__67941)
                        (recur (clojure.core/next search_space__67940))
                        result__67941))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__67938)))]
          (if
           (clojure.core/vector? input__66972)
           (if
            (clojure.core/= (clojure.core/count input__66972) 3)
            (clojure.core/let
             [input__66972_nth_0__
              (clojure.core/nth input__66972 0)
              input__66972_nth_1__
              (clojure.core/nth input__66972 1)
              input__66972_nth_2__
              (clojure.core/nth input__66972 2)]
             (if
              (clojure.core/=
               input__66972_nth_0__
               'meander.dev.parse.zeta/parse-string)
              (if
               (clojure.core/vector? input__66972_nth_1__)
               (clojure.core/loop
                [search_space__67943
                 (meander.match.runtime.epsilon/partitions
                  2
                  input__66972_nth_1__)]
                (if
                 (clojure.core/seq search_space__67943)
                 (clojure.core/let
                  [input__66972_nth_1___parts__
                   (clojure.core/first search_space__67943)
                   result__67944
                   (clojure.core/let
                    [input__66972_nth_1___l__
                     (clojure.core/nth input__66972_nth_1___parts__ 0)
                     input__66972_nth_1___r__
                     (clojure.core/nth input__66972_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__66972_nth_1___l__)]
                     (clojure.core/let
                      [input__66972_nth_1___r___l__
                       (clojure.core/subvec
                        input__66972_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__66972_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__66972_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__66972_nth_1___r___r__
                         (clojure.core/subvec
                          input__66972_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__66972_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__66972_nth_1___r___l__
                           0)
                          input__66972_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__66972_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__66972_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__67143
                            (clojure.core/namespace
                             input__66972_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__67143]
                            (clojure.core/let
                             [X__67145
                              (clojure.core/name
                               input__66972_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__67145)
                              (if
                               (clojure.core/re-matches
                                #"&.*"
                                X__67145)
                               (clojure.core/let
                                [?pattern
                                 input__66972_nth_1___r___l___nth_1__]
                                (clojure.core/let
                                 [?rest input__66972_nth_1___r___r__]
                                 (clojure.core/let
                                  [x__30988__auto__
                                   (def__67134
                                    input__66972_nth_2__
                                    ?ns)]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    x__30988__auto__)
                                   (meander.runtime.zeta/fail)
                                   (clojure.core/let
                                    [[?env ?ns] x__30988__auto__]
                                    (try
                                     [(clojure.core/let
                                       [!init__counter
                                        (meander.runtime.zeta/iterator
                                         !init)]
                                       (clojure.core/let
                                        [CATA_RESULT__32291__auto__
                                         (CATA__FN__67009
                                          ['meander.dev.parse.zeta/string-join-args
                                           (clojure.core/let
                                            [CATA_RESULT__32291__auto__
                                             (CATA__FN__67009
                                              ['meander.dev.parse.zeta/parse-string
                                               (clojure.core/into
                                                []
                                                (clojure.core/vec
                                                 (clojure.core/iterator-seq
                                                  !init__counter)))
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__32291__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__32291__auto__
                                              0)))
                                           (clojure.core/let
                                            [CATA_RESULT__32291__auto__
                                             (CATA__FN__67009
                                              ['meander.dev.parse.zeta/string-join-args
                                               (clojure.core/let
                                                [CATA_RESULT__32291__auto__
                                                 (CATA__FN__67009
                                                  [?pattern ?env])]
                                                (if
                                                 (meander.runtime.zeta/fail?
                                                  CATA_RESULT__32291__auto__)
                                                 (throw
                                                  (meander.runtime.zeta/fail))
                                                 (clojure.core/nth
                                                  CATA_RESULT__32291__auto__
                                                  0)))
                                               (clojure.core/let
                                                [CATA_RESULT__32291__auto__
                                                 (CATA__FN__67009
                                                  ['meander.dev.parse.zeta/parse-string
                                                   (clojure.core/into
                                                    []
                                                    ?rest)
                                                   ?env])]
                                                (if
                                                 (meander.runtime.zeta/fail?
                                                  CATA_RESULT__32291__auto__)
                                                 (throw
                                                  (meander.runtime.zeta/fail))
                                                 (clojure.core/nth
                                                  CATA_RESULT__32291__auto__
                                                  0)))])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__32291__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__32291__auto__
                                              0)))])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__32291__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__32291__auto__
                                          0))))]
                                     (catch
                                      java.lang.Exception
                                      e__33231__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__33231__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__33231__auto__)))))))))
                               (meander.runtime.zeta/fail))
                              (meander.runtime.zeta/fail)))))
                          (meander.runtime.zeta/fail))))
                       (meander.runtime.zeta/fail)))))]
                  (if
                   (meander.runtime.zeta/fail? result__67944)
                   (recur (clojure.core/next search_space__67943))
                   result__67944))
                 (state__67905)))
               (state__67905))
              (state__67905)))
            (state__67905))
           (state__67905))))
        (state__67905
         []
         (if
          (clojure.core/vector? input__66972)
          (if
           (clojure.core/= (clojure.core/count input__66972) 3)
           (clojure.core/let
            [input__66972_nth_0__
             (clojure.core/nth input__66972 0)
             input__66972_nth_1__
             (clojure.core/nth input__66972 1)
             input__66972_nth_2__
             (clojure.core/nth input__66972 2)]
            (clojure.core/letfn
             [(state__67946
               []
               (if
                (clojure.core/=
                 input__66972_nth_0__
                 'meander.dev.parse.zeta/parse-seq)
                (if
                 (clojure.core/vector? input__66972_nth_1__)
                 (clojure.core/let
                  [n__67196
                   (clojure.core/count input__66972_nth_1__)
                   m__67197
                   (clojure.core/max 0 (clojure.core/- n__67196 2))
                   input__66972_nth_1___l__
                   (clojure.core/subvec
                    input__66972_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__66972_nth_1__)
                     m__67197))
                   input__66972_nth_1___r__
                   (clojure.core/subvec input__66972_nth_1__ m__67197)]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__66972_nth_1___r__)
                    2)
                   (clojure.core/let
                    [!xs (clojure.core/vec input__66972_nth_1___l__)]
                    (if
                     (clojure.core/=
                      (clojure.core/count input__66972_nth_1___r__)
                      2)
                     (clojure.core/let
                      [input__66972_nth_1___r___nth_0__
                       (clojure.core/nth input__66972_nth_1___r__ 0)
                       input__66972_nth_1___r___nth_1__
                       (clojure.core/nth input__66972_nth_1___r__ 1)]
                      (if
                       (clojure.core/symbol?
                        input__66972_nth_1___r___nth_0__)
                       (clojure.core/let
                        [X__67201
                         (clojure.core/namespace
                          input__66972_nth_1___r___nth_0__)]
                        (clojure.core/let
                         [?ns X__67201]
                         (clojure.core/let
                          [X__67203
                           (clojure.core/name
                            input__66972_nth_1___r___nth_0__)]
                          (if
                           (clojure.core/string? X__67203)
                           (clojure.core/let
                            [ret__67204
                             (clojure.core/re-matches #"&.*" X__67203)]
                            (if
                             (clojure.core/some? ret__67204)
                             (clojure.core/let
                              [?name ret__67204]
                              (clojure.core/let
                               [?pattern
                                input__66972_nth_1___r___nth_1__]
                               (if
                                (clojure.core/map?
                                 input__66972_nth_2__)
                                (clojure.core/let
                                 [VAL__67188
                                  (.valAt
                                   input__66972_nth_2__
                                   :aliases)]
                                 (if
                                  (clojure.core/map? VAL__67188)
                                  (clojure.core/let
                                   [X__67190
                                    (clojure.core/set VAL__67188)]
                                   (if
                                    (clojure.core/<=
                                     1
                                     (clojure.core/count X__67190))
                                    (clojure.core/loop
                                     [search_space__67969
                                      (clojure.core/seq X__67190)]
                                     (if
                                      (clojure.core/seq
                                       search_space__67969)
                                      (clojure.core/let
                                       [elem__67191
                                        (clojure.core/first
                                         search_space__67969)
                                        result__67970
                                        (clojure.core/let
                                         [elem__67191_nth_0__
                                          (clojure.core/nth
                                           elem__67191
                                           0)
                                          elem__67191_nth_1__
                                          (clojure.core/nth
                                           elem__67191
                                           1)]
                                         (if
                                          (clojure.core/symbol?
                                           elem__67191_nth_0__)
                                          (clojure.core/let
                                           [X__67193
                                            (clojure.core/name
                                             elem__67191_nth_0__)]
                                           (if
                                            (clojure.core/=
                                             ?ns
                                             X__67193)
                                            (if
                                             (clojure.core/symbol?
                                              elem__67191_nth_1__)
                                             (clojure.core/let
                                              [X__67195
                                               (clojure.core/name
                                                elem__67191_nth_1__)]
                                              (clojure.core/case
                                               X__67195
                                               ("meander.zeta")
                                               (clojure.core/let
                                                [?env
                                                 input__66972_nth_2__]
                                                (try
                                                 [(clojure.core/let
                                                   [!xs__counter
                                                    (meander.runtime.zeta/iterator
                                                     !xs)]
                                                   (clojure.core/let
                                                    [CATA_RESULT__32291__auto__
                                                     (CATA__FN__67009
                                                      ['meander.dev.parse.zeta/parse-seq
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
                                                      CATA_RESULT__32291__auto__)
                                                     (throw
                                                      (meander.runtime.zeta/fail))
                                                     (clojure.core/nth
                                                      CATA_RESULT__32291__auto__
                                                      0))))]
                                                 (catch
                                                  java.lang.Exception
                                                  e__33231__auto__
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    e__33231__auto__)
                                                   (meander.runtime.zeta/fail)
                                                   (throw
                                                    e__33231__auto__)))))
                                               (meander.runtime.zeta/fail)))
                                             (meander.runtime.zeta/fail))
                                            (meander.runtime.zeta/fail)))
                                          (meander.runtime.zeta/fail)))]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         result__67970)
                                        (recur
                                         (clojure.core/next
                                          search_space__67969))
                                        result__67970))
                                      (state__67947)))
                                    (state__67947)))
                                  (state__67947)))
                                (state__67947))))
                             (state__67947)))
                           (state__67947)))))
                       (state__67947)))
                     (state__67947)))
                   (state__67947)))
                 (state__67947))
                (state__67947)))
              (state__67947
               []
               (if
                (clojure.core/=
                 input__66972_nth_0__
                 'meander.dev.parse.zeta/parse-string)
                (if
                 (clojure.core/vector? input__66972_nth_1__)
                 (clojure.core/let
                  [n__67215
                   (clojure.core/count input__66972_nth_1__)
                   m__67216
                   (clojure.core/max 0 (clojure.core/- n__67215 2))
                   input__66972_nth_1___l__
                   (clojure.core/subvec
                    input__66972_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__66972_nth_1__)
                     m__67216))
                   input__66972_nth_1___r__
                   (clojure.core/subvec input__66972_nth_1__ m__67216)]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__66972_nth_1___r__)
                    2)
                   (clojure.core/let
                    [!xs (clojure.core/vec input__66972_nth_1___l__)]
                    (if
                     (clojure.core/=
                      (clojure.core/count input__66972_nth_1___r__)
                      2)
                     (clojure.core/let
                      [input__66972_nth_1___r___nth_0__
                       (clojure.core/nth input__66972_nth_1___r__ 0)
                       input__66972_nth_1___r___nth_1__
                       (clojure.core/nth input__66972_nth_1___r__ 1)]
                      (if
                       (clojure.core/symbol?
                        input__66972_nth_1___r___nth_0__)
                       (clojure.core/let
                        [X__67220
                         (clojure.core/namespace
                          input__66972_nth_1___r___nth_0__)]
                        (clojure.core/let
                         [?ns X__67220]
                         (clojure.core/let
                          [X__67222
                           (clojure.core/name
                            input__66972_nth_1___r___nth_0__)]
                          (if
                           (clojure.core/string? X__67222)
                           (clojure.core/let
                            [ret__67223
                             (clojure.core/re-matches #"&.*" X__67222)]
                            (if
                             (clojure.core/some? ret__67223)
                             (clojure.core/let
                              [?name ret__67223]
                              (clojure.core/let
                               [?pattern
                                input__66972_nth_1___r___nth_1__]
                               (if
                                (clojure.core/map?
                                 input__66972_nth_2__)
                                (clojure.core/let
                                 [VAL__67207
                                  (.valAt
                                   input__66972_nth_2__
                                   :aliases)]
                                 (if
                                  (clojure.core/map? VAL__67207)
                                  (clojure.core/let
                                   [X__67209
                                    (clojure.core/set VAL__67207)]
                                   (if
                                    (clojure.core/<=
                                     1
                                     (clojure.core/count X__67209))
                                    (clojure.core/loop
                                     [search_space__67972
                                      (clojure.core/seq X__67209)]
                                     (if
                                      (clojure.core/seq
                                       search_space__67972)
                                      (clojure.core/let
                                       [elem__67210
                                        (clojure.core/first
                                         search_space__67972)
                                        result__67973
                                        (clojure.core/let
                                         [elem__67210_nth_0__
                                          (clojure.core/nth
                                           elem__67210
                                           0)
                                          elem__67210_nth_1__
                                          (clojure.core/nth
                                           elem__67210
                                           1)]
                                         (if
                                          (clojure.core/symbol?
                                           elem__67210_nth_0__)
                                          (clojure.core/let
                                           [X__67212
                                            (clojure.core/name
                                             elem__67210_nth_0__)]
                                           (if
                                            (clojure.core/=
                                             ?ns
                                             X__67212)
                                            (if
                                             (clojure.core/symbol?
                                              elem__67210_nth_1__)
                                             (clojure.core/let
                                              [X__67214
                                               (clojure.core/name
                                                elem__67210_nth_1__)]
                                              (clojure.core/case
                                               X__67214
                                               ("meander.zeta")
                                               (clojure.core/let
                                                [?env
                                                 input__66972_nth_2__]
                                                (try
                                                 [(clojure.core/let
                                                   [!xs__counter
                                                    (meander.runtime.zeta/iterator
                                                     !xs)]
                                                   (clojure.core/let
                                                    [CATA_RESULT__32291__auto__
                                                     (CATA__FN__67009
                                                      ['meander.dev.parse.zeta/parse-string
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
                                                      CATA_RESULT__32291__auto__)
                                                     (throw
                                                      (meander.runtime.zeta/fail))
                                                     (clojure.core/nth
                                                      CATA_RESULT__32291__auto__
                                                      0))))]
                                                 (catch
                                                  java.lang.Exception
                                                  e__33231__auto__
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    e__33231__auto__)
                                                   (meander.runtime.zeta/fail)
                                                   (throw
                                                    e__33231__auto__)))))
                                               (meander.runtime.zeta/fail)))
                                             (meander.runtime.zeta/fail))
                                            (meander.runtime.zeta/fail)))
                                          (meander.runtime.zeta/fail)))]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         result__67973)
                                        (recur
                                         (clojure.core/next
                                          search_space__67972))
                                        result__67973))
                                      (state__67948)))
                                    (state__67948)))
                                  (state__67948)))
                                (state__67948))))
                             (state__67948)))
                           (state__67948)))))
                       (state__67948)))
                     (state__67948)))
                   (state__67948)))
                 (state__67948))
                (state__67948)))
              (state__67948
               []
               (if
                (clojure.core/let
                 [x__30048__auto__ input__66972_nth_0__]
                 (clojure.core/case
                  x__30048__auto__
                  (meander.dev.parse.zeta/parse-seq
                   meander.dev.parse.zeta/parse-string)
                  true
                  false))
                (clojure.core/let
                 [?rule-name input__66972_nth_0__]
                 (if
                  (clojure.core/vector? input__66972_nth_1__)
                  (clojure.core/letfn
                   [(state__67975
                     []
                     (clojure.core/loop
                      [search_space__67978
                       (meander.match.runtime.epsilon/partitions
                        2
                        input__66972_nth_1__)]
                      (if
                       (clojure.core/seq search_space__67978)
                       (clojure.core/let
                        [input__66972_nth_1___parts__
                         (clojure.core/first search_space__67978)
                         result__67979
                         (clojure.core/let
                          [input__66972_nth_1___l__
                           (clojure.core/nth
                            input__66972_nth_1___parts__
                            0)
                           input__66972_nth_1___r__
                           (clojure.core/nth
                            input__66972_nth_1___parts__
                            1)]
                          (clojure.core/let
                           [!xs
                            (clojure.core/vec
                             input__66972_nth_1___l__)]
                           (clojure.core/let
                            [input__66972_nth_1___r___l__
                             (clojure.core/subvec
                              input__66972_nth_1___r__
                              0
                              (clojure.core/min
                               (clojure.core/count
                                input__66972_nth_1___r__)
                               1))]
                            (if
                             (clojure.core/=
                              (clojure.core/count
                               input__66972_nth_1___r___l__)
                              1)
                             (clojure.core/let
                              [input__66972_nth_1___r___r__
                               (clojure.core/subvec
                                input__66972_nth_1___r__
                                1)]
                              (if
                               (clojure.core/=
                                input__66972_nth_1___r___l__
                                ['.])
                               (clojure.core/let
                                [?rest input__66972_nth_1___r___r__]
                                (clojure.core/let
                                 [?env input__66972_nth_2__]
                                 (try
                                  [(clojure.core/let
                                    [!xs__counter
                                     (meander.runtime.zeta/iterator
                                      !xs)]
                                    (clojure.core/let
                                     [CATA_RESULT__32291__auto__
                                      (CATA__FN__67009
                                       ['meander.dev.parse.zeta/join-args
                                        (clojure.core/let
                                         [CATA_RESULT__32291__auto__
                                          (CATA__FN__67009
                                           [?rule-name
                                            (clojure.core/into
                                             []
                                             (clojure.core/vec
                                              (clojure.core/iterator-seq
                                               !xs__counter)))
                                            ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__32291__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__32291__auto__
                                           0)))
                                        (clojure.core/let
                                         [CATA_RESULT__32291__auto__
                                          (CATA__FN__67009
                                           [?rule-name ?rest ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__32291__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__32291__auto__
                                           0)))])]
                                     (if
                                      (meander.runtime.zeta/fail?
                                       CATA_RESULT__32291__auto__)
                                      (throw
                                       (meander.runtime.zeta/fail))
                                      (clojure.core/nth
                                       CATA_RESULT__32291__auto__
                                       0))))]
                                  (catch
                                   java.lang.Exception
                                   e__33231__auto__
                                   (if
                                    (meander.runtime.zeta/fail?
                                     e__33231__auto__)
                                    (meander.runtime.zeta/fail)
                                    (throw e__33231__auto__))))))
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail)))))]
                        (if
                         (meander.runtime.zeta/fail? result__67979)
                         (recur
                          (clojure.core/next search_space__67978))
                         result__67979))
                       (state__67976))))
                    (state__67976
                     []
                     (clojure.core/let
                      [input__66972_nth_1___l__
                       (clojure.core/subvec
                        input__66972_nth_1__
                        0
                        (clojure.core/min
                         (clojure.core/count input__66972_nth_1__)
                         1))]
                      (if
                       (clojure.core/=
                        (clojure.core/count input__66972_nth_1___l__)
                        1)
                       (clojure.core/let
                        [input__66972_nth_1___r__
                         (clojure.core/subvec input__66972_nth_1__ 1)]
                        (if
                         (clojure.core/=
                          input__66972_nth_1___l__
                          ['...])
                         (clojure.core/let
                          [?rest input__66972_nth_1___r__]
                          (clojure.core/let
                           [?env input__66972_nth_2__]
                           (try
                            [(clojure.core/let
                              [CATA_RESULT__32291__auto__
                               (CATA__FN__67009
                                [?rule-name ?rest ?env])]
                              (if
                               (meander.runtime.zeta/fail?
                                CATA_RESULT__32291__auto__)
                               (throw (meander.runtime.zeta/fail))
                               (clojure.core/nth
                                CATA_RESULT__32291__auto__
                                0)))]
                            (catch
                             java.lang.Exception
                             e__33231__auto__
                             (if
                              (meander.runtime.zeta/fail?
                               e__33231__auto__)
                              (meander.runtime.zeta/fail)
                              (throw e__33231__auto__))))))
                         (state__67977)))
                       (state__67977))))
                    (state__67977
                     []
                     (clojure.core/loop
                      [search_space__67981
                       (meander.match.runtime.epsilon/partitions
                        2
                        input__66972_nth_1__)]
                      (if
                       (clojure.core/seq search_space__67981)
                       (clojure.core/let
                        [input__66972_nth_1___parts__
                         (clojure.core/first search_space__67981)
                         result__67982
                         (clojure.core/let
                          [input__66972_nth_1___l__
                           (clojure.core/nth
                            input__66972_nth_1___parts__
                            0)
                           input__66972_nth_1___r__
                           (clojure.core/nth
                            input__66972_nth_1___parts__
                            1)]
                          (clojure.core/let
                           [!xs []]
                           (clojure.core/let
                            [ret__31152__auto__
                             (meander.runtime.zeta/epsilon-run-star-1
                              input__66972_nth_1___l__
                              [!xs]
                              (clojure.core/fn
                               [[!xs] input__67240]
                               (clojure.core/let
                                [input__67240_nth_0__
                                 (clojure.core/nth input__67240 0)]
                                (clojure.core/letfn
                                 [(save__67241
                                   []
                                   (meander.runtime.zeta/fail))
                                  (f__67985
                                   []
                                   (clojure.core/let
                                    [!xs
                                     (clojure.core/conj
                                      !xs
                                      input__67240_nth_0__)]
                                    [!xs]))]
                                 (if
                                  (clojure.core/symbol?
                                   input__67240_nth_0__)
                                  (clojure.core/let
                                   [X__67243
                                    (clojure.core/namespace
                                     input__67240_nth_0__)]
                                   (clojure.core/case
                                    X__67243
                                    (nil)
                                    (clojure.core/let
                                     [X__67245
                                      (clojure.core/name
                                       input__67240_nth_0__)]
                                     (if
                                      (clojure.core/string? X__67245)
                                      (if
                                       (clojure.core/re-matches
                                        #"\.\.(?:\.|\d+)"
                                        X__67245)
                                       (save__67241)
                                       (f__67985))
                                      (f__67985)))
                                    (f__67985)))
                                  (f__67985)))))
                              (clojure.core/fn
                               [[!xs]]
                               (clojure.core/let
                                [input__66972_nth_1___r___l__
                                 (clojure.core/subvec
                                  input__66972_nth_1___r__
                                  0
                                  (clojure.core/min
                                   (clojure.core/count
                                    input__66972_nth_1___r__)
                                   1))]
                                (if
                                 (clojure.core/=
                                  (clojure.core/count
                                   input__66972_nth_1___r___l__)
                                  1)
                                 (clojure.core/let
                                  [input__66972_nth_1___r___r__
                                   (clojure.core/subvec
                                    input__66972_nth_1___r__
                                    1)]
                                  (if
                                   (clojure.core/=
                                    input__66972_nth_1___r___l__
                                    ['...])
                                   (clojure.core/let
                                    [?rest
                                     input__66972_nth_1___r___r__]
                                    (clojure.core/let
                                     [?env input__66972_nth_2__]
                                     (try
                                      [(clojure.core/let
                                        [!xs__counter
                                         (meander.runtime.zeta/iterator
                                          !xs)]
                                        (clojure.core/let
                                         [CATA_RESULT__32291__auto__
                                          (CATA__FN__67009
                                           ['meander.dev.parse.zeta/star-args
                                            (clojure.core/let
                                             [CATA_RESULT__32291__auto__
                                              (CATA__FN__67009
                                               [?rule-name
                                                (clojure.core/into
                                                 []
                                                 (clojure.core/vec
                                                  (clojure.core/iterator-seq
                                                   !xs__counter)))
                                                ?env])]
                                             (if
                                              (meander.runtime.zeta/fail?
                                               CATA_RESULT__32291__auto__)
                                              (throw
                                               (meander.runtime.zeta/fail))
                                              (clojure.core/nth
                                               CATA_RESULT__32291__auto__
                                               0)))
                                            (clojure.core/let
                                             [CATA_RESULT__32291__auto__
                                              (CATA__FN__67009
                                               [?rule-name
                                                ?rest
                                                ?env])]
                                             (if
                                              (meander.runtime.zeta/fail?
                                               CATA_RESULT__32291__auto__)
                                              (throw
                                               (meander.runtime.zeta/fail))
                                              (clojure.core/nth
                                               CATA_RESULT__32291__auto__
                                               0)))])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__32291__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__32291__auto__
                                           0))))]
                                      (catch
                                       java.lang.Exception
                                       e__33231__auto__
                                       (if
                                        (meander.runtime.zeta/fail?
                                         e__33231__auto__)
                                        (meander.runtime.zeta/fail)
                                        (throw e__33231__auto__))))))
                                   (meander.runtime.zeta/fail)))
                                 (meander.runtime.zeta/fail)))))]
                            (if
                             (meander.runtime.zeta/fail?
                              ret__31152__auto__)
                             (meander.runtime.zeta/fail)
                             ret__31152__auto__))))]
                        (if
                         (meander.runtime.zeta/fail? result__67982)
                         (recur
                          (clojure.core/next search_space__67981))
                         result__67982))
                       (state__67949))))]
                   (state__67975))
                  (state__67949)))
                (state__67949)))
              (state__67949
               []
               (if
                (clojure.core/=
                 input__66972_nth_0__
                 'meander.dev.parse.zeta/parse-string)
                (if
                 (clojure.core/vector? input__66972_nth_1__)
                 (clojure.core/loop
                  [search_space__67986
                   (meander.match.runtime.epsilon/partitions
                    2
                    input__66972_nth_1__)]
                  (if
                   (clojure.core/seq search_space__67986)
                   (clojure.core/let
                    [input__66972_nth_1___parts__
                     (clojure.core/first search_space__67986)
                     result__67987
                     (clojure.core/let
                      [input__66972_nth_1___l__
                       (clojure.core/nth
                        input__66972_nth_1___parts__
                        0)
                       input__66972_nth_1___r__
                       (clojure.core/nth
                        input__66972_nth_1___parts__
                        1)]
                      (clojure.core/let
                       [!xs []]
                       (clojure.core/let
                        [ret__31152__auto__
                         (meander.runtime.zeta/epsilon-run-star-1
                          input__66972_nth_1___l__
                          [!xs]
                          (clojure.core/fn
                           [[!xs] input__67253]
                           (clojure.core/let
                            [input__67253_nth_0__
                             (clojure.core/nth input__67253 0)]
                            (clojure.core/letfn
                             [(save__67254
                               []
                               (meander.runtime.zeta/fail))
                              (f__67990
                               []
                               (clojure.core/let
                                [!xs
                                 (clojure.core/conj
                                  !xs
                                  input__67253_nth_0__)]
                                [!xs]))]
                             (if
                              (clojure.core/symbol?
                               input__67253_nth_0__)
                              (clojure.core/let
                               [X__67256
                                (clojure.core/namespace
                                 input__67253_nth_0__)]
                               (clojure.core/case
                                X__67256
                                (nil)
                                (clojure.core/let
                                 [X__67258
                                  (clojure.core/name
                                   input__67253_nth_0__)]
                                 (if
                                  (clojure.core/string? X__67258)
                                  (if
                                   (clojure.core/re-matches
                                    #"\.\.(?:\.|\d+)"
                                    X__67258)
                                   (save__67254)
                                   (f__67990))
                                  (f__67990)))
                                (f__67990)))
                              (f__67990)))))
                          (clojure.core/fn
                           [[!xs]]
                           (clojure.core/let
                            [input__66972_nth_1___r___l__
                             (clojure.core/subvec
                              input__66972_nth_1___r__
                              0
                              (clojure.core/min
                               (clojure.core/count
                                input__66972_nth_1___r__)
                               1))]
                            (if
                             (clojure.core/=
                              (clojure.core/count
                               input__66972_nth_1___r___l__)
                              1)
                             (clojure.core/let
                              [input__66972_nth_1___r___r__
                               (clojure.core/subvec
                                input__66972_nth_1___r__
                                1)]
                              (if
                               (clojure.core/=
                                input__66972_nth_1___r___l__
                                ['...])
                               (clojure.core/let
                                [?rest input__66972_nth_1___r___r__]
                                (clojure.core/let
                                 [?env input__66972_nth_2__]
                                 (try
                                  [(clojure.core/let
                                    [!xs__counter
                                     (meander.runtime.zeta/iterator
                                      !xs)]
                                    (clojure.core/let
                                     [CATA_RESULT__32291__auto__
                                      (CATA__FN__67009
                                       ['meander.dev.parse.zeta/star-args
                                        (clojure.core/let
                                         [CATA_RESULT__32291__auto__
                                          (CATA__FN__67009
                                           ['meander.dev.parse.zeta/parse-string
                                            (clojure.core/into
                                             []
                                             (clojure.core/vec
                                              (clojure.core/iterator-seq
                                               !xs__counter)))
                                            ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__32291__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__32291__auto__
                                           0)))
                                        (clojure.core/let
                                         [CATA_RESULT__32291__auto__
                                          (CATA__FN__67009
                                           ['meander.dev.parse.zeta/parse-string
                                            ?rest
                                            ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__32291__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__32291__auto__
                                           0)))])]
                                     (if
                                      (meander.runtime.zeta/fail?
                                       CATA_RESULT__32291__auto__)
                                      (throw
                                       (meander.runtime.zeta/fail))
                                      (clojure.core/nth
                                       CATA_RESULT__32291__auto__
                                       0))))]
                                  (catch
                                   java.lang.Exception
                                   e__33231__auto__
                                   (if
                                    (meander.runtime.zeta/fail?
                                     e__33231__auto__)
                                    (meander.runtime.zeta/fail)
                                    (throw e__33231__auto__))))))
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail)))))]
                        (if
                         (meander.runtime.zeta/fail?
                          ret__31152__auto__)
                         (meander.runtime.zeta/fail)
                         ret__31152__auto__))))]
                    (if
                     (meander.runtime.zeta/fail? result__67987)
                     (recur (clojure.core/next search_space__67986))
                     result__67987))
                   (state__67950)))
                 (state__67950))
                (state__67950)))
              (state__67950
               []
               (if
                (clojure.core/=
                 input__66972_nth_0__
                 'meander.dev.parse.zeta/star-args)
                (if
                 (clojure.core/map? input__66972_nth_1__)
                 (clojure.core/let
                  [VAL__67262 (.valAt input__66972_nth_1__ :tag)]
                  (if
                   (clojure.core/let
                    [x__30048__auto__ VAL__67262]
                    (clojure.core/case
                     x__30048__auto__
                     (:cat :string-cat)
                     true
                     false))
                   (clojure.core/let
                    [VAL__67263
                     (.valAt input__66972_nth_1__ :sequence)]
                    (if
                     (clojure.core/vector? VAL__67263)
                     (if
                      (clojure.core/=
                       (clojure.core/count VAL__67263)
                       1)
                      (clojure.core/let
                       [VAL__67263_nth_0__
                        (clojure.core/nth VAL__67263 0)]
                       (if
                        (clojure.core/map? VAL__67263_nth_0__)
                        (clojure.core/let
                         [VAL__67268 (.valAt VAL__67263_nth_0__ :tag)]
                         (clojure.core/case
                          VAL__67268
                          (:memory-variable)
                          (clojure.core/let
                           [?memory-variable VAL__67263_nth_0__]
                           (clojure.core/let
                            [VAL__67264
                             (.valAt input__66972_nth_1__ :next)]
                            (if
                             (clojure.core/map? VAL__67264)
                             (clojure.core/let
                              [VAL__67265 (.valAt VAL__67264 :tag)]
                              (clojure.core/case
                               VAL__67265
                               (:empty)
                               (clojure.core/let
                                [?next input__66972_nth_2__]
                                (try
                                 [(clojure.core/let
                                   [CATA_RESULT__32291__auto__
                                    (CATA__FN__67009
                                     ['meander.dev.parse.zeta/join-args
                                      {:tag :into,
                                       :memory-variable
                                       ?memory-variable}
                                      ?next])]
                                   (if
                                    (meander.runtime.zeta/fail?
                                     CATA_RESULT__32291__auto__)
                                    (throw (meander.runtime.zeta/fail))
                                    (clojure.core/nth
                                     CATA_RESULT__32291__auto__
                                     0)))]
                                 (catch
                                  java.lang.Exception
                                  e__33231__auto__
                                  (if
                                   (meander.runtime.zeta/fail?
                                    e__33231__auto__)
                                   (meander.runtime.zeta/fail)
                                   (throw e__33231__auto__)))))
                               (state__67951)))
                             (state__67951))))
                          (state__67951)))
                        (state__67951)))
                      (state__67951))
                     (state__67951)))
                   (state__67951)))
                 (state__67951))
                (state__67951)))
              (state__67951
               []
               (if
                (clojure.core/=
                 input__66972_nth_0__
                 'meander.dev.parse.zeta/star-args)
                (clojure.core/let
                 [?pattern input__66972_nth_1__]
                 (clojure.core/let
                  [?next input__66972_nth_2__]
                  (try
                   [{:tag :star, :pattern ?pattern, :next ?next}]
                   (catch
                    java.lang.Exception
                    e__33231__auto__
                    (if
                     (meander.runtime.zeta/fail? e__33231__auto__)
                     (meander.runtime.zeta/fail)
                     (throw e__33231__auto__))))))
                (state__67952)))
              (state__67952
               []
               (if
                (clojure.core/let
                 [x__30048__auto__ input__66972_nth_0__]
                 (clojure.core/case
                  x__30048__auto__
                  (meander.dev.parse.zeta/parse-seq
                   meander.dev.parse.zeta/parse-string)
                  true
                  false))
                (clojure.core/letfn
                 [(state__67991
                   []
                   (if
                    (clojure.core/vector? input__66972_nth_1__)
                    (clojure.core/let
                     [input__66972_nth_1___l__
                      (clojure.core/subvec
                       input__66972_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__66972_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__66972_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__66972_nth_1___r__
                        (clojure.core/subvec input__66972_nth_1__ 1)]
                       (clojure.core/let
                        [input__66972_nth_1___l___nth_0__
                         (clojure.core/nth input__66972_nth_1___l__ 0)]
                        (if
                         (clojure.core/symbol?
                          input__66972_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__67276
                           (clojure.core/namespace
                            input__66972_nth_1___l___nth_0__)]
                          (clojure.core/case
                           X__67276
                           (nil)
                           (clojure.core/let
                            [X__67278
                             (clojure.core/name
                              input__66972_nth_1___l___nth_0__)]
                            (if
                             (clojure.core/string? X__67278)
                             (clojure.core/let
                              [ret__67279
                               (clojure.core/re-matches
                                #"\.\.(\d+)"
                                X__67278)]
                              (if
                               (clojure.core/some? ret__67279)
                               (if
                                (clojure.core/vector? ret__67279)
                                (if
                                 (clojure.core/=
                                  (clojure.core/count ret__67279)
                                  2)
                                 (clojure.core/let
                                  [ret__67279_nth_1__
                                   (clojure.core/nth ret__67279 1)]
                                  (clojure.core/let
                                   [?n ret__67279_nth_1__]
                                   (clojure.core/let
                                    [?operator
                                     input__66972_nth_1___l___nth_0__]
                                    (clojure.core/let
                                     [?rest input__66972_nth_1___r__]
                                     (clojure.core/let
                                      [?env input__66972_nth_2__]
                                      (try
                                       [{:tag :syntax-error,
                                         :message
                                         "The n or more operator ..N must be preceeded by at least one pattern"}]
                                       (catch
                                        java.lang.Exception
                                        e__33231__auto__
                                        (if
                                         (meander.runtime.zeta/fail?
                                          e__33231__auto__)
                                         (meander.runtime.zeta/fail)
                                         (throw
                                          e__33231__auto__)))))))))
                                 (state__67992))
                                (state__67992))
                               (state__67992)))
                             (state__67992)))
                           (state__67992)))
                         (state__67992))))
                      (state__67992)))
                    (state__67992)))
                  (state__67992
                   []
                   (clojure.core/let
                    [?rule-name input__66972_nth_0__]
                    (if
                     (clojure.core/vector? input__66972_nth_1__)
                     (clojure.core/loop
                      [search_space__67997
                       (meander.match.runtime.epsilon/partitions
                        2
                        input__66972_nth_1__)]
                      (if
                       (clojure.core/seq search_space__67997)
                       (clojure.core/let
                        [input__66972_nth_1___parts__
                         (clojure.core/first search_space__67997)
                         result__67998
                         (clojure.core/let
                          [input__66972_nth_1___l__
                           (clojure.core/nth
                            input__66972_nth_1___parts__
                            0)
                           input__66972_nth_1___r__
                           (clojure.core/nth
                            input__66972_nth_1___parts__
                            1)]
                          (clojure.core/let
                           [!xs []]
                           (clojure.core/let
                            [ret__31152__auto__
                             (meander.runtime.zeta/epsilon-run-star-1
                              input__66972_nth_1___l__
                              [!xs]
                              (clojure.core/fn
                               [[!xs] input__67295]
                               (clojure.core/let
                                [input__67295_nth_0__
                                 (clojure.core/nth input__67295 0)]
                                (clojure.core/letfn
                                 [(save__67296
                                   []
                                   (meander.runtime.zeta/fail))
                                  (f__68001
                                   []
                                   (clojure.core/let
                                    [!xs
                                     (clojure.core/conj
                                      !xs
                                      input__67295_nth_0__)]
                                    [!xs]))]
                                 (if
                                  (clojure.core/symbol?
                                   input__67295_nth_0__)
                                  (clojure.core/let
                                   [X__67298
                                    (clojure.core/namespace
                                     input__67295_nth_0__)]
                                   (clojure.core/case
                                    X__67298
                                    (nil)
                                    (clojure.core/let
                                     [X__67300
                                      (clojure.core/name
                                       input__67295_nth_0__)]
                                     (if
                                      (clojure.core/string? X__67300)
                                      (if
                                       (clojure.core/re-matches
                                        #"\.\.(?:\.|\d+)"
                                        X__67300)
                                       (save__67296)
                                       (f__68001))
                                      (f__68001)))
                                    (f__68001)))
                                  (f__68001)))))
                              (clojure.core/fn
                               [[!xs]]
                               (clojure.core/let
                                [input__66972_nth_1___r___l__
                                 (clojure.core/subvec
                                  input__66972_nth_1___r__
                                  0
                                  (clojure.core/min
                                   (clojure.core/count
                                    input__66972_nth_1___r__)
                                   1))]
                                (if
                                 (clojure.core/=
                                  (clojure.core/count
                                   input__66972_nth_1___r___l__)
                                  1)
                                 (clojure.core/let
                                  [input__66972_nth_1___r___r__
                                   (clojure.core/subvec
                                    input__66972_nth_1___r__
                                    1)]
                                  (clojure.core/let
                                   [input__66972_nth_1___r___l___nth_0__
                                    (clojure.core/nth
                                     input__66972_nth_1___r___l__
                                     0)]
                                   (if
                                    (clojure.core/symbol?
                                     input__66972_nth_1___r___l___nth_0__)
                                    (clojure.core/let
                                     [X__67289
                                      (clojure.core/namespace
                                       input__66972_nth_1___r___l___nth_0__)]
                                     (clojure.core/case
                                      X__67289
                                      (nil)
                                      (clojure.core/let
                                       [X__67291
                                        (clojure.core/name
                                         input__66972_nth_1___r___l___nth_0__)]
                                       (if
                                        (clojure.core/string? X__67291)
                                        (clojure.core/let
                                         [ret__67292
                                          (clojure.core/re-matches
                                           #"\.\.(\d+)"
                                           X__67291)]
                                         (if
                                          (clojure.core/some?
                                           ret__67292)
                                          (if
                                           (clojure.core/vector?
                                            ret__67292)
                                           (if
                                            (clojure.core/=
                                             (clojure.core/count
                                              ret__67292)
                                             2)
                                            (clojure.core/let
                                             [ret__67292_nth_1__
                                              (clojure.core/nth
                                               ret__67292
                                               1)]
                                             (clojure.core/let
                                              [?n ret__67292_nth_1__]
                                              (clojure.core/let
                                               [?rest
                                                input__66972_nth_1___r___r__]
                                               (clojure.core/let
                                                [?env
                                                 input__66972_nth_2__]
                                                (try
                                                 [(clojure.core/let
                                                   [!xs__counter
                                                    (meander.runtime.zeta/iterator
                                                     !xs)]
                                                   {:tag :plus,
                                                    :n (Integer. ?n),
                                                    :pattern
                                                    (clojure.core/let
                                                     [CATA_RESULT__32291__auto__
                                                      (CATA__FN__67009
                                                       [?rule-name
                                                        (clojure.core/into
                                                         []
                                                         (clojure.core/vec
                                                          (clojure.core/iterator-seq
                                                           !xs__counter)))
                                                        ?env])]
                                                     (if
                                                      (meander.runtime.zeta/fail?
                                                       CATA_RESULT__32291__auto__)
                                                      (throw
                                                       (meander.runtime.zeta/fail))
                                                      (clojure.core/nth
                                                       CATA_RESULT__32291__auto__
                                                       0))),
                                                    :next
                                                    (clojure.core/let
                                                     [CATA_RESULT__32291__auto__
                                                      (CATA__FN__67009
                                                       [?rule-name
                                                        ?rest
                                                        ?env])]
                                                     (if
                                                      (meander.runtime.zeta/fail?
                                                       CATA_RESULT__32291__auto__)
                                                      (throw
                                                       (meander.runtime.zeta/fail))
                                                      (clojure.core/nth
                                                       CATA_RESULT__32291__auto__
                                                       0)))})]
                                                 (catch
                                                  java.lang.Exception
                                                  e__33231__auto__
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    e__33231__auto__)
                                                   (meander.runtime.zeta/fail)
                                                   (throw
                                                    e__33231__auto__))))))))
                                            (meander.runtime.zeta/fail))
                                           (meander.runtime.zeta/fail))
                                          (meander.runtime.zeta/fail)))
                                        (meander.runtime.zeta/fail)))
                                      (meander.runtime.zeta/fail)))
                                    (meander.runtime.zeta/fail))))
                                 (meander.runtime.zeta/fail)))))]
                            (if
                             (meander.runtime.zeta/fail?
                              ret__31152__auto__)
                             (meander.runtime.zeta/fail)
                             ret__31152__auto__))))]
                        (if
                         (meander.runtime.zeta/fail? result__67998)
                         (recur
                          (clojure.core/next search_space__67997))
                         result__67998))
                       (state__67993)))
                     (state__67993))))
                  (state__67993
                   []
                   (if
                    (clojure.core/vector? input__66972_nth_1__)
                    (clojure.core/let
                     [input__66972_nth_1___l__
                      (clojure.core/subvec
                       input__66972_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__66972_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__66972_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__66972_nth_1___r__
                        (clojure.core/subvec input__66972_nth_1__ 1)]
                       (clojure.core/let
                        [input__66972_nth_1___l___nth_0__
                         (clojure.core/nth input__66972_nth_1___l__ 0)]
                        (if
                         (clojure.core/symbol?
                          input__66972_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__67307
                           (clojure.core/namespace
                            input__66972_nth_1___l___nth_0__)]
                          (clojure.core/case
                           X__67307
                           (nil)
                           (clojure.core/let
                            [X__67309
                             (clojure.core/name
                              input__66972_nth_1___l___nth_0__)]
                            (if
                             (clojure.core/string? X__67309)
                             (clojure.core/let
                              [ret__67310
                               (clojure.core/re-matches
                                #"\.\.(\?.+)"
                                X__67309)]
                              (if
                               (clojure.core/some? ret__67310)
                               (if
                                (clojure.core/vector? ret__67310)
                                (if
                                 (clojure.core/=
                                  (clojure.core/count ret__67310)
                                  2)
                                 (clojure.core/let
                                  [ret__67310_nth_1__
                                   (clojure.core/nth ret__67310 1)]
                                  (clojure.core/let
                                   [?n ret__67310_nth_1__]
                                   (clojure.core/let
                                    [?operator
                                     input__66972_nth_1___l___nth_0__]
                                    (clojure.core/let
                                     [?rest input__66972_nth_1___r__]
                                     (clojure.core/let
                                      [?env input__66972_nth_2__]
                                      (try
                                       [{:tag :syntax-error,
                                         :message
                                         "The ?n or more operator ..?n must be preceeded by at least one pattern"}]
                                       (catch
                                        java.lang.Exception
                                        e__33231__auto__
                                        (if
                                         (meander.runtime.zeta/fail?
                                          e__33231__auto__)
                                         (meander.runtime.zeta/fail)
                                         (throw
                                          e__33231__auto__)))))))))
                                 (state__67994))
                                (state__67994))
                               (state__67994)))
                             (state__67994)))
                           (state__67994)))
                         (state__67994))))
                      (state__67994)))
                    (state__67994)))
                  (state__67994
                   []
                   (clojure.core/let
                    [?rule-name input__66972_nth_0__]
                    (if
                     (clojure.core/vector? input__66972_nth_1__)
                     (clojure.core/loop
                      [search_space__68002
                       (meander.match.runtime.epsilon/partitions
                        2
                        input__66972_nth_1__)]
                      (if
                       (clojure.core/seq search_space__68002)
                       (clojure.core/let
                        [input__66972_nth_1___parts__
                         (clojure.core/first search_space__68002)
                         result__68003
                         (clojure.core/let
                          [input__66972_nth_1___l__
                           (clojure.core/nth
                            input__66972_nth_1___parts__
                            0)
                           input__66972_nth_1___r__
                           (clojure.core/nth
                            input__66972_nth_1___parts__
                            1)]
                          (clojure.core/let
                           [!xs []]
                           (clojure.core/let
                            [ret__31152__auto__
                             (meander.runtime.zeta/epsilon-run-star-1
                              input__66972_nth_1___l__
                              [!xs]
                              (clojure.core/fn
                               [[!xs] input__67326]
                               (clojure.core/let
                                [input__67326_nth_0__
                                 (clojure.core/nth input__67326 0)]
                                (clojure.core/letfn
                                 [(save__67327
                                   []
                                   (meander.runtime.zeta/fail))
                                  (f__68006
                                   []
                                   (clojure.core/let
                                    [!xs
                                     (clojure.core/conj
                                      !xs
                                      input__67326_nth_0__)]
                                    [!xs]))]
                                 (if
                                  (clojure.core/symbol?
                                   input__67326_nth_0__)
                                  (clojure.core/let
                                   [X__67329
                                    (clojure.core/namespace
                                     input__67326_nth_0__)]
                                   (clojure.core/case
                                    X__67329
                                    (nil)
                                    (clojure.core/let
                                     [X__67331
                                      (clojure.core/name
                                       input__67326_nth_0__)]
                                     (if
                                      (clojure.core/string? X__67331)
                                      (if
                                       (clojure.core/re-matches
                                        #"\.\.(?:\.|\d+)"
                                        X__67331)
                                       (save__67327)
                                       (f__68006))
                                      (f__68006)))
                                    (f__68006)))
                                  (f__68006)))))
                              (clojure.core/fn
                               [[!xs]]
                               (clojure.core/let
                                [input__66972_nth_1___r___l__
                                 (clojure.core/subvec
                                  input__66972_nth_1___r__
                                  0
                                  (clojure.core/min
                                   (clojure.core/count
                                    input__66972_nth_1___r__)
                                   1))]
                                (if
                                 (clojure.core/=
                                  (clojure.core/count
                                   input__66972_nth_1___r___l__)
                                  1)
                                 (clojure.core/let
                                  [input__66972_nth_1___r___r__
                                   (clojure.core/subvec
                                    input__66972_nth_1___r__
                                    1)]
                                  (clojure.core/let
                                   [input__66972_nth_1___r___l___nth_0__
                                    (clojure.core/nth
                                     input__66972_nth_1___r___l__
                                     0)]
                                   (if
                                    (clojure.core/symbol?
                                     input__66972_nth_1___r___l___nth_0__)
                                    (clojure.core/let
                                     [X__67320
                                      (clojure.core/namespace
                                       input__66972_nth_1___r___l___nth_0__)]
                                     (clojure.core/case
                                      X__67320
                                      (nil)
                                      (clojure.core/let
                                       [X__67322
                                        (clojure.core/name
                                         input__66972_nth_1___r___l___nth_0__)]
                                       (if
                                        (clojure.core/string? X__67322)
                                        (clojure.core/let
                                         [ret__67323
                                          (clojure.core/re-matches
                                           #"\.\.(\?.+)"
                                           X__67322)]
                                         (if
                                          (clojure.core/some?
                                           ret__67323)
                                          (if
                                           (clojure.core/vector?
                                            ret__67323)
                                           (if
                                            (clojure.core/=
                                             (clojure.core/count
                                              ret__67323)
                                             2)
                                            (clojure.core/let
                                             [ret__67323_nth_1__
                                              (clojure.core/nth
                                               ret__67323
                                               1)]
                                             (clojure.core/let
                                              [?n ret__67323_nth_1__]
                                              (clojure.core/let
                                               [?rest
                                                input__66972_nth_1___r___r__]
                                               (clojure.core/let
                                                [?env
                                                 input__66972_nth_2__]
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
                                                     [CATA_RESULT__32291__auto__
                                                      (CATA__FN__67009
                                                       [?rule-name
                                                        (clojure.core/into
                                                         []
                                                         (clojure.core/vec
                                                          (clojure.core/iterator-seq
                                                           !xs__counter)))
                                                        ?env])]
                                                     (if
                                                      (meander.runtime.zeta/fail?
                                                       CATA_RESULT__32291__auto__)
                                                      (throw
                                                       (meander.runtime.zeta/fail))
                                                      (clojure.core/nth
                                                       CATA_RESULT__32291__auto__
                                                       0))),
                                                    :next
                                                    (clojure.core/let
                                                     [CATA_RESULT__32291__auto__
                                                      (CATA__FN__67009
                                                       [?rule-name
                                                        ?rest
                                                        ?env])]
                                                     (if
                                                      (meander.runtime.zeta/fail?
                                                       CATA_RESULT__32291__auto__)
                                                      (throw
                                                       (meander.runtime.zeta/fail))
                                                      (clojure.core/nth
                                                       CATA_RESULT__32291__auto__
                                                       0)))})]
                                                 (catch
                                                  java.lang.Exception
                                                  e__33231__auto__
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    e__33231__auto__)
                                                   (meander.runtime.zeta/fail)
                                                   (throw
                                                    e__33231__auto__))))))))
                                            (meander.runtime.zeta/fail))
                                           (meander.runtime.zeta/fail))
                                          (meander.runtime.zeta/fail)))
                                        (meander.runtime.zeta/fail)))
                                      (meander.runtime.zeta/fail)))
                                    (meander.runtime.zeta/fail))))
                                 (meander.runtime.zeta/fail)))))]
                            (if
                             (meander.runtime.zeta/fail?
                              ret__31152__auto__)
                             (meander.runtime.zeta/fail)
                             ret__31152__auto__))))]
                        (if
                         (meander.runtime.zeta/fail? result__68003)
                         (recur
                          (clojure.core/next search_space__68002))
                         result__68003))
                       (state__67995)))
                     (state__67995))))
                  (state__67995
                   []
                   (if
                    (clojure.core/vector? input__66972_nth_1__)
                    (clojure.core/let
                     [input__66972_nth_1___l__
                      (clojure.core/subvec
                       input__66972_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__66972_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__66972_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__66972_nth_1___r__
                        (clojure.core/subvec input__66972_nth_1__ 1)]
                       (clojure.core/let
                        [input__66972_nth_1___l___nth_0__
                         (clojure.core/nth input__66972_nth_1___l__ 0)]
                        (if
                         (clojure.core/symbol?
                          input__66972_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__67338
                           (clojure.core/namespace
                            input__66972_nth_1___l___nth_0__)]
                          (clojure.core/case
                           X__67338
                           (nil)
                           (clojure.core/let
                            [X__67340
                             (clojure.core/name
                              input__66972_nth_1___l___nth_0__)]
                            (if
                             (clojure.core/string? X__67340)
                             (clojure.core/let
                              [ret__67341
                               (clojure.core/re-matches
                                #"\.\.(!.+)"
                                X__67340)]
                              (if
                               (clojure.core/some? ret__67341)
                               (if
                                (clojure.core/vector? ret__67341)
                                (if
                                 (clojure.core/=
                                  (clojure.core/count ret__67341)
                                  2)
                                 (clojure.core/let
                                  [ret__67341_nth_1__
                                   (clojure.core/nth ret__67341 1)]
                                  (clojure.core/let
                                   [?n ret__67341_nth_1__]
                                   (clojure.core/let
                                    [?operator
                                     input__66972_nth_1___l___nth_0__]
                                    (clojure.core/let
                                     [?rest input__66972_nth_1___r__]
                                     (clojure.core/let
                                      [?env input__66972_nth_2__]
                                      (try
                                       [{:tag :syntax-error,
                                         :message
                                         "The operator ..!n must be preceeded by at least one pattern"}]
                                       (catch
                                        java.lang.Exception
                                        e__33231__auto__
                                        (if
                                         (meander.runtime.zeta/fail?
                                          e__33231__auto__)
                                         (meander.runtime.zeta/fail)
                                         (throw
                                          e__33231__auto__)))))))))
                                 (state__67996))
                                (state__67996))
                               (state__67996)))
                             (state__67996)))
                           (state__67996)))
                         (state__67996))))
                      (state__67996)))
                    (state__67996)))
                  (state__67996
                   []
                   (clojure.core/let
                    [?rule-name input__66972_nth_0__]
                    (if
                     (clojure.core/vector? input__66972_nth_1__)
                     (clojure.core/loop
                      [search_space__68007
                       (meander.match.runtime.epsilon/partitions
                        2
                        input__66972_nth_1__)]
                      (if
                       (clojure.core/seq search_space__68007)
                       (clojure.core/let
                        [input__66972_nth_1___parts__
                         (clojure.core/first search_space__68007)
                         result__68008
                         (clojure.core/let
                          [input__66972_nth_1___l__
                           (clojure.core/nth
                            input__66972_nth_1___parts__
                            0)
                           input__66972_nth_1___r__
                           (clojure.core/nth
                            input__66972_nth_1___parts__
                            1)]
                          (clojure.core/let
                           [!xs []]
                           (clojure.core/let
                            [ret__31152__auto__
                             (meander.runtime.zeta/epsilon-run-star-1
                              input__66972_nth_1___l__
                              [!xs]
                              (clojure.core/fn
                               [[!xs] input__67357]
                               (clojure.core/let
                                [input__67357_nth_0__
                                 (clojure.core/nth input__67357 0)]
                                (clojure.core/letfn
                                 [(save__67358
                                   []
                                   (meander.runtime.zeta/fail))
                                  (f__68011
                                   []
                                   (clojure.core/let
                                    [!xs
                                     (clojure.core/conj
                                      !xs
                                      input__67357_nth_0__)]
                                    [!xs]))]
                                 (if
                                  (clojure.core/symbol?
                                   input__67357_nth_0__)
                                  (clojure.core/let
                                   [X__67360
                                    (clojure.core/namespace
                                     input__67357_nth_0__)]
                                   (clojure.core/case
                                    X__67360
                                    (nil)
                                    (clojure.core/let
                                     [X__67362
                                      (clojure.core/name
                                       input__67357_nth_0__)]
                                     (if
                                      (clojure.core/string? X__67362)
                                      (if
                                       (clojure.core/re-matches
                                        #"\.\.(?:\.|\d+)"
                                        X__67362)
                                       (save__67358)
                                       (f__68011))
                                      (f__68011)))
                                    (f__68011)))
                                  (f__68011)))))
                              (clojure.core/fn
                               [[!xs]]
                               (clojure.core/let
                                [input__66972_nth_1___r___l__
                                 (clojure.core/subvec
                                  input__66972_nth_1___r__
                                  0
                                  (clojure.core/min
                                   (clojure.core/count
                                    input__66972_nth_1___r__)
                                   1))]
                                (if
                                 (clojure.core/=
                                  (clojure.core/count
                                   input__66972_nth_1___r___l__)
                                  1)
                                 (clojure.core/let
                                  [input__66972_nth_1___r___r__
                                   (clojure.core/subvec
                                    input__66972_nth_1___r__
                                    1)]
                                  (clojure.core/let
                                   [input__66972_nth_1___r___l___nth_0__
                                    (clojure.core/nth
                                     input__66972_nth_1___r___l__
                                     0)]
                                   (if
                                    (clojure.core/symbol?
                                     input__66972_nth_1___r___l___nth_0__)
                                    (clojure.core/let
                                     [X__67351
                                      (clojure.core/namespace
                                       input__66972_nth_1___r___l___nth_0__)]
                                     (clojure.core/case
                                      X__67351
                                      (nil)
                                      (clojure.core/let
                                       [X__67353
                                        (clojure.core/name
                                         input__66972_nth_1___r___l___nth_0__)]
                                       (if
                                        (clojure.core/string? X__67353)
                                        (clojure.core/let
                                         [ret__67354
                                          (clojure.core/re-matches
                                           #"\.\.(\!.+)"
                                           X__67353)]
                                         (if
                                          (clojure.core/some?
                                           ret__67354)
                                          (if
                                           (clojure.core/vector?
                                            ret__67354)
                                           (if
                                            (clojure.core/=
                                             (clojure.core/count
                                              ret__67354)
                                             2)
                                            (clojure.core/let
                                             [ret__67354_nth_1__
                                              (clojure.core/nth
                                               ret__67354
                                               1)]
                                             (clojure.core/let
                                              [?n ret__67354_nth_1__]
                                              (clojure.core/let
                                               [?rest
                                                input__66972_nth_1___r___r__]
                                               (clojure.core/let
                                                [?env
                                                 input__66972_nth_2__]
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
                                                     [CATA_RESULT__32291__auto__
                                                      (CATA__FN__67009
                                                       [?rule-name
                                                        (clojure.core/into
                                                         []
                                                         (clojure.core/vec
                                                          (clojure.core/iterator-seq
                                                           !xs__counter)))
                                                        ?env])]
                                                     (if
                                                      (meander.runtime.zeta/fail?
                                                       CATA_RESULT__32291__auto__)
                                                      (throw
                                                       (meander.runtime.zeta/fail))
                                                      (clojure.core/nth
                                                       CATA_RESULT__32291__auto__
                                                       0))),
                                                    :next
                                                    (clojure.core/let
                                                     [CATA_RESULT__32291__auto__
                                                      (CATA__FN__67009
                                                       [?rule-name
                                                        ?rest
                                                        ?env])]
                                                     (if
                                                      (meander.runtime.zeta/fail?
                                                       CATA_RESULT__32291__auto__)
                                                      (throw
                                                       (meander.runtime.zeta/fail))
                                                      (clojure.core/nth
                                                       CATA_RESULT__32291__auto__
                                                       0)))})]
                                                 (catch
                                                  java.lang.Exception
                                                  e__33231__auto__
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    e__33231__auto__)
                                                   (meander.runtime.zeta/fail)
                                                   (throw
                                                    e__33231__auto__))))))))
                                            (meander.runtime.zeta/fail))
                                           (meander.runtime.zeta/fail))
                                          (meander.runtime.zeta/fail)))
                                        (meander.runtime.zeta/fail)))
                                      (meander.runtime.zeta/fail)))
                                    (meander.runtime.zeta/fail))))
                                 (meander.runtime.zeta/fail)))))]
                            (if
                             (meander.runtime.zeta/fail?
                              ret__31152__auto__)
                             (meander.runtime.zeta/fail)
                             ret__31152__auto__))))]
                        (if
                         (meander.runtime.zeta/fail? result__68008)
                         (recur
                          (clojure.core/next search_space__68007))
                         result__68008))
                       (state__67953)))
                     (state__67953))))]
                 (state__67991))
                (state__67953)))
              (state__67953
               []
               (if
                (clojure.core/=
                 input__66972_nth_0__
                 'meander.dev.parse.zeta/parse-seq)
                (if
                 (clojure.core/vector? input__66972_nth_1__)
                 (clojure.core/let
                  [!xs (clojure.core/vec input__66972_nth_1__)]
                  (clojure.core/let
                   [?env input__66972_nth_2__]
                   (try
                    [(clojure.core/let
                      [!xs__counter
                       (meander.runtime.zeta/iterator !xs)]
                      (clojure.core/let
                       [CATA_RESULT__32291__auto__
                        (CATA__FN__67009
                         ['meander.dev.parse.zeta/cat-args
                          (clojure.core/into
                           []
                           (clojure.core/loop
                            [return__67010 (clojure.core/transient [])]
                            (if
                             (clojure.core/and (.hasNext !xs__counter))
                             (recur
                              (clojure.core/conj!
                               return__67010
                               (clojure.core/let
                                [CATA_RESULT__32291__auto__
                                 (CATA__FN__67009
                                  [(if
                                    (.hasNext !xs__counter)
                                    (.next !xs__counter))
                                   ?env])]
                                (if
                                 (meander.runtime.zeta/fail?
                                  CATA_RESULT__32291__auto__)
                                 (throw (meander.runtime.zeta/fail))
                                 (clojure.core/nth
                                  CATA_RESULT__32291__auto__
                                  0)))))
                             (clojure.core/persistent!
                              return__67010))))
                          {:tag :empty}])]
                       (if
                        (meander.runtime.zeta/fail?
                         CATA_RESULT__32291__auto__)
                        (throw (meander.runtime.zeta/fail))
                        (clojure.core/nth
                         CATA_RESULT__32291__auto__
                         0))))]
                    (catch
                     java.lang.Exception
                     e__33231__auto__
                     (if
                      (meander.runtime.zeta/fail? e__33231__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__33231__auto__))))))
                 (state__67954))
                (state__67954)))
              (state__67954
               []
               (if
                (clojure.core/=
                 input__66972_nth_0__
                 'meander.dev.parse.zeta/parse-string)
                (if
                 (clojure.core/vector? input__66972_nth_1__)
                 (clojure.core/let
                  [!xs (clojure.core/vec input__66972_nth_1__)]
                  (clojure.core/let
                   [?env input__66972_nth_2__]
                   (try
                    [(clojure.core/let
                      [!xs__counter
                       (meander.runtime.zeta/iterator !xs)]
                      (clojure.core/let
                       [CATA_RESULT__32291__auto__
                        (CATA__FN__67009
                         ['meander.dev.parse.zeta/string-cat-args
                          (clojure.core/into
                           []
                           (clojure.core/loop
                            [return__67011 (clojure.core/transient [])]
                            (if
                             (clojure.core/and (.hasNext !xs__counter))
                             (recur
                              (clojure.core/conj!
                               return__67011
                               (clojure.core/let
                                [CATA_RESULT__32291__auto__
                                 (CATA__FN__67009
                                  [(if
                                    (.hasNext !xs__counter)
                                    (.next !xs__counter))
                                   ?env])]
                                (if
                                 (meander.runtime.zeta/fail?
                                  CATA_RESULT__32291__auto__)
                                 (throw (meander.runtime.zeta/fail))
                                 (clojure.core/nth
                                  CATA_RESULT__32291__auto__
                                  0)))))
                             (clojure.core/persistent!
                              return__67011))))
                          {:tag :empty}])]
                       (if
                        (meander.runtime.zeta/fail?
                         CATA_RESULT__32291__auto__)
                        (throw (meander.runtime.zeta/fail))
                        (clojure.core/nth
                         CATA_RESULT__32291__auto__
                         0))))]
                    (catch
                     java.lang.Exception
                     e__33231__auto__
                     (if
                      (meander.runtime.zeta/fail? e__33231__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__33231__auto__))))))
                 (state__67955))
                (state__67955)))
              (state__67955
               []
               (if
                (clojure.core/=
                 input__66972_nth_0__
                 'meander.dev.parse.zeta/string-cat-args)
                (if
                 (clojure.core/vector? input__66972_nth_1__)
                 (clojure.core/let
                  [!forms []]
                  (clojure.core/let
                   [ret__31152__auto__
                    (meander.runtime.zeta/epsilon-run-star-1
                     input__66972_nth_1__
                     [!forms]
                     (clojure.core/fn
                      [[!forms] input__67377]
                      (clojure.core/let
                       [input__67377_nth_0__
                        (clojure.core/nth input__67377 0)]
                       (if
                        (clojure.core/map? input__67377_nth_0__)
                        (clojure.core/let
                         [VAL__67378
                          (.valAt input__67377_nth_0__ :tag)]
                         (clojure.core/case
                          VAL__67378
                          (:literal)
                          (clojure.core/let
                           [VAL__67379
                            (.valAt input__67377_nth_0__ :type)]
                           (if
                            (clojure.core/let
                             [x__30048__auto__ VAL__67379]
                             (clojure.core/case
                              x__30048__auto__
                              (:string :char)
                              true
                              false))
                            (clojure.core/let
                             [VAL__67380
                              (.valAt input__67377_nth_0__ :form)]
                             (clojure.core/let
                              [!forms
                               (clojure.core/conj !forms VAL__67380)]
                              [!forms]))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))
                        (meander.runtime.zeta/fail))))
                     (clojure.core/fn
                      [[!forms]]
                      (if
                       (clojure.core/map? input__66972_nth_2__)
                       (clojure.core/let
                        [VAL__67374 (.valAt input__66972_nth_2__ :tag)]
                        (clojure.core/case
                         VAL__67374
                         (:empty)
                         (try
                          [{:tag :literal,
                            :type :string,
                            :form
                            (clojure.string/join
                             (clojure.core/into [] !forms))}]
                          (catch
                           java.lang.Exception
                           e__33231__auto__
                           (if
                            (meander.runtime.zeta/fail?
                             e__33231__auto__)
                            (meander.runtime.zeta/fail)
                            (throw e__33231__auto__))))
                         (state__67956)))
                       (state__67956))))]
                   (if
                    (meander.runtime.zeta/fail? ret__31152__auto__)
                    (state__67956)
                    ret__31152__auto__)))
                 (state__67956))
                (state__67956)))
              (state__67956
               []
               (if
                (clojure.core/=
                 input__66972_nth_0__
                 'meander.dev.parse.zeta/cat-args)
                (if
                 (clojure.core/vector? input__66972_nth_1__)
                 (clojure.core/let
                  [!forms []]
                  (clojure.core/let
                   [ret__31152__auto__
                    (meander.runtime.zeta/epsilon-run-star-1
                     input__66972_nth_1__
                     [!forms]
                     (clojure.core/fn
                      [[!forms] input__67387]
                      (clojure.core/let
                       [input__67387_nth_0__
                        (clojure.core/nth input__67387 0)]
                       (if
                        (clojure.core/map? input__67387_nth_0__)
                        (clojure.core/let
                         [VAL__67388
                          (.valAt input__67387_nth_0__ :tag)]
                         (clojure.core/case
                          VAL__67388
                          (:literal)
                          (clojure.core/let
                           [VAL__67389
                            (.valAt input__67387_nth_0__ :form)]
                           (clojure.core/let
                            [!forms
                             (clojure.core/conj !forms VAL__67389)]
                            [!forms]))
                          (meander.runtime.zeta/fail)))
                        (meander.runtime.zeta/fail))))
                     (clojure.core/fn
                      [[!forms]]
                      (if
                       (clojure.core/map? input__66972_nth_2__)
                       (clojure.core/let
                        [VAL__67384 (.valAt input__66972_nth_2__ :tag)]
                        (clojure.core/case
                         VAL__67384
                         (:empty)
                         (try
                          [{:tag :literal,
                            :form (clojure.core/into [] !forms)}]
                          (catch
                           java.lang.Exception
                           e__33231__auto__
                           (if
                            (meander.runtime.zeta/fail?
                             e__33231__auto__)
                            (meander.runtime.zeta/fail)
                            (throw e__33231__auto__))))
                         (state__67957)))
                       (state__67957))))]
                   (if
                    (meander.runtime.zeta/fail? ret__31152__auto__)
                    (state__67957)
                    ret__31152__auto__)))
                 (state__67957))
                (state__67957)))
              (state__67957
               []
               (if
                (clojure.core/=
                 input__66972_nth_0__
                 'meander.dev.parse.zeta/cat-args)
                (clojure.core/let
                 [?sequence input__66972_nth_1__]
                 (clojure.core/let
                  [?next input__66972_nth_2__]
                  (try
                   [{:tag :cat, :sequence ?sequence, :next ?next}]
                   (catch
                    java.lang.Exception
                    e__33231__auto__
                    (if
                     (meander.runtime.zeta/fail? e__33231__auto__)
                     (meander.runtime.zeta/fail)
                     (throw e__33231__auto__))))))
                (state__67958)))
              (state__67958
               []
               (if
                (clojure.core/=
                 input__66972_nth_0__
                 'meander.dev.parse.zeta/string-cat-args)
                (if
                 (clojure.core/vector? input__66972_nth_1__)
                 (clojure.core/let
                  [input__66972_nth_1___l__
                   (clojure.core/subvec
                    input__66972_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__66972_nth_1__)
                     1))]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__66972_nth_1___l__)
                    1)
                   (clojure.core/let
                    [input__66972_nth_1___r__
                     (clojure.core/subvec input__66972_nth_1__ 1)]
                    (clojure.core/let
                     [input__66972_nth_1___l___nth_0__
                      (clojure.core/nth input__66972_nth_1___l__ 0)]
                     (if
                      (clojure.core/map?
                       input__66972_nth_1___l___nth_0__)
                      (clojure.core/let
                       [VAL__67397
                        (.valAt input__66972_nth_1___l___nth_0__ :tag)]
                       (clojure.core/case
                        VAL__67397
                        (:literal)
                        (clojure.core/let
                         [VAL__67398
                          (.valAt
                           input__66972_nth_1___l___nth_0__
                           :type)]
                         (clojure.core/case
                          VAL__67398
                          (:string)
                          (clojure.core/let
                           [?ast input__66972_nth_1___l___nth_0__]
                           (clojure.core/let
                            [?rest input__66972_nth_1___r__]
                            (clojure.core/let
                             [?next input__66972_nth_2__]
                             (try
                              [(clojure.core/let
                                [CATA_RESULT__32291__auto__
                                 (CATA__FN__67009
                                  ['meander.dev.parse.zeta/string-join-args
                                   ?ast
                                   (clojure.core/let
                                    [CATA_RESULT__32291__auto__
                                     (CATA__FN__67009
                                      ['meander.dev.parse.zeta/string-cat-args
                                       ?rest
                                       ?next])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__32291__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__32291__auto__
                                      0)))])]
                                (if
                                 (meander.runtime.zeta/fail?
                                  CATA_RESULT__32291__auto__)
                                 (throw (meander.runtime.zeta/fail))
                                 (clojure.core/nth
                                  CATA_RESULT__32291__auto__
                                  0)))]
                              (catch
                               java.lang.Exception
                               e__33231__auto__
                               (if
                                (meander.runtime.zeta/fail?
                                 e__33231__auto__)
                                (meander.runtime.zeta/fail)
                                (throw e__33231__auto__)))))))
                          (state__67959)))
                        (state__67959)))
                      (state__67959))))
                   (state__67959)))
                 (state__67959))
                (state__67959)))
              (state__67959
               []
               (if
                (clojure.core/=
                 input__66972_nth_0__
                 'meander.dev.parse.zeta/string-cat-args)
                (clojure.core/let
                 [?sequence input__66972_nth_1__]
                 (clojure.core/let
                  [?next input__66972_nth_2__]
                  (try
                   [{:tag :string-cat,
                     :sequence ?sequence,
                     :next ?next}]
                   (catch
                    java.lang.Exception
                    e__33231__auto__
                    (if
                     (meander.runtime.zeta/fail? e__33231__auto__)
                     (meander.runtime.zeta/fail)
                     (throw e__33231__auto__))))))
                (state__67960)))
              (state__67960
               []
               (if
                (clojure.core/=
                 input__66972_nth_0__
                 'meander.dev.parse.zeta/join-args)
                (if
                 (clojure.core/map? input__66972_nth_1__)
                 (clojure.core/let
                  [VAL__67403 (.valAt input__66972_nth_1__ :tag)]
                  (clojure.core/case
                   VAL__67403
                   (:cat)
                   (clojure.core/let
                    [VAL__67404
                     (.valAt input__66972_nth_1__ :sequence)]
                    (clojure.core/let
                     [?sequence VAL__67404]
                     (clojure.core/let
                      [VAL__67405 (.valAt input__66972_nth_1__ :next)]
                      (if
                       (clojure.core/map? VAL__67405)
                       (clojure.core/let
                        [VAL__67406 (.valAt VAL__67405 :tag)]
                        (clojure.core/case
                         VAL__67406
                         (:empty)
                         (clojure.core/let
                          [?right input__66972_nth_2__]
                          (try
                           [(clojure.core/let
                             [CATA_RESULT__32291__auto__
                              (CATA__FN__67009
                               ['meander.dev.parse.zeta/cat-args
                                ?sequence
                                ?right])]
                             (if
                              (meander.runtime.zeta/fail?
                               CATA_RESULT__32291__auto__)
                              (throw (meander.runtime.zeta/fail))
                              (clojure.core/nth
                               CATA_RESULT__32291__auto__
                               0)))]
                           (catch
                            java.lang.Exception
                            e__33231__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__33231__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__33231__auto__)))))
                         (state__67961)))
                       (state__67961)))))
                   (state__67961)))
                 (state__67961))
                (state__67961)))
              (state__67961
               []
               (if
                (clojure.core/=
                 input__66972_nth_0__
                 'meander.dev.parse.zeta/join-args)
                (clojure.core/let
                 [?left input__66972_nth_1__]
                 (if
                  (clojure.core/map? input__66972_nth_2__)
                  (clojure.core/let
                   [VAL__67409 (.valAt input__66972_nth_2__ :tag)]
                   (clojure.core/case
                    VAL__67409
                    (:empty)
                    (try
                     [?left]
                     (catch
                      java.lang.Exception
                      e__33231__auto__
                      (if
                       (meander.runtime.zeta/fail? e__33231__auto__)
                       (meander.runtime.zeta/fail)
                       (throw e__33231__auto__))))
                    (state__67962)))
                  (state__67962)))
                (state__67962)))
              (state__67962
               []
               (if
                (clojure.core/=
                 input__66972_nth_0__
                 'meander.dev.parse.zeta/join-args)
                (if
                 (clojure.core/map? input__66972_nth_1__)
                 (clojure.core/let
                  [VAL__67412 (.valAt input__66972_nth_1__ :tag)]
                  (clojure.core/case
                   VAL__67412
                   (:empty)
                   (clojure.core/let
                    [?right input__66972_nth_2__]
                    (try
                     [?right]
                     (catch
                      java.lang.Exception
                      e__33231__auto__
                      (if
                       (meander.runtime.zeta/fail? e__33231__auto__)
                       (meander.runtime.zeta/fail)
                       (throw e__33231__auto__)))))
                   (state__67963)))
                 (state__67963))
                (state__67963)))
              (state__67963
               []
               (if
                (clojure.core/=
                 input__66972_nth_0__
                 'meander.dev.parse.zeta/join-args)
                (clojure.core/let
                 [?left input__66972_nth_1__]
                 (clojure.core/let
                  [?right input__66972_nth_2__]
                  (try
                   [{:tag :join, :left ?left, :right ?right}]
                   (catch
                    java.lang.Exception
                    e__33231__auto__
                    (if
                     (meander.runtime.zeta/fail? e__33231__auto__)
                     (meander.runtime.zeta/fail)
                     (throw e__33231__auto__))))))
                (state__67964)))
              (state__67964
               []
               (if
                (clojure.core/=
                 input__66972_nth_0__
                 'meander.dev.parse.zeta/string-join-args)
                (if
                 (clojure.core/map? input__66972_nth_1__)
                 (clojure.core/let
                  [VAL__67417 (.valAt input__66972_nth_1__ :tag)]
                  (clojure.core/case
                   VAL__67417
                   (:literal)
                   (clojure.core/let
                    [VAL__67418 (.valAt input__66972_nth_1__ :type)]
                    (clojure.core/case
                     VAL__67418
                     (:string)
                     (clojure.core/let
                      [VAL__67419 (.valAt input__66972_nth_1__ :form)]
                      (clojure.core/let
                       [?form-1 VAL__67419]
                       (if
                        (clojure.core/map? input__66972_nth_2__)
                        (clojure.core/let
                         [VAL__67420
                          (.valAt input__66972_nth_2__ :tag)]
                         (clojure.core/case
                          VAL__67420
                          (:string-join)
                          (clojure.core/let
                           [VAL__67421
                            (.valAt input__66972_nth_2__ :left)]
                           (if
                            (clojure.core/map? VAL__67421)
                            (clojure.core/let
                             [VAL__67422 (.valAt VAL__67421 :tag)]
                             (clojure.core/case
                              VAL__67422
                              (:literal)
                              (clojure.core/let
                               [VAL__67423 (.valAt VAL__67421 :type)]
                               (clojure.core/case
                                VAL__67423
                                (:string)
                                (clojure.core/let
                                 [VAL__67424 (.valAt VAL__67421 :form)]
                                 (clojure.core/let
                                  [?form-2 VAL__67424]
                                  (clojure.core/let
                                   [VAL__67425
                                    (.valAt
                                     input__66972_nth_2__
                                     :right)]
                                   (clojure.core/let
                                    [?right VAL__67425]
                                    (try
                                     [(clojure.core/let
                                       [CATA_RESULT__32291__auto__
                                        (CATA__FN__67009
                                         ['meander.dev.parse.zeta/string-join-args
                                          {:tag :literal,
                                           :type :string,
                                           :form
                                           ((clojure.core/partial
                                             clojure.core/apply
                                             str)
                                            [?form-1 ?form-2])}
                                          ?right])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__32291__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__32291__auto__
                                         0)))]
                                     (catch
                                      java.lang.Exception
                                      e__33231__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__33231__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__33231__auto__))))))))
                                (state__67965)))
                              (state__67965)))
                            (state__67965)))
                          (state__67965)))
                        (state__67965))))
                     (state__67965)))
                   (state__67965)))
                 (state__67965))
                (state__67965)))
              (state__67965
               []
               (if
                (clojure.core/=
                 input__66972_nth_0__
                 'meander.dev.parse.zeta/string-join-args)
                (if
                 (clojure.core/map? input__66972_nth_1__)
                 (clojure.core/let
                  [VAL__67428 (.valAt input__66972_nth_1__ :tag)]
                  (clojure.core/case
                   VAL__67428
                   (:literal)
                   (clojure.core/let
                    [VAL__67429 (.valAt input__66972_nth_1__ :type)]
                    (clojure.core/case
                     VAL__67429
                     (:string)
                     (clojure.core/let
                      [?ast input__66972_nth_1__]
                      (if
                       (clojure.core/map? input__66972_nth_2__)
                       (clojure.core/let
                        [VAL__67430 (.valAt input__66972_nth_2__ :tag)]
                        (clojure.core/case
                         VAL__67430
                         (:string-cat)
                         (clojure.core/let
                          [VAL__67431
                           (.valAt input__66972_nth_2__ :sequence)]
                          (clojure.core/let
                           [?sequence VAL__67431]
                           (clojure.core/let
                            [VAL__67432
                             (.valAt input__66972_nth_2__ :next)]
                            (clojure.core/let
                             [?next VAL__67432]
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
                               e__33231__auto__
                               (if
                                (meander.runtime.zeta/fail?
                                 e__33231__auto__)
                                (meander.runtime.zeta/fail)
                                (throw e__33231__auto__))))))))
                         (state__67966)))
                       (state__67966)))
                     (state__67966)))
                   (state__67966)))
                 (state__67966))
                (state__67966)))
              (state__67966
               []
               (if
                (clojure.core/=
                 input__66972_nth_0__
                 'meander.dev.parse.zeta/string-join-args)
                (if
                 (clojure.core/map? input__66972_nth_1__)
                 (clojure.core/let
                  [VAL__67435 (.valAt input__66972_nth_1__ :tag)]
                  (clojure.core/case
                   VAL__67435
                   (:string-cat)
                   (clojure.core/let
                    [VAL__67436
                     (.valAt input__66972_nth_1__ :sequence)]
                    (clojure.core/let
                     [?sequence VAL__67436]
                     (clojure.core/let
                      [VAL__67437 (.valAt input__66972_nth_1__ :next)]
                      (if
                       (clojure.core/map? VAL__67437)
                       (clojure.core/let
                        [VAL__67438 (.valAt VAL__67437 :tag)]
                        (clojure.core/case
                         VAL__67438
                         (:empty)
                         (clojure.core/let
                          [?right input__66972_nth_2__]
                          (try
                           [(clojure.core/let
                             [CATA_RESULT__32291__auto__
                              (CATA__FN__67009
                               ['meander.dev.parse.zeta/string-cat-args
                                ?sequence
                                ?right])]
                             (if
                              (meander.runtime.zeta/fail?
                               CATA_RESULT__32291__auto__)
                              (throw (meander.runtime.zeta/fail))
                              (clojure.core/nth
                               CATA_RESULT__32291__auto__
                               0)))]
                           (catch
                            java.lang.Exception
                            e__33231__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__33231__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__33231__auto__)))))
                         (state__67967)))
                       (state__67967)))))
                   (state__67967)))
                 (state__67967))
                (state__67967)))
              (state__67967
               []
               (if
                (clojure.core/=
                 input__66972_nth_0__
                 'meander.dev.parse.zeta/string-join-args)
                (clojure.core/let
                 [?left input__66972_nth_1__]
                 (clojure.core/let
                  [?right input__66972_nth_2__]
                  (try
                   [{:tag :string-join, :left ?left, :right ?right}]
                   (catch
                    java.lang.Exception
                    e__33231__auto__
                    (if
                     (meander.runtime.zeta/fail? e__33231__auto__)
                     (meander.runtime.zeta/fail)
                     (throw e__33231__auto__))))))
                (state__67968)))
              (state__67968
               []
               (if
                (clojure.core/=
                 input__66972_nth_0__
                 'meander.dev.parse.zeta/parse-entries)
                (if
                 (clojure.core/map? input__66972_nth_1__)
                 (clojure.core/let
                  [VAL__67443
                   (.valAt input__66972_nth_1__ :meander.zeta/as)]
                  (clojure.core/let
                   [?pattern VAL__67443]
                   (clojure.core/let
                    [X__67445
                     ((clojure.core/fn
                       [m__30055__auto__]
                       (clojure.core/dissoc
                        m__30055__auto__
                        :meander.zeta/as))
                      input__66972_nth_1__)]
                    (clojure.core/let
                     [?rest X__67445]
                     (clojure.core/letfn
                      [(save__67446 [] (state__67906))
                       (f__68014
                        []
                        (clojure.core/let
                         [?env input__66972_nth_2__]
                         (try
                          [{:tag :as,
                            :pattern ?pattern,
                            :next
                            (clojure.core/let
                             [CATA_RESULT__32291__auto__
                              (CATA__FN__67009 [?rest ?env])]
                             (if
                              (meander.runtime.zeta/fail?
                               CATA_RESULT__32291__auto__)
                              (throw (meander.runtime.zeta/fail))
                              (clojure.core/nth
                               CATA_RESULT__32291__auto__
                               0)))}]
                          (catch
                           java.lang.Exception
                           e__33231__auto__
                           (if
                            (meander.runtime.zeta/fail?
                             e__33231__auto__)
                            (meander.runtime.zeta/fail)
                            (throw e__33231__auto__))))))]
                      (if
                       (clojure.core/= ?rest input__66972_nth_1__)
                       (save__67446)
                       (f__68014)))))))
                 (state__67906))
                (state__67906)))]
             (state__67946)))
           (state__67906))
          (state__67906)))
        (state__67906
         []
         (clojure.core/letfn
          [(def__67449
            [arg__67482 ?ns]
            (clojure.core/letfn
             [(state__68015
               []
               (clojure.core/let
                [x__67483 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__67483)
                 (clojure.core/let [?env arg__67482] [?env ?ns])
                 (state__68016))))
              (state__68016
               []
               (if
                (clojure.core/map? arg__67482)
                (clojure.core/let
                 [VAL__67484 (.valAt arg__67482 :aliases)]
                 (if
                  (clojure.core/map? VAL__67484)
                  (clojure.core/let
                   [X__67486 (clojure.core/set VAL__67484)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__67486))
                    (clojure.core/loop
                     [search_space__68017 (clojure.core/seq X__67486)]
                     (if
                      (clojure.core/seq search_space__68017)
                      (clojure.core/let
                       [elem__67487
                        (clojure.core/first search_space__68017)
                        result__68018
                        (clojure.core/let
                         [elem__67487_nth_0__
                          (clojure.core/nth elem__67487 0)
                          elem__67487_nth_1__
                          (clojure.core/nth elem__67487 1)]
                         (if
                          (clojure.core/symbol? elem__67487_nth_0__)
                          (clojure.core/let
                           [X__67489
                            (clojure.core/name elem__67487_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__67489)
                            (if
                             (clojure.core/symbol? elem__67487_nth_1__)
                             (clojure.core/let
                              [X__67491
                               (clojure.core/name elem__67487_nth_1__)]
                              (clojure.core/case
                               X__67491
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__67482]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__68018)
                        (recur (clojure.core/next search_space__68017))
                        result__68018))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__68015)))]
          (if
           (clojure.core/vector? input__66972)
           (if
            (clojure.core/= (clojure.core/count input__66972) 3)
            (clojure.core/let
             [input__66972_nth_0__
              (clojure.core/nth input__66972 0)
              input__66972_nth_1__
              (clojure.core/nth input__66972 1)
              input__66972_nth_2__
              (clojure.core/nth input__66972 2)]
             (if
              (clojure.core/=
               input__66972_nth_0__
               'meander.dev.parse.zeta/parse-entries)
              (if
               (clojure.core/map? input__66972_nth_1__)
               (clojure.core/let
                [X__67454 (clojure.core/set input__66972_nth_1__)]
                (if
                 (clojure.core/<= 1 (clojure.core/count X__67454))
                 (clojure.core/loop
                  [search_space__68020 (clojure.core/seq X__67454)]
                  (if
                   (clojure.core/seq search_space__68020)
                   (clojure.core/let
                    [elem__67455
                     (clojure.core/first search_space__68020)
                     result__68021
                     (clojure.core/let
                      [elem__67455_nth_0__
                       (clojure.core/nth elem__67455 0)
                       elem__67455_nth_1__
                       (clojure.core/nth elem__67455 1)]
                      (clojure.core/let
                       [*m__66988 elem__67455_nth_0__]
                       (if
                        (clojure.core/symbol? elem__67455_nth_0__)
                        (clojure.core/let
                         [X__67457
                          (clojure.core/namespace elem__67455_nth_0__)]
                         (clojure.core/let
                          [?ns X__67457]
                          (clojure.core/let
                           [X__67459
                            (clojure.core/name elem__67455_nth_0__)]
                           (if
                            (clojure.core/string? X__67459)
                            (if
                             (clojure.core/re-matches #"&.*" X__67459)
                             (clojure.core/let
                              [?pattern elem__67455_nth_1__]
                              (clojure.core/let
                               [X__67461
                                ((clojure.core/fn
                                  [m__30055__auto__]
                                  (clojure.core/dissoc
                                   m__30055__auto__
                                   *m__66988))
                                 input__66972_nth_1__)]
                               (clojure.core/let
                                [?rest X__67461]
                                (clojure.core/let
                                 [x__30988__auto__
                                  (def__67449
                                   input__66972_nth_2__
                                   ?ns)]
                                 (if
                                  (meander.runtime.zeta/fail?
                                   x__30988__auto__)
                                  (meander.runtime.zeta/fail)
                                  (clojure.core/let
                                   [[?env ?ns] x__30988__auto__]
                                   (try
                                    [{:tag :rest-map,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__32291__auto__
                                        (CATA__FN__67009
                                         [?pattern ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__32291__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__32291__auto__
                                         0))),
                                      :next
                                      (clojure.core/let
                                       [CATA_RESULT__32291__auto__
                                        (CATA__FN__67009
                                         ['meander.dev.parse.zeta/parse-entries
                                          ?rest
                                          ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__32291__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__32291__auto__
                                         0)))}]
                                    (catch
                                     java.lang.Exception
                                     e__33231__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__33231__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__33231__auto__))))))))))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))))
                        (meander.runtime.zeta/fail))))]
                    (if
                     (meander.runtime.zeta/fail? result__68021)
                     (recur (clojure.core/next search_space__68020))
                     result__68021))
                   (state__67907)))
                 (state__67907)))
               (state__67907))
              (state__67907)))
            (state__67907))
           (state__67907))))
        (state__67907
         []
         (if
          (clojure.core/vector? input__66972)
          (clojure.core/letfn
           [(state__68023
             []
             (if
              (clojure.core/= (clojure.core/count input__66972) 3)
              (clojure.core/let
               [input__66972_nth_0__
                (clojure.core/nth input__66972 0)
                input__66972_nth_1__
                (clojure.core/nth input__66972 1)
                input__66972_nth_2__
                (clojure.core/nth input__66972 2)]
               (clojure.core/letfn
                [(state__68026
                  []
                  (if
                   (clojure.core/=
                    input__66972_nth_0__
                    'meander.dev.parse.zeta/parse-entries)
                   (if
                    (clojure.core/map? input__66972_nth_1__)
                    (clojure.core/let
                     [X__67505 (clojure.core/set input__66972_nth_1__)]
                     (if
                      (clojure.core/<= 1 (clojure.core/count X__67505))
                      (clojure.core/loop
                       [search_space__68032
                        (clojure.core/seq X__67505)]
                       (if
                        (clojure.core/seq search_space__68032)
                        (clojure.core/let
                         [elem__67506
                          (clojure.core/first search_space__68032)
                          result__68033
                          (clojure.core/let
                           [elem__67506_nth_0__
                            (clojure.core/nth elem__67506 0)
                            elem__67506_nth_1__
                            (clojure.core/nth elem__67506 1)]
                           (clojure.core/let
                            [?key-pattern elem__67506_nth_0__]
                            (clojure.core/let
                             [?val-pattern elem__67506_nth_1__]
                             (clojure.core/let
                              [X__67508
                               ((clojure.core/fn
                                 [m__30055__auto__]
                                 (clojure.core/dissoc
                                  m__30055__auto__
                                  ?key-pattern))
                                input__66972_nth_1__)]
                              (clojure.core/let
                               [?rest X__67508]
                               (clojure.core/let
                                [?env input__66972_nth_2__]
                                (try
                                 [{:tag :entry,
                                   :key-pattern
                                   (clojure.core/let
                                    [CATA_RESULT__32291__auto__
                                     (CATA__FN__67009
                                      [?key-pattern ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__32291__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__32291__auto__
                                      0))),
                                   :val-pattern
                                   (clojure.core/let
                                    [CATA_RESULT__32291__auto__
                                     (CATA__FN__67009
                                      [?val-pattern ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__32291__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__32291__auto__
                                      0))),
                                   :next
                                   (clojure.core/let
                                    [CATA_RESULT__32291__auto__
                                     (CATA__FN__67009
                                      ['meander.dev.parse.zeta/parse-entries
                                       ?rest
                                       ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__32291__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__32291__auto__
                                      0)))}]
                                 (catch
                                  java.lang.Exception
                                  e__33231__auto__
                                  (if
                                   (meander.runtime.zeta/fail?
                                    e__33231__auto__)
                                   (meander.runtime.zeta/fail)
                                   (throw e__33231__auto__))))))))))]
                         (if
                          (meander.runtime.zeta/fail? result__68033)
                          (recur
                           (clojure.core/next search_space__68032))
                          result__68033))
                        (state__68027)))
                      (state__68027)))
                    (state__68027))
                   (state__68027)))
                 (state__68027
                  []
                  (if
                   (clojure.core/=
                    input__66972_nth_0__
                    'meander.dev.parse.zeta/parse-entries)
                   (if
                    (clojure.core/map? input__66972_nth_1__)
                    (clojure.core/let
                     [?env input__66972_nth_2__]
                     (try
                      [{:tag :some-map}]
                      (catch
                       java.lang.Exception
                       e__33231__auto__
                       (if
                        (meander.runtime.zeta/fail? e__33231__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__33231__auto__)))))
                    (state__68028))
                   (state__68028)))
                 (state__68028
                  []
                  (if
                   (clojure.core/=
                    input__66972_nth_0__
                    'meander.dev.parse.zeta/parse-with-bindings)
                   (if
                    (clojure.core/vector? input__66972_nth_1__)
                    (clojure.core/case
                     input__66972_nth_1__
                     ([])
                     (clojure.core/let
                      [?env input__66972_nth_2__]
                      (try
                       [[]]
                       (catch
                        java.lang.Exception
                        e__33231__auto__
                        (if
                         (meander.runtime.zeta/fail? e__33231__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__33231__auto__)))))
                     (state__68029))
                    (state__68029))
                   (state__68029)))
                 (state__68029
                  []
                  (if
                   (clojure.core/=
                    input__66972_nth_0__
                    'meander.dev.parse.zeta/parse-with-bindings)
                   (if
                    (clojure.core/vector? input__66972_nth_1__)
                    (if
                     (clojure.core/=
                      (clojure.core/count input__66972_nth_1__)
                      1)
                     (clojure.core/let
                      [?env input__66972_nth_2__]
                      (try
                       [[{:tag :error,
                          :message
                          "meander.zeta/with expects an even number of bindings"}]]
                       (catch
                        java.lang.Exception
                        e__33231__auto__
                        (if
                         (meander.runtime.zeta/fail? e__33231__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__33231__auto__)))))
                     (state__68030))
                    (state__68030))
                   (state__68030)))
                 (state__68030
                  []
                  (if
                   (clojure.core/=
                    input__66972_nth_0__
                    'meander.dev.parse.zeta/parse-with-bindings)
                   (if
                    (clojure.core/vector? input__66972_nth_1__)
                    (clojure.core/let
                     [input__66972_nth_1___l__
                      (clojure.core/subvec
                       input__66972_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__66972_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__66972_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__66972_nth_1___r__
                        (clojure.core/subvec input__66972_nth_1__ 2)]
                       (clojure.core/let
                        [input__66972_nth_1___l___nth_0__
                         (clojure.core/nth input__66972_nth_1___l__ 0)
                         input__66972_nth_1___l___nth_1__
                         (clojure.core/nth input__66972_nth_1___l__ 1)]
                        (if
                         (clojure.core/symbol?
                          input__66972_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__67522
                           (clojure.core/namespace
                            input__66972_nth_1___l___nth_0__)]
                          (clojure.core/let
                           [X__67524
                            (clojure.core/name
                             input__66972_nth_1___l___nth_0__)]
                           (if
                            (clojure.core/string? X__67524)
                            (if
                             (clojure.core/re-matches #"%.+" X__67524)
                             (clojure.core/let
                              [?symbol
                               input__66972_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?pattern
                                input__66972_nth_1___l___nth_1__]
                               (clojure.core/let
                                [?rest input__66972_nth_1___r__]
                                (clojure.core/let
                                 [?env input__66972_nth_2__]
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
                                        [CATA_RESULT__32291__auto__
                                         (CATA__FN__67009
                                          [?pattern ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__32291__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__32291__auto__
                                          0)))})
                                     (clojure.core/let
                                      [CATA_RESULT__32291__auto__
                                       (CATA__FN__67009
                                        ['meander.dev.parse.zeta/parse-with-bindings
                                         ?rest
                                         ?env])]
                                      (if
                                       (meander.runtime.zeta/fail?
                                        CATA_RESULT__32291__auto__)
                                       (throw
                                        (meander.runtime.zeta/fail))
                                       (clojure.core/nth
                                        CATA_RESULT__32291__auto__
                                        0)))))]
                                  (catch
                                   java.lang.Exception
                                   e__33231__auto__
                                   (if
                                    (meander.runtime.zeta/fail?
                                     e__33231__auto__)
                                    (meander.runtime.zeta/fail)
                                    (throw e__33231__auto__))))))))
                             (state__68031))
                            (state__68031))))
                         (state__68031))))
                      (state__68031)))
                    (state__68031))
                   (state__68031)))
                 (state__68031
                  []
                  (if
                   (clojure.core/=
                    input__66972_nth_0__
                    'meander.dev.parse.zeta/parse-with-bindings)
                   (if
                    (clojure.core/vector? input__66972_nth_1__)
                    (clojure.core/let
                     [input__66972_nth_1___l__
                      (clojure.core/subvec
                       input__66972_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__66972_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__66972_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__66972_nth_1___r__
                        (clojure.core/subvec input__66972_nth_1__ 2)]
                       (clojure.core/let
                        [input__66972_nth_1___l___nth_0__
                         (clojure.core/nth input__66972_nth_1___l__ 0)
                         input__66972_nth_1___l___nth_1__
                         (clojure.core/nth input__66972_nth_1___l__ 1)]
                        (clojure.core/let
                         [?x input__66972_nth_1___l___nth_0__]
                         (clojure.core/let
                          [?pattern input__66972_nth_1___l___nth_1__]
                          (clojure.core/let
                           [?rest input__66972_nth_1___r__]
                           (clojure.core/let
                            [?env input__66972_nth_2__]
                            (try
                             [[{:tag :error,
                                :message
                                "meander.zeta/with bindings must be an repeating sequence of %name pattern"}]]
                             (catch
                              java.lang.Exception
                              e__33231__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__33231__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__33231__auto__))))))))))
                      (state__68024)))
                    (state__68024))
                   (state__68024)))]
                (state__68026)))
              (state__68024)))
            (state__68024
             []
             (if
              (clojure.core/= (clojure.core/count input__66972) 2)
              (clojure.core/let
               [input__66972_nth_0__
                (clojure.core/nth input__66972 0)
                input__66972_nth_1__
                (clojure.core/nth input__66972 1)]
               (if
                (clojure.core/vector? input__66972_nth_0__)
                (clojure.core/let
                 [?sequence input__66972_nth_0__]
                 (clojure.core/let
                  [?env input__66972_nth_1__]
                  (try
                   [(clojure.core/let
                     [CATA_RESULT__32291__auto__
                      (CATA__FN__67009
                       ['meander.dev.parse.zeta/vector-args
                        (clojure.core/let
                         [CATA_RESULT__32291__auto__
                          (CATA__FN__67009
                           ['meander.dev.parse.zeta/parse-seq
                            ?sequence
                            ?env])]
                         (if
                          (meander.runtime.zeta/fail?
                           CATA_RESULT__32291__auto__)
                          (throw (meander.runtime.zeta/fail))
                          (clojure.core/nth
                           CATA_RESULT__32291__auto__
                           0)))
                        ?sequence])]
                     (if
                      (meander.runtime.zeta/fail?
                       CATA_RESULT__32291__auto__)
                      (throw (meander.runtime.zeta/fail))
                      (clojure.core/nth
                       CATA_RESULT__32291__auto__
                       0)))]
                   (catch
                    java.lang.Exception
                    e__33231__auto__
                    (if
                     (meander.runtime.zeta/fail? e__33231__auto__)
                     (meander.runtime.zeta/fail)
                     (throw e__33231__auto__))))))
                (state__68025)))
              (state__68025)))
            (state__68025
             []
             (if
              (clojure.core/= (clojure.core/count input__66972) 3)
              (clojure.core/let
               [input__66972_nth_0__
                (clojure.core/nth input__66972 0)
                input__66972_nth_1__
                (clojure.core/nth input__66972 1)]
               (clojure.core/letfn
                [(state__68035
                  []
                  (if
                   (clojure.core/=
                    input__66972_nth_0__
                    'meander.dev.parse.zeta/vector-args)
                   (if
                    (clojure.core/map? input__66972_nth_1__)
                    (clojure.core/let
                     [VAL__67535 (.valAt input__66972_nth_1__ :tag)]
                     (clojure.core/case
                      VAL__67535
                      (:literal)
                      (clojure.core/let
                       [?literal input__66972_nth_1__]
                       (try
                        [?literal]
                        (catch
                         java.lang.Exception
                         e__33231__auto__
                         (if
                          (meander.runtime.zeta/fail? e__33231__auto__)
                          (meander.runtime.zeta/fail)
                          (throw e__33231__auto__)))))
                      (state__68036)))
                    (state__68036))
                   (state__68036)))
                 (state__68036
                  []
                  (clojure.core/let
                   [input__66972_nth_2__
                    (clojure.core/nth input__66972 2)]
                   (if
                    (clojure.core/=
                     input__66972_nth_0__
                     'meander.dev.parse.zeta/vector-args)
                    (clojure.core/let
                     [?next input__66972_nth_1__]
                     (clojure.core/let
                      [?sequence input__66972_nth_2__]
                      (try
                       [{:tag :vector, :next ?next, :form ?sequence}]
                       (catch
                        java.lang.Exception
                        e__33231__auto__
                        (if
                         (meander.runtime.zeta/fail? e__33231__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__33231__auto__))))))
                    (state__67908))))]
                (state__68035)))
              (state__67908)))]
           (state__68023))
          (state__67908)))
        (state__67908
         []
         (clojure.core/letfn
          [(def__67539
            [arg__67562 ?__66973]
            (clojure.core/letfn
             [(state__68037
               []
               (clojure.core/let
                [x__67563 "meander.zeta"]
                (if
                 (clojure.core/= ?__66973 x__67563)
                 [?__66973]
                 (state__68038))))
              (state__68038
               []
               (if
                (clojure.core/map? arg__67562)
                (clojure.core/let
                 [VAL__67564 (.valAt arg__67562 :aliases)]
                 (if
                  (clojure.core/map? VAL__67564)
                  (clojure.core/let
                   [X__67566 (clojure.core/set VAL__67564)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__67566))
                    (clojure.core/loop
                     [search_space__68039 (clojure.core/seq X__67566)]
                     (if
                      (clojure.core/seq search_space__68039)
                      (clojure.core/let
                       [elem__67567
                        (clojure.core/first search_space__68039)
                        result__68040
                        (clojure.core/let
                         [elem__67567_nth_0__
                          (clojure.core/nth elem__67567 0)
                          elem__67567_nth_1__
                          (clojure.core/nth elem__67567 1)]
                         (if
                          (clojure.core/symbol? elem__67567_nth_0__)
                          (clojure.core/let
                           [X__67569
                            (clojure.core/name elem__67567_nth_0__)]
                           (if
                            (clojure.core/= ?__66973 X__67569)
                            (if
                             (clojure.core/symbol? elem__67567_nth_1__)
                             (clojure.core/let
                              [X__67571
                               (clojure.core/name elem__67567_nth_1__)]
                              (clojure.core/case
                               X__67571
                               ("meander.zeta")
                               [?__66973]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__68040)
                        (recur (clojure.core/next search_space__68039))
                        result__68040))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__68037)))]
          (if
           (clojure.core/vector? input__66972)
           (if
            (clojure.core/= (clojure.core/count input__66972) 2)
            (clojure.core/let
             [input__66972_nth_0__
              (clojure.core/nth input__66972 0)
              input__66972_nth_1__
              (clojure.core/nth input__66972 1)]
             (if
              (clojure.core/seq? input__66972_nth_0__)
              (clojure.core/let
               [input__66972_nth_0___l__
                (clojure.core/take 1 input__66972_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__66972_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__66972_nth_0___r__
                  (clojure.core/drop 1 input__66972_nth_0__)]
                 (clojure.core/let
                  [input__66972_nth_0___l___nth_0__
                   (clojure.core/nth input__66972_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__66972_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__67549
                     (clojure.core/namespace
                      input__66972_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__66973 X__67549]
                     (clojure.core/let
                      [X__67551
                       (clojure.core/name
                        input__66972_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__67551
                       ("with")
                       (clojure.core/let
                        [x__30988__auto__
                         (def__67539 input__66972_nth_1__ ?__66973)]
                        (if
                         (meander.runtime.zeta/fail? x__30988__auto__)
                         (state__67909)
                         (clojure.core/let
                          [[?__66973] x__30988__auto__]
                          (if
                           (clojure.core/vector? input__66972)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__66972)
                             2)
                            (clojure.core/let
                             [input__66972_nth_0__
                              (clojure.core/nth input__66972 0)
                              input__66972_nth_1__
                              (clojure.core/nth input__66972 1)]
                             (if
                              (clojure.core/seq? input__66972_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__66972_nth_0__)
                                3)
                               (clojure.core/let
                                [input__66972_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__66972_nth_0__
                                  1)
                                 input__66972_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__66972_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?bindings
                                  input__66972_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?body input__66972_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__66972_nth_0__]
                                   (clojure.core/let
                                    [?env input__66972_nth_1__]
                                    (try
                                     [{:tag :with,
                                       :bindings
                                       {:tag :with-bindings,
                                        :bindings
                                        (clojure.core/let
                                         [CATA_RESULT__32291__auto__
                                          (CATA__FN__67009
                                           ['meander.dev.parse.zeta/parse-with-bindings
                                            ?bindings
                                            ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__32291__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__32291__auto__
                                           0)))},
                                       :body
                                       (clojure.core/let
                                        [CATA_RESULT__32291__auto__
                                         (CATA__FN__67009
                                          [?body ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__32291__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__32291__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__33231__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__33231__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__33231__auto__)))))))))
                               (state__67909))
                              (state__67909)))
                            (state__67909))
                           (state__67909)))))
                       (state__67909)))))
                   (state__67909))))
                (state__67909)))
              (state__67909)))
            (state__67909))
           (state__67909))))
        (state__67909
         []
         (clojure.core/letfn
          [(def__67573
            [arg__67596 ?__66974]
            (clojure.core/letfn
             [(state__68042
               []
               (clojure.core/let
                [x__67597 "meander.zeta"]
                (if
                 (clojure.core/= ?__66974 x__67597)
                 [?__66974]
                 (state__68043))))
              (state__68043
               []
               (if
                (clojure.core/map? arg__67596)
                (clojure.core/let
                 [VAL__67598 (.valAt arg__67596 :aliases)]
                 (if
                  (clojure.core/map? VAL__67598)
                  (clojure.core/let
                   [X__67600 (clojure.core/set VAL__67598)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__67600))
                    (clojure.core/loop
                     [search_space__68044 (clojure.core/seq X__67600)]
                     (if
                      (clojure.core/seq search_space__68044)
                      (clojure.core/let
                       [elem__67601
                        (clojure.core/first search_space__68044)
                        result__68045
                        (clojure.core/let
                         [elem__67601_nth_0__
                          (clojure.core/nth elem__67601 0)
                          elem__67601_nth_1__
                          (clojure.core/nth elem__67601 1)]
                         (if
                          (clojure.core/symbol? elem__67601_nth_0__)
                          (clojure.core/let
                           [X__67603
                            (clojure.core/name elem__67601_nth_0__)]
                           (if
                            (clojure.core/= ?__66974 X__67603)
                            (if
                             (clojure.core/symbol? elem__67601_nth_1__)
                             (clojure.core/let
                              [X__67605
                               (clojure.core/name elem__67601_nth_1__)]
                              (clojure.core/case
                               X__67605
                               ("meander.zeta")
                               [?__66974]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__68045)
                        (recur (clojure.core/next search_space__68044))
                        result__68045))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__68042)))]
          (if
           (clojure.core/vector? input__66972)
           (if
            (clojure.core/= (clojure.core/count input__66972) 2)
            (clojure.core/let
             [input__66972_nth_0__
              (clojure.core/nth input__66972 0)
              input__66972_nth_1__
              (clojure.core/nth input__66972 1)]
             (if
              (clojure.core/seq? input__66972_nth_0__)
              (clojure.core/let
               [input__66972_nth_0___l__
                (clojure.core/take 1 input__66972_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__66972_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__66972_nth_0___r__
                  (clojure.core/drop 1 input__66972_nth_0__)]
                 (clojure.core/let
                  [input__66972_nth_0___l___nth_0__
                   (clojure.core/nth input__66972_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__66972_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__67583
                     (clojure.core/namespace
                      input__66972_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__66974 X__67583]
                     (clojure.core/let
                      [X__67585
                       (clojure.core/name
                        input__66972_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__67585
                       ("apply")
                       (clojure.core/let
                        [x__30988__auto__
                         (def__67573 input__66972_nth_1__ ?__66974)]
                        (if
                         (meander.runtime.zeta/fail? x__30988__auto__)
                         (state__67910)
                         (clojure.core/let
                          [[?__66974] x__30988__auto__]
                          (if
                           (clojure.core/vector? input__66972)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__66972)
                             2)
                            (clojure.core/let
                             [input__66972_nth_0__
                              (clojure.core/nth input__66972 0)
                              input__66972_nth_1__
                              (clojure.core/nth input__66972 1)]
                             (if
                              (clojure.core/seq? input__66972_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__66972_nth_0__)
                                3)
                               (clojure.core/let
                                [input__66972_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__66972_nth_0__
                                  1)
                                 input__66972_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__66972_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?fn input__66972_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?pattern
                                   input__66972_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__66972_nth_0__]
                                   (clojure.core/let
                                    [?env input__66972_nth_1__]
                                    (try
                                     [{:tag :apply,
                                       :fn ?fn,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__32291__auto__
                                         (CATA__FN__67009
                                          [?pattern ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__32291__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__32291__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__33231__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__33231__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__33231__auto__)))))))))
                               (state__67910))
                              (state__67910)))
                            (state__67910))
                           (state__67910)))))
                       (state__67910)))))
                   (state__67910))))
                (state__67910)))
              (state__67910)))
            (state__67910))
           (state__67910))))
        (state__67910
         []
         (clojure.core/letfn
          [(def__67607
            [arg__67630 ?__66975]
            (clojure.core/letfn
             [(state__68047
               []
               (clojure.core/let
                [x__67631 "meander.zeta"]
                (if
                 (clojure.core/= ?__66975 x__67631)
                 [?__66975]
                 (state__68048))))
              (state__68048
               []
               (if
                (clojure.core/map? arg__67630)
                (clojure.core/let
                 [VAL__67632 (.valAt arg__67630 :aliases)]
                 (if
                  (clojure.core/map? VAL__67632)
                  (clojure.core/let
                   [X__67634 (clojure.core/set VAL__67632)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__67634))
                    (clojure.core/loop
                     [search_space__68049 (clojure.core/seq X__67634)]
                     (if
                      (clojure.core/seq search_space__68049)
                      (clojure.core/let
                       [elem__67635
                        (clojure.core/first search_space__68049)
                        result__68050
                        (clojure.core/let
                         [elem__67635_nth_0__
                          (clojure.core/nth elem__67635 0)
                          elem__67635_nth_1__
                          (clojure.core/nth elem__67635 1)]
                         (if
                          (clojure.core/symbol? elem__67635_nth_0__)
                          (clojure.core/let
                           [X__67637
                            (clojure.core/name elem__67635_nth_0__)]
                           (if
                            (clojure.core/= ?__66975 X__67637)
                            (if
                             (clojure.core/symbol? elem__67635_nth_1__)
                             (clojure.core/let
                              [X__67639
                               (clojure.core/name elem__67635_nth_1__)]
                              (clojure.core/case
                               X__67639
                               ("meander.zeta")
                               [?__66975]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__68050)
                        (recur (clojure.core/next search_space__68049))
                        result__68050))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__68047)))]
          (if
           (clojure.core/vector? input__66972)
           (if
            (clojure.core/= (clojure.core/count input__66972) 2)
            (clojure.core/let
             [input__66972_nth_0__
              (clojure.core/nth input__66972 0)
              input__66972_nth_1__
              (clojure.core/nth input__66972 1)]
             (if
              (clojure.core/seq? input__66972_nth_0__)
              (clojure.core/let
               [input__66972_nth_0___l__
                (clojure.core/take 1 input__66972_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__66972_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__66972_nth_0___r__
                  (clojure.core/drop 1 input__66972_nth_0__)]
                 (clojure.core/let
                  [input__66972_nth_0___l___nth_0__
                   (clojure.core/nth input__66972_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__66972_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__67617
                     (clojure.core/namespace
                      input__66972_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__66975 X__67617]
                     (clojure.core/let
                      [X__67619
                       (clojure.core/name
                        input__66972_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__67619
                       ("and")
                       (clojure.core/let
                        [x__30988__auto__
                         (def__67607 input__66972_nth_1__ ?__66975)]
                        (if
                         (meander.runtime.zeta/fail? x__30988__auto__)
                         (state__67911)
                         (clojure.core/let
                          [[?__66975] x__30988__auto__]
                          (if
                           (clojure.core/vector? input__66972)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__66972)
                             2)
                            (clojure.core/let
                             [input__66972_nth_0__
                              (clojure.core/nth input__66972 0)
                              input__66972_nth_1__
                              (clojure.core/nth input__66972 1)]
                             (if
                              (clojure.core/seq? input__66972_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__66972_nth_0__)
                                3)
                               (clojure.core/let
                                [input__66972_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__66972_nth_0__
                                  1)
                                 input__66972_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__66972_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?left input__66972_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?right input__66972_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__66972_nth_0__]
                                   (clojure.core/let
                                    [?env input__66972_nth_1__]
                                    (try
                                     [{:tag :and,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__32291__auto__
                                         (CATA__FN__67009
                                          [?left ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__32291__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__32291__auto__
                                          0))),
                                       :right
                                       (clojure.core/let
                                        [CATA_RESULT__32291__auto__
                                         (CATA__FN__67009
                                          [?right ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__32291__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__32291__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__33231__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__33231__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__33231__auto__)))))))))
                               (state__67911))
                              (state__67911)))
                            (state__67911))
                           (state__67911)))))
                       (state__67911)))))
                   (state__67911))))
                (state__67911)))
              (state__67911)))
            (state__67911))
           (state__67911))))
        (state__67911
         []
         (clojure.core/letfn
          [(def__67641
            [arg__67664 ?__66976]
            (clojure.core/letfn
             [(state__68052
               []
               (clojure.core/let
                [x__67665 "meander.zeta"]
                (if
                 (clojure.core/= ?__66976 x__67665)
                 [?__66976]
                 (state__68053))))
              (state__68053
               []
               (if
                (clojure.core/map? arg__67664)
                (clojure.core/let
                 [VAL__67666 (.valAt arg__67664 :aliases)]
                 (if
                  (clojure.core/map? VAL__67666)
                  (clojure.core/let
                   [X__67668 (clojure.core/set VAL__67666)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__67668))
                    (clojure.core/loop
                     [search_space__68054 (clojure.core/seq X__67668)]
                     (if
                      (clojure.core/seq search_space__68054)
                      (clojure.core/let
                       [elem__67669
                        (clojure.core/first search_space__68054)
                        result__68055
                        (clojure.core/let
                         [elem__67669_nth_0__
                          (clojure.core/nth elem__67669 0)
                          elem__67669_nth_1__
                          (clojure.core/nth elem__67669 1)]
                         (if
                          (clojure.core/symbol? elem__67669_nth_0__)
                          (clojure.core/let
                           [X__67671
                            (clojure.core/name elem__67669_nth_0__)]
                           (if
                            (clojure.core/= ?__66976 X__67671)
                            (if
                             (clojure.core/symbol? elem__67669_nth_1__)
                             (clojure.core/let
                              [X__67673
                               (clojure.core/name elem__67669_nth_1__)]
                              (clojure.core/case
                               X__67673
                               ("meander.zeta")
                               [?__66976]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__68055)
                        (recur (clojure.core/next search_space__68054))
                        result__68055))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__68052)))]
          (if
           (clojure.core/vector? input__66972)
           (if
            (clojure.core/= (clojure.core/count input__66972) 2)
            (clojure.core/let
             [input__66972_nth_0__
              (clojure.core/nth input__66972 0)
              input__66972_nth_1__
              (clojure.core/nth input__66972 1)]
             (if
              (clojure.core/seq? input__66972_nth_0__)
              (clojure.core/let
               [input__66972_nth_0___l__
                (clojure.core/take 1 input__66972_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__66972_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__66972_nth_0___r__
                  (clojure.core/drop 1 input__66972_nth_0__)]
                 (clojure.core/let
                  [input__66972_nth_0___l___nth_0__
                   (clojure.core/nth input__66972_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__66972_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__67651
                     (clojure.core/namespace
                      input__66972_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__66976 X__67651]
                     (clojure.core/let
                      [X__67653
                       (clojure.core/name
                        input__66972_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__67653
                       ("cata")
                       (clojure.core/let
                        [x__30988__auto__
                         (def__67641 input__66972_nth_1__ ?__66976)]
                        (if
                         (meander.runtime.zeta/fail? x__30988__auto__)
                         (state__67912)
                         (clojure.core/let
                          [[?__66976] x__30988__auto__]
                          (if
                           (clojure.core/vector? input__66972)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__66972)
                             2)
                            (clojure.core/let
                             [input__66972_nth_0__
                              (clojure.core/nth input__66972 0)
                              input__66972_nth_1__
                              (clojure.core/nth input__66972 1)]
                             (if
                              (clojure.core/seq? input__66972_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__66972_nth_0__)
                                2)
                               (clojure.core/let
                                [input__66972_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__66972_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__66972_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__66972_nth_0__]
                                  (clojure.core/let
                                   [?env input__66972_nth_1__]
                                   (try
                                    [{:tag :cata,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__32291__auto__
                                        (CATA__FN__67009
                                         [?pattern ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__32291__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__32291__auto__
                                         0))),
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__33231__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__33231__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__33231__auto__))))))))
                               (state__67912))
                              (state__67912)))
                            (state__67912))
                           (state__67912)))))
                       (state__67912)))))
                   (state__67912))))
                (state__67912)))
              (state__67912)))
            (state__67912))
           (state__67912))))
        (state__67912
         []
         (clojure.core/letfn
          [(def__67675
            [arg__67698 ?__66977]
            (clojure.core/letfn
             [(state__68057
               []
               (clojure.core/let
                [x__67699 "meander.zeta"]
                (if
                 (clojure.core/= ?__66977 x__67699)
                 [?__66977]
                 (state__68058))))
              (state__68058
               []
               (if
                (clojure.core/map? arg__67698)
                (clojure.core/let
                 [VAL__67700 (.valAt arg__67698 :aliases)]
                 (if
                  (clojure.core/map? VAL__67700)
                  (clojure.core/let
                   [X__67702 (clojure.core/set VAL__67700)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__67702))
                    (clojure.core/loop
                     [search_space__68059 (clojure.core/seq X__67702)]
                     (if
                      (clojure.core/seq search_space__68059)
                      (clojure.core/let
                       [elem__67703
                        (clojure.core/first search_space__68059)
                        result__68060
                        (clojure.core/let
                         [elem__67703_nth_0__
                          (clojure.core/nth elem__67703 0)
                          elem__67703_nth_1__
                          (clojure.core/nth elem__67703 1)]
                         (if
                          (clojure.core/symbol? elem__67703_nth_0__)
                          (clojure.core/let
                           [X__67705
                            (clojure.core/name elem__67703_nth_0__)]
                           (if
                            (clojure.core/= ?__66977 X__67705)
                            (if
                             (clojure.core/symbol? elem__67703_nth_1__)
                             (clojure.core/let
                              [X__67707
                               (clojure.core/name elem__67703_nth_1__)]
                              (clojure.core/case
                               X__67707
                               ("meander.zeta")
                               [?__66977]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__68060)
                        (recur (clojure.core/next search_space__68059))
                        result__68060))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__68057)))]
          (if
           (clojure.core/vector? input__66972)
           (if
            (clojure.core/= (clojure.core/count input__66972) 2)
            (clojure.core/let
             [input__66972_nth_0__
              (clojure.core/nth input__66972 0)
              input__66972_nth_1__
              (clojure.core/nth input__66972 1)]
             (if
              (clojure.core/seq? input__66972_nth_0__)
              (clojure.core/let
               [input__66972_nth_0___l__
                (clojure.core/take 1 input__66972_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__66972_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__66972_nth_0___r__
                  (clojure.core/drop 1 input__66972_nth_0__)]
                 (clojure.core/let
                  [input__66972_nth_0___l___nth_0__
                   (clojure.core/nth input__66972_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__66972_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__67685
                     (clojure.core/namespace
                      input__66972_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__66977 X__67685]
                     (clojure.core/let
                      [X__67687
                       (clojure.core/name
                        input__66972_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__67687
                       ("fold")
                       (clojure.core/let
                        [x__30988__auto__
                         (def__67675 input__66972_nth_1__ ?__66977)]
                        (if
                         (meander.runtime.zeta/fail? x__30988__auto__)
                         (state__67913)
                         (clojure.core/let
                          [[?__66977] x__30988__auto__]
                          (if
                           (clojure.core/vector? input__66972)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__66972)
                             2)
                            (clojure.core/let
                             [input__66972_nth_0__
                              (clojure.core/nth input__66972 0)
                              input__66972_nth_1__
                              (clojure.core/nth input__66972 1)]
                             (if
                              (clojure.core/seq? input__66972_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 4)
                                 input__66972_nth_0__)
                                4)
                               (clojure.core/let
                                [input__66972_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__66972_nth_0__
                                  1)
                                 input__66972_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__66972_nth_0__
                                  2)
                                 input__66972_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__66972_nth_0__
                                  3)]
                                (clojure.core/let
                                 [?mutable-variable
                                  input__66972_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?initial-value
                                   input__66972_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?fold-function
                                    input__66972_nth_0___nth_3__]
                                   (clojure.core/let
                                    [?form input__66972_nth_0__]
                                    (clojure.core/let
                                     [?env input__66972_nth_1__]
                                     (try
                                      [(clojure.core/let
                                        [CATA_RESULT__32291__auto__
                                         (CATA__FN__67009
                                          ['meander.dev.parse.zeta/fold-args
                                           (clojure.core/let
                                            [CATA_RESULT__32291__auto__
                                             (CATA__FN__67009
                                              [?mutable-variable
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__32291__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__32291__auto__
                                              0)))
                                           (clojure.core/let
                                            [CATA_RESULT__32291__auto__
                                             (CATA__FN__67009
                                              [?initial-value ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__32291__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__32291__auto__
                                              0)))
                                           ?fold-function
                                           ?form])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__32291__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__32291__auto__
                                          0)))]
                                      (catch
                                       java.lang.Exception
                                       e__33231__auto__
                                       (if
                                        (meander.runtime.zeta/fail?
                                         e__33231__auto__)
                                        (meander.runtime.zeta/fail)
                                        (throw
                                         e__33231__auto__))))))))))
                               (state__67913))
                              (state__67913)))
                            (state__67913))
                           (state__67913)))))
                       (state__67913)))))
                   (state__67913))))
                (state__67913)))
              (state__67913)))
            (state__67913))
           (state__67913))))
        (state__67913
         []
         (if
          (clojure.core/vector? input__66972)
          (if
           (clojure.core/= (clojure.core/count input__66972) 5)
           (clojure.core/let
            [input__66972_nth_0__
             (clojure.core/nth input__66972 0)
             input__66972_nth_1__
             (clojure.core/nth input__66972 1)
             input__66972_nth_2__
             (clojure.core/nth input__66972 2)
             input__66972_nth_3__
             (clojure.core/nth input__66972 3)
             input__66972_nth_4__
             (clojure.core/nth input__66972 4)]
            (if
             (clojure.core/=
              input__66972_nth_0__
              'meander.dev.parse.zeta/fold-args)
             (if
              (clojure.core/map? input__66972_nth_1__)
              (clojure.core/let
               [VAL__67710 (.valAt input__66972_nth_1__ :tag)]
               (clojure.core/case
                VAL__67710
                (:mutable-variable)
                (clojure.core/let
                 [?variable-ast input__66972_nth_1__]
                 (clojure.core/let
                  [?initial-value-ast input__66972_nth_2__]
                  (clojure.core/let
                   [?fold-function input__66972_nth_3__]
                   (clojure.core/let
                    [?form input__66972_nth_4__]
                    (try
                     [{:tag :fold,
                       :variable ?variable-ast,
                       :initial-value ?initial-value-ast,
                       :fold-function
                       {:tag :host-expression, :form ?fold-function},
                       :form ?form}]
                     (catch
                      java.lang.Exception
                      e__33231__auto__
                      (if
                       (meander.runtime.zeta/fail? e__33231__auto__)
                       (meander.runtime.zeta/fail)
                       (throw e__33231__auto__))))))))
                (state__67914)))
              (state__67914))
             (state__67914)))
           (state__67914))
          (state__67914)))
        (state__67914
         []
         (clojure.core/letfn
          [(def__67712
            [arg__67735 ?__66978]
            (clojure.core/letfn
             [(state__68062
               []
               (clojure.core/let
                [x__67736 "meander.zeta"]
                (if
                 (clojure.core/= ?__66978 x__67736)
                 [?__66978]
                 (state__68063))))
              (state__68063
               []
               (if
                (clojure.core/map? arg__67735)
                (clojure.core/let
                 [VAL__67737 (.valAt arg__67735 :aliases)]
                 (if
                  (clojure.core/map? VAL__67737)
                  (clojure.core/let
                   [X__67739 (clojure.core/set VAL__67737)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__67739))
                    (clojure.core/loop
                     [search_space__68064 (clojure.core/seq X__67739)]
                     (if
                      (clojure.core/seq search_space__68064)
                      (clojure.core/let
                       [elem__67740
                        (clojure.core/first search_space__68064)
                        result__68065
                        (clojure.core/let
                         [elem__67740_nth_0__
                          (clojure.core/nth elem__67740 0)
                          elem__67740_nth_1__
                          (clojure.core/nth elem__67740 1)]
                         (if
                          (clojure.core/symbol? elem__67740_nth_0__)
                          (clojure.core/let
                           [X__67742
                            (clojure.core/name elem__67740_nth_0__)]
                           (if
                            (clojure.core/= ?__66978 X__67742)
                            (if
                             (clojure.core/symbol? elem__67740_nth_1__)
                             (clojure.core/let
                              [X__67744
                               (clojure.core/name elem__67740_nth_1__)]
                              (clojure.core/case
                               X__67744
                               ("meander.zeta")
                               [?__66978]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__68065)
                        (recur (clojure.core/next search_space__68064))
                        result__68065))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__68062)))]
          (if
           (clojure.core/vector? input__66972)
           (if
            (clojure.core/= (clojure.core/count input__66972) 2)
            (clojure.core/let
             [input__66972_nth_0__
              (clojure.core/nth input__66972 0)
              input__66972_nth_1__
              (clojure.core/nth input__66972 1)]
             (if
              (clojure.core/seq? input__66972_nth_0__)
              (clojure.core/let
               [input__66972_nth_0___l__
                (clojure.core/take 1 input__66972_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__66972_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__66972_nth_0___r__
                  (clojure.core/drop 1 input__66972_nth_0__)]
                 (clojure.core/let
                  [input__66972_nth_0___l___nth_0__
                   (clojure.core/nth input__66972_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__66972_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__67722
                     (clojure.core/namespace
                      input__66972_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__66978 X__67722]
                     (clojure.core/let
                      [X__67724
                       (clojure.core/name
                        input__66972_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__67724
                       ("let")
                       (clojure.core/let
                        [x__30988__auto__
                         (def__67712 input__66972_nth_1__ ?__66978)]
                        (if
                         (meander.runtime.zeta/fail? x__30988__auto__)
                         (state__67915)
                         (clojure.core/let
                          [[?__66978] x__30988__auto__]
                          (if
                           (clojure.core/vector? input__66972)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__66972)
                             2)
                            (clojure.core/let
                             [input__66972_nth_0__
                              (clojure.core/nth input__66972 0)
                              input__66972_nth_1__
                              (clojure.core/nth input__66972 1)]
                             (if
                              (clojure.core/seq? input__66972_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__66972_nth_0__)
                                3)
                               (clojure.core/let
                                [input__66972_nth_0___nth_0__
                                 (clojure.core/nth
                                  input__66972_nth_0__
                                  0)
                                 input__66972_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__66972_nth_0__
                                  1)
                                 input__66972_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__66972_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?pattern
                                  input__66972_nth_0___nth_0__]
                                 (clojure.core/let
                                  [?expression
                                   input__66972_nth_0___nth_1__]
                                  (clojure.core/let
                                   [?next input__66972_nth_0___nth_2__]
                                   (clojure.core/let
                                    [?form input__66972_nth_0__]
                                    (clojure.core/let
                                     [?env input__66972_nth_1__]
                                     (try
                                      [{:tag :let,
                                        :pattern
                                        (clojure.core/let
                                         [CATA_RESULT__32291__auto__
                                          (CATA__FN__67009
                                           [?pattern ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__32291__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__32291__auto__
                                           0))),
                                        :expression
                                        {:tag :host-expression,
                                         :form ?expression},
                                        :next
                                        (clojure.core/let
                                         [CATA_RESULT__32291__auto__
                                          (CATA__FN__67009
                                           [?next ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__32291__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__32291__auto__
                                           0)))}]
                                      (catch
                                       java.lang.Exception
                                       e__33231__auto__
                                       (if
                                        (meander.runtime.zeta/fail?
                                         e__33231__auto__)
                                        (meander.runtime.zeta/fail)
                                        (throw
                                         e__33231__auto__))))))))))
                               (state__67915))
                              (state__67915)))
                            (state__67915))
                           (state__67915)))))
                       (state__67915)))))
                   (state__67915))))
                (state__67915)))
              (state__67915)))
            (state__67915))
           (state__67915))))
        (state__67915
         []
         (clojure.core/letfn
          [(def__67746
            [arg__67769 ?__66979]
            (clojure.core/letfn
             [(state__68067
               []
               (clojure.core/let
                [x__67770 "meander.zeta"]
                (if
                 (clojure.core/= ?__66979 x__67770)
                 [?__66979]
                 (state__68068))))
              (state__68068
               []
               (if
                (clojure.core/map? arg__67769)
                (clojure.core/let
                 [VAL__67771 (.valAt arg__67769 :aliases)]
                 (if
                  (clojure.core/map? VAL__67771)
                  (clojure.core/let
                   [X__67773 (clojure.core/set VAL__67771)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__67773))
                    (clojure.core/loop
                     [search_space__68069 (clojure.core/seq X__67773)]
                     (if
                      (clojure.core/seq search_space__68069)
                      (clojure.core/let
                       [elem__67774
                        (clojure.core/first search_space__68069)
                        result__68070
                        (clojure.core/let
                         [elem__67774_nth_0__
                          (clojure.core/nth elem__67774 0)
                          elem__67774_nth_1__
                          (clojure.core/nth elem__67774 1)]
                         (if
                          (clojure.core/symbol? elem__67774_nth_0__)
                          (clojure.core/let
                           [X__67776
                            (clojure.core/name elem__67774_nth_0__)]
                           (if
                            (clojure.core/= ?__66979 X__67776)
                            (if
                             (clojure.core/symbol? elem__67774_nth_1__)
                             (clojure.core/let
                              [X__67778
                               (clojure.core/name elem__67774_nth_1__)]
                              (clojure.core/case
                               X__67778
                               ("meander.zeta")
                               [?__66979]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__68070)
                        (recur (clojure.core/next search_space__68069))
                        result__68070))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__68067)))]
          (if
           (clojure.core/vector? input__66972)
           (if
            (clojure.core/= (clojure.core/count input__66972) 2)
            (clojure.core/let
             [input__66972_nth_0__
              (clojure.core/nth input__66972 0)
              input__66972_nth_1__
              (clojure.core/nth input__66972 1)]
             (if
              (clojure.core/seq? input__66972_nth_0__)
              (clojure.core/let
               [input__66972_nth_0___l__
                (clojure.core/take 1 input__66972_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__66972_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__66972_nth_0___r__
                  (clojure.core/drop 1 input__66972_nth_0__)]
                 (clojure.core/let
                  [input__66972_nth_0___l___nth_0__
                   (clojure.core/nth input__66972_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__66972_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__67756
                     (clojure.core/namespace
                      input__66972_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__66979 X__67756]
                     (clojure.core/let
                      [X__67758
                       (clojure.core/name
                        input__66972_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__67758
                       ("not")
                       (clojure.core/let
                        [x__30988__auto__
                         (def__67746 input__66972_nth_1__ ?__66979)]
                        (if
                         (meander.runtime.zeta/fail? x__30988__auto__)
                         (state__67916)
                         (clojure.core/let
                          [[?__66979] x__30988__auto__]
                          (if
                           (clojure.core/vector? input__66972)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__66972)
                             2)
                            (clojure.core/let
                             [input__66972_nth_0__
                              (clojure.core/nth input__66972 0)
                              input__66972_nth_1__
                              (clojure.core/nth input__66972 1)]
                             (if
                              (clojure.core/seq? input__66972_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__66972_nth_0__)
                                2)
                               (clojure.core/let
                                [input__66972_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__66972_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__66972_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__66972_nth_0__]
                                  (clojure.core/let
                                   [?env input__66972_nth_1__]
                                   (try
                                    [{:tag :not,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__32291__auto__
                                        (CATA__FN__67009
                                         [?pattern ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__32291__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__32291__auto__
                                         0))),
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__33231__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__33231__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__33231__auto__))))))))
                               (state__67916))
                              (state__67916)))
                            (state__67916))
                           (state__67916)))))
                       (state__67916)))))
                   (state__67916))))
                (state__67916)))
              (state__67916)))
            (state__67916))
           (state__67916))))
        (state__67916
         []
         (clojure.core/letfn
          [(def__67780
            [arg__67803 ?__66980]
            (clojure.core/letfn
             [(state__68072
               []
               (clojure.core/let
                [x__67804 "meander.zeta"]
                (if
                 (clojure.core/= ?__66980 x__67804)
                 [?__66980]
                 (state__68073))))
              (state__68073
               []
               (if
                (clojure.core/map? arg__67803)
                (clojure.core/let
                 [VAL__67805 (.valAt arg__67803 :aliases)]
                 (if
                  (clojure.core/map? VAL__67805)
                  (clojure.core/let
                   [X__67807 (clojure.core/set VAL__67805)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__67807))
                    (clojure.core/loop
                     [search_space__68074 (clojure.core/seq X__67807)]
                     (if
                      (clojure.core/seq search_space__68074)
                      (clojure.core/let
                       [elem__67808
                        (clojure.core/first search_space__68074)
                        result__68075
                        (clojure.core/let
                         [elem__67808_nth_0__
                          (clojure.core/nth elem__67808 0)
                          elem__67808_nth_1__
                          (clojure.core/nth elem__67808 1)]
                         (if
                          (clojure.core/symbol? elem__67808_nth_0__)
                          (clojure.core/let
                           [X__67810
                            (clojure.core/name elem__67808_nth_0__)]
                           (if
                            (clojure.core/= ?__66980 X__67810)
                            (if
                             (clojure.core/symbol? elem__67808_nth_1__)
                             (clojure.core/let
                              [X__67812
                               (clojure.core/name elem__67808_nth_1__)]
                              (clojure.core/case
                               X__67812
                               ("meander.zeta")
                               [?__66980]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__68075)
                        (recur (clojure.core/next search_space__68074))
                        result__68075))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__68072)))]
          (if
           (clojure.core/vector? input__66972)
           (if
            (clojure.core/= (clojure.core/count input__66972) 2)
            (clojure.core/let
             [input__66972_nth_0__
              (clojure.core/nth input__66972 0)
              input__66972_nth_1__
              (clojure.core/nth input__66972 1)]
             (if
              (clojure.core/seq? input__66972_nth_0__)
              (clojure.core/let
               [input__66972_nth_0___l__
                (clojure.core/take 1 input__66972_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__66972_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__66972_nth_0___r__
                  (clojure.core/drop 1 input__66972_nth_0__)]
                 (clojure.core/let
                  [input__66972_nth_0___l___nth_0__
                   (clojure.core/nth input__66972_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__66972_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__67790
                     (clojure.core/namespace
                      input__66972_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__66980 X__67790]
                     (clojure.core/let
                      [X__67792
                       (clojure.core/name
                        input__66972_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__67792
                       ("or")
                       (clojure.core/let
                        [x__30988__auto__
                         (def__67780 input__66972_nth_1__ ?__66980)]
                        (if
                         (meander.runtime.zeta/fail? x__30988__auto__)
                         (state__67917)
                         (clojure.core/let
                          [[?__66980] x__30988__auto__]
                          (if
                           (clojure.core/vector? input__66972)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__66972)
                             2)
                            (clojure.core/let
                             [input__66972_nth_0__
                              (clojure.core/nth input__66972 0)
                              input__66972_nth_1__
                              (clojure.core/nth input__66972 1)]
                             (if
                              (clojure.core/seq? input__66972_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__66972_nth_0__)
                                3)
                               (clojure.core/let
                                [input__66972_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__66972_nth_0__
                                  1)
                                 input__66972_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__66972_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?left input__66972_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?right input__66972_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__66972_nth_0__]
                                   (clojure.core/let
                                    [?env input__66972_nth_1__]
                                    (try
                                     [{:tag :or,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__32291__auto__
                                         (CATA__FN__67009
                                          [?left ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__32291__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__32291__auto__
                                          0))),
                                       :right
                                       (clojure.core/let
                                        [CATA_RESULT__32291__auto__
                                         (CATA__FN__67009
                                          [?right ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__32291__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__32291__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__33231__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__33231__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__33231__auto__)))))))))
                               (state__67917))
                              (state__67917)))
                            (state__67917))
                           (state__67917)))))
                       (state__67917)))))
                   (state__67917))))
                (state__67917)))
              (state__67917)))
            (state__67917))
           (state__67917))))
        (state__67917
         []
         (clojure.core/letfn
          [(def__67814
            [arg__67837 ?__66981]
            (clojure.core/letfn
             [(state__68077
               []
               (clojure.core/let
                [x__67838 "meander.zeta"]
                (if
                 (clojure.core/= ?__66981 x__67838)
                 [?__66981]
                 (state__68078))))
              (state__68078
               []
               (if
                (clojure.core/map? arg__67837)
                (clojure.core/let
                 [VAL__67839 (.valAt arg__67837 :aliases)]
                 (if
                  (clojure.core/map? VAL__67839)
                  (clojure.core/let
                   [X__67841 (clojure.core/set VAL__67839)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__67841))
                    (clojure.core/loop
                     [search_space__68079 (clojure.core/seq X__67841)]
                     (if
                      (clojure.core/seq search_space__68079)
                      (clojure.core/let
                       [elem__67842
                        (clojure.core/first search_space__68079)
                        result__68080
                        (clojure.core/let
                         [elem__67842_nth_0__
                          (clojure.core/nth elem__67842 0)
                          elem__67842_nth_1__
                          (clojure.core/nth elem__67842 1)]
                         (if
                          (clojure.core/symbol? elem__67842_nth_0__)
                          (clojure.core/let
                           [X__67844
                            (clojure.core/name elem__67842_nth_0__)]
                           (if
                            (clojure.core/= ?__66981 X__67844)
                            (if
                             (clojure.core/symbol? elem__67842_nth_1__)
                             (clojure.core/let
                              [X__67846
                               (clojure.core/name elem__67842_nth_1__)]
                              (clojure.core/case
                               X__67846
                               ("meander.zeta")
                               [?__66981]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__68080)
                        (recur (clojure.core/next search_space__68079))
                        result__68080))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__68077)))]
          (if
           (clojure.core/vector? input__66972)
           (if
            (clojure.core/= (clojure.core/count input__66972) 2)
            (clojure.core/let
             [input__66972_nth_0__
              (clojure.core/nth input__66972 0)
              input__66972_nth_1__
              (clojure.core/nth input__66972 1)]
             (if
              (clojure.core/seq? input__66972_nth_0__)
              (clojure.core/let
               [input__66972_nth_0___l__
                (clojure.core/take 1 input__66972_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__66972_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__66972_nth_0___r__
                  (clojure.core/drop 1 input__66972_nth_0__)]
                 (clojure.core/let
                  [input__66972_nth_0___l___nth_0__
                   (clojure.core/nth input__66972_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__66972_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__67824
                     (clojure.core/namespace
                      input__66972_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__66981 X__67824]
                     (clojure.core/let
                      [X__67826
                       (clojure.core/name
                        input__66972_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__67826
                       ("string")
                       (clojure.core/let
                        [x__30988__auto__
                         (def__67814 input__66972_nth_1__ ?__66981)]
                        (if
                         (meander.runtime.zeta/fail? x__30988__auto__)
                         (state__67918)
                         (clojure.core/let
                          [[?__66981] x__30988__auto__]
                          (if
                           (clojure.core/vector? input__66972)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__66972)
                             2)
                            (clojure.core/let
                             [input__66972_nth_0__
                              (clojure.core/nth input__66972 0)
                              input__66972_nth_1__
                              (clojure.core/nth input__66972 1)]
                             (if
                              (clojure.core/seq? input__66972_nth_0__)
                              (clojure.core/let
                               [input__66972_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__66972_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__66972_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__66972_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__66972_nth_0__)]
                                 (clojure.core/let
                                  [?sequence input__66972_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__66972_nth_0__]
                                   (clojure.core/let
                                    [?env input__66972_nth_1__]
                                    (try
                                     [{:tag :string,
                                       :next
                                       (clojure.core/let
                                        [CATA_RESULT__32291__auto__
                                         (CATA__FN__67009
                                          ['meander.dev.parse.zeta/parse-string
                                           (clojure.core/into
                                            []
                                            ?sequence)
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__32291__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__32291__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__33231__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__33231__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__33231__auto__))))))))
                                (state__67918)))
                              (state__67918)))
                            (state__67918))
                           (state__67918)))))
                       (state__67918)))))
                   (state__67918))))
                (state__67918)))
              (state__67918)))
            (state__67918))
           (state__67918))))
        (state__67918
         []
         (if
          (clojure.core/vector? input__66972)
          (if
           (clojure.core/= (clojure.core/count input__66972) 2)
           (clojure.core/let
            [input__66972_nth_0__ (clojure.core/nth input__66972 0)]
            (clojure.core/letfn
             [(state__68082
               []
               (clojure.core/let
                [input__66972_nth_1__
                 (clojure.core/nth input__66972 1)]
                (clojure.core/letfn
                 [(state__68087
                   []
                   (if
                    (clojure.core/seq? input__66972_nth_0__)
                    (clojure.core/let
                     [?sequence input__66972_nth_0__]
                     (clojure.core/let
                      [?env input__66972_nth_1__]
                      (try
                       [{:tag :seq,
                         :next
                         (clojure.core/let
                          [CATA_RESULT__32291__auto__
                           (CATA__FN__67009
                            ['meander.dev.parse.zeta/parse-seq
                             (clojure.core/into [] ?sequence)
                             ?env])]
                          (if
                           (meander.runtime.zeta/fail?
                            CATA_RESULT__32291__auto__)
                           (throw (meander.runtime.zeta/fail))
                           (clojure.core/nth
                            CATA_RESULT__32291__auto__
                            0))),
                         :form ?sequence}]
                       (catch
                        java.lang.Exception
                        e__33231__auto__
                        (if
                         (meander.runtime.zeta/fail? e__33231__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__33231__auto__))))))
                    (state__68088)))
                  (state__68088
                   []
                   (if
                    (clojure.core/map? input__66972_nth_0__)
                    (clojure.core/let
                     [?map input__66972_nth_0__]
                     (clojure.core/let
                      [?env input__66972_nth_1__]
                      (try
                       [{:tag :map,
                         :next
                         (clojure.core/let
                          [CATA_RESULT__32291__auto__
                           (CATA__FN__67009
                            ['meander.dev.parse.zeta/parse-entries
                             ?map
                             ?env])]
                          (if
                           (meander.runtime.zeta/fail?
                            CATA_RESULT__32291__auto__)
                           (throw (meander.runtime.zeta/fail))
                           (clojure.core/nth
                            CATA_RESULT__32291__auto__
                            0))),
                         :form ?map}]
                       (catch
                        java.lang.Exception
                        e__33231__auto__
                        (if
                         (meander.runtime.zeta/fail? e__33231__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__33231__auto__))))))
                    (state__68083)))]
                 (state__68087))))
              (state__68083
               []
               (if
                (clojure.core/symbol? input__66972_nth_0__)
                (clojure.core/let
                 [X__67856
                  (clojure.core/namespace input__66972_nth_0__)]
                 (clojure.core/let
                  [X__67858 (clojure.core/name input__66972_nth_0__)]
                  (if
                   (clojure.core/string? X__67858)
                   (clojure.core/letfn
                    [(state__68089
                      []
                      (clojure.core/let
                       [ret__67859
                        (clojure.core/re-matches #"_.*" X__67858)]
                       (if
                        (clojure.core/some? ret__67859)
                        (clojure.core/let
                         [?name ret__67859]
                         (clojure.core/let
                          [?symbol input__66972_nth_0__]
                          (try
                           [{:tag :wildcard,
                             :name ?name,
                             :form ?symbol,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__33231__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__33231__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__33231__auto__))))))
                        (state__68090))))
                     (state__68090
                      []
                      (clojure.core/let
                       [ret__67866
                        (clojure.core/re-matches #".+#" X__67858)]
                       (if
                        (clojure.core/some? ret__67866)
                        (clojure.core/let
                         [?name ret__67866]
                         (clojure.core/let
                          [?symbol input__66972_nth_0__]
                          (try
                           [{:tag :random-symbol,
                             :name ?name,
                             :form ?symbol,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__33231__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__33231__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__33231__auto__))))))
                        (state__68091))))
                     (state__68091
                      []
                      (clojure.core/let
                       [ret__67873
                        (clojure.core/re-matches #"%.+" X__67858)]
                       (if
                        (clojure.core/some? ret__67873)
                        (clojure.core/let
                         [?name ret__67873]
                         (clojure.core/let
                          [?symbol input__66972_nth_0__]
                          (try
                           [{:tag :reference,
                             :name ?name,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__33231__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__33231__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__33231__auto__))))))
                        (state__68092))))
                     (state__68092
                      []
                      (clojure.core/let
                       [ret__67880
                        (clojure.core/re-matches #"\*.+" X__67858)]
                       (if
                        (clojure.core/some? ret__67880)
                        (clojure.core/let
                         [?name ret__67880]
                         (clojure.core/let
                          [?symbol input__66972_nth_0__]
                          (try
                           [{:tag :mutable-variable,
                             :name ?name,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__33231__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__33231__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__33231__auto__))))))
                        (state__68093))))
                     (state__68093
                      []
                      (clojure.core/let
                       [ret__67887
                        (clojure.core/re-matches #"\!.+" X__67858)]
                       (if
                        (clojure.core/some? ret__67887)
                        (clojure.core/let
                         [?name ret__67887]
                         (clojure.core/let
                          [?symbol input__66972_nth_0__]
                          (try
                           [{:tag :memory-variable,
                             :name ?name,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__33231__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__33231__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__33231__auto__))))))
                        (state__68094))))
                     (state__68094
                      []
                      (clojure.core/let
                       [ret__67894
                        (clojure.core/re-matches #"\?.+" X__67858)]
                       (if
                        (clojure.core/some? ret__67894)
                        (clojure.core/let
                         [?name ret__67894]
                         (clojure.core/let
                          [?symbol input__66972_nth_0__]
                          (try
                           [{:tag :logic-variable,
                             :name ?name,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__33231__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__33231__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__33231__auto__))))))
                        (state__68084))))]
                    (state__68089))
                   (state__68084))))
                (state__68084)))
              (state__68084
               []
               (if
                (string? input__66972_nth_0__)
                (clojure.core/let
                 [?x input__66972_nth_0__]
                 (try
                  [{:tag :literal, :type :string, :form ?x}]
                  (catch
                   java.lang.Exception
                   e__33231__auto__
                   (if
                    (meander.runtime.zeta/fail? e__33231__auto__)
                    (meander.runtime.zeta/fail)
                    (throw e__33231__auto__)))))
                (state__68085)))
              (state__68085
               []
               (if
                (char? input__66972_nth_0__)
                (clojure.core/let
                 [?x input__66972_nth_0__]
                 (try
                  [{:tag :literal, :type :char, :form ?x}]
                  (catch
                   java.lang.Exception
                   e__33231__auto__
                   (if
                    (meander.runtime.zeta/fail? e__33231__auto__)
                    (meander.runtime.zeta/fail)
                    (throw e__33231__auto__)))))
                (state__68086)))
              (state__68086
               []
               (clojure.core/let
                [?x input__66972_nth_0__]
                (try
                 [{:tag :literal, :form ?x}]
                 (catch
                  java.lang.Exception
                  e__33231__auto__
                  (if
                   (meander.runtime.zeta/fail? e__33231__auto__)
                   (meander.runtime.zeta/fail)
                   (throw e__33231__auto__))))))]
             (state__68082)))
           (state__67919))
          (state__67919)))
        (state__67919
         []
         (clojure.core/let
          [?x input__66972]
          (try
           [?x]
           (catch
            java.lang.Exception
            e__33231__auto__
            (if
             (meander.runtime.zeta/fail? e__33231__auto__)
             (meander.runtime.zeta/fail)
             (throw e__33231__auto__))))))]
       (state__67901)))]
    (clojure.core/let
     [x__30988__auto__ (CATA__FN__67009 input__66972)]
     (if
      (meander.runtime.zeta/fail? x__30988__auto__)
      (meander.runtime.zeta/fail)
      (clojure.core/let
       [[CATA_RETURN__67012] x__30988__auto__]
       CATA_RETURN__67012))))]
  (if
   (meander.runtime.zeta/fail? ret__31168__auto__)
   nil
   ret__31168__auto__)))
