(ns meander.compiled.parse.zeta (:require [meander.runtime.zeta]))
(clojure.core/defn
 parse
 [input__82096]
 (let*
  [ret__8106__auto__
   (clojure.core/letfn
    [(CATA__FN__82152
      [input__82096]
      (clojure.core/letfn
       [(state__83208
         []
         (if
          (clojure.core/vector? input__82096)
          (if
           (clojure.core/= (clojure.core/count input__82096) 3)
           (clojure.core/let
            [input__82096_nth_0__
             (clojure.core/nth input__82096 0)
             input__82096_nth_1__
             (clojure.core/nth input__82096 1)
             input__82096_nth_2__
             (clojure.core/nth input__82096 2)]
            (clojure.core/case
             input__82096_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__82096_nth_1__)
              (clojure.core/letfn
               [(state__83237
                 []
                 (clojure.core/case
                  input__82096_nth_1__
                  ([])
                  (clojure.core/let
                   [?env input__82096_nth_2__]
                   (try
                    [{:tag :empty}]
                    (catch
                     java.lang.Exception
                     e__10169__auto__
                     (if
                      (meander.runtime.zeta/fail? e__10169__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__10169__auto__)))))
                  (state__83238)))
                (state__83238
                 []
                 (clojure.core/let
                  [n__82159
                   (clojure.core/count input__82096_nth_1__)
                   m__82160
                   (clojure.core/max 0 (clojure.core/- n__82159 2))
                   input__82096_nth_1___l__
                   (clojure.core/subvec
                    input__82096_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__82096_nth_1__)
                     m__82160))
                   input__82096_nth_1___r__
                   (clojure.core/subvec input__82096_nth_1__ m__82160)]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__82096_nth_1___r__)
                    2)
                   (clojure.core/let
                    [!xs (clojure.core/vec input__82096_nth_1___l__)]
                    (if
                     (clojure.core/=
                      (clojure.core/count input__82096_nth_1___r__)
                      2)
                     (clojure.core/let
                      [input__82096_nth_1___r___nth_0__
                       (clojure.core/nth input__82096_nth_1___r__ 0)
                       input__82096_nth_1___r___nth_1__
                       (clojure.core/nth input__82096_nth_1___r__ 1)]
                      (clojure.core/case
                       input__82096_nth_1___r___nth_0__
                       (:meander.zeta/as)
                       (clojure.core/let
                        [?pattern input__82096_nth_1___r___nth_1__]
                        (clojure.core/let
                         [?env input__82096_nth_2__]
                         (try
                          [(clojure.core/let
                            [!xs__counter
                             (meander.runtime.zeta/iterator !xs)]
                            {:tag :as,
                             :pattern
                             (clojure.core/let
                              [CATA_RESULT__9229__auto__
                               (CATA__FN__82152 [?pattern ?env])]
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
                               (CATA__FN__82152
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
                       (state__83209)))
                     (state__83209)))
                   (state__83209))))]
               (state__83237))
              (state__83209))
             (state__83209)))
           (state__83209))
          (state__83209)))
        (state__83209
         []
         (clojure.core/letfn
          [(def__82165
            [arg__82200 ?ns]
            (clojure.core/letfn
             [(state__83239
               []
               (clojure.core/let
                [x__82201 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__82201)
                 (clojure.core/let [?env arg__82200] [?env ?ns])
                 (state__83240))))
              (state__83240
               []
               (if
                (clojure.core/map? arg__82200)
                (clojure.core/let
                 [VAL__82202 (.valAt arg__82200 :aliases)]
                 (if
                  (clojure.core/map? VAL__82202)
                  (clojure.core/let
                   [X__82204 (clojure.core/set VAL__82202)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__82204))
                    (clojure.core/loop
                     [search_space__83241 (clojure.core/seq X__82204)]
                     (if
                      (clojure.core/seq search_space__83241)
                      (clojure.core/let
                       [elem__82205
                        (clojure.core/first search_space__83241)
                        result__83242
                        (clojure.core/let
                         [elem__82205_nth_0__
                          (clojure.core/nth elem__82205 0)
                          elem__82205_nth_1__
                          (clojure.core/nth elem__82205 1)]
                         (if
                          (clojure.core/symbol? elem__82205_nth_0__)
                          (clojure.core/let
                           [X__82207
                            (clojure.core/name elem__82205_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__82207)
                            (if
                             (clojure.core/symbol? elem__82205_nth_1__)
                             (clojure.core/let
                              [X__82209
                               (clojure.core/name elem__82205_nth_1__)]
                              (clojure.core/case
                               X__82209
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__82200]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__83242)
                        (recur (clojure.core/next search_space__83241))
                        result__83242))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__83239)))]
          (if
           (clojure.core/vector? input__82096)
           (if
            (clojure.core/= (clojure.core/count input__82096) 3)
            (clojure.core/let
             [input__82096_nth_0__
              (clojure.core/nth input__82096 0)
              input__82096_nth_1__
              (clojure.core/nth input__82096 1)
              input__82096_nth_2__
              (clojure.core/nth input__82096 2)]
             (clojure.core/case
              input__82096_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__82096_nth_1__)
               (clojure.core/loop
                [search_space__83244
                 (meander.match.runtime.epsilon/partitions
                  2
                  input__82096_nth_1__)]
                (if
                 (clojure.core/seq search_space__83244)
                 (clojure.core/let
                  [input__82096_nth_1___parts__
                   (clojure.core/first search_space__83244)
                   result__83245
                   (clojure.core/let
                    [input__82096_nth_1___l__
                     (clojure.core/nth input__82096_nth_1___parts__ 0)
                     input__82096_nth_1___r__
                     (clojure.core/nth input__82096_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__82096_nth_1___l__)]
                     (clojure.core/let
                      [input__82096_nth_1___r___l__
                       (clojure.core/subvec
                        input__82096_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__82096_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__82096_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__82096_nth_1___r___r__
                         (clojure.core/subvec
                          input__82096_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__82096_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__82096_nth_1___r___l__
                           0)
                          input__82096_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__82096_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__82096_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__82174
                            (clojure.core/namespace
                             input__82096_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__82174]
                            (clojure.core/let
                             [X__82176
                              (clojure.core/name
                               input__82096_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__82176)
                              (clojure.core/let
                               [ret__82177
                                (clojure.core/re-matches
                                 #"&(\d+)"
                                 X__82176)]
                               (if
                                (clojure.core/some? ret__82177)
                                (if
                                 (clojure.core/vector? ret__82177)
                                 (if
                                  (clojure.core/=
                                   (clojure.core/count ret__82177)
                                   2)
                                  (clojure.core/let
                                   [ret__82177_nth_1__
                                    (clojure.core/nth ret__82177 1)]
                                   (clojure.core/let
                                    [?n ret__82177_nth_1__]
                                    (clojure.core/let
                                     [?pattern
                                      input__82096_nth_1___r___l___nth_1__]
                                     (clojure.core/let
                                      [?rest
                                       input__82096_nth_1___r___r__]
                                      (clojure.core/let
                                       [x__7926__auto__
                                        (def__82165
                                         input__82096_nth_2__
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
                                              (CATA__FN__82152
                                               ['meander.dev.parse.zeta/make-join
                                                (clojure.core/let
                                                 [CATA_RESULT__9229__auto__
                                                  (CATA__FN__82152
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
                                                  (CATA__FN__82152
                                                   ['meander.dev.parse.zeta/make-join
                                                    {:tag :slice,
                                                     :size
                                                     (Integer. ?n),
                                                     :pattern
                                                     (clojure.core/let
                                                      [CATA_RESULT__9229__auto__
                                                       (CATA__FN__82152
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
                                                      (CATA__FN__82152
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
                   (meander.runtime.zeta/fail? result__83245)
                   (recur (clojure.core/next search_space__83244))
                   result__83245))
                 (state__83210)))
               (state__83210))
              (state__83210)))
            (state__83210))
           (state__83210))))
        (state__83210
         []
         (clojure.core/letfn
          [(def__82222
            [arg__82254 ?ns]
            (clojure.core/letfn
             [(state__83247
               []
               (clojure.core/let
                [x__82255 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__82255)
                 (clojure.core/let [?env arg__82254] [?env ?ns])
                 (state__83248))))
              (state__83248
               []
               (if
                (clojure.core/map? arg__82254)
                (clojure.core/let
                 [VAL__82256 (.valAt arg__82254 :aliases)]
                 (if
                  (clojure.core/map? VAL__82256)
                  (clojure.core/let
                   [X__82258 (clojure.core/set VAL__82256)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__82258))
                    (clojure.core/loop
                     [search_space__83249 (clojure.core/seq X__82258)]
                     (if
                      (clojure.core/seq search_space__83249)
                      (clojure.core/let
                       [elem__82259
                        (clojure.core/first search_space__83249)
                        result__83250
                        (clojure.core/let
                         [elem__82259_nth_0__
                          (clojure.core/nth elem__82259 0)
                          elem__82259_nth_1__
                          (clojure.core/nth elem__82259 1)]
                         (if
                          (clojure.core/symbol? elem__82259_nth_0__)
                          (clojure.core/let
                           [X__82261
                            (clojure.core/name elem__82259_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__82261)
                            (if
                             (clojure.core/symbol? elem__82259_nth_1__)
                             (clojure.core/let
                              [X__82263
                               (clojure.core/name elem__82259_nth_1__)]
                              (clojure.core/case
                               X__82263
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__82254]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__83250)
                        (recur (clojure.core/next search_space__83249))
                        result__83250))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__83247)))]
          (if
           (clojure.core/vector? input__82096)
           (if
            (clojure.core/= (clojure.core/count input__82096) 3)
            (clojure.core/let
             [input__82096_nth_0__
              (clojure.core/nth input__82096 0)
              input__82096_nth_1__
              (clojure.core/nth input__82096 1)
              input__82096_nth_2__
              (clojure.core/nth input__82096 2)]
             (clojure.core/case
              input__82096_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__82096_nth_1__)
               (clojure.core/loop
                [search_space__83252
                 (meander.match.runtime.epsilon/partitions
                  2
                  input__82096_nth_1__)]
                (if
                 (clojure.core/seq search_space__83252)
                 (clojure.core/let
                  [input__82096_nth_1___parts__
                   (clojure.core/first search_space__83252)
                   result__83253
                   (clojure.core/let
                    [input__82096_nth_1___l__
                     (clojure.core/nth input__82096_nth_1___parts__ 0)
                     input__82096_nth_1___r__
                     (clojure.core/nth input__82096_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__82096_nth_1___l__)]
                     (clojure.core/let
                      [input__82096_nth_1___r___l__
                       (clojure.core/subvec
                        input__82096_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__82096_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__82096_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__82096_nth_1___r___r__
                         (clojure.core/subvec
                          input__82096_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__82096_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__82096_nth_1___r___l__
                           0)
                          input__82096_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__82096_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__82096_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__82231
                            (clojure.core/namespace
                             input__82096_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__82231]
                            (clojure.core/let
                             [X__82233
                              (clojure.core/name
                               input__82096_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__82233)
                              (if
                               (clojure.core/re-matches
                                #"&.*"
                                X__82233)
                               (clojure.core/let
                                [?pattern
                                 input__82096_nth_1___r___l___nth_1__]
                                (clojure.core/let
                                 [?rest input__82096_nth_1___r___r__]
                                 (clojure.core/let
                                  [x__7926__auto__
                                   (def__82222
                                    input__82096_nth_2__
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
                                         (CATA__FN__82152
                                          ['meander.dev.parse.zeta/make-join
                                           (clojure.core/let
                                            [CATA_RESULT__9229__auto__
                                             (CATA__FN__82152
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
                                             (CATA__FN__82152
                                              ['meander.dev.parse.zeta/make-join
                                               (clojure.core/let
                                                [CATA_RESULT__9229__auto__
                                                 (CATA__FN__82152
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
                                                 (CATA__FN__82152
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
                   (meander.runtime.zeta/fail? result__83253)
                   (recur (clojure.core/next search_space__83252))
                   result__83253))
                 (state__83211)))
               (state__83211))
              (state__83211)))
            (state__83211))
           (state__83211))))
        (state__83211
         []
         (if
          (clojure.core/vector? input__82096)
          (clojure.core/letfn
           [(state__83255
             []
             (if
              (clojure.core/= (clojure.core/count input__82096) 3)
              (clojure.core/let
               [input__82096_nth_0__
                (clojure.core/nth input__82096 0)
                input__82096_nth_1__
                (clojure.core/nth input__82096 1)
                input__82096_nth_2__
                (clojure.core/nth input__82096 2)]
               (clojure.core/case
                input__82096_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__82096_nth_1__)
                 (clojure.core/letfn
                  [(state__83258
                    []
                    (clojure.core/let
                     [n__82284
                      (clojure.core/count input__82096_nth_1__)
                      m__82285
                      (clojure.core/max 0 (clojure.core/- n__82284 2))
                      input__82096_nth_1___l__
                      (clojure.core/subvec
                       input__82096_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__82096_nth_1__)
                        m__82285))
                      input__82096_nth_1___r__
                      (clojure.core/subvec
                       input__82096_nth_1__
                       m__82285)]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__82096_nth_1___r__)
                       2)
                      (clojure.core/let
                       [!xs
                        (clojure.core/vec input__82096_nth_1___l__)]
                       (if
                        (clojure.core/=
                         (clojure.core/count input__82096_nth_1___r__)
                         2)
                        (clojure.core/let
                         [input__82096_nth_1___r___nth_0__
                          (clojure.core/nth input__82096_nth_1___r__ 0)
                          input__82096_nth_1___r___nth_1__
                          (clojure.core/nth
                           input__82096_nth_1___r__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__82096_nth_1___r___nth_0__)
                          (clojure.core/let
                           [X__82289
                            (clojure.core/namespace
                             input__82096_nth_1___r___nth_0__)]
                           (clojure.core/let
                            [?ns X__82289]
                            (clojure.core/let
                             [X__82291
                              (clojure.core/name
                               input__82096_nth_1___r___nth_0__)]
                             (if
                              (clojure.core/string? X__82291)
                              (clojure.core/let
                               [ret__82292
                                (clojure.core/re-matches
                                 #"&.*"
                                 X__82291)]
                               (if
                                (clojure.core/some? ret__82292)
                                (clojure.core/let
                                 [?name ret__82292]
                                 (clojure.core/let
                                  [?pattern
                                   input__82096_nth_1___r___nth_1__]
                                  (if
                                   (clojure.core/map?
                                    input__82096_nth_2__)
                                   (clojure.core/let
                                    [VAL__82276
                                     (.valAt
                                      input__82096_nth_2__
                                      :aliases)]
                                    (if
                                     (clojure.core/map? VAL__82276)
                                     (clojure.core/let
                                      [X__82278
                                       (clojure.core/set VAL__82276)]
                                      (if
                                       (clojure.core/<=
                                        1
                                        (clojure.core/count X__82278))
                                       (clojure.core/loop
                                        [search_space__83262
                                         (clojure.core/seq X__82278)]
                                        (if
                                         (clojure.core/seq
                                          search_space__83262)
                                         (clojure.core/let
                                          [elem__82279
                                           (clojure.core/first
                                            search_space__83262)
                                           result__83263
                                           (clojure.core/let
                                            [elem__82279_nth_0__
                                             (clojure.core/nth
                                              elem__82279
                                              0)
                                             elem__82279_nth_1__
                                             (clojure.core/nth
                                              elem__82279
                                              1)]
                                            (if
                                             (clojure.core/symbol?
                                              elem__82279_nth_0__)
                                             (clojure.core/let
                                              [X__82281
                                               (clojure.core/name
                                                elem__82279_nth_0__)]
                                              (if
                                               (clojure.core/=
                                                ?ns
                                                X__82281)
                                               (if
                                                (clojure.core/symbol?
                                                 elem__82279_nth_1__)
                                                (clojure.core/let
                                                 [X__82283
                                                  (clojure.core/name
                                                   elem__82279_nth_1__)]
                                                 (clojure.core/case
                                                  X__82283
                                                  ("meander.zeta")
                                                  (clojure.core/let
                                                   [?env
                                                    input__82096_nth_2__]
                                                   (try
                                                    [(clojure.core/let
                                                      [!xs__counter
                                                       (meander.runtime.zeta/iterator
                                                        !xs)]
                                                      (clojure.core/let
                                                       [CATA_RESULT__9229__auto__
                                                        (CATA__FN__82152
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
                                            result__83263)
                                           (recur
                                            (clojure.core/next
                                             search_space__83262))
                                           result__83263))
                                         (state__83259)))
                                       (state__83259)))
                                     (state__83259)))
                                   (state__83259))))
                                (state__83259)))
                              (state__83259)))))
                          (state__83259)))
                        (state__83259)))
                      (state__83259))))
                   (state__83259
                    []
                    (clojure.core/loop
                     [search_space__83265
                      (meander.match.runtime.epsilon/partitions
                       2
                       input__82096_nth_1__)]
                     (if
                      (clojure.core/seq search_space__83265)
                      (clojure.core/let
                       [input__82096_nth_1___parts__
                        (clojure.core/first search_space__83265)
                        result__83266
                        (clojure.core/let
                         [input__82096_nth_1___l__
                          (clojure.core/nth
                           input__82096_nth_1___parts__
                           0)
                          input__82096_nth_1___r__
                          (clojure.core/nth
                           input__82096_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs
                           (clojure.core/vec input__82096_nth_1___l__)]
                          (clojure.core/let
                           [input__82096_nth_1___r___l__
                            (clojure.core/subvec
                             input__82096_nth_1___r__
                             0
                             (clojure.core/min
                              (clojure.core/count
                               input__82096_nth_1___r__)
                              1))]
                           (if
                            (clojure.core/=
                             (clojure.core/count
                              input__82096_nth_1___r___l__)
                             1)
                            (clojure.core/let
                             [input__82096_nth_1___r___r__
                              (clojure.core/subvec
                               input__82096_nth_1___r__
                               1)]
                             (if
                              (clojure.core/=
                               input__82096_nth_1___r___l__
                               ['.])
                              (clojure.core/let
                               [?rest input__82096_nth_1___r___r__]
                               (clojure.core/let
                                [?env input__82096_nth_2__]
                                (try
                                 [(clojure.core/let
                                   [!xs__counter
                                    (meander.runtime.zeta/iterator
                                     !xs)]
                                   (clojure.core/let
                                    [CATA_RESULT__9229__auto__
                                     (CATA__FN__82152
                                      ['meander.dev.parse.zeta/make-join
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__82152
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
                                         (CATA__FN__82152
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
                        (meander.runtime.zeta/fail? result__83266)
                        (recur (clojure.core/next search_space__83265))
                        result__83266))
                      (state__83260))))
                   (state__83260
                    []
                    (clojure.core/let
                     [input__82096_nth_1___l__
                      (clojure.core/subvec
                       input__82096_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__82096_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__82096_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__82096_nth_1___r__
                        (clojure.core/subvec input__82096_nth_1__ 1)]
                       (if
                        (clojure.core/=
                         input__82096_nth_1___l__
                         ['...])
                        (clojure.core/let
                         [?rest input__82096_nth_1___r__]
                         (clojure.core/let
                          [?env input__82096_nth_2__]
                          (try
                           [(clojure.core/let
                             [CATA_RESULT__9229__auto__
                              (CATA__FN__82152
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
                        (state__83261)))
                      (state__83261))))
                   (state__83261
                    []
                    (clojure.core/loop
                     [search_space__83268
                      (meander.match.runtime.epsilon/partitions
                       2
                       input__82096_nth_1__)]
                     (if
                      (clojure.core/seq search_space__83268)
                      (clojure.core/let
                       [input__82096_nth_1___parts__
                        (clojure.core/first search_space__83268)
                        result__83269
                        (clojure.core/let
                         [input__82096_nth_1___l__
                          (clojure.core/nth
                           input__82096_nth_1___parts__
                           0)
                          input__82096_nth_1___r__
                          (clojure.core/nth
                           input__82096_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs []]
                          (clojure.core/let
                           [ret__8090__auto__
                            (meander.runtime.zeta/epsilon-run-star-1
                             input__82096_nth_1___l__
                             [!xs]
                             (clojure.core/fn
                              [[!xs] input__82309]
                              (clojure.core/let
                               [input__82309_nth_0__
                                (clojure.core/nth input__82309 0)]
                               (clojure.core/letfn
                                [(save__82310
                                  []
                                  (meander.runtime.zeta/fail))
                                 (f__83272
                                  []
                                  (clojure.core/let
                                   [!xs
                                    (clojure.core/conj
                                     !xs
                                     input__82309_nth_0__)]
                                   [!xs]))]
                                (if
                                 (clojure.core/symbol?
                                  input__82309_nth_0__)
                                 (clojure.core/let
                                  [X__82312
                                   (clojure.core/namespace
                                    input__82309_nth_0__)]
                                  (clojure.core/case
                                   X__82312
                                   (nil)
                                   (clojure.core/let
                                    [X__82314
                                     (clojure.core/name
                                      input__82309_nth_0__)]
                                    (if
                                     (clojure.core/string? X__82314)
                                     (if
                                      (clojure.core/re-matches
                                       #"\.\.(?:\.|\d+)"
                                       X__82314)
                                      (save__82310)
                                      (f__83272))
                                     (f__83272)))
                                   (f__83272)))
                                 (f__83272)))))
                             (clojure.core/fn
                              [[!xs]]
                              (clojure.core/let
                               [input__82096_nth_1___r___l__
                                (clojure.core/subvec
                                 input__82096_nth_1___r__
                                 0
                                 (clojure.core/min
                                  (clojure.core/count
                                   input__82096_nth_1___r__)
                                  1))]
                               (if
                                (clojure.core/=
                                 (clojure.core/count
                                  input__82096_nth_1___r___l__)
                                 1)
                                (clojure.core/let
                                 [input__82096_nth_1___r___r__
                                  (clojure.core/subvec
                                   input__82096_nth_1___r__
                                   1)]
                                 (if
                                  (clojure.core/=
                                   input__82096_nth_1___r___l__
                                   ['...])
                                  (clojure.core/let
                                   [?rest input__82096_nth_1___r___r__]
                                   (clojure.core/let
                                    [?env input__82096_nth_2__]
                                    (try
                                     [(clojure.core/let
                                       [!xs__counter
                                        (meander.runtime.zeta/iterator
                                         !xs)]
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__82152
                                          ['meander.dev.parse.zeta/make-star
                                           (clojure.core/let
                                            [CATA_RESULT__9229__auto__
                                             (CATA__FN__82152
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
                                             (CATA__FN__82152
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
                        (meander.runtime.zeta/fail? result__83269)
                        (recur (clojure.core/next search_space__83268))
                        result__83269))
                      (state__83256))))]
                  (state__83258))
                 (state__83256))
                (state__83256)))
              (state__83256)))
            (state__83256
             []
             (if
              (clojure.core/= (clojure.core/count input__82096) 4)
              (clojure.core/let
               [input__82096_nth_0__
                (clojure.core/nth input__82096 0)
                input__82096_nth_1__
                (clojure.core/nth input__82096 1)
                input__82096_nth_2__
                (clojure.core/nth input__82096 2)]
               (clojure.core/letfn
                [(state__83273
                  []
                  (clojure.core/let
                   [input__82096_nth_3__
                    (clojure.core/nth input__82096 3)]
                   (clojure.core/case
                    input__82096_nth_0__
                    (meander.dev.parse.zeta/make-star)
                    (clojure.core/letfn
                     [(state__83275
                       []
                       (if
                        (clojure.core/map? input__82096_nth_1__)
                        (clojure.core/let
                         [VAL__82318
                          (.valAt input__82096_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__82318
                          (:cat)
                          (clojure.core/let
                           [VAL__82319
                            (.valAt input__82096_nth_1__ :sequence)]
                           (if
                            (clojure.core/vector? VAL__82319)
                            (if
                             (clojure.core/=
                              (clojure.core/count VAL__82319)
                              1)
                             (clojure.core/let
                              [VAL__82319_nth_0__
                               (clojure.core/nth VAL__82319 0)]
                              (if
                               (clojure.core/map? VAL__82319_nth_0__)
                               (clojure.core/let
                                [VAL__82324
                                 (.valAt VAL__82319_nth_0__ :tag)]
                                (clojure.core/case
                                 VAL__82324
                                 (:memory-variable)
                                 (clojure.core/let
                                  [?memory-variable VAL__82319_nth_0__]
                                  (clojure.core/let
                                   [VAL__82320
                                    (.valAt
                                     input__82096_nth_1__
                                     :next)]
                                   (if
                                    (clojure.core/map? VAL__82320)
                                    (clojure.core/let
                                     [VAL__82321
                                      (.valAt VAL__82320 :tag)]
                                     (clojure.core/case
                                      VAL__82321
                                      (:empty)
                                      (clojure.core/let
                                       [?next input__82096_nth_2__]
                                       (clojure.core/let
                                        [?env input__82096_nth_3__]
                                        (try
                                         [(clojure.core/let
                                           [CATA_RESULT__9229__auto__
                                            (CATA__FN__82152
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
                                      (state__83276)))
                                    (state__83276))))
                                 (state__83276)))
                               (state__83276)))
                             (state__83276))
                            (state__83276)))
                          (state__83276)))
                        (state__83276)))
                      (state__83276
                       []
                       (clojure.core/let
                        [?pattern input__82096_nth_1__]
                        (clojure.core/let
                         [?next input__82096_nth_2__]
                         (if
                          (clojure.core/map? input__82096_nth_3__)
                          (clojure.core/let
                           [VAL__82327
                            (.valAt input__82096_nth_3__ :context)]
                           (clojure.core/case
                            VAL__82327
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
                            (state__83274)))
                          (state__83274)))))]
                     (state__83275))
                    (state__83274))))
                 (state__83274
                  []
                  (clojure.core/case
                   input__82096_nth_0__
                   (meander.dev.parse.zeta/make-star)
                   (clojure.core/let
                    [?pattern input__82096_nth_1__]
                    (clojure.core/let
                     [?next input__82096_nth_2__]
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
                   (state__83257)))]
                (state__83273)))
              (state__83257)))
            (state__83257
             []
             (if
              (clojure.core/= (clojure.core/count input__82096) 3)
              (clojure.core/let
               [input__82096_nth_0__
                (clojure.core/nth input__82096 0)
                input__82096_nth_1__
                (clojure.core/nth input__82096 1)
                input__82096_nth_2__
                (clojure.core/nth input__82096 2)]
               (clojure.core/case
                input__82096_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__82096_nth_1__)
                 (clojure.core/let
                  [input__82096_nth_1___l__
                   (clojure.core/subvec
                    input__82096_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__82096_nth_1__)
                     1))]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__82096_nth_1___l__)
                    1)
                   (clojure.core/let
                    [input__82096_nth_1___r__
                     (clojure.core/subvec input__82096_nth_1__ 1)]
                    (clojure.core/let
                     [input__82096_nth_1___l___nth_0__
                      (clojure.core/nth input__82096_nth_1___l__ 0)]
                     (if
                      (clojure.core/symbol?
                       input__82096_nth_1___l___nth_0__)
                      (clojure.core/let
                       [X__82335
                        (clojure.core/namespace
                         input__82096_nth_1___l___nth_0__)]
                       (clojure.core/case
                        X__82335
                        (nil)
                        (clojure.core/let
                         [X__82337
                          (clojure.core/name
                           input__82096_nth_1___l___nth_0__)]
                         (if
                          (clojure.core/string? X__82337)
                          (clojure.core/let
                           [ret__82338
                            (clojure.core/re-matches
                             #"\.\.(\d+)"
                             X__82337)]
                           (if
                            (clojure.core/some? ret__82338)
                            (if
                             (clojure.core/vector? ret__82338)
                             (if
                              (clojure.core/=
                               (clojure.core/count ret__82338)
                               2)
                              (clojure.core/let
                               [ret__82338_nth_1__
                                (clojure.core/nth ret__82338 1)]
                               (clojure.core/let
                                [?n ret__82338_nth_1__]
                                (clojure.core/let
                                 [?operator
                                  input__82096_nth_1___l___nth_0__]
                                 (clojure.core/let
                                  [?rest input__82096_nth_1___r__]
                                  (clojure.core/let
                                   [?env input__82096_nth_2__]
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
                              (state__83212))
                             (state__83212))
                            (state__83212)))
                          (state__83212)))
                        (state__83212)))
                      (state__83212))))
                   (state__83212)))
                 (state__83212))
                (state__83212)))
              (state__83212)))]
           (state__83255))
          (state__83212)))
        (state__83212
         []
         (clojure.core/letfn
          [(def__82341
            [arg__82365]
            (clojure.core/letfn
             [(state__83277
               []
               (clojure.core/let
                [x__82366 :string-plus]
                (clojure.core/let
                 [?tag x__82366]
                 (if
                  (clojure.core/map? arg__82365)
                  (clojure.core/let
                   [VAL__82367 (.valAt arg__82365 :context)]
                   (clojure.core/case
                    VAL__82367
                    (:string)
                    (clojure.core/let [?env arg__82365] [?tag ?env])
                    (state__83278)))
                  (state__83278)))))
              (state__83278
               []
               (clojure.core/let
                [x__82368 :plus]
                (clojure.core/let
                 [?tag x__82368]
                 (clojure.core/let [?env arg__82365] [?tag ?env]))))]
             (state__83277)))]
          (if
           (clojure.core/vector? input__82096)
           (if
            (clojure.core/= (clojure.core/count input__82096) 3)
            (clojure.core/let
             [input__82096_nth_0__
              (clojure.core/nth input__82096 0)
              input__82096_nth_1__
              (clojure.core/nth input__82096 1)
              input__82096_nth_2__
              (clojure.core/nth input__82096 2)]
             (clojure.core/case
              input__82096_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__82096_nth_1__)
               (clojure.core/loop
                [search_space__83279
                 (meander.match.runtime.epsilon/partitions
                  2
                  input__82096_nth_1__)]
                (if
                 (clojure.core/seq search_space__83279)
                 (clojure.core/let
                  [input__82096_nth_1___parts__
                   (clojure.core/first search_space__83279)
                   result__83280
                   (clojure.core/let
                    [input__82096_nth_1___l__
                     (clojure.core/nth input__82096_nth_1___parts__ 0)
                     input__82096_nth_1___r__
                     (clojure.core/nth input__82096_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__8090__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__82096_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__82358]
                         (clojure.core/let
                          [input__82358_nth_0__
                           (clojure.core/nth input__82358 0)]
                          (clojure.core/letfn
                           [(save__82359
                             []
                             (meander.runtime.zeta/fail))
                            (f__83283
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__82358_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol? input__82358_nth_0__)
                            (clojure.core/let
                             [X__82361
                              (clojure.core/namespace
                               input__82358_nth_0__)]
                             (clojure.core/case
                              X__82361
                              (nil)
                              (clojure.core/let
                               [X__82363
                                (clojure.core/name
                                 input__82358_nth_0__)]
                               (if
                                (clojure.core/string? X__82363)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__82363)
                                 (save__82359)
                                 (f__83283))
                                (f__83283)))
                              (f__83283)))
                            (f__83283)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__82096_nth_1___r___l__
                           (clojure.core/subvec
                            input__82096_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__82096_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__82096_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__82096_nth_1___r___r__
                             (clojure.core/subvec
                              input__82096_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__82096_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__82096_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__82096_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__82352
                                (clojure.core/namespace
                                 input__82096_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__82352
                                (nil)
                                (clojure.core/let
                                 [X__82354
                                  (clojure.core/name
                                   input__82096_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__82354)
                                  (clojure.core/let
                                   [ret__82355
                                    (clojure.core/re-matches
                                     #"\.\.(\d+)"
                                     X__82354)]
                                   (if
                                    (clojure.core/some? ret__82355)
                                    (if
                                     (clojure.core/vector? ret__82355)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__82355)
                                       2)
                                      (clojure.core/let
                                       [ret__82355_nth_1__
                                        (clojure.core/nth
                                         ret__82355
                                         1)]
                                       (clojure.core/let
                                        [?n ret__82355_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__82096_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__7926__auto__
                                           (def__82341
                                            input__82096_nth_2__)]
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
                                                  (CATA__FN__82152
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
                                                  (CATA__FN__82152
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
                   (meander.runtime.zeta/fail? result__83280)
                   (recur (clojure.core/next search_space__83279))
                   result__83280))
                 (state__83213)))
               (state__83213))
              (state__83213)))
            (state__83213))
           (state__83213))))
        (state__83213
         []
         (if
          (clojure.core/vector? input__82096)
          (if
           (clojure.core/= (clojure.core/count input__82096) 3)
           (clojure.core/let
            [input__82096_nth_0__
             (clojure.core/nth input__82096 0)
             input__82096_nth_1__
             (clojure.core/nth input__82096 1)
             input__82096_nth_2__
             (clojure.core/nth input__82096 2)]
            (clojure.core/case
             input__82096_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__82096_nth_1__)
              (clojure.core/let
               [input__82096_nth_1___l__
                (clojure.core/subvec
                 input__82096_nth_1__
                 0
                 (clojure.core/min
                  (clojure.core/count input__82096_nth_1__)
                  1))]
               (if
                (clojure.core/=
                 (clojure.core/count input__82096_nth_1___l__)
                 1)
                (clojure.core/let
                 [input__82096_nth_1___r__
                  (clojure.core/subvec input__82096_nth_1__ 1)]
                 (clojure.core/let
                  [input__82096_nth_1___l___nth_0__
                   (clojure.core/nth input__82096_nth_1___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__82096_nth_1___l___nth_0__)
                   (clojure.core/let
                    [X__82386
                     (clojure.core/namespace
                      input__82096_nth_1___l___nth_0__)]
                    (clojure.core/case
                     X__82386
                     (nil)
                     (clojure.core/let
                      [X__82388
                       (clojure.core/name
                        input__82096_nth_1___l___nth_0__)]
                      (if
                       (clojure.core/string? X__82388)
                       (clojure.core/let
                        [ret__82389
                         (clojure.core/re-matches
                          #"\.\.(\?.+)"
                          X__82388)]
                        (if
                         (clojure.core/some? ret__82389)
                         (if
                          (clojure.core/vector? ret__82389)
                          (if
                           (clojure.core/=
                            (clojure.core/count ret__82389)
                            2)
                           (clojure.core/let
                            [ret__82389_nth_1__
                             (clojure.core/nth ret__82389 1)]
                            (clojure.core/let
                             [?n ret__82389_nth_1__]
                             (clojure.core/let
                              [?operator
                               input__82096_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?rest input__82096_nth_1___r__]
                               (clojure.core/let
                                [?env input__82096_nth_2__]
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
                           (state__83214))
                          (state__83214))
                         (state__83214)))
                       (state__83214)))
                     (state__83214)))
                   (state__83214))))
                (state__83214)))
              (state__83214))
             (state__83214)))
           (state__83214))
          (state__83214)))
        (state__83214
         []
         (clojure.core/letfn
          [(def__82392
            [arg__82416]
            (clojure.core/letfn
             [(state__83284
               []
               (clojure.core/let
                [x__82417 :string-logical-plus]
                (clojure.core/let
                 [?tag x__82417]
                 (if
                  (clojure.core/map? arg__82416)
                  (clojure.core/let
                   [VAL__82418 (.valAt arg__82416 :context)]
                   (clojure.core/case
                    VAL__82418
                    (:string)
                    (clojure.core/let [?env arg__82416] [?tag ?env])
                    (state__83285)))
                  (state__83285)))))
              (state__83285
               []
               (clojure.core/let
                [x__82419 :logical-plus]
                (clojure.core/let
                 [?tag x__82419]
                 (clojure.core/let [?env arg__82416] [?tag ?env]))))]
             (state__83284)))]
          (if
           (clojure.core/vector? input__82096)
           (if
            (clojure.core/= (clojure.core/count input__82096) 3)
            (clojure.core/let
             [input__82096_nth_0__
              (clojure.core/nth input__82096 0)
              input__82096_nth_1__
              (clojure.core/nth input__82096 1)
              input__82096_nth_2__
              (clojure.core/nth input__82096 2)]
             (clojure.core/case
              input__82096_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__82096_nth_1__)
               (clojure.core/loop
                [search_space__83286
                 (meander.match.runtime.epsilon/partitions
                  2
                  input__82096_nth_1__)]
                (if
                 (clojure.core/seq search_space__83286)
                 (clojure.core/let
                  [input__82096_nth_1___parts__
                   (clojure.core/first search_space__83286)
                   result__83287
                   (clojure.core/let
                    [input__82096_nth_1___l__
                     (clojure.core/nth input__82096_nth_1___parts__ 0)
                     input__82096_nth_1___r__
                     (clojure.core/nth input__82096_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__8090__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__82096_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__82409]
                         (clojure.core/let
                          [input__82409_nth_0__
                           (clojure.core/nth input__82409 0)]
                          (clojure.core/letfn
                           [(save__82410
                             []
                             (meander.runtime.zeta/fail))
                            (f__83290
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__82409_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol? input__82409_nth_0__)
                            (clojure.core/let
                             [X__82412
                              (clojure.core/namespace
                               input__82409_nth_0__)]
                             (clojure.core/case
                              X__82412
                              (nil)
                              (clojure.core/let
                               [X__82414
                                (clojure.core/name
                                 input__82409_nth_0__)]
                               (if
                                (clojure.core/string? X__82414)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__82414)
                                 (save__82410)
                                 (f__83290))
                                (f__83290)))
                              (f__83290)))
                            (f__83290)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__82096_nth_1___r___l__
                           (clojure.core/subvec
                            input__82096_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__82096_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__82096_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__82096_nth_1___r___r__
                             (clojure.core/subvec
                              input__82096_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__82096_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__82096_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__82096_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__82403
                                (clojure.core/namespace
                                 input__82096_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__82403
                                (nil)
                                (clojure.core/let
                                 [X__82405
                                  (clojure.core/name
                                   input__82096_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__82405)
                                  (clojure.core/let
                                   [ret__82406
                                    (clojure.core/re-matches
                                     #"\.\.(\?.+)"
                                     X__82405)]
                                   (if
                                    (clojure.core/some? ret__82406)
                                    (if
                                     (clojure.core/vector? ret__82406)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__82406)
                                       2)
                                      (clojure.core/let
                                       [ret__82406_nth_1__
                                        (clojure.core/nth
                                         ret__82406
                                         1)]
                                       (clojure.core/let
                                        [?n ret__82406_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__82096_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__7926__auto__
                                           (def__82392
                                            input__82096_nth_2__)]
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
                                                  (CATA__FN__82152
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
                                                  (CATA__FN__82152
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
                   (meander.runtime.zeta/fail? result__83287)
                   (recur (clojure.core/next search_space__83286))
                   result__83287))
                 (state__83215)))
               (state__83215))
              (state__83215)))
            (state__83215))
           (state__83215))))
        (state__83215
         []
         (if
          (clojure.core/vector? input__82096)
          (if
           (clojure.core/= (clojure.core/count input__82096) 3)
           (clojure.core/let
            [input__82096_nth_0__
             (clojure.core/nth input__82096 0)
             input__82096_nth_1__
             (clojure.core/nth input__82096 1)
             input__82096_nth_2__
             (clojure.core/nth input__82096 2)]
            (clojure.core/case
             input__82096_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__82096_nth_1__)
              (clojure.core/let
               [input__82096_nth_1___l__
                (clojure.core/subvec
                 input__82096_nth_1__
                 0
                 (clojure.core/min
                  (clojure.core/count input__82096_nth_1__)
                  1))]
               (if
                (clojure.core/=
                 (clojure.core/count input__82096_nth_1___l__)
                 1)
                (clojure.core/let
                 [input__82096_nth_1___r__
                  (clojure.core/subvec input__82096_nth_1__ 1)]
                 (clojure.core/let
                  [input__82096_nth_1___l___nth_0__
                   (clojure.core/nth input__82096_nth_1___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__82096_nth_1___l___nth_0__)
                   (clojure.core/let
                    [X__82437
                     (clojure.core/namespace
                      input__82096_nth_1___l___nth_0__)]
                    (clojure.core/case
                     X__82437
                     (nil)
                     (clojure.core/let
                      [X__82439
                       (clojure.core/name
                        input__82096_nth_1___l___nth_0__)]
                      (if
                       (clojure.core/string? X__82439)
                       (clojure.core/let
                        [ret__82440
                         (clojure.core/re-matches
                          #"\.\.(!.+)"
                          X__82439)]
                        (if
                         (clojure.core/some? ret__82440)
                         (if
                          (clojure.core/vector? ret__82440)
                          (if
                           (clojure.core/=
                            (clojure.core/count ret__82440)
                            2)
                           (clojure.core/let
                            [ret__82440_nth_1__
                             (clojure.core/nth ret__82440 1)]
                            (clojure.core/let
                             [?n ret__82440_nth_1__]
                             (clojure.core/let
                              [?operator
                               input__82096_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?rest input__82096_nth_1___r__]
                               (clojure.core/let
                                [?env input__82096_nth_2__]
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
                           (state__83216))
                          (state__83216))
                         (state__83216)))
                       (state__83216)))
                     (state__83216)))
                   (state__83216))))
                (state__83216)))
              (state__83216))
             (state__83216)))
           (state__83216))
          (state__83216)))
        (state__83216
         []
         (clojure.core/letfn
          [(def__82443
            [arg__82467]
            (clojure.core/letfn
             [(state__83291
               []
               (clojure.core/let
                [x__82468 :string-memory-plus]
                (clojure.core/let
                 [?tag x__82468]
                 (if
                  (clojure.core/map? arg__82467)
                  (clojure.core/let
                   [VAL__82469 (.valAt arg__82467 :context)]
                   (clojure.core/case
                    VAL__82469
                    (:string)
                    (clojure.core/let [?env arg__82467] [?tag ?env])
                    (state__83292)))
                  (state__83292)))))
              (state__83292
               []
               (clojure.core/let
                [x__82470 :memory-plus]
                (clojure.core/let
                 [?tag x__82470]
                 (clojure.core/let [?env arg__82467] [?tag ?env]))))]
             (state__83291)))]
          (if
           (clojure.core/vector? input__82096)
           (if
            (clojure.core/= (clojure.core/count input__82096) 3)
            (clojure.core/let
             [input__82096_nth_0__
              (clojure.core/nth input__82096 0)
              input__82096_nth_1__
              (clojure.core/nth input__82096 1)
              input__82096_nth_2__
              (clojure.core/nth input__82096 2)]
             (clojure.core/case
              input__82096_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__82096_nth_1__)
               (clojure.core/loop
                [search_space__83293
                 (meander.match.runtime.epsilon/partitions
                  2
                  input__82096_nth_1__)]
                (if
                 (clojure.core/seq search_space__83293)
                 (clojure.core/let
                  [input__82096_nth_1___parts__
                   (clojure.core/first search_space__83293)
                   result__83294
                   (clojure.core/let
                    [input__82096_nth_1___l__
                     (clojure.core/nth input__82096_nth_1___parts__ 0)
                     input__82096_nth_1___r__
                     (clojure.core/nth input__82096_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__8090__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__82096_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__82460]
                         (clojure.core/let
                          [input__82460_nth_0__
                           (clojure.core/nth input__82460 0)]
                          (clojure.core/letfn
                           [(save__82461
                             []
                             (meander.runtime.zeta/fail))
                            (f__83297
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__82460_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol? input__82460_nth_0__)
                            (clojure.core/let
                             [X__82463
                              (clojure.core/namespace
                               input__82460_nth_0__)]
                             (clojure.core/case
                              X__82463
                              (nil)
                              (clojure.core/let
                               [X__82465
                                (clojure.core/name
                                 input__82460_nth_0__)]
                               (if
                                (clojure.core/string? X__82465)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__82465)
                                 (save__82461)
                                 (f__83297))
                                (f__83297)))
                              (f__83297)))
                            (f__83297)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__82096_nth_1___r___l__
                           (clojure.core/subvec
                            input__82096_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__82096_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__82096_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__82096_nth_1___r___r__
                             (clojure.core/subvec
                              input__82096_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__82096_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__82096_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__82096_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__82454
                                (clojure.core/namespace
                                 input__82096_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__82454
                                (nil)
                                (clojure.core/let
                                 [X__82456
                                  (clojure.core/name
                                   input__82096_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__82456)
                                  (clojure.core/let
                                   [ret__82457
                                    (clojure.core/re-matches
                                     #"\.\.(\!.+)"
                                     X__82456)]
                                   (if
                                    (clojure.core/some? ret__82457)
                                    (if
                                     (clojure.core/vector? ret__82457)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__82457)
                                       2)
                                      (clojure.core/let
                                       [ret__82457_nth_1__
                                        (clojure.core/nth
                                         ret__82457
                                         1)]
                                       (clojure.core/let
                                        [?n ret__82457_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__82096_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__7926__auto__
                                           (def__82443
                                            input__82096_nth_2__)]
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
                                                  (CATA__FN__82152
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
                                                  (CATA__FN__82152
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
                   (meander.runtime.zeta/fail? result__83294)
                   (recur (clojure.core/next search_space__83293))
                   result__83294))
                 (state__83217)))
               (state__83217))
              (state__83217)))
            (state__83217))
           (state__83217))))
        (state__83217
         []
         (if
          (clojure.core/vector? input__82096)
          (clojure.core/letfn
           [(state__83298
             []
             (if
              (clojure.core/= (clojure.core/count input__82096) 3)
              (clojure.core/let
               [input__82096_nth_0__
                (clojure.core/nth input__82096 0)
                input__82096_nth_1__
                (clojure.core/nth input__82096 1)
                input__82096_nth_2__
                (clojure.core/nth input__82096 2)]
               (clojure.core/case
                input__82096_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__82096_nth_1__)
                 (clojure.core/let
                  [!xs (clojure.core/vec input__82096_nth_1__)]
                  (clojure.core/let
                   [?env input__82096_nth_2__]
                   (try
                    [(clojure.core/let
                      [!xs__counter
                       (meander.runtime.zeta/iterator !xs)]
                      (clojure.core/let
                       [CATA_RESULT__9229__auto__
                        (CATA__FN__82152
                         ['meander.dev.parse.zeta/make-cat
                          (clojure.core/into
                           []
                           (clojure.core/loop
                            [return__82153 (clojure.core/transient [])]
                            (if
                             (clojure.core/and (.hasNext !xs__counter))
                             (recur
                              (clojure.core/conj!
                               return__82153
                               (clojure.core/let
                                [CATA_RESULT__9229__auto__
                                 (CATA__FN__82152
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
                              return__82153))))
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
                 (state__83299))
                (state__83299)))
              (state__83299)))
            (state__83299
             []
             (if
              (clojure.core/= (clojure.core/count input__82096) 4)
              (clojure.core/let
               [input__82096_nth_0__
                (clojure.core/nth input__82096 0)
                input__82096_nth_1__
                (clojure.core/nth input__82096 1)
                input__82096_nth_2__
                (clojure.core/nth input__82096 2)]
               (clojure.core/letfn
                [(state__83301
                  []
                  (clojure.core/case
                   input__82096_nth_0__
                   (meander.dev.parse.zeta/make-cat)
                   (if
                    (clojure.core/vector? input__82096_nth_1__)
                    (clojure.core/let
                     [!forms []]
                     (clojure.core/let
                      [ret__8090__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__82096_nth_1__
                        [!forms]
                        (clojure.core/fn
                         [[!forms] input__82492]
                         (clojure.core/let
                          [input__82492_nth_0__
                           (clojure.core/nth input__82492 0)]
                          (if
                           (clojure.core/map? input__82492_nth_0__)
                           (clojure.core/let
                            [VAL__82493
                             (.valAt input__82492_nth_0__ :tag)]
                            (clojure.core/case
                             VAL__82493
                             (:literal)
                             (clojure.core/let
                              [VAL__82494
                               (.valAt input__82492_nth_0__ :type)]
                              (if
                               (clojure.core/let
                                [x__6986__auto__ VAL__82494]
                                (clojure.core/case
                                 x__6986__auto__
                                 (:string :char)
                                 true
                                 false))
                               (clojure.core/let
                                [VAL__82495
                                 (.valAt input__82492_nth_0__ :form)]
                                (clojure.core/let
                                 [!forms
                                  (clojure.core/conj
                                   !forms
                                   VAL__82495)]
                                 [!forms]))
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail)))
                           (meander.runtime.zeta/fail))))
                        (clojure.core/fn
                         [[!forms]]
                         (if
                          (clojure.core/map? input__82096_nth_2__)
                          (clojure.core/let
                           [VAL__82489
                            (.valAt input__82096_nth_2__ :tag)]
                           (clojure.core/case
                            VAL__82489
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
                            (state__83302)))
                          (state__83302))))]
                      (if
                       (meander.runtime.zeta/fail? ret__8090__auto__)
                       (state__83302)
                       ret__8090__auto__)))
                    (state__83302))
                   (state__83302)))
                 (state__83302
                  []
                  (clojure.core/let
                   [input__82096_nth_3__
                    (clojure.core/nth input__82096 3)]
                   (clojure.core/case
                    input__82096_nth_0__
                    (meander.dev.parse.zeta/make-cat)
                    (clojure.core/letfn
                     [(state__83308
                       []
                       (if
                        (clojure.core/vector? input__82096_nth_1__)
                        (clojure.core/letfn
                         [(state__83310
                           []
                           (clojure.core/let
                            [input__82096_nth_1___l__
                             (clojure.core/subvec
                              input__82096_nth_1__
                              0
                              (clojure.core/min
                               (clojure.core/count
                                input__82096_nth_1__)
                               1))]
                            (if
                             (clojure.core/=
                              (clojure.core/count
                               input__82096_nth_1___l__)
                              1)
                             (clojure.core/let
                              [input__82096_nth_1___r__
                               (clojure.core/subvec
                                input__82096_nth_1__
                                1)]
                              (clojure.core/let
                               [input__82096_nth_1___l___nth_0__
                                (clojure.core/nth
                                 input__82096_nth_1___l__
                                 0)]
                               (if
                                (clojure.core/map?
                                 input__82096_nth_1___l___nth_0__)
                                (clojure.core/let
                                 [VAL__82501
                                  (.valAt
                                   input__82096_nth_1___l___nth_0__
                                   :tag)]
                                 (clojure.core/case
                                  VAL__82501
                                  (:literal)
                                  (clojure.core/let
                                   [VAL__82502
                                    (.valAt
                                     input__82096_nth_1___l___nth_0__
                                     :type)]
                                   (clojure.core/case
                                    VAL__82502
                                    (:string)
                                    (clojure.core/let
                                     [?ast
                                      input__82096_nth_1___l___nth_0__]
                                     (clojure.core/let
                                      [?rest input__82096_nth_1___r__]
                                      (clojure.core/let
                                       [?next input__82096_nth_2__]
                                       (clojure.core/let
                                        [?env input__82096_nth_3__]
                                        (try
                                         [(clojure.core/let
                                           [CATA_RESULT__9229__auto__
                                            (CATA__FN__82152
                                             ['meander.dev.parse.zeta/make-join
                                              ?ast
                                              (clojure.core/let
                                               [CATA_RESULT__9229__auto__
                                                (CATA__FN__82152
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
                                    (state__83311)))
                                  (state__83311)))
                                (state__83311))))
                             (state__83311))))
                          (state__83311
                           []
                           (clojure.core/let
                            [!forms []]
                            (clojure.core/let
                             [ret__8090__auto__
                              (meander.runtime.zeta/epsilon-run-star-1
                               input__82096_nth_1__
                               [!forms]
                               (clojure.core/fn
                                [[!forms] input__82508]
                                (clojure.core/let
                                 [input__82508_nth_0__
                                  (clojure.core/nth input__82508 0)]
                                 (if
                                  (clojure.core/map?
                                   input__82508_nth_0__)
                                  (clojure.core/let
                                   [VAL__82509
                                    (.valAt input__82508_nth_0__ :tag)]
                                   (clojure.core/case
                                    VAL__82509
                                    (:literal)
                                    (clojure.core/let
                                     [VAL__82510
                                      (.valAt
                                       input__82508_nth_0__
                                       :form)]
                                     (clojure.core/let
                                      [!forms
                                       (clojure.core/conj
                                        !forms
                                        VAL__82510)]
                                      [!forms]))
                                    (meander.runtime.zeta/fail)))
                                  (meander.runtime.zeta/fail))))
                               (clojure.core/fn
                                [[!forms]]
                                (if
                                 (clojure.core/map?
                                  input__82096_nth_2__)
                                 (clojure.core/let
                                  [VAL__82505
                                   (.valAt input__82096_nth_2__ :tag)]
                                  (clojure.core/case
                                   VAL__82505
                                   (:empty)
                                   (clojure.core/let
                                    [?env input__82096_nth_3__]
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
                                   (state__83309)))
                                 (state__83309))))]
                             (if
                              (meander.runtime.zeta/fail?
                               ret__8090__auto__)
                              (state__83309)
                              ret__8090__auto__))))]
                         (state__83310))
                        (state__83309)))
                      (state__83309
                       []
                       (clojure.core/let
                        [?sequence input__82096_nth_1__]
                        (clojure.core/let
                         [?next input__82096_nth_2__]
                         (if
                          (clojure.core/map? input__82096_nth_3__)
                          (clojure.core/let
                           [VAL__82514
                            (.valAt input__82096_nth_3__ :context)]
                           (clojure.core/case
                            VAL__82514
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
                            (state__83303)))
                          (state__83303)))))]
                     (state__83308))
                    (state__83303))))
                 (state__83303
                  []
                  (clojure.core/case
                   input__82096_nth_0__
                   (meander.dev.parse.zeta/make-cat)
                   (clojure.core/let
                    [?sequence input__82096_nth_1__]
                    (clojure.core/let
                     [?next input__82096_nth_2__]
                     (try
                      [{:tag :cat, :sequence ?sequence, :next ?next}]
                      (catch
                       java.lang.Exception
                       e__10169__auto__
                       (if
                        (meander.runtime.zeta/fail? e__10169__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__10169__auto__))))))
                   (state__83304)))
                 (state__83304
                  []
                  (clojure.core/let
                   [input__82096_nth_3__
                    (clojure.core/nth input__82096 3)]
                   (clojure.core/case
                    input__82096_nth_0__
                    (meander.dev.parse.zeta/make-join)
                    (if
                     (clojure.core/map? input__82096_nth_1__)
                     (clojure.core/let
                      [VAL__83204 (.valAt input__82096_nth_1__ :tag)]
                      (clojure.core/case
                       VAL__83204
                       (:cat)
                       (clojure.core/let
                        [VAL__82520
                         (.valAt input__82096_nth_1__ :sequence)]
                        (clojure.core/let
                         [?sequence VAL__82520]
                         (clojure.core/let
                          [VAL__82521
                           (.valAt input__82096_nth_1__ :next)]
                          (if
                           (clojure.core/map? VAL__82521)
                           (clojure.core/let
                            [VAL__82522 (.valAt VAL__82521 :tag)]
                            (clojure.core/case
                             VAL__82522
                             (:empty)
                             (clojure.core/let
                              [?right input__82096_nth_2__]
                              (clojure.core/let
                               [?env input__82096_nth_3__]
                               (try
                                [(clojure.core/let
                                  [CATA_RESULT__9229__auto__
                                   (CATA__FN__82152
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
                             (state__83305)))
                           (state__83305)))))
                       (:literal)
                       (clojure.core/let
                        [VAL__82526
                         (.valAt input__82096_nth_1__ :type)]
                        (clojure.core/case
                         VAL__82526
                         (:string)
                         (clojure.core/let
                          [VAL__82527
                           (.valAt input__82096_nth_1__ :form)]
                          (clojure.core/let
                           [?form-1 VAL__82527]
                           (if
                            (clojure.core/map? input__82096_nth_2__)
                            (clojure.core/let
                             [VAL__82528
                              (.valAt input__82096_nth_2__ :tag)]
                             (clojure.core/case
                              VAL__82528
                              (:string-join)
                              (clojure.core/let
                               [VAL__82529
                                (.valAt input__82096_nth_2__ :left)]
                               (if
                                (clojure.core/map? VAL__82529)
                                (clojure.core/let
                                 [VAL__82530 (.valAt VAL__82529 :tag)]
                                 (clojure.core/case
                                  VAL__82530
                                  (:literal)
                                  (clojure.core/let
                                   [VAL__82531
                                    (.valAt VAL__82529 :type)]
                                   (clojure.core/case
                                    VAL__82531
                                    (:string)
                                    (clojure.core/let
                                     [VAL__82532
                                      (.valAt VAL__82529 :form)]
                                     (clojure.core/let
                                      [?form-2 VAL__82532]
                                      (clojure.core/let
                                       [VAL__82533
                                        (.valAt
                                         input__82096_nth_2__
                                         :right)]
                                       (clojure.core/let
                                        [?right VAL__82533]
                                        (if
                                         (clojure.core/map?
                                          input__82096_nth_3__)
                                         (clojure.core/let
                                          [VAL__82534
                                           (.valAt
                                            input__82096_nth_3__
                                            :context)]
                                          (clojure.core/case
                                           VAL__82534
                                           (:string)
                                           (clojure.core/let
                                            [?env input__82096_nth_3__]
                                            (try
                                             [(clojure.core/let
                                               [CATA_RESULT__9229__auto__
                                                (CATA__FN__82152
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
                                           (state__83305)))
                                         (state__83305))))))
                                    (state__83305)))
                                  (state__83305)))
                                (state__83305)))
                              (state__83305)))
                            (state__83305))))
                         (state__83305)))
                       (state__83305)))
                     (state__83305))
                    (state__83305))))
                 (state__83305
                  []
                  (clojure.core/case
                   input__82096_nth_0__
                   (meander.dev.parse.zeta/make-join)
                   (if
                    (clojure.core/map? input__82096_nth_1__)
                    (clojure.core/let
                     [VAL__83206 (.valAt input__82096_nth_1__ :tag)]
                     (clojure.core/letfn
                      [(state__83313
                        []
                        (clojure.core/letfn
                         [(save__82538 [] (state__83314))
                          (f__83315
                           []
                           (clojure.core/let
                            [?ast input__82096_nth_1__]
                            (if
                             (clojure.core/map? input__82096_nth_2__)
                             (clojure.core/let
                              [VAL__82539
                               (.valAt input__82096_nth_2__ :tag)]
                              (clojure.core/case
                               VAL__82539
                               (:cat)
                               (clojure.core/let
                                [VAL__82540
                                 (.valAt
                                  input__82096_nth_2__
                                  :sequence)]
                                (clojure.core/let
                                 [?sequence VAL__82540]
                                 (clojure.core/let
                                  [VAL__82541
                                   (.valAt input__82096_nth_2__ :next)]
                                  (clojure.core/let
                                   [?next VAL__82541]
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
                               (state__83314)))
                             (state__83314))))]
                         (if
                          (clojure.core/let
                           [x__6986__auto__ VAL__83206]
                           (clojure.core/case
                            x__6986__auto__
                            (:join :star :plus)
                            true
                            false))
                          (save__82538)
                          (f__83315))))
                       (state__83314
                        []
                        (clojure.core/letfn
                         [(save__82545 [] (state__83306))
                          (f__83316
                           []
                           (clojure.core/let
                            [?ast input__82096_nth_1__]
                            (if
                             (clojure.core/map? input__82096_nth_2__)
                             (clojure.core/let
                              [VAL__82546
                               (.valAt input__82096_nth_2__ :tag)]
                              (clojure.core/case
                               VAL__82546
                               (:string-cat)
                               (clojure.core/let
                                [VAL__82547
                                 (.valAt
                                  input__82096_nth_2__
                                  :sequence)]
                                (clojure.core/let
                                 [?sequence VAL__82547]
                                 (clojure.core/let
                                  [VAL__82548
                                   (.valAt input__82096_nth_2__ :next)]
                                  (clojure.core/let
                                   [?next VAL__82548]
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
                               (state__83306)))
                             (state__83306))))]
                         (if
                          (clojure.core/let
                           [x__6986__auto__ VAL__83206]
                           (clojure.core/case
                            x__6986__auto__
                            (:string-join :string-star :string-plus)
                            true
                            false))
                          (save__82545)
                          (f__83316))))]
                      (state__83313)))
                    (state__83306))
                   (state__83306)))
                 (state__83306
                  []
                  (clojure.core/let
                   [input__82096_nth_3__
                    (clojure.core/nth input__82096 3)]
                   (clojure.core/case
                    input__82096_nth_0__
                    (meander.dev.parse.zeta/make-join)
                    (clojure.core/letfn
                     [(state__83317
                       []
                       (if
                        (clojure.core/map? input__82096_nth_1__)
                        (clojure.core/let
                         [VAL__83207
                          (.valAt input__82096_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__83207
                          (:string-cat)
                          (clojure.core/let
                           [VAL__82552
                            (.valAt input__82096_nth_1__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__82552]
                            (clojure.core/let
                             [VAL__82553
                              (.valAt input__82096_nth_1__ :next)]
                             (if
                              (clojure.core/map? VAL__82553)
                              (clojure.core/let
                               [VAL__82554 (.valAt VAL__82553 :tag)]
                               (clojure.core/case
                                VAL__82554
                                (:empty)
                                (clojure.core/let
                                 [?right input__82096_nth_2__]
                                 (clojure.core/let
                                  [?env input__82096_nth_3__]
                                  (try
                                   [(clojure.core/let
                                     [CATA_RESULT__9229__auto__
                                      (CATA__FN__82152
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
                                (state__83318)))
                              (state__83318)))))
                          (:string-star)
                          (clojure.core/let
                           [VAL__82558
                            (.valAt input__82096_nth_1__ :pattern)]
                           (clojure.core/let
                            [?pattern VAL__82558]
                            (clojure.core/let
                             [VAL__82559
                              (.valAt input__82096_nth_1__ :next)]
                             (if
                              (clojure.core/map? VAL__82559)
                              (clojure.core/let
                               [VAL__82560 (.valAt VAL__82559 :tag)]
                               (clojure.core/case
                                VAL__82560
                                (:empty)
                                (clojure.core/let
                                 [?right input__82096_nth_2__]
                                 (if
                                  (clojure.core/map?
                                   input__82096_nth_3__)
                                  (clojure.core/let
                                   [VAL__82561
                                    (.valAt
                                     input__82096_nth_3__
                                     :context)]
                                   (clojure.core/case
                                    VAL__82561
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
                                    (state__83318)))
                                  (state__83318)))
                                (state__83318)))
                              (state__83318)))))
                          (:string-join)
                          (clojure.core/let
                           [VAL__82565
                            (.valAt input__82096_nth_1__ :left)]
                           (clojure.core/let
                            [?left VAL__82565]
                            (clojure.core/let
                             [VAL__82566
                              (.valAt input__82096_nth_1__ :right)]
                             (clojure.core/let
                              [?right-1 VAL__82566]
                              (clojure.core/let
                               [?right-2 input__82096_nth_2__]
                               (if
                                (clojure.core/map?
                                 input__82096_nth_3__)
                                (clojure.core/let
                                 [VAL__82567
                                  (.valAt
                                   input__82096_nth_3__
                                   :context)]
                                 (clojure.core/case
                                  VAL__82567
                                  (:string)
                                  (clojure.core/let
                                   [?env input__82096_nth_3__]
                                   (try
                                    [{:tag :string-join,
                                      :left ?left,
                                      :right
                                      (clojure.core/let
                                       [CATA_RESULT__9229__auto__
                                        (CATA__FN__82152
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
                                  (state__83318)))
                                (state__83318)))))))
                          (state__83318)))
                        (state__83318)))
                      (state__83318
                       []
                       (clojure.core/let
                        [?left input__82096_nth_1__]
                        (if
                         (clojure.core/map? input__82096_nth_2__)
                         (clojure.core/let
                          [VAL__82570
                           (.valAt input__82096_nth_2__ :tag)]
                          (clojure.core/case
                           VAL__82570
                           (:empty)
                           (clojure.core/let
                            [?env input__82096_nth_3__]
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
                           (state__83319)))
                         (state__83319))))
                      (state__83319
                       []
                       (if
                        (clojure.core/map? input__82096_nth_1__)
                        (clojure.core/let
                         [VAL__82573
                          (.valAt input__82096_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__82573
                          (:empty)
                          (clojure.core/let
                           [?right input__82096_nth_2__]
                           (clojure.core/let
                            [?env input__82096_nth_3__]
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
                          (state__83320)))
                        (state__83320)))
                      (state__83320
                       []
                       (clojure.core/let
                        [?left input__82096_nth_1__]
                        (clojure.core/let
                         [?right input__82096_nth_2__]
                         (clojure.core/letfn
                          [(state__83321
                            []
                            (if
                             (clojure.core/map? input__82096_nth_3__)
                             (clojure.core/let
                              [VAL__82576
                               (.valAt input__82096_nth_3__ :context)]
                              (clojure.core/case
                               VAL__82576
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
                               (state__83322)))
                             (state__83322)))
                           (state__83322
                            []
                            (clojure.core/let
                             [?env input__82096_nth_3__]
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
                          (state__83321)))))]
                     (state__83317))
                    (state__83300))))]
                (state__83301)))
              (state__83300)))
            (state__83300
             []
             (if
              (clojure.core/= (clojure.core/count input__82096) 3)
              (clojure.core/let
               [input__82096_nth_0__
                (clojure.core/nth input__82096 0)
                input__82096_nth_1__
                (clojure.core/nth input__82096 1)
                input__82096_nth_2__
                (clojure.core/nth input__82096 2)]
               (clojure.core/case
                input__82096_nth_0__
                (meander.dev.parse.zeta/parse-entries)
                (if
                 (clojure.core/map? input__82096_nth_1__)
                 (clojure.core/let
                  [VAL__82581
                   (.valAt input__82096_nth_1__ :meander.zeta/as)]
                  (clojure.core/let
                   [?pattern VAL__82581]
                   (clojure.core/let
                    [X__82583
                     ((clojure.core/fn
                       [m__6993__auto__]
                       (clojure.core/dissoc
                        m__6993__auto__
                        :meander.zeta/as))
                      input__82096_nth_1__)]
                    (clojure.core/let
                     [?rest X__82583]
                     (clojure.core/letfn
                      [(save__82584 [] (state__83218))
                       (f__83323
                        []
                        (clojure.core/let
                         [?env input__82096_nth_2__]
                         (try
                          [{:tag :as,
                            :pattern
                            (clojure.core/let
                             [CATA_RESULT__9229__auto__
                              (CATA__FN__82152 [?pattern ?env])]
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
                              (CATA__FN__82152 [?rest ?env])]
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
                       (clojure.core/= ?rest input__82096_nth_1__)
                       (save__82584)
                       (f__83323)))))))
                 (state__83218))
                (state__83218)))
              (state__83218)))]
           (state__83298))
          (state__83218)))
        (state__83218
         []
         (clojure.core/letfn
          [(def__82587
            [arg__82620 ?ns]
            (clojure.core/letfn
             [(state__83324
               []
               (clojure.core/let
                [x__82621 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__82621)
                 (clojure.core/let [?env arg__82620] [?env ?ns])
                 (state__83325))))
              (state__83325
               []
               (if
                (clojure.core/map? arg__82620)
                (clojure.core/let
                 [VAL__82622 (.valAt arg__82620 :aliases)]
                 (if
                  (clojure.core/map? VAL__82622)
                  (clojure.core/let
                   [X__82624 (clojure.core/set VAL__82622)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__82624))
                    (clojure.core/loop
                     [search_space__83326 (clojure.core/seq X__82624)]
                     (if
                      (clojure.core/seq search_space__83326)
                      (clojure.core/let
                       [elem__82625
                        (clojure.core/first search_space__83326)
                        result__83327
                        (clojure.core/let
                         [elem__82625_nth_0__
                          (clojure.core/nth elem__82625 0)
                          elem__82625_nth_1__
                          (clojure.core/nth elem__82625 1)]
                         (if
                          (clojure.core/symbol? elem__82625_nth_0__)
                          (clojure.core/let
                           [X__82627
                            (clojure.core/name elem__82625_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__82627)
                            (if
                             (clojure.core/symbol? elem__82625_nth_1__)
                             (clojure.core/let
                              [X__82629
                               (clojure.core/name elem__82625_nth_1__)]
                              (clojure.core/case
                               X__82629
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__82620]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__83327)
                        (recur (clojure.core/next search_space__83326))
                        result__83327))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__83324)))]
          (if
           (clojure.core/vector? input__82096)
           (if
            (clojure.core/= (clojure.core/count input__82096) 3)
            (clojure.core/let
             [input__82096_nth_0__
              (clojure.core/nth input__82096 0)
              input__82096_nth_1__
              (clojure.core/nth input__82096 1)
              input__82096_nth_2__
              (clojure.core/nth input__82096 2)]
             (clojure.core/case
              input__82096_nth_0__
              (meander.dev.parse.zeta/parse-entries)
              (if
               (clojure.core/map? input__82096_nth_1__)
               (clojure.core/let
                [X__82592 (clojure.core/set input__82096_nth_1__)]
                (if
                 (clojure.core/<= 1 (clojure.core/count X__82592))
                 (clojure.core/loop
                  [search_space__83329 (clojure.core/seq X__82592)]
                  (if
                   (clojure.core/seq search_space__83329)
                   (clojure.core/let
                    [elem__82593
                     (clojure.core/first search_space__83329)
                     result__83330
                     (clojure.core/let
                      [elem__82593_nth_0__
                       (clojure.core/nth elem__82593 0)
                       elem__82593_nth_1__
                       (clojure.core/nth elem__82593 1)]
                      (clojure.core/let
                       [*m__82121 elem__82593_nth_0__]
                       (if
                        (clojure.core/symbol? elem__82593_nth_0__)
                        (clojure.core/let
                         [X__82595
                          (clojure.core/namespace elem__82593_nth_0__)]
                         (clojure.core/let
                          [?ns X__82595]
                          (clojure.core/let
                           [X__82597
                            (clojure.core/name elem__82593_nth_0__)]
                           (if
                            (clojure.core/string? X__82597)
                            (if
                             (clojure.core/re-matches #"&.*" X__82597)
                             (clojure.core/let
                              [?pattern elem__82593_nth_1__]
                              (clojure.core/let
                               [X__82599
                                ((clojure.core/fn
                                  [m__6993__auto__]
                                  (clojure.core/dissoc
                                   m__6993__auto__
                                   *m__82121))
                                 input__82096_nth_1__)]
                               (clojure.core/let
                                [?rest X__82599]
                                (clojure.core/let
                                 [x__7926__auto__
                                  (def__82587
                                   input__82096_nth_2__
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
                                        (CATA__FN__82152
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
                                        (CATA__FN__82152
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
                     (meander.runtime.zeta/fail? result__83330)
                     (recur (clojure.core/next search_space__83329))
                     result__83330))
                   (state__83219)))
                 (state__83219)))
               (state__83219))
              (state__83219)))
            (state__83219))
           (state__83219))))
        (state__83219
         []
         (if
          (clojure.core/vector? input__82096)
          (clojure.core/letfn
           [(state__83332
             []
             (if
              (clojure.core/= (clojure.core/count input__82096) 3)
              (clojure.core/let
               [input__82096_nth_0__
                (clojure.core/nth input__82096 0)
                input__82096_nth_1__
                (clojure.core/nth input__82096 1)
                input__82096_nth_2__
                (clojure.core/nth input__82096 2)]
               (clojure.core/case
                input__82096_nth_0__
                (meander.dev.parse.zeta/parse-entries)
                (clojure.core/letfn
                 [(state__83334
                   []
                   (if
                    (clojure.core/map? input__82096_nth_1__)
                    (clojure.core/let
                     [X__82643 (clojure.core/set input__82096_nth_1__)]
                     (if
                      (clojure.core/<= 1 (clojure.core/count X__82643))
                      (clojure.core/loop
                       [search_space__83336
                        (clojure.core/seq X__82643)]
                       (if
                        (clojure.core/seq search_space__83336)
                        (clojure.core/let
                         [elem__82644
                          (clojure.core/first search_space__83336)
                          result__83337
                          (clojure.core/let
                           [elem__82644_nth_0__
                            (clojure.core/nth elem__82644 0)
                            elem__82644_nth_1__
                            (clojure.core/nth elem__82644 1)]
                           (clojure.core/let
                            [?key-pattern elem__82644_nth_0__]
                            (clojure.core/let
                             [?val-pattern elem__82644_nth_1__]
                             (clojure.core/let
                              [X__82646
                               ((clojure.core/fn
                                 [m__6993__auto__]
                                 (clojure.core/dissoc
                                  m__6993__auto__
                                  ?key-pattern))
                                input__82096_nth_1__)]
                              (clojure.core/let
                               [?rest X__82646]
                               (clojure.core/let
                                [?env input__82096_nth_2__]
                                (try
                                 [{:tag :entry,
                                   :key-pattern
                                   (clojure.core/let
                                    [CATA_RESULT__9229__auto__
                                     (CATA__FN__82152
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
                                     (CATA__FN__82152
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
                                     (CATA__FN__82152
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
                          (meander.runtime.zeta/fail? result__83337)
                          (recur
                           (clojure.core/next search_space__83336))
                          result__83337))
                        (state__83335)))
                      (state__83335)))
                    (state__83335)))
                  (state__83335
                   []
                   (if
                    (clojure.core/map? input__82096_nth_1__)
                    (clojure.core/let
                     [?env input__82096_nth_2__]
                     (try
                      [{:tag :some-map}]
                      (catch
                       java.lang.Exception
                       e__10169__auto__
                       (if
                        (meander.runtime.zeta/fail? e__10169__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__10169__auto__)))))
                    (state__83333)))]
                 (state__83334))
                (meander.dev.parse.zeta/parse-with-bindings)
                (clojure.core/letfn
                 [(state__83339
                   []
                   (if
                    (clojure.core/vector? input__82096_nth_1__)
                    (clojure.core/case
                     input__82096_nth_1__
                     ([])
                     (clojure.core/let
                      [?env input__82096_nth_2__]
                      (try
                       [[]]
                       (catch
                        java.lang.Exception
                        e__10169__auto__
                        (if
                         (meander.runtime.zeta/fail? e__10169__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__10169__auto__)))))
                     (state__83340))
                    (state__83340)))
                  (state__83340
                   []
                   (if
                    (clojure.core/vector? input__82096_nth_1__)
                    (if
                     (clojure.core/=
                      (clojure.core/count input__82096_nth_1__)
                      1)
                     (clojure.core/let
                      [?env input__82096_nth_2__]
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
                     (state__83341))
                    (state__83341)))
                  (state__83341
                   []
                   (if
                    (clojure.core/vector? input__82096_nth_1__)
                    (clojure.core/let
                     [input__82096_nth_1___l__
                      (clojure.core/subvec
                       input__82096_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__82096_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__82096_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__82096_nth_1___r__
                        (clojure.core/subvec input__82096_nth_1__ 2)]
                       (clojure.core/let
                        [input__82096_nth_1___l___nth_0__
                         (clojure.core/nth input__82096_nth_1___l__ 0)
                         input__82096_nth_1___l___nth_1__
                         (clojure.core/nth input__82096_nth_1___l__ 1)]
                        (if
                         (clojure.core/symbol?
                          input__82096_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__82660
                           (clojure.core/namespace
                            input__82096_nth_1___l___nth_0__)]
                          (clojure.core/let
                           [X__82662
                            (clojure.core/name
                             input__82096_nth_1___l___nth_0__)]
                           (if
                            (clojure.core/string? X__82662)
                            (if
                             (clojure.core/re-matches #"%.+" X__82662)
                             (clojure.core/let
                              [?symbol
                               input__82096_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?pattern
                                input__82096_nth_1___l___nth_1__]
                               (clojure.core/let
                                [?rest input__82096_nth_1___r__]
                                (clojure.core/let
                                 [?env input__82096_nth_2__]
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
                                         (CATA__FN__82152
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
                                       (CATA__FN__82152
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
                             (state__83342))
                            (state__83342))))
                         (state__83342))))
                      (state__83342)))
                    (state__83342)))
                  (state__83342
                   []
                   (if
                    (clojure.core/vector? input__82096_nth_1__)
                    (clojure.core/let
                     [input__82096_nth_1___l__
                      (clojure.core/subvec
                       input__82096_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__82096_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__82096_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__82096_nth_1___r__
                        (clojure.core/subvec input__82096_nth_1__ 2)]
                       (clojure.core/let
                        [input__82096_nth_1___l___nth_0__
                         (clojure.core/nth input__82096_nth_1___l__ 0)
                         input__82096_nth_1___l___nth_1__
                         (clojure.core/nth input__82096_nth_1___l__ 1)]
                        (clojure.core/let
                         [?x input__82096_nth_1___l___nth_0__]
                         (clojure.core/let
                          [?pattern input__82096_nth_1___l___nth_1__]
                          (clojure.core/let
                           [?rest input__82096_nth_1___r__]
                           (clojure.core/let
                            [?env input__82096_nth_2__]
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
                      (state__83333)))
                    (state__83333)))]
                 (state__83339))
                (state__83333)))
              (state__83333)))
            (state__83333
             []
             (if
              (clojure.core/= (clojure.core/count input__82096) 2)
              (clojure.core/let
               [input__82096_nth_0__
                (clojure.core/nth input__82096 0)
                input__82096_nth_1__
                (clojure.core/nth input__82096 1)]
               (if
                (clojure.core/vector? input__82096_nth_0__)
                (clojure.core/let
                 [?sequence input__82096_nth_0__]
                 (clojure.core/let
                  [?form input__82096_nth_0__]
                  (clojure.core/let
                   [?env input__82096_nth_1__]
                   (try
                    [{:tag :vector,
                      :next
                      (clojure.core/let
                       [CATA_RESULT__9229__auto__
                        (CATA__FN__82152
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
                (state__83220)))
              (state__83220)))]
           (state__83332))
          (state__83220)))
        (state__83220
         []
         (clojure.core/letfn
          [(def__82672
            [arg__82695 ?__82097]
            (clojure.core/letfn
             [(state__83343
               []
               (clojure.core/let
                [x__82696 "meander.zeta"]
                (if
                 (clojure.core/= ?__82097 x__82696)
                 [?__82097]
                 (state__83344))))
              (state__83344
               []
               (if
                (clojure.core/map? arg__82695)
                (clojure.core/let
                 [VAL__82697 (.valAt arg__82695 :aliases)]
                 (if
                  (clojure.core/map? VAL__82697)
                  (clojure.core/let
                   [X__82699 (clojure.core/set VAL__82697)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__82699))
                    (clojure.core/loop
                     [search_space__83345 (clojure.core/seq X__82699)]
                     (if
                      (clojure.core/seq search_space__83345)
                      (clojure.core/let
                       [elem__82700
                        (clojure.core/first search_space__83345)
                        result__83346
                        (clojure.core/let
                         [elem__82700_nth_0__
                          (clojure.core/nth elem__82700 0)
                          elem__82700_nth_1__
                          (clojure.core/nth elem__82700 1)]
                         (if
                          (clojure.core/symbol? elem__82700_nth_0__)
                          (clojure.core/let
                           [X__82702
                            (clojure.core/name elem__82700_nth_0__)]
                           (if
                            (clojure.core/= ?__82097 X__82702)
                            (if
                             (clojure.core/symbol? elem__82700_nth_1__)
                             (clojure.core/let
                              [X__82704
                               (clojure.core/name elem__82700_nth_1__)]
                              (clojure.core/case
                               X__82704
                               ("meander.zeta")
                               [?__82097]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__83346)
                        (recur (clojure.core/next search_space__83345))
                        result__83346))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__83343)))]
          (if
           (clojure.core/vector? input__82096)
           (if
            (clojure.core/= (clojure.core/count input__82096) 2)
            (clojure.core/let
             [input__82096_nth_0__
              (clojure.core/nth input__82096 0)
              input__82096_nth_1__
              (clojure.core/nth input__82096 1)]
             (if
              (clojure.core/seq? input__82096_nth_0__)
              (clojure.core/let
               [input__82096_nth_0___l__
                (clojure.core/take 1 input__82096_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__82096_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__82096_nth_0___r__
                  (clojure.core/drop 1 input__82096_nth_0__)]
                 (clojure.core/let
                  [input__82096_nth_0___l___nth_0__
                   (clojure.core/nth input__82096_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__82096_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__82682
                     (clojure.core/namespace
                      input__82096_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__82097 X__82682]
                     (clojure.core/let
                      [X__82684
                       (clojure.core/name
                        input__82096_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__82684
                       ("with")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__82672 input__82096_nth_1__ ?__82097)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__83221)
                         (clojure.core/let
                          [[?__82097] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__82096)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__82096)
                             2)
                            (clojure.core/let
                             [input__82096_nth_0__
                              (clojure.core/nth input__82096 0)
                              input__82096_nth_1__
                              (clojure.core/nth input__82096 1)]
                             (if
                              (clojure.core/seq? input__82096_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__82096_nth_0__)
                                3)
                               (clojure.core/let
                                [input__82096_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__82096_nth_0__
                                  1)
                                 input__82096_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__82096_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?bindings
                                  input__82096_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?body input__82096_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__82096_nth_0__]
                                   (clojure.core/let
                                    [?env input__82096_nth_1__]
                                    (try
                                     [{:tag :with,
                                       :bindings
                                       {:tag :with-bindings,
                                        :bindings
                                        (clojure.core/let
                                         [CATA_RESULT__9229__auto__
                                          (CATA__FN__82152
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
                                         (CATA__FN__82152
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
                               (state__83221))
                              (state__83221)))
                            (state__83221))
                           (state__83221)))))
                       (state__83221)))))
                   (state__83221))))
                (state__83221)))
              (state__83221)))
            (state__83221))
           (state__83221))))
        (state__83221
         []
         (clojure.core/letfn
          [(def__82706
            [arg__82729 ?__82098]
            (clojure.core/letfn
             [(state__83348
               []
               (clojure.core/let
                [x__82730 "meander.zeta"]
                (if
                 (clojure.core/= ?__82098 x__82730)
                 [?__82098]
                 (state__83349))))
              (state__83349
               []
               (if
                (clojure.core/map? arg__82729)
                (clojure.core/let
                 [VAL__82731 (.valAt arg__82729 :aliases)]
                 (if
                  (clojure.core/map? VAL__82731)
                  (clojure.core/let
                   [X__82733 (clojure.core/set VAL__82731)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__82733))
                    (clojure.core/loop
                     [search_space__83350 (clojure.core/seq X__82733)]
                     (if
                      (clojure.core/seq search_space__83350)
                      (clojure.core/let
                       [elem__82734
                        (clojure.core/first search_space__83350)
                        result__83351
                        (clojure.core/let
                         [elem__82734_nth_0__
                          (clojure.core/nth elem__82734 0)
                          elem__82734_nth_1__
                          (clojure.core/nth elem__82734 1)]
                         (if
                          (clojure.core/symbol? elem__82734_nth_0__)
                          (clojure.core/let
                           [X__82736
                            (clojure.core/name elem__82734_nth_0__)]
                           (if
                            (clojure.core/= ?__82098 X__82736)
                            (if
                             (clojure.core/symbol? elem__82734_nth_1__)
                             (clojure.core/let
                              [X__82738
                               (clojure.core/name elem__82734_nth_1__)]
                              (clojure.core/case
                               X__82738
                               ("meander.zeta")
                               [?__82098]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__83351)
                        (recur (clojure.core/next search_space__83350))
                        result__83351))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__83348)))]
          (if
           (clojure.core/vector? input__82096)
           (if
            (clojure.core/= (clojure.core/count input__82096) 2)
            (clojure.core/let
             [input__82096_nth_0__
              (clojure.core/nth input__82096 0)
              input__82096_nth_1__
              (clojure.core/nth input__82096 1)]
             (if
              (clojure.core/seq? input__82096_nth_0__)
              (clojure.core/let
               [input__82096_nth_0___l__
                (clojure.core/take 1 input__82096_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__82096_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__82096_nth_0___r__
                  (clojure.core/drop 1 input__82096_nth_0__)]
                 (clojure.core/let
                  [input__82096_nth_0___l___nth_0__
                   (clojure.core/nth input__82096_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__82096_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__82716
                     (clojure.core/namespace
                      input__82096_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__82098 X__82716]
                     (clojure.core/let
                      [X__82718
                       (clojure.core/name
                        input__82096_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__82718
                       ("apply")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__82706 input__82096_nth_1__ ?__82098)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__83222)
                         (clojure.core/let
                          [[?__82098] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__82096)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__82096)
                             2)
                            (clojure.core/let
                             [input__82096_nth_0__
                              (clojure.core/nth input__82096 0)
                              input__82096_nth_1__
                              (clojure.core/nth input__82096 1)]
                             (if
                              (clojure.core/seq? input__82096_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__82096_nth_0__)
                                3)
                               (clojure.core/let
                                [input__82096_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__82096_nth_0__
                                  1)
                                 input__82096_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__82096_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?fn input__82096_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?pattern
                                   input__82096_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__82096_nth_0__]
                                   (clojure.core/let
                                    [?env input__82096_nth_1__]
                                    (try
                                     [{:tag :apply,
                                       :fn ?fn,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__82152
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
                               (state__83222))
                              (state__83222)))
                            (state__83222))
                           (state__83222)))))
                       (state__83222)))))
                   (state__83222))))
                (state__83222)))
              (state__83222)))
            (state__83222))
           (state__83222))))
        (state__83222
         []
         (clojure.core/letfn
          [(def__82740
            [arg__82763 ?__82099]
            (clojure.core/letfn
             [(state__83353
               []
               (clojure.core/let
                [x__82764 "meander.zeta"]
                (if
                 (clojure.core/= ?__82099 x__82764)
                 [?__82099]
                 (state__83354))))
              (state__83354
               []
               (if
                (clojure.core/map? arg__82763)
                (clojure.core/let
                 [VAL__82765 (.valAt arg__82763 :aliases)]
                 (if
                  (clojure.core/map? VAL__82765)
                  (clojure.core/let
                   [X__82767 (clojure.core/set VAL__82765)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__82767))
                    (clojure.core/loop
                     [search_space__83355 (clojure.core/seq X__82767)]
                     (if
                      (clojure.core/seq search_space__83355)
                      (clojure.core/let
                       [elem__82768
                        (clojure.core/first search_space__83355)
                        result__83356
                        (clojure.core/let
                         [elem__82768_nth_0__
                          (clojure.core/nth elem__82768 0)
                          elem__82768_nth_1__
                          (clojure.core/nth elem__82768 1)]
                         (if
                          (clojure.core/symbol? elem__82768_nth_0__)
                          (clojure.core/let
                           [X__82770
                            (clojure.core/name elem__82768_nth_0__)]
                           (if
                            (clojure.core/= ?__82099 X__82770)
                            (if
                             (clojure.core/symbol? elem__82768_nth_1__)
                             (clojure.core/let
                              [X__82772
                               (clojure.core/name elem__82768_nth_1__)]
                              (clojure.core/case
                               X__82772
                               ("meander.zeta")
                               [?__82099]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__83356)
                        (recur (clojure.core/next search_space__83355))
                        result__83356))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__83353)))]
          (if
           (clojure.core/vector? input__82096)
           (if
            (clojure.core/= (clojure.core/count input__82096) 2)
            (clojure.core/let
             [input__82096_nth_0__
              (clojure.core/nth input__82096 0)
              input__82096_nth_1__
              (clojure.core/nth input__82096 1)]
             (if
              (clojure.core/seq? input__82096_nth_0__)
              (clojure.core/let
               [input__82096_nth_0___l__
                (clojure.core/take 1 input__82096_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__82096_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__82096_nth_0___r__
                  (clojure.core/drop 1 input__82096_nth_0__)]
                 (clojure.core/let
                  [input__82096_nth_0___l___nth_0__
                   (clojure.core/nth input__82096_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__82096_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__82750
                     (clojure.core/namespace
                      input__82096_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__82099 X__82750]
                     (clojure.core/let
                      [X__82752
                       (clojure.core/name
                        input__82096_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__82752
                       ("and")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__82740 input__82096_nth_1__ ?__82099)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__83223)
                         (clojure.core/let
                          [[?__82099] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__82096)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__82096)
                             2)
                            (clojure.core/let
                             [input__82096_nth_0__
                              (clojure.core/nth input__82096 0)
                              input__82096_nth_1__
                              (clojure.core/nth input__82096 1)]
                             (if
                              (clojure.core/seq? input__82096_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__82096_nth_0__)
                                3)
                               (clojure.core/let
                                [input__82096_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__82096_nth_0__
                                  1)
                                 input__82096_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__82096_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?left input__82096_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?right input__82096_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__82096_nth_0__]
                                   (clojure.core/let
                                    [?env input__82096_nth_1__]
                                    (try
                                     [{:tag :and,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__82152
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
                                         (CATA__FN__82152
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
                               (state__83223))
                              (state__83223)))
                            (state__83223))
                           (state__83223)))))
                       (state__83223)))))
                   (state__83223))))
                (state__83223)))
              (state__83223)))
            (state__83223))
           (state__83223))))
        (state__83223
         []
         (clojure.core/letfn
          [(def__82774
            [arg__82797 ?__82100]
            (clojure.core/letfn
             [(state__83358
               []
               (clojure.core/let
                [x__82798 "meander.zeta"]
                (if
                 (clojure.core/= ?__82100 x__82798)
                 [?__82100]
                 (state__83359))))
              (state__83359
               []
               (if
                (clojure.core/map? arg__82797)
                (clojure.core/let
                 [VAL__82799 (.valAt arg__82797 :aliases)]
                 (if
                  (clojure.core/map? VAL__82799)
                  (clojure.core/let
                   [X__82801 (clojure.core/set VAL__82799)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__82801))
                    (clojure.core/loop
                     [search_space__83360 (clojure.core/seq X__82801)]
                     (if
                      (clojure.core/seq search_space__83360)
                      (clojure.core/let
                       [elem__82802
                        (clojure.core/first search_space__83360)
                        result__83361
                        (clojure.core/let
                         [elem__82802_nth_0__
                          (clojure.core/nth elem__82802 0)
                          elem__82802_nth_1__
                          (clojure.core/nth elem__82802 1)]
                         (if
                          (clojure.core/symbol? elem__82802_nth_0__)
                          (clojure.core/let
                           [X__82804
                            (clojure.core/name elem__82802_nth_0__)]
                           (if
                            (clojure.core/= ?__82100 X__82804)
                            (if
                             (clojure.core/symbol? elem__82802_nth_1__)
                             (clojure.core/let
                              [X__82806
                               (clojure.core/name elem__82802_nth_1__)]
                              (clojure.core/case
                               X__82806
                               ("meander.zeta")
                               [?__82100]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__83361)
                        (recur (clojure.core/next search_space__83360))
                        result__83361))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__83358)))]
          (if
           (clojure.core/vector? input__82096)
           (if
            (clojure.core/= (clojure.core/count input__82096) 2)
            (clojure.core/let
             [input__82096_nth_0__
              (clojure.core/nth input__82096 0)
              input__82096_nth_1__
              (clojure.core/nth input__82096 1)]
             (if
              (clojure.core/seq? input__82096_nth_0__)
              (clojure.core/let
               [input__82096_nth_0___l__
                (clojure.core/take 1 input__82096_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__82096_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__82096_nth_0___r__
                  (clojure.core/drop 1 input__82096_nth_0__)]
                 (clojure.core/let
                  [input__82096_nth_0___l___nth_0__
                   (clojure.core/nth input__82096_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__82096_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__82784
                     (clojure.core/namespace
                      input__82096_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__82100 X__82784]
                     (clojure.core/let
                      [X__82786
                       (clojure.core/name
                        input__82096_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__82786
                       ("cata")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__82774 input__82096_nth_1__ ?__82100)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__83224)
                         (clojure.core/let
                          [[?__82100] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__82096)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__82096)
                             2)
                            (clojure.core/let
                             [input__82096_nth_0__
                              (clojure.core/nth input__82096 0)
                              input__82096_nth_1__
                              (clojure.core/nth input__82096 1)]
                             (if
                              (clojure.core/seq? input__82096_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__82096_nth_0__)
                                2)
                               (clojure.core/let
                                [input__82096_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__82096_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__82096_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__82096_nth_0__]
                                  (clojure.core/let
                                   [?env input__82096_nth_1__]
                                   (try
                                    [{:tag :cata,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__9229__auto__
                                        (CATA__FN__82152
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
                               (state__83224))
                              (state__83224)))
                            (state__83224))
                           (state__83224)))))
                       (state__83224)))))
                   (state__83224))))
                (state__83224)))
              (state__83224)))
            (state__83224))
           (state__83224))))
        (state__83224
         []
         (clojure.core/letfn
          [(def__82808
            [arg__82831 ?__82101]
            (clojure.core/letfn
             [(state__83363
               []
               (clojure.core/let
                [x__82832 "meander.zeta"]
                (if
                 (clojure.core/= ?__82101 x__82832)
                 [?__82101]
                 (state__83364))))
              (state__83364
               []
               (if
                (clojure.core/map? arg__82831)
                (clojure.core/let
                 [VAL__82833 (.valAt arg__82831 :aliases)]
                 (if
                  (clojure.core/map? VAL__82833)
                  (clojure.core/let
                   [X__82835 (clojure.core/set VAL__82833)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__82835))
                    (clojure.core/loop
                     [search_space__83365 (clojure.core/seq X__82835)]
                     (if
                      (clojure.core/seq search_space__83365)
                      (clojure.core/let
                       [elem__82836
                        (clojure.core/first search_space__83365)
                        result__83366
                        (clojure.core/let
                         [elem__82836_nth_0__
                          (clojure.core/nth elem__82836 0)
                          elem__82836_nth_1__
                          (clojure.core/nth elem__82836 1)]
                         (if
                          (clojure.core/symbol? elem__82836_nth_0__)
                          (clojure.core/let
                           [X__82838
                            (clojure.core/name elem__82836_nth_0__)]
                           (if
                            (clojure.core/= ?__82101 X__82838)
                            (if
                             (clojure.core/symbol? elem__82836_nth_1__)
                             (clojure.core/let
                              [X__82840
                               (clojure.core/name elem__82836_nth_1__)]
                              (clojure.core/case
                               X__82840
                               ("meander.zeta")
                               [?__82101]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__83366)
                        (recur (clojure.core/next search_space__83365))
                        result__83366))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__83363)))]
          (if
           (clojure.core/vector? input__82096)
           (if
            (clojure.core/= (clojure.core/count input__82096) 2)
            (clojure.core/let
             [input__82096_nth_0__
              (clojure.core/nth input__82096 0)
              input__82096_nth_1__
              (clojure.core/nth input__82096 1)]
             (if
              (clojure.core/seq? input__82096_nth_0__)
              (clojure.core/let
               [input__82096_nth_0___l__
                (clojure.core/take 1 input__82096_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__82096_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__82096_nth_0___r__
                  (clojure.core/drop 1 input__82096_nth_0__)]
                 (clojure.core/let
                  [input__82096_nth_0___l___nth_0__
                   (clojure.core/nth input__82096_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__82096_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__82818
                     (clojure.core/namespace
                      input__82096_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__82101 X__82818]
                     (clojure.core/let
                      [X__82820
                       (clojure.core/name
                        input__82096_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__82820
                       ("fold")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__82808 input__82096_nth_1__ ?__82101)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__83225)
                         (clojure.core/let
                          [[?__82101] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__82096)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__82096)
                             2)
                            (clojure.core/let
                             [input__82096_nth_0__
                              (clojure.core/nth input__82096 0)
                              input__82096_nth_1__
                              (clojure.core/nth input__82096 1)]
                             (if
                              (clojure.core/seq? input__82096_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 4)
                                 input__82096_nth_0__)
                                4)
                               (clojure.core/let
                                [input__82096_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__82096_nth_0__
                                  1)
                                 input__82096_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__82096_nth_0__
                                  2)
                                 input__82096_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__82096_nth_0__
                                  3)]
                                (clojure.core/let
                                 [?mutable-variable
                                  input__82096_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?initial-value
                                   input__82096_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?fold-function
                                    input__82096_nth_0___nth_3__]
                                   (clojure.core/let
                                    [?form input__82096_nth_0__]
                                    (clojure.core/let
                                     [?env input__82096_nth_1__]
                                     (try
                                      [(clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__82152
                                          ['meander.dev.parse.zeta/make-fold
                                           (clojure.core/let
                                            [CATA_RESULT__9229__auto__
                                             (CATA__FN__82152
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
                                             (CATA__FN__82152
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
                               (state__83225))
                              (state__83225)))
                            (state__83225))
                           (state__83225)))))
                       (state__83225)))))
                   (state__83225))))
                (state__83225)))
              (state__83225)))
            (state__83225))
           (state__83225))))
        (state__83225
         []
         (if
          (clojure.core/vector? input__82096)
          (if
           (clojure.core/= (clojure.core/count input__82096) 5)
           (clojure.core/let
            [input__82096_nth_0__
             (clojure.core/nth input__82096 0)
             input__82096_nth_1__
             (clojure.core/nth input__82096 1)
             input__82096_nth_2__
             (clojure.core/nth input__82096 2)
             input__82096_nth_3__
             (clojure.core/nth input__82096 3)
             input__82096_nth_4__
             (clojure.core/nth input__82096 4)]
            (clojure.core/case
             input__82096_nth_0__
             (meander.dev.parse.zeta/make-fold)
             (if
              (clojure.core/map? input__82096_nth_1__)
              (clojure.core/let
               [VAL__82843 (.valAt input__82096_nth_1__ :tag)]
               (clojure.core/case
                VAL__82843
                (:mutable-variable)
                (clojure.core/let
                 [?variable-ast input__82096_nth_1__]
                 (clojure.core/let
                  [?initial-value-ast input__82096_nth_2__]
                  (clojure.core/let
                   [?fold-function input__82096_nth_3__]
                   (clojure.core/let
                    [?form input__82096_nth_4__]
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
                (state__83226)))
              (state__83226))
             (state__83226)))
           (state__83226))
          (state__83226)))
        (state__83226
         []
         (clojure.core/letfn
          [(def__82845
            [arg__82868 ?__82102]
            (clojure.core/letfn
             [(state__83368
               []
               (clojure.core/let
                [x__82869 "meander.zeta"]
                (if
                 (clojure.core/= ?__82102 x__82869)
                 [?__82102]
                 (state__83369))))
              (state__83369
               []
               (if
                (clojure.core/map? arg__82868)
                (clojure.core/let
                 [VAL__82870 (.valAt arg__82868 :aliases)]
                 (if
                  (clojure.core/map? VAL__82870)
                  (clojure.core/let
                   [X__82872 (clojure.core/set VAL__82870)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__82872))
                    (clojure.core/loop
                     [search_space__83370 (clojure.core/seq X__82872)]
                     (if
                      (clojure.core/seq search_space__83370)
                      (clojure.core/let
                       [elem__82873
                        (clojure.core/first search_space__83370)
                        result__83371
                        (clojure.core/let
                         [elem__82873_nth_0__
                          (clojure.core/nth elem__82873 0)
                          elem__82873_nth_1__
                          (clojure.core/nth elem__82873 1)]
                         (if
                          (clojure.core/symbol? elem__82873_nth_0__)
                          (clojure.core/let
                           [X__82875
                            (clojure.core/name elem__82873_nth_0__)]
                           (if
                            (clojure.core/= ?__82102 X__82875)
                            (if
                             (clojure.core/symbol? elem__82873_nth_1__)
                             (clojure.core/let
                              [X__82877
                               (clojure.core/name elem__82873_nth_1__)]
                              (clojure.core/case
                               X__82877
                               ("meander.zeta")
                               [?__82102]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__83371)
                        (recur (clojure.core/next search_space__83370))
                        result__83371))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__83368)))]
          (if
           (clojure.core/vector? input__82096)
           (if
            (clojure.core/= (clojure.core/count input__82096) 2)
            (clojure.core/let
             [input__82096_nth_0__
              (clojure.core/nth input__82096 0)
              input__82096_nth_1__
              (clojure.core/nth input__82096 1)]
             (if
              (clojure.core/seq? input__82096_nth_0__)
              (clojure.core/let
               [input__82096_nth_0___l__
                (clojure.core/take 1 input__82096_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__82096_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__82096_nth_0___r__
                  (clojure.core/drop 1 input__82096_nth_0__)]
                 (clojure.core/let
                  [input__82096_nth_0___l___nth_0__
                   (clojure.core/nth input__82096_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__82096_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__82855
                     (clojure.core/namespace
                      input__82096_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__82102 X__82855]
                     (clojure.core/let
                      [X__82857
                       (clojure.core/name
                        input__82096_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__82857
                       ("let")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__82845 input__82096_nth_1__ ?__82102)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__83227)
                         (clojure.core/let
                          [[?__82102] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__82096)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__82096)
                             2)
                            (clojure.core/let
                             [input__82096_nth_0__
                              (clojure.core/nth input__82096 0)
                              input__82096_nth_1__
                              (clojure.core/nth input__82096 1)]
                             (if
                              (clojure.core/seq? input__82096_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__82096_nth_0__)
                                3)
                               (clojure.core/let
                                [input__82096_nth_0___nth_0__
                                 (clojure.core/nth
                                  input__82096_nth_0__
                                  0)
                                 input__82096_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__82096_nth_0__
                                  1)
                                 input__82096_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__82096_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?pattern
                                  input__82096_nth_0___nth_0__]
                                 (clojure.core/let
                                  [?expression
                                   input__82096_nth_0___nth_1__]
                                  (clojure.core/let
                                   [?next input__82096_nth_0___nth_2__]
                                   (clojure.core/let
                                    [?form input__82096_nth_0__]
                                    (clojure.core/let
                                     [?env input__82096_nth_1__]
                                     (try
                                      [{:tag :let,
                                        :pattern
                                        (clojure.core/let
                                         [CATA_RESULT__9229__auto__
                                          (CATA__FN__82152
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
                                          (CATA__FN__82152
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
                               (state__83227))
                              (state__83227)))
                            (state__83227))
                           (state__83227)))))
                       (state__83227)))))
                   (state__83227))))
                (state__83227)))
              (state__83227)))
            (state__83227))
           (state__83227))))
        (state__83227
         []
         (clojure.core/letfn
          [(def__82879
            [arg__82902 ?__82103]
            (clojure.core/letfn
             [(state__83373
               []
               (clojure.core/let
                [x__82903 "meander.zeta"]
                (if
                 (clojure.core/= ?__82103 x__82903)
                 [?__82103]
                 (state__83374))))
              (state__83374
               []
               (if
                (clojure.core/map? arg__82902)
                (clojure.core/let
                 [VAL__82904 (.valAt arg__82902 :aliases)]
                 (if
                  (clojure.core/map? VAL__82904)
                  (clojure.core/let
                   [X__82906 (clojure.core/set VAL__82904)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__82906))
                    (clojure.core/loop
                     [search_space__83375 (clojure.core/seq X__82906)]
                     (if
                      (clojure.core/seq search_space__83375)
                      (clojure.core/let
                       [elem__82907
                        (clojure.core/first search_space__83375)
                        result__83376
                        (clojure.core/let
                         [elem__82907_nth_0__
                          (clojure.core/nth elem__82907 0)
                          elem__82907_nth_1__
                          (clojure.core/nth elem__82907 1)]
                         (if
                          (clojure.core/symbol? elem__82907_nth_0__)
                          (clojure.core/let
                           [X__82909
                            (clojure.core/name elem__82907_nth_0__)]
                           (if
                            (clojure.core/= ?__82103 X__82909)
                            (if
                             (clojure.core/symbol? elem__82907_nth_1__)
                             (clojure.core/let
                              [X__82911
                               (clojure.core/name elem__82907_nth_1__)]
                              (clojure.core/case
                               X__82911
                               ("meander.zeta")
                               [?__82103]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__83376)
                        (recur (clojure.core/next search_space__83375))
                        result__83376))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__83373)))]
          (if
           (clojure.core/vector? input__82096)
           (if
            (clojure.core/= (clojure.core/count input__82096) 2)
            (clojure.core/let
             [input__82096_nth_0__
              (clojure.core/nth input__82096 0)
              input__82096_nth_1__
              (clojure.core/nth input__82096 1)]
             (if
              (clojure.core/seq? input__82096_nth_0__)
              (clojure.core/let
               [input__82096_nth_0___l__
                (clojure.core/take 1 input__82096_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__82096_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__82096_nth_0___r__
                  (clojure.core/drop 1 input__82096_nth_0__)]
                 (clojure.core/let
                  [input__82096_nth_0___l___nth_0__
                   (clojure.core/nth input__82096_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__82096_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__82889
                     (clojure.core/namespace
                      input__82096_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__82103 X__82889]
                     (clojure.core/let
                      [X__82891
                       (clojure.core/name
                        input__82096_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__82891
                       ("not")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__82879 input__82096_nth_1__ ?__82103)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__83228)
                         (clojure.core/let
                          [[?__82103] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__82096)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__82096)
                             2)
                            (clojure.core/let
                             [input__82096_nth_0__
                              (clojure.core/nth input__82096 0)
                              input__82096_nth_1__
                              (clojure.core/nth input__82096 1)]
                             (if
                              (clojure.core/seq? input__82096_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__82096_nth_0__)
                                2)
                               (clojure.core/let
                                [input__82096_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__82096_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__82096_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__82096_nth_0__]
                                  (clojure.core/let
                                   [?env input__82096_nth_1__]
                                   (try
                                    [{:tag :not,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__9229__auto__
                                        (CATA__FN__82152
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
                               (state__83228))
                              (state__83228)))
                            (state__83228))
                           (state__83228)))))
                       (state__83228)))))
                   (state__83228))))
                (state__83228)))
              (state__83228)))
            (state__83228))
           (state__83228))))
        (state__83228
         []
         (clojure.core/letfn
          [(def__82913
            [arg__82936 ?__82104]
            (clojure.core/letfn
             [(state__83378
               []
               (clojure.core/let
                [x__82937 "meander.zeta"]
                (if
                 (clojure.core/= ?__82104 x__82937)
                 [?__82104]
                 (state__83379))))
              (state__83379
               []
               (if
                (clojure.core/map? arg__82936)
                (clojure.core/let
                 [VAL__82938 (.valAt arg__82936 :aliases)]
                 (if
                  (clojure.core/map? VAL__82938)
                  (clojure.core/let
                   [X__82940 (clojure.core/set VAL__82938)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__82940))
                    (clojure.core/loop
                     [search_space__83380 (clojure.core/seq X__82940)]
                     (if
                      (clojure.core/seq search_space__83380)
                      (clojure.core/let
                       [elem__82941
                        (clojure.core/first search_space__83380)
                        result__83381
                        (clojure.core/let
                         [elem__82941_nth_0__
                          (clojure.core/nth elem__82941 0)
                          elem__82941_nth_1__
                          (clojure.core/nth elem__82941 1)]
                         (if
                          (clojure.core/symbol? elem__82941_nth_0__)
                          (clojure.core/let
                           [X__82943
                            (clojure.core/name elem__82941_nth_0__)]
                           (if
                            (clojure.core/= ?__82104 X__82943)
                            (if
                             (clojure.core/symbol? elem__82941_nth_1__)
                             (clojure.core/let
                              [X__82945
                               (clojure.core/name elem__82941_nth_1__)]
                              (clojure.core/case
                               X__82945
                               ("meander.zeta")
                               [?__82104]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__83381)
                        (recur (clojure.core/next search_space__83380))
                        result__83381))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__83378)))]
          (if
           (clojure.core/vector? input__82096)
           (if
            (clojure.core/= (clojure.core/count input__82096) 2)
            (clojure.core/let
             [input__82096_nth_0__
              (clojure.core/nth input__82096 0)
              input__82096_nth_1__
              (clojure.core/nth input__82096 1)]
             (if
              (clojure.core/seq? input__82096_nth_0__)
              (clojure.core/let
               [input__82096_nth_0___l__
                (clojure.core/take 1 input__82096_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__82096_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__82096_nth_0___r__
                  (clojure.core/drop 1 input__82096_nth_0__)]
                 (clojure.core/let
                  [input__82096_nth_0___l___nth_0__
                   (clojure.core/nth input__82096_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__82096_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__82923
                     (clojure.core/namespace
                      input__82096_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__82104 X__82923]
                     (clojure.core/let
                      [X__82925
                       (clojure.core/name
                        input__82096_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__82925
                       ("or")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__82913 input__82096_nth_1__ ?__82104)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__83229)
                         (clojure.core/let
                          [[?__82104] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__82096)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__82096)
                             2)
                            (clojure.core/let
                             [input__82096_nth_0__
                              (clojure.core/nth input__82096 0)
                              input__82096_nth_1__
                              (clojure.core/nth input__82096 1)]
                             (if
                              (clojure.core/seq? input__82096_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__82096_nth_0__)
                                3)
                               (clojure.core/let
                                [input__82096_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__82096_nth_0__
                                  1)
                                 input__82096_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__82096_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?left input__82096_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?right input__82096_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__82096_nth_0__]
                                   (clojure.core/let
                                    [?env input__82096_nth_1__]
                                    (try
                                     [{:tag :or,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__82152
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
                                         (CATA__FN__82152
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
                               (state__83229))
                              (state__83229)))
                            (state__83229))
                           (state__83229)))))
                       (state__83229)))))
                   (state__83229))))
                (state__83229)))
              (state__83229)))
            (state__83229))
           (state__83229))))
        (state__83229
         []
         (clojure.core/letfn
          [(def__82947
            [arg__82970 ?__82105]
            (clojure.core/letfn
             [(state__83383
               []
               (clojure.core/let
                [x__82971 "meander.zeta"]
                (if
                 (clojure.core/= ?__82105 x__82971)
                 [?__82105]
                 (state__83384))))
              (state__83384
               []
               (if
                (clojure.core/map? arg__82970)
                (clojure.core/let
                 [VAL__82972 (.valAt arg__82970 :aliases)]
                 (if
                  (clojure.core/map? VAL__82972)
                  (clojure.core/let
                   [X__82974 (clojure.core/set VAL__82972)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__82974))
                    (clojure.core/loop
                     [search_space__83385 (clojure.core/seq X__82974)]
                     (if
                      (clojure.core/seq search_space__83385)
                      (clojure.core/let
                       [elem__82975
                        (clojure.core/first search_space__83385)
                        result__83386
                        (clojure.core/let
                         [elem__82975_nth_0__
                          (clojure.core/nth elem__82975 0)
                          elem__82975_nth_1__
                          (clojure.core/nth elem__82975 1)]
                         (if
                          (clojure.core/symbol? elem__82975_nth_0__)
                          (clojure.core/let
                           [X__82977
                            (clojure.core/name elem__82975_nth_0__)]
                           (if
                            (clojure.core/= ?__82105 X__82977)
                            (if
                             (clojure.core/symbol? elem__82975_nth_1__)
                             (clojure.core/let
                              [X__82979
                               (clojure.core/name elem__82975_nth_1__)]
                              (clojure.core/case
                               X__82979
                               ("meander.zeta")
                               [?__82105]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__83386)
                        (recur (clojure.core/next search_space__83385))
                        result__83386))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__83383)))]
          (if
           (clojure.core/vector? input__82096)
           (if
            (clojure.core/= (clojure.core/count input__82096) 2)
            (clojure.core/let
             [input__82096_nth_0__
              (clojure.core/nth input__82096 0)
              input__82096_nth_1__
              (clojure.core/nth input__82096 1)]
             (if
              (clojure.core/seq? input__82096_nth_0__)
              (clojure.core/let
               [input__82096_nth_0___l__
                (clojure.core/take 1 input__82096_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__82096_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__82096_nth_0___r__
                  (clojure.core/drop 1 input__82096_nth_0__)]
                 (clojure.core/let
                  [input__82096_nth_0___l___nth_0__
                   (clojure.core/nth input__82096_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__82096_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__82957
                     (clojure.core/namespace
                      input__82096_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__82105 X__82957]
                     (clojure.core/let
                      [X__82959
                       (clojure.core/name
                        input__82096_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__82959
                       ("re")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__82947 input__82096_nth_1__ ?__82105)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__83230)
                         (clojure.core/let
                          [[?__82105] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__82096)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__82096)
                             2)
                            (clojure.core/let
                             [input__82096_nth_0__
                              (clojure.core/nth input__82096 0)
                              input__82096_nth_1__
                              (clojure.core/nth input__82096 1)]
                             (if
                              (clojure.core/seq? input__82096_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__82096_nth_0__)
                                2)
                               (clojure.core/let
                                [input__82096_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__82096_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?regex input__82096_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__82096_nth_0__]
                                  (clojure.core/let
                                   [?env input__82096_nth_1__]
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
                               (state__83230))
                              (state__83230)))
                            (state__83230))
                           (state__83230)))))
                       (state__83230)))))
                   (state__83230))))
                (state__83230)))
              (state__83230)))
            (state__83230))
           (state__83230))))
        (state__83230
         []
         (clojure.core/letfn
          [(def__82981
            [arg__83004 ?__82106]
            (clojure.core/letfn
             [(state__83388
               []
               (clojure.core/let
                [x__83005 "meander.zeta"]
                (if
                 (clojure.core/= ?__82106 x__83005)
                 [?__82106]
                 (state__83389))))
              (state__83389
               []
               (if
                (clojure.core/map? arg__83004)
                (clojure.core/let
                 [VAL__83006 (.valAt arg__83004 :aliases)]
                 (if
                  (clojure.core/map? VAL__83006)
                  (clojure.core/let
                   [X__83008 (clojure.core/set VAL__83006)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__83008))
                    (clojure.core/loop
                     [search_space__83390 (clojure.core/seq X__83008)]
                     (if
                      (clojure.core/seq search_space__83390)
                      (clojure.core/let
                       [elem__83009
                        (clojure.core/first search_space__83390)
                        result__83391
                        (clojure.core/let
                         [elem__83009_nth_0__
                          (clojure.core/nth elem__83009 0)
                          elem__83009_nth_1__
                          (clojure.core/nth elem__83009 1)]
                         (if
                          (clojure.core/symbol? elem__83009_nth_0__)
                          (clojure.core/let
                           [X__83011
                            (clojure.core/name elem__83009_nth_0__)]
                           (if
                            (clojure.core/= ?__82106 X__83011)
                            (if
                             (clojure.core/symbol? elem__83009_nth_1__)
                             (clojure.core/let
                              [X__83013
                               (clojure.core/name elem__83009_nth_1__)]
                              (clojure.core/case
                               X__83013
                               ("meander.zeta")
                               [?__82106]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__83391)
                        (recur (clojure.core/next search_space__83390))
                        result__83391))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__83388)))]
          (if
           (clojure.core/vector? input__82096)
           (if
            (clojure.core/= (clojure.core/count input__82096) 2)
            (clojure.core/let
             [input__82096_nth_0__
              (clojure.core/nth input__82096 0)
              input__82096_nth_1__
              (clojure.core/nth input__82096 1)]
             (if
              (clojure.core/seq? input__82096_nth_0__)
              (clojure.core/let
               [input__82096_nth_0___l__
                (clojure.core/take 1 input__82096_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__82096_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__82096_nth_0___r__
                  (clojure.core/drop 1 input__82096_nth_0__)]
                 (clojure.core/let
                  [input__82096_nth_0___l___nth_0__
                   (clojure.core/nth input__82096_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__82096_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__82991
                     (clojure.core/namespace
                      input__82096_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__82106 X__82991]
                     (clojure.core/let
                      [X__82993
                       (clojure.core/name
                        input__82096_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__82993
                       ("re")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__82981 input__82096_nth_1__ ?__82106)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__83231)
                         (clojure.core/let
                          [[?__82106] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__82096)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__82096)
                             2)
                            (clojure.core/let
                             [input__82096_nth_0__
                              (clojure.core/nth input__82096 0)
                              input__82096_nth_1__
                              (clojure.core/nth input__82096 1)]
                             (if
                              (clojure.core/seq? input__82096_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__82096_nth_0__)
                                3)
                               (clojure.core/let
                                [input__82096_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__82096_nth_0__
                                  1)
                                 input__82096_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__82096_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?regex input__82096_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?capture
                                   input__82096_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__82096_nth_0__]
                                   (clojure.core/let
                                    [?env input__82096_nth_1__]
                                    (try
                                     [{:tag :regex,
                                       :regex ?regex,
                                       :capture
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__82152
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
                               (state__83231))
                              (state__83231)))
                            (state__83231))
                           (state__83231)))))
                       (state__83231)))))
                   (state__83231))))
                (state__83231)))
              (state__83231)))
            (state__83231))
           (state__83231))))
        (state__83231
         []
         (clojure.core/letfn
          [(def__83015
            [arg__83038 ?__82107]
            (clojure.core/letfn
             [(state__83393
               []
               (clojure.core/let
                [x__83039 "meander.zeta"]
                (if
                 (clojure.core/= ?__82107 x__83039)
                 [?__82107]
                 (state__83394))))
              (state__83394
               []
               (if
                (clojure.core/map? arg__83038)
                (clojure.core/let
                 [VAL__83040 (.valAt arg__83038 :aliases)]
                 (if
                  (clojure.core/map? VAL__83040)
                  (clojure.core/let
                   [X__83042 (clojure.core/set VAL__83040)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__83042))
                    (clojure.core/loop
                     [search_space__83395 (clojure.core/seq X__83042)]
                     (if
                      (clojure.core/seq search_space__83395)
                      (clojure.core/let
                       [elem__83043
                        (clojure.core/first search_space__83395)
                        result__83396
                        (clojure.core/let
                         [elem__83043_nth_0__
                          (clojure.core/nth elem__83043 0)
                          elem__83043_nth_1__
                          (clojure.core/nth elem__83043 1)]
                         (if
                          (clojure.core/symbol? elem__83043_nth_0__)
                          (clojure.core/let
                           [X__83045
                            (clojure.core/name elem__83043_nth_0__)]
                           (if
                            (clojure.core/= ?__82107 X__83045)
                            (if
                             (clojure.core/symbol? elem__83043_nth_1__)
                             (clojure.core/let
                              [X__83047
                               (clojure.core/name elem__83043_nth_1__)]
                              (clojure.core/case
                               X__83047
                               ("meander.zeta")
                               [?__82107]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__83396)
                        (recur (clojure.core/next search_space__83395))
                        result__83396))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__83393)))]
          (if
           (clojure.core/vector? input__82096)
           (if
            (clojure.core/= (clojure.core/count input__82096) 2)
            (clojure.core/let
             [input__82096_nth_0__
              (clojure.core/nth input__82096 0)
              input__82096_nth_1__
              (clojure.core/nth input__82096 1)]
             (if
              (clojure.core/seq? input__82096_nth_0__)
              (clojure.core/let
               [input__82096_nth_0___l__
                (clojure.core/take 1 input__82096_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__82096_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__82096_nth_0___r__
                  (clojure.core/drop 1 input__82096_nth_0__)]
                 (clojure.core/let
                  [input__82096_nth_0___l___nth_0__
                   (clojure.core/nth input__82096_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__82096_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__83025
                     (clojure.core/namespace
                      input__82096_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__82107 X__83025]
                     (clojure.core/let
                      [X__83027
                       (clojure.core/name
                        input__82096_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__83027
                       ("string")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__83015 input__82096_nth_1__ ?__82107)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__83232)
                         (clojure.core/let
                          [[?__82107] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__82096)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__82096)
                             2)
                            (clojure.core/let
                             [input__82096_nth_0__
                              (clojure.core/nth input__82096 0)
                              input__82096_nth_1__
                              (clojure.core/nth input__82096 1)]
                             (if
                              (clojure.core/seq? input__82096_nth_0__)
                              (clojure.core/let
                               [input__82096_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__82096_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__82096_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__82096_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__82096_nth_0__)]
                                 (clojure.core/let
                                  [?sequence input__82096_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__82096_nth_0__]
                                   (clojure.core/let
                                    [?env input__82096_nth_1__]
                                    (try
                                     [{:tag :string,
                                       :next
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__82152
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
                                (state__83232)))
                              (state__83232)))
                            (state__83232))
                           (state__83232)))))
                       (state__83232)))))
                   (state__83232))))
                (state__83232)))
              (state__83232)))
            (state__83232))
           (state__83232))))
        (state__83232
         []
         (clojure.core/letfn
          [(def__83049
            [arg__83072 ?__82108]
            (clojure.core/letfn
             [(state__83398
               []
               (clojure.core/let
                [x__83073 "meander.zeta"]
                (if
                 (clojure.core/= ?__82108 x__83073)
                 [?__82108]
                 (state__83399))))
              (state__83399
               []
               (if
                (clojure.core/map? arg__83072)
                (clojure.core/let
                 [VAL__83074 (.valAt arg__83072 :aliases)]
                 (if
                  (clojure.core/map? VAL__83074)
                  (clojure.core/let
                   [X__83076 (clojure.core/set VAL__83074)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__83076))
                    (clojure.core/loop
                     [search_space__83400 (clojure.core/seq X__83076)]
                     (if
                      (clojure.core/seq search_space__83400)
                      (clojure.core/let
                       [elem__83077
                        (clojure.core/first search_space__83400)
                        result__83401
                        (clojure.core/let
                         [elem__83077_nth_0__
                          (clojure.core/nth elem__83077 0)
                          elem__83077_nth_1__
                          (clojure.core/nth elem__83077 1)]
                         (if
                          (clojure.core/symbol? elem__83077_nth_0__)
                          (clojure.core/let
                           [X__83079
                            (clojure.core/name elem__83077_nth_0__)]
                           (if
                            (clojure.core/= ?__82108 X__83079)
                            (if
                             (clojure.core/symbol? elem__83077_nth_1__)
                             (clojure.core/let
                              [X__83081
                               (clojure.core/name elem__83077_nth_1__)]
                              (clojure.core/case
                               X__83081
                               ("meander.zeta")
                               [?__82108]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__83401)
                        (recur (clojure.core/next search_space__83400))
                        result__83401))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__83398)))]
          (if
           (clojure.core/vector? input__82096)
           (if
            (clojure.core/= (clojure.core/count input__82096) 2)
            (clojure.core/let
             [input__82096_nth_0__
              (clojure.core/nth input__82096 0)
              input__82096_nth_1__
              (clojure.core/nth input__82096 1)]
             (if
              (clojure.core/seq? input__82096_nth_0__)
              (clojure.core/let
               [input__82096_nth_0___l__
                (clojure.core/take 1 input__82096_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__82096_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__82096_nth_0___r__
                  (clojure.core/drop 1 input__82096_nth_0__)]
                 (clojure.core/let
                  [input__82096_nth_0___l___nth_0__
                   (clojure.core/nth input__82096_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__82096_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__83059
                     (clojure.core/namespace
                      input__82096_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__82108 X__83059]
                     (clojure.core/let
                      [X__83061
                       (clojure.core/name
                        input__82096_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__83061
                       ("symbol")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__83049 input__82096_nth_1__ ?__82108)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__83233)
                         (clojure.core/let
                          [[?__82108] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__82096)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__82096)
                             2)
                            (clojure.core/let
                             [input__82096_nth_0__
                              (clojure.core/nth input__82096 0)
                              input__82096_nth_1__
                              (clojure.core/nth input__82096 1)]
                             (if
                              (clojure.core/seq? input__82096_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__82096_nth_0__)
                                2)
                               (clojure.core/let
                                [input__82096_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__82096_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?name input__82096_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__82096_nth_0__]
                                  (clojure.core/let
                                   [?env input__82096_nth_1__]
                                   (try
                                    [{:tag :symbol,
                                      :name
                                      (clojure.core/let
                                       [CATA_RESULT__9229__auto__
                                        (CATA__FN__82152 [?name ?env])]
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
                               (state__83233))
                              (state__83233)))
                            (state__83233))
                           (state__83233)))))
                       (state__83233)))))
                   (state__83233))))
                (state__83233)))
              (state__83233)))
            (state__83233))
           (state__83233))))
        (state__83233
         []
         (clojure.core/letfn
          [(def__83083
            [arg__83106 ?__82109]
            (clojure.core/letfn
             [(state__83403
               []
               (clojure.core/let
                [x__83107 "meander.zeta"]
                (if
                 (clojure.core/= ?__82109 x__83107)
                 [?__82109]
                 (state__83404))))
              (state__83404
               []
               (if
                (clojure.core/map? arg__83106)
                (clojure.core/let
                 [VAL__83108 (.valAt arg__83106 :aliases)]
                 (if
                  (clojure.core/map? VAL__83108)
                  (clojure.core/let
                   [X__83110 (clojure.core/set VAL__83108)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__83110))
                    (clojure.core/loop
                     [search_space__83405 (clojure.core/seq X__83110)]
                     (if
                      (clojure.core/seq search_space__83405)
                      (clojure.core/let
                       [elem__83111
                        (clojure.core/first search_space__83405)
                        result__83406
                        (clojure.core/let
                         [elem__83111_nth_0__
                          (clojure.core/nth elem__83111 0)
                          elem__83111_nth_1__
                          (clojure.core/nth elem__83111 1)]
                         (if
                          (clojure.core/symbol? elem__83111_nth_0__)
                          (clojure.core/let
                           [X__83113
                            (clojure.core/name elem__83111_nth_0__)]
                           (if
                            (clojure.core/= ?__82109 X__83113)
                            (if
                             (clojure.core/symbol? elem__83111_nth_1__)
                             (clojure.core/let
                              [X__83115
                               (clojure.core/name elem__83111_nth_1__)]
                              (clojure.core/case
                               X__83115
                               ("meander.zeta")
                               [?__82109]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__83406)
                        (recur (clojure.core/next search_space__83405))
                        result__83406))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__83403)))]
          (if
           (clojure.core/vector? input__82096)
           (if
            (clojure.core/= (clojure.core/count input__82096) 2)
            (clojure.core/let
             [input__82096_nth_0__
              (clojure.core/nth input__82096 0)
              input__82096_nth_1__
              (clojure.core/nth input__82096 1)]
             (if
              (clojure.core/seq? input__82096_nth_0__)
              (clojure.core/let
               [input__82096_nth_0___l__
                (clojure.core/take 1 input__82096_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__82096_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__82096_nth_0___r__
                  (clojure.core/drop 1 input__82096_nth_0__)]
                 (clojure.core/let
                  [input__82096_nth_0___l___nth_0__
                   (clojure.core/nth input__82096_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__82096_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__83093
                     (clojure.core/namespace
                      input__82096_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__82109 X__83093]
                     (clojure.core/let
                      [X__83095
                       (clojure.core/name
                        input__82096_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__83095
                       ("symbol")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__83083 input__82096_nth_1__ ?__82109)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__83234)
                         (clojure.core/let
                          [[?__82109] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__82096)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__82096)
                             2)
                            (clojure.core/let
                             [input__82096_nth_0__
                              (clojure.core/nth input__82096 0)
                              input__82096_nth_1__
                              (clojure.core/nth input__82096 1)]
                             (if
                              (clojure.core/seq? input__82096_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__82096_nth_0__)
                                3)
                               (clojure.core/let
                                [input__82096_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__82096_nth_0__
                                  1)
                                 input__82096_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__82096_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?namespace
                                  input__82096_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?name input__82096_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__82096_nth_0__]
                                   (clojure.core/let
                                    [?env input__82096_nth_1__]
                                    (try
                                     [{:tag :symbol,
                                       :name
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__82152
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
                                         (CATA__FN__82152
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
                               (state__83234))
                              (state__83234)))
                            (state__83234))
                           (state__83234)))))
                       (state__83234)))))
                   (state__83234))))
                (state__83234)))
              (state__83234)))
            (state__83234))
           (state__83234))))
        (state__83234
         []
         (clojure.core/letfn
          [(def__83117
            [arg__83140 ?__82110]
            (clojure.core/letfn
             [(state__83408
               []
               (clojure.core/let
                [x__83141 "meander.zeta"]
                (if
                 (clojure.core/= ?__82110 x__83141)
                 [?__82110]
                 (state__83409))))
              (state__83409
               []
               (if
                (clojure.core/map? arg__83140)
                (clojure.core/let
                 [VAL__83142 (.valAt arg__83140 :aliases)]
                 (if
                  (clojure.core/map? VAL__83142)
                  (clojure.core/let
                   [X__83144 (clojure.core/set VAL__83142)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__83144))
                    (clojure.core/loop
                     [search_space__83410 (clojure.core/seq X__83144)]
                     (if
                      (clojure.core/seq search_space__83410)
                      (clojure.core/let
                       [elem__83145
                        (clojure.core/first search_space__83410)
                        result__83411
                        (clojure.core/let
                         [elem__83145_nth_0__
                          (clojure.core/nth elem__83145 0)
                          elem__83145_nth_1__
                          (clojure.core/nth elem__83145 1)]
                         (if
                          (clojure.core/symbol? elem__83145_nth_0__)
                          (clojure.core/let
                           [X__83147
                            (clojure.core/name elem__83145_nth_0__)]
                           (if
                            (clojure.core/= ?__82110 X__83147)
                            (if
                             (clojure.core/symbol? elem__83145_nth_1__)
                             (clojure.core/let
                              [X__83149
                               (clojure.core/name elem__83145_nth_1__)]
                              (clojure.core/case
                               X__83149
                               ("meander.zeta")
                               [?__82110]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__83411)
                        (recur (clojure.core/next search_space__83410))
                        result__83411))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__83408)))]
          (if
           (clojure.core/vector? input__82096)
           (if
            (clojure.core/= (clojure.core/count input__82096) 2)
            (clojure.core/let
             [input__82096_nth_0__
              (clojure.core/nth input__82096 0)
              input__82096_nth_1__
              (clojure.core/nth input__82096 1)]
             (if
              (clojure.core/seq? input__82096_nth_0__)
              (clojure.core/let
               [input__82096_nth_0___l__
                (clojure.core/take 1 input__82096_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__82096_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__82096_nth_0___r__
                  (clojure.core/drop 1 input__82096_nth_0__)]
                 (clojure.core/let
                  [input__82096_nth_0___l___nth_0__
                   (clojure.core/nth input__82096_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__82096_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__83127
                     (clojure.core/namespace
                      input__82096_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__82110 X__83127]
                     (clojure.core/let
                      [X__83129
                       (clojure.core/name
                        input__82096_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__83129
                       ("symbol")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__83117 input__82096_nth_1__ ?__82110)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__83235)
                         (clojure.core/let
                          [[?__82110] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__82096)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__82096)
                             2)
                            (clojure.core/let
                             [input__82096_nth_0__
                              (clojure.core/nth input__82096 0)
                              input__82096_nth_1__
                              (clojure.core/nth input__82096 1)]
                             (if
                              (clojure.core/seq? input__82096_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 5)
                                 input__82096_nth_0__)
                                5)
                               (clojure.core/let
                                [input__82096_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__82096_nth_0__
                                  1)
                                 input__82096_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__82096_nth_0__
                                  2)
                                 input__82096_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__82096_nth_0__
                                  3)
                                 input__82096_nth_0___nth_4__
                                 (clojure.core/nth
                                  input__82096_nth_0__
                                  4)]
                                (clojure.core/case
                                 input__82096_nth_0___nth_3__
                                 (:meander.zeta/as)
                                 (clojure.core/let
                                  [?namespace
                                   input__82096_nth_0___nth_1__]
                                  (clojure.core/let
                                   [?name input__82096_nth_0___nth_2__]
                                   (clojure.core/let
                                    [?pattern
                                     input__82096_nth_0___nth_4__]
                                    (clojure.core/let
                                     [?form input__82096_nth_0__]
                                     (clojure.core/let
                                      [?env input__82096_nth_1__]
                                      (try
                                       [{:tag :symbol,
                                         :name
                                         (clojure.core/let
                                          [CATA_RESULT__9229__auto__
                                           (CATA__FN__82152
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
                                           (CATA__FN__82152
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
                                           (CATA__FN__82152
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
                                 (state__83235)))
                               (state__83235))
                              (state__83235)))
                            (state__83235))
                           (state__83235)))))
                       (state__83235)))))
                   (state__83235))))
                (state__83235)))
              (state__83235)))
            (state__83235))
           (state__83235))))
        (state__83235
         []
         (if
          (clojure.core/vector? input__82096)
          (if
           (clojure.core/= (clojure.core/count input__82096) 2)
           (clojure.core/let
            [input__82096_nth_0__ (clojure.core/nth input__82096 0)]
            (clojure.core/letfn
             [(state__83413
               []
               (clojure.core/let
                [input__82096_nth_1__
                 (clojure.core/nth input__82096 1)]
                (clojure.core/letfn
                 [(state__83418
                   []
                   (if
                    (clojure.core/seq? input__82096_nth_0__)
                    (clojure.core/let
                     [?sequence input__82096_nth_0__]
                     (clojure.core/let
                      [?env input__82096_nth_1__]
                      (try
                       [{:tag :seq,
                         :next
                         (clojure.core/let
                          [CATA_RESULT__9229__auto__
                           (CATA__FN__82152
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
                    (state__83419)))
                  (state__83419
                   []
                   (if
                    (clojure.core/map? input__82096_nth_0__)
                    (clojure.core/let
                     [?map input__82096_nth_0__]
                     (clojure.core/let
                      [?env input__82096_nth_1__]
                      (try
                       [{:tag :map,
                         :next
                         (clojure.core/let
                          [CATA_RESULT__9229__auto__
                           (CATA__FN__82152
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
                    (state__83414)))]
                 (state__83418))))
              (state__83414
               []
               (if
                (clojure.core/symbol? input__82096_nth_0__)
                (clojure.core/let
                 [X__83159
                  (clojure.core/namespace input__82096_nth_0__)]
                 (clojure.core/let
                  [X__83161 (clojure.core/name input__82096_nth_0__)]
                  (if
                   (clojure.core/string? X__83161)
                   (clojure.core/letfn
                    [(state__83420
                      []
                      (clojure.core/let
                       [ret__83162
                        (clojure.core/re-matches #"_.*" X__83161)]
                       (if
                        (clojure.core/some? ret__83162)
                        (clojure.core/let
                         [?name ret__83162]
                         (clojure.core/let
                          [?symbol input__82096_nth_0__]
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
                        (state__83421))))
                     (state__83421
                      []
                      (clojure.core/let
                       [ret__83169
                        (clojure.core/re-matches #".+#" X__83161)]
                       (if
                        (clojure.core/some? ret__83169)
                        (clojure.core/let
                         [?name ret__83169]
                         (clojure.core/let
                          [?symbol input__82096_nth_0__]
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
                        (state__83422))))
                     (state__83422
                      []
                      (clojure.core/let
                       [ret__83176
                        (clojure.core/re-matches #"%.+" X__83161)]
                       (if
                        (clojure.core/some? ret__83176)
                        (clojure.core/let
                         [?name ret__83176]
                         (clojure.core/let
                          [?symbol input__82096_nth_0__]
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
                        (state__83423))))
                     (state__83423
                      []
                      (clojure.core/let
                       [ret__83183
                        (clojure.core/re-matches #"\*.+" X__83161)]
                       (if
                        (clojure.core/some? ret__83183)
                        (clojure.core/let
                         [?name ret__83183]
                         (clojure.core/let
                          [?symbol input__82096_nth_0__]
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
                        (state__83424))))
                     (state__83424
                      []
                      (clojure.core/let
                       [ret__83190
                        (clojure.core/re-matches #"\!.+" X__83161)]
                       (if
                        (clojure.core/some? ret__83190)
                        (clojure.core/let
                         [?name ret__83190]
                         (clojure.core/let
                          [?symbol input__82096_nth_0__]
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
                        (state__83425))))
                     (state__83425
                      []
                      (clojure.core/let
                       [ret__83197
                        (clojure.core/re-matches #"\?.+" X__83161)]
                       (if
                        (clojure.core/some? ret__83197)
                        (clojure.core/let
                         [?name ret__83197]
                         (clojure.core/let
                          [?symbol input__82096_nth_0__]
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
                        (state__83415))))]
                    (state__83420))
                   (state__83415))))
                (state__83415)))
              (state__83415
               []
               (if
                (string? input__82096_nth_0__)
                (clojure.core/let
                 [?x input__82096_nth_0__]
                 (try
                  [{:tag :literal, :type :string, :form ?x}]
                  (catch
                   java.lang.Exception
                   e__10169__auto__
                   (if
                    (meander.runtime.zeta/fail? e__10169__auto__)
                    (meander.runtime.zeta/fail)
                    (throw e__10169__auto__)))))
                (state__83416)))
              (state__83416
               []
               (if
                (char? input__82096_nth_0__)
                (clojure.core/let
                 [?x input__82096_nth_0__]
                 (try
                  [{:tag :literal, :type :char, :form ?x}]
                  (catch
                   java.lang.Exception
                   e__10169__auto__
                   (if
                    (meander.runtime.zeta/fail? e__10169__auto__)
                    (meander.runtime.zeta/fail)
                    (throw e__10169__auto__)))))
                (state__83417)))
              (state__83417
               []
               (clojure.core/let
                [?x input__82096_nth_0__]
                (try
                 [{:tag :literal, :form ?x}]
                 (catch
                  java.lang.Exception
                  e__10169__auto__
                  (if
                   (meander.runtime.zeta/fail? e__10169__auto__)
                   (meander.runtime.zeta/fail)
                   (throw e__10169__auto__))))))]
             (state__83413)))
           (state__83236))
          (state__83236)))
        (state__83236
         []
         (clojure.core/let
          [?x input__82096]
          (try
           [{:tag :mistake, :x ?x}]
           (catch
            java.lang.Exception
            e__10169__auto__
            (if
             (meander.runtime.zeta/fail? e__10169__auto__)
             (meander.runtime.zeta/fail)
             (throw e__10169__auto__))))))]
       (state__83208)))]
    (clojure.core/let
     [x__7926__auto__ (CATA__FN__82152 input__82096)]
     (if
      (meander.runtime.zeta/fail? x__7926__auto__)
      (meander.runtime.zeta/fail)
      (clojure.core/let
       [[CATA_RETURN__82154] x__7926__auto__]
       CATA_RETURN__82154))))]
  (if
   (meander.runtime.zeta/fail? ret__8106__auto__)
   nil
   ret__8106__auto__)))
