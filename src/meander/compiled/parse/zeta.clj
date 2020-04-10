(ns
 meander.compiled.parse.zeta
 (:require [meander.runtime.zeta] [meander.util.zeta]))
(clojure.core/defn
 parse
 [input__243425]
 (let*
  [ret__9766__auto__
   (clojure.core/letfn
    [(CATA__FN__243502
      [input__243425]
      (clojure.core/letfn
       [(state__244921
         []
         (if
          (clojure.core/vector? input__243425)
          (if
           (clojure.core/= (clojure.core/count input__243425) 3)
           (clojure.core/let
            [input__243425_nth_0__
             (clojure.core/nth input__243425 0)
             input__243425_nth_1__
             (clojure.core/nth input__243425 1)
             input__243425_nth_2__
             (clojure.core/nth input__243425 2)]
            (clojure.core/case
             input__243425_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__243425_nth_1__)
              (clojure.core/letfn
               [(state__244960
                 []
                 (clojure.core/case
                  input__243425_nth_1__
                  ([])
                  (clojure.core/let
                   [?env input__243425_nth_2__]
                   (try
                    [{:tag :empty}]
                    (catch
                     java.lang.Exception
                     e__11829__auto__
                     (if
                      (meander.runtime.zeta/fail? e__11829__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__11829__auto__)))))
                  (state__244961)))
                (state__244961
                 []
                 (clojure.core/let
                  [n__243511
                   (clojure.core/count input__243425_nth_1__)
                   m__243512
                   (clojure.core/max 0 (clojure.core/- n__243511 2))
                   input__243425_nth_1___l__
                   (clojure.core/subvec
                    input__243425_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__243425_nth_1__)
                     m__243512))
                   input__243425_nth_1___r__
                   (clojure.core/subvec
                    input__243425_nth_1__
                    m__243512)]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__243425_nth_1___r__)
                    2)
                   (clojure.core/let
                    [!xs (clojure.core/vec input__243425_nth_1___l__)]
                    (if
                     (clojure.core/=
                      (clojure.core/count input__243425_nth_1___r__)
                      2)
                     (clojure.core/let
                      [input__243425_nth_1___r___nth_0__
                       (clojure.core/nth input__243425_nth_1___r__ 0)
                       input__243425_nth_1___r___nth_1__
                       (clojure.core/nth input__243425_nth_1___r__ 1)]
                      (clojure.core/case
                       input__243425_nth_1___r___nth_0__
                       (:meander.zeta/as)
                       (clojure.core/let
                        [?pattern input__243425_nth_1___r___nth_1__]
                        (clojure.core/let
                         [?env input__243425_nth_2__]
                         (try
                          [(clojure.core/let
                            [!xs__counter
                             (meander.runtime.zeta/iterator !xs)]
                            {:tag :as,
                             :pattern
                             (clojure.core/let
                              [CATA_RESULT__10889__auto__
                               (CATA__FN__243502 [?pattern ?env])]
                              (if
                               (meander.runtime.zeta/fail?
                                CATA_RESULT__10889__auto__)
                               (throw (meander.runtime.zeta/fail))
                               (clojure.core/nth
                                CATA_RESULT__10889__auto__
                                0))),
                             :next
                             (clojure.core/let
                              [CATA_RESULT__10889__auto__
                               (CATA__FN__243502
                                ['meander.dev.parse.zeta/parse-sequential
                                 (clojure.core/into
                                  []
                                  (clojure.core/vec
                                   (clojure.core/iterator-seq
                                    !xs__counter)))
                                 ?env])]
                              (if
                               (meander.runtime.zeta/fail?
                                CATA_RESULT__10889__auto__)
                               (throw (meander.runtime.zeta/fail))
                               (clojure.core/nth
                                CATA_RESULT__10889__auto__
                                0)))})]
                          (catch
                           java.lang.Exception
                           e__11829__auto__
                           (if
                            (meander.runtime.zeta/fail?
                             e__11829__auto__)
                            (meander.runtime.zeta/fail)
                            (throw e__11829__auto__))))))
                       (state__244922)))
                     (state__244922)))
                   (state__244922))))]
               (state__244960))
              (state__244922))
             (state__244922)))
           (state__244922))
          (state__244922)))
        (state__244922
         []
         (clojure.core/letfn
          [(def__243517
            [arg__243552 ?ns]
            (clojure.core/letfn
             [(state__244962
               []
               (clojure.core/let
                [x__243553 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__243553)
                 (clojure.core/let [?env arg__243552] [?env ?ns])
                 (state__244963))))
              (state__244963
               []
               (if
                (clojure.core/map? arg__243552)
                (clojure.core/let
                 [VAL__243554 (.valAt arg__243552 :aliases)]
                 (if
                  (clojure.core/map? VAL__243554)
                  (clojure.core/let
                   [X__243556 (clojure.core/set VAL__243554)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__243556))
                    (clojure.core/loop
                     [search_space__244964
                      (clojure.core/seq X__243556)]
                     (if
                      (clojure.core/seq search_space__244964)
                      (clojure.core/let
                       [elem__243557
                        (clojure.core/first search_space__244964)
                        result__244965
                        (clojure.core/let
                         [elem__243557_nth_0__
                          (clojure.core/nth elem__243557 0)
                          elem__243557_nth_1__
                          (clojure.core/nth elem__243557 1)]
                         (if
                          (clojure.core/symbol? elem__243557_nth_0__)
                          (clojure.core/let
                           [X__243559
                            (clojure.core/name elem__243557_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__243559)
                            (if
                             (clojure.core/symbol?
                              elem__243557_nth_1__)
                             (clojure.core/let
                              [X__243561
                               (clojure.core/name
                                elem__243557_nth_1__)]
                              (clojure.core/case
                               X__243561
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__243552]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__244965)
                        (recur
                         (clojure.core/next search_space__244964))
                        result__244965))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__244962)))]
          (if
           (clojure.core/vector? input__243425)
           (if
            (clojure.core/= (clojure.core/count input__243425) 3)
            (clojure.core/let
             [input__243425_nth_0__
              (clojure.core/nth input__243425 0)
              input__243425_nth_1__
              (clojure.core/nth input__243425 1)
              input__243425_nth_2__
              (clojure.core/nth input__243425 2)]
             (clojure.core/case
              input__243425_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__243425_nth_1__)
               (clojure.core/loop
                [search_space__244967
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__243425_nth_1__)]
                (if
                 (clojure.core/seq search_space__244967)
                 (clojure.core/let
                  [input__243425_nth_1___parts__
                   (clojure.core/first search_space__244967)
                   result__244968
                   (clojure.core/let
                    [input__243425_nth_1___l__
                     (clojure.core/nth input__243425_nth_1___parts__ 0)
                     input__243425_nth_1___r__
                     (clojure.core/nth
                      input__243425_nth_1___parts__
                      1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__243425_nth_1___l__)]
                     (clojure.core/let
                      [input__243425_nth_1___r___l__
                       (clojure.core/subvec
                        input__243425_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__243425_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__243425_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__243425_nth_1___r___r__
                         (clojure.core/subvec
                          input__243425_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__243425_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__243425_nth_1___r___l__
                           0)
                          input__243425_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__243425_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__243425_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__243526
                            (clojure.core/namespace
                             input__243425_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__243526]
                            (clojure.core/let
                             [X__243528
                              (clojure.core/name
                               input__243425_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__243528)
                              (clojure.core/let
                               [ret__243529
                                (clojure.core/re-matches
                                 #"&(\d+)"
                                 X__243528)]
                               (if
                                (clojure.core/some? ret__243529)
                                (if
                                 (clojure.core/vector? ret__243529)
                                 (if
                                  (clojure.core/=
                                   (clojure.core/count ret__243529)
                                   2)
                                  (clojure.core/let
                                   [ret__243529_nth_1__
                                    (clojure.core/nth ret__243529 1)]
                                   (clojure.core/let
                                    [?n ret__243529_nth_1__]
                                    (clojure.core/let
                                     [?pattern
                                      input__243425_nth_1___r___l___nth_1__]
                                     (clojure.core/let
                                      [?rest
                                       input__243425_nth_1___r___r__]
                                      (clojure.core/let
                                       [x__9586__auto__
                                        (def__243517
                                         input__243425_nth_2__
                                         ?ns)]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         x__9586__auto__)
                                        (meander.runtime.zeta/fail)
                                        (clojure.core/let
                                         [[?env ?ns] x__9586__auto__]
                                         (try
                                          [(clojure.core/let
                                            [!init__counter
                                             (meander.runtime.zeta/iterator
                                              !init)]
                                            (clojure.core/let
                                             [CATA_RESULT__10889__auto__
                                              (CATA__FN__243502
                                               ['meander.dev.parse.zeta/make-join
                                                (clojure.core/let
                                                 [CATA_RESULT__10889__auto__
                                                  (CATA__FN__243502
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    (clojure.core/into
                                                     []
                                                     (clojure.core/vec
                                                      (clojure.core/iterator-seq
                                                       !init__counter)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__10889__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__10889__auto__
                                                   0)))
                                                (clojure.core/let
                                                 [CATA_RESULT__10889__auto__
                                                  (CATA__FN__243502
                                                   ['meander.dev.parse.zeta/make-join
                                                    {:tag :slice,
                                                     :size
                                                     (Integer. ?n),
                                                     :pattern
                                                     (clojure.core/let
                                                      [CATA_RESULT__10889__auto__
                                                       (CATA__FN__243502
                                                        [?pattern
                                                         ?env])]
                                                      (if
                                                       (meander.runtime.zeta/fail?
                                                        CATA_RESULT__10889__auto__)
                                                       (throw
                                                        (meander.runtime.zeta/fail))
                                                       (clojure.core/nth
                                                        CATA_RESULT__10889__auto__
                                                        0)))}
                                                    (clojure.core/let
                                                     [CATA_RESULT__10889__auto__
                                                      (CATA__FN__243502
                                                       ['meander.dev.parse.zeta/parse-sequential
                                                        (clojure.core/into
                                                         []
                                                         ?rest)
                                                        ?env])]
                                                     (if
                                                      (meander.runtime.zeta/fail?
                                                       CATA_RESULT__10889__auto__)
                                                      (throw
                                                       (meander.runtime.zeta/fail))
                                                      (clojure.core/nth
                                                       CATA_RESULT__10889__auto__
                                                       0)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__10889__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__10889__auto__
                                                   0)))
                                                ?env])]
                                             (if
                                              (meander.runtime.zeta/fail?
                                               CATA_RESULT__10889__auto__)
                                              (throw
                                               (meander.runtime.zeta/fail))
                                              (clojure.core/nth
                                               CATA_RESULT__10889__auto__
                                               0))))]
                                          (catch
                                           java.lang.Exception
                                           e__11829__auto__
                                           (if
                                            (meander.runtime.zeta/fail?
                                             e__11829__auto__)
                                            (meander.runtime.zeta/fail)
                                            (throw
                                             e__11829__auto__)))))))))))
                                  (meander.runtime.zeta/fail))
                                 (meander.runtime.zeta/fail))
                                (meander.runtime.zeta/fail)))
                              (meander.runtime.zeta/fail)))))
                          (meander.runtime.zeta/fail))))
                       (meander.runtime.zeta/fail)))))]
                  (if
                   (meander.runtime.zeta/fail? result__244968)
                   (recur (clojure.core/next search_space__244967))
                   result__244968))
                 (state__244923)))
               (state__244923))
              (state__244923)))
            (state__244923))
           (state__244923))))
        (state__244923
         []
         (clojure.core/letfn
          [(def__243574
            [arg__243606 ?ns]
            (clojure.core/letfn
             [(state__244970
               []
               (clojure.core/let
                [x__243607 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__243607)
                 (clojure.core/let [?env arg__243606] [?env ?ns])
                 (state__244971))))
              (state__244971
               []
               (if
                (clojure.core/map? arg__243606)
                (clojure.core/let
                 [VAL__243608 (.valAt arg__243606 :aliases)]
                 (if
                  (clojure.core/map? VAL__243608)
                  (clojure.core/let
                   [X__243610 (clojure.core/set VAL__243608)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__243610))
                    (clojure.core/loop
                     [search_space__244972
                      (clojure.core/seq X__243610)]
                     (if
                      (clojure.core/seq search_space__244972)
                      (clojure.core/let
                       [elem__243611
                        (clojure.core/first search_space__244972)
                        result__244973
                        (clojure.core/let
                         [elem__243611_nth_0__
                          (clojure.core/nth elem__243611 0)
                          elem__243611_nth_1__
                          (clojure.core/nth elem__243611 1)]
                         (if
                          (clojure.core/symbol? elem__243611_nth_0__)
                          (clojure.core/let
                           [X__243613
                            (clojure.core/name elem__243611_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__243613)
                            (if
                             (clojure.core/symbol?
                              elem__243611_nth_1__)
                             (clojure.core/let
                              [X__243615
                               (clojure.core/name
                                elem__243611_nth_1__)]
                              (clojure.core/case
                               X__243615
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__243606]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__244973)
                        (recur
                         (clojure.core/next search_space__244972))
                        result__244973))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__244970)))]
          (if
           (clojure.core/vector? input__243425)
           (if
            (clojure.core/= (clojure.core/count input__243425) 3)
            (clojure.core/let
             [input__243425_nth_0__
              (clojure.core/nth input__243425 0)
              input__243425_nth_1__
              (clojure.core/nth input__243425 1)
              input__243425_nth_2__
              (clojure.core/nth input__243425 2)]
             (clojure.core/case
              input__243425_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__243425_nth_1__)
               (clojure.core/loop
                [search_space__244975
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__243425_nth_1__)]
                (if
                 (clojure.core/seq search_space__244975)
                 (clojure.core/let
                  [input__243425_nth_1___parts__
                   (clojure.core/first search_space__244975)
                   result__244976
                   (clojure.core/let
                    [input__243425_nth_1___l__
                     (clojure.core/nth input__243425_nth_1___parts__ 0)
                     input__243425_nth_1___r__
                     (clojure.core/nth
                      input__243425_nth_1___parts__
                      1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__243425_nth_1___l__)]
                     (clojure.core/let
                      [input__243425_nth_1___r___l__
                       (clojure.core/subvec
                        input__243425_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__243425_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__243425_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__243425_nth_1___r___r__
                         (clojure.core/subvec
                          input__243425_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__243425_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__243425_nth_1___r___l__
                           0)
                          input__243425_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__243425_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__243425_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__243583
                            (clojure.core/namespace
                             input__243425_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__243583]
                            (clojure.core/let
                             [X__243585
                              (clojure.core/name
                               input__243425_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__243585)
                              (if
                               (clojure.core/re-matches
                                #"&.*"
                                X__243585)
                               (clojure.core/let
                                [?pattern
                                 input__243425_nth_1___r___l___nth_1__]
                                (clojure.core/let
                                 [?rest input__243425_nth_1___r___r__]
                                 (clojure.core/let
                                  [x__9586__auto__
                                   (def__243574
                                    input__243425_nth_2__
                                    ?ns)]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    x__9586__auto__)
                                   (meander.runtime.zeta/fail)
                                   (clojure.core/let
                                    [[?env ?ns] x__9586__auto__]
                                    (try
                                     [(clojure.core/let
                                       [!init__counter
                                        (meander.runtime.zeta/iterator
                                         !init)]
                                       (clojure.core/let
                                        [CATA_RESULT__10889__auto__
                                         (CATA__FN__243502
                                          ['meander.dev.parse.zeta/make-join
                                           (clojure.core/let
                                            [CATA_RESULT__10889__auto__
                                             (CATA__FN__243502
                                              ['meander.dev.parse.zeta/parse-sequential
                                               (clojure.core/into
                                                []
                                                (clojure.core/vec
                                                 (clojure.core/iterator-seq
                                                  !init__counter)))
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__10889__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__10889__auto__
                                              0)))
                                           (clojure.core/let
                                            [CATA_RESULT__10889__auto__
                                             (CATA__FN__243502
                                              ['meander.dev.parse.zeta/make-join
                                               (clojure.core/let
                                                [CATA_RESULT__10889__auto__
                                                 (CATA__FN__243502
                                                  [?pattern ?env])]
                                                (if
                                                 (meander.runtime.zeta/fail?
                                                  CATA_RESULT__10889__auto__)
                                                 (throw
                                                  (meander.runtime.zeta/fail))
                                                 (clojure.core/nth
                                                  CATA_RESULT__10889__auto__
                                                  0)))
                                               (clojure.core/let
                                                [CATA_RESULT__10889__auto__
                                                 (CATA__FN__243502
                                                  ['meander.dev.parse.zeta/parse-sequential
                                                   (clojure.core/into
                                                    []
                                                    ?rest)
                                                   ?env])]
                                                (if
                                                 (meander.runtime.zeta/fail?
                                                  CATA_RESULT__10889__auto__)
                                                 (throw
                                                  (meander.runtime.zeta/fail))
                                                 (clojure.core/nth
                                                  CATA_RESULT__10889__auto__
                                                  0)))
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__10889__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__10889__auto__
                                              0)))
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10889__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10889__auto__
                                          0))))]
                                     (catch
                                      java.lang.Exception
                                      e__11829__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__11829__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__11829__auto__)))))))))
                               (meander.runtime.zeta/fail))
                              (meander.runtime.zeta/fail)))))
                          (meander.runtime.zeta/fail))))
                       (meander.runtime.zeta/fail)))))]
                  (if
                   (meander.runtime.zeta/fail? result__244976)
                   (recur (clojure.core/next search_space__244975))
                   result__244976))
                 (state__244924)))
               (state__244924))
              (state__244924)))
            (state__244924))
           (state__244924))))
        (state__244924
         []
         (if
          (clojure.core/vector? input__243425)
          (clojure.core/letfn
           [(state__244978
             []
             (if
              (clojure.core/= (clojure.core/count input__243425) 3)
              (clojure.core/let
               [input__243425_nth_0__
                (clojure.core/nth input__243425 0)
                input__243425_nth_1__
                (clojure.core/nth input__243425 1)
                input__243425_nth_2__
                (clojure.core/nth input__243425 2)]
               (clojure.core/case
                input__243425_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__243425_nth_1__)
                 (clojure.core/letfn
                  [(state__244981
                    []
                    (clojure.core/let
                     [n__243636
                      (clojure.core/count input__243425_nth_1__)
                      m__243637
                      (clojure.core/max 0 (clojure.core/- n__243636 2))
                      input__243425_nth_1___l__
                      (clojure.core/subvec
                       input__243425_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__243425_nth_1__)
                        m__243637))
                      input__243425_nth_1___r__
                      (clojure.core/subvec
                       input__243425_nth_1__
                       m__243637)]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__243425_nth_1___r__)
                       2)
                      (clojure.core/let
                       [!xs
                        (clojure.core/vec input__243425_nth_1___l__)]
                       (if
                        (clojure.core/=
                         (clojure.core/count input__243425_nth_1___r__)
                         2)
                        (clojure.core/let
                         [input__243425_nth_1___r___nth_0__
                          (clojure.core/nth
                           input__243425_nth_1___r__
                           0)
                          input__243425_nth_1___r___nth_1__
                          (clojure.core/nth
                           input__243425_nth_1___r__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__243425_nth_1___r___nth_0__)
                          (clojure.core/let
                           [X__243641
                            (clojure.core/namespace
                             input__243425_nth_1___r___nth_0__)]
                           (clojure.core/let
                            [?ns X__243641]
                            (clojure.core/let
                             [X__243643
                              (clojure.core/name
                               input__243425_nth_1___r___nth_0__)]
                             (if
                              (clojure.core/string? X__243643)
                              (clojure.core/let
                               [ret__243644
                                (clojure.core/re-matches
                                 #"&.*"
                                 X__243643)]
                               (if
                                (clojure.core/some? ret__243644)
                                (clojure.core/let
                                 [?name ret__243644]
                                 (clojure.core/let
                                  [?pattern
                                   input__243425_nth_1___r___nth_1__]
                                  (if
                                   (clojure.core/map?
                                    input__243425_nth_2__)
                                   (clojure.core/let
                                    [VAL__243628
                                     (.valAt
                                      input__243425_nth_2__
                                      :aliases)]
                                    (if
                                     (clojure.core/map? VAL__243628)
                                     (clojure.core/let
                                      [X__243630
                                       (clojure.core/set VAL__243628)]
                                      (if
                                       (clojure.core/<=
                                        1
                                        (clojure.core/count X__243630))
                                       (clojure.core/loop
                                        [search_space__244985
                                         (clojure.core/seq X__243630)]
                                        (if
                                         (clojure.core/seq
                                          search_space__244985)
                                         (clojure.core/let
                                          [elem__243631
                                           (clojure.core/first
                                            search_space__244985)
                                           result__244986
                                           (clojure.core/let
                                            [elem__243631_nth_0__
                                             (clojure.core/nth
                                              elem__243631
                                              0)
                                             elem__243631_nth_1__
                                             (clojure.core/nth
                                              elem__243631
                                              1)]
                                            (if
                                             (clojure.core/symbol?
                                              elem__243631_nth_0__)
                                             (clojure.core/let
                                              [X__243633
                                               (clojure.core/name
                                                elem__243631_nth_0__)]
                                              (if
                                               (clojure.core/=
                                                ?ns
                                                X__243633)
                                               (if
                                                (clojure.core/symbol?
                                                 elem__243631_nth_1__)
                                                (clojure.core/let
                                                 [X__243635
                                                  (clojure.core/name
                                                   elem__243631_nth_1__)]
                                                 (clojure.core/case
                                                  X__243635
                                                  ("meander.zeta")
                                                  (clojure.core/let
                                                   [?env
                                                    input__243425_nth_2__]
                                                   (try
                                                    [(clojure.core/let
                                                      [!xs__counter
                                                       (meander.runtime.zeta/iterator
                                                        !xs)]
                                                      (clojure.core/let
                                                       [CATA_RESULT__10889__auto__
                                                        (CATA__FN__243502
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
                                                         CATA_RESULT__10889__auto__)
                                                        (throw
                                                         (meander.runtime.zeta/fail))
                                                        (clojure.core/nth
                                                         CATA_RESULT__10889__auto__
                                                         0))))]
                                                    (catch
                                                     java.lang.Exception
                                                     e__11829__auto__
                                                     (if
                                                      (meander.runtime.zeta/fail?
                                                       e__11829__auto__)
                                                      (meander.runtime.zeta/fail)
                                                      (throw
                                                       e__11829__auto__)))))
                                                  (meander.runtime.zeta/fail)))
                                                (meander.runtime.zeta/fail))
                                               (meander.runtime.zeta/fail)))
                                             (meander.runtime.zeta/fail)))]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            result__244986)
                                           (recur
                                            (clojure.core/next
                                             search_space__244985))
                                           result__244986))
                                         (state__244982)))
                                       (state__244982)))
                                     (state__244982)))
                                   (state__244982))))
                                (state__244982)))
                              (state__244982)))))
                          (state__244982)))
                        (state__244982)))
                      (state__244982))))
                   (state__244982
                    []
                    (clojure.core/loop
                     [search_space__244988
                      (meander.runtime.zeta/epsilon-partitions
                       2
                       input__243425_nth_1__)]
                     (if
                      (clojure.core/seq search_space__244988)
                      (clojure.core/let
                       [input__243425_nth_1___parts__
                        (clojure.core/first search_space__244988)
                        result__244989
                        (clojure.core/let
                         [input__243425_nth_1___l__
                          (clojure.core/nth
                           input__243425_nth_1___parts__
                           0)
                          input__243425_nth_1___r__
                          (clojure.core/nth
                           input__243425_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs
                           (clojure.core/vec
                            input__243425_nth_1___l__)]
                          (clojure.core/let
                           [input__243425_nth_1___r___l__
                            (clojure.core/subvec
                             input__243425_nth_1___r__
                             0
                             (clojure.core/min
                              (clojure.core/count
                               input__243425_nth_1___r__)
                              1))]
                           (if
                            (clojure.core/=
                             (clojure.core/count
                              input__243425_nth_1___r___l__)
                             1)
                            (clojure.core/let
                             [input__243425_nth_1___r___r__
                              (clojure.core/subvec
                               input__243425_nth_1___r__
                               1)]
                             (if
                              (clojure.core/=
                               input__243425_nth_1___r___l__
                               ['.])
                              (clojure.core/let
                               [?rest input__243425_nth_1___r___r__]
                               (clojure.core/let
                                [?env input__243425_nth_2__]
                                (try
                                 [(clojure.core/let
                                   [!xs__counter
                                    (meander.runtime.zeta/iterator
                                     !xs)]
                                   (clojure.core/let
                                    [CATA_RESULT__10889__auto__
                                     (CATA__FN__243502
                                      ['meander.dev.parse.zeta/make-join
                                       (clojure.core/let
                                        [CATA_RESULT__10889__auto__
                                         (CATA__FN__243502
                                          ['meander.dev.parse.zeta/parse-sequential
                                           (clojure.core/into
                                            []
                                            (clojure.core/vec
                                             (clojure.core/iterator-seq
                                              !xs__counter)))
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10889__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10889__auto__
                                          0)))
                                       (clojure.core/let
                                        [CATA_RESULT__10889__auto__
                                         (CATA__FN__243502
                                          ['meander.dev.parse.zeta/parse-sequential
                                           ?rest
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10889__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10889__auto__
                                          0)))
                                       ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__10889__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__10889__auto__
                                      0))))]
                                 (catch
                                  java.lang.Exception
                                  e__11829__auto__
                                  (if
                                   (meander.runtime.zeta/fail?
                                    e__11829__auto__)
                                   (meander.runtime.zeta/fail)
                                   (throw e__11829__auto__))))))
                              (meander.runtime.zeta/fail)))
                            (meander.runtime.zeta/fail)))))]
                       (if
                        (meander.runtime.zeta/fail? result__244989)
                        (recur
                         (clojure.core/next search_space__244988))
                        result__244989))
                      (state__244983))))
                   (state__244983
                    []
                    (clojure.core/let
                     [input__243425_nth_1___l__
                      (clojure.core/subvec
                       input__243425_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__243425_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__243425_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__243425_nth_1___r__
                        (clojure.core/subvec input__243425_nth_1__ 1)]
                       (if
                        (clojure.core/=
                         input__243425_nth_1___l__
                         ['...])
                        (clojure.core/let
                         [?rest input__243425_nth_1___r__]
                         (clojure.core/let
                          [?env input__243425_nth_2__]
                          (try
                           [(clojure.core/let
                             [CATA_RESULT__10889__auto__
                              (CATA__FN__243502
                               ['meander.dev.parse.zeta/parse-sequential
                                ?rest
                                ?env])]
                             (if
                              (meander.runtime.zeta/fail?
                               CATA_RESULT__10889__auto__)
                              (throw (meander.runtime.zeta/fail))
                              (clojure.core/nth
                               CATA_RESULT__10889__auto__
                               0)))]
                           (catch
                            java.lang.Exception
                            e__11829__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__11829__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__11829__auto__))))))
                        (state__244984)))
                      (state__244984))))
                   (state__244984
                    []
                    (clojure.core/loop
                     [search_space__244991
                      (meander.runtime.zeta/epsilon-partitions
                       2
                       input__243425_nth_1__)]
                     (if
                      (clojure.core/seq search_space__244991)
                      (clojure.core/let
                       [input__243425_nth_1___parts__
                        (clojure.core/first search_space__244991)
                        result__244992
                        (clojure.core/let
                         [input__243425_nth_1___l__
                          (clojure.core/nth
                           input__243425_nth_1___parts__
                           0)
                          input__243425_nth_1___r__
                          (clojure.core/nth
                           input__243425_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs []]
                          (clojure.core/let
                           [ret__9750__auto__
                            (meander.runtime.zeta/epsilon-run-star-1
                             input__243425_nth_1___l__
                             [!xs]
                             (clojure.core/fn
                              [[!xs] input__243661]
                              (clojure.core/let
                               [input__243661_nth_0__
                                (clojure.core/nth input__243661 0)]
                               (clojure.core/letfn
                                [(save__243662
                                  []
                                  (meander.runtime.zeta/fail))
                                 (f__244995
                                  []
                                  (clojure.core/let
                                   [!xs
                                    (clojure.core/conj
                                     !xs
                                     input__243661_nth_0__)]
                                   [!xs]))]
                                (if
                                 (clojure.core/symbol?
                                  input__243661_nth_0__)
                                 (clojure.core/let
                                  [X__243664
                                   (clojure.core/namespace
                                    input__243661_nth_0__)]
                                  (clojure.core/case
                                   X__243664
                                   (nil)
                                   (clojure.core/let
                                    [X__243666
                                     (clojure.core/name
                                      input__243661_nth_0__)]
                                    (if
                                     (clojure.core/string? X__243666)
                                     (if
                                      (clojure.core/re-matches
                                       #"\.\.(?:\.|\d+)"
                                       X__243666)
                                      (save__243662)
                                      (f__244995))
                                     (f__244995)))
                                   (f__244995)))
                                 (f__244995)))))
                             (clojure.core/fn
                              [[!xs]]
                              (clojure.core/let
                               [input__243425_nth_1___r___l__
                                (clojure.core/subvec
                                 input__243425_nth_1___r__
                                 0
                                 (clojure.core/min
                                  (clojure.core/count
                                   input__243425_nth_1___r__)
                                  1))]
                               (if
                                (clojure.core/=
                                 (clojure.core/count
                                  input__243425_nth_1___r___l__)
                                 1)
                                (clojure.core/let
                                 [input__243425_nth_1___r___r__
                                  (clojure.core/subvec
                                   input__243425_nth_1___r__
                                   1)]
                                 (if
                                  (clojure.core/=
                                   input__243425_nth_1___r___l__
                                   ['...])
                                  (clojure.core/let
                                   [?rest
                                    input__243425_nth_1___r___r__]
                                   (clojure.core/let
                                    [?env input__243425_nth_2__]
                                    (try
                                     [(clojure.core/let
                                       [!xs__counter
                                        (meander.runtime.zeta/iterator
                                         !xs)]
                                       (clojure.core/let
                                        [CATA_RESULT__10889__auto__
                                         (CATA__FN__243502
                                          ['meander.dev.parse.zeta/make-star
                                           (clojure.core/let
                                            [CATA_RESULT__10889__auto__
                                             (CATA__FN__243502
                                              ['meander.dev.parse.zeta/parse-sequential
                                               (clojure.core/into
                                                []
                                                (clojure.core/vec
                                                 (clojure.core/iterator-seq
                                                  !xs__counter)))
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__10889__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__10889__auto__
                                              0)))
                                           (clojure.core/let
                                            [CATA_RESULT__10889__auto__
                                             (CATA__FN__243502
                                              ['meander.dev.parse.zeta/parse-sequential
                                               ?rest
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__10889__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__10889__auto__
                                              0)))
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10889__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10889__auto__
                                          0))))]
                                     (catch
                                      java.lang.Exception
                                      e__11829__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__11829__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__11829__auto__))))))
                                  (meander.runtime.zeta/fail)))
                                (meander.runtime.zeta/fail)))))]
                           (if
                            (meander.runtime.zeta/fail?
                             ret__9750__auto__)
                            (meander.runtime.zeta/fail)
                            ret__9750__auto__))))]
                       (if
                        (meander.runtime.zeta/fail? result__244992)
                        (recur
                         (clojure.core/next search_space__244991))
                        result__244992))
                      (state__244979))))]
                  (state__244981))
                 (state__244979))
                (state__244979)))
              (state__244979)))
            (state__244979
             []
             (if
              (clojure.core/= (clojure.core/count input__243425) 4)
              (clojure.core/let
               [input__243425_nth_0__
                (clojure.core/nth input__243425 0)
                input__243425_nth_1__
                (clojure.core/nth input__243425 1)
                input__243425_nth_2__
                (clojure.core/nth input__243425 2)]
               (clojure.core/letfn
                [(state__244996
                  []
                  (clojure.core/let
                   [input__243425_nth_3__
                    (clojure.core/nth input__243425 3)]
                   (clojure.core/case
                    input__243425_nth_0__
                    (meander.dev.parse.zeta/make-star)
                    (clojure.core/letfn
                     [(state__244998
                       []
                       (if
                        (clojure.core/map? input__243425_nth_1__)
                        (clojure.core/let
                         [VAL__243670
                          (.valAt input__243425_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__243670
                          (:cat)
                          (clojure.core/let
                           [VAL__243671
                            (.valAt input__243425_nth_1__ :sequence)]
                           (if
                            (clojure.core/vector? VAL__243671)
                            (if
                             (clojure.core/=
                              (clojure.core/count VAL__243671)
                              1)
                             (clojure.core/let
                              [VAL__243671_nth_0__
                               (clojure.core/nth VAL__243671 0)]
                              (if
                               (clojure.core/map? VAL__243671_nth_0__)
                               (clojure.core/let
                                [VAL__243676
                                 (.valAt VAL__243671_nth_0__ :tag)]
                                (clojure.core/case
                                 VAL__243676
                                 (:memory-variable)
                                 (clojure.core/let
                                  [?memory-variable
                                   VAL__243671_nth_0__]
                                  (clojure.core/let
                                   [VAL__243672
                                    (.valAt
                                     input__243425_nth_1__
                                     :next)]
                                   (if
                                    (clojure.core/map? VAL__243672)
                                    (clojure.core/let
                                     [VAL__243673
                                      (.valAt VAL__243672 :tag)]
                                     (clojure.core/case
                                      VAL__243673
                                      (:empty)
                                      (clojure.core/let
                                       [?next input__243425_nth_2__]
                                       (clojure.core/let
                                        [?env input__243425_nth_3__]
                                        (try
                                         [(clojure.core/let
                                           [CATA_RESULT__10889__auto__
                                            (CATA__FN__243502
                                             ['meander.dev.parse.zeta/make-join
                                              {:tag :into,
                                               :memory-variable
                                               ?memory-variable}
                                              ?next
                                              ?env])]
                                           (if
                                            (meander.runtime.zeta/fail?
                                             CATA_RESULT__10889__auto__)
                                            (throw
                                             (meander.runtime.zeta/fail))
                                            (clojure.core/nth
                                             CATA_RESULT__10889__auto__
                                             0)))]
                                         (catch
                                          java.lang.Exception
                                          e__11829__auto__
                                          (if
                                           (meander.runtime.zeta/fail?
                                            e__11829__auto__)
                                           (meander.runtime.zeta/fail)
                                           (throw
                                            e__11829__auto__))))))
                                      (state__244999)))
                                    (state__244999))))
                                 (state__244999)))
                               (state__244999)))
                             (state__244999))
                            (state__244999)))
                          (state__244999)))
                        (state__244999)))
                      (state__244999
                       []
                       (clojure.core/let
                        [?pattern input__243425_nth_1__]
                        (clojure.core/let
                         [?next input__243425_nth_2__]
                         (if
                          (clojure.core/map? input__243425_nth_3__)
                          (clojure.core/let
                           [VAL__243679
                            (.valAt input__243425_nth_3__ :context)]
                           (clojure.core/case
                            VAL__243679
                            (:string)
                            (try
                             [{:tag :string-star,
                               :greedy? false,
                               :pattern ?pattern,
                               :next ?next}]
                             (catch
                              java.lang.Exception
                              e__11829__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__11829__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__11829__auto__))))
                            (state__244997)))
                          (state__244997)))))]
                     (state__244998))
                    (state__244997))))
                 (state__244997
                  []
                  (clojure.core/case
                   input__243425_nth_0__
                   (meander.dev.parse.zeta/make-star)
                   (clojure.core/let
                    [?pattern input__243425_nth_1__]
                    (clojure.core/let
                     [?next input__243425_nth_2__]
                     (try
                      [{:tag :star,
                        :greedy? false,
                        :pattern ?pattern,
                        :next ?next}]
                      (catch
                       java.lang.Exception
                       e__11829__auto__
                       (if
                        (meander.runtime.zeta/fail? e__11829__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__11829__auto__))))))
                   (state__244980)))]
                (state__244996)))
              (state__244980)))
            (state__244980
             []
             (if
              (clojure.core/= (clojure.core/count input__243425) 3)
              (clojure.core/let
               [input__243425_nth_0__
                (clojure.core/nth input__243425 0)
                input__243425_nth_1__
                (clojure.core/nth input__243425 1)
                input__243425_nth_2__
                (clojure.core/nth input__243425 2)]
               (clojure.core/case
                input__243425_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__243425_nth_1__)
                 (clojure.core/let
                  [input__243425_nth_1___l__
                   (clojure.core/subvec
                    input__243425_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__243425_nth_1__)
                     1))]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__243425_nth_1___l__)
                    1)
                   (clojure.core/let
                    [input__243425_nth_1___r__
                     (clojure.core/subvec input__243425_nth_1__ 1)]
                    (clojure.core/let
                     [input__243425_nth_1___l___nth_0__
                      (clojure.core/nth input__243425_nth_1___l__ 0)]
                     (if
                      (clojure.core/symbol?
                       input__243425_nth_1___l___nth_0__)
                      (clojure.core/let
                       [X__243687
                        (clojure.core/namespace
                         input__243425_nth_1___l___nth_0__)]
                       (clojure.core/case
                        X__243687
                        (nil)
                        (clojure.core/let
                         [X__243689
                          (clojure.core/name
                           input__243425_nth_1___l___nth_0__)]
                         (if
                          (clojure.core/string? X__243689)
                          (clojure.core/let
                           [ret__243690
                            (clojure.core/re-matches
                             #"\.\.(\d+)"
                             X__243689)]
                           (if
                            (clojure.core/some? ret__243690)
                            (if
                             (clojure.core/vector? ret__243690)
                             (if
                              (clojure.core/=
                               (clojure.core/count ret__243690)
                               2)
                              (clojure.core/let
                               [ret__243690_nth_1__
                                (clojure.core/nth ret__243690 1)]
                               (clojure.core/let
                                [?n ret__243690_nth_1__]
                                (clojure.core/let
                                 [?operator
                                  input__243425_nth_1___l___nth_0__]
                                 (clojure.core/let
                                  [?rest input__243425_nth_1___r__]
                                  (clojure.core/let
                                   [?env input__243425_nth_2__]
                                   (try
                                    [{:tag :syntax-error,
                                      :message
                                      "The n or more operator ..N must be preceeded by at least one pattern"}]
                                    (catch
                                     java.lang.Exception
                                     e__11829__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__11829__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__11829__auto__)))))))))
                              (state__244925))
                             (state__244925))
                            (state__244925)))
                          (state__244925)))
                        (state__244925)))
                      (state__244925))))
                   (state__244925)))
                 (state__244925))
                (state__244925)))
              (state__244925)))]
           (state__244978))
          (state__244925)))
        (state__244925
         []
         (clojure.core/letfn
          [(def__243693
            [arg__243717]
            (clojure.core/letfn
             [(state__245000
               []
               (clojure.core/let
                [x__243718 :string-plus]
                (clojure.core/let
                 [?tag x__243718]
                 (if
                  (clojure.core/map? arg__243717)
                  (clojure.core/let
                   [VAL__243719 (.valAt arg__243717 :context)]
                   (clojure.core/case
                    VAL__243719
                    (:string)
                    (clojure.core/let [?env arg__243717] [?tag ?env])
                    (state__245001)))
                  (state__245001)))))
              (state__245001
               []
               (clojure.core/let
                [x__243720 :plus]
                (clojure.core/let
                 [?tag x__243720]
                 (clojure.core/let [?env arg__243717] [?tag ?env]))))]
             (state__245000)))]
          (if
           (clojure.core/vector? input__243425)
           (if
            (clojure.core/= (clojure.core/count input__243425) 3)
            (clojure.core/let
             [input__243425_nth_0__
              (clojure.core/nth input__243425 0)
              input__243425_nth_1__
              (clojure.core/nth input__243425 1)
              input__243425_nth_2__
              (clojure.core/nth input__243425 2)]
             (clojure.core/case
              input__243425_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__243425_nth_1__)
               (clojure.core/loop
                [search_space__245002
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__243425_nth_1__)]
                (if
                 (clojure.core/seq search_space__245002)
                 (clojure.core/let
                  [input__243425_nth_1___parts__
                   (clojure.core/first search_space__245002)
                   result__245003
                   (clojure.core/let
                    [input__243425_nth_1___l__
                     (clojure.core/nth input__243425_nth_1___parts__ 0)
                     input__243425_nth_1___r__
                     (clojure.core/nth
                      input__243425_nth_1___parts__
                      1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__9750__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__243425_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__243710]
                         (clojure.core/let
                          [input__243710_nth_0__
                           (clojure.core/nth input__243710 0)]
                          (clojure.core/letfn
                           [(save__243711
                             []
                             (meander.runtime.zeta/fail))
                            (f__245006
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__243710_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol?
                             input__243710_nth_0__)
                            (clojure.core/let
                             [X__243713
                              (clojure.core/namespace
                               input__243710_nth_0__)]
                             (clojure.core/case
                              X__243713
                              (nil)
                              (clojure.core/let
                               [X__243715
                                (clojure.core/name
                                 input__243710_nth_0__)]
                               (if
                                (clojure.core/string? X__243715)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__243715)
                                 (save__243711)
                                 (f__245006))
                                (f__245006)))
                              (f__245006)))
                            (f__245006)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__243425_nth_1___r___l__
                           (clojure.core/subvec
                            input__243425_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__243425_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__243425_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__243425_nth_1___r___r__
                             (clojure.core/subvec
                              input__243425_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__243425_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__243425_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__243425_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__243704
                                (clojure.core/namespace
                                 input__243425_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__243704
                                (nil)
                                (clojure.core/let
                                 [X__243706
                                  (clojure.core/name
                                   input__243425_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__243706)
                                  (clojure.core/let
                                   [ret__243707
                                    (clojure.core/re-matches
                                     #"\.\.(\d+)"
                                     X__243706)]
                                   (if
                                    (clojure.core/some? ret__243707)
                                    (if
                                     (clojure.core/vector? ret__243707)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__243707)
                                       2)
                                      (clojure.core/let
                                       [ret__243707_nth_1__
                                        (clojure.core/nth
                                         ret__243707
                                         1)]
                                       (clojure.core/let
                                        [?n ret__243707_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__243425_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__9586__auto__
                                           (def__243693
                                            input__243425_nth_2__)]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            x__9586__auto__)
                                           (meander.runtime.zeta/fail)
                                           (clojure.core/let
                                            [[?tag ?env]
                                             x__9586__auto__]
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
                                                 [CATA_RESULT__10889__auto__
                                                  (CATA__FN__243502
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    (clojure.core/into
                                                     []
                                                     (clojure.core/vec
                                                      (clojure.core/iterator-seq
                                                       !xs__counter)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__10889__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__10889__auto__
                                                   0))),
                                                :next
                                                (clojure.core/let
                                                 [CATA_RESULT__10889__auto__
                                                  (CATA__FN__243502
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    ?rest
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__10889__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__10889__auto__
                                                   0)))})]
                                             (catch
                                              java.lang.Exception
                                              e__11829__auto__
                                              (if
                                               (meander.runtime.zeta/fail?
                                                e__11829__auto__)
                                               (meander.runtime.zeta/fail)
                                               (throw
                                                e__11829__auto__))))))))))
                                      (meander.runtime.zeta/fail))
                                     (meander.runtime.zeta/fail))
                                    (meander.runtime.zeta/fail)))
                                  (meander.runtime.zeta/fail)))
                                (meander.runtime.zeta/fail)))
                              (meander.runtime.zeta/fail))))
                           (meander.runtime.zeta/fail)))))]
                      (if
                       (meander.runtime.zeta/fail? ret__9750__auto__)
                       (meander.runtime.zeta/fail)
                       ret__9750__auto__))))]
                  (if
                   (meander.runtime.zeta/fail? result__245003)
                   (recur (clojure.core/next search_space__245002))
                   result__245003))
                 (state__244926)))
               (state__244926))
              (state__244926)))
            (state__244926))
           (state__244926))))
        (state__244926
         []
         (if
          (clojure.core/vector? input__243425)
          (if
           (clojure.core/= (clojure.core/count input__243425) 3)
           (clojure.core/let
            [input__243425_nth_0__
             (clojure.core/nth input__243425 0)
             input__243425_nth_1__
             (clojure.core/nth input__243425 1)
             input__243425_nth_2__
             (clojure.core/nth input__243425 2)]
            (clojure.core/case
             input__243425_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__243425_nth_1__)
              (clojure.core/let
               [input__243425_nth_1___l__
                (clojure.core/subvec
                 input__243425_nth_1__
                 0
                 (clojure.core/min
                  (clojure.core/count input__243425_nth_1__)
                  1))]
               (if
                (clojure.core/=
                 (clojure.core/count input__243425_nth_1___l__)
                 1)
                (clojure.core/let
                 [input__243425_nth_1___r__
                  (clojure.core/subvec input__243425_nth_1__ 1)]
                 (clojure.core/let
                  [input__243425_nth_1___l___nth_0__
                   (clojure.core/nth input__243425_nth_1___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__243425_nth_1___l___nth_0__)
                   (clojure.core/let
                    [X__243738
                     (clojure.core/namespace
                      input__243425_nth_1___l___nth_0__)]
                    (clojure.core/case
                     X__243738
                     (nil)
                     (clojure.core/let
                      [X__243740
                       (clojure.core/name
                        input__243425_nth_1___l___nth_0__)]
                      (if
                       (clojure.core/string? X__243740)
                       (clojure.core/let
                        [ret__243741
                         (clojure.core/re-matches
                          #"\.\.(\?.+)"
                          X__243740)]
                        (if
                         (clojure.core/some? ret__243741)
                         (if
                          (clojure.core/vector? ret__243741)
                          (if
                           (clojure.core/=
                            (clojure.core/count ret__243741)
                            2)
                           (clojure.core/let
                            [ret__243741_nth_1__
                             (clojure.core/nth ret__243741 1)]
                            (clojure.core/let
                             [?n ret__243741_nth_1__]
                             (clojure.core/let
                              [?operator
                               input__243425_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?rest input__243425_nth_1___r__]
                               (clojure.core/let
                                [?env input__243425_nth_2__]
                                (try
                                 [{:tag :syntax-error,
                                   :message
                                   "The ?n or more operator ..?n must be preceeded by at least one pattern"}]
                                 (catch
                                  java.lang.Exception
                                  e__11829__auto__
                                  (if
                                   (meander.runtime.zeta/fail?
                                    e__11829__auto__)
                                   (meander.runtime.zeta/fail)
                                   (throw e__11829__auto__)))))))))
                           (state__244927))
                          (state__244927))
                         (state__244927)))
                       (state__244927)))
                     (state__244927)))
                   (state__244927))))
                (state__244927)))
              (state__244927))
             (state__244927)))
           (state__244927))
          (state__244927)))
        (state__244927
         []
         (clojure.core/letfn
          [(def__243744
            [arg__243768]
            (clojure.core/letfn
             [(state__245007
               []
               (clojure.core/let
                [x__243769 :string-logical-plus]
                (clojure.core/let
                 [?tag x__243769]
                 (if
                  (clojure.core/map? arg__243768)
                  (clojure.core/let
                   [VAL__243770 (.valAt arg__243768 :context)]
                   (clojure.core/case
                    VAL__243770
                    (:string)
                    (clojure.core/let [?env arg__243768] [?tag ?env])
                    (state__245008)))
                  (state__245008)))))
              (state__245008
               []
               (clojure.core/let
                [x__243771 :logical-plus]
                (clojure.core/let
                 [?tag x__243771]
                 (clojure.core/let [?env arg__243768] [?tag ?env]))))]
             (state__245007)))]
          (if
           (clojure.core/vector? input__243425)
           (if
            (clojure.core/= (clojure.core/count input__243425) 3)
            (clojure.core/let
             [input__243425_nth_0__
              (clojure.core/nth input__243425 0)
              input__243425_nth_1__
              (clojure.core/nth input__243425 1)
              input__243425_nth_2__
              (clojure.core/nth input__243425 2)]
             (clojure.core/case
              input__243425_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__243425_nth_1__)
               (clojure.core/loop
                [search_space__245009
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__243425_nth_1__)]
                (if
                 (clojure.core/seq search_space__245009)
                 (clojure.core/let
                  [input__243425_nth_1___parts__
                   (clojure.core/first search_space__245009)
                   result__245010
                   (clojure.core/let
                    [input__243425_nth_1___l__
                     (clojure.core/nth input__243425_nth_1___parts__ 0)
                     input__243425_nth_1___r__
                     (clojure.core/nth
                      input__243425_nth_1___parts__
                      1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__9750__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__243425_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__243761]
                         (clojure.core/let
                          [input__243761_nth_0__
                           (clojure.core/nth input__243761 0)]
                          (clojure.core/letfn
                           [(save__243762
                             []
                             (meander.runtime.zeta/fail))
                            (f__245013
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__243761_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol?
                             input__243761_nth_0__)
                            (clojure.core/let
                             [X__243764
                              (clojure.core/namespace
                               input__243761_nth_0__)]
                             (clojure.core/case
                              X__243764
                              (nil)
                              (clojure.core/let
                               [X__243766
                                (clojure.core/name
                                 input__243761_nth_0__)]
                               (if
                                (clojure.core/string? X__243766)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__243766)
                                 (save__243762)
                                 (f__245013))
                                (f__245013)))
                              (f__245013)))
                            (f__245013)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__243425_nth_1___r___l__
                           (clojure.core/subvec
                            input__243425_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__243425_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__243425_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__243425_nth_1___r___r__
                             (clojure.core/subvec
                              input__243425_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__243425_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__243425_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__243425_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__243755
                                (clojure.core/namespace
                                 input__243425_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__243755
                                (nil)
                                (clojure.core/let
                                 [X__243757
                                  (clojure.core/name
                                   input__243425_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__243757)
                                  (clojure.core/let
                                   [ret__243758
                                    (clojure.core/re-matches
                                     #"\.\.(\?.+)"
                                     X__243757)]
                                   (if
                                    (clojure.core/some? ret__243758)
                                    (if
                                     (clojure.core/vector? ret__243758)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__243758)
                                       2)
                                      (clojure.core/let
                                       [ret__243758_nth_1__
                                        (clojure.core/nth
                                         ret__243758
                                         1)]
                                       (clojure.core/let
                                        [?n ret__243758_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__243425_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__9586__auto__
                                           (def__243744
                                            input__243425_nth_2__)]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            x__9586__auto__)
                                           (meander.runtime.zeta/fail)
                                           (clojure.core/let
                                            [[?tag ?env]
                                             x__9586__auto__]
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
                                                 [CATA_RESULT__10889__auto__
                                                  (CATA__FN__243502
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    (clojure.core/into
                                                     []
                                                     (clojure.core/vec
                                                      (clojure.core/iterator-seq
                                                       !xs__counter)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__10889__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__10889__auto__
                                                   0))),
                                                :next
                                                (clojure.core/let
                                                 [CATA_RESULT__10889__auto__
                                                  (CATA__FN__243502
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    ?rest
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__10889__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__10889__auto__
                                                   0)))})]
                                             (catch
                                              java.lang.Exception
                                              e__11829__auto__
                                              (if
                                               (meander.runtime.zeta/fail?
                                                e__11829__auto__)
                                               (meander.runtime.zeta/fail)
                                               (throw
                                                e__11829__auto__))))))))))
                                      (meander.runtime.zeta/fail))
                                     (meander.runtime.zeta/fail))
                                    (meander.runtime.zeta/fail)))
                                  (meander.runtime.zeta/fail)))
                                (meander.runtime.zeta/fail)))
                              (meander.runtime.zeta/fail))))
                           (meander.runtime.zeta/fail)))))]
                      (if
                       (meander.runtime.zeta/fail? ret__9750__auto__)
                       (meander.runtime.zeta/fail)
                       ret__9750__auto__))))]
                  (if
                   (meander.runtime.zeta/fail? result__245010)
                   (recur (clojure.core/next search_space__245009))
                   result__245010))
                 (state__244928)))
               (state__244928))
              (state__244928)))
            (state__244928))
           (state__244928))))
        (state__244928
         []
         (if
          (clojure.core/vector? input__243425)
          (if
           (clojure.core/= (clojure.core/count input__243425) 3)
           (clojure.core/let
            [input__243425_nth_0__
             (clojure.core/nth input__243425 0)
             input__243425_nth_1__
             (clojure.core/nth input__243425 1)
             input__243425_nth_2__
             (clojure.core/nth input__243425 2)]
            (clojure.core/case
             input__243425_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__243425_nth_1__)
              (clojure.core/let
               [input__243425_nth_1___l__
                (clojure.core/subvec
                 input__243425_nth_1__
                 0
                 (clojure.core/min
                  (clojure.core/count input__243425_nth_1__)
                  1))]
               (if
                (clojure.core/=
                 (clojure.core/count input__243425_nth_1___l__)
                 1)
                (clojure.core/let
                 [input__243425_nth_1___r__
                  (clojure.core/subvec input__243425_nth_1__ 1)]
                 (clojure.core/let
                  [input__243425_nth_1___l___nth_0__
                   (clojure.core/nth input__243425_nth_1___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__243425_nth_1___l___nth_0__)
                   (clojure.core/let
                    [X__243789
                     (clojure.core/namespace
                      input__243425_nth_1___l___nth_0__)]
                    (clojure.core/case
                     X__243789
                     (nil)
                     (clojure.core/let
                      [X__243791
                       (clojure.core/name
                        input__243425_nth_1___l___nth_0__)]
                      (if
                       (clojure.core/string? X__243791)
                       (clojure.core/let
                        [ret__243792
                         (clojure.core/re-matches
                          #"\.\.(!.+)"
                          X__243791)]
                        (if
                         (clojure.core/some? ret__243792)
                         (if
                          (clojure.core/vector? ret__243792)
                          (if
                           (clojure.core/=
                            (clojure.core/count ret__243792)
                            2)
                           (clojure.core/let
                            [ret__243792_nth_1__
                             (clojure.core/nth ret__243792 1)]
                            (clojure.core/let
                             [?n ret__243792_nth_1__]
                             (clojure.core/let
                              [?operator
                               input__243425_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?rest input__243425_nth_1___r__]
                               (clojure.core/let
                                [?env input__243425_nth_2__]
                                (try
                                 [{:tag :syntax-error,
                                   :message
                                   "The operator ..!n must be preceeded by at least one pattern"}]
                                 (catch
                                  java.lang.Exception
                                  e__11829__auto__
                                  (if
                                   (meander.runtime.zeta/fail?
                                    e__11829__auto__)
                                   (meander.runtime.zeta/fail)
                                   (throw e__11829__auto__)))))))))
                           (state__244929))
                          (state__244929))
                         (state__244929)))
                       (state__244929)))
                     (state__244929)))
                   (state__244929))))
                (state__244929)))
              (state__244929))
             (state__244929)))
           (state__244929))
          (state__244929)))
        (state__244929
         []
         (clojure.core/letfn
          [(def__243795
            [arg__243819]
            (clojure.core/letfn
             [(state__245014
               []
               (clojure.core/let
                [x__243820 :string-memory-plus]
                (clojure.core/let
                 [?tag x__243820]
                 (if
                  (clojure.core/map? arg__243819)
                  (clojure.core/let
                   [VAL__243821 (.valAt arg__243819 :context)]
                   (clojure.core/case
                    VAL__243821
                    (:string)
                    (clojure.core/let [?env arg__243819] [?tag ?env])
                    (state__245015)))
                  (state__245015)))))
              (state__245015
               []
               (clojure.core/let
                [x__243822 :memory-plus]
                (clojure.core/let
                 [?tag x__243822]
                 (clojure.core/let [?env arg__243819] [?tag ?env]))))]
             (state__245014)))]
          (if
           (clojure.core/vector? input__243425)
           (if
            (clojure.core/= (clojure.core/count input__243425) 3)
            (clojure.core/let
             [input__243425_nth_0__
              (clojure.core/nth input__243425 0)
              input__243425_nth_1__
              (clojure.core/nth input__243425 1)
              input__243425_nth_2__
              (clojure.core/nth input__243425 2)]
             (clojure.core/case
              input__243425_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__243425_nth_1__)
               (clojure.core/loop
                [search_space__245016
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__243425_nth_1__)]
                (if
                 (clojure.core/seq search_space__245016)
                 (clojure.core/let
                  [input__243425_nth_1___parts__
                   (clojure.core/first search_space__245016)
                   result__245017
                   (clojure.core/let
                    [input__243425_nth_1___l__
                     (clojure.core/nth input__243425_nth_1___parts__ 0)
                     input__243425_nth_1___r__
                     (clojure.core/nth
                      input__243425_nth_1___parts__
                      1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__9750__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__243425_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__243812]
                         (clojure.core/let
                          [input__243812_nth_0__
                           (clojure.core/nth input__243812 0)]
                          (clojure.core/letfn
                           [(save__243813
                             []
                             (meander.runtime.zeta/fail))
                            (f__245020
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__243812_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol?
                             input__243812_nth_0__)
                            (clojure.core/let
                             [X__243815
                              (clojure.core/namespace
                               input__243812_nth_0__)]
                             (clojure.core/case
                              X__243815
                              (nil)
                              (clojure.core/let
                               [X__243817
                                (clojure.core/name
                                 input__243812_nth_0__)]
                               (if
                                (clojure.core/string? X__243817)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__243817)
                                 (save__243813)
                                 (f__245020))
                                (f__245020)))
                              (f__245020)))
                            (f__245020)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__243425_nth_1___r___l__
                           (clojure.core/subvec
                            input__243425_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__243425_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__243425_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__243425_nth_1___r___r__
                             (clojure.core/subvec
                              input__243425_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__243425_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__243425_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__243425_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__243806
                                (clojure.core/namespace
                                 input__243425_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__243806
                                (nil)
                                (clojure.core/let
                                 [X__243808
                                  (clojure.core/name
                                   input__243425_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__243808)
                                  (clojure.core/let
                                   [ret__243809
                                    (clojure.core/re-matches
                                     #"\.\.(\!.+)"
                                     X__243808)]
                                   (if
                                    (clojure.core/some? ret__243809)
                                    (if
                                     (clojure.core/vector? ret__243809)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__243809)
                                       2)
                                      (clojure.core/let
                                       [ret__243809_nth_1__
                                        (clojure.core/nth
                                         ret__243809
                                         1)]
                                       (clojure.core/let
                                        [?n ret__243809_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__243425_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__9586__auto__
                                           (def__243795
                                            input__243425_nth_2__)]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            x__9586__auto__)
                                           (meander.runtime.zeta/fail)
                                           (clojure.core/let
                                            [[?tag ?env]
                                             x__9586__auto__]
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
                                                 [CATA_RESULT__10889__auto__
                                                  (CATA__FN__243502
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    (clojure.core/into
                                                     []
                                                     (clojure.core/vec
                                                      (clojure.core/iterator-seq
                                                       !xs__counter)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__10889__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__10889__auto__
                                                   0))),
                                                :next
                                                (clojure.core/let
                                                 [CATA_RESULT__10889__auto__
                                                  (CATA__FN__243502
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    ?rest
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__10889__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__10889__auto__
                                                   0)))})]
                                             (catch
                                              java.lang.Exception
                                              e__11829__auto__
                                              (if
                                               (meander.runtime.zeta/fail?
                                                e__11829__auto__)
                                               (meander.runtime.zeta/fail)
                                               (throw
                                                e__11829__auto__))))))))))
                                      (meander.runtime.zeta/fail))
                                     (meander.runtime.zeta/fail))
                                    (meander.runtime.zeta/fail)))
                                  (meander.runtime.zeta/fail)))
                                (meander.runtime.zeta/fail)))
                              (meander.runtime.zeta/fail))))
                           (meander.runtime.zeta/fail)))))]
                      (if
                       (meander.runtime.zeta/fail? ret__9750__auto__)
                       (meander.runtime.zeta/fail)
                       ret__9750__auto__))))]
                  (if
                   (meander.runtime.zeta/fail? result__245017)
                   (recur (clojure.core/next search_space__245016))
                   result__245017))
                 (state__244930)))
               (state__244930))
              (state__244930)))
            (state__244930))
           (state__244930))))
        (state__244930
         []
         (if
          (clojure.core/vector? input__243425)
          (clojure.core/letfn
           [(state__245021
             []
             (if
              (clojure.core/= (clojure.core/count input__243425) 3)
              (clojure.core/let
               [input__243425_nth_0__
                (clojure.core/nth input__243425 0)
                input__243425_nth_1__
                (clojure.core/nth input__243425 1)
                input__243425_nth_2__
                (clojure.core/nth input__243425 2)]
               (clojure.core/case
                input__243425_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__243425_nth_1__)
                 (clojure.core/let
                  [!xs (clojure.core/vec input__243425_nth_1__)]
                  (clojure.core/let
                   [?env input__243425_nth_2__]
                   (try
                    [(clojure.core/let
                      [!xs__counter
                       (meander.runtime.zeta/iterator !xs)]
                      (clojure.core/let
                       [CATA_RESULT__10889__auto__
                        (CATA__FN__243502
                         ['meander.dev.parse.zeta/make-cat
                          (clojure.core/into
                           []
                           (clojure.core/loop
                            [return__243503
                             (clojure.core/transient [])]
                            (if
                             (clojure.core/and (.hasNext !xs__counter))
                             (recur
                              (clojure.core/conj!
                               return__243503
                               (clojure.core/let
                                [CATA_RESULT__10889__auto__
                                 (CATA__FN__243502
                                  [(if
                                    (.hasNext !xs__counter)
                                    (.next !xs__counter))
                                   ?env])]
                                (if
                                 (meander.runtime.zeta/fail?
                                  CATA_RESULT__10889__auto__)
                                 (throw (meander.runtime.zeta/fail))
                                 (clojure.core/nth
                                  CATA_RESULT__10889__auto__
                                  0)))))
                             (clojure.core/persistent!
                              return__243503))))
                          {:tag :empty}
                          ?env])]
                       (if
                        (meander.runtime.zeta/fail?
                         CATA_RESULT__10889__auto__)
                        (throw (meander.runtime.zeta/fail))
                        (clojure.core/nth
                         CATA_RESULT__10889__auto__
                         0))))]
                    (catch
                     java.lang.Exception
                     e__11829__auto__
                     (if
                      (meander.runtime.zeta/fail? e__11829__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__11829__auto__))))))
                 (state__245022))
                (state__245022)))
              (state__245022)))
            (state__245022
             []
             (if
              (clojure.core/= (clojure.core/count input__243425) 4)
              (clojure.core/let
               [input__243425_nth_0__
                (clojure.core/nth input__243425 0)
                input__243425_nth_1__
                (clojure.core/nth input__243425 1)
                input__243425_nth_2__
                (clojure.core/nth input__243425 2)]
               (clojure.core/letfn
                [(state__245024
                  []
                  (clojure.core/let
                   [input__243425_nth_3__
                    (clojure.core/nth input__243425 3)]
                   (clojure.core/case
                    input__243425_nth_0__
                    (meander.dev.parse.zeta/make-cat)
                    (clojure.core/letfn
                     [(state__245029
                       []
                       (if
                        (clojure.core/vector? input__243425_nth_1__)
                        (clojure.core/letfn
                         [(state__245031
                           []
                           (clojure.core/case
                            input__243425_nth_1__
                            ([])
                            (clojure.core/let
                             [?next input__243425_nth_2__]
                             (clojure.core/let
                              [?env input__243425_nth_3__]
                              (try
                               [?next]
                               (catch
                                java.lang.Exception
                                e__11829__auto__
                                (if
                                 (meander.runtime.zeta/fail?
                                  e__11829__auto__)
                                 (meander.runtime.zeta/fail)
                                 (throw e__11829__auto__))))))
                            (state__245032)))
                          (state__245032
                           []
                           (clojure.core/loop
                            [search_space__245034
                             (meander.runtime.zeta/epsilon-partitions
                              2
                              input__243425_nth_1__)]
                            (if
                             (clojure.core/seq search_space__245034)
                             (clojure.core/let
                              [input__243425_nth_1___parts__
                               (clojure.core/first
                                search_space__245034)
                               result__245035
                               (clojure.core/let
                                [input__243425_nth_1___l__
                                 (clojure.core/nth
                                  input__243425_nth_1___parts__
                                  0)
                                 input__243425_nth_1___r__
                                 (clojure.core/nth
                                  input__243425_nth_1___parts__
                                  1)]
                                (clojure.core/letfn
                                 [(state__245037
                                   []
                                   (clojure.core/let
                                    [!xs []]
                                    (clojure.core/let
                                     [ret__9750__auto__
                                      (meander.runtime.zeta/epsilon-run-star-1
                                       input__243425_nth_1___l__
                                       [!xs]
                                       (clojure.core/fn
                                        [[!xs] input__243848]
                                        (clojure.core/let
                                         [input__243848_nth_0__
                                          (clojure.core/nth
                                           input__243848
                                           0)]
                                         (clojure.core/letfn
                                          [(save__243849
                                            []
                                            (meander.runtime.zeta/fail))
                                           (f__245041
                                            []
                                            (clojure.core/let
                                             [!xs
                                              (clojure.core/conj
                                               !xs
                                               input__243848_nth_0__)]
                                             [!xs]))]
                                          (if
                                           (clojure.core/map?
                                            input__243848_nth_0__)
                                           (clojure.core/let
                                            [VAL__243850
                                             (.valAt
                                              input__243848_nth_0__
                                              :tag)]
                                            (clojure.core/case
                                             VAL__243850
                                             (:group)
                                             (save__243849)
                                             (f__245041)))
                                           (f__245041)))))
                                       (clojure.core/fn
                                        [[!xs]]
                                        (clojure.core/let
                                         [input__243425_nth_1___r___l__
                                          (clojure.core/subvec
                                           input__243425_nth_1___r__
                                           0
                                           (clojure.core/min
                                            (clojure.core/count
                                             input__243425_nth_1___r__)
                                            1))]
                                         (if
                                          (clojure.core/=
                                           (clojure.core/count
                                            input__243425_nth_1___r___l__)
                                           1)
                                          (clojure.core/let
                                           [input__243425_nth_1___r___r__
                                            (clojure.core/subvec
                                             input__243425_nth_1___r__
                                             1)]
                                           (clojure.core/let
                                            [input__243425_nth_1___r___l___nth_0__
                                             (clojure.core/nth
                                              input__243425_nth_1___r___l__
                                              0)]
                                            (if
                                             (clojure.core/map?
                                              input__243425_nth_1___r___l___nth_0__)
                                             (clojure.core/let
                                              [VAL__243847
                                               (.valAt
                                                input__243425_nth_1___r___l___nth_0__
                                                :tag)]
                                              (clojure.core/case
                                               VAL__243847
                                               (:group)
                                               (clojure.core/let
                                                [?group
                                                 input__243425_nth_1___r___l___nth_0__]
                                                (clojure.core/let
                                                 [?rest
                                                  input__243425_nth_1___r___r__]
                                                 (clojure.core/let
                                                  [?next
                                                   input__243425_nth_2__]
                                                  (clojure.core/let
                                                   [?env
                                                    input__243425_nth_3__]
                                                   (try
                                                    [(clojure.core/let
                                                      [!xs__counter
                                                       (meander.runtime.zeta/iterator
                                                        !xs)]
                                                      (clojure.core/let
                                                       [CATA_RESULT__10889__auto__
                                                        (CATA__FN__243502
                                                         ['meander.dev.parse.zeta/make-join
                                                          (clojure.core/let
                                                           [CATA_RESULT__10889__auto__
                                                            (CATA__FN__243502
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
                                                             CATA_RESULT__10889__auto__)
                                                            (throw
                                                             (meander.runtime.zeta/fail))
                                                            (clojure.core/nth
                                                             CATA_RESULT__10889__auto__
                                                             0)))
                                                          (clojure.core/let
                                                           [CATA_RESULT__10889__auto__
                                                            (CATA__FN__243502
                                                             ['meander.dev.parse.zeta/make-join
                                                              ?group
                                                              (clojure.core/let
                                                               [CATA_RESULT__10889__auto__
                                                                (CATA__FN__243502
                                                                 ['meander.dev.parse.zeta/make-cat
                                                                  ?rest
                                                                  ?next
                                                                  ?env])]
                                                               (if
                                                                (meander.runtime.zeta/fail?
                                                                 CATA_RESULT__10889__auto__)
                                                                (throw
                                                                 (meander.runtime.zeta/fail))
                                                                (clojure.core/nth
                                                                 CATA_RESULT__10889__auto__
                                                                 0)))
                                                              ?env])]
                                                           (if
                                                            (meander.runtime.zeta/fail?
                                                             CATA_RESULT__10889__auto__)
                                                            (throw
                                                             (meander.runtime.zeta/fail))
                                                            (clojure.core/nth
                                                             CATA_RESULT__10889__auto__
                                                             0)))
                                                          ?env])]
                                                       (if
                                                        (meander.runtime.zeta/fail?
                                                         CATA_RESULT__10889__auto__)
                                                        (throw
                                                         (meander.runtime.zeta/fail))
                                                        (clojure.core/nth
                                                         CATA_RESULT__10889__auto__
                                                         0))))]
                                                    (catch
                                                     java.lang.Exception
                                                     e__11829__auto__
                                                     (if
                                                      (meander.runtime.zeta/fail?
                                                       e__11829__auto__)
                                                      (meander.runtime.zeta/fail)
                                                      (throw
                                                       e__11829__auto__))))))))
                                               (state__245038)))
                                             (state__245038))))
                                          (state__245038)))))]
                                     (if
                                      (meander.runtime.zeta/fail?
                                       ret__9750__auto__)
                                      (state__245038)
                                      ret__9750__auto__))))
                                  (state__245038
                                   []
                                   (clojure.core/let
                                    [!xs []]
                                    (clojure.core/let
                                     [ret__9750__auto__
                                      (meander.runtime.zeta/epsilon-run-star-1
                                       input__243425_nth_1___l__
                                       [!xs]
                                       (clojure.core/fn
                                        [[!xs] input__243859]
                                        (clojure.core/let
                                         [input__243859_nth_0__
                                          (clojure.core/nth
                                           input__243859
                                           0)]
                                         (clojure.core/letfn
                                          [(save__243860
                                            []
                                            (meander.runtime.zeta/fail))
                                           (f__245043
                                            []
                                            (clojure.core/let
                                             [!xs
                                              (clojure.core/conj
                                               !xs
                                               input__243859_nth_0__)]
                                             [!xs]))]
                                          (if
                                           (clojure.core/map?
                                            input__243859_nth_0__)
                                           (clojure.core/let
                                            [VAL__243861
                                             (.valAt
                                              input__243859_nth_0__
                                              :tag)]
                                            (clojure.core/case
                                             VAL__243861
                                             (:star)
                                             (save__243860)
                                             (f__245043)))
                                           (f__245043)))))
                                       (clojure.core/fn
                                        [[!xs]]
                                        (clojure.core/let
                                         [input__243425_nth_1___r___l__
                                          (clojure.core/subvec
                                           input__243425_nth_1___r__
                                           0
                                           (clojure.core/min
                                            (clojure.core/count
                                             input__243425_nth_1___r__)
                                            1))]
                                         (if
                                          (clojure.core/=
                                           (clojure.core/count
                                            input__243425_nth_1___r___l__)
                                           1)
                                          (clojure.core/let
                                           [input__243425_nth_1___r___r__
                                            (clojure.core/subvec
                                             input__243425_nth_1___r__
                                             1)]
                                           (clojure.core/let
                                            [input__243425_nth_1___r___l___nth_0__
                                             (clojure.core/nth
                                              input__243425_nth_1___r___l__
                                              0)]
                                            (if
                                             (clojure.core/map?
                                              input__243425_nth_1___r___l___nth_0__)
                                             (clojure.core/let
                                              [VAL__243858
                                               (.valAt
                                                input__243425_nth_1___r___l___nth_0__
                                                :tag)]
                                              (clojure.core/case
                                               VAL__243858
                                               (:star)
                                               (clojure.core/let
                                                [?star
                                                 input__243425_nth_1___r___l___nth_0__]
                                                (clojure.core/let
                                                 [?rest
                                                  input__243425_nth_1___r___r__]
                                                 (clojure.core/let
                                                  [?next
                                                   input__243425_nth_2__]
                                                  (clojure.core/let
                                                   [?env
                                                    input__243425_nth_3__]
                                                   (try
                                                    [(clojure.core/let
                                                      [!xs__counter
                                                       (meander.runtime.zeta/iterator
                                                        !xs)]
                                                      (clojure.core/let
                                                       [CATA_RESULT__10889__auto__
                                                        (CATA__FN__243502
                                                         ['meander.dev.parse.zeta/make-join
                                                          (clojure.core/let
                                                           [CATA_RESULT__10889__auto__
                                                            (CATA__FN__243502
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
                                                             CATA_RESULT__10889__auto__)
                                                            (throw
                                                             (meander.runtime.zeta/fail))
                                                            (clojure.core/nth
                                                             CATA_RESULT__10889__auto__
                                                             0)))
                                                          (clojure.core/let
                                                           [CATA_RESULT__10889__auto__
                                                            (CATA__FN__243502
                                                             ['meander.dev.parse.zeta/make-join
                                                              ?star
                                                              (clojure.core/let
                                                               [CATA_RESULT__10889__auto__
                                                                (CATA__FN__243502
                                                                 ['meander.dev.parse.zeta/make-cat
                                                                  ?rest
                                                                  ?next
                                                                  ?env])]
                                                               (if
                                                                (meander.runtime.zeta/fail?
                                                                 CATA_RESULT__10889__auto__)
                                                                (throw
                                                                 (meander.runtime.zeta/fail))
                                                                (clojure.core/nth
                                                                 CATA_RESULT__10889__auto__
                                                                 0)))
                                                              ?env])]
                                                           (if
                                                            (meander.runtime.zeta/fail?
                                                             CATA_RESULT__10889__auto__)
                                                            (throw
                                                             (meander.runtime.zeta/fail))
                                                            (clojure.core/nth
                                                             CATA_RESULT__10889__auto__
                                                             0)))
                                                          ?env])]
                                                       (if
                                                        (meander.runtime.zeta/fail?
                                                         CATA_RESULT__10889__auto__)
                                                        (throw
                                                         (meander.runtime.zeta/fail))
                                                        (clojure.core/nth
                                                         CATA_RESULT__10889__auto__
                                                         0))))]
                                                    (catch
                                                     java.lang.Exception
                                                     e__11829__auto__
                                                     (if
                                                      (meander.runtime.zeta/fail?
                                                       e__11829__auto__)
                                                      (meander.runtime.zeta/fail)
                                                      (throw
                                                       e__11829__auto__))))))))
                                               (state__245039)))
                                             (state__245039))))
                                          (state__245039)))))]
                                     (if
                                      (meander.runtime.zeta/fail?
                                       ret__9750__auto__)
                                      (state__245039)
                                      ret__9750__auto__))))
                                  (state__245039
                                   []
                                   (clojure.core/let
                                    [input__243425_nth_1___l___l__
                                     (clojure.core/subvec
                                      input__243425_nth_1___l__
                                      0
                                      (clojure.core/min
                                       (clojure.core/count
                                        input__243425_nth_1___l__)
                                       1))]
                                    (if
                                     (clojure.core/=
                                      (clojure.core/count
                                       input__243425_nth_1___l___l__)
                                      1)
                                     (clojure.core/let
                                      [input__243425_nth_1___l___r__
                                       (clojure.core/subvec
                                        input__243425_nth_1___l__
                                        1)]
                                      (clojure.core/let
                                       [input__243425_nth_1___l___l___nth_0__
                                        (clojure.core/nth
                                         input__243425_nth_1___l___l__
                                         0)]
                                       (clojure.core/letfn
                                        [(save__243869
                                          []
                                          (meander.runtime.zeta/fail))
                                         (f__245044
                                          []
                                          (clojure.core/let
                                           [!xs []]
                                           (clojure.core/let
                                            [!xs
                                             (clojure.core/conj
                                              !xs
                                              input__243425_nth_1___l___l___nth_0__)]
                                            (clojure.core/loop
                                             [i__9723__auto__
                                              0
                                              coll__245045
                                              input__243425_nth_1___l___r__
                                              [!xs]
                                              [!xs]]
                                             (clojure.core/let
                                              [input__243874
                                               (clojure.core/subvec
                                                coll__245045
                                                0
                                                (clojure.core/min
                                                 (clojure.core/count
                                                  coll__245045)
                                                 1))]
                                              (if
                                               (clojure.core/=
                                                (clojure.core/count
                                                 input__243874)
                                                1)
                                               (clojure.core/let
                                                [result__9724__auto__
                                                 (clojure.core/let
                                                  [input__243874_nth_0__
                                                   (clojure.core/nth
                                                    input__243874
                                                    0)]
                                                  (clojure.core/letfn
                                                   [(save__243875
                                                     []
                                                     (meander.runtime.zeta/fail))
                                                    (f__245046
                                                     []
                                                     (clojure.core/let
                                                      [!xs
                                                       (clojure.core/conj
                                                        !xs
                                                        input__243874_nth_0__)]
                                                      [!xs]))]
                                                   (if
                                                    (clojure.core/map?
                                                     input__243874_nth_0__)
                                                    (clojure.core/let
                                                     [VAL__243876
                                                      (.valAt
                                                       input__243874_nth_0__
                                                       :tag)]
                                                     (clojure.core/case
                                                      VAL__243876
                                                      (:reference)
                                                      (save__243875)
                                                      (f__245046)))
                                                    (f__245046))))]
                                                (if
                                                 (meander.runtime.zeta/fail?
                                                  result__9724__auto__)
                                                 (meander.runtime.zeta/fail)
                                                 (recur
                                                  (clojure.core/inc
                                                   i__9723__auto__)
                                                  (clojure.core/subvec
                                                   coll__245045
                                                   1)
                                                  result__9724__auto__)))
                                               (if
                                                (clojure.core/or
                                                 (clojure.core/seq
                                                  coll__245045)
                                                 (clojure.core/<
                                                  i__9723__auto__
                                                  0))
                                                (meander.runtime.zeta/fail)
                                                (clojure.core/let
                                                 [input__243425_nth_1___r___l__
                                                  (clojure.core/subvec
                                                   input__243425_nth_1___r__
                                                   0
                                                   (clojure.core/min
                                                    (clojure.core/count
                                                     input__243425_nth_1___r__)
                                                    1))]
                                                 (if
                                                  (clojure.core/=
                                                   (clojure.core/count
                                                    input__243425_nth_1___r___l__)
                                                   1)
                                                  (clojure.core/let
                                                   [input__243425_nth_1___r___r__
                                                    (clojure.core/subvec
                                                     input__243425_nth_1___r__
                                                     1)]
                                                   (clojure.core/let
                                                    [input__243425_nth_1___r___l___nth_0__
                                                     (clojure.core/nth
                                                      input__243425_nth_1___r___l__
                                                      0)]
                                                    (if
                                                     (clojure.core/map?
                                                      input__243425_nth_1___r___l___nth_0__)
                                                     (clojure.core/let
                                                      [VAL__243873
                                                       (.valAt
                                                        input__243425_nth_1___r___l___nth_0__
                                                        :tag)]
                                                      (clojure.core/case
                                                       VAL__243873
                                                       (:reference)
                                                       (clojure.core/let
                                                        [?reference
                                                         input__243425_nth_1___r___l___nth_0__]
                                                        (clojure.core/let
                                                         [?rest
                                                          input__243425_nth_1___r___r__]
                                                         (clojure.core/let
                                                          [?next
                                                           input__243425_nth_2__]
                                                          (clojure.core/let
                                                           [?env
                                                            input__243425_nth_3__]
                                                           (try
                                                            [(clojure.core/let
                                                              [!xs__counter
                                                               (meander.runtime.zeta/iterator
                                                                !xs)]
                                                              (clojure.core/let
                                                               [CATA_RESULT__10889__auto__
                                                                (CATA__FN__243502
                                                                 ['meander.dev.parse.zeta/make-join
                                                                  (clojure.core/let
                                                                   [CATA_RESULT__10889__auto__
                                                                    (CATA__FN__243502
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
                                                                     CATA_RESULT__10889__auto__)
                                                                    (throw
                                                                     (meander.runtime.zeta/fail))
                                                                    (clojure.core/nth
                                                                     CATA_RESULT__10889__auto__
                                                                     0)))
                                                                  (clojure.core/let
                                                                   [CATA_RESULT__10889__auto__
                                                                    (CATA__FN__243502
                                                                     ['meander.dev.parse.zeta/make-join
                                                                      (clojure.core/let
                                                                       [CATA_RESULT__10889__auto__
                                                                        (CATA__FN__243502
                                                                         ['meander.dev.parse.zeta/make-cat
                                                                          [?reference]
                                                                          {:tag
                                                                           :empty}
                                                                          ?env])]
                                                                       (if
                                                                        (meander.runtime.zeta/fail?
                                                                         CATA_RESULT__10889__auto__)
                                                                        (throw
                                                                         (meander.runtime.zeta/fail))
                                                                        (clojure.core/nth
                                                                         CATA_RESULT__10889__auto__
                                                                         0)))
                                                                      (clojure.core/let
                                                                       [CATA_RESULT__10889__auto__
                                                                        (CATA__FN__243502
                                                                         ['meander.dev.parse.zeta/make-cat
                                                                          ?rest
                                                                          ?next
                                                                          ?env])]
                                                                       (if
                                                                        (meander.runtime.zeta/fail?
                                                                         CATA_RESULT__10889__auto__)
                                                                        (throw
                                                                         (meander.runtime.zeta/fail))
                                                                        (clojure.core/nth
                                                                         CATA_RESULT__10889__auto__
                                                                         0)))
                                                                      ?env])]
                                                                   (if
                                                                    (meander.runtime.zeta/fail?
                                                                     CATA_RESULT__10889__auto__)
                                                                    (throw
                                                                     (meander.runtime.zeta/fail))
                                                                    (clojure.core/nth
                                                                     CATA_RESULT__10889__auto__
                                                                     0)))
                                                                  ?env])]
                                                               (if
                                                                (meander.runtime.zeta/fail?
                                                                 CATA_RESULT__10889__auto__)
                                                                (throw
                                                                 (meander.runtime.zeta/fail))
                                                                (clojure.core/nth
                                                                 CATA_RESULT__10889__auto__
                                                                 0))))]
                                                            (catch
                                                             java.lang.Exception
                                                             e__11829__auto__
                                                             (if
                                                              (meander.runtime.zeta/fail?
                                                               e__11829__auto__)
                                                              (meander.runtime.zeta/fail)
                                                              (throw
                                                               e__11829__auto__))))))))
                                                       (meander.runtime.zeta/fail)))
                                                     (meander.runtime.zeta/fail))))
                                                  (meander.runtime.zeta/fail))))))))))]
                                        (if
                                         (clojure.core/map?
                                          input__243425_nth_1___l___l___nth_0__)
                                         (clojure.core/let
                                          [VAL__243870
                                           (.valAt
                                            input__243425_nth_1___l___l___nth_0__
                                            :tag)]
                                          (clojure.core/case
                                           VAL__243870
                                           (:reference)
                                           (save__243869)
                                           (f__245044)))
                                         (f__245044)))))
                                     (meander.runtime.zeta/fail))))]
                                 (state__245037)))]
                              (if
                               (meander.runtime.zeta/fail?
                                result__245035)
                               (recur
                                (clojure.core/next
                                 search_space__245034))
                               result__245035))
                             (state__245033))))
                          (state__245033
                           []
                           (clojure.core/let
                            [input__243425_nth_1___l__
                             (clojure.core/subvec
                              input__243425_nth_1__
                              0
                              (clojure.core/min
                               (clojure.core/count
                                input__243425_nth_1__)
                               1))]
                            (if
                             (clojure.core/=
                              (clojure.core/count
                               input__243425_nth_1___l__)
                              1)
                             (clojure.core/let
                              [input__243425_nth_1___r__
                               (clojure.core/subvec
                                input__243425_nth_1__
                                1)]
                              (clojure.core/let
                               [input__243425_nth_1___l___nth_0__
                                (clojure.core/nth
                                 input__243425_nth_1___l__
                                 0)]
                               (if
                                (clojure.core/map?
                                 input__243425_nth_1___l___nth_0__)
                                (clojure.core/let
                                 [VAL__243882
                                  (.valAt
                                   input__243425_nth_1___l___nth_0__
                                   :tag)]
                                 (clojure.core/case
                                  VAL__243882
                                  (:literal)
                                  (clojure.core/let
                                   [VAL__243883
                                    (.valAt
                                     input__243425_nth_1___l___nth_0__
                                     :type)]
                                   (clojure.core/case
                                    VAL__243883
                                    (:string)
                                    (clojure.core/let
                                     [?ast
                                      input__243425_nth_1___l___nth_0__]
                                     (clojure.core/let
                                      [?rest input__243425_nth_1___r__]
                                      (clojure.core/let
                                       [?next input__243425_nth_2__]
                                       (clojure.core/let
                                        [?env input__243425_nth_3__]
                                        (try
                                         [(clojure.core/let
                                           [CATA_RESULT__10889__auto__
                                            (CATA__FN__243502
                                             ['meander.dev.parse.zeta/make-join
                                              ?ast
                                              (clojure.core/let
                                               [CATA_RESULT__10889__auto__
                                                (CATA__FN__243502
                                                 ['meander.dev.parse.zeta/make-cat
                                                  ?rest
                                                  ?next
                                                  ?env])]
                                               (if
                                                (meander.runtime.zeta/fail?
                                                 CATA_RESULT__10889__auto__)
                                                (throw
                                                 (meander.runtime.zeta/fail))
                                                (clojure.core/nth
                                                 CATA_RESULT__10889__auto__
                                                 0)))
                                              ?env])]
                                           (if
                                            (meander.runtime.zeta/fail?
                                             CATA_RESULT__10889__auto__)
                                            (throw
                                             (meander.runtime.zeta/fail))
                                            (clojure.core/nth
                                             CATA_RESULT__10889__auto__
                                             0)))]
                                         (catch
                                          java.lang.Exception
                                          e__11829__auto__
                                          (if
                                           (meander.runtime.zeta/fail?
                                            e__11829__auto__)
                                           (meander.runtime.zeta/fail)
                                           (throw
                                            e__11829__auto__))))))))
                                    (state__245030)))
                                  (state__245030)))
                                (state__245030))))
                             (state__245030))))]
                         (state__245031))
                        (state__245030)))
                      (state__245030
                       []
                       (clojure.core/let
                        [?sequence input__243425_nth_1__]
                        (clojure.core/let
                         [?next input__243425_nth_2__]
                         (if
                          (clojure.core/map? input__243425_nth_3__)
                          (clojure.core/let
                           [VAL__243886
                            (.valAt input__243425_nth_3__ :context)]
                           (clojure.core/case
                            VAL__243886
                            (:string)
                            (try
                             [{:tag :string-cat,
                               :sequence ?sequence,
                               :next ?next}]
                             (catch
                              java.lang.Exception
                              e__11829__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__11829__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__11829__auto__))))
                            (state__245025)))
                          (state__245025)))))]
                     (state__245029))
                    (state__245025))))
                 (state__245025
                  []
                  (clojure.core/case
                   input__243425_nth_0__
                   (meander.dev.parse.zeta/make-cat)
                   (clojure.core/let
                    [?sequence input__243425_nth_1__]
                    (clojure.core/let
                     [?next input__243425_nth_2__]
                     (try
                      [{:tag :cat, :sequence ?sequence, :next ?next}]
                      (catch
                       java.lang.Exception
                       e__11829__auto__
                       (if
                        (meander.runtime.zeta/fail? e__11829__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__11829__auto__))))))
                   (state__245026)))
                 (state__245026
                  []
                  (clojure.core/let
                   [input__243425_nth_3__
                    (clojure.core/nth input__243425 3)]
                   (clojure.core/case
                    input__243425_nth_0__
                    (meander.dev.parse.zeta/make-join)
                    (if
                     (clojure.core/map? input__243425_nth_1__)
                     (clojure.core/let
                      [VAL__243891 (.valAt input__243425_nth_1__ :tag)]
                      (clojure.core/case
                       VAL__243891
                       (:cat)
                       (clojure.core/let
                        [VAL__243892
                         (.valAt input__243425_nth_1__ :sequence)]
                        (clojure.core/let
                         [?sequence VAL__243892]
                         (clojure.core/let
                          [VAL__243893
                           (.valAt input__243425_nth_1__ :next)]
                          (if
                           (clojure.core/map? VAL__243893)
                           (clojure.core/let
                            [VAL__243894 (.valAt VAL__243893 :tag)]
                            (clojure.core/case
                             VAL__243894
                             (:empty)
                             (clojure.core/let
                              [?right input__243425_nth_2__]
                              (clojure.core/let
                               [?env input__243425_nth_3__]
                               (try
                                [(clojure.core/let
                                  [CATA_RESULT__10889__auto__
                                   (CATA__FN__243502
                                    ['meander.dev.parse.zeta/make-cat
                                     ?sequence
                                     ?right
                                     ?env])]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    CATA_RESULT__10889__auto__)
                                   (throw (meander.runtime.zeta/fail))
                                   (clojure.core/nth
                                    CATA_RESULT__10889__auto__
                                    0)))]
                                (catch
                                 java.lang.Exception
                                 e__11829__auto__
                                 (if
                                  (meander.runtime.zeta/fail?
                                   e__11829__auto__)
                                  (meander.runtime.zeta/fail)
                                  (throw e__11829__auto__))))))
                             (state__245027)))
                           (state__245027)))))
                       (state__245027)))
                     (state__245027))
                    (state__245027))))
                 (state__245027
                  []
                  (clojure.core/case
                   input__243425_nth_0__
                   (meander.dev.parse.zeta/make-join)
                   (if
                    (clojure.core/map? input__243425_nth_1__)
                    (clojure.core/let
                     [VAL__244917 (.valAt input__243425_nth_1__ :tag)]
                     (clojure.core/case
                      VAL__244917
                      (:cat)
                      (clojure.core/let
                       [?ast input__243425_nth_1__]
                       (if
                        (clojure.core/map? input__243425_nth_2__)
                        (clojure.core/let
                         [VAL__243898
                          (.valAt input__243425_nth_2__ :tag)]
                         (clojure.core/case
                          VAL__243898
                          (:cat)
                          (clojure.core/let
                           [VAL__243899
                            (.valAt input__243425_nth_2__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__243899]
                            (clojure.core/let
                             [VAL__243900
                              (.valAt input__243425_nth_2__ :next)]
                             (clojure.core/let
                              [?next VAL__243900]
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
                                e__11829__auto__
                                (if
                                 (meander.runtime.zeta/fail?
                                  e__11829__auto__)
                                 (meander.runtime.zeta/fail)
                                 (throw e__11829__auto__))))))))
                          (state__245028)))
                        (state__245028)))
                      (:string-cat)
                      (clojure.core/let
                       [?ast input__243425_nth_1__]
                       (if
                        (clojure.core/map? input__243425_nth_2__)
                        (clojure.core/let
                         [VAL__243904
                          (.valAt input__243425_nth_2__ :tag)]
                         (clojure.core/case
                          VAL__243904
                          (:string-cat)
                          (clojure.core/let
                           [VAL__243905
                            (.valAt input__243425_nth_2__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__243905]
                            (clojure.core/let
                             [VAL__243906
                              (.valAt input__243425_nth_2__ :next)]
                             (clojure.core/let
                              [?next VAL__243906]
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
                                e__11829__auto__
                                (if
                                 (meander.runtime.zeta/fail?
                                  e__11829__auto__)
                                 (meander.runtime.zeta/fail)
                                 (throw e__11829__auto__))))))))
                          (state__245028)))
                        (state__245028)))
                      (state__245028)))
                    (state__245028))
                   (state__245028)))
                 (state__245028
                  []
                  (clojure.core/let
                   [input__243425_nth_3__
                    (clojure.core/nth input__243425 3)]
                   (clojure.core/case
                    input__243425_nth_0__
                    (meander.dev.parse.zeta/make-join)
                    (clojure.core/letfn
                     [(state__245047
                       []
                       (if
                        (clojure.core/map? input__243425_nth_1__)
                        (clojure.core/let
                         [VAL__244920
                          (.valAt input__243425_nth_1__ :next)
                          VAL__244919
                          (.valAt input__243425_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__244919
                          (:string-cat)
                          (clojure.core/let
                           [VAL__243910
                            (.valAt input__243425_nth_1__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__243910]
                            (if
                             (clojure.core/map? VAL__244920)
                             (clojure.core/let
                              [VAL__243912 (.valAt VAL__244920 :tag)]
                              (clojure.core/case
                               VAL__243912
                               (:empty)
                               (clojure.core/let
                                [?right input__243425_nth_2__]
                                (clojure.core/let
                                 [?env input__243425_nth_3__]
                                 (try
                                  [(clojure.core/let
                                    [CATA_RESULT__10889__auto__
                                     (CATA__FN__243502
                                      ['meander.dev.parse.zeta/make-join
                                       ?sequence
                                       ?right
                                       ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__10889__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__10889__auto__
                                      0)))]
                                  (catch
                                   java.lang.Exception
                                   e__11829__auto__
                                   (if
                                    (meander.runtime.zeta/fail?
                                     e__11829__auto__)
                                    (meander.runtime.zeta/fail)
                                    (throw e__11829__auto__))))))
                               (state__245048)))
                             (state__245048))))
                          (:string-star)
                          (clojure.core/let
                           [VAL__243916
                            (.valAt input__243425_nth_1__ :pattern)]
                           (clojure.core/let
                            [?pattern VAL__243916]
                            (if
                             (clojure.core/map? VAL__244920)
                             (clojure.core/let
                              [VAL__243918 (.valAt VAL__244920 :tag)]
                              (clojure.core/case
                               VAL__243918
                               (:empty)
                               (clojure.core/let
                                [?right input__243425_nth_2__]
                                (if
                                 (clojure.core/map?
                                  input__243425_nth_3__)
                                 (clojure.core/let
                                  [VAL__243919
                                   (.valAt
                                    input__243425_nth_3__
                                    :context)]
                                  (clojure.core/case
                                   VAL__243919
                                   (:string)
                                   (try
                                    [{:tag :string-star,
                                      :pattern ?pattern,
                                      :next ?right}]
                                    (catch
                                     java.lang.Exception
                                     e__11829__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__11829__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__11829__auto__))))
                                   (state__245048)))
                                 (state__245048)))
                               (state__245048)))
                             (state__245048))))
                          (:string-join)
                          (clojure.core/let
                           [VAL__243923
                            (.valAt input__243425_nth_1__ :left)]
                           (clojure.core/let
                            [?left VAL__243923]
                            (clojure.core/let
                             [VAL__243924
                              (.valAt input__243425_nth_1__ :right)]
                             (clojure.core/let
                              [?right-1 VAL__243924]
                              (clojure.core/let
                               [?right-2 input__243425_nth_2__]
                               (if
                                (clojure.core/map?
                                 input__243425_nth_3__)
                                (clojure.core/let
                                 [VAL__243925
                                  (.valAt
                                   input__243425_nth_3__
                                   :context)]
                                 (clojure.core/case
                                  VAL__243925
                                  (:string)
                                  (clojure.core/let
                                   [?env input__243425_nth_3__]
                                   (try
                                    [{:tag :string-join,
                                      :left ?left,
                                      :right
                                      (clojure.core/let
                                       [CATA_RESULT__10889__auto__
                                        (CATA__FN__243502
                                         ['meander.dev.parse.zeta/make-join
                                          ?right-1
                                          ?right-2
                                          ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__10889__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__10889__auto__
                                         0)))}]
                                    (catch
                                     java.lang.Exception
                                     e__11829__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__11829__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__11829__auto__)))))
                                  (state__245048)))
                                (state__245048)))))))
                          (state__245048)))
                        (state__245048)))
                      (state__245048
                       []
                       (clojure.core/let
                        [?left input__243425_nth_1__]
                        (if
                         (clojure.core/map? input__243425_nth_2__)
                         (clojure.core/let
                          [VAL__243928
                           (.valAt input__243425_nth_2__ :tag)]
                          (clojure.core/case
                           VAL__243928
                           (:empty)
                           (clojure.core/let
                            [?env input__243425_nth_3__]
                            (try
                             [?left]
                             (catch
                              java.lang.Exception
                              e__11829__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__11829__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__11829__auto__)))))
                           (state__245049)))
                         (state__245049))))
                      (state__245049
                       []
                       (if
                        (clojure.core/map? input__243425_nth_1__)
                        (clojure.core/let
                         [VAL__244918
                          (.valAt input__243425_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__244918
                          (:empty)
                          (clojure.core/let
                           [?right input__243425_nth_2__]
                           (clojure.core/let
                            [?env input__243425_nth_3__]
                            (try
                             [?right]
                             (catch
                              java.lang.Exception
                              e__11829__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__11829__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__11829__auto__))))))
                          (:star)
                          (clojure.core/let
                           [VAL__243935
                            (.valAt input__243425_nth_1__ :next)]
                           (if
                            (clojure.core/map? VAL__243935)
                            (clojure.core/let
                             [VAL__243936 (.valAt VAL__243935 :tag)]
                             (clojure.core/case
                              VAL__243936
                              (:empty)
                              (clojure.core/let
                               [?left input__243425_nth_1__]
                               (clojure.core/let
                                [?right input__243425_nth_2__]
                                (clojure.core/let
                                 [?env input__243425_nth_3__]
                                 (try
                                  [(clojure.core/let
                                    [form__10988__auto__
                                     {:tag :star, :next ?right}]
                                    (clojure.core/merge
                                     (clojure.core/into {} ?left)
                                     form__10988__auto__))]
                                  (catch
                                   java.lang.Exception
                                   e__11829__auto__
                                   (if
                                    (meander.runtime.zeta/fail?
                                     e__11829__auto__)
                                    (meander.runtime.zeta/fail)
                                    (throw e__11829__auto__)))))))
                              (state__245050)))
                            (state__245050)))
                          (state__245050)))
                        (state__245050)))
                      (state__245050
                       []
                       (clojure.core/let
                        [?left input__243425_nth_1__]
                        (clojure.core/let
                         [?right input__243425_nth_2__]
                         (clojure.core/letfn
                          [(state__245051
                            []
                            (if
                             (clojure.core/map? input__243425_nth_3__)
                             (clojure.core/let
                              [VAL__243939
                               (.valAt input__243425_nth_3__ :context)]
                              (clojure.core/case
                               VAL__243939
                               (:string)
                               (try
                                [{:tag :string-join,
                                  :left ?left,
                                  :right ?right}]
                                (catch
                                 java.lang.Exception
                                 e__11829__auto__
                                 (if
                                  (meander.runtime.zeta/fail?
                                   e__11829__auto__)
                                  (meander.runtime.zeta/fail)
                                  (throw e__11829__auto__))))
                               (state__245052)))
                             (state__245052)))
                           (state__245052
                            []
                            (clojure.core/let
                             [?env input__243425_nth_3__]
                             (try
                              [{:tag :join,
                                :left ?left,
                                :right ?right}]
                              (catch
                               java.lang.Exception
                               e__11829__auto__
                               (if
                                (meander.runtime.zeta/fail?
                                 e__11829__auto__)
                                (meander.runtime.zeta/fail)
                                (throw e__11829__auto__))))))]
                          (state__245051)))))]
                     (state__245047))
                    (state__245023))))]
                (state__245024)))
              (state__245023)))
            (state__245023
             []
             (if
              (clojure.core/= (clojure.core/count input__243425) 3)
              (clojure.core/let
               [input__243425_nth_0__
                (clojure.core/nth input__243425 0)
                input__243425_nth_1__
                (clojure.core/nth input__243425 1)
                input__243425_nth_2__
                (clojure.core/nth input__243425 2)]
               (clojure.core/case
                input__243425_nth_0__
                (meander.dev.parse.zeta/parse-entries)
                (if
                 (clojure.core/map? input__243425_nth_1__)
                 (clojure.core/let
                  [VAL__243944
                   (.valAt input__243425_nth_1__ :meander.zeta/as)]
                  (clojure.core/let
                   [?pattern VAL__243944]
                   (clojure.core/let
                    [X__243946
                     ((clojure.core/fn
                       [m__8653__auto__]
                       (clojure.core/dissoc
                        m__8653__auto__
                        :meander.zeta/as))
                      input__243425_nth_1__)]
                    (clojure.core/let
                     [?rest X__243946]
                     (clojure.core/letfn
                      [(save__243947 [] (state__244931))
                       (f__245053
                        []
                        (clojure.core/let
                         [?env input__243425_nth_2__]
                         (try
                          [{:tag :as,
                            :pattern
                            (clojure.core/let
                             [CATA_RESULT__10889__auto__
                              (CATA__FN__243502 [?pattern ?env])]
                             (if
                              (meander.runtime.zeta/fail?
                               CATA_RESULT__10889__auto__)
                              (throw (meander.runtime.zeta/fail))
                              (clojure.core/nth
                               CATA_RESULT__10889__auto__
                               0))),
                            :next
                            (clojure.core/let
                             [CATA_RESULT__10889__auto__
                              (CATA__FN__243502 [?rest ?env])]
                             (if
                              (meander.runtime.zeta/fail?
                               CATA_RESULT__10889__auto__)
                              (throw (meander.runtime.zeta/fail))
                              (clojure.core/nth
                               CATA_RESULT__10889__auto__
                               0)))}]
                          (catch
                           java.lang.Exception
                           e__11829__auto__
                           (if
                            (meander.runtime.zeta/fail?
                             e__11829__auto__)
                            (meander.runtime.zeta/fail)
                            (throw e__11829__auto__))))))]
                      (if
                       (clojure.core/= ?rest input__243425_nth_1__)
                       (save__243947)
                       (f__245053)))))))
                 (state__244931))
                (state__244931)))
              (state__244931)))]
           (state__245021))
          (state__244931)))
        (state__244931
         []
         (clojure.core/letfn
          [(def__243950
            [arg__243983 ?ns]
            (clojure.core/letfn
             [(state__245054
               []
               (clojure.core/let
                [x__243984 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__243984)
                 (clojure.core/let [?env arg__243983] [?env ?ns])
                 (state__245055))))
              (state__245055
               []
               (if
                (clojure.core/map? arg__243983)
                (clojure.core/let
                 [VAL__243985 (.valAt arg__243983 :aliases)]
                 (if
                  (clojure.core/map? VAL__243985)
                  (clojure.core/let
                   [X__243987 (clojure.core/set VAL__243985)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__243987))
                    (clojure.core/loop
                     [search_space__245056
                      (clojure.core/seq X__243987)]
                     (if
                      (clojure.core/seq search_space__245056)
                      (clojure.core/let
                       [elem__243988
                        (clojure.core/first search_space__245056)
                        result__245057
                        (clojure.core/let
                         [elem__243988_nth_0__
                          (clojure.core/nth elem__243988 0)
                          elem__243988_nth_1__
                          (clojure.core/nth elem__243988 1)]
                         (if
                          (clojure.core/symbol? elem__243988_nth_0__)
                          (clojure.core/let
                           [X__243990
                            (clojure.core/name elem__243988_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__243990)
                            (if
                             (clojure.core/symbol?
                              elem__243988_nth_1__)
                             (clojure.core/let
                              [X__243992
                               (clojure.core/name
                                elem__243988_nth_1__)]
                              (clojure.core/case
                               X__243992
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__243983]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__245057)
                        (recur
                         (clojure.core/next search_space__245056))
                        result__245057))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__245054)))]
          (if
           (clojure.core/vector? input__243425)
           (if
            (clojure.core/= (clojure.core/count input__243425) 3)
            (clojure.core/let
             [input__243425_nth_0__
              (clojure.core/nth input__243425 0)
              input__243425_nth_1__
              (clojure.core/nth input__243425 1)
              input__243425_nth_2__
              (clojure.core/nth input__243425 2)]
             (clojure.core/case
              input__243425_nth_0__
              (meander.dev.parse.zeta/parse-entries)
              (if
               (clojure.core/map? input__243425_nth_1__)
               (clojure.core/let
                [X__243955 (clojure.core/set input__243425_nth_1__)]
                (if
                 (clojure.core/<= 1 (clojure.core/count X__243955))
                 (clojure.core/loop
                  [search_space__245059 (clojure.core/seq X__243955)]
                  (if
                   (clojure.core/seq search_space__245059)
                   (clojure.core/let
                    [elem__243956
                     (clojure.core/first search_space__245059)
                     result__245060
                     (clojure.core/let
                      [elem__243956_nth_0__
                       (clojure.core/nth elem__243956 0)
                       elem__243956_nth_1__
                       (clojure.core/nth elem__243956 1)]
                      (clojure.core/let
                       [*m__243457 elem__243956_nth_0__]
                       (if
                        (clojure.core/symbol? elem__243956_nth_0__)
                        (clojure.core/let
                         [X__243958
                          (clojure.core/namespace
                           elem__243956_nth_0__)]
                         (clojure.core/let
                          [?ns X__243958]
                          (clojure.core/let
                           [X__243960
                            (clojure.core/name elem__243956_nth_0__)]
                           (if
                            (clojure.core/string? X__243960)
                            (if
                             (clojure.core/re-matches #"&.*" X__243960)
                             (clojure.core/let
                              [?pattern elem__243956_nth_1__]
                              (clojure.core/let
                               [X__243962
                                ((clojure.core/fn
                                  [m__8653__auto__]
                                  (clojure.core/dissoc
                                   m__8653__auto__
                                   *m__243457))
                                 input__243425_nth_1__)]
                               (clojure.core/let
                                [?rest X__243962]
                                (clojure.core/let
                                 [x__9586__auto__
                                  (def__243950
                                   input__243425_nth_2__
                                   ?ns)]
                                 (if
                                  (meander.runtime.zeta/fail?
                                   x__9586__auto__)
                                  (meander.runtime.zeta/fail)
                                  (clojure.core/let
                                   [[?env ?ns] x__9586__auto__]
                                   (try
                                    [{:tag :rest-map,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__10889__auto__
                                        (CATA__FN__243502
                                         [?pattern ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__10889__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__10889__auto__
                                         0))),
                                      :next
                                      (clojure.core/let
                                       [CATA_RESULT__10889__auto__
                                        (CATA__FN__243502
                                         ['meander.dev.parse.zeta/parse-entries
                                          ?rest
                                          ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__10889__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__10889__auto__
                                         0)))}]
                                    (catch
                                     java.lang.Exception
                                     e__11829__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__11829__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__11829__auto__))))))))))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))))
                        (meander.runtime.zeta/fail))))]
                    (if
                     (meander.runtime.zeta/fail? result__245060)
                     (recur (clojure.core/next search_space__245059))
                     result__245060))
                   (state__244932)))
                 (state__244932)))
               (state__244932))
              (state__244932)))
            (state__244932))
           (state__244932))))
        (state__244932
         []
         (if
          (clojure.core/vector? input__243425)
          (clojure.core/letfn
           [(state__245062
             []
             (if
              (clojure.core/= (clojure.core/count input__243425) 3)
              (clojure.core/let
               [input__243425_nth_0__
                (clojure.core/nth input__243425 0)
                input__243425_nth_1__
                (clojure.core/nth input__243425 1)
                input__243425_nth_2__
                (clojure.core/nth input__243425 2)]
               (clojure.core/case
                input__243425_nth_0__
                (meander.dev.parse.zeta/parse-entries)
                (clojure.core/letfn
                 [(state__245064
                   []
                   (if
                    (clojure.core/map? input__243425_nth_1__)
                    (clojure.core/let
                     [X__244006
                      (clojure.core/set input__243425_nth_1__)]
                     (if
                      (clojure.core/<=
                       1
                       (clojure.core/count X__244006))
                      (clojure.core/loop
                       [search_space__245066
                        (clojure.core/seq X__244006)]
                       (if
                        (clojure.core/seq search_space__245066)
                        (clojure.core/let
                         [elem__244007
                          (clojure.core/first search_space__245066)
                          result__245067
                          (clojure.core/let
                           [elem__244007_nth_0__
                            (clojure.core/nth elem__244007 0)
                            elem__244007_nth_1__
                            (clojure.core/nth elem__244007 1)]
                           (clojure.core/let
                            [?key-pattern elem__244007_nth_0__]
                            (clojure.core/let
                             [?val-pattern elem__244007_nth_1__]
                             (clojure.core/let
                              [X__244009
                               ((clojure.core/fn
                                 [m__8653__auto__]
                                 (clojure.core/dissoc
                                  m__8653__auto__
                                  ?key-pattern))
                                input__243425_nth_1__)]
                              (clojure.core/let
                               [?rest X__244009]
                               (clojure.core/let
                                [?env input__243425_nth_2__]
                                (try
                                 [{:tag :entry,
                                   :key-pattern
                                   (clojure.core/let
                                    [CATA_RESULT__10889__auto__
                                     (CATA__FN__243502
                                      [?key-pattern ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__10889__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__10889__auto__
                                      0))),
                                   :val-pattern
                                   (clojure.core/let
                                    [CATA_RESULT__10889__auto__
                                     (CATA__FN__243502
                                      [?val-pattern ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__10889__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__10889__auto__
                                      0))),
                                   :next
                                   (clojure.core/let
                                    [CATA_RESULT__10889__auto__
                                     (CATA__FN__243502
                                      ['meander.dev.parse.zeta/parse-entries
                                       ?rest
                                       ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__10889__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__10889__auto__
                                      0)))}]
                                 (catch
                                  java.lang.Exception
                                  e__11829__auto__
                                  (if
                                   (meander.runtime.zeta/fail?
                                    e__11829__auto__)
                                   (meander.runtime.zeta/fail)
                                   (throw e__11829__auto__))))))))))]
                         (if
                          (meander.runtime.zeta/fail? result__245067)
                          (recur
                           (clojure.core/next search_space__245066))
                          result__245067))
                        (state__245065)))
                      (state__245065)))
                    (state__245065)))
                  (state__245065
                   []
                   (if
                    (clojure.core/map? input__243425_nth_1__)
                    (clojure.core/let
                     [?env input__243425_nth_2__]
                     (try
                      [{:tag :some-map}]
                      (catch
                       java.lang.Exception
                       e__11829__auto__
                       (if
                        (meander.runtime.zeta/fail? e__11829__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__11829__auto__)))))
                    (state__245063)))]
                 (state__245064))
                (meander.dev.parse.zeta/parse-with-bindings)
                (clojure.core/letfn
                 [(state__245069
                   []
                   (if
                    (clojure.core/vector? input__243425_nth_1__)
                    (clojure.core/case
                     input__243425_nth_1__
                     ([])
                     (clojure.core/let
                      [?env input__243425_nth_2__]
                      (try
                       [[]]
                       (catch
                        java.lang.Exception
                        e__11829__auto__
                        (if
                         (meander.runtime.zeta/fail? e__11829__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__11829__auto__)))))
                     (state__245070))
                    (state__245070)))
                  (state__245070
                   []
                   (if
                    (clojure.core/vector? input__243425_nth_1__)
                    (if
                     (clojure.core/=
                      (clojure.core/count input__243425_nth_1__)
                      1)
                     (clojure.core/let
                      [?env input__243425_nth_2__]
                      (try
                       [[{:tag :error,
                          :message
                          "meander.zeta/with expects an even number of bindings"}]]
                       (catch
                        java.lang.Exception
                        e__11829__auto__
                        (if
                         (meander.runtime.zeta/fail? e__11829__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__11829__auto__)))))
                     (state__245071))
                    (state__245071)))
                  (state__245071
                   []
                   (if
                    (clojure.core/vector? input__243425_nth_1__)
                    (clojure.core/let
                     [input__243425_nth_1___l__
                      (clojure.core/subvec
                       input__243425_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__243425_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__243425_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__243425_nth_1___r__
                        (clojure.core/subvec input__243425_nth_1__ 2)]
                       (clojure.core/let
                        [input__243425_nth_1___l___nth_0__
                         (clojure.core/nth input__243425_nth_1___l__ 0)
                         input__243425_nth_1___l___nth_1__
                         (clojure.core/nth
                          input__243425_nth_1___l__
                          1)]
                        (if
                         (clojure.core/symbol?
                          input__243425_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__244023
                           (clojure.core/namespace
                            input__243425_nth_1___l___nth_0__)]
                          (clojure.core/let
                           [X__244025
                            (clojure.core/name
                             input__243425_nth_1___l___nth_0__)]
                           (if
                            (clojure.core/string? X__244025)
                            (if
                             (clojure.core/re-matches #"%.+" X__244025)
                             (clojure.core/let
                              [?symbol
                               input__243425_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?pattern
                                input__243425_nth_1___l___nth_1__]
                               (clojure.core/let
                                [?rest input__243425_nth_1___r__]
                                (clojure.core/let
                                 [?env input__243425_nth_2__]
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
                                        [CATA_RESULT__10889__auto__
                                         (CATA__FN__243502
                                          [?pattern ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10889__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10889__auto__
                                          0)))})
                                     (clojure.core/let
                                      [CATA_RESULT__10889__auto__
                                       (CATA__FN__243502
                                        ['meander.dev.parse.zeta/parse-with-bindings
                                         ?rest
                                         ?env])]
                                      (if
                                       (meander.runtime.zeta/fail?
                                        CATA_RESULT__10889__auto__)
                                       (throw
                                        (meander.runtime.zeta/fail))
                                       (clojure.core/nth
                                        CATA_RESULT__10889__auto__
                                        0)))))]
                                  (catch
                                   java.lang.Exception
                                   e__11829__auto__
                                   (if
                                    (meander.runtime.zeta/fail?
                                     e__11829__auto__)
                                    (meander.runtime.zeta/fail)
                                    (throw e__11829__auto__))))))))
                             (state__245072))
                            (state__245072))))
                         (state__245072))))
                      (state__245072)))
                    (state__245072)))
                  (state__245072
                   []
                   (if
                    (clojure.core/vector? input__243425_nth_1__)
                    (clojure.core/let
                     [input__243425_nth_1___l__
                      (clojure.core/subvec
                       input__243425_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__243425_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__243425_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__243425_nth_1___r__
                        (clojure.core/subvec input__243425_nth_1__ 2)]
                       (clojure.core/let
                        [input__243425_nth_1___l___nth_0__
                         (clojure.core/nth input__243425_nth_1___l__ 0)
                         input__243425_nth_1___l___nth_1__
                         (clojure.core/nth
                          input__243425_nth_1___l__
                          1)]
                        (clojure.core/let
                         [?x input__243425_nth_1___l___nth_0__]
                         (clojure.core/let
                          [?pattern input__243425_nth_1___l___nth_1__]
                          (clojure.core/let
                           [?rest input__243425_nth_1___r__]
                           (clojure.core/let
                            [?env input__243425_nth_2__]
                            (try
                             [[{:tag :error,
                                :message
                                "meander.zeta/with bindings must be an repeating sequence of %name pattern"}]]
                             (catch
                              java.lang.Exception
                              e__11829__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__11829__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__11829__auto__))))))))))
                      (state__245063)))
                    (state__245063)))]
                 (state__245069))
                (state__245063)))
              (state__245063)))
            (state__245063
             []
             (if
              (clojure.core/= (clojure.core/count input__243425) 2)
              (clojure.core/let
               [input__243425_nth_0__
                (clojure.core/nth input__243425 0)
                input__243425_nth_1__
                (clojure.core/nth input__243425 1)]
               (if
                (clojure.core/vector? input__243425_nth_0__)
                (clojure.core/let
                 [?sequence input__243425_nth_0__]
                 (clojure.core/let
                  [?form input__243425_nth_0__]
                  (clojure.core/let
                   [?env input__243425_nth_1__]
                   (try
                    [{:tag :vector,
                      :next
                      (clojure.core/let
                       [CATA_RESULT__10889__auto__
                        (CATA__FN__243502
                         ['meander.dev.parse.zeta/parse-sequential
                          ?sequence
                          (clojure.core/let
                           [form__10988__auto__ {:context :vector}]
                           (clojure.core/merge
                            (clojure.core/into {} ?env)
                            form__10988__auto__))])]
                       (if
                        (meander.runtime.zeta/fail?
                         CATA_RESULT__10889__auto__)
                        (throw (meander.runtime.zeta/fail))
                        (clojure.core/nth
                         CATA_RESULT__10889__auto__
                         0))),
                      :form ?form}]
                    (catch
                     java.lang.Exception
                     e__11829__auto__
                     (if
                      (meander.runtime.zeta/fail? e__11829__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__11829__auto__)))))))
                (state__244933)))
              (state__244933)))]
           (state__245062))
          (state__244933)))
        (state__244933
         []
         (clojure.core/letfn
          [(def__244035
            [arg__244058 ?__243426]
            (clojure.core/letfn
             [(state__245073
               []
               (clojure.core/let
                [x__244059 "clojure.core"]
                (if
                 (clojure.core/= ?__243426 x__244059)
                 [?__243426]
                 (state__245074))))
              (state__245074
               []
               (if
                (clojure.core/map? arg__244058)
                (clojure.core/let
                 [VAL__244060 (.valAt arg__244058 :aliases)]
                 (if
                  (clojure.core/map? VAL__244060)
                  (clojure.core/let
                   [X__244062 (clojure.core/set VAL__244060)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__244062))
                    (clojure.core/loop
                     [search_space__245075
                      (clojure.core/seq X__244062)]
                     (if
                      (clojure.core/seq search_space__245075)
                      (clojure.core/let
                       [elem__244063
                        (clojure.core/first search_space__245075)
                        result__245076
                        (clojure.core/let
                         [elem__244063_nth_0__
                          (clojure.core/nth elem__244063 0)
                          elem__244063_nth_1__
                          (clojure.core/nth elem__244063 1)]
                         (if
                          (clojure.core/symbol? elem__244063_nth_0__)
                          (clojure.core/let
                           [X__244065
                            (clojure.core/name elem__244063_nth_0__)]
                           (if
                            (clojure.core/= ?__243426 X__244065)
                            (if
                             (clojure.core/symbol?
                              elem__244063_nth_1__)
                             (clojure.core/let
                              [X__244067
                               (clojure.core/name
                                elem__244063_nth_1__)]
                              (clojure.core/case
                               X__244067
                               ("clojure.core")
                               [?__243426]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__245076)
                        (recur
                         (clojure.core/next search_space__245075))
                        result__245076))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__245073)))]
          (if
           (clojure.core/vector? input__243425)
           (if
            (clojure.core/= (clojure.core/count input__243425) 2)
            (clojure.core/let
             [input__243425_nth_0__
              (clojure.core/nth input__243425 0)
              input__243425_nth_1__
              (clojure.core/nth input__243425 1)]
             (if
              (clojure.core/seq? input__243425_nth_0__)
              (clojure.core/let
               [input__243425_nth_0___l__
                (clojure.core/take 1 input__243425_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__243425_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__243425_nth_0___r__
                  (clojure.core/drop 1 input__243425_nth_0__)]
                 (clojure.core/let
                  [input__243425_nth_0___l___nth_0__
                   (clojure.core/nth input__243425_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__243425_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__244045
                     (clojure.core/namespace
                      input__243425_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__243426 X__244045]
                     (clojure.core/let
                      [X__244047
                       (clojure.core/name
                        input__243425_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__244047
                       ("unquote")
                       (clojure.core/let
                        [x__9586__auto__
                         (def__244035 input__243425_nth_1__ ?__243426)]
                        (if
                         (meander.runtime.zeta/fail? x__9586__auto__)
                         (state__244934)
                         (clojure.core/let
                          [[?__243426] x__9586__auto__]
                          (if
                           (clojure.core/vector? input__243425)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__243425)
                             2)
                            (clojure.core/let
                             [input__243425_nth_0__
                              (clojure.core/nth input__243425 0)
                              input__243425_nth_1__
                              (clojure.core/nth input__243425 1)]
                             (if
                              (clojure.core/seq? input__243425_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__243425_nth_0__)
                                2)
                               (clojure.core/let
                                [input__243425_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__243425_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?x input__243425_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__243425_nth_0__]
                                  (clojure.core/let
                                   [?env input__243425_nth_1__]
                                   (try
                                    [{:tag :host-expression,
                                      :expression ?x,
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__11829__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__11829__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__11829__auto__))))))))
                               (state__244934))
                              (state__244934)))
                            (state__244934))
                           (state__244934)))))
                       (state__244934)))))
                   (state__244934))))
                (state__244934)))
              (state__244934)))
            (state__244934))
           (state__244934))))
        (state__244934
         []
         (clojure.core/letfn
          [(def__244069
            [arg__244092 ?__243427]
            (clojure.core/letfn
             [(state__245078
               []
               (clojure.core/let
                [x__244093 "meander.zeta"]
                (if
                 (clojure.core/= ?__243427 x__244093)
                 [?__243427]
                 (state__245079))))
              (state__245079
               []
               (if
                (clojure.core/map? arg__244092)
                (clojure.core/let
                 [VAL__244094 (.valAt arg__244092 :aliases)]
                 (if
                  (clojure.core/map? VAL__244094)
                  (clojure.core/let
                   [X__244096 (clojure.core/set VAL__244094)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__244096))
                    (clojure.core/loop
                     [search_space__245080
                      (clojure.core/seq X__244096)]
                     (if
                      (clojure.core/seq search_space__245080)
                      (clojure.core/let
                       [elem__244097
                        (clojure.core/first search_space__245080)
                        result__245081
                        (clojure.core/let
                         [elem__244097_nth_0__
                          (clojure.core/nth elem__244097 0)
                          elem__244097_nth_1__
                          (clojure.core/nth elem__244097 1)]
                         (if
                          (clojure.core/symbol? elem__244097_nth_0__)
                          (clojure.core/let
                           [X__244099
                            (clojure.core/name elem__244097_nth_0__)]
                           (if
                            (clojure.core/= ?__243427 X__244099)
                            (if
                             (clojure.core/symbol?
                              elem__244097_nth_1__)
                             (clojure.core/let
                              [X__244101
                               (clojure.core/name
                                elem__244097_nth_1__)]
                              (clojure.core/case
                               X__244101
                               ("meander.zeta")
                               [?__243427]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__245081)
                        (recur
                         (clojure.core/next search_space__245080))
                        result__245081))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__245078)))]
          (if
           (clojure.core/vector? input__243425)
           (if
            (clojure.core/= (clojure.core/count input__243425) 2)
            (clojure.core/let
             [input__243425_nth_0__
              (clojure.core/nth input__243425 0)
              input__243425_nth_1__
              (clojure.core/nth input__243425 1)]
             (if
              (clojure.core/seq? input__243425_nth_0__)
              (clojure.core/let
               [input__243425_nth_0___l__
                (clojure.core/take 1 input__243425_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__243425_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__243425_nth_0___r__
                  (clojure.core/drop 1 input__243425_nth_0__)]
                 (clojure.core/let
                  [input__243425_nth_0___l___nth_0__
                   (clojure.core/nth input__243425_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__243425_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__244079
                     (clojure.core/namespace
                      input__243425_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__243427 X__244079]
                     (clojure.core/let
                      [X__244081
                       (clojure.core/name
                        input__243425_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__244081
                       ("*")
                       (clojure.core/let
                        [x__9586__auto__
                         (def__244069 input__243425_nth_1__ ?__243427)]
                        (if
                         (meander.runtime.zeta/fail? x__9586__auto__)
                         (state__244935)
                         (clojure.core/let
                          [[?__243427] x__9586__auto__]
                          (if
                           (clojure.core/vector? input__243425)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__243425)
                             2)
                            (clojure.core/let
                             [input__243425_nth_0__
                              (clojure.core/nth input__243425 0)
                              input__243425_nth_1__
                              (clojure.core/nth input__243425 1)]
                             (if
                              (clojure.core/seq? input__243425_nth_0__)
                              (clojure.core/let
                               [input__243425_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__243425_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__243425_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__243425_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__243425_nth_0__)]
                                 (clojure.core/let
                                  [?patterns input__243425_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__243425_nth_0__]
                                   (clojure.core/let
                                    [?env input__243425_nth_1__]
                                    (try
                                     [{:tag :star,
                                       :greedy? true,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__10889__auto__
                                         (CATA__FN__243502
                                          ['meander.dev.parse.zeta/parse-sequential
                                           (clojure.core/into
                                            []
                                            ?patterns)
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10889__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10889__auto__
                                          0))),
                                       :next {:tag :empty}}]
                                     (catch
                                      java.lang.Exception
                                      e__11829__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__11829__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__11829__auto__))))))))
                                (state__244935)))
                              (state__244935)))
                            (state__244935))
                           (state__244935)))))
                       (state__244935)))))
                   (state__244935))))
                (state__244935)))
              (state__244935)))
            (state__244935))
           (state__244935))))
        (state__244935
         []
         (clojure.core/letfn
          [(def__244103
            [arg__244126 ?__243428]
            (clojure.core/letfn
             [(state__245083
               []
               (clojure.core/let
                [x__244127 "meander.zeta"]
                (if
                 (clojure.core/= ?__243428 x__244127)
                 [?__243428]
                 (state__245084))))
              (state__245084
               []
               (if
                (clojure.core/map? arg__244126)
                (clojure.core/let
                 [VAL__244128 (.valAt arg__244126 :aliases)]
                 (if
                  (clojure.core/map? VAL__244128)
                  (clojure.core/let
                   [X__244130 (clojure.core/set VAL__244128)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__244130))
                    (clojure.core/loop
                     [search_space__245085
                      (clojure.core/seq X__244130)]
                     (if
                      (clojure.core/seq search_space__245085)
                      (clojure.core/let
                       [elem__244131
                        (clojure.core/first search_space__245085)
                        result__245086
                        (clojure.core/let
                         [elem__244131_nth_0__
                          (clojure.core/nth elem__244131 0)
                          elem__244131_nth_1__
                          (clojure.core/nth elem__244131 1)]
                         (if
                          (clojure.core/symbol? elem__244131_nth_0__)
                          (clojure.core/let
                           [X__244133
                            (clojure.core/name elem__244131_nth_0__)]
                           (if
                            (clojure.core/= ?__243428 X__244133)
                            (if
                             (clojure.core/symbol?
                              elem__244131_nth_1__)
                             (clojure.core/let
                              [X__244135
                               (clojure.core/name
                                elem__244131_nth_1__)]
                              (clojure.core/case
                               X__244135
                               ("meander.zeta")
                               [?__243428]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__245086)
                        (recur
                         (clojure.core/next search_space__245085))
                        result__245086))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__245083)))]
          (if
           (clojure.core/vector? input__243425)
           (if
            (clojure.core/= (clojure.core/count input__243425) 2)
            (clojure.core/let
             [input__243425_nth_0__
              (clojure.core/nth input__243425 0)
              input__243425_nth_1__
              (clojure.core/nth input__243425 1)]
             (if
              (clojure.core/seq? input__243425_nth_0__)
              (clojure.core/let
               [input__243425_nth_0___l__
                (clojure.core/take 1 input__243425_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__243425_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__243425_nth_0___r__
                  (clojure.core/drop 1 input__243425_nth_0__)]
                 (clojure.core/let
                  [input__243425_nth_0___l___nth_0__
                   (clojure.core/nth input__243425_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__243425_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__244113
                     (clojure.core/namespace
                      input__243425_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__243428 X__244113]
                     (clojure.core/let
                      [X__244115
                       (clojure.core/name
                        input__243425_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__244115
                       ("<>")
                       (clojure.core/let
                        [x__9586__auto__
                         (def__244103 input__243425_nth_1__ ?__243428)]
                        (if
                         (meander.runtime.zeta/fail? x__9586__auto__)
                         (state__244936)
                         (clojure.core/let
                          [[?__243428] x__9586__auto__]
                          (if
                           (clojure.core/vector? input__243425)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__243425)
                             2)
                            (clojure.core/let
                             [input__243425_nth_0__
                              (clojure.core/nth input__243425 0)
                              input__243425_nth_1__
                              (clojure.core/nth input__243425 1)]
                             (if
                              (clojure.core/seq? input__243425_nth_0__)
                              (clojure.core/let
                               [input__243425_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__243425_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__243425_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__243425_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__243425_nth_0__)]
                                 (clojure.core/let
                                  [?patterns input__243425_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__243425_nth_0__]
                                   (clojure.core/let
                                    [?env input__243425_nth_1__]
                                    (try
                                     [{:tag :group,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__10889__auto__
                                         (CATA__FN__243502
                                          ['meander.dev.parse.zeta/parse-sequential
                                           (clojure.core/into
                                            []
                                            ?patterns)
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10889__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10889__auto__
                                          0)))}]
                                     (catch
                                      java.lang.Exception
                                      e__11829__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__11829__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__11829__auto__))))))))
                                (state__244936)))
                              (state__244936)))
                            (state__244936))
                           (state__244936)))))
                       (state__244936)))))
                   (state__244936))))
                (state__244936)))
              (state__244936)))
            (state__244936))
           (state__244936))))
        (state__244936
         []
         (clojure.core/letfn
          [(def__244137
            [arg__244160 ?__243429]
            (clojure.core/letfn
             [(state__245088
               []
               (clojure.core/let
                [x__244161 "meander.math.zeta"]
                (if
                 (clojure.core/= ?__243429 x__244161)
                 [?__243429]
                 (state__245089))))
              (state__245089
               []
               (if
                (clojure.core/map? arg__244160)
                (clojure.core/let
                 [VAL__244162 (.valAt arg__244160 :aliases)]
                 (if
                  (clojure.core/map? VAL__244162)
                  (clojure.core/let
                   [X__244164 (clojure.core/set VAL__244162)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__244164))
                    (clojure.core/loop
                     [search_space__245090
                      (clojure.core/seq X__244164)]
                     (if
                      (clojure.core/seq search_space__245090)
                      (clojure.core/let
                       [elem__244165
                        (clojure.core/first search_space__245090)
                        result__245091
                        (clojure.core/let
                         [elem__244165_nth_0__
                          (clojure.core/nth elem__244165 0)
                          elem__244165_nth_1__
                          (clojure.core/nth elem__244165 1)]
                         (if
                          (clojure.core/symbol? elem__244165_nth_0__)
                          (clojure.core/let
                           [X__244167
                            (clojure.core/name elem__244165_nth_0__)]
                           (if
                            (clojure.core/= ?__243429 X__244167)
                            (if
                             (clojure.core/symbol?
                              elem__244165_nth_1__)
                             (clojure.core/let
                              [X__244169
                               (clojure.core/name
                                elem__244165_nth_1__)]
                              (clojure.core/case
                               X__244169
                               ("meander.math.zeta")
                               [?__243429]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__245091)
                        (recur
                         (clojure.core/next search_space__245090))
                        result__245091))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__245088)))]
          (if
           (clojure.core/vector? input__243425)
           (if
            (clojure.core/= (clojure.core/count input__243425) 2)
            (clojure.core/let
             [input__243425_nth_0__
              (clojure.core/nth input__243425 0)
              input__243425_nth_1__
              (clojure.core/nth input__243425 1)]
             (if
              (clojure.core/seq? input__243425_nth_0__)
              (clojure.core/let
               [input__243425_nth_0___l__
                (clojure.core/take 1 input__243425_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__243425_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__243425_nth_0___r__
                  (clojure.core/drop 1 input__243425_nth_0__)]
                 (clojure.core/let
                  [input__243425_nth_0___l___nth_0__
                   (clojure.core/nth input__243425_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__243425_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__244147
                     (clojure.core/namespace
                      input__243425_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__243429 X__244147]
                     (clojure.core/let
                      [X__244149
                       (clojure.core/name
                        input__243425_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__244149
                       ("+")
                       (clojure.core/let
                        [x__9586__auto__
                         (def__244137 input__243425_nth_1__ ?__243429)]
                        (if
                         (meander.runtime.zeta/fail? x__9586__auto__)
                         (state__244937)
                         (clojure.core/let
                          [[?__243429] x__9586__auto__]
                          (if
                           (clojure.core/vector? input__243425)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__243425)
                             2)
                            (clojure.core/let
                             [input__243425_nth_0__
                              (clojure.core/nth input__243425 0)
                              input__243425_nth_1__
                              (clojure.core/nth input__243425 1)]
                             (if
                              (clojure.core/seq? input__243425_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__243425_nth_0__)
                                3)
                               (clojure.core/let
                                [input__243425_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__243425_nth_0__
                                  1)
                                 input__243425_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__243425_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?a input__243425_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?b input__243425_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__243425_nth_0__]
                                   (clojure.core/let
                                    [?env input__243425_nth_1__]
                                    (try
                                     [{:tag :meander.math.zeta/+,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__10889__auto__
                                         (CATA__FN__243502 [?a ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10889__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10889__auto__
                                          0))),
                                       :right
                                       (clojure.core/let
                                        [CATA_RESULT__10889__auto__
                                         (CATA__FN__243502 [?b ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10889__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10889__auto__
                                          0)))}]
                                     (catch
                                      java.lang.Exception
                                      e__11829__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__11829__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__11829__auto__)))))))))
                               (state__244937))
                              (state__244937)))
                            (state__244937))
                           (state__244937)))))
                       (state__244937)))))
                   (state__244937))))
                (state__244937)))
              (state__244937)))
            (state__244937))
           (state__244937))))
        (state__244937
         []
         (clojure.core/letfn
          [(def__244171
            [arg__244194 ?__243430]
            (clojure.core/letfn
             [(state__245093
               []
               (clojure.core/let
                [x__244195 "meander.math.zeta"]
                (if
                 (clojure.core/= ?__243430 x__244195)
                 [?__243430]
                 (state__245094))))
              (state__245094
               []
               (if
                (clojure.core/map? arg__244194)
                (clojure.core/let
                 [VAL__244196 (.valAt arg__244194 :aliases)]
                 (if
                  (clojure.core/map? VAL__244196)
                  (clojure.core/let
                   [X__244198 (clojure.core/set VAL__244196)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__244198))
                    (clojure.core/loop
                     [search_space__245095
                      (clojure.core/seq X__244198)]
                     (if
                      (clojure.core/seq search_space__245095)
                      (clojure.core/let
                       [elem__244199
                        (clojure.core/first search_space__245095)
                        result__245096
                        (clojure.core/let
                         [elem__244199_nth_0__
                          (clojure.core/nth elem__244199 0)
                          elem__244199_nth_1__
                          (clojure.core/nth elem__244199 1)]
                         (if
                          (clojure.core/symbol? elem__244199_nth_0__)
                          (clojure.core/let
                           [X__244201
                            (clojure.core/name elem__244199_nth_0__)]
                           (if
                            (clojure.core/= ?__243430 X__244201)
                            (if
                             (clojure.core/symbol?
                              elem__244199_nth_1__)
                             (clojure.core/let
                              [X__244203
                               (clojure.core/name
                                elem__244199_nth_1__)]
                              (clojure.core/case
                               X__244203
                               ("meander.math.zeta")
                               [?__243430]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__245096)
                        (recur
                         (clojure.core/next search_space__245095))
                        result__245096))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__245093)))]
          (if
           (clojure.core/vector? input__243425)
           (if
            (clojure.core/= (clojure.core/count input__243425) 2)
            (clojure.core/let
             [input__243425_nth_0__
              (clojure.core/nth input__243425 0)
              input__243425_nth_1__
              (clojure.core/nth input__243425 1)]
             (if
              (clojure.core/seq? input__243425_nth_0__)
              (clojure.core/let
               [input__243425_nth_0___l__
                (clojure.core/take 1 input__243425_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__243425_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__243425_nth_0___r__
                  (clojure.core/drop 1 input__243425_nth_0__)]
                 (clojure.core/let
                  [input__243425_nth_0___l___nth_0__
                   (clojure.core/nth input__243425_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__243425_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__244181
                     (clojure.core/namespace
                      input__243425_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__243430 X__244181]
                     (clojure.core/let
                      [X__244183
                       (clojure.core/name
                        input__243425_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__244183
                       ("-")
                       (clojure.core/let
                        [x__9586__auto__
                         (def__244171 input__243425_nth_1__ ?__243430)]
                        (if
                         (meander.runtime.zeta/fail? x__9586__auto__)
                         (state__244938)
                         (clojure.core/let
                          [[?__243430] x__9586__auto__]
                          (if
                           (clojure.core/vector? input__243425)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__243425)
                             2)
                            (clojure.core/let
                             [input__243425_nth_0__
                              (clojure.core/nth input__243425 0)
                              input__243425_nth_1__
                              (clojure.core/nth input__243425 1)]
                             (if
                              (clojure.core/seq? input__243425_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__243425_nth_0__)
                                3)
                               (clojure.core/let
                                [input__243425_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__243425_nth_0__
                                  1)
                                 input__243425_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__243425_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?a input__243425_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?b input__243425_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__243425_nth_0__]
                                   (clojure.core/let
                                    [?env input__243425_nth_1__]
                                    (try
                                     [{:tag :meander.math.zeta/-,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__10889__auto__
                                         (CATA__FN__243502 [?a ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10889__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10889__auto__
                                          0))),
                                       :right
                                       (clojure.core/let
                                        [CATA_RESULT__10889__auto__
                                         (CATA__FN__243502 [?b ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10889__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10889__auto__
                                          0)))}]
                                     (catch
                                      java.lang.Exception
                                      e__11829__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__11829__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__11829__auto__)))))))))
                               (state__244938))
                              (state__244938)))
                            (state__244938))
                           (state__244938)))))
                       (state__244938)))))
                   (state__244938))))
                (state__244938)))
              (state__244938)))
            (state__244938))
           (state__244938))))
        (state__244938
         []
         (clojure.core/letfn
          [(def__244205
            [arg__244228 ?__243431]
            (clojure.core/letfn
             [(state__245098
               []
               (clojure.core/let
                [x__244229 "meander.zeta"]
                (if
                 (clojure.core/= ?__243431 x__244229)
                 [?__243431]
                 (state__245099))))
              (state__245099
               []
               (if
                (clojure.core/map? arg__244228)
                (clojure.core/let
                 [VAL__244230 (.valAt arg__244228 :aliases)]
                 (if
                  (clojure.core/map? VAL__244230)
                  (clojure.core/let
                   [X__244232 (clojure.core/set VAL__244230)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__244232))
                    (clojure.core/loop
                     [search_space__245100
                      (clojure.core/seq X__244232)]
                     (if
                      (clojure.core/seq search_space__245100)
                      (clojure.core/let
                       [elem__244233
                        (clojure.core/first search_space__245100)
                        result__245101
                        (clojure.core/let
                         [elem__244233_nth_0__
                          (clojure.core/nth elem__244233 0)
                          elem__244233_nth_1__
                          (clojure.core/nth elem__244233 1)]
                         (if
                          (clojure.core/symbol? elem__244233_nth_0__)
                          (clojure.core/let
                           [X__244235
                            (clojure.core/name elem__244233_nth_0__)]
                           (if
                            (clojure.core/= ?__243431 X__244235)
                            (if
                             (clojure.core/symbol?
                              elem__244233_nth_1__)
                             (clojure.core/let
                              [X__244237
                               (clojure.core/name
                                elem__244233_nth_1__)]
                              (clojure.core/case
                               X__244237
                               ("meander.zeta")
                               [?__243431]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__245101)
                        (recur
                         (clojure.core/next search_space__245100))
                        result__245101))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__245098)))]
          (if
           (clojure.core/vector? input__243425)
           (if
            (clojure.core/= (clojure.core/count input__243425) 2)
            (clojure.core/let
             [input__243425_nth_0__
              (clojure.core/nth input__243425 0)
              input__243425_nth_1__
              (clojure.core/nth input__243425 1)]
             (if
              (clojure.core/seq? input__243425_nth_0__)
              (clojure.core/let
               [input__243425_nth_0___l__
                (clojure.core/take 1 input__243425_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__243425_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__243425_nth_0___r__
                  (clojure.core/drop 1 input__243425_nth_0__)]
                 (clojure.core/let
                  [input__243425_nth_0___l___nth_0__
                   (clojure.core/nth input__243425_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__243425_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__244215
                     (clojure.core/namespace
                      input__243425_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__243431 X__244215]
                     (clojure.core/let
                      [X__244217
                       (clojure.core/name
                        input__243425_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__244217
                       ("with")
                       (clojure.core/let
                        [x__9586__auto__
                         (def__244205 input__243425_nth_1__ ?__243431)]
                        (if
                         (meander.runtime.zeta/fail? x__9586__auto__)
                         (state__244939)
                         (clojure.core/let
                          [[?__243431] x__9586__auto__]
                          (if
                           (clojure.core/vector? input__243425)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__243425)
                             2)
                            (clojure.core/let
                             [input__243425_nth_0__
                              (clojure.core/nth input__243425 0)
                              input__243425_nth_1__
                              (clojure.core/nth input__243425 1)]
                             (if
                              (clojure.core/seq? input__243425_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__243425_nth_0__)
                                3)
                               (clojure.core/let
                                [input__243425_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__243425_nth_0__
                                  1)
                                 input__243425_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__243425_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?bindings
                                  input__243425_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?body input__243425_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__243425_nth_0__]
                                   (clojure.core/let
                                    [?env input__243425_nth_1__]
                                    (try
                                     [{:tag :with,
                                       :bindings
                                       {:tag :with-bindings,
                                        :bindings
                                        (clojure.core/let
                                         [CATA_RESULT__10889__auto__
                                          (CATA__FN__243502
                                           ['meander.dev.parse.zeta/parse-with-bindings
                                            ?bindings
                                            ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__10889__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__10889__auto__
                                           0)))},
                                       :body
                                       (clojure.core/let
                                        [CATA_RESULT__10889__auto__
                                         (CATA__FN__243502
                                          [?body ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10889__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10889__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__11829__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__11829__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__11829__auto__)))))))))
                               (state__244939))
                              (state__244939)))
                            (state__244939))
                           (state__244939)))))
                       (state__244939)))))
                   (state__244939))))
                (state__244939)))
              (state__244939)))
            (state__244939))
           (state__244939))))
        (state__244939
         []
         (clojure.core/letfn
          [(def__244239
            [arg__244262 ?__243432]
            (clojure.core/letfn
             [(state__245103
               []
               (clojure.core/let
                [x__244263 "meander.zeta"]
                (if
                 (clojure.core/= ?__243432 x__244263)
                 [?__243432]
                 (state__245104))))
              (state__245104
               []
               (if
                (clojure.core/map? arg__244262)
                (clojure.core/let
                 [VAL__244264 (.valAt arg__244262 :aliases)]
                 (if
                  (clojure.core/map? VAL__244264)
                  (clojure.core/let
                   [X__244266 (clojure.core/set VAL__244264)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__244266))
                    (clojure.core/loop
                     [search_space__245105
                      (clojure.core/seq X__244266)]
                     (if
                      (clojure.core/seq search_space__245105)
                      (clojure.core/let
                       [elem__244267
                        (clojure.core/first search_space__245105)
                        result__245106
                        (clojure.core/let
                         [elem__244267_nth_0__
                          (clojure.core/nth elem__244267 0)
                          elem__244267_nth_1__
                          (clojure.core/nth elem__244267 1)]
                         (if
                          (clojure.core/symbol? elem__244267_nth_0__)
                          (clojure.core/let
                           [X__244269
                            (clojure.core/name elem__244267_nth_0__)]
                           (if
                            (clojure.core/= ?__243432 X__244269)
                            (if
                             (clojure.core/symbol?
                              elem__244267_nth_1__)
                             (clojure.core/let
                              [X__244271
                               (clojure.core/name
                                elem__244267_nth_1__)]
                              (clojure.core/case
                               X__244271
                               ("meander.zeta")
                               [?__243432]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__245106)
                        (recur
                         (clojure.core/next search_space__245105))
                        result__245106))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__245103)))]
          (if
           (clojure.core/vector? input__243425)
           (if
            (clojure.core/= (clojure.core/count input__243425) 2)
            (clojure.core/let
             [input__243425_nth_0__
              (clojure.core/nth input__243425 0)
              input__243425_nth_1__
              (clojure.core/nth input__243425 1)]
             (if
              (clojure.core/seq? input__243425_nth_0__)
              (clojure.core/let
               [input__243425_nth_0___l__
                (clojure.core/take 1 input__243425_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__243425_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__243425_nth_0___r__
                  (clojure.core/drop 1 input__243425_nth_0__)]
                 (clojure.core/let
                  [input__243425_nth_0___l___nth_0__
                   (clojure.core/nth input__243425_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__243425_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__244249
                     (clojure.core/namespace
                      input__243425_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__243432 X__244249]
                     (clojure.core/let
                      [X__244251
                       (clojure.core/name
                        input__243425_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__244251
                       ("apply")
                       (clojure.core/let
                        [x__9586__auto__
                         (def__244239 input__243425_nth_1__ ?__243432)]
                        (if
                         (meander.runtime.zeta/fail? x__9586__auto__)
                         (state__244940)
                         (clojure.core/let
                          [[?__243432] x__9586__auto__]
                          (if
                           (clojure.core/vector? input__243425)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__243425)
                             2)
                            (clojure.core/let
                             [input__243425_nth_0__
                              (clojure.core/nth input__243425 0)
                              input__243425_nth_1__
                              (clojure.core/nth input__243425 1)]
                             (if
                              (clojure.core/seq? input__243425_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__243425_nth_0__)
                                3)
                               (clojure.core/let
                                [input__243425_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__243425_nth_0__
                                  1)
                                 input__243425_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__243425_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?fn input__243425_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?pattern
                                   input__243425_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__243425_nth_0__]
                                   (clojure.core/let
                                    [?env input__243425_nth_1__]
                                    (try
                                     [{:tag :apply,
                                       :fn ?fn,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__10889__auto__
                                         (CATA__FN__243502
                                          [?pattern ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10889__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10889__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__11829__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__11829__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__11829__auto__)))))))))
                               (state__244940))
                              (state__244940)))
                            (state__244940))
                           (state__244940)))))
                       (state__244940)))))
                   (state__244940))))
                (state__244940)))
              (state__244940)))
            (state__244940))
           (state__244940))))
        (state__244940
         []
         (clojure.core/letfn
          [(def__244273
            [arg__244298 ?__243433]
            (clojure.core/letfn
             [(state__245108
               []
               (clojure.core/let
                [x__244299 "meander.zeta"]
                (if
                 (clojure.core/= ?__243433 x__244299)
                 [?__243433]
                 (state__245109))))
              (state__245109
               []
               (if
                (clojure.core/map? arg__244298)
                (clojure.core/let
                 [VAL__244300 (.valAt arg__244298 :aliases)]
                 (if
                  (clojure.core/map? VAL__244300)
                  (clojure.core/let
                   [X__244302 (clojure.core/set VAL__244300)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__244302))
                    (clojure.core/loop
                     [search_space__245110
                      (clojure.core/seq X__244302)]
                     (if
                      (clojure.core/seq search_space__245110)
                      (clojure.core/let
                       [elem__244303
                        (clojure.core/first search_space__245110)
                        result__245111
                        (clojure.core/let
                         [elem__244303_nth_0__
                          (clojure.core/nth elem__244303 0)
                          elem__244303_nth_1__
                          (clojure.core/nth elem__244303 1)]
                         (if
                          (clojure.core/symbol? elem__244303_nth_0__)
                          (clojure.core/let
                           [X__244305
                            (clojure.core/name elem__244303_nth_0__)]
                           (if
                            (clojure.core/= ?__243433 X__244305)
                            (if
                             (clojure.core/symbol?
                              elem__244303_nth_1__)
                             (clojure.core/let
                              [X__244307
                               (clojure.core/name
                                elem__244303_nth_1__)]
                              (clojure.core/case
                               X__244307
                               ("meander.zeta")
                               [?__243433]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__245111)
                        (recur
                         (clojure.core/next search_space__245110))
                        result__245111))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__245108)))]
          (if
           (clojure.core/vector? input__243425)
           (if
            (clojure.core/= (clojure.core/count input__243425) 2)
            (clojure.core/let
             [input__243425_nth_0__
              (clojure.core/nth input__243425 0)
              input__243425_nth_1__
              (clojure.core/nth input__243425 1)]
             (if
              (clojure.core/seq? input__243425_nth_0__)
              (clojure.core/let
               [input__243425_nth_0___l__
                (clojure.core/take 1 input__243425_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__243425_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__243425_nth_0___r__
                  (clojure.core/drop 1 input__243425_nth_0__)]
                 (clojure.core/let
                  [input__243425_nth_0___l___nth_0__
                   (clojure.core/nth input__243425_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__243425_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__244285
                     (clojure.core/namespace
                      input__243425_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__243433 X__244285]
                     (clojure.core/let
                      [X__244287
                       (clojure.core/name
                        input__243425_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__244287
                       ("and")
                       (clojure.core/let
                        [x__9586__auto__
                         (def__244273 input__243425_nth_1__ ?__243433)]
                        (if
                         (meander.runtime.zeta/fail? x__9586__auto__)
                         (state__244941)
                         (clojure.core/let
                          [[?__243433] x__9586__auto__]
                          (if
                           (clojure.core/vector? input__243425)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__243425)
                             2)
                            (clojure.core/let
                             [input__243425_nth_0__
                              (clojure.core/nth input__243425 0)
                              input__243425_nth_1__
                              (clojure.core/nth input__243425 1)]
                             (if
                              (clojure.core/seq? input__243425_nth_0__)
                              (clojure.core/let
                               [input__243425_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__243425_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__243425_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__243425_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__243425_nth_0__)]
                                 (clojure.core/let
                                  [!forms
                                   (clojure.core/vec
                                    input__243425_nth_0___r__)]
                                  (clojure.core/let
                                   [?form input__243425_nth_0__]
                                   (clojure.core/let
                                    [?env input__243425_nth_1__]
                                    (try
                                     [(clojure.core/let
                                       [!forms__counter
                                        (meander.runtime.zeta/iterator
                                         !forms)]
                                       (clojure.core/let
                                        [CATA_RESULT__10889__auto__
                                         (CATA__FN__243502
                                          ['meander.dev.parse.zeta/make-and
                                           (clojure.core/into
                                            []
                                            (clojure.core/loop
                                             [return__243504
                                              (clojure.core/transient
                                               [])]
                                             (if
                                              (clojure.core/and
                                               (.hasNext
                                                !forms__counter))
                                              (recur
                                               (clojure.core/conj!
                                                return__243504
                                                (clojure.core/let
                                                 [CATA_RESULT__10889__auto__
                                                  (CATA__FN__243502
                                                   [(if
                                                     (.hasNext
                                                      !forms__counter)
                                                     (.next
                                                      !forms__counter))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__10889__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__10889__auto__
                                                   0)))))
                                              (clojure.core/persistent!
                                               return__243504))))
                                           ?form])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10889__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10889__auto__
                                          0))))]
                                     (catch
                                      java.lang.Exception
                                      e__11829__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__11829__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__11829__auto__))))))))
                                (state__244941)))
                              (state__244941)))
                            (state__244941))
                           (state__244941)))))
                       (state__244941)))))
                   (state__244941))))
                (state__244941)))
              (state__244941)))
            (state__244941))
           (state__244941))))
        (state__244941
         []
         (if
          (clojure.core/vector? input__243425)
          (if
           (clojure.core/= (clojure.core/count input__243425) 3)
           (clojure.core/let
            [input__243425_nth_0__
             (clojure.core/nth input__243425 0)
             input__243425_nth_1__
             (clojure.core/nth input__243425 1)
             input__243425_nth_2__
             (clojure.core/nth input__243425 2)]
            (clojure.core/case
             input__243425_nth_0__
             (meander.dev.parse.zeta/make-and)
             (clojure.core/letfn
              [(state__245113
                []
                (if
                 (clojure.core/vector? input__243425_nth_1__)
                 (clojure.core/case
                  input__243425_nth_1__
                  ([])
                  (clojure.core/let
                   [?form input__243425_nth_2__]
                   (try
                    [{:tag :error,
                      :message
                      "meander.zeta/and requires 1 or more arguments",
                      :form ?form}]
                    (catch
                     java.lang.Exception
                     e__11829__auto__
                     (if
                      (meander.runtime.zeta/fail? e__11829__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__11829__auto__)))))
                  (state__245114))
                 (state__245114)))
               (state__245114
                []
                (clojure.core/case
                 input__243425_nth_2__
                 (nil)
                 (if
                  (clojure.core/vector? input__243425_nth_1__)
                  (if
                   (clojure.core/=
                    (clojure.core/count input__243425_nth_1__)
                    1)
                   (clojure.core/let
                    [input__243425_nth_1___nth_0__
                     (clojure.core/nth input__243425_nth_1__ 0)]
                    (clojure.core/let
                     [?ast-a input__243425_nth_1___nth_0__]
                     (try
                      [?ast-a]
                      (catch
                       java.lang.Exception
                       e__11829__auto__
                       (if
                        (meander.runtime.zeta/fail? e__11829__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__11829__auto__))))))
                   (state__245115))
                  (state__245115))
                 (state__245115)))
               (state__245115
                []
                (if
                 (clojure.core/vector? input__243425_nth_1__)
                 (clojure.core/letfn
                  [(state__245116
                    []
                    (if
                     (clojure.core/=
                      (clojure.core/count input__243425_nth_1__)
                      1)
                     (clojure.core/let
                      [input__243425_nth_1___nth_0__
                       (clojure.core/nth input__243425_nth_1__ 0)]
                      (clojure.core/let
                       [?ast-a input__243425_nth_1___nth_0__]
                       (clojure.core/let
                        [?form input__243425_nth_2__]
                        (try
                         [(clojure.core/let
                           [CATA_RESULT__10889__auto__
                            (CATA__FN__243502
                             ['meander.dev.parse.zeta/make-and
                              [?ast-a {:tag :pass}]
                              ?form])]
                           (if
                            (meander.runtime.zeta/fail?
                             CATA_RESULT__10889__auto__)
                            (throw (meander.runtime.zeta/fail))
                            (clojure.core/nth
                             CATA_RESULT__10889__auto__
                             0)))]
                         (catch
                          java.lang.Exception
                          e__11829__auto__
                          (if
                           (meander.runtime.zeta/fail?
                            e__11829__auto__)
                           (meander.runtime.zeta/fail)
                           (throw e__11829__auto__)))))))
                     (state__245117)))
                   (state__245117
                    []
                    (if
                     (clojure.core/=
                      (clojure.core/count input__243425_nth_1__)
                      2)
                     (clojure.core/let
                      [input__243425_nth_1___nth_0__
                       (clojure.core/nth input__243425_nth_1__ 0)
                       input__243425_nth_1___nth_1__
                       (clojure.core/nth input__243425_nth_1__ 1)]
                      (clojure.core/let
                       [?ast-a input__243425_nth_1___nth_0__]
                       (clojure.core/let
                        [?ast-b input__243425_nth_1___nth_1__]
                        (clojure.core/let
                         [?form input__243425_nth_2__]
                         (try
                          [{:tag :and,
                            :left ?ast-a,
                            :right ?ast-b,
                            :form ?form}]
                          (catch
                           java.lang.Exception
                           e__11829__auto__
                           (if
                            (meander.runtime.zeta/fail?
                             e__11829__auto__)
                            (meander.runtime.zeta/fail)
                            (throw e__11829__auto__))))))))
                     (state__245118)))
                   (state__245118
                    []
                    (clojure.core/loop
                     [search_space__245119
                      (meander.runtime.zeta/epsilon-partitions
                       2
                       input__243425_nth_1__)]
                     (if
                      (clojure.core/seq search_space__245119)
                      (clojure.core/let
                       [input__243425_nth_1___parts__
                        (clojure.core/first search_space__245119)
                        result__245120
                        (clojure.core/let
                         [input__243425_nth_1___l__
                          (clojure.core/nth
                           input__243425_nth_1___parts__
                           0)
                          input__243425_nth_1___r__
                          (clojure.core/nth
                           input__243425_nth_1___parts__
                           1)]
                         (clojure.core/letfn
                          [(state__245122
                            []
                            (clojure.core/let
                             [!asts-1 []]
                             (clojure.core/let
                              [ret__9750__auto__
                               (meander.runtime.zeta/epsilon-run-star-1
                                input__243425_nth_1___l__
                                [!asts-1]
                                (clojure.core/fn
                                 [[!asts-1] input__244334]
                                 (clojure.core/let
                                  [input__244334_nth_0__
                                   (clojure.core/nth input__244334 0)]
                                  (clojure.core/let
                                   [!asts-1
                                    (clojure.core/conj
                                     !asts-1
                                     input__244334_nth_0__)]
                                   [!asts-1])))
                                (clojure.core/fn
                                 [[!asts-1]]
                                 (clojure.core/let
                                  [X__244327
                                   (clojure.core/count
                                    input__243425_nth_1___l__)]
                                  (clojure.core/let
                                   [?n X__244327]
                                   (clojure.core/let
                                    [X__244331
                                     (clojure.core/count
                                      input__243425_nth_1___r__)]
                                    (if
                                     (clojure.core/= ?n X__244331)
                                     (clojure.core/let
                                      [!asts-2 []]
                                      (clojure.core/let
                                       [ret__9750__auto__
                                        (meander.runtime.zeta/epsilon-run-star-1
                                         input__243425_nth_1___r__
                                         [!asts-2 !asts-1]
                                         (clojure.core/fn
                                          [[!asts-2 !asts-1]
                                           input__244332]
                                          (clojure.core/let
                                           [input__244332_nth_0__
                                            (clojure.core/nth
                                             input__244332
                                             0)]
                                           (clojure.core/let
                                            [!asts-2
                                             (clojure.core/conj
                                              !asts-2
                                              input__244332_nth_0__)]
                                            [!asts-2 !asts-1])))
                                         (clojure.core/fn
                                          [[!asts-2 !asts-1]]
                                          (clojure.core/let
                                           [?form
                                            input__243425_nth_2__]
                                           (try
                                            [(clojure.core/let
                                              [!asts-1__counter
                                               (meander.runtime.zeta/iterator
                                                !asts-1)
                                               !asts-2__counter
                                               (meander.runtime.zeta/iterator
                                                !asts-2)]
                                              (clojure.core/let
                                               [CATA_RESULT__10889__auto__
                                                (CATA__FN__243502
                                                 ['meander.dev.parse.zeta/make-and
                                                  [(clojure.core/let
                                                    [CATA_RESULT__10889__auto__
                                                     (CATA__FN__243502
                                                      ['meander.dev.parse.zeta/make-and
                                                       (clojure.core/into
                                                        []
                                                        (clojure.core/vec
                                                         (clojure.core/iterator-seq
                                                          !asts-1__counter)))
                                                       nil])]
                                                    (if
                                                     (meander.runtime.zeta/fail?
                                                      CATA_RESULT__10889__auto__)
                                                     (throw
                                                      (meander.runtime.zeta/fail))
                                                     (clojure.core/nth
                                                      CATA_RESULT__10889__auto__
                                                      0)))
                                                   (clojure.core/let
                                                    [CATA_RESULT__10889__auto__
                                                     (CATA__FN__243502
                                                      ['meander.dev.parse.zeta/make-and
                                                       (clojure.core/into
                                                        []
                                                        (clojure.core/vec
                                                         (clojure.core/iterator-seq
                                                          !asts-2__counter)))
                                                       nil])]
                                                    (if
                                                     (meander.runtime.zeta/fail?
                                                      CATA_RESULT__10889__auto__)
                                                     (throw
                                                      (meander.runtime.zeta/fail))
                                                     (clojure.core/nth
                                                      CATA_RESULT__10889__auto__
                                                      0)))]
                                                  ?form])]
                                               (if
                                                (meander.runtime.zeta/fail?
                                                 CATA_RESULT__10889__auto__)
                                                (throw
                                                 (meander.runtime.zeta/fail))
                                                (clojure.core/nth
                                                 CATA_RESULT__10889__auto__
                                                 0))))]
                                            (catch
                                             java.lang.Exception
                                             e__11829__auto__
                                             (if
                                              (meander.runtime.zeta/fail?
                                               e__11829__auto__)
                                              (meander.runtime.zeta/fail)
                                              (throw
                                               e__11829__auto__)))))))]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         ret__9750__auto__)
                                        (state__245123)
                                        ret__9750__auto__)))
                                     (state__245123)))))))]
                              (if
                               (meander.runtime.zeta/fail?
                                ret__9750__auto__)
                               (state__245123)
                               ret__9750__auto__))))
                           (state__245123
                            []
                            (clojure.core/let
                             [!asts-1 []]
                             (clojure.core/let
                              [ret__9750__auto__
                               (meander.runtime.zeta/epsilon-run-star-1
                                input__243425_nth_1___l__
                                [!asts-1]
                                (clojure.core/fn
                                 [[!asts-1] input__244350]
                                 (clojure.core/let
                                  [input__244350_nth_0__
                                   (clojure.core/nth input__244350 0)]
                                  (clojure.core/let
                                   [!asts-1
                                    (clojure.core/conj
                                     !asts-1
                                     input__244350_nth_0__)]
                                   [!asts-1])))
                                (clojure.core/fn
                                 [[!asts-1]]
                                 (clojure.core/let
                                  [X__244341
                                   (clojure.core/count
                                    input__243425_nth_1___l__)]
                                  (clojure.core/let
                                   [?n X__244341]
                                   (clojure.core/let
                                    [input__243425_nth_1___r___l__
                                     (clojure.core/subvec
                                      input__243425_nth_1___r__
                                      0
                                      (clojure.core/min
                                       (clojure.core/count
                                        input__243425_nth_1___r__)
                                       1))]
                                    (if
                                     (clojure.core/=
                                      (clojure.core/count
                                       input__243425_nth_1___r___l__)
                                      1)
                                     (clojure.core/let
                                      [input__243425_nth_1___r___r__
                                       (clojure.core/subvec
                                        input__243425_nth_1___r__
                                        1)]
                                      (clojure.core/let
                                       [input__243425_nth_1___r___l___nth_0__
                                        (clojure.core/nth
                                         input__243425_nth_1___r___l__
                                         0)]
                                       (clojure.core/let
                                        [?ast
                                         input__243425_nth_1___r___l___nth_0__]
                                        (clojure.core/let
                                         [X__244347
                                          (clojure.core/count
                                           input__243425_nth_1___r___r__)]
                                         (if
                                          (clojure.core/= ?n X__244347)
                                          (clojure.core/let
                                           [!asts-2 []]
                                           (clojure.core/let
                                            [ret__9750__auto__
                                             (meander.runtime.zeta/epsilon-run-star-1
                                              input__243425_nth_1___r___r__
                                              [!asts-2 !asts-1]
                                              (clojure.core/fn
                                               [[!asts-2 !asts-1]
                                                input__244348]
                                               (clojure.core/let
                                                [input__244348_nth_0__
                                                 (clojure.core/nth
                                                  input__244348
                                                  0)]
                                                (clojure.core/let
                                                 [!asts-2
                                                  (clojure.core/conj
                                                   !asts-2
                                                   input__244348_nth_0__)]
                                                 [!asts-2 !asts-1])))
                                              (clojure.core/fn
                                               [[!asts-2 !asts-1]]
                                               (clojure.core/let
                                                [?form
                                                 input__243425_nth_2__]
                                                (try
                                                 [(clojure.core/let
                                                   [!asts-1__counter
                                                    (meander.runtime.zeta/iterator
                                                     !asts-1)
                                                    !asts-2__counter
                                                    (meander.runtime.zeta/iterator
                                                     !asts-2)]
                                                   (clojure.core/let
                                                    [CATA_RESULT__10889__auto__
                                                     (CATA__FN__243502
                                                      ['meander.dev.parse.zeta/make-and
                                                       [(clojure.core/let
                                                         [CATA_RESULT__10889__auto__
                                                          (CATA__FN__243502
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
                                                           CATA_RESULT__10889__auto__)
                                                          (throw
                                                           (meander.runtime.zeta/fail))
                                                          (clojure.core/nth
                                                           CATA_RESULT__10889__auto__
                                                           0)))
                                                        (clojure.core/let
                                                         [CATA_RESULT__10889__auto__
                                                          (CATA__FN__243502
                                                           ['meander.dev.parse.zeta/make-and
                                                            (clojure.core/into
                                                             []
                                                             (clojure.core/vec
                                                              (clojure.core/iterator-seq
                                                               !asts-2__counter)))
                                                            nil])]
                                                         (if
                                                          (meander.runtime.zeta/fail?
                                                           CATA_RESULT__10889__auto__)
                                                          (throw
                                                           (meander.runtime.zeta/fail))
                                                          (clojure.core/nth
                                                           CATA_RESULT__10889__auto__
                                                           0)))]
                                                       ?form])]
                                                    (if
                                                     (meander.runtime.zeta/fail?
                                                      CATA_RESULT__10889__auto__)
                                                     (throw
                                                      (meander.runtime.zeta/fail))
                                                     (clojure.core/nth
                                                      CATA_RESULT__10889__auto__
                                                      0))))]
                                                 (catch
                                                  java.lang.Exception
                                                  e__11829__auto__
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    e__11829__auto__)
                                                   (meander.runtime.zeta/fail)
                                                   (throw
                                                    e__11829__auto__)))))))]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              ret__9750__auto__)
                                             (meander.runtime.zeta/fail)
                                             ret__9750__auto__)))
                                          (meander.runtime.zeta/fail))))))
                                     (meander.runtime.zeta/fail)))))))]
                              (if
                               (meander.runtime.zeta/fail?
                                ret__9750__auto__)
                               (meander.runtime.zeta/fail)
                               ret__9750__auto__))))]
                          (state__245122)))]
                       (if
                        (meander.runtime.zeta/fail? result__245120)
                        (recur
                         (clojure.core/next search_space__245119))
                        result__245120))
                      (state__244942))))]
                  (state__245116))
                 (state__244942)))]
              (state__245113))
             (state__244942)))
           (state__244942))
          (state__244942)))
        (state__244942
         []
         (clojure.core/letfn
          [(def__244353
            [arg__244376 ?__243434]
            (clojure.core/letfn
             [(state__245128
               []
               (clojure.core/let
                [x__244377 "meander.zeta"]
                (if
                 (clojure.core/= ?__243434 x__244377)
                 [?__243434]
                 (state__245129))))
              (state__245129
               []
               (if
                (clojure.core/map? arg__244376)
                (clojure.core/let
                 [VAL__244378 (.valAt arg__244376 :aliases)]
                 (if
                  (clojure.core/map? VAL__244378)
                  (clojure.core/let
                   [X__244380 (clojure.core/set VAL__244378)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__244380))
                    (clojure.core/loop
                     [search_space__245130
                      (clojure.core/seq X__244380)]
                     (if
                      (clojure.core/seq search_space__245130)
                      (clojure.core/let
                       [elem__244381
                        (clojure.core/first search_space__245130)
                        result__245131
                        (clojure.core/let
                         [elem__244381_nth_0__
                          (clojure.core/nth elem__244381 0)
                          elem__244381_nth_1__
                          (clojure.core/nth elem__244381 1)]
                         (if
                          (clojure.core/symbol? elem__244381_nth_0__)
                          (clojure.core/let
                           [X__244383
                            (clojure.core/name elem__244381_nth_0__)]
                           (if
                            (clojure.core/= ?__243434 X__244383)
                            (if
                             (clojure.core/symbol?
                              elem__244381_nth_1__)
                             (clojure.core/let
                              [X__244385
                               (clojure.core/name
                                elem__244381_nth_1__)]
                              (clojure.core/case
                               X__244385
                               ("meander.zeta")
                               [?__243434]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__245131)
                        (recur
                         (clojure.core/next search_space__245130))
                        result__245131))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__245128)))]
          (if
           (clojure.core/vector? input__243425)
           (if
            (clojure.core/= (clojure.core/count input__243425) 2)
            (clojure.core/let
             [input__243425_nth_0__
              (clojure.core/nth input__243425 0)
              input__243425_nth_1__
              (clojure.core/nth input__243425 1)]
             (if
              (clojure.core/seq? input__243425_nth_0__)
              (clojure.core/let
               [input__243425_nth_0___l__
                (clojure.core/take 1 input__243425_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__243425_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__243425_nth_0___r__
                  (clojure.core/drop 1 input__243425_nth_0__)]
                 (clojure.core/let
                  [input__243425_nth_0___l___nth_0__
                   (clojure.core/nth input__243425_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__243425_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__244363
                     (clojure.core/namespace
                      input__243425_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__243434 X__244363]
                     (clojure.core/let
                      [X__244365
                       (clojure.core/name
                        input__243425_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__244365
                       ("cata")
                       (clojure.core/let
                        [x__9586__auto__
                         (def__244353 input__243425_nth_1__ ?__243434)]
                        (if
                         (meander.runtime.zeta/fail? x__9586__auto__)
                         (state__244943)
                         (clojure.core/let
                          [[?__243434] x__9586__auto__]
                          (if
                           (clojure.core/vector? input__243425)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__243425)
                             2)
                            (clojure.core/let
                             [input__243425_nth_0__
                              (clojure.core/nth input__243425 0)
                              input__243425_nth_1__
                              (clojure.core/nth input__243425 1)]
                             (if
                              (clojure.core/seq? input__243425_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__243425_nth_0__)
                                2)
                               (clojure.core/let
                                [input__243425_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__243425_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__243425_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__243425_nth_0__]
                                  (clojure.core/let
                                   [?env input__243425_nth_1__]
                                   (try
                                    [{:tag :cata,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__10889__auto__
                                        (CATA__FN__243502
                                         [?pattern ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__10889__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__10889__auto__
                                         0))),
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__11829__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__11829__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__11829__auto__))))))))
                               (state__244943))
                              (state__244943)))
                            (state__244943))
                           (state__244943)))))
                       (state__244943)))))
                   (state__244943))))
                (state__244943)))
              (state__244943)))
            (state__244943))
           (state__244943))))
        (state__244943
         []
         (clojure.core/letfn
          [(def__244387
            [arg__244410 ?__243435]
            (clojure.core/letfn
             [(state__245133
               []
               (clojure.core/let
                [x__244411 "meander.zeta"]
                (if
                 (clojure.core/= ?__243435 x__244411)
                 [?__243435]
                 (state__245134))))
              (state__245134
               []
               (if
                (clojure.core/map? arg__244410)
                (clojure.core/let
                 [VAL__244412 (.valAt arg__244410 :aliases)]
                 (if
                  (clojure.core/map? VAL__244412)
                  (clojure.core/let
                   [X__244414 (clojure.core/set VAL__244412)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__244414))
                    (clojure.core/loop
                     [search_space__245135
                      (clojure.core/seq X__244414)]
                     (if
                      (clojure.core/seq search_space__245135)
                      (clojure.core/let
                       [elem__244415
                        (clojure.core/first search_space__245135)
                        result__245136
                        (clojure.core/let
                         [elem__244415_nth_0__
                          (clojure.core/nth elem__244415 0)
                          elem__244415_nth_1__
                          (clojure.core/nth elem__244415 1)]
                         (if
                          (clojure.core/symbol? elem__244415_nth_0__)
                          (clojure.core/let
                           [X__244417
                            (clojure.core/name elem__244415_nth_0__)]
                           (if
                            (clojure.core/= ?__243435 X__244417)
                            (if
                             (clojure.core/symbol?
                              elem__244415_nth_1__)
                             (clojure.core/let
                              [X__244419
                               (clojure.core/name
                                elem__244415_nth_1__)]
                              (clojure.core/case
                               X__244419
                               ("meander.zeta")
                               [?__243435]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__245136)
                        (recur
                         (clojure.core/next search_space__245135))
                        result__245136))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__245133)))]
          (if
           (clojure.core/vector? input__243425)
           (if
            (clojure.core/= (clojure.core/count input__243425) 2)
            (clojure.core/let
             [input__243425_nth_0__
              (clojure.core/nth input__243425 0)
              input__243425_nth_1__
              (clojure.core/nth input__243425 1)]
             (if
              (clojure.core/seq? input__243425_nth_0__)
              (clojure.core/let
               [input__243425_nth_0___l__
                (clojure.core/take 1 input__243425_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__243425_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__243425_nth_0___r__
                  (clojure.core/drop 1 input__243425_nth_0__)]
                 (clojure.core/let
                  [input__243425_nth_0___l___nth_0__
                   (clojure.core/nth input__243425_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__243425_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__244397
                     (clojure.core/namespace
                      input__243425_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__243435 X__244397]
                     (clojure.core/let
                      [X__244399
                       (clojure.core/name
                        input__243425_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__244399
                       ("fold")
                       (clojure.core/let
                        [x__9586__auto__
                         (def__244387 input__243425_nth_1__ ?__243435)]
                        (if
                         (meander.runtime.zeta/fail? x__9586__auto__)
                         (state__244944)
                         (clojure.core/let
                          [[?__243435] x__9586__auto__]
                          (if
                           (clojure.core/vector? input__243425)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__243425)
                             2)
                            (clojure.core/let
                             [input__243425_nth_0__
                              (clojure.core/nth input__243425 0)
                              input__243425_nth_1__
                              (clojure.core/nth input__243425 1)]
                             (if
                              (clojure.core/seq? input__243425_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 4)
                                 input__243425_nth_0__)
                                4)
                               (clojure.core/let
                                [input__243425_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__243425_nth_0__
                                  1)
                                 input__243425_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__243425_nth_0__
                                  2)
                                 input__243425_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__243425_nth_0__
                                  3)]
                                (clojure.core/let
                                 [?mutable-variable
                                  input__243425_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?initial-value
                                   input__243425_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?fold-function
                                    input__243425_nth_0___nth_3__]
                                   (clojure.core/let
                                    [?form input__243425_nth_0__]
                                    (clojure.core/let
                                     [?env input__243425_nth_1__]
                                     (try
                                      [(clojure.core/let
                                        [CATA_RESULT__10889__auto__
                                         (CATA__FN__243502
                                          ['meander.dev.parse.zeta/make-fold
                                           (clojure.core/let
                                            [CATA_RESULT__10889__auto__
                                             (CATA__FN__243502
                                              [?mutable-variable
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__10889__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__10889__auto__
                                              0)))
                                           (clojure.core/let
                                            [CATA_RESULT__10889__auto__
                                             (CATA__FN__243502
                                              [?initial-value ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__10889__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__10889__auto__
                                              0)))
                                           ?fold-function
                                           ?form])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10889__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10889__auto__
                                          0)))]
                                      (catch
                                       java.lang.Exception
                                       e__11829__auto__
                                       (if
                                        (meander.runtime.zeta/fail?
                                         e__11829__auto__)
                                        (meander.runtime.zeta/fail)
                                        (throw
                                         e__11829__auto__))))))))))
                               (state__244944))
                              (state__244944)))
                            (state__244944))
                           (state__244944)))))
                       (state__244944)))))
                   (state__244944))))
                (state__244944)))
              (state__244944)))
            (state__244944))
           (state__244944))))
        (state__244944
         []
         (if
          (clojure.core/vector? input__243425)
          (if
           (clojure.core/= (clojure.core/count input__243425) 5)
           (clojure.core/let
            [input__243425_nth_0__
             (clojure.core/nth input__243425 0)
             input__243425_nth_1__
             (clojure.core/nth input__243425 1)
             input__243425_nth_2__
             (clojure.core/nth input__243425 2)
             input__243425_nth_3__
             (clojure.core/nth input__243425 3)
             input__243425_nth_4__
             (clojure.core/nth input__243425 4)]
            (clojure.core/case
             input__243425_nth_0__
             (meander.dev.parse.zeta/make-fold)
             (if
              (clojure.core/map? input__243425_nth_1__)
              (clojure.core/let
               [VAL__244422 (.valAt input__243425_nth_1__ :tag)]
               (clojure.core/case
                VAL__244422
                (:mutable-variable)
                (clojure.core/let
                 [?variable-ast input__243425_nth_1__]
                 (clojure.core/let
                  [?initial-value-ast input__243425_nth_2__]
                  (clojure.core/let
                   [?fold-function input__243425_nth_3__]
                   (clojure.core/let
                    [?form input__243425_nth_4__]
                    (try
                     [{:tag :fold,
                       :variable ?variable-ast,
                       :initial-value ?initial-value-ast,
                       :fold-function
                       {:tag :host-expression, :form ?fold-function},
                       :form ?form}]
                     (catch
                      java.lang.Exception
                      e__11829__auto__
                      (if
                       (meander.runtime.zeta/fail? e__11829__auto__)
                       (meander.runtime.zeta/fail)
                       (throw e__11829__auto__))))))))
                (state__244945)))
              (state__244945))
             (state__244945)))
           (state__244945))
          (state__244945)))
        (state__244945
         []
         (clojure.core/letfn
          [(def__244424
            [arg__244447 ?__243436]
            (clojure.core/letfn
             [(state__245138
               []
               (clojure.core/let
                [x__244448 "meander.zeta"]
                (if
                 (clojure.core/= ?__243436 x__244448)
                 [?__243436]
                 (state__245139))))
              (state__245139
               []
               (if
                (clojure.core/map? arg__244447)
                (clojure.core/let
                 [VAL__244449 (.valAt arg__244447 :aliases)]
                 (if
                  (clojure.core/map? VAL__244449)
                  (clojure.core/let
                   [X__244451 (clojure.core/set VAL__244449)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__244451))
                    (clojure.core/loop
                     [search_space__245140
                      (clojure.core/seq X__244451)]
                     (if
                      (clojure.core/seq search_space__245140)
                      (clojure.core/let
                       [elem__244452
                        (clojure.core/first search_space__245140)
                        result__245141
                        (clojure.core/let
                         [elem__244452_nth_0__
                          (clojure.core/nth elem__244452 0)
                          elem__244452_nth_1__
                          (clojure.core/nth elem__244452 1)]
                         (if
                          (clojure.core/symbol? elem__244452_nth_0__)
                          (clojure.core/let
                           [X__244454
                            (clojure.core/name elem__244452_nth_0__)]
                           (if
                            (clojure.core/= ?__243436 X__244454)
                            (if
                             (clojure.core/symbol?
                              elem__244452_nth_1__)
                             (clojure.core/let
                              [X__244456
                               (clojure.core/name
                                elem__244452_nth_1__)]
                              (clojure.core/case
                               X__244456
                               ("meander.zeta")
                               [?__243436]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__245141)
                        (recur
                         (clojure.core/next search_space__245140))
                        result__245141))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__245138)))]
          (if
           (clojure.core/vector? input__243425)
           (if
            (clojure.core/= (clojure.core/count input__243425) 2)
            (clojure.core/let
             [input__243425_nth_0__
              (clojure.core/nth input__243425 0)
              input__243425_nth_1__
              (clojure.core/nth input__243425 1)]
             (if
              (clojure.core/seq? input__243425_nth_0__)
              (clojure.core/let
               [input__243425_nth_0___l__
                (clojure.core/take 1 input__243425_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__243425_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__243425_nth_0___r__
                  (clojure.core/drop 1 input__243425_nth_0__)]
                 (clojure.core/let
                  [input__243425_nth_0___l___nth_0__
                   (clojure.core/nth input__243425_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__243425_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__244434
                     (clojure.core/namespace
                      input__243425_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__243436 X__244434]
                     (clojure.core/let
                      [X__244436
                       (clojure.core/name
                        input__243425_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__244436
                       ("keyword")
                       (clojure.core/let
                        [x__9586__auto__
                         (def__244424 input__243425_nth_1__ ?__243436)]
                        (if
                         (meander.runtime.zeta/fail? x__9586__auto__)
                         (state__244946)
                         (clojure.core/let
                          [[?__243436] x__9586__auto__]
                          (if
                           (clojure.core/vector? input__243425)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__243425)
                             2)
                            (clojure.core/let
                             [input__243425_nth_0__
                              (clojure.core/nth input__243425 0)
                              input__243425_nth_1__
                              (clojure.core/nth input__243425 1)]
                             (if
                              (clojure.core/seq? input__243425_nth_0__)
                              (clojure.core/let
                               [input__243425_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__243425_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__243425_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__243425_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__243425_nth_0__)]
                                 (clojure.core/let
                                  [?keyword-args
                                   input__243425_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__243425_nth_0__]
                                   (clojure.core/let
                                    [?env input__243425_nth_1__]
                                    (try
                                     [(clojure.core/let
                                       [CATA_RESULT__10889__auto__
                                        (CATA__FN__243502
                                         ['meander.dev.parse.zeta/make-keyword
                                          (clojure.core/into
                                           []
                                           ?keyword-args)
                                          ?form
                                          ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__10889__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__10889__auto__
                                         0)))]
                                     (catch
                                      java.lang.Exception
                                      e__11829__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__11829__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__11829__auto__))))))))
                                (state__244946)))
                              (state__244946)))
                            (state__244946))
                           (state__244946)))))
                       (state__244946)))))
                   (state__244946))))
                (state__244946)))
              (state__244946)))
            (state__244946))
           (state__244946))))
        (state__244946
         []
         (if
          (clojure.core/vector? input__243425)
          (if
           (clojure.core/= (clojure.core/count input__243425) 4)
           (clojure.core/let
            [input__243425_nth_0__
             (clojure.core/nth input__243425 0)
             input__243425_nth_1__
             (clojure.core/nth input__243425 1)
             input__243425_nth_2__
             (clojure.core/nth input__243425 2)]
            (clojure.core/letfn
             [(state__245143
               []
               (clojure.core/case
                input__243425_nth_0__
                (meander.dev.parse.zeta/make-keyword)
                (if
                 (clojure.core/vector? input__243425_nth_1__)
                 (clojure.core/case
                  input__243425_nth_1__
                  ([])
                  (clojure.core/let
                   [?form input__243425_nth_2__]
                   (try
                    [{:tag :keyword,
                      :namespace {:tag :wildcard},
                      :name {:tag :wildcard},
                      :form ?form}]
                    (catch
                     java.lang.Exception
                     e__11829__auto__
                     (if
                      (meander.runtime.zeta/fail? e__11829__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__11829__auto__)))))
                  (state__245144))
                 (state__245144))
                (state__245144)))
              (state__245144
               []
               (clojure.core/let
                [input__243425_nth_3__
                 (clojure.core/nth input__243425 3)]
                (clojure.core/case
                 input__243425_nth_0__
                 (meander.dev.parse.zeta/make-keyword)
                 (if
                  (clojure.core/vector? input__243425_nth_1__)
                  (clojure.core/letfn
                   [(state__245145
                     []
                     (if
                      (clojure.core/=
                       (clojure.core/count input__243425_nth_1__)
                       1)
                      (clojure.core/let
                       [input__243425_nth_1___nth_0__
                        (clojure.core/nth input__243425_nth_1__ 0)]
                       (clojure.core/let
                        [?name input__243425_nth_1___nth_0__]
                        (clojure.core/let
                         [?form input__243425_nth_2__]
                         (clojure.core/let
                          [?env input__243425_nth_3__]
                          (try
                           [{:tag :keyword,
                             :namespace {:tag :wildcard},
                             :name
                             (clojure.core/let
                              [CATA_RESULT__10889__auto__
                               (CATA__FN__243502 [?name ?env])]
                              (if
                               (meander.runtime.zeta/fail?
                                CATA_RESULT__10889__auto__)
                               (throw (meander.runtime.zeta/fail))
                               (clojure.core/nth
                                CATA_RESULT__10889__auto__
                                0))),
                             :form ?form}]
                           (catch
                            java.lang.Exception
                            e__11829__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__11829__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__11829__auto__))))))))
                      (state__245146)))
                    (state__245146
                     []
                     (if
                      (clojure.core/=
                       (clojure.core/count input__243425_nth_1__)
                       2)
                      (clojure.core/let
                       [input__243425_nth_1___nth_0__
                        (clojure.core/nth input__243425_nth_1__ 0)
                        input__243425_nth_1___nth_1__
                        (clojure.core/nth input__243425_nth_1__ 1)]
                       (clojure.core/let
                        [?namespace input__243425_nth_1___nth_0__]
                        (clojure.core/let
                         [?name input__243425_nth_1___nth_1__]
                         (clojure.core/let
                          [?form input__243425_nth_2__]
                          (clojure.core/let
                           [?env input__243425_nth_3__]
                           (try
                            [{:tag :keyword,
                              :namespace
                              (clojure.core/let
                               [CATA_RESULT__10889__auto__
                                (CATA__FN__243502 [?namespace ?env])]
                               (if
                                (meander.runtime.zeta/fail?
                                 CATA_RESULT__10889__auto__)
                                (throw (meander.runtime.zeta/fail))
                                (clojure.core/nth
                                 CATA_RESULT__10889__auto__
                                 0))),
                              :name
                              (clojure.core/let
                               [CATA_RESULT__10889__auto__
                                (CATA__FN__243502 [?name ?env])]
                               (if
                                (meander.runtime.zeta/fail?
                                 CATA_RESULT__10889__auto__)
                                (throw (meander.runtime.zeta/fail))
                                (clojure.core/nth
                                 CATA_RESULT__10889__auto__
                                 0))),
                              :form ?form}]
                            (catch
                             java.lang.Exception
                             e__11829__auto__
                             (if
                              (meander.runtime.zeta/fail?
                               e__11829__auto__)
                              (meander.runtime.zeta/fail)
                              (throw e__11829__auto__)))))))))
                      (state__244947)))]
                   (state__245145))
                  (state__244947))
                 (state__244947))))]
             (state__245143)))
           (state__244947))
          (state__244947)))
        (state__244947
         []
         (clojure.core/letfn
          [(def__244468
            [arg__244491 ?__243437]
            (clojure.core/letfn
             [(state__245147
               []
               (clojure.core/let
                [x__244492 "meander.zeta"]
                (if
                 (clojure.core/= ?__243437 x__244492)
                 [?__243437]
                 (state__245148))))
              (state__245148
               []
               (if
                (clojure.core/map? arg__244491)
                (clojure.core/let
                 [VAL__244493 (.valAt arg__244491 :aliases)]
                 (if
                  (clojure.core/map? VAL__244493)
                  (clojure.core/let
                   [X__244495 (clojure.core/set VAL__244493)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__244495))
                    (clojure.core/loop
                     [search_space__245149
                      (clojure.core/seq X__244495)]
                     (if
                      (clojure.core/seq search_space__245149)
                      (clojure.core/let
                       [elem__244496
                        (clojure.core/first search_space__245149)
                        result__245150
                        (clojure.core/let
                         [elem__244496_nth_0__
                          (clojure.core/nth elem__244496 0)
                          elem__244496_nth_1__
                          (clojure.core/nth elem__244496 1)]
                         (if
                          (clojure.core/symbol? elem__244496_nth_0__)
                          (clojure.core/let
                           [X__244498
                            (clojure.core/name elem__244496_nth_0__)]
                           (if
                            (clojure.core/= ?__243437 X__244498)
                            (if
                             (clojure.core/symbol?
                              elem__244496_nth_1__)
                             (clojure.core/let
                              [X__244500
                               (clojure.core/name
                                elem__244496_nth_1__)]
                              (clojure.core/case
                               X__244500
                               ("meander.zeta")
                               [?__243437]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__245150)
                        (recur
                         (clojure.core/next search_space__245149))
                        result__245150))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__245147)))]
          (if
           (clojure.core/vector? input__243425)
           (if
            (clojure.core/= (clojure.core/count input__243425) 2)
            (clojure.core/let
             [input__243425_nth_0__
              (clojure.core/nth input__243425 0)
              input__243425_nth_1__
              (clojure.core/nth input__243425 1)]
             (if
              (clojure.core/seq? input__243425_nth_0__)
              (clojure.core/let
               [input__243425_nth_0___l__
                (clojure.core/take 1 input__243425_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__243425_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__243425_nth_0___r__
                  (clojure.core/drop 1 input__243425_nth_0__)]
                 (clojure.core/let
                  [input__243425_nth_0___l___nth_0__
                   (clojure.core/nth input__243425_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__243425_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__244478
                     (clojure.core/namespace
                      input__243425_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__243437 X__244478]
                     (clojure.core/let
                      [X__244480
                       (clojure.core/name
                        input__243425_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__244480
                       ("let")
                       (clojure.core/let
                        [x__9586__auto__
                         (def__244468 input__243425_nth_1__ ?__243437)]
                        (if
                         (meander.runtime.zeta/fail? x__9586__auto__)
                         (state__244948)
                         (clojure.core/let
                          [[?__243437] x__9586__auto__]
                          (if
                           (clojure.core/vector? input__243425)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__243425)
                             2)
                            (clojure.core/let
                             [input__243425_nth_0__
                              (clojure.core/nth input__243425 0)
                              input__243425_nth_1__
                              (clojure.core/nth input__243425 1)]
                             (if
                              (clojure.core/seq? input__243425_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__243425_nth_0__)
                                3)
                               (clojure.core/let
                                [input__243425_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__243425_nth_0__
                                  1)
                                 input__243425_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__243425_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?pattern
                                  input__243425_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?expression
                                   input__243425_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__243425_nth_0__]
                                   (clojure.core/let
                                    [?env input__243425_nth_1__]
                                    (try
                                     [{:tag :let,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__10889__auto__
                                         (CATA__FN__243502
                                          [?pattern ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10889__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10889__auto__
                                          0))),
                                       :expression
                                       {:tag :host-expression,
                                        :form ?expression},
                                       :next {:tag :pass}}]
                                     (catch
                                      java.lang.Exception
                                      e__11829__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__11829__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__11829__auto__)))))))))
                               (state__244948))
                              (state__244948)))
                            (state__244948))
                           (state__244948)))))
                       (state__244948)))))
                   (state__244948))))
                (state__244948)))
              (state__244948)))
            (state__244948))
           (state__244948))))
        (state__244948
         []
         (clojure.core/letfn
          [(def__244502
            [arg__244525 ?__243438]
            (clojure.core/letfn
             [(state__245152
               []
               (clojure.core/let
                [x__244526 "meander.zeta"]
                (if
                 (clojure.core/= ?__243438 x__244526)
                 [?__243438]
                 (state__245153))))
              (state__245153
               []
               (if
                (clojure.core/map? arg__244525)
                (clojure.core/let
                 [VAL__244527 (.valAt arg__244525 :aliases)]
                 (if
                  (clojure.core/map? VAL__244527)
                  (clojure.core/let
                   [X__244529 (clojure.core/set VAL__244527)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__244529))
                    (clojure.core/loop
                     [search_space__245154
                      (clojure.core/seq X__244529)]
                     (if
                      (clojure.core/seq search_space__245154)
                      (clojure.core/let
                       [elem__244530
                        (clojure.core/first search_space__245154)
                        result__245155
                        (clojure.core/let
                         [elem__244530_nth_0__
                          (clojure.core/nth elem__244530 0)
                          elem__244530_nth_1__
                          (clojure.core/nth elem__244530 1)]
                         (if
                          (clojure.core/symbol? elem__244530_nth_0__)
                          (clojure.core/let
                           [X__244532
                            (clojure.core/name elem__244530_nth_0__)]
                           (if
                            (clojure.core/= ?__243438 X__244532)
                            (if
                             (clojure.core/symbol?
                              elem__244530_nth_1__)
                             (clojure.core/let
                              [X__244534
                               (clojure.core/name
                                elem__244530_nth_1__)]
                              (clojure.core/case
                               X__244534
                               ("meander.zeta")
                               [?__243438]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__245155)
                        (recur
                         (clojure.core/next search_space__245154))
                        result__245155))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__245152)))]
          (if
           (clojure.core/vector? input__243425)
           (if
            (clojure.core/= (clojure.core/count input__243425) 2)
            (clojure.core/let
             [input__243425_nth_0__
              (clojure.core/nth input__243425 0)
              input__243425_nth_1__
              (clojure.core/nth input__243425 1)]
             (if
              (clojure.core/seq? input__243425_nth_0__)
              (clojure.core/let
               [input__243425_nth_0___l__
                (clojure.core/take 1 input__243425_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__243425_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__243425_nth_0___r__
                  (clojure.core/drop 1 input__243425_nth_0__)]
                 (clojure.core/let
                  [input__243425_nth_0___l___nth_0__
                   (clojure.core/nth input__243425_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__243425_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__244512
                     (clojure.core/namespace
                      input__243425_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__243438 X__244512]
                     (clojure.core/let
                      [X__244514
                       (clojure.core/name
                        input__243425_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__244514
                       ("let")
                       (clojure.core/let
                        [x__9586__auto__
                         (def__244502 input__243425_nth_1__ ?__243438)]
                        (if
                         (meander.runtime.zeta/fail? x__9586__auto__)
                         (state__244949)
                         (clojure.core/let
                          [[?__243438] x__9586__auto__]
                          (if
                           (clojure.core/vector? input__243425)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__243425)
                             2)
                            (clojure.core/let
                             [input__243425_nth_0__
                              (clojure.core/nth input__243425 0)
                              input__243425_nth_1__
                              (clojure.core/nth input__243425 1)]
                             (if
                              (clojure.core/seq? input__243425_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 4)
                                 input__243425_nth_0__)
                                4)
                               (clojure.core/let
                                [input__243425_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__243425_nth_0__
                                  1)
                                 input__243425_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__243425_nth_0__
                                  2)
                                 input__243425_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__243425_nth_0__
                                  3)]
                                (clojure.core/let
                                 [?pattern
                                  input__243425_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?expression
                                   input__243425_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?next
                                    input__243425_nth_0___nth_3__]
                                   (clojure.core/let
                                    [?form input__243425_nth_0__]
                                    (clojure.core/let
                                     [?env input__243425_nth_1__]
                                     (try
                                      [{:tag :let,
                                        :pattern
                                        (clojure.core/let
                                         [CATA_RESULT__10889__auto__
                                          (CATA__FN__243502
                                           [?pattern ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__10889__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__10889__auto__
                                           0))),
                                        :expression
                                        {:tag :host-expression,
                                         :form ?expression},
                                        :next
                                        (clojure.core/let
                                         [CATA_RESULT__10889__auto__
                                          (CATA__FN__243502
                                           [?next ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__10889__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__10889__auto__
                                           0)))}]
                                      (catch
                                       java.lang.Exception
                                       e__11829__auto__
                                       (if
                                        (meander.runtime.zeta/fail?
                                         e__11829__auto__)
                                        (meander.runtime.zeta/fail)
                                        (throw
                                         e__11829__auto__))))))))))
                               (state__244949))
                              (state__244949)))
                            (state__244949))
                           (state__244949)))))
                       (state__244949)))))
                   (state__244949))))
                (state__244949)))
              (state__244949)))
            (state__244949))
           (state__244949))))
        (state__244949
         []
         (clojure.core/letfn
          [(def__244536
            [arg__244559 ?__243439]
            (clojure.core/letfn
             [(state__245157
               []
               (clojure.core/let
                [x__244560 "meander.zeta"]
                (if
                 (clojure.core/= ?__243439 x__244560)
                 [?__243439]
                 (state__245158))))
              (state__245158
               []
               (if
                (clojure.core/map? arg__244559)
                (clojure.core/let
                 [VAL__244561 (.valAt arg__244559 :aliases)]
                 (if
                  (clojure.core/map? VAL__244561)
                  (clojure.core/let
                   [X__244563 (clojure.core/set VAL__244561)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__244563))
                    (clojure.core/loop
                     [search_space__245159
                      (clojure.core/seq X__244563)]
                     (if
                      (clojure.core/seq search_space__245159)
                      (clojure.core/let
                       [elem__244564
                        (clojure.core/first search_space__245159)
                        result__245160
                        (clojure.core/let
                         [elem__244564_nth_0__
                          (clojure.core/nth elem__244564 0)
                          elem__244564_nth_1__
                          (clojure.core/nth elem__244564 1)]
                         (if
                          (clojure.core/symbol? elem__244564_nth_0__)
                          (clojure.core/let
                           [X__244566
                            (clojure.core/name elem__244564_nth_0__)]
                           (if
                            (clojure.core/= ?__243439 X__244566)
                            (if
                             (clojure.core/symbol?
                              elem__244564_nth_1__)
                             (clojure.core/let
                              [X__244568
                               (clojure.core/name
                                elem__244564_nth_1__)]
                              (clojure.core/case
                               X__244568
                               ("meander.zeta")
                               [?__243439]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__245160)
                        (recur
                         (clojure.core/next search_space__245159))
                        result__245160))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__245157)))]
          (if
           (clojure.core/vector? input__243425)
           (if
            (clojure.core/= (clojure.core/count input__243425) 2)
            (clojure.core/let
             [input__243425_nth_0__
              (clojure.core/nth input__243425 0)
              input__243425_nth_1__
              (clojure.core/nth input__243425 1)]
             (if
              (clojure.core/seq? input__243425_nth_0__)
              (clojure.core/let
               [input__243425_nth_0___l__
                (clojure.core/take 1 input__243425_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__243425_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__243425_nth_0___r__
                  (clojure.core/drop 1 input__243425_nth_0__)]
                 (clojure.core/let
                  [input__243425_nth_0___l___nth_0__
                   (clojure.core/nth input__243425_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__243425_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__244546
                     (clojure.core/namespace
                      input__243425_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__243439 X__244546]
                     (clojure.core/let
                      [X__244548
                       (clojure.core/name
                        input__243425_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__244548
                       ("not")
                       (clojure.core/let
                        [x__9586__auto__
                         (def__244536 input__243425_nth_1__ ?__243439)]
                        (if
                         (meander.runtime.zeta/fail? x__9586__auto__)
                         (state__244950)
                         (clojure.core/let
                          [[?__243439] x__9586__auto__]
                          (if
                           (clojure.core/vector? input__243425)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__243425)
                             2)
                            (clojure.core/let
                             [input__243425_nth_0__
                              (clojure.core/nth input__243425 0)
                              input__243425_nth_1__
                              (clojure.core/nth input__243425 1)]
                             (if
                              (clojure.core/seq? input__243425_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__243425_nth_0__)
                                2)
                               (clojure.core/let
                                [input__243425_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__243425_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__243425_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__243425_nth_0__]
                                  (clojure.core/let
                                   [?env input__243425_nth_1__]
                                   (try
                                    [{:tag :not,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__10889__auto__
                                        (CATA__FN__243502
                                         [?pattern ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__10889__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__10889__auto__
                                         0))),
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__11829__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__11829__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__11829__auto__))))))))
                               (state__244950))
                              (state__244950)))
                            (state__244950))
                           (state__244950)))))
                       (state__244950)))))
                   (state__244950))))
                (state__244950)))
              (state__244950)))
            (state__244950))
           (state__244950))))
        (state__244950
         []
         (clojure.core/letfn
          [(def__244570
            [arg__244595 ?__243440]
            (clojure.core/letfn
             [(state__245162
               []
               (clojure.core/let
                [x__244596 "meander.zeta"]
                (if
                 (clojure.core/= ?__243440 x__244596)
                 [?__243440]
                 (state__245163))))
              (state__245163
               []
               (if
                (clojure.core/map? arg__244595)
                (clojure.core/let
                 [VAL__244597 (.valAt arg__244595 :aliases)]
                 (if
                  (clojure.core/map? VAL__244597)
                  (clojure.core/let
                   [X__244599 (clojure.core/set VAL__244597)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__244599))
                    (clojure.core/loop
                     [search_space__245164
                      (clojure.core/seq X__244599)]
                     (if
                      (clojure.core/seq search_space__245164)
                      (clojure.core/let
                       [elem__244600
                        (clojure.core/first search_space__245164)
                        result__245165
                        (clojure.core/let
                         [elem__244600_nth_0__
                          (clojure.core/nth elem__244600 0)
                          elem__244600_nth_1__
                          (clojure.core/nth elem__244600 1)]
                         (if
                          (clojure.core/symbol? elem__244600_nth_0__)
                          (clojure.core/let
                           [X__244602
                            (clojure.core/name elem__244600_nth_0__)]
                           (if
                            (clojure.core/= ?__243440 X__244602)
                            (if
                             (clojure.core/symbol?
                              elem__244600_nth_1__)
                             (clojure.core/let
                              [X__244604
                               (clojure.core/name
                                elem__244600_nth_1__)]
                              (clojure.core/case
                               X__244604
                               ("meander.zeta")
                               [?__243440]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__245165)
                        (recur
                         (clojure.core/next search_space__245164))
                        result__245165))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__245162)))]
          (if
           (clojure.core/vector? input__243425)
           (if
            (clojure.core/= (clojure.core/count input__243425) 2)
            (clojure.core/let
             [input__243425_nth_0__
              (clojure.core/nth input__243425 0)
              input__243425_nth_1__
              (clojure.core/nth input__243425 1)]
             (if
              (clojure.core/seq? input__243425_nth_0__)
              (clojure.core/let
               [input__243425_nth_0___l__
                (clojure.core/take 1 input__243425_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__243425_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__243425_nth_0___r__
                  (clojure.core/drop 1 input__243425_nth_0__)]
                 (clojure.core/let
                  [input__243425_nth_0___l___nth_0__
                   (clojure.core/nth input__243425_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__243425_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__244582
                     (clojure.core/namespace
                      input__243425_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__243440 X__244582]
                     (clojure.core/let
                      [X__244584
                       (clojure.core/name
                        input__243425_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__244584
                       ("or")
                       (clojure.core/let
                        [x__9586__auto__
                         (def__244570 input__243425_nth_1__ ?__243440)]
                        (if
                         (meander.runtime.zeta/fail? x__9586__auto__)
                         (state__244951)
                         (clojure.core/let
                          [[?__243440] x__9586__auto__]
                          (if
                           (clojure.core/vector? input__243425)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__243425)
                             2)
                            (clojure.core/let
                             [input__243425_nth_0__
                              (clojure.core/nth input__243425 0)
                              input__243425_nth_1__
                              (clojure.core/nth input__243425 1)]
                             (if
                              (clojure.core/seq? input__243425_nth_0__)
                              (clojure.core/let
                               [input__243425_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__243425_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__243425_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__243425_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__243425_nth_0__)]
                                 (clojure.core/let
                                  [!forms
                                   (clojure.core/vec
                                    input__243425_nth_0___r__)]
                                  (clojure.core/let
                                   [?form input__243425_nth_0__]
                                   (clojure.core/let
                                    [?env input__243425_nth_1__]
                                    (try
                                     [(clojure.core/let
                                       [!forms__counter
                                        (meander.runtime.zeta/iterator
                                         !forms)]
                                       (clojure.core/let
                                        [CATA_RESULT__10889__auto__
                                         (CATA__FN__243502
                                          ['meander.dev.parse.zeta/make-or
                                           (clojure.core/into
                                            []
                                            (clojure.core/loop
                                             [return__243505
                                              (clojure.core/transient
                                               [])]
                                             (if
                                              (clojure.core/and
                                               (.hasNext
                                                !forms__counter))
                                              (recur
                                               (clojure.core/conj!
                                                return__243505
                                                (clojure.core/let
                                                 [CATA_RESULT__10889__auto__
                                                  (CATA__FN__243502
                                                   [(if
                                                     (.hasNext
                                                      !forms__counter)
                                                     (.next
                                                      !forms__counter))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__10889__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__10889__auto__
                                                   0)))))
                                              (clojure.core/persistent!
                                               return__243505))))
                                           ?form])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10889__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10889__auto__
                                          0))))]
                                     (catch
                                      java.lang.Exception
                                      e__11829__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__11829__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__11829__auto__))))))))
                                (state__244951)))
                              (state__244951)))
                            (state__244951))
                           (state__244951)))))
                       (state__244951)))))
                   (state__244951))))
                (state__244951)))
              (state__244951)))
            (state__244951))
           (state__244951))))
        (state__244951
         []
         (if
          (clojure.core/vector? input__243425)
          (if
           (clojure.core/= (clojure.core/count input__243425) 3)
           (clojure.core/let
            [input__243425_nth_0__
             (clojure.core/nth input__243425 0)
             input__243425_nth_1__
             (clojure.core/nth input__243425 1)
             input__243425_nth_2__
             (clojure.core/nth input__243425 2)]
            (clojure.core/case
             input__243425_nth_0__
             (meander.dev.parse.zeta/make-or)
             (clojure.core/letfn
              [(state__245167
                []
                (if
                 (clojure.core/vector? input__243425_nth_1__)
                 (clojure.core/case
                  input__243425_nth_1__
                  ([])
                  (clojure.core/let
                   [?form input__243425_nth_2__]
                   (try
                    [{:tag :error,
                      :message
                      "meander.zeta/or requires 1 or more arguments",
                      :form ?form}]
                    (catch
                     java.lang.Exception
                     e__11829__auto__
                     (if
                      (meander.runtime.zeta/fail? e__11829__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__11829__auto__)))))
                  (state__245168))
                 (state__245168)))
               (state__245168
                []
                (clojure.core/case
                 input__243425_nth_2__
                 (nil)
                 (if
                  (clojure.core/vector? input__243425_nth_1__)
                  (if
                   (clojure.core/=
                    (clojure.core/count input__243425_nth_1__)
                    1)
                   (clojure.core/let
                    [input__243425_nth_1___nth_0__
                     (clojure.core/nth input__243425_nth_1__ 0)]
                    (clojure.core/let
                     [?ast-a input__243425_nth_1___nth_0__]
                     (try
                      [?ast-a]
                      (catch
                       java.lang.Exception
                       e__11829__auto__
                       (if
                        (meander.runtime.zeta/fail? e__11829__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__11829__auto__))))))
                   (state__245169))
                  (state__245169))
                 (state__245169)))
               (state__245169
                []
                (if
                 (clojure.core/vector? input__243425_nth_1__)
                 (clojure.core/letfn
                  [(state__245170
                    []
                    (if
                     (clojure.core/=
                      (clojure.core/count input__243425_nth_1__)
                      1)
                     (clojure.core/let
                      [input__243425_nth_1___nth_0__
                       (clojure.core/nth input__243425_nth_1__ 0)]
                      (clojure.core/let
                       [?ast-a input__243425_nth_1___nth_0__]
                       (clojure.core/let
                        [?form input__243425_nth_2__]
                        (try
                         [(clojure.core/let
                           [CATA_RESULT__10889__auto__
                            (CATA__FN__243502
                             ['meander.dev.parse.zeta/make-or
                              [?ast-a {:tag :pass}]
                              ?form])]
                           (if
                            (meander.runtime.zeta/fail?
                             CATA_RESULT__10889__auto__)
                            (throw (meander.runtime.zeta/fail))
                            (clojure.core/nth
                             CATA_RESULT__10889__auto__
                             0)))]
                         (catch
                          java.lang.Exception
                          e__11829__auto__
                          (if
                           (meander.runtime.zeta/fail?
                            e__11829__auto__)
                           (meander.runtime.zeta/fail)
                           (throw e__11829__auto__)))))))
                     (state__245171)))
                   (state__245171
                    []
                    (if
                     (clojure.core/=
                      (clojure.core/count input__243425_nth_1__)
                      2)
                     (clojure.core/let
                      [input__243425_nth_1___nth_0__
                       (clojure.core/nth input__243425_nth_1__ 0)
                       input__243425_nth_1___nth_1__
                       (clojure.core/nth input__243425_nth_1__ 1)]
                      (clojure.core/let
                       [?ast-a input__243425_nth_1___nth_0__]
                       (clojure.core/let
                        [?ast-b input__243425_nth_1___nth_1__]
                        (clojure.core/let
                         [?form input__243425_nth_2__]
                         (try
                          [{:tag :or,
                            :left ?ast-a,
                            :right ?ast-b,
                            :form ?form}]
                          (catch
                           java.lang.Exception
                           e__11829__auto__
                           (if
                            (meander.runtime.zeta/fail?
                             e__11829__auto__)
                            (meander.runtime.zeta/fail)
                            (throw e__11829__auto__))))))))
                     (state__245172)))
                   (state__245172
                    []
                    (clojure.core/loop
                     [search_space__245173
                      (meander.runtime.zeta/epsilon-partitions
                       2
                       input__243425_nth_1__)]
                     (if
                      (clojure.core/seq search_space__245173)
                      (clojure.core/let
                       [input__243425_nth_1___parts__
                        (clojure.core/first search_space__245173)
                        result__245174
                        (clojure.core/let
                         [input__243425_nth_1___l__
                          (clojure.core/nth
                           input__243425_nth_1___parts__
                           0)
                          input__243425_nth_1___r__
                          (clojure.core/nth
                           input__243425_nth_1___parts__
                           1)]
                         (clojure.core/letfn
                          [(state__245176
                            []
                            (clojure.core/let
                             [!asts-1 []]
                             (clojure.core/let
                              [ret__9750__auto__
                               (meander.runtime.zeta/epsilon-run-star-1
                                input__243425_nth_1___l__
                                [!asts-1]
                                (clojure.core/fn
                                 [[!asts-1] input__244631]
                                 (clojure.core/let
                                  [input__244631_nth_0__
                                   (clojure.core/nth input__244631 0)]
                                  (clojure.core/let
                                   [!asts-1
                                    (clojure.core/conj
                                     !asts-1
                                     input__244631_nth_0__)]
                                   [!asts-1])))
                                (clojure.core/fn
                                 [[!asts-1]]
                                 (clojure.core/let
                                  [X__244624
                                   (clojure.core/count
                                    input__243425_nth_1___l__)]
                                  (clojure.core/let
                                   [?n X__244624]
                                   (clojure.core/let
                                    [X__244628
                                     (clojure.core/count
                                      input__243425_nth_1___r__)]
                                    (if
                                     (clojure.core/= ?n X__244628)
                                     (clojure.core/let
                                      [!asts-2 []]
                                      (clojure.core/let
                                       [ret__9750__auto__
                                        (meander.runtime.zeta/epsilon-run-star-1
                                         input__243425_nth_1___r__
                                         [!asts-2 !asts-1]
                                         (clojure.core/fn
                                          [[!asts-2 !asts-1]
                                           input__244629]
                                          (clojure.core/let
                                           [input__244629_nth_0__
                                            (clojure.core/nth
                                             input__244629
                                             0)]
                                           (clojure.core/let
                                            [!asts-2
                                             (clojure.core/conj
                                              !asts-2
                                              input__244629_nth_0__)]
                                            [!asts-2 !asts-1])))
                                         (clojure.core/fn
                                          [[!asts-2 !asts-1]]
                                          (clojure.core/let
                                           [?form
                                            input__243425_nth_2__]
                                           (try
                                            [(clojure.core/let
                                              [!asts-1__counter
                                               (meander.runtime.zeta/iterator
                                                !asts-1)
                                               !asts-2__counter
                                               (meander.runtime.zeta/iterator
                                                !asts-2)]
                                              (clojure.core/let
                                               [CATA_RESULT__10889__auto__
                                                (CATA__FN__243502
                                                 ['meander.dev.parse.zeta/make-or
                                                  [(clojure.core/let
                                                    [CATA_RESULT__10889__auto__
                                                     (CATA__FN__243502
                                                      ['meander.dev.parse.zeta/make-or
                                                       (clojure.core/into
                                                        []
                                                        (clojure.core/vec
                                                         (clojure.core/iterator-seq
                                                          !asts-1__counter)))
                                                       nil])]
                                                    (if
                                                     (meander.runtime.zeta/fail?
                                                      CATA_RESULT__10889__auto__)
                                                     (throw
                                                      (meander.runtime.zeta/fail))
                                                     (clojure.core/nth
                                                      CATA_RESULT__10889__auto__
                                                      0)))
                                                   (clojure.core/let
                                                    [CATA_RESULT__10889__auto__
                                                     (CATA__FN__243502
                                                      ['meander.dev.parse.zeta/make-or
                                                       (clojure.core/into
                                                        []
                                                        (clojure.core/vec
                                                         (clojure.core/iterator-seq
                                                          !asts-2__counter)))
                                                       nil])]
                                                    (if
                                                     (meander.runtime.zeta/fail?
                                                      CATA_RESULT__10889__auto__)
                                                     (throw
                                                      (meander.runtime.zeta/fail))
                                                     (clojure.core/nth
                                                      CATA_RESULT__10889__auto__
                                                      0)))]
                                                  ?form])]
                                               (if
                                                (meander.runtime.zeta/fail?
                                                 CATA_RESULT__10889__auto__)
                                                (throw
                                                 (meander.runtime.zeta/fail))
                                                (clojure.core/nth
                                                 CATA_RESULT__10889__auto__
                                                 0))))]
                                            (catch
                                             java.lang.Exception
                                             e__11829__auto__
                                             (if
                                              (meander.runtime.zeta/fail?
                                               e__11829__auto__)
                                              (meander.runtime.zeta/fail)
                                              (throw
                                               e__11829__auto__)))))))]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         ret__9750__auto__)
                                        (state__245177)
                                        ret__9750__auto__)))
                                     (state__245177)))))))]
                              (if
                               (meander.runtime.zeta/fail?
                                ret__9750__auto__)
                               (state__245177)
                               ret__9750__auto__))))
                           (state__245177
                            []
                            (clojure.core/let
                             [!asts-1 []]
                             (clojure.core/let
                              [ret__9750__auto__
                               (meander.runtime.zeta/epsilon-run-star-1
                                input__243425_nth_1___l__
                                [!asts-1]
                                (clojure.core/fn
                                 [[!asts-1] input__244647]
                                 (clojure.core/let
                                  [input__244647_nth_0__
                                   (clojure.core/nth input__244647 0)]
                                  (clojure.core/let
                                   [!asts-1
                                    (clojure.core/conj
                                     !asts-1
                                     input__244647_nth_0__)]
                                   [!asts-1])))
                                (clojure.core/fn
                                 [[!asts-1]]
                                 (clojure.core/let
                                  [X__244638
                                   (clojure.core/count
                                    input__243425_nth_1___l__)]
                                  (clojure.core/let
                                   [?n X__244638]
                                   (clojure.core/let
                                    [input__243425_nth_1___r___l__
                                     (clojure.core/subvec
                                      input__243425_nth_1___r__
                                      0
                                      (clojure.core/min
                                       (clojure.core/count
                                        input__243425_nth_1___r__)
                                       1))]
                                    (if
                                     (clojure.core/=
                                      (clojure.core/count
                                       input__243425_nth_1___r___l__)
                                      1)
                                     (clojure.core/let
                                      [input__243425_nth_1___r___r__
                                       (clojure.core/subvec
                                        input__243425_nth_1___r__
                                        1)]
                                      (clojure.core/let
                                       [input__243425_nth_1___r___l___nth_0__
                                        (clojure.core/nth
                                         input__243425_nth_1___r___l__
                                         0)]
                                       (clojure.core/let
                                        [?ast
                                         input__243425_nth_1___r___l___nth_0__]
                                        (clojure.core/let
                                         [X__244644
                                          (clojure.core/count
                                           input__243425_nth_1___r___r__)]
                                         (if
                                          (clojure.core/= ?n X__244644)
                                          (clojure.core/let
                                           [!asts-2 []]
                                           (clojure.core/let
                                            [ret__9750__auto__
                                             (meander.runtime.zeta/epsilon-run-star-1
                                              input__243425_nth_1___r___r__
                                              [!asts-2 !asts-1]
                                              (clojure.core/fn
                                               [[!asts-2 !asts-1]
                                                input__244645]
                                               (clojure.core/let
                                                [input__244645_nth_0__
                                                 (clojure.core/nth
                                                  input__244645
                                                  0)]
                                                (clojure.core/let
                                                 [!asts-2
                                                  (clojure.core/conj
                                                   !asts-2
                                                   input__244645_nth_0__)]
                                                 [!asts-2 !asts-1])))
                                              (clojure.core/fn
                                               [[!asts-2 !asts-1]]
                                               (clojure.core/let
                                                [?form
                                                 input__243425_nth_2__]
                                                (try
                                                 [(clojure.core/let
                                                   [!asts-1__counter
                                                    (meander.runtime.zeta/iterator
                                                     !asts-1)
                                                    !asts-2__counter
                                                    (meander.runtime.zeta/iterator
                                                     !asts-2)]
                                                   (clojure.core/let
                                                    [CATA_RESULT__10889__auto__
                                                     (CATA__FN__243502
                                                      ['meander.dev.parse.zeta/make-or
                                                       [(clojure.core/let
                                                         [CATA_RESULT__10889__auto__
                                                          (CATA__FN__243502
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
                                                           CATA_RESULT__10889__auto__)
                                                          (throw
                                                           (meander.runtime.zeta/fail))
                                                          (clojure.core/nth
                                                           CATA_RESULT__10889__auto__
                                                           0)))
                                                        (clojure.core/let
                                                         [CATA_RESULT__10889__auto__
                                                          (CATA__FN__243502
                                                           ['meander.dev.parse.zeta/make-or
                                                            (clojure.core/into
                                                             []
                                                             (clojure.core/vec
                                                              (clojure.core/iterator-seq
                                                               !asts-2__counter)))
                                                            nil])]
                                                         (if
                                                          (meander.runtime.zeta/fail?
                                                           CATA_RESULT__10889__auto__)
                                                          (throw
                                                           (meander.runtime.zeta/fail))
                                                          (clojure.core/nth
                                                           CATA_RESULT__10889__auto__
                                                           0)))]
                                                       ?form])]
                                                    (if
                                                     (meander.runtime.zeta/fail?
                                                      CATA_RESULT__10889__auto__)
                                                     (throw
                                                      (meander.runtime.zeta/fail))
                                                     (clojure.core/nth
                                                      CATA_RESULT__10889__auto__
                                                      0))))]
                                                 (catch
                                                  java.lang.Exception
                                                  e__11829__auto__
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    e__11829__auto__)
                                                   (meander.runtime.zeta/fail)
                                                   (throw
                                                    e__11829__auto__)))))))]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              ret__9750__auto__)
                                             (meander.runtime.zeta/fail)
                                             ret__9750__auto__)))
                                          (meander.runtime.zeta/fail))))))
                                     (meander.runtime.zeta/fail)))))))]
                              (if
                               (meander.runtime.zeta/fail?
                                ret__9750__auto__)
                               (meander.runtime.zeta/fail)
                               ret__9750__auto__))))]
                          (state__245176)))]
                       (if
                        (meander.runtime.zeta/fail? result__245174)
                        (recur
                         (clojure.core/next search_space__245173))
                        result__245174))
                      (state__244952))))]
                  (state__245170))
                 (state__244952)))]
              (state__245167))
             (state__244952)))
           (state__244952))
          (state__244952)))
        (state__244952
         []
         (clojure.core/letfn
          [(def__244650
            [arg__244673 ?__243441]
            (clojure.core/letfn
             [(state__245182
               []
               (clojure.core/let
                [x__244674 "meander.zeta"]
                (if
                 (clojure.core/= ?__243441 x__244674)
                 [?__243441]
                 (state__245183))))
              (state__245183
               []
               (if
                (clojure.core/map? arg__244673)
                (clojure.core/let
                 [VAL__244675 (.valAt arg__244673 :aliases)]
                 (if
                  (clojure.core/map? VAL__244675)
                  (clojure.core/let
                   [X__244677 (clojure.core/set VAL__244675)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__244677))
                    (clojure.core/loop
                     [search_space__245184
                      (clojure.core/seq X__244677)]
                     (if
                      (clojure.core/seq search_space__245184)
                      (clojure.core/let
                       [elem__244678
                        (clojure.core/first search_space__245184)
                        result__245185
                        (clojure.core/let
                         [elem__244678_nth_0__
                          (clojure.core/nth elem__244678 0)
                          elem__244678_nth_1__
                          (clojure.core/nth elem__244678 1)]
                         (if
                          (clojure.core/symbol? elem__244678_nth_0__)
                          (clojure.core/let
                           [X__244680
                            (clojure.core/name elem__244678_nth_0__)]
                           (if
                            (clojure.core/= ?__243441 X__244680)
                            (if
                             (clojure.core/symbol?
                              elem__244678_nth_1__)
                             (clojure.core/let
                              [X__244682
                               (clojure.core/name
                                elem__244678_nth_1__)]
                              (clojure.core/case
                               X__244682
                               ("meander.zeta")
                               [?__243441]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__245185)
                        (recur
                         (clojure.core/next search_space__245184))
                        result__245185))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__245182)))]
          (if
           (clojure.core/vector? input__243425)
           (if
            (clojure.core/= (clojure.core/count input__243425) 2)
            (clojure.core/let
             [input__243425_nth_0__
              (clojure.core/nth input__243425 0)
              input__243425_nth_1__
              (clojure.core/nth input__243425 1)]
             (if
              (clojure.core/seq? input__243425_nth_0__)
              (clojure.core/let
               [input__243425_nth_0___l__
                (clojure.core/take 1 input__243425_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__243425_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__243425_nth_0___r__
                  (clojure.core/drop 1 input__243425_nth_0__)]
                 (clojure.core/let
                  [input__243425_nth_0___l___nth_0__
                   (clojure.core/nth input__243425_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__243425_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__244660
                     (clojure.core/namespace
                      input__243425_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__243441 X__244660]
                     (clojure.core/let
                      [X__244662
                       (clojure.core/name
                        input__243425_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__244662
                       ("pred")
                       (clojure.core/let
                        [x__9586__auto__
                         (def__244650 input__243425_nth_1__ ?__243441)]
                        (if
                         (meander.runtime.zeta/fail? x__9586__auto__)
                         (state__244953)
                         (clojure.core/let
                          [[?__243441] x__9586__auto__]
                          (if
                           (clojure.core/vector? input__243425)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__243425)
                             2)
                            (clojure.core/let
                             [input__243425_nth_0__
                              (clojure.core/nth input__243425 0)
                              input__243425_nth_1__
                              (clojure.core/nth input__243425 1)]
                             (if
                              (clojure.core/seq? input__243425_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__243425_nth_0__)
                                2)
                               (clojure.core/let
                                [input__243425_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__243425_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?expression
                                  input__243425_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__243425_nth_0__]
                                  (clojure.core/let
                                   [?env input__243425_nth_1__]
                                   (try
                                    [{:tag :pred,
                                      :expression
                                      {:tag :host-expression,
                                       :form ?expression},
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__11829__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__11829__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__11829__auto__))))))))
                               (state__244953))
                              (state__244953)))
                            (state__244953))
                           (state__244953)))))
                       (state__244953)))))
                   (state__244953))))
                (state__244953)))
              (state__244953)))
            (state__244953))
           (state__244953))))
        (state__244953
         []
         (clojure.core/letfn
          [(def__244684
            [arg__244707 ?__243442]
            (clojure.core/letfn
             [(state__245187
               []
               (clojure.core/let
                [x__244708 "meander.zeta"]
                (if
                 (clojure.core/= ?__243442 x__244708)
                 [?__243442]
                 (state__245188))))
              (state__245188
               []
               (if
                (clojure.core/map? arg__244707)
                (clojure.core/let
                 [VAL__244709 (.valAt arg__244707 :aliases)]
                 (if
                  (clojure.core/map? VAL__244709)
                  (clojure.core/let
                   [X__244711 (clojure.core/set VAL__244709)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__244711))
                    (clojure.core/loop
                     [search_space__245189
                      (clojure.core/seq X__244711)]
                     (if
                      (clojure.core/seq search_space__245189)
                      (clojure.core/let
                       [elem__244712
                        (clojure.core/first search_space__245189)
                        result__245190
                        (clojure.core/let
                         [elem__244712_nth_0__
                          (clojure.core/nth elem__244712 0)
                          elem__244712_nth_1__
                          (clojure.core/nth elem__244712 1)]
                         (if
                          (clojure.core/symbol? elem__244712_nth_0__)
                          (clojure.core/let
                           [X__244714
                            (clojure.core/name elem__244712_nth_0__)]
                           (if
                            (clojure.core/= ?__243442 X__244714)
                            (if
                             (clojure.core/symbol?
                              elem__244712_nth_1__)
                             (clojure.core/let
                              [X__244716
                               (clojure.core/name
                                elem__244712_nth_1__)]
                              (clojure.core/case
                               X__244716
                               ("meander.zeta")
                               [?__243442]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__245190)
                        (recur
                         (clojure.core/next search_space__245189))
                        result__245190))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__245187)))]
          (if
           (clojure.core/vector? input__243425)
           (if
            (clojure.core/= (clojure.core/count input__243425) 2)
            (clojure.core/let
             [input__243425_nth_0__
              (clojure.core/nth input__243425 0)
              input__243425_nth_1__
              (clojure.core/nth input__243425 1)]
             (if
              (clojure.core/seq? input__243425_nth_0__)
              (clojure.core/let
               [input__243425_nth_0___l__
                (clojure.core/take 1 input__243425_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__243425_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__243425_nth_0___r__
                  (clojure.core/drop 1 input__243425_nth_0__)]
                 (clojure.core/let
                  [input__243425_nth_0___l___nth_0__
                   (clojure.core/nth input__243425_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__243425_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__244694
                     (clojure.core/namespace
                      input__243425_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__243442 X__244694]
                     (clojure.core/let
                      [X__244696
                       (clojure.core/name
                        input__243425_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__244696
                       ("pred")
                       (clojure.core/let
                        [x__9586__auto__
                         (def__244684 input__243425_nth_1__ ?__243442)]
                        (if
                         (meander.runtime.zeta/fail? x__9586__auto__)
                         (state__244954)
                         (clojure.core/let
                          [[?__243442] x__9586__auto__]
                          (if
                           (clojure.core/vector? input__243425)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__243425)
                             2)
                            (clojure.core/let
                             [input__243425_nth_0__
                              (clojure.core/nth input__243425 0)
                              input__243425_nth_1__
                              (clojure.core/nth input__243425 1)]
                             (if
                              (clojure.core/seq? input__243425_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__243425_nth_0__)
                                3)
                               (clojure.core/let
                                [input__243425_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__243425_nth_0__
                                  1)
                                 input__243425_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__243425_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?expression
                                  input__243425_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?pattern
                                   input__243425_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__243425_nth_0__]
                                   (clojure.core/let
                                    [?env input__243425_nth_1__]
                                    (try
                                     [{:tag :pred,
                                       :expression
                                       {:tag :host-expression,
                                        :form ?expression},
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__10889__auto__
                                         (CATA__FN__243502
                                          [?pattern ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10889__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10889__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__11829__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__11829__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__11829__auto__)))))))))
                               (state__244954))
                              (state__244954)))
                            (state__244954))
                           (state__244954)))))
                       (state__244954)))))
                   (state__244954))))
                (state__244954)))
              (state__244954)))
            (state__244954))
           (state__244954))))
        (state__244954
         []
         (clojure.core/letfn
          [(def__244718
            [arg__244741 ?__243443]
            (clojure.core/letfn
             [(state__245192
               []
               (clojure.core/let
                [x__244742 "meander.zeta"]
                (if
                 (clojure.core/= ?__243443 x__244742)
                 [?__243443]
                 (state__245193))))
              (state__245193
               []
               (if
                (clojure.core/map? arg__244741)
                (clojure.core/let
                 [VAL__244743 (.valAt arg__244741 :aliases)]
                 (if
                  (clojure.core/map? VAL__244743)
                  (clojure.core/let
                   [X__244745 (clojure.core/set VAL__244743)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__244745))
                    (clojure.core/loop
                     [search_space__245194
                      (clojure.core/seq X__244745)]
                     (if
                      (clojure.core/seq search_space__245194)
                      (clojure.core/let
                       [elem__244746
                        (clojure.core/first search_space__245194)
                        result__245195
                        (clojure.core/let
                         [elem__244746_nth_0__
                          (clojure.core/nth elem__244746 0)
                          elem__244746_nth_1__
                          (clojure.core/nth elem__244746 1)]
                         (if
                          (clojure.core/symbol? elem__244746_nth_0__)
                          (clojure.core/let
                           [X__244748
                            (clojure.core/name elem__244746_nth_0__)]
                           (if
                            (clojure.core/= ?__243443 X__244748)
                            (if
                             (clojure.core/symbol?
                              elem__244746_nth_1__)
                             (clojure.core/let
                              [X__244750
                               (clojure.core/name
                                elem__244746_nth_1__)]
                              (clojure.core/case
                               X__244750
                               ("meander.zeta")
                               [?__243443]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__245195)
                        (recur
                         (clojure.core/next search_space__245194))
                        result__245195))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__245192)))]
          (if
           (clojure.core/vector? input__243425)
           (if
            (clojure.core/= (clojure.core/count input__243425) 2)
            (clojure.core/let
             [input__243425_nth_0__
              (clojure.core/nth input__243425 0)
              input__243425_nth_1__
              (clojure.core/nth input__243425 1)]
             (if
              (clojure.core/seq? input__243425_nth_0__)
              (clojure.core/let
               [input__243425_nth_0___l__
                (clojure.core/take 1 input__243425_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__243425_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__243425_nth_0___r__
                  (clojure.core/drop 1 input__243425_nth_0__)]
                 (clojure.core/let
                  [input__243425_nth_0___l___nth_0__
                   (clojure.core/nth input__243425_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__243425_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__244728
                     (clojure.core/namespace
                      input__243425_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__243443 X__244728]
                     (clojure.core/let
                      [X__244730
                       (clojure.core/name
                        input__243425_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__244730
                       ("re")
                       (clojure.core/let
                        [x__9586__auto__
                         (def__244718 input__243425_nth_1__ ?__243443)]
                        (if
                         (meander.runtime.zeta/fail? x__9586__auto__)
                         (state__244955)
                         (clojure.core/let
                          [[?__243443] x__9586__auto__]
                          (if
                           (clojure.core/vector? input__243425)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__243425)
                             2)
                            (clojure.core/let
                             [input__243425_nth_0__
                              (clojure.core/nth input__243425 0)
                              input__243425_nth_1__
                              (clojure.core/nth input__243425 1)]
                             (if
                              (clojure.core/seq? input__243425_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__243425_nth_0__)
                                2)
                               (clojure.core/let
                                [input__243425_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__243425_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?regex input__243425_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__243425_nth_0__]
                                  (clojure.core/let
                                   [?env input__243425_nth_1__]
                                   (try
                                    [{:tag :regex,
                                      :regex ?regex,
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__11829__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__11829__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__11829__auto__))))))))
                               (state__244955))
                              (state__244955)))
                            (state__244955))
                           (state__244955)))))
                       (state__244955)))))
                   (state__244955))))
                (state__244955)))
              (state__244955)))
            (state__244955))
           (state__244955))))
        (state__244955
         []
         (clojure.core/letfn
          [(def__244752
            [arg__244775 ?__243444]
            (clojure.core/letfn
             [(state__245197
               []
               (clojure.core/let
                [x__244776 "meander.zeta"]
                (if
                 (clojure.core/= ?__243444 x__244776)
                 [?__243444]
                 (state__245198))))
              (state__245198
               []
               (if
                (clojure.core/map? arg__244775)
                (clojure.core/let
                 [VAL__244777 (.valAt arg__244775 :aliases)]
                 (if
                  (clojure.core/map? VAL__244777)
                  (clojure.core/let
                   [X__244779 (clojure.core/set VAL__244777)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__244779))
                    (clojure.core/loop
                     [search_space__245199
                      (clojure.core/seq X__244779)]
                     (if
                      (clojure.core/seq search_space__245199)
                      (clojure.core/let
                       [elem__244780
                        (clojure.core/first search_space__245199)
                        result__245200
                        (clojure.core/let
                         [elem__244780_nth_0__
                          (clojure.core/nth elem__244780 0)
                          elem__244780_nth_1__
                          (clojure.core/nth elem__244780 1)]
                         (if
                          (clojure.core/symbol? elem__244780_nth_0__)
                          (clojure.core/let
                           [X__244782
                            (clojure.core/name elem__244780_nth_0__)]
                           (if
                            (clojure.core/= ?__243444 X__244782)
                            (if
                             (clojure.core/symbol?
                              elem__244780_nth_1__)
                             (clojure.core/let
                              [X__244784
                               (clojure.core/name
                                elem__244780_nth_1__)]
                              (clojure.core/case
                               X__244784
                               ("meander.zeta")
                               [?__243444]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__245200)
                        (recur
                         (clojure.core/next search_space__245199))
                        result__245200))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__245197)))]
          (if
           (clojure.core/vector? input__243425)
           (if
            (clojure.core/= (clojure.core/count input__243425) 2)
            (clojure.core/let
             [input__243425_nth_0__
              (clojure.core/nth input__243425 0)
              input__243425_nth_1__
              (clojure.core/nth input__243425 1)]
             (if
              (clojure.core/seq? input__243425_nth_0__)
              (clojure.core/let
               [input__243425_nth_0___l__
                (clojure.core/take 1 input__243425_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__243425_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__243425_nth_0___r__
                  (clojure.core/drop 1 input__243425_nth_0__)]
                 (clojure.core/let
                  [input__243425_nth_0___l___nth_0__
                   (clojure.core/nth input__243425_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__243425_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__244762
                     (clojure.core/namespace
                      input__243425_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__243444 X__244762]
                     (clojure.core/let
                      [X__244764
                       (clojure.core/name
                        input__243425_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__244764
                       ("re")
                       (clojure.core/let
                        [x__9586__auto__
                         (def__244752 input__243425_nth_1__ ?__243444)]
                        (if
                         (meander.runtime.zeta/fail? x__9586__auto__)
                         (state__244956)
                         (clojure.core/let
                          [[?__243444] x__9586__auto__]
                          (if
                           (clojure.core/vector? input__243425)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__243425)
                             2)
                            (clojure.core/let
                             [input__243425_nth_0__
                              (clojure.core/nth input__243425 0)
                              input__243425_nth_1__
                              (clojure.core/nth input__243425 1)]
                             (if
                              (clojure.core/seq? input__243425_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__243425_nth_0__)
                                3)
                               (clojure.core/let
                                [input__243425_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__243425_nth_0__
                                  1)
                                 input__243425_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__243425_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?regex input__243425_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?capture
                                   input__243425_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__243425_nth_0__]
                                   (clojure.core/let
                                    [?env input__243425_nth_1__]
                                    (try
                                     [{:tag :regex-with-capture,
                                       :regex ?regex,
                                       :capture
                                       (clojure.core/let
                                        [CATA_RESULT__10889__auto__
                                         (CATA__FN__243502
                                          [?capture ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10889__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10889__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__11829__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__11829__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__11829__auto__)))))))))
                               (state__244956))
                              (state__244956)))
                            (state__244956))
                           (state__244956)))))
                       (state__244956)))))
                   (state__244956))))
                (state__244956)))
              (state__244956)))
            (state__244956))
           (state__244956))))
        (state__244956
         []
         (clojure.core/letfn
          [(def__244786
            [arg__244809 ?__243445]
            (clojure.core/letfn
             [(state__245202
               []
               (clojure.core/let
                [x__244810 "meander.zeta"]
                (if
                 (clojure.core/= ?__243445 x__244810)
                 [?__243445]
                 (state__245203))))
              (state__245203
               []
               (if
                (clojure.core/map? arg__244809)
                (clojure.core/let
                 [VAL__244811 (.valAt arg__244809 :aliases)]
                 (if
                  (clojure.core/map? VAL__244811)
                  (clojure.core/let
                   [X__244813 (clojure.core/set VAL__244811)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__244813))
                    (clojure.core/loop
                     [search_space__245204
                      (clojure.core/seq X__244813)]
                     (if
                      (clojure.core/seq search_space__245204)
                      (clojure.core/let
                       [elem__244814
                        (clojure.core/first search_space__245204)
                        result__245205
                        (clojure.core/let
                         [elem__244814_nth_0__
                          (clojure.core/nth elem__244814 0)
                          elem__244814_nth_1__
                          (clojure.core/nth elem__244814 1)]
                         (if
                          (clojure.core/symbol? elem__244814_nth_0__)
                          (clojure.core/let
                           [X__244816
                            (clojure.core/name elem__244814_nth_0__)]
                           (if
                            (clojure.core/= ?__243445 X__244816)
                            (if
                             (clojure.core/symbol?
                              elem__244814_nth_1__)
                             (clojure.core/let
                              [X__244818
                               (clojure.core/name
                                elem__244814_nth_1__)]
                              (clojure.core/case
                               X__244818
                               ("meander.zeta")
                               [?__243445]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__245205)
                        (recur
                         (clojure.core/next search_space__245204))
                        result__245205))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__245202)))]
          (if
           (clojure.core/vector? input__243425)
           (if
            (clojure.core/= (clojure.core/count input__243425) 2)
            (clojure.core/let
             [input__243425_nth_0__
              (clojure.core/nth input__243425 0)
              input__243425_nth_1__
              (clojure.core/nth input__243425 1)]
             (if
              (clojure.core/seq? input__243425_nth_0__)
              (clojure.core/let
               [input__243425_nth_0___l__
                (clojure.core/take 1 input__243425_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__243425_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__243425_nth_0___r__
                  (clojure.core/drop 1 input__243425_nth_0__)]
                 (clojure.core/let
                  [input__243425_nth_0___l___nth_0__
                   (clojure.core/nth input__243425_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__243425_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__244796
                     (clojure.core/namespace
                      input__243425_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__243445 X__244796]
                     (clojure.core/let
                      [X__244798
                       (clojure.core/name
                        input__243425_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__244798
                       ("string")
                       (clojure.core/let
                        [x__9586__auto__
                         (def__244786 input__243425_nth_1__ ?__243445)]
                        (if
                         (meander.runtime.zeta/fail? x__9586__auto__)
                         (state__244957)
                         (clojure.core/let
                          [[?__243445] x__9586__auto__]
                          (if
                           (clojure.core/vector? input__243425)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__243425)
                             2)
                            (clojure.core/let
                             [input__243425_nth_0__
                              (clojure.core/nth input__243425 0)
                              input__243425_nth_1__
                              (clojure.core/nth input__243425 1)]
                             (if
                              (clojure.core/seq? input__243425_nth_0__)
                              (clojure.core/let
                               [input__243425_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__243425_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__243425_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__243425_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__243425_nth_0__)]
                                 (clojure.core/let
                                  [?sequence input__243425_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__243425_nth_0__]
                                   (clojure.core/let
                                    [?env input__243425_nth_1__]
                                    (try
                                     [{:tag :string,
                                       :next
                                       (clojure.core/let
                                        [CATA_RESULT__10889__auto__
                                         (CATA__FN__243502
                                          ['meander.dev.parse.zeta/parse-sequential
                                           (clojure.core/into
                                            []
                                            ?sequence)
                                           (clojure.core/let
                                            [form__10988__auto__
                                             {:context :string}]
                                            (clojure.core/merge
                                             (clojure.core/into
                                              {}
                                              ?env)
                                             form__10988__auto__))])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10889__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10889__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__11829__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__11829__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__11829__auto__))))))))
                                (state__244957)))
                              (state__244957)))
                            (state__244957))
                           (state__244957)))))
                       (state__244957)))))
                   (state__244957))))
                (state__244957)))
              (state__244957)))
            (state__244957))
           (state__244957))))
        (state__244957
         []
         (clojure.core/letfn
          [(def__244820
            [arg__244843 ?__243446]
            (clojure.core/letfn
             [(state__245207
               []
               (clojure.core/let
                [x__244844 "meander.zeta"]
                (if
                 (clojure.core/= ?__243446 x__244844)
                 [?__243446]
                 (state__245208))))
              (state__245208
               []
               (if
                (clojure.core/map? arg__244843)
                (clojure.core/let
                 [VAL__244845 (.valAt arg__244843 :aliases)]
                 (if
                  (clojure.core/map? VAL__244845)
                  (clojure.core/let
                   [X__244847 (clojure.core/set VAL__244845)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__244847))
                    (clojure.core/loop
                     [search_space__245209
                      (clojure.core/seq X__244847)]
                     (if
                      (clojure.core/seq search_space__245209)
                      (clojure.core/let
                       [elem__244848
                        (clojure.core/first search_space__245209)
                        result__245210
                        (clojure.core/let
                         [elem__244848_nth_0__
                          (clojure.core/nth elem__244848 0)
                          elem__244848_nth_1__
                          (clojure.core/nth elem__244848 1)]
                         (if
                          (clojure.core/symbol? elem__244848_nth_0__)
                          (clojure.core/let
                           [X__244850
                            (clojure.core/name elem__244848_nth_0__)]
                           (if
                            (clojure.core/= ?__243446 X__244850)
                            (if
                             (clojure.core/symbol?
                              elem__244848_nth_1__)
                             (clojure.core/let
                              [X__244852
                               (clojure.core/name
                                elem__244848_nth_1__)]
                              (clojure.core/case
                               X__244852
                               ("meander.zeta")
                               [?__243446]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__245210)
                        (recur
                         (clojure.core/next search_space__245209))
                        result__245210))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__245207)))]
          (if
           (clojure.core/vector? input__243425)
           (if
            (clojure.core/= (clojure.core/count input__243425) 2)
            (clojure.core/let
             [input__243425_nth_0__
              (clojure.core/nth input__243425 0)
              input__243425_nth_1__
              (clojure.core/nth input__243425 1)]
             (if
              (clojure.core/seq? input__243425_nth_0__)
              (clojure.core/let
               [input__243425_nth_0___l__
                (clojure.core/take 1 input__243425_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__243425_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__243425_nth_0___r__
                  (clojure.core/drop 1 input__243425_nth_0__)]
                 (clojure.core/let
                  [input__243425_nth_0___l___nth_0__
                   (clojure.core/nth input__243425_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__243425_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__244830
                     (clojure.core/namespace
                      input__243425_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__243446 X__244830]
                     (clojure.core/let
                      [X__244832
                       (clojure.core/name
                        input__243425_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__244832
                       ("symbol")
                       (clojure.core/let
                        [x__9586__auto__
                         (def__244820 input__243425_nth_1__ ?__243446)]
                        (if
                         (meander.runtime.zeta/fail? x__9586__auto__)
                         (state__244958)
                         (clojure.core/let
                          [[?__243446] x__9586__auto__]
                          (if
                           (clojure.core/vector? input__243425)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__243425)
                             2)
                            (clojure.core/let
                             [input__243425_nth_0__
                              (clojure.core/nth input__243425 0)
                              input__243425_nth_1__
                              (clojure.core/nth input__243425 1)]
                             (if
                              (clojure.core/seq? input__243425_nth_0__)
                              (clojure.core/let
                               [input__243425_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__243425_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__243425_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__243425_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__243425_nth_0__)]
                                 (clojure.core/let
                                  [?symbol-args
                                   input__243425_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__243425_nth_0__]
                                   (clojure.core/let
                                    [?env input__243425_nth_1__]
                                    (try
                                     [(clojure.core/let
                                       [CATA_RESULT__10889__auto__
                                        (CATA__FN__243502
                                         ['meander.dev.parse.zeta/make-symbol
                                          (clojure.core/into
                                           []
                                           ?symbol-args)
                                          ?form
                                          ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__10889__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__10889__auto__
                                         0)))]
                                     (catch
                                      java.lang.Exception
                                      e__11829__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__11829__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__11829__auto__))))))))
                                (state__244958)))
                              (state__244958)))
                            (state__244958))
                           (state__244958)))))
                       (state__244958)))))
                   (state__244958))))
                (state__244958)))
              (state__244958)))
            (state__244958))
           (state__244958))))
        (state__244958
         []
         (if
          (clojure.core/vector? input__243425)
          (clojure.core/letfn
           [(state__245212
             []
             (if
              (clojure.core/= (clojure.core/count input__243425) 4)
              (clojure.core/let
               [input__243425_nth_0__
                (clojure.core/nth input__243425 0)
                input__243425_nth_1__
                (clojure.core/nth input__243425 1)
                input__243425_nth_2__
                (clojure.core/nth input__243425 2)]
               (clojure.core/letfn
                [(state__245214
                  []
                  (clojure.core/case
                   input__243425_nth_0__
                   (meander.dev.parse.zeta/make-symbol)
                   (if
                    (clojure.core/vector? input__243425_nth_1__)
                    (clojure.core/case
                     input__243425_nth_1__
                     ([])
                     (clojure.core/let
                      [?form input__243425_nth_2__]
                      (try
                       [{:tag :symbol,
                         :namespace {:tag :wildcard},
                         :name {:tag :wildcard},
                         :form ?form}]
                       (catch
                        java.lang.Exception
                        e__11829__auto__
                        (if
                         (meander.runtime.zeta/fail? e__11829__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__11829__auto__)))))
                     (state__245215))
                    (state__245215))
                   (state__245215)))
                 (state__245215
                  []
                  (clojure.core/let
                   [input__243425_nth_3__
                    (clojure.core/nth input__243425 3)]
                   (clojure.core/case
                    input__243425_nth_0__
                    (meander.dev.parse.zeta/make-symbol)
                    (if
                     (clojure.core/vector? input__243425_nth_1__)
                     (clojure.core/letfn
                      [(state__245216
                        []
                        (if
                         (clojure.core/=
                          (clojure.core/count input__243425_nth_1__)
                          1)
                         (clojure.core/let
                          [input__243425_nth_1___nth_0__
                           (clojure.core/nth input__243425_nth_1__ 0)]
                          (clojure.core/let
                           [?name input__243425_nth_1___nth_0__]
                           (clojure.core/let
                            [?form input__243425_nth_2__]
                            (clojure.core/let
                             [?env input__243425_nth_3__]
                             (try
                              [{:tag :symbol,
                                :namespace {:tag :wildcard},
                                :name
                                (clojure.core/let
                                 [CATA_RESULT__10889__auto__
                                  (CATA__FN__243502 [?name ?env])]
                                 (if
                                  (meander.runtime.zeta/fail?
                                   CATA_RESULT__10889__auto__)
                                  (throw (meander.runtime.zeta/fail))
                                  (clojure.core/nth
                                   CATA_RESULT__10889__auto__
                                   0))),
                                :form ?form}]
                              (catch
                               java.lang.Exception
                               e__11829__auto__
                               (if
                                (meander.runtime.zeta/fail?
                                 e__11829__auto__)
                                (meander.runtime.zeta/fail)
                                (throw e__11829__auto__))))))))
                         (state__245217)))
                       (state__245217
                        []
                        (if
                         (clojure.core/=
                          (clojure.core/count input__243425_nth_1__)
                          2)
                         (clojure.core/let
                          [input__243425_nth_1___nth_0__
                           (clojure.core/nth input__243425_nth_1__ 0)
                           input__243425_nth_1___nth_1__
                           (clojure.core/nth input__243425_nth_1__ 1)]
                          (clojure.core/let
                           [?namespace input__243425_nth_1___nth_0__]
                           (clojure.core/let
                            [?name input__243425_nth_1___nth_1__]
                            (clojure.core/let
                             [?form input__243425_nth_2__]
                             (clojure.core/let
                              [?env input__243425_nth_3__]
                              (try
                               [{:tag :symbol,
                                 :namespace
                                 (clojure.core/let
                                  [CATA_RESULT__10889__auto__
                                   (CATA__FN__243502
                                    [?namespace ?env])]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    CATA_RESULT__10889__auto__)
                                   (throw (meander.runtime.zeta/fail))
                                   (clojure.core/nth
                                    CATA_RESULT__10889__auto__
                                    0))),
                                 :name
                                 (clojure.core/let
                                  [CATA_RESULT__10889__auto__
                                   (CATA__FN__243502 [?name ?env])]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    CATA_RESULT__10889__auto__)
                                   (throw (meander.runtime.zeta/fail))
                                   (clojure.core/nth
                                    CATA_RESULT__10889__auto__
                                    0))),
                                 :form ?form}]
                               (catch
                                java.lang.Exception
                                e__11829__auto__
                                (if
                                 (meander.runtime.zeta/fail?
                                  e__11829__auto__)
                                 (meander.runtime.zeta/fail)
                                 (throw e__11829__auto__)))))))))
                         (state__245213)))]
                      (state__245216))
                     (state__245213))
                    (state__245213))))]
                (state__245214)))
              (state__245213)))
            (state__245213
             []
             (if
              (clojure.core/= (clojure.core/count input__243425) 2)
              (clojure.core/let
               [input__243425_nth_0__
                (clojure.core/nth input__243425 0)]
               (clojure.core/letfn
                [(state__245218
                  []
                  (clojure.core/let
                   [input__243425_nth_1__
                    (clojure.core/nth input__243425 1)]
                   (clojure.core/letfn
                    [(state__245223
                      []
                      (if
                       (clojure.core/seq? input__243425_nth_0__)
                       (clojure.core/let
                        [?sequence input__243425_nth_0__]
                        (clojure.core/let
                         [?env input__243425_nth_1__]
                         (try
                          [{:tag :seq,
                            :next
                            (clojure.core/let
                             [CATA_RESULT__10889__auto__
                              (CATA__FN__243502
                               ['meander.dev.parse.zeta/parse-sequential
                                (clojure.core/into [] ?sequence)
                                (clojure.core/let
                                 [form__10988__auto__ {:context :seq}]
                                 (clojure.core/merge
                                  (clojure.core/into {} ?env)
                                  form__10988__auto__))])]
                             (if
                              (meander.runtime.zeta/fail?
                               CATA_RESULT__10889__auto__)
                              (throw (meander.runtime.zeta/fail))
                              (clojure.core/nth
                               CATA_RESULT__10889__auto__
                               0))),
                            :form ?sequence}]
                          (catch
                           java.lang.Exception
                           e__11829__auto__
                           (if
                            (meander.runtime.zeta/fail?
                             e__11829__auto__)
                            (meander.runtime.zeta/fail)
                            (throw e__11829__auto__))))))
                       (state__245224)))
                     (state__245224
                      []
                      (if
                       (clojure.core/map? input__243425_nth_0__)
                       (clojure.core/let
                        [?map input__243425_nth_0__]
                        (clojure.core/let
                         [?env input__243425_nth_1__]
                         (try
                          [{:tag :map,
                            :next
                            (clojure.core/let
                             [CATA_RESULT__10889__auto__
                              (CATA__FN__243502
                               ['meander.dev.parse.zeta/parse-entries
                                ?map
                                ?env])]
                             (if
                              (meander.runtime.zeta/fail?
                               CATA_RESULT__10889__auto__)
                              (throw (meander.runtime.zeta/fail))
                              (clojure.core/nth
                               CATA_RESULT__10889__auto__
                               0))),
                            :form ?map}]
                          (catch
                           java.lang.Exception
                           e__11829__auto__
                           (if
                            (meander.runtime.zeta/fail?
                             e__11829__auto__)
                            (meander.runtime.zeta/fail)
                            (throw e__11829__auto__))))))
                       (state__245219)))]
                    (state__245223))))
                 (state__245219
                  []
                  (if
                   (clojure.core/symbol? input__243425_nth_0__)
                   (clojure.core/let
                    [X__244872
                     (clojure.core/namespace input__243425_nth_0__)]
                    (clojure.core/let
                     [X__244874
                      (clojure.core/name input__243425_nth_0__)]
                     (if
                      (clojure.core/string? X__244874)
                      (clojure.core/letfn
                       [(state__245225
                         []
                         (clojure.core/let
                          [ret__244875
                           (clojure.core/re-matches #"_.*" X__244874)]
                          (if
                           (clojure.core/some? ret__244875)
                           (clojure.core/let
                            [?name ret__244875]
                            (clojure.core/let
                             [?symbol input__243425_nth_0__]
                             (try
                              [{:tag :wildcard,
                                :name ?name,
                                :form ?symbol,
                                :symbol ?symbol}]
                              (catch
                               java.lang.Exception
                               e__11829__auto__
                               (if
                                (meander.runtime.zeta/fail?
                                 e__11829__auto__)
                                (meander.runtime.zeta/fail)
                                (throw e__11829__auto__))))))
                           (state__245226))))
                        (state__245226
                         []
                         (clojure.core/let
                          [ret__244882
                           (clojure.core/re-matches #".+#" X__244874)]
                          (if
                           (clojure.core/some? ret__244882)
                           (clojure.core/let
                            [?name ret__244882]
                            (clojure.core/let
                             [?symbol input__243425_nth_0__]
                             (try
                              [{:tag :random-symbol,
                                :name ?name,
                                :form ?symbol,
                                :symbol ?symbol}]
                              (catch
                               java.lang.Exception
                               e__11829__auto__
                               (if
                                (meander.runtime.zeta/fail?
                                 e__11829__auto__)
                                (meander.runtime.zeta/fail)
                                (throw e__11829__auto__))))))
                           (state__245227))))
                        (state__245227
                         []
                         (clojure.core/let
                          [ret__244889
                           (clojure.core/re-matches #"%.+" X__244874)]
                          (if
                           (clojure.core/some? ret__244889)
                           (clojure.core/let
                            [?name ret__244889]
                            (clojure.core/let
                             [?symbol input__243425_nth_0__]
                             (try
                              [{:tag :reference,
                                :name ?name,
                                :symbol ?symbol}]
                              (catch
                               java.lang.Exception
                               e__11829__auto__
                               (if
                                (meander.runtime.zeta/fail?
                                 e__11829__auto__)
                                (meander.runtime.zeta/fail)
                                (throw e__11829__auto__))))))
                           (state__245228))))
                        (state__245228
                         []
                         (clojure.core/let
                          [ret__244896
                           (clojure.core/re-matches #"\*.+" X__244874)]
                          (if
                           (clojure.core/some? ret__244896)
                           (clojure.core/let
                            [?name ret__244896]
                            (clojure.core/let
                             [?symbol input__243425_nth_0__]
                             (try
                              [{:tag :mutable-variable,
                                :name ?name,
                                :symbol ?symbol}]
                              (catch
                               java.lang.Exception
                               e__11829__auto__
                               (if
                                (meander.runtime.zeta/fail?
                                 e__11829__auto__)
                                (meander.runtime.zeta/fail)
                                (throw e__11829__auto__))))))
                           (state__245229))))
                        (state__245229
                         []
                         (clojure.core/let
                          [ret__244903
                           (clojure.core/re-matches #"\!.+" X__244874)]
                          (if
                           (clojure.core/some? ret__244903)
                           (clojure.core/let
                            [?name ret__244903]
                            (clojure.core/let
                             [?symbol input__243425_nth_0__]
                             (try
                              [{:tag :memory-variable,
                                :name ?name,
                                :symbol ?symbol}]
                              (catch
                               java.lang.Exception
                               e__11829__auto__
                               (if
                                (meander.runtime.zeta/fail?
                                 e__11829__auto__)
                                (meander.runtime.zeta/fail)
                                (throw e__11829__auto__))))))
                           (state__245230))))
                        (state__245230
                         []
                         (clojure.core/let
                          [ret__244910
                           (clojure.core/re-matches #"\?.+" X__244874)]
                          (if
                           (clojure.core/some? ret__244910)
                           (clojure.core/let
                            [?name ret__244910]
                            (clojure.core/let
                             [?symbol input__243425_nth_0__]
                             (try
                              [{:tag :logic-variable,
                                :name ?name,
                                :symbol ?symbol}]
                              (catch
                               java.lang.Exception
                               e__11829__auto__
                               (if
                                (meander.runtime.zeta/fail?
                                 e__11829__auto__)
                                (meander.runtime.zeta/fail)
                                (throw e__11829__auto__))))))
                           (state__245220))))]
                       (state__245225))
                      (state__245220))))
                   (state__245220)))
                 (state__245220
                  []
                  (if
                   (string? input__243425_nth_0__)
                   (clojure.core/let
                    [?x input__243425_nth_0__]
                    (try
                     [{:tag :literal, :type :string, :form ?x}]
                     (catch
                      java.lang.Exception
                      e__11829__auto__
                      (if
                       (meander.runtime.zeta/fail? e__11829__auto__)
                       (meander.runtime.zeta/fail)
                       (throw e__11829__auto__)))))
                   (state__245221)))
                 (state__245221
                  []
                  (if
                   (char? input__243425_nth_0__)
                   (clojure.core/let
                    [?x input__243425_nth_0__]
                    (try
                     [{:tag :literal, :type :char, :form ?x}]
                     (catch
                      java.lang.Exception
                      e__11829__auto__
                      (if
                       (meander.runtime.zeta/fail? e__11829__auto__)
                       (meander.runtime.zeta/fail)
                       (throw e__11829__auto__)))))
                   (state__245222)))
                 (state__245222
                  []
                  (clojure.core/let
                   [?x input__243425_nth_0__]
                   (try
                    [{:tag :literal, :form ?x}]
                    (catch
                     java.lang.Exception
                     e__11829__auto__
                     (if
                      (meander.runtime.zeta/fail? e__11829__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__11829__auto__))))))]
                (state__245218)))
              (state__244959)))]
           (state__245212))
          (state__244959)))
        (state__244959
         []
         (clojure.core/let
          [?x input__243425]
          (try
           [{:tag :mistake, :x ?x}]
           (catch
            java.lang.Exception
            e__11829__auto__
            (if
             (meander.runtime.zeta/fail? e__11829__auto__)
             (meander.runtime.zeta/fail)
             (throw e__11829__auto__))))))]
       (state__244921)))]
    (clojure.core/let
     [x__9586__auto__ (CATA__FN__243502 input__243425)]
     (if
      (meander.runtime.zeta/fail? x__9586__auto__)
      (meander.runtime.zeta/fail)
      (clojure.core/let
       [[CATA_RETURN__243506] x__9586__auto__]
       CATA_RETURN__243506))))]
  (if
   (meander.runtime.zeta/fail? ret__9766__auto__)
   nil
   ret__9766__auto__)))
