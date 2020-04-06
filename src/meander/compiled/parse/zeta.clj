(ns meander.compiled.parse.zeta (:require [meander.runtime.zeta]))
(clojure.core/defn
 parse
 [input__62968]
 (let*
  [ret__14518__auto__
   (clojure.core/letfn
    [(CATA__FN__63051
      [input__62968]
      (clojure.core/letfn
       [(state__64557
         []
         (if
          (clojure.core/vector? input__62968)
          (if
           (clojure.core/= (clojure.core/count input__62968) 3)
           (clojure.core/let
            [input__62968_nth_0__
             (clojure.core/nth input__62968 0)
             input__62968_nth_1__
             (clojure.core/nth input__62968 1)
             input__62968_nth_2__
             (clojure.core/nth input__62968 2)]
            (clojure.core/case
             input__62968_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__62968_nth_1__)
              (clojure.core/letfn
               [(state__64597
                 []
                 (clojure.core/case
                  input__62968_nth_1__
                  ([])
                  (clojure.core/let
                   [?env input__62968_nth_2__]
                   (try
                    [{:tag :empty}]
                    (catch
                     java.lang.Exception
                     e__16581__auto__
                     (if
                      (meander.runtime.zeta/fail? e__16581__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__16581__auto__)))))
                  (state__64598)))
                (state__64598
                 []
                 (clojure.core/let
                  [n__63060
                   (clojure.core/count input__62968_nth_1__)
                   m__63061
                   (clojure.core/max 0 (clojure.core/- n__63060 2))
                   input__62968_nth_1___l__
                   (clojure.core/subvec
                    input__62968_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__62968_nth_1__)
                     m__63061))
                   input__62968_nth_1___r__
                   (clojure.core/subvec input__62968_nth_1__ m__63061)]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__62968_nth_1___r__)
                    2)
                   (clojure.core/let
                    [!xs (clojure.core/vec input__62968_nth_1___l__)]
                    (if
                     (clojure.core/=
                      (clojure.core/count input__62968_nth_1___r__)
                      2)
                     (clojure.core/let
                      [input__62968_nth_1___r___nth_0__
                       (clojure.core/nth input__62968_nth_1___r__ 0)
                       input__62968_nth_1___r___nth_1__
                       (clojure.core/nth input__62968_nth_1___r__ 1)]
                      (clojure.core/case
                       input__62968_nth_1___r___nth_0__
                       (:meander.zeta/as)
                       (clojure.core/let
                        [?pattern input__62968_nth_1___r___nth_1__]
                        (clojure.core/let
                         [?env input__62968_nth_2__]
                         (try
                          [(clojure.core/let
                            [!xs__counter
                             (meander.runtime.zeta/iterator !xs)]
                            {:tag :as,
                             :pattern
                             (clojure.core/let
                              [CATA_RESULT__15641__auto__
                               (CATA__FN__63051 [?pattern ?env])]
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
                               (CATA__FN__63051
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
                       (state__64558)))
                     (state__64558)))
                   (state__64558))))]
               (state__64597))
              (state__64558))
             (state__64558)))
           (state__64558))
          (state__64558)))
        (state__64558
         []
         (clojure.core/letfn
          [(def__63066
            [arg__63101 ?ns]
            (clojure.core/letfn
             [(state__64599
               []
               (clojure.core/let
                [x__63102 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__63102)
                 (clojure.core/let [?env arg__63101] [?env ?ns])
                 (state__64600))))
              (state__64600
               []
               (if
                (clojure.core/map? arg__63101)
                (clojure.core/let
                 [VAL__63103 (.valAt arg__63101 :aliases)]
                 (if
                  (clojure.core/map? VAL__63103)
                  (clojure.core/let
                   [X__63105 (clojure.core/set VAL__63103)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__63105))
                    (clojure.core/loop
                     [search_space__64601 (clojure.core/seq X__63105)]
                     (if
                      (clojure.core/seq search_space__64601)
                      (clojure.core/let
                       [elem__63106
                        (clojure.core/first search_space__64601)
                        result__64602
                        (clojure.core/let
                         [elem__63106_nth_0__
                          (clojure.core/nth elem__63106 0)
                          elem__63106_nth_1__
                          (clojure.core/nth elem__63106 1)]
                         (if
                          (clojure.core/symbol? elem__63106_nth_0__)
                          (clojure.core/let
                           [X__63108
                            (clojure.core/name elem__63106_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__63108)
                            (if
                             (clojure.core/symbol? elem__63106_nth_1__)
                             (clojure.core/let
                              [X__63110
                               (clojure.core/name elem__63106_nth_1__)]
                              (clojure.core/case
                               X__63110
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__63101]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__64602)
                        (recur (clojure.core/next search_space__64601))
                        result__64602))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__64599)))]
          (if
           (clojure.core/vector? input__62968)
           (if
            (clojure.core/= (clojure.core/count input__62968) 3)
            (clojure.core/let
             [input__62968_nth_0__
              (clojure.core/nth input__62968 0)
              input__62968_nth_1__
              (clojure.core/nth input__62968 1)
              input__62968_nth_2__
              (clojure.core/nth input__62968 2)]
             (clojure.core/case
              input__62968_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__62968_nth_1__)
               (clojure.core/loop
                [search_space__64604
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__62968_nth_1__)]
                (if
                 (clojure.core/seq search_space__64604)
                 (clojure.core/let
                  [input__62968_nth_1___parts__
                   (clojure.core/first search_space__64604)
                   result__64605
                   (clojure.core/let
                    [input__62968_nth_1___l__
                     (clojure.core/nth input__62968_nth_1___parts__ 0)
                     input__62968_nth_1___r__
                     (clojure.core/nth input__62968_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__62968_nth_1___l__)]
                     (clojure.core/let
                      [input__62968_nth_1___r___l__
                       (clojure.core/subvec
                        input__62968_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__62968_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__62968_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__62968_nth_1___r___r__
                         (clojure.core/subvec
                          input__62968_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__62968_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__62968_nth_1___r___l__
                           0)
                          input__62968_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__62968_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__62968_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__63075
                            (clojure.core/namespace
                             input__62968_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__63075]
                            (clojure.core/let
                             [X__63077
                              (clojure.core/name
                               input__62968_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__63077)
                              (clojure.core/let
                               [ret__63078
                                (clojure.core/re-matches
                                 #"&(\d+)"
                                 X__63077)]
                               (if
                                (clojure.core/some? ret__63078)
                                (if
                                 (clojure.core/vector? ret__63078)
                                 (if
                                  (clojure.core/=
                                   (clojure.core/count ret__63078)
                                   2)
                                  (clojure.core/let
                                   [ret__63078_nth_1__
                                    (clojure.core/nth ret__63078 1)]
                                   (clojure.core/let
                                    [?n ret__63078_nth_1__]
                                    (clojure.core/let
                                     [?pattern
                                      input__62968_nth_1___r___l___nth_1__]
                                     (clojure.core/let
                                      [?rest
                                       input__62968_nth_1___r___r__]
                                      (clojure.core/let
                                       [x__14338__auto__
                                        (def__63066
                                         input__62968_nth_2__
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
                                              (CATA__FN__63051
                                               ['meander.dev.parse.zeta/make-join
                                                (clojure.core/let
                                                 [CATA_RESULT__15641__auto__
                                                  (CATA__FN__63051
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
                                                  (CATA__FN__63051
                                                   ['meander.dev.parse.zeta/make-join
                                                    {:tag :slice,
                                                     :size
                                                     (Integer. ?n),
                                                     :pattern
                                                     (clojure.core/let
                                                      [CATA_RESULT__15641__auto__
                                                       (CATA__FN__63051
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
                                                      (CATA__FN__63051
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
                   (meander.runtime.zeta/fail? result__64605)
                   (recur (clojure.core/next search_space__64604))
                   result__64605))
                 (state__64559)))
               (state__64559))
              (state__64559)))
            (state__64559))
           (state__64559))))
        (state__64559
         []
         (clojure.core/letfn
          [(def__63123
            [arg__63155 ?ns]
            (clojure.core/letfn
             [(state__64607
               []
               (clojure.core/let
                [x__63156 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__63156)
                 (clojure.core/let [?env arg__63155] [?env ?ns])
                 (state__64608))))
              (state__64608
               []
               (if
                (clojure.core/map? arg__63155)
                (clojure.core/let
                 [VAL__63157 (.valAt arg__63155 :aliases)]
                 (if
                  (clojure.core/map? VAL__63157)
                  (clojure.core/let
                   [X__63159 (clojure.core/set VAL__63157)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__63159))
                    (clojure.core/loop
                     [search_space__64609 (clojure.core/seq X__63159)]
                     (if
                      (clojure.core/seq search_space__64609)
                      (clojure.core/let
                       [elem__63160
                        (clojure.core/first search_space__64609)
                        result__64610
                        (clojure.core/let
                         [elem__63160_nth_0__
                          (clojure.core/nth elem__63160 0)
                          elem__63160_nth_1__
                          (clojure.core/nth elem__63160 1)]
                         (if
                          (clojure.core/symbol? elem__63160_nth_0__)
                          (clojure.core/let
                           [X__63162
                            (clojure.core/name elem__63160_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__63162)
                            (if
                             (clojure.core/symbol? elem__63160_nth_1__)
                             (clojure.core/let
                              [X__63164
                               (clojure.core/name elem__63160_nth_1__)]
                              (clojure.core/case
                               X__63164
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__63155]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__64610)
                        (recur (clojure.core/next search_space__64609))
                        result__64610))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__64607)))]
          (if
           (clojure.core/vector? input__62968)
           (if
            (clojure.core/= (clojure.core/count input__62968) 3)
            (clojure.core/let
             [input__62968_nth_0__
              (clojure.core/nth input__62968 0)
              input__62968_nth_1__
              (clojure.core/nth input__62968 1)
              input__62968_nth_2__
              (clojure.core/nth input__62968 2)]
             (clojure.core/case
              input__62968_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__62968_nth_1__)
               (clojure.core/loop
                [search_space__64612
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__62968_nth_1__)]
                (if
                 (clojure.core/seq search_space__64612)
                 (clojure.core/let
                  [input__62968_nth_1___parts__
                   (clojure.core/first search_space__64612)
                   result__64613
                   (clojure.core/let
                    [input__62968_nth_1___l__
                     (clojure.core/nth input__62968_nth_1___parts__ 0)
                     input__62968_nth_1___r__
                     (clojure.core/nth input__62968_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__62968_nth_1___l__)]
                     (clojure.core/let
                      [input__62968_nth_1___r___l__
                       (clojure.core/subvec
                        input__62968_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__62968_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__62968_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__62968_nth_1___r___r__
                         (clojure.core/subvec
                          input__62968_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__62968_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__62968_nth_1___r___l__
                           0)
                          input__62968_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__62968_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__62968_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__63132
                            (clojure.core/namespace
                             input__62968_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__63132]
                            (clojure.core/let
                             [X__63134
                              (clojure.core/name
                               input__62968_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__63134)
                              (if
                               (clojure.core/re-matches
                                #"&.*"
                                X__63134)
                               (clojure.core/let
                                [?pattern
                                 input__62968_nth_1___r___l___nth_1__]
                                (clojure.core/let
                                 [?rest input__62968_nth_1___r___r__]
                                 (clojure.core/let
                                  [x__14338__auto__
                                   (def__63123
                                    input__62968_nth_2__
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
                                         (CATA__FN__63051
                                          ['meander.dev.parse.zeta/make-join
                                           (clojure.core/let
                                            [CATA_RESULT__15641__auto__
                                             (CATA__FN__63051
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
                                             (CATA__FN__63051
                                              ['meander.dev.parse.zeta/make-join
                                               (clojure.core/let
                                                [CATA_RESULT__15641__auto__
                                                 (CATA__FN__63051
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
                                                 (CATA__FN__63051
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
                   (meander.runtime.zeta/fail? result__64613)
                   (recur (clojure.core/next search_space__64612))
                   result__64613))
                 (state__64560)))
               (state__64560))
              (state__64560)))
            (state__64560))
           (state__64560))))
        (state__64560
         []
         (if
          (clojure.core/vector? input__62968)
          (clojure.core/letfn
           [(state__64615
             []
             (if
              (clojure.core/= (clojure.core/count input__62968) 3)
              (clojure.core/let
               [input__62968_nth_0__
                (clojure.core/nth input__62968 0)
                input__62968_nth_1__
                (clojure.core/nth input__62968 1)
                input__62968_nth_2__
                (clojure.core/nth input__62968 2)]
               (clojure.core/case
                input__62968_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__62968_nth_1__)
                 (clojure.core/letfn
                  [(state__64618
                    []
                    (clojure.core/let
                     [n__63185
                      (clojure.core/count input__62968_nth_1__)
                      m__63186
                      (clojure.core/max 0 (clojure.core/- n__63185 2))
                      input__62968_nth_1___l__
                      (clojure.core/subvec
                       input__62968_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__62968_nth_1__)
                        m__63186))
                      input__62968_nth_1___r__
                      (clojure.core/subvec
                       input__62968_nth_1__
                       m__63186)]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__62968_nth_1___r__)
                       2)
                      (clojure.core/let
                       [!xs
                        (clojure.core/vec input__62968_nth_1___l__)]
                       (if
                        (clojure.core/=
                         (clojure.core/count input__62968_nth_1___r__)
                         2)
                        (clojure.core/let
                         [input__62968_nth_1___r___nth_0__
                          (clojure.core/nth input__62968_nth_1___r__ 0)
                          input__62968_nth_1___r___nth_1__
                          (clojure.core/nth
                           input__62968_nth_1___r__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__62968_nth_1___r___nth_0__)
                          (clojure.core/let
                           [X__63190
                            (clojure.core/namespace
                             input__62968_nth_1___r___nth_0__)]
                           (clojure.core/let
                            [?ns X__63190]
                            (clojure.core/let
                             [X__63192
                              (clojure.core/name
                               input__62968_nth_1___r___nth_0__)]
                             (if
                              (clojure.core/string? X__63192)
                              (clojure.core/let
                               [ret__63193
                                (clojure.core/re-matches
                                 #"&.*"
                                 X__63192)]
                               (if
                                (clojure.core/some? ret__63193)
                                (clojure.core/let
                                 [?name ret__63193]
                                 (clojure.core/let
                                  [?pattern
                                   input__62968_nth_1___r___nth_1__]
                                  (if
                                   (clojure.core/map?
                                    input__62968_nth_2__)
                                   (clojure.core/let
                                    [VAL__63177
                                     (.valAt
                                      input__62968_nth_2__
                                      :aliases)]
                                    (if
                                     (clojure.core/map? VAL__63177)
                                     (clojure.core/let
                                      [X__63179
                                       (clojure.core/set VAL__63177)]
                                      (if
                                       (clojure.core/<=
                                        1
                                        (clojure.core/count X__63179))
                                       (clojure.core/loop
                                        [search_space__64622
                                         (clojure.core/seq X__63179)]
                                        (if
                                         (clojure.core/seq
                                          search_space__64622)
                                         (clojure.core/let
                                          [elem__63180
                                           (clojure.core/first
                                            search_space__64622)
                                           result__64623
                                           (clojure.core/let
                                            [elem__63180_nth_0__
                                             (clojure.core/nth
                                              elem__63180
                                              0)
                                             elem__63180_nth_1__
                                             (clojure.core/nth
                                              elem__63180
                                              1)]
                                            (if
                                             (clojure.core/symbol?
                                              elem__63180_nth_0__)
                                             (clojure.core/let
                                              [X__63182
                                               (clojure.core/name
                                                elem__63180_nth_0__)]
                                              (if
                                               (clojure.core/=
                                                ?ns
                                                X__63182)
                                               (if
                                                (clojure.core/symbol?
                                                 elem__63180_nth_1__)
                                                (clojure.core/let
                                                 [X__63184
                                                  (clojure.core/name
                                                   elem__63180_nth_1__)]
                                                 (clojure.core/case
                                                  X__63184
                                                  ("meander.zeta")
                                                  (clojure.core/let
                                                   [?env
                                                    input__62968_nth_2__]
                                                   (try
                                                    [(clojure.core/let
                                                      [!xs__counter
                                                       (meander.runtime.zeta/iterator
                                                        !xs)]
                                                      (clojure.core/let
                                                       [CATA_RESULT__15641__auto__
                                                        (CATA__FN__63051
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
                                            result__64623)
                                           (recur
                                            (clojure.core/next
                                             search_space__64622))
                                           result__64623))
                                         (state__64619)))
                                       (state__64619)))
                                     (state__64619)))
                                   (state__64619))))
                                (state__64619)))
                              (state__64619)))))
                          (state__64619)))
                        (state__64619)))
                      (state__64619))))
                   (state__64619
                    []
                    (clojure.core/loop
                     [search_space__64625
                      (meander.runtime.zeta/epsilon-partitions
                       2
                       input__62968_nth_1__)]
                     (if
                      (clojure.core/seq search_space__64625)
                      (clojure.core/let
                       [input__62968_nth_1___parts__
                        (clojure.core/first search_space__64625)
                        result__64626
                        (clojure.core/let
                         [input__62968_nth_1___l__
                          (clojure.core/nth
                           input__62968_nth_1___parts__
                           0)
                          input__62968_nth_1___r__
                          (clojure.core/nth
                           input__62968_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs
                           (clojure.core/vec input__62968_nth_1___l__)]
                          (clojure.core/let
                           [input__62968_nth_1___r___l__
                            (clojure.core/subvec
                             input__62968_nth_1___r__
                             0
                             (clojure.core/min
                              (clojure.core/count
                               input__62968_nth_1___r__)
                              1))]
                           (if
                            (clojure.core/=
                             (clojure.core/count
                              input__62968_nth_1___r___l__)
                             1)
                            (clojure.core/let
                             [input__62968_nth_1___r___r__
                              (clojure.core/subvec
                               input__62968_nth_1___r__
                               1)]
                             (if
                              (clojure.core/=
                               input__62968_nth_1___r___l__
                               ['.])
                              (clojure.core/let
                               [?rest input__62968_nth_1___r___r__]
                               (clojure.core/let
                                [?env input__62968_nth_2__]
                                (try
                                 [(clojure.core/let
                                   [!xs__counter
                                    (meander.runtime.zeta/iterator
                                     !xs)]
                                   (clojure.core/let
                                    [CATA_RESULT__15641__auto__
                                     (CATA__FN__63051
                                      ['meander.dev.parse.zeta/make-join
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__63051
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
                                         (CATA__FN__63051
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
                        (meander.runtime.zeta/fail? result__64626)
                        (recur (clojure.core/next search_space__64625))
                        result__64626))
                      (state__64620))))
                   (state__64620
                    []
                    (clojure.core/let
                     [input__62968_nth_1___l__
                      (clojure.core/subvec
                       input__62968_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__62968_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__62968_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__62968_nth_1___r__
                        (clojure.core/subvec input__62968_nth_1__ 1)]
                       (if
                        (clojure.core/=
                         input__62968_nth_1___l__
                         ['...])
                        (clojure.core/let
                         [?rest input__62968_nth_1___r__]
                         (clojure.core/let
                          [?env input__62968_nth_2__]
                          (try
                           [(clojure.core/let
                             [CATA_RESULT__15641__auto__
                              (CATA__FN__63051
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
                        (state__64621)))
                      (state__64621))))
                   (state__64621
                    []
                    (clojure.core/loop
                     [search_space__64628
                      (meander.runtime.zeta/epsilon-partitions
                       2
                       input__62968_nth_1__)]
                     (if
                      (clojure.core/seq search_space__64628)
                      (clojure.core/let
                       [input__62968_nth_1___parts__
                        (clojure.core/first search_space__64628)
                        result__64629
                        (clojure.core/let
                         [input__62968_nth_1___l__
                          (clojure.core/nth
                           input__62968_nth_1___parts__
                           0)
                          input__62968_nth_1___r__
                          (clojure.core/nth
                           input__62968_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs []]
                          (clojure.core/let
                           [ret__14502__auto__
                            (meander.runtime.zeta/epsilon-run-star-1
                             input__62968_nth_1___l__
                             [!xs]
                             (clojure.core/fn
                              [[!xs] input__63210]
                              (clojure.core/let
                               [input__63210_nth_0__
                                (clojure.core/nth input__63210 0)]
                               (clojure.core/letfn
                                [(save__63211
                                  []
                                  (meander.runtime.zeta/fail))
                                 (f__64632
                                  []
                                  (clojure.core/let
                                   [!xs
                                    (clojure.core/conj
                                     !xs
                                     input__63210_nth_0__)]
                                   [!xs]))]
                                (if
                                 (clojure.core/symbol?
                                  input__63210_nth_0__)
                                 (clojure.core/let
                                  [X__63213
                                   (clojure.core/namespace
                                    input__63210_nth_0__)]
                                  (clojure.core/case
                                   X__63213
                                   (nil)
                                   (clojure.core/let
                                    [X__63215
                                     (clojure.core/name
                                      input__63210_nth_0__)]
                                    (if
                                     (clojure.core/string? X__63215)
                                     (if
                                      (clojure.core/re-matches
                                       #"\.\.(?:\.|\d+)"
                                       X__63215)
                                      (save__63211)
                                      (f__64632))
                                     (f__64632)))
                                   (f__64632)))
                                 (f__64632)))))
                             (clojure.core/fn
                              [[!xs]]
                              (clojure.core/let
                               [input__62968_nth_1___r___l__
                                (clojure.core/subvec
                                 input__62968_nth_1___r__
                                 0
                                 (clojure.core/min
                                  (clojure.core/count
                                   input__62968_nth_1___r__)
                                  1))]
                               (if
                                (clojure.core/=
                                 (clojure.core/count
                                  input__62968_nth_1___r___l__)
                                 1)
                                (clojure.core/let
                                 [input__62968_nth_1___r___r__
                                  (clojure.core/subvec
                                   input__62968_nth_1___r__
                                   1)]
                                 (if
                                  (clojure.core/=
                                   input__62968_nth_1___r___l__
                                   ['...])
                                  (clojure.core/let
                                   [?rest input__62968_nth_1___r___r__]
                                   (clojure.core/let
                                    [?env input__62968_nth_2__]
                                    (try
                                     [(clojure.core/let
                                       [!xs__counter
                                        (meander.runtime.zeta/iterator
                                         !xs)]
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__63051
                                          ['meander.dev.parse.zeta/make-star
                                           (clojure.core/let
                                            [CATA_RESULT__15641__auto__
                                             (CATA__FN__63051
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
                                             (CATA__FN__63051
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
                        (meander.runtime.zeta/fail? result__64629)
                        (recur (clojure.core/next search_space__64628))
                        result__64629))
                      (state__64616))))]
                  (state__64618))
                 (state__64616))
                (state__64616)))
              (state__64616)))
            (state__64616
             []
             (if
              (clojure.core/= (clojure.core/count input__62968) 4)
              (clojure.core/let
               [input__62968_nth_0__
                (clojure.core/nth input__62968 0)
                input__62968_nth_1__
                (clojure.core/nth input__62968 1)
                input__62968_nth_2__
                (clojure.core/nth input__62968 2)]
               (clojure.core/letfn
                [(state__64633
                  []
                  (clojure.core/let
                   [input__62968_nth_3__
                    (clojure.core/nth input__62968 3)]
                   (clojure.core/case
                    input__62968_nth_0__
                    (meander.dev.parse.zeta/make-star)
                    (clojure.core/letfn
                     [(state__64635
                       []
                       (if
                        (clojure.core/map? input__62968_nth_1__)
                        (clojure.core/let
                         [VAL__63219
                          (.valAt input__62968_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__63219
                          (:cat)
                          (clojure.core/let
                           [VAL__63220
                            (.valAt input__62968_nth_1__ :sequence)]
                           (if
                            (clojure.core/vector? VAL__63220)
                            (if
                             (clojure.core/=
                              (clojure.core/count VAL__63220)
                              1)
                             (clojure.core/let
                              [VAL__63220_nth_0__
                               (clojure.core/nth VAL__63220 0)]
                              (if
                               (clojure.core/map? VAL__63220_nth_0__)
                               (clojure.core/let
                                [VAL__63225
                                 (.valAt VAL__63220_nth_0__ :tag)]
                                (clojure.core/case
                                 VAL__63225
                                 (:memory-variable)
                                 (clojure.core/let
                                  [?memory-variable VAL__63220_nth_0__]
                                  (clojure.core/let
                                   [VAL__63221
                                    (.valAt
                                     input__62968_nth_1__
                                     :next)]
                                   (if
                                    (clojure.core/map? VAL__63221)
                                    (clojure.core/let
                                     [VAL__63222
                                      (.valAt VAL__63221 :tag)]
                                     (clojure.core/case
                                      VAL__63222
                                      (:empty)
                                      (clojure.core/let
                                       [?next input__62968_nth_2__]
                                       (clojure.core/let
                                        [?env input__62968_nth_3__]
                                        (try
                                         [(clojure.core/let
                                           [CATA_RESULT__15641__auto__
                                            (CATA__FN__63051
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
                                      (state__64636)))
                                    (state__64636))))
                                 (state__64636)))
                               (state__64636)))
                             (state__64636))
                            (state__64636)))
                          (state__64636)))
                        (state__64636)))
                      (state__64636
                       []
                       (clojure.core/let
                        [?pattern input__62968_nth_1__]
                        (clojure.core/let
                         [?next input__62968_nth_2__]
                         (if
                          (clojure.core/map? input__62968_nth_3__)
                          (clojure.core/let
                           [VAL__63228
                            (.valAt input__62968_nth_3__ :context)]
                           (clojure.core/case
                            VAL__63228
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
                            (state__64634)))
                          (state__64634)))))]
                     (state__64635))
                    (state__64634))))
                 (state__64634
                  []
                  (clojure.core/case
                   input__62968_nth_0__
                   (meander.dev.parse.zeta/make-star)
                   (clojure.core/let
                    [?pattern input__62968_nth_1__]
                    (clojure.core/let
                     [?next input__62968_nth_2__]
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
                   (state__64617)))]
                (state__64633)))
              (state__64617)))
            (state__64617
             []
             (if
              (clojure.core/= (clojure.core/count input__62968) 3)
              (clojure.core/let
               [input__62968_nth_0__
                (clojure.core/nth input__62968 0)
                input__62968_nth_1__
                (clojure.core/nth input__62968 1)
                input__62968_nth_2__
                (clojure.core/nth input__62968 2)]
               (clojure.core/case
                input__62968_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__62968_nth_1__)
                 (clojure.core/let
                  [input__62968_nth_1___l__
                   (clojure.core/subvec
                    input__62968_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__62968_nth_1__)
                     1))]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__62968_nth_1___l__)
                    1)
                   (clojure.core/let
                    [input__62968_nth_1___r__
                     (clojure.core/subvec input__62968_nth_1__ 1)]
                    (clojure.core/let
                     [input__62968_nth_1___l___nth_0__
                      (clojure.core/nth input__62968_nth_1___l__ 0)]
                     (if
                      (clojure.core/symbol?
                       input__62968_nth_1___l___nth_0__)
                      (clojure.core/let
                       [X__63236
                        (clojure.core/namespace
                         input__62968_nth_1___l___nth_0__)]
                       (clojure.core/case
                        X__63236
                        (nil)
                        (clojure.core/let
                         [X__63238
                          (clojure.core/name
                           input__62968_nth_1___l___nth_0__)]
                         (if
                          (clojure.core/string? X__63238)
                          (clojure.core/let
                           [ret__63239
                            (clojure.core/re-matches
                             #"\.\.(\d+)"
                             X__63238)]
                           (if
                            (clojure.core/some? ret__63239)
                            (if
                             (clojure.core/vector? ret__63239)
                             (if
                              (clojure.core/=
                               (clojure.core/count ret__63239)
                               2)
                              (clojure.core/let
                               [ret__63239_nth_1__
                                (clojure.core/nth ret__63239 1)]
                               (clojure.core/let
                                [?n ret__63239_nth_1__]
                                (clojure.core/let
                                 [?operator
                                  input__62968_nth_1___l___nth_0__]
                                 (clojure.core/let
                                  [?rest input__62968_nth_1___r__]
                                  (clojure.core/let
                                   [?env input__62968_nth_2__]
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
                              (state__64561))
                             (state__64561))
                            (state__64561)))
                          (state__64561)))
                        (state__64561)))
                      (state__64561))))
                   (state__64561)))
                 (state__64561))
                (state__64561)))
              (state__64561)))]
           (state__64615))
          (state__64561)))
        (state__64561
         []
         (clojure.core/letfn
          [(def__63242
            [arg__63266]
            (clojure.core/letfn
             [(state__64637
               []
               (clojure.core/let
                [x__63267 :string-plus]
                (clojure.core/let
                 [?tag x__63267]
                 (if
                  (clojure.core/map? arg__63266)
                  (clojure.core/let
                   [VAL__63268 (.valAt arg__63266 :context)]
                   (clojure.core/case
                    VAL__63268
                    (:string)
                    (clojure.core/let [?env arg__63266] [?tag ?env])
                    (state__64638)))
                  (state__64638)))))
              (state__64638
               []
               (clojure.core/let
                [x__63269 :plus]
                (clojure.core/let
                 [?tag x__63269]
                 (clojure.core/let [?env arg__63266] [?tag ?env]))))]
             (state__64637)))]
          (if
           (clojure.core/vector? input__62968)
           (if
            (clojure.core/= (clojure.core/count input__62968) 3)
            (clojure.core/let
             [input__62968_nth_0__
              (clojure.core/nth input__62968 0)
              input__62968_nth_1__
              (clojure.core/nth input__62968 1)
              input__62968_nth_2__
              (clojure.core/nth input__62968 2)]
             (clojure.core/case
              input__62968_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__62968_nth_1__)
               (clojure.core/loop
                [search_space__64639
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__62968_nth_1__)]
                (if
                 (clojure.core/seq search_space__64639)
                 (clojure.core/let
                  [input__62968_nth_1___parts__
                   (clojure.core/first search_space__64639)
                   result__64640
                   (clojure.core/let
                    [input__62968_nth_1___l__
                     (clojure.core/nth input__62968_nth_1___parts__ 0)
                     input__62968_nth_1___r__
                     (clojure.core/nth input__62968_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__14502__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__62968_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__63259]
                         (clojure.core/let
                          [input__63259_nth_0__
                           (clojure.core/nth input__63259 0)]
                          (clojure.core/letfn
                           [(save__63260
                             []
                             (meander.runtime.zeta/fail))
                            (f__64643
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__63259_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol? input__63259_nth_0__)
                            (clojure.core/let
                             [X__63262
                              (clojure.core/namespace
                               input__63259_nth_0__)]
                             (clojure.core/case
                              X__63262
                              (nil)
                              (clojure.core/let
                               [X__63264
                                (clojure.core/name
                                 input__63259_nth_0__)]
                               (if
                                (clojure.core/string? X__63264)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__63264)
                                 (save__63260)
                                 (f__64643))
                                (f__64643)))
                              (f__64643)))
                            (f__64643)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__62968_nth_1___r___l__
                           (clojure.core/subvec
                            input__62968_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__62968_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__62968_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__62968_nth_1___r___r__
                             (clojure.core/subvec
                              input__62968_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__62968_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__62968_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__62968_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__63253
                                (clojure.core/namespace
                                 input__62968_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__63253
                                (nil)
                                (clojure.core/let
                                 [X__63255
                                  (clojure.core/name
                                   input__62968_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__63255)
                                  (clojure.core/let
                                   [ret__63256
                                    (clojure.core/re-matches
                                     #"\.\.(\d+)"
                                     X__63255)]
                                   (if
                                    (clojure.core/some? ret__63256)
                                    (if
                                     (clojure.core/vector? ret__63256)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__63256)
                                       2)
                                      (clojure.core/let
                                       [ret__63256_nth_1__
                                        (clojure.core/nth
                                         ret__63256
                                         1)]
                                       (clojure.core/let
                                        [?n ret__63256_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__62968_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__14338__auto__
                                           (def__63242
                                            input__62968_nth_2__)]
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
                                                  (CATA__FN__63051
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
                                                  (CATA__FN__63051
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
                   (meander.runtime.zeta/fail? result__64640)
                   (recur (clojure.core/next search_space__64639))
                   result__64640))
                 (state__64562)))
               (state__64562))
              (state__64562)))
            (state__64562))
           (state__64562))))
        (state__64562
         []
         (if
          (clojure.core/vector? input__62968)
          (if
           (clojure.core/= (clojure.core/count input__62968) 3)
           (clojure.core/let
            [input__62968_nth_0__
             (clojure.core/nth input__62968 0)
             input__62968_nth_1__
             (clojure.core/nth input__62968 1)
             input__62968_nth_2__
             (clojure.core/nth input__62968 2)]
            (clojure.core/case
             input__62968_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__62968_nth_1__)
              (clojure.core/let
               [input__62968_nth_1___l__
                (clojure.core/subvec
                 input__62968_nth_1__
                 0
                 (clojure.core/min
                  (clojure.core/count input__62968_nth_1__)
                  1))]
               (if
                (clojure.core/=
                 (clojure.core/count input__62968_nth_1___l__)
                 1)
                (clojure.core/let
                 [input__62968_nth_1___r__
                  (clojure.core/subvec input__62968_nth_1__ 1)]
                 (clojure.core/let
                  [input__62968_nth_1___l___nth_0__
                   (clojure.core/nth input__62968_nth_1___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__62968_nth_1___l___nth_0__)
                   (clojure.core/let
                    [X__63287
                     (clojure.core/namespace
                      input__62968_nth_1___l___nth_0__)]
                    (clojure.core/case
                     X__63287
                     (nil)
                     (clojure.core/let
                      [X__63289
                       (clojure.core/name
                        input__62968_nth_1___l___nth_0__)]
                      (if
                       (clojure.core/string? X__63289)
                       (clojure.core/let
                        [ret__63290
                         (clojure.core/re-matches
                          #"\.\.(\?.+)"
                          X__63289)]
                        (if
                         (clojure.core/some? ret__63290)
                         (if
                          (clojure.core/vector? ret__63290)
                          (if
                           (clojure.core/=
                            (clojure.core/count ret__63290)
                            2)
                           (clojure.core/let
                            [ret__63290_nth_1__
                             (clojure.core/nth ret__63290 1)]
                            (clojure.core/let
                             [?n ret__63290_nth_1__]
                             (clojure.core/let
                              [?operator
                               input__62968_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?rest input__62968_nth_1___r__]
                               (clojure.core/let
                                [?env input__62968_nth_2__]
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
                           (state__64563))
                          (state__64563))
                         (state__64563)))
                       (state__64563)))
                     (state__64563)))
                   (state__64563))))
                (state__64563)))
              (state__64563))
             (state__64563)))
           (state__64563))
          (state__64563)))
        (state__64563
         []
         (clojure.core/letfn
          [(def__63293
            [arg__63317]
            (clojure.core/letfn
             [(state__64644
               []
               (clojure.core/let
                [x__63318 :string-logical-plus]
                (clojure.core/let
                 [?tag x__63318]
                 (if
                  (clojure.core/map? arg__63317)
                  (clojure.core/let
                   [VAL__63319 (.valAt arg__63317 :context)]
                   (clojure.core/case
                    VAL__63319
                    (:string)
                    (clojure.core/let [?env arg__63317] [?tag ?env])
                    (state__64645)))
                  (state__64645)))))
              (state__64645
               []
               (clojure.core/let
                [x__63320 :logical-plus]
                (clojure.core/let
                 [?tag x__63320]
                 (clojure.core/let [?env arg__63317] [?tag ?env]))))]
             (state__64644)))]
          (if
           (clojure.core/vector? input__62968)
           (if
            (clojure.core/= (clojure.core/count input__62968) 3)
            (clojure.core/let
             [input__62968_nth_0__
              (clojure.core/nth input__62968 0)
              input__62968_nth_1__
              (clojure.core/nth input__62968 1)
              input__62968_nth_2__
              (clojure.core/nth input__62968 2)]
             (clojure.core/case
              input__62968_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__62968_nth_1__)
               (clojure.core/loop
                [search_space__64646
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__62968_nth_1__)]
                (if
                 (clojure.core/seq search_space__64646)
                 (clojure.core/let
                  [input__62968_nth_1___parts__
                   (clojure.core/first search_space__64646)
                   result__64647
                   (clojure.core/let
                    [input__62968_nth_1___l__
                     (clojure.core/nth input__62968_nth_1___parts__ 0)
                     input__62968_nth_1___r__
                     (clojure.core/nth input__62968_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__14502__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__62968_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__63310]
                         (clojure.core/let
                          [input__63310_nth_0__
                           (clojure.core/nth input__63310 0)]
                          (clojure.core/letfn
                           [(save__63311
                             []
                             (meander.runtime.zeta/fail))
                            (f__64650
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__63310_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol? input__63310_nth_0__)
                            (clojure.core/let
                             [X__63313
                              (clojure.core/namespace
                               input__63310_nth_0__)]
                             (clojure.core/case
                              X__63313
                              (nil)
                              (clojure.core/let
                               [X__63315
                                (clojure.core/name
                                 input__63310_nth_0__)]
                               (if
                                (clojure.core/string? X__63315)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__63315)
                                 (save__63311)
                                 (f__64650))
                                (f__64650)))
                              (f__64650)))
                            (f__64650)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__62968_nth_1___r___l__
                           (clojure.core/subvec
                            input__62968_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__62968_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__62968_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__62968_nth_1___r___r__
                             (clojure.core/subvec
                              input__62968_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__62968_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__62968_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__62968_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__63304
                                (clojure.core/namespace
                                 input__62968_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__63304
                                (nil)
                                (clojure.core/let
                                 [X__63306
                                  (clojure.core/name
                                   input__62968_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__63306)
                                  (clojure.core/let
                                   [ret__63307
                                    (clojure.core/re-matches
                                     #"\.\.(\?.+)"
                                     X__63306)]
                                   (if
                                    (clojure.core/some? ret__63307)
                                    (if
                                     (clojure.core/vector? ret__63307)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__63307)
                                       2)
                                      (clojure.core/let
                                       [ret__63307_nth_1__
                                        (clojure.core/nth
                                         ret__63307
                                         1)]
                                       (clojure.core/let
                                        [?n ret__63307_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__62968_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__14338__auto__
                                           (def__63293
                                            input__62968_nth_2__)]
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
                                                  (CATA__FN__63051
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
                                                  (CATA__FN__63051
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
                   (meander.runtime.zeta/fail? result__64647)
                   (recur (clojure.core/next search_space__64646))
                   result__64647))
                 (state__64564)))
               (state__64564))
              (state__64564)))
            (state__64564))
           (state__64564))))
        (state__64564
         []
         (if
          (clojure.core/vector? input__62968)
          (if
           (clojure.core/= (clojure.core/count input__62968) 3)
           (clojure.core/let
            [input__62968_nth_0__
             (clojure.core/nth input__62968 0)
             input__62968_nth_1__
             (clojure.core/nth input__62968 1)
             input__62968_nth_2__
             (clojure.core/nth input__62968 2)]
            (clojure.core/case
             input__62968_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__62968_nth_1__)
              (clojure.core/let
               [input__62968_nth_1___l__
                (clojure.core/subvec
                 input__62968_nth_1__
                 0
                 (clojure.core/min
                  (clojure.core/count input__62968_nth_1__)
                  1))]
               (if
                (clojure.core/=
                 (clojure.core/count input__62968_nth_1___l__)
                 1)
                (clojure.core/let
                 [input__62968_nth_1___r__
                  (clojure.core/subvec input__62968_nth_1__ 1)]
                 (clojure.core/let
                  [input__62968_nth_1___l___nth_0__
                   (clojure.core/nth input__62968_nth_1___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__62968_nth_1___l___nth_0__)
                   (clojure.core/let
                    [X__63338
                     (clojure.core/namespace
                      input__62968_nth_1___l___nth_0__)]
                    (clojure.core/case
                     X__63338
                     (nil)
                     (clojure.core/let
                      [X__63340
                       (clojure.core/name
                        input__62968_nth_1___l___nth_0__)]
                      (if
                       (clojure.core/string? X__63340)
                       (clojure.core/let
                        [ret__63341
                         (clojure.core/re-matches
                          #"\.\.(!.+)"
                          X__63340)]
                        (if
                         (clojure.core/some? ret__63341)
                         (if
                          (clojure.core/vector? ret__63341)
                          (if
                           (clojure.core/=
                            (clojure.core/count ret__63341)
                            2)
                           (clojure.core/let
                            [ret__63341_nth_1__
                             (clojure.core/nth ret__63341 1)]
                            (clojure.core/let
                             [?n ret__63341_nth_1__]
                             (clojure.core/let
                              [?operator
                               input__62968_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?rest input__62968_nth_1___r__]
                               (clojure.core/let
                                [?env input__62968_nth_2__]
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
                           (state__64565))
                          (state__64565))
                         (state__64565)))
                       (state__64565)))
                     (state__64565)))
                   (state__64565))))
                (state__64565)))
              (state__64565))
             (state__64565)))
           (state__64565))
          (state__64565)))
        (state__64565
         []
         (clojure.core/letfn
          [(def__63344
            [arg__63368]
            (clojure.core/letfn
             [(state__64651
               []
               (clojure.core/let
                [x__63369 :string-memory-plus]
                (clojure.core/let
                 [?tag x__63369]
                 (if
                  (clojure.core/map? arg__63368)
                  (clojure.core/let
                   [VAL__63370 (.valAt arg__63368 :context)]
                   (clojure.core/case
                    VAL__63370
                    (:string)
                    (clojure.core/let [?env arg__63368] [?tag ?env])
                    (state__64652)))
                  (state__64652)))))
              (state__64652
               []
               (clojure.core/let
                [x__63371 :memory-plus]
                (clojure.core/let
                 [?tag x__63371]
                 (clojure.core/let [?env arg__63368] [?tag ?env]))))]
             (state__64651)))]
          (if
           (clojure.core/vector? input__62968)
           (if
            (clojure.core/= (clojure.core/count input__62968) 3)
            (clojure.core/let
             [input__62968_nth_0__
              (clojure.core/nth input__62968 0)
              input__62968_nth_1__
              (clojure.core/nth input__62968 1)
              input__62968_nth_2__
              (clojure.core/nth input__62968 2)]
             (clojure.core/case
              input__62968_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__62968_nth_1__)
               (clojure.core/loop
                [search_space__64653
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__62968_nth_1__)]
                (if
                 (clojure.core/seq search_space__64653)
                 (clojure.core/let
                  [input__62968_nth_1___parts__
                   (clojure.core/first search_space__64653)
                   result__64654
                   (clojure.core/let
                    [input__62968_nth_1___l__
                     (clojure.core/nth input__62968_nth_1___parts__ 0)
                     input__62968_nth_1___r__
                     (clojure.core/nth input__62968_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__14502__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__62968_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__63361]
                         (clojure.core/let
                          [input__63361_nth_0__
                           (clojure.core/nth input__63361 0)]
                          (clojure.core/letfn
                           [(save__63362
                             []
                             (meander.runtime.zeta/fail))
                            (f__64657
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__63361_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol? input__63361_nth_0__)
                            (clojure.core/let
                             [X__63364
                              (clojure.core/namespace
                               input__63361_nth_0__)]
                             (clojure.core/case
                              X__63364
                              (nil)
                              (clojure.core/let
                               [X__63366
                                (clojure.core/name
                                 input__63361_nth_0__)]
                               (if
                                (clojure.core/string? X__63366)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__63366)
                                 (save__63362)
                                 (f__64657))
                                (f__64657)))
                              (f__64657)))
                            (f__64657)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__62968_nth_1___r___l__
                           (clojure.core/subvec
                            input__62968_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__62968_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__62968_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__62968_nth_1___r___r__
                             (clojure.core/subvec
                              input__62968_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__62968_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__62968_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__62968_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__63355
                                (clojure.core/namespace
                                 input__62968_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__63355
                                (nil)
                                (clojure.core/let
                                 [X__63357
                                  (clojure.core/name
                                   input__62968_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__63357)
                                  (clojure.core/let
                                   [ret__63358
                                    (clojure.core/re-matches
                                     #"\.\.(\!.+)"
                                     X__63357)]
                                   (if
                                    (clojure.core/some? ret__63358)
                                    (if
                                     (clojure.core/vector? ret__63358)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__63358)
                                       2)
                                      (clojure.core/let
                                       [ret__63358_nth_1__
                                        (clojure.core/nth
                                         ret__63358
                                         1)]
                                       (clojure.core/let
                                        [?n ret__63358_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__62968_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__14338__auto__
                                           (def__63344
                                            input__62968_nth_2__)]
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
                                                  (CATA__FN__63051
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
                                                  (CATA__FN__63051
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
                   (meander.runtime.zeta/fail? result__64654)
                   (recur (clojure.core/next search_space__64653))
                   result__64654))
                 (state__64566)))
               (state__64566))
              (state__64566)))
            (state__64566))
           (state__64566))))
        (state__64566
         []
         (if
          (clojure.core/vector? input__62968)
          (clojure.core/letfn
           [(state__64658
             []
             (if
              (clojure.core/= (clojure.core/count input__62968) 3)
              (clojure.core/let
               [input__62968_nth_0__
                (clojure.core/nth input__62968 0)
                input__62968_nth_1__
                (clojure.core/nth input__62968 1)
                input__62968_nth_2__
                (clojure.core/nth input__62968 2)]
               (clojure.core/case
                input__62968_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__62968_nth_1__)
                 (clojure.core/let
                  [!xs (clojure.core/vec input__62968_nth_1__)]
                  (clojure.core/let
                   [?env input__62968_nth_2__]
                   (try
                    [(clojure.core/let
                      [!xs__counter
                       (meander.runtime.zeta/iterator !xs)]
                      (clojure.core/let
                       [CATA_RESULT__15641__auto__
                        (CATA__FN__63051
                         ['meander.dev.parse.zeta/make-cat
                          (clojure.core/into
                           []
                           (clojure.core/loop
                            [return__63052 (clojure.core/transient [])]
                            (if
                             (clojure.core/and (.hasNext !xs__counter))
                             (recur
                              (clojure.core/conj!
                               return__63052
                               (clojure.core/let
                                [CATA_RESULT__15641__auto__
                                 (CATA__FN__63051
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
                              return__63052))))
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
                 (state__64659))
                (state__64659)))
              (state__64659)))
            (state__64659
             []
             (if
              (clojure.core/= (clojure.core/count input__62968) 4)
              (clojure.core/let
               [input__62968_nth_0__
                (clojure.core/nth input__62968 0)
                input__62968_nth_1__
                (clojure.core/nth input__62968 1)
                input__62968_nth_2__
                (clojure.core/nth input__62968 2)]
               (clojure.core/letfn
                [(state__64661
                  []
                  (clojure.core/let
                   [input__62968_nth_3__
                    (clojure.core/nth input__62968 3)]
                   (clojure.core/case
                    input__62968_nth_0__
                    (meander.dev.parse.zeta/make-cat)
                    (if
                     (clojure.core/vector? input__62968_nth_1__)
                     (clojure.core/letfn
                      [(state__64668
                        []
                        (clojure.core/case
                         input__62968_nth_1__
                         ([])
                         (clojure.core/let
                          [?next input__62968_nth_2__]
                          (clojure.core/let
                           [?env input__62968_nth_3__]
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
                         (state__64669)))
                       (state__64669
                        []
                        (clojure.core/loop
                         [search_space__64670
                          (meander.runtime.zeta/epsilon-partitions
                           2
                           input__62968_nth_1__)]
                         (if
                          (clojure.core/seq search_space__64670)
                          (clojure.core/let
                           [input__62968_nth_1___parts__
                            (clojure.core/first search_space__64670)
                            result__64671
                            (clojure.core/let
                             [input__62968_nth_1___l__
                              (clojure.core/nth
                               input__62968_nth_1___parts__
                               0)
                              input__62968_nth_1___r__
                              (clojure.core/nth
                               input__62968_nth_1___parts__
                               1)]
                             (clojure.core/letfn
                              [(state__64673
                                []
                                (clojure.core/let
                                 [!xs []]
                                 (clojure.core/let
                                  [ret__14502__auto__
                                   (meander.runtime.zeta/epsilon-run-star-1
                                    input__62968_nth_1___l__
                                    [!xs]
                                    (clojure.core/fn
                                     [[!xs] input__63397]
                                     (clojure.core/let
                                      [input__63397_nth_0__
                                       (clojure.core/nth
                                        input__63397
                                        0)]
                                      (clojure.core/letfn
                                       [(save__63398
                                         []
                                         (meander.runtime.zeta/fail))
                                        (f__64677
                                         []
                                         (clojure.core/let
                                          [!xs
                                           (clojure.core/conj
                                            !xs
                                            input__63397_nth_0__)]
                                          [!xs]))]
                                       (if
                                        (clojure.core/map?
                                         input__63397_nth_0__)
                                        (clojure.core/let
                                         [VAL__63399
                                          (.valAt
                                           input__63397_nth_0__
                                           :tag)]
                                         (clojure.core/case
                                          VAL__63399
                                          (:group)
                                          (save__63398)
                                          (f__64677)))
                                        (f__64677)))))
                                    (clojure.core/fn
                                     [[!xs]]
                                     (clojure.core/let
                                      [input__62968_nth_1___r___l__
                                       (clojure.core/subvec
                                        input__62968_nth_1___r__
                                        0
                                        (clojure.core/min
                                         (clojure.core/count
                                          input__62968_nth_1___r__)
                                         1))]
                                      (if
                                       (clojure.core/=
                                        (clojure.core/count
                                         input__62968_nth_1___r___l__)
                                        1)
                                       (clojure.core/let
                                        [input__62968_nth_1___r___r__
                                         (clojure.core/subvec
                                          input__62968_nth_1___r__
                                          1)]
                                        (clojure.core/let
                                         [input__62968_nth_1___r___l___nth_0__
                                          (clojure.core/nth
                                           input__62968_nth_1___r___l__
                                           0)]
                                         (if
                                          (clojure.core/map?
                                           input__62968_nth_1___r___l___nth_0__)
                                          (clojure.core/let
                                           [VAL__63396
                                            (.valAt
                                             input__62968_nth_1___r___l___nth_0__
                                             :tag)]
                                           (clojure.core/case
                                            VAL__63396
                                            (:group)
                                            (clojure.core/let
                                             [?group
                                              input__62968_nth_1___r___l___nth_0__]
                                             (clojure.core/let
                                              [?rest
                                               input__62968_nth_1___r___r__]
                                              (clojure.core/let
                                               [?next
                                                input__62968_nth_2__]
                                               (clojure.core/let
                                                [?env
                                                 input__62968_nth_3__]
                                                (try
                                                 [(clojure.core/let
                                                   [!xs__counter
                                                    (meander.runtime.zeta/iterator
                                                     !xs)]
                                                   (clojure.core/let
                                                    [CATA_RESULT__15641__auto__
                                                     (CATA__FN__63051
                                                      ['meander.dev.parse.zeta/make-join
                                                       (clojure.core/let
                                                        [CATA_RESULT__15641__auto__
                                                         (CATA__FN__63051
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
                                                         (CATA__FN__63051
                                                          ['meander.dev.parse.zeta/make-join
                                                           ?group
                                                           (clojure.core/let
                                                            [CATA_RESULT__15641__auto__
                                                             (CATA__FN__63051
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
                                            (state__64674)))
                                          (state__64674))))
                                       (state__64674)))))]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    ret__14502__auto__)
                                   (state__64674)
                                   ret__14502__auto__))))
                               (state__64674
                                []
                                (clojure.core/let
                                 [!xs []]
                                 (clojure.core/let
                                  [ret__14502__auto__
                                   (meander.runtime.zeta/epsilon-run-star-1
                                    input__62968_nth_1___l__
                                    [!xs]
                                    (clojure.core/fn
                                     [[!xs] input__63408]
                                     (clojure.core/let
                                      [input__63408_nth_0__
                                       (clojure.core/nth
                                        input__63408
                                        0)]
                                      (clojure.core/letfn
                                       [(save__63409
                                         []
                                         (meander.runtime.zeta/fail))
                                        (f__64679
                                         []
                                         (clojure.core/let
                                          [!xs
                                           (clojure.core/conj
                                            !xs
                                            input__63408_nth_0__)]
                                          [!xs]))]
                                       (if
                                        (clojure.core/map?
                                         input__63408_nth_0__)
                                        (clojure.core/let
                                         [VAL__63410
                                          (.valAt
                                           input__63408_nth_0__
                                           :tag)]
                                         (clojure.core/case
                                          VAL__63410
                                          (:star)
                                          (save__63409)
                                          (f__64679)))
                                        (f__64679)))))
                                    (clojure.core/fn
                                     [[!xs]]
                                     (clojure.core/let
                                      [input__62968_nth_1___r___l__
                                       (clojure.core/subvec
                                        input__62968_nth_1___r__
                                        0
                                        (clojure.core/min
                                         (clojure.core/count
                                          input__62968_nth_1___r__)
                                         1))]
                                      (if
                                       (clojure.core/=
                                        (clojure.core/count
                                         input__62968_nth_1___r___l__)
                                        1)
                                       (clojure.core/let
                                        [input__62968_nth_1___r___r__
                                         (clojure.core/subvec
                                          input__62968_nth_1___r__
                                          1)]
                                        (clojure.core/let
                                         [input__62968_nth_1___r___l___nth_0__
                                          (clojure.core/nth
                                           input__62968_nth_1___r___l__
                                           0)]
                                         (if
                                          (clojure.core/map?
                                           input__62968_nth_1___r___l___nth_0__)
                                          (clojure.core/let
                                           [VAL__63407
                                            (.valAt
                                             input__62968_nth_1___r___l___nth_0__
                                             :tag)]
                                           (clojure.core/case
                                            VAL__63407
                                            (:star)
                                            (clojure.core/let
                                             [?star
                                              input__62968_nth_1___r___l___nth_0__]
                                             (clojure.core/let
                                              [?rest
                                               input__62968_nth_1___r___r__]
                                              (clojure.core/let
                                               [?next
                                                input__62968_nth_2__]
                                               (clojure.core/let
                                                [?env
                                                 input__62968_nth_3__]
                                                (try
                                                 [(clojure.core/let
                                                   [!xs__counter
                                                    (meander.runtime.zeta/iterator
                                                     !xs)]
                                                   (clojure.core/let
                                                    [CATA_RESULT__15641__auto__
                                                     (CATA__FN__63051
                                                      ['meander.dev.parse.zeta/make-join
                                                       (clojure.core/let
                                                        [CATA_RESULT__15641__auto__
                                                         (CATA__FN__63051
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
                                                         (CATA__FN__63051
                                                          ['meander.dev.parse.zeta/make-join
                                                           ?star
                                                           (clojure.core/let
                                                            [CATA_RESULT__15641__auto__
                                                             (CATA__FN__63051
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
                                            (state__64675)))
                                          (state__64675))))
                                       (state__64675)))))]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    ret__14502__auto__)
                                   (state__64675)
                                   ret__14502__auto__))))
                               (state__64675
                                []
                                (clojure.core/let
                                 [!xs []]
                                 (clojure.core/let
                                  [ret__14502__auto__
                                   (meander.runtime.zeta/epsilon-run-star-1
                                    input__62968_nth_1___l__
                                    [!xs]
                                    (clojure.core/fn
                                     [[!xs] input__63419]
                                     (clojure.core/let
                                      [input__63419_nth_0__
                                       (clojure.core/nth
                                        input__63419
                                        0)]
                                      (clojure.core/letfn
                                       [(save__63420
                                         []
                                         (meander.runtime.zeta/fail))
                                        (f__64681
                                         []
                                         (clojure.core/let
                                          [!xs
                                           (clojure.core/conj
                                            !xs
                                            input__63419_nth_0__)]
                                          [!xs]))]
                                       (if
                                        (clojure.core/map?
                                         input__63419_nth_0__)
                                        (clojure.core/let
                                         [VAL__63421
                                          (.valAt
                                           input__63419_nth_0__
                                           :tag)]
                                         (clojure.core/case
                                          VAL__63421
                                          (:reference)
                                          (save__63420)
                                          (f__64681)))
                                        (f__64681)))))
                                    (clojure.core/fn
                                     [[!xs]]
                                     (clojure.core/let
                                      [input__62968_nth_1___r___l__
                                       (clojure.core/subvec
                                        input__62968_nth_1___r__
                                        0
                                        (clojure.core/min
                                         (clojure.core/count
                                          input__62968_nth_1___r__)
                                         1))]
                                      (if
                                       (clojure.core/=
                                        (clojure.core/count
                                         input__62968_nth_1___r___l__)
                                        1)
                                       (clojure.core/let
                                        [input__62968_nth_1___r___r__
                                         (clojure.core/subvec
                                          input__62968_nth_1___r__
                                          1)]
                                        (clojure.core/let
                                         [input__62968_nth_1___r___l___nth_0__
                                          (clojure.core/nth
                                           input__62968_nth_1___r___l__
                                           0)]
                                         (if
                                          (clojure.core/map?
                                           input__62968_nth_1___r___l___nth_0__)
                                          (clojure.core/let
                                           [VAL__63418
                                            (.valAt
                                             input__62968_nth_1___r___l___nth_0__
                                             :tag)]
                                           (clojure.core/case
                                            VAL__63418
                                            (:reference)
                                            (clojure.core/let
                                             [?reference
                                              input__62968_nth_1___r___l___nth_0__]
                                             (clojure.core/let
                                              [?rest
                                               input__62968_nth_1___r___r__]
                                              (clojure.core/let
                                               [?next
                                                input__62968_nth_2__]
                                               (clojure.core/let
                                                [?env
                                                 input__62968_nth_3__]
                                                (try
                                                 [(clojure.core/let
                                                   [!xs__counter
                                                    (meander.runtime.zeta/iterator
                                                     !xs)]
                                                   (clojure.core/let
                                                    [CATA_RESULT__15641__auto__
                                                     (CATA__FN__63051
                                                      ['meander.dev.parse.zeta/make-join
                                                       (clojure.core/let
                                                        [CATA_RESULT__15641__auto__
                                                         (CATA__FN__63051
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
                                                         (CATA__FN__63051
                                                          ['meander.dev.parse.zeta/make-join
                                                           ?reference
                                                           (clojure.core/let
                                                            [CATA_RESULT__15641__auto__
                                                             (CATA__FN__63051
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
                              (state__64673)))]
                           (if
                            (meander.runtime.zeta/fail? result__64671)
                            (recur
                             (clojure.core/next search_space__64670))
                            result__64671))
                          (state__64662))))]
                      (state__64668))
                     (state__64662))
                    (state__64662))))
                 (state__64662
                  []
                  (clojure.core/case
                   input__62968_nth_0__
                   (meander.dev.parse.zeta/make-cat)
                   (if
                    (clojure.core/vector? input__62968_nth_1__)
                    (clojure.core/let
                     [input__62968_nth_1___l__
                      (clojure.core/subvec
                       input__62968_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__62968_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__62968_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__62968_nth_1___r__
                        (clojure.core/subvec input__62968_nth_1__ 1)]
                       (clojure.core/let
                        [input__62968_nth_1___l___nth_0__
                         (clojure.core/nth input__62968_nth_1___l__ 0)]
                        (if
                         (clojure.core/map?
                          input__62968_nth_1___l___nth_0__)
                         (clojure.core/let
                          [VAL__63430
                           (.valAt
                            input__62968_nth_1___l___nth_0__
                            :tag)]
                          (clojure.core/case
                           VAL__63430
                           (:literal)
                           (clojure.core/let
                            [VAL__63431
                             (.valAt
                              input__62968_nth_1___l___nth_0__
                              :type)]
                            (if
                             (clojure.core/let
                              [x__13398__auto__ VAL__63431]
                              (clojure.core/case
                               x__13398__auto__
                               (:string :char)
                               true
                               false))
                             (clojure.core/let
                              [VAL__63432
                               (.valAt
                                input__62968_nth_1___l___nth_0__
                                :form)]
                              (clojure.core/let
                               [!forms []]
                               (clojure.core/let
                                [!forms
                                 (clojure.core/conj !forms VAL__63432)]
                                (clojure.core/loop
                                 [i__14475__auto__
                                  0
                                  coll__64682
                                  input__62968_nth_1___r__
                                  [!forms]
                                  [!forms]]
                                 (clojure.core/let
                                  [input__63433
                                   (clojure.core/subvec
                                    coll__64682
                                    0
                                    (clojure.core/min
                                     (clojure.core/count coll__64682)
                                     1))]
                                  (if
                                   (clojure.core/=
                                    (clojure.core/count input__63433)
                                    1)
                                   (clojure.core/let
                                    [result__14476__auto__
                                     (clojure.core/let
                                      [input__63433_nth_0__
                                       (clojure.core/nth
                                        input__63433
                                        0)]
                                      (if
                                       (clojure.core/map?
                                        input__63433_nth_0__)
                                       (clojure.core/let
                                        [VAL__63434
                                         (.valAt
                                          input__63433_nth_0__
                                          :tag)]
                                        (clojure.core/case
                                         VAL__63434
                                         (:literal)
                                         (clojure.core/let
                                          [VAL__63435
                                           (.valAt
                                            input__63433_nth_0__
                                            :type)]
                                          (if
                                           (clojure.core/let
                                            [x__13398__auto__
                                             VAL__63435]
                                            (clojure.core/case
                                             x__13398__auto__
                                             (:string :char)
                                             true
                                             false))
                                           (clojure.core/let
                                            [VAL__63436
                                             (.valAt
                                              input__63433_nth_0__
                                              :form)]
                                            (clojure.core/let
                                             [!forms
                                              (clojure.core/conj
                                               !forms
                                               VAL__63436)]
                                             [!forms]))
                                           (meander.runtime.zeta/fail)))
                                         (meander.runtime.zeta/fail)))
                                       (meander.runtime.zeta/fail)))]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      result__14476__auto__)
                                     (state__64663)
                                     (recur
                                      (clojure.core/inc
                                       i__14475__auto__)
                                      (clojure.core/subvec
                                       coll__64682
                                       1)
                                      result__14476__auto__)))
                                   (if
                                    (clojure.core/or
                                     (clojure.core/seq coll__64682)
                                     (clojure.core/<
                                      i__14475__auto__
                                      0))
                                    (state__64663)
                                    (if
                                     (clojure.core/map?
                                      input__62968_nth_2__)
                                     (clojure.core/let
                                      [VAL__63425
                                       (.valAt
                                        input__62968_nth_2__
                                        :tag)]
                                      (clojure.core/case
                                       VAL__63425
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
                                       (state__64663)))
                                     (state__64663)))))))))
                             (state__64663)))
                           (state__64663)))
                         (state__64663))))
                      (state__64663)))
                    (state__64663))
                   (state__64663)))
                 (state__64663
                  []
                  (clojure.core/let
                   [input__62968_nth_3__
                    (clojure.core/nth input__62968 3)]
                   (clojure.core/case
                    input__62968_nth_0__
                    (meander.dev.parse.zeta/make-cat)
                    (clojure.core/letfn
                     [(state__64683
                       []
                       (if
                        (clojure.core/vector? input__62968_nth_1__)
                        (clojure.core/let
                         [input__62968_nth_1___l__
                          (clojure.core/subvec
                           input__62968_nth_1__
                           0
                           (clojure.core/min
                            (clojure.core/count input__62968_nth_1__)
                            1))]
                         (if
                          (clojure.core/=
                           (clojure.core/count
                            input__62968_nth_1___l__)
                           1)
                          (clojure.core/let
                           [input__62968_nth_1___r__
                            (clojure.core/subvec
                             input__62968_nth_1__
                             1)]
                           (clojure.core/let
                            [input__62968_nth_1___l___nth_0__
                             (clojure.core/nth
                              input__62968_nth_1___l__
                              0)]
                            (if
                             (clojure.core/map?
                              input__62968_nth_1___l___nth_0__)
                             (clojure.core/let
                              [VAL__64553
                               (.valAt
                                input__62968_nth_1___l___nth_0__
                                :tag)]
                              (clojure.core/case
                               VAL__64553
                               (:literal)
                               (clojure.core/letfn
                                [(state__64685
                                  []
                                  (clojure.core/let
                                   [VAL__63443
                                    (.valAt
                                     input__62968_nth_1___l___nth_0__
                                     :type)]
                                   (clojure.core/case
                                    VAL__63443
                                    (:string)
                                    (clojure.core/let
                                     [?ast
                                      input__62968_nth_1___l___nth_0__]
                                     (clojure.core/let
                                      [?rest input__62968_nth_1___r__]
                                      (clojure.core/let
                                       [?next input__62968_nth_2__]
                                       (clojure.core/let
                                        [?env input__62968_nth_3__]
                                        (try
                                         [(clojure.core/let
                                           [CATA_RESULT__15641__auto__
                                            (CATA__FN__63051
                                             ['meander.dev.parse.zeta/make-join
                                              ?ast
                                              (clojure.core/let
                                               [CATA_RESULT__15641__auto__
                                                (CATA__FN__63051
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
                                    (state__64686))))
                                 (state__64686
                                  []
                                  (clojure.core/let
                                   [VAL__63453
                                    (.valAt
                                     input__62968_nth_1___l___nth_0__
                                     :form)]
                                   (clojure.core/let
                                    [!forms []]
                                    (clojure.core/let
                                     [!forms
                                      (clojure.core/conj
                                       !forms
                                       VAL__63453)]
                                     (clojure.core/loop
                                      [i__14475__auto__
                                       0
                                       coll__64687
                                       input__62968_nth_1___r__
                                       [!forms]
                                       [!forms]]
                                      (clojure.core/let
                                       [input__63454
                                        (clojure.core/subvec
                                         coll__64687
                                         0
                                         (clojure.core/min
                                          (clojure.core/count
                                           coll__64687)
                                          1))]
                                       (if
                                        (clojure.core/=
                                         (clojure.core/count
                                          input__63454)
                                         1)
                                        (clojure.core/let
                                         [result__14476__auto__
                                          (clojure.core/let
                                           [input__63454_nth_0__
                                            (clojure.core/nth
                                             input__63454
                                             0)]
                                           (if
                                            (clojure.core/map?
                                             input__63454_nth_0__)
                                            (clojure.core/let
                                             [VAL__63455
                                              (.valAt
                                               input__63454_nth_0__
                                               :tag)]
                                             (clojure.core/case
                                              VAL__63455
                                              (:literal)
                                              (clojure.core/let
                                               [VAL__63456
                                                (.valAt
                                                 input__63454_nth_0__
                                                 :form)]
                                               (clojure.core/let
                                                [!forms
                                                 (clojure.core/conj
                                                  !forms
                                                  VAL__63456)]
                                                [!forms]))
                                              (meander.runtime.zeta/fail)))
                                            (meander.runtime.zeta/fail)))]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           result__14476__auto__)
                                          (state__64684)
                                          (recur
                                           (clojure.core/inc
                                            i__14475__auto__)
                                           (clojure.core/subvec
                                            coll__64687
                                            1)
                                           result__14476__auto__)))
                                        (if
                                         (clojure.core/or
                                          (clojure.core/seq
                                           coll__64687)
                                          (clojure.core/<
                                           i__14475__auto__
                                           0))
                                         (state__64684)
                                         (if
                                          (clojure.core/map?
                                           input__62968_nth_2__)
                                          (clojure.core/let
                                           [VAL__63446
                                            (.valAt
                                             input__62968_nth_2__
                                             :tag)]
                                           (clojure.core/case
                                            VAL__63446
                                            (:empty)
                                            (if
                                             (clojure.core/map?
                                              input__62968_nth_3__)
                                             (clojure.core/let
                                              [VAL__63447
                                               (.valAt
                                                input__62968_nth_3__
                                                :context)]
                                              (clojure.core/let
                                               [?context VAL__63447]
                                               (clojure.core/let
                                                [?env
                                                 input__62968_nth_3__]
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
                                             (state__64684))
                                            (state__64684)))
                                          (state__64684))))))))))]
                                (state__64685))
                               (state__64684)))
                             (state__64684))))
                          (state__64684)))
                        (state__64684)))
                      (state__64684
                       []
                       (clojure.core/let
                        [?sequence input__62968_nth_1__]
                        (clojure.core/let
                         [?next input__62968_nth_2__]
                         (if
                          (clojure.core/map? input__62968_nth_3__)
                          (clojure.core/let
                           [VAL__63460
                            (.valAt input__62968_nth_3__ :context)]
                           (clojure.core/case
                            VAL__63460
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
                            (state__64664)))
                          (state__64664)))))]
                     (state__64683))
                    (state__64664))))
                 (state__64664
                  []
                  (clojure.core/case
                   input__62968_nth_0__
                   (meander.dev.parse.zeta/make-cat)
                   (clojure.core/let
                    [?sequence input__62968_nth_1__]
                    (clojure.core/let
                     [?next input__62968_nth_2__]
                     (try
                      [{:tag :cat, :sequence ?sequence, :next ?next}]
                      (catch
                       java.lang.Exception
                       e__16581__auto__
                       (if
                        (meander.runtime.zeta/fail? e__16581__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__16581__auto__))))))
                   (state__64665)))
                 (state__64665
                  []
                  (clojure.core/let
                   [input__62968_nth_3__
                    (clojure.core/nth input__62968 3)]
                   (clojure.core/case
                    input__62968_nth_0__
                    (meander.dev.parse.zeta/make-join)
                    (if
                     (clojure.core/map? input__62968_nth_1__)
                     (clojure.core/let
                      [VAL__64551 (.valAt input__62968_nth_1__ :tag)]
                      (clojure.core/case
                       VAL__64551
                       (:cat)
                       (clojure.core/let
                        [VAL__63466
                         (.valAt input__62968_nth_1__ :sequence)]
                        (clojure.core/let
                         [?sequence VAL__63466]
                         (clojure.core/let
                          [VAL__63467
                           (.valAt input__62968_nth_1__ :next)]
                          (if
                           (clojure.core/map? VAL__63467)
                           (clojure.core/let
                            [VAL__63468 (.valAt VAL__63467 :tag)]
                            (clojure.core/case
                             VAL__63468
                             (:empty)
                             (clojure.core/let
                              [?right input__62968_nth_2__]
                              (clojure.core/let
                               [?env input__62968_nth_3__]
                               (try
                                [(clojure.core/let
                                  [CATA_RESULT__15641__auto__
                                   (CATA__FN__63051
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
                             (state__64666)))
                           (state__64666)))))
                       (:literal)
                       (clojure.core/let
                        [VAL__63472
                         (.valAt input__62968_nth_1__ :type)]
                        (clojure.core/case
                         VAL__63472
                         (:string)
                         (clojure.core/let
                          [VAL__63473
                           (.valAt input__62968_nth_1__ :form)]
                          (clojure.core/let
                           [?form-1 VAL__63473]
                           (if
                            (clojure.core/map? input__62968_nth_2__)
                            (clojure.core/let
                             [VAL__63474
                              (.valAt input__62968_nth_2__ :tag)]
                             (clojure.core/case
                              VAL__63474
                              (:string-join)
                              (clojure.core/let
                               [VAL__63475
                                (.valAt input__62968_nth_2__ :left)]
                               (if
                                (clojure.core/map? VAL__63475)
                                (clojure.core/let
                                 [VAL__63476 (.valAt VAL__63475 :tag)]
                                 (clojure.core/case
                                  VAL__63476
                                  (:literal)
                                  (clojure.core/let
                                   [VAL__63477
                                    (.valAt VAL__63475 :type)]
                                   (clojure.core/case
                                    VAL__63477
                                    (:string)
                                    (clojure.core/let
                                     [VAL__63478
                                      (.valAt VAL__63475 :form)]
                                     (clojure.core/let
                                      [?form-2 VAL__63478]
                                      (clojure.core/let
                                       [VAL__63479
                                        (.valAt
                                         input__62968_nth_2__
                                         :right)]
                                       (clojure.core/let
                                        [?right VAL__63479]
                                        (if
                                         (clojure.core/map?
                                          input__62968_nth_3__)
                                         (clojure.core/let
                                          [VAL__63480
                                           (.valAt
                                            input__62968_nth_3__
                                            :context)]
                                          (clojure.core/case
                                           VAL__63480
                                           (:string)
                                           (clojure.core/let
                                            [?env input__62968_nth_3__]
                                            (try
                                             [(clojure.core/let
                                               [CATA_RESULT__15641__auto__
                                                (CATA__FN__63051
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
                                           (state__64666)))
                                         (state__64666))))))
                                    (state__64666)))
                                  (state__64666)))
                                (state__64666)))
                              (state__64666)))
                            (state__64666))))
                         (state__64666)))
                       (state__64666)))
                     (state__64666))
                    (state__64666))))
                 (state__64666
                  []
                  (clojure.core/case
                   input__62968_nth_0__
                   (meander.dev.parse.zeta/make-join)
                   (if
                    (clojure.core/map? input__62968_nth_1__)
                    (clojure.core/let
                     [VAL__64552 (.valAt input__62968_nth_1__ :tag)]
                     (clojure.core/case
                      VAL__64552
                      (:cat)
                      (clojure.core/let
                       [?ast input__62968_nth_1__]
                       (if
                        (clojure.core/map? input__62968_nth_2__)
                        (clojure.core/let
                         [VAL__63484
                          (.valAt input__62968_nth_2__ :tag)]
                         (clojure.core/case
                          VAL__63484
                          (:cat)
                          (clojure.core/let
                           [VAL__63485
                            (.valAt input__62968_nth_2__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__63485]
                            (clojure.core/let
                             [VAL__63486
                              (.valAt input__62968_nth_2__ :next)]
                             (clojure.core/let
                              [?next VAL__63486]
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
                          (state__64667)))
                        (state__64667)))
                      (:string-cat)
                      (clojure.core/let
                       [?ast input__62968_nth_1__]
                       (if
                        (clojure.core/map? input__62968_nth_2__)
                        (clojure.core/let
                         [VAL__63490
                          (.valAt input__62968_nth_2__ :tag)]
                         (clojure.core/case
                          VAL__63490
                          (:string-cat)
                          (clojure.core/let
                           [VAL__63491
                            (.valAt input__62968_nth_2__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__63491]
                            (clojure.core/let
                             [VAL__63492
                              (.valAt input__62968_nth_2__ :next)]
                             (clojure.core/let
                              [?next VAL__63492]
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
                          (state__64667)))
                        (state__64667)))
                      (state__64667)))
                    (state__64667))
                   (state__64667)))
                 (state__64667
                  []
                  (clojure.core/let
                   [input__62968_nth_3__
                    (clojure.core/nth input__62968 3)]
                   (clojure.core/case
                    input__62968_nth_0__
                    (meander.dev.parse.zeta/make-join)
                    (clojure.core/letfn
                     [(state__64688
                       []
                       (if
                        (clojure.core/map? input__62968_nth_1__)
                        (clojure.core/let
                         [VAL__64556
                          (.valAt input__62968_nth_1__ :next)
                          VAL__64555
                          (.valAt input__62968_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__64555
                          (:string-cat)
                          (clojure.core/let
                           [VAL__63496
                            (.valAt input__62968_nth_1__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__63496]
                            (if
                             (clojure.core/map? VAL__64556)
                             (clojure.core/let
                              [VAL__63498 (.valAt VAL__64556 :tag)]
                              (clojure.core/case
                               VAL__63498
                               (:empty)
                               (clojure.core/let
                                [?right input__62968_nth_2__]
                                (clojure.core/let
                                 [?env input__62968_nth_3__]
                                 (try
                                  [(clojure.core/let
                                    [CATA_RESULT__15641__auto__
                                     (CATA__FN__63051
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
                               (state__64689)))
                             (state__64689))))
                          (:string-star)
                          (clojure.core/let
                           [VAL__63502
                            (.valAt input__62968_nth_1__ :pattern)]
                           (clojure.core/let
                            [?pattern VAL__63502]
                            (if
                             (clojure.core/map? VAL__64556)
                             (clojure.core/let
                              [VAL__63504 (.valAt VAL__64556 :tag)]
                              (clojure.core/case
                               VAL__63504
                               (:empty)
                               (clojure.core/let
                                [?right input__62968_nth_2__]
                                (if
                                 (clojure.core/map?
                                  input__62968_nth_3__)
                                 (clojure.core/let
                                  [VAL__63505
                                   (.valAt
                                    input__62968_nth_3__
                                    :context)]
                                  (clojure.core/case
                                   VAL__63505
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
                                   (state__64689)))
                                 (state__64689)))
                               (state__64689)))
                             (state__64689))))
                          (:string-join)
                          (clojure.core/let
                           [VAL__63509
                            (.valAt input__62968_nth_1__ :left)]
                           (clojure.core/let
                            [?left VAL__63509]
                            (clojure.core/let
                             [VAL__63510
                              (.valAt input__62968_nth_1__ :right)]
                             (clojure.core/let
                              [?right-1 VAL__63510]
                              (clojure.core/let
                               [?right-2 input__62968_nth_2__]
                               (if
                                (clojure.core/map?
                                 input__62968_nth_3__)
                                (clojure.core/let
                                 [VAL__63511
                                  (.valAt
                                   input__62968_nth_3__
                                   :context)]
                                 (clojure.core/case
                                  VAL__63511
                                  (:string)
                                  (clojure.core/let
                                   [?env input__62968_nth_3__]
                                   (try
                                    [{:tag :string-join,
                                      :left ?left,
                                      :right
                                      (clojure.core/let
                                       [CATA_RESULT__15641__auto__
                                        (CATA__FN__63051
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
                                  (state__64689)))
                                (state__64689)))))))
                          (state__64689)))
                        (state__64689)))
                      (state__64689
                       []
                       (clojure.core/let
                        [?left input__62968_nth_1__]
                        (if
                         (clojure.core/map? input__62968_nth_2__)
                         (clojure.core/let
                          [VAL__63514
                           (.valAt input__62968_nth_2__ :tag)]
                          (clojure.core/case
                           VAL__63514
                           (:empty)
                           (clojure.core/let
                            [?env input__62968_nth_3__]
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
                           (state__64690)))
                         (state__64690))))
                      (state__64690
                       []
                       (if
                        (clojure.core/map? input__62968_nth_1__)
                        (clojure.core/let
                         [VAL__64554
                          (.valAt input__62968_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__64554
                          (:empty)
                          (clojure.core/let
                           [?right input__62968_nth_2__]
                           (clojure.core/let
                            [?env input__62968_nth_3__]
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
                           [VAL__63521
                            (.valAt input__62968_nth_1__ :next)]
                           (if
                            (clojure.core/map? VAL__63521)
                            (clojure.core/let
                             [VAL__63522 (.valAt VAL__63521 :tag)]
                             (clojure.core/case
                              VAL__63522
                              (:empty)
                              (clojure.core/let
                               [?left input__62968_nth_1__]
                               (clojure.core/let
                                [?right input__62968_nth_2__]
                                (clojure.core/let
                                 [?env input__62968_nth_3__]
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
                              (state__64691)))
                            (state__64691)))
                          (state__64691)))
                        (state__64691)))
                      (state__64691
                       []
                       (clojure.core/let
                        [?left input__62968_nth_1__]
                        (clojure.core/let
                         [?right input__62968_nth_2__]
                         (clojure.core/letfn
                          [(state__64692
                            []
                            (if
                             (clojure.core/map? input__62968_nth_3__)
                             (clojure.core/let
                              [VAL__63525
                               (.valAt input__62968_nth_3__ :context)]
                              (clojure.core/case
                               VAL__63525
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
                               (state__64693)))
                             (state__64693)))
                           (state__64693
                            []
                            (clojure.core/let
                             [?env input__62968_nth_3__]
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
                          (state__64692)))))]
                     (state__64688))
                    (state__64660))))]
                (state__64661)))
              (state__64660)))
            (state__64660
             []
             (if
              (clojure.core/= (clojure.core/count input__62968) 3)
              (clojure.core/let
               [input__62968_nth_0__
                (clojure.core/nth input__62968 0)
                input__62968_nth_1__
                (clojure.core/nth input__62968 1)
                input__62968_nth_2__
                (clojure.core/nth input__62968 2)]
               (clojure.core/case
                input__62968_nth_0__
                (meander.dev.parse.zeta/parse-entries)
                (if
                 (clojure.core/map? input__62968_nth_1__)
                 (clojure.core/let
                  [VAL__63530
                   (.valAt input__62968_nth_1__ :meander.zeta/as)]
                  (clojure.core/let
                   [?pattern VAL__63530]
                   (clojure.core/let
                    [X__63532
                     ((clojure.core/fn
                       [m__13405__auto__]
                       (clojure.core/dissoc
                        m__13405__auto__
                        :meander.zeta/as))
                      input__62968_nth_1__)]
                    (clojure.core/let
                     [?rest X__63532]
                     (clojure.core/letfn
                      [(save__63533 [] (state__64567))
                       (f__64694
                        []
                        (clojure.core/let
                         [?env input__62968_nth_2__]
                         (try
                          [{:tag :as,
                            :pattern
                            (clojure.core/let
                             [CATA_RESULT__15641__auto__
                              (CATA__FN__63051 [?pattern ?env])]
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
                              (CATA__FN__63051 [?rest ?env])]
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
                       (clojure.core/= ?rest input__62968_nth_1__)
                       (save__63533)
                       (f__64694)))))))
                 (state__64567))
                (state__64567)))
              (state__64567)))]
           (state__64658))
          (state__64567)))
        (state__64567
         []
         (clojure.core/letfn
          [(def__63536
            [arg__63569 ?ns]
            (clojure.core/letfn
             [(state__64695
               []
               (clojure.core/let
                [x__63570 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__63570)
                 (clojure.core/let [?env arg__63569] [?env ?ns])
                 (state__64696))))
              (state__64696
               []
               (if
                (clojure.core/map? arg__63569)
                (clojure.core/let
                 [VAL__63571 (.valAt arg__63569 :aliases)]
                 (if
                  (clojure.core/map? VAL__63571)
                  (clojure.core/let
                   [X__63573 (clojure.core/set VAL__63571)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__63573))
                    (clojure.core/loop
                     [search_space__64697 (clojure.core/seq X__63573)]
                     (if
                      (clojure.core/seq search_space__64697)
                      (clojure.core/let
                       [elem__63574
                        (clojure.core/first search_space__64697)
                        result__64698
                        (clojure.core/let
                         [elem__63574_nth_0__
                          (clojure.core/nth elem__63574 0)
                          elem__63574_nth_1__
                          (clojure.core/nth elem__63574 1)]
                         (if
                          (clojure.core/symbol? elem__63574_nth_0__)
                          (clojure.core/let
                           [X__63576
                            (clojure.core/name elem__63574_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__63576)
                            (if
                             (clojure.core/symbol? elem__63574_nth_1__)
                             (clojure.core/let
                              [X__63578
                               (clojure.core/name elem__63574_nth_1__)]
                              (clojure.core/case
                               X__63578
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__63569]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__64698)
                        (recur (clojure.core/next search_space__64697))
                        result__64698))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__64695)))]
          (if
           (clojure.core/vector? input__62968)
           (if
            (clojure.core/= (clojure.core/count input__62968) 3)
            (clojure.core/let
             [input__62968_nth_0__
              (clojure.core/nth input__62968 0)
              input__62968_nth_1__
              (clojure.core/nth input__62968 1)
              input__62968_nth_2__
              (clojure.core/nth input__62968 2)]
             (clojure.core/case
              input__62968_nth_0__
              (meander.dev.parse.zeta/parse-entries)
              (if
               (clojure.core/map? input__62968_nth_1__)
               (clojure.core/let
                [X__63541 (clojure.core/set input__62968_nth_1__)]
                (if
                 (clojure.core/<= 1 (clojure.core/count X__63541))
                 (clojure.core/loop
                  [search_space__64700 (clojure.core/seq X__63541)]
                  (if
                   (clojure.core/seq search_space__64700)
                   (clojure.core/let
                    [elem__63542
                     (clojure.core/first search_space__64700)
                     result__64701
                     (clojure.core/let
                      [elem__63542_nth_0__
                       (clojure.core/nth elem__63542 0)
                       elem__63542_nth_1__
                       (clojure.core/nth elem__63542 1)]
                      (clojure.core/let
                       [*m__63002 elem__63542_nth_0__]
                       (if
                        (clojure.core/symbol? elem__63542_nth_0__)
                        (clojure.core/let
                         [X__63544
                          (clojure.core/namespace elem__63542_nth_0__)]
                         (clojure.core/let
                          [?ns X__63544]
                          (clojure.core/let
                           [X__63546
                            (clojure.core/name elem__63542_nth_0__)]
                           (if
                            (clojure.core/string? X__63546)
                            (if
                             (clojure.core/re-matches #"&.*" X__63546)
                             (clojure.core/let
                              [?pattern elem__63542_nth_1__]
                              (clojure.core/let
                               [X__63548
                                ((clojure.core/fn
                                  [m__13405__auto__]
                                  (clojure.core/dissoc
                                   m__13405__auto__
                                   *m__63002))
                                 input__62968_nth_1__)]
                               (clojure.core/let
                                [?rest X__63548]
                                (clojure.core/let
                                 [x__14338__auto__
                                  (def__63536
                                   input__62968_nth_2__
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
                                        (CATA__FN__63051
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
                                        (CATA__FN__63051
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
                     (meander.runtime.zeta/fail? result__64701)
                     (recur (clojure.core/next search_space__64700))
                     result__64701))
                   (state__64568)))
                 (state__64568)))
               (state__64568))
              (state__64568)))
            (state__64568))
           (state__64568))))
        (state__64568
         []
         (if
          (clojure.core/vector? input__62968)
          (clojure.core/letfn
           [(state__64703
             []
             (if
              (clojure.core/= (clojure.core/count input__62968) 3)
              (clojure.core/let
               [input__62968_nth_0__
                (clojure.core/nth input__62968 0)
                input__62968_nth_1__
                (clojure.core/nth input__62968 1)
                input__62968_nth_2__
                (clojure.core/nth input__62968 2)]
               (clojure.core/case
                input__62968_nth_0__
                (meander.dev.parse.zeta/parse-entries)
                (clojure.core/letfn
                 [(state__64705
                   []
                   (if
                    (clojure.core/map? input__62968_nth_1__)
                    (clojure.core/let
                     [X__63592 (clojure.core/set input__62968_nth_1__)]
                     (if
                      (clojure.core/<= 1 (clojure.core/count X__63592))
                      (clojure.core/loop
                       [search_space__64707
                        (clojure.core/seq X__63592)]
                       (if
                        (clojure.core/seq search_space__64707)
                        (clojure.core/let
                         [elem__63593
                          (clojure.core/first search_space__64707)
                          result__64708
                          (clojure.core/let
                           [elem__63593_nth_0__
                            (clojure.core/nth elem__63593 0)
                            elem__63593_nth_1__
                            (clojure.core/nth elem__63593 1)]
                           (clojure.core/let
                            [?key-pattern elem__63593_nth_0__]
                            (clojure.core/let
                             [?val-pattern elem__63593_nth_1__]
                             (clojure.core/let
                              [X__63595
                               ((clojure.core/fn
                                 [m__13405__auto__]
                                 (clojure.core/dissoc
                                  m__13405__auto__
                                  ?key-pattern))
                                input__62968_nth_1__)]
                              (clojure.core/let
                               [?rest X__63595]
                               (clojure.core/let
                                [?env input__62968_nth_2__]
                                (try
                                 [{:tag :entry,
                                   :key-pattern
                                   (clojure.core/let
                                    [CATA_RESULT__15641__auto__
                                     (CATA__FN__63051
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
                                     (CATA__FN__63051
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
                                     (CATA__FN__63051
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
                          (meander.runtime.zeta/fail? result__64708)
                          (recur
                           (clojure.core/next search_space__64707))
                          result__64708))
                        (state__64706)))
                      (state__64706)))
                    (state__64706)))
                  (state__64706
                   []
                   (if
                    (clojure.core/map? input__62968_nth_1__)
                    (clojure.core/let
                     [?env input__62968_nth_2__]
                     (try
                      [{:tag :some-map}]
                      (catch
                       java.lang.Exception
                       e__16581__auto__
                       (if
                        (meander.runtime.zeta/fail? e__16581__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__16581__auto__)))))
                    (state__64704)))]
                 (state__64705))
                (meander.dev.parse.zeta/parse-with-bindings)
                (clojure.core/letfn
                 [(state__64710
                   []
                   (if
                    (clojure.core/vector? input__62968_nth_1__)
                    (clojure.core/case
                     input__62968_nth_1__
                     ([])
                     (clojure.core/let
                      [?env input__62968_nth_2__]
                      (try
                       [[]]
                       (catch
                        java.lang.Exception
                        e__16581__auto__
                        (if
                         (meander.runtime.zeta/fail? e__16581__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__16581__auto__)))))
                     (state__64711))
                    (state__64711)))
                  (state__64711
                   []
                   (if
                    (clojure.core/vector? input__62968_nth_1__)
                    (if
                     (clojure.core/=
                      (clojure.core/count input__62968_nth_1__)
                      1)
                     (clojure.core/let
                      [?env input__62968_nth_2__]
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
                     (state__64712))
                    (state__64712)))
                  (state__64712
                   []
                   (if
                    (clojure.core/vector? input__62968_nth_1__)
                    (clojure.core/let
                     [input__62968_nth_1___l__
                      (clojure.core/subvec
                       input__62968_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__62968_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__62968_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__62968_nth_1___r__
                        (clojure.core/subvec input__62968_nth_1__ 2)]
                       (clojure.core/let
                        [input__62968_nth_1___l___nth_0__
                         (clojure.core/nth input__62968_nth_1___l__ 0)
                         input__62968_nth_1___l___nth_1__
                         (clojure.core/nth input__62968_nth_1___l__ 1)]
                        (if
                         (clojure.core/symbol?
                          input__62968_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__63609
                           (clojure.core/namespace
                            input__62968_nth_1___l___nth_0__)]
                          (clojure.core/let
                           [X__63611
                            (clojure.core/name
                             input__62968_nth_1___l___nth_0__)]
                           (if
                            (clojure.core/string? X__63611)
                            (if
                             (clojure.core/re-matches #"%.+" X__63611)
                             (clojure.core/let
                              [?symbol
                               input__62968_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?pattern
                                input__62968_nth_1___l___nth_1__]
                               (clojure.core/let
                                [?rest input__62968_nth_1___r__]
                                (clojure.core/let
                                 [?env input__62968_nth_2__]
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
                                         (CATA__FN__63051
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
                                       (CATA__FN__63051
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
                             (state__64713))
                            (state__64713))))
                         (state__64713))))
                      (state__64713)))
                    (state__64713)))
                  (state__64713
                   []
                   (if
                    (clojure.core/vector? input__62968_nth_1__)
                    (clojure.core/let
                     [input__62968_nth_1___l__
                      (clojure.core/subvec
                       input__62968_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__62968_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__62968_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__62968_nth_1___r__
                        (clojure.core/subvec input__62968_nth_1__ 2)]
                       (clojure.core/let
                        [input__62968_nth_1___l___nth_0__
                         (clojure.core/nth input__62968_nth_1___l__ 0)
                         input__62968_nth_1___l___nth_1__
                         (clojure.core/nth input__62968_nth_1___l__ 1)]
                        (clojure.core/let
                         [?x input__62968_nth_1___l___nth_0__]
                         (clojure.core/let
                          [?pattern input__62968_nth_1___l___nth_1__]
                          (clojure.core/let
                           [?rest input__62968_nth_1___r__]
                           (clojure.core/let
                            [?env input__62968_nth_2__]
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
                      (state__64704)))
                    (state__64704)))]
                 (state__64710))
                (state__64704)))
              (state__64704)))
            (state__64704
             []
             (if
              (clojure.core/= (clojure.core/count input__62968) 2)
              (clojure.core/let
               [input__62968_nth_0__
                (clojure.core/nth input__62968 0)
                input__62968_nth_1__
                (clojure.core/nth input__62968 1)]
               (if
                (clojure.core/vector? input__62968_nth_0__)
                (clojure.core/let
                 [?sequence input__62968_nth_0__]
                 (clojure.core/let
                  [?form input__62968_nth_0__]
                  (clojure.core/let
                   [?env input__62968_nth_1__]
                   (try
                    [{:tag :vector,
                      :next
                      (clojure.core/let
                       [CATA_RESULT__15641__auto__
                        (CATA__FN__63051
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
                (state__64569)))
              (state__64569)))]
           (state__64703))
          (state__64569)))
        (state__64569
         []
         (clojure.core/letfn
          [(def__63621
            [arg__63644 ?__62969]
            (clojure.core/letfn
             [(state__64714
               []
               (clojure.core/let
                [x__63645 "clojure.core"]
                (if
                 (clojure.core/= ?__62969 x__63645)
                 [?__62969]
                 (state__64715))))
              (state__64715
               []
               (if
                (clojure.core/map? arg__63644)
                (clojure.core/let
                 [VAL__63646 (.valAt arg__63644 :aliases)]
                 (if
                  (clojure.core/map? VAL__63646)
                  (clojure.core/let
                   [X__63648 (clojure.core/set VAL__63646)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__63648))
                    (clojure.core/loop
                     [search_space__64716 (clojure.core/seq X__63648)]
                     (if
                      (clojure.core/seq search_space__64716)
                      (clojure.core/let
                       [elem__63649
                        (clojure.core/first search_space__64716)
                        result__64717
                        (clojure.core/let
                         [elem__63649_nth_0__
                          (clojure.core/nth elem__63649 0)
                          elem__63649_nth_1__
                          (clojure.core/nth elem__63649 1)]
                         (if
                          (clojure.core/symbol? elem__63649_nth_0__)
                          (clojure.core/let
                           [X__63651
                            (clojure.core/name elem__63649_nth_0__)]
                           (if
                            (clojure.core/= ?__62969 X__63651)
                            (if
                             (clojure.core/symbol? elem__63649_nth_1__)
                             (clojure.core/let
                              [X__63653
                               (clojure.core/name elem__63649_nth_1__)]
                              (clojure.core/case
                               X__63653
                               ("clojure.core")
                               [?__62969]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__64717)
                        (recur (clojure.core/next search_space__64716))
                        result__64717))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__64714)))]
          (if
           (clojure.core/vector? input__62968)
           (if
            (clojure.core/= (clojure.core/count input__62968) 2)
            (clojure.core/let
             [input__62968_nth_0__
              (clojure.core/nth input__62968 0)
              input__62968_nth_1__
              (clojure.core/nth input__62968 1)]
             (if
              (clojure.core/seq? input__62968_nth_0__)
              (clojure.core/let
               [input__62968_nth_0___l__
                (clojure.core/take 1 input__62968_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__62968_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__62968_nth_0___r__
                  (clojure.core/drop 1 input__62968_nth_0__)]
                 (clojure.core/let
                  [input__62968_nth_0___l___nth_0__
                   (clojure.core/nth input__62968_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__62968_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__63631
                     (clojure.core/namespace
                      input__62968_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__62969 X__63631]
                     (clojure.core/let
                      [X__63633
                       (clojure.core/name
                        input__62968_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__63633
                       ("unquote")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__63621 input__62968_nth_1__ ?__62969)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__64570)
                         (clojure.core/let
                          [[?__62969] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__62968)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__62968)
                             2)
                            (clojure.core/let
                             [input__62968_nth_0__
                              (clojure.core/nth input__62968 0)
                              input__62968_nth_1__
                              (clojure.core/nth input__62968 1)]
                             (if
                              (clojure.core/seq? input__62968_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__62968_nth_0__)
                                2)
                               (clojure.core/let
                                [input__62968_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__62968_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?x input__62968_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__62968_nth_0__]
                                  (clojure.core/let
                                   [?env input__62968_nth_1__]
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
                               (state__64570))
                              (state__64570)))
                            (state__64570))
                           (state__64570)))))
                       (state__64570)))))
                   (state__64570))))
                (state__64570)))
              (state__64570)))
            (state__64570))
           (state__64570))))
        (state__64570
         []
         (clojure.core/letfn
          [(def__63655
            [arg__63678 ?__62970]
            (clojure.core/letfn
             [(state__64719
               []
               (clojure.core/let
                [x__63679 "meander.zeta"]
                (if
                 (clojure.core/= ?__62970 x__63679)
                 [?__62970]
                 (state__64720))))
              (state__64720
               []
               (if
                (clojure.core/map? arg__63678)
                (clojure.core/let
                 [VAL__63680 (.valAt arg__63678 :aliases)]
                 (if
                  (clojure.core/map? VAL__63680)
                  (clojure.core/let
                   [X__63682 (clojure.core/set VAL__63680)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__63682))
                    (clojure.core/loop
                     [search_space__64721 (clojure.core/seq X__63682)]
                     (if
                      (clojure.core/seq search_space__64721)
                      (clojure.core/let
                       [elem__63683
                        (clojure.core/first search_space__64721)
                        result__64722
                        (clojure.core/let
                         [elem__63683_nth_0__
                          (clojure.core/nth elem__63683 0)
                          elem__63683_nth_1__
                          (clojure.core/nth elem__63683 1)]
                         (if
                          (clojure.core/symbol? elem__63683_nth_0__)
                          (clojure.core/let
                           [X__63685
                            (clojure.core/name elem__63683_nth_0__)]
                           (if
                            (clojure.core/= ?__62970 X__63685)
                            (if
                             (clojure.core/symbol? elem__63683_nth_1__)
                             (clojure.core/let
                              [X__63687
                               (clojure.core/name elem__63683_nth_1__)]
                              (clojure.core/case
                               X__63687
                               ("meander.zeta")
                               [?__62970]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__64722)
                        (recur (clojure.core/next search_space__64721))
                        result__64722))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__64719)))]
          (if
           (clojure.core/vector? input__62968)
           (if
            (clojure.core/= (clojure.core/count input__62968) 2)
            (clojure.core/let
             [input__62968_nth_0__
              (clojure.core/nth input__62968 0)
              input__62968_nth_1__
              (clojure.core/nth input__62968 1)]
             (if
              (clojure.core/seq? input__62968_nth_0__)
              (clojure.core/let
               [input__62968_nth_0___l__
                (clojure.core/take 1 input__62968_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__62968_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__62968_nth_0___r__
                  (clojure.core/drop 1 input__62968_nth_0__)]
                 (clojure.core/let
                  [input__62968_nth_0___l___nth_0__
                   (clojure.core/nth input__62968_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__62968_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__63665
                     (clojure.core/namespace
                      input__62968_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__62970 X__63665]
                     (clojure.core/let
                      [X__63667
                       (clojure.core/name
                        input__62968_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__63667
                       ("*")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__63655 input__62968_nth_1__ ?__62970)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__64571)
                         (clojure.core/let
                          [[?__62970] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__62968)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__62968)
                             2)
                            (clojure.core/let
                             [input__62968_nth_0__
                              (clojure.core/nth input__62968 0)
                              input__62968_nth_1__
                              (clojure.core/nth input__62968 1)]
                             (if
                              (clojure.core/seq? input__62968_nth_0__)
                              (clojure.core/let
                               [input__62968_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__62968_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__62968_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__62968_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__62968_nth_0__)]
                                 (clojure.core/let
                                  [?patterns input__62968_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__62968_nth_0__]
                                   (clojure.core/let
                                    [?env input__62968_nth_1__]
                                    (try
                                     [{:tag :star,
                                       :greedy? true,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__63051
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
                                (state__64571)))
                              (state__64571)))
                            (state__64571))
                           (state__64571)))))
                       (state__64571)))))
                   (state__64571))))
                (state__64571)))
              (state__64571)))
            (state__64571))
           (state__64571))))
        (state__64571
         []
         (clojure.core/letfn
          [(def__63689
            [arg__63712 ?__62971]
            (clojure.core/letfn
             [(state__64724
               []
               (clojure.core/let
                [x__63713 "meander.zeta"]
                (if
                 (clojure.core/= ?__62971 x__63713)
                 [?__62971]
                 (state__64725))))
              (state__64725
               []
               (if
                (clojure.core/map? arg__63712)
                (clojure.core/let
                 [VAL__63714 (.valAt arg__63712 :aliases)]
                 (if
                  (clojure.core/map? VAL__63714)
                  (clojure.core/let
                   [X__63716 (clojure.core/set VAL__63714)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__63716))
                    (clojure.core/loop
                     [search_space__64726 (clojure.core/seq X__63716)]
                     (if
                      (clojure.core/seq search_space__64726)
                      (clojure.core/let
                       [elem__63717
                        (clojure.core/first search_space__64726)
                        result__64727
                        (clojure.core/let
                         [elem__63717_nth_0__
                          (clojure.core/nth elem__63717 0)
                          elem__63717_nth_1__
                          (clojure.core/nth elem__63717 1)]
                         (if
                          (clojure.core/symbol? elem__63717_nth_0__)
                          (clojure.core/let
                           [X__63719
                            (clojure.core/name elem__63717_nth_0__)]
                           (if
                            (clojure.core/= ?__62971 X__63719)
                            (if
                             (clojure.core/symbol? elem__63717_nth_1__)
                             (clojure.core/let
                              [X__63721
                               (clojure.core/name elem__63717_nth_1__)]
                              (clojure.core/case
                               X__63721
                               ("meander.zeta")
                               [?__62971]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__64727)
                        (recur (clojure.core/next search_space__64726))
                        result__64727))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__64724)))]
          (if
           (clojure.core/vector? input__62968)
           (if
            (clojure.core/= (clojure.core/count input__62968) 2)
            (clojure.core/let
             [input__62968_nth_0__
              (clojure.core/nth input__62968 0)
              input__62968_nth_1__
              (clojure.core/nth input__62968 1)]
             (if
              (clojure.core/seq? input__62968_nth_0__)
              (clojure.core/let
               [input__62968_nth_0___l__
                (clojure.core/take 1 input__62968_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__62968_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__62968_nth_0___r__
                  (clojure.core/drop 1 input__62968_nth_0__)]
                 (clojure.core/let
                  [input__62968_nth_0___l___nth_0__
                   (clojure.core/nth input__62968_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__62968_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__63699
                     (clojure.core/namespace
                      input__62968_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__62971 X__63699]
                     (clojure.core/let
                      [X__63701
                       (clojure.core/name
                        input__62968_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__63701
                       ("<>")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__63689 input__62968_nth_1__ ?__62971)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__64572)
                         (clojure.core/let
                          [[?__62971] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__62968)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__62968)
                             2)
                            (clojure.core/let
                             [input__62968_nth_0__
                              (clojure.core/nth input__62968 0)
                              input__62968_nth_1__
                              (clojure.core/nth input__62968 1)]
                             (if
                              (clojure.core/seq? input__62968_nth_0__)
                              (clojure.core/let
                               [input__62968_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__62968_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__62968_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__62968_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__62968_nth_0__)]
                                 (clojure.core/let
                                  [?patterns input__62968_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__62968_nth_0__]
                                   (clojure.core/let
                                    [?env input__62968_nth_1__]
                                    (try
                                     [{:tag :group,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__63051
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
                                (state__64572)))
                              (state__64572)))
                            (state__64572))
                           (state__64572)))))
                       (state__64572)))))
                   (state__64572))))
                (state__64572)))
              (state__64572)))
            (state__64572))
           (state__64572))))
        (state__64572
         []
         (clojure.core/letfn
          [(def__63723
            [arg__63746 ?__62972]
            (clojure.core/letfn
             [(state__64729
               []
               (clojure.core/let
                [x__63747 "meander.math.zeta"]
                (if
                 (clojure.core/= ?__62972 x__63747)
                 [?__62972]
                 (state__64730))))
              (state__64730
               []
               (if
                (clojure.core/map? arg__63746)
                (clojure.core/let
                 [VAL__63748 (.valAt arg__63746 :aliases)]
                 (if
                  (clojure.core/map? VAL__63748)
                  (clojure.core/let
                   [X__63750 (clojure.core/set VAL__63748)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__63750))
                    (clojure.core/loop
                     [search_space__64731 (clojure.core/seq X__63750)]
                     (if
                      (clojure.core/seq search_space__64731)
                      (clojure.core/let
                       [elem__63751
                        (clojure.core/first search_space__64731)
                        result__64732
                        (clojure.core/let
                         [elem__63751_nth_0__
                          (clojure.core/nth elem__63751 0)
                          elem__63751_nth_1__
                          (clojure.core/nth elem__63751 1)]
                         (if
                          (clojure.core/symbol? elem__63751_nth_0__)
                          (clojure.core/let
                           [X__63753
                            (clojure.core/name elem__63751_nth_0__)]
                           (if
                            (clojure.core/= ?__62972 X__63753)
                            (if
                             (clojure.core/symbol? elem__63751_nth_1__)
                             (clojure.core/let
                              [X__63755
                               (clojure.core/name elem__63751_nth_1__)]
                              (clojure.core/case
                               X__63755
                               ("meander.math.zeta")
                               [?__62972]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__64732)
                        (recur (clojure.core/next search_space__64731))
                        result__64732))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__64729)))]
          (if
           (clojure.core/vector? input__62968)
           (if
            (clojure.core/= (clojure.core/count input__62968) 2)
            (clojure.core/let
             [input__62968_nth_0__
              (clojure.core/nth input__62968 0)
              input__62968_nth_1__
              (clojure.core/nth input__62968 1)]
             (if
              (clojure.core/seq? input__62968_nth_0__)
              (clojure.core/let
               [input__62968_nth_0___l__
                (clojure.core/take 1 input__62968_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__62968_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__62968_nth_0___r__
                  (clojure.core/drop 1 input__62968_nth_0__)]
                 (clojure.core/let
                  [input__62968_nth_0___l___nth_0__
                   (clojure.core/nth input__62968_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__62968_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__63733
                     (clojure.core/namespace
                      input__62968_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__62972 X__63733]
                     (clojure.core/let
                      [X__63735
                       (clojure.core/name
                        input__62968_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__63735
                       ("+")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__63723 input__62968_nth_1__ ?__62972)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__64573)
                         (clojure.core/let
                          [[?__62972] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__62968)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__62968)
                             2)
                            (clojure.core/let
                             [input__62968_nth_0__
                              (clojure.core/nth input__62968 0)
                              input__62968_nth_1__
                              (clojure.core/nth input__62968 1)]
                             (if
                              (clojure.core/seq? input__62968_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__62968_nth_0__)
                                3)
                               (clojure.core/let
                                [input__62968_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__62968_nth_0__
                                  1)
                                 input__62968_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__62968_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?a input__62968_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?b input__62968_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__62968_nth_0__]
                                   (clojure.core/let
                                    [?env input__62968_nth_1__]
                                    (try
                                     [{:tag :meander.math.zeta/+,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__63051 [?a ?env])]
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
                                         (CATA__FN__63051 [?b ?env])]
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
                               (state__64573))
                              (state__64573)))
                            (state__64573))
                           (state__64573)))))
                       (state__64573)))))
                   (state__64573))))
                (state__64573)))
              (state__64573)))
            (state__64573))
           (state__64573))))
        (state__64573
         []
         (clojure.core/letfn
          [(def__63757
            [arg__63780 ?__62973]
            (clojure.core/letfn
             [(state__64734
               []
               (clojure.core/let
                [x__63781 "meander.math.zeta"]
                (if
                 (clojure.core/= ?__62973 x__63781)
                 [?__62973]
                 (state__64735))))
              (state__64735
               []
               (if
                (clojure.core/map? arg__63780)
                (clojure.core/let
                 [VAL__63782 (.valAt arg__63780 :aliases)]
                 (if
                  (clojure.core/map? VAL__63782)
                  (clojure.core/let
                   [X__63784 (clojure.core/set VAL__63782)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__63784))
                    (clojure.core/loop
                     [search_space__64736 (clojure.core/seq X__63784)]
                     (if
                      (clojure.core/seq search_space__64736)
                      (clojure.core/let
                       [elem__63785
                        (clojure.core/first search_space__64736)
                        result__64737
                        (clojure.core/let
                         [elem__63785_nth_0__
                          (clojure.core/nth elem__63785 0)
                          elem__63785_nth_1__
                          (clojure.core/nth elem__63785 1)]
                         (if
                          (clojure.core/symbol? elem__63785_nth_0__)
                          (clojure.core/let
                           [X__63787
                            (clojure.core/name elem__63785_nth_0__)]
                           (if
                            (clojure.core/= ?__62973 X__63787)
                            (if
                             (clojure.core/symbol? elem__63785_nth_1__)
                             (clojure.core/let
                              [X__63789
                               (clojure.core/name elem__63785_nth_1__)]
                              (clojure.core/case
                               X__63789
                               ("meander.math.zeta")
                               [?__62973]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__64737)
                        (recur (clojure.core/next search_space__64736))
                        result__64737))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__64734)))]
          (if
           (clojure.core/vector? input__62968)
           (if
            (clojure.core/= (clojure.core/count input__62968) 2)
            (clojure.core/let
             [input__62968_nth_0__
              (clojure.core/nth input__62968 0)
              input__62968_nth_1__
              (clojure.core/nth input__62968 1)]
             (if
              (clojure.core/seq? input__62968_nth_0__)
              (clojure.core/let
               [input__62968_nth_0___l__
                (clojure.core/take 1 input__62968_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__62968_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__62968_nth_0___r__
                  (clojure.core/drop 1 input__62968_nth_0__)]
                 (clojure.core/let
                  [input__62968_nth_0___l___nth_0__
                   (clojure.core/nth input__62968_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__62968_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__63767
                     (clojure.core/namespace
                      input__62968_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__62973 X__63767]
                     (clojure.core/let
                      [X__63769
                       (clojure.core/name
                        input__62968_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__63769
                       ("-")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__63757 input__62968_nth_1__ ?__62973)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__64574)
                         (clojure.core/let
                          [[?__62973] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__62968)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__62968)
                             2)
                            (clojure.core/let
                             [input__62968_nth_0__
                              (clojure.core/nth input__62968 0)
                              input__62968_nth_1__
                              (clojure.core/nth input__62968 1)]
                             (if
                              (clojure.core/seq? input__62968_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__62968_nth_0__)
                                3)
                               (clojure.core/let
                                [input__62968_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__62968_nth_0__
                                  1)
                                 input__62968_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__62968_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?a input__62968_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?b input__62968_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__62968_nth_0__]
                                   (clojure.core/let
                                    [?env input__62968_nth_1__]
                                    (try
                                     [{:tag :meander.math.zeta/-,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__63051 [?a ?env])]
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
                                         (CATA__FN__63051 [?b ?env])]
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
                               (state__64574))
                              (state__64574)))
                            (state__64574))
                           (state__64574)))))
                       (state__64574)))))
                   (state__64574))))
                (state__64574)))
              (state__64574)))
            (state__64574))
           (state__64574))))
        (state__64574
         []
         (clojure.core/letfn
          [(def__63791
            [arg__63814 ?__62974]
            (clojure.core/letfn
             [(state__64739
               []
               (clojure.core/let
                [x__63815 "meander.zeta"]
                (if
                 (clojure.core/= ?__62974 x__63815)
                 [?__62974]
                 (state__64740))))
              (state__64740
               []
               (if
                (clojure.core/map? arg__63814)
                (clojure.core/let
                 [VAL__63816 (.valAt arg__63814 :aliases)]
                 (if
                  (clojure.core/map? VAL__63816)
                  (clojure.core/let
                   [X__63818 (clojure.core/set VAL__63816)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__63818))
                    (clojure.core/loop
                     [search_space__64741 (clojure.core/seq X__63818)]
                     (if
                      (clojure.core/seq search_space__64741)
                      (clojure.core/let
                       [elem__63819
                        (clojure.core/first search_space__64741)
                        result__64742
                        (clojure.core/let
                         [elem__63819_nth_0__
                          (clojure.core/nth elem__63819 0)
                          elem__63819_nth_1__
                          (clojure.core/nth elem__63819 1)]
                         (if
                          (clojure.core/symbol? elem__63819_nth_0__)
                          (clojure.core/let
                           [X__63821
                            (clojure.core/name elem__63819_nth_0__)]
                           (if
                            (clojure.core/= ?__62974 X__63821)
                            (if
                             (clojure.core/symbol? elem__63819_nth_1__)
                             (clojure.core/let
                              [X__63823
                               (clojure.core/name elem__63819_nth_1__)]
                              (clojure.core/case
                               X__63823
                               ("meander.zeta")
                               [?__62974]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__64742)
                        (recur (clojure.core/next search_space__64741))
                        result__64742))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__64739)))]
          (if
           (clojure.core/vector? input__62968)
           (if
            (clojure.core/= (clojure.core/count input__62968) 2)
            (clojure.core/let
             [input__62968_nth_0__
              (clojure.core/nth input__62968 0)
              input__62968_nth_1__
              (clojure.core/nth input__62968 1)]
             (if
              (clojure.core/seq? input__62968_nth_0__)
              (clojure.core/let
               [input__62968_nth_0___l__
                (clojure.core/take 1 input__62968_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__62968_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__62968_nth_0___r__
                  (clojure.core/drop 1 input__62968_nth_0__)]
                 (clojure.core/let
                  [input__62968_nth_0___l___nth_0__
                   (clojure.core/nth input__62968_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__62968_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__63801
                     (clojure.core/namespace
                      input__62968_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__62974 X__63801]
                     (clojure.core/let
                      [X__63803
                       (clojure.core/name
                        input__62968_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__63803
                       ("with")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__63791 input__62968_nth_1__ ?__62974)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__64575)
                         (clojure.core/let
                          [[?__62974] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__62968)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__62968)
                             2)
                            (clojure.core/let
                             [input__62968_nth_0__
                              (clojure.core/nth input__62968 0)
                              input__62968_nth_1__
                              (clojure.core/nth input__62968 1)]
                             (if
                              (clojure.core/seq? input__62968_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__62968_nth_0__)
                                3)
                               (clojure.core/let
                                [input__62968_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__62968_nth_0__
                                  1)
                                 input__62968_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__62968_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?bindings
                                  input__62968_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?body input__62968_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__62968_nth_0__]
                                   (clojure.core/let
                                    [?env input__62968_nth_1__]
                                    (try
                                     [{:tag :with,
                                       :bindings
                                       {:tag :with-bindings,
                                        :bindings
                                        (clojure.core/let
                                         [CATA_RESULT__15641__auto__
                                          (CATA__FN__63051
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
                                         (CATA__FN__63051
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
                               (state__64575))
                              (state__64575)))
                            (state__64575))
                           (state__64575)))))
                       (state__64575)))))
                   (state__64575))))
                (state__64575)))
              (state__64575)))
            (state__64575))
           (state__64575))))
        (state__64575
         []
         (clojure.core/letfn
          [(def__63825
            [arg__63848 ?__62975]
            (clojure.core/letfn
             [(state__64744
               []
               (clojure.core/let
                [x__63849 "meander.zeta"]
                (if
                 (clojure.core/= ?__62975 x__63849)
                 [?__62975]
                 (state__64745))))
              (state__64745
               []
               (if
                (clojure.core/map? arg__63848)
                (clojure.core/let
                 [VAL__63850 (.valAt arg__63848 :aliases)]
                 (if
                  (clojure.core/map? VAL__63850)
                  (clojure.core/let
                   [X__63852 (clojure.core/set VAL__63850)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__63852))
                    (clojure.core/loop
                     [search_space__64746 (clojure.core/seq X__63852)]
                     (if
                      (clojure.core/seq search_space__64746)
                      (clojure.core/let
                       [elem__63853
                        (clojure.core/first search_space__64746)
                        result__64747
                        (clojure.core/let
                         [elem__63853_nth_0__
                          (clojure.core/nth elem__63853 0)
                          elem__63853_nth_1__
                          (clojure.core/nth elem__63853 1)]
                         (if
                          (clojure.core/symbol? elem__63853_nth_0__)
                          (clojure.core/let
                           [X__63855
                            (clojure.core/name elem__63853_nth_0__)]
                           (if
                            (clojure.core/= ?__62975 X__63855)
                            (if
                             (clojure.core/symbol? elem__63853_nth_1__)
                             (clojure.core/let
                              [X__63857
                               (clojure.core/name elem__63853_nth_1__)]
                              (clojure.core/case
                               X__63857
                               ("meander.zeta")
                               [?__62975]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__64747)
                        (recur (clojure.core/next search_space__64746))
                        result__64747))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__64744)))]
          (if
           (clojure.core/vector? input__62968)
           (if
            (clojure.core/= (clojure.core/count input__62968) 2)
            (clojure.core/let
             [input__62968_nth_0__
              (clojure.core/nth input__62968 0)
              input__62968_nth_1__
              (clojure.core/nth input__62968 1)]
             (if
              (clojure.core/seq? input__62968_nth_0__)
              (clojure.core/let
               [input__62968_nth_0___l__
                (clojure.core/take 1 input__62968_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__62968_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__62968_nth_0___r__
                  (clojure.core/drop 1 input__62968_nth_0__)]
                 (clojure.core/let
                  [input__62968_nth_0___l___nth_0__
                   (clojure.core/nth input__62968_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__62968_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__63835
                     (clojure.core/namespace
                      input__62968_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__62975 X__63835]
                     (clojure.core/let
                      [X__63837
                       (clojure.core/name
                        input__62968_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__63837
                       ("apply")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__63825 input__62968_nth_1__ ?__62975)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__64576)
                         (clojure.core/let
                          [[?__62975] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__62968)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__62968)
                             2)
                            (clojure.core/let
                             [input__62968_nth_0__
                              (clojure.core/nth input__62968 0)
                              input__62968_nth_1__
                              (clojure.core/nth input__62968 1)]
                             (if
                              (clojure.core/seq? input__62968_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__62968_nth_0__)
                                3)
                               (clojure.core/let
                                [input__62968_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__62968_nth_0__
                                  1)
                                 input__62968_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__62968_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?fn input__62968_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?pattern
                                   input__62968_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__62968_nth_0__]
                                   (clojure.core/let
                                    [?env input__62968_nth_1__]
                                    (try
                                     [{:tag :apply,
                                       :fn ?fn,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__63051
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
                               (state__64576))
                              (state__64576)))
                            (state__64576))
                           (state__64576)))))
                       (state__64576)))))
                   (state__64576))))
                (state__64576)))
              (state__64576)))
            (state__64576))
           (state__64576))))
        (state__64576
         []
         (clojure.core/letfn
          [(def__63859
            [arg__63884 ?__62976]
            (clojure.core/letfn
             [(state__64749
               []
               (clojure.core/let
                [x__63885 "meander.zeta"]
                (if
                 (clojure.core/= ?__62976 x__63885)
                 [?__62976]
                 (state__64750))))
              (state__64750
               []
               (if
                (clojure.core/map? arg__63884)
                (clojure.core/let
                 [VAL__63886 (.valAt arg__63884 :aliases)]
                 (if
                  (clojure.core/map? VAL__63886)
                  (clojure.core/let
                   [X__63888 (clojure.core/set VAL__63886)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__63888))
                    (clojure.core/loop
                     [search_space__64751 (clojure.core/seq X__63888)]
                     (if
                      (clojure.core/seq search_space__64751)
                      (clojure.core/let
                       [elem__63889
                        (clojure.core/first search_space__64751)
                        result__64752
                        (clojure.core/let
                         [elem__63889_nth_0__
                          (clojure.core/nth elem__63889 0)
                          elem__63889_nth_1__
                          (clojure.core/nth elem__63889 1)]
                         (if
                          (clojure.core/symbol? elem__63889_nth_0__)
                          (clojure.core/let
                           [X__63891
                            (clojure.core/name elem__63889_nth_0__)]
                           (if
                            (clojure.core/= ?__62976 X__63891)
                            (if
                             (clojure.core/symbol? elem__63889_nth_1__)
                             (clojure.core/let
                              [X__63893
                               (clojure.core/name elem__63889_nth_1__)]
                              (clojure.core/case
                               X__63893
                               ("meander.zeta")
                               [?__62976]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__64752)
                        (recur (clojure.core/next search_space__64751))
                        result__64752))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__64749)))]
          (if
           (clojure.core/vector? input__62968)
           (if
            (clojure.core/= (clojure.core/count input__62968) 2)
            (clojure.core/let
             [input__62968_nth_0__
              (clojure.core/nth input__62968 0)
              input__62968_nth_1__
              (clojure.core/nth input__62968 1)]
             (if
              (clojure.core/seq? input__62968_nth_0__)
              (clojure.core/let
               [input__62968_nth_0___l__
                (clojure.core/take 1 input__62968_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__62968_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__62968_nth_0___r__
                  (clojure.core/drop 1 input__62968_nth_0__)]
                 (clojure.core/let
                  [input__62968_nth_0___l___nth_0__
                   (clojure.core/nth input__62968_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__62968_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__63871
                     (clojure.core/namespace
                      input__62968_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__62976 X__63871]
                     (clojure.core/let
                      [X__63873
                       (clojure.core/name
                        input__62968_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__63873
                       ("and")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__63859 input__62968_nth_1__ ?__62976)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__64577)
                         (clojure.core/let
                          [[?__62976] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__62968)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__62968)
                             2)
                            (clojure.core/let
                             [input__62968_nth_0__
                              (clojure.core/nth input__62968 0)
                              input__62968_nth_1__
                              (clojure.core/nth input__62968 1)]
                             (if
                              (clojure.core/seq? input__62968_nth_0__)
                              (clojure.core/let
                               [input__62968_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__62968_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__62968_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__62968_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__62968_nth_0__)]
                                 (clojure.core/let
                                  [!forms
                                   (clojure.core/vec
                                    input__62968_nth_0___r__)]
                                  (clojure.core/let
                                   [?form input__62968_nth_0__]
                                   (clojure.core/let
                                    [?env input__62968_nth_1__]
                                    (try
                                     [(clojure.core/let
                                       [!forms__counter
                                        (meander.runtime.zeta/iterator
                                         !forms)]
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__63051
                                          ['meander.dev.parse.zeta/make-and
                                           (clojure.core/into
                                            []
                                            (clojure.core/loop
                                             [return__63053
                                              (clojure.core/transient
                                               [])]
                                             (if
                                              (clojure.core/and
                                               (.hasNext
                                                !forms__counter))
                                              (recur
                                               (clojure.core/conj!
                                                return__63053
                                                (clojure.core/let
                                                 [CATA_RESULT__15641__auto__
                                                  (CATA__FN__63051
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
                                               return__63053))))
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
                                (state__64577)))
                              (state__64577)))
                            (state__64577))
                           (state__64577)))))
                       (state__64577)))))
                   (state__64577))))
                (state__64577)))
              (state__64577)))
            (state__64577))
           (state__64577))))
        (state__64577
         []
         (if
          (clojure.core/vector? input__62968)
          (if
           (clojure.core/= (clojure.core/count input__62968) 3)
           (clojure.core/let
            [input__62968_nth_0__
             (clojure.core/nth input__62968 0)
             input__62968_nth_1__
             (clojure.core/nth input__62968 1)
             input__62968_nth_2__
             (clojure.core/nth input__62968 2)]
            (clojure.core/case
             input__62968_nth_0__
             (meander.dev.parse.zeta/make-and)
             (clojure.core/letfn
              [(state__64754
                []
                (if
                 (clojure.core/vector? input__62968_nth_1__)
                 (clojure.core/case
                  input__62968_nth_1__
                  ([])
                  (clojure.core/let
                   [?form input__62968_nth_2__]
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
                  (state__64755))
                 (state__64755)))
               (state__64755
                []
                (clojure.core/case
                 input__62968_nth_2__
                 (nil)
                 (if
                  (clojure.core/vector? input__62968_nth_1__)
                  (if
                   (clojure.core/=
                    (clojure.core/count input__62968_nth_1__)
                    1)
                   (clojure.core/let
                    [input__62968_nth_1___nth_0__
                     (clojure.core/nth input__62968_nth_1__ 0)]
                    (clojure.core/let
                     [?ast-a input__62968_nth_1___nth_0__]
                     (try
                      [?ast-a]
                      (catch
                       java.lang.Exception
                       e__16581__auto__
                       (if
                        (meander.runtime.zeta/fail? e__16581__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__16581__auto__))))))
                   (state__64756))
                  (state__64756))
                 (state__64756)))
               (state__64756
                []
                (if
                 (clojure.core/vector? input__62968_nth_1__)
                 (clojure.core/letfn
                  [(state__64757
                    []
                    (if
                     (clojure.core/=
                      (clojure.core/count input__62968_nth_1__)
                      1)
                     (clojure.core/let
                      [input__62968_nth_1___nth_0__
                       (clojure.core/nth input__62968_nth_1__ 0)]
                      (clojure.core/let
                       [?ast-a input__62968_nth_1___nth_0__]
                       (clojure.core/let
                        [?form input__62968_nth_2__]
                        (try
                         [(clojure.core/let
                           [CATA_RESULT__15641__auto__
                            (CATA__FN__63051
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
                     (state__64758)))
                   (state__64758
                    []
                    (if
                     (clojure.core/=
                      (clojure.core/count input__62968_nth_1__)
                      2)
                     (clojure.core/let
                      [input__62968_nth_1___nth_0__
                       (clojure.core/nth input__62968_nth_1__ 0)
                       input__62968_nth_1___nth_1__
                       (clojure.core/nth input__62968_nth_1__ 1)]
                      (clojure.core/let
                       [?ast-a input__62968_nth_1___nth_0__]
                       (clojure.core/let
                        [?ast-b input__62968_nth_1___nth_1__]
                        (clojure.core/let
                         [?form input__62968_nth_2__]
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
                     (state__64759)))
                   (state__64759
                    []
                    (clojure.core/loop
                     [search_space__64760
                      (meander.runtime.zeta/epsilon-partitions
                       2
                       input__62968_nth_1__)]
                     (if
                      (clojure.core/seq search_space__64760)
                      (clojure.core/let
                       [input__62968_nth_1___parts__
                        (clojure.core/first search_space__64760)
                        result__64761
                        (clojure.core/let
                         [input__62968_nth_1___l__
                          (clojure.core/nth
                           input__62968_nth_1___parts__
                           0)
                          input__62968_nth_1___r__
                          (clojure.core/nth
                           input__62968_nth_1___parts__
                           1)]
                         (clojure.core/letfn
                          [(state__64763
                            []
                            (clojure.core/let
                             [!asts-1 []]
                             (clojure.core/let
                              [ret__14502__auto__
                               (meander.runtime.zeta/epsilon-run-star-1
                                input__62968_nth_1___l__
                                [!asts-1]
                                (clojure.core/fn
                                 [[!asts-1] input__63920]
                                 (clojure.core/let
                                  [input__63920_nth_0__
                                   (clojure.core/nth input__63920 0)]
                                  (clojure.core/let
                                   [!asts-1
                                    (clojure.core/conj
                                     !asts-1
                                     input__63920_nth_0__)]
                                   [!asts-1])))
                                (clojure.core/fn
                                 [[!asts-1]]
                                 (clojure.core/let
                                  [X__63913
                                   (clojure.core/count
                                    input__62968_nth_1___l__)]
                                  (clojure.core/let
                                   [?n X__63913]
                                   (clojure.core/let
                                    [X__63917
                                     (clojure.core/count
                                      input__62968_nth_1___r__)]
                                    (if
                                     (clojure.core/= ?n X__63917)
                                     (clojure.core/let
                                      [!asts-2 []]
                                      (clojure.core/let
                                       [ret__14502__auto__
                                        (meander.runtime.zeta/epsilon-run-star-1
                                         input__62968_nth_1___r__
                                         [!asts-2 !asts-1]
                                         (clojure.core/fn
                                          [[!asts-2 !asts-1]
                                           input__63918]
                                          (clojure.core/let
                                           [input__63918_nth_0__
                                            (clojure.core/nth
                                             input__63918
                                             0)]
                                           (clojure.core/let
                                            [!asts-2
                                             (clojure.core/conj
                                              !asts-2
                                              input__63918_nth_0__)]
                                            [!asts-2 !asts-1])))
                                         (clojure.core/fn
                                          [[!asts-2 !asts-1]]
                                          (clojure.core/let
                                           [?form input__62968_nth_2__]
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
                                                (CATA__FN__63051
                                                 ['meander.dev.parse.zeta/make-and
                                                  [(clojure.core/let
                                                    [CATA_RESULT__15641__auto__
                                                     (CATA__FN__63051
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
                                                     (CATA__FN__63051
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
                                        (state__64764)
                                        ret__14502__auto__)))
                                     (state__64764)))))))]
                              (if
                               (meander.runtime.zeta/fail?
                                ret__14502__auto__)
                               (state__64764)
                               ret__14502__auto__))))
                           (state__64764
                            []
                            (clojure.core/let
                             [!asts-1 []]
                             (clojure.core/let
                              [ret__14502__auto__
                               (meander.runtime.zeta/epsilon-run-star-1
                                input__62968_nth_1___l__
                                [!asts-1]
                                (clojure.core/fn
                                 [[!asts-1] input__63936]
                                 (clojure.core/let
                                  [input__63936_nth_0__
                                   (clojure.core/nth input__63936 0)]
                                  (clojure.core/let
                                   [!asts-1
                                    (clojure.core/conj
                                     !asts-1
                                     input__63936_nth_0__)]
                                   [!asts-1])))
                                (clojure.core/fn
                                 [[!asts-1]]
                                 (clojure.core/let
                                  [X__63927
                                   (clojure.core/count
                                    input__62968_nth_1___l__)]
                                  (clojure.core/let
                                   [?n X__63927]
                                   (clojure.core/let
                                    [input__62968_nth_1___r___l__
                                     (clojure.core/subvec
                                      input__62968_nth_1___r__
                                      0
                                      (clojure.core/min
                                       (clojure.core/count
                                        input__62968_nth_1___r__)
                                       1))]
                                    (if
                                     (clojure.core/=
                                      (clojure.core/count
                                       input__62968_nth_1___r___l__)
                                      1)
                                     (clojure.core/let
                                      [input__62968_nth_1___r___r__
                                       (clojure.core/subvec
                                        input__62968_nth_1___r__
                                        1)]
                                      (clojure.core/let
                                       [input__62968_nth_1___r___l___nth_0__
                                        (clojure.core/nth
                                         input__62968_nth_1___r___l__
                                         0)]
                                       (clojure.core/let
                                        [?ast
                                         input__62968_nth_1___r___l___nth_0__]
                                        (clojure.core/let
                                         [X__63933
                                          (clojure.core/count
                                           input__62968_nth_1___r___r__)]
                                         (if
                                          (clojure.core/= ?n X__63933)
                                          (clojure.core/let
                                           [!asts-2 []]
                                           (clojure.core/let
                                            [ret__14502__auto__
                                             (meander.runtime.zeta/epsilon-run-star-1
                                              input__62968_nth_1___r___r__
                                              [!asts-2 !asts-1]
                                              (clojure.core/fn
                                               [[!asts-2 !asts-1]
                                                input__63934]
                                               (clojure.core/let
                                                [input__63934_nth_0__
                                                 (clojure.core/nth
                                                  input__63934
                                                  0)]
                                                (clojure.core/let
                                                 [!asts-2
                                                  (clojure.core/conj
                                                   !asts-2
                                                   input__63934_nth_0__)]
                                                 [!asts-2 !asts-1])))
                                              (clojure.core/fn
                                               [[!asts-2 !asts-1]]
                                               (clojure.core/let
                                                [?form
                                                 input__62968_nth_2__]
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
                                                     (CATA__FN__63051
                                                      ['meander.dev.parse.zeta/make-and
                                                       [(clojure.core/let
                                                         [CATA_RESULT__15641__auto__
                                                          (CATA__FN__63051
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
                                                          (CATA__FN__63051
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
                          (state__64763)))]
                       (if
                        (meander.runtime.zeta/fail? result__64761)
                        (recur (clojure.core/next search_space__64760))
                        result__64761))
                      (state__64578))))]
                  (state__64757))
                 (state__64578)))]
              (state__64754))
             (state__64578)))
           (state__64578))
          (state__64578)))
        (state__64578
         []
         (clojure.core/letfn
          [(def__63939
            [arg__63962 ?__62977]
            (clojure.core/letfn
             [(state__64769
               []
               (clojure.core/let
                [x__63963 "meander.zeta"]
                (if
                 (clojure.core/= ?__62977 x__63963)
                 [?__62977]
                 (state__64770))))
              (state__64770
               []
               (if
                (clojure.core/map? arg__63962)
                (clojure.core/let
                 [VAL__63964 (.valAt arg__63962 :aliases)]
                 (if
                  (clojure.core/map? VAL__63964)
                  (clojure.core/let
                   [X__63966 (clojure.core/set VAL__63964)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__63966))
                    (clojure.core/loop
                     [search_space__64771 (clojure.core/seq X__63966)]
                     (if
                      (clojure.core/seq search_space__64771)
                      (clojure.core/let
                       [elem__63967
                        (clojure.core/first search_space__64771)
                        result__64772
                        (clojure.core/let
                         [elem__63967_nth_0__
                          (clojure.core/nth elem__63967 0)
                          elem__63967_nth_1__
                          (clojure.core/nth elem__63967 1)]
                         (if
                          (clojure.core/symbol? elem__63967_nth_0__)
                          (clojure.core/let
                           [X__63969
                            (clojure.core/name elem__63967_nth_0__)]
                           (if
                            (clojure.core/= ?__62977 X__63969)
                            (if
                             (clojure.core/symbol? elem__63967_nth_1__)
                             (clojure.core/let
                              [X__63971
                               (clojure.core/name elem__63967_nth_1__)]
                              (clojure.core/case
                               X__63971
                               ("meander.zeta")
                               [?__62977]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__64772)
                        (recur (clojure.core/next search_space__64771))
                        result__64772))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__64769)))]
          (if
           (clojure.core/vector? input__62968)
           (if
            (clojure.core/= (clojure.core/count input__62968) 2)
            (clojure.core/let
             [input__62968_nth_0__
              (clojure.core/nth input__62968 0)
              input__62968_nth_1__
              (clojure.core/nth input__62968 1)]
             (if
              (clojure.core/seq? input__62968_nth_0__)
              (clojure.core/let
               [input__62968_nth_0___l__
                (clojure.core/take 1 input__62968_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__62968_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__62968_nth_0___r__
                  (clojure.core/drop 1 input__62968_nth_0__)]
                 (clojure.core/let
                  [input__62968_nth_0___l___nth_0__
                   (clojure.core/nth input__62968_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__62968_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__63949
                     (clojure.core/namespace
                      input__62968_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__62977 X__63949]
                     (clojure.core/let
                      [X__63951
                       (clojure.core/name
                        input__62968_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__63951
                       ("cata")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__63939 input__62968_nth_1__ ?__62977)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__64579)
                         (clojure.core/let
                          [[?__62977] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__62968)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__62968)
                             2)
                            (clojure.core/let
                             [input__62968_nth_0__
                              (clojure.core/nth input__62968 0)
                              input__62968_nth_1__
                              (clojure.core/nth input__62968 1)]
                             (if
                              (clojure.core/seq? input__62968_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__62968_nth_0__)
                                2)
                               (clojure.core/let
                                [input__62968_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__62968_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__62968_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__62968_nth_0__]
                                  (clojure.core/let
                                   [?env input__62968_nth_1__]
                                   (try
                                    [{:tag :cata,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__15641__auto__
                                        (CATA__FN__63051
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
                               (state__64579))
                              (state__64579)))
                            (state__64579))
                           (state__64579)))))
                       (state__64579)))))
                   (state__64579))))
                (state__64579)))
              (state__64579)))
            (state__64579))
           (state__64579))))
        (state__64579
         []
         (clojure.core/letfn
          [(def__63973
            [arg__63996 ?__62978]
            (clojure.core/letfn
             [(state__64774
               []
               (clojure.core/let
                [x__63997 "meander.zeta"]
                (if
                 (clojure.core/= ?__62978 x__63997)
                 [?__62978]
                 (state__64775))))
              (state__64775
               []
               (if
                (clojure.core/map? arg__63996)
                (clojure.core/let
                 [VAL__63998 (.valAt arg__63996 :aliases)]
                 (if
                  (clojure.core/map? VAL__63998)
                  (clojure.core/let
                   [X__64000 (clojure.core/set VAL__63998)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__64000))
                    (clojure.core/loop
                     [search_space__64776 (clojure.core/seq X__64000)]
                     (if
                      (clojure.core/seq search_space__64776)
                      (clojure.core/let
                       [elem__64001
                        (clojure.core/first search_space__64776)
                        result__64777
                        (clojure.core/let
                         [elem__64001_nth_0__
                          (clojure.core/nth elem__64001 0)
                          elem__64001_nth_1__
                          (clojure.core/nth elem__64001 1)]
                         (if
                          (clojure.core/symbol? elem__64001_nth_0__)
                          (clojure.core/let
                           [X__64003
                            (clojure.core/name elem__64001_nth_0__)]
                           (if
                            (clojure.core/= ?__62978 X__64003)
                            (if
                             (clojure.core/symbol? elem__64001_nth_1__)
                             (clojure.core/let
                              [X__64005
                               (clojure.core/name elem__64001_nth_1__)]
                              (clojure.core/case
                               X__64005
                               ("meander.zeta")
                               [?__62978]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__64777)
                        (recur (clojure.core/next search_space__64776))
                        result__64777))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__64774)))]
          (if
           (clojure.core/vector? input__62968)
           (if
            (clojure.core/= (clojure.core/count input__62968) 2)
            (clojure.core/let
             [input__62968_nth_0__
              (clojure.core/nth input__62968 0)
              input__62968_nth_1__
              (clojure.core/nth input__62968 1)]
             (if
              (clojure.core/seq? input__62968_nth_0__)
              (clojure.core/let
               [input__62968_nth_0___l__
                (clojure.core/take 1 input__62968_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__62968_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__62968_nth_0___r__
                  (clojure.core/drop 1 input__62968_nth_0__)]
                 (clojure.core/let
                  [input__62968_nth_0___l___nth_0__
                   (clojure.core/nth input__62968_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__62968_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__63983
                     (clojure.core/namespace
                      input__62968_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__62978 X__63983]
                     (clojure.core/let
                      [X__63985
                       (clojure.core/name
                        input__62968_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__63985
                       ("fold")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__63973 input__62968_nth_1__ ?__62978)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__64580)
                         (clojure.core/let
                          [[?__62978] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__62968)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__62968)
                             2)
                            (clojure.core/let
                             [input__62968_nth_0__
                              (clojure.core/nth input__62968 0)
                              input__62968_nth_1__
                              (clojure.core/nth input__62968 1)]
                             (if
                              (clojure.core/seq? input__62968_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 4)
                                 input__62968_nth_0__)
                                4)
                               (clojure.core/let
                                [input__62968_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__62968_nth_0__
                                  1)
                                 input__62968_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__62968_nth_0__
                                  2)
                                 input__62968_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__62968_nth_0__
                                  3)]
                                (clojure.core/let
                                 [?mutable-variable
                                  input__62968_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?initial-value
                                   input__62968_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?fold-function
                                    input__62968_nth_0___nth_3__]
                                   (clojure.core/let
                                    [?form input__62968_nth_0__]
                                    (clojure.core/let
                                     [?env input__62968_nth_1__]
                                     (try
                                      [(clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__63051
                                          ['meander.dev.parse.zeta/make-fold
                                           (clojure.core/let
                                            [CATA_RESULT__15641__auto__
                                             (CATA__FN__63051
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
                                             (CATA__FN__63051
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
                               (state__64580))
                              (state__64580)))
                            (state__64580))
                           (state__64580)))))
                       (state__64580)))))
                   (state__64580))))
                (state__64580)))
              (state__64580)))
            (state__64580))
           (state__64580))))
        (state__64580
         []
         (if
          (clojure.core/vector? input__62968)
          (if
           (clojure.core/= (clojure.core/count input__62968) 5)
           (clojure.core/let
            [input__62968_nth_0__
             (clojure.core/nth input__62968 0)
             input__62968_nth_1__
             (clojure.core/nth input__62968 1)
             input__62968_nth_2__
             (clojure.core/nth input__62968 2)
             input__62968_nth_3__
             (clojure.core/nth input__62968 3)
             input__62968_nth_4__
             (clojure.core/nth input__62968 4)]
            (clojure.core/case
             input__62968_nth_0__
             (meander.dev.parse.zeta/make-fold)
             (if
              (clojure.core/map? input__62968_nth_1__)
              (clojure.core/let
               [VAL__64008 (.valAt input__62968_nth_1__ :tag)]
               (clojure.core/case
                VAL__64008
                (:mutable-variable)
                (clojure.core/let
                 [?variable-ast input__62968_nth_1__]
                 (clojure.core/let
                  [?initial-value-ast input__62968_nth_2__]
                  (clojure.core/let
                   [?fold-function input__62968_nth_3__]
                   (clojure.core/let
                    [?form input__62968_nth_4__]
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
                (state__64581)))
              (state__64581))
             (state__64581)))
           (state__64581))
          (state__64581)))
        (state__64581
         []
         (clojure.core/letfn
          [(def__64010
            [arg__64033 ?__62979]
            (clojure.core/letfn
             [(state__64779
               []
               (clojure.core/let
                [x__64034 "meander.zeta"]
                (if
                 (clojure.core/= ?__62979 x__64034)
                 [?__62979]
                 (state__64780))))
              (state__64780
               []
               (if
                (clojure.core/map? arg__64033)
                (clojure.core/let
                 [VAL__64035 (.valAt arg__64033 :aliases)]
                 (if
                  (clojure.core/map? VAL__64035)
                  (clojure.core/let
                   [X__64037 (clojure.core/set VAL__64035)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__64037))
                    (clojure.core/loop
                     [search_space__64781 (clojure.core/seq X__64037)]
                     (if
                      (clojure.core/seq search_space__64781)
                      (clojure.core/let
                       [elem__64038
                        (clojure.core/first search_space__64781)
                        result__64782
                        (clojure.core/let
                         [elem__64038_nth_0__
                          (clojure.core/nth elem__64038 0)
                          elem__64038_nth_1__
                          (clojure.core/nth elem__64038 1)]
                         (if
                          (clojure.core/symbol? elem__64038_nth_0__)
                          (clojure.core/let
                           [X__64040
                            (clojure.core/name elem__64038_nth_0__)]
                           (if
                            (clojure.core/= ?__62979 X__64040)
                            (if
                             (clojure.core/symbol? elem__64038_nth_1__)
                             (clojure.core/let
                              [X__64042
                               (clojure.core/name elem__64038_nth_1__)]
                              (clojure.core/case
                               X__64042
                               ("meander.zeta")
                               [?__62979]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__64782)
                        (recur (clojure.core/next search_space__64781))
                        result__64782))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__64779)))]
          (if
           (clojure.core/vector? input__62968)
           (if
            (clojure.core/= (clojure.core/count input__62968) 2)
            (clojure.core/let
             [input__62968_nth_0__
              (clojure.core/nth input__62968 0)
              input__62968_nth_1__
              (clojure.core/nth input__62968 1)]
             (if
              (clojure.core/seq? input__62968_nth_0__)
              (clojure.core/let
               [input__62968_nth_0___l__
                (clojure.core/take 1 input__62968_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__62968_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__62968_nth_0___r__
                  (clojure.core/drop 1 input__62968_nth_0__)]
                 (clojure.core/let
                  [input__62968_nth_0___l___nth_0__
                   (clojure.core/nth input__62968_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__62968_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__64020
                     (clojure.core/namespace
                      input__62968_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__62979 X__64020]
                     (clojure.core/let
                      [X__64022
                       (clojure.core/name
                        input__62968_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__64022
                       ("keyword")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__64010 input__62968_nth_1__ ?__62979)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__64582)
                         (clojure.core/let
                          [[?__62979] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__62968)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__62968)
                             2)
                            (clojure.core/let
                             [input__62968_nth_0__
                              (clojure.core/nth input__62968 0)
                              input__62968_nth_1__
                              (clojure.core/nth input__62968 1)]
                             (if
                              (clojure.core/seq? input__62968_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__62968_nth_0__)
                                2)
                               (clojure.core/let
                                [input__62968_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__62968_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?name input__62968_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__62968_nth_0__]
                                  (clojure.core/let
                                   [?env input__62968_nth_1__]
                                   (try
                                    [{:tag :keyword,
                                      :name
                                      (clojure.core/let
                                       [CATA_RESULT__15641__auto__
                                        (CATA__FN__63051 [?name ?env])]
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
                               (state__64582))
                              (state__64582)))
                            (state__64582))
                           (state__64582)))))
                       (state__64582)))))
                   (state__64582))))
                (state__64582)))
              (state__64582)))
            (state__64582))
           (state__64582))))
        (state__64582
         []
         (clojure.core/letfn
          [(def__64044
            [arg__64067 ?__62980]
            (clojure.core/letfn
             [(state__64784
               []
               (clojure.core/let
                [x__64068 "meander.zeta"]
                (if
                 (clojure.core/= ?__62980 x__64068)
                 [?__62980]
                 (state__64785))))
              (state__64785
               []
               (if
                (clojure.core/map? arg__64067)
                (clojure.core/let
                 [VAL__64069 (.valAt arg__64067 :aliases)]
                 (if
                  (clojure.core/map? VAL__64069)
                  (clojure.core/let
                   [X__64071 (clojure.core/set VAL__64069)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__64071))
                    (clojure.core/loop
                     [search_space__64786 (clojure.core/seq X__64071)]
                     (if
                      (clojure.core/seq search_space__64786)
                      (clojure.core/let
                       [elem__64072
                        (clojure.core/first search_space__64786)
                        result__64787
                        (clojure.core/let
                         [elem__64072_nth_0__
                          (clojure.core/nth elem__64072 0)
                          elem__64072_nth_1__
                          (clojure.core/nth elem__64072 1)]
                         (if
                          (clojure.core/symbol? elem__64072_nth_0__)
                          (clojure.core/let
                           [X__64074
                            (clojure.core/name elem__64072_nth_0__)]
                           (if
                            (clojure.core/= ?__62980 X__64074)
                            (if
                             (clojure.core/symbol? elem__64072_nth_1__)
                             (clojure.core/let
                              [X__64076
                               (clojure.core/name elem__64072_nth_1__)]
                              (clojure.core/case
                               X__64076
                               ("meander.zeta")
                               [?__62980]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__64787)
                        (recur (clojure.core/next search_space__64786))
                        result__64787))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__64784)))]
          (if
           (clojure.core/vector? input__62968)
           (if
            (clojure.core/= (clojure.core/count input__62968) 2)
            (clojure.core/let
             [input__62968_nth_0__
              (clojure.core/nth input__62968 0)
              input__62968_nth_1__
              (clojure.core/nth input__62968 1)]
             (if
              (clojure.core/seq? input__62968_nth_0__)
              (clojure.core/let
               [input__62968_nth_0___l__
                (clojure.core/take 1 input__62968_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__62968_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__62968_nth_0___r__
                  (clojure.core/drop 1 input__62968_nth_0__)]
                 (clojure.core/let
                  [input__62968_nth_0___l___nth_0__
                   (clojure.core/nth input__62968_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__62968_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__64054
                     (clojure.core/namespace
                      input__62968_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__62980 X__64054]
                     (clojure.core/let
                      [X__64056
                       (clojure.core/name
                        input__62968_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__64056
                       ("let")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__64044 input__62968_nth_1__ ?__62980)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__64583)
                         (clojure.core/let
                          [[?__62980] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__62968)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__62968)
                             2)
                            (clojure.core/let
                             [input__62968_nth_0__
                              (clojure.core/nth input__62968 0)
                              input__62968_nth_1__
                              (clojure.core/nth input__62968 1)]
                             (if
                              (clojure.core/seq? input__62968_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__62968_nth_0__)
                                3)
                               (clojure.core/let
                                [input__62968_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__62968_nth_0__
                                  1)
                                 input__62968_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__62968_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?pattern
                                  input__62968_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?expression
                                   input__62968_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__62968_nth_0__]
                                   (clojure.core/let
                                    [?env input__62968_nth_1__]
                                    (try
                                     [{:tag :let,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__63051
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
                               (state__64583))
                              (state__64583)))
                            (state__64583))
                           (state__64583)))))
                       (state__64583)))))
                   (state__64583))))
                (state__64583)))
              (state__64583)))
            (state__64583))
           (state__64583))))
        (state__64583
         []
         (clojure.core/letfn
          [(def__64078
            [arg__64101 ?__62981]
            (clojure.core/letfn
             [(state__64789
               []
               (clojure.core/let
                [x__64102 "meander.zeta"]
                (if
                 (clojure.core/= ?__62981 x__64102)
                 [?__62981]
                 (state__64790))))
              (state__64790
               []
               (if
                (clojure.core/map? arg__64101)
                (clojure.core/let
                 [VAL__64103 (.valAt arg__64101 :aliases)]
                 (if
                  (clojure.core/map? VAL__64103)
                  (clojure.core/let
                   [X__64105 (clojure.core/set VAL__64103)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__64105))
                    (clojure.core/loop
                     [search_space__64791 (clojure.core/seq X__64105)]
                     (if
                      (clojure.core/seq search_space__64791)
                      (clojure.core/let
                       [elem__64106
                        (clojure.core/first search_space__64791)
                        result__64792
                        (clojure.core/let
                         [elem__64106_nth_0__
                          (clojure.core/nth elem__64106 0)
                          elem__64106_nth_1__
                          (clojure.core/nth elem__64106 1)]
                         (if
                          (clojure.core/symbol? elem__64106_nth_0__)
                          (clojure.core/let
                           [X__64108
                            (clojure.core/name elem__64106_nth_0__)]
                           (if
                            (clojure.core/= ?__62981 X__64108)
                            (if
                             (clojure.core/symbol? elem__64106_nth_1__)
                             (clojure.core/let
                              [X__64110
                               (clojure.core/name elem__64106_nth_1__)]
                              (clojure.core/case
                               X__64110
                               ("meander.zeta")
                               [?__62981]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__64792)
                        (recur (clojure.core/next search_space__64791))
                        result__64792))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__64789)))]
          (if
           (clojure.core/vector? input__62968)
           (if
            (clojure.core/= (clojure.core/count input__62968) 2)
            (clojure.core/let
             [input__62968_nth_0__
              (clojure.core/nth input__62968 0)
              input__62968_nth_1__
              (clojure.core/nth input__62968 1)]
             (if
              (clojure.core/seq? input__62968_nth_0__)
              (clojure.core/let
               [input__62968_nth_0___l__
                (clojure.core/take 1 input__62968_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__62968_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__62968_nth_0___r__
                  (clojure.core/drop 1 input__62968_nth_0__)]
                 (clojure.core/let
                  [input__62968_nth_0___l___nth_0__
                   (clojure.core/nth input__62968_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__62968_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__64088
                     (clojure.core/namespace
                      input__62968_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__62981 X__64088]
                     (clojure.core/let
                      [X__64090
                       (clojure.core/name
                        input__62968_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__64090
                       ("let")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__64078 input__62968_nth_1__ ?__62981)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__64584)
                         (clojure.core/let
                          [[?__62981] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__62968)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__62968)
                             2)
                            (clojure.core/let
                             [input__62968_nth_0__
                              (clojure.core/nth input__62968 0)
                              input__62968_nth_1__
                              (clojure.core/nth input__62968 1)]
                             (if
                              (clojure.core/seq? input__62968_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 4)
                                 input__62968_nth_0__)
                                4)
                               (clojure.core/let
                                [input__62968_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__62968_nth_0__
                                  1)
                                 input__62968_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__62968_nth_0__
                                  2)
                                 input__62968_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__62968_nth_0__
                                  3)]
                                (clojure.core/let
                                 [?pattern
                                  input__62968_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?expression
                                   input__62968_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?next input__62968_nth_0___nth_3__]
                                   (clojure.core/let
                                    [?form input__62968_nth_0__]
                                    (clojure.core/let
                                     [?env input__62968_nth_1__]
                                     (try
                                      [{:tag :let,
                                        :pattern
                                        (clojure.core/let
                                         [CATA_RESULT__15641__auto__
                                          (CATA__FN__63051
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
                                          (CATA__FN__63051
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
                               (state__64584))
                              (state__64584)))
                            (state__64584))
                           (state__64584)))))
                       (state__64584)))))
                   (state__64584))))
                (state__64584)))
              (state__64584)))
            (state__64584))
           (state__64584))))
        (state__64584
         []
         (clojure.core/letfn
          [(def__64112
            [arg__64135 ?__62982]
            (clojure.core/letfn
             [(state__64794
               []
               (clojure.core/let
                [x__64136 "meander.zeta"]
                (if
                 (clojure.core/= ?__62982 x__64136)
                 [?__62982]
                 (state__64795))))
              (state__64795
               []
               (if
                (clojure.core/map? arg__64135)
                (clojure.core/let
                 [VAL__64137 (.valAt arg__64135 :aliases)]
                 (if
                  (clojure.core/map? VAL__64137)
                  (clojure.core/let
                   [X__64139 (clojure.core/set VAL__64137)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__64139))
                    (clojure.core/loop
                     [search_space__64796 (clojure.core/seq X__64139)]
                     (if
                      (clojure.core/seq search_space__64796)
                      (clojure.core/let
                       [elem__64140
                        (clojure.core/first search_space__64796)
                        result__64797
                        (clojure.core/let
                         [elem__64140_nth_0__
                          (clojure.core/nth elem__64140 0)
                          elem__64140_nth_1__
                          (clojure.core/nth elem__64140 1)]
                         (if
                          (clojure.core/symbol? elem__64140_nth_0__)
                          (clojure.core/let
                           [X__64142
                            (clojure.core/name elem__64140_nth_0__)]
                           (if
                            (clojure.core/= ?__62982 X__64142)
                            (if
                             (clojure.core/symbol? elem__64140_nth_1__)
                             (clojure.core/let
                              [X__64144
                               (clojure.core/name elem__64140_nth_1__)]
                              (clojure.core/case
                               X__64144
                               ("meander.zeta")
                               [?__62982]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__64797)
                        (recur (clojure.core/next search_space__64796))
                        result__64797))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__64794)))]
          (if
           (clojure.core/vector? input__62968)
           (if
            (clojure.core/= (clojure.core/count input__62968) 2)
            (clojure.core/let
             [input__62968_nth_0__
              (clojure.core/nth input__62968 0)
              input__62968_nth_1__
              (clojure.core/nth input__62968 1)]
             (if
              (clojure.core/seq? input__62968_nth_0__)
              (clojure.core/let
               [input__62968_nth_0___l__
                (clojure.core/take 1 input__62968_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__62968_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__62968_nth_0___r__
                  (clojure.core/drop 1 input__62968_nth_0__)]
                 (clojure.core/let
                  [input__62968_nth_0___l___nth_0__
                   (clojure.core/nth input__62968_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__62968_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__64122
                     (clojure.core/namespace
                      input__62968_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__62982 X__64122]
                     (clojure.core/let
                      [X__64124
                       (clojure.core/name
                        input__62968_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__64124
                       ("not")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__64112 input__62968_nth_1__ ?__62982)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__64585)
                         (clojure.core/let
                          [[?__62982] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__62968)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__62968)
                             2)
                            (clojure.core/let
                             [input__62968_nth_0__
                              (clojure.core/nth input__62968 0)
                              input__62968_nth_1__
                              (clojure.core/nth input__62968 1)]
                             (if
                              (clojure.core/seq? input__62968_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__62968_nth_0__)
                                2)
                               (clojure.core/let
                                [input__62968_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__62968_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__62968_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__62968_nth_0__]
                                  (clojure.core/let
                                   [?env input__62968_nth_1__]
                                   (try
                                    [{:tag :not,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__15641__auto__
                                        (CATA__FN__63051
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
                               (state__64585))
                              (state__64585)))
                            (state__64585))
                           (state__64585)))))
                       (state__64585)))))
                   (state__64585))))
                (state__64585)))
              (state__64585)))
            (state__64585))
           (state__64585))))
        (state__64585
         []
         (clojure.core/letfn
          [(def__64146
            [arg__64171 ?__62983]
            (clojure.core/letfn
             [(state__64799
               []
               (clojure.core/let
                [x__64172 "meander.zeta"]
                (if
                 (clojure.core/= ?__62983 x__64172)
                 [?__62983]
                 (state__64800))))
              (state__64800
               []
               (if
                (clojure.core/map? arg__64171)
                (clojure.core/let
                 [VAL__64173 (.valAt arg__64171 :aliases)]
                 (if
                  (clojure.core/map? VAL__64173)
                  (clojure.core/let
                   [X__64175 (clojure.core/set VAL__64173)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__64175))
                    (clojure.core/loop
                     [search_space__64801 (clojure.core/seq X__64175)]
                     (if
                      (clojure.core/seq search_space__64801)
                      (clojure.core/let
                       [elem__64176
                        (clojure.core/first search_space__64801)
                        result__64802
                        (clojure.core/let
                         [elem__64176_nth_0__
                          (clojure.core/nth elem__64176 0)
                          elem__64176_nth_1__
                          (clojure.core/nth elem__64176 1)]
                         (if
                          (clojure.core/symbol? elem__64176_nth_0__)
                          (clojure.core/let
                           [X__64178
                            (clojure.core/name elem__64176_nth_0__)]
                           (if
                            (clojure.core/= ?__62983 X__64178)
                            (if
                             (clojure.core/symbol? elem__64176_nth_1__)
                             (clojure.core/let
                              [X__64180
                               (clojure.core/name elem__64176_nth_1__)]
                              (clojure.core/case
                               X__64180
                               ("meander.zeta")
                               [?__62983]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__64802)
                        (recur (clojure.core/next search_space__64801))
                        result__64802))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__64799)))]
          (if
           (clojure.core/vector? input__62968)
           (if
            (clojure.core/= (clojure.core/count input__62968) 2)
            (clojure.core/let
             [input__62968_nth_0__
              (clojure.core/nth input__62968 0)
              input__62968_nth_1__
              (clojure.core/nth input__62968 1)]
             (if
              (clojure.core/seq? input__62968_nth_0__)
              (clojure.core/let
               [input__62968_nth_0___l__
                (clojure.core/take 1 input__62968_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__62968_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__62968_nth_0___r__
                  (clojure.core/drop 1 input__62968_nth_0__)]
                 (clojure.core/let
                  [input__62968_nth_0___l___nth_0__
                   (clojure.core/nth input__62968_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__62968_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__64158
                     (clojure.core/namespace
                      input__62968_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__62983 X__64158]
                     (clojure.core/let
                      [X__64160
                       (clojure.core/name
                        input__62968_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__64160
                       ("or")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__64146 input__62968_nth_1__ ?__62983)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__64586)
                         (clojure.core/let
                          [[?__62983] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__62968)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__62968)
                             2)
                            (clojure.core/let
                             [input__62968_nth_0__
                              (clojure.core/nth input__62968 0)
                              input__62968_nth_1__
                              (clojure.core/nth input__62968 1)]
                             (if
                              (clojure.core/seq? input__62968_nth_0__)
                              (clojure.core/let
                               [input__62968_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__62968_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__62968_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__62968_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__62968_nth_0__)]
                                 (clojure.core/let
                                  [!forms
                                   (clojure.core/vec
                                    input__62968_nth_0___r__)]
                                  (clojure.core/let
                                   [?form input__62968_nth_0__]
                                   (clojure.core/let
                                    [?env input__62968_nth_1__]
                                    (try
                                     [(clojure.core/let
                                       [!forms__counter
                                        (meander.runtime.zeta/iterator
                                         !forms)]
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__63051
                                          ['meander.dev.parse.zeta/make-or
                                           (clojure.core/into
                                            []
                                            (clojure.core/loop
                                             [return__63054
                                              (clojure.core/transient
                                               [])]
                                             (if
                                              (clojure.core/and
                                               (.hasNext
                                                !forms__counter))
                                              (recur
                                               (clojure.core/conj!
                                                return__63054
                                                (clojure.core/let
                                                 [CATA_RESULT__15641__auto__
                                                  (CATA__FN__63051
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
                                               return__63054))))
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
                                (state__64586)))
                              (state__64586)))
                            (state__64586))
                           (state__64586)))))
                       (state__64586)))))
                   (state__64586))))
                (state__64586)))
              (state__64586)))
            (state__64586))
           (state__64586))))
        (state__64586
         []
         (if
          (clojure.core/vector? input__62968)
          (if
           (clojure.core/= (clojure.core/count input__62968) 3)
           (clojure.core/let
            [input__62968_nth_0__
             (clojure.core/nth input__62968 0)
             input__62968_nth_1__
             (clojure.core/nth input__62968 1)
             input__62968_nth_2__
             (clojure.core/nth input__62968 2)]
            (clojure.core/case
             input__62968_nth_0__
             (meander.dev.parse.zeta/make-or)
             (clojure.core/letfn
              [(state__64804
                []
                (if
                 (clojure.core/vector? input__62968_nth_1__)
                 (clojure.core/case
                  input__62968_nth_1__
                  ([])
                  (clojure.core/let
                   [?form input__62968_nth_2__]
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
                  (state__64805))
                 (state__64805)))
               (state__64805
                []
                (clojure.core/case
                 input__62968_nth_2__
                 (nil)
                 (if
                  (clojure.core/vector? input__62968_nth_1__)
                  (if
                   (clojure.core/=
                    (clojure.core/count input__62968_nth_1__)
                    1)
                   (clojure.core/let
                    [input__62968_nth_1___nth_0__
                     (clojure.core/nth input__62968_nth_1__ 0)]
                    (clojure.core/let
                     [?ast-a input__62968_nth_1___nth_0__]
                     (try
                      [?ast-a]
                      (catch
                       java.lang.Exception
                       e__16581__auto__
                       (if
                        (meander.runtime.zeta/fail? e__16581__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__16581__auto__))))))
                   (state__64806))
                  (state__64806))
                 (state__64806)))
               (state__64806
                []
                (if
                 (clojure.core/vector? input__62968_nth_1__)
                 (clojure.core/letfn
                  [(state__64807
                    []
                    (if
                     (clojure.core/=
                      (clojure.core/count input__62968_nth_1__)
                      1)
                     (clojure.core/let
                      [input__62968_nth_1___nth_0__
                       (clojure.core/nth input__62968_nth_1__ 0)]
                      (clojure.core/let
                       [?ast-a input__62968_nth_1___nth_0__]
                       (clojure.core/let
                        [?form input__62968_nth_2__]
                        (try
                         [(clojure.core/let
                           [CATA_RESULT__15641__auto__
                            (CATA__FN__63051
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
                     (state__64808)))
                   (state__64808
                    []
                    (if
                     (clojure.core/=
                      (clojure.core/count input__62968_nth_1__)
                      2)
                     (clojure.core/let
                      [input__62968_nth_1___nth_0__
                       (clojure.core/nth input__62968_nth_1__ 0)
                       input__62968_nth_1___nth_1__
                       (clojure.core/nth input__62968_nth_1__ 1)]
                      (clojure.core/let
                       [?ast-a input__62968_nth_1___nth_0__]
                       (clojure.core/let
                        [?ast-b input__62968_nth_1___nth_1__]
                        (clojure.core/let
                         [?form input__62968_nth_2__]
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
                     (state__64809)))
                   (state__64809
                    []
                    (clojure.core/loop
                     [search_space__64810
                      (meander.runtime.zeta/epsilon-partitions
                       2
                       input__62968_nth_1__)]
                     (if
                      (clojure.core/seq search_space__64810)
                      (clojure.core/let
                       [input__62968_nth_1___parts__
                        (clojure.core/first search_space__64810)
                        result__64811
                        (clojure.core/let
                         [input__62968_nth_1___l__
                          (clojure.core/nth
                           input__62968_nth_1___parts__
                           0)
                          input__62968_nth_1___r__
                          (clojure.core/nth
                           input__62968_nth_1___parts__
                           1)]
                         (clojure.core/letfn
                          [(state__64813
                            []
                            (clojure.core/let
                             [!asts-1 []]
                             (clojure.core/let
                              [ret__14502__auto__
                               (meander.runtime.zeta/epsilon-run-star-1
                                input__62968_nth_1___l__
                                [!asts-1]
                                (clojure.core/fn
                                 [[!asts-1] input__64207]
                                 (clojure.core/let
                                  [input__64207_nth_0__
                                   (clojure.core/nth input__64207 0)]
                                  (clojure.core/let
                                   [!asts-1
                                    (clojure.core/conj
                                     !asts-1
                                     input__64207_nth_0__)]
                                   [!asts-1])))
                                (clojure.core/fn
                                 [[!asts-1]]
                                 (clojure.core/let
                                  [X__64200
                                   (clojure.core/count
                                    input__62968_nth_1___l__)]
                                  (clojure.core/let
                                   [?n X__64200]
                                   (clojure.core/let
                                    [X__64204
                                     (clojure.core/count
                                      input__62968_nth_1___r__)]
                                    (if
                                     (clojure.core/= ?n X__64204)
                                     (clojure.core/let
                                      [!asts-2 []]
                                      (clojure.core/let
                                       [ret__14502__auto__
                                        (meander.runtime.zeta/epsilon-run-star-1
                                         input__62968_nth_1___r__
                                         [!asts-2 !asts-1]
                                         (clojure.core/fn
                                          [[!asts-2 !asts-1]
                                           input__64205]
                                          (clojure.core/let
                                           [input__64205_nth_0__
                                            (clojure.core/nth
                                             input__64205
                                             0)]
                                           (clojure.core/let
                                            [!asts-2
                                             (clojure.core/conj
                                              !asts-2
                                              input__64205_nth_0__)]
                                            [!asts-2 !asts-1])))
                                         (clojure.core/fn
                                          [[!asts-2 !asts-1]]
                                          (clojure.core/let
                                           [?form input__62968_nth_2__]
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
                                                (CATA__FN__63051
                                                 ['meander.dev.parse.zeta/make-or
                                                  [(clojure.core/let
                                                    [CATA_RESULT__15641__auto__
                                                     (CATA__FN__63051
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
                                                     (CATA__FN__63051
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
                                        (state__64814)
                                        ret__14502__auto__)))
                                     (state__64814)))))))]
                              (if
                               (meander.runtime.zeta/fail?
                                ret__14502__auto__)
                               (state__64814)
                               ret__14502__auto__))))
                           (state__64814
                            []
                            (clojure.core/let
                             [!asts-1 []]
                             (clojure.core/let
                              [ret__14502__auto__
                               (meander.runtime.zeta/epsilon-run-star-1
                                input__62968_nth_1___l__
                                [!asts-1]
                                (clojure.core/fn
                                 [[!asts-1] input__64223]
                                 (clojure.core/let
                                  [input__64223_nth_0__
                                   (clojure.core/nth input__64223 0)]
                                  (clojure.core/let
                                   [!asts-1
                                    (clojure.core/conj
                                     !asts-1
                                     input__64223_nth_0__)]
                                   [!asts-1])))
                                (clojure.core/fn
                                 [[!asts-1]]
                                 (clojure.core/let
                                  [X__64214
                                   (clojure.core/count
                                    input__62968_nth_1___l__)]
                                  (clojure.core/let
                                   [?n X__64214]
                                   (clojure.core/let
                                    [input__62968_nth_1___r___l__
                                     (clojure.core/subvec
                                      input__62968_nth_1___r__
                                      0
                                      (clojure.core/min
                                       (clojure.core/count
                                        input__62968_nth_1___r__)
                                       1))]
                                    (if
                                     (clojure.core/=
                                      (clojure.core/count
                                       input__62968_nth_1___r___l__)
                                      1)
                                     (clojure.core/let
                                      [input__62968_nth_1___r___r__
                                       (clojure.core/subvec
                                        input__62968_nth_1___r__
                                        1)]
                                      (clojure.core/let
                                       [input__62968_nth_1___r___l___nth_0__
                                        (clojure.core/nth
                                         input__62968_nth_1___r___l__
                                         0)]
                                       (clojure.core/let
                                        [?ast
                                         input__62968_nth_1___r___l___nth_0__]
                                        (clojure.core/let
                                         [X__64220
                                          (clojure.core/count
                                           input__62968_nth_1___r___r__)]
                                         (if
                                          (clojure.core/= ?n X__64220)
                                          (clojure.core/let
                                           [!asts-2 []]
                                           (clojure.core/let
                                            [ret__14502__auto__
                                             (meander.runtime.zeta/epsilon-run-star-1
                                              input__62968_nth_1___r___r__
                                              [!asts-2 !asts-1]
                                              (clojure.core/fn
                                               [[!asts-2 !asts-1]
                                                input__64221]
                                               (clojure.core/let
                                                [input__64221_nth_0__
                                                 (clojure.core/nth
                                                  input__64221
                                                  0)]
                                                (clojure.core/let
                                                 [!asts-2
                                                  (clojure.core/conj
                                                   !asts-2
                                                   input__64221_nth_0__)]
                                                 [!asts-2 !asts-1])))
                                              (clojure.core/fn
                                               [[!asts-2 !asts-1]]
                                               (clojure.core/let
                                                [?form
                                                 input__62968_nth_2__]
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
                                                     (CATA__FN__63051
                                                      ['meander.dev.parse.zeta/make-or
                                                       [(clojure.core/let
                                                         [CATA_RESULT__15641__auto__
                                                          (CATA__FN__63051
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
                                                          (CATA__FN__63051
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
                          (state__64813)))]
                       (if
                        (meander.runtime.zeta/fail? result__64811)
                        (recur (clojure.core/next search_space__64810))
                        result__64811))
                      (state__64587))))]
                  (state__64807))
                 (state__64587)))]
              (state__64804))
             (state__64587)))
           (state__64587))
          (state__64587)))
        (state__64587
         []
         (clojure.core/letfn
          [(def__64226
            [arg__64249 ?__62984]
            (clojure.core/letfn
             [(state__64819
               []
               (clojure.core/let
                [x__64250 "meander.zeta"]
                (if
                 (clojure.core/= ?__62984 x__64250)
                 [?__62984]
                 (state__64820))))
              (state__64820
               []
               (if
                (clojure.core/map? arg__64249)
                (clojure.core/let
                 [VAL__64251 (.valAt arg__64249 :aliases)]
                 (if
                  (clojure.core/map? VAL__64251)
                  (clojure.core/let
                   [X__64253 (clojure.core/set VAL__64251)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__64253))
                    (clojure.core/loop
                     [search_space__64821 (clojure.core/seq X__64253)]
                     (if
                      (clojure.core/seq search_space__64821)
                      (clojure.core/let
                       [elem__64254
                        (clojure.core/first search_space__64821)
                        result__64822
                        (clojure.core/let
                         [elem__64254_nth_0__
                          (clojure.core/nth elem__64254 0)
                          elem__64254_nth_1__
                          (clojure.core/nth elem__64254 1)]
                         (if
                          (clojure.core/symbol? elem__64254_nth_0__)
                          (clojure.core/let
                           [X__64256
                            (clojure.core/name elem__64254_nth_0__)]
                           (if
                            (clojure.core/= ?__62984 X__64256)
                            (if
                             (clojure.core/symbol? elem__64254_nth_1__)
                             (clojure.core/let
                              [X__64258
                               (clojure.core/name elem__64254_nth_1__)]
                              (clojure.core/case
                               X__64258
                               ("meander.zeta")
                               [?__62984]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__64822)
                        (recur (clojure.core/next search_space__64821))
                        result__64822))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__64819)))]
          (if
           (clojure.core/vector? input__62968)
           (if
            (clojure.core/= (clojure.core/count input__62968) 2)
            (clojure.core/let
             [input__62968_nth_0__
              (clojure.core/nth input__62968 0)
              input__62968_nth_1__
              (clojure.core/nth input__62968 1)]
             (if
              (clojure.core/seq? input__62968_nth_0__)
              (clojure.core/let
               [input__62968_nth_0___l__
                (clojure.core/take 1 input__62968_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__62968_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__62968_nth_0___r__
                  (clojure.core/drop 1 input__62968_nth_0__)]
                 (clojure.core/let
                  [input__62968_nth_0___l___nth_0__
                   (clojure.core/nth input__62968_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__62968_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__64236
                     (clojure.core/namespace
                      input__62968_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__62984 X__64236]
                     (clojure.core/let
                      [X__64238
                       (clojure.core/name
                        input__62968_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__64238
                       ("pred")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__64226 input__62968_nth_1__ ?__62984)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__64588)
                         (clojure.core/let
                          [[?__62984] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__62968)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__62968)
                             2)
                            (clojure.core/let
                             [input__62968_nth_0__
                              (clojure.core/nth input__62968 0)
                              input__62968_nth_1__
                              (clojure.core/nth input__62968 1)]
                             (if
                              (clojure.core/seq? input__62968_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__62968_nth_0__)
                                2)
                               (clojure.core/let
                                [input__62968_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__62968_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?expression
                                  input__62968_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__62968_nth_0__]
                                  (clojure.core/let
                                   [?env input__62968_nth_1__]
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
                               (state__64588))
                              (state__64588)))
                            (state__64588))
                           (state__64588)))))
                       (state__64588)))))
                   (state__64588))))
                (state__64588)))
              (state__64588)))
            (state__64588))
           (state__64588))))
        (state__64588
         []
         (clojure.core/letfn
          [(def__64260
            [arg__64283 ?__62985]
            (clojure.core/letfn
             [(state__64824
               []
               (clojure.core/let
                [x__64284 "meander.zeta"]
                (if
                 (clojure.core/= ?__62985 x__64284)
                 [?__62985]
                 (state__64825))))
              (state__64825
               []
               (if
                (clojure.core/map? arg__64283)
                (clojure.core/let
                 [VAL__64285 (.valAt arg__64283 :aliases)]
                 (if
                  (clojure.core/map? VAL__64285)
                  (clojure.core/let
                   [X__64287 (clojure.core/set VAL__64285)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__64287))
                    (clojure.core/loop
                     [search_space__64826 (clojure.core/seq X__64287)]
                     (if
                      (clojure.core/seq search_space__64826)
                      (clojure.core/let
                       [elem__64288
                        (clojure.core/first search_space__64826)
                        result__64827
                        (clojure.core/let
                         [elem__64288_nth_0__
                          (clojure.core/nth elem__64288 0)
                          elem__64288_nth_1__
                          (clojure.core/nth elem__64288 1)]
                         (if
                          (clojure.core/symbol? elem__64288_nth_0__)
                          (clojure.core/let
                           [X__64290
                            (clojure.core/name elem__64288_nth_0__)]
                           (if
                            (clojure.core/= ?__62985 X__64290)
                            (if
                             (clojure.core/symbol? elem__64288_nth_1__)
                             (clojure.core/let
                              [X__64292
                               (clojure.core/name elem__64288_nth_1__)]
                              (clojure.core/case
                               X__64292
                               ("meander.zeta")
                               [?__62985]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__64827)
                        (recur (clojure.core/next search_space__64826))
                        result__64827))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__64824)))]
          (if
           (clojure.core/vector? input__62968)
           (if
            (clojure.core/= (clojure.core/count input__62968) 2)
            (clojure.core/let
             [input__62968_nth_0__
              (clojure.core/nth input__62968 0)
              input__62968_nth_1__
              (clojure.core/nth input__62968 1)]
             (if
              (clojure.core/seq? input__62968_nth_0__)
              (clojure.core/let
               [input__62968_nth_0___l__
                (clojure.core/take 1 input__62968_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__62968_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__62968_nth_0___r__
                  (clojure.core/drop 1 input__62968_nth_0__)]
                 (clojure.core/let
                  [input__62968_nth_0___l___nth_0__
                   (clojure.core/nth input__62968_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__62968_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__64270
                     (clojure.core/namespace
                      input__62968_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__62985 X__64270]
                     (clojure.core/let
                      [X__64272
                       (clojure.core/name
                        input__62968_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__64272
                       ("pred")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__64260 input__62968_nth_1__ ?__62985)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__64589)
                         (clojure.core/let
                          [[?__62985] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__62968)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__62968)
                             2)
                            (clojure.core/let
                             [input__62968_nth_0__
                              (clojure.core/nth input__62968 0)
                              input__62968_nth_1__
                              (clojure.core/nth input__62968 1)]
                             (if
                              (clojure.core/seq? input__62968_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__62968_nth_0__)
                                3)
                               (clojure.core/let
                                [input__62968_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__62968_nth_0__
                                  1)
                                 input__62968_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__62968_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?expression
                                  input__62968_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?pattern
                                   input__62968_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__62968_nth_0__]
                                   (clojure.core/let
                                    [?env input__62968_nth_1__]
                                    (try
                                     [{:tag :pred,
                                       :expression
                                       {:tag :host-expression,
                                        :form ?expression},
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__63051
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
                               (state__64589))
                              (state__64589)))
                            (state__64589))
                           (state__64589)))))
                       (state__64589)))))
                   (state__64589))))
                (state__64589)))
              (state__64589)))
            (state__64589))
           (state__64589))))
        (state__64589
         []
         (clojure.core/letfn
          [(def__64294
            [arg__64317 ?__62986]
            (clojure.core/letfn
             [(state__64829
               []
               (clojure.core/let
                [x__64318 "meander.zeta"]
                (if
                 (clojure.core/= ?__62986 x__64318)
                 [?__62986]
                 (state__64830))))
              (state__64830
               []
               (if
                (clojure.core/map? arg__64317)
                (clojure.core/let
                 [VAL__64319 (.valAt arg__64317 :aliases)]
                 (if
                  (clojure.core/map? VAL__64319)
                  (clojure.core/let
                   [X__64321 (clojure.core/set VAL__64319)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__64321))
                    (clojure.core/loop
                     [search_space__64831 (clojure.core/seq X__64321)]
                     (if
                      (clojure.core/seq search_space__64831)
                      (clojure.core/let
                       [elem__64322
                        (clojure.core/first search_space__64831)
                        result__64832
                        (clojure.core/let
                         [elem__64322_nth_0__
                          (clojure.core/nth elem__64322 0)
                          elem__64322_nth_1__
                          (clojure.core/nth elem__64322 1)]
                         (if
                          (clojure.core/symbol? elem__64322_nth_0__)
                          (clojure.core/let
                           [X__64324
                            (clojure.core/name elem__64322_nth_0__)]
                           (if
                            (clojure.core/= ?__62986 X__64324)
                            (if
                             (clojure.core/symbol? elem__64322_nth_1__)
                             (clojure.core/let
                              [X__64326
                               (clojure.core/name elem__64322_nth_1__)]
                              (clojure.core/case
                               X__64326
                               ("meander.zeta")
                               [?__62986]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__64832)
                        (recur (clojure.core/next search_space__64831))
                        result__64832))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__64829)))]
          (if
           (clojure.core/vector? input__62968)
           (if
            (clojure.core/= (clojure.core/count input__62968) 2)
            (clojure.core/let
             [input__62968_nth_0__
              (clojure.core/nth input__62968 0)
              input__62968_nth_1__
              (clojure.core/nth input__62968 1)]
             (if
              (clojure.core/seq? input__62968_nth_0__)
              (clojure.core/let
               [input__62968_nth_0___l__
                (clojure.core/take 1 input__62968_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__62968_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__62968_nth_0___r__
                  (clojure.core/drop 1 input__62968_nth_0__)]
                 (clojure.core/let
                  [input__62968_nth_0___l___nth_0__
                   (clojure.core/nth input__62968_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__62968_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__64304
                     (clojure.core/namespace
                      input__62968_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__62986 X__64304]
                     (clojure.core/let
                      [X__64306
                       (clojure.core/name
                        input__62968_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__64306
                       ("re")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__64294 input__62968_nth_1__ ?__62986)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__64590)
                         (clojure.core/let
                          [[?__62986] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__62968)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__62968)
                             2)
                            (clojure.core/let
                             [input__62968_nth_0__
                              (clojure.core/nth input__62968 0)
                              input__62968_nth_1__
                              (clojure.core/nth input__62968 1)]
                             (if
                              (clojure.core/seq? input__62968_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__62968_nth_0__)
                                2)
                               (clojure.core/let
                                [input__62968_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__62968_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?regex input__62968_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__62968_nth_0__]
                                  (clojure.core/let
                                   [?env input__62968_nth_1__]
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
                               (state__64590))
                              (state__64590)))
                            (state__64590))
                           (state__64590)))))
                       (state__64590)))))
                   (state__64590))))
                (state__64590)))
              (state__64590)))
            (state__64590))
           (state__64590))))
        (state__64590
         []
         (clojure.core/letfn
          [(def__64328
            [arg__64351 ?__62987]
            (clojure.core/letfn
             [(state__64834
               []
               (clojure.core/let
                [x__64352 "meander.zeta"]
                (if
                 (clojure.core/= ?__62987 x__64352)
                 [?__62987]
                 (state__64835))))
              (state__64835
               []
               (if
                (clojure.core/map? arg__64351)
                (clojure.core/let
                 [VAL__64353 (.valAt arg__64351 :aliases)]
                 (if
                  (clojure.core/map? VAL__64353)
                  (clojure.core/let
                   [X__64355 (clojure.core/set VAL__64353)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__64355))
                    (clojure.core/loop
                     [search_space__64836 (clojure.core/seq X__64355)]
                     (if
                      (clojure.core/seq search_space__64836)
                      (clojure.core/let
                       [elem__64356
                        (clojure.core/first search_space__64836)
                        result__64837
                        (clojure.core/let
                         [elem__64356_nth_0__
                          (clojure.core/nth elem__64356 0)
                          elem__64356_nth_1__
                          (clojure.core/nth elem__64356 1)]
                         (if
                          (clojure.core/symbol? elem__64356_nth_0__)
                          (clojure.core/let
                           [X__64358
                            (clojure.core/name elem__64356_nth_0__)]
                           (if
                            (clojure.core/= ?__62987 X__64358)
                            (if
                             (clojure.core/symbol? elem__64356_nth_1__)
                             (clojure.core/let
                              [X__64360
                               (clojure.core/name elem__64356_nth_1__)]
                              (clojure.core/case
                               X__64360
                               ("meander.zeta")
                               [?__62987]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__64837)
                        (recur (clojure.core/next search_space__64836))
                        result__64837))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__64834)))]
          (if
           (clojure.core/vector? input__62968)
           (if
            (clojure.core/= (clojure.core/count input__62968) 2)
            (clojure.core/let
             [input__62968_nth_0__
              (clojure.core/nth input__62968 0)
              input__62968_nth_1__
              (clojure.core/nth input__62968 1)]
             (if
              (clojure.core/seq? input__62968_nth_0__)
              (clojure.core/let
               [input__62968_nth_0___l__
                (clojure.core/take 1 input__62968_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__62968_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__62968_nth_0___r__
                  (clojure.core/drop 1 input__62968_nth_0__)]
                 (clojure.core/let
                  [input__62968_nth_0___l___nth_0__
                   (clojure.core/nth input__62968_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__62968_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__64338
                     (clojure.core/namespace
                      input__62968_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__62987 X__64338]
                     (clojure.core/let
                      [X__64340
                       (clojure.core/name
                        input__62968_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__64340
                       ("re")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__64328 input__62968_nth_1__ ?__62987)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__64591)
                         (clojure.core/let
                          [[?__62987] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__62968)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__62968)
                             2)
                            (clojure.core/let
                             [input__62968_nth_0__
                              (clojure.core/nth input__62968 0)
                              input__62968_nth_1__
                              (clojure.core/nth input__62968 1)]
                             (if
                              (clojure.core/seq? input__62968_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__62968_nth_0__)
                                3)
                               (clojure.core/let
                                [input__62968_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__62968_nth_0__
                                  1)
                                 input__62968_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__62968_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?regex input__62968_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?capture
                                   input__62968_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__62968_nth_0__]
                                   (clojure.core/let
                                    [?env input__62968_nth_1__]
                                    (try
                                     [{:tag :regex,
                                       :regex ?regex,
                                       :capture
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__63051
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
                               (state__64591))
                              (state__64591)))
                            (state__64591))
                           (state__64591)))))
                       (state__64591)))))
                   (state__64591))))
                (state__64591)))
              (state__64591)))
            (state__64591))
           (state__64591))))
        (state__64591
         []
         (clojure.core/letfn
          [(def__64362
            [arg__64385 ?__62988]
            (clojure.core/letfn
             [(state__64839
               []
               (clojure.core/let
                [x__64386 "meander.zeta"]
                (if
                 (clojure.core/= ?__62988 x__64386)
                 [?__62988]
                 (state__64840))))
              (state__64840
               []
               (if
                (clojure.core/map? arg__64385)
                (clojure.core/let
                 [VAL__64387 (.valAt arg__64385 :aliases)]
                 (if
                  (clojure.core/map? VAL__64387)
                  (clojure.core/let
                   [X__64389 (clojure.core/set VAL__64387)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__64389))
                    (clojure.core/loop
                     [search_space__64841 (clojure.core/seq X__64389)]
                     (if
                      (clojure.core/seq search_space__64841)
                      (clojure.core/let
                       [elem__64390
                        (clojure.core/first search_space__64841)
                        result__64842
                        (clojure.core/let
                         [elem__64390_nth_0__
                          (clojure.core/nth elem__64390 0)
                          elem__64390_nth_1__
                          (clojure.core/nth elem__64390 1)]
                         (if
                          (clojure.core/symbol? elem__64390_nth_0__)
                          (clojure.core/let
                           [X__64392
                            (clojure.core/name elem__64390_nth_0__)]
                           (if
                            (clojure.core/= ?__62988 X__64392)
                            (if
                             (clojure.core/symbol? elem__64390_nth_1__)
                             (clojure.core/let
                              [X__64394
                               (clojure.core/name elem__64390_nth_1__)]
                              (clojure.core/case
                               X__64394
                               ("meander.zeta")
                               [?__62988]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__64842)
                        (recur (clojure.core/next search_space__64841))
                        result__64842))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__64839)))]
          (if
           (clojure.core/vector? input__62968)
           (if
            (clojure.core/= (clojure.core/count input__62968) 2)
            (clojure.core/let
             [input__62968_nth_0__
              (clojure.core/nth input__62968 0)
              input__62968_nth_1__
              (clojure.core/nth input__62968 1)]
             (if
              (clojure.core/seq? input__62968_nth_0__)
              (clojure.core/let
               [input__62968_nth_0___l__
                (clojure.core/take 1 input__62968_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__62968_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__62968_nth_0___r__
                  (clojure.core/drop 1 input__62968_nth_0__)]
                 (clojure.core/let
                  [input__62968_nth_0___l___nth_0__
                   (clojure.core/nth input__62968_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__62968_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__64372
                     (clojure.core/namespace
                      input__62968_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__62988 X__64372]
                     (clojure.core/let
                      [X__64374
                       (clojure.core/name
                        input__62968_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__64374
                       ("string")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__64362 input__62968_nth_1__ ?__62988)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__64592)
                         (clojure.core/let
                          [[?__62988] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__62968)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__62968)
                             2)
                            (clojure.core/let
                             [input__62968_nth_0__
                              (clojure.core/nth input__62968 0)
                              input__62968_nth_1__
                              (clojure.core/nth input__62968 1)]
                             (if
                              (clojure.core/seq? input__62968_nth_0__)
                              (clojure.core/let
                               [input__62968_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__62968_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__62968_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__62968_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__62968_nth_0__)]
                                 (clojure.core/let
                                  [?sequence input__62968_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__62968_nth_0__]
                                   (clojure.core/let
                                    [?env input__62968_nth_1__]
                                    (try
                                     [{:tag :string,
                                       :next
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__63051
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
                                (state__64592)))
                              (state__64592)))
                            (state__64592))
                           (state__64592)))))
                       (state__64592)))))
                   (state__64592))))
                (state__64592)))
              (state__64592)))
            (state__64592))
           (state__64592))))
        (state__64592
         []
         (clojure.core/letfn
          [(def__64396
            [arg__64419 ?__62989]
            (clojure.core/letfn
             [(state__64844
               []
               (clojure.core/let
                [x__64420 "meander.zeta"]
                (if
                 (clojure.core/= ?__62989 x__64420)
                 [?__62989]
                 (state__64845))))
              (state__64845
               []
               (if
                (clojure.core/map? arg__64419)
                (clojure.core/let
                 [VAL__64421 (.valAt arg__64419 :aliases)]
                 (if
                  (clojure.core/map? VAL__64421)
                  (clojure.core/let
                   [X__64423 (clojure.core/set VAL__64421)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__64423))
                    (clojure.core/loop
                     [search_space__64846 (clojure.core/seq X__64423)]
                     (if
                      (clojure.core/seq search_space__64846)
                      (clojure.core/let
                       [elem__64424
                        (clojure.core/first search_space__64846)
                        result__64847
                        (clojure.core/let
                         [elem__64424_nth_0__
                          (clojure.core/nth elem__64424 0)
                          elem__64424_nth_1__
                          (clojure.core/nth elem__64424 1)]
                         (if
                          (clojure.core/symbol? elem__64424_nth_0__)
                          (clojure.core/let
                           [X__64426
                            (clojure.core/name elem__64424_nth_0__)]
                           (if
                            (clojure.core/= ?__62989 X__64426)
                            (if
                             (clojure.core/symbol? elem__64424_nth_1__)
                             (clojure.core/let
                              [X__64428
                               (clojure.core/name elem__64424_nth_1__)]
                              (clojure.core/case
                               X__64428
                               ("meander.zeta")
                               [?__62989]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__64847)
                        (recur (clojure.core/next search_space__64846))
                        result__64847))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__64844)))]
          (if
           (clojure.core/vector? input__62968)
           (if
            (clojure.core/= (clojure.core/count input__62968) 2)
            (clojure.core/let
             [input__62968_nth_0__
              (clojure.core/nth input__62968 0)
              input__62968_nth_1__
              (clojure.core/nth input__62968 1)]
             (if
              (clojure.core/seq? input__62968_nth_0__)
              (clojure.core/let
               [input__62968_nth_0___l__
                (clojure.core/take 1 input__62968_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__62968_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__62968_nth_0___r__
                  (clojure.core/drop 1 input__62968_nth_0__)]
                 (clojure.core/let
                  [input__62968_nth_0___l___nth_0__
                   (clojure.core/nth input__62968_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__62968_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__64406
                     (clojure.core/namespace
                      input__62968_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__62989 X__64406]
                     (clojure.core/let
                      [X__64408
                       (clojure.core/name
                        input__62968_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__64408
                       ("symbol")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__64396 input__62968_nth_1__ ?__62989)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__64593)
                         (clojure.core/let
                          [[?__62989] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__62968)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__62968)
                             2)
                            (clojure.core/let
                             [input__62968_nth_0__
                              (clojure.core/nth input__62968 0)
                              input__62968_nth_1__
                              (clojure.core/nth input__62968 1)]
                             (if
                              (clojure.core/seq? input__62968_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__62968_nth_0__)
                                2)
                               (clojure.core/let
                                [input__62968_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__62968_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?name input__62968_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__62968_nth_0__]
                                  (clojure.core/let
                                   [?env input__62968_nth_1__]
                                   (try
                                    [{:tag :symbol,
                                      :name
                                      (clojure.core/let
                                       [CATA_RESULT__15641__auto__
                                        (CATA__FN__63051 [?name ?env])]
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
                               (state__64593))
                              (state__64593)))
                            (state__64593))
                           (state__64593)))))
                       (state__64593)))))
                   (state__64593))))
                (state__64593)))
              (state__64593)))
            (state__64593))
           (state__64593))))
        (state__64593
         []
         (clojure.core/letfn
          [(def__64430
            [arg__64453 ?__62990]
            (clojure.core/letfn
             [(state__64849
               []
               (clojure.core/let
                [x__64454 "meander.zeta"]
                (if
                 (clojure.core/= ?__62990 x__64454)
                 [?__62990]
                 (state__64850))))
              (state__64850
               []
               (if
                (clojure.core/map? arg__64453)
                (clojure.core/let
                 [VAL__64455 (.valAt arg__64453 :aliases)]
                 (if
                  (clojure.core/map? VAL__64455)
                  (clojure.core/let
                   [X__64457 (clojure.core/set VAL__64455)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__64457))
                    (clojure.core/loop
                     [search_space__64851 (clojure.core/seq X__64457)]
                     (if
                      (clojure.core/seq search_space__64851)
                      (clojure.core/let
                       [elem__64458
                        (clojure.core/first search_space__64851)
                        result__64852
                        (clojure.core/let
                         [elem__64458_nth_0__
                          (clojure.core/nth elem__64458 0)
                          elem__64458_nth_1__
                          (clojure.core/nth elem__64458 1)]
                         (if
                          (clojure.core/symbol? elem__64458_nth_0__)
                          (clojure.core/let
                           [X__64460
                            (clojure.core/name elem__64458_nth_0__)]
                           (if
                            (clojure.core/= ?__62990 X__64460)
                            (if
                             (clojure.core/symbol? elem__64458_nth_1__)
                             (clojure.core/let
                              [X__64462
                               (clojure.core/name elem__64458_nth_1__)]
                              (clojure.core/case
                               X__64462
                               ("meander.zeta")
                               [?__62990]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__64852)
                        (recur (clojure.core/next search_space__64851))
                        result__64852))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__64849)))]
          (if
           (clojure.core/vector? input__62968)
           (if
            (clojure.core/= (clojure.core/count input__62968) 2)
            (clojure.core/let
             [input__62968_nth_0__
              (clojure.core/nth input__62968 0)
              input__62968_nth_1__
              (clojure.core/nth input__62968 1)]
             (if
              (clojure.core/seq? input__62968_nth_0__)
              (clojure.core/let
               [input__62968_nth_0___l__
                (clojure.core/take 1 input__62968_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__62968_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__62968_nth_0___r__
                  (clojure.core/drop 1 input__62968_nth_0__)]
                 (clojure.core/let
                  [input__62968_nth_0___l___nth_0__
                   (clojure.core/nth input__62968_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__62968_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__64440
                     (clojure.core/namespace
                      input__62968_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__62990 X__64440]
                     (clojure.core/let
                      [X__64442
                       (clojure.core/name
                        input__62968_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__64442
                       ("symbol")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__64430 input__62968_nth_1__ ?__62990)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__64594)
                         (clojure.core/let
                          [[?__62990] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__62968)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__62968)
                             2)
                            (clojure.core/let
                             [input__62968_nth_0__
                              (clojure.core/nth input__62968 0)
                              input__62968_nth_1__
                              (clojure.core/nth input__62968 1)]
                             (if
                              (clojure.core/seq? input__62968_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__62968_nth_0__)
                                3)
                               (clojure.core/let
                                [input__62968_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__62968_nth_0__
                                  1)
                                 input__62968_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__62968_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?namespace
                                  input__62968_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?name input__62968_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__62968_nth_0__]
                                   (clojure.core/let
                                    [?env input__62968_nth_1__]
                                    (try
                                     [{:tag :symbol,
                                       :name
                                       (clojure.core/let
                                        [CATA_RESULT__15641__auto__
                                         (CATA__FN__63051
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
                                         (CATA__FN__63051
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
                               (state__64594))
                              (state__64594)))
                            (state__64594))
                           (state__64594)))))
                       (state__64594)))))
                   (state__64594))))
                (state__64594)))
              (state__64594)))
            (state__64594))
           (state__64594))))
        (state__64594
         []
         (clojure.core/letfn
          [(def__64464
            [arg__64487 ?__62991]
            (clojure.core/letfn
             [(state__64854
               []
               (clojure.core/let
                [x__64488 "meander.zeta"]
                (if
                 (clojure.core/= ?__62991 x__64488)
                 [?__62991]
                 (state__64855))))
              (state__64855
               []
               (if
                (clojure.core/map? arg__64487)
                (clojure.core/let
                 [VAL__64489 (.valAt arg__64487 :aliases)]
                 (if
                  (clojure.core/map? VAL__64489)
                  (clojure.core/let
                   [X__64491 (clojure.core/set VAL__64489)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__64491))
                    (clojure.core/loop
                     [search_space__64856 (clojure.core/seq X__64491)]
                     (if
                      (clojure.core/seq search_space__64856)
                      (clojure.core/let
                       [elem__64492
                        (clojure.core/first search_space__64856)
                        result__64857
                        (clojure.core/let
                         [elem__64492_nth_0__
                          (clojure.core/nth elem__64492 0)
                          elem__64492_nth_1__
                          (clojure.core/nth elem__64492 1)]
                         (if
                          (clojure.core/symbol? elem__64492_nth_0__)
                          (clojure.core/let
                           [X__64494
                            (clojure.core/name elem__64492_nth_0__)]
                           (if
                            (clojure.core/= ?__62991 X__64494)
                            (if
                             (clojure.core/symbol? elem__64492_nth_1__)
                             (clojure.core/let
                              [X__64496
                               (clojure.core/name elem__64492_nth_1__)]
                              (clojure.core/case
                               X__64496
                               ("meander.zeta")
                               [?__62991]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__64857)
                        (recur (clojure.core/next search_space__64856))
                        result__64857))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__64854)))]
          (if
           (clojure.core/vector? input__62968)
           (if
            (clojure.core/= (clojure.core/count input__62968) 2)
            (clojure.core/let
             [input__62968_nth_0__
              (clojure.core/nth input__62968 0)
              input__62968_nth_1__
              (clojure.core/nth input__62968 1)]
             (if
              (clojure.core/seq? input__62968_nth_0__)
              (clojure.core/let
               [input__62968_nth_0___l__
                (clojure.core/take 1 input__62968_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__62968_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__62968_nth_0___r__
                  (clojure.core/drop 1 input__62968_nth_0__)]
                 (clojure.core/let
                  [input__62968_nth_0___l___nth_0__
                   (clojure.core/nth input__62968_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__62968_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__64474
                     (clojure.core/namespace
                      input__62968_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__62991 X__64474]
                     (clojure.core/let
                      [X__64476
                       (clojure.core/name
                        input__62968_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__64476
                       ("symbol")
                       (clojure.core/let
                        [x__14338__auto__
                         (def__64464 input__62968_nth_1__ ?__62991)]
                        (if
                         (meander.runtime.zeta/fail? x__14338__auto__)
                         (state__64595)
                         (clojure.core/let
                          [[?__62991] x__14338__auto__]
                          (if
                           (clojure.core/vector? input__62968)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__62968)
                             2)
                            (clojure.core/let
                             [input__62968_nth_0__
                              (clojure.core/nth input__62968 0)
                              input__62968_nth_1__
                              (clojure.core/nth input__62968 1)]
                             (if
                              (clojure.core/seq? input__62968_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 5)
                                 input__62968_nth_0__)
                                5)
                               (clojure.core/let
                                [input__62968_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__62968_nth_0__
                                  1)
                                 input__62968_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__62968_nth_0__
                                  2)
                                 input__62968_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__62968_nth_0__
                                  3)
                                 input__62968_nth_0___nth_4__
                                 (clojure.core/nth
                                  input__62968_nth_0__
                                  4)]
                                (clojure.core/case
                                 input__62968_nth_0___nth_3__
                                 (:meander.zeta/as)
                                 (clojure.core/let
                                  [?namespace
                                   input__62968_nth_0___nth_1__]
                                  (clojure.core/let
                                   [?name input__62968_nth_0___nth_2__]
                                   (clojure.core/let
                                    [?pattern
                                     input__62968_nth_0___nth_4__]
                                    (clojure.core/let
                                     [?form input__62968_nth_0__]
                                     (clojure.core/let
                                      [?env input__62968_nth_1__]
                                      (try
                                       [{:tag :symbol,
                                         :name
                                         (clojure.core/let
                                          [CATA_RESULT__15641__auto__
                                           (CATA__FN__63051
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
                                           (CATA__FN__63051
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
                                           (CATA__FN__63051
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
                                 (state__64595)))
                               (state__64595))
                              (state__64595)))
                            (state__64595))
                           (state__64595)))))
                       (state__64595)))))
                   (state__64595))))
                (state__64595)))
              (state__64595)))
            (state__64595))
           (state__64595))))
        (state__64595
         []
         (if
          (clojure.core/vector? input__62968)
          (if
           (clojure.core/= (clojure.core/count input__62968) 2)
           (clojure.core/let
            [input__62968_nth_0__ (clojure.core/nth input__62968 0)]
            (clojure.core/letfn
             [(state__64859
               []
               (clojure.core/let
                [input__62968_nth_1__
                 (clojure.core/nth input__62968 1)]
                (clojure.core/letfn
                 [(state__64864
                   []
                   (if
                    (clojure.core/seq? input__62968_nth_0__)
                    (clojure.core/let
                     [?sequence input__62968_nth_0__]
                     (clojure.core/let
                      [?env input__62968_nth_1__]
                      (try
                       [{:tag :seq,
                         :next
                         (clojure.core/let
                          [CATA_RESULT__15641__auto__
                           (CATA__FN__63051
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
                    (state__64865)))
                  (state__64865
                   []
                   (if
                    (clojure.core/map? input__62968_nth_0__)
                    (clojure.core/let
                     [?map input__62968_nth_0__]
                     (clojure.core/let
                      [?env input__62968_nth_1__]
                      (try
                       [{:tag :map,
                         :next
                         (clojure.core/let
                          [CATA_RESULT__15641__auto__
                           (CATA__FN__63051
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
                    (state__64860)))]
                 (state__64864))))
              (state__64860
               []
               (if
                (clojure.core/symbol? input__62968_nth_0__)
                (clojure.core/let
                 [X__64506
                  (clojure.core/namespace input__62968_nth_0__)]
                 (clojure.core/let
                  [X__64508 (clojure.core/name input__62968_nth_0__)]
                  (if
                   (clojure.core/string? X__64508)
                   (clojure.core/letfn
                    [(state__64866
                      []
                      (clojure.core/let
                       [ret__64509
                        (clojure.core/re-matches #"_.*" X__64508)]
                       (if
                        (clojure.core/some? ret__64509)
                        (clojure.core/let
                         [?name ret__64509]
                         (clojure.core/let
                          [?symbol input__62968_nth_0__]
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
                        (state__64867))))
                     (state__64867
                      []
                      (clojure.core/let
                       [ret__64516
                        (clojure.core/re-matches #".+#" X__64508)]
                       (if
                        (clojure.core/some? ret__64516)
                        (clojure.core/let
                         [?name ret__64516]
                         (clojure.core/let
                          [?symbol input__62968_nth_0__]
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
                        (state__64868))))
                     (state__64868
                      []
                      (clojure.core/let
                       [ret__64523
                        (clojure.core/re-matches #"%.+" X__64508)]
                       (if
                        (clojure.core/some? ret__64523)
                        (clojure.core/let
                         [?name ret__64523]
                         (clojure.core/let
                          [?symbol input__62968_nth_0__]
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
                        (state__64869))))
                     (state__64869
                      []
                      (clojure.core/let
                       [ret__64530
                        (clojure.core/re-matches #"\*.+" X__64508)]
                       (if
                        (clojure.core/some? ret__64530)
                        (clojure.core/let
                         [?name ret__64530]
                         (clojure.core/let
                          [?symbol input__62968_nth_0__]
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
                        (state__64870))))
                     (state__64870
                      []
                      (clojure.core/let
                       [ret__64537
                        (clojure.core/re-matches #"\!.+" X__64508)]
                       (if
                        (clojure.core/some? ret__64537)
                        (clojure.core/let
                         [?name ret__64537]
                         (clojure.core/let
                          [?symbol input__62968_nth_0__]
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
                        (state__64871))))
                     (state__64871
                      []
                      (clojure.core/let
                       [ret__64544
                        (clojure.core/re-matches #"\?.+" X__64508)]
                       (if
                        (clojure.core/some? ret__64544)
                        (clojure.core/let
                         [?name ret__64544]
                         (clojure.core/let
                          [?symbol input__62968_nth_0__]
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
                        (state__64861))))]
                    (state__64866))
                   (state__64861))))
                (state__64861)))
              (state__64861
               []
               (if
                (string? input__62968_nth_0__)
                (clojure.core/let
                 [?x input__62968_nth_0__]
                 (try
                  [{:tag :literal, :type :string, :form ?x}]
                  (catch
                   java.lang.Exception
                   e__16581__auto__
                   (if
                    (meander.runtime.zeta/fail? e__16581__auto__)
                    (meander.runtime.zeta/fail)
                    (throw e__16581__auto__)))))
                (state__64862)))
              (state__64862
               []
               (if
                (char? input__62968_nth_0__)
                (clojure.core/let
                 [?x input__62968_nth_0__]
                 (try
                  [{:tag :literal, :type :char, :form ?x}]
                  (catch
                   java.lang.Exception
                   e__16581__auto__
                   (if
                    (meander.runtime.zeta/fail? e__16581__auto__)
                    (meander.runtime.zeta/fail)
                    (throw e__16581__auto__)))))
                (state__64863)))
              (state__64863
               []
               (clojure.core/let
                [?x input__62968_nth_0__]
                (try
                 [{:tag :literal, :form ?x}]
                 (catch
                  java.lang.Exception
                  e__16581__auto__
                  (if
                   (meander.runtime.zeta/fail? e__16581__auto__)
                   (meander.runtime.zeta/fail)
                   (throw e__16581__auto__))))))]
             (state__64859)))
           (state__64596))
          (state__64596)))
        (state__64596
         []
         (clojure.core/let
          [?x input__62968]
          (try
           [{:tag :mistake, :x ?x}]
           (catch
            java.lang.Exception
            e__16581__auto__
            (if
             (meander.runtime.zeta/fail? e__16581__auto__)
             (meander.runtime.zeta/fail)
             (throw e__16581__auto__))))))]
       (state__64557)))]
    (clojure.core/let
     [x__14338__auto__ (CATA__FN__63051 input__62968)]
     (if
      (meander.runtime.zeta/fail? x__14338__auto__)
      (meander.runtime.zeta/fail)
      (clojure.core/let
       [[CATA_RETURN__63055] x__14338__auto__]
       CATA_RETURN__63055))))]
  (if
   (meander.runtime.zeta/fail? ret__14518__auto__)
   nil
   ret__14518__auto__)))
