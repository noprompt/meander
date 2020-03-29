(ns meander.compiled.parse.zeta (:require [meander.runtime.zeta]))
(clojure.core/defn
 parse
 [input__40881]
 (let*
  [ret__9760__auto__
   (clojure.core/letfn
    [(CATA__FN__40949
      [input__40881]
      (clojure.core/letfn
       [(state__42180
         []
         (if
          (clojure.core/vector? input__40881)
          (if
           (clojure.core/= (clojure.core/count input__40881) 3)
           (clojure.core/let
            [input__40881_nth_0__
             (clojure.core/nth input__40881 0)
             input__40881_nth_1__
             (clojure.core/nth input__40881 1)
             input__40881_nth_2__
             (clojure.core/nth input__40881 2)]
            (clojure.core/case
             input__40881_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__40881_nth_1__)
              (clojure.core/letfn
               [(state__42213
                 []
                 (clojure.core/case
                  input__40881_nth_1__
                  ([])
                  (clojure.core/let
                   [?env input__40881_nth_2__]
                   (try
                    [{:tag :empty}]
                    (catch
                     java.lang.Exception
                     e__11823__auto__
                     (if
                      (meander.runtime.zeta/fail? e__11823__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__11823__auto__)))))
                  (state__42214)))
                (state__42214
                 []
                 (clojure.core/let
                  [n__40956
                   (clojure.core/count input__40881_nth_1__)
                   m__40957
                   (clojure.core/max 0 (clojure.core/- n__40956 2))
                   input__40881_nth_1___l__
                   (clojure.core/subvec
                    input__40881_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__40881_nth_1__)
                     m__40957))
                   input__40881_nth_1___r__
                   (clojure.core/subvec input__40881_nth_1__ m__40957)]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__40881_nth_1___r__)
                    2)
                   (clojure.core/let
                    [!xs (clojure.core/vec input__40881_nth_1___l__)]
                    (if
                     (clojure.core/=
                      (clojure.core/count input__40881_nth_1___r__)
                      2)
                     (clojure.core/let
                      [input__40881_nth_1___r___nth_0__
                       (clojure.core/nth input__40881_nth_1___r__ 0)
                       input__40881_nth_1___r___nth_1__
                       (clojure.core/nth input__40881_nth_1___r__ 1)]
                      (clojure.core/case
                       input__40881_nth_1___r___nth_0__
                       (:meander.zeta/as)
                       (clojure.core/let
                        [?pattern input__40881_nth_1___r___nth_1__]
                        (clojure.core/let
                         [?env input__40881_nth_2__]
                         (try
                          [(clojure.core/let
                            [!xs__counter
                             (meander.runtime.zeta/iterator !xs)]
                            {:tag :as,
                             :pattern
                             (clojure.core/let
                              [CATA_RESULT__10883__auto__
                               (CATA__FN__40949 [?pattern ?env])]
                              (if
                               (meander.runtime.zeta/fail?
                                CATA_RESULT__10883__auto__)
                               (throw (meander.runtime.zeta/fail))
                               (clojure.core/nth
                                CATA_RESULT__10883__auto__
                                0))),
                             :next
                             (clojure.core/let
                              [CATA_RESULT__10883__auto__
                               (CATA__FN__40949
                                ['meander.dev.parse.zeta/parse-sequential
                                 (clojure.core/into
                                  []
                                  (clojure.core/vec
                                   (clojure.core/iterator-seq
                                    !xs__counter)))
                                 ?env])]
                              (if
                               (meander.runtime.zeta/fail?
                                CATA_RESULT__10883__auto__)
                               (throw (meander.runtime.zeta/fail))
                               (clojure.core/nth
                                CATA_RESULT__10883__auto__
                                0)))})]
                          (catch
                           java.lang.Exception
                           e__11823__auto__
                           (if
                            (meander.runtime.zeta/fail?
                             e__11823__auto__)
                            (meander.runtime.zeta/fail)
                            (throw e__11823__auto__))))))
                       (state__42181)))
                     (state__42181)))
                   (state__42181))))]
               (state__42213))
              (state__42181))
             (state__42181)))
           (state__42181))
          (state__42181)))
        (state__42181
         []
         (clojure.core/letfn
          [(def__40962
            [arg__40997 ?ns]
            (clojure.core/letfn
             [(state__42215
               []
               (clojure.core/let
                [x__40998 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__40998)
                 (clojure.core/let [?env arg__40997] [?env ?ns])
                 (state__42216))))
              (state__42216
               []
               (if
                (clojure.core/map? arg__40997)
                (clojure.core/let
                 [VAL__40999 (.valAt arg__40997 :aliases)]
                 (if
                  (clojure.core/map? VAL__40999)
                  (clojure.core/let
                   [X__41001 (clojure.core/set VAL__40999)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__41001))
                    (clojure.core/loop
                     [search_space__42217 (clojure.core/seq X__41001)]
                     (if
                      (clojure.core/seq search_space__42217)
                      (clojure.core/let
                       [elem__41002
                        (clojure.core/first search_space__42217)
                        result__42218
                        (clojure.core/let
                         [elem__41002_nth_0__
                          (clojure.core/nth elem__41002 0)
                          elem__41002_nth_1__
                          (clojure.core/nth elem__41002 1)]
                         (if
                          (clojure.core/symbol? elem__41002_nth_0__)
                          (clojure.core/let
                           [X__41004
                            (clojure.core/name elem__41002_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__41004)
                            (if
                             (clojure.core/symbol? elem__41002_nth_1__)
                             (clojure.core/let
                              [X__41006
                               (clojure.core/name elem__41002_nth_1__)]
                              (clojure.core/case
                               X__41006
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__40997]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__42218)
                        (recur (clojure.core/next search_space__42217))
                        result__42218))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__42215)))]
          (if
           (clojure.core/vector? input__40881)
           (if
            (clojure.core/= (clojure.core/count input__40881) 3)
            (clojure.core/let
             [input__40881_nth_0__
              (clojure.core/nth input__40881 0)
              input__40881_nth_1__
              (clojure.core/nth input__40881 1)
              input__40881_nth_2__
              (clojure.core/nth input__40881 2)]
             (clojure.core/case
              input__40881_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__40881_nth_1__)
               (clojure.core/loop
                [search_space__42220
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__40881_nth_1__)]
                (if
                 (clojure.core/seq search_space__42220)
                 (clojure.core/let
                  [input__40881_nth_1___parts__
                   (clojure.core/first search_space__42220)
                   result__42221
                   (clojure.core/let
                    [input__40881_nth_1___l__
                     (clojure.core/nth input__40881_nth_1___parts__ 0)
                     input__40881_nth_1___r__
                     (clojure.core/nth input__40881_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__40881_nth_1___l__)]
                     (clojure.core/let
                      [input__40881_nth_1___r___l__
                       (clojure.core/subvec
                        input__40881_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__40881_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__40881_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__40881_nth_1___r___r__
                         (clojure.core/subvec
                          input__40881_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__40881_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__40881_nth_1___r___l__
                           0)
                          input__40881_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__40881_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__40881_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__40971
                            (clojure.core/namespace
                             input__40881_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__40971]
                            (clojure.core/let
                             [X__40973
                              (clojure.core/name
                               input__40881_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__40973)
                              (clojure.core/let
                               [ret__40974
                                (clojure.core/re-matches
                                 #"&(\d+)"
                                 X__40973)]
                               (if
                                (clojure.core/some? ret__40974)
                                (if
                                 (clojure.core/vector? ret__40974)
                                 (if
                                  (clojure.core/=
                                   (clojure.core/count ret__40974)
                                   2)
                                  (clojure.core/let
                                   [ret__40974_nth_1__
                                    (clojure.core/nth ret__40974 1)]
                                   (clojure.core/let
                                    [?n ret__40974_nth_1__]
                                    (clojure.core/let
                                     [?pattern
                                      input__40881_nth_1___r___l___nth_1__]
                                     (clojure.core/let
                                      [?rest
                                       input__40881_nth_1___r___r__]
                                      (clojure.core/let
                                       [x__9580__auto__
                                        (def__40962
                                         input__40881_nth_2__
                                         ?ns)]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         x__9580__auto__)
                                        (meander.runtime.zeta/fail)
                                        (clojure.core/let
                                         [[?env ?ns] x__9580__auto__]
                                         (try
                                          [(clojure.core/let
                                            [!init__counter
                                             (meander.runtime.zeta/iterator
                                              !init)]
                                            (clojure.core/let
                                             [CATA_RESULT__10883__auto__
                                              (CATA__FN__40949
                                               ['meander.dev.parse.zeta/make-join
                                                (clojure.core/let
                                                 [CATA_RESULT__10883__auto__
                                                  (CATA__FN__40949
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    (clojure.core/into
                                                     []
                                                     (clojure.core/vec
                                                      (clojure.core/iterator-seq
                                                       !init__counter)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__10883__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__10883__auto__
                                                   0)))
                                                (clojure.core/let
                                                 [CATA_RESULT__10883__auto__
                                                  (CATA__FN__40949
                                                   ['meander.dev.parse.zeta/make-join
                                                    {:tag :slice,
                                                     :size
                                                     (Integer. ?n),
                                                     :pattern
                                                     (clojure.core/let
                                                      [CATA_RESULT__10883__auto__
                                                       (CATA__FN__40949
                                                        [?pattern
                                                         ?env])]
                                                      (if
                                                       (meander.runtime.zeta/fail?
                                                        CATA_RESULT__10883__auto__)
                                                       (throw
                                                        (meander.runtime.zeta/fail))
                                                       (clojure.core/nth
                                                        CATA_RESULT__10883__auto__
                                                        0)))}
                                                    (clojure.core/let
                                                     [CATA_RESULT__10883__auto__
                                                      (CATA__FN__40949
                                                       ['meander.dev.parse.zeta/parse-sequential
                                                        (clojure.core/into
                                                         []
                                                         ?rest)
                                                        ?env])]
                                                     (if
                                                      (meander.runtime.zeta/fail?
                                                       CATA_RESULT__10883__auto__)
                                                      (throw
                                                       (meander.runtime.zeta/fail))
                                                      (clojure.core/nth
                                                       CATA_RESULT__10883__auto__
                                                       0)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__10883__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__10883__auto__
                                                   0)))
                                                ?env])]
                                             (if
                                              (meander.runtime.zeta/fail?
                                               CATA_RESULT__10883__auto__)
                                              (throw
                                               (meander.runtime.zeta/fail))
                                              (clojure.core/nth
                                               CATA_RESULT__10883__auto__
                                               0))))]
                                          (catch
                                           java.lang.Exception
                                           e__11823__auto__
                                           (if
                                            (meander.runtime.zeta/fail?
                                             e__11823__auto__)
                                            (meander.runtime.zeta/fail)
                                            (throw
                                             e__11823__auto__)))))))))))
                                  (meander.runtime.zeta/fail))
                                 (meander.runtime.zeta/fail))
                                (meander.runtime.zeta/fail)))
                              (meander.runtime.zeta/fail)))))
                          (meander.runtime.zeta/fail))))
                       (meander.runtime.zeta/fail)))))]
                  (if
                   (meander.runtime.zeta/fail? result__42221)
                   (recur (clojure.core/next search_space__42220))
                   result__42221))
                 (state__42182)))
               (state__42182))
              (state__42182)))
            (state__42182))
           (state__42182))))
        (state__42182
         []
         (clojure.core/letfn
          [(def__41019
            [arg__41051 ?ns]
            (clojure.core/letfn
             [(state__42223
               []
               (clojure.core/let
                [x__41052 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__41052)
                 (clojure.core/let [?env arg__41051] [?env ?ns])
                 (state__42224))))
              (state__42224
               []
               (if
                (clojure.core/map? arg__41051)
                (clojure.core/let
                 [VAL__41053 (.valAt arg__41051 :aliases)]
                 (if
                  (clojure.core/map? VAL__41053)
                  (clojure.core/let
                   [X__41055 (clojure.core/set VAL__41053)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__41055))
                    (clojure.core/loop
                     [search_space__42225 (clojure.core/seq X__41055)]
                     (if
                      (clojure.core/seq search_space__42225)
                      (clojure.core/let
                       [elem__41056
                        (clojure.core/first search_space__42225)
                        result__42226
                        (clojure.core/let
                         [elem__41056_nth_0__
                          (clojure.core/nth elem__41056 0)
                          elem__41056_nth_1__
                          (clojure.core/nth elem__41056 1)]
                         (if
                          (clojure.core/symbol? elem__41056_nth_0__)
                          (clojure.core/let
                           [X__41058
                            (clojure.core/name elem__41056_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__41058)
                            (if
                             (clojure.core/symbol? elem__41056_nth_1__)
                             (clojure.core/let
                              [X__41060
                               (clojure.core/name elem__41056_nth_1__)]
                              (clojure.core/case
                               X__41060
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__41051]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__42226)
                        (recur (clojure.core/next search_space__42225))
                        result__42226))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__42223)))]
          (if
           (clojure.core/vector? input__40881)
           (if
            (clojure.core/= (clojure.core/count input__40881) 3)
            (clojure.core/let
             [input__40881_nth_0__
              (clojure.core/nth input__40881 0)
              input__40881_nth_1__
              (clojure.core/nth input__40881 1)
              input__40881_nth_2__
              (clojure.core/nth input__40881 2)]
             (clojure.core/case
              input__40881_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__40881_nth_1__)
               (clojure.core/loop
                [search_space__42228
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__40881_nth_1__)]
                (if
                 (clojure.core/seq search_space__42228)
                 (clojure.core/let
                  [input__40881_nth_1___parts__
                   (clojure.core/first search_space__42228)
                   result__42229
                   (clojure.core/let
                    [input__40881_nth_1___l__
                     (clojure.core/nth input__40881_nth_1___parts__ 0)
                     input__40881_nth_1___r__
                     (clojure.core/nth input__40881_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__40881_nth_1___l__)]
                     (clojure.core/let
                      [input__40881_nth_1___r___l__
                       (clojure.core/subvec
                        input__40881_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__40881_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__40881_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__40881_nth_1___r___r__
                         (clojure.core/subvec
                          input__40881_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__40881_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__40881_nth_1___r___l__
                           0)
                          input__40881_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__40881_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__40881_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__41028
                            (clojure.core/namespace
                             input__40881_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__41028]
                            (clojure.core/let
                             [X__41030
                              (clojure.core/name
                               input__40881_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__41030)
                              (if
                               (clojure.core/re-matches
                                #"&.*"
                                X__41030)
                               (clojure.core/let
                                [?pattern
                                 input__40881_nth_1___r___l___nth_1__]
                                (clojure.core/let
                                 [?rest input__40881_nth_1___r___r__]
                                 (clojure.core/let
                                  [x__9580__auto__
                                   (def__41019
                                    input__40881_nth_2__
                                    ?ns)]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    x__9580__auto__)
                                   (meander.runtime.zeta/fail)
                                   (clojure.core/let
                                    [[?env ?ns] x__9580__auto__]
                                    (try
                                     [(clojure.core/let
                                       [!init__counter
                                        (meander.runtime.zeta/iterator
                                         !init)]
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__40949
                                          ['meander.dev.parse.zeta/make-join
                                           (clojure.core/let
                                            [CATA_RESULT__10883__auto__
                                             (CATA__FN__40949
                                              ['meander.dev.parse.zeta/parse-sequential
                                               (clojure.core/into
                                                []
                                                (clojure.core/vec
                                                 (clojure.core/iterator-seq
                                                  !init__counter)))
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__10883__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__10883__auto__
                                              0)))
                                           (clojure.core/let
                                            [CATA_RESULT__10883__auto__
                                             (CATA__FN__40949
                                              ['meander.dev.parse.zeta/make-join
                                               (clojure.core/let
                                                [CATA_RESULT__10883__auto__
                                                 (CATA__FN__40949
                                                  [?pattern ?env])]
                                                (if
                                                 (meander.runtime.zeta/fail?
                                                  CATA_RESULT__10883__auto__)
                                                 (throw
                                                  (meander.runtime.zeta/fail))
                                                 (clojure.core/nth
                                                  CATA_RESULT__10883__auto__
                                                  0)))
                                               (clojure.core/let
                                                [CATA_RESULT__10883__auto__
                                                 (CATA__FN__40949
                                                  ['meander.dev.parse.zeta/parse-sequential
                                                   (clojure.core/into
                                                    []
                                                    ?rest)
                                                   ?env])]
                                                (if
                                                 (meander.runtime.zeta/fail?
                                                  CATA_RESULT__10883__auto__)
                                                 (throw
                                                  (meander.runtime.zeta/fail))
                                                 (clojure.core/nth
                                                  CATA_RESULT__10883__auto__
                                                  0)))
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__10883__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__10883__auto__
                                              0)))
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10883__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10883__auto__
                                          0))))]
                                     (catch
                                      java.lang.Exception
                                      e__11823__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__11823__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__11823__auto__)))))))))
                               (meander.runtime.zeta/fail))
                              (meander.runtime.zeta/fail)))))
                          (meander.runtime.zeta/fail))))
                       (meander.runtime.zeta/fail)))))]
                  (if
                   (meander.runtime.zeta/fail? result__42229)
                   (recur (clojure.core/next search_space__42228))
                   result__42229))
                 (state__42183)))
               (state__42183))
              (state__42183)))
            (state__42183))
           (state__42183))))
        (state__42183
         []
         (if
          (clojure.core/vector? input__40881)
          (clojure.core/letfn
           [(state__42231
             []
             (if
              (clojure.core/= (clojure.core/count input__40881) 3)
              (clojure.core/let
               [input__40881_nth_0__
                (clojure.core/nth input__40881 0)
                input__40881_nth_1__
                (clojure.core/nth input__40881 1)
                input__40881_nth_2__
                (clojure.core/nth input__40881 2)]
               (clojure.core/case
                input__40881_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__40881_nth_1__)
                 (clojure.core/letfn
                  [(state__42234
                    []
                    (clojure.core/let
                     [n__41081
                      (clojure.core/count input__40881_nth_1__)
                      m__41082
                      (clojure.core/max 0 (clojure.core/- n__41081 2))
                      input__40881_nth_1___l__
                      (clojure.core/subvec
                       input__40881_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__40881_nth_1__)
                        m__41082))
                      input__40881_nth_1___r__
                      (clojure.core/subvec
                       input__40881_nth_1__
                       m__41082)]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__40881_nth_1___r__)
                       2)
                      (clojure.core/let
                       [!xs
                        (clojure.core/vec input__40881_nth_1___l__)]
                       (if
                        (clojure.core/=
                         (clojure.core/count input__40881_nth_1___r__)
                         2)
                        (clojure.core/let
                         [input__40881_nth_1___r___nth_0__
                          (clojure.core/nth input__40881_nth_1___r__ 0)
                          input__40881_nth_1___r___nth_1__
                          (clojure.core/nth
                           input__40881_nth_1___r__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__40881_nth_1___r___nth_0__)
                          (clojure.core/let
                           [X__41086
                            (clojure.core/namespace
                             input__40881_nth_1___r___nth_0__)]
                           (clojure.core/let
                            [?ns X__41086]
                            (clojure.core/let
                             [X__41088
                              (clojure.core/name
                               input__40881_nth_1___r___nth_0__)]
                             (if
                              (clojure.core/string? X__41088)
                              (clojure.core/let
                               [ret__41089
                                (clojure.core/re-matches
                                 #"&.*"
                                 X__41088)]
                               (if
                                (clojure.core/some? ret__41089)
                                (clojure.core/let
                                 [?name ret__41089]
                                 (clojure.core/let
                                  [?pattern
                                   input__40881_nth_1___r___nth_1__]
                                  (if
                                   (clojure.core/map?
                                    input__40881_nth_2__)
                                   (clojure.core/let
                                    [VAL__41073
                                     (.valAt
                                      input__40881_nth_2__
                                      :aliases)]
                                    (if
                                     (clojure.core/map? VAL__41073)
                                     (clojure.core/let
                                      [X__41075
                                       (clojure.core/set VAL__41073)]
                                      (if
                                       (clojure.core/<=
                                        1
                                        (clojure.core/count X__41075))
                                       (clojure.core/loop
                                        [search_space__42238
                                         (clojure.core/seq X__41075)]
                                        (if
                                         (clojure.core/seq
                                          search_space__42238)
                                         (clojure.core/let
                                          [elem__41076
                                           (clojure.core/first
                                            search_space__42238)
                                           result__42239
                                           (clojure.core/let
                                            [elem__41076_nth_0__
                                             (clojure.core/nth
                                              elem__41076
                                              0)
                                             elem__41076_nth_1__
                                             (clojure.core/nth
                                              elem__41076
                                              1)]
                                            (if
                                             (clojure.core/symbol?
                                              elem__41076_nth_0__)
                                             (clojure.core/let
                                              [X__41078
                                               (clojure.core/name
                                                elem__41076_nth_0__)]
                                              (if
                                               (clojure.core/=
                                                ?ns
                                                X__41078)
                                               (if
                                                (clojure.core/symbol?
                                                 elem__41076_nth_1__)
                                                (clojure.core/let
                                                 [X__41080
                                                  (clojure.core/name
                                                   elem__41076_nth_1__)]
                                                 (clojure.core/case
                                                  X__41080
                                                  ("meander.zeta")
                                                  (clojure.core/let
                                                   [?env
                                                    input__40881_nth_2__]
                                                   (try
                                                    [(clojure.core/let
                                                      [!xs__counter
                                                       (meander.runtime.zeta/iterator
                                                        !xs)]
                                                      (clojure.core/let
                                                       [CATA_RESULT__10883__auto__
                                                        (CATA__FN__40949
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
                                                         CATA_RESULT__10883__auto__)
                                                        (throw
                                                         (meander.runtime.zeta/fail))
                                                        (clojure.core/nth
                                                         CATA_RESULT__10883__auto__
                                                         0))))]
                                                    (catch
                                                     java.lang.Exception
                                                     e__11823__auto__
                                                     (if
                                                      (meander.runtime.zeta/fail?
                                                       e__11823__auto__)
                                                      (meander.runtime.zeta/fail)
                                                      (throw
                                                       e__11823__auto__)))))
                                                  (meander.runtime.zeta/fail)))
                                                (meander.runtime.zeta/fail))
                                               (meander.runtime.zeta/fail)))
                                             (meander.runtime.zeta/fail)))]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            result__42239)
                                           (recur
                                            (clojure.core/next
                                             search_space__42238))
                                           result__42239))
                                         (state__42235)))
                                       (state__42235)))
                                     (state__42235)))
                                   (state__42235))))
                                (state__42235)))
                              (state__42235)))))
                          (state__42235)))
                        (state__42235)))
                      (state__42235))))
                   (state__42235
                    []
                    (clojure.core/loop
                     [search_space__42241
                      (meander.runtime.zeta/epsilon-partitions
                       2
                       input__40881_nth_1__)]
                     (if
                      (clojure.core/seq search_space__42241)
                      (clojure.core/let
                       [input__40881_nth_1___parts__
                        (clojure.core/first search_space__42241)
                        result__42242
                        (clojure.core/let
                         [input__40881_nth_1___l__
                          (clojure.core/nth
                           input__40881_nth_1___parts__
                           0)
                          input__40881_nth_1___r__
                          (clojure.core/nth
                           input__40881_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs
                           (clojure.core/vec input__40881_nth_1___l__)]
                          (clojure.core/let
                           [input__40881_nth_1___r___l__
                            (clojure.core/subvec
                             input__40881_nth_1___r__
                             0
                             (clojure.core/min
                              (clojure.core/count
                               input__40881_nth_1___r__)
                              1))]
                           (if
                            (clojure.core/=
                             (clojure.core/count
                              input__40881_nth_1___r___l__)
                             1)
                            (clojure.core/let
                             [input__40881_nth_1___r___r__
                              (clojure.core/subvec
                               input__40881_nth_1___r__
                               1)]
                             (if
                              (clojure.core/=
                               input__40881_nth_1___r___l__
                               ['.])
                              (clojure.core/let
                               [?rest input__40881_nth_1___r___r__]
                               (clojure.core/let
                                [?env input__40881_nth_2__]
                                (try
                                 [(clojure.core/let
                                   [!xs__counter
                                    (meander.runtime.zeta/iterator
                                     !xs)]
                                   (clojure.core/let
                                    [CATA_RESULT__10883__auto__
                                     (CATA__FN__40949
                                      ['meander.dev.parse.zeta/make-join
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__40949
                                          ['meander.dev.parse.zeta/parse-sequential
                                           (clojure.core/into
                                            []
                                            (clojure.core/vec
                                             (clojure.core/iterator-seq
                                              !xs__counter)))
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10883__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10883__auto__
                                          0)))
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__40949
                                          ['meander.dev.parse.zeta/parse-sequential
                                           ?rest
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10883__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10883__auto__
                                          0)))
                                       ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__10883__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__10883__auto__
                                      0))))]
                                 (catch
                                  java.lang.Exception
                                  e__11823__auto__
                                  (if
                                   (meander.runtime.zeta/fail?
                                    e__11823__auto__)
                                   (meander.runtime.zeta/fail)
                                   (throw e__11823__auto__))))))
                              (meander.runtime.zeta/fail)))
                            (meander.runtime.zeta/fail)))))]
                       (if
                        (meander.runtime.zeta/fail? result__42242)
                        (recur (clojure.core/next search_space__42241))
                        result__42242))
                      (state__42236))))
                   (state__42236
                    []
                    (clojure.core/let
                     [input__40881_nth_1___l__
                      (clojure.core/subvec
                       input__40881_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__40881_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__40881_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__40881_nth_1___r__
                        (clojure.core/subvec input__40881_nth_1__ 1)]
                       (if
                        (clojure.core/=
                         input__40881_nth_1___l__
                         ['...])
                        (clojure.core/let
                         [?rest input__40881_nth_1___r__]
                         (clojure.core/let
                          [?env input__40881_nth_2__]
                          (try
                           [(clojure.core/let
                             [CATA_RESULT__10883__auto__
                              (CATA__FN__40949
                               ['meander.dev.parse.zeta/parse-sequential
                                ?rest
                                ?env])]
                             (if
                              (meander.runtime.zeta/fail?
                               CATA_RESULT__10883__auto__)
                              (throw (meander.runtime.zeta/fail))
                              (clojure.core/nth
                               CATA_RESULT__10883__auto__
                               0)))]
                           (catch
                            java.lang.Exception
                            e__11823__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__11823__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__11823__auto__))))))
                        (state__42237)))
                      (state__42237))))
                   (state__42237
                    []
                    (clojure.core/loop
                     [search_space__42244
                      (meander.runtime.zeta/epsilon-partitions
                       2
                       input__40881_nth_1__)]
                     (if
                      (clojure.core/seq search_space__42244)
                      (clojure.core/let
                       [input__40881_nth_1___parts__
                        (clojure.core/first search_space__42244)
                        result__42245
                        (clojure.core/let
                         [input__40881_nth_1___l__
                          (clojure.core/nth
                           input__40881_nth_1___parts__
                           0)
                          input__40881_nth_1___r__
                          (clojure.core/nth
                           input__40881_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs []]
                          (clojure.core/let
                           [ret__9744__auto__
                            (meander.runtime.zeta/epsilon-run-star-1
                             input__40881_nth_1___l__
                             [!xs]
                             (clojure.core/fn
                              [[!xs] input__41106]
                              (clojure.core/let
                               [input__41106_nth_0__
                                (clojure.core/nth input__41106 0)]
                               (clojure.core/letfn
                                [(save__41107
                                  []
                                  (meander.runtime.zeta/fail))
                                 (f__42248
                                  []
                                  (clojure.core/let
                                   [!xs
                                    (clojure.core/conj
                                     !xs
                                     input__41106_nth_0__)]
                                   [!xs]))]
                                (if
                                 (clojure.core/symbol?
                                  input__41106_nth_0__)
                                 (clojure.core/let
                                  [X__41109
                                   (clojure.core/namespace
                                    input__41106_nth_0__)]
                                  (clojure.core/case
                                   X__41109
                                   (nil)
                                   (clojure.core/let
                                    [X__41111
                                     (clojure.core/name
                                      input__41106_nth_0__)]
                                    (if
                                     (clojure.core/string? X__41111)
                                     (if
                                      (clojure.core/re-matches
                                       #"\.\.(?:\.|\d+)"
                                       X__41111)
                                      (save__41107)
                                      (f__42248))
                                     (f__42248)))
                                   (f__42248)))
                                 (f__42248)))))
                             (clojure.core/fn
                              [[!xs]]
                              (clojure.core/let
                               [input__40881_nth_1___r___l__
                                (clojure.core/subvec
                                 input__40881_nth_1___r__
                                 0
                                 (clojure.core/min
                                  (clojure.core/count
                                   input__40881_nth_1___r__)
                                  1))]
                               (if
                                (clojure.core/=
                                 (clojure.core/count
                                  input__40881_nth_1___r___l__)
                                 1)
                                (clojure.core/let
                                 [input__40881_nth_1___r___r__
                                  (clojure.core/subvec
                                   input__40881_nth_1___r__
                                   1)]
                                 (if
                                  (clojure.core/=
                                   input__40881_nth_1___r___l__
                                   ['...])
                                  (clojure.core/let
                                   [?rest input__40881_nth_1___r___r__]
                                   (clojure.core/let
                                    [?env input__40881_nth_2__]
                                    (try
                                     [(clojure.core/let
                                       [!xs__counter
                                        (meander.runtime.zeta/iterator
                                         !xs)]
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__40949
                                          ['meander.dev.parse.zeta/make-star
                                           (clojure.core/let
                                            [CATA_RESULT__10883__auto__
                                             (CATA__FN__40949
                                              ['meander.dev.parse.zeta/parse-sequential
                                               (clojure.core/into
                                                []
                                                (clojure.core/vec
                                                 (clojure.core/iterator-seq
                                                  !xs__counter)))
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__10883__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__10883__auto__
                                              0)))
                                           (clojure.core/let
                                            [CATA_RESULT__10883__auto__
                                             (CATA__FN__40949
                                              ['meander.dev.parse.zeta/parse-sequential
                                               ?rest
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__10883__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__10883__auto__
                                              0)))
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10883__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10883__auto__
                                          0))))]
                                     (catch
                                      java.lang.Exception
                                      e__11823__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__11823__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__11823__auto__))))))
                                  (meander.runtime.zeta/fail)))
                                (meander.runtime.zeta/fail)))))]
                           (if
                            (meander.runtime.zeta/fail?
                             ret__9744__auto__)
                            (meander.runtime.zeta/fail)
                            ret__9744__auto__))))]
                       (if
                        (meander.runtime.zeta/fail? result__42245)
                        (recur (clojure.core/next search_space__42244))
                        result__42245))
                      (state__42232))))]
                  (state__42234))
                 (state__42232))
                (state__42232)))
              (state__42232)))
            (state__42232
             []
             (if
              (clojure.core/= (clojure.core/count input__40881) 4)
              (clojure.core/let
               [input__40881_nth_0__
                (clojure.core/nth input__40881 0)
                input__40881_nth_1__
                (clojure.core/nth input__40881 1)
                input__40881_nth_2__
                (clojure.core/nth input__40881 2)]
               (clojure.core/letfn
                [(state__42249
                  []
                  (clojure.core/let
                   [input__40881_nth_3__
                    (clojure.core/nth input__40881 3)]
                   (clojure.core/case
                    input__40881_nth_0__
                    (meander.dev.parse.zeta/make-star)
                    (clojure.core/letfn
                     [(state__42251
                       []
                       (if
                        (clojure.core/map? input__40881_nth_1__)
                        (clojure.core/let
                         [VAL__41115
                          (.valAt input__40881_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__41115
                          (:cat)
                          (clojure.core/let
                           [VAL__41116
                            (.valAt input__40881_nth_1__ :sequence)]
                           (if
                            (clojure.core/vector? VAL__41116)
                            (if
                             (clojure.core/=
                              (clojure.core/count VAL__41116)
                              1)
                             (clojure.core/let
                              [VAL__41116_nth_0__
                               (clojure.core/nth VAL__41116 0)]
                              (if
                               (clojure.core/map? VAL__41116_nth_0__)
                               (clojure.core/let
                                [VAL__41121
                                 (.valAt VAL__41116_nth_0__ :tag)]
                                (clojure.core/case
                                 VAL__41121
                                 (:memory-variable)
                                 (clojure.core/let
                                  [?memory-variable VAL__41116_nth_0__]
                                  (clojure.core/let
                                   [VAL__41117
                                    (.valAt
                                     input__40881_nth_1__
                                     :next)]
                                   (if
                                    (clojure.core/map? VAL__41117)
                                    (clojure.core/let
                                     [VAL__41118
                                      (.valAt VAL__41117 :tag)]
                                     (clojure.core/case
                                      VAL__41118
                                      (:empty)
                                      (clojure.core/let
                                       [?next input__40881_nth_2__]
                                       (clojure.core/let
                                        [?env input__40881_nth_3__]
                                        (try
                                         [(clojure.core/let
                                           [CATA_RESULT__10883__auto__
                                            (CATA__FN__40949
                                             ['meander.dev.parse.zeta/make-join
                                              {:tag :into,
                                               :memory-variable
                                               ?memory-variable}
                                              ?next
                                              ?env])]
                                           (if
                                            (meander.runtime.zeta/fail?
                                             CATA_RESULT__10883__auto__)
                                            (throw
                                             (meander.runtime.zeta/fail))
                                            (clojure.core/nth
                                             CATA_RESULT__10883__auto__
                                             0)))]
                                         (catch
                                          java.lang.Exception
                                          e__11823__auto__
                                          (if
                                           (meander.runtime.zeta/fail?
                                            e__11823__auto__)
                                           (meander.runtime.zeta/fail)
                                           (throw
                                            e__11823__auto__))))))
                                      (state__42252)))
                                    (state__42252))))
                                 (state__42252)))
                               (state__42252)))
                             (state__42252))
                            (state__42252)))
                          (state__42252)))
                        (state__42252)))
                      (state__42252
                       []
                       (clojure.core/let
                        [?pattern input__40881_nth_1__]
                        (clojure.core/let
                         [?next input__40881_nth_2__]
                         (if
                          (clojure.core/map? input__40881_nth_3__)
                          (clojure.core/let
                           [VAL__41124
                            (.valAt input__40881_nth_3__ :context)]
                           (clojure.core/case
                            VAL__41124
                            (:string)
                            (try
                             [{:tag :string-star,
                               :greedy? false,
                               :pattern ?pattern,
                               :next ?next}]
                             (catch
                              java.lang.Exception
                              e__11823__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__11823__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__11823__auto__))))
                            (state__42250)))
                          (state__42250)))))]
                     (state__42251))
                    (state__42250))))
                 (state__42250
                  []
                  (clojure.core/case
                   input__40881_nth_0__
                   (meander.dev.parse.zeta/make-star)
                   (clojure.core/let
                    [?pattern input__40881_nth_1__]
                    (clojure.core/let
                     [?next input__40881_nth_2__]
                     (try
                      [{:tag :star,
                        :greedy? false,
                        :pattern ?pattern,
                        :next ?next}]
                      (catch
                       java.lang.Exception
                       e__11823__auto__
                       (if
                        (meander.runtime.zeta/fail? e__11823__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__11823__auto__))))))
                   (state__42233)))]
                (state__42249)))
              (state__42233)))
            (state__42233
             []
             (if
              (clojure.core/= (clojure.core/count input__40881) 3)
              (clojure.core/let
               [input__40881_nth_0__
                (clojure.core/nth input__40881 0)
                input__40881_nth_1__
                (clojure.core/nth input__40881 1)
                input__40881_nth_2__
                (clojure.core/nth input__40881 2)]
               (clojure.core/case
                input__40881_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__40881_nth_1__)
                 (clojure.core/let
                  [input__40881_nth_1___l__
                   (clojure.core/subvec
                    input__40881_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__40881_nth_1__)
                     1))]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__40881_nth_1___l__)
                    1)
                   (clojure.core/let
                    [input__40881_nth_1___r__
                     (clojure.core/subvec input__40881_nth_1__ 1)]
                    (clojure.core/let
                     [input__40881_nth_1___l___nth_0__
                      (clojure.core/nth input__40881_nth_1___l__ 0)]
                     (if
                      (clojure.core/symbol?
                       input__40881_nth_1___l___nth_0__)
                      (clojure.core/let
                       [X__41132
                        (clojure.core/namespace
                         input__40881_nth_1___l___nth_0__)]
                       (clojure.core/case
                        X__41132
                        (nil)
                        (clojure.core/let
                         [X__41134
                          (clojure.core/name
                           input__40881_nth_1___l___nth_0__)]
                         (if
                          (clojure.core/string? X__41134)
                          (clojure.core/let
                           [ret__41135
                            (clojure.core/re-matches
                             #"\.\.(\d+)"
                             X__41134)]
                           (if
                            (clojure.core/some? ret__41135)
                            (if
                             (clojure.core/vector? ret__41135)
                             (if
                              (clojure.core/=
                               (clojure.core/count ret__41135)
                               2)
                              (clojure.core/let
                               [ret__41135_nth_1__
                                (clojure.core/nth ret__41135 1)]
                               (clojure.core/let
                                [?n ret__41135_nth_1__]
                                (clojure.core/let
                                 [?operator
                                  input__40881_nth_1___l___nth_0__]
                                 (clojure.core/let
                                  [?rest input__40881_nth_1___r__]
                                  (clojure.core/let
                                   [?env input__40881_nth_2__]
                                   (try
                                    [{:tag :syntax-error,
                                      :message
                                      "The n or more operator ..N must be preceeded by at least one pattern"}]
                                    (catch
                                     java.lang.Exception
                                     e__11823__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__11823__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__11823__auto__)))))))))
                              (state__42184))
                             (state__42184))
                            (state__42184)))
                          (state__42184)))
                        (state__42184)))
                      (state__42184))))
                   (state__42184)))
                 (state__42184))
                (state__42184)))
              (state__42184)))]
           (state__42231))
          (state__42184)))
        (state__42184
         []
         (clojure.core/letfn
          [(def__41138
            [arg__41162]
            (clojure.core/letfn
             [(state__42253
               []
               (clojure.core/let
                [x__41163 :string-plus]
                (clojure.core/let
                 [?tag x__41163]
                 (if
                  (clojure.core/map? arg__41162)
                  (clojure.core/let
                   [VAL__41164 (.valAt arg__41162 :context)]
                   (clojure.core/case
                    VAL__41164
                    (:string)
                    (clojure.core/let [?env arg__41162] [?tag ?env])
                    (state__42254)))
                  (state__42254)))))
              (state__42254
               []
               (clojure.core/let
                [x__41165 :plus]
                (clojure.core/let
                 [?tag x__41165]
                 (clojure.core/let [?env arg__41162] [?tag ?env]))))]
             (state__42253)))]
          (if
           (clojure.core/vector? input__40881)
           (if
            (clojure.core/= (clojure.core/count input__40881) 3)
            (clojure.core/let
             [input__40881_nth_0__
              (clojure.core/nth input__40881 0)
              input__40881_nth_1__
              (clojure.core/nth input__40881 1)
              input__40881_nth_2__
              (clojure.core/nth input__40881 2)]
             (clojure.core/case
              input__40881_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__40881_nth_1__)
               (clojure.core/loop
                [search_space__42255
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__40881_nth_1__)]
                (if
                 (clojure.core/seq search_space__42255)
                 (clojure.core/let
                  [input__40881_nth_1___parts__
                   (clojure.core/first search_space__42255)
                   result__42256
                   (clojure.core/let
                    [input__40881_nth_1___l__
                     (clojure.core/nth input__40881_nth_1___parts__ 0)
                     input__40881_nth_1___r__
                     (clojure.core/nth input__40881_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__9744__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__40881_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__41155]
                         (clojure.core/let
                          [input__41155_nth_0__
                           (clojure.core/nth input__41155 0)]
                          (clojure.core/letfn
                           [(save__41156
                             []
                             (meander.runtime.zeta/fail))
                            (f__42259
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__41155_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol? input__41155_nth_0__)
                            (clojure.core/let
                             [X__41158
                              (clojure.core/namespace
                               input__41155_nth_0__)]
                             (clojure.core/case
                              X__41158
                              (nil)
                              (clojure.core/let
                               [X__41160
                                (clojure.core/name
                                 input__41155_nth_0__)]
                               (if
                                (clojure.core/string? X__41160)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__41160)
                                 (save__41156)
                                 (f__42259))
                                (f__42259)))
                              (f__42259)))
                            (f__42259)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__40881_nth_1___r___l__
                           (clojure.core/subvec
                            input__40881_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__40881_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__40881_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__40881_nth_1___r___r__
                             (clojure.core/subvec
                              input__40881_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__40881_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__40881_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__40881_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__41149
                                (clojure.core/namespace
                                 input__40881_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__41149
                                (nil)
                                (clojure.core/let
                                 [X__41151
                                  (clojure.core/name
                                   input__40881_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__41151)
                                  (clojure.core/let
                                   [ret__41152
                                    (clojure.core/re-matches
                                     #"\.\.(\d+)"
                                     X__41151)]
                                   (if
                                    (clojure.core/some? ret__41152)
                                    (if
                                     (clojure.core/vector? ret__41152)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__41152)
                                       2)
                                      (clojure.core/let
                                       [ret__41152_nth_1__
                                        (clojure.core/nth
                                         ret__41152
                                         1)]
                                       (clojure.core/let
                                        [?n ret__41152_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__40881_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__9580__auto__
                                           (def__41138
                                            input__40881_nth_2__)]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            x__9580__auto__)
                                           (meander.runtime.zeta/fail)
                                           (clojure.core/let
                                            [[?tag ?env]
                                             x__9580__auto__]
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
                                                 [CATA_RESULT__10883__auto__
                                                  (CATA__FN__40949
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    (clojure.core/into
                                                     []
                                                     (clojure.core/vec
                                                      (clojure.core/iterator-seq
                                                       !xs__counter)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__10883__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__10883__auto__
                                                   0))),
                                                :next
                                                (clojure.core/let
                                                 [CATA_RESULT__10883__auto__
                                                  (CATA__FN__40949
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    ?rest
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__10883__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__10883__auto__
                                                   0)))})]
                                             (catch
                                              java.lang.Exception
                                              e__11823__auto__
                                              (if
                                               (meander.runtime.zeta/fail?
                                                e__11823__auto__)
                                               (meander.runtime.zeta/fail)
                                               (throw
                                                e__11823__auto__))))))))))
                                      (meander.runtime.zeta/fail))
                                     (meander.runtime.zeta/fail))
                                    (meander.runtime.zeta/fail)))
                                  (meander.runtime.zeta/fail)))
                                (meander.runtime.zeta/fail)))
                              (meander.runtime.zeta/fail))))
                           (meander.runtime.zeta/fail)))))]
                      (if
                       (meander.runtime.zeta/fail? ret__9744__auto__)
                       (meander.runtime.zeta/fail)
                       ret__9744__auto__))))]
                  (if
                   (meander.runtime.zeta/fail? result__42256)
                   (recur (clojure.core/next search_space__42255))
                   result__42256))
                 (state__42185)))
               (state__42185))
              (state__42185)))
            (state__42185))
           (state__42185))))
        (state__42185
         []
         (if
          (clojure.core/vector? input__40881)
          (if
           (clojure.core/= (clojure.core/count input__40881) 3)
           (clojure.core/let
            [input__40881_nth_0__
             (clojure.core/nth input__40881 0)
             input__40881_nth_1__
             (clojure.core/nth input__40881 1)
             input__40881_nth_2__
             (clojure.core/nth input__40881 2)]
            (clojure.core/case
             input__40881_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__40881_nth_1__)
              (clojure.core/let
               [input__40881_nth_1___l__
                (clojure.core/subvec
                 input__40881_nth_1__
                 0
                 (clojure.core/min
                  (clojure.core/count input__40881_nth_1__)
                  1))]
               (if
                (clojure.core/=
                 (clojure.core/count input__40881_nth_1___l__)
                 1)
                (clojure.core/let
                 [input__40881_nth_1___r__
                  (clojure.core/subvec input__40881_nth_1__ 1)]
                 (clojure.core/let
                  [input__40881_nth_1___l___nth_0__
                   (clojure.core/nth input__40881_nth_1___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__40881_nth_1___l___nth_0__)
                   (clojure.core/let
                    [X__41183
                     (clojure.core/namespace
                      input__40881_nth_1___l___nth_0__)]
                    (clojure.core/case
                     X__41183
                     (nil)
                     (clojure.core/let
                      [X__41185
                       (clojure.core/name
                        input__40881_nth_1___l___nth_0__)]
                      (if
                       (clojure.core/string? X__41185)
                       (clojure.core/let
                        [ret__41186
                         (clojure.core/re-matches
                          #"\.\.(\?.+)"
                          X__41185)]
                        (if
                         (clojure.core/some? ret__41186)
                         (if
                          (clojure.core/vector? ret__41186)
                          (if
                           (clojure.core/=
                            (clojure.core/count ret__41186)
                            2)
                           (clojure.core/let
                            [ret__41186_nth_1__
                             (clojure.core/nth ret__41186 1)]
                            (clojure.core/let
                             [?n ret__41186_nth_1__]
                             (clojure.core/let
                              [?operator
                               input__40881_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?rest input__40881_nth_1___r__]
                               (clojure.core/let
                                [?env input__40881_nth_2__]
                                (try
                                 [{:tag :syntax-error,
                                   :message
                                   "The ?n or more operator ..?n must be preceeded by at least one pattern"}]
                                 (catch
                                  java.lang.Exception
                                  e__11823__auto__
                                  (if
                                   (meander.runtime.zeta/fail?
                                    e__11823__auto__)
                                   (meander.runtime.zeta/fail)
                                   (throw e__11823__auto__)))))))))
                           (state__42186))
                          (state__42186))
                         (state__42186)))
                       (state__42186)))
                     (state__42186)))
                   (state__42186))))
                (state__42186)))
              (state__42186))
             (state__42186)))
           (state__42186))
          (state__42186)))
        (state__42186
         []
         (clojure.core/letfn
          [(def__41189
            [arg__41213]
            (clojure.core/letfn
             [(state__42260
               []
               (clojure.core/let
                [x__41214 :string-logical-plus]
                (clojure.core/let
                 [?tag x__41214]
                 (if
                  (clojure.core/map? arg__41213)
                  (clojure.core/let
                   [VAL__41215 (.valAt arg__41213 :context)]
                   (clojure.core/case
                    VAL__41215
                    (:string)
                    (clojure.core/let [?env arg__41213] [?tag ?env])
                    (state__42261)))
                  (state__42261)))))
              (state__42261
               []
               (clojure.core/let
                [x__41216 :logical-plus]
                (clojure.core/let
                 [?tag x__41216]
                 (clojure.core/let [?env arg__41213] [?tag ?env]))))]
             (state__42260)))]
          (if
           (clojure.core/vector? input__40881)
           (if
            (clojure.core/= (clojure.core/count input__40881) 3)
            (clojure.core/let
             [input__40881_nth_0__
              (clojure.core/nth input__40881 0)
              input__40881_nth_1__
              (clojure.core/nth input__40881 1)
              input__40881_nth_2__
              (clojure.core/nth input__40881 2)]
             (clojure.core/case
              input__40881_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__40881_nth_1__)
               (clojure.core/loop
                [search_space__42262
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__40881_nth_1__)]
                (if
                 (clojure.core/seq search_space__42262)
                 (clojure.core/let
                  [input__40881_nth_1___parts__
                   (clojure.core/first search_space__42262)
                   result__42263
                   (clojure.core/let
                    [input__40881_nth_1___l__
                     (clojure.core/nth input__40881_nth_1___parts__ 0)
                     input__40881_nth_1___r__
                     (clojure.core/nth input__40881_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__9744__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__40881_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__41206]
                         (clojure.core/let
                          [input__41206_nth_0__
                           (clojure.core/nth input__41206 0)]
                          (clojure.core/letfn
                           [(save__41207
                             []
                             (meander.runtime.zeta/fail))
                            (f__42266
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__41206_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol? input__41206_nth_0__)
                            (clojure.core/let
                             [X__41209
                              (clojure.core/namespace
                               input__41206_nth_0__)]
                             (clojure.core/case
                              X__41209
                              (nil)
                              (clojure.core/let
                               [X__41211
                                (clojure.core/name
                                 input__41206_nth_0__)]
                               (if
                                (clojure.core/string? X__41211)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__41211)
                                 (save__41207)
                                 (f__42266))
                                (f__42266)))
                              (f__42266)))
                            (f__42266)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__40881_nth_1___r___l__
                           (clojure.core/subvec
                            input__40881_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__40881_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__40881_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__40881_nth_1___r___r__
                             (clojure.core/subvec
                              input__40881_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__40881_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__40881_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__40881_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__41200
                                (clojure.core/namespace
                                 input__40881_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__41200
                                (nil)
                                (clojure.core/let
                                 [X__41202
                                  (clojure.core/name
                                   input__40881_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__41202)
                                  (clojure.core/let
                                   [ret__41203
                                    (clojure.core/re-matches
                                     #"\.\.(\?.+)"
                                     X__41202)]
                                   (if
                                    (clojure.core/some? ret__41203)
                                    (if
                                     (clojure.core/vector? ret__41203)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__41203)
                                       2)
                                      (clojure.core/let
                                       [ret__41203_nth_1__
                                        (clojure.core/nth
                                         ret__41203
                                         1)]
                                       (clojure.core/let
                                        [?n ret__41203_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__40881_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__9580__auto__
                                           (def__41189
                                            input__40881_nth_2__)]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            x__9580__auto__)
                                           (meander.runtime.zeta/fail)
                                           (clojure.core/let
                                            [[?tag ?env]
                                             x__9580__auto__]
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
                                                 [CATA_RESULT__10883__auto__
                                                  (CATA__FN__40949
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    (clojure.core/into
                                                     []
                                                     (clojure.core/vec
                                                      (clojure.core/iterator-seq
                                                       !xs__counter)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__10883__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__10883__auto__
                                                   0))),
                                                :next
                                                (clojure.core/let
                                                 [CATA_RESULT__10883__auto__
                                                  (CATA__FN__40949
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    ?rest
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__10883__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__10883__auto__
                                                   0)))})]
                                             (catch
                                              java.lang.Exception
                                              e__11823__auto__
                                              (if
                                               (meander.runtime.zeta/fail?
                                                e__11823__auto__)
                                               (meander.runtime.zeta/fail)
                                               (throw
                                                e__11823__auto__))))))))))
                                      (meander.runtime.zeta/fail))
                                     (meander.runtime.zeta/fail))
                                    (meander.runtime.zeta/fail)))
                                  (meander.runtime.zeta/fail)))
                                (meander.runtime.zeta/fail)))
                              (meander.runtime.zeta/fail))))
                           (meander.runtime.zeta/fail)))))]
                      (if
                       (meander.runtime.zeta/fail? ret__9744__auto__)
                       (meander.runtime.zeta/fail)
                       ret__9744__auto__))))]
                  (if
                   (meander.runtime.zeta/fail? result__42263)
                   (recur (clojure.core/next search_space__42262))
                   result__42263))
                 (state__42187)))
               (state__42187))
              (state__42187)))
            (state__42187))
           (state__42187))))
        (state__42187
         []
         (if
          (clojure.core/vector? input__40881)
          (if
           (clojure.core/= (clojure.core/count input__40881) 3)
           (clojure.core/let
            [input__40881_nth_0__
             (clojure.core/nth input__40881 0)
             input__40881_nth_1__
             (clojure.core/nth input__40881 1)
             input__40881_nth_2__
             (clojure.core/nth input__40881 2)]
            (clojure.core/case
             input__40881_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__40881_nth_1__)
              (clojure.core/let
               [input__40881_nth_1___l__
                (clojure.core/subvec
                 input__40881_nth_1__
                 0
                 (clojure.core/min
                  (clojure.core/count input__40881_nth_1__)
                  1))]
               (if
                (clojure.core/=
                 (clojure.core/count input__40881_nth_1___l__)
                 1)
                (clojure.core/let
                 [input__40881_nth_1___r__
                  (clojure.core/subvec input__40881_nth_1__ 1)]
                 (clojure.core/let
                  [input__40881_nth_1___l___nth_0__
                   (clojure.core/nth input__40881_nth_1___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__40881_nth_1___l___nth_0__)
                   (clojure.core/let
                    [X__41234
                     (clojure.core/namespace
                      input__40881_nth_1___l___nth_0__)]
                    (clojure.core/case
                     X__41234
                     (nil)
                     (clojure.core/let
                      [X__41236
                       (clojure.core/name
                        input__40881_nth_1___l___nth_0__)]
                      (if
                       (clojure.core/string? X__41236)
                       (clojure.core/let
                        [ret__41237
                         (clojure.core/re-matches
                          #"\.\.(!.+)"
                          X__41236)]
                        (if
                         (clojure.core/some? ret__41237)
                         (if
                          (clojure.core/vector? ret__41237)
                          (if
                           (clojure.core/=
                            (clojure.core/count ret__41237)
                            2)
                           (clojure.core/let
                            [ret__41237_nth_1__
                             (clojure.core/nth ret__41237 1)]
                            (clojure.core/let
                             [?n ret__41237_nth_1__]
                             (clojure.core/let
                              [?operator
                               input__40881_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?rest input__40881_nth_1___r__]
                               (clojure.core/let
                                [?env input__40881_nth_2__]
                                (try
                                 [{:tag :syntax-error,
                                   :message
                                   "The operator ..!n must be preceeded by at least one pattern"}]
                                 (catch
                                  java.lang.Exception
                                  e__11823__auto__
                                  (if
                                   (meander.runtime.zeta/fail?
                                    e__11823__auto__)
                                   (meander.runtime.zeta/fail)
                                   (throw e__11823__auto__)))))))))
                           (state__42188))
                          (state__42188))
                         (state__42188)))
                       (state__42188)))
                     (state__42188)))
                   (state__42188))))
                (state__42188)))
              (state__42188))
             (state__42188)))
           (state__42188))
          (state__42188)))
        (state__42188
         []
         (clojure.core/letfn
          [(def__41240
            [arg__41264]
            (clojure.core/letfn
             [(state__42267
               []
               (clojure.core/let
                [x__41265 :string-memory-plus]
                (clojure.core/let
                 [?tag x__41265]
                 (if
                  (clojure.core/map? arg__41264)
                  (clojure.core/let
                   [VAL__41266 (.valAt arg__41264 :context)]
                   (clojure.core/case
                    VAL__41266
                    (:string)
                    (clojure.core/let [?env arg__41264] [?tag ?env])
                    (state__42268)))
                  (state__42268)))))
              (state__42268
               []
               (clojure.core/let
                [x__41267 :memory-plus]
                (clojure.core/let
                 [?tag x__41267]
                 (clojure.core/let [?env arg__41264] [?tag ?env]))))]
             (state__42267)))]
          (if
           (clojure.core/vector? input__40881)
           (if
            (clojure.core/= (clojure.core/count input__40881) 3)
            (clojure.core/let
             [input__40881_nth_0__
              (clojure.core/nth input__40881 0)
              input__40881_nth_1__
              (clojure.core/nth input__40881 1)
              input__40881_nth_2__
              (clojure.core/nth input__40881 2)]
             (clojure.core/case
              input__40881_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__40881_nth_1__)
               (clojure.core/loop
                [search_space__42269
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__40881_nth_1__)]
                (if
                 (clojure.core/seq search_space__42269)
                 (clojure.core/let
                  [input__40881_nth_1___parts__
                   (clojure.core/first search_space__42269)
                   result__42270
                   (clojure.core/let
                    [input__40881_nth_1___l__
                     (clojure.core/nth input__40881_nth_1___parts__ 0)
                     input__40881_nth_1___r__
                     (clojure.core/nth input__40881_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__9744__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__40881_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__41257]
                         (clojure.core/let
                          [input__41257_nth_0__
                           (clojure.core/nth input__41257 0)]
                          (clojure.core/letfn
                           [(save__41258
                             []
                             (meander.runtime.zeta/fail))
                            (f__42273
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__41257_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol? input__41257_nth_0__)
                            (clojure.core/let
                             [X__41260
                              (clojure.core/namespace
                               input__41257_nth_0__)]
                             (clojure.core/case
                              X__41260
                              (nil)
                              (clojure.core/let
                               [X__41262
                                (clojure.core/name
                                 input__41257_nth_0__)]
                               (if
                                (clojure.core/string? X__41262)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__41262)
                                 (save__41258)
                                 (f__42273))
                                (f__42273)))
                              (f__42273)))
                            (f__42273)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__40881_nth_1___r___l__
                           (clojure.core/subvec
                            input__40881_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__40881_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__40881_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__40881_nth_1___r___r__
                             (clojure.core/subvec
                              input__40881_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__40881_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__40881_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__40881_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__41251
                                (clojure.core/namespace
                                 input__40881_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__41251
                                (nil)
                                (clojure.core/let
                                 [X__41253
                                  (clojure.core/name
                                   input__40881_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__41253)
                                  (clojure.core/let
                                   [ret__41254
                                    (clojure.core/re-matches
                                     #"\.\.(\!.+)"
                                     X__41253)]
                                   (if
                                    (clojure.core/some? ret__41254)
                                    (if
                                     (clojure.core/vector? ret__41254)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__41254)
                                       2)
                                      (clojure.core/let
                                       [ret__41254_nth_1__
                                        (clojure.core/nth
                                         ret__41254
                                         1)]
                                       (clojure.core/let
                                        [?n ret__41254_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__40881_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__9580__auto__
                                           (def__41240
                                            input__40881_nth_2__)]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            x__9580__auto__)
                                           (meander.runtime.zeta/fail)
                                           (clojure.core/let
                                            [[?tag ?env]
                                             x__9580__auto__]
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
                                                 [CATA_RESULT__10883__auto__
                                                  (CATA__FN__40949
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    (clojure.core/into
                                                     []
                                                     (clojure.core/vec
                                                      (clojure.core/iterator-seq
                                                       !xs__counter)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__10883__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__10883__auto__
                                                   0))),
                                                :next
                                                (clojure.core/let
                                                 [CATA_RESULT__10883__auto__
                                                  (CATA__FN__40949
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    ?rest
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__10883__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__10883__auto__
                                                   0)))})]
                                             (catch
                                              java.lang.Exception
                                              e__11823__auto__
                                              (if
                                               (meander.runtime.zeta/fail?
                                                e__11823__auto__)
                                               (meander.runtime.zeta/fail)
                                               (throw
                                                e__11823__auto__))))))))))
                                      (meander.runtime.zeta/fail))
                                     (meander.runtime.zeta/fail))
                                    (meander.runtime.zeta/fail)))
                                  (meander.runtime.zeta/fail)))
                                (meander.runtime.zeta/fail)))
                              (meander.runtime.zeta/fail))))
                           (meander.runtime.zeta/fail)))))]
                      (if
                       (meander.runtime.zeta/fail? ret__9744__auto__)
                       (meander.runtime.zeta/fail)
                       ret__9744__auto__))))]
                  (if
                   (meander.runtime.zeta/fail? result__42270)
                   (recur (clojure.core/next search_space__42269))
                   result__42270))
                 (state__42189)))
               (state__42189))
              (state__42189)))
            (state__42189))
           (state__42189))))
        (state__42189
         []
         (if
          (clojure.core/vector? input__40881)
          (clojure.core/letfn
           [(state__42274
             []
             (if
              (clojure.core/= (clojure.core/count input__40881) 3)
              (clojure.core/let
               [input__40881_nth_0__
                (clojure.core/nth input__40881 0)
                input__40881_nth_1__
                (clojure.core/nth input__40881 1)
                input__40881_nth_2__
                (clojure.core/nth input__40881 2)]
               (clojure.core/case
                input__40881_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__40881_nth_1__)
                 (clojure.core/let
                  [!xs (clojure.core/vec input__40881_nth_1__)]
                  (clojure.core/let
                   [?env input__40881_nth_2__]
                   (try
                    [(clojure.core/let
                      [!xs__counter
                       (meander.runtime.zeta/iterator !xs)]
                      (clojure.core/let
                       [CATA_RESULT__10883__auto__
                        (CATA__FN__40949
                         ['meander.dev.parse.zeta/make-cat
                          (clojure.core/into
                           []
                           (clojure.core/loop
                            [return__40950 (clojure.core/transient [])]
                            (if
                             (clojure.core/and (.hasNext !xs__counter))
                             (recur
                              (clojure.core/conj!
                               return__40950
                               (clojure.core/let
                                [CATA_RESULT__10883__auto__
                                 (CATA__FN__40949
                                  [(if
                                    (.hasNext !xs__counter)
                                    (.next !xs__counter))
                                   ?env])]
                                (if
                                 (meander.runtime.zeta/fail?
                                  CATA_RESULT__10883__auto__)
                                 (throw (meander.runtime.zeta/fail))
                                 (clojure.core/nth
                                  CATA_RESULT__10883__auto__
                                  0)))))
                             (clojure.core/persistent!
                              return__40950))))
                          {:tag :empty}
                          ?env])]
                       (if
                        (meander.runtime.zeta/fail?
                         CATA_RESULT__10883__auto__)
                        (throw (meander.runtime.zeta/fail))
                        (clojure.core/nth
                         CATA_RESULT__10883__auto__
                         0))))]
                    (catch
                     java.lang.Exception
                     e__11823__auto__
                     (if
                      (meander.runtime.zeta/fail? e__11823__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__11823__auto__))))))
                 (state__42275))
                (state__42275)))
              (state__42275)))
            (state__42275
             []
             (if
              (clojure.core/= (clojure.core/count input__40881) 4)
              (clojure.core/let
               [input__40881_nth_0__
                (clojure.core/nth input__40881 0)
                input__40881_nth_1__
                (clojure.core/nth input__40881 1)
                input__40881_nth_2__
                (clojure.core/nth input__40881 2)]
               (clojure.core/letfn
                [(state__42277
                  []
                  (clojure.core/let
                   [input__40881_nth_3__
                    (clojure.core/nth input__40881 3)]
                   (clojure.core/case
                    input__40881_nth_0__
                    (meander.dev.parse.zeta/make-cat)
                    (if
                     (clojure.core/vector? input__40881_nth_1__)
                     (clojure.core/letfn
                      [(state__42284
                        []
                        (clojure.core/case
                         input__40881_nth_1__
                         ([])
                         (clojure.core/let
                          [?next input__40881_nth_2__]
                          (clojure.core/let
                           [?env input__40881_nth_3__]
                           (try
                            [?next]
                            (catch
                             java.lang.Exception
                             e__11823__auto__
                             (if
                              (meander.runtime.zeta/fail?
                               e__11823__auto__)
                              (meander.runtime.zeta/fail)
                              (throw e__11823__auto__))))))
                         (state__42285)))
                       (state__42285
                        []
                        (clojure.core/loop
                         [search_space__42286
                          (meander.runtime.zeta/epsilon-partitions
                           2
                           input__40881_nth_1__)]
                         (if
                          (clojure.core/seq search_space__42286)
                          (clojure.core/let
                           [input__40881_nth_1___parts__
                            (clojure.core/first search_space__42286)
                            result__42287
                            (clojure.core/let
                             [input__40881_nth_1___l__
                              (clojure.core/nth
                               input__40881_nth_1___parts__
                               0)
                              input__40881_nth_1___r__
                              (clojure.core/nth
                               input__40881_nth_1___parts__
                               1)]
                             (clojure.core/letfn
                              [(state__42289
                                []
                                (clojure.core/let
                                 [!xs []]
                                 (clojure.core/let
                                  [ret__9744__auto__
                                   (meander.runtime.zeta/epsilon-run-star-1
                                    input__40881_nth_1___l__
                                    [!xs]
                                    (clojure.core/fn
                                     [[!xs] input__41293]
                                     (clojure.core/let
                                      [input__41293_nth_0__
                                       (clojure.core/nth
                                        input__41293
                                        0)]
                                      (clojure.core/letfn
                                       [(save__41294
                                         []
                                         (meander.runtime.zeta/fail))
                                        (f__42292
                                         []
                                         (clojure.core/let
                                          [!xs
                                           (clojure.core/conj
                                            !xs
                                            input__41293_nth_0__)]
                                          [!xs]))]
                                       (if
                                        (clojure.core/map?
                                         input__41293_nth_0__)
                                        (clojure.core/let
                                         [VAL__41295
                                          (.valAt
                                           input__41293_nth_0__
                                           :tag)]
                                         (clojure.core/case
                                          VAL__41295
                                          (:group)
                                          (save__41294)
                                          (f__42292)))
                                        (f__42292)))))
                                    (clojure.core/fn
                                     [[!xs]]
                                     (clojure.core/let
                                      [input__40881_nth_1___r___l__
                                       (clojure.core/subvec
                                        input__40881_nth_1___r__
                                        0
                                        (clojure.core/min
                                         (clojure.core/count
                                          input__40881_nth_1___r__)
                                         1))]
                                      (if
                                       (clojure.core/=
                                        (clojure.core/count
                                         input__40881_nth_1___r___l__)
                                        1)
                                       (clojure.core/let
                                        [input__40881_nth_1___r___r__
                                         (clojure.core/subvec
                                          input__40881_nth_1___r__
                                          1)]
                                        (clojure.core/let
                                         [input__40881_nth_1___r___l___nth_0__
                                          (clojure.core/nth
                                           input__40881_nth_1___r___l__
                                           0)]
                                         (if
                                          (clojure.core/map?
                                           input__40881_nth_1___r___l___nth_0__)
                                          (clojure.core/let
                                           [VAL__41292
                                            (.valAt
                                             input__40881_nth_1___r___l___nth_0__
                                             :tag)]
                                           (clojure.core/case
                                            VAL__41292
                                            (:group)
                                            (clojure.core/let
                                             [?group
                                              input__40881_nth_1___r___l___nth_0__]
                                             (clojure.core/let
                                              [?rest
                                               input__40881_nth_1___r___r__]
                                              (clojure.core/let
                                               [?next
                                                input__40881_nth_2__]
                                               (clojure.core/let
                                                [?env
                                                 input__40881_nth_3__]
                                                (try
                                                 [(clojure.core/let
                                                   [!xs__counter
                                                    (meander.runtime.zeta/iterator
                                                     !xs)]
                                                   (clojure.core/let
                                                    [CATA_RESULT__10883__auto__
                                                     (CATA__FN__40949
                                                      ['meander.dev.parse.zeta/make-join
                                                       (clojure.core/let
                                                        [CATA_RESULT__10883__auto__
                                                         (CATA__FN__40949
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
                                                          CATA_RESULT__10883__auto__)
                                                         (throw
                                                          (meander.runtime.zeta/fail))
                                                         (clojure.core/nth
                                                          CATA_RESULT__10883__auto__
                                                          0)))
                                                       (clojure.core/let
                                                        [CATA_RESULT__10883__auto__
                                                         (CATA__FN__40949
                                                          ['meander.dev.parse.zeta/make-join
                                                           ?group
                                                           (clojure.core/let
                                                            [CATA_RESULT__10883__auto__
                                                             (CATA__FN__40949
                                                              ['meander.dev.parse.zeta/make-cat
                                                               ?rest
                                                               ?next
                                                               ?env])]
                                                            (if
                                                             (meander.runtime.zeta/fail?
                                                              CATA_RESULT__10883__auto__)
                                                             (throw
                                                              (meander.runtime.zeta/fail))
                                                             (clojure.core/nth
                                                              CATA_RESULT__10883__auto__
                                                              0)))
                                                           ?env])]
                                                        (if
                                                         (meander.runtime.zeta/fail?
                                                          CATA_RESULT__10883__auto__)
                                                         (throw
                                                          (meander.runtime.zeta/fail))
                                                         (clojure.core/nth
                                                          CATA_RESULT__10883__auto__
                                                          0)))
                                                       ?env])]
                                                    (if
                                                     (meander.runtime.zeta/fail?
                                                      CATA_RESULT__10883__auto__)
                                                     (throw
                                                      (meander.runtime.zeta/fail))
                                                     (clojure.core/nth
                                                      CATA_RESULT__10883__auto__
                                                      0))))]
                                                 (catch
                                                  java.lang.Exception
                                                  e__11823__auto__
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    e__11823__auto__)
                                                   (meander.runtime.zeta/fail)
                                                   (throw
                                                    e__11823__auto__))))))))
                                            (state__42290)))
                                          (state__42290))))
                                       (state__42290)))))]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    ret__9744__auto__)
                                   (state__42290)
                                   ret__9744__auto__))))
                               (state__42290
                                []
                                (clojure.core/let
                                 [!xs []]
                                 (clojure.core/let
                                  [ret__9744__auto__
                                   (meander.runtime.zeta/epsilon-run-star-1
                                    input__40881_nth_1___l__
                                    [!xs]
                                    (clojure.core/fn
                                     [[!xs] input__41304]
                                     (clojure.core/let
                                      [input__41304_nth_0__
                                       (clojure.core/nth
                                        input__41304
                                        0)]
                                      (clojure.core/letfn
                                       [(save__41305
                                         []
                                         (meander.runtime.zeta/fail))
                                        (f__42294
                                         []
                                         (clojure.core/let
                                          [!xs
                                           (clojure.core/conj
                                            !xs
                                            input__41304_nth_0__)]
                                          [!xs]))]
                                       (if
                                        (clojure.core/map?
                                         input__41304_nth_0__)
                                        (clojure.core/let
                                         [VAL__41306
                                          (.valAt
                                           input__41304_nth_0__
                                           :tag)]
                                         (clojure.core/case
                                          VAL__41306
                                          (:star)
                                          (save__41305)
                                          (f__42294)))
                                        (f__42294)))))
                                    (clojure.core/fn
                                     [[!xs]]
                                     (clojure.core/let
                                      [input__40881_nth_1___r___l__
                                       (clojure.core/subvec
                                        input__40881_nth_1___r__
                                        0
                                        (clojure.core/min
                                         (clojure.core/count
                                          input__40881_nth_1___r__)
                                         1))]
                                      (if
                                       (clojure.core/=
                                        (clojure.core/count
                                         input__40881_nth_1___r___l__)
                                        1)
                                       (clojure.core/let
                                        [input__40881_nth_1___r___r__
                                         (clojure.core/subvec
                                          input__40881_nth_1___r__
                                          1)]
                                        (clojure.core/let
                                         [input__40881_nth_1___r___l___nth_0__
                                          (clojure.core/nth
                                           input__40881_nth_1___r___l__
                                           0)]
                                         (if
                                          (clojure.core/map?
                                           input__40881_nth_1___r___l___nth_0__)
                                          (clojure.core/let
                                           [VAL__41303
                                            (.valAt
                                             input__40881_nth_1___r___l___nth_0__
                                             :tag)]
                                           (clojure.core/case
                                            VAL__41303
                                            (:star)
                                            (clojure.core/let
                                             [?group
                                              input__40881_nth_1___r___l___nth_0__]
                                             (clojure.core/let
                                              [?rest
                                               input__40881_nth_1___r___r__]
                                              (clojure.core/let
                                               [?next
                                                input__40881_nth_2__]
                                               (clojure.core/let
                                                [?env
                                                 input__40881_nth_3__]
                                                (try
                                                 [(clojure.core/let
                                                   [!xs__counter
                                                    (meander.runtime.zeta/iterator
                                                     !xs)]
                                                   (clojure.core/let
                                                    [CATA_RESULT__10883__auto__
                                                     (CATA__FN__40949
                                                      ['meander.dev.parse.zeta/make-join
                                                       (clojure.core/let
                                                        [CATA_RESULT__10883__auto__
                                                         (CATA__FN__40949
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
                                                          CATA_RESULT__10883__auto__)
                                                         (throw
                                                          (meander.runtime.zeta/fail))
                                                         (clojure.core/nth
                                                          CATA_RESULT__10883__auto__
                                                          0)))
                                                       (clojure.core/let
                                                        [CATA_RESULT__10883__auto__
                                                         (CATA__FN__40949
                                                          ['meander.dev.parse.zeta/make-join
                                                           ?group
                                                           (clojure.core/let
                                                            [CATA_RESULT__10883__auto__
                                                             (CATA__FN__40949
                                                              ['meander.dev.parse.zeta/make-cat
                                                               ?rest
                                                               ?next
                                                               ?env])]
                                                            (if
                                                             (meander.runtime.zeta/fail?
                                                              CATA_RESULT__10883__auto__)
                                                             (throw
                                                              (meander.runtime.zeta/fail))
                                                             (clojure.core/nth
                                                              CATA_RESULT__10883__auto__
                                                              0)))
                                                           ?env])]
                                                        (if
                                                         (meander.runtime.zeta/fail?
                                                          CATA_RESULT__10883__auto__)
                                                         (throw
                                                          (meander.runtime.zeta/fail))
                                                         (clojure.core/nth
                                                          CATA_RESULT__10883__auto__
                                                          0)))
                                                       ?env])]
                                                    (if
                                                     (meander.runtime.zeta/fail?
                                                      CATA_RESULT__10883__auto__)
                                                     (throw
                                                      (meander.runtime.zeta/fail))
                                                     (clojure.core/nth
                                                      CATA_RESULT__10883__auto__
                                                      0))))]
                                                 (catch
                                                  java.lang.Exception
                                                  e__11823__auto__
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    e__11823__auto__)
                                                   (meander.runtime.zeta/fail)
                                                   (throw
                                                    e__11823__auto__))))))))
                                            (meander.runtime.zeta/fail)))
                                          (meander.runtime.zeta/fail))))
                                       (meander.runtime.zeta/fail)))))]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    ret__9744__auto__)
                                   (meander.runtime.zeta/fail)
                                   ret__9744__auto__))))]
                              (state__42289)))]
                           (if
                            (meander.runtime.zeta/fail? result__42287)
                            (recur
                             (clojure.core/next search_space__42286))
                            result__42287))
                          (state__42278))))]
                      (state__42284))
                     (state__42278))
                    (state__42278))))
                 (state__42278
                  []
                  (clojure.core/case
                   input__40881_nth_0__
                   (meander.dev.parse.zeta/make-cat)
                   (if
                    (clojure.core/vector? input__40881_nth_1__)
                    (clojure.core/let
                     [input__40881_nth_1___l__
                      (clojure.core/subvec
                       input__40881_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__40881_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__40881_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__40881_nth_1___r__
                        (clojure.core/subvec input__40881_nth_1__ 1)]
                       (clojure.core/let
                        [input__40881_nth_1___l___nth_0__
                         (clojure.core/nth input__40881_nth_1___l__ 0)]
                        (if
                         (clojure.core/map?
                          input__40881_nth_1___l___nth_0__)
                         (clojure.core/let
                          [VAL__41315
                           (.valAt
                            input__40881_nth_1___l___nth_0__
                            :tag)]
                          (clojure.core/case
                           VAL__41315
                           (:literal)
                           (clojure.core/let
                            [VAL__41316
                             (.valAt
                              input__40881_nth_1___l___nth_0__
                              :type)]
                            (if
                             (clojure.core/let
                              [x__8640__auto__ VAL__41316]
                              (clojure.core/case
                               x__8640__auto__
                               (:string :char)
                               true
                               false))
                             (clojure.core/let
                              [VAL__41317
                               (.valAt
                                input__40881_nth_1___l___nth_0__
                                :form)]
                              (clojure.core/let
                               [!forms []]
                               (clojure.core/let
                                [!forms
                                 (clojure.core/conj !forms VAL__41317)]
                                (clojure.core/loop
                                 [i__9717__auto__
                                  0
                                  coll__42295
                                  input__40881_nth_1___r__
                                  [!forms]
                                  [!forms]]
                                 (clojure.core/let
                                  [input__41318
                                   (clojure.core/subvec
                                    coll__42295
                                    0
                                    (clojure.core/min
                                     (clojure.core/count coll__42295)
                                     1))]
                                  (if
                                   (clojure.core/=
                                    (clojure.core/count input__41318)
                                    1)
                                   (clojure.core/let
                                    [result__9718__auto__
                                     (clojure.core/let
                                      [input__41318_nth_0__
                                       (clojure.core/nth
                                        input__41318
                                        0)]
                                      (if
                                       (clojure.core/map?
                                        input__41318_nth_0__)
                                       (clojure.core/let
                                        [VAL__41319
                                         (.valAt
                                          input__41318_nth_0__
                                          :tag)]
                                        (clojure.core/case
                                         VAL__41319
                                         (:literal)
                                         (clojure.core/let
                                          [VAL__41320
                                           (.valAt
                                            input__41318_nth_0__
                                            :type)]
                                          (if
                                           (clojure.core/let
                                            [x__8640__auto__
                                             VAL__41320]
                                            (clojure.core/case
                                             x__8640__auto__
                                             (:string :char)
                                             true
                                             false))
                                           (clojure.core/let
                                            [VAL__41321
                                             (.valAt
                                              input__41318_nth_0__
                                              :form)]
                                            (clojure.core/let
                                             [!forms
                                              (clojure.core/conj
                                               !forms
                                               VAL__41321)]
                                             [!forms]))
                                           (meander.runtime.zeta/fail)))
                                         (meander.runtime.zeta/fail)))
                                       (meander.runtime.zeta/fail)))]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      result__9718__auto__)
                                     (state__42279)
                                     (recur
                                      (clojure.core/inc
                                       i__9717__auto__)
                                      (clojure.core/subvec
                                       coll__42295
                                       1)
                                      result__9718__auto__)))
                                   (if
                                    (clojure.core/or
                                     (clojure.core/seq coll__42295)
                                     (clojure.core/<
                                      i__9717__auto__
                                      0))
                                    (state__42279)
                                    (if
                                     (clojure.core/map?
                                      input__40881_nth_2__)
                                     (clojure.core/let
                                      [VAL__41310
                                       (.valAt
                                        input__40881_nth_2__
                                        :tag)]
                                      (clojure.core/case
                                       VAL__41310
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
                                         e__11823__auto__
                                         (if
                                          (meander.runtime.zeta/fail?
                                           e__11823__auto__)
                                          (meander.runtime.zeta/fail)
                                          (throw e__11823__auto__))))
                                       (state__42279)))
                                     (state__42279)))))))))
                             (state__42279)))
                           (state__42279)))
                         (state__42279))))
                      (state__42279)))
                    (state__42279))
                   (state__42279)))
                 (state__42279
                  []
                  (clojure.core/let
                   [input__40881_nth_3__
                    (clojure.core/nth input__40881 3)]
                   (clojure.core/case
                    input__40881_nth_0__
                    (meander.dev.parse.zeta/make-cat)
                    (clojure.core/letfn
                     [(state__42296
                       []
                       (if
                        (clojure.core/vector? input__40881_nth_1__)
                        (clojure.core/let
                         [input__40881_nth_1___l__
                          (clojure.core/subvec
                           input__40881_nth_1__
                           0
                           (clojure.core/min
                            (clojure.core/count input__40881_nth_1__)
                            1))]
                         (if
                          (clojure.core/=
                           (clojure.core/count
                            input__40881_nth_1___l__)
                           1)
                          (clojure.core/let
                           [input__40881_nth_1___r__
                            (clojure.core/subvec
                             input__40881_nth_1__
                             1)]
                           (clojure.core/let
                            [input__40881_nth_1___l___nth_0__
                             (clojure.core/nth
                              input__40881_nth_1___l__
                              0)]
                            (if
                             (clojure.core/map?
                              input__40881_nth_1___l___nth_0__)
                             (clojure.core/let
                              [VAL__42176
                               (.valAt
                                input__40881_nth_1___l___nth_0__
                                :tag)]
                              (clojure.core/case
                               VAL__42176
                               (:literal)
                               (clojure.core/letfn
                                [(state__42298
                                  []
                                  (clojure.core/let
                                   [VAL__41328
                                    (.valAt
                                     input__40881_nth_1___l___nth_0__
                                     :type)]
                                   (clojure.core/case
                                    VAL__41328
                                    (:string)
                                    (clojure.core/let
                                     [?ast
                                      input__40881_nth_1___l___nth_0__]
                                     (clojure.core/let
                                      [?rest input__40881_nth_1___r__]
                                      (clojure.core/let
                                       [?next input__40881_nth_2__]
                                       (clojure.core/let
                                        [?env input__40881_nth_3__]
                                        (try
                                         [(clojure.core/let
                                           [CATA_RESULT__10883__auto__
                                            (CATA__FN__40949
                                             ['meander.dev.parse.zeta/make-join
                                              ?ast
                                              (clojure.core/let
                                               [CATA_RESULT__10883__auto__
                                                (CATA__FN__40949
                                                 ['meander.dev.parse.zeta/make-cat
                                                  ?rest
                                                  ?next
                                                  ?env])]
                                               (if
                                                (meander.runtime.zeta/fail?
                                                 CATA_RESULT__10883__auto__)
                                                (throw
                                                 (meander.runtime.zeta/fail))
                                                (clojure.core/nth
                                                 CATA_RESULT__10883__auto__
                                                 0)))
                                              ?env])]
                                           (if
                                            (meander.runtime.zeta/fail?
                                             CATA_RESULT__10883__auto__)
                                            (throw
                                             (meander.runtime.zeta/fail))
                                            (clojure.core/nth
                                             CATA_RESULT__10883__auto__
                                             0)))]
                                         (catch
                                          java.lang.Exception
                                          e__11823__auto__
                                          (if
                                           (meander.runtime.zeta/fail?
                                            e__11823__auto__)
                                           (meander.runtime.zeta/fail)
                                           (throw
                                            e__11823__auto__))))))))
                                    (state__42299))))
                                 (state__42299
                                  []
                                  (clojure.core/let
                                   [VAL__41338
                                    (.valAt
                                     input__40881_nth_1___l___nth_0__
                                     :form)]
                                   (clojure.core/let
                                    [!forms []]
                                    (clojure.core/let
                                     [!forms
                                      (clojure.core/conj
                                       !forms
                                       VAL__41338)]
                                     (clojure.core/loop
                                      [i__9717__auto__
                                       0
                                       coll__42300
                                       input__40881_nth_1___r__
                                       [!forms]
                                       [!forms]]
                                      (clojure.core/let
                                       [input__41339
                                        (clojure.core/subvec
                                         coll__42300
                                         0
                                         (clojure.core/min
                                          (clojure.core/count
                                           coll__42300)
                                          1))]
                                       (if
                                        (clojure.core/=
                                         (clojure.core/count
                                          input__41339)
                                         1)
                                        (clojure.core/let
                                         [result__9718__auto__
                                          (clojure.core/let
                                           [input__41339_nth_0__
                                            (clojure.core/nth
                                             input__41339
                                             0)]
                                           (if
                                            (clojure.core/map?
                                             input__41339_nth_0__)
                                            (clojure.core/let
                                             [VAL__41340
                                              (.valAt
                                               input__41339_nth_0__
                                               :tag)]
                                             (clojure.core/case
                                              VAL__41340
                                              (:literal)
                                              (clojure.core/let
                                               [VAL__41341
                                                (.valAt
                                                 input__41339_nth_0__
                                                 :form)]
                                               (clojure.core/let
                                                [!forms
                                                 (clojure.core/conj
                                                  !forms
                                                  VAL__41341)]
                                                [!forms]))
                                              (meander.runtime.zeta/fail)))
                                            (meander.runtime.zeta/fail)))]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           result__9718__auto__)
                                          (state__42297)
                                          (recur
                                           (clojure.core/inc
                                            i__9717__auto__)
                                           (clojure.core/subvec
                                            coll__42300
                                            1)
                                           result__9718__auto__)))
                                        (if
                                         (clojure.core/or
                                          (clojure.core/seq
                                           coll__42300)
                                          (clojure.core/<
                                           i__9717__auto__
                                           0))
                                         (state__42297)
                                         (if
                                          (clojure.core/map?
                                           input__40881_nth_2__)
                                          (clojure.core/let
                                           [VAL__41331
                                            (.valAt
                                             input__40881_nth_2__
                                             :tag)]
                                           (clojure.core/case
                                            VAL__41331
                                            (:empty)
                                            (if
                                             (clojure.core/map?
                                              input__40881_nth_3__)
                                             (clojure.core/let
                                              [VAL__41332
                                               (.valAt
                                                input__40881_nth_3__
                                                :context)]
                                              (clojure.core/let
                                               [?context VAL__41332]
                                               (clojure.core/let
                                                [?env
                                                 input__40881_nth_3__]
                                                (try
                                                 [{:tag :literal,
                                                   :type ?context,
                                                   :form
                                                   (clojure.core/into
                                                    []
                                                    !forms)}]
                                                 (catch
                                                  java.lang.Exception
                                                  e__11823__auto__
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    e__11823__auto__)
                                                   (meander.runtime.zeta/fail)
                                                   (throw
                                                    e__11823__auto__)))))))
                                             (state__42297))
                                            (state__42297)))
                                          (state__42297))))))))))]
                                (state__42298))
                               (state__42297)))
                             (state__42297))))
                          (state__42297)))
                        (state__42297)))
                      (state__42297
                       []
                       (clojure.core/let
                        [?sequence input__40881_nth_1__]
                        (clojure.core/let
                         [?next input__40881_nth_2__]
                         (if
                          (clojure.core/map? input__40881_nth_3__)
                          (clojure.core/let
                           [VAL__41345
                            (.valAt input__40881_nth_3__ :context)]
                           (clojure.core/case
                            VAL__41345
                            (:string)
                            (try
                             [{:tag :string-cat,
                               :sequence ?sequence,
                               :next ?next}]
                             (catch
                              java.lang.Exception
                              e__11823__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__11823__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__11823__auto__))))
                            (state__42280)))
                          (state__42280)))))]
                     (state__42296))
                    (state__42280))))
                 (state__42280
                  []
                  (clojure.core/case
                   input__40881_nth_0__
                   (meander.dev.parse.zeta/make-cat)
                   (clojure.core/let
                    [?sequence input__40881_nth_1__]
                    (clojure.core/let
                     [?next input__40881_nth_2__]
                     (try
                      [{:tag :cat, :sequence ?sequence, :next ?next}]
                      (catch
                       java.lang.Exception
                       e__11823__auto__
                       (if
                        (meander.runtime.zeta/fail? e__11823__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__11823__auto__))))))
                   (state__42281)))
                 (state__42281
                  []
                  (clojure.core/let
                   [input__40881_nth_3__
                    (clojure.core/nth input__40881 3)]
                   (clojure.core/case
                    input__40881_nth_0__
                    (meander.dev.parse.zeta/make-join)
                    (if
                     (clojure.core/map? input__40881_nth_1__)
                     (clojure.core/let
                      [VAL__42174 (.valAt input__40881_nth_1__ :tag)]
                      (clojure.core/case
                       VAL__42174
                       (:cat)
                       (clojure.core/let
                        [VAL__41351
                         (.valAt input__40881_nth_1__ :sequence)]
                        (clojure.core/let
                         [?sequence VAL__41351]
                         (clojure.core/let
                          [VAL__41352
                           (.valAt input__40881_nth_1__ :next)]
                          (if
                           (clojure.core/map? VAL__41352)
                           (clojure.core/let
                            [VAL__41353 (.valAt VAL__41352 :tag)]
                            (clojure.core/case
                             VAL__41353
                             (:empty)
                             (clojure.core/let
                              [?right input__40881_nth_2__]
                              (clojure.core/let
                               [?env input__40881_nth_3__]
                               (try
                                [(clojure.core/let
                                  [CATA_RESULT__10883__auto__
                                   (CATA__FN__40949
                                    ['meander.dev.parse.zeta/make-cat
                                     ?sequence
                                     ?right
                                     ?env])]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    CATA_RESULT__10883__auto__)
                                   (throw (meander.runtime.zeta/fail))
                                   (clojure.core/nth
                                    CATA_RESULT__10883__auto__
                                    0)))]
                                (catch
                                 java.lang.Exception
                                 e__11823__auto__
                                 (if
                                  (meander.runtime.zeta/fail?
                                   e__11823__auto__)
                                  (meander.runtime.zeta/fail)
                                  (throw e__11823__auto__))))))
                             (state__42282)))
                           (state__42282)))))
                       (:literal)
                       (clojure.core/let
                        [VAL__41357
                         (.valAt input__40881_nth_1__ :type)]
                        (clojure.core/case
                         VAL__41357
                         (:string)
                         (clojure.core/let
                          [VAL__41358
                           (.valAt input__40881_nth_1__ :form)]
                          (clojure.core/let
                           [?form-1 VAL__41358]
                           (if
                            (clojure.core/map? input__40881_nth_2__)
                            (clojure.core/let
                             [VAL__41359
                              (.valAt input__40881_nth_2__ :tag)]
                             (clojure.core/case
                              VAL__41359
                              (:string-join)
                              (clojure.core/let
                               [VAL__41360
                                (.valAt input__40881_nth_2__ :left)]
                               (if
                                (clojure.core/map? VAL__41360)
                                (clojure.core/let
                                 [VAL__41361 (.valAt VAL__41360 :tag)]
                                 (clojure.core/case
                                  VAL__41361
                                  (:literal)
                                  (clojure.core/let
                                   [VAL__41362
                                    (.valAt VAL__41360 :type)]
                                   (clojure.core/case
                                    VAL__41362
                                    (:string)
                                    (clojure.core/let
                                     [VAL__41363
                                      (.valAt VAL__41360 :form)]
                                     (clojure.core/let
                                      [?form-2 VAL__41363]
                                      (clojure.core/let
                                       [VAL__41364
                                        (.valAt
                                         input__40881_nth_2__
                                         :right)]
                                       (clojure.core/let
                                        [?right VAL__41364]
                                        (if
                                         (clojure.core/map?
                                          input__40881_nth_3__)
                                         (clojure.core/let
                                          [VAL__41365
                                           (.valAt
                                            input__40881_nth_3__
                                            :context)]
                                          (clojure.core/case
                                           VAL__41365
                                           (:string)
                                           (clojure.core/let
                                            [?env input__40881_nth_3__]
                                            (try
                                             [(clojure.core/let
                                               [CATA_RESULT__10883__auto__
                                                (CATA__FN__40949
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
                                                 CATA_RESULT__10883__auto__)
                                                (throw
                                                 (meander.runtime.zeta/fail))
                                                (clojure.core/nth
                                                 CATA_RESULT__10883__auto__
                                                 0)))]
                                             (catch
                                              java.lang.Exception
                                              e__11823__auto__
                                              (if
                                               (meander.runtime.zeta/fail?
                                                e__11823__auto__)
                                               (meander.runtime.zeta/fail)
                                               (throw
                                                e__11823__auto__)))))
                                           (state__42282)))
                                         (state__42282))))))
                                    (state__42282)))
                                  (state__42282)))
                                (state__42282)))
                              (state__42282)))
                            (state__42282))))
                         (state__42282)))
                       (state__42282)))
                     (state__42282))
                    (state__42282))))
                 (state__42282
                  []
                  (clojure.core/case
                   input__40881_nth_0__
                   (meander.dev.parse.zeta/make-join)
                   (if
                    (clojure.core/map? input__40881_nth_1__)
                    (clojure.core/let
                     [VAL__42175 (.valAt input__40881_nth_1__ :tag)]
                     (clojure.core/case
                      VAL__42175
                      (:cat)
                      (clojure.core/let
                       [?ast input__40881_nth_1__]
                       (if
                        (clojure.core/map? input__40881_nth_2__)
                        (clojure.core/let
                         [VAL__41369
                          (.valAt input__40881_nth_2__ :tag)]
                         (clojure.core/case
                          VAL__41369
                          (:cat)
                          (clojure.core/let
                           [VAL__41370
                            (.valAt input__40881_nth_2__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__41370]
                            (clojure.core/let
                             [VAL__41371
                              (.valAt input__40881_nth_2__ :next)]
                             (clojure.core/let
                              [?next VAL__41371]
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
                                e__11823__auto__
                                (if
                                 (meander.runtime.zeta/fail?
                                  e__11823__auto__)
                                 (meander.runtime.zeta/fail)
                                 (throw e__11823__auto__))))))))
                          (state__42283)))
                        (state__42283)))
                      (:string-cat)
                      (clojure.core/let
                       [?ast input__40881_nth_1__]
                       (if
                        (clojure.core/map? input__40881_nth_2__)
                        (clojure.core/let
                         [VAL__41375
                          (.valAt input__40881_nth_2__ :tag)]
                         (clojure.core/case
                          VAL__41375
                          (:string-cat)
                          (clojure.core/let
                           [VAL__41376
                            (.valAt input__40881_nth_2__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__41376]
                            (clojure.core/let
                             [VAL__41377
                              (.valAt input__40881_nth_2__ :next)]
                             (clojure.core/let
                              [?next VAL__41377]
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
                                e__11823__auto__
                                (if
                                 (meander.runtime.zeta/fail?
                                  e__11823__auto__)
                                 (meander.runtime.zeta/fail)
                                 (throw e__11823__auto__))))))))
                          (state__42283)))
                        (state__42283)))
                      (state__42283)))
                    (state__42283))
                   (state__42283)))
                 (state__42283
                  []
                  (clojure.core/let
                   [input__40881_nth_3__
                    (clojure.core/nth input__40881 3)]
                   (clojure.core/case
                    input__40881_nth_0__
                    (meander.dev.parse.zeta/make-join)
                    (clojure.core/letfn
                     [(state__42301
                       []
                       (if
                        (clojure.core/map? input__40881_nth_1__)
                        (clojure.core/let
                         [VAL__42179
                          (.valAt input__40881_nth_1__ :next)
                          VAL__42178
                          (.valAt input__40881_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__42178
                          (:string-cat)
                          (clojure.core/let
                           [VAL__41381
                            (.valAt input__40881_nth_1__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__41381]
                            (if
                             (clojure.core/map? VAL__42179)
                             (clojure.core/let
                              [VAL__41383 (.valAt VAL__42179 :tag)]
                              (clojure.core/case
                               VAL__41383
                               (:empty)
                               (clojure.core/let
                                [?right input__40881_nth_2__]
                                (clojure.core/let
                                 [?env input__40881_nth_3__]
                                 (try
                                  [(clojure.core/let
                                    [CATA_RESULT__10883__auto__
                                     (CATA__FN__40949
                                      ['meander.dev.parse.zeta/make-join
                                       ?sequence
                                       ?right
                                       ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__10883__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__10883__auto__
                                      0)))]
                                  (catch
                                   java.lang.Exception
                                   e__11823__auto__
                                   (if
                                    (meander.runtime.zeta/fail?
                                     e__11823__auto__)
                                    (meander.runtime.zeta/fail)
                                    (throw e__11823__auto__))))))
                               (state__42302)))
                             (state__42302))))
                          (:string-star)
                          (clojure.core/let
                           [VAL__41387
                            (.valAt input__40881_nth_1__ :pattern)]
                           (clojure.core/let
                            [?pattern VAL__41387]
                            (if
                             (clojure.core/map? VAL__42179)
                             (clojure.core/let
                              [VAL__41389 (.valAt VAL__42179 :tag)]
                              (clojure.core/case
                               VAL__41389
                               (:empty)
                               (clojure.core/let
                                [?right input__40881_nth_2__]
                                (if
                                 (clojure.core/map?
                                  input__40881_nth_3__)
                                 (clojure.core/let
                                  [VAL__41390
                                   (.valAt
                                    input__40881_nth_3__
                                    :context)]
                                  (clojure.core/case
                                   VAL__41390
                                   (:string)
                                   (try
                                    [{:tag :string-star,
                                      :pattern ?pattern,
                                      :next ?right}]
                                    (catch
                                     java.lang.Exception
                                     e__11823__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__11823__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__11823__auto__))))
                                   (state__42302)))
                                 (state__42302)))
                               (state__42302)))
                             (state__42302))))
                          (:string-join)
                          (clojure.core/let
                           [VAL__41394
                            (.valAt input__40881_nth_1__ :left)]
                           (clojure.core/let
                            [?left VAL__41394]
                            (clojure.core/let
                             [VAL__41395
                              (.valAt input__40881_nth_1__ :right)]
                             (clojure.core/let
                              [?right-1 VAL__41395]
                              (clojure.core/let
                               [?right-2 input__40881_nth_2__]
                               (if
                                (clojure.core/map?
                                 input__40881_nth_3__)
                                (clojure.core/let
                                 [VAL__41396
                                  (.valAt
                                   input__40881_nth_3__
                                   :context)]
                                 (clojure.core/case
                                  VAL__41396
                                  (:string)
                                  (clojure.core/let
                                   [?env input__40881_nth_3__]
                                   (try
                                    [{:tag :string-join,
                                      :left ?left,
                                      :right
                                      (clojure.core/let
                                       [CATA_RESULT__10883__auto__
                                        (CATA__FN__40949
                                         ['meander.dev.parse.zeta/make-join
                                          ?right-1
                                          ?right-2
                                          ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__10883__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__10883__auto__
                                         0)))}]
                                    (catch
                                     java.lang.Exception
                                     e__11823__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__11823__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__11823__auto__)))))
                                  (state__42302)))
                                (state__42302)))))))
                          (state__42302)))
                        (state__42302)))
                      (state__42302
                       []
                       (clojure.core/let
                        [?left input__40881_nth_1__]
                        (if
                         (clojure.core/map? input__40881_nth_2__)
                         (clojure.core/let
                          [VAL__41399
                           (.valAt input__40881_nth_2__ :tag)]
                          (clojure.core/case
                           VAL__41399
                           (:empty)
                           (clojure.core/let
                            [?env input__40881_nth_3__]
                            (try
                             [?left]
                             (catch
                              java.lang.Exception
                              e__11823__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__11823__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__11823__auto__)))))
                           (state__42303)))
                         (state__42303))))
                      (state__42303
                       []
                       (if
                        (clojure.core/map? input__40881_nth_1__)
                        (clojure.core/let
                         [VAL__42177
                          (.valAt input__40881_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__42177
                          (:empty)
                          (clojure.core/let
                           [?right input__40881_nth_2__]
                           (clojure.core/let
                            [?env input__40881_nth_3__]
                            (try
                             [?right]
                             (catch
                              java.lang.Exception
                              e__11823__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__11823__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__11823__auto__))))))
                          (:star)
                          (clojure.core/let
                           [VAL__41406
                            (.valAt input__40881_nth_1__ :next)]
                           (if
                            (clojure.core/map? VAL__41406)
                            (clojure.core/let
                             [VAL__41407 (.valAt VAL__41406 :tag)]
                             (clojure.core/case
                              VAL__41407
                              (:empty)
                              (clojure.core/let
                               [?left input__40881_nth_1__]
                               (clojure.core/let
                                [?right input__40881_nth_2__]
                                (clojure.core/let
                                 [?env input__40881_nth_3__]
                                 (try
                                  [(clojure.core/let
                                    [form__10982__auto__
                                     {:tag :star, :next ?right}]
                                    (clojure.core/merge
                                     (clojure.core/into {} ?left)
                                     form__10982__auto__))]
                                  (catch
                                   java.lang.Exception
                                   e__11823__auto__
                                   (if
                                    (meander.runtime.zeta/fail?
                                     e__11823__auto__)
                                    (meander.runtime.zeta/fail)
                                    (throw e__11823__auto__)))))))
                              (state__42304)))
                            (state__42304)))
                          (state__42304)))
                        (state__42304)))
                      (state__42304
                       []
                       (clojure.core/let
                        [?left input__40881_nth_1__]
                        (clojure.core/let
                         [?right input__40881_nth_2__]
                         (clojure.core/letfn
                          [(state__42305
                            []
                            (if
                             (clojure.core/map? input__40881_nth_3__)
                             (clojure.core/let
                              [VAL__41410
                               (.valAt input__40881_nth_3__ :context)]
                              (clojure.core/case
                               VAL__41410
                               (:string)
                               (try
                                [{:tag :string-join,
                                  :left ?left,
                                  :right ?right}]
                                (catch
                                 java.lang.Exception
                                 e__11823__auto__
                                 (if
                                  (meander.runtime.zeta/fail?
                                   e__11823__auto__)
                                  (meander.runtime.zeta/fail)
                                  (throw e__11823__auto__))))
                               (state__42306)))
                             (state__42306)))
                           (state__42306
                            []
                            (clojure.core/let
                             [?env input__40881_nth_3__]
                             (try
                              [{:tag :join,
                                :left ?left,
                                :right ?right}]
                              (catch
                               java.lang.Exception
                               e__11823__auto__
                               (if
                                (meander.runtime.zeta/fail?
                                 e__11823__auto__)
                                (meander.runtime.zeta/fail)
                                (throw e__11823__auto__))))))]
                          (state__42305)))))]
                     (state__42301))
                    (state__42276))))]
                (state__42277)))
              (state__42276)))
            (state__42276
             []
             (if
              (clojure.core/= (clojure.core/count input__40881) 3)
              (clojure.core/let
               [input__40881_nth_0__
                (clojure.core/nth input__40881 0)
                input__40881_nth_1__
                (clojure.core/nth input__40881 1)
                input__40881_nth_2__
                (clojure.core/nth input__40881 2)]
               (clojure.core/case
                input__40881_nth_0__
                (meander.dev.parse.zeta/parse-entries)
                (if
                 (clojure.core/map? input__40881_nth_1__)
                 (clojure.core/let
                  [VAL__41415
                   (.valAt input__40881_nth_1__ :meander.zeta/as)]
                  (clojure.core/let
                   [?pattern VAL__41415]
                   (clojure.core/let
                    [X__41417
                     ((clojure.core/fn
                       [m__8647__auto__]
                       (clojure.core/dissoc
                        m__8647__auto__
                        :meander.zeta/as))
                      input__40881_nth_1__)]
                    (clojure.core/let
                     [?rest X__41417]
                     (clojure.core/letfn
                      [(save__41418 [] (state__42190))
                       (f__42307
                        []
                        (clojure.core/let
                         [?env input__40881_nth_2__]
                         (try
                          [{:tag :as,
                            :pattern
                            (clojure.core/let
                             [CATA_RESULT__10883__auto__
                              (CATA__FN__40949 [?pattern ?env])]
                             (if
                              (meander.runtime.zeta/fail?
                               CATA_RESULT__10883__auto__)
                              (throw (meander.runtime.zeta/fail))
                              (clojure.core/nth
                               CATA_RESULT__10883__auto__
                               0))),
                            :next
                            (clojure.core/let
                             [CATA_RESULT__10883__auto__
                              (CATA__FN__40949 [?rest ?env])]
                             (if
                              (meander.runtime.zeta/fail?
                               CATA_RESULT__10883__auto__)
                              (throw (meander.runtime.zeta/fail))
                              (clojure.core/nth
                               CATA_RESULT__10883__auto__
                               0)))}]
                          (catch
                           java.lang.Exception
                           e__11823__auto__
                           (if
                            (meander.runtime.zeta/fail?
                             e__11823__auto__)
                            (meander.runtime.zeta/fail)
                            (throw e__11823__auto__))))))]
                      (if
                       (clojure.core/= ?rest input__40881_nth_1__)
                       (save__41418)
                       (f__42307)))))))
                 (state__42190))
                (state__42190)))
              (state__42190)))]
           (state__42274))
          (state__42190)))
        (state__42190
         []
         (clojure.core/letfn
          [(def__41421
            [arg__41454 ?ns]
            (clojure.core/letfn
             [(state__42308
               []
               (clojure.core/let
                [x__41455 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__41455)
                 (clojure.core/let [?env arg__41454] [?env ?ns])
                 (state__42309))))
              (state__42309
               []
               (if
                (clojure.core/map? arg__41454)
                (clojure.core/let
                 [VAL__41456 (.valAt arg__41454 :aliases)]
                 (if
                  (clojure.core/map? VAL__41456)
                  (clojure.core/let
                   [X__41458 (clojure.core/set VAL__41456)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__41458))
                    (clojure.core/loop
                     [search_space__42310 (clojure.core/seq X__41458)]
                     (if
                      (clojure.core/seq search_space__42310)
                      (clojure.core/let
                       [elem__41459
                        (clojure.core/first search_space__42310)
                        result__42311
                        (clojure.core/let
                         [elem__41459_nth_0__
                          (clojure.core/nth elem__41459 0)
                          elem__41459_nth_1__
                          (clojure.core/nth elem__41459 1)]
                         (if
                          (clojure.core/symbol? elem__41459_nth_0__)
                          (clojure.core/let
                           [X__41461
                            (clojure.core/name elem__41459_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__41461)
                            (if
                             (clojure.core/symbol? elem__41459_nth_1__)
                             (clojure.core/let
                              [X__41463
                               (clojure.core/name elem__41459_nth_1__)]
                              (clojure.core/case
                               X__41463
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__41454]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__42311)
                        (recur (clojure.core/next search_space__42310))
                        result__42311))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__42308)))]
          (if
           (clojure.core/vector? input__40881)
           (if
            (clojure.core/= (clojure.core/count input__40881) 3)
            (clojure.core/let
             [input__40881_nth_0__
              (clojure.core/nth input__40881 0)
              input__40881_nth_1__
              (clojure.core/nth input__40881 1)
              input__40881_nth_2__
              (clojure.core/nth input__40881 2)]
             (clojure.core/case
              input__40881_nth_0__
              (meander.dev.parse.zeta/parse-entries)
              (if
               (clojure.core/map? input__40881_nth_1__)
               (clojure.core/let
                [X__41426 (clojure.core/set input__40881_nth_1__)]
                (if
                 (clojure.core/<= 1 (clojure.core/count X__41426))
                 (clojure.core/loop
                  [search_space__42313 (clojure.core/seq X__41426)]
                  (if
                   (clojure.core/seq search_space__42313)
                   (clojure.core/let
                    [elem__41427
                     (clojure.core/first search_space__42313)
                     result__42314
                     (clojure.core/let
                      [elem__41427_nth_0__
                       (clojure.core/nth elem__41427 0)
                       elem__41427_nth_1__
                       (clojure.core/nth elem__41427 1)]
                      (clojure.core/let
                       [*m__40910 elem__41427_nth_0__]
                       (if
                        (clojure.core/symbol? elem__41427_nth_0__)
                        (clojure.core/let
                         [X__41429
                          (clojure.core/namespace elem__41427_nth_0__)]
                         (clojure.core/let
                          [?ns X__41429]
                          (clojure.core/let
                           [X__41431
                            (clojure.core/name elem__41427_nth_0__)]
                           (if
                            (clojure.core/string? X__41431)
                            (if
                             (clojure.core/re-matches #"&.*" X__41431)
                             (clojure.core/let
                              [?pattern elem__41427_nth_1__]
                              (clojure.core/let
                               [X__41433
                                ((clojure.core/fn
                                  [m__8647__auto__]
                                  (clojure.core/dissoc
                                   m__8647__auto__
                                   *m__40910))
                                 input__40881_nth_1__)]
                               (clojure.core/let
                                [?rest X__41433]
                                (clojure.core/let
                                 [x__9580__auto__
                                  (def__41421
                                   input__40881_nth_2__
                                   ?ns)]
                                 (if
                                  (meander.runtime.zeta/fail?
                                   x__9580__auto__)
                                  (meander.runtime.zeta/fail)
                                  (clojure.core/let
                                   [[?env ?ns] x__9580__auto__]
                                   (try
                                    [{:tag :rest-map,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__10883__auto__
                                        (CATA__FN__40949
                                         [?pattern ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__10883__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__10883__auto__
                                         0))),
                                      :next
                                      (clojure.core/let
                                       [CATA_RESULT__10883__auto__
                                        (CATA__FN__40949
                                         ['meander.dev.parse.zeta/parse-entries
                                          ?rest
                                          ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__10883__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__10883__auto__
                                         0)))}]
                                    (catch
                                     java.lang.Exception
                                     e__11823__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__11823__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__11823__auto__))))))))))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))))
                        (meander.runtime.zeta/fail))))]
                    (if
                     (meander.runtime.zeta/fail? result__42314)
                     (recur (clojure.core/next search_space__42313))
                     result__42314))
                   (state__42191)))
                 (state__42191)))
               (state__42191))
              (state__42191)))
            (state__42191))
           (state__42191))))
        (state__42191
         []
         (if
          (clojure.core/vector? input__40881)
          (clojure.core/letfn
           [(state__42316
             []
             (if
              (clojure.core/= (clojure.core/count input__40881) 3)
              (clojure.core/let
               [input__40881_nth_0__
                (clojure.core/nth input__40881 0)
                input__40881_nth_1__
                (clojure.core/nth input__40881 1)
                input__40881_nth_2__
                (clojure.core/nth input__40881 2)]
               (clojure.core/case
                input__40881_nth_0__
                (meander.dev.parse.zeta/parse-entries)
                (clojure.core/letfn
                 [(state__42318
                   []
                   (if
                    (clojure.core/map? input__40881_nth_1__)
                    (clojure.core/let
                     [X__41477 (clojure.core/set input__40881_nth_1__)]
                     (if
                      (clojure.core/<= 1 (clojure.core/count X__41477))
                      (clojure.core/loop
                       [search_space__42320
                        (clojure.core/seq X__41477)]
                       (if
                        (clojure.core/seq search_space__42320)
                        (clojure.core/let
                         [elem__41478
                          (clojure.core/first search_space__42320)
                          result__42321
                          (clojure.core/let
                           [elem__41478_nth_0__
                            (clojure.core/nth elem__41478 0)
                            elem__41478_nth_1__
                            (clojure.core/nth elem__41478 1)]
                           (clojure.core/let
                            [?key-pattern elem__41478_nth_0__]
                            (clojure.core/let
                             [?val-pattern elem__41478_nth_1__]
                             (clojure.core/let
                              [X__41480
                               ((clojure.core/fn
                                 [m__8647__auto__]
                                 (clojure.core/dissoc
                                  m__8647__auto__
                                  ?key-pattern))
                                input__40881_nth_1__)]
                              (clojure.core/let
                               [?rest X__41480]
                               (clojure.core/let
                                [?env input__40881_nth_2__]
                                (try
                                 [{:tag :entry,
                                   :key-pattern
                                   (clojure.core/let
                                    [CATA_RESULT__10883__auto__
                                     (CATA__FN__40949
                                      [?key-pattern ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__10883__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__10883__auto__
                                      0))),
                                   :val-pattern
                                   (clojure.core/let
                                    [CATA_RESULT__10883__auto__
                                     (CATA__FN__40949
                                      [?val-pattern ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__10883__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__10883__auto__
                                      0))),
                                   :next
                                   (clojure.core/let
                                    [CATA_RESULT__10883__auto__
                                     (CATA__FN__40949
                                      ['meander.dev.parse.zeta/parse-entries
                                       ?rest
                                       ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__10883__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__10883__auto__
                                      0)))}]
                                 (catch
                                  java.lang.Exception
                                  e__11823__auto__
                                  (if
                                   (meander.runtime.zeta/fail?
                                    e__11823__auto__)
                                   (meander.runtime.zeta/fail)
                                   (throw e__11823__auto__))))))))))]
                         (if
                          (meander.runtime.zeta/fail? result__42321)
                          (recur
                           (clojure.core/next search_space__42320))
                          result__42321))
                        (state__42319)))
                      (state__42319)))
                    (state__42319)))
                  (state__42319
                   []
                   (if
                    (clojure.core/map? input__40881_nth_1__)
                    (clojure.core/let
                     [?env input__40881_nth_2__]
                     (try
                      [{:tag :some-map}]
                      (catch
                       java.lang.Exception
                       e__11823__auto__
                       (if
                        (meander.runtime.zeta/fail? e__11823__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__11823__auto__)))))
                    (state__42317)))]
                 (state__42318))
                (meander.dev.parse.zeta/parse-with-bindings)
                (clojure.core/letfn
                 [(state__42323
                   []
                   (if
                    (clojure.core/vector? input__40881_nth_1__)
                    (clojure.core/case
                     input__40881_nth_1__
                     ([])
                     (clojure.core/let
                      [?env input__40881_nth_2__]
                      (try
                       [[]]
                       (catch
                        java.lang.Exception
                        e__11823__auto__
                        (if
                         (meander.runtime.zeta/fail? e__11823__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__11823__auto__)))))
                     (state__42324))
                    (state__42324)))
                  (state__42324
                   []
                   (if
                    (clojure.core/vector? input__40881_nth_1__)
                    (if
                     (clojure.core/=
                      (clojure.core/count input__40881_nth_1__)
                      1)
                     (clojure.core/let
                      [?env input__40881_nth_2__]
                      (try
                       [[{:tag :error,
                          :message
                          "meander.zeta/with expects an even number of bindings"}]]
                       (catch
                        java.lang.Exception
                        e__11823__auto__
                        (if
                         (meander.runtime.zeta/fail? e__11823__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__11823__auto__)))))
                     (state__42325))
                    (state__42325)))
                  (state__42325
                   []
                   (if
                    (clojure.core/vector? input__40881_nth_1__)
                    (clojure.core/let
                     [input__40881_nth_1___l__
                      (clojure.core/subvec
                       input__40881_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__40881_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__40881_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__40881_nth_1___r__
                        (clojure.core/subvec input__40881_nth_1__ 2)]
                       (clojure.core/let
                        [input__40881_nth_1___l___nth_0__
                         (clojure.core/nth input__40881_nth_1___l__ 0)
                         input__40881_nth_1___l___nth_1__
                         (clojure.core/nth input__40881_nth_1___l__ 1)]
                        (if
                         (clojure.core/symbol?
                          input__40881_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__41494
                           (clojure.core/namespace
                            input__40881_nth_1___l___nth_0__)]
                          (clojure.core/let
                           [X__41496
                            (clojure.core/name
                             input__40881_nth_1___l___nth_0__)]
                           (if
                            (clojure.core/string? X__41496)
                            (if
                             (clojure.core/re-matches #"%.+" X__41496)
                             (clojure.core/let
                              [?symbol
                               input__40881_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?pattern
                                input__40881_nth_1___l___nth_1__]
                               (clojure.core/let
                                [?rest input__40881_nth_1___r__]
                                (clojure.core/let
                                 [?env input__40881_nth_2__]
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
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__40949
                                          [?pattern ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10883__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10883__auto__
                                          0)))})
                                     (clojure.core/let
                                      [CATA_RESULT__10883__auto__
                                       (CATA__FN__40949
                                        ['meander.dev.parse.zeta/parse-with-bindings
                                         ?rest
                                         ?env])]
                                      (if
                                       (meander.runtime.zeta/fail?
                                        CATA_RESULT__10883__auto__)
                                       (throw
                                        (meander.runtime.zeta/fail))
                                       (clojure.core/nth
                                        CATA_RESULT__10883__auto__
                                        0)))))]
                                  (catch
                                   java.lang.Exception
                                   e__11823__auto__
                                   (if
                                    (meander.runtime.zeta/fail?
                                     e__11823__auto__)
                                    (meander.runtime.zeta/fail)
                                    (throw e__11823__auto__))))))))
                             (state__42326))
                            (state__42326))))
                         (state__42326))))
                      (state__42326)))
                    (state__42326)))
                  (state__42326
                   []
                   (if
                    (clojure.core/vector? input__40881_nth_1__)
                    (clojure.core/let
                     [input__40881_nth_1___l__
                      (clojure.core/subvec
                       input__40881_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__40881_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__40881_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__40881_nth_1___r__
                        (clojure.core/subvec input__40881_nth_1__ 2)]
                       (clojure.core/let
                        [input__40881_nth_1___l___nth_0__
                         (clojure.core/nth input__40881_nth_1___l__ 0)
                         input__40881_nth_1___l___nth_1__
                         (clojure.core/nth input__40881_nth_1___l__ 1)]
                        (clojure.core/let
                         [?x input__40881_nth_1___l___nth_0__]
                         (clojure.core/let
                          [?pattern input__40881_nth_1___l___nth_1__]
                          (clojure.core/let
                           [?rest input__40881_nth_1___r__]
                           (clojure.core/let
                            [?env input__40881_nth_2__]
                            (try
                             [[{:tag :error,
                                :message
                                "meander.zeta/with bindings must be an repeating sequence of %name pattern"}]]
                             (catch
                              java.lang.Exception
                              e__11823__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__11823__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__11823__auto__))))))))))
                      (state__42317)))
                    (state__42317)))]
                 (state__42323))
                (state__42317)))
              (state__42317)))
            (state__42317
             []
             (if
              (clojure.core/= (clojure.core/count input__40881) 2)
              (clojure.core/let
               [input__40881_nth_0__
                (clojure.core/nth input__40881 0)
                input__40881_nth_1__
                (clojure.core/nth input__40881 1)]
               (if
                (clojure.core/vector? input__40881_nth_0__)
                (clojure.core/let
                 [?sequence input__40881_nth_0__]
                 (clojure.core/let
                  [?form input__40881_nth_0__]
                  (clojure.core/let
                   [?env input__40881_nth_1__]
                   (try
                    [{:tag :vector,
                      :next
                      (clojure.core/let
                       [CATA_RESULT__10883__auto__
                        (CATA__FN__40949
                         ['meander.dev.parse.zeta/parse-sequential
                          ?sequence
                          (clojure.core/let
                           [form__10982__auto__ {:context :vector}]
                           (clojure.core/merge
                            (clojure.core/into {} ?env)
                            form__10982__auto__))])]
                       (if
                        (meander.runtime.zeta/fail?
                         CATA_RESULT__10883__auto__)
                        (throw (meander.runtime.zeta/fail))
                        (clojure.core/nth
                         CATA_RESULT__10883__auto__
                         0))),
                      :form ?form}]
                    (catch
                     java.lang.Exception
                     e__11823__auto__
                     (if
                      (meander.runtime.zeta/fail? e__11823__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__11823__auto__)))))))
                (state__42192)))
              (state__42192)))]
           (state__42316))
          (state__42192)))
        (state__42192
         []
         (clojure.core/letfn
          [(def__41506
            [arg__41529 ?__40882]
            (clojure.core/letfn
             [(state__42327
               []
               (clojure.core/let
                [x__41530 "meander.zeta"]
                (if
                 (clojure.core/= ?__40882 x__41530)
                 [?__40882]
                 (state__42328))))
              (state__42328
               []
               (if
                (clojure.core/map? arg__41529)
                (clojure.core/let
                 [VAL__41531 (.valAt arg__41529 :aliases)]
                 (if
                  (clojure.core/map? VAL__41531)
                  (clojure.core/let
                   [X__41533 (clojure.core/set VAL__41531)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__41533))
                    (clojure.core/loop
                     [search_space__42329 (clojure.core/seq X__41533)]
                     (if
                      (clojure.core/seq search_space__42329)
                      (clojure.core/let
                       [elem__41534
                        (clojure.core/first search_space__42329)
                        result__42330
                        (clojure.core/let
                         [elem__41534_nth_0__
                          (clojure.core/nth elem__41534 0)
                          elem__41534_nth_1__
                          (clojure.core/nth elem__41534 1)]
                         (if
                          (clojure.core/symbol? elem__41534_nth_0__)
                          (clojure.core/let
                           [X__41536
                            (clojure.core/name elem__41534_nth_0__)]
                           (if
                            (clojure.core/= ?__40882 X__41536)
                            (if
                             (clojure.core/symbol? elem__41534_nth_1__)
                             (clojure.core/let
                              [X__41538
                               (clojure.core/name elem__41534_nth_1__)]
                              (clojure.core/case
                               X__41538
                               ("meander.zeta")
                               [?__40882]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__42330)
                        (recur (clojure.core/next search_space__42329))
                        result__42330))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__42327)))]
          (if
           (clojure.core/vector? input__40881)
           (if
            (clojure.core/= (clojure.core/count input__40881) 2)
            (clojure.core/let
             [input__40881_nth_0__
              (clojure.core/nth input__40881 0)
              input__40881_nth_1__
              (clojure.core/nth input__40881 1)]
             (if
              (clojure.core/seq? input__40881_nth_0__)
              (clojure.core/let
               [input__40881_nth_0___l__
                (clojure.core/take 1 input__40881_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__40881_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__40881_nth_0___r__
                  (clojure.core/drop 1 input__40881_nth_0__)]
                 (clojure.core/let
                  [input__40881_nth_0___l___nth_0__
                   (clojure.core/nth input__40881_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__40881_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__41516
                     (clojure.core/namespace
                      input__40881_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__40882 X__41516]
                     (clojure.core/let
                      [X__41518
                       (clojure.core/name
                        input__40881_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__41518
                       ("*")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__41506 input__40881_nth_1__ ?__40882)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__42193)
                         (clojure.core/let
                          [[?__40882] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__40881)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__40881)
                             2)
                            (clojure.core/let
                             [input__40881_nth_0__
                              (clojure.core/nth input__40881 0)
                              input__40881_nth_1__
                              (clojure.core/nth input__40881 1)]
                             (if
                              (clojure.core/seq? input__40881_nth_0__)
                              (clojure.core/let
                               [input__40881_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__40881_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__40881_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__40881_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__40881_nth_0__)]
                                 (clojure.core/let
                                  [?patterns input__40881_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__40881_nth_0__]
                                   (clojure.core/let
                                    [?env input__40881_nth_1__]
                                    (try
                                     [{:tag :star,
                                       :greedy? true,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__40949
                                          ['meander.dev.parse.zeta/parse-sequential
                                           (clojure.core/into
                                            []
                                            ?patterns)
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10883__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10883__auto__
                                          0))),
                                       :next {:tag :empty}}]
                                     (catch
                                      java.lang.Exception
                                      e__11823__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__11823__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__11823__auto__))))))))
                                (state__42193)))
                              (state__42193)))
                            (state__42193))
                           (state__42193)))))
                       (state__42193)))))
                   (state__42193))))
                (state__42193)))
              (state__42193)))
            (state__42193))
           (state__42193))))
        (state__42193
         []
         (clojure.core/letfn
          [(def__41540
            [arg__41563 ?__40883]
            (clojure.core/letfn
             [(state__42332
               []
               (clojure.core/let
                [x__41564 "meander.zeta"]
                (if
                 (clojure.core/= ?__40883 x__41564)
                 [?__40883]
                 (state__42333))))
              (state__42333
               []
               (if
                (clojure.core/map? arg__41563)
                (clojure.core/let
                 [VAL__41565 (.valAt arg__41563 :aliases)]
                 (if
                  (clojure.core/map? VAL__41565)
                  (clojure.core/let
                   [X__41567 (clojure.core/set VAL__41565)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__41567))
                    (clojure.core/loop
                     [search_space__42334 (clojure.core/seq X__41567)]
                     (if
                      (clojure.core/seq search_space__42334)
                      (clojure.core/let
                       [elem__41568
                        (clojure.core/first search_space__42334)
                        result__42335
                        (clojure.core/let
                         [elem__41568_nth_0__
                          (clojure.core/nth elem__41568 0)
                          elem__41568_nth_1__
                          (clojure.core/nth elem__41568 1)]
                         (if
                          (clojure.core/symbol? elem__41568_nth_0__)
                          (clojure.core/let
                           [X__41570
                            (clojure.core/name elem__41568_nth_0__)]
                           (if
                            (clojure.core/= ?__40883 X__41570)
                            (if
                             (clojure.core/symbol? elem__41568_nth_1__)
                             (clojure.core/let
                              [X__41572
                               (clojure.core/name elem__41568_nth_1__)]
                              (clojure.core/case
                               X__41572
                               ("meander.zeta")
                               [?__40883]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__42335)
                        (recur (clojure.core/next search_space__42334))
                        result__42335))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__42332)))]
          (if
           (clojure.core/vector? input__40881)
           (if
            (clojure.core/= (clojure.core/count input__40881) 2)
            (clojure.core/let
             [input__40881_nth_0__
              (clojure.core/nth input__40881 0)
              input__40881_nth_1__
              (clojure.core/nth input__40881 1)]
             (if
              (clojure.core/seq? input__40881_nth_0__)
              (clojure.core/let
               [input__40881_nth_0___l__
                (clojure.core/take 1 input__40881_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__40881_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__40881_nth_0___r__
                  (clojure.core/drop 1 input__40881_nth_0__)]
                 (clojure.core/let
                  [input__40881_nth_0___l___nth_0__
                   (clojure.core/nth input__40881_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__40881_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__41550
                     (clojure.core/namespace
                      input__40881_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__40883 X__41550]
                     (clojure.core/let
                      [X__41552
                       (clojure.core/name
                        input__40881_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__41552
                       ("<>")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__41540 input__40881_nth_1__ ?__40883)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__42194)
                         (clojure.core/let
                          [[?__40883] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__40881)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__40881)
                             2)
                            (clojure.core/let
                             [input__40881_nth_0__
                              (clojure.core/nth input__40881 0)
                              input__40881_nth_1__
                              (clojure.core/nth input__40881 1)]
                             (if
                              (clojure.core/seq? input__40881_nth_0__)
                              (clojure.core/let
                               [input__40881_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__40881_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__40881_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__40881_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__40881_nth_0__)]
                                 (clojure.core/let
                                  [?patterns input__40881_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__40881_nth_0__]
                                   (clojure.core/let
                                    [?env input__40881_nth_1__]
                                    (try
                                     [{:tag :group,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__40949
                                          ['meander.dev.parse.zeta/parse-sequential
                                           (clojure.core/into
                                            []
                                            ?patterns)
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10883__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10883__auto__
                                          0)))}]
                                     (catch
                                      java.lang.Exception
                                      e__11823__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__11823__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__11823__auto__))))))))
                                (state__42194)))
                              (state__42194)))
                            (state__42194))
                           (state__42194)))))
                       (state__42194)))))
                   (state__42194))))
                (state__42194)))
              (state__42194)))
            (state__42194))
           (state__42194))))
        (state__42194
         []
         (clojure.core/letfn
          [(def__41574
            [arg__41597 ?__40884]
            (clojure.core/letfn
             [(state__42337
               []
               (clojure.core/let
                [x__41598 "meander.math.zeta"]
                (if
                 (clojure.core/= ?__40884 x__41598)
                 [?__40884]
                 (state__42338))))
              (state__42338
               []
               (if
                (clojure.core/map? arg__41597)
                (clojure.core/let
                 [VAL__41599 (.valAt arg__41597 :aliases)]
                 (if
                  (clojure.core/map? VAL__41599)
                  (clojure.core/let
                   [X__41601 (clojure.core/set VAL__41599)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__41601))
                    (clojure.core/loop
                     [search_space__42339 (clojure.core/seq X__41601)]
                     (if
                      (clojure.core/seq search_space__42339)
                      (clojure.core/let
                       [elem__41602
                        (clojure.core/first search_space__42339)
                        result__42340
                        (clojure.core/let
                         [elem__41602_nth_0__
                          (clojure.core/nth elem__41602 0)
                          elem__41602_nth_1__
                          (clojure.core/nth elem__41602 1)]
                         (if
                          (clojure.core/symbol? elem__41602_nth_0__)
                          (clojure.core/let
                           [X__41604
                            (clojure.core/name elem__41602_nth_0__)]
                           (if
                            (clojure.core/= ?__40884 X__41604)
                            (if
                             (clojure.core/symbol? elem__41602_nth_1__)
                             (clojure.core/let
                              [X__41606
                               (clojure.core/name elem__41602_nth_1__)]
                              (clojure.core/case
                               X__41606
                               ("meander.math.zeta")
                               [?__40884]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__42340)
                        (recur (clojure.core/next search_space__42339))
                        result__42340))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__42337)))]
          (if
           (clojure.core/vector? input__40881)
           (if
            (clojure.core/= (clojure.core/count input__40881) 2)
            (clojure.core/let
             [input__40881_nth_0__
              (clojure.core/nth input__40881 0)
              input__40881_nth_1__
              (clojure.core/nth input__40881 1)]
             (if
              (clojure.core/seq? input__40881_nth_0__)
              (clojure.core/let
               [input__40881_nth_0___l__
                (clojure.core/take 1 input__40881_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__40881_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__40881_nth_0___r__
                  (clojure.core/drop 1 input__40881_nth_0__)]
                 (clojure.core/let
                  [input__40881_nth_0___l___nth_0__
                   (clojure.core/nth input__40881_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__40881_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__41584
                     (clojure.core/namespace
                      input__40881_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__40884 X__41584]
                     (clojure.core/let
                      [X__41586
                       (clojure.core/name
                        input__40881_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__41586
                       ("+")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__41574 input__40881_nth_1__ ?__40884)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__42195)
                         (clojure.core/let
                          [[?__40884] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__40881)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__40881)
                             2)
                            (clojure.core/let
                             [input__40881_nth_0__
                              (clojure.core/nth input__40881 0)
                              input__40881_nth_1__
                              (clojure.core/nth input__40881 1)]
                             (if
                              (clojure.core/seq? input__40881_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__40881_nth_0__)
                                3)
                               (clojure.core/let
                                [input__40881_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__40881_nth_0__
                                  1)
                                 input__40881_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__40881_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?a input__40881_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?b input__40881_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__40881_nth_0__]
                                   (clojure.core/let
                                    [?env input__40881_nth_1__]
                                    (try
                                     [{:tag :meander.math.zeta/+,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__40949 [?a ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10883__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10883__auto__
                                          0))),
                                       :right
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__40949 [?b ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10883__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10883__auto__
                                          0)))}]
                                     (catch
                                      java.lang.Exception
                                      e__11823__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__11823__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__11823__auto__)))))))))
                               (state__42195))
                              (state__42195)))
                            (state__42195))
                           (state__42195)))))
                       (state__42195)))))
                   (state__42195))))
                (state__42195)))
              (state__42195)))
            (state__42195))
           (state__42195))))
        (state__42195
         []
         (clojure.core/letfn
          [(def__41608
            [arg__41631 ?__40885]
            (clojure.core/letfn
             [(state__42342
               []
               (clojure.core/let
                [x__41632 "meander.zeta"]
                (if
                 (clojure.core/= ?__40885 x__41632)
                 [?__40885]
                 (state__42343))))
              (state__42343
               []
               (if
                (clojure.core/map? arg__41631)
                (clojure.core/let
                 [VAL__41633 (.valAt arg__41631 :aliases)]
                 (if
                  (clojure.core/map? VAL__41633)
                  (clojure.core/let
                   [X__41635 (clojure.core/set VAL__41633)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__41635))
                    (clojure.core/loop
                     [search_space__42344 (clojure.core/seq X__41635)]
                     (if
                      (clojure.core/seq search_space__42344)
                      (clojure.core/let
                       [elem__41636
                        (clojure.core/first search_space__42344)
                        result__42345
                        (clojure.core/let
                         [elem__41636_nth_0__
                          (clojure.core/nth elem__41636 0)
                          elem__41636_nth_1__
                          (clojure.core/nth elem__41636 1)]
                         (if
                          (clojure.core/symbol? elem__41636_nth_0__)
                          (clojure.core/let
                           [X__41638
                            (clojure.core/name elem__41636_nth_0__)]
                           (if
                            (clojure.core/= ?__40885 X__41638)
                            (if
                             (clojure.core/symbol? elem__41636_nth_1__)
                             (clojure.core/let
                              [X__41640
                               (clojure.core/name elem__41636_nth_1__)]
                              (clojure.core/case
                               X__41640
                               ("meander.zeta")
                               [?__40885]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__42345)
                        (recur (clojure.core/next search_space__42344))
                        result__42345))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__42342)))]
          (if
           (clojure.core/vector? input__40881)
           (if
            (clojure.core/= (clojure.core/count input__40881) 2)
            (clojure.core/let
             [input__40881_nth_0__
              (clojure.core/nth input__40881 0)
              input__40881_nth_1__
              (clojure.core/nth input__40881 1)]
             (if
              (clojure.core/seq? input__40881_nth_0__)
              (clojure.core/let
               [input__40881_nth_0___l__
                (clojure.core/take 1 input__40881_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__40881_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__40881_nth_0___r__
                  (clojure.core/drop 1 input__40881_nth_0__)]
                 (clojure.core/let
                  [input__40881_nth_0___l___nth_0__
                   (clojure.core/nth input__40881_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__40881_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__41618
                     (clojure.core/namespace
                      input__40881_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__40885 X__41618]
                     (clojure.core/let
                      [X__41620
                       (clojure.core/name
                        input__40881_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__41620
                       ("with")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__41608 input__40881_nth_1__ ?__40885)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__42196)
                         (clojure.core/let
                          [[?__40885] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__40881)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__40881)
                             2)
                            (clojure.core/let
                             [input__40881_nth_0__
                              (clojure.core/nth input__40881 0)
                              input__40881_nth_1__
                              (clojure.core/nth input__40881 1)]
                             (if
                              (clojure.core/seq? input__40881_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__40881_nth_0__)
                                3)
                               (clojure.core/let
                                [input__40881_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__40881_nth_0__
                                  1)
                                 input__40881_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__40881_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?bindings
                                  input__40881_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?body input__40881_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__40881_nth_0__]
                                   (clojure.core/let
                                    [?env input__40881_nth_1__]
                                    (try
                                     [{:tag :with,
                                       :bindings
                                       {:tag :with-bindings,
                                        :bindings
                                        (clojure.core/let
                                         [CATA_RESULT__10883__auto__
                                          (CATA__FN__40949
                                           ['meander.dev.parse.zeta/parse-with-bindings
                                            ?bindings
                                            ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__10883__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__10883__auto__
                                           0)))},
                                       :body
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__40949
                                          [?body ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10883__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10883__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__11823__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__11823__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__11823__auto__)))))))))
                               (state__42196))
                              (state__42196)))
                            (state__42196))
                           (state__42196)))))
                       (state__42196)))))
                   (state__42196))))
                (state__42196)))
              (state__42196)))
            (state__42196))
           (state__42196))))
        (state__42196
         []
         (clojure.core/letfn
          [(def__41642
            [arg__41665 ?__40886]
            (clojure.core/letfn
             [(state__42347
               []
               (clojure.core/let
                [x__41666 "meander.zeta"]
                (if
                 (clojure.core/= ?__40886 x__41666)
                 [?__40886]
                 (state__42348))))
              (state__42348
               []
               (if
                (clojure.core/map? arg__41665)
                (clojure.core/let
                 [VAL__41667 (.valAt arg__41665 :aliases)]
                 (if
                  (clojure.core/map? VAL__41667)
                  (clojure.core/let
                   [X__41669 (clojure.core/set VAL__41667)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__41669))
                    (clojure.core/loop
                     [search_space__42349 (clojure.core/seq X__41669)]
                     (if
                      (clojure.core/seq search_space__42349)
                      (clojure.core/let
                       [elem__41670
                        (clojure.core/first search_space__42349)
                        result__42350
                        (clojure.core/let
                         [elem__41670_nth_0__
                          (clojure.core/nth elem__41670 0)
                          elem__41670_nth_1__
                          (clojure.core/nth elem__41670 1)]
                         (if
                          (clojure.core/symbol? elem__41670_nth_0__)
                          (clojure.core/let
                           [X__41672
                            (clojure.core/name elem__41670_nth_0__)]
                           (if
                            (clojure.core/= ?__40886 X__41672)
                            (if
                             (clojure.core/symbol? elem__41670_nth_1__)
                             (clojure.core/let
                              [X__41674
                               (clojure.core/name elem__41670_nth_1__)]
                              (clojure.core/case
                               X__41674
                               ("meander.zeta")
                               [?__40886]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__42350)
                        (recur (clojure.core/next search_space__42349))
                        result__42350))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__42347)))]
          (if
           (clojure.core/vector? input__40881)
           (if
            (clojure.core/= (clojure.core/count input__40881) 2)
            (clojure.core/let
             [input__40881_nth_0__
              (clojure.core/nth input__40881 0)
              input__40881_nth_1__
              (clojure.core/nth input__40881 1)]
             (if
              (clojure.core/seq? input__40881_nth_0__)
              (clojure.core/let
               [input__40881_nth_0___l__
                (clojure.core/take 1 input__40881_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__40881_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__40881_nth_0___r__
                  (clojure.core/drop 1 input__40881_nth_0__)]
                 (clojure.core/let
                  [input__40881_nth_0___l___nth_0__
                   (clojure.core/nth input__40881_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__40881_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__41652
                     (clojure.core/namespace
                      input__40881_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__40886 X__41652]
                     (clojure.core/let
                      [X__41654
                       (clojure.core/name
                        input__40881_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__41654
                       ("apply")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__41642 input__40881_nth_1__ ?__40886)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__42197)
                         (clojure.core/let
                          [[?__40886] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__40881)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__40881)
                             2)
                            (clojure.core/let
                             [input__40881_nth_0__
                              (clojure.core/nth input__40881 0)
                              input__40881_nth_1__
                              (clojure.core/nth input__40881 1)]
                             (if
                              (clojure.core/seq? input__40881_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__40881_nth_0__)
                                3)
                               (clojure.core/let
                                [input__40881_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__40881_nth_0__
                                  1)
                                 input__40881_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__40881_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?fn input__40881_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?pattern
                                   input__40881_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__40881_nth_0__]
                                   (clojure.core/let
                                    [?env input__40881_nth_1__]
                                    (try
                                     [{:tag :apply,
                                       :fn ?fn,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__40949
                                          [?pattern ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10883__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10883__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__11823__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__11823__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__11823__auto__)))))))))
                               (state__42197))
                              (state__42197)))
                            (state__42197))
                           (state__42197)))))
                       (state__42197)))))
                   (state__42197))))
                (state__42197)))
              (state__42197)))
            (state__42197))
           (state__42197))))
        (state__42197
         []
         (clojure.core/letfn
          [(def__41676
            [arg__41699 ?__40887]
            (clojure.core/letfn
             [(state__42352
               []
               (clojure.core/let
                [x__41700 "meander.zeta"]
                (if
                 (clojure.core/= ?__40887 x__41700)
                 [?__40887]
                 (state__42353))))
              (state__42353
               []
               (if
                (clojure.core/map? arg__41699)
                (clojure.core/let
                 [VAL__41701 (.valAt arg__41699 :aliases)]
                 (if
                  (clojure.core/map? VAL__41701)
                  (clojure.core/let
                   [X__41703 (clojure.core/set VAL__41701)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__41703))
                    (clojure.core/loop
                     [search_space__42354 (clojure.core/seq X__41703)]
                     (if
                      (clojure.core/seq search_space__42354)
                      (clojure.core/let
                       [elem__41704
                        (clojure.core/first search_space__42354)
                        result__42355
                        (clojure.core/let
                         [elem__41704_nth_0__
                          (clojure.core/nth elem__41704 0)
                          elem__41704_nth_1__
                          (clojure.core/nth elem__41704 1)]
                         (if
                          (clojure.core/symbol? elem__41704_nth_0__)
                          (clojure.core/let
                           [X__41706
                            (clojure.core/name elem__41704_nth_0__)]
                           (if
                            (clojure.core/= ?__40887 X__41706)
                            (if
                             (clojure.core/symbol? elem__41704_nth_1__)
                             (clojure.core/let
                              [X__41708
                               (clojure.core/name elem__41704_nth_1__)]
                              (clojure.core/case
                               X__41708
                               ("meander.zeta")
                               [?__40887]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__42355)
                        (recur (clojure.core/next search_space__42354))
                        result__42355))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__42352)))]
          (if
           (clojure.core/vector? input__40881)
           (if
            (clojure.core/= (clojure.core/count input__40881) 2)
            (clojure.core/let
             [input__40881_nth_0__
              (clojure.core/nth input__40881 0)
              input__40881_nth_1__
              (clojure.core/nth input__40881 1)]
             (if
              (clojure.core/seq? input__40881_nth_0__)
              (clojure.core/let
               [input__40881_nth_0___l__
                (clojure.core/take 1 input__40881_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__40881_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__40881_nth_0___r__
                  (clojure.core/drop 1 input__40881_nth_0__)]
                 (clojure.core/let
                  [input__40881_nth_0___l___nth_0__
                   (clojure.core/nth input__40881_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__40881_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__41686
                     (clojure.core/namespace
                      input__40881_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__40887 X__41686]
                     (clojure.core/let
                      [X__41688
                       (clojure.core/name
                        input__40881_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__41688
                       ("and")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__41676 input__40881_nth_1__ ?__40887)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__42198)
                         (clojure.core/let
                          [[?__40887] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__40881)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__40881)
                             2)
                            (clojure.core/let
                             [input__40881_nth_0__
                              (clojure.core/nth input__40881 0)
                              input__40881_nth_1__
                              (clojure.core/nth input__40881 1)]
                             (if
                              (clojure.core/seq? input__40881_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__40881_nth_0__)
                                3)
                               (clojure.core/let
                                [input__40881_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__40881_nth_0__
                                  1)
                                 input__40881_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__40881_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?left input__40881_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?right input__40881_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__40881_nth_0__]
                                   (clojure.core/let
                                    [?env input__40881_nth_1__]
                                    (try
                                     [{:tag :and,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__40949
                                          [?left ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10883__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10883__auto__
                                          0))),
                                       :right
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__40949
                                          [?right ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10883__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10883__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__11823__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__11823__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__11823__auto__)))))))))
                               (state__42198))
                              (state__42198)))
                            (state__42198))
                           (state__42198)))))
                       (state__42198)))))
                   (state__42198))))
                (state__42198)))
              (state__42198)))
            (state__42198))
           (state__42198))))
        (state__42198
         []
         (clojure.core/letfn
          [(def__41710
            [arg__41733 ?__40888]
            (clojure.core/letfn
             [(state__42357
               []
               (clojure.core/let
                [x__41734 "meander.zeta"]
                (if
                 (clojure.core/= ?__40888 x__41734)
                 [?__40888]
                 (state__42358))))
              (state__42358
               []
               (if
                (clojure.core/map? arg__41733)
                (clojure.core/let
                 [VAL__41735 (.valAt arg__41733 :aliases)]
                 (if
                  (clojure.core/map? VAL__41735)
                  (clojure.core/let
                   [X__41737 (clojure.core/set VAL__41735)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__41737))
                    (clojure.core/loop
                     [search_space__42359 (clojure.core/seq X__41737)]
                     (if
                      (clojure.core/seq search_space__42359)
                      (clojure.core/let
                       [elem__41738
                        (clojure.core/first search_space__42359)
                        result__42360
                        (clojure.core/let
                         [elem__41738_nth_0__
                          (clojure.core/nth elem__41738 0)
                          elem__41738_nth_1__
                          (clojure.core/nth elem__41738 1)]
                         (if
                          (clojure.core/symbol? elem__41738_nth_0__)
                          (clojure.core/let
                           [X__41740
                            (clojure.core/name elem__41738_nth_0__)]
                           (if
                            (clojure.core/= ?__40888 X__41740)
                            (if
                             (clojure.core/symbol? elem__41738_nth_1__)
                             (clojure.core/let
                              [X__41742
                               (clojure.core/name elem__41738_nth_1__)]
                              (clojure.core/case
                               X__41742
                               ("meander.zeta")
                               [?__40888]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__42360)
                        (recur (clojure.core/next search_space__42359))
                        result__42360))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__42357)))]
          (if
           (clojure.core/vector? input__40881)
           (if
            (clojure.core/= (clojure.core/count input__40881) 2)
            (clojure.core/let
             [input__40881_nth_0__
              (clojure.core/nth input__40881 0)
              input__40881_nth_1__
              (clojure.core/nth input__40881 1)]
             (if
              (clojure.core/seq? input__40881_nth_0__)
              (clojure.core/let
               [input__40881_nth_0___l__
                (clojure.core/take 1 input__40881_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__40881_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__40881_nth_0___r__
                  (clojure.core/drop 1 input__40881_nth_0__)]
                 (clojure.core/let
                  [input__40881_nth_0___l___nth_0__
                   (clojure.core/nth input__40881_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__40881_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__41720
                     (clojure.core/namespace
                      input__40881_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__40888 X__41720]
                     (clojure.core/let
                      [X__41722
                       (clojure.core/name
                        input__40881_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__41722
                       ("cata")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__41710 input__40881_nth_1__ ?__40888)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__42199)
                         (clojure.core/let
                          [[?__40888] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__40881)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__40881)
                             2)
                            (clojure.core/let
                             [input__40881_nth_0__
                              (clojure.core/nth input__40881 0)
                              input__40881_nth_1__
                              (clojure.core/nth input__40881 1)]
                             (if
                              (clojure.core/seq? input__40881_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__40881_nth_0__)
                                2)
                               (clojure.core/let
                                [input__40881_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__40881_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__40881_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__40881_nth_0__]
                                  (clojure.core/let
                                   [?env input__40881_nth_1__]
                                   (try
                                    [{:tag :cata,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__10883__auto__
                                        (CATA__FN__40949
                                         [?pattern ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__10883__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__10883__auto__
                                         0))),
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__11823__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__11823__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__11823__auto__))))))))
                               (state__42199))
                              (state__42199)))
                            (state__42199))
                           (state__42199)))))
                       (state__42199)))))
                   (state__42199))))
                (state__42199)))
              (state__42199)))
            (state__42199))
           (state__42199))))
        (state__42199
         []
         (clojure.core/letfn
          [(def__41744
            [arg__41767 ?__40889]
            (clojure.core/letfn
             [(state__42362
               []
               (clojure.core/let
                [x__41768 "meander.zeta"]
                (if
                 (clojure.core/= ?__40889 x__41768)
                 [?__40889]
                 (state__42363))))
              (state__42363
               []
               (if
                (clojure.core/map? arg__41767)
                (clojure.core/let
                 [VAL__41769 (.valAt arg__41767 :aliases)]
                 (if
                  (clojure.core/map? VAL__41769)
                  (clojure.core/let
                   [X__41771 (clojure.core/set VAL__41769)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__41771))
                    (clojure.core/loop
                     [search_space__42364 (clojure.core/seq X__41771)]
                     (if
                      (clojure.core/seq search_space__42364)
                      (clojure.core/let
                       [elem__41772
                        (clojure.core/first search_space__42364)
                        result__42365
                        (clojure.core/let
                         [elem__41772_nth_0__
                          (clojure.core/nth elem__41772 0)
                          elem__41772_nth_1__
                          (clojure.core/nth elem__41772 1)]
                         (if
                          (clojure.core/symbol? elem__41772_nth_0__)
                          (clojure.core/let
                           [X__41774
                            (clojure.core/name elem__41772_nth_0__)]
                           (if
                            (clojure.core/= ?__40889 X__41774)
                            (if
                             (clojure.core/symbol? elem__41772_nth_1__)
                             (clojure.core/let
                              [X__41776
                               (clojure.core/name elem__41772_nth_1__)]
                              (clojure.core/case
                               X__41776
                               ("meander.zeta")
                               [?__40889]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__42365)
                        (recur (clojure.core/next search_space__42364))
                        result__42365))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__42362)))]
          (if
           (clojure.core/vector? input__40881)
           (if
            (clojure.core/= (clojure.core/count input__40881) 2)
            (clojure.core/let
             [input__40881_nth_0__
              (clojure.core/nth input__40881 0)
              input__40881_nth_1__
              (clojure.core/nth input__40881 1)]
             (if
              (clojure.core/seq? input__40881_nth_0__)
              (clojure.core/let
               [input__40881_nth_0___l__
                (clojure.core/take 1 input__40881_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__40881_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__40881_nth_0___r__
                  (clojure.core/drop 1 input__40881_nth_0__)]
                 (clojure.core/let
                  [input__40881_nth_0___l___nth_0__
                   (clojure.core/nth input__40881_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__40881_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__41754
                     (clojure.core/namespace
                      input__40881_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__40889 X__41754]
                     (clojure.core/let
                      [X__41756
                       (clojure.core/name
                        input__40881_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__41756
                       ("fold")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__41744 input__40881_nth_1__ ?__40889)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__42200)
                         (clojure.core/let
                          [[?__40889] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__40881)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__40881)
                             2)
                            (clojure.core/let
                             [input__40881_nth_0__
                              (clojure.core/nth input__40881 0)
                              input__40881_nth_1__
                              (clojure.core/nth input__40881 1)]
                             (if
                              (clojure.core/seq? input__40881_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 4)
                                 input__40881_nth_0__)
                                4)
                               (clojure.core/let
                                [input__40881_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__40881_nth_0__
                                  1)
                                 input__40881_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__40881_nth_0__
                                  2)
                                 input__40881_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__40881_nth_0__
                                  3)]
                                (clojure.core/let
                                 [?mutable-variable
                                  input__40881_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?initial-value
                                   input__40881_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?fold-function
                                    input__40881_nth_0___nth_3__]
                                   (clojure.core/let
                                    [?form input__40881_nth_0__]
                                    (clojure.core/let
                                     [?env input__40881_nth_1__]
                                     (try
                                      [(clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__40949
                                          ['meander.dev.parse.zeta/make-fold
                                           (clojure.core/let
                                            [CATA_RESULT__10883__auto__
                                             (CATA__FN__40949
                                              [?mutable-variable
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__10883__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__10883__auto__
                                              0)))
                                           (clojure.core/let
                                            [CATA_RESULT__10883__auto__
                                             (CATA__FN__40949
                                              [?initial-value ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__10883__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__10883__auto__
                                              0)))
                                           ?fold-function
                                           ?form])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10883__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10883__auto__
                                          0)))]
                                      (catch
                                       java.lang.Exception
                                       e__11823__auto__
                                       (if
                                        (meander.runtime.zeta/fail?
                                         e__11823__auto__)
                                        (meander.runtime.zeta/fail)
                                        (throw
                                         e__11823__auto__))))))))))
                               (state__42200))
                              (state__42200)))
                            (state__42200))
                           (state__42200)))))
                       (state__42200)))))
                   (state__42200))))
                (state__42200)))
              (state__42200)))
            (state__42200))
           (state__42200))))
        (state__42200
         []
         (if
          (clojure.core/vector? input__40881)
          (if
           (clojure.core/= (clojure.core/count input__40881) 5)
           (clojure.core/let
            [input__40881_nth_0__
             (clojure.core/nth input__40881 0)
             input__40881_nth_1__
             (clojure.core/nth input__40881 1)
             input__40881_nth_2__
             (clojure.core/nth input__40881 2)
             input__40881_nth_3__
             (clojure.core/nth input__40881 3)
             input__40881_nth_4__
             (clojure.core/nth input__40881 4)]
            (clojure.core/case
             input__40881_nth_0__
             (meander.dev.parse.zeta/make-fold)
             (if
              (clojure.core/map? input__40881_nth_1__)
              (clojure.core/let
               [VAL__41779 (.valAt input__40881_nth_1__ :tag)]
               (clojure.core/case
                VAL__41779
                (:mutable-variable)
                (clojure.core/let
                 [?variable-ast input__40881_nth_1__]
                 (clojure.core/let
                  [?initial-value-ast input__40881_nth_2__]
                  (clojure.core/let
                   [?fold-function input__40881_nth_3__]
                   (clojure.core/let
                    [?form input__40881_nth_4__]
                    (try
                     [{:tag :fold,
                       :variable ?variable-ast,
                       :initial-value ?initial-value-ast,
                       :fold-function
                       {:tag :host-expression, :form ?fold-function},
                       :form ?form}]
                     (catch
                      java.lang.Exception
                      e__11823__auto__
                      (if
                       (meander.runtime.zeta/fail? e__11823__auto__)
                       (meander.runtime.zeta/fail)
                       (throw e__11823__auto__))))))))
                (state__42201)))
              (state__42201))
             (state__42201)))
           (state__42201))
          (state__42201)))
        (state__42201
         []
         (clojure.core/letfn
          [(def__41781
            [arg__41804 ?__40890]
            (clojure.core/letfn
             [(state__42367
               []
               (clojure.core/let
                [x__41805 "meander.zeta"]
                (if
                 (clojure.core/= ?__40890 x__41805)
                 [?__40890]
                 (state__42368))))
              (state__42368
               []
               (if
                (clojure.core/map? arg__41804)
                (clojure.core/let
                 [VAL__41806 (.valAt arg__41804 :aliases)]
                 (if
                  (clojure.core/map? VAL__41806)
                  (clojure.core/let
                   [X__41808 (clojure.core/set VAL__41806)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__41808))
                    (clojure.core/loop
                     [search_space__42369 (clojure.core/seq X__41808)]
                     (if
                      (clojure.core/seq search_space__42369)
                      (clojure.core/let
                       [elem__41809
                        (clojure.core/first search_space__42369)
                        result__42370
                        (clojure.core/let
                         [elem__41809_nth_0__
                          (clojure.core/nth elem__41809 0)
                          elem__41809_nth_1__
                          (clojure.core/nth elem__41809 1)]
                         (if
                          (clojure.core/symbol? elem__41809_nth_0__)
                          (clojure.core/let
                           [X__41811
                            (clojure.core/name elem__41809_nth_0__)]
                           (if
                            (clojure.core/= ?__40890 X__41811)
                            (if
                             (clojure.core/symbol? elem__41809_nth_1__)
                             (clojure.core/let
                              [X__41813
                               (clojure.core/name elem__41809_nth_1__)]
                              (clojure.core/case
                               X__41813
                               ("meander.zeta")
                               [?__40890]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__42370)
                        (recur (clojure.core/next search_space__42369))
                        result__42370))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__42367)))]
          (if
           (clojure.core/vector? input__40881)
           (if
            (clojure.core/= (clojure.core/count input__40881) 2)
            (clojure.core/let
             [input__40881_nth_0__
              (clojure.core/nth input__40881 0)
              input__40881_nth_1__
              (clojure.core/nth input__40881 1)]
             (if
              (clojure.core/seq? input__40881_nth_0__)
              (clojure.core/let
               [input__40881_nth_0___l__
                (clojure.core/take 1 input__40881_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__40881_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__40881_nth_0___r__
                  (clojure.core/drop 1 input__40881_nth_0__)]
                 (clojure.core/let
                  [input__40881_nth_0___l___nth_0__
                   (clojure.core/nth input__40881_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__40881_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__41791
                     (clojure.core/namespace
                      input__40881_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__40890 X__41791]
                     (clojure.core/let
                      [X__41793
                       (clojure.core/name
                        input__40881_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__41793
                       ("let")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__41781 input__40881_nth_1__ ?__40890)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__42202)
                         (clojure.core/let
                          [[?__40890] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__40881)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__40881)
                             2)
                            (clojure.core/let
                             [input__40881_nth_0__
                              (clojure.core/nth input__40881 0)
                              input__40881_nth_1__
                              (clojure.core/nth input__40881 1)]
                             (if
                              (clojure.core/seq? input__40881_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__40881_nth_0__)
                                3)
                               (clojure.core/let
                                [input__40881_nth_0___nth_0__
                                 (clojure.core/nth
                                  input__40881_nth_0__
                                  0)
                                 input__40881_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__40881_nth_0__
                                  1)
                                 input__40881_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__40881_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?pattern
                                  input__40881_nth_0___nth_0__]
                                 (clojure.core/let
                                  [?expression
                                   input__40881_nth_0___nth_1__]
                                  (clojure.core/let
                                   [?next input__40881_nth_0___nth_2__]
                                   (clojure.core/let
                                    [?form input__40881_nth_0__]
                                    (clojure.core/let
                                     [?env input__40881_nth_1__]
                                     (try
                                      [{:tag :let,
                                        :pattern
                                        (clojure.core/let
                                         [CATA_RESULT__10883__auto__
                                          (CATA__FN__40949
                                           [?pattern ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__10883__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__10883__auto__
                                           0))),
                                        :expression
                                        {:tag :host-expression,
                                         :form ?expression},
                                        :next
                                        (clojure.core/let
                                         [CATA_RESULT__10883__auto__
                                          (CATA__FN__40949
                                           [?next ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__10883__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__10883__auto__
                                           0)))}]
                                      (catch
                                       java.lang.Exception
                                       e__11823__auto__
                                       (if
                                        (meander.runtime.zeta/fail?
                                         e__11823__auto__)
                                        (meander.runtime.zeta/fail)
                                        (throw
                                         e__11823__auto__))))))))))
                               (state__42202))
                              (state__42202)))
                            (state__42202))
                           (state__42202)))))
                       (state__42202)))))
                   (state__42202))))
                (state__42202)))
              (state__42202)))
            (state__42202))
           (state__42202))))
        (state__42202
         []
         (clojure.core/letfn
          [(def__41815
            [arg__41838 ?__40891]
            (clojure.core/letfn
             [(state__42372
               []
               (clojure.core/let
                [x__41839 "meander.zeta"]
                (if
                 (clojure.core/= ?__40891 x__41839)
                 [?__40891]
                 (state__42373))))
              (state__42373
               []
               (if
                (clojure.core/map? arg__41838)
                (clojure.core/let
                 [VAL__41840 (.valAt arg__41838 :aliases)]
                 (if
                  (clojure.core/map? VAL__41840)
                  (clojure.core/let
                   [X__41842 (clojure.core/set VAL__41840)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__41842))
                    (clojure.core/loop
                     [search_space__42374 (clojure.core/seq X__41842)]
                     (if
                      (clojure.core/seq search_space__42374)
                      (clojure.core/let
                       [elem__41843
                        (clojure.core/first search_space__42374)
                        result__42375
                        (clojure.core/let
                         [elem__41843_nth_0__
                          (clojure.core/nth elem__41843 0)
                          elem__41843_nth_1__
                          (clojure.core/nth elem__41843 1)]
                         (if
                          (clojure.core/symbol? elem__41843_nth_0__)
                          (clojure.core/let
                           [X__41845
                            (clojure.core/name elem__41843_nth_0__)]
                           (if
                            (clojure.core/= ?__40891 X__41845)
                            (if
                             (clojure.core/symbol? elem__41843_nth_1__)
                             (clojure.core/let
                              [X__41847
                               (clojure.core/name elem__41843_nth_1__)]
                              (clojure.core/case
                               X__41847
                               ("meander.zeta")
                               [?__40891]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__42375)
                        (recur (clojure.core/next search_space__42374))
                        result__42375))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__42372)))]
          (if
           (clojure.core/vector? input__40881)
           (if
            (clojure.core/= (clojure.core/count input__40881) 2)
            (clojure.core/let
             [input__40881_nth_0__
              (clojure.core/nth input__40881 0)
              input__40881_nth_1__
              (clojure.core/nth input__40881 1)]
             (if
              (clojure.core/seq? input__40881_nth_0__)
              (clojure.core/let
               [input__40881_nth_0___l__
                (clojure.core/take 1 input__40881_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__40881_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__40881_nth_0___r__
                  (clojure.core/drop 1 input__40881_nth_0__)]
                 (clojure.core/let
                  [input__40881_nth_0___l___nth_0__
                   (clojure.core/nth input__40881_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__40881_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__41825
                     (clojure.core/namespace
                      input__40881_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__40891 X__41825]
                     (clojure.core/let
                      [X__41827
                       (clojure.core/name
                        input__40881_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__41827
                       ("not")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__41815 input__40881_nth_1__ ?__40891)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__42203)
                         (clojure.core/let
                          [[?__40891] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__40881)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__40881)
                             2)
                            (clojure.core/let
                             [input__40881_nth_0__
                              (clojure.core/nth input__40881 0)
                              input__40881_nth_1__
                              (clojure.core/nth input__40881 1)]
                             (if
                              (clojure.core/seq? input__40881_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__40881_nth_0__)
                                2)
                               (clojure.core/let
                                [input__40881_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__40881_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__40881_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__40881_nth_0__]
                                  (clojure.core/let
                                   [?env input__40881_nth_1__]
                                   (try
                                    [{:tag :not,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__10883__auto__
                                        (CATA__FN__40949
                                         [?pattern ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__10883__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__10883__auto__
                                         0))),
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__11823__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__11823__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__11823__auto__))))))))
                               (state__42203))
                              (state__42203)))
                            (state__42203))
                           (state__42203)))))
                       (state__42203)))))
                   (state__42203))))
                (state__42203)))
              (state__42203)))
            (state__42203))
           (state__42203))))
        (state__42203
         []
         (clojure.core/letfn
          [(def__41849
            [arg__41872 ?__40892]
            (clojure.core/letfn
             [(state__42377
               []
               (clojure.core/let
                [x__41873 "meander.zeta"]
                (if
                 (clojure.core/= ?__40892 x__41873)
                 [?__40892]
                 (state__42378))))
              (state__42378
               []
               (if
                (clojure.core/map? arg__41872)
                (clojure.core/let
                 [VAL__41874 (.valAt arg__41872 :aliases)]
                 (if
                  (clojure.core/map? VAL__41874)
                  (clojure.core/let
                   [X__41876 (clojure.core/set VAL__41874)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__41876))
                    (clojure.core/loop
                     [search_space__42379 (clojure.core/seq X__41876)]
                     (if
                      (clojure.core/seq search_space__42379)
                      (clojure.core/let
                       [elem__41877
                        (clojure.core/first search_space__42379)
                        result__42380
                        (clojure.core/let
                         [elem__41877_nth_0__
                          (clojure.core/nth elem__41877 0)
                          elem__41877_nth_1__
                          (clojure.core/nth elem__41877 1)]
                         (if
                          (clojure.core/symbol? elem__41877_nth_0__)
                          (clojure.core/let
                           [X__41879
                            (clojure.core/name elem__41877_nth_0__)]
                           (if
                            (clojure.core/= ?__40892 X__41879)
                            (if
                             (clojure.core/symbol? elem__41877_nth_1__)
                             (clojure.core/let
                              [X__41881
                               (clojure.core/name elem__41877_nth_1__)]
                              (clojure.core/case
                               X__41881
                               ("meander.zeta")
                               [?__40892]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__42380)
                        (recur (clojure.core/next search_space__42379))
                        result__42380))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__42377)))]
          (if
           (clojure.core/vector? input__40881)
           (if
            (clojure.core/= (clojure.core/count input__40881) 2)
            (clojure.core/let
             [input__40881_nth_0__
              (clojure.core/nth input__40881 0)
              input__40881_nth_1__
              (clojure.core/nth input__40881 1)]
             (if
              (clojure.core/seq? input__40881_nth_0__)
              (clojure.core/let
               [input__40881_nth_0___l__
                (clojure.core/take 1 input__40881_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__40881_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__40881_nth_0___r__
                  (clojure.core/drop 1 input__40881_nth_0__)]
                 (clojure.core/let
                  [input__40881_nth_0___l___nth_0__
                   (clojure.core/nth input__40881_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__40881_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__41859
                     (clojure.core/namespace
                      input__40881_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__40892 X__41859]
                     (clojure.core/let
                      [X__41861
                       (clojure.core/name
                        input__40881_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__41861
                       ("or")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__41849 input__40881_nth_1__ ?__40892)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__42204)
                         (clojure.core/let
                          [[?__40892] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__40881)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__40881)
                             2)
                            (clojure.core/let
                             [input__40881_nth_0__
                              (clojure.core/nth input__40881 0)
                              input__40881_nth_1__
                              (clojure.core/nth input__40881 1)]
                             (if
                              (clojure.core/seq? input__40881_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__40881_nth_0__)
                                3)
                               (clojure.core/let
                                [input__40881_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__40881_nth_0__
                                  1)
                                 input__40881_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__40881_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?left input__40881_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?right input__40881_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__40881_nth_0__]
                                   (clojure.core/let
                                    [?env input__40881_nth_1__]
                                    (try
                                     [{:tag :or,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__40949
                                          [?left ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10883__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10883__auto__
                                          0))),
                                       :right
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__40949
                                          [?right ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10883__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10883__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__11823__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__11823__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__11823__auto__)))))))))
                               (state__42204))
                              (state__42204)))
                            (state__42204))
                           (state__42204)))))
                       (state__42204)))))
                   (state__42204))))
                (state__42204)))
              (state__42204)))
            (state__42204))
           (state__42204))))
        (state__42204
         []
         (clojure.core/letfn
          [(def__41883
            [arg__41906 ?__40893]
            (clojure.core/letfn
             [(state__42382
               []
               (clojure.core/let
                [x__41907 "meander.zeta"]
                (if
                 (clojure.core/= ?__40893 x__41907)
                 [?__40893]
                 (state__42383))))
              (state__42383
               []
               (if
                (clojure.core/map? arg__41906)
                (clojure.core/let
                 [VAL__41908 (.valAt arg__41906 :aliases)]
                 (if
                  (clojure.core/map? VAL__41908)
                  (clojure.core/let
                   [X__41910 (clojure.core/set VAL__41908)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__41910))
                    (clojure.core/loop
                     [search_space__42384 (clojure.core/seq X__41910)]
                     (if
                      (clojure.core/seq search_space__42384)
                      (clojure.core/let
                       [elem__41911
                        (clojure.core/first search_space__42384)
                        result__42385
                        (clojure.core/let
                         [elem__41911_nth_0__
                          (clojure.core/nth elem__41911 0)
                          elem__41911_nth_1__
                          (clojure.core/nth elem__41911 1)]
                         (if
                          (clojure.core/symbol? elem__41911_nth_0__)
                          (clojure.core/let
                           [X__41913
                            (clojure.core/name elem__41911_nth_0__)]
                           (if
                            (clojure.core/= ?__40893 X__41913)
                            (if
                             (clojure.core/symbol? elem__41911_nth_1__)
                             (clojure.core/let
                              [X__41915
                               (clojure.core/name elem__41911_nth_1__)]
                              (clojure.core/case
                               X__41915
                               ("meander.zeta")
                               [?__40893]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__42385)
                        (recur (clojure.core/next search_space__42384))
                        result__42385))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__42382)))]
          (if
           (clojure.core/vector? input__40881)
           (if
            (clojure.core/= (clojure.core/count input__40881) 2)
            (clojure.core/let
             [input__40881_nth_0__
              (clojure.core/nth input__40881 0)
              input__40881_nth_1__
              (clojure.core/nth input__40881 1)]
             (if
              (clojure.core/seq? input__40881_nth_0__)
              (clojure.core/let
               [input__40881_nth_0___l__
                (clojure.core/take 1 input__40881_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__40881_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__40881_nth_0___r__
                  (clojure.core/drop 1 input__40881_nth_0__)]
                 (clojure.core/let
                  [input__40881_nth_0___l___nth_0__
                   (clojure.core/nth input__40881_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__40881_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__41893
                     (clojure.core/namespace
                      input__40881_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__40893 X__41893]
                     (clojure.core/let
                      [X__41895
                       (clojure.core/name
                        input__40881_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__41895
                       ("pred")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__41883 input__40881_nth_1__ ?__40893)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__42205)
                         (clojure.core/let
                          [[?__40893] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__40881)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__40881)
                             2)
                            (clojure.core/let
                             [input__40881_nth_0__
                              (clojure.core/nth input__40881 0)
                              input__40881_nth_1__
                              (clojure.core/nth input__40881 1)]
                             (if
                              (clojure.core/seq? input__40881_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__40881_nth_0__)
                                2)
                               (clojure.core/let
                                [input__40881_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__40881_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?expression
                                  input__40881_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__40881_nth_0__]
                                  (clojure.core/let
                                   [?env input__40881_nth_1__]
                                   (try
                                    [{:tag :pred,
                                      :expression
                                      {:tag :host-expression,
                                       :form ?expression},
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__11823__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__11823__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__11823__auto__))))))))
                               (state__42205))
                              (state__42205)))
                            (state__42205))
                           (state__42205)))))
                       (state__42205)))))
                   (state__42205))))
                (state__42205)))
              (state__42205)))
            (state__42205))
           (state__42205))))
        (state__42205
         []
         (clojure.core/letfn
          [(def__41917
            [arg__41940 ?__40894]
            (clojure.core/letfn
             [(state__42387
               []
               (clojure.core/let
                [x__41941 "meander.zeta"]
                (if
                 (clojure.core/= ?__40894 x__41941)
                 [?__40894]
                 (state__42388))))
              (state__42388
               []
               (if
                (clojure.core/map? arg__41940)
                (clojure.core/let
                 [VAL__41942 (.valAt arg__41940 :aliases)]
                 (if
                  (clojure.core/map? VAL__41942)
                  (clojure.core/let
                   [X__41944 (clojure.core/set VAL__41942)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__41944))
                    (clojure.core/loop
                     [search_space__42389 (clojure.core/seq X__41944)]
                     (if
                      (clojure.core/seq search_space__42389)
                      (clojure.core/let
                       [elem__41945
                        (clojure.core/first search_space__42389)
                        result__42390
                        (clojure.core/let
                         [elem__41945_nth_0__
                          (clojure.core/nth elem__41945 0)
                          elem__41945_nth_1__
                          (clojure.core/nth elem__41945 1)]
                         (if
                          (clojure.core/symbol? elem__41945_nth_0__)
                          (clojure.core/let
                           [X__41947
                            (clojure.core/name elem__41945_nth_0__)]
                           (if
                            (clojure.core/= ?__40894 X__41947)
                            (if
                             (clojure.core/symbol? elem__41945_nth_1__)
                             (clojure.core/let
                              [X__41949
                               (clojure.core/name elem__41945_nth_1__)]
                              (clojure.core/case
                               X__41949
                               ("meander.zeta")
                               [?__40894]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__42390)
                        (recur (clojure.core/next search_space__42389))
                        result__42390))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__42387)))]
          (if
           (clojure.core/vector? input__40881)
           (if
            (clojure.core/= (clojure.core/count input__40881) 2)
            (clojure.core/let
             [input__40881_nth_0__
              (clojure.core/nth input__40881 0)
              input__40881_nth_1__
              (clojure.core/nth input__40881 1)]
             (if
              (clojure.core/seq? input__40881_nth_0__)
              (clojure.core/let
               [input__40881_nth_0___l__
                (clojure.core/take 1 input__40881_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__40881_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__40881_nth_0___r__
                  (clojure.core/drop 1 input__40881_nth_0__)]
                 (clojure.core/let
                  [input__40881_nth_0___l___nth_0__
                   (clojure.core/nth input__40881_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__40881_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__41927
                     (clojure.core/namespace
                      input__40881_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__40894 X__41927]
                     (clojure.core/let
                      [X__41929
                       (clojure.core/name
                        input__40881_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__41929
                       ("re")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__41917 input__40881_nth_1__ ?__40894)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__42206)
                         (clojure.core/let
                          [[?__40894] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__40881)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__40881)
                             2)
                            (clojure.core/let
                             [input__40881_nth_0__
                              (clojure.core/nth input__40881 0)
                              input__40881_nth_1__
                              (clojure.core/nth input__40881 1)]
                             (if
                              (clojure.core/seq? input__40881_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__40881_nth_0__)
                                2)
                               (clojure.core/let
                                [input__40881_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__40881_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?regex input__40881_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__40881_nth_0__]
                                  (clojure.core/let
                                   [?env input__40881_nth_1__]
                                   (try
                                    [{:tag :regex,
                                      :regex ?regex,
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__11823__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__11823__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__11823__auto__))))))))
                               (state__42206))
                              (state__42206)))
                            (state__42206))
                           (state__42206)))))
                       (state__42206)))))
                   (state__42206))))
                (state__42206)))
              (state__42206)))
            (state__42206))
           (state__42206))))
        (state__42206
         []
         (clojure.core/letfn
          [(def__41951
            [arg__41974 ?__40895]
            (clojure.core/letfn
             [(state__42392
               []
               (clojure.core/let
                [x__41975 "meander.zeta"]
                (if
                 (clojure.core/= ?__40895 x__41975)
                 [?__40895]
                 (state__42393))))
              (state__42393
               []
               (if
                (clojure.core/map? arg__41974)
                (clojure.core/let
                 [VAL__41976 (.valAt arg__41974 :aliases)]
                 (if
                  (clojure.core/map? VAL__41976)
                  (clojure.core/let
                   [X__41978 (clojure.core/set VAL__41976)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__41978))
                    (clojure.core/loop
                     [search_space__42394 (clojure.core/seq X__41978)]
                     (if
                      (clojure.core/seq search_space__42394)
                      (clojure.core/let
                       [elem__41979
                        (clojure.core/first search_space__42394)
                        result__42395
                        (clojure.core/let
                         [elem__41979_nth_0__
                          (clojure.core/nth elem__41979 0)
                          elem__41979_nth_1__
                          (clojure.core/nth elem__41979 1)]
                         (if
                          (clojure.core/symbol? elem__41979_nth_0__)
                          (clojure.core/let
                           [X__41981
                            (clojure.core/name elem__41979_nth_0__)]
                           (if
                            (clojure.core/= ?__40895 X__41981)
                            (if
                             (clojure.core/symbol? elem__41979_nth_1__)
                             (clojure.core/let
                              [X__41983
                               (clojure.core/name elem__41979_nth_1__)]
                              (clojure.core/case
                               X__41983
                               ("meander.zeta")
                               [?__40895]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__42395)
                        (recur (clojure.core/next search_space__42394))
                        result__42395))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__42392)))]
          (if
           (clojure.core/vector? input__40881)
           (if
            (clojure.core/= (clojure.core/count input__40881) 2)
            (clojure.core/let
             [input__40881_nth_0__
              (clojure.core/nth input__40881 0)
              input__40881_nth_1__
              (clojure.core/nth input__40881 1)]
             (if
              (clojure.core/seq? input__40881_nth_0__)
              (clojure.core/let
               [input__40881_nth_0___l__
                (clojure.core/take 1 input__40881_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__40881_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__40881_nth_0___r__
                  (clojure.core/drop 1 input__40881_nth_0__)]
                 (clojure.core/let
                  [input__40881_nth_0___l___nth_0__
                   (clojure.core/nth input__40881_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__40881_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__41961
                     (clojure.core/namespace
                      input__40881_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__40895 X__41961]
                     (clojure.core/let
                      [X__41963
                       (clojure.core/name
                        input__40881_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__41963
                       ("re")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__41951 input__40881_nth_1__ ?__40895)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__42207)
                         (clojure.core/let
                          [[?__40895] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__40881)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__40881)
                             2)
                            (clojure.core/let
                             [input__40881_nth_0__
                              (clojure.core/nth input__40881 0)
                              input__40881_nth_1__
                              (clojure.core/nth input__40881 1)]
                             (if
                              (clojure.core/seq? input__40881_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__40881_nth_0__)
                                3)
                               (clojure.core/let
                                [input__40881_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__40881_nth_0__
                                  1)
                                 input__40881_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__40881_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?regex input__40881_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?capture
                                   input__40881_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__40881_nth_0__]
                                   (clojure.core/let
                                    [?env input__40881_nth_1__]
                                    (try
                                     [{:tag :regex,
                                       :regex ?regex,
                                       :capture
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__40949
                                          [?capture ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10883__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10883__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__11823__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__11823__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__11823__auto__)))))))))
                               (state__42207))
                              (state__42207)))
                            (state__42207))
                           (state__42207)))))
                       (state__42207)))))
                   (state__42207))))
                (state__42207)))
              (state__42207)))
            (state__42207))
           (state__42207))))
        (state__42207
         []
         (clojure.core/letfn
          [(def__41985
            [arg__42008 ?__40896]
            (clojure.core/letfn
             [(state__42397
               []
               (clojure.core/let
                [x__42009 "meander.zeta"]
                (if
                 (clojure.core/= ?__40896 x__42009)
                 [?__40896]
                 (state__42398))))
              (state__42398
               []
               (if
                (clojure.core/map? arg__42008)
                (clojure.core/let
                 [VAL__42010 (.valAt arg__42008 :aliases)]
                 (if
                  (clojure.core/map? VAL__42010)
                  (clojure.core/let
                   [X__42012 (clojure.core/set VAL__42010)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__42012))
                    (clojure.core/loop
                     [search_space__42399 (clojure.core/seq X__42012)]
                     (if
                      (clojure.core/seq search_space__42399)
                      (clojure.core/let
                       [elem__42013
                        (clojure.core/first search_space__42399)
                        result__42400
                        (clojure.core/let
                         [elem__42013_nth_0__
                          (clojure.core/nth elem__42013 0)
                          elem__42013_nth_1__
                          (clojure.core/nth elem__42013 1)]
                         (if
                          (clojure.core/symbol? elem__42013_nth_0__)
                          (clojure.core/let
                           [X__42015
                            (clojure.core/name elem__42013_nth_0__)]
                           (if
                            (clojure.core/= ?__40896 X__42015)
                            (if
                             (clojure.core/symbol? elem__42013_nth_1__)
                             (clojure.core/let
                              [X__42017
                               (clojure.core/name elem__42013_nth_1__)]
                              (clojure.core/case
                               X__42017
                               ("meander.zeta")
                               [?__40896]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__42400)
                        (recur (clojure.core/next search_space__42399))
                        result__42400))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__42397)))]
          (if
           (clojure.core/vector? input__40881)
           (if
            (clojure.core/= (clojure.core/count input__40881) 2)
            (clojure.core/let
             [input__40881_nth_0__
              (clojure.core/nth input__40881 0)
              input__40881_nth_1__
              (clojure.core/nth input__40881 1)]
             (if
              (clojure.core/seq? input__40881_nth_0__)
              (clojure.core/let
               [input__40881_nth_0___l__
                (clojure.core/take 1 input__40881_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__40881_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__40881_nth_0___r__
                  (clojure.core/drop 1 input__40881_nth_0__)]
                 (clojure.core/let
                  [input__40881_nth_0___l___nth_0__
                   (clojure.core/nth input__40881_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__40881_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__41995
                     (clojure.core/namespace
                      input__40881_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__40896 X__41995]
                     (clojure.core/let
                      [X__41997
                       (clojure.core/name
                        input__40881_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__41997
                       ("string")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__41985 input__40881_nth_1__ ?__40896)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__42208)
                         (clojure.core/let
                          [[?__40896] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__40881)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__40881)
                             2)
                            (clojure.core/let
                             [input__40881_nth_0__
                              (clojure.core/nth input__40881 0)
                              input__40881_nth_1__
                              (clojure.core/nth input__40881 1)]
                             (if
                              (clojure.core/seq? input__40881_nth_0__)
                              (clojure.core/let
                               [input__40881_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__40881_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__40881_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__40881_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__40881_nth_0__)]
                                 (clojure.core/let
                                  [?sequence input__40881_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__40881_nth_0__]
                                   (clojure.core/let
                                    [?env input__40881_nth_1__]
                                    (try
                                     [{:tag :string,
                                       :next
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__40949
                                          ['meander.dev.parse.zeta/parse-sequential
                                           (clojure.core/into
                                            []
                                            ?sequence)
                                           (clojure.core/let
                                            [form__10982__auto__
                                             {:context :string}]
                                            (clojure.core/merge
                                             (clojure.core/into
                                              {}
                                              ?env)
                                             form__10982__auto__))])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10883__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10883__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__11823__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__11823__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__11823__auto__))))))))
                                (state__42208)))
                              (state__42208)))
                            (state__42208))
                           (state__42208)))))
                       (state__42208)))))
                   (state__42208))))
                (state__42208)))
              (state__42208)))
            (state__42208))
           (state__42208))))
        (state__42208
         []
         (clojure.core/letfn
          [(def__42019
            [arg__42042 ?__40897]
            (clojure.core/letfn
             [(state__42402
               []
               (clojure.core/let
                [x__42043 "meander.zeta"]
                (if
                 (clojure.core/= ?__40897 x__42043)
                 [?__40897]
                 (state__42403))))
              (state__42403
               []
               (if
                (clojure.core/map? arg__42042)
                (clojure.core/let
                 [VAL__42044 (.valAt arg__42042 :aliases)]
                 (if
                  (clojure.core/map? VAL__42044)
                  (clojure.core/let
                   [X__42046 (clojure.core/set VAL__42044)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__42046))
                    (clojure.core/loop
                     [search_space__42404 (clojure.core/seq X__42046)]
                     (if
                      (clojure.core/seq search_space__42404)
                      (clojure.core/let
                       [elem__42047
                        (clojure.core/first search_space__42404)
                        result__42405
                        (clojure.core/let
                         [elem__42047_nth_0__
                          (clojure.core/nth elem__42047 0)
                          elem__42047_nth_1__
                          (clojure.core/nth elem__42047 1)]
                         (if
                          (clojure.core/symbol? elem__42047_nth_0__)
                          (clojure.core/let
                           [X__42049
                            (clojure.core/name elem__42047_nth_0__)]
                           (if
                            (clojure.core/= ?__40897 X__42049)
                            (if
                             (clojure.core/symbol? elem__42047_nth_1__)
                             (clojure.core/let
                              [X__42051
                               (clojure.core/name elem__42047_nth_1__)]
                              (clojure.core/case
                               X__42051
                               ("meander.zeta")
                               [?__40897]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__42405)
                        (recur (clojure.core/next search_space__42404))
                        result__42405))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__42402)))]
          (if
           (clojure.core/vector? input__40881)
           (if
            (clojure.core/= (clojure.core/count input__40881) 2)
            (clojure.core/let
             [input__40881_nth_0__
              (clojure.core/nth input__40881 0)
              input__40881_nth_1__
              (clojure.core/nth input__40881 1)]
             (if
              (clojure.core/seq? input__40881_nth_0__)
              (clojure.core/let
               [input__40881_nth_0___l__
                (clojure.core/take 1 input__40881_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__40881_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__40881_nth_0___r__
                  (clojure.core/drop 1 input__40881_nth_0__)]
                 (clojure.core/let
                  [input__40881_nth_0___l___nth_0__
                   (clojure.core/nth input__40881_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__40881_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__42029
                     (clojure.core/namespace
                      input__40881_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__40897 X__42029]
                     (clojure.core/let
                      [X__42031
                       (clojure.core/name
                        input__40881_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__42031
                       ("symbol")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__42019 input__40881_nth_1__ ?__40897)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__42209)
                         (clojure.core/let
                          [[?__40897] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__40881)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__40881)
                             2)
                            (clojure.core/let
                             [input__40881_nth_0__
                              (clojure.core/nth input__40881 0)
                              input__40881_nth_1__
                              (clojure.core/nth input__40881 1)]
                             (if
                              (clojure.core/seq? input__40881_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__40881_nth_0__)
                                2)
                               (clojure.core/let
                                [input__40881_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__40881_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?name input__40881_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__40881_nth_0__]
                                  (clojure.core/let
                                   [?env input__40881_nth_1__]
                                   (try
                                    [{:tag :symbol,
                                      :name
                                      (clojure.core/let
                                       [CATA_RESULT__10883__auto__
                                        (CATA__FN__40949 [?name ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__10883__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__10883__auto__
                                         0))),
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__11823__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__11823__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__11823__auto__))))))))
                               (state__42209))
                              (state__42209)))
                            (state__42209))
                           (state__42209)))))
                       (state__42209)))))
                   (state__42209))))
                (state__42209)))
              (state__42209)))
            (state__42209))
           (state__42209))))
        (state__42209
         []
         (clojure.core/letfn
          [(def__42053
            [arg__42076 ?__40898]
            (clojure.core/letfn
             [(state__42407
               []
               (clojure.core/let
                [x__42077 "meander.zeta"]
                (if
                 (clojure.core/= ?__40898 x__42077)
                 [?__40898]
                 (state__42408))))
              (state__42408
               []
               (if
                (clojure.core/map? arg__42076)
                (clojure.core/let
                 [VAL__42078 (.valAt arg__42076 :aliases)]
                 (if
                  (clojure.core/map? VAL__42078)
                  (clojure.core/let
                   [X__42080 (clojure.core/set VAL__42078)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__42080))
                    (clojure.core/loop
                     [search_space__42409 (clojure.core/seq X__42080)]
                     (if
                      (clojure.core/seq search_space__42409)
                      (clojure.core/let
                       [elem__42081
                        (clojure.core/first search_space__42409)
                        result__42410
                        (clojure.core/let
                         [elem__42081_nth_0__
                          (clojure.core/nth elem__42081 0)
                          elem__42081_nth_1__
                          (clojure.core/nth elem__42081 1)]
                         (if
                          (clojure.core/symbol? elem__42081_nth_0__)
                          (clojure.core/let
                           [X__42083
                            (clojure.core/name elem__42081_nth_0__)]
                           (if
                            (clojure.core/= ?__40898 X__42083)
                            (if
                             (clojure.core/symbol? elem__42081_nth_1__)
                             (clojure.core/let
                              [X__42085
                               (clojure.core/name elem__42081_nth_1__)]
                              (clojure.core/case
                               X__42085
                               ("meander.zeta")
                               [?__40898]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__42410)
                        (recur (clojure.core/next search_space__42409))
                        result__42410))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__42407)))]
          (if
           (clojure.core/vector? input__40881)
           (if
            (clojure.core/= (clojure.core/count input__40881) 2)
            (clojure.core/let
             [input__40881_nth_0__
              (clojure.core/nth input__40881 0)
              input__40881_nth_1__
              (clojure.core/nth input__40881 1)]
             (if
              (clojure.core/seq? input__40881_nth_0__)
              (clojure.core/let
               [input__40881_nth_0___l__
                (clojure.core/take 1 input__40881_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__40881_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__40881_nth_0___r__
                  (clojure.core/drop 1 input__40881_nth_0__)]
                 (clojure.core/let
                  [input__40881_nth_0___l___nth_0__
                   (clojure.core/nth input__40881_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__40881_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__42063
                     (clojure.core/namespace
                      input__40881_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__40898 X__42063]
                     (clojure.core/let
                      [X__42065
                       (clojure.core/name
                        input__40881_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__42065
                       ("symbol")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__42053 input__40881_nth_1__ ?__40898)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__42210)
                         (clojure.core/let
                          [[?__40898] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__40881)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__40881)
                             2)
                            (clojure.core/let
                             [input__40881_nth_0__
                              (clojure.core/nth input__40881 0)
                              input__40881_nth_1__
                              (clojure.core/nth input__40881 1)]
                             (if
                              (clojure.core/seq? input__40881_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__40881_nth_0__)
                                3)
                               (clojure.core/let
                                [input__40881_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__40881_nth_0__
                                  1)
                                 input__40881_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__40881_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?namespace
                                  input__40881_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?name input__40881_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__40881_nth_0__]
                                   (clojure.core/let
                                    [?env input__40881_nth_1__]
                                    (try
                                     [{:tag :symbol,
                                       :name
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__40949
                                          [?name ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10883__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10883__auto__
                                          0))),
                                       :namespace
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__40949
                                          [?namespace ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10883__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10883__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__11823__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__11823__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__11823__auto__)))))))))
                               (state__42210))
                              (state__42210)))
                            (state__42210))
                           (state__42210)))))
                       (state__42210)))))
                   (state__42210))))
                (state__42210)))
              (state__42210)))
            (state__42210))
           (state__42210))))
        (state__42210
         []
         (clojure.core/letfn
          [(def__42087
            [arg__42110 ?__40899]
            (clojure.core/letfn
             [(state__42412
               []
               (clojure.core/let
                [x__42111 "meander.zeta"]
                (if
                 (clojure.core/= ?__40899 x__42111)
                 [?__40899]
                 (state__42413))))
              (state__42413
               []
               (if
                (clojure.core/map? arg__42110)
                (clojure.core/let
                 [VAL__42112 (.valAt arg__42110 :aliases)]
                 (if
                  (clojure.core/map? VAL__42112)
                  (clojure.core/let
                   [X__42114 (clojure.core/set VAL__42112)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__42114))
                    (clojure.core/loop
                     [search_space__42414 (clojure.core/seq X__42114)]
                     (if
                      (clojure.core/seq search_space__42414)
                      (clojure.core/let
                       [elem__42115
                        (clojure.core/first search_space__42414)
                        result__42415
                        (clojure.core/let
                         [elem__42115_nth_0__
                          (clojure.core/nth elem__42115 0)
                          elem__42115_nth_1__
                          (clojure.core/nth elem__42115 1)]
                         (if
                          (clojure.core/symbol? elem__42115_nth_0__)
                          (clojure.core/let
                           [X__42117
                            (clojure.core/name elem__42115_nth_0__)]
                           (if
                            (clojure.core/= ?__40899 X__42117)
                            (if
                             (clojure.core/symbol? elem__42115_nth_1__)
                             (clojure.core/let
                              [X__42119
                               (clojure.core/name elem__42115_nth_1__)]
                              (clojure.core/case
                               X__42119
                               ("meander.zeta")
                               [?__40899]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__42415)
                        (recur (clojure.core/next search_space__42414))
                        result__42415))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__42412)))]
          (if
           (clojure.core/vector? input__40881)
           (if
            (clojure.core/= (clojure.core/count input__40881) 2)
            (clojure.core/let
             [input__40881_nth_0__
              (clojure.core/nth input__40881 0)
              input__40881_nth_1__
              (clojure.core/nth input__40881 1)]
             (if
              (clojure.core/seq? input__40881_nth_0__)
              (clojure.core/let
               [input__40881_nth_0___l__
                (clojure.core/take 1 input__40881_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__40881_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__40881_nth_0___r__
                  (clojure.core/drop 1 input__40881_nth_0__)]
                 (clojure.core/let
                  [input__40881_nth_0___l___nth_0__
                   (clojure.core/nth input__40881_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__40881_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__42097
                     (clojure.core/namespace
                      input__40881_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__40899 X__42097]
                     (clojure.core/let
                      [X__42099
                       (clojure.core/name
                        input__40881_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__42099
                       ("symbol")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__42087 input__40881_nth_1__ ?__40899)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__42211)
                         (clojure.core/let
                          [[?__40899] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__40881)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__40881)
                             2)
                            (clojure.core/let
                             [input__40881_nth_0__
                              (clojure.core/nth input__40881 0)
                              input__40881_nth_1__
                              (clojure.core/nth input__40881 1)]
                             (if
                              (clojure.core/seq? input__40881_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 5)
                                 input__40881_nth_0__)
                                5)
                               (clojure.core/let
                                [input__40881_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__40881_nth_0__
                                  1)
                                 input__40881_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__40881_nth_0__
                                  2)
                                 input__40881_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__40881_nth_0__
                                  3)
                                 input__40881_nth_0___nth_4__
                                 (clojure.core/nth
                                  input__40881_nth_0__
                                  4)]
                                (clojure.core/case
                                 input__40881_nth_0___nth_3__
                                 (:meander.zeta/as)
                                 (clojure.core/let
                                  [?namespace
                                   input__40881_nth_0___nth_1__]
                                  (clojure.core/let
                                   [?name input__40881_nth_0___nth_2__]
                                   (clojure.core/let
                                    [?pattern
                                     input__40881_nth_0___nth_4__]
                                    (clojure.core/let
                                     [?form input__40881_nth_0__]
                                     (clojure.core/let
                                      [?env input__40881_nth_1__]
                                      (try
                                       [{:tag :symbol,
                                         :name
                                         (clojure.core/let
                                          [CATA_RESULT__10883__auto__
                                           (CATA__FN__40949
                                            [?name ?env])]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            CATA_RESULT__10883__auto__)
                                           (throw
                                            (meander.runtime.zeta/fail))
                                           (clojure.core/nth
                                            CATA_RESULT__10883__auto__
                                            0))),
                                         :namespace
                                         (clojure.core/let
                                          [CATA_RESULT__10883__auto__
                                           (CATA__FN__40949
                                            [?namespace ?env])]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            CATA_RESULT__10883__auto__)
                                           (throw
                                            (meander.runtime.zeta/fail))
                                           (clojure.core/nth
                                            CATA_RESULT__10883__auto__
                                            0))),
                                         :as-pattern
                                         (clojure.core/let
                                          [CATA_RESULT__10883__auto__
                                           (CATA__FN__40949
                                            [?pattern ?env])]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            CATA_RESULT__10883__auto__)
                                           (throw
                                            (meander.runtime.zeta/fail))
                                           (clojure.core/nth
                                            CATA_RESULT__10883__auto__
                                            0))),
                                         :form ?form}]
                                       (catch
                                        java.lang.Exception
                                        e__11823__auto__
                                        (if
                                         (meander.runtime.zeta/fail?
                                          e__11823__auto__)
                                         (meander.runtime.zeta/fail)
                                         (throw
                                          e__11823__auto__)))))))))
                                 (state__42211)))
                               (state__42211))
                              (state__42211)))
                            (state__42211))
                           (state__42211)))))
                       (state__42211)))))
                   (state__42211))))
                (state__42211)))
              (state__42211)))
            (state__42211))
           (state__42211))))
        (state__42211
         []
         (if
          (clojure.core/vector? input__40881)
          (if
           (clojure.core/= (clojure.core/count input__40881) 2)
           (clojure.core/let
            [input__40881_nth_0__ (clojure.core/nth input__40881 0)]
            (clojure.core/letfn
             [(state__42417
               []
               (clojure.core/let
                [input__40881_nth_1__
                 (clojure.core/nth input__40881 1)]
                (clojure.core/letfn
                 [(state__42422
                   []
                   (if
                    (clojure.core/seq? input__40881_nth_0__)
                    (clojure.core/let
                     [?sequence input__40881_nth_0__]
                     (clojure.core/let
                      [?env input__40881_nth_1__]
                      (try
                       [{:tag :seq,
                         :next
                         (clojure.core/let
                          [CATA_RESULT__10883__auto__
                           (CATA__FN__40949
                            ['meander.dev.parse.zeta/parse-sequential
                             (clojure.core/into [] ?sequence)
                             (clojure.core/let
                              [form__10982__auto__ {:context :seq}]
                              (clojure.core/merge
                               (clojure.core/into {} ?env)
                               form__10982__auto__))])]
                          (if
                           (meander.runtime.zeta/fail?
                            CATA_RESULT__10883__auto__)
                           (throw (meander.runtime.zeta/fail))
                           (clojure.core/nth
                            CATA_RESULT__10883__auto__
                            0))),
                         :form ?sequence}]
                       (catch
                        java.lang.Exception
                        e__11823__auto__
                        (if
                         (meander.runtime.zeta/fail? e__11823__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__11823__auto__))))))
                    (state__42423)))
                  (state__42423
                   []
                   (if
                    (clojure.core/map? input__40881_nth_0__)
                    (clojure.core/let
                     [?map input__40881_nth_0__]
                     (clojure.core/let
                      [?env input__40881_nth_1__]
                      (try
                       [{:tag :map,
                         :next
                         (clojure.core/let
                          [CATA_RESULT__10883__auto__
                           (CATA__FN__40949
                            ['meander.dev.parse.zeta/parse-entries
                             ?map
                             ?env])]
                          (if
                           (meander.runtime.zeta/fail?
                            CATA_RESULT__10883__auto__)
                           (throw (meander.runtime.zeta/fail))
                           (clojure.core/nth
                            CATA_RESULT__10883__auto__
                            0))),
                         :form ?map}]
                       (catch
                        java.lang.Exception
                        e__11823__auto__
                        (if
                         (meander.runtime.zeta/fail? e__11823__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__11823__auto__))))))
                    (state__42418)))]
                 (state__42422))))
              (state__42418
               []
               (if
                (clojure.core/symbol? input__40881_nth_0__)
                (clojure.core/let
                 [X__42129
                  (clojure.core/namespace input__40881_nth_0__)]
                 (clojure.core/let
                  [X__42131 (clojure.core/name input__40881_nth_0__)]
                  (if
                   (clojure.core/string? X__42131)
                   (clojure.core/letfn
                    [(state__42424
                      []
                      (clojure.core/let
                       [ret__42132
                        (clojure.core/re-matches #"_.*" X__42131)]
                       (if
                        (clojure.core/some? ret__42132)
                        (clojure.core/let
                         [?name ret__42132]
                         (clojure.core/let
                          [?symbol input__40881_nth_0__]
                          (try
                           [{:tag :wildcard,
                             :name ?name,
                             :form ?symbol,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__11823__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__11823__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__11823__auto__))))))
                        (state__42425))))
                     (state__42425
                      []
                      (clojure.core/let
                       [ret__42139
                        (clojure.core/re-matches #".+#" X__42131)]
                       (if
                        (clojure.core/some? ret__42139)
                        (clojure.core/let
                         [?name ret__42139]
                         (clojure.core/let
                          [?symbol input__40881_nth_0__]
                          (try
                           [{:tag :random-symbol,
                             :name ?name,
                             :form ?symbol,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__11823__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__11823__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__11823__auto__))))))
                        (state__42426))))
                     (state__42426
                      []
                      (clojure.core/let
                       [ret__42146
                        (clojure.core/re-matches #"%.+" X__42131)]
                       (if
                        (clojure.core/some? ret__42146)
                        (clojure.core/let
                         [?name ret__42146]
                         (clojure.core/let
                          [?symbol input__40881_nth_0__]
                          (try
                           [{:tag :reference,
                             :name ?name,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__11823__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__11823__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__11823__auto__))))))
                        (state__42427))))
                     (state__42427
                      []
                      (clojure.core/let
                       [ret__42153
                        (clojure.core/re-matches #"\*.+" X__42131)]
                       (if
                        (clojure.core/some? ret__42153)
                        (clojure.core/let
                         [?name ret__42153]
                         (clojure.core/let
                          [?symbol input__40881_nth_0__]
                          (try
                           [{:tag :mutable-variable,
                             :name ?name,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__11823__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__11823__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__11823__auto__))))))
                        (state__42428))))
                     (state__42428
                      []
                      (clojure.core/let
                       [ret__42160
                        (clojure.core/re-matches #"\!.+" X__42131)]
                       (if
                        (clojure.core/some? ret__42160)
                        (clojure.core/let
                         [?name ret__42160]
                         (clojure.core/let
                          [?symbol input__40881_nth_0__]
                          (try
                           [{:tag :memory-variable,
                             :name ?name,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__11823__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__11823__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__11823__auto__))))))
                        (state__42429))))
                     (state__42429
                      []
                      (clojure.core/let
                       [ret__42167
                        (clojure.core/re-matches #"\?.+" X__42131)]
                       (if
                        (clojure.core/some? ret__42167)
                        (clojure.core/let
                         [?name ret__42167]
                         (clojure.core/let
                          [?symbol input__40881_nth_0__]
                          (try
                           [{:tag :logic-variable,
                             :name ?name,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__11823__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__11823__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__11823__auto__))))))
                        (state__42419))))]
                    (state__42424))
                   (state__42419))))
                (state__42419)))
              (state__42419
               []
               (if
                (string? input__40881_nth_0__)
                (clojure.core/let
                 [?x input__40881_nth_0__]
                 (try
                  [{:tag :literal, :type :string, :form ?x}]
                  (catch
                   java.lang.Exception
                   e__11823__auto__
                   (if
                    (meander.runtime.zeta/fail? e__11823__auto__)
                    (meander.runtime.zeta/fail)
                    (throw e__11823__auto__)))))
                (state__42420)))
              (state__42420
               []
               (if
                (char? input__40881_nth_0__)
                (clojure.core/let
                 [?x input__40881_nth_0__]
                 (try
                  [{:tag :literal, :type :char, :form ?x}]
                  (catch
                   java.lang.Exception
                   e__11823__auto__
                   (if
                    (meander.runtime.zeta/fail? e__11823__auto__)
                    (meander.runtime.zeta/fail)
                    (throw e__11823__auto__)))))
                (state__42421)))
              (state__42421
               []
               (clojure.core/let
                [?x input__40881_nth_0__]
                (try
                 [{:tag :literal, :form ?x}]
                 (catch
                  java.lang.Exception
                  e__11823__auto__
                  (if
                   (meander.runtime.zeta/fail? e__11823__auto__)
                   (meander.runtime.zeta/fail)
                   (throw e__11823__auto__))))))]
             (state__42417)))
           (state__42212))
          (state__42212)))
        (state__42212
         []
         (clojure.core/let
          [?x input__40881]
          (try
           [{:tag :mistake, :x ?x}]
           (catch
            java.lang.Exception
            e__11823__auto__
            (if
             (meander.runtime.zeta/fail? e__11823__auto__)
             (meander.runtime.zeta/fail)
             (throw e__11823__auto__))))))]
       (state__42180)))]
    (clojure.core/let
     [x__9580__auto__ (CATA__FN__40949 input__40881)]
     (if
      (meander.runtime.zeta/fail? x__9580__auto__)
      (meander.runtime.zeta/fail)
      (clojure.core/let
       [[CATA_RETURN__40951] x__9580__auto__]
       CATA_RETURN__40951))))]
  (if
   (meander.runtime.zeta/fail? ret__9760__auto__)
   nil
   ret__9760__auto__)))
