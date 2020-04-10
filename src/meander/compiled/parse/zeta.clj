(ns
 meander.compiled.parse.zeta
 (:require [meander.runtime.zeta] [meander.util.zeta]))
(clojure.core/defn
 parse
 [input__14712]
 (let*
  [ret__9766__auto__
   (clojure.core/letfn
    [(CATA__FN__14795
      [input__14712]
      (clojure.core/letfn
       [(state__16305
         []
         (if
          (clojure.core/vector? input__14712)
          (if
           (clojure.core/= (clojure.core/count input__14712) 3)
           (clojure.core/let
            [input__14712_nth_0__
             (clojure.core/nth input__14712 0)
             input__14712_nth_1__
             (clojure.core/nth input__14712 1)
             input__14712_nth_2__
             (clojure.core/nth input__14712 2)]
            (clojure.core/case
             input__14712_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__14712_nth_1__)
              (clojure.core/letfn
               [(state__16345
                 []
                 (clojure.core/case
                  input__14712_nth_1__
                  ([])
                  (clojure.core/let
                   [?env input__14712_nth_2__]
                   (try
                    [{:tag :empty}]
                    (catch
                     java.lang.Exception
                     e__11829__auto__
                     (if
                      (meander.runtime.zeta/fail? e__11829__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__11829__auto__)))))
                  (state__16346)))
                (state__16346
                 []
                 (clojure.core/let
                  [n__14804
                   (clojure.core/count input__14712_nth_1__)
                   m__14805
                   (clojure.core/max 0 (clojure.core/- n__14804 2))
                   input__14712_nth_1___l__
                   (clojure.core/subvec
                    input__14712_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__14712_nth_1__)
                     m__14805))
                   input__14712_nth_1___r__
                   (clojure.core/subvec input__14712_nth_1__ m__14805)]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__14712_nth_1___r__)
                    2)
                   (clojure.core/let
                    [!xs (clojure.core/vec input__14712_nth_1___l__)]
                    (if
                     (clojure.core/=
                      (clojure.core/count input__14712_nth_1___r__)
                      2)
                     (clojure.core/let
                      [input__14712_nth_1___r___nth_0__
                       (clojure.core/nth input__14712_nth_1___r__ 0)
                       input__14712_nth_1___r___nth_1__
                       (clojure.core/nth input__14712_nth_1___r__ 1)]
                      (clojure.core/case
                       input__14712_nth_1___r___nth_0__
                       (:meander.zeta/as)
                       (clojure.core/let
                        [?pattern input__14712_nth_1___r___nth_1__]
                        (clojure.core/let
                         [?env input__14712_nth_2__]
                         (try
                          [(clojure.core/let
                            [!xs__counter
                             (meander.runtime.zeta/iterator !xs)]
                            {:tag :as,
                             :pattern
                             (clojure.core/let
                              [CATA_RESULT__10889__auto__
                               (CATA__FN__14795 [?pattern ?env])]
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
                               (CATA__FN__14795
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
                       (state__16306)))
                     (state__16306)))
                   (state__16306))))]
               (state__16345))
              (state__16306))
             (state__16306)))
           (state__16306))
          (state__16306)))
        (state__16306
         []
         (clojure.core/letfn
          [(def__14810
            [arg__14845 ?ns]
            (clojure.core/letfn
             [(state__16347
               []
               (clojure.core/let
                [x__14846 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__14846)
                 (clojure.core/let [?env arg__14845] [?env ?ns])
                 (state__16348))))
              (state__16348
               []
               (if
                (clojure.core/map? arg__14845)
                (clojure.core/let
                 [VAL__14847 (.valAt arg__14845 :aliases)]
                 (if
                  (clojure.core/map? VAL__14847)
                  (clojure.core/let
                   [X__14849 (clojure.core/set VAL__14847)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__14849))
                    (clojure.core/loop
                     [search_space__16349 (clojure.core/seq X__14849)]
                     (if
                      (clojure.core/seq search_space__16349)
                      (clojure.core/let
                       [elem__14850
                        (clojure.core/first search_space__16349)
                        result__16350
                        (clojure.core/let
                         [elem__14850_nth_0__
                          (clojure.core/nth elem__14850 0)
                          elem__14850_nth_1__
                          (clojure.core/nth elem__14850 1)]
                         (if
                          (clojure.core/symbol? elem__14850_nth_0__)
                          (clojure.core/let
                           [X__14852
                            (clojure.core/name elem__14850_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__14852)
                            (if
                             (clojure.core/symbol? elem__14850_nth_1__)
                             (clojure.core/let
                              [X__14854
                               (clojure.core/name elem__14850_nth_1__)]
                              (clojure.core/case
                               X__14854
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__14845]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16350)
                        (recur (clojure.core/next search_space__16349))
                        result__16350))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16347)))]
          (if
           (clojure.core/vector? input__14712)
           (if
            (clojure.core/= (clojure.core/count input__14712) 3)
            (clojure.core/let
             [input__14712_nth_0__
              (clojure.core/nth input__14712 0)
              input__14712_nth_1__
              (clojure.core/nth input__14712 1)
              input__14712_nth_2__
              (clojure.core/nth input__14712 2)]
             (clojure.core/case
              input__14712_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__14712_nth_1__)
               (clojure.core/loop
                [search_space__16352
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__14712_nth_1__)]
                (if
                 (clojure.core/seq search_space__16352)
                 (clojure.core/let
                  [input__14712_nth_1___parts__
                   (clojure.core/first search_space__16352)
                   result__16353
                   (clojure.core/let
                    [input__14712_nth_1___l__
                     (clojure.core/nth input__14712_nth_1___parts__ 0)
                     input__14712_nth_1___r__
                     (clojure.core/nth input__14712_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__14712_nth_1___l__)]
                     (clojure.core/let
                      [input__14712_nth_1___r___l__
                       (clojure.core/subvec
                        input__14712_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__14712_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__14712_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__14712_nth_1___r___r__
                         (clojure.core/subvec
                          input__14712_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__14712_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__14712_nth_1___r___l__
                           0)
                          input__14712_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__14712_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__14712_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__14819
                            (clojure.core/namespace
                             input__14712_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__14819]
                            (clojure.core/let
                             [X__14821
                              (clojure.core/name
                               input__14712_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__14821)
                              (clojure.core/let
                               [ret__14822
                                (clojure.core/re-matches
                                 #"&(\d+)"
                                 X__14821)]
                               (if
                                (clojure.core/some? ret__14822)
                                (if
                                 (clojure.core/vector? ret__14822)
                                 (if
                                  (clojure.core/=
                                   (clojure.core/count ret__14822)
                                   2)
                                  (clojure.core/let
                                   [ret__14822_nth_1__
                                    (clojure.core/nth ret__14822 1)]
                                   (clojure.core/let
                                    [?n ret__14822_nth_1__]
                                    (clojure.core/let
                                     [?pattern
                                      input__14712_nth_1___r___l___nth_1__]
                                     (clojure.core/let
                                      [?rest
                                       input__14712_nth_1___r___r__]
                                      (clojure.core/let
                                       [x__9586__auto__
                                        (def__14810
                                         input__14712_nth_2__
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
                                              (CATA__FN__14795
                                               ['meander.dev.parse.zeta/make-join
                                                (clojure.core/let
                                                 [CATA_RESULT__10889__auto__
                                                  (CATA__FN__14795
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
                                                  (CATA__FN__14795
                                                   ['meander.dev.parse.zeta/make-join
                                                    {:tag :slice,
                                                     :size
                                                     (Integer. ?n),
                                                     :pattern
                                                     (clojure.core/let
                                                      [CATA_RESULT__10889__auto__
                                                       (CATA__FN__14795
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
                                                      (CATA__FN__14795
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
                   (meander.runtime.zeta/fail? result__16353)
                   (recur (clojure.core/next search_space__16352))
                   result__16353))
                 (state__16307)))
               (state__16307))
              (state__16307)))
            (state__16307))
           (state__16307))))
        (state__16307
         []
         (clojure.core/letfn
          [(def__14867
            [arg__14899 ?ns]
            (clojure.core/letfn
             [(state__16355
               []
               (clojure.core/let
                [x__14900 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__14900)
                 (clojure.core/let [?env arg__14899] [?env ?ns])
                 (state__16356))))
              (state__16356
               []
               (if
                (clojure.core/map? arg__14899)
                (clojure.core/let
                 [VAL__14901 (.valAt arg__14899 :aliases)]
                 (if
                  (clojure.core/map? VAL__14901)
                  (clojure.core/let
                   [X__14903 (clojure.core/set VAL__14901)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__14903))
                    (clojure.core/loop
                     [search_space__16357 (clojure.core/seq X__14903)]
                     (if
                      (clojure.core/seq search_space__16357)
                      (clojure.core/let
                       [elem__14904
                        (clojure.core/first search_space__16357)
                        result__16358
                        (clojure.core/let
                         [elem__14904_nth_0__
                          (clojure.core/nth elem__14904 0)
                          elem__14904_nth_1__
                          (clojure.core/nth elem__14904 1)]
                         (if
                          (clojure.core/symbol? elem__14904_nth_0__)
                          (clojure.core/let
                           [X__14906
                            (clojure.core/name elem__14904_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__14906)
                            (if
                             (clojure.core/symbol? elem__14904_nth_1__)
                             (clojure.core/let
                              [X__14908
                               (clojure.core/name elem__14904_nth_1__)]
                              (clojure.core/case
                               X__14908
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__14899]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16358)
                        (recur (clojure.core/next search_space__16357))
                        result__16358))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16355)))]
          (if
           (clojure.core/vector? input__14712)
           (if
            (clojure.core/= (clojure.core/count input__14712) 3)
            (clojure.core/let
             [input__14712_nth_0__
              (clojure.core/nth input__14712 0)
              input__14712_nth_1__
              (clojure.core/nth input__14712 1)
              input__14712_nth_2__
              (clojure.core/nth input__14712 2)]
             (clojure.core/case
              input__14712_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__14712_nth_1__)
               (clojure.core/loop
                [search_space__16360
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__14712_nth_1__)]
                (if
                 (clojure.core/seq search_space__16360)
                 (clojure.core/let
                  [input__14712_nth_1___parts__
                   (clojure.core/first search_space__16360)
                   result__16361
                   (clojure.core/let
                    [input__14712_nth_1___l__
                     (clojure.core/nth input__14712_nth_1___parts__ 0)
                     input__14712_nth_1___r__
                     (clojure.core/nth input__14712_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__14712_nth_1___l__)]
                     (clojure.core/let
                      [input__14712_nth_1___r___l__
                       (clojure.core/subvec
                        input__14712_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__14712_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__14712_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__14712_nth_1___r___r__
                         (clojure.core/subvec
                          input__14712_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__14712_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__14712_nth_1___r___l__
                           0)
                          input__14712_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__14712_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__14712_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__14876
                            (clojure.core/namespace
                             input__14712_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__14876]
                            (clojure.core/let
                             [X__14878
                              (clojure.core/name
                               input__14712_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__14878)
                              (if
                               (clojure.core/re-matches
                                #"&.*"
                                X__14878)
                               (clojure.core/let
                                [?pattern
                                 input__14712_nth_1___r___l___nth_1__]
                                (clojure.core/let
                                 [?rest input__14712_nth_1___r___r__]
                                 (clojure.core/let
                                  [x__9586__auto__
                                   (def__14867
                                    input__14712_nth_2__
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
                                         (CATA__FN__14795
                                          ['meander.dev.parse.zeta/make-join
                                           (clojure.core/let
                                            [CATA_RESULT__10889__auto__
                                             (CATA__FN__14795
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
                                             (CATA__FN__14795
                                              ['meander.dev.parse.zeta/make-join
                                               (clojure.core/let
                                                [CATA_RESULT__10889__auto__
                                                 (CATA__FN__14795
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
                                                 (CATA__FN__14795
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
                   (meander.runtime.zeta/fail? result__16361)
                   (recur (clojure.core/next search_space__16360))
                   result__16361))
                 (state__16308)))
               (state__16308))
              (state__16308)))
            (state__16308))
           (state__16308))))
        (state__16308
         []
         (if
          (clojure.core/vector? input__14712)
          (clojure.core/letfn
           [(state__16363
             []
             (if
              (clojure.core/= (clojure.core/count input__14712) 3)
              (clojure.core/let
               [input__14712_nth_0__
                (clojure.core/nth input__14712 0)
                input__14712_nth_1__
                (clojure.core/nth input__14712 1)
                input__14712_nth_2__
                (clojure.core/nth input__14712 2)]
               (clojure.core/case
                input__14712_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__14712_nth_1__)
                 (clojure.core/letfn
                  [(state__16366
                    []
                    (clojure.core/let
                     [n__14929
                      (clojure.core/count input__14712_nth_1__)
                      m__14930
                      (clojure.core/max 0 (clojure.core/- n__14929 2))
                      input__14712_nth_1___l__
                      (clojure.core/subvec
                       input__14712_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__14712_nth_1__)
                        m__14930))
                      input__14712_nth_1___r__
                      (clojure.core/subvec
                       input__14712_nth_1__
                       m__14930)]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__14712_nth_1___r__)
                       2)
                      (clojure.core/let
                       [!xs
                        (clojure.core/vec input__14712_nth_1___l__)]
                       (if
                        (clojure.core/=
                         (clojure.core/count input__14712_nth_1___r__)
                         2)
                        (clojure.core/let
                         [input__14712_nth_1___r___nth_0__
                          (clojure.core/nth input__14712_nth_1___r__ 0)
                          input__14712_nth_1___r___nth_1__
                          (clojure.core/nth
                           input__14712_nth_1___r__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__14712_nth_1___r___nth_0__)
                          (clojure.core/let
                           [X__14934
                            (clojure.core/namespace
                             input__14712_nth_1___r___nth_0__)]
                           (clojure.core/let
                            [?ns X__14934]
                            (clojure.core/let
                             [X__14936
                              (clojure.core/name
                               input__14712_nth_1___r___nth_0__)]
                             (if
                              (clojure.core/string? X__14936)
                              (clojure.core/let
                               [ret__14937
                                (clojure.core/re-matches
                                 #"&.*"
                                 X__14936)]
                               (if
                                (clojure.core/some? ret__14937)
                                (clojure.core/let
                                 [?name ret__14937]
                                 (clojure.core/let
                                  [?pattern
                                   input__14712_nth_1___r___nth_1__]
                                  (if
                                   (clojure.core/map?
                                    input__14712_nth_2__)
                                   (clojure.core/let
                                    [VAL__14921
                                     (.valAt
                                      input__14712_nth_2__
                                      :aliases)]
                                    (if
                                     (clojure.core/map? VAL__14921)
                                     (clojure.core/let
                                      [X__14923
                                       (clojure.core/set VAL__14921)]
                                      (if
                                       (clojure.core/<=
                                        1
                                        (clojure.core/count X__14923))
                                       (clojure.core/loop
                                        [search_space__16370
                                         (clojure.core/seq X__14923)]
                                        (if
                                         (clojure.core/seq
                                          search_space__16370)
                                         (clojure.core/let
                                          [elem__14924
                                           (clojure.core/first
                                            search_space__16370)
                                           result__16371
                                           (clojure.core/let
                                            [elem__14924_nth_0__
                                             (clojure.core/nth
                                              elem__14924
                                              0)
                                             elem__14924_nth_1__
                                             (clojure.core/nth
                                              elem__14924
                                              1)]
                                            (if
                                             (clojure.core/symbol?
                                              elem__14924_nth_0__)
                                             (clojure.core/let
                                              [X__14926
                                               (clojure.core/name
                                                elem__14924_nth_0__)]
                                              (if
                                               (clojure.core/=
                                                ?ns
                                                X__14926)
                                               (if
                                                (clojure.core/symbol?
                                                 elem__14924_nth_1__)
                                                (clojure.core/let
                                                 [X__14928
                                                  (clojure.core/name
                                                   elem__14924_nth_1__)]
                                                 (clojure.core/case
                                                  X__14928
                                                  ("meander.zeta")
                                                  (clojure.core/let
                                                   [?env
                                                    input__14712_nth_2__]
                                                   (try
                                                    [(clojure.core/let
                                                      [!xs__counter
                                                       (meander.runtime.zeta/iterator
                                                        !xs)]
                                                      (clojure.core/let
                                                       [CATA_RESULT__10889__auto__
                                                        (CATA__FN__14795
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
                                            result__16371)
                                           (recur
                                            (clojure.core/next
                                             search_space__16370))
                                           result__16371))
                                         (state__16367)))
                                       (state__16367)))
                                     (state__16367)))
                                   (state__16367))))
                                (state__16367)))
                              (state__16367)))))
                          (state__16367)))
                        (state__16367)))
                      (state__16367))))
                   (state__16367
                    []
                    (clojure.core/loop
                     [search_space__16373
                      (meander.runtime.zeta/epsilon-partitions
                       2
                       input__14712_nth_1__)]
                     (if
                      (clojure.core/seq search_space__16373)
                      (clojure.core/let
                       [input__14712_nth_1___parts__
                        (clojure.core/first search_space__16373)
                        result__16374
                        (clojure.core/let
                         [input__14712_nth_1___l__
                          (clojure.core/nth
                           input__14712_nth_1___parts__
                           0)
                          input__14712_nth_1___r__
                          (clojure.core/nth
                           input__14712_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs
                           (clojure.core/vec input__14712_nth_1___l__)]
                          (clojure.core/let
                           [input__14712_nth_1___r___l__
                            (clojure.core/subvec
                             input__14712_nth_1___r__
                             0
                             (clojure.core/min
                              (clojure.core/count
                               input__14712_nth_1___r__)
                              1))]
                           (if
                            (clojure.core/=
                             (clojure.core/count
                              input__14712_nth_1___r___l__)
                             1)
                            (clojure.core/let
                             [input__14712_nth_1___r___r__
                              (clojure.core/subvec
                               input__14712_nth_1___r__
                               1)]
                             (if
                              (clojure.core/=
                               input__14712_nth_1___r___l__
                               ['.])
                              (clojure.core/let
                               [?rest input__14712_nth_1___r___r__]
                               (clojure.core/let
                                [?env input__14712_nth_2__]
                                (try
                                 [(clojure.core/let
                                   [!xs__counter
                                    (meander.runtime.zeta/iterator
                                     !xs)]
                                   (clojure.core/let
                                    [CATA_RESULT__10889__auto__
                                     (CATA__FN__14795
                                      ['meander.dev.parse.zeta/make-join
                                       (clojure.core/let
                                        [CATA_RESULT__10889__auto__
                                         (CATA__FN__14795
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
                                         (CATA__FN__14795
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
                        (meander.runtime.zeta/fail? result__16374)
                        (recur (clojure.core/next search_space__16373))
                        result__16374))
                      (state__16368))))
                   (state__16368
                    []
                    (clojure.core/let
                     [input__14712_nth_1___l__
                      (clojure.core/subvec
                       input__14712_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__14712_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__14712_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__14712_nth_1___r__
                        (clojure.core/subvec input__14712_nth_1__ 1)]
                       (if
                        (clojure.core/=
                         input__14712_nth_1___l__
                         ['...])
                        (clojure.core/let
                         [?rest input__14712_nth_1___r__]
                         (clojure.core/let
                          [?env input__14712_nth_2__]
                          (try
                           [(clojure.core/let
                             [CATA_RESULT__10889__auto__
                              (CATA__FN__14795
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
                        (state__16369)))
                      (state__16369))))
                   (state__16369
                    []
                    (clojure.core/loop
                     [search_space__16376
                      (meander.runtime.zeta/epsilon-partitions
                       2
                       input__14712_nth_1__)]
                     (if
                      (clojure.core/seq search_space__16376)
                      (clojure.core/let
                       [input__14712_nth_1___parts__
                        (clojure.core/first search_space__16376)
                        result__16377
                        (clojure.core/let
                         [input__14712_nth_1___l__
                          (clojure.core/nth
                           input__14712_nth_1___parts__
                           0)
                          input__14712_nth_1___r__
                          (clojure.core/nth
                           input__14712_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs []]
                          (clojure.core/let
                           [ret__9750__auto__
                            (meander.runtime.zeta/epsilon-run-star-1
                             input__14712_nth_1___l__
                             [!xs]
                             (clojure.core/fn
                              [[!xs] input__14954]
                              (clojure.core/let
                               [input__14954_nth_0__
                                (clojure.core/nth input__14954 0)]
                               (clojure.core/letfn
                                [(save__14955
                                  []
                                  (meander.runtime.zeta/fail))
                                 (f__16380
                                  []
                                  (clojure.core/let
                                   [!xs
                                    (clojure.core/conj
                                     !xs
                                     input__14954_nth_0__)]
                                   [!xs]))]
                                (if
                                 (clojure.core/symbol?
                                  input__14954_nth_0__)
                                 (clojure.core/let
                                  [X__14957
                                   (clojure.core/namespace
                                    input__14954_nth_0__)]
                                  (clojure.core/case
                                   X__14957
                                   (nil)
                                   (clojure.core/let
                                    [X__14959
                                     (clojure.core/name
                                      input__14954_nth_0__)]
                                    (if
                                     (clojure.core/string? X__14959)
                                     (if
                                      (clojure.core/re-matches
                                       #"\.\.(?:\.|\d+)"
                                       X__14959)
                                      (save__14955)
                                      (f__16380))
                                     (f__16380)))
                                   (f__16380)))
                                 (f__16380)))))
                             (clojure.core/fn
                              [[!xs]]
                              (clojure.core/let
                               [input__14712_nth_1___r___l__
                                (clojure.core/subvec
                                 input__14712_nth_1___r__
                                 0
                                 (clojure.core/min
                                  (clojure.core/count
                                   input__14712_nth_1___r__)
                                  1))]
                               (if
                                (clojure.core/=
                                 (clojure.core/count
                                  input__14712_nth_1___r___l__)
                                 1)
                                (clojure.core/let
                                 [input__14712_nth_1___r___r__
                                  (clojure.core/subvec
                                   input__14712_nth_1___r__
                                   1)]
                                 (if
                                  (clojure.core/=
                                   input__14712_nth_1___r___l__
                                   ['...])
                                  (clojure.core/let
                                   [?rest input__14712_nth_1___r___r__]
                                   (clojure.core/let
                                    [?env input__14712_nth_2__]
                                    (try
                                     [(clojure.core/let
                                       [!xs__counter
                                        (meander.runtime.zeta/iterator
                                         !xs)]
                                       (clojure.core/let
                                        [CATA_RESULT__10889__auto__
                                         (CATA__FN__14795
                                          ['meander.dev.parse.zeta/make-star
                                           (clojure.core/let
                                            [CATA_RESULT__10889__auto__
                                             (CATA__FN__14795
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
                                             (CATA__FN__14795
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
                        (meander.runtime.zeta/fail? result__16377)
                        (recur (clojure.core/next search_space__16376))
                        result__16377))
                      (state__16364))))]
                  (state__16366))
                 (state__16364))
                (state__16364)))
              (state__16364)))
            (state__16364
             []
             (if
              (clojure.core/= (clojure.core/count input__14712) 4)
              (clojure.core/let
               [input__14712_nth_0__
                (clojure.core/nth input__14712 0)
                input__14712_nth_1__
                (clojure.core/nth input__14712 1)
                input__14712_nth_2__
                (clojure.core/nth input__14712 2)]
               (clojure.core/letfn
                [(state__16381
                  []
                  (clojure.core/let
                   [input__14712_nth_3__
                    (clojure.core/nth input__14712 3)]
                   (clojure.core/case
                    input__14712_nth_0__
                    (meander.dev.parse.zeta/make-star)
                    (clojure.core/letfn
                     [(state__16383
                       []
                       (if
                        (clojure.core/map? input__14712_nth_1__)
                        (clojure.core/let
                         [VAL__14963
                          (.valAt input__14712_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__14963
                          (:cat)
                          (clojure.core/let
                           [VAL__14964
                            (.valAt input__14712_nth_1__ :sequence)]
                           (if
                            (clojure.core/vector? VAL__14964)
                            (if
                             (clojure.core/=
                              (clojure.core/count VAL__14964)
                              1)
                             (clojure.core/let
                              [VAL__14964_nth_0__
                               (clojure.core/nth VAL__14964 0)]
                              (if
                               (clojure.core/map? VAL__14964_nth_0__)
                               (clojure.core/let
                                [VAL__14969
                                 (.valAt VAL__14964_nth_0__ :tag)]
                                (clojure.core/case
                                 VAL__14969
                                 (:memory-variable)
                                 (clojure.core/let
                                  [?memory-variable VAL__14964_nth_0__]
                                  (clojure.core/let
                                   [VAL__14965
                                    (.valAt
                                     input__14712_nth_1__
                                     :next)]
                                   (if
                                    (clojure.core/map? VAL__14965)
                                    (clojure.core/let
                                     [VAL__14966
                                      (.valAt VAL__14965 :tag)]
                                     (clojure.core/case
                                      VAL__14966
                                      (:empty)
                                      (clojure.core/let
                                       [?next input__14712_nth_2__]
                                       (clojure.core/let
                                        [?env input__14712_nth_3__]
                                        (try
                                         [(clojure.core/let
                                           [CATA_RESULT__10889__auto__
                                            (CATA__FN__14795
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
                                      (state__16384)))
                                    (state__16384))))
                                 (state__16384)))
                               (state__16384)))
                             (state__16384))
                            (state__16384)))
                          (state__16384)))
                        (state__16384)))
                      (state__16384
                       []
                       (clojure.core/let
                        [?pattern input__14712_nth_1__]
                        (clojure.core/let
                         [?next input__14712_nth_2__]
                         (if
                          (clojure.core/map? input__14712_nth_3__)
                          (clojure.core/let
                           [VAL__14972
                            (.valAt input__14712_nth_3__ :context)]
                           (clojure.core/case
                            VAL__14972
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
                            (state__16382)))
                          (state__16382)))))]
                     (state__16383))
                    (state__16382))))
                 (state__16382
                  []
                  (clojure.core/case
                   input__14712_nth_0__
                   (meander.dev.parse.zeta/make-star)
                   (clojure.core/let
                    [?pattern input__14712_nth_1__]
                    (clojure.core/let
                     [?next input__14712_nth_2__]
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
                   (state__16365)))]
                (state__16381)))
              (state__16365)))
            (state__16365
             []
             (if
              (clojure.core/= (clojure.core/count input__14712) 3)
              (clojure.core/let
               [input__14712_nth_0__
                (clojure.core/nth input__14712 0)
                input__14712_nth_1__
                (clojure.core/nth input__14712 1)
                input__14712_nth_2__
                (clojure.core/nth input__14712 2)]
               (clojure.core/case
                input__14712_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__14712_nth_1__)
                 (clojure.core/let
                  [input__14712_nth_1___l__
                   (clojure.core/subvec
                    input__14712_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__14712_nth_1__)
                     1))]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__14712_nth_1___l__)
                    1)
                   (clojure.core/let
                    [input__14712_nth_1___r__
                     (clojure.core/subvec input__14712_nth_1__ 1)]
                    (clojure.core/let
                     [input__14712_nth_1___l___nth_0__
                      (clojure.core/nth input__14712_nth_1___l__ 0)]
                     (if
                      (clojure.core/symbol?
                       input__14712_nth_1___l___nth_0__)
                      (clojure.core/let
                       [X__14980
                        (clojure.core/namespace
                         input__14712_nth_1___l___nth_0__)]
                       (clojure.core/case
                        X__14980
                        (nil)
                        (clojure.core/let
                         [X__14982
                          (clojure.core/name
                           input__14712_nth_1___l___nth_0__)]
                         (if
                          (clojure.core/string? X__14982)
                          (clojure.core/let
                           [ret__14983
                            (clojure.core/re-matches
                             #"\.\.(\d+)"
                             X__14982)]
                           (if
                            (clojure.core/some? ret__14983)
                            (if
                             (clojure.core/vector? ret__14983)
                             (if
                              (clojure.core/=
                               (clojure.core/count ret__14983)
                               2)
                              (clojure.core/let
                               [ret__14983_nth_1__
                                (clojure.core/nth ret__14983 1)]
                               (clojure.core/let
                                [?n ret__14983_nth_1__]
                                (clojure.core/let
                                 [?operator
                                  input__14712_nth_1___l___nth_0__]
                                 (clojure.core/let
                                  [?rest input__14712_nth_1___r__]
                                  (clojure.core/let
                                   [?env input__14712_nth_2__]
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
                              (state__16309))
                             (state__16309))
                            (state__16309)))
                          (state__16309)))
                        (state__16309)))
                      (state__16309))))
                   (state__16309)))
                 (state__16309))
                (state__16309)))
              (state__16309)))]
           (state__16363))
          (state__16309)))
        (state__16309
         []
         (clojure.core/letfn
          [(def__14986
            [arg__15010]
            (clojure.core/letfn
             [(state__16385
               []
               (clojure.core/let
                [x__15011 :string-plus]
                (clojure.core/let
                 [?tag x__15011]
                 (if
                  (clojure.core/map? arg__15010)
                  (clojure.core/let
                   [VAL__15012 (.valAt arg__15010 :context)]
                   (clojure.core/case
                    VAL__15012
                    (:string)
                    (clojure.core/let [?env arg__15010] [?tag ?env])
                    (state__16386)))
                  (state__16386)))))
              (state__16386
               []
               (clojure.core/let
                [x__15013 :plus]
                (clojure.core/let
                 [?tag x__15013]
                 (clojure.core/let [?env arg__15010] [?tag ?env]))))]
             (state__16385)))]
          (if
           (clojure.core/vector? input__14712)
           (if
            (clojure.core/= (clojure.core/count input__14712) 3)
            (clojure.core/let
             [input__14712_nth_0__
              (clojure.core/nth input__14712 0)
              input__14712_nth_1__
              (clojure.core/nth input__14712 1)
              input__14712_nth_2__
              (clojure.core/nth input__14712 2)]
             (clojure.core/case
              input__14712_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__14712_nth_1__)
               (clojure.core/loop
                [search_space__16387
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__14712_nth_1__)]
                (if
                 (clojure.core/seq search_space__16387)
                 (clojure.core/let
                  [input__14712_nth_1___parts__
                   (clojure.core/first search_space__16387)
                   result__16388
                   (clojure.core/let
                    [input__14712_nth_1___l__
                     (clojure.core/nth input__14712_nth_1___parts__ 0)
                     input__14712_nth_1___r__
                     (clojure.core/nth input__14712_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__9750__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__14712_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__15003]
                         (clojure.core/let
                          [input__15003_nth_0__
                           (clojure.core/nth input__15003 0)]
                          (clojure.core/letfn
                           [(save__15004
                             []
                             (meander.runtime.zeta/fail))
                            (f__16391
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__15003_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol? input__15003_nth_0__)
                            (clojure.core/let
                             [X__15006
                              (clojure.core/namespace
                               input__15003_nth_0__)]
                             (clojure.core/case
                              X__15006
                              (nil)
                              (clojure.core/let
                               [X__15008
                                (clojure.core/name
                                 input__15003_nth_0__)]
                               (if
                                (clojure.core/string? X__15008)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__15008)
                                 (save__15004)
                                 (f__16391))
                                (f__16391)))
                              (f__16391)))
                            (f__16391)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__14712_nth_1___r___l__
                           (clojure.core/subvec
                            input__14712_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__14712_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__14712_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__14712_nth_1___r___r__
                             (clojure.core/subvec
                              input__14712_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__14712_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__14712_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__14712_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__14997
                                (clojure.core/namespace
                                 input__14712_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__14997
                                (nil)
                                (clojure.core/let
                                 [X__14999
                                  (clojure.core/name
                                   input__14712_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__14999)
                                  (clojure.core/let
                                   [ret__15000
                                    (clojure.core/re-matches
                                     #"\.\.(\d+)"
                                     X__14999)]
                                   (if
                                    (clojure.core/some? ret__15000)
                                    (if
                                     (clojure.core/vector? ret__15000)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__15000)
                                       2)
                                      (clojure.core/let
                                       [ret__15000_nth_1__
                                        (clojure.core/nth
                                         ret__15000
                                         1)]
                                       (clojure.core/let
                                        [?n ret__15000_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__14712_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__9586__auto__
                                           (def__14986
                                            input__14712_nth_2__)]
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
                                                  (CATA__FN__14795
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
                                                  (CATA__FN__14795
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
                   (meander.runtime.zeta/fail? result__16388)
                   (recur (clojure.core/next search_space__16387))
                   result__16388))
                 (state__16310)))
               (state__16310))
              (state__16310)))
            (state__16310))
           (state__16310))))
        (state__16310
         []
         (if
          (clojure.core/vector? input__14712)
          (if
           (clojure.core/= (clojure.core/count input__14712) 3)
           (clojure.core/let
            [input__14712_nth_0__
             (clojure.core/nth input__14712 0)
             input__14712_nth_1__
             (clojure.core/nth input__14712 1)
             input__14712_nth_2__
             (clojure.core/nth input__14712 2)]
            (clojure.core/case
             input__14712_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__14712_nth_1__)
              (clojure.core/let
               [input__14712_nth_1___l__
                (clojure.core/subvec
                 input__14712_nth_1__
                 0
                 (clojure.core/min
                  (clojure.core/count input__14712_nth_1__)
                  1))]
               (if
                (clojure.core/=
                 (clojure.core/count input__14712_nth_1___l__)
                 1)
                (clojure.core/let
                 [input__14712_nth_1___r__
                  (clojure.core/subvec input__14712_nth_1__ 1)]
                 (clojure.core/let
                  [input__14712_nth_1___l___nth_0__
                   (clojure.core/nth input__14712_nth_1___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14712_nth_1___l___nth_0__)
                   (clojure.core/let
                    [X__15031
                     (clojure.core/namespace
                      input__14712_nth_1___l___nth_0__)]
                    (clojure.core/case
                     X__15031
                     (nil)
                     (clojure.core/let
                      [X__15033
                       (clojure.core/name
                        input__14712_nth_1___l___nth_0__)]
                      (if
                       (clojure.core/string? X__15033)
                       (clojure.core/let
                        [ret__15034
                         (clojure.core/re-matches
                          #"\.\.(\?.+)"
                          X__15033)]
                        (if
                         (clojure.core/some? ret__15034)
                         (if
                          (clojure.core/vector? ret__15034)
                          (if
                           (clojure.core/=
                            (clojure.core/count ret__15034)
                            2)
                           (clojure.core/let
                            [ret__15034_nth_1__
                             (clojure.core/nth ret__15034 1)]
                            (clojure.core/let
                             [?n ret__15034_nth_1__]
                             (clojure.core/let
                              [?operator
                               input__14712_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?rest input__14712_nth_1___r__]
                               (clojure.core/let
                                [?env input__14712_nth_2__]
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
                           (state__16311))
                          (state__16311))
                         (state__16311)))
                       (state__16311)))
                     (state__16311)))
                   (state__16311))))
                (state__16311)))
              (state__16311))
             (state__16311)))
           (state__16311))
          (state__16311)))
        (state__16311
         []
         (clojure.core/letfn
          [(def__15037
            [arg__15061]
            (clojure.core/letfn
             [(state__16392
               []
               (clojure.core/let
                [x__15062 :string-logical-plus]
                (clojure.core/let
                 [?tag x__15062]
                 (if
                  (clojure.core/map? arg__15061)
                  (clojure.core/let
                   [VAL__15063 (.valAt arg__15061 :context)]
                   (clojure.core/case
                    VAL__15063
                    (:string)
                    (clojure.core/let [?env arg__15061] [?tag ?env])
                    (state__16393)))
                  (state__16393)))))
              (state__16393
               []
               (clojure.core/let
                [x__15064 :logical-plus]
                (clojure.core/let
                 [?tag x__15064]
                 (clojure.core/let [?env arg__15061] [?tag ?env]))))]
             (state__16392)))]
          (if
           (clojure.core/vector? input__14712)
           (if
            (clojure.core/= (clojure.core/count input__14712) 3)
            (clojure.core/let
             [input__14712_nth_0__
              (clojure.core/nth input__14712 0)
              input__14712_nth_1__
              (clojure.core/nth input__14712 1)
              input__14712_nth_2__
              (clojure.core/nth input__14712 2)]
             (clojure.core/case
              input__14712_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__14712_nth_1__)
               (clojure.core/loop
                [search_space__16394
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__14712_nth_1__)]
                (if
                 (clojure.core/seq search_space__16394)
                 (clojure.core/let
                  [input__14712_nth_1___parts__
                   (clojure.core/first search_space__16394)
                   result__16395
                   (clojure.core/let
                    [input__14712_nth_1___l__
                     (clojure.core/nth input__14712_nth_1___parts__ 0)
                     input__14712_nth_1___r__
                     (clojure.core/nth input__14712_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__9750__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__14712_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__15054]
                         (clojure.core/let
                          [input__15054_nth_0__
                           (clojure.core/nth input__15054 0)]
                          (clojure.core/letfn
                           [(save__15055
                             []
                             (meander.runtime.zeta/fail))
                            (f__16398
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__15054_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol? input__15054_nth_0__)
                            (clojure.core/let
                             [X__15057
                              (clojure.core/namespace
                               input__15054_nth_0__)]
                             (clojure.core/case
                              X__15057
                              (nil)
                              (clojure.core/let
                               [X__15059
                                (clojure.core/name
                                 input__15054_nth_0__)]
                               (if
                                (clojure.core/string? X__15059)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__15059)
                                 (save__15055)
                                 (f__16398))
                                (f__16398)))
                              (f__16398)))
                            (f__16398)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__14712_nth_1___r___l__
                           (clojure.core/subvec
                            input__14712_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__14712_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__14712_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__14712_nth_1___r___r__
                             (clojure.core/subvec
                              input__14712_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__14712_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__14712_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__14712_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__15048
                                (clojure.core/namespace
                                 input__14712_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__15048
                                (nil)
                                (clojure.core/let
                                 [X__15050
                                  (clojure.core/name
                                   input__14712_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__15050)
                                  (clojure.core/let
                                   [ret__15051
                                    (clojure.core/re-matches
                                     #"\.\.(\?.+)"
                                     X__15050)]
                                   (if
                                    (clojure.core/some? ret__15051)
                                    (if
                                     (clojure.core/vector? ret__15051)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__15051)
                                       2)
                                      (clojure.core/let
                                       [ret__15051_nth_1__
                                        (clojure.core/nth
                                         ret__15051
                                         1)]
                                       (clojure.core/let
                                        [?n ret__15051_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__14712_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__9586__auto__
                                           (def__15037
                                            input__14712_nth_2__)]
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
                                                  (CATA__FN__14795
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
                                                  (CATA__FN__14795
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
                   (meander.runtime.zeta/fail? result__16395)
                   (recur (clojure.core/next search_space__16394))
                   result__16395))
                 (state__16312)))
               (state__16312))
              (state__16312)))
            (state__16312))
           (state__16312))))
        (state__16312
         []
         (if
          (clojure.core/vector? input__14712)
          (if
           (clojure.core/= (clojure.core/count input__14712) 3)
           (clojure.core/let
            [input__14712_nth_0__
             (clojure.core/nth input__14712 0)
             input__14712_nth_1__
             (clojure.core/nth input__14712 1)
             input__14712_nth_2__
             (clojure.core/nth input__14712 2)]
            (clojure.core/case
             input__14712_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__14712_nth_1__)
              (clojure.core/let
               [input__14712_nth_1___l__
                (clojure.core/subvec
                 input__14712_nth_1__
                 0
                 (clojure.core/min
                  (clojure.core/count input__14712_nth_1__)
                  1))]
               (if
                (clojure.core/=
                 (clojure.core/count input__14712_nth_1___l__)
                 1)
                (clojure.core/let
                 [input__14712_nth_1___r__
                  (clojure.core/subvec input__14712_nth_1__ 1)]
                 (clojure.core/let
                  [input__14712_nth_1___l___nth_0__
                   (clojure.core/nth input__14712_nth_1___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14712_nth_1___l___nth_0__)
                   (clojure.core/let
                    [X__15082
                     (clojure.core/namespace
                      input__14712_nth_1___l___nth_0__)]
                    (clojure.core/case
                     X__15082
                     (nil)
                     (clojure.core/let
                      [X__15084
                       (clojure.core/name
                        input__14712_nth_1___l___nth_0__)]
                      (if
                       (clojure.core/string? X__15084)
                       (clojure.core/let
                        [ret__15085
                         (clojure.core/re-matches
                          #"\.\.(!.+)"
                          X__15084)]
                        (if
                         (clojure.core/some? ret__15085)
                         (if
                          (clojure.core/vector? ret__15085)
                          (if
                           (clojure.core/=
                            (clojure.core/count ret__15085)
                            2)
                           (clojure.core/let
                            [ret__15085_nth_1__
                             (clojure.core/nth ret__15085 1)]
                            (clojure.core/let
                             [?n ret__15085_nth_1__]
                             (clojure.core/let
                              [?operator
                               input__14712_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?rest input__14712_nth_1___r__]
                               (clojure.core/let
                                [?env input__14712_nth_2__]
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
                           (state__16313))
                          (state__16313))
                         (state__16313)))
                       (state__16313)))
                     (state__16313)))
                   (state__16313))))
                (state__16313)))
              (state__16313))
             (state__16313)))
           (state__16313))
          (state__16313)))
        (state__16313
         []
         (clojure.core/letfn
          [(def__15088
            [arg__15112]
            (clojure.core/letfn
             [(state__16399
               []
               (clojure.core/let
                [x__15113 :string-memory-plus]
                (clojure.core/let
                 [?tag x__15113]
                 (if
                  (clojure.core/map? arg__15112)
                  (clojure.core/let
                   [VAL__15114 (.valAt arg__15112 :context)]
                   (clojure.core/case
                    VAL__15114
                    (:string)
                    (clojure.core/let [?env arg__15112] [?tag ?env])
                    (state__16400)))
                  (state__16400)))))
              (state__16400
               []
               (clojure.core/let
                [x__15115 :memory-plus]
                (clojure.core/let
                 [?tag x__15115]
                 (clojure.core/let [?env arg__15112] [?tag ?env]))))]
             (state__16399)))]
          (if
           (clojure.core/vector? input__14712)
           (if
            (clojure.core/= (clojure.core/count input__14712) 3)
            (clojure.core/let
             [input__14712_nth_0__
              (clojure.core/nth input__14712 0)
              input__14712_nth_1__
              (clojure.core/nth input__14712 1)
              input__14712_nth_2__
              (clojure.core/nth input__14712 2)]
             (clojure.core/case
              input__14712_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__14712_nth_1__)
               (clojure.core/loop
                [search_space__16401
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__14712_nth_1__)]
                (if
                 (clojure.core/seq search_space__16401)
                 (clojure.core/let
                  [input__14712_nth_1___parts__
                   (clojure.core/first search_space__16401)
                   result__16402
                   (clojure.core/let
                    [input__14712_nth_1___l__
                     (clojure.core/nth input__14712_nth_1___parts__ 0)
                     input__14712_nth_1___r__
                     (clojure.core/nth input__14712_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__9750__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__14712_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__15105]
                         (clojure.core/let
                          [input__15105_nth_0__
                           (clojure.core/nth input__15105 0)]
                          (clojure.core/letfn
                           [(save__15106
                             []
                             (meander.runtime.zeta/fail))
                            (f__16405
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__15105_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol? input__15105_nth_0__)
                            (clojure.core/let
                             [X__15108
                              (clojure.core/namespace
                               input__15105_nth_0__)]
                             (clojure.core/case
                              X__15108
                              (nil)
                              (clojure.core/let
                               [X__15110
                                (clojure.core/name
                                 input__15105_nth_0__)]
                               (if
                                (clojure.core/string? X__15110)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__15110)
                                 (save__15106)
                                 (f__16405))
                                (f__16405)))
                              (f__16405)))
                            (f__16405)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__14712_nth_1___r___l__
                           (clojure.core/subvec
                            input__14712_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__14712_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__14712_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__14712_nth_1___r___r__
                             (clojure.core/subvec
                              input__14712_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__14712_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__14712_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__14712_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__15099
                                (clojure.core/namespace
                                 input__14712_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__15099
                                (nil)
                                (clojure.core/let
                                 [X__15101
                                  (clojure.core/name
                                   input__14712_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__15101)
                                  (clojure.core/let
                                   [ret__15102
                                    (clojure.core/re-matches
                                     #"\.\.(\!.+)"
                                     X__15101)]
                                   (if
                                    (clojure.core/some? ret__15102)
                                    (if
                                     (clojure.core/vector? ret__15102)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__15102)
                                       2)
                                      (clojure.core/let
                                       [ret__15102_nth_1__
                                        (clojure.core/nth
                                         ret__15102
                                         1)]
                                       (clojure.core/let
                                        [?n ret__15102_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__14712_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__9586__auto__
                                           (def__15088
                                            input__14712_nth_2__)]
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
                                                  (CATA__FN__14795
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
                                                  (CATA__FN__14795
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
                   (meander.runtime.zeta/fail? result__16402)
                   (recur (clojure.core/next search_space__16401))
                   result__16402))
                 (state__16314)))
               (state__16314))
              (state__16314)))
            (state__16314))
           (state__16314))))
        (state__16314
         []
         (if
          (clojure.core/vector? input__14712)
          (clojure.core/letfn
           [(state__16406
             []
             (if
              (clojure.core/= (clojure.core/count input__14712) 3)
              (clojure.core/let
               [input__14712_nth_0__
                (clojure.core/nth input__14712 0)
                input__14712_nth_1__
                (clojure.core/nth input__14712 1)
                input__14712_nth_2__
                (clojure.core/nth input__14712 2)]
               (clojure.core/case
                input__14712_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__14712_nth_1__)
                 (clojure.core/let
                  [!xs (clojure.core/vec input__14712_nth_1__)]
                  (clojure.core/let
                   [?env input__14712_nth_2__]
                   (try
                    [(clojure.core/let
                      [!xs__counter
                       (meander.runtime.zeta/iterator !xs)]
                      (clojure.core/let
                       [CATA_RESULT__10889__auto__
                        (CATA__FN__14795
                         ['meander.dev.parse.zeta/make-cat
                          (clojure.core/into
                           []
                           (clojure.core/loop
                            [return__14796 (clojure.core/transient [])]
                            (if
                             (clojure.core/and (.hasNext !xs__counter))
                             (recur
                              (clojure.core/conj!
                               return__14796
                               (clojure.core/let
                                [CATA_RESULT__10889__auto__
                                 (CATA__FN__14795
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
                              return__14796))))
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
                 (state__16407))
                (state__16407)))
              (state__16407)))
            (state__16407
             []
             (if
              (clojure.core/= (clojure.core/count input__14712) 4)
              (clojure.core/let
               [input__14712_nth_0__
                (clojure.core/nth input__14712 0)
                input__14712_nth_1__
                (clojure.core/nth input__14712 1)
                input__14712_nth_2__
                (clojure.core/nth input__14712 2)]
               (clojure.core/letfn
                [(state__16409
                  []
                  (clojure.core/let
                   [input__14712_nth_3__
                    (clojure.core/nth input__14712 3)]
                   (clojure.core/case
                    input__14712_nth_0__
                    (meander.dev.parse.zeta/make-cat)
                    (if
                     (clojure.core/vector? input__14712_nth_1__)
                     (clojure.core/letfn
                      [(state__16416
                        []
                        (clojure.core/case
                         input__14712_nth_1__
                         ([])
                         (clojure.core/let
                          [?next input__14712_nth_2__]
                          (clojure.core/let
                           [?env input__14712_nth_3__]
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
                         (state__16417)))
                       (state__16417
                        []
                        (clojure.core/loop
                         [search_space__16418
                          (meander.runtime.zeta/epsilon-partitions
                           2
                           input__14712_nth_1__)]
                         (if
                          (clojure.core/seq search_space__16418)
                          (clojure.core/let
                           [input__14712_nth_1___parts__
                            (clojure.core/first search_space__16418)
                            result__16419
                            (clojure.core/let
                             [input__14712_nth_1___l__
                              (clojure.core/nth
                               input__14712_nth_1___parts__
                               0)
                              input__14712_nth_1___r__
                              (clojure.core/nth
                               input__14712_nth_1___parts__
                               1)]
                             (clojure.core/letfn
                              [(state__16421
                                []
                                (clojure.core/let
                                 [!xs []]
                                 (clojure.core/let
                                  [ret__9750__auto__
                                   (meander.runtime.zeta/epsilon-run-star-1
                                    input__14712_nth_1___l__
                                    [!xs]
                                    (clojure.core/fn
                                     [[!xs] input__15141]
                                     (clojure.core/let
                                      [input__15141_nth_0__
                                       (clojure.core/nth
                                        input__15141
                                        0)]
                                      (clojure.core/letfn
                                       [(save__15142
                                         []
                                         (meander.runtime.zeta/fail))
                                        (f__16425
                                         []
                                         (clojure.core/let
                                          [!xs
                                           (clojure.core/conj
                                            !xs
                                            input__15141_nth_0__)]
                                          [!xs]))]
                                       (if
                                        (clojure.core/map?
                                         input__15141_nth_0__)
                                        (clojure.core/let
                                         [VAL__15143
                                          (.valAt
                                           input__15141_nth_0__
                                           :tag)]
                                         (clojure.core/case
                                          VAL__15143
                                          (:group)
                                          (save__15142)
                                          (f__16425)))
                                        (f__16425)))))
                                    (clojure.core/fn
                                     [[!xs]]
                                     (clojure.core/let
                                      [input__14712_nth_1___r___l__
                                       (clojure.core/subvec
                                        input__14712_nth_1___r__
                                        0
                                        (clojure.core/min
                                         (clojure.core/count
                                          input__14712_nth_1___r__)
                                         1))]
                                      (if
                                       (clojure.core/=
                                        (clojure.core/count
                                         input__14712_nth_1___r___l__)
                                        1)
                                       (clojure.core/let
                                        [input__14712_nth_1___r___r__
                                         (clojure.core/subvec
                                          input__14712_nth_1___r__
                                          1)]
                                        (clojure.core/let
                                         [input__14712_nth_1___r___l___nth_0__
                                          (clojure.core/nth
                                           input__14712_nth_1___r___l__
                                           0)]
                                         (if
                                          (clojure.core/map?
                                           input__14712_nth_1___r___l___nth_0__)
                                          (clojure.core/let
                                           [VAL__15140
                                            (.valAt
                                             input__14712_nth_1___r___l___nth_0__
                                             :tag)]
                                           (clojure.core/case
                                            VAL__15140
                                            (:group)
                                            (clojure.core/let
                                             [?group
                                              input__14712_nth_1___r___l___nth_0__]
                                             (clojure.core/let
                                              [?rest
                                               input__14712_nth_1___r___r__]
                                              (clojure.core/let
                                               [?next
                                                input__14712_nth_2__]
                                               (clojure.core/let
                                                [?env
                                                 input__14712_nth_3__]
                                                (try
                                                 [(clojure.core/let
                                                   [!xs__counter
                                                    (meander.runtime.zeta/iterator
                                                     !xs)]
                                                   (clojure.core/let
                                                    [CATA_RESULT__10889__auto__
                                                     (CATA__FN__14795
                                                      ['meander.dev.parse.zeta/make-join
                                                       (clojure.core/let
                                                        [CATA_RESULT__10889__auto__
                                                         (CATA__FN__14795
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
                                                         (CATA__FN__14795
                                                          ['meander.dev.parse.zeta/make-join
                                                           ?group
                                                           (clojure.core/let
                                                            [CATA_RESULT__10889__auto__
                                                             (CATA__FN__14795
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
                                            (state__16422)))
                                          (state__16422))))
                                       (state__16422)))))]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    ret__9750__auto__)
                                   (state__16422)
                                   ret__9750__auto__))))
                               (state__16422
                                []
                                (clojure.core/let
                                 [!xs []]
                                 (clojure.core/let
                                  [ret__9750__auto__
                                   (meander.runtime.zeta/epsilon-run-star-1
                                    input__14712_nth_1___l__
                                    [!xs]
                                    (clojure.core/fn
                                     [[!xs] input__15152]
                                     (clojure.core/let
                                      [input__15152_nth_0__
                                       (clojure.core/nth
                                        input__15152
                                        0)]
                                      (clojure.core/letfn
                                       [(save__15153
                                         []
                                         (meander.runtime.zeta/fail))
                                        (f__16427
                                         []
                                         (clojure.core/let
                                          [!xs
                                           (clojure.core/conj
                                            !xs
                                            input__15152_nth_0__)]
                                          [!xs]))]
                                       (if
                                        (clojure.core/map?
                                         input__15152_nth_0__)
                                        (clojure.core/let
                                         [VAL__15154
                                          (.valAt
                                           input__15152_nth_0__
                                           :tag)]
                                         (clojure.core/case
                                          VAL__15154
                                          (:star)
                                          (save__15153)
                                          (f__16427)))
                                        (f__16427)))))
                                    (clojure.core/fn
                                     [[!xs]]
                                     (clojure.core/let
                                      [input__14712_nth_1___r___l__
                                       (clojure.core/subvec
                                        input__14712_nth_1___r__
                                        0
                                        (clojure.core/min
                                         (clojure.core/count
                                          input__14712_nth_1___r__)
                                         1))]
                                      (if
                                       (clojure.core/=
                                        (clojure.core/count
                                         input__14712_nth_1___r___l__)
                                        1)
                                       (clojure.core/let
                                        [input__14712_nth_1___r___r__
                                         (clojure.core/subvec
                                          input__14712_nth_1___r__
                                          1)]
                                        (clojure.core/let
                                         [input__14712_nth_1___r___l___nth_0__
                                          (clojure.core/nth
                                           input__14712_nth_1___r___l__
                                           0)]
                                         (if
                                          (clojure.core/map?
                                           input__14712_nth_1___r___l___nth_0__)
                                          (clojure.core/let
                                           [VAL__15151
                                            (.valAt
                                             input__14712_nth_1___r___l___nth_0__
                                             :tag)]
                                           (clojure.core/case
                                            VAL__15151
                                            (:star)
                                            (clojure.core/let
                                             [?star
                                              input__14712_nth_1___r___l___nth_0__]
                                             (clojure.core/let
                                              [?rest
                                               input__14712_nth_1___r___r__]
                                              (clojure.core/let
                                               [?next
                                                input__14712_nth_2__]
                                               (clojure.core/let
                                                [?env
                                                 input__14712_nth_3__]
                                                (try
                                                 [(clojure.core/let
                                                   [!xs__counter
                                                    (meander.runtime.zeta/iterator
                                                     !xs)]
                                                   (clojure.core/let
                                                    [CATA_RESULT__10889__auto__
                                                     (CATA__FN__14795
                                                      ['meander.dev.parse.zeta/make-join
                                                       (clojure.core/let
                                                        [CATA_RESULT__10889__auto__
                                                         (CATA__FN__14795
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
                                                         (CATA__FN__14795
                                                          ['meander.dev.parse.zeta/make-join
                                                           ?star
                                                           (clojure.core/let
                                                            [CATA_RESULT__10889__auto__
                                                             (CATA__FN__14795
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
                                            (state__16423)))
                                          (state__16423))))
                                       (state__16423)))))]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    ret__9750__auto__)
                                   (state__16423)
                                   ret__9750__auto__))))
                               (state__16423
                                []
                                (clojure.core/let
                                 [input__14712_nth_1___l___l__
                                  (clojure.core/subvec
                                   input__14712_nth_1___l__
                                   0
                                   (clojure.core/min
                                    (clojure.core/count
                                     input__14712_nth_1___l__)
                                    1))]
                                 (if
                                  (clojure.core/=
                                   (clojure.core/count
                                    input__14712_nth_1___l___l__)
                                   1)
                                  (clojure.core/let
                                   [input__14712_nth_1___l___r__
                                    (clojure.core/subvec
                                     input__14712_nth_1___l__
                                     1)]
                                   (clojure.core/let
                                    [input__14712_nth_1___l___l___nth_0__
                                     (clojure.core/nth
                                      input__14712_nth_1___l___l__
                                      0)]
                                    (clojure.core/letfn
                                     [(save__15162
                                       []
                                       (meander.runtime.zeta/fail))
                                      (f__16428
                                       []
                                       (clojure.core/let
                                        [!xs []]
                                        (clojure.core/let
                                         [!xs
                                          (clojure.core/conj
                                           !xs
                                           input__14712_nth_1___l___l___nth_0__)]
                                         (clojure.core/loop
                                          [i__9723__auto__
                                           0
                                           coll__16429
                                           input__14712_nth_1___l___r__
                                           [!xs]
                                           [!xs]]
                                          (clojure.core/let
                                           [input__15167
                                            (clojure.core/subvec
                                             coll__16429
                                             0
                                             (clojure.core/min
                                              (clojure.core/count
                                               coll__16429)
                                              1))]
                                           (if
                                            (clojure.core/=
                                             (clojure.core/count
                                              input__15167)
                                             1)
                                            (clojure.core/let
                                             [result__9724__auto__
                                              (clojure.core/let
                                               [input__15167_nth_0__
                                                (clojure.core/nth
                                                 input__15167
                                                 0)]
                                               (clojure.core/letfn
                                                [(save__15168
                                                  []
                                                  (meander.runtime.zeta/fail))
                                                 (f__16430
                                                  []
                                                  (clojure.core/let
                                                   [!xs
                                                    (clojure.core/conj
                                                     !xs
                                                     input__15167_nth_0__)]
                                                   [!xs]))]
                                                (if
                                                 (clojure.core/map?
                                                  input__15167_nth_0__)
                                                 (clojure.core/let
                                                  [VAL__15169
                                                   (.valAt
                                                    input__15167_nth_0__
                                                    :tag)]
                                                  (clojure.core/case
                                                   VAL__15169
                                                   (:reference)
                                                   (save__15168)
                                                   (f__16430)))
                                                 (f__16430))))]
                                             (if
                                              (meander.runtime.zeta/fail?
                                               result__9724__auto__)
                                              (meander.runtime.zeta/fail)
                                              (recur
                                               (clojure.core/inc
                                                i__9723__auto__)
                                               (clojure.core/subvec
                                                coll__16429
                                                1)
                                               result__9724__auto__)))
                                            (if
                                             (clojure.core/or
                                              (clojure.core/seq
                                               coll__16429)
                                              (clojure.core/<
                                               i__9723__auto__
                                               0))
                                             (meander.runtime.zeta/fail)
                                             (clojure.core/let
                                              [input__14712_nth_1___r___l__
                                               (clojure.core/subvec
                                                input__14712_nth_1___r__
                                                0
                                                (clojure.core/min
                                                 (clojure.core/count
                                                  input__14712_nth_1___r__)
                                                 1))]
                                              (if
                                               (clojure.core/=
                                                (clojure.core/count
                                                 input__14712_nth_1___r___l__)
                                                1)
                                               (clojure.core/let
                                                [input__14712_nth_1___r___r__
                                                 (clojure.core/subvec
                                                  input__14712_nth_1___r__
                                                  1)]
                                                (clojure.core/let
                                                 [input__14712_nth_1___r___l___nth_0__
                                                  (clojure.core/nth
                                                   input__14712_nth_1___r___l__
                                                   0)]
                                                 (if
                                                  (clojure.core/map?
                                                   input__14712_nth_1___r___l___nth_0__)
                                                  (clojure.core/let
                                                   [VAL__15166
                                                    (.valAt
                                                     input__14712_nth_1___r___l___nth_0__
                                                     :tag)]
                                                   (clojure.core/case
                                                    VAL__15166
                                                    (:reference)
                                                    (clojure.core/let
                                                     [?reference
                                                      input__14712_nth_1___r___l___nth_0__]
                                                     (clojure.core/let
                                                      [?rest
                                                       input__14712_nth_1___r___r__]
                                                      (clojure.core/let
                                                       [?next
                                                        input__14712_nth_2__]
                                                       (clojure.core/let
                                                        [?env
                                                         input__14712_nth_3__]
                                                        (try
                                                         [(clojure.core/let
                                                           [!xs__counter
                                                            (meander.runtime.zeta/iterator
                                                             !xs)]
                                                           (clojure.core/let
                                                            [CATA_RESULT__10889__auto__
                                                             (CATA__FN__14795
                                                              ['meander.dev.parse.zeta/make-join
                                                               (clojure.core/let
                                                                [CATA_RESULT__10889__auto__
                                                                 (CATA__FN__14795
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
                                                                 (CATA__FN__14795
                                                                  ['meander.dev.parse.zeta/make-join
                                                                   (clojure.core/let
                                                                    [CATA_RESULT__10889__auto__
                                                                     (CATA__FN__14795
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
                                                                     (CATA__FN__14795
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
                                       input__14712_nth_1___l___l___nth_0__)
                                      (clojure.core/let
                                       [VAL__15163
                                        (.valAt
                                         input__14712_nth_1___l___l___nth_0__
                                         :tag)]
                                       (clojure.core/case
                                        VAL__15163
                                        (:reference)
                                        (save__15162)
                                        (f__16428)))
                                      (f__16428)))))
                                  (meander.runtime.zeta/fail))))]
                              (state__16421)))]
                           (if
                            (meander.runtime.zeta/fail? result__16419)
                            (recur
                             (clojure.core/next search_space__16418))
                            result__16419))
                          (state__16410))))]
                      (state__16416))
                     (state__16410))
                    (state__16410))))
                 (state__16410
                  []
                  (clojure.core/case
                   input__14712_nth_0__
                   (meander.dev.parse.zeta/make-cat)
                   (if
                    (clojure.core/vector? input__14712_nth_1__)
                    (clojure.core/let
                     [input__14712_nth_1___l__
                      (clojure.core/subvec
                       input__14712_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__14712_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__14712_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__14712_nth_1___r__
                        (clojure.core/subvec input__14712_nth_1__ 1)]
                       (clojure.core/let
                        [input__14712_nth_1___l___nth_0__
                         (clojure.core/nth input__14712_nth_1___l__ 0)]
                        (if
                         (clojure.core/map?
                          input__14712_nth_1___l___nth_0__)
                         (clojure.core/let
                          [VAL__15178
                           (.valAt
                            input__14712_nth_1___l___nth_0__
                            :tag)]
                          (clojure.core/case
                           VAL__15178
                           (:literal)
                           (clojure.core/let
                            [VAL__15179
                             (.valAt
                              input__14712_nth_1___l___nth_0__
                              :type)]
                            (if
                             (clojure.core/let
                              [x__8646__auto__ VAL__15179]
                              (clojure.core/case
                               x__8646__auto__
                               (:string :char)
                               true
                               false))
                             (clojure.core/let
                              [VAL__15180
                               (.valAt
                                input__14712_nth_1___l___nth_0__
                                :form)]
                              (clojure.core/let
                               [!forms []]
                               (clojure.core/let
                                [!forms
                                 (clojure.core/conj !forms VAL__15180)]
                                (clojure.core/loop
                                 [i__9723__auto__
                                  0
                                  coll__16431
                                  input__14712_nth_1___r__
                                  [!forms]
                                  [!forms]]
                                 (clojure.core/let
                                  [input__15181
                                   (clojure.core/subvec
                                    coll__16431
                                    0
                                    (clojure.core/min
                                     (clojure.core/count coll__16431)
                                     1))]
                                  (if
                                   (clojure.core/=
                                    (clojure.core/count input__15181)
                                    1)
                                   (clojure.core/let
                                    [result__9724__auto__
                                     (clojure.core/let
                                      [input__15181_nth_0__
                                       (clojure.core/nth
                                        input__15181
                                        0)]
                                      (if
                                       (clojure.core/map?
                                        input__15181_nth_0__)
                                       (clojure.core/let
                                        [VAL__15182
                                         (.valAt
                                          input__15181_nth_0__
                                          :tag)]
                                        (clojure.core/case
                                         VAL__15182
                                         (:literal)
                                         (clojure.core/let
                                          [VAL__15183
                                           (.valAt
                                            input__15181_nth_0__
                                            :type)]
                                          (if
                                           (clojure.core/let
                                            [x__8646__auto__
                                             VAL__15183]
                                            (clojure.core/case
                                             x__8646__auto__
                                             (:string :char)
                                             true
                                             false))
                                           (clojure.core/let
                                            [VAL__15184
                                             (.valAt
                                              input__15181_nth_0__
                                              :form)]
                                            (clojure.core/let
                                             [!forms
                                              (clojure.core/conj
                                               !forms
                                               VAL__15184)]
                                             [!forms]))
                                           (meander.runtime.zeta/fail)))
                                         (meander.runtime.zeta/fail)))
                                       (meander.runtime.zeta/fail)))]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      result__9724__auto__)
                                     (state__16411)
                                     (recur
                                      (clojure.core/inc
                                       i__9723__auto__)
                                      (clojure.core/subvec
                                       coll__16431
                                       1)
                                      result__9724__auto__)))
                                   (if
                                    (clojure.core/or
                                     (clojure.core/seq coll__16431)
                                     (clojure.core/<
                                      i__9723__auto__
                                      0))
                                    (state__16411)
                                    (if
                                     (clojure.core/map?
                                      input__14712_nth_2__)
                                     (clojure.core/let
                                      [VAL__15173
                                       (.valAt
                                        input__14712_nth_2__
                                        :tag)]
                                      (clojure.core/case
                                       VAL__15173
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
                                         e__11829__auto__
                                         (if
                                          (meander.runtime.zeta/fail?
                                           e__11829__auto__)
                                          (meander.runtime.zeta/fail)
                                          (throw e__11829__auto__))))
                                       (state__16411)))
                                     (state__16411)))))))))
                             (state__16411)))
                           (state__16411)))
                         (state__16411))))
                      (state__16411)))
                    (state__16411))
                   (state__16411)))
                 (state__16411
                  []
                  (clojure.core/let
                   [input__14712_nth_3__
                    (clojure.core/nth input__14712 3)]
                   (clojure.core/case
                    input__14712_nth_0__
                    (meander.dev.parse.zeta/make-cat)
                    (clojure.core/letfn
                     [(state__16432
                       []
                       (if
                        (clojure.core/vector? input__14712_nth_1__)
                        (clojure.core/let
                         [input__14712_nth_1___l__
                          (clojure.core/subvec
                           input__14712_nth_1__
                           0
                           (clojure.core/min
                            (clojure.core/count input__14712_nth_1__)
                            1))]
                         (if
                          (clojure.core/=
                           (clojure.core/count
                            input__14712_nth_1___l__)
                           1)
                          (clojure.core/let
                           [input__14712_nth_1___r__
                            (clojure.core/subvec
                             input__14712_nth_1__
                             1)]
                           (clojure.core/let
                            [input__14712_nth_1___l___nth_0__
                             (clojure.core/nth
                              input__14712_nth_1___l__
                              0)]
                            (if
                             (clojure.core/map?
                              input__14712_nth_1___l___nth_0__)
                             (clojure.core/let
                              [VAL__16301
                               (.valAt
                                input__14712_nth_1___l___nth_0__
                                :tag)]
                              (clojure.core/case
                               VAL__16301
                               (:literal)
                               (clojure.core/letfn
                                [(state__16434
                                  []
                                  (clojure.core/let
                                   [VAL__15191
                                    (.valAt
                                     input__14712_nth_1___l___nth_0__
                                     :type)]
                                   (clojure.core/case
                                    VAL__15191
                                    (:string)
                                    (clojure.core/let
                                     [?ast
                                      input__14712_nth_1___l___nth_0__]
                                     (clojure.core/let
                                      [?rest input__14712_nth_1___r__]
                                      (clojure.core/let
                                       [?next input__14712_nth_2__]
                                       (clojure.core/let
                                        [?env input__14712_nth_3__]
                                        (try
                                         [(clojure.core/let
                                           [CATA_RESULT__10889__auto__
                                            (CATA__FN__14795
                                             ['meander.dev.parse.zeta/make-join
                                              ?ast
                                              (clojure.core/let
                                               [CATA_RESULT__10889__auto__
                                                (CATA__FN__14795
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
                                    (state__16435))))
                                 (state__16435
                                  []
                                  (clojure.core/let
                                   [VAL__15201
                                    (.valAt
                                     input__14712_nth_1___l___nth_0__
                                     :form)]
                                   (clojure.core/let
                                    [!forms []]
                                    (clojure.core/let
                                     [!forms
                                      (clojure.core/conj
                                       !forms
                                       VAL__15201)]
                                     (clojure.core/loop
                                      [i__9723__auto__
                                       0
                                       coll__16436
                                       input__14712_nth_1___r__
                                       [!forms]
                                       [!forms]]
                                      (clojure.core/let
                                       [input__15202
                                        (clojure.core/subvec
                                         coll__16436
                                         0
                                         (clojure.core/min
                                          (clojure.core/count
                                           coll__16436)
                                          1))]
                                       (if
                                        (clojure.core/=
                                         (clojure.core/count
                                          input__15202)
                                         1)
                                        (clojure.core/let
                                         [result__9724__auto__
                                          (clojure.core/let
                                           [input__15202_nth_0__
                                            (clojure.core/nth
                                             input__15202
                                             0)]
                                           (if
                                            (clojure.core/map?
                                             input__15202_nth_0__)
                                            (clojure.core/let
                                             [VAL__15203
                                              (.valAt
                                               input__15202_nth_0__
                                               :tag)]
                                             (clojure.core/case
                                              VAL__15203
                                              (:literal)
                                              (clojure.core/let
                                               [VAL__15204
                                                (.valAt
                                                 input__15202_nth_0__
                                                 :form)]
                                               (clojure.core/let
                                                [!forms
                                                 (clojure.core/conj
                                                  !forms
                                                  VAL__15204)]
                                                [!forms]))
                                              (meander.runtime.zeta/fail)))
                                            (meander.runtime.zeta/fail)))]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           result__9724__auto__)
                                          (state__16433)
                                          (recur
                                           (clojure.core/inc
                                            i__9723__auto__)
                                           (clojure.core/subvec
                                            coll__16436
                                            1)
                                           result__9724__auto__)))
                                        (if
                                         (clojure.core/or
                                          (clojure.core/seq
                                           coll__16436)
                                          (clojure.core/<
                                           i__9723__auto__
                                           0))
                                         (state__16433)
                                         (if
                                          (clojure.core/map?
                                           input__14712_nth_2__)
                                          (clojure.core/let
                                           [VAL__15194
                                            (.valAt
                                             input__14712_nth_2__
                                             :tag)]
                                           (clojure.core/case
                                            VAL__15194
                                            (:empty)
                                            (if
                                             (clojure.core/map?
                                              input__14712_nth_3__)
                                             (clojure.core/let
                                              [VAL__15195
                                               (.valAt
                                                input__14712_nth_3__
                                                :context)]
                                              (clojure.core/let
                                               [?context VAL__15195]
                                               (clojure.core/let
                                                [?env
                                                 input__14712_nth_3__]
                                                (try
                                                 [{:tag :literal,
                                                   :type ?context,
                                                   :form
                                                   (clojure.core/into
                                                    []
                                                    !forms)}]
                                                 (catch
                                                  java.lang.Exception
                                                  e__11829__auto__
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    e__11829__auto__)
                                                   (meander.runtime.zeta/fail)
                                                   (throw
                                                    e__11829__auto__)))))))
                                             (state__16433))
                                            (state__16433)))
                                          (state__16433))))))))))]
                                (state__16434))
                               (state__16433)))
                             (state__16433))))
                          (state__16433)))
                        (state__16433)))
                      (state__16433
                       []
                       (clojure.core/let
                        [?sequence input__14712_nth_1__]
                        (clojure.core/let
                         [?next input__14712_nth_2__]
                         (if
                          (clojure.core/map? input__14712_nth_3__)
                          (clojure.core/let
                           [VAL__15208
                            (.valAt input__14712_nth_3__ :context)]
                           (clojure.core/case
                            VAL__15208
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
                            (state__16412)))
                          (state__16412)))))]
                     (state__16432))
                    (state__16412))))
                 (state__16412
                  []
                  (clojure.core/case
                   input__14712_nth_0__
                   (meander.dev.parse.zeta/make-cat)
                   (clojure.core/let
                    [?sequence input__14712_nth_1__]
                    (clojure.core/let
                     [?next input__14712_nth_2__]
                     (try
                      [{:tag :cat, :sequence ?sequence, :next ?next}]
                      (catch
                       java.lang.Exception
                       e__11829__auto__
                       (if
                        (meander.runtime.zeta/fail? e__11829__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__11829__auto__))))))
                   (state__16413)))
                 (state__16413
                  []
                  (clojure.core/let
                   [input__14712_nth_3__
                    (clojure.core/nth input__14712 3)]
                   (clojure.core/case
                    input__14712_nth_0__
                    (meander.dev.parse.zeta/make-join)
                    (if
                     (clojure.core/map? input__14712_nth_1__)
                     (clojure.core/let
                      [VAL__16299 (.valAt input__14712_nth_1__ :tag)]
                      (clojure.core/case
                       VAL__16299
                       (:cat)
                       (clojure.core/let
                        [VAL__15214
                         (.valAt input__14712_nth_1__ :sequence)]
                        (clojure.core/let
                         [?sequence VAL__15214]
                         (clojure.core/let
                          [VAL__15215
                           (.valAt input__14712_nth_1__ :next)]
                          (if
                           (clojure.core/map? VAL__15215)
                           (clojure.core/let
                            [VAL__15216 (.valAt VAL__15215 :tag)]
                            (clojure.core/case
                             VAL__15216
                             (:empty)
                             (clojure.core/let
                              [?right input__14712_nth_2__]
                              (clojure.core/let
                               [?env input__14712_nth_3__]
                               (try
                                [(clojure.core/let
                                  [CATA_RESULT__10889__auto__
                                   (CATA__FN__14795
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
                             (state__16414)))
                           (state__16414)))))
                       (:literal)
                       (clojure.core/let
                        [VAL__15220
                         (.valAt input__14712_nth_1__ :type)]
                        (clojure.core/case
                         VAL__15220
                         (:string)
                         (clojure.core/let
                          [VAL__15221
                           (.valAt input__14712_nth_1__ :form)]
                          (clojure.core/let
                           [?form-1 VAL__15221]
                           (if
                            (clojure.core/map? input__14712_nth_2__)
                            (clojure.core/let
                             [VAL__15222
                              (.valAt input__14712_nth_2__ :tag)]
                             (clojure.core/case
                              VAL__15222
                              (:string-join)
                              (clojure.core/let
                               [VAL__15223
                                (.valAt input__14712_nth_2__ :left)]
                               (if
                                (clojure.core/map? VAL__15223)
                                (clojure.core/let
                                 [VAL__15224 (.valAt VAL__15223 :tag)]
                                 (clojure.core/case
                                  VAL__15224
                                  (:literal)
                                  (clojure.core/let
                                   [VAL__15225
                                    (.valAt VAL__15223 :type)]
                                   (clojure.core/case
                                    VAL__15225
                                    (:string)
                                    (clojure.core/let
                                     [VAL__15226
                                      (.valAt VAL__15223 :form)]
                                     (clojure.core/let
                                      [?form-2 VAL__15226]
                                      (clojure.core/let
                                       [VAL__15227
                                        (.valAt
                                         input__14712_nth_2__
                                         :right)]
                                       (clojure.core/let
                                        [?right VAL__15227]
                                        (if
                                         (clojure.core/map?
                                          input__14712_nth_3__)
                                         (clojure.core/let
                                          [VAL__15228
                                           (.valAt
                                            input__14712_nth_3__
                                            :context)]
                                          (clojure.core/case
                                           VAL__15228
                                           (:string)
                                           (clojure.core/let
                                            [?env input__14712_nth_3__]
                                            (try
                                             [(clojure.core/let
                                               [CATA_RESULT__10889__auto__
                                                (CATA__FN__14795
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
                                                e__11829__auto__)))))
                                           (state__16414)))
                                         (state__16414))))))
                                    (state__16414)))
                                  (state__16414)))
                                (state__16414)))
                              (state__16414)))
                            (state__16414))))
                         (state__16414)))
                       (state__16414)))
                     (state__16414))
                    (state__16414))))
                 (state__16414
                  []
                  (clojure.core/case
                   input__14712_nth_0__
                   (meander.dev.parse.zeta/make-join)
                   (if
                    (clojure.core/map? input__14712_nth_1__)
                    (clojure.core/let
                     [VAL__16300 (.valAt input__14712_nth_1__ :tag)]
                     (clojure.core/case
                      VAL__16300
                      (:cat)
                      (clojure.core/let
                       [?ast input__14712_nth_1__]
                       (if
                        (clojure.core/map? input__14712_nth_2__)
                        (clojure.core/let
                         [VAL__15232
                          (.valAt input__14712_nth_2__ :tag)]
                         (clojure.core/case
                          VAL__15232
                          (:cat)
                          (clojure.core/let
                           [VAL__15233
                            (.valAt input__14712_nth_2__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__15233]
                            (clojure.core/let
                             [VAL__15234
                              (.valAt input__14712_nth_2__ :next)]
                             (clojure.core/let
                              [?next VAL__15234]
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
                          (state__16415)))
                        (state__16415)))
                      (:string-cat)
                      (clojure.core/let
                       [?ast input__14712_nth_1__]
                       (if
                        (clojure.core/map? input__14712_nth_2__)
                        (clojure.core/let
                         [VAL__15238
                          (.valAt input__14712_nth_2__ :tag)]
                         (clojure.core/case
                          VAL__15238
                          (:string-cat)
                          (clojure.core/let
                           [VAL__15239
                            (.valAt input__14712_nth_2__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__15239]
                            (clojure.core/let
                             [VAL__15240
                              (.valAt input__14712_nth_2__ :next)]
                             (clojure.core/let
                              [?next VAL__15240]
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
                          (state__16415)))
                        (state__16415)))
                      (state__16415)))
                    (state__16415))
                   (state__16415)))
                 (state__16415
                  []
                  (clojure.core/let
                   [input__14712_nth_3__
                    (clojure.core/nth input__14712 3)]
                   (clojure.core/case
                    input__14712_nth_0__
                    (meander.dev.parse.zeta/make-join)
                    (clojure.core/letfn
                     [(state__16437
                       []
                       (if
                        (clojure.core/map? input__14712_nth_1__)
                        (clojure.core/let
                         [VAL__16304
                          (.valAt input__14712_nth_1__ :next)
                          VAL__16303
                          (.valAt input__14712_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__16303
                          (:string-cat)
                          (clojure.core/let
                           [VAL__15244
                            (.valAt input__14712_nth_1__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__15244]
                            (if
                             (clojure.core/map? VAL__16304)
                             (clojure.core/let
                              [VAL__15246 (.valAt VAL__16304 :tag)]
                              (clojure.core/case
                               VAL__15246
                               (:empty)
                               (clojure.core/let
                                [?right input__14712_nth_2__]
                                (clojure.core/let
                                 [?env input__14712_nth_3__]
                                 (try
                                  [(clojure.core/let
                                    [CATA_RESULT__10889__auto__
                                     (CATA__FN__14795
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
                               (state__16438)))
                             (state__16438))))
                          (:string-star)
                          (clojure.core/let
                           [VAL__15250
                            (.valAt input__14712_nth_1__ :pattern)]
                           (clojure.core/let
                            [?pattern VAL__15250]
                            (if
                             (clojure.core/map? VAL__16304)
                             (clojure.core/let
                              [VAL__15252 (.valAt VAL__16304 :tag)]
                              (clojure.core/case
                               VAL__15252
                               (:empty)
                               (clojure.core/let
                                [?right input__14712_nth_2__]
                                (if
                                 (clojure.core/map?
                                  input__14712_nth_3__)
                                 (clojure.core/let
                                  [VAL__15253
                                   (.valAt
                                    input__14712_nth_3__
                                    :context)]
                                  (clojure.core/case
                                   VAL__15253
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
                                   (state__16438)))
                                 (state__16438)))
                               (state__16438)))
                             (state__16438))))
                          (:string-join)
                          (clojure.core/let
                           [VAL__15257
                            (.valAt input__14712_nth_1__ :left)]
                           (clojure.core/let
                            [?left VAL__15257]
                            (clojure.core/let
                             [VAL__15258
                              (.valAt input__14712_nth_1__ :right)]
                             (clojure.core/let
                              [?right-1 VAL__15258]
                              (clojure.core/let
                               [?right-2 input__14712_nth_2__]
                               (if
                                (clojure.core/map?
                                 input__14712_nth_3__)
                                (clojure.core/let
                                 [VAL__15259
                                  (.valAt
                                   input__14712_nth_3__
                                   :context)]
                                 (clojure.core/case
                                  VAL__15259
                                  (:string)
                                  (clojure.core/let
                                   [?env input__14712_nth_3__]
                                   (try
                                    [{:tag :string-join,
                                      :left ?left,
                                      :right
                                      (clojure.core/let
                                       [CATA_RESULT__10889__auto__
                                        (CATA__FN__14795
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
                                  (state__16438)))
                                (state__16438)))))))
                          (state__16438)))
                        (state__16438)))
                      (state__16438
                       []
                       (clojure.core/let
                        [?left input__14712_nth_1__]
                        (if
                         (clojure.core/map? input__14712_nth_2__)
                         (clojure.core/let
                          [VAL__15262
                           (.valAt input__14712_nth_2__ :tag)]
                          (clojure.core/case
                           VAL__15262
                           (:empty)
                           (clojure.core/let
                            [?env input__14712_nth_3__]
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
                           (state__16439)))
                         (state__16439))))
                      (state__16439
                       []
                       (if
                        (clojure.core/map? input__14712_nth_1__)
                        (clojure.core/let
                         [VAL__16302
                          (.valAt input__14712_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__16302
                          (:empty)
                          (clojure.core/let
                           [?right input__14712_nth_2__]
                           (clojure.core/let
                            [?env input__14712_nth_3__]
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
                           [VAL__15269
                            (.valAt input__14712_nth_1__ :next)]
                           (if
                            (clojure.core/map? VAL__15269)
                            (clojure.core/let
                             [VAL__15270 (.valAt VAL__15269 :tag)]
                             (clojure.core/case
                              VAL__15270
                              (:empty)
                              (clojure.core/let
                               [?left input__14712_nth_1__]
                               (clojure.core/let
                                [?right input__14712_nth_2__]
                                (clojure.core/let
                                 [?env input__14712_nth_3__]
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
                              (state__16440)))
                            (state__16440)))
                          (state__16440)))
                        (state__16440)))
                      (state__16440
                       []
                       (clojure.core/let
                        [?left input__14712_nth_1__]
                        (clojure.core/let
                         [?right input__14712_nth_2__]
                         (clojure.core/letfn
                          [(state__16441
                            []
                            (if
                             (clojure.core/map? input__14712_nth_3__)
                             (clojure.core/let
                              [VAL__15273
                               (.valAt input__14712_nth_3__ :context)]
                              (clojure.core/case
                               VAL__15273
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
                               (state__16442)))
                             (state__16442)))
                           (state__16442
                            []
                            (clojure.core/let
                             [?env input__14712_nth_3__]
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
                          (state__16441)))))]
                     (state__16437))
                    (state__16408))))]
                (state__16409)))
              (state__16408)))
            (state__16408
             []
             (if
              (clojure.core/= (clojure.core/count input__14712) 3)
              (clojure.core/let
               [input__14712_nth_0__
                (clojure.core/nth input__14712 0)
                input__14712_nth_1__
                (clojure.core/nth input__14712 1)
                input__14712_nth_2__
                (clojure.core/nth input__14712 2)]
               (clojure.core/case
                input__14712_nth_0__
                (meander.dev.parse.zeta/parse-entries)
                (if
                 (clojure.core/map? input__14712_nth_1__)
                 (clojure.core/let
                  [VAL__15278
                   (.valAt input__14712_nth_1__ :meander.zeta/as)]
                  (clojure.core/let
                   [?pattern VAL__15278]
                   (clojure.core/let
                    [X__15280
                     ((clojure.core/fn
                       [m__8653__auto__]
                       (clojure.core/dissoc
                        m__8653__auto__
                        :meander.zeta/as))
                      input__14712_nth_1__)]
                    (clojure.core/let
                     [?rest X__15280]
                     (clojure.core/letfn
                      [(save__15281 [] (state__16315))
                       (f__16443
                        []
                        (clojure.core/let
                         [?env input__14712_nth_2__]
                         (try
                          [{:tag :as,
                            :pattern
                            (clojure.core/let
                             [CATA_RESULT__10889__auto__
                              (CATA__FN__14795 [?pattern ?env])]
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
                              (CATA__FN__14795 [?rest ?env])]
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
                       (clojure.core/= ?rest input__14712_nth_1__)
                       (save__15281)
                       (f__16443)))))))
                 (state__16315))
                (state__16315)))
              (state__16315)))]
           (state__16406))
          (state__16315)))
        (state__16315
         []
         (clojure.core/letfn
          [(def__15284
            [arg__15317 ?ns]
            (clojure.core/letfn
             [(state__16444
               []
               (clojure.core/let
                [x__15318 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__15318)
                 (clojure.core/let [?env arg__15317] [?env ?ns])
                 (state__16445))))
              (state__16445
               []
               (if
                (clojure.core/map? arg__15317)
                (clojure.core/let
                 [VAL__15319 (.valAt arg__15317 :aliases)]
                 (if
                  (clojure.core/map? VAL__15319)
                  (clojure.core/let
                   [X__15321 (clojure.core/set VAL__15319)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15321))
                    (clojure.core/loop
                     [search_space__16446 (clojure.core/seq X__15321)]
                     (if
                      (clojure.core/seq search_space__16446)
                      (clojure.core/let
                       [elem__15322
                        (clojure.core/first search_space__16446)
                        result__16447
                        (clojure.core/let
                         [elem__15322_nth_0__
                          (clojure.core/nth elem__15322 0)
                          elem__15322_nth_1__
                          (clojure.core/nth elem__15322 1)]
                         (if
                          (clojure.core/symbol? elem__15322_nth_0__)
                          (clojure.core/let
                           [X__15324
                            (clojure.core/name elem__15322_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__15324)
                            (if
                             (clojure.core/symbol? elem__15322_nth_1__)
                             (clojure.core/let
                              [X__15326
                               (clojure.core/name elem__15322_nth_1__)]
                              (clojure.core/case
                               X__15326
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__15317]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16447)
                        (recur (clojure.core/next search_space__16446))
                        result__16447))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16444)))]
          (if
           (clojure.core/vector? input__14712)
           (if
            (clojure.core/= (clojure.core/count input__14712) 3)
            (clojure.core/let
             [input__14712_nth_0__
              (clojure.core/nth input__14712 0)
              input__14712_nth_1__
              (clojure.core/nth input__14712 1)
              input__14712_nth_2__
              (clojure.core/nth input__14712 2)]
             (clojure.core/case
              input__14712_nth_0__
              (meander.dev.parse.zeta/parse-entries)
              (if
               (clojure.core/map? input__14712_nth_1__)
               (clojure.core/let
                [X__15289 (clojure.core/set input__14712_nth_1__)]
                (if
                 (clojure.core/<= 1 (clojure.core/count X__15289))
                 (clojure.core/loop
                  [search_space__16449 (clojure.core/seq X__15289)]
                  (if
                   (clojure.core/seq search_space__16449)
                   (clojure.core/let
                    [elem__15290
                     (clojure.core/first search_space__16449)
                     result__16450
                     (clojure.core/let
                      [elem__15290_nth_0__
                       (clojure.core/nth elem__15290 0)
                       elem__15290_nth_1__
                       (clojure.core/nth elem__15290 1)]
                      (clojure.core/let
                       [*m__14746 elem__15290_nth_0__]
                       (if
                        (clojure.core/symbol? elem__15290_nth_0__)
                        (clojure.core/let
                         [X__15292
                          (clojure.core/namespace elem__15290_nth_0__)]
                         (clojure.core/let
                          [?ns X__15292]
                          (clojure.core/let
                           [X__15294
                            (clojure.core/name elem__15290_nth_0__)]
                           (if
                            (clojure.core/string? X__15294)
                            (if
                             (clojure.core/re-matches #"&.*" X__15294)
                             (clojure.core/let
                              [?pattern elem__15290_nth_1__]
                              (clojure.core/let
                               [X__15296
                                ((clojure.core/fn
                                  [m__8653__auto__]
                                  (clojure.core/dissoc
                                   m__8653__auto__
                                   *m__14746))
                                 input__14712_nth_1__)]
                               (clojure.core/let
                                [?rest X__15296]
                                (clojure.core/let
                                 [x__9586__auto__
                                  (def__15284
                                   input__14712_nth_2__
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
                                        (CATA__FN__14795
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
                                        (CATA__FN__14795
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
                     (meander.runtime.zeta/fail? result__16450)
                     (recur (clojure.core/next search_space__16449))
                     result__16450))
                   (state__16316)))
                 (state__16316)))
               (state__16316))
              (state__16316)))
            (state__16316))
           (state__16316))))
        (state__16316
         []
         (if
          (clojure.core/vector? input__14712)
          (clojure.core/letfn
           [(state__16452
             []
             (if
              (clojure.core/= (clojure.core/count input__14712) 3)
              (clojure.core/let
               [input__14712_nth_0__
                (clojure.core/nth input__14712 0)
                input__14712_nth_1__
                (clojure.core/nth input__14712 1)
                input__14712_nth_2__
                (clojure.core/nth input__14712 2)]
               (clojure.core/case
                input__14712_nth_0__
                (meander.dev.parse.zeta/parse-entries)
                (clojure.core/letfn
                 [(state__16454
                   []
                   (if
                    (clojure.core/map? input__14712_nth_1__)
                    (clojure.core/let
                     [X__15340 (clojure.core/set input__14712_nth_1__)]
                     (if
                      (clojure.core/<= 1 (clojure.core/count X__15340))
                      (clojure.core/loop
                       [search_space__16456
                        (clojure.core/seq X__15340)]
                       (if
                        (clojure.core/seq search_space__16456)
                        (clojure.core/let
                         [elem__15341
                          (clojure.core/first search_space__16456)
                          result__16457
                          (clojure.core/let
                           [elem__15341_nth_0__
                            (clojure.core/nth elem__15341 0)
                            elem__15341_nth_1__
                            (clojure.core/nth elem__15341 1)]
                           (clojure.core/let
                            [?key-pattern elem__15341_nth_0__]
                            (clojure.core/let
                             [?val-pattern elem__15341_nth_1__]
                             (clojure.core/let
                              [X__15343
                               ((clojure.core/fn
                                 [m__8653__auto__]
                                 (clojure.core/dissoc
                                  m__8653__auto__
                                  ?key-pattern))
                                input__14712_nth_1__)]
                              (clojure.core/let
                               [?rest X__15343]
                               (clojure.core/let
                                [?env input__14712_nth_2__]
                                (try
                                 [{:tag :entry,
                                   :key-pattern
                                   (clojure.core/let
                                    [CATA_RESULT__10889__auto__
                                     (CATA__FN__14795
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
                                     (CATA__FN__14795
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
                                     (CATA__FN__14795
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
                          (meander.runtime.zeta/fail? result__16457)
                          (recur
                           (clojure.core/next search_space__16456))
                          result__16457))
                        (state__16455)))
                      (state__16455)))
                    (state__16455)))
                  (state__16455
                   []
                   (if
                    (clojure.core/map? input__14712_nth_1__)
                    (clojure.core/let
                     [?env input__14712_nth_2__]
                     (try
                      [{:tag :some-map}]
                      (catch
                       java.lang.Exception
                       e__11829__auto__
                       (if
                        (meander.runtime.zeta/fail? e__11829__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__11829__auto__)))))
                    (state__16453)))]
                 (state__16454))
                (meander.dev.parse.zeta/parse-with-bindings)
                (clojure.core/letfn
                 [(state__16459
                   []
                   (if
                    (clojure.core/vector? input__14712_nth_1__)
                    (clojure.core/case
                     input__14712_nth_1__
                     ([])
                     (clojure.core/let
                      [?env input__14712_nth_2__]
                      (try
                       [[]]
                       (catch
                        java.lang.Exception
                        e__11829__auto__
                        (if
                         (meander.runtime.zeta/fail? e__11829__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__11829__auto__)))))
                     (state__16460))
                    (state__16460)))
                  (state__16460
                   []
                   (if
                    (clojure.core/vector? input__14712_nth_1__)
                    (if
                     (clojure.core/=
                      (clojure.core/count input__14712_nth_1__)
                      1)
                     (clojure.core/let
                      [?env input__14712_nth_2__]
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
                     (state__16461))
                    (state__16461)))
                  (state__16461
                   []
                   (if
                    (clojure.core/vector? input__14712_nth_1__)
                    (clojure.core/let
                     [input__14712_nth_1___l__
                      (clojure.core/subvec
                       input__14712_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__14712_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__14712_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__14712_nth_1___r__
                        (clojure.core/subvec input__14712_nth_1__ 2)]
                       (clojure.core/let
                        [input__14712_nth_1___l___nth_0__
                         (clojure.core/nth input__14712_nth_1___l__ 0)
                         input__14712_nth_1___l___nth_1__
                         (clojure.core/nth input__14712_nth_1___l__ 1)]
                        (if
                         (clojure.core/symbol?
                          input__14712_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__15357
                           (clojure.core/namespace
                            input__14712_nth_1___l___nth_0__)]
                          (clojure.core/let
                           [X__15359
                            (clojure.core/name
                             input__14712_nth_1___l___nth_0__)]
                           (if
                            (clojure.core/string? X__15359)
                            (if
                             (clojure.core/re-matches #"%.+" X__15359)
                             (clojure.core/let
                              [?symbol
                               input__14712_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?pattern
                                input__14712_nth_1___l___nth_1__]
                               (clojure.core/let
                                [?rest input__14712_nth_1___r__]
                                (clojure.core/let
                                 [?env input__14712_nth_2__]
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
                                         (CATA__FN__14795
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
                                       (CATA__FN__14795
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
                             (state__16462))
                            (state__16462))))
                         (state__16462))))
                      (state__16462)))
                    (state__16462)))
                  (state__16462
                   []
                   (if
                    (clojure.core/vector? input__14712_nth_1__)
                    (clojure.core/let
                     [input__14712_nth_1___l__
                      (clojure.core/subvec
                       input__14712_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__14712_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__14712_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__14712_nth_1___r__
                        (clojure.core/subvec input__14712_nth_1__ 2)]
                       (clojure.core/let
                        [input__14712_nth_1___l___nth_0__
                         (clojure.core/nth input__14712_nth_1___l__ 0)
                         input__14712_nth_1___l___nth_1__
                         (clojure.core/nth input__14712_nth_1___l__ 1)]
                        (clojure.core/let
                         [?x input__14712_nth_1___l___nth_0__]
                         (clojure.core/let
                          [?pattern input__14712_nth_1___l___nth_1__]
                          (clojure.core/let
                           [?rest input__14712_nth_1___r__]
                           (clojure.core/let
                            [?env input__14712_nth_2__]
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
                      (state__16453)))
                    (state__16453)))]
                 (state__16459))
                (state__16453)))
              (state__16453)))
            (state__16453
             []
             (if
              (clojure.core/= (clojure.core/count input__14712) 2)
              (clojure.core/let
               [input__14712_nth_0__
                (clojure.core/nth input__14712 0)
                input__14712_nth_1__
                (clojure.core/nth input__14712 1)]
               (if
                (clojure.core/vector? input__14712_nth_0__)
                (clojure.core/let
                 [?sequence input__14712_nth_0__]
                 (clojure.core/let
                  [?form input__14712_nth_0__]
                  (clojure.core/let
                   [?env input__14712_nth_1__]
                   (try
                    [{:tag :vector,
                      :next
                      (clojure.core/let
                       [CATA_RESULT__10889__auto__
                        (CATA__FN__14795
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
                (state__16317)))
              (state__16317)))]
           (state__16452))
          (state__16317)))
        (state__16317
         []
         (clojure.core/letfn
          [(def__15369
            [arg__15392 ?__14713]
            (clojure.core/letfn
             [(state__16463
               []
               (clojure.core/let
                [x__15393 "clojure.core"]
                (if
                 (clojure.core/= ?__14713 x__15393)
                 [?__14713]
                 (state__16464))))
              (state__16464
               []
               (if
                (clojure.core/map? arg__15392)
                (clojure.core/let
                 [VAL__15394 (.valAt arg__15392 :aliases)]
                 (if
                  (clojure.core/map? VAL__15394)
                  (clojure.core/let
                   [X__15396 (clojure.core/set VAL__15394)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15396))
                    (clojure.core/loop
                     [search_space__16465 (clojure.core/seq X__15396)]
                     (if
                      (clojure.core/seq search_space__16465)
                      (clojure.core/let
                       [elem__15397
                        (clojure.core/first search_space__16465)
                        result__16466
                        (clojure.core/let
                         [elem__15397_nth_0__
                          (clojure.core/nth elem__15397 0)
                          elem__15397_nth_1__
                          (clojure.core/nth elem__15397 1)]
                         (if
                          (clojure.core/symbol? elem__15397_nth_0__)
                          (clojure.core/let
                           [X__15399
                            (clojure.core/name elem__15397_nth_0__)]
                           (if
                            (clojure.core/= ?__14713 X__15399)
                            (if
                             (clojure.core/symbol? elem__15397_nth_1__)
                             (clojure.core/let
                              [X__15401
                               (clojure.core/name elem__15397_nth_1__)]
                              (clojure.core/case
                               X__15401
                               ("clojure.core")
                               [?__14713]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16466)
                        (recur (clojure.core/next search_space__16465))
                        result__16466))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16463)))]
          (if
           (clojure.core/vector? input__14712)
           (if
            (clojure.core/= (clojure.core/count input__14712) 2)
            (clojure.core/let
             [input__14712_nth_0__
              (clojure.core/nth input__14712 0)
              input__14712_nth_1__
              (clojure.core/nth input__14712 1)]
             (if
              (clojure.core/seq? input__14712_nth_0__)
              (clojure.core/let
               [input__14712_nth_0___l__
                (clojure.core/take 1 input__14712_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14712_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14712_nth_0___r__
                  (clojure.core/drop 1 input__14712_nth_0__)]
                 (clojure.core/let
                  [input__14712_nth_0___l___nth_0__
                   (clojure.core/nth input__14712_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14712_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15379
                     (clojure.core/namespace
                      input__14712_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14713 X__15379]
                     (clojure.core/let
                      [X__15381
                       (clojure.core/name
                        input__14712_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15381
                       ("unquote")
                       (clojure.core/let
                        [x__9586__auto__
                         (def__15369 input__14712_nth_1__ ?__14713)]
                        (if
                         (meander.runtime.zeta/fail? x__9586__auto__)
                         (state__16318)
                         (clojure.core/let
                          [[?__14713] x__9586__auto__]
                          (if
                           (clojure.core/vector? input__14712)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14712)
                             2)
                            (clojure.core/let
                             [input__14712_nth_0__
                              (clojure.core/nth input__14712 0)
                              input__14712_nth_1__
                              (clojure.core/nth input__14712 1)]
                             (if
                              (clojure.core/seq? input__14712_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__14712_nth_0__)
                                2)
                               (clojure.core/let
                                [input__14712_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14712_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?x input__14712_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__14712_nth_0__]
                                  (clojure.core/let
                                   [?env input__14712_nth_1__]
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
                               (state__16318))
                              (state__16318)))
                            (state__16318))
                           (state__16318)))))
                       (state__16318)))))
                   (state__16318))))
                (state__16318)))
              (state__16318)))
            (state__16318))
           (state__16318))))
        (state__16318
         []
         (clojure.core/letfn
          [(def__15403
            [arg__15426 ?__14714]
            (clojure.core/letfn
             [(state__16468
               []
               (clojure.core/let
                [x__15427 "meander.zeta"]
                (if
                 (clojure.core/= ?__14714 x__15427)
                 [?__14714]
                 (state__16469))))
              (state__16469
               []
               (if
                (clojure.core/map? arg__15426)
                (clojure.core/let
                 [VAL__15428 (.valAt arg__15426 :aliases)]
                 (if
                  (clojure.core/map? VAL__15428)
                  (clojure.core/let
                   [X__15430 (clojure.core/set VAL__15428)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15430))
                    (clojure.core/loop
                     [search_space__16470 (clojure.core/seq X__15430)]
                     (if
                      (clojure.core/seq search_space__16470)
                      (clojure.core/let
                       [elem__15431
                        (clojure.core/first search_space__16470)
                        result__16471
                        (clojure.core/let
                         [elem__15431_nth_0__
                          (clojure.core/nth elem__15431 0)
                          elem__15431_nth_1__
                          (clojure.core/nth elem__15431 1)]
                         (if
                          (clojure.core/symbol? elem__15431_nth_0__)
                          (clojure.core/let
                           [X__15433
                            (clojure.core/name elem__15431_nth_0__)]
                           (if
                            (clojure.core/= ?__14714 X__15433)
                            (if
                             (clojure.core/symbol? elem__15431_nth_1__)
                             (clojure.core/let
                              [X__15435
                               (clojure.core/name elem__15431_nth_1__)]
                              (clojure.core/case
                               X__15435
                               ("meander.zeta")
                               [?__14714]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16471)
                        (recur (clojure.core/next search_space__16470))
                        result__16471))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16468)))]
          (if
           (clojure.core/vector? input__14712)
           (if
            (clojure.core/= (clojure.core/count input__14712) 2)
            (clojure.core/let
             [input__14712_nth_0__
              (clojure.core/nth input__14712 0)
              input__14712_nth_1__
              (clojure.core/nth input__14712 1)]
             (if
              (clojure.core/seq? input__14712_nth_0__)
              (clojure.core/let
               [input__14712_nth_0___l__
                (clojure.core/take 1 input__14712_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14712_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14712_nth_0___r__
                  (clojure.core/drop 1 input__14712_nth_0__)]
                 (clojure.core/let
                  [input__14712_nth_0___l___nth_0__
                   (clojure.core/nth input__14712_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14712_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15413
                     (clojure.core/namespace
                      input__14712_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14714 X__15413]
                     (clojure.core/let
                      [X__15415
                       (clojure.core/name
                        input__14712_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15415
                       ("*")
                       (clojure.core/let
                        [x__9586__auto__
                         (def__15403 input__14712_nth_1__ ?__14714)]
                        (if
                         (meander.runtime.zeta/fail? x__9586__auto__)
                         (state__16319)
                         (clojure.core/let
                          [[?__14714] x__9586__auto__]
                          (if
                           (clojure.core/vector? input__14712)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14712)
                             2)
                            (clojure.core/let
                             [input__14712_nth_0__
                              (clojure.core/nth input__14712 0)
                              input__14712_nth_1__
                              (clojure.core/nth input__14712 1)]
                             (if
                              (clojure.core/seq? input__14712_nth_0__)
                              (clojure.core/let
                               [input__14712_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__14712_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__14712_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__14712_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__14712_nth_0__)]
                                 (clojure.core/let
                                  [?patterns input__14712_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__14712_nth_0__]
                                   (clojure.core/let
                                    [?env input__14712_nth_1__]
                                    (try
                                     [{:tag :star,
                                       :greedy? true,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__10889__auto__
                                         (CATA__FN__14795
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
                                (state__16319)))
                              (state__16319)))
                            (state__16319))
                           (state__16319)))))
                       (state__16319)))))
                   (state__16319))))
                (state__16319)))
              (state__16319)))
            (state__16319))
           (state__16319))))
        (state__16319
         []
         (clojure.core/letfn
          [(def__15437
            [arg__15460 ?__14715]
            (clojure.core/letfn
             [(state__16473
               []
               (clojure.core/let
                [x__15461 "meander.zeta"]
                (if
                 (clojure.core/= ?__14715 x__15461)
                 [?__14715]
                 (state__16474))))
              (state__16474
               []
               (if
                (clojure.core/map? arg__15460)
                (clojure.core/let
                 [VAL__15462 (.valAt arg__15460 :aliases)]
                 (if
                  (clojure.core/map? VAL__15462)
                  (clojure.core/let
                   [X__15464 (clojure.core/set VAL__15462)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15464))
                    (clojure.core/loop
                     [search_space__16475 (clojure.core/seq X__15464)]
                     (if
                      (clojure.core/seq search_space__16475)
                      (clojure.core/let
                       [elem__15465
                        (clojure.core/first search_space__16475)
                        result__16476
                        (clojure.core/let
                         [elem__15465_nth_0__
                          (clojure.core/nth elem__15465 0)
                          elem__15465_nth_1__
                          (clojure.core/nth elem__15465 1)]
                         (if
                          (clojure.core/symbol? elem__15465_nth_0__)
                          (clojure.core/let
                           [X__15467
                            (clojure.core/name elem__15465_nth_0__)]
                           (if
                            (clojure.core/= ?__14715 X__15467)
                            (if
                             (clojure.core/symbol? elem__15465_nth_1__)
                             (clojure.core/let
                              [X__15469
                               (clojure.core/name elem__15465_nth_1__)]
                              (clojure.core/case
                               X__15469
                               ("meander.zeta")
                               [?__14715]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16476)
                        (recur (clojure.core/next search_space__16475))
                        result__16476))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16473)))]
          (if
           (clojure.core/vector? input__14712)
           (if
            (clojure.core/= (clojure.core/count input__14712) 2)
            (clojure.core/let
             [input__14712_nth_0__
              (clojure.core/nth input__14712 0)
              input__14712_nth_1__
              (clojure.core/nth input__14712 1)]
             (if
              (clojure.core/seq? input__14712_nth_0__)
              (clojure.core/let
               [input__14712_nth_0___l__
                (clojure.core/take 1 input__14712_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14712_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14712_nth_0___r__
                  (clojure.core/drop 1 input__14712_nth_0__)]
                 (clojure.core/let
                  [input__14712_nth_0___l___nth_0__
                   (clojure.core/nth input__14712_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14712_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15447
                     (clojure.core/namespace
                      input__14712_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14715 X__15447]
                     (clojure.core/let
                      [X__15449
                       (clojure.core/name
                        input__14712_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15449
                       ("<>")
                       (clojure.core/let
                        [x__9586__auto__
                         (def__15437 input__14712_nth_1__ ?__14715)]
                        (if
                         (meander.runtime.zeta/fail? x__9586__auto__)
                         (state__16320)
                         (clojure.core/let
                          [[?__14715] x__9586__auto__]
                          (if
                           (clojure.core/vector? input__14712)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14712)
                             2)
                            (clojure.core/let
                             [input__14712_nth_0__
                              (clojure.core/nth input__14712 0)
                              input__14712_nth_1__
                              (clojure.core/nth input__14712 1)]
                             (if
                              (clojure.core/seq? input__14712_nth_0__)
                              (clojure.core/let
                               [input__14712_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__14712_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__14712_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__14712_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__14712_nth_0__)]
                                 (clojure.core/let
                                  [?patterns input__14712_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__14712_nth_0__]
                                   (clojure.core/let
                                    [?env input__14712_nth_1__]
                                    (try
                                     [{:tag :group,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__10889__auto__
                                         (CATA__FN__14795
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
                                (state__16320)))
                              (state__16320)))
                            (state__16320))
                           (state__16320)))))
                       (state__16320)))))
                   (state__16320))))
                (state__16320)))
              (state__16320)))
            (state__16320))
           (state__16320))))
        (state__16320
         []
         (clojure.core/letfn
          [(def__15471
            [arg__15494 ?__14716]
            (clojure.core/letfn
             [(state__16478
               []
               (clojure.core/let
                [x__15495 "meander.math.zeta"]
                (if
                 (clojure.core/= ?__14716 x__15495)
                 [?__14716]
                 (state__16479))))
              (state__16479
               []
               (if
                (clojure.core/map? arg__15494)
                (clojure.core/let
                 [VAL__15496 (.valAt arg__15494 :aliases)]
                 (if
                  (clojure.core/map? VAL__15496)
                  (clojure.core/let
                   [X__15498 (clojure.core/set VAL__15496)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15498))
                    (clojure.core/loop
                     [search_space__16480 (clojure.core/seq X__15498)]
                     (if
                      (clojure.core/seq search_space__16480)
                      (clojure.core/let
                       [elem__15499
                        (clojure.core/first search_space__16480)
                        result__16481
                        (clojure.core/let
                         [elem__15499_nth_0__
                          (clojure.core/nth elem__15499 0)
                          elem__15499_nth_1__
                          (clojure.core/nth elem__15499 1)]
                         (if
                          (clojure.core/symbol? elem__15499_nth_0__)
                          (clojure.core/let
                           [X__15501
                            (clojure.core/name elem__15499_nth_0__)]
                           (if
                            (clojure.core/= ?__14716 X__15501)
                            (if
                             (clojure.core/symbol? elem__15499_nth_1__)
                             (clojure.core/let
                              [X__15503
                               (clojure.core/name elem__15499_nth_1__)]
                              (clojure.core/case
                               X__15503
                               ("meander.math.zeta")
                               [?__14716]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16481)
                        (recur (clojure.core/next search_space__16480))
                        result__16481))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16478)))]
          (if
           (clojure.core/vector? input__14712)
           (if
            (clojure.core/= (clojure.core/count input__14712) 2)
            (clojure.core/let
             [input__14712_nth_0__
              (clojure.core/nth input__14712 0)
              input__14712_nth_1__
              (clojure.core/nth input__14712 1)]
             (if
              (clojure.core/seq? input__14712_nth_0__)
              (clojure.core/let
               [input__14712_nth_0___l__
                (clojure.core/take 1 input__14712_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14712_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14712_nth_0___r__
                  (clojure.core/drop 1 input__14712_nth_0__)]
                 (clojure.core/let
                  [input__14712_nth_0___l___nth_0__
                   (clojure.core/nth input__14712_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14712_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15481
                     (clojure.core/namespace
                      input__14712_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14716 X__15481]
                     (clojure.core/let
                      [X__15483
                       (clojure.core/name
                        input__14712_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15483
                       ("+")
                       (clojure.core/let
                        [x__9586__auto__
                         (def__15471 input__14712_nth_1__ ?__14716)]
                        (if
                         (meander.runtime.zeta/fail? x__9586__auto__)
                         (state__16321)
                         (clojure.core/let
                          [[?__14716] x__9586__auto__]
                          (if
                           (clojure.core/vector? input__14712)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14712)
                             2)
                            (clojure.core/let
                             [input__14712_nth_0__
                              (clojure.core/nth input__14712 0)
                              input__14712_nth_1__
                              (clojure.core/nth input__14712 1)]
                             (if
                              (clojure.core/seq? input__14712_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__14712_nth_0__)
                                3)
                               (clojure.core/let
                                [input__14712_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14712_nth_0__
                                  1)
                                 input__14712_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__14712_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?a input__14712_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?b input__14712_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__14712_nth_0__]
                                   (clojure.core/let
                                    [?env input__14712_nth_1__]
                                    (try
                                     [{:tag :meander.math.zeta/+,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__10889__auto__
                                         (CATA__FN__14795 [?a ?env])]
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
                                         (CATA__FN__14795 [?b ?env])]
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
                               (state__16321))
                              (state__16321)))
                            (state__16321))
                           (state__16321)))))
                       (state__16321)))))
                   (state__16321))))
                (state__16321)))
              (state__16321)))
            (state__16321))
           (state__16321))))
        (state__16321
         []
         (clojure.core/letfn
          [(def__15505
            [arg__15528 ?__14717]
            (clojure.core/letfn
             [(state__16483
               []
               (clojure.core/let
                [x__15529 "meander.math.zeta"]
                (if
                 (clojure.core/= ?__14717 x__15529)
                 [?__14717]
                 (state__16484))))
              (state__16484
               []
               (if
                (clojure.core/map? arg__15528)
                (clojure.core/let
                 [VAL__15530 (.valAt arg__15528 :aliases)]
                 (if
                  (clojure.core/map? VAL__15530)
                  (clojure.core/let
                   [X__15532 (clojure.core/set VAL__15530)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15532))
                    (clojure.core/loop
                     [search_space__16485 (clojure.core/seq X__15532)]
                     (if
                      (clojure.core/seq search_space__16485)
                      (clojure.core/let
                       [elem__15533
                        (clojure.core/first search_space__16485)
                        result__16486
                        (clojure.core/let
                         [elem__15533_nth_0__
                          (clojure.core/nth elem__15533 0)
                          elem__15533_nth_1__
                          (clojure.core/nth elem__15533 1)]
                         (if
                          (clojure.core/symbol? elem__15533_nth_0__)
                          (clojure.core/let
                           [X__15535
                            (clojure.core/name elem__15533_nth_0__)]
                           (if
                            (clojure.core/= ?__14717 X__15535)
                            (if
                             (clojure.core/symbol? elem__15533_nth_1__)
                             (clojure.core/let
                              [X__15537
                               (clojure.core/name elem__15533_nth_1__)]
                              (clojure.core/case
                               X__15537
                               ("meander.math.zeta")
                               [?__14717]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16486)
                        (recur (clojure.core/next search_space__16485))
                        result__16486))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16483)))]
          (if
           (clojure.core/vector? input__14712)
           (if
            (clojure.core/= (clojure.core/count input__14712) 2)
            (clojure.core/let
             [input__14712_nth_0__
              (clojure.core/nth input__14712 0)
              input__14712_nth_1__
              (clojure.core/nth input__14712 1)]
             (if
              (clojure.core/seq? input__14712_nth_0__)
              (clojure.core/let
               [input__14712_nth_0___l__
                (clojure.core/take 1 input__14712_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14712_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14712_nth_0___r__
                  (clojure.core/drop 1 input__14712_nth_0__)]
                 (clojure.core/let
                  [input__14712_nth_0___l___nth_0__
                   (clojure.core/nth input__14712_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14712_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15515
                     (clojure.core/namespace
                      input__14712_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14717 X__15515]
                     (clojure.core/let
                      [X__15517
                       (clojure.core/name
                        input__14712_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15517
                       ("-")
                       (clojure.core/let
                        [x__9586__auto__
                         (def__15505 input__14712_nth_1__ ?__14717)]
                        (if
                         (meander.runtime.zeta/fail? x__9586__auto__)
                         (state__16322)
                         (clojure.core/let
                          [[?__14717] x__9586__auto__]
                          (if
                           (clojure.core/vector? input__14712)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14712)
                             2)
                            (clojure.core/let
                             [input__14712_nth_0__
                              (clojure.core/nth input__14712 0)
                              input__14712_nth_1__
                              (clojure.core/nth input__14712 1)]
                             (if
                              (clojure.core/seq? input__14712_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__14712_nth_0__)
                                3)
                               (clojure.core/let
                                [input__14712_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14712_nth_0__
                                  1)
                                 input__14712_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__14712_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?a input__14712_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?b input__14712_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__14712_nth_0__]
                                   (clojure.core/let
                                    [?env input__14712_nth_1__]
                                    (try
                                     [{:tag :meander.math.zeta/-,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__10889__auto__
                                         (CATA__FN__14795 [?a ?env])]
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
                                         (CATA__FN__14795 [?b ?env])]
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
                               (state__16322))
                              (state__16322)))
                            (state__16322))
                           (state__16322)))))
                       (state__16322)))))
                   (state__16322))))
                (state__16322)))
              (state__16322)))
            (state__16322))
           (state__16322))))
        (state__16322
         []
         (clojure.core/letfn
          [(def__15539
            [arg__15562 ?__14718]
            (clojure.core/letfn
             [(state__16488
               []
               (clojure.core/let
                [x__15563 "meander.zeta"]
                (if
                 (clojure.core/= ?__14718 x__15563)
                 [?__14718]
                 (state__16489))))
              (state__16489
               []
               (if
                (clojure.core/map? arg__15562)
                (clojure.core/let
                 [VAL__15564 (.valAt arg__15562 :aliases)]
                 (if
                  (clojure.core/map? VAL__15564)
                  (clojure.core/let
                   [X__15566 (clojure.core/set VAL__15564)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15566))
                    (clojure.core/loop
                     [search_space__16490 (clojure.core/seq X__15566)]
                     (if
                      (clojure.core/seq search_space__16490)
                      (clojure.core/let
                       [elem__15567
                        (clojure.core/first search_space__16490)
                        result__16491
                        (clojure.core/let
                         [elem__15567_nth_0__
                          (clojure.core/nth elem__15567 0)
                          elem__15567_nth_1__
                          (clojure.core/nth elem__15567 1)]
                         (if
                          (clojure.core/symbol? elem__15567_nth_0__)
                          (clojure.core/let
                           [X__15569
                            (clojure.core/name elem__15567_nth_0__)]
                           (if
                            (clojure.core/= ?__14718 X__15569)
                            (if
                             (clojure.core/symbol? elem__15567_nth_1__)
                             (clojure.core/let
                              [X__15571
                               (clojure.core/name elem__15567_nth_1__)]
                              (clojure.core/case
                               X__15571
                               ("meander.zeta")
                               [?__14718]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16491)
                        (recur (clojure.core/next search_space__16490))
                        result__16491))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16488)))]
          (if
           (clojure.core/vector? input__14712)
           (if
            (clojure.core/= (clojure.core/count input__14712) 2)
            (clojure.core/let
             [input__14712_nth_0__
              (clojure.core/nth input__14712 0)
              input__14712_nth_1__
              (clojure.core/nth input__14712 1)]
             (if
              (clojure.core/seq? input__14712_nth_0__)
              (clojure.core/let
               [input__14712_nth_0___l__
                (clojure.core/take 1 input__14712_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14712_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14712_nth_0___r__
                  (clojure.core/drop 1 input__14712_nth_0__)]
                 (clojure.core/let
                  [input__14712_nth_0___l___nth_0__
                   (clojure.core/nth input__14712_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14712_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15549
                     (clojure.core/namespace
                      input__14712_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14718 X__15549]
                     (clojure.core/let
                      [X__15551
                       (clojure.core/name
                        input__14712_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15551
                       ("with")
                       (clojure.core/let
                        [x__9586__auto__
                         (def__15539 input__14712_nth_1__ ?__14718)]
                        (if
                         (meander.runtime.zeta/fail? x__9586__auto__)
                         (state__16323)
                         (clojure.core/let
                          [[?__14718] x__9586__auto__]
                          (if
                           (clojure.core/vector? input__14712)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14712)
                             2)
                            (clojure.core/let
                             [input__14712_nth_0__
                              (clojure.core/nth input__14712 0)
                              input__14712_nth_1__
                              (clojure.core/nth input__14712 1)]
                             (if
                              (clojure.core/seq? input__14712_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__14712_nth_0__)
                                3)
                               (clojure.core/let
                                [input__14712_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14712_nth_0__
                                  1)
                                 input__14712_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__14712_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?bindings
                                  input__14712_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?body input__14712_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__14712_nth_0__]
                                   (clojure.core/let
                                    [?env input__14712_nth_1__]
                                    (try
                                     [{:tag :with,
                                       :bindings
                                       {:tag :with-bindings,
                                        :bindings
                                        (clojure.core/let
                                         [CATA_RESULT__10889__auto__
                                          (CATA__FN__14795
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
                                         (CATA__FN__14795
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
                               (state__16323))
                              (state__16323)))
                            (state__16323))
                           (state__16323)))))
                       (state__16323)))))
                   (state__16323))))
                (state__16323)))
              (state__16323)))
            (state__16323))
           (state__16323))))
        (state__16323
         []
         (clojure.core/letfn
          [(def__15573
            [arg__15596 ?__14719]
            (clojure.core/letfn
             [(state__16493
               []
               (clojure.core/let
                [x__15597 "meander.zeta"]
                (if
                 (clojure.core/= ?__14719 x__15597)
                 [?__14719]
                 (state__16494))))
              (state__16494
               []
               (if
                (clojure.core/map? arg__15596)
                (clojure.core/let
                 [VAL__15598 (.valAt arg__15596 :aliases)]
                 (if
                  (clojure.core/map? VAL__15598)
                  (clojure.core/let
                   [X__15600 (clojure.core/set VAL__15598)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15600))
                    (clojure.core/loop
                     [search_space__16495 (clojure.core/seq X__15600)]
                     (if
                      (clojure.core/seq search_space__16495)
                      (clojure.core/let
                       [elem__15601
                        (clojure.core/first search_space__16495)
                        result__16496
                        (clojure.core/let
                         [elem__15601_nth_0__
                          (clojure.core/nth elem__15601 0)
                          elem__15601_nth_1__
                          (clojure.core/nth elem__15601 1)]
                         (if
                          (clojure.core/symbol? elem__15601_nth_0__)
                          (clojure.core/let
                           [X__15603
                            (clojure.core/name elem__15601_nth_0__)]
                           (if
                            (clojure.core/= ?__14719 X__15603)
                            (if
                             (clojure.core/symbol? elem__15601_nth_1__)
                             (clojure.core/let
                              [X__15605
                               (clojure.core/name elem__15601_nth_1__)]
                              (clojure.core/case
                               X__15605
                               ("meander.zeta")
                               [?__14719]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16496)
                        (recur (clojure.core/next search_space__16495))
                        result__16496))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16493)))]
          (if
           (clojure.core/vector? input__14712)
           (if
            (clojure.core/= (clojure.core/count input__14712) 2)
            (clojure.core/let
             [input__14712_nth_0__
              (clojure.core/nth input__14712 0)
              input__14712_nth_1__
              (clojure.core/nth input__14712 1)]
             (if
              (clojure.core/seq? input__14712_nth_0__)
              (clojure.core/let
               [input__14712_nth_0___l__
                (clojure.core/take 1 input__14712_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14712_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14712_nth_0___r__
                  (clojure.core/drop 1 input__14712_nth_0__)]
                 (clojure.core/let
                  [input__14712_nth_0___l___nth_0__
                   (clojure.core/nth input__14712_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14712_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15583
                     (clojure.core/namespace
                      input__14712_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14719 X__15583]
                     (clojure.core/let
                      [X__15585
                       (clojure.core/name
                        input__14712_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15585
                       ("apply")
                       (clojure.core/let
                        [x__9586__auto__
                         (def__15573 input__14712_nth_1__ ?__14719)]
                        (if
                         (meander.runtime.zeta/fail? x__9586__auto__)
                         (state__16324)
                         (clojure.core/let
                          [[?__14719] x__9586__auto__]
                          (if
                           (clojure.core/vector? input__14712)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14712)
                             2)
                            (clojure.core/let
                             [input__14712_nth_0__
                              (clojure.core/nth input__14712 0)
                              input__14712_nth_1__
                              (clojure.core/nth input__14712 1)]
                             (if
                              (clojure.core/seq? input__14712_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__14712_nth_0__)
                                3)
                               (clojure.core/let
                                [input__14712_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14712_nth_0__
                                  1)
                                 input__14712_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__14712_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?fn input__14712_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?pattern
                                   input__14712_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__14712_nth_0__]
                                   (clojure.core/let
                                    [?env input__14712_nth_1__]
                                    (try
                                     [{:tag :apply,
                                       :fn ?fn,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__10889__auto__
                                         (CATA__FN__14795
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
                               (state__16324))
                              (state__16324)))
                            (state__16324))
                           (state__16324)))))
                       (state__16324)))))
                   (state__16324))))
                (state__16324)))
              (state__16324)))
            (state__16324))
           (state__16324))))
        (state__16324
         []
         (clojure.core/letfn
          [(def__15607
            [arg__15632 ?__14720]
            (clojure.core/letfn
             [(state__16498
               []
               (clojure.core/let
                [x__15633 "meander.zeta"]
                (if
                 (clojure.core/= ?__14720 x__15633)
                 [?__14720]
                 (state__16499))))
              (state__16499
               []
               (if
                (clojure.core/map? arg__15632)
                (clojure.core/let
                 [VAL__15634 (.valAt arg__15632 :aliases)]
                 (if
                  (clojure.core/map? VAL__15634)
                  (clojure.core/let
                   [X__15636 (clojure.core/set VAL__15634)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15636))
                    (clojure.core/loop
                     [search_space__16500 (clojure.core/seq X__15636)]
                     (if
                      (clojure.core/seq search_space__16500)
                      (clojure.core/let
                       [elem__15637
                        (clojure.core/first search_space__16500)
                        result__16501
                        (clojure.core/let
                         [elem__15637_nth_0__
                          (clojure.core/nth elem__15637 0)
                          elem__15637_nth_1__
                          (clojure.core/nth elem__15637 1)]
                         (if
                          (clojure.core/symbol? elem__15637_nth_0__)
                          (clojure.core/let
                           [X__15639
                            (clojure.core/name elem__15637_nth_0__)]
                           (if
                            (clojure.core/= ?__14720 X__15639)
                            (if
                             (clojure.core/symbol? elem__15637_nth_1__)
                             (clojure.core/let
                              [X__15641
                               (clojure.core/name elem__15637_nth_1__)]
                              (clojure.core/case
                               X__15641
                               ("meander.zeta")
                               [?__14720]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16501)
                        (recur (clojure.core/next search_space__16500))
                        result__16501))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16498)))]
          (if
           (clojure.core/vector? input__14712)
           (if
            (clojure.core/= (clojure.core/count input__14712) 2)
            (clojure.core/let
             [input__14712_nth_0__
              (clojure.core/nth input__14712 0)
              input__14712_nth_1__
              (clojure.core/nth input__14712 1)]
             (if
              (clojure.core/seq? input__14712_nth_0__)
              (clojure.core/let
               [input__14712_nth_0___l__
                (clojure.core/take 1 input__14712_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14712_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14712_nth_0___r__
                  (clojure.core/drop 1 input__14712_nth_0__)]
                 (clojure.core/let
                  [input__14712_nth_0___l___nth_0__
                   (clojure.core/nth input__14712_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14712_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15619
                     (clojure.core/namespace
                      input__14712_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14720 X__15619]
                     (clojure.core/let
                      [X__15621
                       (clojure.core/name
                        input__14712_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15621
                       ("and")
                       (clojure.core/let
                        [x__9586__auto__
                         (def__15607 input__14712_nth_1__ ?__14720)]
                        (if
                         (meander.runtime.zeta/fail? x__9586__auto__)
                         (state__16325)
                         (clojure.core/let
                          [[?__14720] x__9586__auto__]
                          (if
                           (clojure.core/vector? input__14712)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14712)
                             2)
                            (clojure.core/let
                             [input__14712_nth_0__
                              (clojure.core/nth input__14712 0)
                              input__14712_nth_1__
                              (clojure.core/nth input__14712 1)]
                             (if
                              (clojure.core/seq? input__14712_nth_0__)
                              (clojure.core/let
                               [input__14712_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__14712_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__14712_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__14712_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__14712_nth_0__)]
                                 (clojure.core/let
                                  [!forms
                                   (clojure.core/vec
                                    input__14712_nth_0___r__)]
                                  (clojure.core/let
                                   [?form input__14712_nth_0__]
                                   (clojure.core/let
                                    [?env input__14712_nth_1__]
                                    (try
                                     [(clojure.core/let
                                       [!forms__counter
                                        (meander.runtime.zeta/iterator
                                         !forms)]
                                       (clojure.core/let
                                        [CATA_RESULT__10889__auto__
                                         (CATA__FN__14795
                                          ['meander.dev.parse.zeta/make-and
                                           (clojure.core/into
                                            []
                                            (clojure.core/loop
                                             [return__14797
                                              (clojure.core/transient
                                               [])]
                                             (if
                                              (clojure.core/and
                                               (.hasNext
                                                !forms__counter))
                                              (recur
                                               (clojure.core/conj!
                                                return__14797
                                                (clojure.core/let
                                                 [CATA_RESULT__10889__auto__
                                                  (CATA__FN__14795
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
                                               return__14797))))
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
                                (state__16325)))
                              (state__16325)))
                            (state__16325))
                           (state__16325)))))
                       (state__16325)))))
                   (state__16325))))
                (state__16325)))
              (state__16325)))
            (state__16325))
           (state__16325))))
        (state__16325
         []
         (if
          (clojure.core/vector? input__14712)
          (if
           (clojure.core/= (clojure.core/count input__14712) 3)
           (clojure.core/let
            [input__14712_nth_0__
             (clojure.core/nth input__14712 0)
             input__14712_nth_1__
             (clojure.core/nth input__14712 1)
             input__14712_nth_2__
             (clojure.core/nth input__14712 2)]
            (clojure.core/case
             input__14712_nth_0__
             (meander.dev.parse.zeta/make-and)
             (clojure.core/letfn
              [(state__16503
                []
                (if
                 (clojure.core/vector? input__14712_nth_1__)
                 (clojure.core/case
                  input__14712_nth_1__
                  ([])
                  (clojure.core/let
                   [?form input__14712_nth_2__]
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
                  (state__16504))
                 (state__16504)))
               (state__16504
                []
                (clojure.core/case
                 input__14712_nth_2__
                 (nil)
                 (if
                  (clojure.core/vector? input__14712_nth_1__)
                  (if
                   (clojure.core/=
                    (clojure.core/count input__14712_nth_1__)
                    1)
                   (clojure.core/let
                    [input__14712_nth_1___nth_0__
                     (clojure.core/nth input__14712_nth_1__ 0)]
                    (clojure.core/let
                     [?ast-a input__14712_nth_1___nth_0__]
                     (try
                      [?ast-a]
                      (catch
                       java.lang.Exception
                       e__11829__auto__
                       (if
                        (meander.runtime.zeta/fail? e__11829__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__11829__auto__))))))
                   (state__16505))
                  (state__16505))
                 (state__16505)))
               (state__16505
                []
                (if
                 (clojure.core/vector? input__14712_nth_1__)
                 (clojure.core/letfn
                  [(state__16506
                    []
                    (if
                     (clojure.core/=
                      (clojure.core/count input__14712_nth_1__)
                      1)
                     (clojure.core/let
                      [input__14712_nth_1___nth_0__
                       (clojure.core/nth input__14712_nth_1__ 0)]
                      (clojure.core/let
                       [?ast-a input__14712_nth_1___nth_0__]
                       (clojure.core/let
                        [?form input__14712_nth_2__]
                        (try
                         [(clojure.core/let
                           [CATA_RESULT__10889__auto__
                            (CATA__FN__14795
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
                     (state__16507)))
                   (state__16507
                    []
                    (if
                     (clojure.core/=
                      (clojure.core/count input__14712_nth_1__)
                      2)
                     (clojure.core/let
                      [input__14712_nth_1___nth_0__
                       (clojure.core/nth input__14712_nth_1__ 0)
                       input__14712_nth_1___nth_1__
                       (clojure.core/nth input__14712_nth_1__ 1)]
                      (clojure.core/let
                       [?ast-a input__14712_nth_1___nth_0__]
                       (clojure.core/let
                        [?ast-b input__14712_nth_1___nth_1__]
                        (clojure.core/let
                         [?form input__14712_nth_2__]
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
                     (state__16508)))
                   (state__16508
                    []
                    (clojure.core/loop
                     [search_space__16509
                      (meander.runtime.zeta/epsilon-partitions
                       2
                       input__14712_nth_1__)]
                     (if
                      (clojure.core/seq search_space__16509)
                      (clojure.core/let
                       [input__14712_nth_1___parts__
                        (clojure.core/first search_space__16509)
                        result__16510
                        (clojure.core/let
                         [input__14712_nth_1___l__
                          (clojure.core/nth
                           input__14712_nth_1___parts__
                           0)
                          input__14712_nth_1___r__
                          (clojure.core/nth
                           input__14712_nth_1___parts__
                           1)]
                         (clojure.core/letfn
                          [(state__16512
                            []
                            (clojure.core/let
                             [!asts-1 []]
                             (clojure.core/let
                              [ret__9750__auto__
                               (meander.runtime.zeta/epsilon-run-star-1
                                input__14712_nth_1___l__
                                [!asts-1]
                                (clojure.core/fn
                                 [[!asts-1] input__15668]
                                 (clojure.core/let
                                  [input__15668_nth_0__
                                   (clojure.core/nth input__15668 0)]
                                  (clojure.core/let
                                   [!asts-1
                                    (clojure.core/conj
                                     !asts-1
                                     input__15668_nth_0__)]
                                   [!asts-1])))
                                (clojure.core/fn
                                 [[!asts-1]]
                                 (clojure.core/let
                                  [X__15661
                                   (clojure.core/count
                                    input__14712_nth_1___l__)]
                                  (clojure.core/let
                                   [?n X__15661]
                                   (clojure.core/let
                                    [X__15665
                                     (clojure.core/count
                                      input__14712_nth_1___r__)]
                                    (if
                                     (clojure.core/= ?n X__15665)
                                     (clojure.core/let
                                      [!asts-2 []]
                                      (clojure.core/let
                                       [ret__9750__auto__
                                        (meander.runtime.zeta/epsilon-run-star-1
                                         input__14712_nth_1___r__
                                         [!asts-2 !asts-1]
                                         (clojure.core/fn
                                          [[!asts-2 !asts-1]
                                           input__15666]
                                          (clojure.core/let
                                           [input__15666_nth_0__
                                            (clojure.core/nth
                                             input__15666
                                             0)]
                                           (clojure.core/let
                                            [!asts-2
                                             (clojure.core/conj
                                              !asts-2
                                              input__15666_nth_0__)]
                                            [!asts-2 !asts-1])))
                                         (clojure.core/fn
                                          [[!asts-2 !asts-1]]
                                          (clojure.core/let
                                           [?form input__14712_nth_2__]
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
                                                (CATA__FN__14795
                                                 ['meander.dev.parse.zeta/make-and
                                                  [(clojure.core/let
                                                    [CATA_RESULT__10889__auto__
                                                     (CATA__FN__14795
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
                                                     (CATA__FN__14795
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
                                        (state__16513)
                                        ret__9750__auto__)))
                                     (state__16513)))))))]
                              (if
                               (meander.runtime.zeta/fail?
                                ret__9750__auto__)
                               (state__16513)
                               ret__9750__auto__))))
                           (state__16513
                            []
                            (clojure.core/let
                             [!asts-1 []]
                             (clojure.core/let
                              [ret__9750__auto__
                               (meander.runtime.zeta/epsilon-run-star-1
                                input__14712_nth_1___l__
                                [!asts-1]
                                (clojure.core/fn
                                 [[!asts-1] input__15684]
                                 (clojure.core/let
                                  [input__15684_nth_0__
                                   (clojure.core/nth input__15684 0)]
                                  (clojure.core/let
                                   [!asts-1
                                    (clojure.core/conj
                                     !asts-1
                                     input__15684_nth_0__)]
                                   [!asts-1])))
                                (clojure.core/fn
                                 [[!asts-1]]
                                 (clojure.core/let
                                  [X__15675
                                   (clojure.core/count
                                    input__14712_nth_1___l__)]
                                  (clojure.core/let
                                   [?n X__15675]
                                   (clojure.core/let
                                    [input__14712_nth_1___r___l__
                                     (clojure.core/subvec
                                      input__14712_nth_1___r__
                                      0
                                      (clojure.core/min
                                       (clojure.core/count
                                        input__14712_nth_1___r__)
                                       1))]
                                    (if
                                     (clojure.core/=
                                      (clojure.core/count
                                       input__14712_nth_1___r___l__)
                                      1)
                                     (clojure.core/let
                                      [input__14712_nth_1___r___r__
                                       (clojure.core/subvec
                                        input__14712_nth_1___r__
                                        1)]
                                      (clojure.core/let
                                       [input__14712_nth_1___r___l___nth_0__
                                        (clojure.core/nth
                                         input__14712_nth_1___r___l__
                                         0)]
                                       (clojure.core/let
                                        [?ast
                                         input__14712_nth_1___r___l___nth_0__]
                                        (clojure.core/let
                                         [X__15681
                                          (clojure.core/count
                                           input__14712_nth_1___r___r__)]
                                         (if
                                          (clojure.core/= ?n X__15681)
                                          (clojure.core/let
                                           [!asts-2 []]
                                           (clojure.core/let
                                            [ret__9750__auto__
                                             (meander.runtime.zeta/epsilon-run-star-1
                                              input__14712_nth_1___r___r__
                                              [!asts-2 !asts-1]
                                              (clojure.core/fn
                                               [[!asts-2 !asts-1]
                                                input__15682]
                                               (clojure.core/let
                                                [input__15682_nth_0__
                                                 (clojure.core/nth
                                                  input__15682
                                                  0)]
                                                (clojure.core/let
                                                 [!asts-2
                                                  (clojure.core/conj
                                                   !asts-2
                                                   input__15682_nth_0__)]
                                                 [!asts-2 !asts-1])))
                                              (clojure.core/fn
                                               [[!asts-2 !asts-1]]
                                               (clojure.core/let
                                                [?form
                                                 input__14712_nth_2__]
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
                                                     (CATA__FN__14795
                                                      ['meander.dev.parse.zeta/make-and
                                                       [(clojure.core/let
                                                         [CATA_RESULT__10889__auto__
                                                          (CATA__FN__14795
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
                                                          (CATA__FN__14795
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
                          (state__16512)))]
                       (if
                        (meander.runtime.zeta/fail? result__16510)
                        (recur (clojure.core/next search_space__16509))
                        result__16510))
                      (state__16326))))]
                  (state__16506))
                 (state__16326)))]
              (state__16503))
             (state__16326)))
           (state__16326))
          (state__16326)))
        (state__16326
         []
         (clojure.core/letfn
          [(def__15687
            [arg__15710 ?__14721]
            (clojure.core/letfn
             [(state__16518
               []
               (clojure.core/let
                [x__15711 "meander.zeta"]
                (if
                 (clojure.core/= ?__14721 x__15711)
                 [?__14721]
                 (state__16519))))
              (state__16519
               []
               (if
                (clojure.core/map? arg__15710)
                (clojure.core/let
                 [VAL__15712 (.valAt arg__15710 :aliases)]
                 (if
                  (clojure.core/map? VAL__15712)
                  (clojure.core/let
                   [X__15714 (clojure.core/set VAL__15712)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15714))
                    (clojure.core/loop
                     [search_space__16520 (clojure.core/seq X__15714)]
                     (if
                      (clojure.core/seq search_space__16520)
                      (clojure.core/let
                       [elem__15715
                        (clojure.core/first search_space__16520)
                        result__16521
                        (clojure.core/let
                         [elem__15715_nth_0__
                          (clojure.core/nth elem__15715 0)
                          elem__15715_nth_1__
                          (clojure.core/nth elem__15715 1)]
                         (if
                          (clojure.core/symbol? elem__15715_nth_0__)
                          (clojure.core/let
                           [X__15717
                            (clojure.core/name elem__15715_nth_0__)]
                           (if
                            (clojure.core/= ?__14721 X__15717)
                            (if
                             (clojure.core/symbol? elem__15715_nth_1__)
                             (clojure.core/let
                              [X__15719
                               (clojure.core/name elem__15715_nth_1__)]
                              (clojure.core/case
                               X__15719
                               ("meander.zeta")
                               [?__14721]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16521)
                        (recur (clojure.core/next search_space__16520))
                        result__16521))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16518)))]
          (if
           (clojure.core/vector? input__14712)
           (if
            (clojure.core/= (clojure.core/count input__14712) 2)
            (clojure.core/let
             [input__14712_nth_0__
              (clojure.core/nth input__14712 0)
              input__14712_nth_1__
              (clojure.core/nth input__14712 1)]
             (if
              (clojure.core/seq? input__14712_nth_0__)
              (clojure.core/let
               [input__14712_nth_0___l__
                (clojure.core/take 1 input__14712_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14712_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14712_nth_0___r__
                  (clojure.core/drop 1 input__14712_nth_0__)]
                 (clojure.core/let
                  [input__14712_nth_0___l___nth_0__
                   (clojure.core/nth input__14712_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14712_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15697
                     (clojure.core/namespace
                      input__14712_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14721 X__15697]
                     (clojure.core/let
                      [X__15699
                       (clojure.core/name
                        input__14712_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15699
                       ("cata")
                       (clojure.core/let
                        [x__9586__auto__
                         (def__15687 input__14712_nth_1__ ?__14721)]
                        (if
                         (meander.runtime.zeta/fail? x__9586__auto__)
                         (state__16327)
                         (clojure.core/let
                          [[?__14721] x__9586__auto__]
                          (if
                           (clojure.core/vector? input__14712)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14712)
                             2)
                            (clojure.core/let
                             [input__14712_nth_0__
                              (clojure.core/nth input__14712 0)
                              input__14712_nth_1__
                              (clojure.core/nth input__14712 1)]
                             (if
                              (clojure.core/seq? input__14712_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__14712_nth_0__)
                                2)
                               (clojure.core/let
                                [input__14712_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14712_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__14712_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__14712_nth_0__]
                                  (clojure.core/let
                                   [?env input__14712_nth_1__]
                                   (try
                                    [{:tag :cata,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__10889__auto__
                                        (CATA__FN__14795
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
                               (state__16327))
                              (state__16327)))
                            (state__16327))
                           (state__16327)))))
                       (state__16327)))))
                   (state__16327))))
                (state__16327)))
              (state__16327)))
            (state__16327))
           (state__16327))))
        (state__16327
         []
         (clojure.core/letfn
          [(def__15721
            [arg__15744 ?__14722]
            (clojure.core/letfn
             [(state__16523
               []
               (clojure.core/let
                [x__15745 "meander.zeta"]
                (if
                 (clojure.core/= ?__14722 x__15745)
                 [?__14722]
                 (state__16524))))
              (state__16524
               []
               (if
                (clojure.core/map? arg__15744)
                (clojure.core/let
                 [VAL__15746 (.valAt arg__15744 :aliases)]
                 (if
                  (clojure.core/map? VAL__15746)
                  (clojure.core/let
                   [X__15748 (clojure.core/set VAL__15746)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15748))
                    (clojure.core/loop
                     [search_space__16525 (clojure.core/seq X__15748)]
                     (if
                      (clojure.core/seq search_space__16525)
                      (clojure.core/let
                       [elem__15749
                        (clojure.core/first search_space__16525)
                        result__16526
                        (clojure.core/let
                         [elem__15749_nth_0__
                          (clojure.core/nth elem__15749 0)
                          elem__15749_nth_1__
                          (clojure.core/nth elem__15749 1)]
                         (if
                          (clojure.core/symbol? elem__15749_nth_0__)
                          (clojure.core/let
                           [X__15751
                            (clojure.core/name elem__15749_nth_0__)]
                           (if
                            (clojure.core/= ?__14722 X__15751)
                            (if
                             (clojure.core/symbol? elem__15749_nth_1__)
                             (clojure.core/let
                              [X__15753
                               (clojure.core/name elem__15749_nth_1__)]
                              (clojure.core/case
                               X__15753
                               ("meander.zeta")
                               [?__14722]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16526)
                        (recur (clojure.core/next search_space__16525))
                        result__16526))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16523)))]
          (if
           (clojure.core/vector? input__14712)
           (if
            (clojure.core/= (clojure.core/count input__14712) 2)
            (clojure.core/let
             [input__14712_nth_0__
              (clojure.core/nth input__14712 0)
              input__14712_nth_1__
              (clojure.core/nth input__14712 1)]
             (if
              (clojure.core/seq? input__14712_nth_0__)
              (clojure.core/let
               [input__14712_nth_0___l__
                (clojure.core/take 1 input__14712_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14712_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14712_nth_0___r__
                  (clojure.core/drop 1 input__14712_nth_0__)]
                 (clojure.core/let
                  [input__14712_nth_0___l___nth_0__
                   (clojure.core/nth input__14712_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14712_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15731
                     (clojure.core/namespace
                      input__14712_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14722 X__15731]
                     (clojure.core/let
                      [X__15733
                       (clojure.core/name
                        input__14712_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15733
                       ("fold")
                       (clojure.core/let
                        [x__9586__auto__
                         (def__15721 input__14712_nth_1__ ?__14722)]
                        (if
                         (meander.runtime.zeta/fail? x__9586__auto__)
                         (state__16328)
                         (clojure.core/let
                          [[?__14722] x__9586__auto__]
                          (if
                           (clojure.core/vector? input__14712)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14712)
                             2)
                            (clojure.core/let
                             [input__14712_nth_0__
                              (clojure.core/nth input__14712 0)
                              input__14712_nth_1__
                              (clojure.core/nth input__14712 1)]
                             (if
                              (clojure.core/seq? input__14712_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 4)
                                 input__14712_nth_0__)
                                4)
                               (clojure.core/let
                                [input__14712_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14712_nth_0__
                                  1)
                                 input__14712_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__14712_nth_0__
                                  2)
                                 input__14712_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__14712_nth_0__
                                  3)]
                                (clojure.core/let
                                 [?mutable-variable
                                  input__14712_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?initial-value
                                   input__14712_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?fold-function
                                    input__14712_nth_0___nth_3__]
                                   (clojure.core/let
                                    [?form input__14712_nth_0__]
                                    (clojure.core/let
                                     [?env input__14712_nth_1__]
                                     (try
                                      [(clojure.core/let
                                        [CATA_RESULT__10889__auto__
                                         (CATA__FN__14795
                                          ['meander.dev.parse.zeta/make-fold
                                           (clojure.core/let
                                            [CATA_RESULT__10889__auto__
                                             (CATA__FN__14795
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
                                             (CATA__FN__14795
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
                               (state__16328))
                              (state__16328)))
                            (state__16328))
                           (state__16328)))))
                       (state__16328)))))
                   (state__16328))))
                (state__16328)))
              (state__16328)))
            (state__16328))
           (state__16328))))
        (state__16328
         []
         (if
          (clojure.core/vector? input__14712)
          (if
           (clojure.core/= (clojure.core/count input__14712) 5)
           (clojure.core/let
            [input__14712_nth_0__
             (clojure.core/nth input__14712 0)
             input__14712_nth_1__
             (clojure.core/nth input__14712 1)
             input__14712_nth_2__
             (clojure.core/nth input__14712 2)
             input__14712_nth_3__
             (clojure.core/nth input__14712 3)
             input__14712_nth_4__
             (clojure.core/nth input__14712 4)]
            (clojure.core/case
             input__14712_nth_0__
             (meander.dev.parse.zeta/make-fold)
             (if
              (clojure.core/map? input__14712_nth_1__)
              (clojure.core/let
               [VAL__15756 (.valAt input__14712_nth_1__ :tag)]
               (clojure.core/case
                VAL__15756
                (:mutable-variable)
                (clojure.core/let
                 [?variable-ast input__14712_nth_1__]
                 (clojure.core/let
                  [?initial-value-ast input__14712_nth_2__]
                  (clojure.core/let
                   [?fold-function input__14712_nth_3__]
                   (clojure.core/let
                    [?form input__14712_nth_4__]
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
                (state__16329)))
              (state__16329))
             (state__16329)))
           (state__16329))
          (state__16329)))
        (state__16329
         []
         (clojure.core/letfn
          [(def__15758
            [arg__15781 ?__14723]
            (clojure.core/letfn
             [(state__16528
               []
               (clojure.core/let
                [x__15782 "meander.zeta"]
                (if
                 (clojure.core/= ?__14723 x__15782)
                 [?__14723]
                 (state__16529))))
              (state__16529
               []
               (if
                (clojure.core/map? arg__15781)
                (clojure.core/let
                 [VAL__15783 (.valAt arg__15781 :aliases)]
                 (if
                  (clojure.core/map? VAL__15783)
                  (clojure.core/let
                   [X__15785 (clojure.core/set VAL__15783)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15785))
                    (clojure.core/loop
                     [search_space__16530 (clojure.core/seq X__15785)]
                     (if
                      (clojure.core/seq search_space__16530)
                      (clojure.core/let
                       [elem__15786
                        (clojure.core/first search_space__16530)
                        result__16531
                        (clojure.core/let
                         [elem__15786_nth_0__
                          (clojure.core/nth elem__15786 0)
                          elem__15786_nth_1__
                          (clojure.core/nth elem__15786 1)]
                         (if
                          (clojure.core/symbol? elem__15786_nth_0__)
                          (clojure.core/let
                           [X__15788
                            (clojure.core/name elem__15786_nth_0__)]
                           (if
                            (clojure.core/= ?__14723 X__15788)
                            (if
                             (clojure.core/symbol? elem__15786_nth_1__)
                             (clojure.core/let
                              [X__15790
                               (clojure.core/name elem__15786_nth_1__)]
                              (clojure.core/case
                               X__15790
                               ("meander.zeta")
                               [?__14723]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16531)
                        (recur (clojure.core/next search_space__16530))
                        result__16531))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16528)))]
          (if
           (clojure.core/vector? input__14712)
           (if
            (clojure.core/= (clojure.core/count input__14712) 2)
            (clojure.core/let
             [input__14712_nth_0__
              (clojure.core/nth input__14712 0)
              input__14712_nth_1__
              (clojure.core/nth input__14712 1)]
             (if
              (clojure.core/seq? input__14712_nth_0__)
              (clojure.core/let
               [input__14712_nth_0___l__
                (clojure.core/take 1 input__14712_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14712_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14712_nth_0___r__
                  (clojure.core/drop 1 input__14712_nth_0__)]
                 (clojure.core/let
                  [input__14712_nth_0___l___nth_0__
                   (clojure.core/nth input__14712_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14712_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15768
                     (clojure.core/namespace
                      input__14712_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14723 X__15768]
                     (clojure.core/let
                      [X__15770
                       (clojure.core/name
                        input__14712_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15770
                       ("keyword")
                       (clojure.core/let
                        [x__9586__auto__
                         (def__15758 input__14712_nth_1__ ?__14723)]
                        (if
                         (meander.runtime.zeta/fail? x__9586__auto__)
                         (state__16330)
                         (clojure.core/let
                          [[?__14723] x__9586__auto__]
                          (if
                           (clojure.core/vector? input__14712)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14712)
                             2)
                            (clojure.core/let
                             [input__14712_nth_0__
                              (clojure.core/nth input__14712 0)
                              input__14712_nth_1__
                              (clojure.core/nth input__14712 1)]
                             (if
                              (clojure.core/seq? input__14712_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__14712_nth_0__)
                                2)
                               (clojure.core/let
                                [input__14712_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14712_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?name input__14712_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__14712_nth_0__]
                                  (clojure.core/let
                                   [?env input__14712_nth_1__]
                                   (try
                                    [{:tag :keyword,
                                      :name
                                      (clojure.core/let
                                       [CATA_RESULT__10889__auto__
                                        (CATA__FN__14795 [?name ?env])]
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
                               (state__16330))
                              (state__16330)))
                            (state__16330))
                           (state__16330)))))
                       (state__16330)))))
                   (state__16330))))
                (state__16330)))
              (state__16330)))
            (state__16330))
           (state__16330))))
        (state__16330
         []
         (clojure.core/letfn
          [(def__15792
            [arg__15815 ?__14724]
            (clojure.core/letfn
             [(state__16533
               []
               (clojure.core/let
                [x__15816 "meander.zeta"]
                (if
                 (clojure.core/= ?__14724 x__15816)
                 [?__14724]
                 (state__16534))))
              (state__16534
               []
               (if
                (clojure.core/map? arg__15815)
                (clojure.core/let
                 [VAL__15817 (.valAt arg__15815 :aliases)]
                 (if
                  (clojure.core/map? VAL__15817)
                  (clojure.core/let
                   [X__15819 (clojure.core/set VAL__15817)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15819))
                    (clojure.core/loop
                     [search_space__16535 (clojure.core/seq X__15819)]
                     (if
                      (clojure.core/seq search_space__16535)
                      (clojure.core/let
                       [elem__15820
                        (clojure.core/first search_space__16535)
                        result__16536
                        (clojure.core/let
                         [elem__15820_nth_0__
                          (clojure.core/nth elem__15820 0)
                          elem__15820_nth_1__
                          (clojure.core/nth elem__15820 1)]
                         (if
                          (clojure.core/symbol? elem__15820_nth_0__)
                          (clojure.core/let
                           [X__15822
                            (clojure.core/name elem__15820_nth_0__)]
                           (if
                            (clojure.core/= ?__14724 X__15822)
                            (if
                             (clojure.core/symbol? elem__15820_nth_1__)
                             (clojure.core/let
                              [X__15824
                               (clojure.core/name elem__15820_nth_1__)]
                              (clojure.core/case
                               X__15824
                               ("meander.zeta")
                               [?__14724]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16536)
                        (recur (clojure.core/next search_space__16535))
                        result__16536))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16533)))]
          (if
           (clojure.core/vector? input__14712)
           (if
            (clojure.core/= (clojure.core/count input__14712) 2)
            (clojure.core/let
             [input__14712_nth_0__
              (clojure.core/nth input__14712 0)
              input__14712_nth_1__
              (clojure.core/nth input__14712 1)]
             (if
              (clojure.core/seq? input__14712_nth_0__)
              (clojure.core/let
               [input__14712_nth_0___l__
                (clojure.core/take 1 input__14712_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14712_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14712_nth_0___r__
                  (clojure.core/drop 1 input__14712_nth_0__)]
                 (clojure.core/let
                  [input__14712_nth_0___l___nth_0__
                   (clojure.core/nth input__14712_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14712_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15802
                     (clojure.core/namespace
                      input__14712_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14724 X__15802]
                     (clojure.core/let
                      [X__15804
                       (clojure.core/name
                        input__14712_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15804
                       ("let")
                       (clojure.core/let
                        [x__9586__auto__
                         (def__15792 input__14712_nth_1__ ?__14724)]
                        (if
                         (meander.runtime.zeta/fail? x__9586__auto__)
                         (state__16331)
                         (clojure.core/let
                          [[?__14724] x__9586__auto__]
                          (if
                           (clojure.core/vector? input__14712)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14712)
                             2)
                            (clojure.core/let
                             [input__14712_nth_0__
                              (clojure.core/nth input__14712 0)
                              input__14712_nth_1__
                              (clojure.core/nth input__14712 1)]
                             (if
                              (clojure.core/seq? input__14712_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__14712_nth_0__)
                                3)
                               (clojure.core/let
                                [input__14712_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14712_nth_0__
                                  1)
                                 input__14712_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__14712_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?pattern
                                  input__14712_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?expression
                                   input__14712_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__14712_nth_0__]
                                   (clojure.core/let
                                    [?env input__14712_nth_1__]
                                    (try
                                     [{:tag :let,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__10889__auto__
                                         (CATA__FN__14795
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
                               (state__16331))
                              (state__16331)))
                            (state__16331))
                           (state__16331)))))
                       (state__16331)))))
                   (state__16331))))
                (state__16331)))
              (state__16331)))
            (state__16331))
           (state__16331))))
        (state__16331
         []
         (clojure.core/letfn
          [(def__15826
            [arg__15849 ?__14725]
            (clojure.core/letfn
             [(state__16538
               []
               (clojure.core/let
                [x__15850 "meander.zeta"]
                (if
                 (clojure.core/= ?__14725 x__15850)
                 [?__14725]
                 (state__16539))))
              (state__16539
               []
               (if
                (clojure.core/map? arg__15849)
                (clojure.core/let
                 [VAL__15851 (.valAt arg__15849 :aliases)]
                 (if
                  (clojure.core/map? VAL__15851)
                  (clojure.core/let
                   [X__15853 (clojure.core/set VAL__15851)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15853))
                    (clojure.core/loop
                     [search_space__16540 (clojure.core/seq X__15853)]
                     (if
                      (clojure.core/seq search_space__16540)
                      (clojure.core/let
                       [elem__15854
                        (clojure.core/first search_space__16540)
                        result__16541
                        (clojure.core/let
                         [elem__15854_nth_0__
                          (clojure.core/nth elem__15854 0)
                          elem__15854_nth_1__
                          (clojure.core/nth elem__15854 1)]
                         (if
                          (clojure.core/symbol? elem__15854_nth_0__)
                          (clojure.core/let
                           [X__15856
                            (clojure.core/name elem__15854_nth_0__)]
                           (if
                            (clojure.core/= ?__14725 X__15856)
                            (if
                             (clojure.core/symbol? elem__15854_nth_1__)
                             (clojure.core/let
                              [X__15858
                               (clojure.core/name elem__15854_nth_1__)]
                              (clojure.core/case
                               X__15858
                               ("meander.zeta")
                               [?__14725]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16541)
                        (recur (clojure.core/next search_space__16540))
                        result__16541))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16538)))]
          (if
           (clojure.core/vector? input__14712)
           (if
            (clojure.core/= (clojure.core/count input__14712) 2)
            (clojure.core/let
             [input__14712_nth_0__
              (clojure.core/nth input__14712 0)
              input__14712_nth_1__
              (clojure.core/nth input__14712 1)]
             (if
              (clojure.core/seq? input__14712_nth_0__)
              (clojure.core/let
               [input__14712_nth_0___l__
                (clojure.core/take 1 input__14712_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14712_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14712_nth_0___r__
                  (clojure.core/drop 1 input__14712_nth_0__)]
                 (clojure.core/let
                  [input__14712_nth_0___l___nth_0__
                   (clojure.core/nth input__14712_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14712_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15836
                     (clojure.core/namespace
                      input__14712_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14725 X__15836]
                     (clojure.core/let
                      [X__15838
                       (clojure.core/name
                        input__14712_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15838
                       ("let")
                       (clojure.core/let
                        [x__9586__auto__
                         (def__15826 input__14712_nth_1__ ?__14725)]
                        (if
                         (meander.runtime.zeta/fail? x__9586__auto__)
                         (state__16332)
                         (clojure.core/let
                          [[?__14725] x__9586__auto__]
                          (if
                           (clojure.core/vector? input__14712)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14712)
                             2)
                            (clojure.core/let
                             [input__14712_nth_0__
                              (clojure.core/nth input__14712 0)
                              input__14712_nth_1__
                              (clojure.core/nth input__14712 1)]
                             (if
                              (clojure.core/seq? input__14712_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 4)
                                 input__14712_nth_0__)
                                4)
                               (clojure.core/let
                                [input__14712_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14712_nth_0__
                                  1)
                                 input__14712_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__14712_nth_0__
                                  2)
                                 input__14712_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__14712_nth_0__
                                  3)]
                                (clojure.core/let
                                 [?pattern
                                  input__14712_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?expression
                                   input__14712_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?next input__14712_nth_0___nth_3__]
                                   (clojure.core/let
                                    [?form input__14712_nth_0__]
                                    (clojure.core/let
                                     [?env input__14712_nth_1__]
                                     (try
                                      [{:tag :let,
                                        :pattern
                                        (clojure.core/let
                                         [CATA_RESULT__10889__auto__
                                          (CATA__FN__14795
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
                                          (CATA__FN__14795
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
                               (state__16332))
                              (state__16332)))
                            (state__16332))
                           (state__16332)))))
                       (state__16332)))))
                   (state__16332))))
                (state__16332)))
              (state__16332)))
            (state__16332))
           (state__16332))))
        (state__16332
         []
         (clojure.core/letfn
          [(def__15860
            [arg__15883 ?__14726]
            (clojure.core/letfn
             [(state__16543
               []
               (clojure.core/let
                [x__15884 "meander.zeta"]
                (if
                 (clojure.core/= ?__14726 x__15884)
                 [?__14726]
                 (state__16544))))
              (state__16544
               []
               (if
                (clojure.core/map? arg__15883)
                (clojure.core/let
                 [VAL__15885 (.valAt arg__15883 :aliases)]
                 (if
                  (clojure.core/map? VAL__15885)
                  (clojure.core/let
                   [X__15887 (clojure.core/set VAL__15885)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15887))
                    (clojure.core/loop
                     [search_space__16545 (clojure.core/seq X__15887)]
                     (if
                      (clojure.core/seq search_space__16545)
                      (clojure.core/let
                       [elem__15888
                        (clojure.core/first search_space__16545)
                        result__16546
                        (clojure.core/let
                         [elem__15888_nth_0__
                          (clojure.core/nth elem__15888 0)
                          elem__15888_nth_1__
                          (clojure.core/nth elem__15888 1)]
                         (if
                          (clojure.core/symbol? elem__15888_nth_0__)
                          (clojure.core/let
                           [X__15890
                            (clojure.core/name elem__15888_nth_0__)]
                           (if
                            (clojure.core/= ?__14726 X__15890)
                            (if
                             (clojure.core/symbol? elem__15888_nth_1__)
                             (clojure.core/let
                              [X__15892
                               (clojure.core/name elem__15888_nth_1__)]
                              (clojure.core/case
                               X__15892
                               ("meander.zeta")
                               [?__14726]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16546)
                        (recur (clojure.core/next search_space__16545))
                        result__16546))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16543)))]
          (if
           (clojure.core/vector? input__14712)
           (if
            (clojure.core/= (clojure.core/count input__14712) 2)
            (clojure.core/let
             [input__14712_nth_0__
              (clojure.core/nth input__14712 0)
              input__14712_nth_1__
              (clojure.core/nth input__14712 1)]
             (if
              (clojure.core/seq? input__14712_nth_0__)
              (clojure.core/let
               [input__14712_nth_0___l__
                (clojure.core/take 1 input__14712_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14712_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14712_nth_0___r__
                  (clojure.core/drop 1 input__14712_nth_0__)]
                 (clojure.core/let
                  [input__14712_nth_0___l___nth_0__
                   (clojure.core/nth input__14712_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14712_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15870
                     (clojure.core/namespace
                      input__14712_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14726 X__15870]
                     (clojure.core/let
                      [X__15872
                       (clojure.core/name
                        input__14712_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15872
                       ("not")
                       (clojure.core/let
                        [x__9586__auto__
                         (def__15860 input__14712_nth_1__ ?__14726)]
                        (if
                         (meander.runtime.zeta/fail? x__9586__auto__)
                         (state__16333)
                         (clojure.core/let
                          [[?__14726] x__9586__auto__]
                          (if
                           (clojure.core/vector? input__14712)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14712)
                             2)
                            (clojure.core/let
                             [input__14712_nth_0__
                              (clojure.core/nth input__14712 0)
                              input__14712_nth_1__
                              (clojure.core/nth input__14712 1)]
                             (if
                              (clojure.core/seq? input__14712_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__14712_nth_0__)
                                2)
                               (clojure.core/let
                                [input__14712_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14712_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__14712_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__14712_nth_0__]
                                  (clojure.core/let
                                   [?env input__14712_nth_1__]
                                   (try
                                    [{:tag :not,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__10889__auto__
                                        (CATA__FN__14795
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
                               (state__16333))
                              (state__16333)))
                            (state__16333))
                           (state__16333)))))
                       (state__16333)))))
                   (state__16333))))
                (state__16333)))
              (state__16333)))
            (state__16333))
           (state__16333))))
        (state__16333
         []
         (clojure.core/letfn
          [(def__15894
            [arg__15919 ?__14727]
            (clojure.core/letfn
             [(state__16548
               []
               (clojure.core/let
                [x__15920 "meander.zeta"]
                (if
                 (clojure.core/= ?__14727 x__15920)
                 [?__14727]
                 (state__16549))))
              (state__16549
               []
               (if
                (clojure.core/map? arg__15919)
                (clojure.core/let
                 [VAL__15921 (.valAt arg__15919 :aliases)]
                 (if
                  (clojure.core/map? VAL__15921)
                  (clojure.core/let
                   [X__15923 (clojure.core/set VAL__15921)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15923))
                    (clojure.core/loop
                     [search_space__16550 (clojure.core/seq X__15923)]
                     (if
                      (clojure.core/seq search_space__16550)
                      (clojure.core/let
                       [elem__15924
                        (clojure.core/first search_space__16550)
                        result__16551
                        (clojure.core/let
                         [elem__15924_nth_0__
                          (clojure.core/nth elem__15924 0)
                          elem__15924_nth_1__
                          (clojure.core/nth elem__15924 1)]
                         (if
                          (clojure.core/symbol? elem__15924_nth_0__)
                          (clojure.core/let
                           [X__15926
                            (clojure.core/name elem__15924_nth_0__)]
                           (if
                            (clojure.core/= ?__14727 X__15926)
                            (if
                             (clojure.core/symbol? elem__15924_nth_1__)
                             (clojure.core/let
                              [X__15928
                               (clojure.core/name elem__15924_nth_1__)]
                              (clojure.core/case
                               X__15928
                               ("meander.zeta")
                               [?__14727]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16551)
                        (recur (clojure.core/next search_space__16550))
                        result__16551))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16548)))]
          (if
           (clojure.core/vector? input__14712)
           (if
            (clojure.core/= (clojure.core/count input__14712) 2)
            (clojure.core/let
             [input__14712_nth_0__
              (clojure.core/nth input__14712 0)
              input__14712_nth_1__
              (clojure.core/nth input__14712 1)]
             (if
              (clojure.core/seq? input__14712_nth_0__)
              (clojure.core/let
               [input__14712_nth_0___l__
                (clojure.core/take 1 input__14712_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14712_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14712_nth_0___r__
                  (clojure.core/drop 1 input__14712_nth_0__)]
                 (clojure.core/let
                  [input__14712_nth_0___l___nth_0__
                   (clojure.core/nth input__14712_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14712_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15906
                     (clojure.core/namespace
                      input__14712_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14727 X__15906]
                     (clojure.core/let
                      [X__15908
                       (clojure.core/name
                        input__14712_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15908
                       ("or")
                       (clojure.core/let
                        [x__9586__auto__
                         (def__15894 input__14712_nth_1__ ?__14727)]
                        (if
                         (meander.runtime.zeta/fail? x__9586__auto__)
                         (state__16334)
                         (clojure.core/let
                          [[?__14727] x__9586__auto__]
                          (if
                           (clojure.core/vector? input__14712)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14712)
                             2)
                            (clojure.core/let
                             [input__14712_nth_0__
                              (clojure.core/nth input__14712 0)
                              input__14712_nth_1__
                              (clojure.core/nth input__14712 1)]
                             (if
                              (clojure.core/seq? input__14712_nth_0__)
                              (clojure.core/let
                               [input__14712_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__14712_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__14712_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__14712_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__14712_nth_0__)]
                                 (clojure.core/let
                                  [!forms
                                   (clojure.core/vec
                                    input__14712_nth_0___r__)]
                                  (clojure.core/let
                                   [?form input__14712_nth_0__]
                                   (clojure.core/let
                                    [?env input__14712_nth_1__]
                                    (try
                                     [(clojure.core/let
                                       [!forms__counter
                                        (meander.runtime.zeta/iterator
                                         !forms)]
                                       (clojure.core/let
                                        [CATA_RESULT__10889__auto__
                                         (CATA__FN__14795
                                          ['meander.dev.parse.zeta/make-or
                                           (clojure.core/into
                                            []
                                            (clojure.core/loop
                                             [return__14798
                                              (clojure.core/transient
                                               [])]
                                             (if
                                              (clojure.core/and
                                               (.hasNext
                                                !forms__counter))
                                              (recur
                                               (clojure.core/conj!
                                                return__14798
                                                (clojure.core/let
                                                 [CATA_RESULT__10889__auto__
                                                  (CATA__FN__14795
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
                                               return__14798))))
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
                                (state__16334)))
                              (state__16334)))
                            (state__16334))
                           (state__16334)))))
                       (state__16334)))))
                   (state__16334))))
                (state__16334)))
              (state__16334)))
            (state__16334))
           (state__16334))))
        (state__16334
         []
         (if
          (clojure.core/vector? input__14712)
          (if
           (clojure.core/= (clojure.core/count input__14712) 3)
           (clojure.core/let
            [input__14712_nth_0__
             (clojure.core/nth input__14712 0)
             input__14712_nth_1__
             (clojure.core/nth input__14712 1)
             input__14712_nth_2__
             (clojure.core/nth input__14712 2)]
            (clojure.core/case
             input__14712_nth_0__
             (meander.dev.parse.zeta/make-or)
             (clojure.core/letfn
              [(state__16553
                []
                (if
                 (clojure.core/vector? input__14712_nth_1__)
                 (clojure.core/case
                  input__14712_nth_1__
                  ([])
                  (clojure.core/let
                   [?form input__14712_nth_2__]
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
                  (state__16554))
                 (state__16554)))
               (state__16554
                []
                (clojure.core/case
                 input__14712_nth_2__
                 (nil)
                 (if
                  (clojure.core/vector? input__14712_nth_1__)
                  (if
                   (clojure.core/=
                    (clojure.core/count input__14712_nth_1__)
                    1)
                   (clojure.core/let
                    [input__14712_nth_1___nth_0__
                     (clojure.core/nth input__14712_nth_1__ 0)]
                    (clojure.core/let
                     [?ast-a input__14712_nth_1___nth_0__]
                     (try
                      [?ast-a]
                      (catch
                       java.lang.Exception
                       e__11829__auto__
                       (if
                        (meander.runtime.zeta/fail? e__11829__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__11829__auto__))))))
                   (state__16555))
                  (state__16555))
                 (state__16555)))
               (state__16555
                []
                (if
                 (clojure.core/vector? input__14712_nth_1__)
                 (clojure.core/letfn
                  [(state__16556
                    []
                    (if
                     (clojure.core/=
                      (clojure.core/count input__14712_nth_1__)
                      1)
                     (clojure.core/let
                      [input__14712_nth_1___nth_0__
                       (clojure.core/nth input__14712_nth_1__ 0)]
                      (clojure.core/let
                       [?ast-a input__14712_nth_1___nth_0__]
                       (clojure.core/let
                        [?form input__14712_nth_2__]
                        (try
                         [(clojure.core/let
                           [CATA_RESULT__10889__auto__
                            (CATA__FN__14795
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
                     (state__16557)))
                   (state__16557
                    []
                    (if
                     (clojure.core/=
                      (clojure.core/count input__14712_nth_1__)
                      2)
                     (clojure.core/let
                      [input__14712_nth_1___nth_0__
                       (clojure.core/nth input__14712_nth_1__ 0)
                       input__14712_nth_1___nth_1__
                       (clojure.core/nth input__14712_nth_1__ 1)]
                      (clojure.core/let
                       [?ast-a input__14712_nth_1___nth_0__]
                       (clojure.core/let
                        [?ast-b input__14712_nth_1___nth_1__]
                        (clojure.core/let
                         [?form input__14712_nth_2__]
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
                     (state__16558)))
                   (state__16558
                    []
                    (clojure.core/loop
                     [search_space__16559
                      (meander.runtime.zeta/epsilon-partitions
                       2
                       input__14712_nth_1__)]
                     (if
                      (clojure.core/seq search_space__16559)
                      (clojure.core/let
                       [input__14712_nth_1___parts__
                        (clojure.core/first search_space__16559)
                        result__16560
                        (clojure.core/let
                         [input__14712_nth_1___l__
                          (clojure.core/nth
                           input__14712_nth_1___parts__
                           0)
                          input__14712_nth_1___r__
                          (clojure.core/nth
                           input__14712_nth_1___parts__
                           1)]
                         (clojure.core/letfn
                          [(state__16562
                            []
                            (clojure.core/let
                             [!asts-1 []]
                             (clojure.core/let
                              [ret__9750__auto__
                               (meander.runtime.zeta/epsilon-run-star-1
                                input__14712_nth_1___l__
                                [!asts-1]
                                (clojure.core/fn
                                 [[!asts-1] input__15955]
                                 (clojure.core/let
                                  [input__15955_nth_0__
                                   (clojure.core/nth input__15955 0)]
                                  (clojure.core/let
                                   [!asts-1
                                    (clojure.core/conj
                                     !asts-1
                                     input__15955_nth_0__)]
                                   [!asts-1])))
                                (clojure.core/fn
                                 [[!asts-1]]
                                 (clojure.core/let
                                  [X__15948
                                   (clojure.core/count
                                    input__14712_nth_1___l__)]
                                  (clojure.core/let
                                   [?n X__15948]
                                   (clojure.core/let
                                    [X__15952
                                     (clojure.core/count
                                      input__14712_nth_1___r__)]
                                    (if
                                     (clojure.core/= ?n X__15952)
                                     (clojure.core/let
                                      [!asts-2 []]
                                      (clojure.core/let
                                       [ret__9750__auto__
                                        (meander.runtime.zeta/epsilon-run-star-1
                                         input__14712_nth_1___r__
                                         [!asts-2 !asts-1]
                                         (clojure.core/fn
                                          [[!asts-2 !asts-1]
                                           input__15953]
                                          (clojure.core/let
                                           [input__15953_nth_0__
                                            (clojure.core/nth
                                             input__15953
                                             0)]
                                           (clojure.core/let
                                            [!asts-2
                                             (clojure.core/conj
                                              !asts-2
                                              input__15953_nth_0__)]
                                            [!asts-2 !asts-1])))
                                         (clojure.core/fn
                                          [[!asts-2 !asts-1]]
                                          (clojure.core/let
                                           [?form input__14712_nth_2__]
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
                                                (CATA__FN__14795
                                                 ['meander.dev.parse.zeta/make-or
                                                  [(clojure.core/let
                                                    [CATA_RESULT__10889__auto__
                                                     (CATA__FN__14795
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
                                                     (CATA__FN__14795
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
                                        (state__16563)
                                        ret__9750__auto__)))
                                     (state__16563)))))))]
                              (if
                               (meander.runtime.zeta/fail?
                                ret__9750__auto__)
                               (state__16563)
                               ret__9750__auto__))))
                           (state__16563
                            []
                            (clojure.core/let
                             [!asts-1 []]
                             (clojure.core/let
                              [ret__9750__auto__
                               (meander.runtime.zeta/epsilon-run-star-1
                                input__14712_nth_1___l__
                                [!asts-1]
                                (clojure.core/fn
                                 [[!asts-1] input__15971]
                                 (clojure.core/let
                                  [input__15971_nth_0__
                                   (clojure.core/nth input__15971 0)]
                                  (clojure.core/let
                                   [!asts-1
                                    (clojure.core/conj
                                     !asts-1
                                     input__15971_nth_0__)]
                                   [!asts-1])))
                                (clojure.core/fn
                                 [[!asts-1]]
                                 (clojure.core/let
                                  [X__15962
                                   (clojure.core/count
                                    input__14712_nth_1___l__)]
                                  (clojure.core/let
                                   [?n X__15962]
                                   (clojure.core/let
                                    [input__14712_nth_1___r___l__
                                     (clojure.core/subvec
                                      input__14712_nth_1___r__
                                      0
                                      (clojure.core/min
                                       (clojure.core/count
                                        input__14712_nth_1___r__)
                                       1))]
                                    (if
                                     (clojure.core/=
                                      (clojure.core/count
                                       input__14712_nth_1___r___l__)
                                      1)
                                     (clojure.core/let
                                      [input__14712_nth_1___r___r__
                                       (clojure.core/subvec
                                        input__14712_nth_1___r__
                                        1)]
                                      (clojure.core/let
                                       [input__14712_nth_1___r___l___nth_0__
                                        (clojure.core/nth
                                         input__14712_nth_1___r___l__
                                         0)]
                                       (clojure.core/let
                                        [?ast
                                         input__14712_nth_1___r___l___nth_0__]
                                        (clojure.core/let
                                         [X__15968
                                          (clojure.core/count
                                           input__14712_nth_1___r___r__)]
                                         (if
                                          (clojure.core/= ?n X__15968)
                                          (clojure.core/let
                                           [!asts-2 []]
                                           (clojure.core/let
                                            [ret__9750__auto__
                                             (meander.runtime.zeta/epsilon-run-star-1
                                              input__14712_nth_1___r___r__
                                              [!asts-2 !asts-1]
                                              (clojure.core/fn
                                               [[!asts-2 !asts-1]
                                                input__15969]
                                               (clojure.core/let
                                                [input__15969_nth_0__
                                                 (clojure.core/nth
                                                  input__15969
                                                  0)]
                                                (clojure.core/let
                                                 [!asts-2
                                                  (clojure.core/conj
                                                   !asts-2
                                                   input__15969_nth_0__)]
                                                 [!asts-2 !asts-1])))
                                              (clojure.core/fn
                                               [[!asts-2 !asts-1]]
                                               (clojure.core/let
                                                [?form
                                                 input__14712_nth_2__]
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
                                                     (CATA__FN__14795
                                                      ['meander.dev.parse.zeta/make-or
                                                       [(clojure.core/let
                                                         [CATA_RESULT__10889__auto__
                                                          (CATA__FN__14795
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
                                                          (CATA__FN__14795
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
                          (state__16562)))]
                       (if
                        (meander.runtime.zeta/fail? result__16560)
                        (recur (clojure.core/next search_space__16559))
                        result__16560))
                      (state__16335))))]
                  (state__16556))
                 (state__16335)))]
              (state__16553))
             (state__16335)))
           (state__16335))
          (state__16335)))
        (state__16335
         []
         (clojure.core/letfn
          [(def__15974
            [arg__15997 ?__14728]
            (clojure.core/letfn
             [(state__16568
               []
               (clojure.core/let
                [x__15998 "meander.zeta"]
                (if
                 (clojure.core/= ?__14728 x__15998)
                 [?__14728]
                 (state__16569))))
              (state__16569
               []
               (if
                (clojure.core/map? arg__15997)
                (clojure.core/let
                 [VAL__15999 (.valAt arg__15997 :aliases)]
                 (if
                  (clojure.core/map? VAL__15999)
                  (clojure.core/let
                   [X__16001 (clojure.core/set VAL__15999)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__16001))
                    (clojure.core/loop
                     [search_space__16570 (clojure.core/seq X__16001)]
                     (if
                      (clojure.core/seq search_space__16570)
                      (clojure.core/let
                       [elem__16002
                        (clojure.core/first search_space__16570)
                        result__16571
                        (clojure.core/let
                         [elem__16002_nth_0__
                          (clojure.core/nth elem__16002 0)
                          elem__16002_nth_1__
                          (clojure.core/nth elem__16002 1)]
                         (if
                          (clojure.core/symbol? elem__16002_nth_0__)
                          (clojure.core/let
                           [X__16004
                            (clojure.core/name elem__16002_nth_0__)]
                           (if
                            (clojure.core/= ?__14728 X__16004)
                            (if
                             (clojure.core/symbol? elem__16002_nth_1__)
                             (clojure.core/let
                              [X__16006
                               (clojure.core/name elem__16002_nth_1__)]
                              (clojure.core/case
                               X__16006
                               ("meander.zeta")
                               [?__14728]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16571)
                        (recur (clojure.core/next search_space__16570))
                        result__16571))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16568)))]
          (if
           (clojure.core/vector? input__14712)
           (if
            (clojure.core/= (clojure.core/count input__14712) 2)
            (clojure.core/let
             [input__14712_nth_0__
              (clojure.core/nth input__14712 0)
              input__14712_nth_1__
              (clojure.core/nth input__14712 1)]
             (if
              (clojure.core/seq? input__14712_nth_0__)
              (clojure.core/let
               [input__14712_nth_0___l__
                (clojure.core/take 1 input__14712_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14712_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14712_nth_0___r__
                  (clojure.core/drop 1 input__14712_nth_0__)]
                 (clojure.core/let
                  [input__14712_nth_0___l___nth_0__
                   (clojure.core/nth input__14712_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14712_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15984
                     (clojure.core/namespace
                      input__14712_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14728 X__15984]
                     (clojure.core/let
                      [X__15986
                       (clojure.core/name
                        input__14712_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15986
                       ("pred")
                       (clojure.core/let
                        [x__9586__auto__
                         (def__15974 input__14712_nth_1__ ?__14728)]
                        (if
                         (meander.runtime.zeta/fail? x__9586__auto__)
                         (state__16336)
                         (clojure.core/let
                          [[?__14728] x__9586__auto__]
                          (if
                           (clojure.core/vector? input__14712)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14712)
                             2)
                            (clojure.core/let
                             [input__14712_nth_0__
                              (clojure.core/nth input__14712 0)
                              input__14712_nth_1__
                              (clojure.core/nth input__14712 1)]
                             (if
                              (clojure.core/seq? input__14712_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__14712_nth_0__)
                                2)
                               (clojure.core/let
                                [input__14712_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14712_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?expression
                                  input__14712_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__14712_nth_0__]
                                  (clojure.core/let
                                   [?env input__14712_nth_1__]
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
                               (state__16336))
                              (state__16336)))
                            (state__16336))
                           (state__16336)))))
                       (state__16336)))))
                   (state__16336))))
                (state__16336)))
              (state__16336)))
            (state__16336))
           (state__16336))))
        (state__16336
         []
         (clojure.core/letfn
          [(def__16008
            [arg__16031 ?__14729]
            (clojure.core/letfn
             [(state__16573
               []
               (clojure.core/let
                [x__16032 "meander.zeta"]
                (if
                 (clojure.core/= ?__14729 x__16032)
                 [?__14729]
                 (state__16574))))
              (state__16574
               []
               (if
                (clojure.core/map? arg__16031)
                (clojure.core/let
                 [VAL__16033 (.valAt arg__16031 :aliases)]
                 (if
                  (clojure.core/map? VAL__16033)
                  (clojure.core/let
                   [X__16035 (clojure.core/set VAL__16033)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__16035))
                    (clojure.core/loop
                     [search_space__16575 (clojure.core/seq X__16035)]
                     (if
                      (clojure.core/seq search_space__16575)
                      (clojure.core/let
                       [elem__16036
                        (clojure.core/first search_space__16575)
                        result__16576
                        (clojure.core/let
                         [elem__16036_nth_0__
                          (clojure.core/nth elem__16036 0)
                          elem__16036_nth_1__
                          (clojure.core/nth elem__16036 1)]
                         (if
                          (clojure.core/symbol? elem__16036_nth_0__)
                          (clojure.core/let
                           [X__16038
                            (clojure.core/name elem__16036_nth_0__)]
                           (if
                            (clojure.core/= ?__14729 X__16038)
                            (if
                             (clojure.core/symbol? elem__16036_nth_1__)
                             (clojure.core/let
                              [X__16040
                               (clojure.core/name elem__16036_nth_1__)]
                              (clojure.core/case
                               X__16040
                               ("meander.zeta")
                               [?__14729]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16576)
                        (recur (clojure.core/next search_space__16575))
                        result__16576))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16573)))]
          (if
           (clojure.core/vector? input__14712)
           (if
            (clojure.core/= (clojure.core/count input__14712) 2)
            (clojure.core/let
             [input__14712_nth_0__
              (clojure.core/nth input__14712 0)
              input__14712_nth_1__
              (clojure.core/nth input__14712 1)]
             (if
              (clojure.core/seq? input__14712_nth_0__)
              (clojure.core/let
               [input__14712_nth_0___l__
                (clojure.core/take 1 input__14712_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14712_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14712_nth_0___r__
                  (clojure.core/drop 1 input__14712_nth_0__)]
                 (clojure.core/let
                  [input__14712_nth_0___l___nth_0__
                   (clojure.core/nth input__14712_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14712_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__16018
                     (clojure.core/namespace
                      input__14712_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14729 X__16018]
                     (clojure.core/let
                      [X__16020
                       (clojure.core/name
                        input__14712_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__16020
                       ("pred")
                       (clojure.core/let
                        [x__9586__auto__
                         (def__16008 input__14712_nth_1__ ?__14729)]
                        (if
                         (meander.runtime.zeta/fail? x__9586__auto__)
                         (state__16337)
                         (clojure.core/let
                          [[?__14729] x__9586__auto__]
                          (if
                           (clojure.core/vector? input__14712)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14712)
                             2)
                            (clojure.core/let
                             [input__14712_nth_0__
                              (clojure.core/nth input__14712 0)
                              input__14712_nth_1__
                              (clojure.core/nth input__14712 1)]
                             (if
                              (clojure.core/seq? input__14712_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__14712_nth_0__)
                                3)
                               (clojure.core/let
                                [input__14712_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14712_nth_0__
                                  1)
                                 input__14712_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__14712_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?expression
                                  input__14712_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?pattern
                                   input__14712_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__14712_nth_0__]
                                   (clojure.core/let
                                    [?env input__14712_nth_1__]
                                    (try
                                     [{:tag :pred,
                                       :expression
                                       {:tag :host-expression,
                                        :form ?expression},
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__10889__auto__
                                         (CATA__FN__14795
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
                               (state__16337))
                              (state__16337)))
                            (state__16337))
                           (state__16337)))))
                       (state__16337)))))
                   (state__16337))))
                (state__16337)))
              (state__16337)))
            (state__16337))
           (state__16337))))
        (state__16337
         []
         (clojure.core/letfn
          [(def__16042
            [arg__16065 ?__14730]
            (clojure.core/letfn
             [(state__16578
               []
               (clojure.core/let
                [x__16066 "meander.zeta"]
                (if
                 (clojure.core/= ?__14730 x__16066)
                 [?__14730]
                 (state__16579))))
              (state__16579
               []
               (if
                (clojure.core/map? arg__16065)
                (clojure.core/let
                 [VAL__16067 (.valAt arg__16065 :aliases)]
                 (if
                  (clojure.core/map? VAL__16067)
                  (clojure.core/let
                   [X__16069 (clojure.core/set VAL__16067)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__16069))
                    (clojure.core/loop
                     [search_space__16580 (clojure.core/seq X__16069)]
                     (if
                      (clojure.core/seq search_space__16580)
                      (clojure.core/let
                       [elem__16070
                        (clojure.core/first search_space__16580)
                        result__16581
                        (clojure.core/let
                         [elem__16070_nth_0__
                          (clojure.core/nth elem__16070 0)
                          elem__16070_nth_1__
                          (clojure.core/nth elem__16070 1)]
                         (if
                          (clojure.core/symbol? elem__16070_nth_0__)
                          (clojure.core/let
                           [X__16072
                            (clojure.core/name elem__16070_nth_0__)]
                           (if
                            (clojure.core/= ?__14730 X__16072)
                            (if
                             (clojure.core/symbol? elem__16070_nth_1__)
                             (clojure.core/let
                              [X__16074
                               (clojure.core/name elem__16070_nth_1__)]
                              (clojure.core/case
                               X__16074
                               ("meander.zeta")
                               [?__14730]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16581)
                        (recur (clojure.core/next search_space__16580))
                        result__16581))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16578)))]
          (if
           (clojure.core/vector? input__14712)
           (if
            (clojure.core/= (clojure.core/count input__14712) 2)
            (clojure.core/let
             [input__14712_nth_0__
              (clojure.core/nth input__14712 0)
              input__14712_nth_1__
              (clojure.core/nth input__14712 1)]
             (if
              (clojure.core/seq? input__14712_nth_0__)
              (clojure.core/let
               [input__14712_nth_0___l__
                (clojure.core/take 1 input__14712_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14712_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14712_nth_0___r__
                  (clojure.core/drop 1 input__14712_nth_0__)]
                 (clojure.core/let
                  [input__14712_nth_0___l___nth_0__
                   (clojure.core/nth input__14712_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14712_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__16052
                     (clojure.core/namespace
                      input__14712_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14730 X__16052]
                     (clojure.core/let
                      [X__16054
                       (clojure.core/name
                        input__14712_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__16054
                       ("re")
                       (clojure.core/let
                        [x__9586__auto__
                         (def__16042 input__14712_nth_1__ ?__14730)]
                        (if
                         (meander.runtime.zeta/fail? x__9586__auto__)
                         (state__16338)
                         (clojure.core/let
                          [[?__14730] x__9586__auto__]
                          (if
                           (clojure.core/vector? input__14712)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14712)
                             2)
                            (clojure.core/let
                             [input__14712_nth_0__
                              (clojure.core/nth input__14712 0)
                              input__14712_nth_1__
                              (clojure.core/nth input__14712 1)]
                             (if
                              (clojure.core/seq? input__14712_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__14712_nth_0__)
                                2)
                               (clojure.core/let
                                [input__14712_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14712_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?regex input__14712_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__14712_nth_0__]
                                  (clojure.core/let
                                   [?env input__14712_nth_1__]
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
                               (state__16338))
                              (state__16338)))
                            (state__16338))
                           (state__16338)))))
                       (state__16338)))))
                   (state__16338))))
                (state__16338)))
              (state__16338)))
            (state__16338))
           (state__16338))))
        (state__16338
         []
         (clojure.core/letfn
          [(def__16076
            [arg__16099 ?__14731]
            (clojure.core/letfn
             [(state__16583
               []
               (clojure.core/let
                [x__16100 "meander.zeta"]
                (if
                 (clojure.core/= ?__14731 x__16100)
                 [?__14731]
                 (state__16584))))
              (state__16584
               []
               (if
                (clojure.core/map? arg__16099)
                (clojure.core/let
                 [VAL__16101 (.valAt arg__16099 :aliases)]
                 (if
                  (clojure.core/map? VAL__16101)
                  (clojure.core/let
                   [X__16103 (clojure.core/set VAL__16101)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__16103))
                    (clojure.core/loop
                     [search_space__16585 (clojure.core/seq X__16103)]
                     (if
                      (clojure.core/seq search_space__16585)
                      (clojure.core/let
                       [elem__16104
                        (clojure.core/first search_space__16585)
                        result__16586
                        (clojure.core/let
                         [elem__16104_nth_0__
                          (clojure.core/nth elem__16104 0)
                          elem__16104_nth_1__
                          (clojure.core/nth elem__16104 1)]
                         (if
                          (clojure.core/symbol? elem__16104_nth_0__)
                          (clojure.core/let
                           [X__16106
                            (clojure.core/name elem__16104_nth_0__)]
                           (if
                            (clojure.core/= ?__14731 X__16106)
                            (if
                             (clojure.core/symbol? elem__16104_nth_1__)
                             (clojure.core/let
                              [X__16108
                               (clojure.core/name elem__16104_nth_1__)]
                              (clojure.core/case
                               X__16108
                               ("meander.zeta")
                               [?__14731]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16586)
                        (recur (clojure.core/next search_space__16585))
                        result__16586))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16583)))]
          (if
           (clojure.core/vector? input__14712)
           (if
            (clojure.core/= (clojure.core/count input__14712) 2)
            (clojure.core/let
             [input__14712_nth_0__
              (clojure.core/nth input__14712 0)
              input__14712_nth_1__
              (clojure.core/nth input__14712 1)]
             (if
              (clojure.core/seq? input__14712_nth_0__)
              (clojure.core/let
               [input__14712_nth_0___l__
                (clojure.core/take 1 input__14712_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14712_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14712_nth_0___r__
                  (clojure.core/drop 1 input__14712_nth_0__)]
                 (clojure.core/let
                  [input__14712_nth_0___l___nth_0__
                   (clojure.core/nth input__14712_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14712_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__16086
                     (clojure.core/namespace
                      input__14712_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14731 X__16086]
                     (clojure.core/let
                      [X__16088
                       (clojure.core/name
                        input__14712_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__16088
                       ("re")
                       (clojure.core/let
                        [x__9586__auto__
                         (def__16076 input__14712_nth_1__ ?__14731)]
                        (if
                         (meander.runtime.zeta/fail? x__9586__auto__)
                         (state__16339)
                         (clojure.core/let
                          [[?__14731] x__9586__auto__]
                          (if
                           (clojure.core/vector? input__14712)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14712)
                             2)
                            (clojure.core/let
                             [input__14712_nth_0__
                              (clojure.core/nth input__14712 0)
                              input__14712_nth_1__
                              (clojure.core/nth input__14712 1)]
                             (if
                              (clojure.core/seq? input__14712_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__14712_nth_0__)
                                3)
                               (clojure.core/let
                                [input__14712_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14712_nth_0__
                                  1)
                                 input__14712_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__14712_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?regex input__14712_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?capture
                                   input__14712_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__14712_nth_0__]
                                   (clojure.core/let
                                    [?env input__14712_nth_1__]
                                    (try
                                     [{:tag :regex,
                                       :regex ?regex,
                                       :capture
                                       (clojure.core/let
                                        [CATA_RESULT__10889__auto__
                                         (CATA__FN__14795
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
                               (state__16339))
                              (state__16339)))
                            (state__16339))
                           (state__16339)))))
                       (state__16339)))))
                   (state__16339))))
                (state__16339)))
              (state__16339)))
            (state__16339))
           (state__16339))))
        (state__16339
         []
         (clojure.core/letfn
          [(def__16110
            [arg__16133 ?__14732]
            (clojure.core/letfn
             [(state__16588
               []
               (clojure.core/let
                [x__16134 "meander.zeta"]
                (if
                 (clojure.core/= ?__14732 x__16134)
                 [?__14732]
                 (state__16589))))
              (state__16589
               []
               (if
                (clojure.core/map? arg__16133)
                (clojure.core/let
                 [VAL__16135 (.valAt arg__16133 :aliases)]
                 (if
                  (clojure.core/map? VAL__16135)
                  (clojure.core/let
                   [X__16137 (clojure.core/set VAL__16135)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__16137))
                    (clojure.core/loop
                     [search_space__16590 (clojure.core/seq X__16137)]
                     (if
                      (clojure.core/seq search_space__16590)
                      (clojure.core/let
                       [elem__16138
                        (clojure.core/first search_space__16590)
                        result__16591
                        (clojure.core/let
                         [elem__16138_nth_0__
                          (clojure.core/nth elem__16138 0)
                          elem__16138_nth_1__
                          (clojure.core/nth elem__16138 1)]
                         (if
                          (clojure.core/symbol? elem__16138_nth_0__)
                          (clojure.core/let
                           [X__16140
                            (clojure.core/name elem__16138_nth_0__)]
                           (if
                            (clojure.core/= ?__14732 X__16140)
                            (if
                             (clojure.core/symbol? elem__16138_nth_1__)
                             (clojure.core/let
                              [X__16142
                               (clojure.core/name elem__16138_nth_1__)]
                              (clojure.core/case
                               X__16142
                               ("meander.zeta")
                               [?__14732]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16591)
                        (recur (clojure.core/next search_space__16590))
                        result__16591))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16588)))]
          (if
           (clojure.core/vector? input__14712)
           (if
            (clojure.core/= (clojure.core/count input__14712) 2)
            (clojure.core/let
             [input__14712_nth_0__
              (clojure.core/nth input__14712 0)
              input__14712_nth_1__
              (clojure.core/nth input__14712 1)]
             (if
              (clojure.core/seq? input__14712_nth_0__)
              (clojure.core/let
               [input__14712_nth_0___l__
                (clojure.core/take 1 input__14712_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14712_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14712_nth_0___r__
                  (clojure.core/drop 1 input__14712_nth_0__)]
                 (clojure.core/let
                  [input__14712_nth_0___l___nth_0__
                   (clojure.core/nth input__14712_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14712_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__16120
                     (clojure.core/namespace
                      input__14712_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14732 X__16120]
                     (clojure.core/let
                      [X__16122
                       (clojure.core/name
                        input__14712_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__16122
                       ("string")
                       (clojure.core/let
                        [x__9586__auto__
                         (def__16110 input__14712_nth_1__ ?__14732)]
                        (if
                         (meander.runtime.zeta/fail? x__9586__auto__)
                         (state__16340)
                         (clojure.core/let
                          [[?__14732] x__9586__auto__]
                          (if
                           (clojure.core/vector? input__14712)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14712)
                             2)
                            (clojure.core/let
                             [input__14712_nth_0__
                              (clojure.core/nth input__14712 0)
                              input__14712_nth_1__
                              (clojure.core/nth input__14712 1)]
                             (if
                              (clojure.core/seq? input__14712_nth_0__)
                              (clojure.core/let
                               [input__14712_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__14712_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__14712_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__14712_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__14712_nth_0__)]
                                 (clojure.core/let
                                  [?sequence input__14712_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__14712_nth_0__]
                                   (clojure.core/let
                                    [?env input__14712_nth_1__]
                                    (try
                                     [{:tag :string,
                                       :next
                                       (clojure.core/let
                                        [CATA_RESULT__10889__auto__
                                         (CATA__FN__14795
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
                                (state__16340)))
                              (state__16340)))
                            (state__16340))
                           (state__16340)))))
                       (state__16340)))))
                   (state__16340))))
                (state__16340)))
              (state__16340)))
            (state__16340))
           (state__16340))))
        (state__16340
         []
         (clojure.core/letfn
          [(def__16144
            [arg__16167 ?__14733]
            (clojure.core/letfn
             [(state__16593
               []
               (clojure.core/let
                [x__16168 "meander.zeta"]
                (if
                 (clojure.core/= ?__14733 x__16168)
                 [?__14733]
                 (state__16594))))
              (state__16594
               []
               (if
                (clojure.core/map? arg__16167)
                (clojure.core/let
                 [VAL__16169 (.valAt arg__16167 :aliases)]
                 (if
                  (clojure.core/map? VAL__16169)
                  (clojure.core/let
                   [X__16171 (clojure.core/set VAL__16169)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__16171))
                    (clojure.core/loop
                     [search_space__16595 (clojure.core/seq X__16171)]
                     (if
                      (clojure.core/seq search_space__16595)
                      (clojure.core/let
                       [elem__16172
                        (clojure.core/first search_space__16595)
                        result__16596
                        (clojure.core/let
                         [elem__16172_nth_0__
                          (clojure.core/nth elem__16172 0)
                          elem__16172_nth_1__
                          (clojure.core/nth elem__16172 1)]
                         (if
                          (clojure.core/symbol? elem__16172_nth_0__)
                          (clojure.core/let
                           [X__16174
                            (clojure.core/name elem__16172_nth_0__)]
                           (if
                            (clojure.core/= ?__14733 X__16174)
                            (if
                             (clojure.core/symbol? elem__16172_nth_1__)
                             (clojure.core/let
                              [X__16176
                               (clojure.core/name elem__16172_nth_1__)]
                              (clojure.core/case
                               X__16176
                               ("meander.zeta")
                               [?__14733]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16596)
                        (recur (clojure.core/next search_space__16595))
                        result__16596))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16593)))]
          (if
           (clojure.core/vector? input__14712)
           (if
            (clojure.core/= (clojure.core/count input__14712) 2)
            (clojure.core/let
             [input__14712_nth_0__
              (clojure.core/nth input__14712 0)
              input__14712_nth_1__
              (clojure.core/nth input__14712 1)]
             (if
              (clojure.core/seq? input__14712_nth_0__)
              (clojure.core/let
               [input__14712_nth_0___l__
                (clojure.core/take 1 input__14712_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14712_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14712_nth_0___r__
                  (clojure.core/drop 1 input__14712_nth_0__)]
                 (clojure.core/let
                  [input__14712_nth_0___l___nth_0__
                   (clojure.core/nth input__14712_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14712_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__16154
                     (clojure.core/namespace
                      input__14712_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14733 X__16154]
                     (clojure.core/let
                      [X__16156
                       (clojure.core/name
                        input__14712_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__16156
                       ("symbol")
                       (clojure.core/let
                        [x__9586__auto__
                         (def__16144 input__14712_nth_1__ ?__14733)]
                        (if
                         (meander.runtime.zeta/fail? x__9586__auto__)
                         (state__16341)
                         (clojure.core/let
                          [[?__14733] x__9586__auto__]
                          (if
                           (clojure.core/vector? input__14712)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14712)
                             2)
                            (clojure.core/let
                             [input__14712_nth_0__
                              (clojure.core/nth input__14712 0)
                              input__14712_nth_1__
                              (clojure.core/nth input__14712 1)]
                             (if
                              (clojure.core/seq? input__14712_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__14712_nth_0__)
                                2)
                               (clojure.core/let
                                [input__14712_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14712_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?name input__14712_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__14712_nth_0__]
                                  (clojure.core/let
                                   [?env input__14712_nth_1__]
                                   (try
                                    [{:tag :symbol,
                                      :name
                                      (clojure.core/let
                                       [CATA_RESULT__10889__auto__
                                        (CATA__FN__14795 [?name ?env])]
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
                               (state__16341))
                              (state__16341)))
                            (state__16341))
                           (state__16341)))))
                       (state__16341)))))
                   (state__16341))))
                (state__16341)))
              (state__16341)))
            (state__16341))
           (state__16341))))
        (state__16341
         []
         (clojure.core/letfn
          [(def__16178
            [arg__16201 ?__14734]
            (clojure.core/letfn
             [(state__16598
               []
               (clojure.core/let
                [x__16202 "meander.zeta"]
                (if
                 (clojure.core/= ?__14734 x__16202)
                 [?__14734]
                 (state__16599))))
              (state__16599
               []
               (if
                (clojure.core/map? arg__16201)
                (clojure.core/let
                 [VAL__16203 (.valAt arg__16201 :aliases)]
                 (if
                  (clojure.core/map? VAL__16203)
                  (clojure.core/let
                   [X__16205 (clojure.core/set VAL__16203)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__16205))
                    (clojure.core/loop
                     [search_space__16600 (clojure.core/seq X__16205)]
                     (if
                      (clojure.core/seq search_space__16600)
                      (clojure.core/let
                       [elem__16206
                        (clojure.core/first search_space__16600)
                        result__16601
                        (clojure.core/let
                         [elem__16206_nth_0__
                          (clojure.core/nth elem__16206 0)
                          elem__16206_nth_1__
                          (clojure.core/nth elem__16206 1)]
                         (if
                          (clojure.core/symbol? elem__16206_nth_0__)
                          (clojure.core/let
                           [X__16208
                            (clojure.core/name elem__16206_nth_0__)]
                           (if
                            (clojure.core/= ?__14734 X__16208)
                            (if
                             (clojure.core/symbol? elem__16206_nth_1__)
                             (clojure.core/let
                              [X__16210
                               (clojure.core/name elem__16206_nth_1__)]
                              (clojure.core/case
                               X__16210
                               ("meander.zeta")
                               [?__14734]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16601)
                        (recur (clojure.core/next search_space__16600))
                        result__16601))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16598)))]
          (if
           (clojure.core/vector? input__14712)
           (if
            (clojure.core/= (clojure.core/count input__14712) 2)
            (clojure.core/let
             [input__14712_nth_0__
              (clojure.core/nth input__14712 0)
              input__14712_nth_1__
              (clojure.core/nth input__14712 1)]
             (if
              (clojure.core/seq? input__14712_nth_0__)
              (clojure.core/let
               [input__14712_nth_0___l__
                (clojure.core/take 1 input__14712_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14712_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14712_nth_0___r__
                  (clojure.core/drop 1 input__14712_nth_0__)]
                 (clojure.core/let
                  [input__14712_nth_0___l___nth_0__
                   (clojure.core/nth input__14712_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14712_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__16188
                     (clojure.core/namespace
                      input__14712_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14734 X__16188]
                     (clojure.core/let
                      [X__16190
                       (clojure.core/name
                        input__14712_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__16190
                       ("symbol")
                       (clojure.core/let
                        [x__9586__auto__
                         (def__16178 input__14712_nth_1__ ?__14734)]
                        (if
                         (meander.runtime.zeta/fail? x__9586__auto__)
                         (state__16342)
                         (clojure.core/let
                          [[?__14734] x__9586__auto__]
                          (if
                           (clojure.core/vector? input__14712)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14712)
                             2)
                            (clojure.core/let
                             [input__14712_nth_0__
                              (clojure.core/nth input__14712 0)
                              input__14712_nth_1__
                              (clojure.core/nth input__14712 1)]
                             (if
                              (clojure.core/seq? input__14712_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__14712_nth_0__)
                                3)
                               (clojure.core/let
                                [input__14712_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14712_nth_0__
                                  1)
                                 input__14712_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__14712_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?namespace
                                  input__14712_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?name input__14712_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__14712_nth_0__]
                                   (clojure.core/let
                                    [?env input__14712_nth_1__]
                                    (try
                                     [{:tag :symbol,
                                       :name
                                       (clojure.core/let
                                        [CATA_RESULT__10889__auto__
                                         (CATA__FN__14795
                                          [?name ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10889__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10889__auto__
                                          0))),
                                       :namespace
                                       (clojure.core/let
                                        [CATA_RESULT__10889__auto__
                                         (CATA__FN__14795
                                          [?namespace ?env])]
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
                               (state__16342))
                              (state__16342)))
                            (state__16342))
                           (state__16342)))))
                       (state__16342)))))
                   (state__16342))))
                (state__16342)))
              (state__16342)))
            (state__16342))
           (state__16342))))
        (state__16342
         []
         (clojure.core/letfn
          [(def__16212
            [arg__16235 ?__14735]
            (clojure.core/letfn
             [(state__16603
               []
               (clojure.core/let
                [x__16236 "meander.zeta"]
                (if
                 (clojure.core/= ?__14735 x__16236)
                 [?__14735]
                 (state__16604))))
              (state__16604
               []
               (if
                (clojure.core/map? arg__16235)
                (clojure.core/let
                 [VAL__16237 (.valAt arg__16235 :aliases)]
                 (if
                  (clojure.core/map? VAL__16237)
                  (clojure.core/let
                   [X__16239 (clojure.core/set VAL__16237)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__16239))
                    (clojure.core/loop
                     [search_space__16605 (clojure.core/seq X__16239)]
                     (if
                      (clojure.core/seq search_space__16605)
                      (clojure.core/let
                       [elem__16240
                        (clojure.core/first search_space__16605)
                        result__16606
                        (clojure.core/let
                         [elem__16240_nth_0__
                          (clojure.core/nth elem__16240 0)
                          elem__16240_nth_1__
                          (clojure.core/nth elem__16240 1)]
                         (if
                          (clojure.core/symbol? elem__16240_nth_0__)
                          (clojure.core/let
                           [X__16242
                            (clojure.core/name elem__16240_nth_0__)]
                           (if
                            (clojure.core/= ?__14735 X__16242)
                            (if
                             (clojure.core/symbol? elem__16240_nth_1__)
                             (clojure.core/let
                              [X__16244
                               (clojure.core/name elem__16240_nth_1__)]
                              (clojure.core/case
                               X__16244
                               ("meander.zeta")
                               [?__14735]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16606)
                        (recur (clojure.core/next search_space__16605))
                        result__16606))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16603)))]
          (if
           (clojure.core/vector? input__14712)
           (if
            (clojure.core/= (clojure.core/count input__14712) 2)
            (clojure.core/let
             [input__14712_nth_0__
              (clojure.core/nth input__14712 0)
              input__14712_nth_1__
              (clojure.core/nth input__14712 1)]
             (if
              (clojure.core/seq? input__14712_nth_0__)
              (clojure.core/let
               [input__14712_nth_0___l__
                (clojure.core/take 1 input__14712_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14712_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14712_nth_0___r__
                  (clojure.core/drop 1 input__14712_nth_0__)]
                 (clojure.core/let
                  [input__14712_nth_0___l___nth_0__
                   (clojure.core/nth input__14712_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14712_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__16222
                     (clojure.core/namespace
                      input__14712_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14735 X__16222]
                     (clojure.core/let
                      [X__16224
                       (clojure.core/name
                        input__14712_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__16224
                       ("symbol")
                       (clojure.core/let
                        [x__9586__auto__
                         (def__16212 input__14712_nth_1__ ?__14735)]
                        (if
                         (meander.runtime.zeta/fail? x__9586__auto__)
                         (state__16343)
                         (clojure.core/let
                          [[?__14735] x__9586__auto__]
                          (if
                           (clojure.core/vector? input__14712)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14712)
                             2)
                            (clojure.core/let
                             [input__14712_nth_0__
                              (clojure.core/nth input__14712 0)
                              input__14712_nth_1__
                              (clojure.core/nth input__14712 1)]
                             (if
                              (clojure.core/seq? input__14712_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 5)
                                 input__14712_nth_0__)
                                5)
                               (clojure.core/let
                                [input__14712_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14712_nth_0__
                                  1)
                                 input__14712_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__14712_nth_0__
                                  2)
                                 input__14712_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__14712_nth_0__
                                  3)
                                 input__14712_nth_0___nth_4__
                                 (clojure.core/nth
                                  input__14712_nth_0__
                                  4)]
                                (clojure.core/case
                                 input__14712_nth_0___nth_3__
                                 (:meander.zeta/as)
                                 (clojure.core/let
                                  [?namespace
                                   input__14712_nth_0___nth_1__]
                                  (clojure.core/let
                                   [?name input__14712_nth_0___nth_2__]
                                   (clojure.core/let
                                    [?pattern
                                     input__14712_nth_0___nth_4__]
                                    (clojure.core/let
                                     [?form input__14712_nth_0__]
                                     (clojure.core/let
                                      [?env input__14712_nth_1__]
                                      (try
                                       [{:tag :symbol,
                                         :name
                                         (clojure.core/let
                                          [CATA_RESULT__10889__auto__
                                           (CATA__FN__14795
                                            [?name ?env])]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            CATA_RESULT__10889__auto__)
                                           (throw
                                            (meander.runtime.zeta/fail))
                                           (clojure.core/nth
                                            CATA_RESULT__10889__auto__
                                            0))),
                                         :namespace
                                         (clojure.core/let
                                          [CATA_RESULT__10889__auto__
                                           (CATA__FN__14795
                                            [?namespace ?env])]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            CATA_RESULT__10889__auto__)
                                           (throw
                                            (meander.runtime.zeta/fail))
                                           (clojure.core/nth
                                            CATA_RESULT__10889__auto__
                                            0))),
                                         :as-pattern
                                         (clojure.core/let
                                          [CATA_RESULT__10889__auto__
                                           (CATA__FN__14795
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
                                         (throw
                                          e__11829__auto__)))))))))
                                 (state__16343)))
                               (state__16343))
                              (state__16343)))
                            (state__16343))
                           (state__16343)))))
                       (state__16343)))))
                   (state__16343))))
                (state__16343)))
              (state__16343)))
            (state__16343))
           (state__16343))))
        (state__16343
         []
         (if
          (clojure.core/vector? input__14712)
          (if
           (clojure.core/= (clojure.core/count input__14712) 2)
           (clojure.core/let
            [input__14712_nth_0__ (clojure.core/nth input__14712 0)]
            (clojure.core/letfn
             [(state__16608
               []
               (clojure.core/let
                [input__14712_nth_1__
                 (clojure.core/nth input__14712 1)]
                (clojure.core/letfn
                 [(state__16613
                   []
                   (if
                    (clojure.core/seq? input__14712_nth_0__)
                    (clojure.core/let
                     [?sequence input__14712_nth_0__]
                     (clojure.core/let
                      [?env input__14712_nth_1__]
                      (try
                       [{:tag :seq,
                         :next
                         (clojure.core/let
                          [CATA_RESULT__10889__auto__
                           (CATA__FN__14795
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
                         (meander.runtime.zeta/fail? e__11829__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__11829__auto__))))))
                    (state__16614)))
                  (state__16614
                   []
                   (if
                    (clojure.core/map? input__14712_nth_0__)
                    (clojure.core/let
                     [?map input__14712_nth_0__]
                     (clojure.core/let
                      [?env input__14712_nth_1__]
                      (try
                       [{:tag :map,
                         :next
                         (clojure.core/let
                          [CATA_RESULT__10889__auto__
                           (CATA__FN__14795
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
                         (meander.runtime.zeta/fail? e__11829__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__11829__auto__))))))
                    (state__16609)))]
                 (state__16613))))
              (state__16609
               []
               (if
                (clojure.core/symbol? input__14712_nth_0__)
                (clojure.core/let
                 [X__16254
                  (clojure.core/namespace input__14712_nth_0__)]
                 (clojure.core/let
                  [X__16256 (clojure.core/name input__14712_nth_0__)]
                  (if
                   (clojure.core/string? X__16256)
                   (clojure.core/letfn
                    [(state__16615
                      []
                      (clojure.core/let
                       [ret__16257
                        (clojure.core/re-matches #"_.*" X__16256)]
                       (if
                        (clojure.core/some? ret__16257)
                        (clojure.core/let
                         [?name ret__16257]
                         (clojure.core/let
                          [?symbol input__14712_nth_0__]
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
                        (state__16616))))
                     (state__16616
                      []
                      (clojure.core/let
                       [ret__16264
                        (clojure.core/re-matches #".+#" X__16256)]
                       (if
                        (clojure.core/some? ret__16264)
                        (clojure.core/let
                         [?name ret__16264]
                         (clojure.core/let
                          [?symbol input__14712_nth_0__]
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
                        (state__16617))))
                     (state__16617
                      []
                      (clojure.core/let
                       [ret__16271
                        (clojure.core/re-matches #"%.+" X__16256)]
                       (if
                        (clojure.core/some? ret__16271)
                        (clojure.core/let
                         [?name ret__16271]
                         (clojure.core/let
                          [?symbol input__14712_nth_0__]
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
                        (state__16618))))
                     (state__16618
                      []
                      (clojure.core/let
                       [ret__16278
                        (clojure.core/re-matches #"\*.+" X__16256)]
                       (if
                        (clojure.core/some? ret__16278)
                        (clojure.core/let
                         [?name ret__16278]
                         (clojure.core/let
                          [?symbol input__14712_nth_0__]
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
                        (state__16619))))
                     (state__16619
                      []
                      (clojure.core/let
                       [ret__16285
                        (clojure.core/re-matches #"\!.+" X__16256)]
                       (if
                        (clojure.core/some? ret__16285)
                        (clojure.core/let
                         [?name ret__16285]
                         (clojure.core/let
                          [?symbol input__14712_nth_0__]
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
                        (state__16620))))
                     (state__16620
                      []
                      (clojure.core/let
                       [ret__16292
                        (clojure.core/re-matches #"\?.+" X__16256)]
                       (if
                        (clojure.core/some? ret__16292)
                        (clojure.core/let
                         [?name ret__16292]
                         (clojure.core/let
                          [?symbol input__14712_nth_0__]
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
                        (state__16610))))]
                    (state__16615))
                   (state__16610))))
                (state__16610)))
              (state__16610
               []
               (if
                (string? input__14712_nth_0__)
                (clojure.core/let
                 [?x input__14712_nth_0__]
                 (try
                  [{:tag :literal, :type :string, :form ?x}]
                  (catch
                   java.lang.Exception
                   e__11829__auto__
                   (if
                    (meander.runtime.zeta/fail? e__11829__auto__)
                    (meander.runtime.zeta/fail)
                    (throw e__11829__auto__)))))
                (state__16611)))
              (state__16611
               []
               (if
                (char? input__14712_nth_0__)
                (clojure.core/let
                 [?x input__14712_nth_0__]
                 (try
                  [{:tag :literal, :type :char, :form ?x}]
                  (catch
                   java.lang.Exception
                   e__11829__auto__
                   (if
                    (meander.runtime.zeta/fail? e__11829__auto__)
                    (meander.runtime.zeta/fail)
                    (throw e__11829__auto__)))))
                (state__16612)))
              (state__16612
               []
               (clojure.core/let
                [?x input__14712_nth_0__]
                (try
                 [{:tag :literal, :form ?x}]
                 (catch
                  java.lang.Exception
                  e__11829__auto__
                  (if
                   (meander.runtime.zeta/fail? e__11829__auto__)
                   (meander.runtime.zeta/fail)
                   (throw e__11829__auto__))))))]
             (state__16608)))
           (state__16344))
          (state__16344)))
        (state__16344
         []
         (clojure.core/let
          [?x input__14712]
          (try
           [{:tag :mistake, :x ?x}]
           (catch
            java.lang.Exception
            e__11829__auto__
            (if
             (meander.runtime.zeta/fail? e__11829__auto__)
             (meander.runtime.zeta/fail)
             (throw e__11829__auto__))))))]
       (state__16305)))]
    (clojure.core/let
     [x__9586__auto__ (CATA__FN__14795 input__14712)]
     (if
      (meander.runtime.zeta/fail? x__9586__auto__)
      (meander.runtime.zeta/fail)
      (clojure.core/let
       [[CATA_RETURN__14799] x__9586__auto__]
       CATA_RETURN__14799))))]
  (if
   (meander.runtime.zeta/fail? ret__9766__auto__)
   nil
   ret__9766__auto__)))
