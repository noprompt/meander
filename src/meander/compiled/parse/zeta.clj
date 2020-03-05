(ns meander.compiled.parse.zeta (:require [meander.runtime.zeta]))
(clojure.core/defn
 parse
 [input__93840]
 (let*
  [ret__31168__auto__
   (clojure.core/letfn
    [(CATA__FN__93885
      [input__93840]
      (clojure.core/letfn
       [(state__94811
         []
         (if
          (clojure.core/vector? input__93840)
          (if
           (clojure.core/= (clojure.core/count input__93840) 3)
           (clojure.core/let
            [input__93840_nth_0__
             (clojure.core/nth input__93840 0)
             input__93840_nth_1__
             (clojure.core/nth input__93840 1)
             input__93840_nth_2__
             (clojure.core/nth input__93840 2)]
            (if
             (clojure.core/let
              [x__30048__auto__ input__93840_nth_0__]
              (clojure.core/case
               x__30048__auto__
               (meander.dev.parse.zeta/parse-seq
                meander.dev.parse.zeta/parse-string)
               true
               false))
             (clojure.core/letfn
              [(state__94832
                []
                (if
                 (clojure.core/vector? input__93840_nth_1__)
                 (clojure.core/case
                  input__93840_nth_1__
                  ([])
                  (clojure.core/let
                   [?env input__93840_nth_2__]
                   (try
                    [{:tag :empty}]
                    (catch
                     java.lang.Exception
                     e__33231__auto__
                     (if
                      (meander.runtime.zeta/fail? e__33231__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__33231__auto__)))))
                  (state__94833))
                 (state__94833)))
               (state__94833
                []
                (clojure.core/let
                 [?rule-name input__93840_nth_0__]
                 (if
                  (clojure.core/vector? input__93840_nth_1__)
                  (clojure.core/let
                   [n__93893
                    (clojure.core/count input__93840_nth_1__)
                    m__93894
                    (clojure.core/max 0 (clojure.core/- n__93893 2))
                    input__93840_nth_1___l__
                    (clojure.core/subvec
                     input__93840_nth_1__
                     0
                     (clojure.core/min
                      (clojure.core/count input__93840_nth_1__)
                      m__93894))
                    input__93840_nth_1___r__
                    (clojure.core/subvec
                     input__93840_nth_1__
                     m__93894)]
                   (if
                    (clojure.core/=
                     (clojure.core/count input__93840_nth_1___r__)
                     2)
                    (clojure.core/let
                     [!xs (clojure.core/vec input__93840_nth_1___l__)]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__93840_nth_1___r__)
                       2)
                      (clojure.core/let
                       [input__93840_nth_1___r___nth_0__
                        (clojure.core/nth input__93840_nth_1___r__ 0)
                        input__93840_nth_1___r___nth_1__
                        (clojure.core/nth input__93840_nth_1___r__ 1)]
                       (clojure.core/case
                        input__93840_nth_1___r___nth_0__
                        (:meander.zeta/as)
                        (clojure.core/let
                         [?pattern input__93840_nth_1___r___nth_1__]
                         (clojure.core/let
                          [?env input__93840_nth_2__]
                          (try
                           [(clojure.core/let
                             [!xs__counter
                              (meander.runtime.zeta/iterator !xs)]
                             {:tag :as,
                              :pattern
                              (clojure.core/let
                               [CATA_RESULT__32291__auto__
                                (CATA__FN__93885 [?pattern ?env])]
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
                                (CATA__FN__93885
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
                        (state__94812)))
                      (state__94812)))
                    (state__94812)))
                  (state__94812))))]
              (state__94832))
             (state__94812)))
           (state__94812))
          (state__94812)))
        (state__94812
         []
         (clojure.core/letfn
          [(def__93899
            [arg__93934 ?ns]
            (clojure.core/letfn
             [(state__94834
               []
               (clojure.core/let
                [x__93935 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__93935)
                 (clojure.core/let [?env arg__93934] [?env ?ns])
                 (state__94835))))
              (state__94835
               []
               (if
                (clojure.core/map? arg__93934)
                (clojure.core/let
                 [VAL__93936 (.valAt arg__93934 :aliases)]
                 (if
                  (clojure.core/map? VAL__93936)
                  (clojure.core/let
                   [X__93938 (clojure.core/set VAL__93936)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__93938))
                    (clojure.core/loop
                     [search_space__94836 (clojure.core/seq X__93938)]
                     (if
                      (clojure.core/seq search_space__94836)
                      (clojure.core/let
                       [elem__93939
                        (clojure.core/first search_space__94836)
                        result__94837
                        (clojure.core/let
                         [elem__93939_nth_0__
                          (clojure.core/nth elem__93939 0)
                          elem__93939_nth_1__
                          (clojure.core/nth elem__93939 1)]
                         (if
                          (clojure.core/symbol? elem__93939_nth_0__)
                          (clojure.core/let
                           [X__93941
                            (clojure.core/name elem__93939_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__93941)
                            (if
                             (clojure.core/symbol? elem__93939_nth_1__)
                             (clojure.core/let
                              [X__93943
                               (clojure.core/name elem__93939_nth_1__)]
                              (clojure.core/case
                               X__93943
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__93934]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__94837)
                        (recur (clojure.core/next search_space__94836))
                        result__94837))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__94834)))]
          (if
           (clojure.core/vector? input__93840)
           (if
            (clojure.core/= (clojure.core/count input__93840) 3)
            (clojure.core/let
             [input__93840_nth_0__
              (clojure.core/nth input__93840 0)
              input__93840_nth_1__
              (clojure.core/nth input__93840 1)
              input__93840_nth_2__
              (clojure.core/nth input__93840 2)]
             (if
              (clojure.core/let
               [x__30048__auto__ input__93840_nth_0__]
               (clojure.core/case
                x__30048__auto__
                (meander.dev.parse.zeta/parse-seq
                 meander.dev.parse.zeta/parse-string)
                true
                false))
              (clojure.core/let
               [?rule-name input__93840_nth_0__]
               (if
                (clojure.core/vector? input__93840_nth_1__)
                (clojure.core/loop
                 [search_space__94839
                  (meander.match.runtime.epsilon/partitions
                   2
                   input__93840_nth_1__)]
                 (if
                  (clojure.core/seq search_space__94839)
                  (clojure.core/let
                   [input__93840_nth_1___parts__
                    (clojure.core/first search_space__94839)
                    result__94840
                    (clojure.core/let
                     [input__93840_nth_1___l__
                      (clojure.core/nth input__93840_nth_1___parts__ 0)
                      input__93840_nth_1___r__
                      (clojure.core/nth
                       input__93840_nth_1___parts__
                       1)]
                     (clojure.core/let
                      [!init
                       (clojure.core/vec input__93840_nth_1___l__)]
                      (clojure.core/let
                       [input__93840_nth_1___r___l__
                        (clojure.core/subvec
                         input__93840_nth_1___r__
                         0
                         (clojure.core/min
                          (clojure.core/count input__93840_nth_1___r__)
                          2))]
                       (if
                        (clojure.core/=
                         (clojure.core/count
                          input__93840_nth_1___r___l__)
                         2)
                        (clojure.core/let
                         [input__93840_nth_1___r___r__
                          (clojure.core/subvec
                           input__93840_nth_1___r__
                           2)]
                         (clojure.core/let
                          [input__93840_nth_1___r___l___nth_0__
                           (clojure.core/nth
                            input__93840_nth_1___r___l__
                            0)
                           input__93840_nth_1___r___l___nth_1__
                           (clojure.core/nth
                            input__93840_nth_1___r___l__
                            1)]
                          (if
                           (clojure.core/symbol?
                            input__93840_nth_1___r___l___nth_0__)
                           (clojure.core/let
                            [X__93908
                             (clojure.core/namespace
                              input__93840_nth_1___r___l___nth_0__)]
                            (clojure.core/let
                             [?ns X__93908]
                             (clojure.core/let
                              [X__93910
                               (clojure.core/name
                                input__93840_nth_1___r___l___nth_0__)]
                              (if
                               (clojure.core/string? X__93910)
                               (clojure.core/let
                                [ret__93911
                                 (clojure.core/re-matches
                                  #"&(\d+)"
                                  X__93910)]
                                (if
                                 (clojure.core/some? ret__93911)
                                 (if
                                  (clojure.core/vector? ret__93911)
                                  (if
                                   (clojure.core/=
                                    (clojure.core/count ret__93911)
                                    2)
                                   (clojure.core/let
                                    [ret__93911_nth_1__
                                     (clojure.core/nth ret__93911 1)]
                                    (clojure.core/let
                                     [?n ret__93911_nth_1__]
                                     (clojure.core/let
                                      [?pattern
                                       input__93840_nth_1___r___l___nth_1__]
                                      (clojure.core/let
                                       [?rest
                                        input__93840_nth_1___r___r__]
                                       (clojure.core/let
                                        [x__30988__auto__
                                         (def__93899
                                          input__93840_nth_2__
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
                                               (CATA__FN__93885
                                                ['meander.dev.parse.zeta/join-args
                                                 (clojure.core/let
                                                  [CATA_RESULT__32291__auto__
                                                   (CATA__FN__93885
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
                                                   (CATA__FN__93885
                                                    ['meander.dev.parse.zeta/join-args
                                                     {:tag :slice,
                                                      :size
                                                      (Integer. ?n),
                                                      :pattern
                                                      (clojure.core/let
                                                       [CATA_RESULT__32291__auto__
                                                        (CATA__FN__93885
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
                                                       (CATA__FN__93885
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
                    (meander.runtime.zeta/fail? result__94840)
                    (recur (clojure.core/next search_space__94839))
                    result__94840))
                  (state__94813)))
                (state__94813)))
              (state__94813)))
            (state__94813))
           (state__94813))))
        (state__94813
         []
         (clojure.core/letfn
          [(def__93956
            [arg__93988 ?ns]
            (clojure.core/letfn
             [(state__94842
               []
               (clojure.core/let
                [x__93989 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__93989)
                 (clojure.core/let [?env arg__93988] [?env ?ns])
                 (state__94843))))
              (state__94843
               []
               (if
                (clojure.core/map? arg__93988)
                (clojure.core/let
                 [VAL__93990 (.valAt arg__93988 :aliases)]
                 (if
                  (clojure.core/map? VAL__93990)
                  (clojure.core/let
                   [X__93992 (clojure.core/set VAL__93990)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__93992))
                    (clojure.core/loop
                     [search_space__94844 (clojure.core/seq X__93992)]
                     (if
                      (clojure.core/seq search_space__94844)
                      (clojure.core/let
                       [elem__93993
                        (clojure.core/first search_space__94844)
                        result__94845
                        (clojure.core/let
                         [elem__93993_nth_0__
                          (clojure.core/nth elem__93993 0)
                          elem__93993_nth_1__
                          (clojure.core/nth elem__93993 1)]
                         (if
                          (clojure.core/symbol? elem__93993_nth_0__)
                          (clojure.core/let
                           [X__93995
                            (clojure.core/name elem__93993_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__93995)
                            (if
                             (clojure.core/symbol? elem__93993_nth_1__)
                             (clojure.core/let
                              [X__93997
                               (clojure.core/name elem__93993_nth_1__)]
                              (clojure.core/case
                               X__93997
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__93988]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__94845)
                        (recur (clojure.core/next search_space__94844))
                        result__94845))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__94842)))]
          (if
           (clojure.core/vector? input__93840)
           (if
            (clojure.core/= (clojure.core/count input__93840) 3)
            (clojure.core/let
             [input__93840_nth_0__
              (clojure.core/nth input__93840 0)
              input__93840_nth_1__
              (clojure.core/nth input__93840 1)
              input__93840_nth_2__
              (clojure.core/nth input__93840 2)]
             (if
              (clojure.core/=
               input__93840_nth_0__
               'meander.dev.parse.zeta/parse-seq)
              (if
               (clojure.core/vector? input__93840_nth_1__)
               (clojure.core/loop
                [search_space__94847
                 (meander.match.runtime.epsilon/partitions
                  2
                  input__93840_nth_1__)]
                (if
                 (clojure.core/seq search_space__94847)
                 (clojure.core/let
                  [input__93840_nth_1___parts__
                   (clojure.core/first search_space__94847)
                   result__94848
                   (clojure.core/let
                    [input__93840_nth_1___l__
                     (clojure.core/nth input__93840_nth_1___parts__ 0)
                     input__93840_nth_1___r__
                     (clojure.core/nth input__93840_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__93840_nth_1___l__)]
                     (clojure.core/let
                      [input__93840_nth_1___r___l__
                       (clojure.core/subvec
                        input__93840_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__93840_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__93840_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__93840_nth_1___r___r__
                         (clojure.core/subvec
                          input__93840_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__93840_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__93840_nth_1___r___l__
                           0)
                          input__93840_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__93840_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__93840_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__93965
                            (clojure.core/namespace
                             input__93840_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__93965]
                            (clojure.core/let
                             [X__93967
                              (clojure.core/name
                               input__93840_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__93967)
                              (if
                               (clojure.core/re-matches
                                #"&.*"
                                X__93967)
                               (clojure.core/let
                                [?pattern
                                 input__93840_nth_1___r___l___nth_1__]
                                (clojure.core/let
                                 [?rest input__93840_nth_1___r___r__]
                                 (clojure.core/let
                                  [x__30988__auto__
                                   (def__93956
                                    input__93840_nth_2__
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
                                         (CATA__FN__93885
                                          ['meander.dev.parse.zeta/join-args
                                           (clojure.core/let
                                            [CATA_RESULT__32291__auto__
                                             (CATA__FN__93885
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
                                             (CATA__FN__93885
                                              ['meander.dev.parse.zeta/join-args
                                               (clojure.core/let
                                                [CATA_RESULT__32291__auto__
                                                 (CATA__FN__93885
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
                                                 (CATA__FN__93885
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
                   (meander.runtime.zeta/fail? result__94848)
                   (recur (clojure.core/next search_space__94847))
                   result__94848))
                 (state__94814)))
               (state__94814))
              (state__94814)))
            (state__94814))
           (state__94814))))
        (state__94814
         []
         (clojure.core/letfn
          [(def__94010
            [arg__94042 ?ns]
            (clojure.core/letfn
             [(state__94850
               []
               (clojure.core/let
                [x__94043 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__94043)
                 (clojure.core/let [?env arg__94042] [?env ?ns])
                 (state__94851))))
              (state__94851
               []
               (if
                (clojure.core/map? arg__94042)
                (clojure.core/let
                 [VAL__94044 (.valAt arg__94042 :aliases)]
                 (if
                  (clojure.core/map? VAL__94044)
                  (clojure.core/let
                   [X__94046 (clojure.core/set VAL__94044)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__94046))
                    (clojure.core/loop
                     [search_space__94852 (clojure.core/seq X__94046)]
                     (if
                      (clojure.core/seq search_space__94852)
                      (clojure.core/let
                       [elem__94047
                        (clojure.core/first search_space__94852)
                        result__94853
                        (clojure.core/let
                         [elem__94047_nth_0__
                          (clojure.core/nth elem__94047 0)
                          elem__94047_nth_1__
                          (clojure.core/nth elem__94047 1)]
                         (if
                          (clojure.core/symbol? elem__94047_nth_0__)
                          (clojure.core/let
                           [X__94049
                            (clojure.core/name elem__94047_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__94049)
                            (if
                             (clojure.core/symbol? elem__94047_nth_1__)
                             (clojure.core/let
                              [X__94051
                               (clojure.core/name elem__94047_nth_1__)]
                              (clojure.core/case
                               X__94051
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__94042]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__94853)
                        (recur (clojure.core/next search_space__94852))
                        result__94853))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__94850)))]
          (if
           (clojure.core/vector? input__93840)
           (if
            (clojure.core/= (clojure.core/count input__93840) 3)
            (clojure.core/let
             [input__93840_nth_0__
              (clojure.core/nth input__93840 0)
              input__93840_nth_1__
              (clojure.core/nth input__93840 1)
              input__93840_nth_2__
              (clojure.core/nth input__93840 2)]
             (if
              (clojure.core/=
               input__93840_nth_0__
               'meander.dev.parse.zeta/parse-string)
              (if
               (clojure.core/vector? input__93840_nth_1__)
               (clojure.core/loop
                [search_space__94855
                 (meander.match.runtime.epsilon/partitions
                  2
                  input__93840_nth_1__)]
                (if
                 (clojure.core/seq search_space__94855)
                 (clojure.core/let
                  [input__93840_nth_1___parts__
                   (clojure.core/first search_space__94855)
                   result__94856
                   (clojure.core/let
                    [input__93840_nth_1___l__
                     (clojure.core/nth input__93840_nth_1___parts__ 0)
                     input__93840_nth_1___r__
                     (clojure.core/nth input__93840_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__93840_nth_1___l__)]
                     (clojure.core/let
                      [input__93840_nth_1___r___l__
                       (clojure.core/subvec
                        input__93840_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__93840_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__93840_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__93840_nth_1___r___r__
                         (clojure.core/subvec
                          input__93840_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__93840_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__93840_nth_1___r___l__
                           0)
                          input__93840_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__93840_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__93840_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__94019
                            (clojure.core/namespace
                             input__93840_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__94019]
                            (clojure.core/let
                             [X__94021
                              (clojure.core/name
                               input__93840_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__94021)
                              (if
                               (clojure.core/re-matches
                                #"&.*"
                                X__94021)
                               (clojure.core/let
                                [?pattern
                                 input__93840_nth_1___r___l___nth_1__]
                                (clojure.core/let
                                 [?rest input__93840_nth_1___r___r__]
                                 (clojure.core/let
                                  [x__30988__auto__
                                   (def__94010
                                    input__93840_nth_2__
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
                                         (CATA__FN__93885
                                          ['meander.dev.parse.zeta/string-join-args
                                           (clojure.core/let
                                            [CATA_RESULT__32291__auto__
                                             (CATA__FN__93885
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
                                             (CATA__FN__93885
                                              ['meander.dev.parse.zeta/string-join-args
                                               (clojure.core/let
                                                [CATA_RESULT__32291__auto__
                                                 (CATA__FN__93885
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
                                                 (CATA__FN__93885
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
                   (meander.runtime.zeta/fail? result__94856)
                   (recur (clojure.core/next search_space__94855))
                   result__94856))
                 (state__94815)))
               (state__94815))
              (state__94815)))
            (state__94815))
           (state__94815))))
        (state__94815
         []
         (if
          (clojure.core/vector? input__93840)
          (if
           (clojure.core/= (clojure.core/count input__93840) 3)
           (clojure.core/let
            [input__93840_nth_0__
             (clojure.core/nth input__93840 0)
             input__93840_nth_1__
             (clojure.core/nth input__93840 1)
             input__93840_nth_2__
             (clojure.core/nth input__93840 2)]
            (clojure.core/letfn
             [(state__94858
               []
               (if
                (clojure.core/=
                 input__93840_nth_0__
                 'meander.dev.parse.zeta/parse-seq)
                (if
                 (clojure.core/vector? input__93840_nth_1__)
                 (clojure.core/let
                  [n__94072
                   (clojure.core/count input__93840_nth_1__)
                   m__94073
                   (clojure.core/max 0 (clojure.core/- n__94072 2))
                   input__93840_nth_1___l__
                   (clojure.core/subvec
                    input__93840_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__93840_nth_1__)
                     m__94073))
                   input__93840_nth_1___r__
                   (clojure.core/subvec input__93840_nth_1__ m__94073)]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__93840_nth_1___r__)
                    2)
                   (clojure.core/let
                    [!xs (clojure.core/vec input__93840_nth_1___l__)]
                    (if
                     (clojure.core/=
                      (clojure.core/count input__93840_nth_1___r__)
                      2)
                     (clojure.core/let
                      [input__93840_nth_1___r___nth_0__
                       (clojure.core/nth input__93840_nth_1___r__ 0)
                       input__93840_nth_1___r___nth_1__
                       (clojure.core/nth input__93840_nth_1___r__ 1)]
                      (if
                       (clojure.core/symbol?
                        input__93840_nth_1___r___nth_0__)
                       (clojure.core/let
                        [X__94077
                         (clojure.core/namespace
                          input__93840_nth_1___r___nth_0__)]
                        (clojure.core/let
                         [?ns X__94077]
                         (clojure.core/let
                          [X__94079
                           (clojure.core/name
                            input__93840_nth_1___r___nth_0__)]
                          (if
                           (clojure.core/string? X__94079)
                           (clojure.core/let
                            [ret__94080
                             (clojure.core/re-matches #"&.*" X__94079)]
                            (if
                             (clojure.core/some? ret__94080)
                             (clojure.core/let
                              [?name ret__94080]
                              (clojure.core/let
                               [?pattern
                                input__93840_nth_1___r___nth_1__]
                               (if
                                (clojure.core/map?
                                 input__93840_nth_2__)
                                (clojure.core/let
                                 [VAL__94064
                                  (.valAt
                                   input__93840_nth_2__
                                   :aliases)]
                                 (if
                                  (clojure.core/map? VAL__94064)
                                  (clojure.core/let
                                   [X__94066
                                    (clojure.core/set VAL__94064)]
                                   (if
                                    (clojure.core/<=
                                     1
                                     (clojure.core/count X__94066))
                                    (clojure.core/loop
                                     [search_space__94863
                                      (clojure.core/seq X__94066)]
                                     (if
                                      (clojure.core/seq
                                       search_space__94863)
                                      (clojure.core/let
                                       [elem__94067
                                        (clojure.core/first
                                         search_space__94863)
                                        result__94864
                                        (clojure.core/let
                                         [elem__94067_nth_0__
                                          (clojure.core/nth
                                           elem__94067
                                           0)
                                          elem__94067_nth_1__
                                          (clojure.core/nth
                                           elem__94067
                                           1)]
                                         (if
                                          (clojure.core/symbol?
                                           elem__94067_nth_0__)
                                          (clojure.core/let
                                           [X__94069
                                            (clojure.core/name
                                             elem__94067_nth_0__)]
                                           (if
                                            (clojure.core/=
                                             ?ns
                                             X__94069)
                                            (if
                                             (clojure.core/symbol?
                                              elem__94067_nth_1__)
                                             (clojure.core/let
                                              [X__94071
                                               (clojure.core/name
                                                elem__94067_nth_1__)]
                                              (clojure.core/case
                                               X__94071
                                               ("meander.zeta")
                                               (clojure.core/let
                                                [?env
                                                 input__93840_nth_2__]
                                                (try
                                                 [(clojure.core/let
                                                   [!xs__counter
                                                    (meander.runtime.zeta/iterator
                                                     !xs)]
                                                   (clojure.core/let
                                                    [CATA_RESULT__32291__auto__
                                                     (CATA__FN__93885
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
                                         result__94864)
                                        (recur
                                         (clojure.core/next
                                          search_space__94863))
                                        result__94864))
                                      (state__94859)))
                                    (state__94859)))
                                  (state__94859)))
                                (state__94859))))
                             (state__94859)))
                           (state__94859)))))
                       (state__94859)))
                     (state__94859)))
                   (state__94859)))
                 (state__94859))
                (state__94859)))
              (state__94859
               []
               (if
                (clojure.core/=
                 input__93840_nth_0__
                 'meander.dev.parse.zeta/parse-string)
                (if
                 (clojure.core/vector? input__93840_nth_1__)
                 (clojure.core/let
                  [n__94091
                   (clojure.core/count input__93840_nth_1__)
                   m__94092
                   (clojure.core/max 0 (clojure.core/- n__94091 2))
                   input__93840_nth_1___l__
                   (clojure.core/subvec
                    input__93840_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__93840_nth_1__)
                     m__94092))
                   input__93840_nth_1___r__
                   (clojure.core/subvec input__93840_nth_1__ m__94092)]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__93840_nth_1___r__)
                    2)
                   (clojure.core/let
                    [!xs (clojure.core/vec input__93840_nth_1___l__)]
                    (if
                     (clojure.core/=
                      (clojure.core/count input__93840_nth_1___r__)
                      2)
                     (clojure.core/let
                      [input__93840_nth_1___r___nth_0__
                       (clojure.core/nth input__93840_nth_1___r__ 0)
                       input__93840_nth_1___r___nth_1__
                       (clojure.core/nth input__93840_nth_1___r__ 1)]
                      (if
                       (clojure.core/symbol?
                        input__93840_nth_1___r___nth_0__)
                       (clojure.core/let
                        [X__94096
                         (clojure.core/namespace
                          input__93840_nth_1___r___nth_0__)]
                        (clojure.core/let
                         [?ns X__94096]
                         (clojure.core/let
                          [X__94098
                           (clojure.core/name
                            input__93840_nth_1___r___nth_0__)]
                          (if
                           (clojure.core/string? X__94098)
                           (clojure.core/let
                            [ret__94099
                             (clojure.core/re-matches #"&.*" X__94098)]
                            (if
                             (clojure.core/some? ret__94099)
                             (clojure.core/let
                              [?name ret__94099]
                              (clojure.core/let
                               [?pattern
                                input__93840_nth_1___r___nth_1__]
                               (if
                                (clojure.core/map?
                                 input__93840_nth_2__)
                                (clojure.core/let
                                 [VAL__94083
                                  (.valAt
                                   input__93840_nth_2__
                                   :aliases)]
                                 (if
                                  (clojure.core/map? VAL__94083)
                                  (clojure.core/let
                                   [X__94085
                                    (clojure.core/set VAL__94083)]
                                   (if
                                    (clojure.core/<=
                                     1
                                     (clojure.core/count X__94085))
                                    (clojure.core/loop
                                     [search_space__94866
                                      (clojure.core/seq X__94085)]
                                     (if
                                      (clojure.core/seq
                                       search_space__94866)
                                      (clojure.core/let
                                       [elem__94086
                                        (clojure.core/first
                                         search_space__94866)
                                        result__94867
                                        (clojure.core/let
                                         [elem__94086_nth_0__
                                          (clojure.core/nth
                                           elem__94086
                                           0)
                                          elem__94086_nth_1__
                                          (clojure.core/nth
                                           elem__94086
                                           1)]
                                         (if
                                          (clojure.core/symbol?
                                           elem__94086_nth_0__)
                                          (clojure.core/let
                                           [X__94088
                                            (clojure.core/name
                                             elem__94086_nth_0__)]
                                           (if
                                            (clojure.core/=
                                             ?ns
                                             X__94088)
                                            (if
                                             (clojure.core/symbol?
                                              elem__94086_nth_1__)
                                             (clojure.core/let
                                              [X__94090
                                               (clojure.core/name
                                                elem__94086_nth_1__)]
                                              (clojure.core/case
                                               X__94090
                                               ("meander.zeta")
                                               (clojure.core/let
                                                [?env
                                                 input__93840_nth_2__]
                                                (try
                                                 [(clojure.core/let
                                                   [!xs__counter
                                                    (meander.runtime.zeta/iterator
                                                     !xs)]
                                                   (clojure.core/let
                                                    [CATA_RESULT__32291__auto__
                                                     (CATA__FN__93885
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
                                         result__94867)
                                        (recur
                                         (clojure.core/next
                                          search_space__94866))
                                        result__94867))
                                      (state__94860)))
                                    (state__94860)))
                                  (state__94860)))
                                (state__94860))))
                             (state__94860)))
                           (state__94860)))))
                       (state__94860)))
                     (state__94860)))
                   (state__94860)))
                 (state__94860))
                (state__94860)))
              (state__94860
               []
               (if
                (clojure.core/=
                 input__93840_nth_0__
                 'meander.dev.parse.zeta/parse-seq)
                (if
                 (clojure.core/vector? input__93840_nth_1__)
                 (clojure.core/loop
                  [search_space__94869
                   (meander.match.runtime.epsilon/partitions
                    2
                    input__93840_nth_1__)]
                  (if
                   (clojure.core/seq search_space__94869)
                   (clojure.core/let
                    [input__93840_nth_1___parts__
                     (clojure.core/first search_space__94869)
                     result__94870
                     (clojure.core/let
                      [input__93840_nth_1___l__
                       (clojure.core/nth
                        input__93840_nth_1___parts__
                        0)
                       input__93840_nth_1___r__
                       (clojure.core/nth
                        input__93840_nth_1___parts__
                        1)]
                      (clojure.core/let
                       [!xs
                        (clojure.core/vec input__93840_nth_1___l__)]
                       (clojure.core/let
                        [input__93840_nth_1___r___l__
                         (clojure.core/subvec
                          input__93840_nth_1___r__
                          0
                          (clojure.core/min
                           (clojure.core/count
                            input__93840_nth_1___r__)
                           1))]
                        (if
                         (clojure.core/=
                          (clojure.core/count
                           input__93840_nth_1___r___l__)
                          1)
                         (clojure.core/let
                          [input__93840_nth_1___r___r__
                           (clojure.core/subvec
                            input__93840_nth_1___r__
                            1)]
                          (if
                           (clojure.core/=
                            input__93840_nth_1___r___l__
                            ['.])
                           (clojure.core/let
                            [?rest input__93840_nth_1___r___r__]
                            (clojure.core/let
                             [?env input__93840_nth_2__]
                             (try
                              [(clojure.core/let
                                [!xs__counter
                                 (meander.runtime.zeta/iterator !xs)]
                                (clojure.core/let
                                 [CATA_RESULT__32291__auto__
                                  (CATA__FN__93885
                                   ['meander.dev.parse.zeta/join-args
                                    (clojure.core/let
                                     [CATA_RESULT__32291__auto__
                                      (CATA__FN__93885
                                       ['meander.dev.parse.zeta/parse-seq
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
                                      (CATA__FN__93885
                                       ['meander.dev.parse.zeta/parse-seq
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
                                  (throw (meander.runtime.zeta/fail))
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
                     (meander.runtime.zeta/fail? result__94870)
                     (recur (clojure.core/next search_space__94869))
                     result__94870))
                   (state__94861)))
                 (state__94861))
                (state__94861)))
              (state__94861
               []
               (if
                (clojure.core/=
                 input__93840_nth_0__
                 'meander.dev.parse.zeta/parse-string)
                (if
                 (clojure.core/vector? input__93840_nth_1__)
                 (clojure.core/loop
                  [search_space__94872
                   (meander.match.runtime.epsilon/partitions
                    2
                    input__93840_nth_1__)]
                  (if
                   (clojure.core/seq search_space__94872)
                   (clojure.core/let
                    [input__93840_nth_1___parts__
                     (clojure.core/first search_space__94872)
                     result__94873
                     (clojure.core/let
                      [input__93840_nth_1___l__
                       (clojure.core/nth
                        input__93840_nth_1___parts__
                        0)
                       input__93840_nth_1___r__
                       (clojure.core/nth
                        input__93840_nth_1___parts__
                        1)]
                      (clojure.core/let
                       [!xs
                        (clojure.core/vec input__93840_nth_1___l__)]
                       (clojure.core/let
                        [input__93840_nth_1___r___l__
                         (clojure.core/subvec
                          input__93840_nth_1___r__
                          0
                          (clojure.core/min
                           (clojure.core/count
                            input__93840_nth_1___r__)
                           1))]
                        (if
                         (clojure.core/=
                          (clojure.core/count
                           input__93840_nth_1___r___l__)
                          1)
                         (clojure.core/let
                          [input__93840_nth_1___r___r__
                           (clojure.core/subvec
                            input__93840_nth_1___r__
                            1)]
                          (if
                           (clojure.core/=
                            input__93840_nth_1___r___l__
                            ['.])
                           (clojure.core/let
                            [?rest input__93840_nth_1___r___r__]
                            (clojure.core/let
                             [?env input__93840_nth_2__]
                             (try
                              [(clojure.core/let
                                [!xs__counter
                                 (meander.runtime.zeta/iterator !xs)]
                                (clojure.core/let
                                 [CATA_RESULT__32291__auto__
                                  (CATA__FN__93885
                                   ['meander.dev.parse.zeta/string-join-args
                                    (clojure.core/let
                                     [CATA_RESULT__32291__auto__
                                      (CATA__FN__93885
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
                                      (CATA__FN__93885
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
                                  (throw (meander.runtime.zeta/fail))
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
                     (meander.runtime.zeta/fail? result__94873)
                     (recur (clojure.core/next search_space__94872))
                     result__94873))
                   (state__94862)))
                 (state__94862))
                (state__94862)))
              (state__94862
               []
               (if
                (clojure.core/let
                 [x__30048__auto__ input__93840_nth_0__]
                 (clojure.core/case
                  x__30048__auto__
                  (meander.dev.parse.zeta/parse-seq
                   meander.dev.parse.zeta/parse-string)
                  true
                  false))
                (clojure.core/let
                 [?rule-name input__93840_nth_0__]
                 (if
                  (clojure.core/vector? input__93840_nth_1__)
                  (clojure.core/let
                   [input__93840_nth_1___l__
                    (clojure.core/subvec
                     input__93840_nth_1__
                     0
                     (clojure.core/min
                      (clojure.core/count input__93840_nth_1__)
                      1))]
                   (if
                    (clojure.core/=
                     (clojure.core/count input__93840_nth_1___l__)
                     1)
                    (clojure.core/let
                     [input__93840_nth_1___r__
                      (clojure.core/subvec input__93840_nth_1__ 1)]
                     (if
                      (clojure.core/= input__93840_nth_1___l__ ['...])
                      (clojure.core/let
                       [?rest input__93840_nth_1___r__]
                       (clojure.core/let
                        [?env input__93840_nth_2__]
                        (try
                         [(clojure.core/let
                           [CATA_RESULT__32291__auto__
                            (CATA__FN__93885 [?rule-name ?rest ?env])]
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
                      (state__94816)))
                    (state__94816)))
                  (state__94816)))
                (state__94816)))]
             (state__94858)))
           (state__94816))
          (state__94816)))
        (state__94816
         []
         (clojure.core/letfn
          [(def__94116
            [arg__94133]
            (clojure.core/letfn
             [(state__94875
               []
               (if
                (clojure.core/=
                 arg__94133
                 'meander.dev.parse.zeta/parse-string)
                (clojure.core/let
                 [?rule-name arg__94133]
                 (clojure.core/let
                  [x__94134 'meander.dev.parse.zeta/string-star-args]
                  (clojure.core/let
                   [?star-name x__94134]
                   [?star-name ?rule-name])))
                (state__94876)))
              (state__94876
               []
               (if
                (clojure.core/=
                 arg__94133
                 'meander.dev.parse.zeta/parse-seq)
                (clojure.core/let
                 [?rule-name arg__94133]
                 (clojure.core/let
                  [x__94135 'meander.dev.parse.zeta/star-args]
                  (clojure.core/let
                   [?star-name x__94135]
                   [?star-name ?rule-name])))
                (meander.runtime.zeta/fail)))]
             (state__94875)))]
          (if
           (clojure.core/vector? input__93840)
           (if
            (clojure.core/= (clojure.core/count input__93840) 3)
            (clojure.core/let
             [input__93840_nth_0__
              (clojure.core/nth input__93840 0)
              input__93840_nth_1__
              (clojure.core/nth input__93840 1)
              input__93840_nth_2__
              (clojure.core/nth input__93840 2)]
             (clojure.core/let
              [x__30988__auto__ (def__94116 input__93840_nth_0__)]
              (if
               (meander.runtime.zeta/fail? x__30988__auto__)
               (state__94817)
               (clojure.core/let
                [[?star-name ?rule-name] x__30988__auto__]
                (if
                 (clojure.core/vector? input__93840_nth_1__)
                 (clojure.core/loop
                  [search_space__94877
                   (meander.match.runtime.epsilon/partitions
                    2
                    input__93840_nth_1__)]
                  (if
                   (clojure.core/seq search_space__94877)
                   (clojure.core/let
                    [input__93840_nth_1___parts__
                     (clojure.core/first search_space__94877)
                     result__94878
                     (clojure.core/let
                      [input__93840_nth_1___l__
                       (clojure.core/nth
                        input__93840_nth_1___parts__
                        0)
                       input__93840_nth_1___r__
                       (clojure.core/nth
                        input__93840_nth_1___parts__
                        1)]
                      (clojure.core/let
                       [!xs []]
                       (clojure.core/let
                        [ret__31152__auto__
                         (meander.runtime.zeta/epsilon-run-star-1
                          input__93840_nth_1___l__
                          [!xs]
                          (clojure.core/fn
                           [[!xs] input__94126]
                           (clojure.core/let
                            [input__94126_nth_0__
                             (clojure.core/nth input__94126 0)]
                            (clojure.core/letfn
                             [(save__94127
                               []
                               (meander.runtime.zeta/fail))
                              (f__94881
                               []
                               (clojure.core/let
                                [!xs
                                 (clojure.core/conj
                                  !xs
                                  input__94126_nth_0__)]
                                [!xs]))]
                             (if
                              (clojure.core/symbol?
                               input__94126_nth_0__)
                              (clojure.core/let
                               [X__94129
                                (clojure.core/namespace
                                 input__94126_nth_0__)]
                               (clojure.core/case
                                X__94129
                                (nil)
                                (clojure.core/let
                                 [X__94131
                                  (clojure.core/name
                                   input__94126_nth_0__)]
                                 (if
                                  (clojure.core/string? X__94131)
                                  (if
                                   (clojure.core/re-matches
                                    #"\.\.(?:\.|\d+)"
                                    X__94131)
                                   (save__94127)
                                   (f__94881))
                                  (f__94881)))
                                (f__94881)))
                              (f__94881)))))
                          (clojure.core/fn
                           [[!xs]]
                           (clojure.core/let
                            [input__93840_nth_1___r___l__
                             (clojure.core/subvec
                              input__93840_nth_1___r__
                              0
                              (clojure.core/min
                               (clojure.core/count
                                input__93840_nth_1___r__)
                               1))]
                            (if
                             (clojure.core/=
                              (clojure.core/count
                               input__93840_nth_1___r___l__)
                              1)
                             (clojure.core/let
                              [input__93840_nth_1___r___r__
                               (clojure.core/subvec
                                input__93840_nth_1___r__
                                1)]
                              (if
                               (clojure.core/=
                                input__93840_nth_1___r___l__
                                ['...])
                               (clojure.core/let
                                [?rest input__93840_nth_1___r___r__]
                                (clojure.core/let
                                 [?env input__93840_nth_2__]
                                 (try
                                  [(clojure.core/let
                                    [!xs__counter
                                     (meander.runtime.zeta/iterator
                                      !xs)]
                                    (clojure.core/let
                                     [CATA_RESULT__32291__auto__
                                      (CATA__FN__93885
                                       [?star-name
                                        (clojure.core/let
                                         [CATA_RESULT__32291__auto__
                                          (CATA__FN__93885
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
                                          (CATA__FN__93885
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
                         (meander.runtime.zeta/fail?
                          ret__31152__auto__)
                         (meander.runtime.zeta/fail)
                         ret__31152__auto__))))]
                    (if
                     (meander.runtime.zeta/fail? result__94878)
                     (recur (clojure.core/next search_space__94877))
                     result__94878))
                   (state__94817)))
                 (state__94817))))))
            (state__94817))
           (state__94817))))
        (state__94817
         []
         (if
          (clojure.core/vector? input__93840)
          (if
           (clojure.core/= (clojure.core/count input__93840) 3)
           (clojure.core/let
            [input__93840_nth_0__
             (clojure.core/nth input__93840 0)
             input__93840_nth_1__
             (clojure.core/nth input__93840 1)
             input__93840_nth_2__
             (clojure.core/nth input__93840 2)]
            (clojure.core/letfn
             [(state__94882
               []
               (if
                (clojure.core/=
                 input__93840_nth_0__
                 'meander.dev.parse.zeta/star-args)
                (if
                 (clojure.core/map? input__93840_nth_1__)
                 (clojure.core/let
                  [VAL__94147 (.valAt input__93840_nth_1__ :tag)]
                  (clojure.core/case
                   VAL__94147
                   (:cat)
                   (clojure.core/let
                    [VAL__94148
                     (.valAt input__93840_nth_1__ :sequence)]
                    (if
                     (clojure.core/vector? VAL__94148)
                     (if
                      (clojure.core/=
                       (clojure.core/count VAL__94148)
                       1)
                      (clojure.core/let
                       [VAL__94148_nth_0__
                        (clojure.core/nth VAL__94148 0)]
                       (if
                        (clojure.core/map? VAL__94148_nth_0__)
                        (clojure.core/let
                         [VAL__94153 (.valAt VAL__94148_nth_0__ :tag)]
                         (clojure.core/case
                          VAL__94153
                          (:memory-variable)
                          (clojure.core/let
                           [?memory-variable VAL__94148_nth_0__]
                           (clojure.core/let
                            [VAL__94149
                             (.valAt input__93840_nth_1__ :next)]
                            (if
                             (clojure.core/map? VAL__94149)
                             (clojure.core/let
                              [VAL__94150 (.valAt VAL__94149 :tag)]
                              (clojure.core/case
                               VAL__94150
                               (:empty)
                               (clojure.core/let
                                [?next input__93840_nth_2__]
                                (try
                                 [(clojure.core/let
                                   [CATA_RESULT__32291__auto__
                                    (CATA__FN__93885
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
                               (state__94883)))
                             (state__94883))))
                          (state__94883)))
                        (state__94883)))
                      (state__94883))
                     (state__94883)))
                   (state__94883)))
                 (state__94883))
                (state__94883)))
              (state__94883
               []
               (if
                (clojure.core/=
                 input__93840_nth_0__
                 'meander.dev.parse.zeta/star-args)
                (clojure.core/let
                 [?pattern input__93840_nth_1__]
                 (clojure.core/let
                  [?next input__93840_nth_2__]
                  (try
                   [{:tag :star, :pattern ?pattern, :next ?next}]
                   (catch
                    java.lang.Exception
                    e__33231__auto__
                    (if
                     (meander.runtime.zeta/fail? e__33231__auto__)
                     (meander.runtime.zeta/fail)
                     (throw e__33231__auto__))))))
                (state__94884)))
              (state__94884
               []
               (if
                (clojure.core/=
                 input__93840_nth_0__
                 'meander.dev.parse.zeta/string-star-args)
                (if
                 (clojure.core/map? input__93840_nth_1__)
                 (clojure.core/let
                  [VAL__94158 (.valAt input__93840_nth_1__ :tag)]
                  (clojure.core/case
                   VAL__94158
                   (:string-cat)
                   (clojure.core/let
                    [VAL__94159
                     (.valAt input__93840_nth_1__ :sequence)]
                    (if
                     (clojure.core/vector? VAL__94159)
                     (if
                      (clojure.core/=
                       (clojure.core/count VAL__94159)
                       1)
                      (clojure.core/let
                       [VAL__94159_nth_0__
                        (clojure.core/nth VAL__94159 0)]
                       (if
                        (clojure.core/map? VAL__94159_nth_0__)
                        (clojure.core/let
                         [VAL__94164 (.valAt VAL__94159_nth_0__ :tag)]
                         (clojure.core/case
                          VAL__94164
                          (:memory-variable)
                          (clojure.core/let
                           [?memory-variable VAL__94159_nth_0__]
                           (clojure.core/let
                            [VAL__94160
                             (.valAt input__93840_nth_1__ :next)]
                            (if
                             (clojure.core/map? VAL__94160)
                             (clojure.core/let
                              [VAL__94161 (.valAt VAL__94160 :tag)]
                              (clojure.core/case
                               VAL__94161
                               (:empty)
                               (clojure.core/let
                                [?next input__93840_nth_2__]
                                (try
                                 [(clojure.core/let
                                   [CATA_RESULT__32291__auto__
                                    (CATA__FN__93885
                                     ['meander.dev.parse.zeta/string-join-args
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
                               (state__94885)))
                             (state__94885))))
                          (state__94885)))
                        (state__94885)))
                      (state__94885))
                     (state__94885)))
                   (state__94885)))
                 (state__94885))
                (state__94885)))
              (state__94885
               []
               (if
                (clojure.core/=
                 input__93840_nth_0__
                 'meander.dev.parse.zeta/string-star-args)
                (clojure.core/let
                 [?pattern input__93840_nth_1__]
                 (clojure.core/let
                  [?next input__93840_nth_2__]
                  (try
                   [{:tag :string-star,
                     :pattern ?pattern,
                     :next ?next}]
                   (catch
                    java.lang.Exception
                    e__33231__auto__
                    (if
                     (meander.runtime.zeta/fail? e__33231__auto__)
                     (meander.runtime.zeta/fail)
                     (throw e__33231__auto__))))))
                (state__94886)))
              (state__94886
               []
               (if
                (clojure.core/let
                 [x__30048__auto__ input__93840_nth_0__]
                 (clojure.core/case
                  x__30048__auto__
                  (meander.dev.parse.zeta/parse-seq
                   meander.dev.parse.zeta/parse-string)
                  true
                  false))
                (clojure.core/letfn
                 [(state__94906
                   []
                   (if
                    (clojure.core/vector? input__93840_nth_1__)
                    (clojure.core/let
                     [input__93840_nth_1___l__
                      (clojure.core/subvec
                       input__93840_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__93840_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__93840_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__93840_nth_1___r__
                        (clojure.core/subvec input__93840_nth_1__ 1)]
                       (clojure.core/let
                        [input__93840_nth_1___l___nth_0__
                         (clojure.core/nth input__93840_nth_1___l__ 0)]
                        (if
                         (clojure.core/symbol?
                          input__93840_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__94172
                           (clojure.core/namespace
                            input__93840_nth_1___l___nth_0__)]
                          (clojure.core/case
                           X__94172
                           (nil)
                           (clojure.core/let
                            [X__94174
                             (clojure.core/name
                              input__93840_nth_1___l___nth_0__)]
                            (if
                             (clojure.core/string? X__94174)
                             (clojure.core/let
                              [ret__94175
                               (clojure.core/re-matches
                                #"\.\.(\d+)"
                                X__94174)]
                              (if
                               (clojure.core/some? ret__94175)
                               (if
                                (clojure.core/vector? ret__94175)
                                (if
                                 (clojure.core/=
                                  (clojure.core/count ret__94175)
                                  2)
                                 (clojure.core/let
                                  [ret__94175_nth_1__
                                   (clojure.core/nth ret__94175 1)]
                                  (clojure.core/let
                                   [?n ret__94175_nth_1__]
                                   (clojure.core/let
                                    [?operator
                                     input__93840_nth_1___l___nth_0__]
                                    (clojure.core/let
                                     [?rest input__93840_nth_1___r__]
                                     (clojure.core/let
                                      [?env input__93840_nth_2__]
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
                                 (state__94907))
                                (state__94907))
                               (state__94907)))
                             (state__94907)))
                           (state__94907)))
                         (state__94907))))
                      (state__94907)))
                    (state__94907)))
                  (state__94907
                   []
                   (clojure.core/let
                    [?rule-name input__93840_nth_0__]
                    (if
                     (clojure.core/vector? input__93840_nth_1__)
                     (clojure.core/loop
                      [search_space__94912
                       (meander.match.runtime.epsilon/partitions
                        2
                        input__93840_nth_1__)]
                      (if
                       (clojure.core/seq search_space__94912)
                       (clojure.core/let
                        [input__93840_nth_1___parts__
                         (clojure.core/first search_space__94912)
                         result__94913
                         (clojure.core/let
                          [input__93840_nth_1___l__
                           (clojure.core/nth
                            input__93840_nth_1___parts__
                            0)
                           input__93840_nth_1___r__
                           (clojure.core/nth
                            input__93840_nth_1___parts__
                            1)]
                          (clojure.core/let
                           [!xs []]
                           (clojure.core/let
                            [ret__31152__auto__
                             (meander.runtime.zeta/epsilon-run-star-1
                              input__93840_nth_1___l__
                              [!xs]
                              (clojure.core/fn
                               [[!xs] input__94191]
                               (clojure.core/let
                                [input__94191_nth_0__
                                 (clojure.core/nth input__94191 0)]
                                (clojure.core/letfn
                                 [(save__94192
                                   []
                                   (meander.runtime.zeta/fail))
                                  (f__94916
                                   []
                                   (clojure.core/let
                                    [!xs
                                     (clojure.core/conj
                                      !xs
                                      input__94191_nth_0__)]
                                    [!xs]))]
                                 (if
                                  (clojure.core/symbol?
                                   input__94191_nth_0__)
                                  (clojure.core/let
                                   [X__94194
                                    (clojure.core/namespace
                                     input__94191_nth_0__)]
                                   (clojure.core/case
                                    X__94194
                                    (nil)
                                    (clojure.core/let
                                     [X__94196
                                      (clojure.core/name
                                       input__94191_nth_0__)]
                                     (if
                                      (clojure.core/string? X__94196)
                                      (if
                                       (clojure.core/re-matches
                                        #"\.\.(?:\.|\d+)"
                                        X__94196)
                                       (save__94192)
                                       (f__94916))
                                      (f__94916)))
                                    (f__94916)))
                                  (f__94916)))))
                              (clojure.core/fn
                               [[!xs]]
                               (clojure.core/let
                                [input__93840_nth_1___r___l__
                                 (clojure.core/subvec
                                  input__93840_nth_1___r__
                                  0
                                  (clojure.core/min
                                   (clojure.core/count
                                    input__93840_nth_1___r__)
                                   1))]
                                (if
                                 (clojure.core/=
                                  (clojure.core/count
                                   input__93840_nth_1___r___l__)
                                  1)
                                 (clojure.core/let
                                  [input__93840_nth_1___r___r__
                                   (clojure.core/subvec
                                    input__93840_nth_1___r__
                                    1)]
                                  (clojure.core/let
                                   [input__93840_nth_1___r___l___nth_0__
                                    (clojure.core/nth
                                     input__93840_nth_1___r___l__
                                     0)]
                                   (if
                                    (clojure.core/symbol?
                                     input__93840_nth_1___r___l___nth_0__)
                                    (clojure.core/let
                                     [X__94185
                                      (clojure.core/namespace
                                       input__93840_nth_1___r___l___nth_0__)]
                                     (clojure.core/case
                                      X__94185
                                      (nil)
                                      (clojure.core/let
                                       [X__94187
                                        (clojure.core/name
                                         input__93840_nth_1___r___l___nth_0__)]
                                       (if
                                        (clojure.core/string? X__94187)
                                        (clojure.core/let
                                         [ret__94188
                                          (clojure.core/re-matches
                                           #"\.\.(\d+)"
                                           X__94187)]
                                         (if
                                          (clojure.core/some?
                                           ret__94188)
                                          (if
                                           (clojure.core/vector?
                                            ret__94188)
                                           (if
                                            (clojure.core/=
                                             (clojure.core/count
                                              ret__94188)
                                             2)
                                            (clojure.core/let
                                             [ret__94188_nth_1__
                                              (clojure.core/nth
                                               ret__94188
                                               1)]
                                             (clojure.core/let
                                              [?n ret__94188_nth_1__]
                                              (clojure.core/let
                                               [?rest
                                                input__93840_nth_1___r___r__]
                                               (clojure.core/let
                                                [?env
                                                 input__93840_nth_2__]
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
                                                      (CATA__FN__93885
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
                                                      (CATA__FN__93885
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
                         (meander.runtime.zeta/fail? result__94913)
                         (recur
                          (clojure.core/next search_space__94912))
                         result__94913))
                       (state__94908)))
                     (state__94908))))
                  (state__94908
                   []
                   (if
                    (clojure.core/vector? input__93840_nth_1__)
                    (clojure.core/let
                     [input__93840_nth_1___l__
                      (clojure.core/subvec
                       input__93840_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__93840_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__93840_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__93840_nth_1___r__
                        (clojure.core/subvec input__93840_nth_1__ 1)]
                       (clojure.core/let
                        [input__93840_nth_1___l___nth_0__
                         (clojure.core/nth input__93840_nth_1___l__ 0)]
                        (if
                         (clojure.core/symbol?
                          input__93840_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__94203
                           (clojure.core/namespace
                            input__93840_nth_1___l___nth_0__)]
                          (clojure.core/case
                           X__94203
                           (nil)
                           (clojure.core/let
                            [X__94205
                             (clojure.core/name
                              input__93840_nth_1___l___nth_0__)]
                            (if
                             (clojure.core/string? X__94205)
                             (clojure.core/let
                              [ret__94206
                               (clojure.core/re-matches
                                #"\.\.(\?.+)"
                                X__94205)]
                              (if
                               (clojure.core/some? ret__94206)
                               (if
                                (clojure.core/vector? ret__94206)
                                (if
                                 (clojure.core/=
                                  (clojure.core/count ret__94206)
                                  2)
                                 (clojure.core/let
                                  [ret__94206_nth_1__
                                   (clojure.core/nth ret__94206 1)]
                                  (clojure.core/let
                                   [?n ret__94206_nth_1__]
                                   (clojure.core/let
                                    [?operator
                                     input__93840_nth_1___l___nth_0__]
                                    (clojure.core/let
                                     [?rest input__93840_nth_1___r__]
                                     (clojure.core/let
                                      [?env input__93840_nth_2__]
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
                                 (state__94909))
                                (state__94909))
                               (state__94909)))
                             (state__94909)))
                           (state__94909)))
                         (state__94909))))
                      (state__94909)))
                    (state__94909)))
                  (state__94909
                   []
                   (clojure.core/let
                    [?rule-name input__93840_nth_0__]
                    (if
                     (clojure.core/vector? input__93840_nth_1__)
                     (clojure.core/loop
                      [search_space__94917
                       (meander.match.runtime.epsilon/partitions
                        2
                        input__93840_nth_1__)]
                      (if
                       (clojure.core/seq search_space__94917)
                       (clojure.core/let
                        [input__93840_nth_1___parts__
                         (clojure.core/first search_space__94917)
                         result__94918
                         (clojure.core/let
                          [input__93840_nth_1___l__
                           (clojure.core/nth
                            input__93840_nth_1___parts__
                            0)
                           input__93840_nth_1___r__
                           (clojure.core/nth
                            input__93840_nth_1___parts__
                            1)]
                          (clojure.core/let
                           [!xs []]
                           (clojure.core/let
                            [ret__31152__auto__
                             (meander.runtime.zeta/epsilon-run-star-1
                              input__93840_nth_1___l__
                              [!xs]
                              (clojure.core/fn
                               [[!xs] input__94222]
                               (clojure.core/let
                                [input__94222_nth_0__
                                 (clojure.core/nth input__94222 0)]
                                (clojure.core/letfn
                                 [(save__94223
                                   []
                                   (meander.runtime.zeta/fail))
                                  (f__94921
                                   []
                                   (clojure.core/let
                                    [!xs
                                     (clojure.core/conj
                                      !xs
                                      input__94222_nth_0__)]
                                    [!xs]))]
                                 (if
                                  (clojure.core/symbol?
                                   input__94222_nth_0__)
                                  (clojure.core/let
                                   [X__94225
                                    (clojure.core/namespace
                                     input__94222_nth_0__)]
                                   (clojure.core/case
                                    X__94225
                                    (nil)
                                    (clojure.core/let
                                     [X__94227
                                      (clojure.core/name
                                       input__94222_nth_0__)]
                                     (if
                                      (clojure.core/string? X__94227)
                                      (if
                                       (clojure.core/re-matches
                                        #"\.\.(?:\.|\d+)"
                                        X__94227)
                                       (save__94223)
                                       (f__94921))
                                      (f__94921)))
                                    (f__94921)))
                                  (f__94921)))))
                              (clojure.core/fn
                               [[!xs]]
                               (clojure.core/let
                                [input__93840_nth_1___r___l__
                                 (clojure.core/subvec
                                  input__93840_nth_1___r__
                                  0
                                  (clojure.core/min
                                   (clojure.core/count
                                    input__93840_nth_1___r__)
                                   1))]
                                (if
                                 (clojure.core/=
                                  (clojure.core/count
                                   input__93840_nth_1___r___l__)
                                  1)
                                 (clojure.core/let
                                  [input__93840_nth_1___r___r__
                                   (clojure.core/subvec
                                    input__93840_nth_1___r__
                                    1)]
                                  (clojure.core/let
                                   [input__93840_nth_1___r___l___nth_0__
                                    (clojure.core/nth
                                     input__93840_nth_1___r___l__
                                     0)]
                                   (if
                                    (clojure.core/symbol?
                                     input__93840_nth_1___r___l___nth_0__)
                                    (clojure.core/let
                                     [X__94216
                                      (clojure.core/namespace
                                       input__93840_nth_1___r___l___nth_0__)]
                                     (clojure.core/case
                                      X__94216
                                      (nil)
                                      (clojure.core/let
                                       [X__94218
                                        (clojure.core/name
                                         input__93840_nth_1___r___l___nth_0__)]
                                       (if
                                        (clojure.core/string? X__94218)
                                        (clojure.core/let
                                         [ret__94219
                                          (clojure.core/re-matches
                                           #"\.\.(\?.+)"
                                           X__94218)]
                                         (if
                                          (clojure.core/some?
                                           ret__94219)
                                          (if
                                           (clojure.core/vector?
                                            ret__94219)
                                           (if
                                            (clojure.core/=
                                             (clojure.core/count
                                              ret__94219)
                                             2)
                                            (clojure.core/let
                                             [ret__94219_nth_1__
                                              (clojure.core/nth
                                               ret__94219
                                               1)]
                                             (clojure.core/let
                                              [?n ret__94219_nth_1__]
                                              (clojure.core/let
                                               [?rest
                                                input__93840_nth_1___r___r__]
                                               (clojure.core/let
                                                [?env
                                                 input__93840_nth_2__]
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
                                                      (CATA__FN__93885
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
                                                      (CATA__FN__93885
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
                         (meander.runtime.zeta/fail? result__94918)
                         (recur
                          (clojure.core/next search_space__94917))
                         result__94918))
                       (state__94910)))
                     (state__94910))))
                  (state__94910
                   []
                   (if
                    (clojure.core/vector? input__93840_nth_1__)
                    (clojure.core/let
                     [input__93840_nth_1___l__
                      (clojure.core/subvec
                       input__93840_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__93840_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__93840_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__93840_nth_1___r__
                        (clojure.core/subvec input__93840_nth_1__ 1)]
                       (clojure.core/let
                        [input__93840_nth_1___l___nth_0__
                         (clojure.core/nth input__93840_nth_1___l__ 0)]
                        (if
                         (clojure.core/symbol?
                          input__93840_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__94234
                           (clojure.core/namespace
                            input__93840_nth_1___l___nth_0__)]
                          (clojure.core/case
                           X__94234
                           (nil)
                           (clojure.core/let
                            [X__94236
                             (clojure.core/name
                              input__93840_nth_1___l___nth_0__)]
                            (if
                             (clojure.core/string? X__94236)
                             (clojure.core/let
                              [ret__94237
                               (clojure.core/re-matches
                                #"\.\.(!.+)"
                                X__94236)]
                              (if
                               (clojure.core/some? ret__94237)
                               (if
                                (clojure.core/vector? ret__94237)
                                (if
                                 (clojure.core/=
                                  (clojure.core/count ret__94237)
                                  2)
                                 (clojure.core/let
                                  [ret__94237_nth_1__
                                   (clojure.core/nth ret__94237 1)]
                                  (clojure.core/let
                                   [?n ret__94237_nth_1__]
                                   (clojure.core/let
                                    [?operator
                                     input__93840_nth_1___l___nth_0__]
                                    (clojure.core/let
                                     [?rest input__93840_nth_1___r__]
                                     (clojure.core/let
                                      [?env input__93840_nth_2__]
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
                                 (state__94911))
                                (state__94911))
                               (state__94911)))
                             (state__94911)))
                           (state__94911)))
                         (state__94911))))
                      (state__94911)))
                    (state__94911)))
                  (state__94911
                   []
                   (clojure.core/let
                    [?rule-name input__93840_nth_0__]
                    (if
                     (clojure.core/vector? input__93840_nth_1__)
                     (clojure.core/loop
                      [search_space__94922
                       (meander.match.runtime.epsilon/partitions
                        2
                        input__93840_nth_1__)]
                      (if
                       (clojure.core/seq search_space__94922)
                       (clojure.core/let
                        [input__93840_nth_1___parts__
                         (clojure.core/first search_space__94922)
                         result__94923
                         (clojure.core/let
                          [input__93840_nth_1___l__
                           (clojure.core/nth
                            input__93840_nth_1___parts__
                            0)
                           input__93840_nth_1___r__
                           (clojure.core/nth
                            input__93840_nth_1___parts__
                            1)]
                          (clojure.core/let
                           [!xs []]
                           (clojure.core/let
                            [ret__31152__auto__
                             (meander.runtime.zeta/epsilon-run-star-1
                              input__93840_nth_1___l__
                              [!xs]
                              (clojure.core/fn
                               [[!xs] input__94253]
                               (clojure.core/let
                                [input__94253_nth_0__
                                 (clojure.core/nth input__94253 0)]
                                (clojure.core/letfn
                                 [(save__94254
                                   []
                                   (meander.runtime.zeta/fail))
                                  (f__94926
                                   []
                                   (clojure.core/let
                                    [!xs
                                     (clojure.core/conj
                                      !xs
                                      input__94253_nth_0__)]
                                    [!xs]))]
                                 (if
                                  (clojure.core/symbol?
                                   input__94253_nth_0__)
                                  (clojure.core/let
                                   [X__94256
                                    (clojure.core/namespace
                                     input__94253_nth_0__)]
                                   (clojure.core/case
                                    X__94256
                                    (nil)
                                    (clojure.core/let
                                     [X__94258
                                      (clojure.core/name
                                       input__94253_nth_0__)]
                                     (if
                                      (clojure.core/string? X__94258)
                                      (if
                                       (clojure.core/re-matches
                                        #"\.\.(?:\.|\d+)"
                                        X__94258)
                                       (save__94254)
                                       (f__94926))
                                      (f__94926)))
                                    (f__94926)))
                                  (f__94926)))))
                              (clojure.core/fn
                               [[!xs]]
                               (clojure.core/let
                                [input__93840_nth_1___r___l__
                                 (clojure.core/subvec
                                  input__93840_nth_1___r__
                                  0
                                  (clojure.core/min
                                   (clojure.core/count
                                    input__93840_nth_1___r__)
                                   1))]
                                (if
                                 (clojure.core/=
                                  (clojure.core/count
                                   input__93840_nth_1___r___l__)
                                  1)
                                 (clojure.core/let
                                  [input__93840_nth_1___r___r__
                                   (clojure.core/subvec
                                    input__93840_nth_1___r__
                                    1)]
                                  (clojure.core/let
                                   [input__93840_nth_1___r___l___nth_0__
                                    (clojure.core/nth
                                     input__93840_nth_1___r___l__
                                     0)]
                                   (if
                                    (clojure.core/symbol?
                                     input__93840_nth_1___r___l___nth_0__)
                                    (clojure.core/let
                                     [X__94247
                                      (clojure.core/namespace
                                       input__93840_nth_1___r___l___nth_0__)]
                                     (clojure.core/case
                                      X__94247
                                      (nil)
                                      (clojure.core/let
                                       [X__94249
                                        (clojure.core/name
                                         input__93840_nth_1___r___l___nth_0__)]
                                       (if
                                        (clojure.core/string? X__94249)
                                        (clojure.core/let
                                         [ret__94250
                                          (clojure.core/re-matches
                                           #"\.\.(\!.+)"
                                           X__94249)]
                                         (if
                                          (clojure.core/some?
                                           ret__94250)
                                          (if
                                           (clojure.core/vector?
                                            ret__94250)
                                           (if
                                            (clojure.core/=
                                             (clojure.core/count
                                              ret__94250)
                                             2)
                                            (clojure.core/let
                                             [ret__94250_nth_1__
                                              (clojure.core/nth
                                               ret__94250
                                               1)]
                                             (clojure.core/let
                                              [?n ret__94250_nth_1__]
                                              (clojure.core/let
                                               [?rest
                                                input__93840_nth_1___r___r__]
                                               (clojure.core/let
                                                [?env
                                                 input__93840_nth_2__]
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
                                                      (CATA__FN__93885
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
                                                      (CATA__FN__93885
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
                         (meander.runtime.zeta/fail? result__94923)
                         (recur
                          (clojure.core/next search_space__94922))
                         result__94923))
                       (state__94887)))
                     (state__94887))))]
                 (state__94906))
                (state__94887)))
              (state__94887
               []
               (if
                (clojure.core/=
                 input__93840_nth_0__
                 'meander.dev.parse.zeta/parse-seq)
                (if
                 (clojure.core/vector? input__93840_nth_1__)
                 (clojure.core/let
                  [!xs (clojure.core/vec input__93840_nth_1__)]
                  (clojure.core/let
                   [?env input__93840_nth_2__]
                   (try
                    [(clojure.core/let
                      [!xs__counter
                       (meander.runtime.zeta/iterator !xs)]
                      (clojure.core/let
                       [CATA_RESULT__32291__auto__
                        (CATA__FN__93885
                         ['meander.dev.parse.zeta/cat-args
                          (clojure.core/into
                           []
                           (clojure.core/loop
                            [return__93886 (clojure.core/transient [])]
                            (if
                             (clojure.core/and (.hasNext !xs__counter))
                             (recur
                              (clojure.core/conj!
                               return__93886
                               (clojure.core/let
                                [CATA_RESULT__32291__auto__
                                 (CATA__FN__93885
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
                              return__93886))))
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
                 (state__94888))
                (state__94888)))
              (state__94888
               []
               (if
                (clojure.core/=
                 input__93840_nth_0__
                 'meander.dev.parse.zeta/parse-string)
                (if
                 (clojure.core/vector? input__93840_nth_1__)
                 (clojure.core/let
                  [!xs (clojure.core/vec input__93840_nth_1__)]
                  (clojure.core/let
                   [?env input__93840_nth_2__]
                   (try
                    [(clojure.core/let
                      [!xs__counter
                       (meander.runtime.zeta/iterator !xs)]
                      (clojure.core/let
                       [CATA_RESULT__32291__auto__
                        (CATA__FN__93885
                         ['meander.dev.parse.zeta/string-cat-args
                          (clojure.core/into
                           []
                           (clojure.core/loop
                            [return__93887 (clojure.core/transient [])]
                            (if
                             (clojure.core/and (.hasNext !xs__counter))
                             (recur
                              (clojure.core/conj!
                               return__93887
                               (clojure.core/let
                                [CATA_RESULT__32291__auto__
                                 (CATA__FN__93885
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
                              return__93887))))
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
                 (state__94889))
                (state__94889)))
              (state__94889
               []
               (if
                (clojure.core/=
                 input__93840_nth_0__
                 'meander.dev.parse.zeta/cat-args)
                (if
                 (clojure.core/vector? input__93840_nth_1__)
                 (clojure.core/let
                  [!forms []]
                  (clojure.core/let
                   [ret__31152__auto__
                    (meander.runtime.zeta/epsilon-run-star-1
                     input__93840_nth_1__
                     [!forms]
                     (clojure.core/fn
                      [[!forms] input__94273]
                      (clojure.core/let
                       [input__94273_nth_0__
                        (clojure.core/nth input__94273 0)]
                       (if
                        (clojure.core/map? input__94273_nth_0__)
                        (clojure.core/let
                         [VAL__94274
                          (.valAt input__94273_nth_0__ :tag)]
                         (clojure.core/case
                          VAL__94274
                          (:literal)
                          (clojure.core/let
                           [VAL__94275
                            (.valAt input__94273_nth_0__ :form)]
                           (clojure.core/let
                            [!forms
                             (clojure.core/conj !forms VAL__94275)]
                            [!forms]))
                          (meander.runtime.zeta/fail)))
                        (meander.runtime.zeta/fail))))
                     (clojure.core/fn
                      [[!forms]]
                      (if
                       (clojure.core/map? input__93840_nth_2__)
                       (clojure.core/let
                        [VAL__94270 (.valAt input__93840_nth_2__ :tag)]
                        (clojure.core/case
                         VAL__94270
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
                         (state__94890)))
                       (state__94890))))]
                   (if
                    (meander.runtime.zeta/fail? ret__31152__auto__)
                    (state__94890)
                    ret__31152__auto__)))
                 (state__94890))
                (state__94890)))
              (state__94890
               []
               (if
                (clojure.core/=
                 input__93840_nth_0__
                 'meander.dev.parse.zeta/cat-args)
                (clojure.core/let
                 [?sequence input__93840_nth_1__]
                 (clojure.core/let
                  [?next input__93840_nth_2__]
                  (try
                   [{:tag :cat, :sequence ?sequence, :next ?next}]
                   (catch
                    java.lang.Exception
                    e__33231__auto__
                    (if
                     (meander.runtime.zeta/fail? e__33231__auto__)
                     (meander.runtime.zeta/fail)
                     (throw e__33231__auto__))))))
                (state__94891)))
              (state__94891
               []
               (if
                (clojure.core/=
                 input__93840_nth_0__
                 'meander.dev.parse.zeta/join-args)
                (if
                 (clojure.core/map? input__93840_nth_1__)
                 (clojure.core/let
                  [VAL__94281 (.valAt input__93840_nth_1__ :tag)]
                  (clojure.core/case
                   VAL__94281
                   (:cat)
                   (clojure.core/let
                    [VAL__94282
                     (.valAt input__93840_nth_1__ :sequence)]
                    (clojure.core/let
                     [?sequence VAL__94282]
                     (clojure.core/let
                      [VAL__94283 (.valAt input__93840_nth_1__ :next)]
                      (if
                       (clojure.core/map? VAL__94283)
                       (clojure.core/let
                        [VAL__94284 (.valAt VAL__94283 :tag)]
                        (clojure.core/case
                         VAL__94284
                         (:empty)
                         (clojure.core/let
                          [?right input__93840_nth_2__]
                          (try
                           [(clojure.core/let
                             [CATA_RESULT__32291__auto__
                              (CATA__FN__93885
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
                         (state__94892)))
                       (state__94892)))))
                   (state__94892)))
                 (state__94892))
                (state__94892)))
              (state__94892
               []
               (if
                (clojure.core/=
                 input__93840_nth_0__
                 'meander.dev.parse.zeta/join-args)
                (clojure.core/let
                 [?left input__93840_nth_1__]
                 (if
                  (clojure.core/map? input__93840_nth_2__)
                  (clojure.core/let
                   [VAL__94287 (.valAt input__93840_nth_2__ :tag)]
                   (clojure.core/case
                    VAL__94287
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
                    (state__94893)))
                  (state__94893)))
                (state__94893)))
              (state__94893
               []
               (if
                (clojure.core/=
                 input__93840_nth_0__
                 'meander.dev.parse.zeta/join-args)
                (if
                 (clojure.core/map? input__93840_nth_1__)
                 (clojure.core/let
                  [VAL__94290 (.valAt input__93840_nth_1__ :tag)]
                  (clojure.core/case
                   VAL__94290
                   (:empty)
                   (clojure.core/let
                    [?right input__93840_nth_2__]
                    (try
                     [?right]
                     (catch
                      java.lang.Exception
                      e__33231__auto__
                      (if
                       (meander.runtime.zeta/fail? e__33231__auto__)
                       (meander.runtime.zeta/fail)
                       (throw e__33231__auto__)))))
                   (state__94894)))
                 (state__94894))
                (state__94894)))
              (state__94894
               []
               (if
                (clojure.core/=
                 input__93840_nth_0__
                 'meander.dev.parse.zeta/join-args)
                (clojure.core/let
                 [?left input__93840_nth_1__]
                 (clojure.core/let
                  [?right input__93840_nth_2__]
                  (try
                   [{:tag :join, :left ?left, :right ?right}]
                   (catch
                    java.lang.Exception
                    e__33231__auto__
                    (if
                     (meander.runtime.zeta/fail? e__33231__auto__)
                     (meander.runtime.zeta/fail)
                     (throw e__33231__auto__))))))
                (state__94895)))
              (state__94895
               []
               (if
                (clojure.core/=
                 input__93840_nth_0__
                 'meander.dev.parse.zeta/string-cat-args)
                (if
                 (clojure.core/vector? input__93840_nth_1__)
                 (clojure.core/let
                  [!forms []]
                  (clojure.core/let
                   [ret__31152__auto__
                    (meander.runtime.zeta/epsilon-run-star-1
                     input__93840_nth_1__
                     [!forms]
                     (clojure.core/fn
                      [[!forms] input__94298]
                      (clojure.core/let
                       [input__94298_nth_0__
                        (clojure.core/nth input__94298 0)]
                       (if
                        (clojure.core/map? input__94298_nth_0__)
                        (clojure.core/let
                         [VAL__94299
                          (.valAt input__94298_nth_0__ :tag)]
                         (clojure.core/case
                          VAL__94299
                          (:literal)
                          (clojure.core/let
                           [VAL__94300
                            (.valAt input__94298_nth_0__ :type)]
                           (if
                            (clojure.core/let
                             [x__30048__auto__ VAL__94300]
                             (clojure.core/case
                              x__30048__auto__
                              (:string :char)
                              true
                              false))
                            (clojure.core/let
                             [VAL__94301
                              (.valAt input__94298_nth_0__ :form)]
                             (clojure.core/let
                              [!forms
                               (clojure.core/conj !forms VAL__94301)]
                              [!forms]))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))
                        (meander.runtime.zeta/fail))))
                     (clojure.core/fn
                      [[!forms]]
                      (if
                       (clojure.core/map? input__93840_nth_2__)
                       (clojure.core/let
                        [VAL__94295 (.valAt input__93840_nth_2__ :tag)]
                        (clojure.core/case
                         VAL__94295
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
                         (state__94896)))
                       (state__94896))))]
                   (if
                    (meander.runtime.zeta/fail? ret__31152__auto__)
                    (state__94896)
                    ret__31152__auto__)))
                 (state__94896))
                (state__94896)))
              (state__94896
               []
               (if
                (clojure.core/=
                 input__93840_nth_0__
                 'meander.dev.parse.zeta/string-cat-args)
                (if
                 (clojure.core/vector? input__93840_nth_1__)
                 (clojure.core/let
                  [input__93840_nth_1___l__
                   (clojure.core/subvec
                    input__93840_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__93840_nth_1__)
                     1))]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__93840_nth_1___l__)
                    1)
                   (clojure.core/let
                    [input__93840_nth_1___r__
                     (clojure.core/subvec input__93840_nth_1__ 1)]
                    (clojure.core/let
                     [input__93840_nth_1___l___nth_0__
                      (clojure.core/nth input__93840_nth_1___l__ 0)]
                     (if
                      (clojure.core/map?
                       input__93840_nth_1___l___nth_0__)
                      (clojure.core/let
                       [VAL__94307
                        (.valAt input__93840_nth_1___l___nth_0__ :tag)]
                       (clojure.core/case
                        VAL__94307
                        (:literal)
                        (clojure.core/let
                         [VAL__94308
                          (.valAt
                           input__93840_nth_1___l___nth_0__
                           :type)]
                         (clojure.core/case
                          VAL__94308
                          (:string)
                          (clojure.core/let
                           [?ast input__93840_nth_1___l___nth_0__]
                           (clojure.core/let
                            [?rest input__93840_nth_1___r__]
                            (clojure.core/let
                             [?next input__93840_nth_2__]
                             (try
                              [(clojure.core/let
                                [CATA_RESULT__32291__auto__
                                 (CATA__FN__93885
                                  ['meander.dev.parse.zeta/string-join-args
                                   ?ast
                                   (clojure.core/let
                                    [CATA_RESULT__32291__auto__
                                     (CATA__FN__93885
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
                          (state__94897)))
                        (state__94897)))
                      (state__94897))))
                   (state__94897)))
                 (state__94897))
                (state__94897)))
              (state__94897
               []
               (if
                (clojure.core/=
                 input__93840_nth_0__
                 'meander.dev.parse.zeta/string-cat-args)
                (clojure.core/let
                 [?sequence input__93840_nth_1__]
                 (clojure.core/let
                  [?next input__93840_nth_2__]
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
                (state__94898)))
              (state__94898
               []
               (if
                (clojure.core/=
                 input__93840_nth_0__
                 'meander.dev.parse.zeta/string-join-args)
                (if
                 (clojure.core/map? input__93840_nth_1__)
                 (clojure.core/let
                  [VAL__94313 (.valAt input__93840_nth_1__ :tag)]
                  (clojure.core/case
                   VAL__94313
                   (:literal)
                   (clojure.core/let
                    [VAL__94314 (.valAt input__93840_nth_1__ :type)]
                    (clojure.core/case
                     VAL__94314
                     (:string)
                     (clojure.core/let
                      [VAL__94315 (.valAt input__93840_nth_1__ :form)]
                      (clojure.core/let
                       [?form-1 VAL__94315]
                       (if
                        (clojure.core/map? input__93840_nth_2__)
                        (clojure.core/let
                         [VAL__94316
                          (.valAt input__93840_nth_2__ :tag)]
                         (clojure.core/case
                          VAL__94316
                          (:string-join)
                          (clojure.core/let
                           [VAL__94317
                            (.valAt input__93840_nth_2__ :left)]
                           (if
                            (clojure.core/map? VAL__94317)
                            (clojure.core/let
                             [VAL__94318 (.valAt VAL__94317 :tag)]
                             (clojure.core/case
                              VAL__94318
                              (:literal)
                              (clojure.core/let
                               [VAL__94319 (.valAt VAL__94317 :type)]
                               (clojure.core/case
                                VAL__94319
                                (:string)
                                (clojure.core/let
                                 [VAL__94320 (.valAt VAL__94317 :form)]
                                 (clojure.core/let
                                  [?form-2 VAL__94320]
                                  (clojure.core/let
                                   [VAL__94321
                                    (.valAt
                                     input__93840_nth_2__
                                     :right)]
                                   (clojure.core/let
                                    [?right VAL__94321]
                                    (try
                                     [(clojure.core/let
                                       [CATA_RESULT__32291__auto__
                                        (CATA__FN__93885
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
                                (state__94899)))
                              (state__94899)))
                            (state__94899)))
                          (state__94899)))
                        (state__94899))))
                     (state__94899)))
                   (state__94899)))
                 (state__94899))
                (state__94899)))
              (state__94899
               []
               (if
                (clojure.core/=
                 input__93840_nth_0__
                 'meander.dev.parse.zeta/string-join-args)
                (if
                 (clojure.core/map? input__93840_nth_1__)
                 (clojure.core/let
                  [VAL__94324 (.valAt input__93840_nth_1__ :tag)]
                  (clojure.core/case
                   VAL__94324
                   (:literal)
                   (clojure.core/let
                    [VAL__94325 (.valAt input__93840_nth_1__ :type)]
                    (clojure.core/case
                     VAL__94325
                     (:string)
                     (clojure.core/let
                      [?ast input__93840_nth_1__]
                      (if
                       (clojure.core/map? input__93840_nth_2__)
                       (clojure.core/let
                        [VAL__94326 (.valAt input__93840_nth_2__ :tag)]
                        (clojure.core/case
                         VAL__94326
                         (:string-cat)
                         (clojure.core/let
                          [VAL__94327
                           (.valAt input__93840_nth_2__ :sequence)]
                          (clojure.core/let
                           [?sequence VAL__94327]
                           (clojure.core/let
                            [VAL__94328
                             (.valAt input__93840_nth_2__ :next)]
                            (clojure.core/let
                             [?next VAL__94328]
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
                         (state__94900)))
                       (state__94900)))
                     (state__94900)))
                   (state__94900)))
                 (state__94900))
                (state__94900)))
              (state__94900
               []
               (if
                (clojure.core/=
                 input__93840_nth_0__
                 'meander.dev.parse.zeta/string-join-args)
                (if
                 (clojure.core/map? input__93840_nth_1__)
                 (clojure.core/let
                  [VAL__94331 (.valAt input__93840_nth_1__ :tag)]
                  (clojure.core/case
                   VAL__94331
                   (:string-cat)
                   (clojure.core/let
                    [VAL__94332
                     (.valAt input__93840_nth_1__ :sequence)]
                    (clojure.core/let
                     [?sequence VAL__94332]
                     (clojure.core/let
                      [VAL__94333 (.valAt input__93840_nth_1__ :next)]
                      (if
                       (clojure.core/map? VAL__94333)
                       (clojure.core/let
                        [VAL__94334 (.valAt VAL__94333 :tag)]
                        (clojure.core/case
                         VAL__94334
                         (:empty)
                         (clojure.core/let
                          [?right input__93840_nth_2__]
                          (try
                           [(clojure.core/let
                             [CATA_RESULT__32291__auto__
                              (CATA__FN__93885
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
                         (state__94901)))
                       (state__94901)))))
                   (state__94901)))
                 (state__94901))
                (state__94901)))
              (state__94901
               []
               (if
                (clojure.core/=
                 input__93840_nth_0__
                 'meander.dev.parse.zeta/string-join-args)
                (if
                 (clojure.core/map? input__93840_nth_1__)
                 (clojure.core/let
                  [VAL__94337 (.valAt input__93840_nth_1__ :tag)]
                  (clojure.core/case
                   VAL__94337
                   (:string-star)
                   (clojure.core/let
                    [VAL__94338 (.valAt input__93840_nth_1__ :pattern)]
                    (clojure.core/let
                     [?pattern VAL__94338]
                     (clojure.core/let
                      [VAL__94339 (.valAt input__93840_nth_1__ :next)]
                      (if
                       (clojure.core/map? VAL__94339)
                       (clojure.core/let
                        [VAL__94340 (.valAt VAL__94339 :tag)]
                        (clojure.core/case
                         VAL__94340
                         (:empty)
                         (clojure.core/let
                          [?right input__93840_nth_2__]
                          (try
                           [{:tag :string-star,
                             :pattern ?pattern,
                             :next ?right}]
                           (catch
                            java.lang.Exception
                            e__33231__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__33231__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__33231__auto__)))))
                         (state__94902)))
                       (state__94902)))))
                   (state__94902)))
                 (state__94902))
                (state__94902)))
              (state__94902
               []
               (if
                (clojure.core/=
                 input__93840_nth_0__
                 'meander.dev.parse.zeta/string-join-args)
                (if
                 (clojure.core/map? input__93840_nth_1__)
                 (clojure.core/let
                  [VAL__94343 (.valAt input__93840_nth_1__ :tag)]
                  (clojure.core/case
                   VAL__94343
                   (:string-join)
                   (clojure.core/let
                    [VAL__94344 (.valAt input__93840_nth_1__ :left)]
                    (clojure.core/let
                     [?left VAL__94344]
                     (clojure.core/let
                      [VAL__94345 (.valAt input__93840_nth_1__ :right)]
                      (clojure.core/let
                       [?right-1 VAL__94345]
                       (clojure.core/let
                        [?right-2 input__93840_nth_2__]
                        (try
                         [{:tag :string-join,
                           :left ?left,
                           :right
                           (clojure.core/let
                            [CATA_RESULT__32291__auto__
                             (CATA__FN__93885
                              ['meander.dev.parse.zeta/string-join-args
                               ?right-1
                               ?right-2])]
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
                           (throw e__33231__auto__)))))))))
                   (state__94903)))
                 (state__94903))
                (state__94903)))
              (state__94903
               []
               (if
                (clojure.core/=
                 input__93840_nth_0__
                 'meander.dev.parse.zeta/string-join-args)
                (clojure.core/let
                 [?left input__93840_nth_1__]
                 (if
                  (clojure.core/map? input__93840_nth_2__)
                  (clojure.core/let
                   [VAL__94348 (.valAt input__93840_nth_2__ :tag)]
                   (clojure.core/case
                    VAL__94348
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
                    (state__94904)))
                  (state__94904)))
                (state__94904)))
              (state__94904
               []
               (if
                (clojure.core/=
                 input__93840_nth_0__
                 'meander.dev.parse.zeta/string-join-args)
                (clojure.core/let
                 [?left input__93840_nth_1__]
                 (clojure.core/let
                  [?right input__93840_nth_2__]
                  (try
                   [{:tag :string-join, :left ?left, :right ?right}]
                   (catch
                    java.lang.Exception
                    e__33231__auto__
                    (if
                     (meander.runtime.zeta/fail? e__33231__auto__)
                     (meander.runtime.zeta/fail)
                     (throw e__33231__auto__))))))
                (state__94905)))
              (state__94905
               []
               (if
                (clojure.core/=
                 input__93840_nth_0__
                 'meander.dev.parse.zeta/parse-entries)
                (if
                 (clojure.core/map? input__93840_nth_1__)
                 (clojure.core/let
                  [VAL__94353
                   (.valAt input__93840_nth_1__ :meander.zeta/as)]
                  (clojure.core/let
                   [?pattern VAL__94353]
                   (clojure.core/let
                    [X__94355
                     ((clojure.core/fn
                       [m__30055__auto__]
                       (clojure.core/dissoc
                        m__30055__auto__
                        :meander.zeta/as))
                      input__93840_nth_1__)]
                    (clojure.core/let
                     [?rest X__94355]
                     (clojure.core/letfn
                      [(save__94356 [] (state__94818))
                       (f__94929
                        []
                        (clojure.core/let
                         [?env input__93840_nth_2__]
                         (try
                          [{:tag :as,
                            :pattern ?pattern,
                            :next
                            (clojure.core/let
                             [CATA_RESULT__32291__auto__
                              (CATA__FN__93885 [?rest ?env])]
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
                       (clojure.core/= ?rest input__93840_nth_1__)
                       (save__94356)
                       (f__94929)))))))
                 (state__94818))
                (state__94818)))]
             (state__94882)))
           (state__94818))
          (state__94818)))
        (state__94818
         []
         (clojure.core/letfn
          [(def__94359
            [arg__94392 ?ns]
            (clojure.core/letfn
             [(state__94930
               []
               (clojure.core/let
                [x__94393 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__94393)
                 (clojure.core/let [?env arg__94392] [?env ?ns])
                 (state__94931))))
              (state__94931
               []
               (if
                (clojure.core/map? arg__94392)
                (clojure.core/let
                 [VAL__94394 (.valAt arg__94392 :aliases)]
                 (if
                  (clojure.core/map? VAL__94394)
                  (clojure.core/let
                   [X__94396 (clojure.core/set VAL__94394)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__94396))
                    (clojure.core/loop
                     [search_space__94932 (clojure.core/seq X__94396)]
                     (if
                      (clojure.core/seq search_space__94932)
                      (clojure.core/let
                       [elem__94397
                        (clojure.core/first search_space__94932)
                        result__94933
                        (clojure.core/let
                         [elem__94397_nth_0__
                          (clojure.core/nth elem__94397 0)
                          elem__94397_nth_1__
                          (clojure.core/nth elem__94397 1)]
                         (if
                          (clojure.core/symbol? elem__94397_nth_0__)
                          (clojure.core/let
                           [X__94399
                            (clojure.core/name elem__94397_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__94399)
                            (if
                             (clojure.core/symbol? elem__94397_nth_1__)
                             (clojure.core/let
                              [X__94401
                               (clojure.core/name elem__94397_nth_1__)]
                              (clojure.core/case
                               X__94401
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__94392]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__94933)
                        (recur (clojure.core/next search_space__94932))
                        result__94933))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__94930)))]
          (if
           (clojure.core/vector? input__93840)
           (if
            (clojure.core/= (clojure.core/count input__93840) 3)
            (clojure.core/let
             [input__93840_nth_0__
              (clojure.core/nth input__93840 0)
              input__93840_nth_1__
              (clojure.core/nth input__93840 1)
              input__93840_nth_2__
              (clojure.core/nth input__93840 2)]
             (if
              (clojure.core/=
               input__93840_nth_0__
               'meander.dev.parse.zeta/parse-entries)
              (if
               (clojure.core/map? input__93840_nth_1__)
               (clojure.core/let
                [X__94364 (clojure.core/set input__93840_nth_1__)]
                (if
                 (clojure.core/<= 1 (clojure.core/count X__94364))
                 (clojure.core/loop
                  [search_space__94935 (clojure.core/seq X__94364)]
                  (if
                   (clojure.core/seq search_space__94935)
                   (clojure.core/let
                    [elem__94365
                     (clojure.core/first search_space__94935)
                     result__94936
                     (clojure.core/let
                      [elem__94365_nth_0__
                       (clojure.core/nth elem__94365 0)
                       elem__94365_nth_1__
                       (clojure.core/nth elem__94365 1)]
                      (clojure.core/let
                       [*m__93864 elem__94365_nth_0__]
                       (if
                        (clojure.core/symbol? elem__94365_nth_0__)
                        (clojure.core/let
                         [X__94367
                          (clojure.core/namespace elem__94365_nth_0__)]
                         (clojure.core/let
                          [?ns X__94367]
                          (clojure.core/let
                           [X__94369
                            (clojure.core/name elem__94365_nth_0__)]
                           (if
                            (clojure.core/string? X__94369)
                            (if
                             (clojure.core/re-matches #"&.*" X__94369)
                             (clojure.core/let
                              [?pattern elem__94365_nth_1__]
                              (clojure.core/let
                               [X__94371
                                ((clojure.core/fn
                                  [m__30055__auto__]
                                  (clojure.core/dissoc
                                   m__30055__auto__
                                   *m__93864))
                                 input__93840_nth_1__)]
                               (clojure.core/let
                                [?rest X__94371]
                                (clojure.core/let
                                 [x__30988__auto__
                                  (def__94359
                                   input__93840_nth_2__
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
                                        (CATA__FN__93885
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
                                        (CATA__FN__93885
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
                     (meander.runtime.zeta/fail? result__94936)
                     (recur (clojure.core/next search_space__94935))
                     result__94936))
                   (state__94819)))
                 (state__94819)))
               (state__94819))
              (state__94819)))
            (state__94819))
           (state__94819))))
        (state__94819
         []
         (if
          (clojure.core/vector? input__93840)
          (clojure.core/letfn
           [(state__94938
             []
             (if
              (clojure.core/= (clojure.core/count input__93840) 3)
              (clojure.core/let
               [input__93840_nth_0__
                (clojure.core/nth input__93840 0)
                input__93840_nth_1__
                (clojure.core/nth input__93840 1)
                input__93840_nth_2__
                (clojure.core/nth input__93840 2)]
               (clojure.core/letfn
                [(state__94941
                  []
                  (if
                   (clojure.core/=
                    input__93840_nth_0__
                    'meander.dev.parse.zeta/parse-entries)
                   (if
                    (clojure.core/map? input__93840_nth_1__)
                    (clojure.core/let
                     [X__94415 (clojure.core/set input__93840_nth_1__)]
                     (if
                      (clojure.core/<= 1 (clojure.core/count X__94415))
                      (clojure.core/loop
                       [search_space__94947
                        (clojure.core/seq X__94415)]
                       (if
                        (clojure.core/seq search_space__94947)
                        (clojure.core/let
                         [elem__94416
                          (clojure.core/first search_space__94947)
                          result__94948
                          (clojure.core/let
                           [elem__94416_nth_0__
                            (clojure.core/nth elem__94416 0)
                            elem__94416_nth_1__
                            (clojure.core/nth elem__94416 1)]
                           (clojure.core/let
                            [?key-pattern elem__94416_nth_0__]
                            (clojure.core/let
                             [?val-pattern elem__94416_nth_1__]
                             (clojure.core/let
                              [X__94418
                               ((clojure.core/fn
                                 [m__30055__auto__]
                                 (clojure.core/dissoc
                                  m__30055__auto__
                                  ?key-pattern))
                                input__93840_nth_1__)]
                              (clojure.core/let
                               [?rest X__94418]
                               (clojure.core/let
                                [?env input__93840_nth_2__]
                                (try
                                 [{:tag :entry,
                                   :key-pattern
                                   (clojure.core/let
                                    [CATA_RESULT__32291__auto__
                                     (CATA__FN__93885
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
                                     (CATA__FN__93885
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
                                     (CATA__FN__93885
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
                          (meander.runtime.zeta/fail? result__94948)
                          (recur
                           (clojure.core/next search_space__94947))
                          result__94948))
                        (state__94942)))
                      (state__94942)))
                    (state__94942))
                   (state__94942)))
                 (state__94942
                  []
                  (if
                   (clojure.core/=
                    input__93840_nth_0__
                    'meander.dev.parse.zeta/parse-entries)
                   (if
                    (clojure.core/map? input__93840_nth_1__)
                    (clojure.core/let
                     [?env input__93840_nth_2__]
                     (try
                      [{:tag :some-map}]
                      (catch
                       java.lang.Exception
                       e__33231__auto__
                       (if
                        (meander.runtime.zeta/fail? e__33231__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__33231__auto__)))))
                    (state__94943))
                   (state__94943)))
                 (state__94943
                  []
                  (if
                   (clojure.core/=
                    input__93840_nth_0__
                    'meander.dev.parse.zeta/parse-with-bindings)
                   (if
                    (clojure.core/vector? input__93840_nth_1__)
                    (clojure.core/case
                     input__93840_nth_1__
                     ([])
                     (clojure.core/let
                      [?env input__93840_nth_2__]
                      (try
                       [[]]
                       (catch
                        java.lang.Exception
                        e__33231__auto__
                        (if
                         (meander.runtime.zeta/fail? e__33231__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__33231__auto__)))))
                     (state__94944))
                    (state__94944))
                   (state__94944)))
                 (state__94944
                  []
                  (if
                   (clojure.core/=
                    input__93840_nth_0__
                    'meander.dev.parse.zeta/parse-with-bindings)
                   (if
                    (clojure.core/vector? input__93840_nth_1__)
                    (if
                     (clojure.core/=
                      (clojure.core/count input__93840_nth_1__)
                      1)
                     (clojure.core/let
                      [?env input__93840_nth_2__]
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
                     (state__94945))
                    (state__94945))
                   (state__94945)))
                 (state__94945
                  []
                  (if
                   (clojure.core/=
                    input__93840_nth_0__
                    'meander.dev.parse.zeta/parse-with-bindings)
                   (if
                    (clojure.core/vector? input__93840_nth_1__)
                    (clojure.core/let
                     [input__93840_nth_1___l__
                      (clojure.core/subvec
                       input__93840_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__93840_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__93840_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__93840_nth_1___r__
                        (clojure.core/subvec input__93840_nth_1__ 2)]
                       (clojure.core/let
                        [input__93840_nth_1___l___nth_0__
                         (clojure.core/nth input__93840_nth_1___l__ 0)
                         input__93840_nth_1___l___nth_1__
                         (clojure.core/nth input__93840_nth_1___l__ 1)]
                        (if
                         (clojure.core/symbol?
                          input__93840_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__94432
                           (clojure.core/namespace
                            input__93840_nth_1___l___nth_0__)]
                          (clojure.core/let
                           [X__94434
                            (clojure.core/name
                             input__93840_nth_1___l___nth_0__)]
                           (if
                            (clojure.core/string? X__94434)
                            (if
                             (clojure.core/re-matches #"%.+" X__94434)
                             (clojure.core/let
                              [?symbol
                               input__93840_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?pattern
                                input__93840_nth_1___l___nth_1__]
                               (clojure.core/let
                                [?rest input__93840_nth_1___r__]
                                (clojure.core/let
                                 [?env input__93840_nth_2__]
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
                                         (CATA__FN__93885
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
                                       (CATA__FN__93885
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
                             (state__94946))
                            (state__94946))))
                         (state__94946))))
                      (state__94946)))
                    (state__94946))
                   (state__94946)))
                 (state__94946
                  []
                  (if
                   (clojure.core/=
                    input__93840_nth_0__
                    'meander.dev.parse.zeta/parse-with-bindings)
                   (if
                    (clojure.core/vector? input__93840_nth_1__)
                    (clojure.core/let
                     [input__93840_nth_1___l__
                      (clojure.core/subvec
                       input__93840_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__93840_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__93840_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__93840_nth_1___r__
                        (clojure.core/subvec input__93840_nth_1__ 2)]
                       (clojure.core/let
                        [input__93840_nth_1___l___nth_0__
                         (clojure.core/nth input__93840_nth_1___l__ 0)
                         input__93840_nth_1___l___nth_1__
                         (clojure.core/nth input__93840_nth_1___l__ 1)]
                        (clojure.core/let
                         [?x input__93840_nth_1___l___nth_0__]
                         (clojure.core/let
                          [?pattern input__93840_nth_1___l___nth_1__]
                          (clojure.core/let
                           [?rest input__93840_nth_1___r__]
                           (clojure.core/let
                            [?env input__93840_nth_2__]
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
                      (state__94939)))
                    (state__94939))
                   (state__94939)))]
                (state__94941)))
              (state__94939)))
            (state__94939
             []
             (if
              (clojure.core/= (clojure.core/count input__93840) 2)
              (clojure.core/let
               [input__93840_nth_0__
                (clojure.core/nth input__93840 0)
                input__93840_nth_1__
                (clojure.core/nth input__93840 1)]
               (if
                (clojure.core/vector? input__93840_nth_0__)
                (clojure.core/let
                 [?sequence input__93840_nth_0__]
                 (clojure.core/let
                  [?env input__93840_nth_1__]
                  (try
                   [(clojure.core/let
                     [CATA_RESULT__32291__auto__
                      (CATA__FN__93885
                       ['meander.dev.parse.zeta/vector-args
                        (clojure.core/let
                         [CATA_RESULT__32291__auto__
                          (CATA__FN__93885
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
                (state__94940)))
              (state__94940)))
            (state__94940
             []
             (if
              (clojure.core/= (clojure.core/count input__93840) 3)
              (clojure.core/let
               [input__93840_nth_0__
                (clojure.core/nth input__93840 0)
                input__93840_nth_1__
                (clojure.core/nth input__93840 1)]
               (clojure.core/letfn
                [(state__94950
                  []
                  (if
                   (clojure.core/=
                    input__93840_nth_0__
                    'meander.dev.parse.zeta/vector-args)
                   (if
                    (clojure.core/map? input__93840_nth_1__)
                    (clojure.core/let
                     [VAL__94445 (.valAt input__93840_nth_1__ :tag)]
                     (clojure.core/case
                      VAL__94445
                      (:literal)
                      (clojure.core/let
                       [?literal input__93840_nth_1__]
                       (try
                        [?literal]
                        (catch
                         java.lang.Exception
                         e__33231__auto__
                         (if
                          (meander.runtime.zeta/fail? e__33231__auto__)
                          (meander.runtime.zeta/fail)
                          (throw e__33231__auto__)))))
                      (state__94951)))
                    (state__94951))
                   (state__94951)))
                 (state__94951
                  []
                  (clojure.core/let
                   [input__93840_nth_2__
                    (clojure.core/nth input__93840 2)]
                   (if
                    (clojure.core/=
                     input__93840_nth_0__
                     'meander.dev.parse.zeta/vector-args)
                    (clojure.core/let
                     [?next input__93840_nth_1__]
                     (clojure.core/let
                      [?sequence input__93840_nth_2__]
                      (try
                       [{:tag :vector, :next ?next, :form ?sequence}]
                       (catch
                        java.lang.Exception
                        e__33231__auto__
                        (if
                         (meander.runtime.zeta/fail? e__33231__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__33231__auto__))))))
                    (state__94820))))]
                (state__94950)))
              (state__94820)))]
           (state__94938))
          (state__94820)))
        (state__94820
         []
         (clojure.core/letfn
          [(def__94449
            [arg__94472 ?__93842]
            (clojure.core/letfn
             [(state__94952
               []
               (clojure.core/let
                [x__94473 "meander.zeta"]
                (if
                 (clojure.core/= ?__93842 x__94473)
                 [?__93842]
                 (state__94953))))
              (state__94953
               []
               (if
                (clojure.core/map? arg__94472)
                (clojure.core/let
                 [VAL__94474 (.valAt arg__94472 :aliases)]
                 (if
                  (clojure.core/map? VAL__94474)
                  (clojure.core/let
                   [X__94476 (clojure.core/set VAL__94474)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__94476))
                    (clojure.core/loop
                     [search_space__94954 (clojure.core/seq X__94476)]
                     (if
                      (clojure.core/seq search_space__94954)
                      (clojure.core/let
                       [elem__94477
                        (clojure.core/first search_space__94954)
                        result__94955
                        (clojure.core/let
                         [elem__94477_nth_0__
                          (clojure.core/nth elem__94477 0)
                          elem__94477_nth_1__
                          (clojure.core/nth elem__94477 1)]
                         (if
                          (clojure.core/symbol? elem__94477_nth_0__)
                          (clojure.core/let
                           [X__94479
                            (clojure.core/name elem__94477_nth_0__)]
                           (if
                            (clojure.core/= ?__93842 X__94479)
                            (if
                             (clojure.core/symbol? elem__94477_nth_1__)
                             (clojure.core/let
                              [X__94481
                               (clojure.core/name elem__94477_nth_1__)]
                              (clojure.core/case
                               X__94481
                               ("meander.zeta")
                               [?__93842]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__94955)
                        (recur (clojure.core/next search_space__94954))
                        result__94955))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__94952)))]
          (if
           (clojure.core/vector? input__93840)
           (if
            (clojure.core/= (clojure.core/count input__93840) 2)
            (clojure.core/let
             [input__93840_nth_0__
              (clojure.core/nth input__93840 0)
              input__93840_nth_1__
              (clojure.core/nth input__93840 1)]
             (if
              (clojure.core/seq? input__93840_nth_0__)
              (clojure.core/let
               [input__93840_nth_0___l__
                (clojure.core/take 1 input__93840_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__93840_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__93840_nth_0___r__
                  (clojure.core/drop 1 input__93840_nth_0__)]
                 (clojure.core/let
                  [input__93840_nth_0___l___nth_0__
                   (clojure.core/nth input__93840_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__93840_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__94459
                     (clojure.core/namespace
                      input__93840_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__93842 X__94459]
                     (clojure.core/let
                      [X__94461
                       (clojure.core/name
                        input__93840_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__94461
                       ("with")
                       (clojure.core/let
                        [x__30988__auto__
                         (def__94449 input__93840_nth_1__ ?__93842)]
                        (if
                         (meander.runtime.zeta/fail? x__30988__auto__)
                         (state__94821)
                         (clojure.core/let
                          [[?__93842] x__30988__auto__]
                          (if
                           (clojure.core/vector? input__93840)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__93840)
                             2)
                            (clojure.core/let
                             [input__93840_nth_0__
                              (clojure.core/nth input__93840 0)
                              input__93840_nth_1__
                              (clojure.core/nth input__93840 1)]
                             (if
                              (clojure.core/seq? input__93840_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__93840_nth_0__)
                                3)
                               (clojure.core/let
                                [input__93840_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__93840_nth_0__
                                  1)
                                 input__93840_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__93840_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?bindings
                                  input__93840_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?body input__93840_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__93840_nth_0__]
                                   (clojure.core/let
                                    [?env input__93840_nth_1__]
                                    (try
                                     [{:tag :with,
                                       :bindings
                                       {:tag :with-bindings,
                                        :bindings
                                        (clojure.core/let
                                         [CATA_RESULT__32291__auto__
                                          (CATA__FN__93885
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
                                         (CATA__FN__93885
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
                               (state__94821))
                              (state__94821)))
                            (state__94821))
                           (state__94821)))))
                       (state__94821)))))
                   (state__94821))))
                (state__94821)))
              (state__94821)))
            (state__94821))
           (state__94821))))
        (state__94821
         []
         (clojure.core/letfn
          [(def__94483
            [arg__94506 ?__93843]
            (clojure.core/letfn
             [(state__94957
               []
               (clojure.core/let
                [x__94507 "meander.zeta"]
                (if
                 (clojure.core/= ?__93843 x__94507)
                 [?__93843]
                 (state__94958))))
              (state__94958
               []
               (if
                (clojure.core/map? arg__94506)
                (clojure.core/let
                 [VAL__94508 (.valAt arg__94506 :aliases)]
                 (if
                  (clojure.core/map? VAL__94508)
                  (clojure.core/let
                   [X__94510 (clojure.core/set VAL__94508)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__94510))
                    (clojure.core/loop
                     [search_space__94959 (clojure.core/seq X__94510)]
                     (if
                      (clojure.core/seq search_space__94959)
                      (clojure.core/let
                       [elem__94511
                        (clojure.core/first search_space__94959)
                        result__94960
                        (clojure.core/let
                         [elem__94511_nth_0__
                          (clojure.core/nth elem__94511 0)
                          elem__94511_nth_1__
                          (clojure.core/nth elem__94511 1)]
                         (if
                          (clojure.core/symbol? elem__94511_nth_0__)
                          (clojure.core/let
                           [X__94513
                            (clojure.core/name elem__94511_nth_0__)]
                           (if
                            (clojure.core/= ?__93843 X__94513)
                            (if
                             (clojure.core/symbol? elem__94511_nth_1__)
                             (clojure.core/let
                              [X__94515
                               (clojure.core/name elem__94511_nth_1__)]
                              (clojure.core/case
                               X__94515
                               ("meander.zeta")
                               [?__93843]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__94960)
                        (recur (clojure.core/next search_space__94959))
                        result__94960))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__94957)))]
          (if
           (clojure.core/vector? input__93840)
           (if
            (clojure.core/= (clojure.core/count input__93840) 2)
            (clojure.core/let
             [input__93840_nth_0__
              (clojure.core/nth input__93840 0)
              input__93840_nth_1__
              (clojure.core/nth input__93840 1)]
             (if
              (clojure.core/seq? input__93840_nth_0__)
              (clojure.core/let
               [input__93840_nth_0___l__
                (clojure.core/take 1 input__93840_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__93840_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__93840_nth_0___r__
                  (clojure.core/drop 1 input__93840_nth_0__)]
                 (clojure.core/let
                  [input__93840_nth_0___l___nth_0__
                   (clojure.core/nth input__93840_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__93840_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__94493
                     (clojure.core/namespace
                      input__93840_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__93843 X__94493]
                     (clojure.core/let
                      [X__94495
                       (clojure.core/name
                        input__93840_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__94495
                       ("apply")
                       (clojure.core/let
                        [x__30988__auto__
                         (def__94483 input__93840_nth_1__ ?__93843)]
                        (if
                         (meander.runtime.zeta/fail? x__30988__auto__)
                         (state__94822)
                         (clojure.core/let
                          [[?__93843] x__30988__auto__]
                          (if
                           (clojure.core/vector? input__93840)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__93840)
                             2)
                            (clojure.core/let
                             [input__93840_nth_0__
                              (clojure.core/nth input__93840 0)
                              input__93840_nth_1__
                              (clojure.core/nth input__93840 1)]
                             (if
                              (clojure.core/seq? input__93840_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__93840_nth_0__)
                                3)
                               (clojure.core/let
                                [input__93840_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__93840_nth_0__
                                  1)
                                 input__93840_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__93840_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?fn input__93840_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?pattern
                                   input__93840_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__93840_nth_0__]
                                   (clojure.core/let
                                    [?env input__93840_nth_1__]
                                    (try
                                     [{:tag :apply,
                                       :fn ?fn,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__32291__auto__
                                         (CATA__FN__93885
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
                               (state__94822))
                              (state__94822)))
                            (state__94822))
                           (state__94822)))))
                       (state__94822)))))
                   (state__94822))))
                (state__94822)))
              (state__94822)))
            (state__94822))
           (state__94822))))
        (state__94822
         []
         (clojure.core/letfn
          [(def__94517
            [arg__94540 ?__93844]
            (clojure.core/letfn
             [(state__94962
               []
               (clojure.core/let
                [x__94541 "meander.zeta"]
                (if
                 (clojure.core/= ?__93844 x__94541)
                 [?__93844]
                 (state__94963))))
              (state__94963
               []
               (if
                (clojure.core/map? arg__94540)
                (clojure.core/let
                 [VAL__94542 (.valAt arg__94540 :aliases)]
                 (if
                  (clojure.core/map? VAL__94542)
                  (clojure.core/let
                   [X__94544 (clojure.core/set VAL__94542)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__94544))
                    (clojure.core/loop
                     [search_space__94964 (clojure.core/seq X__94544)]
                     (if
                      (clojure.core/seq search_space__94964)
                      (clojure.core/let
                       [elem__94545
                        (clojure.core/first search_space__94964)
                        result__94965
                        (clojure.core/let
                         [elem__94545_nth_0__
                          (clojure.core/nth elem__94545 0)
                          elem__94545_nth_1__
                          (clojure.core/nth elem__94545 1)]
                         (if
                          (clojure.core/symbol? elem__94545_nth_0__)
                          (clojure.core/let
                           [X__94547
                            (clojure.core/name elem__94545_nth_0__)]
                           (if
                            (clojure.core/= ?__93844 X__94547)
                            (if
                             (clojure.core/symbol? elem__94545_nth_1__)
                             (clojure.core/let
                              [X__94549
                               (clojure.core/name elem__94545_nth_1__)]
                              (clojure.core/case
                               X__94549
                               ("meander.zeta")
                               [?__93844]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__94965)
                        (recur (clojure.core/next search_space__94964))
                        result__94965))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__94962)))]
          (if
           (clojure.core/vector? input__93840)
           (if
            (clojure.core/= (clojure.core/count input__93840) 2)
            (clojure.core/let
             [input__93840_nth_0__
              (clojure.core/nth input__93840 0)
              input__93840_nth_1__
              (clojure.core/nth input__93840 1)]
             (if
              (clojure.core/seq? input__93840_nth_0__)
              (clojure.core/let
               [input__93840_nth_0___l__
                (clojure.core/take 1 input__93840_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__93840_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__93840_nth_0___r__
                  (clojure.core/drop 1 input__93840_nth_0__)]
                 (clojure.core/let
                  [input__93840_nth_0___l___nth_0__
                   (clojure.core/nth input__93840_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__93840_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__94527
                     (clojure.core/namespace
                      input__93840_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__93844 X__94527]
                     (clojure.core/let
                      [X__94529
                       (clojure.core/name
                        input__93840_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__94529
                       ("and")
                       (clojure.core/let
                        [x__30988__auto__
                         (def__94517 input__93840_nth_1__ ?__93844)]
                        (if
                         (meander.runtime.zeta/fail? x__30988__auto__)
                         (state__94823)
                         (clojure.core/let
                          [[?__93844] x__30988__auto__]
                          (if
                           (clojure.core/vector? input__93840)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__93840)
                             2)
                            (clojure.core/let
                             [input__93840_nth_0__
                              (clojure.core/nth input__93840 0)
                              input__93840_nth_1__
                              (clojure.core/nth input__93840 1)]
                             (if
                              (clojure.core/seq? input__93840_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__93840_nth_0__)
                                3)
                               (clojure.core/let
                                [input__93840_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__93840_nth_0__
                                  1)
                                 input__93840_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__93840_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?left input__93840_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?right input__93840_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__93840_nth_0__]
                                   (clojure.core/let
                                    [?env input__93840_nth_1__]
                                    (try
                                     [{:tag :and,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__32291__auto__
                                         (CATA__FN__93885
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
                                         (CATA__FN__93885
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
                               (state__94823))
                              (state__94823)))
                            (state__94823))
                           (state__94823)))))
                       (state__94823)))))
                   (state__94823))))
                (state__94823)))
              (state__94823)))
            (state__94823))
           (state__94823))))
        (state__94823
         []
         (clojure.core/letfn
          [(def__94551
            [arg__94574 ?__93845]
            (clojure.core/letfn
             [(state__94967
               []
               (clojure.core/let
                [x__94575 "meander.zeta"]
                (if
                 (clojure.core/= ?__93845 x__94575)
                 [?__93845]
                 (state__94968))))
              (state__94968
               []
               (if
                (clojure.core/map? arg__94574)
                (clojure.core/let
                 [VAL__94576 (.valAt arg__94574 :aliases)]
                 (if
                  (clojure.core/map? VAL__94576)
                  (clojure.core/let
                   [X__94578 (clojure.core/set VAL__94576)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__94578))
                    (clojure.core/loop
                     [search_space__94969 (clojure.core/seq X__94578)]
                     (if
                      (clojure.core/seq search_space__94969)
                      (clojure.core/let
                       [elem__94579
                        (clojure.core/first search_space__94969)
                        result__94970
                        (clojure.core/let
                         [elem__94579_nth_0__
                          (clojure.core/nth elem__94579 0)
                          elem__94579_nth_1__
                          (clojure.core/nth elem__94579 1)]
                         (if
                          (clojure.core/symbol? elem__94579_nth_0__)
                          (clojure.core/let
                           [X__94581
                            (clojure.core/name elem__94579_nth_0__)]
                           (if
                            (clojure.core/= ?__93845 X__94581)
                            (if
                             (clojure.core/symbol? elem__94579_nth_1__)
                             (clojure.core/let
                              [X__94583
                               (clojure.core/name elem__94579_nth_1__)]
                              (clojure.core/case
                               X__94583
                               ("meander.zeta")
                               [?__93845]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__94970)
                        (recur (clojure.core/next search_space__94969))
                        result__94970))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__94967)))]
          (if
           (clojure.core/vector? input__93840)
           (if
            (clojure.core/= (clojure.core/count input__93840) 2)
            (clojure.core/let
             [input__93840_nth_0__
              (clojure.core/nth input__93840 0)
              input__93840_nth_1__
              (clojure.core/nth input__93840 1)]
             (if
              (clojure.core/seq? input__93840_nth_0__)
              (clojure.core/let
               [input__93840_nth_0___l__
                (clojure.core/take 1 input__93840_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__93840_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__93840_nth_0___r__
                  (clojure.core/drop 1 input__93840_nth_0__)]
                 (clojure.core/let
                  [input__93840_nth_0___l___nth_0__
                   (clojure.core/nth input__93840_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__93840_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__94561
                     (clojure.core/namespace
                      input__93840_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__93845 X__94561]
                     (clojure.core/let
                      [X__94563
                       (clojure.core/name
                        input__93840_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__94563
                       ("cata")
                       (clojure.core/let
                        [x__30988__auto__
                         (def__94551 input__93840_nth_1__ ?__93845)]
                        (if
                         (meander.runtime.zeta/fail? x__30988__auto__)
                         (state__94824)
                         (clojure.core/let
                          [[?__93845] x__30988__auto__]
                          (if
                           (clojure.core/vector? input__93840)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__93840)
                             2)
                            (clojure.core/let
                             [input__93840_nth_0__
                              (clojure.core/nth input__93840 0)
                              input__93840_nth_1__
                              (clojure.core/nth input__93840 1)]
                             (if
                              (clojure.core/seq? input__93840_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__93840_nth_0__)
                                2)
                               (clojure.core/let
                                [input__93840_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__93840_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__93840_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__93840_nth_0__]
                                  (clojure.core/let
                                   [?env input__93840_nth_1__]
                                   (try
                                    [{:tag :cata,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__32291__auto__
                                        (CATA__FN__93885
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
                               (state__94824))
                              (state__94824)))
                            (state__94824))
                           (state__94824)))))
                       (state__94824)))))
                   (state__94824))))
                (state__94824)))
              (state__94824)))
            (state__94824))
           (state__94824))))
        (state__94824
         []
         (clojure.core/letfn
          [(def__94585
            [arg__94608 ?__93846]
            (clojure.core/letfn
             [(state__94972
               []
               (clojure.core/let
                [x__94609 "meander.zeta"]
                (if
                 (clojure.core/= ?__93846 x__94609)
                 [?__93846]
                 (state__94973))))
              (state__94973
               []
               (if
                (clojure.core/map? arg__94608)
                (clojure.core/let
                 [VAL__94610 (.valAt arg__94608 :aliases)]
                 (if
                  (clojure.core/map? VAL__94610)
                  (clojure.core/let
                   [X__94612 (clojure.core/set VAL__94610)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__94612))
                    (clojure.core/loop
                     [search_space__94974 (clojure.core/seq X__94612)]
                     (if
                      (clojure.core/seq search_space__94974)
                      (clojure.core/let
                       [elem__94613
                        (clojure.core/first search_space__94974)
                        result__94975
                        (clojure.core/let
                         [elem__94613_nth_0__
                          (clojure.core/nth elem__94613 0)
                          elem__94613_nth_1__
                          (clojure.core/nth elem__94613 1)]
                         (if
                          (clojure.core/symbol? elem__94613_nth_0__)
                          (clojure.core/let
                           [X__94615
                            (clojure.core/name elem__94613_nth_0__)]
                           (if
                            (clojure.core/= ?__93846 X__94615)
                            (if
                             (clojure.core/symbol? elem__94613_nth_1__)
                             (clojure.core/let
                              [X__94617
                               (clojure.core/name elem__94613_nth_1__)]
                              (clojure.core/case
                               X__94617
                               ("meander.zeta")
                               [?__93846]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__94975)
                        (recur (clojure.core/next search_space__94974))
                        result__94975))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__94972)))]
          (if
           (clojure.core/vector? input__93840)
           (if
            (clojure.core/= (clojure.core/count input__93840) 2)
            (clojure.core/let
             [input__93840_nth_0__
              (clojure.core/nth input__93840 0)
              input__93840_nth_1__
              (clojure.core/nth input__93840 1)]
             (if
              (clojure.core/seq? input__93840_nth_0__)
              (clojure.core/let
               [input__93840_nth_0___l__
                (clojure.core/take 1 input__93840_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__93840_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__93840_nth_0___r__
                  (clojure.core/drop 1 input__93840_nth_0__)]
                 (clojure.core/let
                  [input__93840_nth_0___l___nth_0__
                   (clojure.core/nth input__93840_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__93840_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__94595
                     (clojure.core/namespace
                      input__93840_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__93846 X__94595]
                     (clojure.core/let
                      [X__94597
                       (clojure.core/name
                        input__93840_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__94597
                       ("fold")
                       (clojure.core/let
                        [x__30988__auto__
                         (def__94585 input__93840_nth_1__ ?__93846)]
                        (if
                         (meander.runtime.zeta/fail? x__30988__auto__)
                         (state__94825)
                         (clojure.core/let
                          [[?__93846] x__30988__auto__]
                          (if
                           (clojure.core/vector? input__93840)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__93840)
                             2)
                            (clojure.core/let
                             [input__93840_nth_0__
                              (clojure.core/nth input__93840 0)
                              input__93840_nth_1__
                              (clojure.core/nth input__93840 1)]
                             (if
                              (clojure.core/seq? input__93840_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 4)
                                 input__93840_nth_0__)
                                4)
                               (clojure.core/let
                                [input__93840_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__93840_nth_0__
                                  1)
                                 input__93840_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__93840_nth_0__
                                  2)
                                 input__93840_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__93840_nth_0__
                                  3)]
                                (clojure.core/let
                                 [?mutable-variable
                                  input__93840_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?initial-value
                                   input__93840_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?fold-function
                                    input__93840_nth_0___nth_3__]
                                   (clojure.core/let
                                    [?form input__93840_nth_0__]
                                    (clojure.core/let
                                     [?env input__93840_nth_1__]
                                     (try
                                      [(clojure.core/let
                                        [CATA_RESULT__32291__auto__
                                         (CATA__FN__93885
                                          ['meander.dev.parse.zeta/fold-args
                                           (clojure.core/let
                                            [CATA_RESULT__32291__auto__
                                             (CATA__FN__93885
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
                                             (CATA__FN__93885
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
                               (state__94825))
                              (state__94825)))
                            (state__94825))
                           (state__94825)))))
                       (state__94825)))))
                   (state__94825))))
                (state__94825)))
              (state__94825)))
            (state__94825))
           (state__94825))))
        (state__94825
         []
         (if
          (clojure.core/vector? input__93840)
          (if
           (clojure.core/= (clojure.core/count input__93840) 5)
           (clojure.core/let
            [input__93840_nth_0__
             (clojure.core/nth input__93840 0)
             input__93840_nth_1__
             (clojure.core/nth input__93840 1)
             input__93840_nth_2__
             (clojure.core/nth input__93840 2)
             input__93840_nth_3__
             (clojure.core/nth input__93840 3)
             input__93840_nth_4__
             (clojure.core/nth input__93840 4)]
            (if
             (clojure.core/=
              input__93840_nth_0__
              'meander.dev.parse.zeta/fold-args)
             (if
              (clojure.core/map? input__93840_nth_1__)
              (clojure.core/let
               [VAL__94620 (.valAt input__93840_nth_1__ :tag)]
               (clojure.core/case
                VAL__94620
                (:mutable-variable)
                (clojure.core/let
                 [?variable-ast input__93840_nth_1__]
                 (clojure.core/let
                  [?initial-value-ast input__93840_nth_2__]
                  (clojure.core/let
                   [?fold-function input__93840_nth_3__]
                   (clojure.core/let
                    [?form input__93840_nth_4__]
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
                (state__94826)))
              (state__94826))
             (state__94826)))
           (state__94826))
          (state__94826)))
        (state__94826
         []
         (clojure.core/letfn
          [(def__94622
            [arg__94645 ?__93847]
            (clojure.core/letfn
             [(state__94977
               []
               (clojure.core/let
                [x__94646 "meander.zeta"]
                (if
                 (clojure.core/= ?__93847 x__94646)
                 [?__93847]
                 (state__94978))))
              (state__94978
               []
               (if
                (clojure.core/map? arg__94645)
                (clojure.core/let
                 [VAL__94647 (.valAt arg__94645 :aliases)]
                 (if
                  (clojure.core/map? VAL__94647)
                  (clojure.core/let
                   [X__94649 (clojure.core/set VAL__94647)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__94649))
                    (clojure.core/loop
                     [search_space__94979 (clojure.core/seq X__94649)]
                     (if
                      (clojure.core/seq search_space__94979)
                      (clojure.core/let
                       [elem__94650
                        (clojure.core/first search_space__94979)
                        result__94980
                        (clojure.core/let
                         [elem__94650_nth_0__
                          (clojure.core/nth elem__94650 0)
                          elem__94650_nth_1__
                          (clojure.core/nth elem__94650 1)]
                         (if
                          (clojure.core/symbol? elem__94650_nth_0__)
                          (clojure.core/let
                           [X__94652
                            (clojure.core/name elem__94650_nth_0__)]
                           (if
                            (clojure.core/= ?__93847 X__94652)
                            (if
                             (clojure.core/symbol? elem__94650_nth_1__)
                             (clojure.core/let
                              [X__94654
                               (clojure.core/name elem__94650_nth_1__)]
                              (clojure.core/case
                               X__94654
                               ("meander.zeta")
                               [?__93847]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__94980)
                        (recur (clojure.core/next search_space__94979))
                        result__94980))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__94977)))]
          (if
           (clojure.core/vector? input__93840)
           (if
            (clojure.core/= (clojure.core/count input__93840) 2)
            (clojure.core/let
             [input__93840_nth_0__
              (clojure.core/nth input__93840 0)
              input__93840_nth_1__
              (clojure.core/nth input__93840 1)]
             (if
              (clojure.core/seq? input__93840_nth_0__)
              (clojure.core/let
               [input__93840_nth_0___l__
                (clojure.core/take 1 input__93840_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__93840_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__93840_nth_0___r__
                  (clojure.core/drop 1 input__93840_nth_0__)]
                 (clojure.core/let
                  [input__93840_nth_0___l___nth_0__
                   (clojure.core/nth input__93840_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__93840_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__94632
                     (clojure.core/namespace
                      input__93840_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__93847 X__94632]
                     (clojure.core/let
                      [X__94634
                       (clojure.core/name
                        input__93840_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__94634
                       ("let")
                       (clojure.core/let
                        [x__30988__auto__
                         (def__94622 input__93840_nth_1__ ?__93847)]
                        (if
                         (meander.runtime.zeta/fail? x__30988__auto__)
                         (state__94827)
                         (clojure.core/let
                          [[?__93847] x__30988__auto__]
                          (if
                           (clojure.core/vector? input__93840)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__93840)
                             2)
                            (clojure.core/let
                             [input__93840_nth_0__
                              (clojure.core/nth input__93840 0)
                              input__93840_nth_1__
                              (clojure.core/nth input__93840 1)]
                             (if
                              (clojure.core/seq? input__93840_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__93840_nth_0__)
                                3)
                               (clojure.core/let
                                [input__93840_nth_0___nth_0__
                                 (clojure.core/nth
                                  input__93840_nth_0__
                                  0)
                                 input__93840_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__93840_nth_0__
                                  1)
                                 input__93840_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__93840_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?pattern
                                  input__93840_nth_0___nth_0__]
                                 (clojure.core/let
                                  [?expression
                                   input__93840_nth_0___nth_1__]
                                  (clojure.core/let
                                   [?next input__93840_nth_0___nth_2__]
                                   (clojure.core/let
                                    [?form input__93840_nth_0__]
                                    (clojure.core/let
                                     [?env input__93840_nth_1__]
                                     (try
                                      [{:tag :let,
                                        :pattern
                                        (clojure.core/let
                                         [CATA_RESULT__32291__auto__
                                          (CATA__FN__93885
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
                                          (CATA__FN__93885
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
                               (state__94827))
                              (state__94827)))
                            (state__94827))
                           (state__94827)))))
                       (state__94827)))))
                   (state__94827))))
                (state__94827)))
              (state__94827)))
            (state__94827))
           (state__94827))))
        (state__94827
         []
         (clojure.core/letfn
          [(def__94656
            [arg__94679 ?__93848]
            (clojure.core/letfn
             [(state__94982
               []
               (clojure.core/let
                [x__94680 "meander.zeta"]
                (if
                 (clojure.core/= ?__93848 x__94680)
                 [?__93848]
                 (state__94983))))
              (state__94983
               []
               (if
                (clojure.core/map? arg__94679)
                (clojure.core/let
                 [VAL__94681 (.valAt arg__94679 :aliases)]
                 (if
                  (clojure.core/map? VAL__94681)
                  (clojure.core/let
                   [X__94683 (clojure.core/set VAL__94681)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__94683))
                    (clojure.core/loop
                     [search_space__94984 (clojure.core/seq X__94683)]
                     (if
                      (clojure.core/seq search_space__94984)
                      (clojure.core/let
                       [elem__94684
                        (clojure.core/first search_space__94984)
                        result__94985
                        (clojure.core/let
                         [elem__94684_nth_0__
                          (clojure.core/nth elem__94684 0)
                          elem__94684_nth_1__
                          (clojure.core/nth elem__94684 1)]
                         (if
                          (clojure.core/symbol? elem__94684_nth_0__)
                          (clojure.core/let
                           [X__94686
                            (clojure.core/name elem__94684_nth_0__)]
                           (if
                            (clojure.core/= ?__93848 X__94686)
                            (if
                             (clojure.core/symbol? elem__94684_nth_1__)
                             (clojure.core/let
                              [X__94688
                               (clojure.core/name elem__94684_nth_1__)]
                              (clojure.core/case
                               X__94688
                               ("meander.zeta")
                               [?__93848]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__94985)
                        (recur (clojure.core/next search_space__94984))
                        result__94985))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__94982)))]
          (if
           (clojure.core/vector? input__93840)
           (if
            (clojure.core/= (clojure.core/count input__93840) 2)
            (clojure.core/let
             [input__93840_nth_0__
              (clojure.core/nth input__93840 0)
              input__93840_nth_1__
              (clojure.core/nth input__93840 1)]
             (if
              (clojure.core/seq? input__93840_nth_0__)
              (clojure.core/let
               [input__93840_nth_0___l__
                (clojure.core/take 1 input__93840_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__93840_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__93840_nth_0___r__
                  (clojure.core/drop 1 input__93840_nth_0__)]
                 (clojure.core/let
                  [input__93840_nth_0___l___nth_0__
                   (clojure.core/nth input__93840_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__93840_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__94666
                     (clojure.core/namespace
                      input__93840_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__93848 X__94666]
                     (clojure.core/let
                      [X__94668
                       (clojure.core/name
                        input__93840_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__94668
                       ("not")
                       (clojure.core/let
                        [x__30988__auto__
                         (def__94656 input__93840_nth_1__ ?__93848)]
                        (if
                         (meander.runtime.zeta/fail? x__30988__auto__)
                         (state__94828)
                         (clojure.core/let
                          [[?__93848] x__30988__auto__]
                          (if
                           (clojure.core/vector? input__93840)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__93840)
                             2)
                            (clojure.core/let
                             [input__93840_nth_0__
                              (clojure.core/nth input__93840 0)
                              input__93840_nth_1__
                              (clojure.core/nth input__93840 1)]
                             (if
                              (clojure.core/seq? input__93840_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__93840_nth_0__)
                                2)
                               (clojure.core/let
                                [input__93840_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__93840_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__93840_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__93840_nth_0__]
                                  (clojure.core/let
                                   [?env input__93840_nth_1__]
                                   (try
                                    [{:tag :not,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__32291__auto__
                                        (CATA__FN__93885
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
                               (state__94828))
                              (state__94828)))
                            (state__94828))
                           (state__94828)))))
                       (state__94828)))))
                   (state__94828))))
                (state__94828)))
              (state__94828)))
            (state__94828))
           (state__94828))))
        (state__94828
         []
         (clojure.core/letfn
          [(def__94690
            [arg__94713 ?__93849]
            (clojure.core/letfn
             [(state__94987
               []
               (clojure.core/let
                [x__94714 "meander.zeta"]
                (if
                 (clojure.core/= ?__93849 x__94714)
                 [?__93849]
                 (state__94988))))
              (state__94988
               []
               (if
                (clojure.core/map? arg__94713)
                (clojure.core/let
                 [VAL__94715 (.valAt arg__94713 :aliases)]
                 (if
                  (clojure.core/map? VAL__94715)
                  (clojure.core/let
                   [X__94717 (clojure.core/set VAL__94715)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__94717))
                    (clojure.core/loop
                     [search_space__94989 (clojure.core/seq X__94717)]
                     (if
                      (clojure.core/seq search_space__94989)
                      (clojure.core/let
                       [elem__94718
                        (clojure.core/first search_space__94989)
                        result__94990
                        (clojure.core/let
                         [elem__94718_nth_0__
                          (clojure.core/nth elem__94718 0)
                          elem__94718_nth_1__
                          (clojure.core/nth elem__94718 1)]
                         (if
                          (clojure.core/symbol? elem__94718_nth_0__)
                          (clojure.core/let
                           [X__94720
                            (clojure.core/name elem__94718_nth_0__)]
                           (if
                            (clojure.core/= ?__93849 X__94720)
                            (if
                             (clojure.core/symbol? elem__94718_nth_1__)
                             (clojure.core/let
                              [X__94722
                               (clojure.core/name elem__94718_nth_1__)]
                              (clojure.core/case
                               X__94722
                               ("meander.zeta")
                               [?__93849]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__94990)
                        (recur (clojure.core/next search_space__94989))
                        result__94990))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__94987)))]
          (if
           (clojure.core/vector? input__93840)
           (if
            (clojure.core/= (clojure.core/count input__93840) 2)
            (clojure.core/let
             [input__93840_nth_0__
              (clojure.core/nth input__93840 0)
              input__93840_nth_1__
              (clojure.core/nth input__93840 1)]
             (if
              (clojure.core/seq? input__93840_nth_0__)
              (clojure.core/let
               [input__93840_nth_0___l__
                (clojure.core/take 1 input__93840_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__93840_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__93840_nth_0___r__
                  (clojure.core/drop 1 input__93840_nth_0__)]
                 (clojure.core/let
                  [input__93840_nth_0___l___nth_0__
                   (clojure.core/nth input__93840_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__93840_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__94700
                     (clojure.core/namespace
                      input__93840_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__93849 X__94700]
                     (clojure.core/let
                      [X__94702
                       (clojure.core/name
                        input__93840_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__94702
                       ("or")
                       (clojure.core/let
                        [x__30988__auto__
                         (def__94690 input__93840_nth_1__ ?__93849)]
                        (if
                         (meander.runtime.zeta/fail? x__30988__auto__)
                         (state__94829)
                         (clojure.core/let
                          [[?__93849] x__30988__auto__]
                          (if
                           (clojure.core/vector? input__93840)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__93840)
                             2)
                            (clojure.core/let
                             [input__93840_nth_0__
                              (clojure.core/nth input__93840 0)
                              input__93840_nth_1__
                              (clojure.core/nth input__93840 1)]
                             (if
                              (clojure.core/seq? input__93840_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__93840_nth_0__)
                                3)
                               (clojure.core/let
                                [input__93840_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__93840_nth_0__
                                  1)
                                 input__93840_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__93840_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?left input__93840_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?right input__93840_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__93840_nth_0__]
                                   (clojure.core/let
                                    [?env input__93840_nth_1__]
                                    (try
                                     [{:tag :or,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__32291__auto__
                                         (CATA__FN__93885
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
                                         (CATA__FN__93885
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
                               (state__94829))
                              (state__94829)))
                            (state__94829))
                           (state__94829)))))
                       (state__94829)))))
                   (state__94829))))
                (state__94829)))
              (state__94829)))
            (state__94829))
           (state__94829))))
        (state__94829
         []
         (clojure.core/letfn
          [(def__94724
            [arg__94747 ?__93850]
            (clojure.core/letfn
             [(state__94992
               []
               (clojure.core/let
                [x__94748 "meander.zeta"]
                (if
                 (clojure.core/= ?__93850 x__94748)
                 [?__93850]
                 (state__94993))))
              (state__94993
               []
               (if
                (clojure.core/map? arg__94747)
                (clojure.core/let
                 [VAL__94749 (.valAt arg__94747 :aliases)]
                 (if
                  (clojure.core/map? VAL__94749)
                  (clojure.core/let
                   [X__94751 (clojure.core/set VAL__94749)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__94751))
                    (clojure.core/loop
                     [search_space__94994 (clojure.core/seq X__94751)]
                     (if
                      (clojure.core/seq search_space__94994)
                      (clojure.core/let
                       [elem__94752
                        (clojure.core/first search_space__94994)
                        result__94995
                        (clojure.core/let
                         [elem__94752_nth_0__
                          (clojure.core/nth elem__94752 0)
                          elem__94752_nth_1__
                          (clojure.core/nth elem__94752 1)]
                         (if
                          (clojure.core/symbol? elem__94752_nth_0__)
                          (clojure.core/let
                           [X__94754
                            (clojure.core/name elem__94752_nth_0__)]
                           (if
                            (clojure.core/= ?__93850 X__94754)
                            (if
                             (clojure.core/symbol? elem__94752_nth_1__)
                             (clojure.core/let
                              [X__94756
                               (clojure.core/name elem__94752_nth_1__)]
                              (clojure.core/case
                               X__94756
                               ("meander.zeta")
                               [?__93850]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__94995)
                        (recur (clojure.core/next search_space__94994))
                        result__94995))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__94992)))]
          (if
           (clojure.core/vector? input__93840)
           (if
            (clojure.core/= (clojure.core/count input__93840) 2)
            (clojure.core/let
             [input__93840_nth_0__
              (clojure.core/nth input__93840 0)
              input__93840_nth_1__
              (clojure.core/nth input__93840 1)]
             (if
              (clojure.core/seq? input__93840_nth_0__)
              (clojure.core/let
               [input__93840_nth_0___l__
                (clojure.core/take 1 input__93840_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__93840_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__93840_nth_0___r__
                  (clojure.core/drop 1 input__93840_nth_0__)]
                 (clojure.core/let
                  [input__93840_nth_0___l___nth_0__
                   (clojure.core/nth input__93840_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__93840_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__94734
                     (clojure.core/namespace
                      input__93840_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__93850 X__94734]
                     (clojure.core/let
                      [X__94736
                       (clojure.core/name
                        input__93840_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__94736
                       ("string")
                       (clojure.core/let
                        [x__30988__auto__
                         (def__94724 input__93840_nth_1__ ?__93850)]
                        (if
                         (meander.runtime.zeta/fail? x__30988__auto__)
                         (state__94830)
                         (clojure.core/let
                          [[?__93850] x__30988__auto__]
                          (if
                           (clojure.core/vector? input__93840)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__93840)
                             2)
                            (clojure.core/let
                             [input__93840_nth_0__
                              (clojure.core/nth input__93840 0)
                              input__93840_nth_1__
                              (clojure.core/nth input__93840 1)]
                             (if
                              (clojure.core/seq? input__93840_nth_0__)
                              (clojure.core/let
                               [input__93840_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__93840_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__93840_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__93840_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__93840_nth_0__)]
                                 (clojure.core/let
                                  [?sequence input__93840_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__93840_nth_0__]
                                   (clojure.core/let
                                    [?env input__93840_nth_1__]
                                    (try
                                     [{:tag :string,
                                       :next
                                       (clojure.core/let
                                        [CATA_RESULT__32291__auto__
                                         (CATA__FN__93885
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
                                (state__94830)))
                              (state__94830)))
                            (state__94830))
                           (state__94830)))))
                       (state__94830)))))
                   (state__94830))))
                (state__94830)))
              (state__94830)))
            (state__94830))
           (state__94830))))
        (state__94830
         []
         (if
          (clojure.core/vector? input__93840)
          (if
           (clojure.core/= (clojure.core/count input__93840) 2)
           (clojure.core/let
            [input__93840_nth_0__ (clojure.core/nth input__93840 0)]
            (clojure.core/letfn
             [(state__94997
               []
               (clojure.core/let
                [input__93840_nth_1__
                 (clojure.core/nth input__93840 1)]
                (clojure.core/letfn
                 [(state__95002
                   []
                   (if
                    (clojure.core/seq? input__93840_nth_0__)
                    (clojure.core/let
                     [?sequence input__93840_nth_0__]
                     (clojure.core/let
                      [?env input__93840_nth_1__]
                      (try
                       [{:tag :seq,
                         :next
                         (clojure.core/let
                          [CATA_RESULT__32291__auto__
                           (CATA__FN__93885
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
                    (state__95003)))
                  (state__95003
                   []
                   (if
                    (clojure.core/map? input__93840_nth_0__)
                    (clojure.core/let
                     [?map input__93840_nth_0__]
                     (clojure.core/let
                      [?env input__93840_nth_1__]
                      (try
                       [{:tag :map,
                         :next
                         (clojure.core/let
                          [CATA_RESULT__32291__auto__
                           (CATA__FN__93885
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
                    (state__94998)))]
                 (state__95002))))
              (state__94998
               []
               (if
                (clojure.core/symbol? input__93840_nth_0__)
                (clojure.core/let
                 [X__94766
                  (clojure.core/namespace input__93840_nth_0__)]
                 (clojure.core/let
                  [X__94768 (clojure.core/name input__93840_nth_0__)]
                  (if
                   (clojure.core/string? X__94768)
                   (clojure.core/letfn
                    [(state__95004
                      []
                      (clojure.core/let
                       [ret__94769
                        (clojure.core/re-matches #"_.*" X__94768)]
                       (if
                        (clojure.core/some? ret__94769)
                        (clojure.core/let
                         [?name ret__94769]
                         (clojure.core/let
                          [?symbol input__93840_nth_0__]
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
                        (state__95005))))
                     (state__95005
                      []
                      (clojure.core/let
                       [ret__94776
                        (clojure.core/re-matches #".+#" X__94768)]
                       (if
                        (clojure.core/some? ret__94776)
                        (clojure.core/let
                         [?name ret__94776]
                         (clojure.core/let
                          [?symbol input__93840_nth_0__]
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
                        (state__95006))))
                     (state__95006
                      []
                      (clojure.core/let
                       [ret__94783
                        (clojure.core/re-matches #"%.+" X__94768)]
                       (if
                        (clojure.core/some? ret__94783)
                        (clojure.core/let
                         [?name ret__94783]
                         (clojure.core/let
                          [?symbol input__93840_nth_0__]
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
                        (state__95007))))
                     (state__95007
                      []
                      (clojure.core/let
                       [ret__94790
                        (clojure.core/re-matches #"\*.+" X__94768)]
                       (if
                        (clojure.core/some? ret__94790)
                        (clojure.core/let
                         [?name ret__94790]
                         (clojure.core/let
                          [?symbol input__93840_nth_0__]
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
                        (state__95008))))
                     (state__95008
                      []
                      (clojure.core/let
                       [ret__94797
                        (clojure.core/re-matches #"\!.+" X__94768)]
                       (if
                        (clojure.core/some? ret__94797)
                        (clojure.core/let
                         [?name ret__94797]
                         (clojure.core/let
                          [?symbol input__93840_nth_0__]
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
                        (state__95009))))
                     (state__95009
                      []
                      (clojure.core/let
                       [ret__94804
                        (clojure.core/re-matches #"\?.+" X__94768)]
                       (if
                        (clojure.core/some? ret__94804)
                        (clojure.core/let
                         [?name ret__94804]
                         (clojure.core/let
                          [?symbol input__93840_nth_0__]
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
                        (state__94999))))]
                    (state__95004))
                   (state__94999))))
                (state__94999)))
              (state__94999
               []
               (if
                (string? input__93840_nth_0__)
                (clojure.core/let
                 [?x input__93840_nth_0__]
                 (try
                  [{:tag :literal, :type :string, :form ?x}]
                  (catch
                   java.lang.Exception
                   e__33231__auto__
                   (if
                    (meander.runtime.zeta/fail? e__33231__auto__)
                    (meander.runtime.zeta/fail)
                    (throw e__33231__auto__)))))
                (state__95000)))
              (state__95000
               []
               (if
                (char? input__93840_nth_0__)
                (clojure.core/let
                 [?x input__93840_nth_0__]
                 (try
                  [{:tag :literal, :type :char, :form ?x}]
                  (catch
                   java.lang.Exception
                   e__33231__auto__
                   (if
                    (meander.runtime.zeta/fail? e__33231__auto__)
                    (meander.runtime.zeta/fail)
                    (throw e__33231__auto__)))))
                (state__95001)))
              (state__95001
               []
               (clojure.core/let
                [?x input__93840_nth_0__]
                (try
                 [{:tag :literal, :form ?x}]
                 (catch
                  java.lang.Exception
                  e__33231__auto__
                  (if
                   (meander.runtime.zeta/fail? e__33231__auto__)
                   (meander.runtime.zeta/fail)
                   (throw e__33231__auto__))))))]
             (state__94997)))
           (state__94831))
          (state__94831)))
        (state__94831
         []
         (clojure.core/let
          [?x input__93840]
          (try
           [?x]
           (catch
            java.lang.Exception
            e__33231__auto__
            (if
             (meander.runtime.zeta/fail? e__33231__auto__)
             (meander.runtime.zeta/fail)
             (throw e__33231__auto__))))))]
       (state__94811)))]
    (clojure.core/let
     [x__30988__auto__ (CATA__FN__93885 input__93840)]
     (if
      (meander.runtime.zeta/fail? x__30988__auto__)
      (meander.runtime.zeta/fail)
      (clojure.core/let
       [[CATA_RETURN__93888] x__30988__auto__]
       CATA_RETURN__93888))))]
  (if
   (meander.runtime.zeta/fail? ret__31168__auto__)
   nil
   ret__31168__auto__)))
